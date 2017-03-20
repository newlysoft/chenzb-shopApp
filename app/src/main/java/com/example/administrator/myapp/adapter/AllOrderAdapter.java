package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.OrderBean;
import com.example.administrator.myapp.utils.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 * 所有订单
 */
public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderAdapter.ViewHolder> {

    private Context context = null;
    private LayoutInflater mlaLayoutInflater = null;
    private OrderBean orderBean;

    public AllOrderAdapter(Context context) {
        this.context = context;
        mlaLayoutInflater = LayoutInflater.from(context);
        orderBean = new OrderBean();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.order_itemview, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        //获取实体类
        List<OrderBean.DataBean> dataBeanList = orderBean.getData();
        OrderBean.DataBean dataBean = dataBeanList.get(position);
        final OrderBean.DataBean.OrderproductBean orderproductBean = dataBean.getOrderproduct();
        //加载网络图片
        GlideUtils.GlideLoadImage(context, holder.imgView, orderproductBean.getPicUrl());
        holder.tvLaterDesc.setText(orderproductBean.getProductname());
        holder.tvLaterPrice.setText(orderproductBean.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOrderOnItemClickListener!=null){
                    mOrderOnItemClickListener.onItemSelected(orderBean,position);
                }
            }
        });
    }

    /**
     * 返回的长度
     * @return
     */
    @Override
    public int getItemCount() {

        if (orderBean == null) {
            return 0;
        }
        if (orderBean.getData() == null) {
            return 0;
        }

        return orderBean.getData().size();
    }

    public void setData(OrderBean orderBean) {
        if (orderBean == null) {
            //立即终止程序
            return;
        }
        this.orderBean = orderBean;
        this.notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView = null;
        TextView tvLaterDesc = null;
        TextView tvLaterPrice = null;

        public ViewHolder(View itemView) {
            super(itemView);
            //初始化控件
            imgView = (ImageView) itemView.findViewById(R.id.orderImgView);
            tvLaterDesc = (TextView) itemView.findViewById(R.id.itemProductDesc);
            tvLaterPrice = (TextView) itemView.findViewById(R.id.itemLaterPrice);
        }
    }

    OrderOnItemClickListener mOrderOnItemClickListener=null;
    public void setOnItemClickListener(OrderOnItemClickListener mOrderOnItemClickListener){
        this.mOrderOnItemClickListener=mOrderOnItemClickListener;
    }

    public interface OrderOnItemClickListener {

        void onItemSelected(OrderBean orderBean,int position);
    }
}
