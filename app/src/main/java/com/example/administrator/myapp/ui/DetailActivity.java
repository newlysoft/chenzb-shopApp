package com.example.administrator.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.base.BaseActivity;

/**
 * 详情页面
 */
public class DetailActivity extends BaseActivity {

    private AppCompatTextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvContent= (AppCompatTextView) findViewById(R.id.tvContent);
        Intent intent=getIntent();
        String message=intent.getStringExtra("message");
        tvContent.setText(message);

    }
}
