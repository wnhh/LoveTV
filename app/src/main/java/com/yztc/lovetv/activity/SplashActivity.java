package com.yztc.lovetv.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yztc.lovetv.R;
import com.yztc.lovetv.myutil.MyConstants;
import com.yztc.lovetv.myutil.PreferencesUtils;

public class SplashActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//调用工具类get方法从sharedprefrerance存数据的xml的读数据
		boolean aBoolean = PreferencesUtils.getBoolean(this, MyConstants.KEY_TEST);
		if(aBoolean)
		{
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent=new Intent(SplashActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}
			},500);
		}
		else
		{
			aBoolean=true;
			//调用工具类get方法从sharedprefrerance存数据的xml的存数据
			PreferencesUtils.putBoolean(this,MyConstants.KEY_TEST,aBoolean);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent=new Intent(SplashActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}
			},500);
		}
	}
}
