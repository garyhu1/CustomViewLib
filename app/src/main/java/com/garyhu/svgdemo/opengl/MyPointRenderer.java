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

public class MyPointRenderer implements GLSurfaceView.Renderer {
    private float ratio;//视口比例
    public float xRotate = 0f;//绕X轴旋转的角度
    public float yRotate = 0f;//绕Y轴旋转的角度

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        /**
         * 步骤：
         * 第一步设置清屏色，第二步启用顶点缓冲数组。
         */

        //设置清屏色
        gl.glClearColor(0f, 0f, 0f, 1f);
        //启用顶点缓冲数组
        gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        /**步骤：
         * 第一步设置视口，再设置平截头体，而要设置平截头体就要先指定矩阵类型为投影矩阵
         * 设置过矩阵类型后，就马上要加载单位矩阵。
         *
         * 设置平截头体：
         * 为了视口所展示的画面不失真，一般设置平截头体的比例与视口比例相同。
         * 所以就要先计算视口比例，再应用到平截头体中。
         */

        //设置视口
        gl.glViewport(0,0,width,height);
        //计算视口比例
        ratio = (float)width / (float)height;
        //设置矩阵模式
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //加载单位矩阵
        gl.glLoadIdentity();
        //设置平截头体
        gl.glFrustumf(-1f,1f,-ratio,ratio,3f,7f);

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        /**
         * 步骤：
         * 主要任务是放置眼球位置和画矩阵。
         * 首先清除颜色缓冲区，再设置绘图颜色.
         * 然后修改矩阵类型为模型视图矩阵，
         * （因为openGL是状态机，如果不修改就一直为投影矩阵）
         * 同样，设置完矩阵类型之后，马上归零。（载入单位矩阵）
         *
         * 接下来放置眼球位置
         *
         * 最后一步画数组
         * 而要画数组，就要先把数组的点计算出来。
         * 所以先计算点的坐标。
         * 用for循环出各个点的坐标后，放进list，
         * 再将点坐标转换为缓冲区数据。
         * 因为openGL是C语言实现的，所以要读缓冲区的数据就要先设置顶点指针
         * 再把缓冲区指针位置定位到0位置。
         * 最后，就可以画数组了。
         */

        //清除颜色缓冲区
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //设置绘图颜色
        gl.glColor4f(1f,0f,0f,1f);
        //设置模型视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //载入单位矩阵
        gl.glLoadIdentity();
        //放置眼球位置
        GLU.gluLookAt( gl,0f,0f,5f,0f,0f,0f,0f,1f,0f );
        //旋转角度，以便更直观查看
        gl.glRotatef(-90,1,0,0);// 绕x轴旋转 （openGL规定，顺时针旋转为负值）
//        gl.glRotatef(yRotate,1,0,0);// 绕y轴旋转

        /**
         * 计算点的坐标
         * @param r 半径
         * @param coordsList 坐标集合
         * @param x,y,z 每个点的坐标
         * @param alpha 角度
         *
         */

        List<Float> coordsList = new ArrayList<Float>(); //用list存放各个点
        float r = 0.5f;//半径
        float x = 0f,y = 0f,z = 1.5f;//点的坐标
        float zStep = 0.01f;//z轴的步长
        //用for循环出各个点的坐标
        for( float alpha = 0f; alpha < Math.PI * 9;alpha = (float) (alpha+Math.PI / 24)){
            x = (float) (Math.cos(alpha) * r);
            y = (float) (Math.sin(alpha) * r);
            z = z - zStep;
            coordsList.add(x);
            coordsList.add(y);
            coordsList.add(z);
        }

        /**
         * 转换点成为缓冲区
         */

        ByteBuffer ibb = ByteBuffer.allocateDirect(coordsList.size() * 4);//一个浮点数等于四个字节
        ibb.order(ByteOrder.nativeOrder());//设置排列顺序，本地顺序
        FloatBuffer fbb = ibb.asFloatBuffer();//将字节缓冲转换成浮点缓冲
        //用增强for循环把每个坐标put进去
        for( float f : coordsList){
            fbb.put(f);
        }
        ibb.position(0);//把缓冲区指针位置定位到0位置
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,ibb);//指定顶点指针

        /**
         * 画数组
         * 因为coordsList中用3个点存一个坐标，
         * 所以点的数量要除以3
         */
        gl.glDrawArrays( GL10.GL_POINTS,0,coordsList.size() / 3 );


    }
}
