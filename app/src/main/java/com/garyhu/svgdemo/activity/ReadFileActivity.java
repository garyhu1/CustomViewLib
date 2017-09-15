package com.garyhu.svgdemo.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.garyhu.svgdemo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFileActivity extends AppCompatActivity {

    private TextView showTxt;//显示读取的文件内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);
        showTxt = (TextView) findViewById(R.id.show_txt);
        findViewById(R.id.load_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File filesDir = getFilesDir();
                writeFile(filesDir.getAbsolutePath());
            }
        });
    }

    public void writeFile(String filePath){
        new MyTask().execute(filePath);
    }

    class MyTask extends AsyncTask<String,Integer,byte[]>{

        @Override
        protected byte[] doInBackground(String... params) {
            byte[] bytes = getFileContent(params[0]);
            return bytes;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            if(bytes!=null){
                String s = new String(bytes,0,bytes.length);
                showTxt.setText(s);
            }
        }
    }

    public byte[] getFileContent(String filePath){
        FileInputStream input = null;
        String file = filePath+File.separator+"config.txt";
        try {
            File f = new File(file);
            if(!f.exists()){
                f.createNewFile();
            }
            input = new FileInputStream(f);
            byte[] buf = new byte[1024*4];
            input.read(buf);
            return buf;
        } catch (IOException e) {
            Log.d("garyhu","error == "+e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if(input!=null)
                   input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
