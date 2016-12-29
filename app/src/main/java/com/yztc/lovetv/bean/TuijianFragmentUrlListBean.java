package com.yztc.lovetv.bean;

import com.yztc.lovetv.contant.TvUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by My on 2016/12/29.
 */

public class TuijianFragmentUrlListBean {
    private String name;
    private String url;

    public static List<TuijianFragmentUrlListBean> getLists() {
        List<TuijianFragmentUrlListBean> lists = new ArrayList<>();
        //颜值控
        TuijianFragmentUrlListBean urlBean = new TuijianFragmentUrlListBean();
        urlBean.setName("love");
        urlBean.setUrl(TvUrl.YANZHI);
        lists.add(urlBean);
        //英雄联盟
        TuijianFragmentUrlListBean urlBean2 = new TuijianFragmentUrlListBean();
        urlBean2.setName("lol");
        urlBean2.setUrl(TvUrl.LOL);
        lists.add(urlBean);
        //全民星秀
        TuijianFragmentUrlListBean urlBean3 = new TuijianFragmentUrlListBean();
        urlBean.setName("beauty");
        urlBean.setUrl(TvUrl.SHOW);
        lists.add(urlBean3);
        //守望先锋
        TuijianFragmentUrlListBean urlBean4 = new TuijianFragmentUrlListBean();
        urlBean.setName("overwatch");
        urlBean.setUrl(TvUrl.SHOUWANG);
        lists.add(urlBean4);
        //全民户外
        TuijianFragmentUrlListBean urlBean5 = new TuijianFragmentUrlListBean();
        urlBean.setName("huwai");
        urlBean.setUrl(TvUrl.HUWAI);
        lists.add(urlBean5);
        //炉石传说
        TuijianFragmentUrlListBean urlBean6 = new TuijianFragmentUrlListBean();
        urlBean.setName("heartstone");
        urlBean.setUrl(TvUrl.LUSHI);
        lists.add(urlBean6);
        //手游专区
        TuijianFragmentUrlListBean urlBean7 = new TuijianFragmentUrlListBean();
        urlBean.setName("mobilegame");
        urlBean.setUrl(TvUrl.SHOUYOU);
        lists.add(urlBean7);
        //网游竞技
        TuijianFragmentUrlListBean urlBean8 = new TuijianFragmentUrlListBean();
        urlBean.setName("webgame");
        urlBean.setUrl(TvUrl.WANGYOU);
        lists.add(urlBean8);
        //单机主机
        TuijianFragmentUrlListBean urlBean9 = new TuijianFragmentUrlListBean();
        urlBean.setName("tvgame");
        urlBean.setUrl(TvUrl.DANJI);
        lists.add(urlBean9);
        //球球大作战
        TuijianFragmentUrlListBean urlBean10 = new TuijianFragmentUrlListBean();
        urlBean.setName("qiuqiu");
        urlBean.setUrl(TvUrl.QIUQIU);
        lists.add(urlBean10);
        //穿越火线
        TuijianFragmentUrlListBean urlBean11 = new TuijianFragmentUrlListBean();
        urlBean.setName("cfpc");
        urlBean.setUrl(TvUrl.CF);
        lists.add(urlBean11);
        //QQ飞车
        TuijianFragmentUrlListBean urlBean12 = new TuijianFragmentUrlListBean();
        urlBean.setName("qqfeiche");
        urlBean.setUrl(TvUrl.FEICHE);
        lists.add(urlBean12);
        //NBA2K
        TuijianFragmentUrlListBean urlBean13 = new TuijianFragmentUrlListBean();
        urlBean.setName("nba2k");
        urlBean.setUrl(TvUrl.KONLINE);
        lists.add(urlBean13);
        //FIFA
        TuijianFragmentUrlListBean urlBean14 = new TuijianFragmentUrlListBean();
        urlBean.setName("fifa");
        urlBean.setUrl(TvUrl.FIFA);
        lists.add(urlBean);
        //魔兽争霸3
        TuijianFragmentUrlListBean urlBean15 = new TuijianFragmentUrlListBean();
        urlBean.setName("war3");
        urlBean.setUrl(TvUrl.WOW);
        lists.add(urlBean15);
        //王者荣耀
        TuijianFragmentUrlListBean urlBean16 = new TuijianFragmentUrlListBean();
        urlBean.setName("wangzhe");
        urlBean.setUrl(TvUrl.WANGZHE);
        lists.add(urlBean);
        //我的世界
        TuijianFragmentUrlListBean urlBean17 = new TuijianFragmentUrlListBean();
        urlBean.setName("minecraft");
        urlBean.setUrl(TvUrl.SHIJIE);
        lists.add(urlBean17);
        //DNF
        TuijianFragmentUrlListBean urlBean18 = new TuijianFragmentUrlListBean();
        urlBean.setName("dnf");
        urlBean.setUrl(TvUrl.DNF);
        lists.add(urlBean18);
        //暴雪经典
        TuijianFragmentUrlListBean urlBean19 = new TuijianFragmentUrlListBean();
        urlBean.setName("blizzard");
        urlBean.setUrl(TvUrl.BAOFENG);
        lists.add(urlBean19);
        //DOTA2
        TuijianFragmentUrlListBean urlBean20 = new TuijianFragmentUrlListBean();
        urlBean.setName("dota2");
        urlBean.setUrl(TvUrl.DOTA);
        lists.add(urlBean20);
        //二次元区
        TuijianFragmentUrlListBean urlBean21 = new TuijianFragmentUrlListBean();
        urlBean.setName("erciyuan");
        urlBean.setUrl(TvUrl.ERCIYUAN);
        lists.add(urlBean21);
        return lists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
