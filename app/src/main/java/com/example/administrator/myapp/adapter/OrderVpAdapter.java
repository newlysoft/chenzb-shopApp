package com.example.administrator.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myapp.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class OrderVpAdapter extends FragmentPagerAdapter {

    private List<String> stringList=null;
    private FragmentManager fm=null;
    public OrderVpAdapter(FragmentManager fm) {
        super(fm);
        this.fm=fm;
        stringList=new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return OrderFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        if (stringList.size()==0){
            return 0;
        }
        return stringList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }

    //刷新数据
    public void setData(List<String> stringList){
        this.stringList=stringList;
        this.notifyDataSetChanged();
    }

}
