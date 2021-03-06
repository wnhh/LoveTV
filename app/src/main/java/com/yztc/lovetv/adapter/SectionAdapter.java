package com.yztc.lovetv.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.ShowActivity;
import com.yztc.lovetv.bean.TuiJianItem;
import com.yztc.lovetv.bean.TuijianStringitem;
import com.yztc.lovetv.myutil.BitmapUtils;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionAdapter extends BaseSectionQuickAdapter<TuijianStringitem> {
    private Context context;
    TuiJianItem itemtj;
    public SectionAdapter(Context context,List<TuijianStringitem> data) {
        super(R.layout.item_tuijian_jingcai_itemview, R.layout.item_tuijian_jingcai, data);
        this.context = context;
    }
    protected void convertHead(BaseViewHolder helper, final TuijianStringitem item) {
        helper.setText(R.id.perfitpro_tv, item.header);
        helper.setVisible(R.id.hahaha, item.isMore());
        helper.setOnClickListener(R.id.tv_chouchou, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到具体页面复用fragment；
            }
        });
    }
    @Override
    protected void convert(BaseViewHolder helper, TuijianStringitem item) {
        itemtj = item.t;
          helper.setText(R.id.introdece_tv, itemtj.getIntroduce())
                .setText(R.id.name_tv, itemtj.getName());

        Glide.with(mContext).load(itemtj.getBigPicUrl()).into((ImageView) helper.getView(R.id.largepic_iv));
        Glide.with(mContext).load(itemtj.getPersonalPicUrl()).transform(new RoundTransformation(context)).into((ImageView) helper.getView(R.id.rtouxiang_iv));

    }
    class RoundTransformation extends BitmapTransformation {

        public RoundTransformation(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                                   int outWidth, int outHeight) {
            Bitmap roteBmp = BitmapUtils.getRoundCornerBitmap(toTransform, 360);
            if (roteBmp != toTransform) {
                toTransform.recycle();
            }
            return roteBmp;
        }
        @Override
        public String getId() {
            return "glide";
        }
    }
}
