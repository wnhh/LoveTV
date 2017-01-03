package com.yztc.lovetv.bean;

/**
 * Created by Administrator on 2017/1/2.
 */

public class ContentText {

    private String HeadImageId;     //头像截图
    private String NameId;          //主播姓名
    private String Brief;           //直播简介

    public String getHeadImageId() {
        return HeadImageId;
    }

    public void setHeadImageId(String headImageId) {
        HeadImageId = headImageId;
    }

    public String getNameId() {
        return NameId;
    }

    public void setNameId(String nameId) {
        NameId = nameId;
    }

    public String getBrief() {
        return Brief;
    }

    public void setBrief(String brief) {
        Brief = brief;
    }
}
