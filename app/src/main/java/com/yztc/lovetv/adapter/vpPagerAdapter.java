package com.yztc.lovetv.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.LunBoPictureBean;

import java.util.List;

/**
 * Created by My on 2016/12/19.
 */

public class vpPagerAdapter extends LoopPagerAdapter {

    private Context mContext;
    private List<LunBoPictureBean> mLunBoPictureBean;

    public vpPagerAdapter(RollPagerView viewPager, Context mContext, List<LunBoPictureBean> mLunBoPictureBean) {
        super(viewPager);
        this.mContext = mContext;
        this.mLunBoPictureBean = mLunBoPictureBean;

    }


    @Override
    public View getView(ViewGroup container, int position) {
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, R.dimen.dp_150));
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        relativeLayout.addView(imageView);
        TextView textView = new TextView(mContext);
        textView.setText(mLunBoPictureBean.get(position).getTitle());
        textView.setTextColor(Color.GRAY);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                -2, -2);
        // layoutParams.setMargins(100, 100, 100, 100);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
        relativeLayout.addView(textView, layoutParams);



        Glide.with(mContext).load(mLunBoPictureBean.get(position).getPicture()).into(imageView);

        return relativeLayout;
    }

    @Override
    public int getRealCount() {
        return mLunBoPictureBean != null ? mLunBoPictureBean.size() : 0;
    }
}


