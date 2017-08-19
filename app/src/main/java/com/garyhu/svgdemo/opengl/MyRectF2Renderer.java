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

public class MyRectF2Renderer implements GLSurfaceView.Renderer {
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
        gl.glFrustumf(ratio,-ratio,-1f,1f,3,3.5f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl,0f,0f,5f,0f,0f,0f,0f,1f,0f);

        boolean flag = false ;
        List<Float> colorLists = new ArrayList<>();
        if(flag=!flag){
            colorLists.add(1f);
            colorLists.add(1f);
            colorLists.add(0f);
            colorLists.add(1f);
        }else {
            colorLists.add(1f);
            colorLists.add(0f);
            colorLists.add(0f);
            colorLists.add(1f);
        }
        ByteBuffer colorB = ByteBuffer.allocateDirect(colorLists.size()*4);
        colorB.order(ByteOrder.nativeOrder());
        FloatBuffer colorFb = colorB.asFloatBuffer();
        for (float f:colorLists) {
            colorFb.put(f);
        }
        colorB.position(0);
        gl.glColorPointer(4,GL10.GL_FLOAT,0,colorB);

        /********************  锥顶 **********************/
        List<Float> coordsList = new ArrayList<>();
        coordsList.add(0f);
        coordsList.add(0f);
        coordsList.add(0.5f);

        /******************** 锥底 **********************/
        float r = 0.5f;//半径
        float x = 0f,y = 0f,z = -0.5f;//底面圆心点的坐标
        List<Float> coordsConeBottomList = new ArrayList<Float>();
        coordsConeBottomList.add(0f);
        coordsConeBottomList.add(0f);
        coordsConeBottomList.add(-0.5f);
        for( float alpha = 0f; alpha < Math.PI * 6;alpha = (float) (alpha+Math.PI / 8 )){
            x = (float) (Math.cos(alpha) * r);
            y = (float) (Math.sin(alpha) * r);
            //锥面坐标
            coordsList.add(x);
            coordsList.add(y);
            coordsList.add(z);
            //锥底坐标
            coordsConeBottomList.add(x);
            coordsConeBottomList.add(y);
            coordsConeBottomList.add(z);
        }
    }
}
