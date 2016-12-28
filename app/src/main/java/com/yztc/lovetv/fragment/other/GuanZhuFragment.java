package com.yztc.lovetv.fragment.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.BackDataAdapter;
import com.yztc.lovetv.bean.BackNeedData;
import com.yztc.lovetv.db.BackDataOperateManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuanZhuFragment extends Fragment {
	//数据源
	private List<BackNeedData> bnd;
	private RecyclerView guanzhu_rv;
	private BackDataOperateManager bmg;
	//适配器
	private BackDataAdapter bap;
	private Button ceshi_btn;
	public GuanZhuFragment() {
		// Required empty public constructor
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_guan_zhu, container, false);
		bmg = new BackDataOperateManager(getContext());
		//测试数据
		ceshi_btn = (Button) view.findViewById(R.id.ceshi_btn);
				BackNeedData back=new BackNeedData();
				back.setBigImg("http://snap.quanmin.tv/4991534-1482836822-976.jpg?imageView2/2/w/390/");
				back.setHeadImg("http://image.quanmin.tv/avatar/a099a68fc2e3164d18b82985b9130b7c?imageView2/2/w/300/");
				back.setUsername("wnax");
				back.setTitleInfo("为我证明");
				try {
					bmg.insert(back);
				} catch (Exception e) {
					e.printStackTrace();
				}
		initData();
		initView(view);
		return view;
	}
	private void initData() {
		try {
			bnd = bmg.getAll();
			bap = new BackDataAdapter(getContext(), bnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initView(View view) {
		guanzhu_rv = (RecyclerView) view.findViewById(R.id.guanzhu_rv);
		guanzhu_rv.setAdapter(bap);
		guanzhu_rv.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
	}
}
