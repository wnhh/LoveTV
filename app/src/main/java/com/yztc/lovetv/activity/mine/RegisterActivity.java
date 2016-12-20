package com.yztc.lovetv.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.UserEntity;
import com.yztc.lovetv.myutil.CountDownButton;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
//import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

	private Toolbar register_tb;
	private CountDownButton yanzhengma_btn;
	private EditText phone_edt;
	private EditText yanzhengma_edt;
	private EditText name_edt;
	private EditText pwd_edt;
	private Button register_id;
	private String edt,edt1,edt2,edt3;

	private UserEntity user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}

	private void initView() {
		register_tb = (Toolbar) findViewById(R.id.register_tb);
		yanzhengma_btn = (CountDownButton) findViewById(R.id.yanzhengma_btn);
		phone_edt = (EditText) findViewById(R.id.phone_edt);
		yanzhengma_edt = (EditText) findViewById(R.id.yanzhengma_edt);
		name_edt = (EditText) findViewById(R.id.name_edt);
		pwd_edt = (EditText) findViewById(R.id.pwd_edt);
		register_id = (Button) findViewById(R.id.register_id);
		yanzhengma_btn.setOnClickListener(this);
		register_id.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.yanzhengma_btn:
				edt = phone_edt.getEditableText().toString();
				if (TextUtils.isEmpty(edt)) {
					Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
					return;
				}
				//CustomDialog cd=new CustomDialog(this);
				//cd.show();
				Log.e("tag","   手机号：" + edt);
					/*BmobSMS.requestSMSCode(edt, "模板名称", new QueryListener<Integer>() {
						public void done(Integer smsId, BmobException ex) {
							//15524612278
							if (ex == null) {//验证码发送成功
								Toast.makeText(RegisterActivity.this, "接受验证码成功", Toast.LENGTH_SHORT).show();
								Log.i("smile", "短信id："+smsId);//用于查询本次短信发送详情
								//Log.e("tag","短信id" + smsId);
							}
						}
					});*/
				break;
			case R.id.register_id:
				edt = phone_edt.getEditableText().toString();
				edt1= yanzhengma_edt.getEditableText().toString();
				edt2 = name_edt.getEditableText().toString();
				edt3 = pwd_edt.getEditableText().toString();
				if (TextUtils.isEmpty(edt)) {
					Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edt1)) {
					Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edt2)) {
					Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(edt3)) {
					Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
					return;
				}
				user = new UserEntity();
				user.setUsername(edt2);
				user.setPassword(edt3);
				user.setMobilePhoneNumber(edt);
				//验证验证码
				/*BmobSMS.verifySmsCode(edt, edt1, new UpdateListener() {

					public void done(BmobException ex) {
						if(ex==null){//短信验证码已验证成功
							//Log.i("smile", "验证通过");
							user.signUp(new SaveListener<UserEntity>() {
								@Override
								public void done(UserEntity o, BmobException e) {
									if (e == null) {
										Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
									} else {
										if (e.getErrorCode() == 202) {
											Toast.makeText(RegisterActivity.this, "用户名重复，" + e.getMessage(), Toast.LENGTH_SHORT).show();
										}
										else
										{
											Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
										}
									}
								}
							});
						}else{
							Toast.makeText(RegisterActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
							return;
						}
					}
				});*/
				break;
		}
	}
}
