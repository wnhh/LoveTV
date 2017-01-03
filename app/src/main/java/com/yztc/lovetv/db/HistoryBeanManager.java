package com.yztc.lovetv.db;

import android.content.Context;

import com.yztc.lovetv.bean.HistoryBean;
import com.yztc.lovetv.greendao.DaoSession;
import com.yztc.lovetv.greendao.HistoryBeanDao;

import java.util.List;

/**
 * Created by My on 2016/12/28.
 */

public class HistoryBeanManager {
    private Context context;

    public HistoryBeanManager(Context context) {
        this.context = context;

    }

    private HistoryBeanDao getHistroyBeanDao(){
        DaoSession daoSession = NewDBHelper.getDaoSession(context);
        return daoSession.getHistoryBeanDao();
    }

    //插入一条数据
    public void insert(HistoryBean history) throws Exception{
        if (history == null) {
            return;
        }
        getHistroyBeanDao().insert(history);
    }
    //添加多条数据
    public void insertAll(List<HistoryBean> history)  throws Exception{
        if (history == null || history.size() == 0) {
            return;
        }
        getHistroyBeanDao().insertInTx(history);
    }

    //删除一条数据
    public void delete(HistoryBean history) throws Exception{
        if (history == null) {
            return;
        }
        getHistroyBeanDao().delete(history);
    }
    //删除所有数据
    public void deleteAll()  throws Exception{
        getHistroyBeanDao().deleteAll();
    }
    //更新一条数据
    public void update(HistoryBean history) throws Exception{
        if (history == null) {
            return;
        }
        getHistroyBeanDao().update(history);
    }
    //更新多条数据
    public void updateAll(List<HistoryBean> history) throws Exception{
        if (history == null || history.size() == 0) {
            return;
        }
        getHistroyBeanDao().updateInTx(history);
    }
    //查找所有数据
    public List<HistoryBean> getAll() throws Exception{
        return getHistroyBeanDao().queryBuilder().list();
    }
}
