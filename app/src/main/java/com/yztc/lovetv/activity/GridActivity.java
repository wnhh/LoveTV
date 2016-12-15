package com.yztc.lovetv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.GridPageAdapter;
import com.yztc.lovetv.bean.GridText;
import com.yztc.lovetv.viewall.FlowIndicator;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {
	//数据源
	private List<GridText> lg;
	//适配器
	private GridPageAdapter gadapter;
	//viewpager
	private ViewPager vp;
	private ViewPager main_vp;
	private TextView mian_tiyan;
	private FlowIndicator fl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		initData();
		initView();
	}
	private void initData() {
		lg=new ArrayList<>();
		GridText gt=new GridText();
		gt.setPicId(R.mipmap.welcome_01);
		gt.setText("鲜肉频道酷炫来袭");
		lg.add(gt);
		GridText gt2=new GridText();
		gt2.setPicId(R.mipmap.welcome_02);
		gt2.setText("定制你的专属首页");
		lg.add(gt2);
		GridText gt3=new GridText();
		gt3.setPicId(R.mipmap.welcome_03);
		gt3.setText("一键开播等你来玩");
		lg.add(gt3);
		GridText gt4=new GridText();
		gt4.setPicId(R.mipmap.welcome_04);
		gt4.setText("撩妹大法出新招");
		lg.add(gt4);

	}
	private void initView() {
		fl= (FlowIndicator) findViewById(R.id.fl_indicator);
		fl.setCount(4);
		main_vp = (ViewPager) findViewById(R.id.main_vp);
		main_vp.setOffscreenPageLimit(lg.size()-1);
		mian_tiyan = (TextView) findViewById(R.id.mian_tiyan);
		mian_tiyan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(GridActivity.this,MainActivity.class);
				//Intent intent=new Intent(GridActivity.this,ChannelManagerActivity.class);
				startActivity(intent);
				finish();
			}
		});
		main_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				fl.setSeletion(position);
				if(position==lg.size()-1)
				{
					mian_tiyan.setVisibility(View.VISIBLE);
					fl.setVisibility(View.GONE);
				}
				else
				{
					mian_tiyan.setVisibility(View.GONE);
					fl.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		gadapter=new GridPageAdapter(lg,GridActivity.this);
		main_vp.setAdapter(gadapter);
	}
}
