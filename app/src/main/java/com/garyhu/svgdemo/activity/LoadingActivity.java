package com.garyhu.svgdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.Kawaii_LoadingView;

public class LoadingActivity extends AppCompatActivity {

    private Kawaii_LoadingView load;
    private View Loading ;
    private Button buttonStart,buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        load = (Kawaii_LoadingView) findViewById(R.id.Kawaii_LoadingView);
        Loading = findViewById(R.id.loadingView);

        buttonStart = (Button)findViewById(R.id.start);
        buttonFinish = (Button)findViewById(R.id.finish);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.startMoving();
                Loading.setVisibility(View.VISIBLE);
            }

        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.stopMoving();
                Loading.setVisibility(View.INVISIBLE);
            }

        });
    }
}
