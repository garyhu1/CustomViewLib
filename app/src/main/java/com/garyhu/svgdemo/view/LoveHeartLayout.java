package com.garyhu.svgdemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.BezierEvaluator;

import java.util.Random;

/**
 * 作者： garyhu.
 * 时间： 2017/8/11.
 * 仿点赞效果
 */

public class LoveHeartLayout extends RelativeLayout {

    private Random mRandom = new Random();
    private int mWidth,mHeight;
    private Drawable mRed,mBlue,mYellow,mGreen,mPink,mDarkGreen;
    private Drawable[] mDrawables ;
    private int mDrawableWidth,mDrawableHeight;
    private LayoutParams params;

    private Interpolator[] mInterpolator;

    public LoveHeartLayout(Context context) {
        this(context,null);
    }

    public LoveHeartLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoveHeartLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        initDrawable();
        initInterpolator();
        params = new LayoutParams(mDrawableWidth,mDrawableHeight);
        params.addRule(CENTER_HORIZONTAL,TRUE);
        params.addRule(ALIGN_PARENT_BOTTOM,TRUE);
    }

    public void initDrawable(){
        mRed = getContext().getResources().getDrawable(R.drawable.red);
        mBlue = getContext().getResources().getDrawable(R.drawable.blue);
        mYellow = getContext().getResources().getDrawable(R.drawable.yellow);
        mGreen = getContext().getResources().getDrawable(R.drawable.green);
        mPink = getContext().getResources().getDrawable(R.drawable.pink);
        mDarkGreen = getContext().getResources().getDrawable(R.drawable.dark_green);

        mDrawables = new Drawable[6];
        mDrawables[0] = mRed;
        mDrawables[1] = mBlue;
        mDrawables[2] = mYellow;
        mDrawables[3] = mGreen;
        mDrawables[4] = mPink;
        mDrawables[5] = mDarkGreen;

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.red);
        mDrawableWidth = drawable.getIntrinsicWidth();
        mDrawableHeight = drawable.getIntrinsicHeight();
    }

    public void initInterpolator(){
        mInterpolator = new Interpolator[4];
        mInterpolator[0] = new LinearInterpolator();//线性
        mInterpolator[1] = new AccelerateDecelerateInterpolator();//先加速后减速
        mInterpolator[2] = new AccelerateInterpolator();//加速
        mInterpolator[3] = new DecelerateInterpolator();//减速
    }

    public void addLove(){
        final ImageView loveImg = new ImageView(getContext());
        loveImg.setImageDrawable(mDrawables[mRandom.nextInt(mDrawables.length)]);
        loveImg.setLayoutParams(params);
        addView(loveImg);

        AnimatorSet animatorSet = getAnimatorSet(loveImg);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(loveImg);
            }
        });
        animatorSet.start();
    }

    public AnimatorSet getAnimatorSet(final ImageView img){
        AnimatorSet allSet = new AnimatorSet();
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(img, "alpha", 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(img, "scaleX", 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(img, "scaleY", 0.2f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new LinearInterpolator());
        set.setDuration(500);
        set.playTogether(alphaAnim,scaleX,scaleY);

        //确保pointF1点的Y值要大于pointF1点的Y值
        PointF pointF0 = new PointF(mWidth/2-mDrawableWidth/2,mHeight-mDrawableHeight);
        PointF pointF1 = getPointF(2);
        PointF pointF2 = getPointF(1);
        PointF pointF3 = new PointF(mRandom.nextInt(mWidth),0-mDrawableHeight);
        BezierEvaluator bezierEvaluator = new BezierEvaluator(pointF1,pointF2);
        ValueAnimator bezierAnim = ObjectAnimator.ofObject(bezierEvaluator,pointF0,pointF3);
        bezierAnim.setDuration(5000);
        bezierAnim.setInterpolator(mInterpolator[mRandom.nextInt(mInterpolator.length)]);
        bezierAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                img.setX(point.x);
                img.setY(point.y);
                img.setAlpha(1-animation.getAnimatedFraction()+0.1f);
            }
        });
        allSet.playSequentially(set,bezierAnim);
        return allSet;
    }

    public PointF getPointF(int index){
        return new PointF(mRandom.nextInt(mWidth)-mDrawableWidth,mRandom.nextInt(mHeight/2)+(index-1)*mHeight/2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }
}
