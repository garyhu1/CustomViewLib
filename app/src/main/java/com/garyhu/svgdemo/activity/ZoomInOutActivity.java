package com.garyhu.svgdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.ZoomInOutView;

public class ZoomInOutActivity extends AppCompatActivity {

    private ZoomInOutView zoomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_in_out);

        zoomView = (ZoomInOutView) findViewById(R.id.zoom_view);
        Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.s_4);
        zoomView.setImageBitmap(b);
    }
}
