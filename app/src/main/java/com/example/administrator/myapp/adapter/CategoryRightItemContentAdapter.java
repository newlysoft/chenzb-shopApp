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
import com.example.administrator.myapp.bean.Category;
import com.example.administrator.myapp.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */

public class CategoryRightItemContentAdapter extends RecyclerView.Adapter<CategoryRightItemContentAdapter.ViewHolder>{
    private List<Category.DataBean.ContentListBean.ContentBean> contentBeanList=null;
    private Context context=null;
    private LayoutInflater mInflater=null;
    public CategoryRightItemContentAdapter(Context context){
        this.context=context;
        this.contentBeanList=new ArrayList<>();
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public CategoryRightItemContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView=mInflater.inflate(R.layout.cate_itemcontent_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryRightItemContentAdapter.ViewHolder holder, final int position) {

        try {
            //加载网络图片
            GlideUtils.GlideLoadImage(holder.mImageView.getContext(),
                    holder.mImageView,
                    contentBeanList.get(position).getPicUrl());
            //文本信息
            holder.mTextView.setText(contentBeanList.get(position).getText());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onItemSelected(contentBeanList,position);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (contentBeanList == null) {
            return 0;
        }
        return contentBeanList.size();
    }
    public void setData(List<Category.DataBean.ContentListBean.ContentBean> contentBeanList){
        this.contentBeanList=contentBeanList;
        Log.i("chent","contentBeanList==="+contentBeanList.toString());
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView=null;
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView= (ImageView) itemView.findViewById(R.id.itemImageView);
            mTextView= (TextView) itemView.findViewById(R.id.itemText);
        }
    }

    private OnItemClickListener mOnItemClickListener=null;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    interface OnItemClickListener{
        void onItemSelected(List<Category.DataBean.ContentListBean.ContentBean> contentBeanList,int position);
    }
}
