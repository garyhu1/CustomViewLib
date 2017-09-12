package com.garyhu.svgdemo.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.garyhu.svgdemo.view.LoveHeartLayout;
import com.garyhu.svgdemo.view.MyProgress;
import com.garyhu.svgdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoveLayoutActivity.class));
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StickActivity.class));
            }
        });

        findViewById(R.id.qq_step).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,QQStepActivity.class));
            }
        });
        findViewById(R.id.my_view_pager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ViewPagerActivity.class));
            }
        });
        findViewById(R.id.open_gl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OpenGlActivity.class));
            }
        });
        findViewById(R.id.load_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoadingActivity.class));
            }
        });
        findViewById(R.id.count_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TimeCountActivity.class));
            }
        });
        findViewById(R.id.msg_bubble).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MessageBubbleActivity.class));
            }
        });
        findViewById(R.id.wave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WaveActivity.class));
            }
        });
        findViewById(R.id.zoom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ZoomInOutActivity.class));
            }
        });
        findViewById(R.id.transition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转时有跳转动画
                Intent intent = new Intent(MainActivity.this, LookPicActivity.class);
                View view = findViewById(R.id.transition);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, view, getResources().getString(R.string.transition_view));
                ActivityCompat.startActivity(MainActivity.this,intent,options.toBundle());
            }
        });
        findViewById(R.id.slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SlideActivity.class));
            }
        });
        findViewById(R.id.map_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MapTextActivity.class));
            }
        });
        findViewById(R.id.circle_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CircleImgActivity.class));
            }
        });
        findViewById(R.id.flash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FlashActivity.class));
            }
        });
        findViewById(R.id.color_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ColorPickerActivity.class));
            }
        });
        findViewById(R.id.path_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PathAnimActivity.class));
            }
        });
    }
}
