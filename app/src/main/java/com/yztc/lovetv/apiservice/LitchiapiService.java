package com.yztc.lovetv.apiservice;

import com.yztc.lovetv.bean.Tuijian;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by My on 2016/12/12.
 */

public interface LitchiapiService {
	//get请求固定请求路径
	@GET("list-android.json?11212054&v=2.2.4&os=1&ver=4")
	Observable<Tuijian> getLitchCall();
	//动态的url
	@GET("{path}?column=0&PageSize=10&pageIndex=1")
	Call<ResponseBody> getLitchCall(@Path("path") String path);
	//查询参数
	@GET("GetFeeds")
	Call<ResponseBody> getLitchCall(
			@Query("column") int column,
			@Query("PageSize") int PageSize,
			@Query("pageIndex") int pageIndex
	);
	//查询参数集合
	@GET("GetFeeds")
	Call<ResponseBody> getLitchCall(@QueryMap HashMap<String, Integer> querymap);
	//动态url + 查询参数
	@GET("{path}")
	Call<ResponseBody> getLitchCall(
			@Path("path") String path,
			@Query("column") int column,
			@Query("PageSize") int PageSize,
			@Query("pageIndex") int pageIndex
	);
}
