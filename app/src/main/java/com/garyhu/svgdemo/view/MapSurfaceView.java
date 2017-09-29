package com.garyhu.svgdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.garyhu.svgdemo.R;


/**
 * Created by garyhu
 * on 2017/8/23.
 */

public class MapSurfaceView extends View {

    private Bitmap mBitmap;
    private Paint mBitPaint;
    private Rect mSrcRect, mDestRect;

    private GestureDetector mDetector;

    // view 的宽高
    private int mTotalWidth, mTotalHeight;

    private Matrix matrix;

    public MapSurfaceView(Context context) {
        this(context,null);
    }

    public MapSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MapSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置为可点击,否则只会出现onDown、onShowPress、onLongPress
        setLongClickable(true);
        matrix = new Matrix();
        initBitmap();
        initPaint();
        mDetector = new GestureDetector(context, new MyGesture());
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mDetector.onTouchEvent(motionEvent);
            }
        });
    }

    private void initPaint() {
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setStrokeCap(Paint.Cap.ROUND);
        mBitPaint.setDither(true);
    }

    private void initBitmap() {
        mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.p_1)).getBitmap();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mSrcRect = new Rect(0,0,mTotalWidth,mTotalHeight);
        mDestRect = new  Rect(0,0,mTotalWidth,mTotalHeight);
        initDraw();
    }

    private void initDraw() {
        matrix.reset();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        if(width>mTotalWidth||height>mTotalHeight){
            if(width-mTotalWidth>height-mTotalHeight){
                float scaleRatio = mTotalWidth / (width * 1.0f);
                matrix.postScale(scaleRatio,scaleRatio);
                float translateY = (mTotalHeight-(width*scaleRatio))/2f;
                matrix.postTranslate(0,translateY);
            }else {
                float scaleRatio = mTotalHeight/(height*1.0f);
                matrix.postScale(scaleRatio,scaleRatio);
                float translateX = (mTotalWidth-(height*scaleRatio))/2f;
                matrix.postTranslate(translateX,0);
            }
        }else {
            float translateX = (mTotalWidth-height)/2f;
            float translateY = (mTotalHeight-width)/2f;
            matrix.postScale(translateX,translateY);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap,matrix,null);
    }
}
