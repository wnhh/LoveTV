package com.yztc.lovetv.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.SearchListViewAdapter;
import com.yztc.lovetv.bean.HistoryBean;
import com.yztc.lovetv.db.HistoryBeanManager;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private PopupWindow mHistories;
    private LayoutInflater mInflater;
    private ImageView ivSearch;
    private ImageView ivDeleteAll;
    private EditText mSearchEt;
    private View background;
    private ListView mHistoryLv;
    private List<HistoryBean> mHistoriesStr;
    private HistoryBeanManager historyBeanManager;
    private RelativeLayout mtitleRl;
    private SearchListViewAdapter searchListViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        historyBeanManager = new HistoryBeanManager(this);
        initView();
        initPopupWindow();
    }

    private void initPopupWindow() {
        mInflater = LayoutInflater.from(this);
        View layout = mInflater.inflate(R.layout.search_popupwindow, null);
        mHistories = new PopupWindow(layout, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        //popupwindow从内部控件获取焦点
        mHistories.setFocusable(true);
        //设置触摸外面的时消失
        mHistories.setBackgroundDrawable(new ColorDrawable());
        mHistories.setOutsideTouchable(true);
        //backgroundAlpha(1f);//背景恢复

        //实例化ListView
        mHistoryLv = (ListView) layout.findViewById(R.id.search_listview);
        ivDeleteAll = (ImageView) layout.findViewById(R.id.iv_search_delete_all);
        ivDeleteAll.setOnClickListener(this);
        background = layout.findViewById(R.id.search_background);
        background.setOnClickListener(this);
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_search_back);
        ivBack.setOnClickListener(this);
        ivSearch = (ImageView) findViewById(R.id.iv_search_search);
        ivSearch.setOnClickListener(this);
        mSearchEt = (EditText) findViewById(R.id.edt_search);
        mtitleRl = (RelativeLayout) findViewById(R.id.edt_rl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_back:
                finish();
                break;
            case R.id.iv_search_search:
                String text = mSearchEt.getText().toString();
                if (!text.equals(null)) {
                    if (mHistories.isShowing()) {
                        mHistories.dismiss();
                        //backgroundAlpha(1f);
                    } else {
                        //popupwindow显示的位置

                        //实例化数据源
                        try {
                            HistoryBean historyBean = new HistoryBean();
                            historyBean.setHistory(text);
                            historyBeanManager.insert(historyBean);
                            mHistoriesStr = historyBeanManager.getAll();
                            searchListViewAdapter = new SearchListViewAdapter(this, mHistoriesStr);
                            mHistoryLv.setAdapter(searchListViewAdapter);
                            mHistories.showAsDropDown(mtitleRl);//在搜索栏的下方
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        //backgroundAlpha(0.5f);
                    }
                } else {
                    Toast.makeText(this, "您输入的内容为空，请重新输入", Toast.LENGTH_SHORT);
                }
                break;

            case R.id.iv_search_delete_all:
                try {
                    historyBeanManager.deleteAll();
                    mHistoriesStr.clear();
                    searchListViewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.search_background:
                if (mHistories.isShowing()) {
                    mHistories.dismiss();
                }
                break;
        }
    }

}
