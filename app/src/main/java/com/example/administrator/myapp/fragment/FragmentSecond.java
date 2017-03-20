package com.example.administrator.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.base.BaseFragment;

/**
 * Created by Administrator on 2017/2/11.
 * 第二个页面
 */

public class FragmentSecond extends BaseFragment {

    private View view=null;
    private TextView tvSecond=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_second,container,false);
        tvSecond= (TextView) view.findViewById(R.id.tvSecond);
        return view;
    }
}
