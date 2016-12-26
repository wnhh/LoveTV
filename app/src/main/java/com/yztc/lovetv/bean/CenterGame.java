package com.yztc.lovetv.bean;

import java.util.List;

/**
 * Created by My on 2016/12/26.
 */

public class CenterGame {

	/**
	 * errno : 0
	 * error :
	 * fingerprint : id=180,ver=13
	 * data : [{"title":"捉妖西游","desc":"登陆即送小悟空","pic":"http://uimg.quanmin.tv/1479787193/d24ef.jpg","game_id":"10027","game_link":"http://down.2144gy.com/zyxy/zyxy_61656.apk","game_source":"2144"},{"title":"格斗学院","desc":"我和你一样,渴望堂堂正正的较量","pic":"http://uimg.quanmin.tv/1479464680/1ae4f.jpg","game_id":"10002","game_link":"http://dl.quanmin.tv/youxi/gdxy.apk","game_source":"2144"}]
	 */

	private int errno;
	private String error;
	private String fingerprint;
	private List<DataBean> data;

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * title : 捉妖西游
		 * desc : 登陆即送小悟空
		 * pic : http://uimg.quanmin.tv/1479787193/d24ef.jpg
		 * game_id : 10027
		 * game_link : http://down.2144gy.com/zyxy/zyxy_61656.apk
		 * game_source : 2144
		 */

		private String title;
		private String desc;
		private String pic;
		private String game_id;
		private String game_link;
		private String game_source;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public String getGame_id() {
			return game_id;
		}

		public void setGame_id(String game_id) {
			this.game_id = game_id;
		}

		public String getGame_link() {
			return game_link;
		}

		public void setGame_link(String game_link) {
			this.game_link = game_link;
		}

		public String getGame_source() {
			return game_source;
		}

		public void setGame_source(String game_source) {
			this.game_source = game_source;
		}
	}
}
