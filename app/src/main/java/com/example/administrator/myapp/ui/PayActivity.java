package com.example.administrator.myapp.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.PayAdapter;
import com.example.administrator.myapp.base.BaseActivity;
import com.example.administrator.myapp.bean.PayBean;
import com.example.administrator.myapp.divider.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {


    @InjectView(R.id.payRecyclerView)
    RecyclerView payRecyclerView;
    @InjectView(R.id.activity_pay)
    LinearLayout activityPay;
    @InjectView(R.id.tvPayMethod)
    TextView tvPayMethod;
    @InjectView(R.id.tvShowPay)
    TextView tvShowPay;

    private String payMethod[] = new String[]{"支付宝支付", "微信支付"};

    private List<PayBean> payBeanList = new ArrayList<>();



    private int ids[]=new int[]{
            R.drawable.pay_alipay,
            R.drawable.pay_wechat
    };
    public PayBean payBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.inject(this);
        context = this;

        for (int i = 0; i < payMethod.length; i++) {
            payBean = new PayBean();
            payBean.id=ids[i];
            payBean.payName=payMethod[i];
            payBeanList.add(payBean);
        }
        PayAdapter payAdapter = new PayAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        payRecyclerView.setLayoutManager(linearLayoutManager);
        payRecyclerView.setHasFixedSize(true);
        payRecyclerView.setItemAnimator(new DefaultItemAnimator());
        payRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
        payRecyclerView.setAdapter(payAdapter);

        //刷新数据
        payAdapter.addList(payBeanList);
        payAdapter.setOnPayItemClickListener(new PayAdapter.OnPayItemClickListener() {
            @Override
            public void onItemPaySelected(List<PayBean> payBeanList, int position) {
                tvShowPay.setText("你选择的支付方式是：" + payBeanList.get(position).payName);
            }
        });
    }

    @OnClick({R.id.tvPayMethod, R.id.tvShowPay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPayMethod:
                break;
            case R.id.tvShowPay:
                break;
        }
    }
}
