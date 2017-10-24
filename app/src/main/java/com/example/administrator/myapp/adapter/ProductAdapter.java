package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.ProductBean;
import com.example.administrator.myapp.divider.RecycleViewDivider;
import com.example.administrator.myapp.ui.HomeActivity;
import com.example.administrator.myapp.ui.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 * 产品适配器======
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    /**
     * 声明变量
     */
    private LayoutInflater mLayoutInflater;

    private List<ProductBean.DataBean> dataBeanList;
    private ProductFootAdapter productFootAdapter;


    /**
     * 构造器
     *
     * @param context
     */
    public ProductAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        //实例化集合
       dataBeanList=new ArrayList<>();
    }

    /**
     * 绑定视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.home_list_item, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 绑定ViewHolder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {

        //获取标题对象

        holder.tvTitle.setText( dataBeanList.get(position).getTitle());//从集合通过位置直接拿到对象 ---不需要进行遍历了 ---如果是用原生的jsonArray就遍历

        //拿到列表bean对象
        //适配器
        productFootAdapter = new ProductFootAdapter(context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        holder.mRecyclerView.setLayoutFrozen(true);
        holder.mRecyclerView.setLayoutManager(gridLayoutManager);
        //这里给RecycleView设置数据
        holder.mRecyclerView.setAdapter(productFootAdapter);
        holder.mRecyclerView.addItemDecoration(new RecycleViewDivider(context, OrientationHelper.HORIZONTAL));
        holder.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        productFootAdapter.setData(dataBeanList.get(position).getList());

        productFootAdapter.setOnItemClickListener(new ProductFootAdapter.RecyclerViewItemListener() {
            @Override
            public void OnItemSelected(List<ProductBean.DataBean.ListBean> listBeanList, int position) {
                /**
                 * 跳转Act
                 */

              if (context instanceof HomeActivity){
                  Intent intent=new Intent(context, ShopDetailActivity.class);
                  Bundle bundle=new Bundle();
                  String listBeanStr=listBeanList.get(position).toString();
                  Log.i("chent","listBean="+listBeanStr);
                  bundle.putSerializable("listBean",listBeanList.get(position));
                  intent.putExtra("bundle",bundle);
//                  HomeActivity mActivity= (HomeActivity)context;
//                  HomeFragment fragment=mActivity.getHomeFragment();
//                  fragment.startActivityForResult(intent,0);
                  context.startActivity(intent);
              }

            }
        });
    }

    /**
     * 返回列表的项目
     *
     * @return
     */
    @Override
    public int getItemCount() {
        if (dataBeanList == null || dataBeanList.size() == 0) {
            return 0;
        }
        return dataBeanList.size();
    }

    //设置数据提供给外部调用 从而刷新数据
    public void setData(ProductBean bean) {
        if (bean != null) {
            dataBeanList= bean.getData();//拿到集合数据
            Log.i("chent", "databean=" + dataBeanList.toString());
            notifyDataSetChanged();
        }
    }





    /**
     * ViewHolder
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.mRecyclerView);
        }
    }

}
