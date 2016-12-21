package com.yztc.lovetv.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.UserEntity;

import org.w3c.dom.Text;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

	private TextView intentregister_tv;
	private Toolbar register_tb;
	private EditText name_edt;
	private EditText pwd_edt;
	private Button login_id;
	private ImageView loginclose_btn;
	private ImageView hideshow_btn;
	private int count=1;

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
		loginclose_btn = (ImageView) findViewById(R.id.loginclose_btn);
		loginclose_btn.setOnClickListener(this);
		hideshow_btn = (ImageView) findViewById(R.id.hideshow_btn);
		hideshow_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.login_id:
				final String edt = name_edt.getEditableText().toString();
				if (TextUtils.isEmpty(edt)) {
					Toast.makeText(this, "用户名/手机号", Toast.LENGTH_SHORT).show();
					return;
				}
				String edt1 = pwd_edt.getEditableText().toString();
				if (TextUtils.isEmpty(edt1)) {
					Toast.makeText(this, "输入密码", Toast.LENGTH_SHORT).show();
					return;
				}
				BmobUser.loginByAccount(this, edt, edt1, new LogInListener<UserEntity>() {

					@Override
					public void done(UserEntity userEntity, BmobException e) {
						if (userEntity != null) {
							Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
							Intent loginbackintent = new Intent();
							loginbackintent.putExtra("loginkey", edt);
							setResult(600, loginbackintent);
							finish();
						} else {
							Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
						}
					}
				});
				break;
			case R.id.intentregister_tv:
				Intent in = new Intent(this, RegisterActivity.class);
				startActivity(in);
				break;
			case R.id.loginclose_btn:
				finish();
				break;
			case R.id.hideshow_btn:
				if (count % 2 == 1) {
					hideshow_btn.setImageResource(R.mipmap.btn_code_show);
					pwd_edt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				} else {
					hideshow_btn.setImageResource(R.mipmap.btn_code_hide);
					pwd_edt.setTransformationMethod(PasswordTransformationMethod.getInstance());

				}
				//切换后将EditText光标置于末尾
				CharSequence charSequence = pwd_edt.getText();
				if (charSequence instanceof Spannable) {
					Spannable spanText = (Spannable) charSequence;
					Selection.setSelection(spanText, charSequence.length());
				}
				count++;
				break;
		}
	}

}
