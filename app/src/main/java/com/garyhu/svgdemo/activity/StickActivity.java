package com.garyhu.svgdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.DragBubbleView;

/**
 * 作者： garyhu.
 * 时间： 2017/8/14.
 */

public class StickActivity extends AppCompatActivity implements View.OnClickListener, DragBubbleView.OnBubbleStateListener {

    private DragBubbleView mDragBubbleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick);

        Button reCreateBtn = (Button) findViewById(R.id.reCreateBtn);
        reCreateBtn.setOnClickListener(this);
        mDragBubbleView = (DragBubbleView) findViewById(R.id.dragBubbleView);
        mDragBubbleView.setText("99+");
        mDragBubbleView.setOnBubbleStateListener(this);
    }

    @Override
    public void onClick(View v) {
        mDragBubbleView.reCreate();
    }

    @Override
    public void onDrag() {
        Log.e("---> ", "拖拽气泡");
    }

    @Override
    public void onMove() {
        Log.e("---> ", "移动气泡");
    }

    @Override
    public void onRestore() {
        Log.e("---> ", "气泡恢复原来位置");
    }

    @Override
    public void onDismiss() {
        Log.e("---> ", "气泡消失");
    }
}
