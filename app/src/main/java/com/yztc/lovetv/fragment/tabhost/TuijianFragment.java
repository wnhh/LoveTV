package com.yztc.lovetv.fragment.tabhost;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.ChannelManagerActivity;
import com.yztc.lovetv.activity.GridActivity;
import com.yztc.lovetv.adapter.ChannelManagerAdapter;
import com.yztc.lovetv.adapter.ViewPagerAdapter;
import com.yztc.lovetv.apiservice.LitchiapiService;
import com.yztc.lovetv.bean.Tuijian;
import com.yztc.lovetv.contant.BaseUrl;
import com.yztc.lovetv.contant.TabhostContant;
import com.yztc.lovetv.fragment.tuijianfragment.AllFragment;
import com.yztc.lovetv.fragment.tuijianfragment.Tuijian_Fragment_Vp;
import com.yztc.lovetv.myutil.OkHttpUtils;
import com.yztc.lovetv.myutil.PreferencesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TuijianFragment extends Fragment {
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private ImageView mImageView;

    //数据源
    List<Fragment> mFragments;
    List<String> mTabs;
    List<String> mAllTabs;

    View v;

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            v.invalidate();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_tuijian, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initData() {
        mTabs = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTabs.add("推荐");
//        mTabs.add("全部");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.TUIJIANITEM)
                .client(OkHttpUtils.newOkHttpClient(getContext()))
                .build();
        LitchiapiService litchiapiService = retrofit.create(LitchiapiService.class);
        Call<ResponseBody> call = litchiapiService.getLitchCall(BaseUrl.TUIJIAN);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    Tuijian tuijian = gson.fromJson(result, Tuijian.class);
                    for (int i = 0; i < tuijian.getRoom().size(); i++) {//第一次进来默认添加9个数据(在添加顶部导航是 推荐写死了)
                        String string = PreferencesUtils.getString(getContext(),TabhostContant.TUIJIAN_ITEM_NAME+i);

                        if (string != null){
                            mTabs.add(string);
                        }
                    }

                    Tuijian_Fragment_Vp fragmentVp = new Tuijian_Fragment_Vp();
                    mFragments.add(fragmentVp);
                    for (int i=0;i<mTabs.size()-1;i++){
                        AllFragment allFragment = new AllFragment();
                        mFragments.add(allFragment);
                    }
                    ViewPagerAdapter fragmentAdapter = new ViewPagerAdapter(getChildFragmentManager(),mFragments,mTabs);
                    mViewPager.setOffscreenPageLimit(mTabs.size()-1);
                    mViewPager.setAdapter(fragmentAdapter);
                    mTabLayout.setupWithViewPager(mViewPager);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


//        mTabs.add("推荐");
//        mTabs.add("全部");
//        mTabs.add("颜值控");
//        mTabs.add("英雄联盟");
//        mTabs.add("全民星宿");
//        mTabs.add("守望先锋");
//        mTabs.add("全民户外");
//        mTabs.add("炉石传说");
//        mTabs.add("手游专区");
//        mTabs.add("网游竞技");
//        mTabs.add("单机主机");
//        mTabs.add("球球大作战");


    }

    private void initView(View v) {
        mTabLayout = (TabLayout) v.findViewById(R.id.tablayout);
        mToolbar = (Toolbar) v.findViewById(R.id.lm_fragment_toolbar);
        mViewPager = (ViewPager) v.findViewById(R.id.firstfragment_vp);
        mImageView = (ImageView) v.findViewById(R.id.first_tablayout_fenlei);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChannelManagerActivity.class);
                startActivity(intent);
            }
        });
    }

}
