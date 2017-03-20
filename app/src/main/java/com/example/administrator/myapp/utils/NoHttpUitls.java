package com.example.administrator.myapp.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.administrator.myapp.adapter.MrvAdapter;
import com.example.administrator.myapp.bean.EatBean;
import com.example.administrator.myapp.divider.RecycleViewDivider;
import com.example.administrator.myapp.imp.Constant;
import com.example.administrator.myapp.ui.DetailActivity;
import com.google.gson.Gson;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/18.
 */

public class NoHttpUitls {


    public RecyclerView mRecyclerView;
    public Context context;
    public MaterialRefreshLayout materialRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private AppCompatTextView tvNomore;
    private EatBean eatBean;

    /**
     * NoHttp的get方式
     * @param context
     * @param httpUrl
     * @param mRv
     * @param materialRefreshLayout
     */
    public void byNoHttpGet(final Context context,String httpUrl, RecyclerView mRv, MaterialRefreshLayout materialRefreshLayout, AppCompatTextView tvNomore) {
        this.context=context;
        mRecyclerView = mRv;
        this.tvNomore=tvNomore;
        this.materialRefreshLayout=materialRefreshLayout;
        // String 请求对象
        Request<String> request = NoHttp.createStringRequest(httpUrl, RequestMethod.GET);
        Log.i("TAG", request.getContentType());
        request.add("name", "宫保鸡丁");
        request.addHeader("apikey", Constant.apikey);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        // 发起请求
        requestQueue.add(1, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

                ProgressBar progressBar=new ProgressBar(context);

                Log.i("TAG", "onStart");
            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                String result = response.get();
                try {
                    JSONObject jsonObject=new JSONObject(response.get());
                    if (jsonObject.has("count")){
                        Log.i("chent","确实有这个字段");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("TAG", "Nohttp请求成功：" + response.get());
                //gson解析
                Gson gson = new Gson();
                eatBean = gson.fromJson(response.get(), EatBean.class);
                if (eatBean==null){
                    Toast.makeText(context,"没有加载到网络数据！",Toast.LENGTH_SHORT).show();
                    return;
                }
                byRecycleView(eatBean);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.i("TAG", "Nohttp请求失败：" + response.get());
                Toast.makeText(context,"Nohttp加载数据失败，请检查网络状态",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {
                Log.i("TAG", "Nohttp请求finish：" + what);
            }
        });

    }

    /**
     * 使用RecycleView显示列表
     */
    private void byRecycleView(EatBean tngou) {
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
        MrvAdapter mrvAdapter = new MrvAdapter(context,tngou);
        mRecyclerView.setAdapter(mrvAdapter);
        mrvAdapter.setOnItemClicklistener(new MrvAdapter.OnItemclickListener() {
            @Override
            public void onItemselected(EatBean eatBean, int position) {
                Toast.makeText(context, eatBean.getTngou().get(position).getFood(), Toast.LENGTH_SHORT).show();
//                View view=mRecyclerView.getChildAt(position);
//               MrvAdapter.ViewHolder viewHolder= (MrvAdapter.ViewHolder) mRecyclerView.getChildViewHolder(view);
//                viewHolder.itemContainer.setBackgroundColor(Color.RED);
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("message",eatBean.getTngou().get(position).getMessage());
                context.startActivity(intent);
            }
        });

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {


                Log.i("ct", "onRresh()");
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int firstPosition=linearLayoutManager.findFirstVisibleItemPosition();
                        Log.i("ct",firstPosition+"");
                        if (linearLayoutManager.findFirstVisibleItemPosition()==0){
                            Toast.makeText(context,"下拉正在刷新",Toast.LENGTH_SHORT).show();
                        }
                        //  mAdapter.setData(strings);
                        //  mrvAdapter.setData(strings);
                        // mAdapter.notifyDataSetChanged();
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                //上拉加载更多
                int firstVisivbleItem=linearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
                int visibleCount=linearLayoutManager.getChildCount();
                int totalCount=linearLayoutManager.getItemCount();
                if (firstVisivbleItem+visibleCount>=totalCount){
                    materialRefreshLayout.setLoadMore(true);
                    tvNomore.setVisibility(View.VISIBLE);
                    Log.i("ct", "onRefreshLoadMore");
                }
                materialRefreshLayout.finishRefreshLoadMore();
                try {
                    Thread.sleep(2000);
                    tvNomore.setVisibility(View.GONE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onfinish() {
                Log.i("chent","刷新完成");
                tvNomore.setVisibility(View.GONE);
            }
        });
        // refresh complete
        // materialRefreshLayout.finishRefresh();
        // load more refresh complete
        // materialRefreshLayout.finishRefreshLoadMore();
    }
}
