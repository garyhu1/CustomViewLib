package com.garyhu.svgdemo.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.garyhu.svgdemo.R;

/**
 * Created by garyhu
 * on 2017/9/12.
 * 路径动画的演示
 */

public class PathMeasureAnim extends View{

    private Paint mPaint;

    private Path innerCircle;//内圆的路径path
    private Path outterCircle;//外圆的路径path
    private Path trangle1;//第一个三角形的path
    private Path trangle2;//第二个三角形的path
    private Path drawPath;//用于截取路径的path

    private PathMeasure pathMeasure;

    private float mViewWidth;
    private float mViewHeight;

    private long duration = 3000;
    private ValueAnimator valueAnimator;

    private Handler mHandler;

    private float distance;//当前动画执行的百分比取值为0-1
    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
    private Animator.AnimatorListener animatorListener;

    private State mCurrentState = State.CIRCLE_STATE;

    //三个阶段的枚举
    private enum State {
        CIRCLE_STATE,
        TRANGLE_STATE,
        FINISH_STATE
    }

    public PathMeasureAnim(Context context) {
        this(context,null);
    }

    public PathMeasureAnim(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasureAnim(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布的颜色
        canvas.drawColor(getResources().getColor(R.color.colorPrimary));
        //保存画布状态
        canvas.save();
        //把画布的原点移动到中心的位置（默认为（0,0））
        canvas.translate(mViewWidth/2,mViewHeight/2);
        //开始绘画
        drawMyPath(canvas);
        //把canvas的状态返回到save之前的状态
        canvas.restore();
    }

    private void drawMyPath(Canvas canvas){
        switch(mCurrentState){
            //默认首先绘画外部两个圆
            case CIRCLE_STATE:
                //重新设置，清空path
                drawPath.reset();
                //测量innerCircle路径,false表示路径不自动闭合
                pathMeasure.setPath(innerCircle,false);
                //截取某段路径，并赋值给drawPath,true表示把截取的路径moveTo到起点
                pathMeasure.getSegment(0,distance*pathMeasure.getLength(),drawPath,true);
                canvas.drawPath(drawPath,mPaint);
                pathMeasure.setPath(outterCircle,false);
                drawPath.reset();
                pathMeasure.getSegment(0,distance*pathMeasure.getLength(),drawPath,true);
                canvas.drawPath(drawPath,mPaint);
                break;
            //绘画内部三角形片段
            case TRANGLE_STATE:
                //首先要把外部的两个圆先画好
                canvas.drawPath(innerCircle,mPaint);
                canvas.drawPath(outterCircle,mPaint);
                drawPath.reset();
                //测量路径trangle1
                pathMeasure.setPath(trangle1,false);
                //获取路径的全长
                float stopD = distance*pathMeasure.getLength();
                //获取开始的位置
                float startD = stopD - (0.5f-Math.abs(0.5f-distance))*200f;
                //截取部分路径，并moveTo起始点位置（即startD的位置）
                pathMeasure.getSegment(startD,stopD,drawPath,true);
                canvas.drawPath(drawPath,mPaint);
                drawPath.reset();
                pathMeasure.setPath(trangle2,false);
                pathMeasure.getSegment(startD,stopD,drawPath,true);
                canvas.drawPath(drawPath,mPaint);
                break;
            //绘画内部三角形
            case FINISH_STATE:
                //首先把外部两个圆画出来
                canvas.drawPath(innerCircle,mPaint);
                canvas.drawPath(outterCircle,mPaint);
                drawPath.reset();
                //测量trangle1路径
                pathMeasure.setPath(trangle1,false);
                pathMeasure.getSegment(0,distance*pathMeasure.getLength(),drawPath,true);
                canvas.drawPath(drawPath,mPaint);
                drawPath.reset();
                pathMeasure.setPath(trangle2,false);
                pathMeasure.getSegment(0,distance*pathMeasure.getLength(),drawPath,true);
                canvas.drawPath(drawPath,mPaint);
                break;
        }
    }

    private void init(){
        initPaint();

        initPath();

        initHandler();

        initAnimationListener();

        initAnimator();

        mCurrentState = State.CIRCLE_STATE;
        if(valueAnimator!=null){
            valueAnimator.start();
        }
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        //设置阴影；参数一：阴影的范围radius；参数二、三：x、y的偏移量；参数四：阴影的颜色
        mPaint.setShadowLayer(15,0,0,Color.WHITE);
    }

    private void initPath(){
        innerCircle = new Path();
        outterCircle = new Path();
        trangle1 = new Path();
        trangle2 = new Path();
        drawPath = new Path();

        pathMeasure = new PathMeasure();

        RectF innerRect = new RectF(-220,-220,220,220);
        RectF outterRect = new RectF(-280,-280,280,280);
        //不能为360f,否则测量的值不准确，找不到起始点
        innerCircle.addArc(innerRect,150,-359.9f);
        outterCircle.addArc(outterRect,60,-359.9f);

        pathMeasure.setPath(innerCircle,false);

        float[] pos = new float[2];
        //获取开始位置的坐标，参数三是x、y的正切值
        pathMeasure.getPosTan(0,pos,null);
        trangle1.moveTo(pos[0],pos[1]);
        //获取1/3长度的point的位置的坐标并赋值给pos数组
        pathMeasure.getPosTan((1f/3f)*pathMeasure.getLength(),pos,null);

        trangle1.lineTo(pos[0],pos[1]);
        pathMeasure.getPosTan((2f/3f)*pathMeasure.getLength(),pos,null);
        trangle1.lineTo(pos[0],pos[1]);
        trangle1.close();

        //旋转180度后变换矩阵，并赋值给trangle2
        pathMeasure.getPosTan((2f/3f)*pathMeasure.getLength(),pos,null);
        Matrix matrix = new Matrix();
        matrix.postRotate(180);
        trangle1.transform(matrix,trangle2);

    }

    private void initHandler(){
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch(mCurrentState){
                    //完成外部两个圆的绘画后，改变状态，重新执行动画
                    case CIRCLE_STATE:
                        //改变状态，为下个状态进行准备
                        mCurrentState = State.TRANGLE_STATE;
                        valueAnimator.start();
                        break;
                    //完成内部三角形的片段绘画后，改变状态，重新执行动画
                    case TRANGLE_STATE:
                        //改变状态，为下个状态做准备，绘画出完整的两个三角形的动画
                        mCurrentState = State.FINISH_STATE;
                        valueAnimator.start();
                        break;
                    //完成三角形的绘画后，把动画属性设置为null，释放内存
                    case FINISH_STATE:
                        if(valueAnimator!=null){
                            valueAnimator = null;
                        }
                        break;
                }
            }
        };
    }

    private void initAnimator(){
        valueAnimator = ObjectAnimator.ofFloat(0,1).setDuration(duration);
        valueAnimator.addUpdateListener(animatorUpdateListener);
        valueAnimator.addListener(animatorListener);

    }

    private void initAnimationListener(){
        animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取比例（0-1）
                distance = ((float) animation.getAnimatedValue());
                invalidate();
            }
        };

        animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //结束一个动画后立刻
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }
}
