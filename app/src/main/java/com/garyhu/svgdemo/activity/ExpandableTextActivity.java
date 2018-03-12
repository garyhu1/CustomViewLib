package com.garyhu.svgdemo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.ExpandableTextView;

/**
 * @Author : garyhu
 * @Since : 2017/10/31
 * @Decription :
 */

public class ExpandableTextActivity extends AppCompatActivity {

    private ExpandableTextView mExpandableText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        mExpandableText = (ExpandableTextView) findViewById(R.id.expandable_text_view);
        mExpandableText.setText(getString(R.string.expandable_text));
    }
}
