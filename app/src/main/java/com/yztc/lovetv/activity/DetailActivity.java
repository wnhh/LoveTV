package com.yztc.lovetv.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.LiveActivityAdapter;
import com.yztc.lovetv.bean.Totalshowbean;
import com.yztc.lovetv.bean.TotaoshowText;
import com.yztc.lovetv.myutil.MyTask;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TotaoshowText> tobao;
    private GridLayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initData();
        initRefresh();
        initLayout();
    }

    private void initLayout() {
        recyclerView= (RecyclerView)findViewById(R.id.recycleview);
        layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent=new Intent();
                Log.e("kim","有没有进去---------------------------------");
                int a=Integer.valueOf(tobao.get(i).getRoomNumId());

            }
        });
    }

    private void initRefresh() {
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
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

    private void initData() {
        tobao=new ArrayList<>();
        MyTask mytask=new MyTask();
        mytask.setOnGetValueListener(new MyTask.OnGetValueListener() {
            @Override
            public void finish(String result) {
                Gson gson=new Gson();
                Totalshowbean showbean=gson.fromJson(result,Totalshowbean.class);
                Log.e("kim","result------------------"+result);
                for (int i=0;i<showbean.getData().size();i++){
                    TotaoshowText show=new TotaoshowText();
                    if (showbean.getData().get(i).getIntro()==""){
                        show.setBrief(showbean.getData().get(i).getTitle());
                    }else {
                        show.setBrief(showbean.getData().get(i).getIntro());
                    }
                    show.setNameId(showbean.getData().get(i).getNick());
                    show.setHeadImageId(showbean.getData().get(i).getAvatar());
                    show.setLiveShotId(showbean.getData().get(i).getThumb());
                    show.setRoomNumId(showbean.getData().get(i).getUid());
                    show.setWatchNum(showbean.getData().get(i).getView());
                    tobao.add(show);
                }
                LiveActivityAdapter live=new LiveActivityAdapter(getApplicationContext(),tobao);
                recyclerView.setAdapter(live);
            }
        });
        list.add("/lol/list.json?11212123&v=2.2.4&os=1&ver=4");
        list.add("/beauty/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/overwatch/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/huwai/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/heartstone/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/love/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/qiuqiu/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/qqfeiche/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/wangzhe/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/yys/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/war3/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/webgame/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/tvgame/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/street/list.json?12201613&v=2.2.4&os=1&ver=4");
        list.add("");//怀旧经典
        list.add("/kadingche/list.json?12201613&v=2.2.4&os=1&ver=4");
        list.add("/limingshaji/list.json?12201547&v=2.2.4&os=1&ver=4");
        list.add("/mobilegame/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/csgo/list.json?12201614&v=2.2.4&os=1&ver=4");
        list.add("/cfpc/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/fs/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/dnf/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/tank/list.json?12201615&v=2.2.4&os=1&ver=4");
        list.add("/erciyuan/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/msg/list.json?12201615&v=2.2.4&os=1&ver=4");
        list.add("/qipai/list.json?12201616&v=2.2.4&os=1&ver=4");
        list.add("/au/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/blizzard/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/snake/list.json?12201616&v=2.2.4&os=1&ver=4");
        list.add("/dota2/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/zhuangjiafengbao/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/qiuqiu/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/minecraft/list.json?12201617&v=2.2.4&os=1&ver=4");
        list.add("/chuanqi/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/fifa/list.json?11212128&v=2.2.4&os=1&ver=4");
        list.add("/nba2k/list.json?11212128&v=2.2.4&os=1&ver=4");
        Intent intent=this.getIntent();
        int aaa= intent.getIntExtra("value",1);
        Log.e("kim","+++++++++++++"+aaa);
        String s=list.get(aaa);
        Log.e("kkkkk","传递的网址"+s);

        mytask.execute("http://www.quanmin.tv/json/categories"+s);
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
