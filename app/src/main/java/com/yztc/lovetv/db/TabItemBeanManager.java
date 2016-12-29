package com.yztc.lovetv.db;

import android.content.Context;

import com.yztc.lovetv.bean.TabItemBean;
import com.yztc.lovetv.greendao.DaoSession;
import com.yztc.lovetv.greendao.TabItemBeanDao;

import java.util.List;

/**
 * Created by My on 2016/12/28.
 */

public class TabItemBeanManager {
    private Context context;

    public TabItemBeanManager(Context context) {
        this.context = context;

    }

    private TabItemBeanDao getTabItemBeanDao(){
        DaoSession daoSession = NewDBHelper.getDaoSession(context);
        return daoSession.getTabItemBeanDao();
    }

    //插入一条数据
    public void insert(TabItemBean bnd) throws Exception{
        if (bnd == null) {
            return;
        }
        getTabItemBeanDao().insert(bnd);
    }
    //添加多条数据
    public void insertAll(List<TabItemBean> bnds)  throws Exception{
        if (bnds == null || bnds.size() == 0) {
            return;
        }
        getTabItemBeanDao().insertInTx(bnds);
    }

    //删除一条数据
    public void delete(TabItemBean bnd) throws Exception{
        if (bnd == null) {
            return;
        }
        getTabItemBeanDao().delete(bnd);
    }
    //删除所有数据
    public void deleteAll()  throws Exception{
        getTabItemBeanDao().deleteAll();
    }
    //更新一条数据
    public void update(TabItemBean bnd) throws Exception{
        if (bnd == null) {
            return;
        }
        getTabItemBeanDao().update(bnd);
    }
    //更新多条数据
    public void updateAll(List<TabItemBean> bnds) throws Exception{
        if (bnds == null || bnds.size() == 0) {
            return;
        }
        getTabItemBeanDao().updateInTx(bnds);
    }
    //查找所有数据
    public List<TabItemBean> getAll() throws Exception{
        return getTabItemBeanDao().queryBuilder().list();
    }
    //根据id查找一条数据
    public TabItemBean getStudent(Long id) throws Exception{
        List<TabItemBean> list = getTabItemBeanDao().queryBuilder()
                .where(TabItemBeanDao.Properties._id.eq(id))
                .list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    //根据id查找一条数据
    public TabItemBean getItemInfo(String name) throws Exception{
        List<TabItemBean> list = getTabItemBeanDao().queryBuilder()
                .where(TabItemBeanDao.Properties.Name.eq(name))
                .list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public TabItemBean getItem(String itemName) throws Exception{
        List<TabItemBean> list = getTabItemBeanDao().queryBuilder()
                .where(TabItemBeanDao.Properties.ItemName.eq(itemName))
                .list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
