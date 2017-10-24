package com.example.administrator.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.TabHostHomeAdapter;
import com.example.administrator.myapp.adrotatorcomponent.view.Advertisements;
import com.example.administrator.myapp.base.BaseFragment;
import com.example.administrator.myapp.bean.TabHostHomeDataBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public class FragmentOne extends BaseFragment {

    private TextView textView = null;
    private View view = null;
    private LinearLayout llAdvertiseBoard;
    private JSONArray advertiseArray;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        llAdvertiseBoard = (LinearLayout) view.findViewById(R.id.llAdvertiseBoard);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        advertiseArray = new JSONArray();
        try {
            //json对象获取图片地址
            JSONObject head_img0 = new JSONObject();
            head_img0.put("head_img", "http://pic.nipic.com/2008-08-12/200881211331729_2.jpg");
            JSONObject head_img1 = new JSONObject();
            head_img1.put("head_img", "http://pic1.ooopic.com/uploadfilepic/sheji/2010-01-12/OOOPIC_1982zpwang407_20100112ae3851a13c83b1c4.jpg");
            JSONObject head_img2 = new JSONObject();
            head_img2.put("head_img", "http://pic1.ooopic.com/uploadfilepic/sheji/2009-09-12/OOOPIC_wenneng837_200909122b2c8368339dd52a.jpg");
            JSONObject head_img3 = new JSONObject();
            head_img3.put("head_img", "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1429108038,1536204131&fm=58");
            //第二步 json数组获取图片对象
            advertiseArray.put(head_img0);
            advertiseArray.put(head_img1);
            advertiseArray.put(head_img2);
            advertiseArray.put(head_img3);
            //第三步骤 讲json数组转化成字符串
            String jsongArrayStr = advertiseArray.toString();
            //广告图片轮播类
            Advertisements advertisements = new Advertisements(context, true, inflater, 3000);
            View dotView = advertisements.initView(advertiseArray);//
            //将做好的图片视图放到容器中去
            llAdvertiseBoard.addView(dotView);//添加视图
            Log.i("ct", jsongArrayStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<TabHostHomeDataBean> tabHostHomeDataBeanList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            TabHostHomeDataBean tabHostHomeDataBean = new TabHostHomeDataBean();
            tabHostHomeDataBean.imgId = "";
            tabHostHomeDataBean.text = "数据" + i;
            tabHostHomeDataBeanList.add(tabHostHomeDataBean);
        }
        TabHostHomeAdapter tabHostHomeAdapter = new TabHostHomeAdapter(context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(tabHostHomeAdapter);
        tabHostHomeAdapter.setData(tabHostHomeDataBeanList);

    }
}
