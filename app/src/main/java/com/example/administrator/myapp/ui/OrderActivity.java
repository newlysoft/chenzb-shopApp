package com.example.administrator.myapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.OrderVpAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mIvReturn=null;
    private String tabTitles[]=new String[]{"全部","待付款","待发货","待收货","待评价"};

    private TabLayout mTabs=null;//
    private ViewPager mViewPager=null;
    private List<String> stringList;

    private SharedPreferences sp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        sp=getSharedPreferences("order.txt",MODE_PRIVATE);
        mIvReturn= (ImageView) findViewById(R.id.ivReturn);
        mViewPager= (ViewPager) findViewById(R.id.mViewPager);
        mTabs= (TabLayout) findViewById(R.id.mTabs);
        stringList=new ArrayList<>();//集合
        for (int i=0;i<tabTitles.length;i++){
            stringList.add(tabTitles[i]);
        }
        //订单适配器
        OrderVpAdapter mAdapter=new OrderVpAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);//适配器
        mAdapter.setData(stringList);//刷新数据
        mTabs.setupWithViewPager(mViewPager);//tablayout跟ViewPager的一个绑定
        //根据取值来搞页面
        String order=sp.getString("order",null);
        Log.i("chent","order="+order);
        shiftPage(order);
        mIvReturn.setOnClickListener(this);//点击事件
    }

    /**
     * 根据字符串动态切换页面
     * @param order
     */
    private void shiftPage(String order) {
        if (TextUtils.isEmpty(order)){
            mViewPager.setCurrentItem(0,false);
        }else{
            if (order.equals("待付款")){
                mViewPager.setCurrentItem(1,false);
            }else if (order.equals("待发货")){
                mViewPager.setCurrentItem(2,false);
            }else if (order.equals("待收货")){
                mViewPager.setCurrentItem(3,false);
            }else if (order.equals("待评价")){
                mViewPager.setCurrentItem(4,false);
            }
        }
    }

    /**
     * 当用户点击的是返回按钮
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ivReturn://返回按钮
                //设置结果码
                finish();//关闭掉
                break;
        }
    }

}
