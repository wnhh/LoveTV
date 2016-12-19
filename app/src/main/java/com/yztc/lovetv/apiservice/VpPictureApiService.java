package com.yztc.lovetv.apiservice;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by My on 2016/12/17.
 */

public interface VpPictureApiService {

    //Get请求固定参数
    @GET("info.json?v=2.2.4&os=1&ver=4")
    Call<ResponseBody> getAdTypeBeanCall();
}
