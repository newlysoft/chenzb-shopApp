package com.example.administrator.myapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.base.BaseActivity;
import com.example.administrator.myapp.fragment.FragmentFour;
import com.example.administrator.myapp.fragment.FragmentOne;
import com.example.administrator.myapp.fragment.FragmentSecond;
import com.example.administrator.myapp.fragment.FragmentThird;

/**
 * FragmentTabHost界面
 */
public class HnActivity extends BaseActivity {

    private FragmentTabHost fragmentTabHost=null;
    private FrameLayout tablayout=null;
    private TabWidget tabs;

    private String tags[]=new String[]{"首页","分类","购物车","我的"};
    private Class<?> []fragments={FragmentOne.class,FragmentSecond.class,FragmentThird.class,FragmentFour.class};
    //这个是图片的id数组
    private int []resIds=new int[]{
            R.mipmap.product_list_top_grid_normal,
            R.mipmap.icon_shop_item_category_normal,
            R.mipmap.icon_shop_item_shopcart_normal,
            R.mipmap.icon_shop_item_mine_normal
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        context=this;
        tabs=(TabWidget) findViewById(android.R.id.tabs);
        tablayout= (FrameLayout) findViewById(android.R.id.tabcontent);
        fragmentTabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        for (int i=0;i<fragments.length;i++){
            View tabView=getLayoutInflater().inflate(R.layout.tab_layout,null,false);
            ImageView imageView= (ImageView) tabView.findViewById(R.id.tab_icon);
            TextView tab_tag= (TextView) tabView.findViewById(R.id.tab_tag);
            imageView.setImageResource(resIds[i]);
            tab_tag.setText(tags[i]);
            TabHost.TabSpec tabSpec=fragmentTabHost.newTabSpec(tags[i]);
            tabSpec.setIndicator(tabView);
            fragmentTabHost.addTab(tabSpec,fragments[i],null);
        }


        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

               // Toast.makeText(context,tabId+"被选中了",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
