package com.yztc.lovetv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.AllFragmentBean;
import com.yztc.lovetv.myutil.BitmapUtils;

import java.util.List;

/**
 * Created by My on 2016/12/20.
 */

public class AllFragmentAdapter extends BaseQuickAdapter<AllFragmentBean> {

    private Context mContext;

    public AllFragmentAdapter(Context context , List<AllFragmentBean> data) {
        super(R.layout.tuijian_item_all_fragment,data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AllFragmentBean allFragmentBean) {
        //名字
        baseViewHolder.setText(R.id.tuijian_tv_name,allFragmentBean.getName());
        //头像
        Glide.with(mContext).load(allFragmentBean.getIconUrl()).transform(new RoundTransformation(mContext)).into((ImageView) baseViewHolder.getView(R.id.tuijian_iv_icon));
        //视频截图
        Glide.with(mContext).load(allFragmentBean.getImgUrl()).placeholder(R.mipmap.live_default).into((ImageView) baseViewHolder.getView(R.id.tuijian_iv_all));
        //简介
        baseViewHolder.setText(R.id.tuijian_tv_introduce,allFragmentBean.getTitle());
        //看视频的人数
        int personCount = Integer.valueOf(allFragmentBean.getCount());
        int personCountPoint;
        if (personCount < 10001){//如果在一万以下  显示数量
            baseViewHolder.setText(R.id.tuijian_all_count,allFragmentBean.getCount());
        }else {//如果在一万以上
            personCountPoint = personCount/1000%10;//先取到除以10000后余数的第一位
            personCount = personCount/10000;//在取到万以上整数
            //最后拼接到一起
            baseViewHolder.setText(R.id.tuijian_all_count,personCount+"."+personCountPoint+"W");
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
