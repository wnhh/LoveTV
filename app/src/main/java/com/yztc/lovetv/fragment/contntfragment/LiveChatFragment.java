package com.yztc.lovetv.fragment.contntfragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveChatFragment extends Fragment {


    private TabLayout tab_fragment_title;
    private ViewPager vp_fragment_pager;
    private FragmentStatePagerAdapter tabAdapter;

    private List<Fragment> list_fragment;
    private List<String> list_title;

    private ChatFragment chatFragment;
    private RankFragment rankFragment;
    private GuardFragment guardFragment;

    public LiveChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_live_chat, container, false);
        initControls(view);
        return view;
    }
    /**
     * 初始化各个控件
     * @param view
     */

    private void initControls(View view) {
        tab_fragment_title= (TabLayout) view.findViewById(R.id.tab_top);

        vp_fragment_pager= (ViewPager) view.findViewById(R.id.live_pager_vp);

        //初始化各fragment
        chatFragment=new ChatFragment();
        rankFragment=new RankFragment();
        guardFragment=new GuardFragment();

        //将fragment装进列表中
        list_fragment=new ArrayList<>();
        list_fragment.add(chatFragment);
        list_fragment.add(rankFragment);
        list_fragment.add(guardFragment);

        //将tab的名字加入列表
        list_title=new ArrayList<>();
        list_title.add("聊天");
        list_title.add("排行");
        list_title.add("守护");

        //设置TabLayout的模式
        tab_fragment_title.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_fragment_title.addTab(tab_fragment_title.newTab().setText(list_title.get(0)));
        tab_fragment_title.addTab(tab_fragment_title.newTab().setText(list_title.get(1)));
        tab_fragment_title.addTab(tab_fragment_title.newTab().setText(list_title.get(2)));

        tabAdapter=new TabAdapter(getActivity().getSupportFragmentManager(),list_fragment,list_title);

        //viewpager加载adapter
        vp_fragment_pager.setAdapter(tabAdapter);
        //TabLayout加载viewpager
        tab_fragment_title.setupWithViewPager(vp_fragment_pager);
    }
}
