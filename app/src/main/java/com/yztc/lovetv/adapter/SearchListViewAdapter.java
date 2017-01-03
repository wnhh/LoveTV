package com.yztc.lovetv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yztc.lovetv.R;
import com.yztc.lovetv.bean.HistoryBean;
import com.yztc.lovetv.db.HistoryBeanManager;

import java.util.List;

/**
 * Created by My on 2017/1/1.
 */

public class SearchListViewAdapter extends BaseAdapter {
    Context mContext;
    List<HistoryBean> mHistories;
    LayoutInflater inflater;
    HistoryBeanManager historyBeanManager;

    public SearchListViewAdapter(Context Context, List<HistoryBean> Histories) {
        this.mContext = Context;
        this.mHistories = Histories;
        inflater = LayoutInflater.from(Context);
        historyBeanManager = new HistoryBeanManager(mContext);
    }

    @Override
    public int getCount() {
        return mHistories.size();
    }

    @Override
    public Object getItem(int position) {
        return mHistories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHold v = null;
        if (convertView == null ){
            convertView = inflater.inflate(R.layout.item_search_listview,parent,false);
            v = new ViewHold(convertView);
            convertView.setTag(v);
        }else{
            v = (ViewHold) convertView.getTag();
        }
        String history = mHistories.get(position).getHistory();
        v.tvHistory.setText(history);
        v.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    historyBeanManager.delete(mHistories.get(position));
                    mHistories.remove(position);
                    notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }


    static class ViewHold{
        TextView tvHistory;
        ImageView ivDelete;

        public ViewHold(View v) {
            tvHistory = (TextView) v.findViewById(R.id.tv_search_history);
            ivDelete = (ImageView) v.findViewById(R.id.delete_item_history);
        }
    }
}
