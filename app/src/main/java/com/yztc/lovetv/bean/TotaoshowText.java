package com.yztc.lovetv.bean;

/**
 * Created by Administrator on 2016/12/20.
 */

public class TotaoshowText {
    //构建五个元素
    //LiveShot,NumView,HeadImage,AnchorName,VideoBrief



    private String HeadImageId;     //头像截图
    private String NameId;          //主播姓名
    private String Brief;           //直播简介
    private String LiveShotId;      //视频直播截图
    private String RoomNumId;       //视频房间号码
    private String WatchNum;        //在线观看人数

    public String getWatchNum() {
        return WatchNum;
    }

    public void setWatchNum(String watchNum) {
        WatchNum = watchNum;
    }

    public String getRoomNumId() {
        return RoomNumId;
    }

    public void setRoomNumId(String roomNumId) {
        RoomNumId = roomNumId;
    }

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

    public String getLiveShotId() {
        return LiveShotId;
    }

    public void setLiveShotId(String liveShotId) {
        LiveShotId = liveShotId;
    }
}
