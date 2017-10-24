package com.example.administrator.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.AllOrderAdapter;
import com.example.administrator.myapp.base.BaseActivity;
import com.example.administrator.myapp.base.BaseFragment;
import com.example.administrator.myapp.bean.OrderBean;
import com.example.administrator.myapp.ui.PayActivity;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/2/9.
 * 订单页面的Fragment
 */

public class OrderFragment extends BaseFragment {

    private static OrderFragment orderFragment = null;
    private static final String PAGE_TAG = "page_tag";
    private int page;
    View view = null;
    private TextView tvData;

    private String data = "{\"data\":[{\"title\":\"夕夕九木旗舰店\",\"orderproduct\":{\"picUrl\":\"https://img.alicdn.com/bao/uploaded/i2/2394200336/TB2qr00aM1J.eBjy0FaXXaXeVXa_!!2394200336.jpg_80x80.jpghttps://img.alicdn.com/bao/uploaded/i2/2394200336/TB2qr00aM1J.eBjy0FaXXaXeVXa_!!2394200336.jpg_80x80.jpg\",\"productname\":\"白色衬衫\",\"price\":\"31.00\"}},\n" +
            "{\"title\":\"hgst梦实专卖店\",\"orderproduct\":{\"picUrl\":\"https://img.alicdn.com/bao/uploaded/i1/1777634143/TB23G5QazHz11Bjy0FpXXcNiVXa_!!1777634143.jpg_80x80.jpg\",\"productname\":\"hgst的1T7200转硬盘\",\"price\":\"372.00\"}}\n" +
            "]}";
    private LayoutInflater mLayoutInflater = null;//布局解析器
    private FrameLayout orderContainer = null;//帧布局
    private AllOrderAdapter mAdapter;

    /**
     * 初始化
     *
     * @param page
     * @return
     */
    public static OrderFragment getInstance(int page) {

        orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_TAG, page);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    /**
     * onCreate方法
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mLayoutInflater = inflater;
        view = inflater.inflate(R.layout.fragment_order_view, container, false);
        orderContainer = (FrameLayout) view.findViewById(R.id.orderContainer);
        tvData = (TextView) view.findViewById(R.id.tvData);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switch (page) {
            case 0:
               vpShowViewData();
                break;
            case 1:
                //挂载内容布局
               vpShowViewData();
                break;

            case 2:
               vpShowViewData();
                break;
            case 3:
                vpShowViewData();
                break;
            case 4:

                tvData.setVisibility(View.VISIBLE);
                tvData.setText(page + "待评价页面");
                break;
        }
    }

    /**
     * json解析
     *
     * @param data
     */
    private void parseJsonData(String data) {
        Gson gson = new Gson();
        OrderBean orderBean = gson.fromJson(data, OrderBean.class);
        //
        Log.i("chent", "orderBean=" + orderBean);
        mAdapter.setData(orderBean);
    }


    public void vpShowViewData(){
        View allOrderView = mLayoutInflater.inflate(R.layout.all_order_view, orderContainer, false);
        RecyclerView mOrderRecyclerView = (RecyclerView) allOrderView.findViewById(R.id.mOrderRecyclerView);
        mAdapter = new AllOrderAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mOrderRecyclerView.setLayoutManager(linearLayoutManager);
        mOrderRecyclerView.setAdapter(mAdapter);
        parseJsonData(data);
        orderContainer.removeAllViews();
        orderContainer.addView(allOrderView);
        //设置适配器
        mAdapter.setOnItemClickListener(new AllOrderAdapter.OrderOnItemClickListener() {
            @Override
            public void onItemSelected(OrderBean orderBean, int position) {

                //这里只是要传递数据
                if (context instanceof BaseActivity) {

                    //如果要传递什么数据到支付界面这里写要传入的界面的逻辑代码
                    BaseActivity baseActivity = (BaseActivity) context;
                    baseActivity.openActivity(context, PayActivity.class);
                }
            }
        });
    }
}
