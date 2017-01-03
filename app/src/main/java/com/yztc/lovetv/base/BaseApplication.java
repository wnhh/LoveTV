package com.yztc.lovetv.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.yztc.lovetv.activity.mine.LoginActivity;
import com.yztc.lovetv.bean.BackNeedData;
import com.yztc.lovetv.bean.UserEntity;
import com.yztc.lovetv.db.BackDataOperateManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by bodhixu on 2016/11/28.
 * 自定义的Application
 */
public class BaseApplication extends Application{
    private  BmobUser bmobUser;
    private BackDataOperateManager bmp;
    private static Executor executor;//线程池
    @Override
    public void onCreate() {
        super.onCreate();
        executor = Executors.newCachedThreadPool();//初始化线程池

        //一次性的初始化操作
        //Application的onCreate()优先于Activity的onCreate()方法调用的
        //第一：默认初始化
        Bmob.initialize(this, "85e81cebd5ed2f4dda7fcfa930ab054f");
        bmp = new BackDataOperateManager(this);
    }
    public void loginIn(String name, String pwd, final Context context)
    {
        bmobUser = BmobUser.getCurrentUser(this);
       if(bmobUser != null){
        }
        else {
           //缓存用户对象为空时， 可打开用户注册界面…*/
           final BmobUser bu2 = new BmobUser();
           bu2.setUsername(name);
           bu2.setPassword(pwd);
           bu2.login(this, new SaveListener() {
               @Override
               public void onSuccess() {

                   //登录成功保存name
                   BackNeedData bd = new BackNeedData();
                   bd.setInId(bu2.getUsername());
                   try {
                       bmp.insert(bd);
                   } catch (Exception e1) {
                       e1.printStackTrace();
                   }
                   Intent loginbackintent = new Intent();
                   loginbackintent.putExtra("loginkey", bu2.getUsername());
                   LoginActivity lo = (LoginActivity) context;
                   lo.setResult(600, loginbackintent);
                   lo.finish();
                   Toast.makeText(lo, "登录成功", Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onFailure(int i, String s) {
                   LoginActivity lo1 = (LoginActivity) context;
                   Toast.makeText(lo1, "登录失败", Toast.LENGTH_SHORT).show();
               }
           });
       }
          /*  BmobUser.loginByAccount(this, name, pwd, new LogInListener<UserEntity>() {
                @Override
                public void done(UserEntity userEntity, BmobException e) {
                    if (userEntity != null) {
                        flag=true;
                    }
                    else
                    {
                        flag=false;
                    }
                }
            });*/
       /* }*/
    }
    public void loginOut()
    {
        BmobUser.logOut(this);
    }

    public static Executor getExecutor() {
        return executor;
    }



}
