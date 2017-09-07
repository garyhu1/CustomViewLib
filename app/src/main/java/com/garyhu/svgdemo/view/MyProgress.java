package com.garyhu.svgdemo.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.garyhu.svgdemo.R;

/**
 * 作者： garyhu.
 * 时间： 2017/8/10.
 * 圆形进度条
 */

public class MyProgress extends View {

    private Paint mPaintBg;//圆的背景色画笔
    private Paint mPaintArc;//圆弧的画笔
    private Paint mPaintText;//文字的画笔
    private int mArcWidth;//最外层圆弧的宽度
    private int mCircleRadius;//内部圆的半径
    private int mBgColor = Color.parseColor("#50980D");//默认的背景色
    private int mArcColor = Color.parseColor("#55B2E5");//默认的圆弧的颜色
    private int mTextSize = sp2px(getContext(),14);//默认字体的大小
    private int mTextColor = Color.parseColor("#111111");//默认字体的颜色
    private int mAnimType ;//动画进退的方式
    private int mDuration = 3000;//动画执行时长
    private String mAnimUnit = "s";//动画单位
    private String mText ;//需要画的文字

    private float mStartAngle;//开始角度
    private float mEndAngle;//结束角度
    private float mSweepStartAngle;//开始旋转的角度
    private float mSweepAngle;//旋转的角度

    private OnLoadFinishListener onLoadFinishListener;//用于完成加载完成后的回调接口

    public MyProgress(Context context) {
        this(context,null);
    }

    public MyProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context,AttributeSet attrs){
        if(attrs==null){
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyProgress);
        mBgColor = typedArray.getColor(R.styleable.MyProgress_cd_bg_color,mBgColor);
        mArcColor = typedArray.getColor(R.styleable.MyProgress_cd_arc_color,mArcColor);
        mAnimType = typedArray.getInt(R.styleable.MyProgress_cd_retreat_type,1);
        mAnimUnit = typedArray.getString(R.styleable.MyProgress_cd_animator_time_unit);
        mArcWidth = (int) typedArray.getDimension(R.styleable.MyProgress_cd_arc_width,dp2px(getContext(),5));
        mCircleRadius = typedArray.getInt(R.styleable.MyProgress_cd_circle_radius,dp2px(getContext(),50));
        mTextColor = typedArray.getColor(R.styleable.MyProgress_cd_text_color,mTextColor);
        mTextSize = typedArray.getInt(R.styleable.MyProgress_cd_text_size,mTextSize);
        mDuration = typedArray.getInt(R.styleable.MyProgress_cd_animator_time,mDuration);
        mAnimUnit = typedArray.getString(R.styleable.MyProgress_cd_animator_time_unit);
        typedArray.recycle();

        mPaintArc = new Paint();
        mPaintArc.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaintArc.setAntiAlias(true);
        mPaintArc.setStyle(Paint.Style.STROKE);
        mPaintArc.setColor(mArcColor);
        mPaintArc.setStrokeCap(Paint.Cap.ROUND);
        mPaintArc.setStrokeWidth(mArcWidth);

        mPaintBg = new Paint();
        mPaintBg.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Paint.Style.FILL);
        mPaintBg.setColor(mBgColor);

        mPaintText = new Paint();
        mPaintText.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setColor(mTextColor);
        mPaintText.setTextSize(mTextSize);

        if(mAnimType == 1){
            mStartAngle = -90;
            mEndAngle = 360;
        }else {
            mStartAngle = 360;
            mSweepStartAngle=360;
            mEndAngle = 0;
        }
        if("s".equals(mAnimUnit)){
            mText = mDuration/1000+mAnimUnit;
        }else if("ms".equals(mAnimUnit)){
            mText = mDuration+mAnimUnit;
        }
    }

    public void setOnFinishListener(OnLoadFinishListener onLoadFinishListener){
        this.onLoadFinishListener = onLoadFinishListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mCircleRadius*2,mCircleRadius*2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleRadius,mCircleRadius,mCircleRadius,mPaintBg);

        RectF rf = new RectF(mArcWidth/2,mArcWidth/2,mCircleRadius*2-mArcWidth/2,mCircleRadius*2-mArcWidth/2);
        canvas.drawArc(rf,mStartAngle,mSweepAngle,false,mPaintArc);

        float mTextWidth = mPaintText.measureText(mText,0,mText.length());
        float dx = mCircleRadius-mTextWidth/2;
        Paint.FontMetricsInt pf = mPaintText.getFontMetricsInt();
        float dy = (pf.bottom-pf.top)/2-pf.bottom;
        float baseLine = mCircleRadius+dy;
        canvas.drawText(mText,dx,baseLine,mPaintText);
    }

    public void startAnim(){
        ValueAnimator anim1 = ValueAnimator.ofFloat(mSweepStartAngle,mEndAngle);
        anim1.setInterpolator(new LinearInterpolator());
        anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mSweepAngle = animatedValue;
                invalidate();
            }
        });

        ValueAnimator anim2 = ValueAnimator.ofInt(mDuration/1000,0);
        anim2.setInterpolator(new LinearInterpolator());
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                int time = animatedValue;
                mText = time+mAnimUnit;
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new LinearInterpolator());
        set.setDuration(mDuration);
        set.playTogether(anim1,anim2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                if(onLoadFinishListener!=null){
                    onLoadFinishListener.onFinishLoad();
                }
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    /** 完成加载后的回调*/
    public interface OnLoadFinishListener{
        void onFinishLoad();
    }

    public int dp2px (Context context,int dpValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density*dpValue+0.5f);
    }

    public int px2dp (Context context,float pxValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/density+0.5f);
    }

    public int sp2px(Context context,int spValue){
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (density*spValue+0.5f);
    }

    public int px2sp(Context context,int pxValue){
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/density+0.5f);
    }
}
