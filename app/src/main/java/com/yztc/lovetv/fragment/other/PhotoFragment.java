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
import com.yztc.lovetv.activity.ConvertVActivity;
import com.yztc.lovetv.bean.UserEntity;
import com.yztc.lovetv.myutil.YZCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment implements View.OnClickListener {
	private EditText phonenumber_edt;
	private EditText yanzhengmaphone_edt;
	private Button nextstep_id;
	private YZCode code;
	private ImageView phoneyzm_iv;

	public PhotoFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_photo, container, false);
		code = code.getInstance();
		initView(view);
		return view;
	}

	private boolean CheckCode() {
		if (yanzhengmaphone_edt.getEditableText().toString().equalsIgnoreCase(code.getCode()))
			return true;
		else
			return false;
	}

	//判断是否全是数字
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	private void initView(View view) {
		phonenumber_edt = (EditText) view.findViewById(R.id.phonenumber_edt);
		yanzhengmaphone_edt = (EditText) view.findViewById(R.id.yanzhengmaphone_edt);
		nextstep_id = (Button) view.findViewById(R.id.nextstep_id);
		phoneyzm_iv = (ImageView) view.findViewById(R.id.phoneyzm_iv);
		phoneyzm_iv.setOnClickListener(this);
		nextstep_id.setOnClickListener(this);
		phoneyzm_iv.setImageBitmap(code.createBitmap());
		phoneyzm_iv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				phoneyzm_iv.setImageBitmap(code.createBitmap());
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.nextstep_id:
				if(!isNumeric(phonenumber_edt.getEditableText().toString()))
				{
					Toast.makeText(getContext(),"手机号码格式不正确",Toast.LENGTH_SHORT).show();
					return;
				}
				if(!CheckCode())
				{
					Toast.makeText(getContext(),"验证码不正确",Toast.LENGTH_SHORT).show();
					return;
				}
				UserEntity user=new UserEntity();
				user.setMobilePhoneNumber(phonenumber_edt.getEditableText().toString());
				user.setMobilePhoneNumberVerified(true);
				UserEntity cur = BmobUser.getCurrentUser(getContext(),UserEntity.class);
				user.update(getContext(),cur.getObjectId(),new UpdateListener() {

					@Override
					public void onSuccess() {
						Toast.makeText(getContext(),"绑定成功",Toast.LENGTH_SHORT).show();
						phonenumber_edt.setText("");
						yanzhengmaphone_edt.setText("");
						ConvertVActivity con= (ConvertVActivity) getActivity();
						con.finish();
					}

					@Override
					public void onFailure(int i, String s) {
						Toast.makeText(getContext(),"绑定失败",Toast.LENGTH_SHORT).show();
					}
				});
				break;
		}
	}

}
