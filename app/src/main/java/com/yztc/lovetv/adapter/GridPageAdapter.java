package com.yztc.lovetv.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.GridText;

import java.util.List;

/**
 * Created by My on 2016/12/7.
 */

public class GridPageAdapter extends PagerAdapter {

	private List<GridText> lg;
	private Context con;
	LayoutInflater inflater;

	public GridPageAdapter(List<GridText> lg, Context con) {
		this.lg = lg;
		this.con = con;
		inflater=LayoutInflater.from(con);
	}

	@Override
	public int getCount() {
		return lg!=null?lg.size():0;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view==object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view=inflater.inflate(R.layout.item_grid,container,false);
		ImageView iv = (ImageView) view.findViewById(R.id.pic_iv);
		iv.setImageResource(lg.get(position).getPicId());
		TextView tv= (TextView) view.findViewById(R.id.grid_tv);
		tv.setText(lg.get(position).getText());
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
		container.removeView((View) object);
	}
}
