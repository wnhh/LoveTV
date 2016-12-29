package com.yztc.lovetv.fragment.tabhost;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.ChannelManagerActivity;
import com.yztc.lovetv.adapter.ViewPagerAdapter;
import com.yztc.lovetv.bean.TabItemBean;
import com.yztc.lovetv.bean.TuijianFragmentUrlListBean;
import com.yztc.lovetv.contant.TabhostContant;
import com.yztc.lovetv.contant.TvUrl;
import com.yztc.lovetv.db.TabItemBeanManager;
import com.yztc.lovetv.fragment.tuijianfragment.AllFragment;
import com.yztc.lovetv.fragment.tuijianfragment.ItemFragment;
import com.yztc.lovetv.fragment.tuijianfragment.Tuijian_Fragment_Vp;

import java.util.ArrayList;
import java.util.List;


public class TuijianFragment extends Fragment {
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private ImageView mImageView;

    //数据源
    List<Fragment> mFragments;
    List<String> mTabs;
    List<String> mAllTabs;

    TabItemBeanManager mTabItemBeanManager;

    public static boolean isUpdate;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_tuijian, container, false);
        mTabItemBeanManager = new TabItemBeanManager(getActivity());
        initView(v);
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onResume() {
        if (isUpdate) {
            try {
                isUpdate = false;
                initData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onResume();

    }

    private void initData() throws Exception {
        mTabs = new ArrayList<>();
        mFragments = new ArrayList<>();

        //首页推荐和Fragment是固定的
        mTabs.add("推荐");
        Tuijian_Fragment_Vp fragmentVp = new Tuijian_Fragment_Vp();
        mFragments.add(fragmentVp);

        //得到数据库存储tab的集合
        List<TabItemBean> all = mTabItemBeanManager.getAll();
        Log.e("TAG","all"+all.size());

        //遍历tab集合添加tab的数据   根据数量添加fragment
        for (TabItemBean tabItemBean : all) {
            mTabs.add(tabItemBean.getItemName());
            ItemFragment itemFragment = new ItemFragment();
            Bundle bundle = new Bundle();
            String url = null;
            //根据数据库跟tab数据相同的接口名称  通过判断得到接口网址
            List<TuijianFragmentUrlListBean> lists = TuijianFragmentUrlListBean.getLists();
            for (TuijianFragmentUrlListBean list : lists) {
               if( list.getName().equals(tabItemBean.getName())){
                   url = list.getName();
                   break;
               }
            }
            //通过bundle传值将接口传过去
            bundle.putString(TabhostContant.URL_KEY,url );
            itemFragment.setArguments(bundle);
            mFragments.add(itemFragment);
        }

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




                FragmentManager manager = getChildFragmentManager();
                ViewPagerAdapter fragmentAdapter = new ViewPagerAdapter(manager,mFragments,mTabs);
                mViewPager.setOffscreenPageLimit(mTabs.size()-1);
                mViewPager.setAdapter(fragmentAdapter);
                mTabLayout.setupWithViewPager(mViewPager);








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
