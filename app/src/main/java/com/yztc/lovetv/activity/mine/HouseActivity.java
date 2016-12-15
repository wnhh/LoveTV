package com.yztc.lovetv.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yztc.lovetv.R;

public class HouseActivity extends AppCompatActivity implements View.OnClickListener {
	private ImageView ivBack;
	private TextView tvLive;
	private TextView tv_busee;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house);
		initView();
		ivBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	private void initView() {
		ivBack = (ImageView) findViewById(R.id.iv_house_back);
		tvLive = (TextView) findViewById(R.id.tv_see);
		tv_busee = (TextView) findViewById(R.id.tv_busee);
		tvLive.setOnClickListener(this);
		tv_busee.setOnClickListener(this);
		tvLive.setSelected(true);
		tv_busee.setSelected(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_busee:
				tv_busee.setSelected(true);
				tvLive.setSelected(false);
				break;
			case R.id.tv_see:
				tvLive.setSelected(true);
				tv_busee.setSelected(false);
				break;
		}
	}
}

