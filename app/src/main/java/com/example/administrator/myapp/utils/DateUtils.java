package com.example.administrator.myapp.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/19.
 * 日期工具类
 */

public class DateUtils {


    public static final String TAG="tag";
    public static String showDate() {
        //日期类
        Date date = new Date();
        //String string = String.format("y-m-d:%tc", date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String string = simpleDateFormat.format(date);
        return string;
    }

    /**
     *
     * @return
     */
    public static String timeFormat(){
        Date date=new Date();
        long time=date.getTime();//获取系统的当前时间
        long currentTime=System.currentTimeMillis();//这个也是获取系统的当前时间
        if (time==currentTime){
            Log.i(TAG,"time==currentTime");
        }
        Log.i(TAG,""+time+"\n"+currentTime);
        Date d=new Date(time);
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
        String result=sdf.format(d);
        return "timeFormat:"+result;
    }
}
