package com.example.administrator.myapp.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.utils.StringUtils;

/**
 * 这个是字符串操作的Activity
 */
public class StringUtilsActivity extends AppCompatActivity {

    private TextView tvResult;

    LayoutInflater mInflater;
    private AlertDialog.Builder builder;
    private Dialog dialog;
    private EditText etStr1;
    private EditText etStr2;
    private View stringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_utils);
        tvResult= (TextView) findViewById(R.id.tvResult);
        mInflater=getLayoutInflater();
        stringView = mInflater.inflate(R.layout.string_add,null,false);
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出对话框对吧
                Toast.makeText(getApplicationContext(),"点击了",Toast.LENGTH_SHORT).show();
                if (dialog == null) {
                    builder = new AlertDialog.Builder(StringUtilsActivity.this);
                    builder.setView(stringView);
                    etStr1 = (EditText) stringView.findViewById(R.id.etStr1);
                    etStr2 = (EditText) stringView.findViewById(R.id.etStr2);
                    final Button btnAdd= (Button) stringView.findViewById(R.id.btnAdd);
                    btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String str1=etStr1.getText().toString().trim();
                            String str2=etStr1.getText().toString().trim();
                            if (TextUtils.isEmpty(str1)||TextUtils.isEmpty(str2)){
                                Toast.makeText(getApplicationContext(),"输入框的内容为空了",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            //
                            dialog.dismiss();
                            //dialog.setCancelable(true);
                            tvResult.setText(StringUtils.connectStr(str1,str2));
                        }
                    });
                    dialog=builder.create();
                }
                dialog.show();
            }
        });

    }
}
