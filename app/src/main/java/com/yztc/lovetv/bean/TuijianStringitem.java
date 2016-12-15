package com.yztc.lovetv.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by My on 2016/12/14.
 */

public class TuijianStringitem extends SectionEntity<TuiJianItem> {


	private boolean isMore;
	public TuijianStringitem(boolean isHeader, String header,boolean isMore) {
		super(isHeader, header);
		this.isMore=isMore;
	}
	public TuijianStringitem(TuiJianItem t) {
		super(t);
	}
	public boolean isMore() {
		return isMore;
	}

	public void setMore(boolean more) {
		isMore = more;
	}
}
