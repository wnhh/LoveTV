package com.yztc.lovetv.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.LunBoPictureBean;
import com.yztc.lovetv.contant.PicUrl;
import com.yztc.lovetv.viewall.FlowIndicator;

import java.util.List;

/**
 * Created by My on 2016/12/17.
 */

public class MyPagerAdapter extends PagerAdapter {
    List<LunBoPictureBean> mPictures;
    Context mContext;
    LayoutInflater inflater;

    public MyPagerAdapter(Context context, List<LunBoPictureBean> pictures) {
        this.mContext = context;
        this.mPictures = pictures;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mPictures !=null ? mPictures.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = inflater.inflate(R.layout.item_tuijian_vp,container,false);
        ImageView picture = (ImageView) v.findViewById(R.id.vp_tuijian_picture);

        Glide.with(mContext).load(mPictures.get(position).getPicture()).into(picture);
        TextView textView = (TextView) v.findViewById(R.id.vp_tuijian_text);
        textView.setText(mPictures.get(position).getTitle());


        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
