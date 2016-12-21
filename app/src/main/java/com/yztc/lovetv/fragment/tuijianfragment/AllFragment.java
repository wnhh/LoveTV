package com.yztc.lovetv.fragment.tuijianfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.AllFragmentAdapter;
import com.yztc.lovetv.apiservice.VpPictureApiService;
import com.yztc.lovetv.bean.AllFragmentBean;
import com.yztc.lovetv.bean.TuijianAllBean;
import com.yztc.lovetv.contant.BaseUrl;
import com.yztc.lovetv.myutil.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {
    RecyclerView allItem ;
    Retrofit retrofit;
    List<AllFragmentBean> allFragmentBeans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all, container, false);
        initRecyclerView(v);
        return v;
    }

    private void initRecyclerView(View v) {
        allFragmentBeans = new ArrayList<>();
        allItem = (RecyclerView) v.findViewById(R.id.tuijian_all);
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.VPPICTURE)
                .client(OkHttpUtils.newOkHttpClient(getContext()))
                .build();
        VpPictureApiService vpPictureApiService = retrofit.create(VpPictureApiService.class);
        Call<ResponseBody> call = vpPictureApiService.getAllPathCall(BaseUrl.ALLPATHFIRST);
        /*call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                String result = "";
                try {
                    result = response.body().string();
                    Log.e("TAG", "onResponse: "+result );
                    TuijianAllBean tuijianAllBean = gson.fromJson(result, TuijianAllBean.class);
                    for (int i = 0;i<tuijianAllBean.getData().size();i++) {
                        AllFragmentBean allFragmentBean = new AllFragmentBean();
                        allFragmentBean.setImgUrl(tuijianAllBean.getData().get(i).getThumb());//视频截图
                        allFragmentBean.setIconUrl(tuijianAllBean.getData().get(i).getAvatar());//头像
                        allFragmentBean.setCount(tuijianAllBean.getData().get(i).getView());//观看直播人的数量
                        allFragmentBean.setTitle(tuijianAllBean.getData().get(i).getTitle());//Title
                        allFragmentBean.setName(tuijianAllBean.getData().get(i).getNick());
                        allFragmentBeans.add(allFragmentBean);
                    }
                    AllFragmentAdapter  allFragmentAdapter = new AllFragmentAdapter(getContext(),allFragmentBeans);
                    allItem.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
                    allItem.setAdapter(allFragmentAdapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

    }


}
