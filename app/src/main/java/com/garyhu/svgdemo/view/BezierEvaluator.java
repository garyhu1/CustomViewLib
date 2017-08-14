package com.garyhu.svgdemo.view;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * 作者： garyhu.
 * 时间： 2017/8/11.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF pointF1,pointF2;

    public BezierEvaluator(PointF pointF1,PointF pointF2){
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float t, PointF p0, PointF p3) {
        //t 的范围(0,1)
        PointF pointF = new PointF();

        pointF.x = p0.x*(1-t)*(1-t)*(1-t)+
                3*pointF1.x*t*(1-t)*(1-t)+
                3*pointF2.x*t*t*(1-t)+
                p3.x*t*t*t;
        pointF.y = p0.y*(1-t)*(1-t)*(1-t)+
                3*pointF1.y*t*(1-t)*(1-t)+
                3*pointF2.y*t*t*(1-t)+
                p3.y*t*t*t;

        return pointF;
    }
}
