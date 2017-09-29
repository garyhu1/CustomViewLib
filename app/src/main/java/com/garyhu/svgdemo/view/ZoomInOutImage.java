package com.garyhu.svgdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by garyhu
 * on 2017/9/19.
 */

public class ZoomInOutImage extends AppCompatImageView {


    public ZoomInOutImage(Context context) {
        this(context,null);
    }

    public ZoomInOutImage(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZoomInOutImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
