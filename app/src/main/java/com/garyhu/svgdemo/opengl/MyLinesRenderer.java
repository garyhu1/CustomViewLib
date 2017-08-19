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
 * 时间： 2017/8/18.
 */

public class MyLinesRenderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置清屏色
        gl.glClearColor(1,1,1,1);
        //启用顶点缓存区
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置视口输出画面的区域
        gl.glViewport(0,0,width,height);
        //计算视口比例
        float ratio = (float)width/(float)height;
        //矩阵模式，投影矩阵，openGL基于状态机
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //加载单位矩阵
        gl.glLoadIdentity();
        //设置平截头体
        gl.glFrustumf(-1f,1f,-ratio,ratio,3f,7f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //先清除颜色缓存区
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //设置绘图颜色
        gl.glColor4f(1f,0,0,1f);//红色
        //设置模型视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //加载单位矩阵
        gl.glLoadIdentity();
        //放置眼球位置
        GLU.gluLookAt(gl,0,0,5,0,0,0,0,1,0);

        float[] cords = {
                0f,0.5f,0f,
                -0.7f,-0.5f,0f,
                1f,-0.6f,0f
        };
        //设置字节缓存空间，用来存放顶点数据
        ByteBuffer bb = ByteBuffer.allocateDirect(cords.length*4);
        //设置顺序（本地顺序）
        bb.order(ByteOrder.nativeOrder());
        //放置顶点数组
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(cords);
        //定位指针位置，从该位置开始读取顶点数据
        bb.position(0);
        /**
         * 3→三维点，使用三个坐标表示一个点。
         * 每个点的数据类型。
         * 0→跨度。
         * bb指定缓冲区。
         */
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,bb);
        //画数组
        gl.glDrawArrays(GL10.GL_TRIANGLES,0,cords.length/3);
    }
}
