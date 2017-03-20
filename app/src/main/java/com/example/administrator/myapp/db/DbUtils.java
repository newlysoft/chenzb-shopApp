package com.example.administrator.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.myapp.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 * 数据库工具类
 */

public class DbUtils  {

    public Context context=null;
    DbHelper dbHelper=null;
    SQLiteDatabase db=null;
    public DbUtils(Context context){
        this.context=context;
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    /**
     * 插入数据
     * @param user
     */
    public void insert(User user){
        String username=user.getUsername();
        String password=user.getPassword();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        db.insert("user","_id",contentValues);
    }

    /**
     * 查询方法验证登录
     * @param user
     * @return
     */
    public boolean query(User user){
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("user",null,null,null,null,null,null);

        List<User> users=new ArrayList<>();
        while (cursor.moveToNext()){
            String username=cursor.getString(cursor.getColumnIndex("username"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            User u=new User(username,password);
            u.setUsername(username);
            u.setPassword(password);
            Log.i("chenzb","user="+u.getUsername()+","+u.getPassword());
            users.add(u);
            if (user.getUsername().equals(username)&&user.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }
}
