package com.example.administrator.myapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 接收广播Act
 */
public class TestReceiverActivity extends BaseActivity {

    @InjectView(R.id.tvReceiver)
    TextView tvReceiver;
    @InjectView(R.id.activity_test_receiver)
    RelativeLayout activityTestReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_receiver);
        ButterKnife.inject(this);
        //注册广播
        tvReceiver.setText(userLoginReceiver.getTestContent()+"\n再次点击返回上一个Activity");
    }



    @OnClick({R.id.tvReceiver, R.id.activity_test_receiver})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvReceiver:
                /**
                 * 按钮点击的时候
                 */
                //第一步做显示
                finish();

                break;
            case R.id.activity_test_receiver:
                break;
        }
    }


}
