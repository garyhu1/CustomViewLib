package com.se.map.semap.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by garyhu
 * on 2017/8/28.
 */

public class MapView extends LinearLayout {

    /*

mScroller.getCurrY() //获取mScroller当前竖直滚动的位置
mScroller.getFinalX() //获取mScroller最终停止的水平位置
mScroller.getFinalY() //获取mScroller最终停止的竖直位置
mScroller.setFinalX(int newX) //设置mScroller最终停留的水平位置，没有动画效果，直接跳到目标位置
mScroller.setFinalY(int newY) //设置mScroller最终停留的竖直位置，没有动画效果，直接跳到目标位置

//滚动，startX, startY为开始滚动的位置，dx,dy为滚动的偏移量, duration为完成滚动的时间
mScroller.startScroll(int startX, int startY, int dx, int dy) //使用默认完成时间250ms
mScroller.startScroll(int startX, int startY, int dx, int dy, int duration)

mScroller.computeScrollOffset() //返回值为boolean，true说明滚动尚未完成，false说明滚动已经完成。
这是一个很重要的方法，通常放在View.computeScroll()中，用来判断是否滚动是否结束。
     */

    private Scroller mScroller;

    public MapView(Context context) {
        this(context,null);
    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    public void smoothScrollTo(int fx,int fy){
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx,dy);
    }

    public void smoothScrollBy(int dx,int dy){
        mScroller.startScroll(mScroller.getFinalX(),mScroller.getStartY(),dx,dy);
        invalidate();
    }


}
