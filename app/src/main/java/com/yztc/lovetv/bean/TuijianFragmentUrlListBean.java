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
        TuijianFragmentUrlListBean urlBean1 = new TuijianFragmentUrlListBean();
        urlBean1.setName("love");
        urlBean1.setUrl(TvUrl.YANZHI);
        lists.add(urlBean1);
        //英雄联盟
        TuijianFragmentUrlListBean urlBean2 = new TuijianFragmentUrlListBean();
        urlBean2.setName("lol");
        urlBean2.setUrl(TvUrl.LOL);
        lists.add(urlBean2);
        //全民星秀
        TuijianFragmentUrlListBean urlBean3 = new TuijianFragmentUrlListBean();
        urlBean3.setName("beauty");
        urlBean3.setUrl(TvUrl.SHOW);
        lists.add(urlBean3);
        //守望先锋
        TuijianFragmentUrlListBean urlBean4 = new TuijianFragmentUrlListBean();
        urlBean4.setName("overwatch");
        urlBean4.setUrl(TvUrl.SHOUWANG);
        lists.add(urlBean4);
        //全民户外
        TuijianFragmentUrlListBean urlBean5 = new TuijianFragmentUrlListBean();
        urlBean5.setName("huwai");
        urlBean5.setUrl(TvUrl.HUWAI);
        lists.add(urlBean5);
        //炉石传说
        TuijianFragmentUrlListBean urlBean6 = new TuijianFragmentUrlListBean();
        urlBean6.setName("heartstone");
        urlBean6.setUrl(TvUrl.LUSHI);
        lists.add(urlBean6);
        //手游专区
        TuijianFragmentUrlListBean urlBean7 = new TuijianFragmentUrlListBean();
        urlBean7.setName("mobilegame");
        urlBean7.setUrl(TvUrl.SHOUYOU);
        lists.add(urlBean7);
        //网游竞技
        TuijianFragmentUrlListBean urlBean8 = new TuijianFragmentUrlListBean();
        urlBean8.setName("webgame");
        urlBean8.setUrl(TvUrl.WANGYOU);
        lists.add(urlBean8);
        //单机主机
        TuijianFragmentUrlListBean urlBean9 = new TuijianFragmentUrlListBean();
        urlBean9.setName("tvgame");
        urlBean9.setUrl(TvUrl.DANJI);
        lists.add(urlBean9);
        //球球大作战
        TuijianFragmentUrlListBean urlBean10 = new TuijianFragmentUrlListBean();
        urlBean10.setName("qiuqiu");
        urlBean10.setUrl(TvUrl.QIUQIU);
        lists.add(urlBean10);
        //穿越火线
        TuijianFragmentUrlListBean urlBean11 = new TuijianFragmentUrlListBean();
        urlBean11.setName("cfpc");
        urlBean11.setUrl(TvUrl.CF);
        lists.add(urlBean11);
        //QQ飞车
        TuijianFragmentUrlListBean urlBean12 = new TuijianFragmentUrlListBean();
        urlBean12.setName("qqfeiche");
        urlBean12.setUrl(TvUrl.FEICHE);
        lists.add(urlBean12);
        //NBA2K
        TuijianFragmentUrlListBean urlBean13 = new TuijianFragmentUrlListBean();
        urlBean13.setName("nba2k");
        urlBean13.setUrl(TvUrl.KONLINE);
        lists.add(urlBean13);
        //FIFA
        TuijianFragmentUrlListBean urlBean14 = new TuijianFragmentUrlListBean();
        urlBean14.setName("fifa");
        urlBean14.setUrl(TvUrl.FIFA);
        lists.add(urlBean14);
        //魔兽争霸3
        TuijianFragmentUrlListBean urlBean15 = new TuijianFragmentUrlListBean();
        urlBean15.setName("war3");
        urlBean15.setUrl(TvUrl.WOW);
        lists.add(urlBean15);
        //王者荣耀
        TuijianFragmentUrlListBean urlBean16 = new TuijianFragmentUrlListBean();
        urlBean16.setName("wangzhe");
        urlBean16.setUrl(TvUrl.WANGZHE);
        lists.add(urlBean16);
        //我的世界
        TuijianFragmentUrlListBean urlBean17 = new TuijianFragmentUrlListBean();
        urlBean17.setName("minecraft");
        urlBean17.setUrl(TvUrl.SHIJIE);
        lists.add(urlBean17);
        //DNF
        TuijianFragmentUrlListBean urlBean18 = new TuijianFragmentUrlListBean();
        urlBean18.setName("dnf");
        urlBean18.setUrl(TvUrl.DNF);
        lists.add(urlBean18);
        //暴雪经典
        TuijianFragmentUrlListBean urlBean19 = new TuijianFragmentUrlListBean();
        urlBean19.setName("blizzard");
        urlBean19.setUrl(TvUrl.BAOFENG);
        lists.add(urlBean19);
        //DOTA2
        TuijianFragmentUrlListBean urlBean20 = new TuijianFragmentUrlListBean();
        urlBean20.setName("dota2");
        urlBean20.setUrl(TvUrl.DOTA);
        lists.add(urlBean20);
        //二次元区
        TuijianFragmentUrlListBean urlBean21 = new TuijianFragmentUrlListBean();
        urlBean21.setName("erciyuan");
        urlBean21.setUrl(TvUrl.ERCIYUAN);
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
