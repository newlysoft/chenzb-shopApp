package com.example.administrator.myapp.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.User;
import com.example.administrator.myapp.db.DbUtils;
import com.example.administrator.myapp.imp.Constant;
import com.example.administrator.myapp.receiver.UserLoginReceiver;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName = null;
    private EditText etPwd = null;
    private Switch mASwitch = null;

    private Intent intent = null;

    private Intent it=null;
    private UserLoginReceiver mUserLoginReceiver = null;


    private SharedPreferences sp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("user.txt",MODE_PRIVATE);
        //注册广播
        mUserLoginReceiver = new UserLoginReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.USER_LOGIN_ACTION);
        registerReceiver(mUserLoginReceiver, intentFilter);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        mASwitch = (Switch) findViewById(R.id.switchChecked);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//不可见
        mASwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnLogin:
                String userName = etUserName.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                intent= new Intent(this, HomeActivity.class);
                it=new Intent();
                DbUtils dbUtils = new DbUtils(getApplicationContext());
                User user = new User();
                user.setUsername(userName);
                user.setPassword(pwd);
                if (dbUtils.query(user)) {
                    Toast.makeText(getApplicationContext(), "登录成功" + userName, Toast.LENGTH_SHORT).show();
                    it.putExtra("username", userName);
                    it.setAction(Constant.USER_LOGIN_ACTION);
                    //用sp保存  用户名
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("username",userName);
                    editor.commit();//提交
                    sendBroadcast(it);//发送广播
                    startActivity(intent);
                    this.finish();
                } else {
                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnRegister:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 取消广播
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mUserLoginReceiver);
    }
}
