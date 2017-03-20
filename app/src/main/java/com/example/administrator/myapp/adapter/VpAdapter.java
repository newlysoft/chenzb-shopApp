package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */

public class VpAdapter extends FragmentPagerAdapter {

    Context context;
    List<Fragment> fragments;

    public VpAdapter(FragmentManager fm,Context context,List<Fragment> fragments) {
        super(fm);
        this.context=context;
        this.fragments=fragments;
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
