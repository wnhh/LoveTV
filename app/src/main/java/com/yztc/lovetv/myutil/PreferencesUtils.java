package com.yztc.lovetv.myutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by bodhixu on 2016/11/7.
 * SharedPreferences工具类
 */

public class PreferencesUtils {

    //获得SharedPreferences实例
    private static final SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //保存Boolean
    public static void putBoolean(Context context, String key, boolean value) {
        //获得SharedPreferences实例
        SharedPreferences preferences = getPreferences(context);
        //获得SharedPreferences.Editor实例
        SharedPreferences.Editor editor = preferences.edit();
        //存储数据
        editor.putBoolean(key, value);
        //提交
        editor.commit();
    }

    //读取Boolean
    public static boolean getBoolean(Context context, String key) {
        //获得SharedPreferences实例
        SharedPreferences preferences = getPreferences(context);
        return preferences.getBoolean(key,false);
    }

    //数据删除
    public static void clear(Context context) {
        //获得SharedPreferences实例
        SharedPreferences preferences = getPreferences(context);
        //获得SharedPreferences.Editor实例
        SharedPreferences.Editor editor = preferences.edit();
        //存储数据
        editor.clear();
        //提交
        editor.commit();
    }

    //保存字符串
    public static void putString(Context context, String key, String value) {
        //获得SharedPreferences实例
        SharedPreferences preferences = getPreferences(context);
        //获得SharedPreferences.Editor实例
        SharedPreferences.Editor editor = preferences.edit();
        //存储数据
        editor.putString(key, value);
        //提交
        editor.commit();
    }

    //读取字符串
    public static String getString(Context context, String key) {
        //获得SharedPreferences实例
        SharedPreferences preferences = getPreferences(context);
        return preferences.getString(key,null);
    }


}
