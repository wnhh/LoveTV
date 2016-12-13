package com.yztc.lovetv.base;

import android.app.Application;

/**
 * Created by bodhixu on 2016/11/28.
 * 自定义的Application
 */
public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //一次性的初始化操作
        //Application的onCreate()优先于Activity的onCreate()方法调用的
    }

}
