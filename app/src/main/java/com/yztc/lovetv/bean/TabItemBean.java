package com.yztc.lovetv.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by My on 2016/12/28.
 */
@Entity
public class TabItemBean {
   @Id
    private Long _id;//主键

    private String itemName;//item名字

    private String name;//接口名字

    private int itemId;

    private String bigImg;//具体图片

    private String iconImg;//头像

    private String viewCount;//观看人数

    private String info;//简介

   @Generated(hash = 123481063)
public TabItemBean(Long _id, String itemName, String name, int itemId,
        String bigImg, String iconImg, String viewCount, String info) {
    this._id = _id;
    this.itemName = itemName;
    this.name = name;
    this.itemId = itemId;
    this.bigImg = bigImg;
    this.iconImg = iconImg;
    this.viewCount = viewCount;
    this.info = info;
}

@Generated(hash = 1363306915)
   public TabItemBean() {
   }

   public Long get_id() {
       return this._id;
   }

   public void set_id(Long _id) {
       this._id = _id;
   }

   public String getItemName() {
       return this.itemName;
   }

   public void setItemName(String itemName) {
       this.itemName = itemName;
   }

   public int getItemId() {
       return this.itemId;
   }

   public void setItemId(int itemId) {
       this.itemId = itemId;
   }

   public String getBigImg() {
       return this.bigImg;
   }

   public void setBigImg(String bigImg) {
       this.bigImg = bigImg;
   }

   public String getIconImg() {
       return this.iconImg;
   }

   public void setIconImg(String iconImg) {
       this.iconImg = iconImg;
   }

   public String getName() {
       return this.name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getViewCount() {
       return this.viewCount;
   }

   public void setViewCount(String viewCount) {
       this.viewCount = viewCount;
   }

   public String getInfo() {
       return this.info;
   }

   public void setInfo(String info) {
       this.info = info;
   }

}
