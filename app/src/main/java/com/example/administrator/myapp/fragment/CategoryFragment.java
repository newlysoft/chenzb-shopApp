package com.example.administrator.myapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.CategoryLeftMrvAdapter;
import com.example.administrator.myapp.adapter.CategoryRightContentAdapter;
import com.example.administrator.myapp.base.BaseFragment;
import com.example.administrator.myapp.bean.Category;
import com.example.administrator.myapp.divider.RecycleViewDivider;
import com.example.administrator.myapp.imp.Constant;
import com.example.administrator.myapp.imp.HttpUrlCacheData;
import com.example.administrator.myapp.utils.FileUtilsMethod;
import com.example.administrator.myapp.utils.HttpNetUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.example.administrator.myapp.R.id.mRecyclerView;

/**
 * Created by Administrator on 2016/10/18.
 * 产品分类Fragment
 */

public class CategoryFragment extends BaseFragment {

    private View view;
    private RecyclerView mLeftRecyclerView;//左边
    private RecyclerView mRightRecyclerView;//右边区域
    private CategoryLeftMrvAdapter mCategoryLeftMrvAdapter;//这个是左边的标题的适配器
    private CategoryRightContentAdapter mContentAdapter;


    private LinearLayoutManager mLinearLayoutManager;
    private String fileName="categoryData.txt";
    private String cacheData;
    private Category category;
    private List<Category.DataBean> dataBeanList=null;
    private List<Category.DataBean.ContentListBean> contentListBeans=null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        category=new Category();
        dataBeanList=new ArrayList<>();
        contentListBeans=new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ;
        view = inflater.inflate(R.layout.fragment_category,container , false);
        mLeftRecyclerView= (RecyclerView) view.findViewById(mRecyclerView);
        mRightRecyclerView= (RecyclerView) view.findViewById(R.id.mContentView);
        return view;
    }

    /**
     * 这个方法里面加载数据
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * 思路分析：
         * 第一步：拿到左边RecycleView的数据做显示
         * 第二步：左边的一个RecycleView的一个监听：
         *  分析：个人觉得2种情况：
         *  第一种情况： 如果左右两边各自为一个Fragment进行通讯的话
         *  第二种情况直接在当前的ui里面 用2个RecycleView即可 ===这里用 的是这种情况
         *  下面开始写代码
         */
        //左边listView做显示数据
        //发送http请求获取所有的数据

        //第一步 首先是我不管判断集合适配器有不有数据 我先做显示再说
        initLeftListView();
        initRightListView();

        //第二步：判断有不有网络====有不有网络 后面再调用mAdapter.setData(list);进行一个数据的刷新===这是我们的一个逻辑
        if (HttpNetUtils.isNetworkConnected(context)){
            //有网进行加载网络数据
            loadAllData();
        }else{
            //这里也是从缓存读取数据
            cacheData=FileUtilsMethod.readFromFile(context,fileName);
            if (!TextUtils.isEmpty(cacheData)){
                //不为空拿着缓存的字符串进行json解析
                parseJsonData(cacheData);
            }else{
                //如果缓存的数据为空===那就从本地拿到缓存的string字符串
                parseJsonData(HttpUrlCacheData.CATEGORY_CACHE_DATA);
                Toast.makeText(context,"没网第一次从本地代码里面拿到的json字符串",Toast.LENGTH_SHORT).show();
            }
        }


       //mContentAdapter.setData();//这数据我应该是在加载网络数据的时候我就刷新完 了因为我一进去肯定就要有数据啊对吧
        //左边标题适配器的点击事件
        mCategoryLeftMrvAdapter.setOnItemListener(new CategoryLeftMrvAdapter.OnItemClickListener() {
            @Override
            public void onItemSelected(Category category, List<Category.DataBean> databeanList, List<Category.DataBean.ContentListBean> contentListBeans,int position) {
                //这个拿到就是最外面的category实体类
                /**
                 * 点击后的逻辑
                 * @1 右边listView做显示
                 * 当用户不点击的时候 右边不可能为空吧 所以啊  这里是刷新数据 个人觉得
                 */

              //  mContentRecyclerView.setAdapter();
                CategoryFragment.this.category=category;
                CategoryFragment.this.dataBeanList=databeanList;
                CategoryFragment.this.contentListBeans=contentListBeans;
                Toast.makeText(getActivity(),"position"+position,Toast.LENGTH_SHORT).show();
                /**
                 *  右边内容区域做显示
                 */
                mContentAdapter.setData(databeanList.get(position));

            }
        });
    }

    /**
     * 右边listView的初始化
     */
    private void initRightListView() {
        mLinearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        mRightRecyclerView.setLayoutFrozen(true);
        mRightRecyclerView.setHasFixedSize(true);
        mRightRecyclerView.setLayoutManager(mLinearLayoutManager);
        mContentAdapter = new CategoryRightContentAdapter(context);
        mRightRecyclerView.addItemDecoration(new RecycleViewDivider(context,OrientationHelper.VERTICAL));
        mRightRecyclerView.setAdapter(mContentAdapter);
    }

    /**
     * 加载所有的数据
     */
    private void loadAllData() {

        OkHttpUtils.get()
                .url(Constant.CATEGORY_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                            //如果错误吧  从缓存读取数据
                        //封装好的工具类直接拿到数据====假设前面没有做保存 进来肯定是报空指针异常
                        cacheData = FileUtilsMethod.readFromFile(context,fileName);
                        //做判断
                        if (TextUtils.isEmpty(cacheData)){
                           //
                            parseJsonData(HttpUrlCacheData.CATEGORY_CACHE_DATA);
                        }else{
                            //如果数据不为空 就再调用数据进行json解析
                            parseJsonData(cacheData);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG,"categoryResponse="+response);
                        FileUtilsMethod.saveDataToFile(context,response, fileName);
                        parseJsonData(response);
                    }
                });
    }

    /**
     * json数据解析===一般我用的gson或者用原生的josnObject跟jsonArray
     * @param response
     */
    private void parseJsonData(String response) {

        Gson gson=new Gson();
        Category category=gson.fromJson(response, Category.class);
        Log.i(TAG,"categoryBean="+category.toString());
        //首先这里我的思路是调用适配器的里面的方法刷新适配的数据
        mCategoryLeftMrvAdapter.setData(category);//不管是网络数据还是  缓存数据 我度要在这里进行数据刷新==从而显示右边的列表

        dataBeanList=category.getData();//
        mContentAdapter.setData(dataBeanList.get(0));//右边内容区域数据做显示===默认加载第0条数据
    }

    /***
     * 先从后台服务器获取整个的接口 也就是--javabean---list集合---Adapter===RecycleView(或者listviwe\ViewPager...)
     */
    private void initLeftListView() {

        //首先左边是一个列表
        mLinearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        mLeftRecyclerView.setLayoutFrozen(true);
        mLeftRecyclerView.setHasFixedSize(true);
        mLeftRecyclerView.setLayoutManager(mLinearLayoutManager);
        mCategoryLeftMrvAdapter =new CategoryLeftMrvAdapter(context);
        mLeftRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mLeftRecyclerView.addItemDecoration(new RecycleViewDivider(context, OrientationHelper.HORIZONTAL));
        mLeftRecyclerView.setAdapter(mCategoryLeftMrvAdapter);
    }
}
