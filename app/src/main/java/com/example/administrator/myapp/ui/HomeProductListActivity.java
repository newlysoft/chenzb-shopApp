package com.example.administrator.myapp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.HomeProductListAdapter;
import com.example.administrator.myapp.adapter.HomeProductViewPagerAdapter;
import com.example.administrator.myapp.divider.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品列表
 */
public class HomeProductListActivity extends AppCompatActivity {

    private TabLayout mTabs=null;
    private ViewPager mViewPager=null;

    String titles[]=new String[]{"综合","销量","价格"};
    private Button btnSelector=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_product_list);
        mTabs= (TabLayout) findViewById(R.id.mTabs);
        mViewPager= (ViewPager) findViewById(R.id.mViewPager);
        btnSelector= (Button) findViewById(R.id.btnSelector);
        HomeProductViewPagerAdapter mAdapter=new HomeProductViewPagerAdapter(getSupportFragmentManager(),this,titles);
        mViewPager.setAdapter(mAdapter);
        mTabs.setupWithViewPager(mViewPager);
        btnSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //点击谈出对话框
                boolean wrapInScrollView = true;
                final MaterialDialog dialog=new MaterialDialog.Builder(HomeProductListActivity.this)
                        .title("选择商品")
                        .customView(R.layout.dialog_custom_view, wrapInScrollView)
                        .show();
                View view=dialog.getCustomView();
                RecyclerView mRvDialogView= (RecyclerView) view.findViewById(R.id.mRvDialogView);
                //
                HomeProductListAdapter mAdapter=new HomeProductListAdapter(HomeProductListActivity.this);
                List<String> stringList=new ArrayList<String>();
                for (int i=0;i<100;i++){
                    stringList.add("苹果手机"+i);
                }
                GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeProductListActivity.this, 2);
                mRvDialogView.setHasFixedSize(true);
                mRvDialogView.setItemAnimator(new DefaultItemAnimator());
                mRvDialogView.setLayoutManager(gridLayoutManager);
                mRvDialogView.addItemDecoration(new RecycleViewDivider(HomeProductListActivity.this, OrientationHelper.HORIZONTAL));

                mRvDialogView.setLayoutManager(gridLayoutManager);
                mAdapter.setData(stringList);
                mRvDialogView.setAdapter(mAdapter);

                //设置适配器的监听事件
              mAdapter.setOnItemClickListener(new HomeProductListAdapter.OnItemClickListener() {
                  @Override
                  public void onItemMethod() {
                        dialog.dismiss();//关闭对话框
                      Toast.makeText(getApplicationContext(),"对话框关闭",Toast.LENGTH_SHORT).show();
                  }
              });
            }
        });
    }


}
