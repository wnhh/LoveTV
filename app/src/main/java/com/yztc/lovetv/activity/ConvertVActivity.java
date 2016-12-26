package com.yztc.lovetv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yztc.lovetv.R;
import com.yztc.lovetv.fragment.other.EmailFragment;
import com.yztc.lovetv.fragment.other.GuanZhuFragment;
import com.yztc.lovetv.fragment.other.MyAttentionFragment;
import com.yztc.lovetv.fragment.other.PhotoFragment;
import com.yztc.lovetv.fragment.other.TVInfoFragment;

public class ConvertVActivity extends AppCompatActivity {

	private TextView convert_id;
	private Toolbar tb_convert;
	private Intent intent;
	private FragmentManager frm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_convert_v);
		frm=getSupportFragmentManager();
		intent = getIntent();
		initToolBar();
		initView();
		/*getSupportFragmentManager().beginTransaction()
				.add(R.id.convert_fl,new EmailFragment(),"EmailFragment")
				.commit();*/
	}
	private void initToolBar() {
		tb_convert = (Toolbar) findViewById(R.id.tb_convert);
		tb_convert.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
	private void initView() {
		FrameLayout fment = (FrameLayout) findViewById(R.id.convert_fl);
		convert_id = (TextView) findViewById(R.id.convert_id);
		convert_id.setText(intent.getStringExtra("conkey"));
		if(intent.getStringExtra("conkey").equals("绑定邮箱"))
		{
			addFragmentMethod(new EmailFragment(),"EmailFragment");
		}
		if(intent.getStringExtra("conkey").equals("绑定手机"))
		{
			addFragmentMethod(new PhotoFragment(),"PhotoFragment");
		}
		if(intent.getStringExtra("conkey").equals("我的关注"))
		{
			addFragmentMethod(new GuanZhuFragment(),"GuanZhuFragment");
		}
		if(intent.getStringExtra("conkey").equals("观看历史"))
		{
			addFragmentMethod(new GuanZhuFragment(),"GuanZhuFragment");
		}
		if(intent.getStringExtra("conkey").equals("观看历史"))
		{
			addFragmentMethod(new GuanZhuFragment(),"GuanZhuFragment");
		}
		if(intent.getStringExtra("conkey").equals("种子任务"))
		{
			addFragmentMethod(new MyAttentionFragment(),"MyAttentionFragment");
		}
		if(intent.getStringExtra("conkey").equals("游戏中心"))
		{
			addFragmentMethod(new GuanZhuFragment(),"GuanZhuFragment");
		}
		if(intent.getStringExtra("conkey").equals("关于我们"))
		{
			addFragmentMethod(new TVInfoFragment(),"TVInfoFragment");
		}
	}
	//添加的fragment
	public void addFragmentMethod(Fragment fr,String fm)
	{
				frm.beginTransaction()
				.add(R.id.convert_fl,fr,fm)
				.commit();
	}
}
