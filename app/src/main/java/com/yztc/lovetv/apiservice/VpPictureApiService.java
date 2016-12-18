package com.yztc.lovetv.apiservice;


import com.yztc.lovetv.bean.FirstPagerBean;
import com.yztc.lovetv.bean.LunBoPictureBean;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by My on 2016/12/17.
 */

public interface VpPictureApiService {

    //Get请求固定参数
    @GET("info.json?v=2.2.4&os=1&ver=4")
    Observable<FirstPagerBean> getAdTypeBeanCall();
}
