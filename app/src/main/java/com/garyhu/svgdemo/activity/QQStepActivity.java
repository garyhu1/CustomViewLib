package com.garyhu.svgdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.QQStepView;

public class QQStepActivity extends AppCompatActivity {

    private QQStepView qqStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqstep);
        qqStepView = (QQStepView) findViewById(R.id.qq_step);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqStepView.setCurrentStep(50f,100f);
            }
        });
    }
}
