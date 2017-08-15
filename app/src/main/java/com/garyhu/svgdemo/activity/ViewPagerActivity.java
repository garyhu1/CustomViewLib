package com.garyhu.svgdemo.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.animation.DepthPageTransformer;
import com.garyhu.svgdemo.animation.RotatePageTransformer;
import com.garyhu.svgdemo.animation.RotateYPageTransformer;
import com.garyhu.svgdemo.animation.ZoomOutPageTransformer;
import com.garyhu.svgdemo.utils.DensityUtils;
import com.garyhu.svgdemo.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private int[] imgIds = new int[]{R.drawable.p_1,R.drawable.p_2,R.drawable.p_3,R.drawable.p_4};
    private List<ImageView> mImageViews;
    private ViewPager viewPager;
//    private MyViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mImageViews = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        viewPager.setPageTransformer(true,new DepthPageTransformer());
//        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        //使用自定义的动画
//        viewPager.setPageTransformer(true,new RotatePageTransformer());
        viewPager.setPageTransformer(true,new RotateYPageTransformer());
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView img = new ImageView(ViewPagerActivity.this);
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

            /*
             重写该方法：
              默认返回的是1f
              修改为0.8f后，一页就会显示出来两条
             */
//            @Override
//            public float getPageWidth(int position) {
//                return 0.8f;
//            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}
