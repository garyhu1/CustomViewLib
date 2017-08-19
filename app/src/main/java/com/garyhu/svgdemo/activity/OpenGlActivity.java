package com.garyhu.svgdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.opengl.MyGLSurfaceView;
import com.garyhu.svgdemo.opengl.MyLineRenderer;
import com.garyhu.svgdemo.opengl.MyLinesRenderer;
import com.garyhu.svgdemo.opengl.MyPointRenderer;
import com.garyhu.svgdemo.opengl.MyRender;

public class OpenGlActivity extends AppCompatActivity {

    private MyGLSurfaceView gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);
        gl = (MyGLSurfaceView) findViewById(R.id.surface);
//        gl.setRenderer(new MyRender());
//        gl.setRenderer(new MyPointRenderer());
//        gl.setRenderer(new MyLinesRenderer());
        gl.setRenderer(new MyLineRenderer());
//        Log.d("garyhu","Math.PI = "+Math.PI);
    }
}
