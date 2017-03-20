package com.example.administrator.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.myapp.imp.Constant;

public class UserLoginReceiver extends BroadcastReceiver {

    private static String username;
    private static String action;
    private static String testContent;

    public UserLoginReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("chent", "onReceiver");
        action = intent.getAction();
        if (action.equals(Constant.USER_LOGIN_ACTION)) {
            username = intent.getStringExtra("username");
            Toast.makeText(context, "广播" + username, Toast.LENGTH_SHORT).show();
        } else if (action.equals(Constant.TEST_ACTION)) {
            testContent = intent.getStringExtra("name");
        }
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUsername() {
        return username;
    }

    public static String getTestContent() {
        return testContent;
    }
}
