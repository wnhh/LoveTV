package com.yztc.lovetv.apiservice;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by My on 2016/12/17.
 */

public interface VpPictureApiService {

    //Get请求固定参数
    @GET("page/app-data/info.json?v=2.2.4&os=1&ver=4")
    Call<ResponseBody> getAdTypeBeanCall();

    //Get请求固定参数
    @GET("getdata?_app=qmtv&cate=game_center&platform=android")
    Call<ResponseBody> getCenterGameBeanCall();

    //Get请求动态添加参数
    @GET("{path}.json?12211353&v=2.2.4&os=1&ver=4")
    Call<ResponseBody> getAllPathCall(
            @Path("path") String path
    );

    //Get请求动态添加参数
    @GET("play/list.json?12201956&v=2.2.4&os=1&ver=4")
    Call<ResponseBody> getAllCall();
}
