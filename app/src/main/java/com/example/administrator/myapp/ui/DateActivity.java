package com.example.administrator.myapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.utils.DateUtils;
import com.example.administrator.myapp.utils.StringUtils;

public class DateActivity extends AppCompatActivity {

    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        tvResult= (TextView) findViewById(R.id.tvResult);
        tvResult.setText(DateUtils.showDate());
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText(DateUtils.timeFormat());
                Toast.makeText(getApplicationContext(),"2个字符串拼接："+ StringUtils.connectStr("str1","str2"),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
