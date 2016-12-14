package com.yztc.lovetv.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.TuiJianItem;

import java.util.List;

public class JingCaiTJItemAdapter extends BaseQuickAdapter<TuiJianItem>{

    private Context context;
    public JingCaiTJItemAdapter(Context context, List<TuiJianItem> data) {
        super(R.layout.item_tuijian_jingcai_itemview,data);
        this.context=context;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, TuiJianItem tuiJianItem) {
        baseViewHolder.setText(R.id.introdece_tv,tuiJianItem.getName())
                .setText(R.id.name_tv,tuiJianItem.getName());
      /*  if(!TextUtils.isEmpty(tuiJianItem.getBigPicUrl())) {
            Picasso.with(mContext).load(tuiJianItem.getBigPicUrl()).into((ImageView) baseViewHolder.getView(R.id.largepic_iv));
        }
        if(!TextUtils.isEmpty(tuiJianItem.getPersonalPicUrl())) {
            Picasso.with(mContext).load(tuiJianItem.getPersonalPicUrl()).into((ImageView) baseViewHolder.getView(R.id.rtouxiang_iv));
        }*/
    }
    protected void convertHead(BaseViewHolder baseViewHolder,final TuiJianItem tuiJianItem) {

    }

}

