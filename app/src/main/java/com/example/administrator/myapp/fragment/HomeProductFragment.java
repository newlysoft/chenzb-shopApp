package com.example.administrator.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.HomeProductListAdapter;
import com.example.administrator.myapp.base.BaseFragment;
import com.example.administrator.myapp.divider.RecycleViewDivider;
import com.example.administrator.myapp.ui.PayActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 * 商城产品列表Fragment
 */

public class HomeProductFragment extends BaseFragment {


    private View view = null;//视图

    private static String PAGE_TAG = "page_tag";
    private int page = 0;
    private TextView tvText;
    private RecyclerView mRvProductlist = null;

    private List<String> stringList=null;


    /**
     * @param positon 位置
     * @return
     */
    public static HomeProductFragment getInstance(int positon) {

        HomeProductFragment mFragment = new HomeProductFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_TAG, positon);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt(PAGE_TAG, 0);
        }
    }

    /**
     * 创建视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home_product, container, false);
        tvText = (TextView) view.findViewById(R.id.tvText);
        mRvProductlist = (RecyclerView) view.findViewById(R.id.mRvHomeProduct);
        stringList=new ArrayList<>();
        return view;
    }

    /**
     * 当Act创建的时候
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        mRvProductlist.setHasFixedSize(true);
        mRvProductlist.setItemAnimator(new DefaultItemAnimator());
        mRvProductlist.setLayoutManager(gridLayoutManager);
        mRvProductlist.addItemDecoration(new RecycleViewDivider(context, OrientationHelper.HORIZONTAL));
        HomeProductListAdapter mAdapter=new HomeProductListAdapter(context);
        mRvProductlist.setAdapter(mAdapter);

        for (int i=0;i<100;i++){
            stringList.add("数据"+i);
        }
        mAdapter.setData(stringList);
        mAdapter.setOnItemClickListener(new HomeProductListAdapter.OnItemClickListener() {
            @Override
            public void onItemMethod() {


                Toast.makeText(context,"发起模拟支付",Toast.LENGTH_SHORT).show();
                //点击进入支付页面
                startActivity(new Intent(context, PayActivity.class));
            }
        });
    }

}
