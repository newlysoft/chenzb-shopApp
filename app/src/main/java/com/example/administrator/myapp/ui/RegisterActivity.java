package com.example.administrator.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.User;
import com.example.administrator.myapp.db.DbUtils;

/**
 * 用户注册
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName = null;
    private EditText etPwd = null;
    private EditText etConfirmPwd = null;
    private Button btnRegister = null;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        etConfirmPwd = (EditText) findViewById(R.id.etConfirmPwd);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:

                //获取密码  以及确认密码
                final String userName = etUserName.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                String confirmPwd = etConfirmPwd.getText().toString().trim();
                if (TextUtils.isEmpty(userName) | TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd)) {
                    Toast.makeText(getApplicationContext(), "用户名或者密码为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    if (pwd.equals(confirmPwd)) {
                        //操作数据库
                        DbUtils dbUtils = new DbUtils(getApplicationContext());
                        User user = new User();
                        user.setUsername(userName);
                        user.setPassword(pwd);
                        dbUtils.insert(user);
                        //跳转到主页面
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                        dbUtils.query(user);

                    } else {
                        //注册失败
                        Toast.makeText(getApplicationContext(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }

                }


                break;
        }
    }
}
