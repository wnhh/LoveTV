package com.yztc.lovetv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.BackNeedData;
import com.yztc.lovetv.myutil.BitmapUtils;

import java.util.List;

/**
 * Created by My on 2016/12/20.
 */

public class BackDataTwoAdapter extends BaseQuickAdapter<BackNeedData> {

    private Context mContext;

    public BackDataTwoAdapter(Context context , List<BackNeedData> data) {
        super(R.layout.tuijian_item_back_fragment,data);
        this.mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, BackNeedData allFragmentBean) {
        //名字
        baseViewHolder.setText(R.id.tuijian_back_tv_name,allFragmentBean.getUsername());
        //头像
        Glide.with(mContext).load(allFragmentBean.getHeadImg()).transform(new RoundTransformation(mContext)).into((ImageView) baseViewHolder.getView(R.id.tuijian_back_iv_icon));
        //简介
        baseViewHolder.setText(R.id.tuijian_back_tv_introduce,allFragmentBean.getTitleInfo());

    }
    //头像变成圆的
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
