package com.yztc.lovetv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.TotaoshowText;
import com.yztc.lovetv.myutil.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class LiveActivityAdapter extends BaseQuickAdapter<TotaoshowText> {

    private Context context;

    public LiveActivityAdapter(Context context, List<TotaoshowText> data) {
        super(R.layout.layout_live,data);
        this.context=context;
    }



    @Override
    protected void convert(BaseViewHolder baseViewHolder, TotaoshowText totalshowtext) {
        baseViewHolder.setText(R.id.room_tv,totalshowtext.getNameId());
        baseViewHolder.setText(R.id.brief_tv,totalshowtext.getBrief());
        baseViewHolder.setText(R.id.num,totalshowtext.getWatchNum());
        if(!TextUtils.isEmpty(totalshowtext.getLiveShotId())) {
            Glide.with(mContext).load(totalshowtext.getLiveShotId()).placeholder(R.mipmap.live_default).into((ImageView) baseViewHolder.getView(R.id.pic));
        }
        //图形转换空指针报错
        if (!TextUtils.isEmpty(totalshowtext.getHeadImageId())){
            Glide.with(mContext).load(totalshowtext.getHeadImageId()).transform(new LiveActivityAdapter.RoundTransformation(mContext)).into((ImageView) baseViewHolder.getView(R.id.head_iv));
        }
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

