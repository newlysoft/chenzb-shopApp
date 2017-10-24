package com.example.administrator.myapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.base.BaseActivity;
import com.example.administrator.myapp.fragment.CategoryFragment;
import com.example.administrator.myapp.fragment.HomeFragment;
import com.example.administrator.myapp.fragment.MeFragment;
import com.example.administrator.myapp.fragment.MrFragment;
import com.example.administrator.myapp.fragment.ShopCartFragment;
import com.example.administrator.myapp.imp.Constant;

/**
 * 主页面
 */
public class HomeActivity extends BaseActivity {


    //string tag
    public static final String HOME_TAG = "home_tag";
    public static final String PRODUCT_TAG = "product_tag";
    public static final String CATEGORY_TAG = "category_tag";
    public static final String SHOPCART_TAG = "shopcart_tag";
    public static final String ME_TAG = "me_tag";
    //
//
//    public static final String SELECT_INDEX = "selectIndex";
//    public static final String CACHE_SELECT_INDEX = "cacheSelectIndex";
//
//    public HomeActivity mActivity;
//    public static final String TAG = "HomeActivity";
//    private NoScrollViewPager mViewPager;
//
//    private List<Fragment> fragments;//fragment集合
    public RadioGroup mTabs; //tab
    public RadioButton mTabHome;//首页
    private RadioButton mTabCategory;//分类
    private RadioButton mTabShop;//购物车
    private RadioButton mTabme;//我的
//
//
    private FragmentManager mFragmentManager;
    //    private FragmentTransaction mFragmentTransaction;
//
//
//    //声明5个碎片
    private MrFragment mMrFragment;
    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private ShopCartFragment mShopCartFragment;
    private MeFragment mMeFragment;

    //
//    public int mCurrentSelectIndex;
//    public static final int INDEX_HOME = 0;
//    public static final int INDEX_CATEGORY = 1;
//    public static final int INDEX_SHOPCART = 2;
//    public static final int INDEX_ME = 3;
//    //
//    AppCompatRadioButton mCurrRb;
//    AppCompatRadioButton mLastRb;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //step1:实例化要添加的fragment
        mMrFragment = new MrFragment();//mrvfragment=======
        mHomeFragment = new HomeFragment();//商城页面的Fragment
        mCategoryFragment = new CategoryFragment();//分类fragment
        mShopCartFragment = new ShopCartFragment();//购物车的fragment
        mMeFragment = new MeFragment();//这个是我的fragment
        mFragmentManager = getSupportFragmentManager();//管理器

        //step2 添加所有的Fragment
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();//开启事物
        mFragmentTransaction.add(R.id.mContainer, mHomeFragment, HOME_TAG);//添加商城
        mFragmentTransaction.add(R.id.mContainer, mCategoryFragment, CATEGORY_TAG);//添加分类
        mFragmentTransaction.add(R.id.mContainer, mShopCartFragment, SHOPCART_TAG);//添加购物车
        mFragmentTransaction.add(R.id.mContainer, mMeFragment, ME_TAG);//添加我的
        mFragmentTransaction.commit();
        //step3显示指定的fragment
        showFrag(mHomeFragment);//显示第一个fragment
        mTabs = (RadioGroup) findViewById(R.id.tabcontainer);
        mTabs.check(R.id.tabhome);//绑定第一个
        mTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tabhome:
                        //根据按钮的监听加载响应的页面
                        showFrag(mHomeFragment);
                        break;
                    case R.id.tabCategory:
                        showFrag(mCategoryFragment);
                        break;
                    case R.id.tab_shopcart:
                        showFrag(mShopCartFragment);
                        break;
                    case R.id.tabme:
                        showFrag(mMeFragment);
                        break;
                }
            }
        });

       String showCartPage=getIntent().getStringExtra(Constant.SHOW_CART_KEY);
        if (TextUtils.isEmpty(showCartPage)){
            return;
        }else {
            if (showCartPage.equals(Constant.SHOW_CART_VALUE)){
                //切换到购物车界面
                showFrag(mShopCartFragment);
                //第三个tab被选中
                mTabs.check(R.id.tab_shopcart);
            }
        }


    }

    /**
     * 获取Fragment
     *
     */
    public HomeFragment getHomeFragment(){
        if (mHomeFragment==null){
            mHomeFragment=new HomeFragment();
            return mHomeFragment;
        }
        return mHomeFragment;
    }
    /**
     * 显示Fragment
     */
    public void showFrag(Fragment fragment) {
        hiddenFragment();//这个方法是隐藏所有的fragment
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        mTransaction.show(fragment);//显示你要显示的fragment
        mTransaction.commit();
    }
    /**
     * @throws
     * @Title: hiddenFragment
     * @Description:
     * @param:
     * @return: void
     */
    private void hiddenFragment() {
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        mTransaction.hide(mHomeFragment);
        mTransaction.hide(mCategoryFragment);
        mTransaction.hide(mShopCartFragment);
        mTransaction.hide(mMeFragment);
        mTransaction.commit();
    }



    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        //第一步是按钮的初始化
