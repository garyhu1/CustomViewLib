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

public class MyRender implements GLSurfaceView.Renderer {
    //表层创建时调用
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置清屏色
        gl.glClearColor(0, 0, 0, 1);
        //启用顶点缓冲区
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

    }
    //当表层size改变时调用
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        //设置视口，输出画面的区域。
        gl.glViewport(0,0,width,height);
        //宽高比
        float ratio = (float)width/(float)height;
        //矩阵模式，投影矩阵，openGL基于状态机。
        gl.glMatrixMode( GL10.GL_PROJECTION );
        //加载单位矩阵
        gl.glLoadIdentity();
        //设置平截头体
        gl.glFrustumf(-1f,1f,-ratio,ratio,3,7); //-1f,1f为平截头体左右大小比，-ratio,ratio为平截头体底面和顶面，3,7为近平面和远平面距离

    }
    //绘图调用
    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓冲区。
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        //模型视图矩阵。
        gl.glMatrixMode( GL10.GL_MODELVIEW );
        //加载单位矩阵。
        gl.glLoadIdentity();
        //0,0,5：放置眼球的坐标。
        //0,0,0：眼球观察的中心点坐标。
        //0,1,0：指定眼球向上的向量。
        GLU.gluLookAt(gl,0,0,5,0,0,0,0,1,0);

        /**
         * 画三角形
         */

        //三角形顶点坐标
        float [] coords = {
                0f,0.5f,0f,
                -0.5f,-0.5f,0f,
                0.5f,-0.5f,0f
        };
        //分配字节缓存空间，存放顶点坐标数据。
        ByteBuffer ibb = ByteBuffer.allocateDirect(coords.length * 4);
        //设置顺序（本地顺序）。
        ibb.order(ByteOrder.nativeOrder());
        //放置顶点数组。
        FloatBuffer fbb = ibb.asFloatBuffer();
        fbb.put(coords);
        //定位指针位置，从该位置开始读取顶点数据。
        ibb.position(0);

        //设置绘图颜色：红色。
        gl.glColor4f(1f,0,0,1f);
        //3→三维点，使用三个坐标表示一个点。
        //每个点的数据类型。
        //0→跨度。
        //ibb指定缓冲区。
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,ibb);
        //画数组
        gl.glDrawArrays(GL10.GL_TRIANGLES,0,3);


    }
}
