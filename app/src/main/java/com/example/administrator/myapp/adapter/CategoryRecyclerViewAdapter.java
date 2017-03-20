package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.utils.GlideUtils;

/**
 * Created by Administrator on 2017/2/14.
 * 产品分类的gridview这种类型的适配器
 */

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    public Context context = null;

    public CategoryRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.category_item_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String imgUrl="https://img.alicdn.com/tps/TB1VlgLPXXXXXbTXVXXXXXXXXXX-276-402.jpg_150x10000q90.jpg";
        //加载网络图片
        GlideUtils.GlideLoadImage(context,holder.imageView,imgUrl);
        holder.tvName.setText("衣服");
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    /**
     * ViewHolder
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView = null;
        TextView tvName = null;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.itemImgView);
            tvName= (TextView) itemView.findViewById(R.id.itemTvName);
        }
    }
}
