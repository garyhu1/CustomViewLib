package com.garyhu.svgdemo.animation;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.View;

/**
 * 作者： garyhu.
 * 时间： 2017/8/15.
 * ViewPager的自定义动画
 */

public class RotatePageTransformer implements ViewPager.PageTransformer {

    /**
     可以看到该接口只有一个方法，第一个是我们的view，第二个是position~~
     当我们滑动时：会打印出当然ViewPager中存活的每个View以及它们的position的变化~~
     注意是每一个，所以建议别只log position，不然你会觉得莫名其妙的输出~~
     position的可能性的值有，其实从官方示例的注释就能看出：
     [-Infinity,-1)  已经看不到了
     (1,+Infinity] 已经看不到了
     [-1,1]
     重点看[-1,1]这个区间 ， 其他两个的View都已经看不到了~~

     假设现在ViewPager在A页现在滑出B页，则:
     A页的position变化就是( 0, -1]
     B页的position变化就是[ 1 , 0 ]
     知道了我们滑动时position的变化~~那么就开始设计我们的个性的切换效果；
     */
    private static final float ROT_MAX = 20.0f;
    private float mRot;

    @Override
    public void transformPage(View view, float position) {
        Log.e("TAG", view + " , " + position + "");

        if (position < -1)
        { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotation(0);

        } else if (position <= 1) // a页滑动至b页 ； a页从 0.0 ~ -1 ；b页从1 ~ 0.0
        { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            if (position < 0)
            {

                mRot = (ROT_MAX * position);
                //设置旋转中心
                view.setPivotX(view.getMeasuredWidth() * 0.5f);
                view.setPivotY(view.getMeasuredHeight());
                view.setRotation(mRot);
            } else
            {

                mRot = (ROT_MAX * position);
                //设置旋转中心
                view.setPivotX(view.getMeasuredWidth() * 0.5f);
                view.setPivotY(view.getMeasuredHeight());
                view.setRotation(mRot);
            }

            // Scale the page down (between MIN_SCALE and 1)

            // Fade the page relative to its size.

        } else
        { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotation(0);
        }
    }
}
