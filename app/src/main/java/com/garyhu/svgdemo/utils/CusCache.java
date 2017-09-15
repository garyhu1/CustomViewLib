package com.garyhu.svgdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by garyhu
 * on 2017/8/23.
 */

public class CusCache {

    private static final String PREFERENCE_NAME = "semap";

    public static boolean putString(Context context,String key,String value){
        if(context==null)
            return false;
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        return edit.commit();
    }

    public static String getString(Context context,String key){
        return getString(context,key,null);
    }

    public static String getString(Context context, String key,String defaultVal){
        if(context ==null)
            return "";
        SharedPreferences sp  = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sp.getString(key,defaultVal);
    }

    /**
     * put int preferences
     */
    public static boolean putInt(Context context, String key, int value) {
        if(context==null){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * get int preferences
     */
    public static int getInt(Context context, String key, int defaultValue) {
        if(context==null){
            return 0;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     */
    public static boolean putLong(Context context, String key, long value) {
        if(context==null){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * get long preferences
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * get long preferences
     */
    public static long getLong(Context context, String key, long defaultValue) {
        if(context==null){
            return 0;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     */
    public static boolean putFloat(Context context, String key, float value) {
        if(context==null){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * get float preferences
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        if(context==null){
            return 0;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        if(context==null){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     */
    public static boolean getBoolean(Context context, String key) {
        if(context==null){
            return false;
        }
        return getBoolean(context, key, false);
    }

    /**
     * get boolean preferences
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        if(context==null){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }
}
