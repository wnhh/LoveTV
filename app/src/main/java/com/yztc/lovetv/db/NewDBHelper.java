package com.yztc.lovetv.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yztc.lovetv.greendao.DaoMaster;
import com.yztc.lovetv.greendao.DaoSession;

/**
 * Created by bodhixu on 2016/12/7.
 * 数据库管理基类
 */
public class NewDBHelper {

    private static final String DB_NAME = "lovetv.db"; //数据库的名字

    private static NewDBHelper dbHelper;
    private static DaoMaster.DevOpenHelper devOpenHelper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    //私有化构造方法
    private NewDBHelper(Context context) {
        devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
    }

    //单例模式获得工具类对象
    public static NewDBHelper newInstance(Context context) {
        if (null == dbHelper) {
            synchronized (NewDBHelper.class) {
                if (null == dbHelper) {
                    dbHelper = new NewDBHelper(context);
                }
            }
        }
        return dbHelper;
    }

    //获取数据库
    public static SQLiteDatabase getReadableDatabase(Context context) {
        newInstance(context);
        return devOpenHelper.getReadableDatabase();
    }

    //获得DaoMaster的单例
    public static DaoMaster getDaoMaster(Context context) {
        if (null == daoMaster) {
            synchronized (NewDBHelper.class) {
                if (null == daoMaster) {
                    daoMaster = new DaoMaster(getReadableDatabase(context));
                }
            }
        }
        return daoMaster;
    }

    //获得DaoSession单例
    public static DaoSession getDaoSession(Context context) {
        if (null == daoSession) {
            synchronized (NewDBHelper.class) {
                if (null == daoSession) {
                    daoSession = getDaoMaster(context).newSession();
                }
            }
        }
        return daoSession;
    }
}
