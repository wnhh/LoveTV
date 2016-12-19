package com.yztc.lovetv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yztc.lovetv.R;

public class ConvertVActivity extends AppCompatActivity {

	private TextView convert_id;
	private Toolbar tb_convert;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_convert_v);
		Log.e("tag","------------------------------");
		intent = getIntent();
		initToolBar();
		initView();
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
		convert_id = (TextView) findViewById(R.id.convert_id);
		convert_id.setText(intent.getStringExtra("conkey"));
	}
}
