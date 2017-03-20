package com.example.administrator.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myapp.fragment.CategoryTablayoutFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 * 分类的
 */

public class CategoryVpAdapter extends FragmentPagerAdapter {


    private List<String> stringList=null;

    public CategoryVpAdapter(FragmentManager fm) {
        super(fm);
        stringList=new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryTablayoutFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        if (stringList.size()==0){
            return 0;
        }
        return stringList.size();
    }

    public void setData(List<String> stringList){
        if (stringList==null){
            return;
        }
        this.stringList=stringList;

        this.notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }
}
