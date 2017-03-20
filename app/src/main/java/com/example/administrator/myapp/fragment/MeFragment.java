package com.example.administrator.myapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.ProductAdapter;
import com.example.administrator.myapp.base.BaseFragment;
import com.example.administrator.myapp.bean.ProductBean;
import com.example.administrator.myapp.divider.RecycleViewDivider;
import com.example.administrator.myapp.imp.Constant;
import com.example.administrator.myapp.imp.HttpUrlCacheData;
import com.example.administrator.myapp.receiver.UserLoginReceiver;
import com.example.administrator.myapp.ui.LoginActivity;
import com.example.administrator.myapp.ui.OrderActivity;
import com.example.administrator.myapp.utils.FileUtilsMethod;
import com.example.administrator.myapp.utils.HttpNetUtils;
import com.example.administrator.myapp.utils.RoundImageView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/18.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    private RecyclerView mRecyclerView=null;
    private ProductAdapter mProductAdapter;

    private String fileName="product.txt";

    private RelativeLayout orderForm=null;
    private RoundImageView headIcon;
    private Intent intent;

    private LinearLayout waitPaylayout=null;
    private LinearLayout waitsendlayout=null;
    private LinearLayout waitReceiverlayout=null;
    private LinearLayout waitThinklayout=null;
    private LinearLayout returnProductlayout=null;

    private SharedPreferences sp=null;
    private SharedPreferences spUser=null;
    private TextView tvUserName=null;
    private String username;
    private String cacheData;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_me,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.mRecyclerView);
        tvUserName= (TextView) view.findViewById(R.id.tvUserName);
        headIcon= (RoundImageView) view.findViewById(R.id.headIcon);
        orderForm= (RelativeLayout) view.findViewById(R.id.orderForm);
        waitPaylayout= (LinearLayout) view.findViewById(R.id.waitPaylayout);
        waitsendlayout= (LinearLayout) view.findViewById(R.id.sendlayout);
        waitReceiverlayout= (LinearLayout) view.findViewById(R.id.waitReceiverlayout);
        waitThinklayout= (LinearLayout) view.findViewById(R.id.thinklayout);
        returnProductlayout= (LinearLayout) view.findViewById(R.id.returnProductlayout);
        sp=context.getSharedPreferences("order.txt",Context.MODE_PRIVATE);
        spUser=context.getSharedPreferences("user.txt",Context.MODE_APPEND);
        Log.i("chent","username="+UserLoginReceiver.getUsername());
        if (!TextUtils.isEmpty(UserLoginReceiver.getUsername())){
            tvUserName.setText(UserLoginReceiver.getUsername());
        }
        username = spUser.getString("username",null);
        if (!TextUtils.isEmpty(username)){
            tvUserName.setText(username);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        headIcon.setOnClickListener(this);//用户头像点击事件
        orderForm.setOnClickListener(this);//用户头像点击事件
        waitPaylayout.setOnClickListener(this);//用户头像点击事件
        waitsendlayout.setOnClickListener(this);
        waitReceiverlayout.setOnClickListener(this);//用户头像点击事件
        waitThinklayout.setOnClickListener(this);//用户头像点击事件
        returnProductlayout.setOnClickListener(this);//用户头像点击事件


        /**
         * 下面可能喜欢的显示列表的数据操作
         */
        mProductAdapter = new ProductAdapter(context);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setLayoutFrozen(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mProductAdapter);
        //设置适配器的数据
        if (HttpNetUtils.isNetworkConnected(context)){
            OkHttpUtils.get()
                    .url(Constant.PRODUCT_URL)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.i(TAG, "response==error");
                            Toast.makeText(getActivity(), "http error", Toast.LENGTH_SHORT).show();
                            //网络请求错误也是从缓存文件中读取数据
                            cacheData = FileUtilsMethod.readFromFile(context, fileName);
                            if (TextUtils.isEmpty(cacheData)) {
                                //如果说数据为空是吧====那我就直接拿到我接口定义好的缓存数据
                                parseJsonData(HttpUrlCacheData.PRODUCT_CACHE_DATA);
                            }else{
                                //json解析
                                parseJsonData(cacheData);
                            }
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            //进行json解析
                            //输出字符串进行解析
                            Log.i(TAG, "response==success=" + response);
                            //对json字符串数据做缓存、
                            FileUtilsMethod.saveDataToFile(context, response, fileName);
                            parseJsonData(response);
                        }
                    });
        }else{
            //没有网络
            /**
             * 2种思路 首先从服务器的缓存文件去获取数据
             * 然后===
             */
            cacheData=FileUtilsMethod.readFromFile(context,fileName);
            //如果从缓存文件拿到的数据为空====我就从本地的接口里面拿数据
            if (TextUtils.isEmpty(cacheData)){
                parseJsonData(HttpUrlCacheData.PRODUCT_CACHE_DATA);
            }else{
                //不为空
                parseJsonData(cacheData);
            }
        }
    }

    private void parseJsonData(String response) {

        Gson gson=new Gson();
        ProductBean product=gson.fromJson(response,ProductBean.class);
        mProductAdapter.setData(product);
    }

    @Override
    public void onClick(View v) {

        SharedPreferences.Editor editor=sp.edit();
        switch (v.getId()){
            case R.id.headIcon://头像的点击事件
                intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                break;
            case R.id.orderForm://全部订单
                intent = new Intent(context,OrderActivity.class);
                context.startActivity(intent);
                break;
            case R.id.waitPaylayout://待付款
                intent=new Intent(context,OrderActivity.class);
                editor.putString("order","待付款");
                editor.commit();
                startActivity(intent);

                break;
            case R.id.sendlayout:
                intent=new Intent(context,OrderActivity.class);
                editor.putString("order","待发货");
                editor.commit();
                startActivity(intent);
                break;
            case R.id.waitReceiverlayout://待收货
                intent=new Intent(context,OrderActivity.class);
                editor.putString("order","待收货");
                editor.commit();
                startActivity(intent);
                break;
            case R.id.thinklayout://待评价
                intent=new Intent(context,OrderActivity.class);
                editor.putString("order","待评价");
                editor.commit();
                startActivity(intent);
                break;
//            case R.id.returnProductlayout://待退货
//                intent=new Intent(context,OrderActivity.class);
//                editor.putString("order","待退货");
//                editor.commit();
//                startActivity(intent);
//
//                break;

        }
    }


}
