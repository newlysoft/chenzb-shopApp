package com.example.administrator.myapp.base;

import android.app.Application;

import com.example.administrator.myapp.adrotatorcomponent.volley.RequestManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.yolanda.nohttp.NoHttp;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/10/18.
 */

public class BaseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

        //NoHttp
        NoHttp.initialize(this);
        //fresco
        Fresco.initialize(this);
        RequestManager.init(this);//Volley的初始化

       ZXingLibrary.initDisplayOpinion(this);//二维码扫描全局初始化
    }

}
