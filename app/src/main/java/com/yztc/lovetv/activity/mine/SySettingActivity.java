package com.yztc.lovetv.activity.mine;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.ConvertVActivity;
import com.yztc.lovetv.myutil.DataCleanManager;

public class SySettingActivity extends AppCompatActivity implements View.OnClickListener {

	private ImageView setback_iv;
	private ImageView setswitch_iv;
	private RelativeLayout cash_rl;
	private RelativeLayout aboutus_rl;
	private RelativeLayout pingfen_rl;
	private int count;
	private TextView cash_tv;
	private String cacheSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sy_setting);
		initView();
		getFileSize();
	}

	private void getFileSize() {
		try {
			cacheSize = DataCleanManager.getCacheSize(this.getCacheDir());
		} catch (Exception e) {
			e.printStackTrace();
		}
		cash_tv.setText(cacheSize);
	}

	private void initView() {
		setback_iv = (ImageView) findViewById(R.id.setback_iv);
		setswitch_iv = (ImageView) findViewById(R.id.setswitch_iv);
		cash_rl = (RelativeLayout) findViewById(R.id.cash_rl);
		aboutus_rl = (RelativeLayout) findViewById(R.id.aboutus_rl);
		pingfen_rl = (RelativeLayout) findViewById(R.id.pingfen_rl);
		setback_iv.setOnClickListener(this);
		setswitch_iv.setOnClickListener(this);
		cash_rl.setOnClickListener(this);
		aboutus_rl.setOnClickListener(this);
		pingfen_rl.setOnClickListener(this);
		cash_tv = (TextView) findViewById(R.id.cash_tv);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.setback_iv:
				finish();
				break;
			case R.id.setswitch_iv:
				count++;
				if (count % 2 == 1) {
					setswitch_iv.setImageResource(R.mipmap.open);
				} else {

					setswitch_iv.setImageResource(R.mipmap.close);
				}
				break;

			case R.id.cash_rl:
				DataCleanManager.cleanInternalCache(this);
				getFileSize();
				Toast.makeText(this, "清理完成", Toast.LENGTH_SHORT).show();
				break;
			case R.id.aboutus_rl:
				Intent intent = new Intent(this, ConvertVActivity.class);
				intent.putExtra("conkey", "关于我们");
				startActivity(intent);
				break;
			case R.id.pingfen_rl:
				try {
					Uri uri = Uri.parse("market://details?id=" + getPackageName());
					Intent in = new Intent(Intent.ACTION_VIEW, uri);
					in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(in);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(this, "Couldn't launch the market !", Toast.LENGTH_SHORT).show();
				}
				break;
		}
	}
}
