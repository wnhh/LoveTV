package com.yztc.lovetv.fragment.tabhost;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yztc.lovetv.R;
import com.yztc.lovetv.activity.mine.ChongzhiActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener{

    Button chongzhiBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        chongzhiBtn = (Button) v.findViewById(R.id.chongzhi_btn);
        chongzhiBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chongzhi_btn:
                Intent intent = new Intent(getActivity(), ChongzhiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
