package com.garyhu.svgdemo.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.garyhu.svgdemo.view.CusDialog;
import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.SwitchMultiButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((SwitchMultiButton) findViewById(R.id.one_switch)).setText("点赞效果","气泡拖拽","计步器").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position==0){
                    startActivity(new Intent(MainActivity.this,LoveLayoutActivity.class));
                }else if(position==1){
                    startActivity(new Intent(MainActivity.this,StickActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this,QQStepActivity.class));
                }
            }
        });
        ((SwitchMultiButton) findViewById(R.id.two_switch)).setText("ViewPager动画","OpenGL动画").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position==0){
                    startActivity(new Intent(MainActivity.this,ViewPagerActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this,OpenGlActivity.class));
                }
            }
        });

        ((SwitchMultiButton) findViewById(R.id.three_switch)).setText("加载动画","倒计时动画","消息拖拽框").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this,LoadingActivity.class));
                }else if(position == 1){
                    startActivity(new Intent(MainActivity.this,TimeCountActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this,MessageBubbleActivity.class));
                }
            }
        });

        ((SwitchMultiButton) findViewById(R.id.four_switch)).setText("水波纹动画","自定义图片缩放").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this,WaveActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this,ZoomInOutActivity.class));
                }
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

        ((SwitchMultiButton) findViewById(R.id.five_switch)).setText("果冻弹性布局","MapText的特殊文本效果").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this,SlideActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this,MapTextActivity.class));
                }
            }
        });

        ((SwitchMultiButton) findViewById(R.id.six_switch)).setText("圆形图片","手电筒","颜色选择器").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this,CircleImgActivity.class));
                }else if(position ==1){
                    startActivity(new Intent(MainActivity.this,FlashActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this,ColorPickerActivity.class));
                }
            }
        });

        ((SwitchMultiButton) findViewById(R.id.seven_switch)).setText("路径动画","自定义dialog","电池管理").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this,PathAnimActivity.class));
                }else if(position ==1){
                    CusDialog dialog = new CusDialog();

                    dialog.show(getFragmentManager(),TAG);
                }else {
                    startActivity(new Intent(MainActivity.this,BatteryActivity.class));
                }
            }
        });

        ((SwitchMultiButton) findViewById(R.id.eight_switch)).setText("监听锁屏","读取文件","分享界面").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this,ScreenActivity.class));
                }else if(position ==1){
                    startActivity(new Intent(MainActivity.this,ReadFileActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this,ShareActivity.class));
                }
            }
        });

        ((SwitchMultiButton) findViewById(R.id.nine_switch)).setText("RecyclerView的item移出屏幕动画","暂无").setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this,RecyclerItemAnimActivity.class));
                }else if(position ==1){
//                    startActivity(new Intent(MainActivity.this,ReadFileActivity.class));
                }else {
//                    startActivity(new Intent(MainActivity.this,ShareActivity.class));
                }
            }
        });
    }

}
