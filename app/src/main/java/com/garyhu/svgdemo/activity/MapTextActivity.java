package com.garyhu.svgdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.MapTextView;

public class MapTextActivity extends AppCompatActivity {

    private MapTextView mapText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_text);
        mapText = (MapTextView) findViewById(R.id.map_text_view);
        mapText.setKeyText("今日运数");
        mapText.setValueText("89");
    }
}
