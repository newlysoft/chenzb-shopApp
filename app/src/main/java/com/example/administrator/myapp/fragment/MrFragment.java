package com.example.administrator.myapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.example.administrator.myapp.R;
import com.example.administrator.myapp.base.BaseFragment;

/**
 * Created by Administrator on 2016/10/18.
 * RecycleView的Fragment
 */

public class MrFragment extends BaseFragment {

    private View view;
    private MaterialRefreshLayout materialRefreshLayout;
    private AppCompatImageView imageView;
    private ListView mListView;
    private RecyclerView mRecyclerView;
    Handler handler = new Handler();
    private Runnable runnable;

    private int times;


    int imgsId[] = new int[]{R.mipmap.play1, R.mipmap.play2, R.mipmap.play3};

    //
    private String httpUrl = "http://apis.baidu.com/tngou/cook/name";
    private AppCompatTextView tvNomoreData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mrv, container, false);
        //初始化
//        mListView = (ListView) view.findViewById(R.id.mListView);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
//        imageView = (AppCompatImageView) view.findViewById(R.id.image);
//        tvNomoreData = (AppCompatTextView) view.findViewById(R.id.tvModeshow);
//        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /**
         * 这里是上面的图片轮播
         */
//        times = 0;
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (times == imgsId.length) {
//                    times = 0;
//                }
//                imageView.setImageResource(imgsId[times]);
//                Log.i("chent", "time=" + System.currentTimeMillis() + "我又走了");
//                handler.postDelayed(runnable, 2000);
//                times++;
//            }
//        };
//        handler.postDelayed(runnable, 2000);
//
//        /**
//         * 这里是下面的下拉刷新
//         */
//        NoHttpUitls noHttpUitls = new NoHttpUitls();
//        noHttpUitls.byNoHttpGet(context, httpUrl, mRecyclerView, materialRefreshLayout, tvNomoreData);

    }
}
