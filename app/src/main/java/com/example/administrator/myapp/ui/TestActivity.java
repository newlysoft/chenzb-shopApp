package com.example.administrator.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapp.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{


   private Button btnDate;
    private Intent intent;
    private Button btnStringUtils;
    private Button btnSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //获取日期按钮
        btnDate= (Button) findViewById(R.id.btnDate);
        btnStringUtils = (Button) findViewById(R.id.btnStringUtils);
        btnSelector = (Button) findViewById(R.id.btnSelector);
        btnDate.setOnClickListener(this);
        btnStringUtils.setOnClickListener(this);
        btnSelector.setOnClickListener(this);
    }

    /**
     * 按钮监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnDate:
                intent = new Intent(this,DateActivity.class);
                startActivity(intent);
                break;
            case R.id.btnStringUtils:
                intent = new Intent(this,StringUtilsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSelector:

                intent = new Intent(this,SelectorActivity.class);
                startActivity(intent);
                break;
        }
    }
}
