package com.example.administrator.myapp.utils;

/**
 * Created by Administrator on 2016/12/19.
 * 字符串类的工具类
 */

public class StringUtils {

    /**
     * 这个方法用来连接2个字符串类
     * @param str1
     * @param str2
     * @return
     */
    public static String connectStr(String str1,String str2){

        String resultStr=str1.concat(str2);
        return resultStr;
    }
}
