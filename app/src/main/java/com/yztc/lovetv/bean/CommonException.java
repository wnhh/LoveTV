package com.yztc.lovetv.bean;

/**
 * Created by My on 2016/12/26.
 */


public class CommonException extends RuntimeException
{

    public CommonException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommonException(String detailMessage, Throwable throwable)
    {
        super(detailMessage, throwable);
        // TODO Auto-generated constructor stub
    }

    public CommonException(String detailMessage)
    {
        super(detailMessage);
        // TODO Auto-generated constructor stub
    }

    public CommonException(Throwable throwable)
    {
        super(throwable);
        // TODO Auto-generated constructor stub
    }

}
