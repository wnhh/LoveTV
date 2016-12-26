package com.yztc.lovetv.bean;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by My on 2016/12/25.
 */

public class ChatMessage {
    private Type type;//消息类型

    private String msg;//消息内容

    private Date date;//日期

    private String dateStr;//日期的字符串格式

    private String name;//发送人

    public enum Type{
        INPUT,OUTPUT
    }

    public ChatMessage() {
    }

    public ChatMessage(Type type, String msg) {
        super();
        this.type = type;
        this.msg = msg;
        setDate(new Date());
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDate(Date date) {
        this.date = date;
        DateFormat df = new SimpleDateFormat("MM-dd HH:mm");
        this.dateStr = df.format(date);

    }
}
