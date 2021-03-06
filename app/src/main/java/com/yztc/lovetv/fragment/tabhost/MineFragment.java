package com.yztc.lovetv.fragment.tabhost;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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
import com.yztc.lovetv.activity.mine.SySettingActivity;
import com.yztc.lovetv.activity.mine.TalkActivity;
import com.yztc.lovetv.db.BackDataOperateManager;
import com.yztc.lovetv.fragment.other.MyAttentionFragment;

import cn.bmob.v3.BmobUser;


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
	public static final int REQUEST_LOGINBACK_CODE = 201;
	public static final int REQUEST_ZHONGZIBACK_CODE = 202;
	private TextView myname_id;
	//存返回码
	private int backcode;
	private TextView picone;
	private ImageView huahua_id;
	private ImageView talkIv;
	//存用户名
	private String str;
	private boolean flag;
	private BackDataOperateManager bdp;
	private  BmobUser bmobUser;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mine, container, false);
		bdp=new BackDataOperateManager(getContext());
		initView(v);
		isLogin();
		return v;
	}
	public void isLogin()
	{
		bmobUser = BmobUser.getCurrentUser(getContext());
		if(bmobUser!=null) {
			String inId = null;
			try {
				inId = bdp.getAll().get(0).getInId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!TextUtils.isEmpty(inId)) {
				flag = true;
				myname_id.setText(inId);
			}
		}
	}
	private void initView(View v) {
		myname_id = (TextView) v.findViewById(R.id.myname_id);
		chongzhiBtn = (Button) v.findViewById(R.id.chongzhi_btn);
		chongzhiBtn.setOnClickListener(this);
		talkIv = (ImageView) v.findViewById(R.id.rebot_id);
		talkIv.setOnClickListener(this);
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
				if (backcode == 600 || backcode == 602||flag==true) {
					Intent loginintent = new Intent(getActivity(), PersonalInfoActivity.class);
					loginintent.putExtra("loginnamekey", myname_id.getText().toString());
					startActivityForResult(loginintent, REQUEST_LOGINBACK_CODE);
				} else {
					Intent loginintent = new Intent(getActivity(), LoginActivity.class);
					startActivityForResult(loginintent, REQUEST_LOGIN_CODE);
				}
			}
		});
		picone = (TextView) v.findViewById(R.id.picone);
		huahua_id = (ImageView) v.findViewById(R.id.huahua_id);
		huahua_id.setOnClickListener(this);

		ll_zhongzirenwu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent zz=new Intent(getContext(),ConvertVActivity.class);
				zz.putExtra("conkey", "种子任务");
				startActivityForResult(zz,REQUEST_ZHONGZIBACK_CODE);
			}
		});
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		backcode = resultCode;
		switch (requestCode) {
			case REQUEST_LOGIN_CODE:
				if (resultCode == 600) {
					str=data.getStringExtra("loginkey");
					myname_id.setText(str);
					flag=true;
				}
				break;
			case REQUEST_LOGINBACK_CODE:
				if (resultCode == 601) {
					touxiang_id.setImageResource(R.mipmap.img_profile_touxiang_default_unknow);
					myname_id.setText("点击登录");
					flag=false;
				}
				if (resultCode == 602) {
					Bitmap bp = data.getParcelableExtra("loginnamekey");
					if (bp == null) {
						touxiang_id.setImageResource(R.mipmap.ic_launcher);
					}
					touxiang_id.setImageBitmap(bp);
				}
				break;
			case REQUEST_ZHONGZIBACK_CODE:
				if(resultCode==800)
				{
					picone.setText(data.getStringExtra("haha"));
				}
				break;
		}
	}
	@Override
	public void onClick(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
			case R.id.huahua_id:
				intent.setClass(getActivity(), SySettingActivity.class);
				break;
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
			case R.id.ll_gamecenter:
				intent.setClass(getActivity(), ConvertVActivity.class);
				intent.putExtra("conkey", "游戏中心");
				break;
			case R.id.rebot_id:
				intent.setClass(getActivity(), TalkActivity.class);
				break;
		}
		startActivity(intent);
	}
}
