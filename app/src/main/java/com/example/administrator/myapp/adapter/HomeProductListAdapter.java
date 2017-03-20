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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/19.
 */

public class HomeProductListAdapter extends RecyclerView.Adapter<HomeProductListAdapter.ViewHolder> {

    private Context context=null;
    private List<String> stringList;

    private LayoutInflater mInflater=null;
    public HomeProductListAdapter(){}

    /**
     * 构造器
     * @param context
     */
    public HomeProductListAdapter(Context context){
        this.context=context;
        stringList=new ArrayList<>();
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public HomeProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.item_product_home_list,parent,false);
        ViewHolder  viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeProductListAdapter.ViewHolder holder, int position) {

        GlideUtils.GlideLoadImage(context,holder.imageView,"https://gw.alicdn.com/bao/uploaded/TB1tSltOVXXXXaIapXXSutbFXXX.jpg_q90");
        holder.tvText.setText(stringList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemMethod();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (stringList==null){
            return 0;
        }
        return stringList.size();
    }


    /**
     * 设置适配器刷新数据
     */

    public void setData(List<String> stringList){
        this.stringList=stringList;
        this.notifyDataSetChanged();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView=null;
        TextView tvText=null;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.itemView);
            tvText= (TextView) itemView.findViewById(R.id.itemText);
        }
    }

   public OnItemClickListener mOnItemClickListener=null;
    /**
     * 设置点击事件
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    public interface OnItemClickListener{
        public void onItemMethod();
    }
}
