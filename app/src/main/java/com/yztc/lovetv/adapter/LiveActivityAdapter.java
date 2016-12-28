package com.yztc.lovetv.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.TotaoshowText;

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

    //设置图形为圆形
    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
            Log.e("kim","----------------------");
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);//定义一个渲染器
            paint.setShader(shader);//设置渲染器
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);//绘制图形

            squaredBitmap.recycle();
            Log.e("kkk","aaa---"+bitmap);
            return bitmap;

        }

        @Override
        public String key() {
            return "circle";
        }
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TotaoshowText totalshowtext) {
        baseViewHolder.setText(R.id.room_tv,totalshowtext.getNameId());
        baseViewHolder.setText(R.id.brief_tv,totalshowtext.getBrief());
        baseViewHolder.setText(R.id.num,totalshowtext.getWatchNum());
        if(!TextUtils.isEmpty(totalshowtext.getLiveShotId())) {
            Picasso.with(mContext).load(totalshowtext.getLiveShotId()).placeholder(R.mipmap.live_default).into((ImageView) baseViewHolder.getView(R.id.pic));
        }
        //图形转换空指针报错
        if (!TextUtils.isEmpty(totalshowtext.getHeadImageId())){
            Picasso.with(mContext).load(totalshowtext.getHeadImageId()).transform(new CircleTransform()).placeholder(R.mipmap.head).into((ImageView) baseViewHolder.getView(R.id.head_iv));
        }
    }
}

