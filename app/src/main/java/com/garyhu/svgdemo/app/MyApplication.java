package com.garyhu.svgdemo.app;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.garyhu.svgdemo.receiver.LockScreenReceiver;
import com.garyhu.svgdemo.utils.CusCache;
import com.mob.MobSDK;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by garyhu
 * on 2017/9/14.
 */

public class MyApplication extends Application {

    private static MyApplication mApp;
    private LockScreenReceiver mReceiver;
    private String mChange = "";

    private boolean isFirst;//判断app是否第一次启动

    private static final String APP_KEY = "21041ca408614";
    private static final String APP_SECRET = "7192b78f492fd39f6768025b075f0437";

    public static MyApplication getInstance() {
        return mApp;
    }

    public String getChangeDesc() {
        return mApp.mChange;
    }

    public void setChangeDesc(String change) {
        mApp.mChange = mApp.mChange + change;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this,APP_KEY,APP_SECRET);
        mApp = this;
        mReceiver = new LockScreenReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mReceiver, filter);
        writeConfig();
    }

    @Override
    public void onTerminate() {
        unregisterReceiver(mReceiver);
        super.onTerminate();
    }

    /**
     * 往内存中写入配置文件
     */
    public void writeConfig(){
        isFirst = CusCache.getBoolean(getApplicationContext(),"isFirst");
        if(!isFirst){
            String path = getFilesDir().getAbsolutePath();
            new MyTask().execute(path);
        }
    }

    class MyTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            String str = "写入失败";
            Log.d("garyhu","开始写入配置文件");
            AssetManager assets = getAssets();
            InputStream input = null;
            OutputStream out = null;
            try {
                String fileName = params[0]+ File.separator+"config.txt";
                File file = new File(fileName);
                if(!file.exists()){
                    file.createNewFile();
                }
                input = assets.open("config.txt");
                out = new FileOutputStream(file);
                int len;
                byte[] buf = new byte[1];
                while((len = input.read(buf))!=-1){
                    out.write(buf,0,len);
                }
                //设置为true后只会在第一次安装的时候写入文件
                CusCache.putBoolean(getApplicationContext(),"isFirst",true);
                str = "写入成功";
            } catch (IOException e) {
                Log.d("garyhu","app--->error : "+e.getMessage());
                e.printStackTrace();
            }finally {
                try {
                    if(input!=null)
                        input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return str;
        }
    }
}
