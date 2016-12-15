package com.yztc.lovetv.fragment.other;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.JingCaiTJAdapter;
import com.yztc.lovetv.adapter.SectionAdapter;
import com.yztc.lovetv.apiservice.LitchiapiService;
import com.yztc.lovetv.bean.TuiJianItem;
import com.yztc.lovetv.bean.TuijianStringitem;
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
    private RecyclerView tuijianitem_rv;
    //数据源
    List<String> liststr;
    List<TuijianStringitem>listTj;
    //adapter
    private JingCaiTJAdapter jcAdapter;
    private SectionAdapter sectionAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tuijian_vp, container,false);
        tuijianitem_rv = (RecyclerView)v.findViewById(R.id.tuijianitem_rv);
        tuijianitem_rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rfit=new Retrofit.Builder()
                .baseUrl(TvUrl.TUIAJIN)
                .build();
        initData();
        return v;
    }
    private void initData() {
        liststr=new ArrayList<>();
        listTj=new ArrayList<>();
        TuijianStringitem ttitem=new TuijianStringitem(true,"精彩推荐",true);

        listTj.add(ttitem);
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
                        TuiJianItem ti=new TuiJianItem();
                        ti.setBigPicUrl(tuijian.getRoom().get(0).getList().get(i).getThumb());
                        ti.setPersonalPicUrl(tuijian.getRoom().get(0).getList().get(i).getAvatar());
                        ti.setIntroduce(tuijian.getRoom().get(0).getList().get(i).getTitle());
                        ti.setName(tuijian.getRoom().get(0).getList().get(i).getNick());
                        TuijianStringitem tti=new TuijianStringitem(ti);
                        tti.setMore(true);
                        listTj.add(tti);

                    }
                    TuijianStringitem ttitem2=new TuijianStringitem(true,"颜值控",true);
                    TuijianStringitem ttitem3=new TuijianStringitem(true,"英雄联盟",true);
                    listTj.add(ttitem2);
                    listTj.add(ttitem3);
                    sectionAdapter=new SectionAdapter(getContext(),listTj);
                    tuijianitem_rv.setAdapter(sectionAdapter);

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
