package com.garyhu.svgdemo.view;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by garyhu
 * on 2017/8/28.
 */

public class MyGesture implements GestureDetector.OnGestureListener {
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        //用户按下屏幕就会触发
        Log.d("garyhu","onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        //如果按下的时间超过瞬间，而且在按下的时候没有松开或者拖动，就会触发
        Log.d("garyhu","onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        //从名字也可以看出,一次单独的轻击抬起操作,也就是轻击一下屏幕，立刻抬起来，才会有这个触发，
        // 当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以也就不会触发这个事件
        //触发顺序：
        //点击一下非常快的（不滑动）Touchup：
        //onDown->onSingleTapUp->onSingleTapConfirmed
        //点击一下稍微慢点的（不滑动）Touchup：
        //onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
        Log.d("garyhu","onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //在屏幕上拖动事件。无论是用手拖动view，或者是以抛的动作滚动，都会多次触发,这个方法 在ACTION_MOVE动作发生时就会触发 滑屏：
        // 手指触动屏幕后，稍微滑动后立即松开
        //onDown-----》onScroll----》onScroll----》onScroll----》………----->onFling
        //        拖动
        //onDown------》onScroll----》onScroll------》onFiling

        //可见，无论是滑屏，还是拖动，影响的只是中间OnScroll触发的数量多少而已，最终都会触发onFling事件！
        Log.d("garyhu","onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        //长按屏幕一定时间就会触发该事件，
        //执行的顺序： onDown-->onShowPress-->onLongPress
        Log.d("garyhu","onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //滑屏，用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
        //参数解释：
        //e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent
        //velocityX：X轴上的移动速度，像素/秒
        //velocityY：Y轴上的移动速度，像素/秒
        Log.d("garyhu","onFling");
        return true;
    }
}
