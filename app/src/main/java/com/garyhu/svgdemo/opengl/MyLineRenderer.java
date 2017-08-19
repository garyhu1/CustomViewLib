package com.garyhu.svgdemo.opengl;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 作者： garyhu.
 * 时间： 2017/8/18.
 */

public class MyLineRenderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0,0,0,1f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0,0,width,height);
        float ratio = (float)width/(float)height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-1f,1f,-ratio,ratio,3,7);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl,0,0,5,0,0,0,0,1,0);
        gl.glColor4f(1f,1f,1f,1f);
        //画点
        /**
         * 计算点的坐标
         * @param r 半径
         * @param coordsList 坐标集合
         * @param x,y,z 每个点的坐标
         * @param alpha 角度
         *
         */

        float r = 0.5f;//半径
        float x = 0f,y = 0f,z = 0f;//点的坐标
        List<Float> coordsList = new ArrayList<Float>();

        //循环绘制点
        for( float alpha = 0f; alpha < Math.PI * 6;alpha = (float) (alpha+Math.PI / 16 )){
            x = (float) (Math.cos(alpha) * r);
            y = (float) (Math.sin(alpha) * r);
            //添加原点
            coordsList.add(0f);
            coordsList.add(0f);
            coordsList.add(0f);
            //添加当前点
            coordsList.add(x);
            coordsList.add(y);
            coordsList.add(z);
        }

        ByteBuffer bb = ByteBuffer.allocateDirect(coordsList.size()*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        for (float f:coordsList) {
            fb.put(f);
        }
        bb.position(0);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bb);
        gl.glDrawArrays( GL10.GL_LINES,0,coordsList.size() / 3 );
    }
}
