package com.yztc.lovetv.fragment.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.JingCaiTJAdapter;
import com.yztc.lovetv.adapter.JingCaiTJItemAdapter;
import com.yztc.lovetv.apiservice.LitchiapiService;
import com.yztc.lovetv.bean.TuiJianItem;
import com.yztc.lovetv.bean.tuijian;
import com.yztc.lovetv.contant.TvUrl;

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
public class Tuijian_Fragment_Vp extends Fragment {
    private Retrofit rfit;
    private LitchiapiService litchias;
    private Call<ResponseBody> call;
    private RecyclerView tf_rv,tuijianitem_rv;
    //数据源
    List<String> liststr;
    List<TuiJianItem>listTj;
    //adapter
    private JingCaiTJAdapter jcAdapter;
    private JingCaiTJItemAdapter jcitemAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tuijian_vp, container,false);
        tf_rv= (RecyclerView) v.findViewById(R.id.tf_rv);
        //View view1=LayoutInflater.from(getContext()).inflate(R.layout.item_tuijian_jingcai,container,false);
        View view2=LayoutInflater.from(getContext()).inflate(R.layout.item_tuijian_jingcai_recyclerview,container,false);
        tuijianitem_rv = (RecyclerView) view2.findViewById(R.id.tuijianitem_rv);
        rfit=new Retrofit.Builder()
                .baseUrl(TvUrl.TUIAJIN)
                .build();
        initData();
        initRecycler(view2);
        return v;
    }

    private void initRecycler(View view) {
        tuijianitem_rv.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
        jcAdapter=new JingCaiTJAdapter(getContext(),liststr);
        tf_rv.setAdapter(jcAdapter);
        tf_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }
    private void initData() {
        liststr=new ArrayList<>();
        listTj=new ArrayList<>();
        //测试
        liststr.add("精彩推荐");
        liststr.add("英雄联盟");
        liststr.add("英雄联盟");
        //获得接口对象
        litchias=rfit.create(LitchiapiService.class);
        call=litchias.getLitchCall();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Gson gson=new Gson();
                    tuijian tuijian = gson.fromJson(response.body().string(), tuijian.class);
                    for(int i=0;i<tuijian.getRoom().get(0).getList().size();i++)
                    {
                        if(i==6)
                        {
                            break;
                        }
                        TuiJianItem ti=new TuiJianItem(false,"");
                        ti.setBigPicUrl(tuijian.getRoom().get(0).getList().get(i).getThumb());
                        ti.setPersonalPicUrl(tuijian.getRoom().get(0).getList().get(i).getAvatar());
                        ti.setName(tuijian.getRoom().get(0).getList().get(i).getNick());
                        ti.setName(tuijian.getRoom().get(0).getList().get(i).getTitle());
                        listTj.add(ti);
                    }
                    jcitemAdapter=new JingCaiTJItemAdapter(getContext(),listTj);
                    tuijianitem_rv.setAdapter(jcitemAdapter);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


}
