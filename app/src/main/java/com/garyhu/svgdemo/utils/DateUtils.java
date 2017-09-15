package com.garyhu.svgdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by garyhu
 * on 2017/9/14.
 */

public class DateUtils {

    public static String getNowDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
}
