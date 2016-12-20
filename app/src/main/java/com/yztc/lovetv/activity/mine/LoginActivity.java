package com.yztc.lovetv.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.UserEntity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

	private TextView intentregister_tv;
	private Toolbar register_tb;
	private EditText name_edt;
	private EditText pwd_edt;
	private Button login_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		intentregister_tv = (TextView) findViewById(R.id.intentregister_tv);
		register_tb = (Toolbar) findViewById(R.id.register_tb);
		name_edt = (EditText) findViewById(R.id.name_edt);
		pwd_edt = (EditText) findViewById(R.id.pwd_edt);
		login_id = (Button) findViewById(R.id.login_id);
		login_id.setOnClickListener(this);
		intentregister_tv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.login_id:
				String edt = name_edt.getEditableText().toString();
				if (TextUtils.isEmpty(edt)) {
					Toast.makeText(this, "用户名/手机号", Toast.LENGTH_SHORT).show();
					return;
				}
				String edt1 = pwd_edt.getEditableText().toString();
				if (TextUtils.isEmpty(edt1)) {
					Toast.makeText(this, "输入密码", Toast.LENGTH_SHORT).show();
					return;
				}
			/*	BmobUser.loginByAccount(edt, edt1, new LogInListener<UserEntity>() {
					public void done(UserEntity user, BmobException e) {
						if(user!=null){
							Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
							name_edt.setText("");
							pwd_edt.setText("");
						}
						else
						{
							Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
						}
					}
				});*/
				break;
			case R.id.intentregister_tv:
				Intent in=new Intent(this,RegisterActivity.class);
				startActivity(in);
				break;
		}
	}

}
