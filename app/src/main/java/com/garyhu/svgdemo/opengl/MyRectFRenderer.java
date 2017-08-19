package com.garyhu.svgdemo.opengl;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 作者： garyhu.
 * 时间： 2017/8/19.
 */

public class MyRectFRenderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0f,0f,0f,1f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0,0,width,height);
        float ratio = (float)width/(float)height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(1f,-1f,ratio,-ratio,3,7);
//        gl.glFrustumf(ratio,-ratio,1f,-1f,3,7);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glColor4f(1f,1f,1f,1f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl,0f,0f,5f,0f,0f,0f,0f,1f,0f);
        gl.glRotatef(0,1f,0f,0f);
        gl.glRotatef(0,0f,1f,0f);

        float r = 0.5f;
        /*
        假如有一个正方形是ABCD,分别关于x轴和y轴对称，则绘画的顺序是A(-r,r,0)->B(-r,-r,0)->D(r,r,0)->C(r,-r,0)
         */
        float[] arr = {
             -r,r,0,
             -r,-r,0,
             r,r,0,
             r,-r,0
        };

        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        for (float f :arr) {
            fb.put(f);
        }

        bb.position(0);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,bb);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,arr.length/3);
    }
}
