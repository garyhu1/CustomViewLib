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
 * 时间： 2017/8/19.
 */

public class MyLine2Renderer implements GLSurfaceView.Renderer {

    private float yRotate = 0f;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0f,0f,0f,1f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0,0,width,height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        float ratio = (float)width/(float)height;
        gl.glLoadIdentity();
        gl.glFrustumf(ratio,-ratio,-1f,1f,3,7);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl,0f,0f,5f,0f,0f,0f,0f,1f,0f);
        gl.glColor4f(1f,0,0,1f);
        gl.glRotatef(-90,1,0,0);
        gl.glRotatef(yRotate,0f,1f,0);

        //画点
        List<Float> coordsList = new ArrayList<Float>(); //用list存放各个点
        float r = 0.5f;//半径
        float x = 0f,y = 0f,z = 1.5f;//点的坐标
        float zStep = 0.005f;//z轴的步长
        float lsize = 1.0f;
        float lStep = 0.5f;
        //用for循环出各个点的坐标
        for( float alpha = 0f; alpha < Math.PI * 12;alpha = (float) (alpha+Math.PI / 32 )){
            x = (float) (Math.cos(alpha) * r);
            y = (float) (Math.sin(alpha) * r);
            z = z - zStep;
            gl.glLineWidth( lsize = lStep+lsize );
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
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,bb);

        gl.glDrawArrays(GL10.GL_LINE_STRIP,0,coordsList.size()/3);

    }
}
