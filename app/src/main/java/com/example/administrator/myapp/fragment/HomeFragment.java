package com.example.administrator.myapp.fragment;

import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.ProductAdapter;
import com.example.administrator.myapp.adrotatorcomponent.view.Advertisements;
import com.example.administrator.myapp.base.BaseFragment;
import com.example.administrator.myapp.bean.ProductBean;
import com.example.administrator.myapp.divider.RecycleViewDivider;
import com.example.administrator.myapp.imp.Constant;
import com.example.administrator.myapp.imp.HttpUrlCacheData;
import com.example.administrator.myapp.ui.HomeActivity;
import com.example.administrator.myapp.ui.HomeProductListActivity;
import com.example.administrator.myapp.ui.OrderActivity;
import com.example.administrator.myapp.ui.ScanWebViewActivity;
import com.example.administrator.myapp.utils.FileUtilsMethod;
import com.example.administrator.myapp.utils.HttpNetUtils;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;

import static android.app.Activity.RESULT_OK;
import static com.example.administrator.myapp.utils.FileUtilsMethod.readFromFile;

/**
 * Created by Administrator on 2016/10/18.
 * 商城fragment
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private static final int REQUEST_CODE_PICK_CITY = 0;
    private static final int ORDER_REQUEST_CODE =1;
    private static final int REQUEST_CODE =0;
    private View view;

    View mHeaderView;//头部view
    RecyclerView mRecyclerView;
    View categoryLayout;
    View shopcartLayout;
    View orderLayout;
    View couponLayout;


    RelativeLayout homeTitleView;
    EditText searchText;
    LinearLayout mScrolllayout;    //轮播广告scrollLayout
    ViewGroup mPointerLayout; //指示点Layout
    //SPHomeCategoryAdapter mAdapter;

    //ImageView upLeftImgv;            //上-> 左
    TextView upRightScanView = null;
    ImageView upLeftImgv=null;
    ImageView upRightTopImgv;        //上-> 右 -> 上
    ImageView upRightBottomImgv;    //上-> 右 -> 下

    ImageView bottomLeftImgv;    //下-> 左
    ImageView bottomRightImgv;    //下-> 右


    private final static int SCANNIN_GREQUEST_CODE = 1;

    private LinearLayout llAdvertiseBoard;//
    private LayoutInflater inflater;
    private JSONArray advertiseArray;
    private ProductAdapter mAdapter;
    private String fileName = "product.txt";
    private String cacheData;

    //声明RecycleView的适配器 它要作为工具类的适配器传参数
    private TextView tvCity = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    // public AMapLocationListener mLocationListener = new AMapLocationListener();

    // AMapLocationListener mAMapLocationListener

    private String cityName=null;//城市名字
    private Intent intent=null; //意图

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     *
     * @param inflater 解析
     * @param container 容器
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initViews(inflater, container);
    }


    /**
     * 初始化view
     *
     * @return
     */
    private View initViews(LayoutInflater mInflater, ViewGroup container) {
        this.inflater = mInflater;
        //step1; 160dp height的viewGroup ====
        view = inflater.inflate(R.layout.fragment_home, container, false);
        //初始化定位
        mLocationClient = new AMapLocationClient(context.getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        //mLocationOption.setWifiActiveScan(false);
        mLocationOption.setWifiScan(false);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        //这个是最上面的那个布局
        homeTitleView = (RelativeLayout) view.findViewById(R.id.toprela);
        //这个是RecycleView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        //这个是fragment里面的RecycycleView待加的HeadView
        mHeaderView = inflater.inflate(R.layout.product_header_view, container, false);

        categoryLayout = mHeaderView.findViewById(R.id.home_menu_categroy_layout);//分类layout的点击事件
        shopcartLayout = mHeaderView.findViewById(R.id.home_menu_shopcart_layout);
        orderLayout = mHeaderView.findViewById(R.id.home_menu_order_layout);//订单详情页
        couponLayout = mHeaderView.findViewById(R.id.home_menu_coupon_layout);
        upLeftImgv= (ImageView) mHeaderView.findViewById(R.id.up_left_imgv);
        upRightTopImgv = (ImageView) mHeaderView.findViewById(R.id.up_right_top_imgv);
        upRightBottomImgv = (ImageView) mHeaderView.findViewById(R.id.up_right_bottom_imgv);
        bottomLeftImgv = (ImageView) mHeaderView.findViewById(R.id.bottom_left_imgv);
        bottomRightImgv = (ImageView) mHeaderView.findViewById(R.id.bottom_right_imgv);

        upRightScanView = (TextView) view.findViewById(R.id.image_right);
        homeTitleView.getBackground().setAlpha(0);
        tvCity = (TextView) view.findViewById(R.id.tvCity);
        searchText = (EditText) homeTitleView.findViewById(R.id.searchkey_edtv);//文本输入框
        searchText.setFocusable(false);
        searchText.setFocusableInTouchMode(false);
        //要在前面实例化一个适配器
        //设置添加头部视图

        mAdapter = new ProductAdapter(context);
        //实例化
        HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        //给RecycleView设置适配器
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认的动画
        mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
        RecyclerViewUtils.setHeaderView(mRecyclerView, mHeaderView);//
        //给RecycleView添加滑动方式
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        setOnClicklistener();
        // 设置listView header view : 广告轮播
        mScrolllayout = (LinearLayout) mHeaderView.findViewById(R.id.banner_slayout);
        autoImgPlay();//图片轮播
        //第一步判断是否有网络
        if (HttpNetUtils.isNetworkConnected(context)) {
            //发送http请求
            OkHttpUtils.get()
                    .url(Constant.PRODUCT_URL)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                            Toast.makeText(getActivity(), "http error", Toast.LENGTH_SHORT).show();
                            //网络请求错误也是从缓存文件中读取数据
                            cacheData = readFromFile(context, fileName);
                            if (!TextUtils.isEmpty(cacheData)) {
                                //json解析
                                parseJsonData(cacheData);
                            }else{
                                parseJsonData(HttpUrlCacheData.PRODUCT_CACHE_DATA);
                            }
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            //输出字符串进行解析
                            Log.i(TAG, "response=" + response);
                            //判断如果response====null
                            //对json字符串数据做缓存、
                            FileUtilsMethod.saveDataToFile(context, response, fileName);
                            parseJsonData(response);
                        }
                    });
        } else {
            Toast.makeText(context, R.string.http_net_toast, Toast.LENGTH_SHORT).show();
            //如果说没有网络从缓存文件中拿到数据
            cacheData = FileUtilsMethod.readFromFile(context, fileName);
            if (!TextUtils.isEmpty(cacheData)) {
                parseJsonData(cacheData);
            }else{
                parseJsonData(HttpUrlCacheData.PRODUCT_CACHE_DATA);
            }
        }
        return view;
    }


    /**
     * 设置点击事件
     */
    private void setOnClicklistener() {

        tvCity.setOnClickListener(this);//城市的点击事件
        upLeftImgv.setOnClickListener(this);
        upRightBottomImgv.setOnClickListener(this);
        upRightScanView.setOnClickListener(this);
        upRightTopImgv.setOnClickListener(this);
        bottomLeftImgv.setOnClickListener(this);
        bottomRightImgv.setOnClickListener(this);
        categoryLayout.setOnClickListener(this);//分类布局的点击事件
        shopcartLayout.setOnClickListener(this);//购物车的点击事件
        orderLayout.setOnClickListener(this);//订单页面点击事件
    }

    /**
     * json解析
     *
     * @param response 字符串数据
     */
    public void parseJsonData(String response) {
        Gson gson = new Gson();
        ProductBean productBean = gson.fromJson(response, ProductBean.class);//javabean数据
        Log.i(TAG, "productBean=" + productBean.toString());
        mAdapter.setData(productBean);//更新数据
    }

    private int getScrolledDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        View firstVisibleItem = mRecyclerView.getChildAt(0);
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        int itemHeight = firstVisibleItem.getHeight();
        int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibleItem);
        return (firstItemPosition + 1) * itemHeight - firstItemBottom;
    }

    /**
     * 扫描按钮监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_right://二维码扫描
                Log.i(TAG, "scanf");
                intent = new Intent(context, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.up_left_imgv:
            case R.id.up_right_top_imgv:
            case R.id.up_right_bottom_imgv:
            case R.id.bottom_left_imgv:
            case R.id.bottom_right_imgv:

                /**
                 * 进入产品列表界面
                 */
                intent = new Intent(context, HomeProductListActivity.class);
                startActivity(intent);
                break;
            case R.id.home_menu_categroy_layout://分类布局的点击事件
                //当这个布局点击的时候 有什么样的逻辑
                //切换到category布局
                if (context instanceof HomeActivity) {
                    HomeActivity mHomeActivity = (HomeActivity) context;
                    mHomeActivity.showFrag(new CategoryFragment());
                    mHomeActivity.mTabs.check(R.id.tabCategory);
                }
                break;
            case R.id.home_menu_shopcart_layout://购物车的点击事件
                if (context instanceof HomeActivity) {
                    HomeActivity mHomeActivity = (HomeActivity) context;
                    mHomeActivity.showFrag(new ShopCartFragment());
                    mHomeActivity.mTabs.check(R.id.tab_shopcart);
                }

                break;

            case R.id.home_menu_order_layout://订单详情页面
                intent =new Intent(context,OrderActivity.class);
                startActivity(intent);// 1
                break;
            case R.id.tvCity://进入美团外卖界面
                //启动
                startActivityForResult(new Intent(context, CityPickerActivity.class), REQUEST_CODE_PICK_CITY);
                break;
        }
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int scrollY = getScrolledDistance();
            if (scrollY == 0) {
                //int progress = (int) (new Float(dy) / new Float(lHeight) * 200);//255
                homeTitleView.getBackground().setAlpha(0);
                //homeTitleView.setAlpha(0);

            } else {
                homeTitleView.getBackground().setAlpha(255 - 55);
                //homeTitleView.setAlpha(0.9f);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("chent","onResult");
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                tvCity.setText(city);
            }
        }
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(context, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    //进行Activity的跳转
                    Intent intent=new Intent(context, ScanWebViewActivity.class);
                    intent.putExtra("resultUrl",result);
                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(context, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * 封装图片轮播
     */

    public void autoImgPlay() {
        try {
            advertiseArray = new JSONArray();
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
            mScrolllayout.removeAllViews();
            //将做好的图片视图放到容器中去
            mScrolllayout.addView(dotView);//添加视图
            Log.i("ct", jsongArrayStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    cityName = amapLocation.getCity();//城市信息
                    Log.i("chent", "city=" + cityName);
                    amapLocation.getDistrict();//城区信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    amapLocation.getAoiName();//获取当前定位点的AOI信息
                    amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    amapLocation.getFloor();//获取当前室内定位的楼层
                    //amapLocation.getGpsStatus();
                    amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    //获取定位时间l
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());
                    df.format(date);
                    tvCity.setText(cityName);
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }


        }
    };

    @Override
    public void onStop() {
        super.onStop();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}
