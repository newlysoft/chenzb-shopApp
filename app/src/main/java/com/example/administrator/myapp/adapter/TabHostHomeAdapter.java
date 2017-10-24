package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.TabHostHomeDataBean;
import com.example.administrator.myapp.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class TabHostHomeAdapter extends RecyclerView.Adapter<TabHostHomeAdapter.TabHostHomeViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private  TabHostHomeViewHolder tabHostHomeViewHolder=null;
    public TabHostHomeDataBean tabHostHomeDataBean=null;
    public List<TabHostHomeDataBean> tabHostHomeDataBeanList=null;
    public TabHostHomeAdapter(Context context){
        this.context=context;
        mInflater=LayoutInflater.from(context);
        tabHostHomeDataBeanList=new ArrayList<>();
    }
    @Override
    public TabHostHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView=mInflater.inflate(R.layout.tab_layout,parent,false);
        tabHostHomeViewHolder=new TabHostHomeViewHolder(itemView);
        return tabHostHomeViewHolder;
    }

    @Override
    public void onBindViewHolder(TabHostHomeViewHolder holder, int position) {

        GlideUtils.GlideLoadImage(context,holder.imageView,tabHostHomeDataBeanList.get(position).getImgId());
        holder.tagText.setText(tabHostHomeDataBeanList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return tabHostHomeDataBeanList.size();
    }



    public void setData(List<TabHostHomeDataBean> tList){

        this.tabHostHomeDataBeanList=tList;
        //刷新数据
        this.notifyDataSetChanged();

    }
    //继承ViewHolder

    class TabHostHomeViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tagText;
        public TabHostHomeViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.tab_icon);
            tagText= (TextView) itemView.findViewById(R.id.tab_tag);
        }
    }


}
