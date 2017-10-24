package com.example.administrator.myapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.base.BaseFragment;
import com.example.administrator.myapp.ui.HomeActivity;
import com.example.administrator.myapp.ui.PayActivity;

/**
 * Created by Administrator on 2016/10/20.
 * 产品分类
 */

public class ShopCartFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    private ImageView ivReturn=null;

    private RecyclerView mShopRecyclerView=null;
    private TextView tvGoShop=null;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_shopcart,container,false);
        ivReturn= (ImageView) view.findViewById(R.id.ivReturn);
        tvGoShop= (TextView) view.findViewById(R.id.tvGoShop);
        viewSetOnclickListener();//视图监听
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 视图监听
     */
    private void viewSetOnclickListener() {
        ivReturn.setOnClickListener(this);
        tvGoShop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.ivReturn://返回按钮的点击事件===Category里面
               Toast.makeText(getActivity(),"iv 被点击了",Toast.LENGTH_SHORT).show();
               if (context instanceof HomeActivity){
                   HomeActivity mActivity= (HomeActivity)context;
                   mActivity.showFrag(new HomeFragment());
                   mActivity.mTabs.check(R.id.tabhome);
               }
                break;
            case R.id.tvGoShop:

                Intent intent=new Intent(context, PayActivity.class);
                startActivity(intent);
                break;
        }
    }
}
