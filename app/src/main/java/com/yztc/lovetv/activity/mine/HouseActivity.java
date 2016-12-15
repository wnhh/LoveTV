package com.yztc.lovetv.activity.mine;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yztc.lovetv.R;

public class HouseActivity extends AppCompatActivity {
    ImageView ivBack;
    Button tvLive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        initView();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_house_back);
        tvLive = (Button) findViewById(R.id.tv_see);
    }
}

