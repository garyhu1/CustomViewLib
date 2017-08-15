package com.garyhu.svgdemo.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.utils.DensityUtils;
import com.garyhu.svgdemo.utils.ScreenUtils;
import com.garyhu.svgdemo.view.ClipViewPager;

import java.util.ArrayList;
import java.util.List;

public class MulViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private int[] imgIds = new int[]{R.drawable.s_1,R.drawable.s_2,R.drawable.s_3,R.drawable.s_4};
    private List<ImageView> mImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_view_pager);
        mImageViews = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.my_view_pager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageMargin(30);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ScreenUtils.getScreenWidth(this) / 3, LinearLayout.LayoutParams.MATCH_PARENT);
        mViewPager.setLayoutParams(params);
        mViewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView img = new ImageView(MulViewPagerActivity.this);
                img.setImageResource(imgIds[position]);
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(img);
                mImageViews.add(img);
                //自定义的ViewPager使用的方法
//                viewPager.setViewOfPosition(position,img);
                return img;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews.get(position));
                //自定义的ViewPager使用的方法
//                viewPager.removeViewOfPosition(position);
            }

            @Override
            public int getCount() {
                return imgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        mViewPager.setCurrentItem(1);
        findViewById(R.id.relative_layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

    }
}
