package com.yztc.lovetv.base;

import android.app.Application;
import cn.bmob.v3.Bmob;

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
        //第一：默认初始化
        Bmob.initialize(this, "85e81cebd5ed2f4dda7fcfa930ab054f");
    }

}
