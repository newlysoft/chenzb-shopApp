package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.ProductBean;
import com.example.administrator.myapp.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/15.
 * 商城首页下面的列表适配器
 */

public class ProductFootAdapter extends RecyclerView.Adapter<ProductFootAdapter.ViewHolder>{

    private Context context;
    LayoutInflater mLayoutInflater;

    private ViewHolder viewHolder=null;

    private List<ProductBean.DataBean.ListBean> listBeanList=null;
    public ProductFootAdapter(Context context){
        this.context=context;
        mLayoutInflater=LayoutInflater.from(context);
        listBeanList=new ArrayList<>();//列表对象集合
    }
    @Override
    public ProductFootAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.product_foot_itemview,parent,false);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductFootAdapter.ViewHolder holder, final int position) {


        //绑定视图
        String picUrl=listBeanList.get(position).getPicUrl();
        Log.i("chent","picUrl="+picUrl);
        GlideUtils.GlideLoadImage(context,holder.imageView,picUrl);//加载图片
        holder.textView.setText(listBeanList.get(position).getText()+"价格："+listBeanList.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecyclerViewItemListener!=null){
                    mRecyclerViewItemListener.OnItemSelected(listBeanList,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listBeanList==null||listBeanList.size()==0){
            return 0;
        }
        return listBeanList.size();
    }

    /**
     * 给外部调用的
     * @param listBeanList
     */
    public void setData(List<ProductBean.DataBean.ListBean> listBeanList){
        this.listBeanList=listBeanList;
        //刷新数据
        notifyDataSetChanged();
    }


    private RecyclerViewItemListener mRecyclerViewItemListener=null;

    public void setOnItemClickListener(RecyclerViewItemListener mRecyclerViewItemListener){
        this.mRecyclerViewItemListener=mRecyclerViewItemListener;
    }
    /**
     * 给Adapter添加点击事件
     */
    interface RecyclerViewItemListener{

        public  void OnItemSelected(List<ProductBean.DataBean.ListBean> listBeanList, int position);
    }

   static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView=null;
        TextView textView=null;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.itemProductView);
            textView= (TextView) itemView.findViewById(R.id.itemText);
        }
    }
}
