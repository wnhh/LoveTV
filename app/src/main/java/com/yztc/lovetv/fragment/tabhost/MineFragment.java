package com.yztc.lovetv.fragment.tabhost;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.ConvertVActivity;
import com.yztc.lovetv.activity.mine.ChongzhiActivity;
import com.yztc.lovetv.activity.mine.HouseActivity;
import com.yztc.lovetv.activity.mine.LoginActivity;
import com.yztc.lovetv.activity.mine.PersonalInfoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

	Button chongzhiBtn;
	LinearLayout houseguanlill;
	private LinearLayout ll_myguanzhu;
	private LinearLayout ll_guankanlishi;
	private LinearLayout ll_kaibotixing;
	private LinearLayout ll_zhongzirenwu;
	private LinearLayout ll_gamecenter;
	private ImageView touxiang_id;
	public static final int REQUEST_LOGIN_CODE = 200;
	public static final int REQUEST_BACK_CODE = 201;
	private TextView myname_id;
	//存返回码
	private int backcode;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mine, container, false);
		initView(v);
		return v;
	}

	private void initView(View v) {
		chongzhiBtn = (Button) v.findViewById(R.id.chongzhi_btn);
		chongzhiBtn.setOnClickListener(this);
		houseguanlill = (LinearLayout) v.findViewById(R.id.ll_houseguanli);
		houseguanlill.setOnClickListener(this);
		ll_myguanzhu = (LinearLayout) v.findViewById(R.id.ll_myguanzhu);
		ll_myguanzhu.setOnClickListener(this);
		ll_guankanlishi = (LinearLayout) v.findViewById(R.id.ll_guankanlishi);
		ll_guankanlishi.setOnClickListener(this);
		ll_kaibotixing = (LinearLayout) v.findViewById(R.id.ll_kaibotixing);
		ll_kaibotixing.setOnClickListener(this);
		ll_zhongzirenwu = (LinearLayout) v.findViewById(R.id.ll_zhongzirenwu);
		ll_zhongzirenwu.setOnClickListener(this);
		ll_gamecenter = (LinearLayout) v.findViewById(R.id.ll_gamecenter);
		ll_gamecenter.setOnClickListener(this);
		touxiang_id = (ImageView) v.findViewById(R.id.touxiang_id);
		touxiang_id.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(backcode==600)
				{
					Intent loginintent = new Intent(getActivity(), PersonalInfoActivity.class);
					startActivity(loginintent);
				}
				else
				{
					Intent loginintent = new Intent(getActivity(), LoginActivity.class);
					startActivityForResult(loginintent, REQUEST_LOGIN_CODE);
				}

			}
		});
		myname_id = (TextView) v.findViewById(R.id.myname_id);
		myname_id.setOnClickListener(this);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		backcode=resultCode;
		switch (requestCode) {
			case REQUEST_LOGIN_CODE:
				if(resultCode==600) {
					myname_id.setText(data.getStringExtra("loginkey"));
				}
				break;
		}
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
			case R.id.chongzhi_btn:
				intent.setClass(getActivity(), ChongzhiActivity.class);
				break;
			case R.id.ll_houseguanli:
				intent.setClass(getActivity(), HouseActivity.class);
				break;
			case R.id.ll_myguanzhu:
				intent.setClass(getActivity(), ConvertVActivity.class);
				intent.putExtra("conkey", "我的关注");
				break;
			case R.id.ll_guankanlishi:
				intent.setClass(getActivity(), ConvertVActivity.class);
				intent.putExtra("conkey", "观看历史");
				break;
			case R.id.ll_kaibotixing:
				intent.setClass(getActivity(), ConvertVActivity.class);
				intent.putExtra("conkey", "开播提醒");
				break;
			case R.id.ll_zhongzirenwu:
				intent.setClass(getActivity(), ConvertVActivity.class);
				intent.putExtra("conkey", "种子任务");
				break;
			case R.id.ll_gamecenter:
				intent.setClass(getActivity(), ConvertVActivity.class);
				intent.putExtra("conkey", "游戏中心");
				break;
		}
		startActivity(intent);
	}
}
