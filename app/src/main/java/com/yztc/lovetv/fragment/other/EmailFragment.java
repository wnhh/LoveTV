package com.yztc.lovetv.fragment.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yztc.lovetv.R;
import com.yztc.lovetv.myutil.YZCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.EmailVerifyListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment implements View.OnClickListener {
	private EditText emaddress_edt;
	private EditText yanzhengma_edt;
	private ImageView yanzhengpa_pic;
	private Button sendemail_id;
	private YZCode code;
	public EmailFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_email, container, false);
		code = code.getInstance();
		initView(view);
		return view;
	}

	//判断email格式是否正确
	public boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}
	private boolean CheckCode() {
		if(yanzhengma_edt.getEditableText().toString().equalsIgnoreCase(code.getCode()))
			return true;
		else
			return false;
	}

	private void initView(View view) {
		emaddress_edt = (EditText) view.findViewById(R.id.emaddress_edt);
		yanzhengma_edt = (EditText) view.findViewById(R.id.yanzhengma_edt);
		yanzhengpa_pic = (ImageView) view.findViewById(R.id.yanzhengpa_pic);
		sendemail_id = (Button) view.findViewById(R.id.sendemail_id);
		sendemail_id.setOnClickListener(this);
		yanzhengpa_pic.setImageBitmap(code.createBitmap());
		yanzhengpa_pic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				yanzhengpa_pic.setImageBitmap(code.createBitmap());
			}
		});
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.sendemail_id:
				if(!isEmail(emaddress_edt.getEditableText().toString()))
				{
					Toast.makeText(getContext(),"邮箱格式不正确",Toast.LENGTH_SHORT).show();
					return;
				}
				if(!CheckCode())
				{
					Toast.makeText(getContext(),"验证码不正确",Toast.LENGTH_SHORT).show();
					return;
				}
				BmobUser.requestEmailVerify(getContext(),emaddress_edt.getEditableText().toString(),new EmailVerifyListener() {
							@Override
							public void onSuccess() {
								Toast.makeText(getContext(),"请求验证邮件成功，请到邮箱中进行激活。",Toast.LENGTH_SHORT).show();
							}
							@Override
							public void onFailure(int i, String s) {

								Toast.makeText(getContext(),"失败"+i,Toast.LENGTH_SHORT).show();
							}
						});
				break;
		}
	}
}
