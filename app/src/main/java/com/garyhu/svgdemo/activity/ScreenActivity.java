package com.garyhu.svgdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.app.MyApplication;

/**
 * created by garyhu.
 * on 2017/9/14.
 * method: 屏幕开关事件
 */

public class ScreenActivity extends AppCompatActivity {

    private static TextView tv_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        tv_screen = (TextView) findViewById(R.id.tv_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tv_screen.setText(MyApplication.getInstance().getChangeDesc());
    }
}
