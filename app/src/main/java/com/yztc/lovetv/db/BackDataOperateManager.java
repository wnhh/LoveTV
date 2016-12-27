package com.yztc.lovetv.db;

import android.content.Context;

import com.yztc.lovetv.bean.BackNeedData;
import com.yztc.lovetv.greendao.BackNeedDataDao;
import com.yztc.lovetv.greendao.DaoSession;

import java.util.List;

/**
 * Created by bodhixu on 2016/12/7.
 * 学生表的管理
 */

public class BackDataOperateManager {

    public BackNeedDataDao backNeedDataDao;

    public BackDataOperateManager(Context context) {
        DaoSession daoSession = NewDBHelper.getDaoSession(context);
        backNeedDataDao = daoSession.getBackNeedDataDao();
    }
    //插入一条数据
    public void insert(BackNeedData bnd) throws Exception{
        if (bnd == null) {
            return;
        }
        backNeedDataDao.insert(bnd);
    }
    //添加多条数据
    public void insertAll(List<BackNeedData> bnds)  throws Exception{
        if (bnds == null || bnds.size() == 0) {
            return;
        }
        backNeedDataDao.insertInTx(bnds);
    }

    //删除一条数据
    public void delete(BackNeedData bnd) throws Exception{
        if (bnd == null) {
            return;
        }
        backNeedDataDao.delete(bnd);
    }
    //删除所有数据
    public void deleteAll()  throws Exception{
        backNeedDataDao.deleteAll();
    }
    //更新一条数据
    public void update(BackNeedData bnd) throws Exception{
        if (bnd == null) {
            return;
        }
        backNeedDataDao.update(bnd);
    }
    //更新多条数据
    public void updateAll(List<BackNeedData> bnds) throws Exception{
        if (bnds == null || bnds.size() == 0) {
            return;
        }
        backNeedDataDao.updateInTx(bnds);
    }
    //查找所有数据
    public List<BackNeedData> getAll() throws Exception{
        return backNeedDataDao.queryBuilder().list();
    }
    //根据id查找一条数据
    public BackNeedData getStudent(Long id) throws Exception{
        List<BackNeedData> list = backNeedDataDao.queryBuilder()
                .where(BackNeedDataDao.Properties.Id.eq(id))
                .list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
