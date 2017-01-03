package com.yztc.lovetv.fragment.contntfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.ContentFragmentAdapter;
import com.yztc.lovetv.adapter.LiveActivityAdapter;
import com.yztc.lovetv.bean.ContentText;
import com.yztc.lovetv.bean.Totalshowbean;
import com.yztc.lovetv.bean.TotaoshowText;
import com.yztc.lovetv.myutil.MyTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment implements View.OnClickListener{

    public ImageView focus;
    public ImageView remind;
    public Boolean ischecked=true;

    private List<ContentText> contentTexts;
    private LinearLayout layout;


    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_content, container, false);
        initViews(v);
        return v;
    }



    private void initViews(View v) {
        focus= (ImageView) v.findViewById(R.id.focus_iv);
        focus.setOnClickListener(this);
        Log.e("kim","------------");
        remind= (ImageView) v.findViewById(R.id.notice_iv);
        remind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.focus_iv:
                if (ischecked){
                    focus.setImageResource(R.mipmap.btn_focus_normal);
                }else {
                    focus.setImageResource(R.mipmap.btn_focus_selected);
                }
                break;
        }
    }
}
