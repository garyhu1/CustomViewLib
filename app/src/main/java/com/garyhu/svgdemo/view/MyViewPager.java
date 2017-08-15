package com.garyhu.svgdemo.view;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.Map;

/**
 * 作者： garyhu.
 * 时间： 2017/8/15.
 * 自定义的ViewPager，通过实现监听来实现动画
 */

public class MyViewPager extends ViewPager implements ViewPager.OnPageChangeListener{

    private View mLeft;
    private View mRight;

    private float mTrans;
    private float mScale;

    private static final float MIN_SCALE = 0.5f;

    private Map<Integer,View> mViews = new ArrayMap<>();

    public MyViewPager(Context context) {
        this(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setViewOfPosition(int position,View view){
        mViews.put(position,view);
    }

    public void removeViewOfPosition(int position){
        mViews.remove(mViews.get(position));
    }

    @Override
    public void onPageScrolled(int position, float offset, int offsetPixels) {
        /*
        从打印结果看：无论左右滑动页面，显示的一直是左边的图片，即position的值为左边的图片位置，
        offset的变化是:从左到右时，offset的值是从0到1的变化
        */
        mLeft = mViews.get(position);
        mRight = mViews.get(position+1);

        animteStack(mLeft,mRight,offset,offsetPixels);
        super.onPageScrolled(position,offset,offsetPixels);
    }

    private void animteStack(View left, View right, float offset, int offsetPixels) {
        if(right!=null){
            //从0页到1页时，offset是从0-1的
            mScale = offset*MIN_SCALE+MIN_SCALE;
            mTrans = -getWidth()-getPageMargin()+offsetPixels;

            right.setScaleX(mScale);
            right.setScaleY(mScale);

            right.setTranslationX(mTrans);
        }
        if(left!=null){
            left.bringToFront();//保证left的视图始终在上面
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
