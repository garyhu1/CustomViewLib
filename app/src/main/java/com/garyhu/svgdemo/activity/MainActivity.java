package com.garyhu.svgdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.garyhu.svgdemo.view.LoveHeartLayout;
import com.garyhu.svgdemo.view.MyProgress;
import com.garyhu.svgdemo.R;

public class MainActivity extends AppCompatActivity {

    private MyProgress progress;
    private LoveHeartLayout loveHeartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (MyProgress) findViewById(R.id.progress);
        progress.startAnim();
        progress.setOnFinishListener(new MyProgress.OnLoadFinishListener() {
            @Override
            public void onFinishLoad() {
                Toast.makeText(MainActivity.this, "完成加载", Toast.LENGTH_SHORT).show();
            }
        });
        loveHeartLayout = (LoveHeartLayout) findViewById(R.id.love_layout);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loveHeartLayout.addLove();
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StickActivity.class));
            }
        });
    }
}
