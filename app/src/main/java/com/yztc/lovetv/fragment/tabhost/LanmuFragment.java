package com.yztc.lovetv.fragment.tabhost;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.DetailActivity;
import com.yztc.lovetv.activity.SearchActivity;
import com.yztc.lovetv.adapter.LanmuAdapter;
import com.yztc.lovetv.bean.Liveshow;
import com.yztc.lovetv.bean.ShowBean;
import com.yztc.lovetv.contant.PicUrl;
import com.yztc.lovetv.myutil.MyTask;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LanmuFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<Liveshow> liveshows;
    private GridLayoutManager layoutManager;
    private TextView search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lanmu, container, false);
        initView(v);
        initData();
        initJump(v);
        return v;
    }

    private void initJump(View v) {
        search= (TextView)v.findViewById(R.id.toolbar_icon);
        search.setOnClickListener(this);
    }

    private void initData() {
        liveshows=new ArrayList<>();
        MyTask mytask=new MyTask();
        mytask.setOnGetValueListener(new MyTask.OnGetValueListener() {
            @Override
            public void finish(String result) {
                Gson gson=new Gson();
                TypeToken<List<ShowBean>> typeToken=new TypeToken<List<ShowBean>>(){};
                List<ShowBean> beans=gson.fromJson(result,typeToken.getType());
                Log.e("kim","result------------------"+result);
                for (int i=0;i<beans.size();i++){
                    Liveshow ls=new Liveshow();
                    ls.setName(beans.get(i).getName());
                    ls.setPicId(beans.get(i).getThumb());
                    ls.setSlug(beans.get(i).getSlug());
                    liveshows.add(ls);
                }
                LanmuAdapter la=new LanmuAdapter(getContext(),liveshows);
                recyclerView.setAdapter(la);
            }
        });
        mytask.execute("http://www.quanmin.tv/json/categories/list.json?11212119&v=2.2.4&os=1&ver=4");
    }

    private void initView(View v) {
        recyclerView= (RecyclerView) v.findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //进行网址传递
                Intent intent=new Intent();
                String url=(liveshows.get(i).getSlug());
                intent.putExtra("value",i);
                Log.e("这个值","--------"+i);
                intent.setClass(getContext(), DetailActivity.class);//keyi
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.toolbar_icon:
                intent.setClass(getContext(),SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
