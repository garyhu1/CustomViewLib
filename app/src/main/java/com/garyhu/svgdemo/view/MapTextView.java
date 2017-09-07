package com.garyhu.svgdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.garyhu.svgdemo.R;


/**
 * Created by garyhu
 * on 2017/9/7.
 * method: 一个圆形的TextView
 */

public class MapTextView extends View {

    private boolean mIsUniformColor;
    /**
     * 圆的颜色
     */
    private int mCircleColor;
    /**
     * 圆的下部分的背景色
     */
    private int mKeyBackGroundColor;
    /**
     * 圆的下部分关键词的颜色
     */
    private int mKeyTextColor;
    /**
     * 圆上部分value的字体颜色
     */
    private int mValueTextColor;
    /**
     * 关键词的字体大小
     */
    private float mKeyTextSize;
    /**
     * value的的字体大小
     */
    private float mValueTextSize;
    /**
     * 圆的大小
     */
    private int mViewSize;
    /**
     * 视图的宽
     */
    private int mWidth;
    /**
     * 视图的高
     */
    private int mHight;
    /**
     * value文本
     */
    private String mValueText;
    /**
     * key文本
     */
    private String mKeyText;
    /**
     * 画笔 （都用一个画笔，通过改变画笔的属性来绘画不同图形）
     */
    private Paint mPaint;

    public MapTextView(Context context) {
        this(context,null);
    }

    public MapTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MapTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    /**
     * 初始化参数
     * @param context
     * @param attrs
     */
    public void init(Context context,AttributeSet attrs){
        if(attrs==null)
            return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MapTextView);

        mValueText = typedArray.getString(R.styleable.MapTextView_valueText);
        mKeyText = typedArray.getString(R.styleable.MapTextView_keyText);

        mIsUniformColor = ((boolean) typedArray.getBoolean(R.styleable.MapTextView_isUniformColor, true));
        int colorAccent = context.getResources().getColor(R.color.colorAccent);
        mCircleColor = typedArray.getColor(R.styleable.MapTextView_circleColor,colorAccent);
        if (mIsUniformColor){
            mKeyBackGroundColor = mCircleColor;
            mKeyTextColor = Color.WHITE;
            mValueTextColor = mCircleColor;
        }else {
            mKeyBackGroundColor = typedArray.getColor(R.styleable.MapTextView_keyBackgroundColor, colorAccent);
            mKeyTextColor = typedArray.getColor(R.styleable.MapTextView_keyTextColor, Color.WHITE);
            mValueTextColor = typedArray.getColor(R.styleable.MapTextView_valueTextColor, colorAccent);
        }
        mKeyTextSize = typedArray.getDimension(R.styleable.MapTextView_keyText_size,0);
        mValueTextSize = typedArray.getDimension(R.styleable.MapTextView_valueText_size,0);
        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(mCircleColor);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewSize = Math.min(w,h);
        mWidth = w;
        mHight = h;
        if (mKeyTextSize == 0.0) {
            mKeyTextSize = mViewSize/10;
        }
        if (mValueTextSize == 0.0) {
            mValueTextSize = mViewSize/4;
        }
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*移动画布的位置*/
        canvas.translate(mWidth/2,mHight/2);

        /*画外层的圆*/
        int radius = mViewSize / 2;
        canvas.drawCircle(0, 0, radius, mPaint);

        /*改变画笔的样式和颜色*/
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mKeyBackGroundColor);

        /*画底部的圆弧*/
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        canvas.drawArc(rectF, 20, 140, false, mPaint);

        /*调整画布*/
        float pathStartX = (float) Math.sqrt(radius * radius - mValueTextSize * mValueTextSize);
        canvas.translate(-pathStartX, 0);
        float pathEndX = pathStartX * 2;
        Path path = new Path();
        path.lineTo(pathEndX, 0);

        /*改变画笔*/
        mPaint.setTextSize(mValueTextSize);
        mPaint.setColor(mValueTextColor);
        mPaint.setTextAlign(Paint.Align.CENTER);
        /*画value文本*/
        String valueText = TextUtils.isEmpty(mValueText)?"love":mValueText;
        canvas.drawTextOnPath(valueText, path, 0, 0, mPaint);

        /*调整画布之前的一些算法*/
        double sin20 = Math.sin(Math.PI * 20 / 180);
        double radiusDistance = sin20 * radius;
        double v = (radius - radiusDistance) / 2;
        double distanceX = Math.sqrt(radius * radius - (v + radiusDistance) * (v + radiusDistance));
        /*调整画布*/
        canvas.translate(pathStartX, 0);
        canvas.translate(0, ((float) (v + radiusDistance)));
        canvas.translate((float) -distanceX, 0);
        /* 文字的路径*/
        Path bottomTextPath = new Path();
        bottomTextPath.lineTo((float) (distanceX * 2), 0);
        /*改变画笔*/
        mPaint.setColor(mKeyTextColor);
        mPaint.setTextSize((float) Math.min(mKeyTextSize, v));
        /*画key文本*/
        String keyText = TextUtils.isEmpty(mKeyText)?"map_text_view":mKeyText;
        canvas.drawTextOnPath(keyText, bottomTextPath, 0, 0, mPaint);
    }

    /**
     * 设置value的文本
     */
    public void setValueText(String valueText) {
        mValueText = valueText;
        invalidate();
    }

    /**
     * 设置key的文本
     */
    public void setKeyText(String keyText) {
        mKeyText = keyText;
        invalidate();
    }
}
