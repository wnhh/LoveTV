package com.yztc.lovetv.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.Liveshow;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public class LanmuAdapter extends BaseQuickAdapter<Liveshow> {
    private Context context;
    public LanmuAdapter(Context context, List<Liveshow> data) {
        super(R.layout.item_lanmu, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Liveshow liveshow) {
        baseViewHolder.setText(R.id.text1,liveshow.getName());
        Picasso.with(context).load(liveshow.getPicId()).placeholder(R.mipmap.loading_default).into((ImageView) baseViewHolder.getView(R.id.icon1));
    }
}
