package com.yztc.lovetv.fragment.tuijianfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.TuijianAsyn;
import com.yztc.lovetv.adapter.AllFragmentAdapter;
import com.yztc.lovetv.bean.AllFragmentBean;
import com.yztc.lovetv.bean.Itembean;
import com.yztc.lovetv.bean.TuijianAllBean;
import com.yztc.lovetv.contant.TabhostContant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {

    RecyclerView itemFragmentRv ;
    List<AllFragmentBean> itemFragmentBeans;
    private String url;
    public  void seturl(String url)
    {
        this.url=url;
        //String url = savedInstanceState.getString(TabhostContant.URL_KEY,null);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item, container, false);
        initRecyclerView(v,url);
        return v;
    }
    private void initRecyclerView(View v,String url) {
        itemFragmentBeans = new ArrayList<>();
        itemFragmentRv = (RecyclerView) v.findViewById(R.id.tuijian_itemfragment);
        TuijianAsyn tuijianAsyn = new TuijianAsyn(url);
        tuijianAsyn.setGetJson(new TuijianAsyn.GetJson() {
            @Override
            public void finish(String s) {
                Gson gson = new Gson();
                TuijianAllBean tuijianAllBean = gson.fromJson(s, TuijianAllBean.class);
                for (int i = 0;i<tuijianAllBean.getData().size();i++) {
                    AllFragmentBean allFragmentBean = new AllFragmentBean();
                    allFragmentBean.setImgUrl(tuijianAllBean.getData().get(i).getThumb());//视频截图
                    allFragmentBean.setIconUrl(tuijianAllBean.getData().get(i).getAvatar());//头像
                    allFragmentBean.setCount(tuijianAllBean.getData().get(i).getView());//观看直播人的数量
                    allFragmentBean.setTitle(tuijianAllBean.getData().get(i).getTitle());//Title
                    allFragmentBean.setName(tuijianAllBean.getData().get(i).getNick());
                    itemFragmentBeans.add(allFragmentBean);
                }
                AllFragmentAdapter  allFragmentAdapter = new AllFragmentAdapter(getContext(),itemFragmentBeans);
                itemFragmentRv.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
                itemFragmentRv.setAdapter(allFragmentAdapter);
            }
        });

//        AllFragmentAdapter AllFragmentAdaptermentAdapter = new AllFragmentAdapter(getContext(),allFragmentBeans);
//        allItem.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
//        allItem.setAdapter(allFragmentAdapter);
    }

}
