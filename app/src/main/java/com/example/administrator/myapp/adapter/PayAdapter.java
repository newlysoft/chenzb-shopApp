package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.PayBean;
import com.example.administrator.myapp.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.ViewHolder> {

    public Context context = null;
    public LayoutInflater mLayoutInflater = null;
    List<PayBean> payBeanList = null;

    public PayAdapter(Context context) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        payBeanList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.recyclerview_item_paymethod, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PayAdapter.ViewHolder holder, final int position) {


        holder.tvPay.setText(payBeanList.get(position).payName);
        GlideUtils.GlideLoadLocalImage(context, holder.ivPay, null, payBeanList.get(position).id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnPayItemClickListener != null) {
                    mOnPayItemClickListener.onItemPaySelected(payBeanList,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (payBeanList == null) {
            return 0;
        }
        return payBeanList.size();
    }

    public void addList(List<PayBean> payBeanList) {
        if (payBeanList == null) {
            return;
        }
        if (payBeanList.size() == 0) {
            return;
        }
        this.payBeanList.addAll(payBeanList);
        //刷新数据
        this.notifyDataSetChanged();
    }

    //
    OnPayItemClickListener mOnPayItemClickListener = null;

    public void setOnPayItemClickListener(OnPayItemClickListener mOnPayItemClickListener) {
        this.mOnPayItemClickListener = mOnPayItemClickListener;
    }

    public interface OnPayItemClickListener {
        void onItemPaySelected(List<PayBean> payBeanList, int position);

    }

    /**
     * ViewHolder
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPay = null;
        ImageView ivPay = null;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPay = (TextView) itemView.findViewById(R.id.itemPayMethod);
            ivPay = (ImageView) itemView.findViewById(R.id.ivPay);
        }
    }
}
