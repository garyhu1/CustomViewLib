package com.garyhu.svgdemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.garyhu.svgdemo.R;
import com.garyhu.svgdemo.utils.DateUtils;

/**
 * created garyhu.
 * on 2017/9/14.
 * method: 电池管理
 * 描述：
 *  BatteryManager名为电池管理，然而查看该类的源代码，
 *  里面只有一些常量定义，并非真正意义上的电池管理。
 *  事实上，开发者并不能直接管理电池，要想获取电池的相关信息，
 *  得通过监听电量改变事件来得知。
 */
public class BatteryActivity extends AppCompatActivity {

    private TextView tv_battery_change;
    private static TextView tv_power_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        tv_battery_change = (TextView) findViewById(R.id.tv_battery_change);
        tv_power_status = (TextView) findViewById(R.id.tv_power_status);
    }

    @Override
    protected void onStart() {
        super.onStart();
        batteryChangeReceiver = new BatteryChangeReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryChangeReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryChangeReceiver);
    }

    private BatteryChangeReceiver batteryChangeReceiver;
    private class BatteryChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                int healthy = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
                int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 3);
                String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
                boolean present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);

                String desc = String.format("%s : 收到广播：%s",
                        DateUtils.getNowDateTime(), intent.getAction());
                desc = String.format("%s\n电量刻度=%d", desc, scale);
                desc = String.format("%s\n当前电量=%d", desc, level);
                desc = String.format("%s\n当前状态=%s", desc, mStatus[status]);
                desc = String.format("%s\n健康程度=%s", desc, mHealthy[healthy]);
                desc = String.format("%s\n当前电压=%d", desc, voltage);
                desc = String.format("%s\n当前电源=%s", desc, mPlugged[plugged]);
                desc = String.format("%s\n当前技术=%s", desc, technology);
                desc = String.format("%s\n当前温度=%d", desc, temperature/10);
                desc = String.format("%s\n是否提供电池=%s", desc, present?"是":"否");
                tv_battery_change.setText(desc);
            }
        }
    }

    private static String[] mStatus = {"不存在", "未知", "正在充电", "正在断电", "不在充电", "充满"};
    private static String[] mHealthy = {"不存在", "未知", "良好", "过热", "坏了", "短路", "未知错误", "冷却"};
    private static String[] mPlugged = {"电池", "充电器", "USB", "不存在", "无线"};

    private static String mChange = "";
    public static class PowerChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                mChange = String.format("%s\n%s : 收到广播：%s",
                        mChange, DateUtils.getNowDateTime(), intent.getAction());
                tv_power_status.setText(mChange);
            }
        }
    }

    /*
    * 电池的电量改变事件，其动作名称是Intent.ACTION_BATTERY_CHANGED，
    * 因为接受该事件要求app必须处于活动状态，
    * 所以用来监听的广播接收器不能在AndroidManifest.xml中静态注册，
    * 而只能在app代码中通过registerReceiver方法来动态注册。
    * 下面是电量改变事件中携带的参数信息：
    * BatteryManager.EXTRA_SCALE : 电量刻度，通过getIntExtra获取。通常是100
    * BatteryManager.EXTRA_LEVEL : 当前电量，通过getIntExtra获取。
    * BatteryManager.EXTRA_STATUS : 当前状态，通过getIntExtra获取。
    * --BATTERY_STATUS_UNKNOWN = 1; 表示未知
    * --BATTERY_STATUS_CHARGING = 2; 表示正在充电
    * --BATTERY_STATUS_DISCHARGING = 3; 表示正在断电
    * --BATTERY_STATUS_NOT_CHARGING = 4; 表示不在充电
    * --BATTERY_STATUS_FULL = 5; 表示充满
    * BatteryManager.EXTRA_HEALTH : 健康程度，通过getIntExtra获取。
    * --BATTERY_HEALTH_UNKNOWN = 1; 表示未知
    * --BATTERY_HEALTH_GOOD = 2; 表示良好
    * --BATTERY_HEALTH_OVERHEAT = 3; 表示过热
    * --BATTERY_HEALTH_DEAD = 4; 表示坏了
    * --BATTERY_HEALTH_OVER_VOLTAGE = 5; 表示短路
    * --BATTERY_HEALTH_UNSPECIFIED_FAILURE = 6; 表示未知错误
    * --BATTERY_HEALTH_COLD = 7; 表示冷却
    * BatteryManager.EXTRA_VOLTAGE : 当前电压，通过getIntExtra获取。
    * BatteryManager.EXTRA_PLUGGED : 当前电源，通过getIntExtra获取。
    * --0 表示电池
    * --BATTERY_PLUGGED_AC = 1; 表示充电器
    * --BATTERY_PLUGGED_USB = 2; 表示USB
    * --BATTERY_PLUGGED_WIRELESS = 4; 表示无线
    * BatteryManager.EXTRA_TECHNOLOGY : 当前技术，通过getStringExtra获取。比如返回Li-ion表示锂电池。
    * BatteryManager.EXTRA_TEMPERATURE : 当前温度，通过getIntExtra获取。
    * BatteryManager.EXTRA_PRESENT : 是否提供电池，通过getBooleanExtra获取。
    *
    *
    * 除了电量改变事件，还有几个事件与电池有关，如下所示
    * Intent.ACTION_BATTERY_LOW : 电池电量过低，静态注册时使用android.intent.action.BATTERY_LOW
    * Intent.ACTION_BATTERY_OKAY : 电池电量恢复，静态注册时使用android.intent.action.BATTERY_OKAY
    * Intent.ACTION_POWER_CONNECTED : 连上外部电源，静态注册时使用android.intent.action.ACTION_POWER_CONNECTED
    * Intent.ACTION_POWER_DISCONNECTED : 断开外部电源，静态注册时使用android.intent.action.ACTION_POWER_DISCONNECTED
    */
}
