package com.yztc.lovetv.fragment.tuijianfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.yztc.lovetv.R;
import com.yztc.lovetv.TuijianAsyn;
import com.yztc.lovetv.adapter.AllFragmentAdapter;
import com.yztc.lovetv.base.BaseApplication;
import com.yztc.lovetv.bean.AllFragmentBean;
import com.yztc.lovetv.bean.ItemFragmentBean;
import com.yztc.lovetv.contant.TabhostContant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {

    RecyclerView itemFragmentRv ;
    List<AllFragmentBean> itemFragmentBeans;
//    private String url;
//    public  void seturl(String url)
//    {
//        this.url=url;
//        Log.e("TAG", "seturl: "+url);
//        //String url = savedInstanceState.getString(TabhostContant.URL_KEY,null);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item, container, false);
        Bundle bundle = getArguments();
        String url = bundle.getString(TabhostContant.URL_KEY);
        initRecyclerView(v,url);
        return v;
    }
    private void initRecyclerView(View v,String url) {
        itemFragmentBeans = new ArrayList<>();
        itemFragmentRv = (RecyclerView) v.findViewById(R.id.tuijian_itemfragment);
        TuijianAsyn tuijianAsyn = new TuijianAsyn(url);
        tuijianAsyn.executeOnExecutor(BaseApplication.getExecutor());
        tuijianAsyn.setGetJson(new TuijianAsyn.GetJson() {
            @Override
            public void finish(String s) {
                try {
                    JSONObject allData = new JSONObject(s);
                    JSONArray data = allData.getJSONArray("data");
                    for (int i=0; i<data.length();i++){
                        AllFragmentBean allFragmentBean = new AllFragmentBean();
                        JSONObject dataInfo = data.getJSONObject(i);
                        String bigImg = dataInfo.getString("thumb");//屏幕截图网址
                        allFragmentBean.setImgUrl(bigImg);
                        String icon = dataInfo.getString("avatar");//头像网址
                        allFragmentBean.setIconUrl(icon);
                        String title = dataInfo.getString("title");//详情title
                        allFragmentBean.setTitle(title);
                        String count = dataInfo.getString("view");//看视频的人数
                        allFragmentBean.setCount(count);
                        String name = dataInfo.getString("nick");//名字
                        allFragmentBean.setName(name);
                        itemFragmentBeans.add(allFragmentBean);

                    }
                    AllFragmentAdapter  allFragmentAdapter = new AllFragmentAdapter(getContext(),itemFragmentBeans);
                    itemFragmentRv.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
                    itemFragmentRv.setAdapter(allFragmentAdapter);
                    allFragmentAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
