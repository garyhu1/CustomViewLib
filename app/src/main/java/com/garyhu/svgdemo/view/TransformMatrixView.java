package com.garyhu.svgdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.garyhu.svgdemo.R;

/**
 * Created by garyhu
 * on 2017/8/30.
 * Matrix 的使用
 */

public class TransformMatrixView extends ImageView {

    private Bitmap bitmap;
    private Matrix matrix;
    public TransformMatrixView(Context context)
    {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sina);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        // 画出原图像
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 画出变换后的图像
        canvas.drawBitmap(bitmap, matrix, null);
        super.onDraw(canvas);
    }

    @Override
    public void setImageMatrix(Matrix matrix)
    {
        this.matrix.set(matrix);
        super.setImageMatrix(matrix);
    }

    public Bitmap getImageBitmap()
    {
        return bitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_UP)
        {
            Matrix matrix = new Matrix();
            // 输出图像的宽度和高度(162 x 251)
            Log.e("garyhu", "image size: width x height = " +  getImageBitmap().getWidth() + " x " + getImageBitmap().getHeight());
//            // 1. 平移
//            matrix.postTranslate(view.getImageBitmap().getWidth(), view.getImageBitmap().getHeight());
//            // 在x方向平移view.getImageBitmap().getWidth()，在y轴方向view.getImageBitmap().getHeight()
//            view.setImageMatrix(matrix);


//          // 2. 旋转(围绕图像的中心点)
//          matrix.setRotate(45f, view.getImageBitmap().getWidth() / 2f, view.getImageBitmap().getHeight() / 2f);
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth() * 1.5f, 0f);
//          view.setImageMatrix(matrix);


//          // 3. 旋转(围绕坐标原点) + 平移(效果同2)
//          matrix.setRotate(45f);
//          matrix.preTranslate(-1f * view.getImageBitmap().getWidth() / 2f, -1f * view.getImageBitmap().getHeight() / 2f);
//          matrix.postTranslate((float)view.getImageBitmap().getWidth() / 2f, (float)view.getImageBitmap().getHeight() / 2f);
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate((float)view.getImageBitmap().getWidth() * 1.5f, 0f);
//          view.setImageMatrix(matrix);


//          // 4. 缩放
//          matrix.setScale(2f, 2f);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("garyhu", temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth(), view.getImageBitmap().getHeight());
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("garyhu", temp);
//          }


//          // 5. 错切 - 水平
//          matrix.setSkew(0.5f, 0f);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth(), 0f);
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }

//          // 6. 错切 - 垂直
//          matrix.setSkew(0f, 0.5f);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight());
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }

//          7. 错切 - 水平 + 垂直
//          matrix.setSkew(0.5f, 0.5f);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight());
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }

//          // 8. 对称 (水平对称)
//          float matrix_values[] = {1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight() * 2f);
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }

//          // 9. 对称 - 垂直
//          float matrix_values[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth() * 2f, 0f);
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }


//          // 10. 对称(对称轴为直线y = x)
//          float matrix_values[] = {0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("TestTransformMatrixActivity", temp);
//          }
//
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth(),
//                  view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth());
//          view.setImageMatrix(matrix);
//
//          // 下面的代码是为了查看matrix中的元素
//          float[] matrixValues = new float[9];
//          matrix.getValues(matrixValues);
//          for(int i = 0; i < 3; ++i)
//          {
//              String temp = new String();
//              for(int j = 0; j < 3; ++j)
//              {
//                  temp += matrixValues[3 * i + j ] + "\t";
//              }
//              Log.e("garyhu", temp);
//          }

            invalidate();
        }
        return true;
    }

}

