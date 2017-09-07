package com.garyhu.svgdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.LoveHeartLayout;

public class LoveLayoutActivity extends AppCompatActivity {

    private LoveHeartLayout loveHeartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_layout);
        loveHeartLayout = (LoveHeartLayout) findViewById(R.id.love_layout);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loveHeartLayout.addLove();
            }
        });
    }
}
