package com.yztc.lovetv.fragment.other;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.ConvertVActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAttentionFragment extends Fragment{

	private TextView registerJL_tv;
	private RelativeLayout RegisterJL_rl;
	public MyAttentionFragment() {
		Bundle bundle=new Bundle();
		bundle.putString("RJLkey","125");
		this.setArguments(bundle);
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
				Toast.makeText(getContext(),"领取成功！",Toast.LENGTH_SHORT).show();
				RegisterJL_rl.setVisibility(View.GONE);
				ConvertVActivity con= (ConvertVActivity) getActivity();
				con.setTextValue("125");
			}
		});

	}

}
