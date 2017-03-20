package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/20.
 */

public class MrvDetailAdapter extends RecyclerView.Adapter<MrvDetailAdapter.DetailViewHolder> {

    private Context context=null;

    private LayoutInflater mInflater=null;

    private List<String> stringList=null;
    public MrvDetailAdapter() {
    }
    public MrvDetailAdapter(Context context) {
        this.context=context;
        stringList=new ArrayList<>();
        mInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public MrvDetailAdapter.DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.item_detail_itemview,parent,false);
        DetailViewHolder viewHolder=new DetailViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MrvDetailAdapter.DetailViewHolder holder, final int position) {

        holder.itemText.setText(stringList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemSelected(stringList,position);
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
    //
    static  class DetailViewHolder extends RecyclerView.ViewHolder{
        ImageView itemIv=null;
        TextView itemText=null;
        public DetailViewHolder(View itemView) {
            super(itemView);
           itemIv= (ImageView) itemView.findViewById(R.id.itemRight);
            itemText= (TextView) itemView.findViewById(R.id.itemText);
        }
    }

    //刷新数据
    public void setData(List<String> stringList){
        if (stringList==null||stringList.size()==0){
            return;
        }
        this.stringList=stringList;
        this.notifyDataSetChanged();
    }
    //声明接口变量
    private OnItemClickListener mOnItemClickListener=null;
    //设置监听
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }
    //接口
   public interface OnItemClickListener{

       void onItemSelected(List<String> stringList,int position);
   }
}
