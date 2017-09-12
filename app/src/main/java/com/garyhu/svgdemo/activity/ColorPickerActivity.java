package com.garyhu.svgdemo.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.view.colorpicker.ColorSelectActivity;
import com.garyhu.svgdemo.view.colorpicker.ColorSelectDialog;
import com.garyhu.svgdemo.view.colorpicker.ColorSelectDialogFragment;

public class ColorPickerActivity extends AppCompatActivity {

    private View view;
    private ColorSelectDialog colorSelectDialog;
    private int lastColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        initView();
    }

    private void initView() {
        view = findViewById(R.id.view);
    }

    public void Color(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (colorSelectDialog == null) {
                    colorSelectDialog = new ColorSelectDialog(this);
                    colorSelectDialog.setOnColorSelectListener(new ColorSelectDialog.OnColorSelectListener() {
                        @Override
                        public void onSelectFinish(int color) {
                            lastColor=color;
                            ColorPickerActivity.this.view.setBackgroundColor(lastColor);
                        }
                    });
                }
                colorSelectDialog.setLastColor(lastColor);
                colorSelectDialog.show();

                break;
            case R.id.button2:
                Intent intent = new Intent(this, ColorSelectActivity.class);
                intent.putExtra(ColorSelectActivity.LAST_COLOR,lastColor);
                startActivityForResult(intent, 0);
                break;
            case R.id.button3:
                ColorSelectDialogFragment colorSelectDialogFragment=new ColorSelectDialogFragment();
                colorSelectDialogFragment.setOnColorSelectListener(new ColorSelectDialogFragment.OnColorSelectListener() {
                    @Override
                    public void onSelectFinish(int color) {
                        lastColor=color;
                        ColorPickerActivity.this.view.setBackgroundColor(lastColor);
                    }
                });
                colorSelectDialogFragment.setLastColor(lastColor);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                colorSelectDialogFragment.show(ft, "colorSelectDialogFragment");
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                lastColor=data.getIntExtra(ColorSelectActivity.RESULT,0x000000);
                view.setBackgroundColor(lastColor);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
