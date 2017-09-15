package com.garyhu.svgdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.garyhu.svgdemo.app.MyApplication;
import com.garyhu.svgdemo.utils.DateUtils;

/**
 * Created by garyhu
 * on 2017/9/14.
 */

public class LockScreenReceiver extends BroadcastReceiver {

    private static final String TAG = "LockScreenReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String mChange = "";
            mChange = String.format("%s\n%s : 收到广播：%s", mChange,
                    DateUtils.getNowDateTime(), intent.getAction());
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                mChange = String.format("%s\n这是屏幕点亮事件", mChange);
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                mChange = String.format("%s\n这是屏幕关闭事件", mChange);
            } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                mChange = String.format("%s\n这是用户解锁事件", mChange);
            }
            Log.d(TAG, mChange);
            MyApplication.getInstance().setChangeDesc(mChange);
        }
    }
}
