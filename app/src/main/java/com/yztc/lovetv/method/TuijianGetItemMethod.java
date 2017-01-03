package com.yztc.lovetv.method;

import android.util.Log;

import com.yztc.lovetv.bean.TuiJianItem;
import com.yztc.lovetv.bean.Tuijian;
import com.yztc.lovetv.bean.TuijianStringitem;

import java.util.List;

/**
 * Created by My on 2016/12/18.
 */

public class TuijianGetItemMethod {
    public static TuijianStringitem getItemMessage(Tuijian tuijian,int itemPosition ,int position){//传进来Gson对象 和第几组的第几个的position
        TuiJianItem ti = new TuiJianItem();
        ti.setBigPicUrl(tuijian.getRoom().get(itemPosition).getList().get(position).getThumb());
        ti.setPersonalPicUrl(tuijian.getRoom().get(itemPosition).getList().get(position).getAvatar());
        ti.setIntroduce(tuijian.getRoom().get(itemPosition).getList().get(position).getTitle());
        ti.setName(tuijian.getRoom().get(itemPosition).getList().get(position).getNick());
        ti.setuId(tuijian.getRoom().get(itemPosition).getList().get(position).getUid());
        TuijianStringitem tti = new TuijianStringitem(ti);
        tti.setMore(true);

        return tti;
    }
    //添加item的方法
    public static void addItem(String name,List<TuijianStringitem> listTj ,Tuijian tuijian,int itemPosition ){
        TuijianStringitem ttitem = new TuijianStringitem(true, tuijian.getRoom().get(itemPosition).getName(), true);
        listTj.add(ttitem);
        for (int i = 0; i < tuijian.getRoom().get(itemPosition).getList().size(); i++) {
            if (i == tuijian.getRoom().get(itemPosition).getList().size()) {

                break;
            }
            listTj.add(TuijianGetItemMethod.getItemMessage(tuijian,itemPosition,i));
        }
    }
}
