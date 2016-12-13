package com.yztc.lovetv.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.yztc.lovetv.R;
import com.yztc.lovetv.contant.TabhostContant;
import com.yztc.lovetv.fragment.tabhost.LanmuFragment;
import com.yztc.lovetv.fragment.tabhost.MineFragment;
import com.yztc.lovetv.fragment.tabhost.TuijianFragment;
import com.yztc.lovetv.fragment.tabhost.ZhiboFragment;

public class MainActivity extends AppCompatActivity {

	private FragmentTabHost mFragmentTabHost;
	private FragmentManager mFragmentManager;
	private TabWidget mTabWidget;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initeView();
	}

	private void initeView() {
		mFragmentTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		mFragmentManager = getSupportFragmentManager();
		mTabWidget = (TabWidget)findViewById(android.R.id.tabs);
		// 绑定TabHost和tabContent，建立关联
		mFragmentTabHost.setup(this, mFragmentManager, android.R.id.tabcontent);

		Bundle bundle = new Bundle();
		mFragmentTabHost.addTab(buildTabSpec(R.drawable.state_tabhost_tuijian, R.string.firstTabhost, TabhostContant.TAB_TUIJIAN),
				TuijianFragment.class, bundle);
		mFragmentTabHost.addTab(buildTabSpec(R.drawable.state_tabhost_lanmu, R.string.secondTabhost, TabhostContant.TAB_LANMU),
				LanmuFragment.class, bundle);
		mFragmentTabHost.addTab(buildTabSpec(R.drawable.state_tabhost_zhibo, R.string.thirdTabhost, TabhostContant.TAB_ZHIBO),
				ZhiboFragment.class, bundle);
		mFragmentTabHost.addTab(buildTabSpec(R.drawable.state_tabhost_mine, R.string.fourthTabhost, TabhostContant.TAB_MINE),
				MineFragment.class, bundle);
		//清除分割线
		mFragmentTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);

	}

	public TabHost.TabSpec buildTabSpec(int imageId, int textId, String tag) {
		//把布局解析成View
		View view = LayoutInflater.from(this).inflate(R.layout.item_tabhost, null);
		ImageView ivTabIcon = (ImageView) view.findViewById(R.id.iv_tabhost);
		TextView tvTabText = (TextView)view.findViewById(R.id.tv_tabhost);
		// 从外部传递进来的图片Id和文字id设置上
		ivTabIcon.setImageResource(imageId);
		tvTabText.setText(textId);
		// 生成TabSpec(需要为当前Fragment绑定Tag标签，另外需要添加一个View)
		return mFragmentTabHost.newTabSpec(tag).setIndicator(view);
	}
}
