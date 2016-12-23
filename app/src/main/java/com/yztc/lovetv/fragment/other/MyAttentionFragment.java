package com.yztc.lovetv.fragment.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yztc.lovetv.R;
import com.yztc.lovetv.fragment.tabhost.MineFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAttentionFragment extends Fragment implements View.OnClickListener{


	private TextView registerJL_tv;
	private RelativeLayout RegisterJL_rl;
	private MineFragment mf;
	private SetValueMethod sm;
	public void setSm(SetValueMethod sm) {
		this.sm = sm;
	}
	public MyAttentionFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my_attention, container, false);

		initView(view);
		return view;
	}
	private void initView(View view) {

		registerJL_tv = (TextView) view.findViewById(R.id.registerJL_tv);
		RegisterJL_rl = (RelativeLayout) view.findViewById(R.id.RegisterJL_rl);
		registerJL_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.e("haha","----------------");
				Toast.makeText(getContext(),"领取成功！",Toast.LENGTH_SHORT).show();
				/*Bundle bundle=new Bundle();
				bundle.putString("RJLkey","125");
				MyAttentionFragment mf=new MyAttentionFragment();
				mf.setArguments(bundle);*/
				if(sm!=null)
				{
					sm.setValue("125");
				}
				RegisterJL_rl.setVisibility(View.GONE);
			}
		});
	}

	@Override
	public void onClick(View view) {
		switch (view.getId())
		{
			case R.id.registerJL_tv:
				break;

		}

	}
	public interface SetValueMethod
	{
		void setValue(String str);
	}

}
