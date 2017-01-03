package com.yztc.lovetv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.yztc.lovetv.R;
import com.yztc.lovetv.fragment.contntfragment.ChatFragment;
import com.yztc.lovetv.fragment.contntfragment.ContentFragment;
import com.yztc.lovetv.fragment.contntfragment.LiveChatFragment;
import com.yztc.lovetv.fragment.show.ShowFragment;

public class ShowActivity extends AppCompatActivity {

    private ShowFragment showFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent = getIntent();
        int value = intent.getIntExtra("value", 1);

        Bundle bundle=new Bundle();
        bundle.putInt("zhang",value);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        ShowFragment showFragment=new ShowFragment();
        ContentFragment contentFragment=new ContentFragment();
        LiveChatFragment liveChatFragment=new LiveChatFragment();
        showFragment.setArguments(bundle);
        contentFragment.setArguments(bundle);
        liveChatFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.activity_show,showFragment);
        fragmentTransaction.add(R.id.activity_show,contentFragment);
        fragmentTransaction.add(R.id.activity_show,liveChatFragment);
        fragmentTransaction.commit();

    }
}
