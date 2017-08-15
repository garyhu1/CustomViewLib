package com.garyhu.svgdemo.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.utils.DensityUtils;

/**
 * 作者： garyhu.
 * 时间： 2017/8/14.
 */

public class QQStepView extends View {

    private String mText;//中间的步数文本
    private int mTextColor = Color.parseColor("#ff0000");//默认中间文本颜色
    private float mTextSize = DensityUtils.sp2px(getContext(),14);//默认的文本字体大小
    private Rect mRect;//文字的边框
    private int mInnerColor = Color.parseColor("#000000");//内部圆弧的颜色
    private int mOuterColor = Color.parseColor("#De5931");//外部圆弧的默认颜色
    private float mBorderWidth = DensityUtils.dp2px(getContext(),8);//默认圆弧的宽度
    private Paint mOuterPaint;//外部圆弧的画笔
    private Paint mInnerPaint;//内部圆弧的画笔
    private Paint mTextPaint;//文本的画笔

    private int mCenterX,mCenterY;//圆心点
    private int mRadius;//圆弧半径

    private int mCurrentStep,mMaxStep;
    private float mSweepAngle;//旋转的角度

    public QQStepView(Context context) {
        this(context,null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if(attrs == null){
            return;
        }

        TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mTextColor = td.getColor(R.styleable.QQStepView_stepTextColor,mTextColor);
        mTextSize = td.getDimension(R.styleable.QQStepView_stepTextSize,mTextSize);
        mBorderWidth = td.getDimension(R.styleable.QQStepView_borderWidth,mBorderWidth);
        mInnerColor = td.getColor(R.styleable.QQStepView_innerColor,mInnerColor);
        mOuterColor = td.getColor(R.styleable.QQStepView_outerColor,mOuterColor);
        td.recycle();

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setColor(mOuterColor);
        mOuterPaint.setStrokeWidth(mBorderWidth);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOuterPaint.setStyle(Paint.Style.STROKE);

        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mRect = new Rect();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //判断宽高的模式
        int w = (int) (MeasureSpec.getSize(widthMeasureSpec));
        int h = (int) (MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(w>h?h:w,w>h?h:w);
    }

    public int measureDimensions(int measureSpec){
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else {
            result = 2*mRadius;
            if(MeasureSpec.AT_MOST == mode){
                result = Math.min(result,size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //内部圆弧
        RectF rectF = new RectF(mBorderWidth/2,mBorderWidth/2,getWidth()-mBorderWidth/2,getHeight()-mBorderWidth/2);
        canvas.drawArc(rectF,135,270,false,mInnerPaint);

        if(mMaxStep==0){
            return;
        }
        canvas.drawArc(rectF,135,mSweepAngle*270,false,mOuterPaint);

        mText = mCurrentStep+"";
        mTextPaint.getTextBounds(mText,0,mText.length(),mRect);
        //获取基线baseline
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float dy = (fm.bottom-fm.top)/2-fm.bottom;
        float baseline = getHeight()/2+dy;
        canvas.drawText(mText,getWidth()/2-mRect.width()/2,baseline,mTextPaint);
    }

    public void setCurrentStep(float cur,final float max){
        mMaxStep = (int) max;
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, cur);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mCurrentStep = (int) animatedValue;
                mSweepAngle = animatedValue/max;
                invalidate();
            }
        });
    }
}
