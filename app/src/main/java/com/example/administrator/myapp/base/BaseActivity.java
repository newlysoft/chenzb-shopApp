package com.example.administrator.myapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.imp.Constant;
import com.example.administrator.myapp.receiver.UserLoginReceiver;
import com.example.administrator.myapp.utils.AndroidShare;

public class BaseActivity extends AppCompatActivity{

    public Context context=null;

    public UserLoginReceiver userLoginReceiver;

    public boolean isRegister=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context=this;
        register();
    }

    /**
     * 开启Activity
     */
    public void openActivity(Context context,Class< ? extends Activity> activity){
        Intent intent=new Intent();
        intent.setClass(context,activity);
        startActivity(intent);
    }

    public void register() {
        if (isRegister==false){
            userLoginReceiver = new UserLoginReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constant.TEST_ACTION);
            registerReceiver(userLoginReceiver, intentFilter);
            isRegister=true;
        }

    }

    public void unRegister(){
        if (userLoginReceiver!=null&&isRegister){
            unregisterReceiver(userLoginReceiver);
            isRegister=false;
        }
    }

    public void oneKeyShare(Context context){
        AndroidShare androidShare=new AndroidShare(context,"分享内容");
        androidShare.show();
    }
}
