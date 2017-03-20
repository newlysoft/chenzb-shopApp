package com.example.administrator.myapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/2/12.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static String DB_NAME="sql.db";
    private static int DB_VERSION=1;
    public DbHelper(Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists user(_id integer primary key autoincrement,username varchar(30),password varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
