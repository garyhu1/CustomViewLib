package com.garyhu.svgdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者： garyhu.
 * 时间： 2017/8/11.
 * 仿扣扣的气泡拖拽
 */

public class MessageBubbleView extends View {

    private PointF mFixationPoint,mDragPoint;
    private Paint mPaint;
    private Path mPath;
    private float mDragRadius = 10;
    private float mFixationRadius = 10;
    private float mMaxRadius = 10;
    private float mMinRadius = 3;

    public MessageBubbleView(Context context) {
        this(context,null);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs){
        initPoint(-dp2px(10),-dp2px(10));

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);

        mDragRadius = dp2px(mDragRadius);
        mFixationRadius = dp2px(mFixationRadius);
        mMinRadius = dp2px(mMinRadius);
        mMaxRadius = dp2px(mMaxRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mDragPoint.x,mDragPoint.y,mDragRadius,mPaint);

        float distance = (float) Math.sqrt((mDragPoint.x-mFixationPoint.x)*(mDragPoint.x-mFixationPoint.x)+(mDragPoint.y-mFixationPoint.y)*(mDragPoint.y-mFixationPoint.y));
        mFixationRadius = mMaxRadius -distance/14;
        if(mFixationRadius>distance/14){
            canvas.drawCircle(mFixationPoint.x,mFixationPoint.y,mFixationRadius,mPaint);
            mPath = getPath();
            canvas.drawPath(mPath,mPaint);
        }
    }

    private Path getPath() {
        Path path = new Path();
        //求角度
        float dy = mDragPoint.y-mFixationPoint.y;
        float dx = mDragPoint.x-mFixationPoint.x;
        double tanA = dy/dx;
        float atan = (float) Math.atan(tanA);
        //p0
        float p0x = (float) (mFixationPoint.x+mFixationRadius*Math.sin(atan));
        float p0y = (float) (mFixationPoint.y-mFixationRadius*Math.cos(atan));
        //p1
        float p1x = (float) (mDragPoint.x+mDragRadius*Math.sin(atan));
        float p1y = (float) (mDragPoint.y-mDragRadius*Math.cos(atan));
        //p2x
        float p2x = (float) (mDragPoint.x-mDragRadius*Math.sin(atan));
        float p2y = (float) (mDragPoint.y+mDragRadius*Math.cos(atan));
        //p3x
        float p3x = (float) (mFixationPoint.x-mFixationRadius*Math.sin(atan));
        float p3y = (float) (mFixationPoint.y+mFixationRadius*Math.cos(atan));
        //获取控制点
        PointF controlPoint = getControlPoint(dx,dy);
        path.moveTo(p0x,p0y);
        path.quadTo(controlPoint.x,controlPoint.y,p1x,p1y);
        path.lineTo(p2x,p2y);
        path.quadTo(controlPoint.x,controlPoint.y,p3x,p3y);
        path.close();
        return path;
    }

    private PointF getControlPoint(float x,float y) {
        return new PointF(x/2+mFixationPoint.x,y/2+mFixationPoint.y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                initPoint(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                updatePoint(moveX,moveY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    private void initPoint(float x, float y) {
        mFixationPoint = new PointF(x,y);
        mDragPoint = new PointF(x,y);
    }

    private void updatePoint(float moveX, float moveY) {
        mDragPoint.x = moveX;
        mDragPoint.y = moveY;
    }

    public float dp2px(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }
}
