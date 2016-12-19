package com.yztc.lovetv.fragment.other;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.SectionAdapter;
import com.yztc.lovetv.adapter.vpPagerAdapter;
import com.yztc.lovetv.apiservice.LitchiapiService;
import com.yztc.lovetv.apiservice.VpPictureApiService;
import com.yztc.lovetv.bean.FirstPagerBean;
import com.yztc.lovetv.bean.LunBoPictureBean;
import com.yztc.lovetv.bean.TuijianStringitem;
import com.yztc.lovetv.bean.Tuijian;
import com.yztc.lovetv.contant.BaseUrl;
import com.yztc.lovetv.contant.TvUrl;
import com.yztc.lovetv.method.TuijianGetItemMethod;
import com.yztc.lovetv.myutil.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tuijian_Fragment_Vp extends Fragment {

    private int temp = 1;//接口中第几个
    private Retrofit rfit, vfit;
    private LitchiapiService litchApiService;
    private VpPictureApiService mApiService;
    private RecyclerView tuijianitem_rv;
    private RollPagerView mViewPager;
    //数据源
    List<String> liststr;
    List<TuijianStringitem> listTj;
    List<LunBoPictureBean> lunBoPictureBeans;
    //adapter
    private SectionAdapter sectionAdapter;
    private RelativeLayout ll_vp;

    @Override
    public void onResume() {
        mViewPager.resume();
        super.onResume();
    }

    @Override
    public void onStop() {
        mViewPager.pause();
        super.onStop();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tuijian_vp, container, false);
        View inflate = inflater.inflate(R.layout.item_vp, container, false);
        mViewPager = (RollPagerView) inflate.findViewById(R.id.vp_mainad);
        mViewPager.setHintView(new ColorPointHintView(getContext(), Color.WHITE, Color.GRAY));
        ll_vp = (RelativeLayout) inflate.findViewById(R.id.ll_vp);
        initViewPager();
        initRecyclerView(v);
        initRetrofit();
        return v;
    }

    private void initRecyclerView(View v) {
        tuijianitem_rv = (RecyclerView) v.findViewById(R.id.tuijianitem_rv);
        tuijianitem_rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TuijianStringitem ts = listTj.get(i);
                if (ts.isHeader) {
                    //陈雨晗你可以写跳转了！
                }
            }
        });

        rfit = new Retrofit.Builder()
                .baseUrl(TvUrl.TUIAJIN)
                .build();
    }

    private void initViewPager() {
        lunBoPictureBeans = new ArrayList<LunBoPictureBean>();

        //实例化小圆点
        vfit = new Retrofit.Builder()
                .baseUrl(BaseUrl.VpPicture)
                .build();
        mApiService = vfit.create(VpPictureApiService.class);
        Call<ResponseBody> call = mApiService.getAdTypeBeanCall();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string().replaceAll("-", "_");
                    Gson gson = new Gson();
                    FirstPagerBean firstPagerBean = gson.fromJson(json, FirstPagerBean.class);
                    for (int i = 0; i < firstPagerBean.getApp_focus().size(); i++) {
                        LunBoPictureBean lunBoPicture = new LunBoPictureBean();
                        lunBoPicture.setPicture(firstPagerBean.getApp_focus().get(i).getThumb());
                        //Log.e("TAG", "firstPagerBean.getApp_focus().get(i).getThumb() "+firstPagerBean.getApp_focus().get(i).getThumb());
                        lunBoPicture.setTitle(firstPagerBean.getApp_focus().get(i).getTitle());
                        lunBoPictureBeans.add(lunBoPicture);
                    }


                    //ViewPager适配器
                    //Adapter里动态生成ViewPager的布局
                    vpPagerAdapter vpPagerAdapter = new vpPagerAdapter(mViewPager, getContext(), lunBoPictureBeans);
                    //set适配器
                    mViewPager.setAdapter(vpPagerAdapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void initRetrofit() {
        liststr = new ArrayList<>();
        listTj = new ArrayList<>();
        TuijianStringitem ttitem = new TuijianStringitem(true, "精彩推荐", true);
        listTj.add(ttitem);
        //实例化Retrofit
        rfit = new Retrofit.Builder()
                .baseUrl(BaseUrl.TUIJIANITEM)
                .client(OkHttpUtils.newOkHttpClient(getContext()))//进入单例模式保证线程安全
                .addConverterFactory(GsonConverterFactory.create())//Gson解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        litchApiService = rfit.create(LitchiapiService.class);
        //通过Rxjava进行在新线程里获得json数据并set值
        litchApiService.getLitchCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Tuijian>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Tuijian tuijian) {
                        for (int i = 0; i < tuijian.getRoom().get(0).getList().size(); i++) {
                            if (i == 6) {
                                break;
                            }
                            listTj.add(TuijianGetItemMethod.getItemMessage(tuijian, 0, i));
                        }
                        //颜值控
                        TuijianGetItemMethod.addItem("颜值控", listTj, tuijian, temp++);
                        //英雄联盟
                        TuijianGetItemMethod.addItem("英雄联盟", listTj, tuijian, temp++);
                        //全民星秀
                        TuijianGetItemMethod.addItem("全民星秀", listTj, tuijian, temp++);
                        //守望先锋
                        TuijianGetItemMethod.addItem("守望先锋", listTj, tuijian, temp++);
                        //全民户外
                        TuijianGetItemMethod.addItem("全民户外", listTj, tuijian, temp++);
                        //炉石传说
                        TuijianGetItemMethod.addItem("炉石传说", listTj, tuijian, temp++);
                        //手游专区
                        TuijianGetItemMethod.addItem("手游专区", listTj, tuijian, temp++);
                        //网游竞技
                        TuijianGetItemMethod.addItem("网游竞技", listTj, tuijian, temp++);
                        //单机主机
                        TuijianGetItemMethod.addItem("单机主机", listTj, tuijian, temp++);
                        sectionAdapter = new SectionAdapter(getContext(), listTj, 1);
                        sectionAdapter.addHeaderView(ll_vp);
                        tuijianitem_rv.setAdapter(sectionAdapter);
                        tuijianitem_rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    }
                });

    }


}
