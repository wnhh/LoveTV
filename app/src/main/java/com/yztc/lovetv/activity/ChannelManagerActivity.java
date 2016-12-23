package com.yztc.lovetv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.ChannelManagerAdapter;
import com.yztc.lovetv.adapter.ChannelManagertwoAdapter;
import com.yztc.lovetv.apiservice.LitchiapiService;
import com.yztc.lovetv.apiservice.LoginApiService;
import com.yztc.lovetv.bean.FirstPagerBean;
import com.yztc.lovetv.bean.Itembean;
import com.yztc.lovetv.bean.TuiJianItem;
import com.yztc.lovetv.bean.Tuijian;
import com.yztc.lovetv.contant.BaseUrl;
import com.yztc.lovetv.contant.TabhostContant;
import com.yztc.lovetv.myutil.MyConstants;
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

import static cn.bmob.newim.core.BmobIMClient.getContext;

public class ChannelManagerActivity extends AppCompatActivity {

	private TextView channelmanager_tv,manager_tv,tv;
	private Toolbar manager_tb;
	private RecyclerView channelone_tv,channeltwo_tv;
	private int count;
	//数据源
	List<String>strlist;
	List<String>strlisttwo;
	List<Itembean> itembeanList;
	List<String> mTabs;

	//适配器
	private ChannelManagerAdapter cma;
	private ChannelManagertwoAdapter cmatwo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_manager);
		initData();
		initView();
	}


	private void initData() {
		strlist=new ArrayList<>();
		strlisttwo=new ArrayList<>();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BaseUrl.TUIJIANITEM)
				.client(OkHttpUtils.newOkHttpClient(this))
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
						String string = PreferencesUtils.getString(ChannelManagerActivity.this,TabhostContant.TUIJIAN_ITEM_NAME+"i");

						if (string != null){
							strlist.add(string);
						}else{
							strlisttwo.add(tuijian.getRoom().get(i).getName());
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

			}
		});

//
//	strlist.add("颜值控");
//		strlist.add("英雄联盟");
//		strlist.add("全民星宿");
//		strlist.add("守望先锋");
//		strlist.add("全民户外");
//		strlist.add("炉石传说");
//		strlist.add("手游专区");
//		strlist.add("网友竞技");
//		strlist.add("单机主机");
//		strlist.add("球球大作战");
//		strlisttwo.add("二次元区");
//		strlisttwo.add("暴雪经典");
//		strlisttwo.add("NBA2K");
//		strlisttwo.add("王者荣耀");
//		strlisttwo.add("QQ飞车");
//		strlisttwo.add("FIFA");
//		strlisttwo.add("穿越火线");
//		strlisttwo.add("DNF");
//		strlisttwo.add("DOTA2");
//		strlisttwo.add("魔兽争霸3");
	}

	private void initView() {
		channelmanager_tv = (TextView) findViewById(R.id.channelmanager_tv);
		manager_tv = (TextView) findViewById(R.id.manager_tv);
		manager_tb = (Toolbar) findViewById(R.id.manager_tb);
		manager_tb.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		tv= (TextView) findViewById(R.id.manager_tv);
		channelone_tv = (RecyclerView) findViewById(R.id.channelone_tv);
		channeltwo_tv = (RecyclerView) findViewById(R.id.channeltwo_tv);
		cma=new ChannelManagerAdapter(this,strlist);
		channelone_tv.setAdapter(cma);
		channelone_tv.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
		//第二个recyclerview
		cmatwo=new ChannelManagertwoAdapter(this,strlisttwo);
		channeltwo_tv.setAdapter(cmatwo);
		channeltwo_tv.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));

		//监听
		channelone_tv.addOnItemTouchListener(new OnItemClickListener() {
			@Override
			public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
				if(count%2==1) {
					strlisttwo.add(strlist.get(i));
					strlist.remove(i);
					//TextView tv = (TextView) view.findViewById(R.id.channelname_tv);
					cma.notifyDataSetChanged();
					cmatwo.notifyDataSetChanged();
				}
			}
		});
		channeltwo_tv.addOnItemTouchListener(new OnItemClickListener() {
			@Override

			public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
				if(count%2==1)
				{
					strlist.add(strlisttwo.get(i));
					strlisttwo.remove(i);
					cma.notifyDataSetChanged();
					cmatwo.notifyDataSetChanged();
				}
			}
		});
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				count++;
				if(count%2==1) {
					tv.setText("完成");
				}
				else
				{
					tv.setText("管理");
					PreferencesUtils.clear(ChannelManagerActivity.this);
					for (int i = 0;i<strlist.size(); i++) {
						PreferencesUtils.putString(ChannelManagerActivity.this, TabhostContant.TUIJIAN_ITEM_NAME+i,strlist.get(i));
					}
					PreferencesUtils.putBoolean(getContext(),MyConstants.KEY_TEST,true);
				}
			}
		});
	}
}
