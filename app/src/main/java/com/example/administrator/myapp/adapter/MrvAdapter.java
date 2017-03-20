package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.EatBean;

/**
 * Created by Administrator on 2016/10/18.
 */

public class MrvAdapter extends RecyclerView.Adapter<MrvAdapter.ViewHolder> {

    public EatBean eatBean;
    public Context context;

    public MrvAdapter(Context context, EatBean eatBean) {
        this.context = context;
        this.eatBean=eatBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewholoder
        ViewHolder viewHolder=new ViewHolder(View.inflate(context, R.layout.itemview,null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        final String itemDescription=eatBean.getTngou().get(position).getDescription();
        final String itemKeywords=eatBean.getTngou().get(position).getKeywords();
        //绑定
        holder.itemTitle.setText(itemKeywords);
        holder.itemContent.setText(itemDescription);

        //ViewGroup.LayoutParams  layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       // holder.itemContainer.setLayoutParams(layoutParams);
        //holder.itemTv.setGravity(Gravity.CENTER);
        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemselected(eatBean,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eatBean.getTngou().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView itemTitle;
        AppCompatTextView itemContent;
        LinearLayout itemContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle= (AppCompatTextView) itemView.findViewById(R.id.itemTitle);
            itemContent= (AppCompatTextView) itemView.findViewById(R.id.itemContent);
            itemContainer = (LinearLayout) itemView.findViewById(R.id.itemContainer);
        }
    }


    private OnItemclickListener listener;

    public interface OnItemclickListener {
        void onItemselected(EatBean eatBean, int position);

    }

    public void  setOnItemClicklistener(OnItemclickListener onItemClicklistener){
        this.listener=onItemClicklistener;
    }


    public void  setData(EatBean eatBean){
        this.eatBean=eatBean;
    }
}
