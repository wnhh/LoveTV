package com.yztc.lovetv.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by My on 2016/12/27.
 */
@Entity
public class BackNeedData {

	@Id
	private Long _id;  //主键
	//用户id
	private String userId;
	//跳转的id
	private String inId;
	//大图
	private String bigImg;
	//用户头像
	private String headImg;
	//用户name
	private String username;
	//简单介绍
	private String titleInfo;
	private boolean switchBtn;
	@Generated(hash = 447235357)
	public BackNeedData(Long _id, String userId, String inId, String bigImg,
									String headImg, String username, String titleInfo, boolean switchBtn) {
					this._id = _id;
					this.userId = userId;
					this.inId = inId;
					this.bigImg = bigImg;
					this.headImg = headImg;
					this.username = username;
					this.titleInfo = titleInfo;
					this.switchBtn = switchBtn;
	}
	@Generated(hash = 1504762173)
	public BackNeedData() {
	}
	public Long get_id() {
					return this._id;
	}
	public void set_id(Long _id) {
					this._id = _id;
	}
	public String getUserId() {
					return this.userId;
	}
	public void setUserId(String userId) {
					this.userId = userId;
	}
	public String getInId() {
					return this.inId;
	}
	public void setInId(String inId) {
					this.inId = inId;
	}
	public String getBigImg() {
					return this.bigImg;
	}
	public void setBigImg(String bigImg) {
					this.bigImg = bigImg;
	}
	public String getHeadImg() {
					return this.headImg;
	}
	public void setHeadImg(String headImg) {
					this.headImg = headImg;
	}
	public String getUsername() {
					return this.username;
	}
	public void setUsername(String username) {
					this.username = username;
	}
	public String getTitleInfo() {
					return this.titleInfo;
	}
	public void setTitleInfo(String titleInfo) {
					this.titleInfo = titleInfo;
	}
	public boolean getSwitchBtn() {
					return this.switchBtn;
	}
	public void setSwitchBtn(boolean switchBtn) {
					this.switchBtn = switchBtn;
	}
}