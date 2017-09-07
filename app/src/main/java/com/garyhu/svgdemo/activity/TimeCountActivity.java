package com.garyhu.svgdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.MyProgress;

public class TimeCountActivity extends AppCompatActivity {

    private MyProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_count);
        progress = (MyProgress) findViewById(R.id.progress);
        progress.startAnim();
        progress.setOnFinishListener(new MyProgress.OnLoadFinishListener() {
            @Override
            public void onFinishLoad() {
                Toast.makeText(TimeCountActivity.this, "完成加载", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
