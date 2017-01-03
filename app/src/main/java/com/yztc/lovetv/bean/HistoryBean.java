package com.yztc.lovetv.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by My on 2017/1/3.
 */
@Entity
public class HistoryBean {
    @Id
    private Long _id;

    private String history;

    @Generated(hash = 1941904951)
    public HistoryBean(Long _id, String history) {
        this._id = _id;
        this.history = history;
    }

    @Generated(hash = 48590348)
    public HistoryBean() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
