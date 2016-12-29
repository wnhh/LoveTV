package com.yztc.lovetv.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
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
import com.yztc.lovetv.bean.TabItemBean;
import com.yztc.lovetv.bean.TuiJianItem;
import com.yztc.lovetv.bean.Tuijian;
import com.yztc.lovetv.contant.BaseUrl;
import com.yztc.lovetv.contant.TabhostContant;
import com.yztc.lovetv.db.TabItemBeanManager;
import com.yztc.lovetv.fragment.tabhost.TuijianFragment;
import com.yztc.lovetv.myutil.MyConstants;
import com.yztc.lovetv.myutil.OkHttpUtils;
import com.yztc.lovetv.myutil.PreferencesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	//拖动
	private int dragFlags,swipeFlags;
	//数据源
	List<String>strlist;
	List<String>strlisttwo;
	List<Itembean> itembeanList;
	List<String> mTabs;
	TabItemBeanManager mTabItemBeanManager;
	//适配器
	private ChannelManagerAdapter cma;
	private ChannelManagertwoAdapter cmatwo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_manager);
		mTabItemBeanManager = new TabItemBeanManager(this);
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
                        List<TabItemBean> all = mTabItemBeanManager.getAll();
                        for (TabItemBean tabItemBean : all) {
                            if (tabItemBean.getItemName().equals(tuijian.getRoom().get(i).getName())){
                                strlist.add(tabItemBean.getItemName());
								break;
                            }else {
                                strlisttwo.add(tuijian.getRoom().get(i).getName());
                            }
                        }
					}
					//recycler上设置adapter
					cma=new ChannelManagerAdapter(ChannelManagerActivity.this,strlist);
					channelone_tv.setAdapter(cma);
					//recycler下设置adapter
					cmatwo=new ChannelManagertwoAdapter(ChannelManagerActivity.this,strlisttwo);
					channeltwo_tv.setAdapter(cmatwo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

			}
		});
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
		//第一个recyclerview
		channelone_tv = (RecyclerView) findViewById(R.id.channelone_tv);
		channelone_tv.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
		//第二个recyclerview
		channeltwo_tv = (RecyclerView) findViewById(R.id.channeltwo_tv);
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
					itemmove();
				}
				else
				{
					tv.setText("管理");
					PreferencesUtils.clear(ChannelManagerActivity.this);
					for (int i = 0;i<strlist.size(); i++) {
						PreferencesUtils.putString(ChannelManagerActivity.this, TabhostContant.TUIJIAN_ITEM_NAME + i,strlist.get(i));
					}
					TuijianFragment.isUpdate = true;
					PreferencesUtils.putBoolean(ChannelManagerActivity.this,MyConstants.KEY_TEST,true);
					finish();
				}
			}
		});
	}
	//设置recyclerview的拖动
	public void itemmove()
	{
		ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
			@Override
			public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
				if(channelone_tv.getLayoutManager() instanceof GridLayoutManager)
				{
					dragFlags=ItemTouchHelper.UP |
							ItemTouchHelper.DOWN |
							ItemTouchHelper.LEFT |
							ItemTouchHelper.RIGHT;
					swipeFlags=0;

					return makeMovementFlags(dragFlags,swipeFlags);
				}else
				{
					dragFlags=ItemTouchHelper.UP |
							ItemTouchHelper.DOWN;
					swipeFlags=0;
					return makeMovementFlags(dragFlags,swipeFlags);
				}

			}
			@Override
			public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
				int fromPosition=viewHolder.getAdapterPosition();
				int toPosition=target.getAdapterPosition();
				if(fromPosition<toPosition)
				{
					for(int i=fromPosition;i<toPosition;i++)
					{
						Collections.swap(strlist,i,i+1);
					}
				}else
				{
					for(int i=fromPosition;i>toPosition;i--)
					{
						Collections.swap(strlist,i,i-1);
					}
				}
				cma.notifyItemMoved(fromPosition,toPosition);
				return true;
			}

			@Override
			public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

			}
			@Override
			public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
				if(actionState!=ItemTouchHelper.ACTION_STATE_IDLE)
				{
					//viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
				}
				super.onSelectedChanged(viewHolder, actionState);
			}
			@Override
			public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
				super.clearView(recyclerView, viewHolder);
				viewHolder.itemView.setBackgroundColor(0);
			}
			@Override
			public boolean isLongPressDragEnabled() {
				//return super.isLongPressDragEnabled();
				return true;
			}
		});
		itemTouchHelper.attachToRecyclerView(channelone_tv);
	}
}
