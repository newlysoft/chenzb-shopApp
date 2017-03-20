package com.example.administrator.myapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapp.R;

public abstract class TestBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_base);
    }


    protected abstract void initData();
    protected abstract void initSubView();

}
