package com.example.administrator.myapp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.CategoryVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 这个是分类的item的Activity
 */
public class CategoryItemActivity extends AppCompatActivity {


    private TabLayout mTabs = null;
    private ViewPager mViewPager = null;

    private ImageView ivReturn=null;
    private String titles[] = new String[]{"为你推荐", "女装", "男装", "鞋靴", "箱包"};


    private List<String> stringList=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);
        ivReturn= (ImageView) findViewById(R.id.ivReturn);
        mTabs = (TabLayout) this.findViewById(R.id.mTabs);
        mViewPager = (ViewPager) this.findViewById(R.id.mViewPager);

        stringList=new ArrayList<>();
        for (int i=0;i<titles.length;i++){
            stringList.add(titles[i]);
        }
        CategoryVpAdapter mCategoryVpAdapter=new CategoryVpAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mCategoryVpAdapter);
        mCategoryVpAdapter.setData(stringList);
        mTabs.setupWithViewPager(mViewPager);
        //返回图片的点击事件
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });
    }

}
