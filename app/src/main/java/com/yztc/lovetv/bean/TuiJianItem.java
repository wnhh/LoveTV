package com.yztc.lovetv.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by My on 2016/12/14.
 */

public class TuiJianItem {


	private String bigPicUrl;
	private String personalPicUrl;
	private String name;
	private String introduce;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	private int uId;

	public String getBigPicUrl() {
		return bigPicUrl;
	}
	public void setBigPicUrl(String bigPicUrl) {
		this.bigPicUrl = bigPicUrl;
	}

	public String getPersonalPicUrl() {
		return personalPicUrl;
	}

	public void setPersonalPicUrl(String personalPicUrl) {
		this.personalPicUrl = personalPicUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "TuiJianItem{" +
				"bigPicUrl='" + bigPicUrl + '\'' +
				", personalPicUrl='" + personalPicUrl + '\'' +
				", name='" + name + '\'' +
				", introduce='" + introduce + '\'' +
				'}';
	}
}
