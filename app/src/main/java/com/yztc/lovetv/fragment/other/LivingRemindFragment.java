package com.yztc.lovetv.fragment.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.BackDataAdapter;
import com.yztc.lovetv.adapter.BackDataTwoAdapter;
import com.yztc.lovetv.bean.BackNeedData;
import com.yztc.lovetv.db.BackDataOperateManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivingRemindFragment extends Fragment {

	//数据源
	private List<BackNeedData> bnd;
	private BackDataOperateManager bmg;
	//适配器
	private BackDataTwoAdapter bap;
	private RecyclerView remindbackrv;
	private ImageView remindiv;

	public LivingRemindFragment() {
		// Required empty public constructor
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_living_remind, container, false);
		bmg = new BackDataOperateManager(getContext());
		remindbackrv = (RecyclerView) view.findViewById(R.id.remindbackrv);
		View itemview = inflater.inflate(R.layout.tuijian_item_back_fragment,null, false);
		remindiv = (ImageView) itemview.findViewById(R.id.setswitch_back_iv);
		initData();
		remindbackrv.addOnItemTouchListener(new OnItemClickListener() {
			@Override
			public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
				boolean switchBtn = bnd.get(0).getSwitchBtn();
				switchBtn=!switchBtn;
				if(switchBtn) {
					remindiv.setImageResource(R.mipmap.open);
					bap.notifyDataSetChanged();
				}
				else {
					remindiv.setImageResource(R.mipmap.close);
					bap.notifyDataSetChanged();
				}

			}
		});
		initView(view);
		return view;
	}
	private void initData() {
		try {
			bnd = bmg.getAll();
			bap = new BackDataTwoAdapter(getContext(), bnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initView(View view) {
		remindbackrv.setAdapter(bap);
		remindbackrv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

	}
}
