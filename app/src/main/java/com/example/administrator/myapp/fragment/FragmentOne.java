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
 */

public class FragmentOne extends BaseFragment {

    private TextView textView=null;
    private View view=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_one,container,false);
        textView= (TextView) view.findViewById(R.id.tvone);
        return view;
    }
}