//        mTabs = (RadioGroup) findViewById(R.id.tabcontainer);
//        mTabHome = (RadioButton) findViewById(R.id.tabhome);//商城
//        mTabCategory = (RadioButton) findViewById(R.id.tabCategory);//分类
//        mTabShop = (RadioButton) findViewById(R.id.tab_shopcart);//购物车
//        mTabme = (RadioButton) findViewById(R.id.tabme);//我的
//
//        mFragmentManager = getSupportFragmentManager();//碎片管理器
//
//        //碎片的初始化
//        initFragment();
//        //第三步添加碎片
//        addFragment();
//
//        if (savedInstanceState != null) {
//            //获取
//            mCurrentSelectIndex = savedInstanceState.getInt(CACHE_SELECT_INDEX, INDEX_HOME);
//            Log.i(TAG,"saveInstance!=null="+mCurrentSelectIndex);
//        } else {
//            mCurrentSelectIndex = INDEX_HOME;
//            Log.i(TAG,"saveInstance=null="+mCurrentSelectIndex);
//        }
//        setSelectIndex(mCurrentSelectIndex);
//        mActivity = this;
//        mTabs.check(R.id.tabhome);
//        //step1  单选按钮的监听
//        mTabs.setOnCheckedChangeListener(new RadioListener());
//
//    }
//
//    /**
//     * 根据索引显示对应的fragment
//     * @param index
//     */
//    public void setSelectIndex(int index) {
//        switch (index) {
//            case INDEX_HOME:
//                //setTitleType(TITLE_HOME);
//                showFragment(mHomeFragment);
//                //changeTabtextSelector(rbtnHome);
//                mCurrentSelectIndex = INDEX_HOME;
//                break;
//            case INDEX_CATEGORY:
//
//                showFragment(mCategoryFragment);
//                //changeTabtextSelector(rbtnCategory);
//
//                mCurrentSelectIndex = INDEX_CATEGORY;
//                break;
//            case INDEX_SHOPCART:
//                //setTitleType(TITLE_DEFAULT);
//                showFragment(mShopCartFragment);
//                //changeTabtextSelector(rbtnShopcart);
//                setTitle(getString(R.string.tab_item_shopcart));
//                mCurrentSelectIndex = INDEX_SHOPCART;
//                break;
//            case INDEX_ME:
//                //setTitleType(TITLE_DEFAULT);
//                showFragment(mMeFragment);
//                // changeTabtextSelector(rbtnPerson);
//                // setTitle(getString(R.string.tab_item_mine));
//                mCurrentSelectIndex = INDEX_ME;
//                break;
//        }
//    }
//
//    /**
//     * 初始化frament
//     */
//    private void initFragment() {
//        mMrFragment = new MrFragment();//mrvfragment
//        mHomeFragment = new HomeFragment();
//        mCategoryFragment = new CategoryFragment();
//        mShopCartFragment = new ShopCartFragment();
//        mMeFragment = new MeFragment();
//    }
//
//    /**
//     * 添加fragment
//     */
//    private void addFragment() {
//        //获取ft
//        mFragmentTransaction = mFragmentManager.beginTransaction();//开启事物
//        mFragmentTransaction.add(R.id.mContainer, mHomeFragment, HOME_TAG);//添加商城
//        mFragmentTransaction.add(R.id.mContainer, mCategoryFragment, CATEGORY_TAG);//添加分类
//        mFragmentTransaction.add(R.id.mContainer, mShopCartFragment, SHOPCART_TAG);//添加购物车
//        mFragmentTransaction.add(R.id.mContainer, mMeFragment, ME_TAG);//添加我的
//        mFragmentTransaction.commitNowAllowingStateLoss();//提交事物
//    }
//
//    /**
//     * 显示fragment
//     */
//    private void showFragment(BaseFragment fragment) {
//        hiddenFragment();//这个方法是隐藏所有的fragment
//        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
//        mTransaction.show(fragment);//显示你要显示的fragment
//        mTransaction.commitAllowingStateLoss();//提交
//    }
//


//
//    /***
//     * 单选按钮的监听
//     */
//    class RadioListener implements RadioGroup.OnCheckedChangeListener {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            switch (checkedId) {
//                case R.id.tabhome:
//                    //根据按钮的监听应该让viewpager加载响应的页面
//                 setSelectIndex(INDEX_HOME);
//                    break;
//                case R.id.tabCategory:
//                   setSelectIndex(INDEX_CATEGORY);
//                    break;
//                case R.id.tab_shopcart:
//               setSelectIndex(INDEX_SHOPCART);
//                    break;
//                case R.id.tabme:
//                  setSelectIndex(INDEX_ME);
//                    break;
//            }
//        }
//    }
//
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//
//        //放入值
//        outState.putInt(CACHE_SELECT_INDEX, mCurrentSelectIndex);
//    }
//
//    /**
//     * 设置HomeActivity
//     */
//    private void setHomeActivity(AppCompatActivity mActivity) {
//        if (mActivity instanceof HomeActivity)
//            this.mActivity = (HomeActivity) mActivity;
//    }



}
