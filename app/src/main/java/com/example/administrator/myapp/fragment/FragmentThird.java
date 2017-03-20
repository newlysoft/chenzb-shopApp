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
 * 第三个页面
 */

public class FragmentThird extends BaseFragment {

    private View view=null;
    private TextView tvThird=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_third,container,false);
        tvThird= (TextView) view.findViewById(R.id.tvThird);
        return view;
    }
}
