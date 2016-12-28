package com.yztc.lovetv.fragment.tabhost;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.SearchActivity;
import com.yztc.lovetv.adapter.LiveActivityAdapter;
import com.yztc.lovetv.bean.Totalshowbean;
import com.yztc.lovetv.bean.TotaoshowText;
import com.yztc.lovetv.myutil.MyTask;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiboFragment extends Fragment implements View.OnClickListener{

    private TextView toolbar_icon;
    private RecyclerView recyclerView;
    private List<TotaoshowText> totaoshowTexts;
    //点击跳转Url
    private List<String> intenturl;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GridLayoutManager gm;

    public ZhiboFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_zhibo, container, false);
        initRefresh(v);
        initView(v);
        initData();
        initlayout(v);
        return v;
    }

    private void initlayout(View v) {
        recyclerView= (RecyclerView)v.findViewById(R.id.recycleview);
        gm=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gm);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent=new Intent();
                Log.e("kim","有没有进去---------------------------------");
                int url=Integer.valueOf(totaoshowTexts.get(i).getRoomNumId());
                Bundle bundle=new Bundle();
                bundle.putInt("value",url);
                Log.e("url","url是"+url);
                intent.putExtras(bundle);
                intent.setClass(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        totaoshowTexts=new ArrayList<>();
        MyTask mytask=new MyTask();
        mytask.setOnGetValueListener(new MyTask.OnGetValueListener() {
            @Override
            public void finish(String result) {
                Gson gson=new Gson();
                Totalshowbean liveData=gson.fromJson(result,Totalshowbean.class);
                for (int i=0;i<liveData.getData().size();i++){
                    TotaoshowText item=new TotaoshowText();
                    if (liveData.getData().get(i).getIntro()==""){
                        item.setBrief(liveData.getData().get(i).getTitle());
                    }else {
                        item.setBrief(liveData.getData().get(i).getIntro());
                    }
                    item.setNameId(liveData.getData().get(i).getNick());
                    item.setHeadImageId(liveData.getData().get(i).getAvatar());
                    item.setLiveShotId(liveData.getData().get(i).getThumb());
                    item.setRoomNumId(liveData.getData().get(i).getUid());
                    item.setWatchNum(liveData.getData().get(i).getView());
                    totaoshowTexts.add(item);
                }
                LiveActivityAdapter la=new LiveActivityAdapter(getContext(),totaoshowTexts);
                recyclerView.setAdapter(la);
            }
        });
        mytask.execute("http://www.quanmin.tv/json/play/list.json?11212157&v=2.2.4&os=1&ver=4");
    }

    private void initView(View v) {
        toolbar_icon = (TextView) v.findViewById(R.id.toolbar_icon);
        toolbar_icon.setOnClickListener(this);
    }

    private void initRefresh(View v) {
        swipeRefreshLayout= (SwipeRefreshLayout)v.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        //设置刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new DownloadTask().execute();
                    }
                },2000);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.toolbar_icon:
                intent.setClass(getContext(),SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    class DownloadTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerView.scrollToPosition(0);
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
