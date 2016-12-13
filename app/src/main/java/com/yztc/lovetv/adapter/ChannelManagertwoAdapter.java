package com.yztc.lovetv.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yztc.lovetv.R;

import java.util.List;

/**
 * Created by bodhixu on 2016/11/21.
 */
/*public class DataAdapter extends BaseRecyclerViewAdapter<User_TouGao>{

    public DataAdapter(Context context, List<User_TouGao> datas) {
        super(context, datas, R.layout.items_user_view, new int[]{R.id.user_img, R.id.user_title_tv,R.id.user_name_tv});
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //获得子View的集合
        BaseViewHolder ba= (BaseViewHolder) holder;
        SparseArray<View> itemViews = ba.getItemViews();
        //对子View处理
        TextView tv = (TextView) itemViews.get(R.id.user_title_tv);
        tv.setText(datas.get(position).getTitle());
        TextView tvone=(TextView) itemViews.get(R.id.user_name_tv);
        tvone.setText(datas.get(position).getName());
        ImageView img= (ImageView) itemViews.get(R.id.user_img);
        Picasso.with(context).load(datas.get(position).getPicid()).into(img);
    }
}*/
public class ChannelManagertwoAdapter extends BaseQuickAdapter<String>{

    private Context context;
    public ChannelManagertwoAdapter(Context context, List<String> data) {
        super(R.layout.item_recyclerviewtwo,data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.channelnametwo_tv,s);
    }
}

