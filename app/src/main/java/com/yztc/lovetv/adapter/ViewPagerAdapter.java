package com.yztc.lovetv.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import java.util.List;

/**
 * Created by My on 2016/12/9.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments;
    List<String> mTabs;


    public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragments,List<String> tabs) {
        super(fm);
        this.mFragments = fragments;
        this.mTabs = tabs;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments!=null ? mFragments.get(position) : null;

    }

    @Override
    public int getCount() {
        Log.e("Tsag"," 00000"+mFragments.size());
        return mFragments!=null ? mFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs!=null ? mTabs.get(position) : null;
    }
}
