package com.example.administrator.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adapter.MrvDetailAdapter;
import com.example.administrator.myapp.base.BaseActivity;
import com.example.administrator.myapp.bean.ProductBean;
import com.example.administrator.myapp.divider.RecycleViewDivider;
import com.example.administrator.myapp.imp.Constant;
import com.example.administrator.myapp.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情页面
 */
public class ShopDetailActivity extends BaseActivity {

    /**
     * onCreate界面
     *
     * @param savedInstanceState
     */
    private RecyclerView mRvDetail = null;
    private ImageView imageView;
    private ImageView ivReturn;
    private TextView tvDetailText;
    private TextView tvGoShop = null;
    String listTitles[] = new String[]{"产品规格", "商品属性", "详情", "累计评价"};


    private TextView tvAddPerson=null;
    private TextView tvAddShop=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        final ProductBean.DataBean.ListBean listBean = (ProductBean.DataBean.ListBean) bundle.getSerializable("listBean");
        //详情页面
        imageView = (ImageView) findViewById(R.id.detailImagView);
        ivReturn = (ImageView) findViewById(R.id.ivReturn);
        tvDetailText = (TextView) findViewById(R.id.tvDetailText);
        mRvDetail = (RecyclerView) findViewById(R.id.mRvDetail);//recyclerview产品信息列表
        tvGoShop = (TextView) this.findViewById(R.id.tvGoShop);//点击购买
        tvAddPerson= (TextView) this.findViewById(R.id.tvAddPerson);//收藏
        tvAddShop= (TextView) this.findViewById(R.id.tvAddShop);
        GlideUtils.GlideLoadImage(this, imageView, listBean.getPicUrl());//加载页面
        tvDetailText.setText(listBean.getText() + "......\n" + listBean.getPrice() + "元");

        tvAddShop.setOnClickListener(listener);
        tvAddPerson.setOnClickListener(listener);
        ivReturn.setOnClickListener(listener);//返回按钮的监听
        tvGoShop.setOnClickListener(listener);//点击按钮进行支付
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < listTitles.length; i++) {
            stringList.add(listTitles[i]);
        }
        MrvDetailAdapter mrvDetailAdapter = new MrvDetailAdapter(this);
        mRvDetail.setItemAnimator(new DefaultItemAnimator());
        mRvDetail.setHasFixedSize(true);
        mRvDetail.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
        mRvDetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvDetail.setAdapter(mrvDetailAdapter);
        mrvDetailAdapter.setData(stringList);
        //列表的点击事件
        mrvDetailAdapter.setOnItemClickListener(new MrvDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemSelected(List<String> stringList, int position) {

                switch (position) {
                    case 0:
                        //点击进行判断 如果是商品规格 就进入商品规格界面
                        Toast.makeText(getApplicationContext(), stringList.get(position), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ShopDetailActivity.this, ProductFormatActivity.class);
                        //传递对象到下面一个Act
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("listBean", listBean);
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                        break;


                }
            }
        });
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tvGoShop://购物

                    openActivity(context, PayActivity.class);
                    break;
                case R.id.ivReturn://返回支付
                    finish();
                    break;
                case R.id.tvAddPerson://点击进行收藏

                    Toast.makeText(context,R.string.addPerson,Toast.LENGTH_SHORT).show();
                    tvAddPerson.setText(R.string.tvReallyAdd);
                    tvAddPerson.setBackground(ContextCompat.getDrawable(context,R.drawable.product_like));
                    break;
                case R.id.tvAddShop:

                    //加入购物车
                    /**
                     *
                     *1.进入主页面
                     * 2.购物车被选中
                     */
                    Intent intent=new Intent(context,HomeActivity.class);
                    //
                    intent.putExtra(Constant.SHOW_CART_KEY,Constant.SHOW_CART_VALUE);
                    startActivity(intent);
                    break;
            }
        }
    };
}
