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
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.ChannelManagerAdapter;
import com.yztc.lovetv.adapter.ChannelManagertwoAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChannelManagerActivity extends AppCompatActivity {

	private TextView channelmanager_tv;
	private TextView manager_tv;
	private Toolbar manager_tb;
	private RecyclerView channelone_tv;
	private RecyclerView channeltwo_tv;
	private ImageView iv;
	private TextView tv;
	private int count;
	//数据源
	List<String>strlist;
	List<String>strlisttwo;
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
		strlist.add("颜值控");
		strlist.add("英雄联盟");
		strlist.add("全民星宿");
		strlist.add("守望先锋");
		strlist.add("全民户外");
		strlist.add("炉石传说");
		strlist.add("手游专区");
		strlist.add("网友竞技");
		strlist.add("单机主机");
		strlist.add("球球大作战");
		strlisttwo.add("二次元区");
		strlisttwo.add("暴雪经典");
		strlisttwo.add("NBA2K");
		strlisttwo.add("王者荣耀");
		strlisttwo.add("QQ飞车");
		strlisttwo.add("FIFA");
		strlisttwo.add("穿越火线");
		strlisttwo.add("DNF");
		strlisttwo.add("DOTA2");
		strlisttwo.add("魔兽争霸3");
	}

	private void initView() {
		channelmanager_tv = (TextView) findViewById(R.id.channelmanager_tv);
		manager_tv = (TextView) findViewById(R.id.manager_tv);
		manager_tb = (Toolbar) findViewById(R.id.manager_tb);
		manager_tb.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent in=new Intent(ChannelManagerActivity.this,MainActivity.class);
				startActivity(in);
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
				}
			}
		});
	}
}
