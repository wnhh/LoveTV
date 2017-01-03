package com.yztc.lovetv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.ContentText;
import com.yztc.lovetv.bean.TotaoshowText;
import com.yztc.lovetv.myutil.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/1/2.
 */

public class ContentFragmentAdapter extends BaseQuickAdapter<ContentText>{

    private Context context;
    public ContentFragmentAdapter(Context context, List<ContentText> data) {
        super(R.layout.fragment_content,data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ContentText contentText) {
        baseViewHolder.setText(R.id.detailname_tv,contentText.getNameId());
        baseViewHolder.setText(R.id.detail_tv,contentText.getBrief());
        //图形转换空指针报错
        if (!TextUtils.isEmpty(contentText.getHeadImageId())){
            Glide.with(mContext).load(contentText.getHeadImageId()).transform(new ContentFragmentAdapter.RoundTransformation(mContext)).into((ImageView) baseViewHolder.getView(R.id.head_iv));
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
