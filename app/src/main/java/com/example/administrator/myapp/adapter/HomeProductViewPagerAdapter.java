package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myapp.fragment.HomeProductFragment;

/**
 * Created by Administrator on 2017/1/19.
 * Fragment适配器
 */

public class HomeProductViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles=null;
    private FragmentManager fm=null;
    private Context context=null;

    public HomeProductViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public HomeProductViewPagerAdapter(FragmentManager fm, Context context,String []titles) {
        super(fm);
        this.fm = fm;
        this.context = context;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment mFragment= HomeProductFragment.getInstance(position);
        return mFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
