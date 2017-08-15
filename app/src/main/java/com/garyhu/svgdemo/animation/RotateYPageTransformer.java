package com.garyhu.svgdemo.animation;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 作者： garyhu.
 * 时间： 2017/8/15.
 */

public class RotateYPageTransformer implements ViewPager.PageTransformer {

    private static final float ROTATE_ANGLE = -20.0f;
    private float mRot;

    @Override
    public void transformPage(View view, float position) {
        if(position<-1){
            view.setRotationY(0);
        }else if(position<=1){
            if(position<0){
                mRot = ROTATE_ANGLE*position;
                view.setRotationY(mRot);
            }else {
                mRot = ROTATE_ANGLE*position;
                view.setRotationY(mRot);
            }
        }else {
            view.setRotationY(0);
        }
    }
}
