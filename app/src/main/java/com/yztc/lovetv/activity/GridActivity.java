package com.yztc.lovetv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.GridPageAdapter;
import com.yztc.lovetv.apiservice.LitchiapiService;
import com.yztc.lovetv.bean.BackNeedData;
import com.yztc.lovetv.bean.GridText;
import com.yztc.lovetv.bean.TabItemBean;
import com.yztc.lovetv.bean.Tuijian;
import com.yztc.lovetv.contant.BaseUrl;
import com.yztc.lovetv.contant.TabhostContant;
import com.yztc.lovetv.db.BackDataOperateManager;
import com.yztc.lovetv.db.TabItemBeanManager;
import com.yztc.lovetv.myutil.OkHttpUtils;
import com.yztc.lovetv.myutil.PreferencesUtils;
import com.yztc.lovetv.viewall.FlowIndicator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static cn.bmob.newim.core.BmobIMClient.getContext;

public class GridActivity extends AppCompatActivity {
	//数据源
	private List<GridText> lg;
	private List<TabItemBean> mTabItemBeanList;
	//适配器
	private GridPageAdapter gadapter;
	//viewpager
	private ViewPager vp;
	private ViewPager main_vp;
	private TextView mian_tiyan;
	private FlowIndicator fl;

	//创建数据库
	TabItemBeanManager itemBeanManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		itemBeanManager =  new TabItemBeanManager(this);
		initData();
		initView();
		initRetrofit();
	}

	private void initRetrofit() {
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
					mTabItemBeanList = new ArrayList<TabItemBean>();
					for (int i = 1; i < 10; i++) {//第一次进来默认添加9个数据(在添加顶部导航是 推荐写死了)
						TabItemBean tabItemBean = new TabItemBean();
						tabItemBean.setItemId(tuijian.getRoom().get(i).getId());
						tabItemBean.setItemName(tuijian.getRoom().get(i).getName());
						Log.e("TAG", "name "+ tuijian.getRoom().get(i).getName());
						mTabItemBeanList.add(tabItemBean);
						itemBeanManager.insert(tabItemBean);
					}
					Log.e("TAG", "size "+ mTabItemBeanList.size());
//					itemBeanManager.insertAll(mTabItemBeanList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

			}
		});
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
