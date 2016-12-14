package com.yztc.lovetv.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yztc.lovetv.R;

import java.util.List;

public class JingCaiTJAdapter extends BaseQuickAdapter<String>{

    private Context context;
    public JingCaiTJAdapter(Context context, List<String> data) {
        super(R.layout.item_tuijian_jingcai,data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.perfitpro_tv,s);
    }
}

