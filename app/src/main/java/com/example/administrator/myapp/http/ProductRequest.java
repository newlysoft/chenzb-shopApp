package com.example.administrator.myapp.http;

import com.example.administrator.myapp.imp.FailuredListener;
import com.example.administrator.myapp.imp.SuccessListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/10/24.
 * 产品请求类
 */

public class ProductRequest {


    //2个回调接口作为参数
    public static void getProductData(String url,SuccessListener successListener, FailuredListener failuredListener) {
        assert (successListener != null);
        assert (failuredListener != null);

        //发送AsyncHttpClient
        requestHttpPost(url,null,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    /**
     * 使用AsyncHttpClient
     *
     * @param url
     * @param params
     */
    public static void requestHttpPost(String url, RequestParams params, JsonHttpResponseHandler jsonHttpResponseHandler) {

        try {
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            if (params == null) {
                params = new RequestParams();
            }
            String headKey = "headKey";
            String headValue = "headValue";
            asyncHttpClient.addHeader(headKey, headValue);
            asyncHttpClient.post(url, params, jsonHttpResponseHandler);
        } catch (Exception e) {
            jsonHttpResponseHandler.onFailure(-1, new Header[]{}, new Throwable(e.getMessage()), new JSONObject());
        }
    }


}
