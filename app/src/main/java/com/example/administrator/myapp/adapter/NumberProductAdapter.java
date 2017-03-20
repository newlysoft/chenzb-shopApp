package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 * 分类tab所对应的右边数码产品的适配器
 */

public class NumberProductAdapter extends RecyclerView.Adapter<NumberProductAdapter.ViewHolder> {


    private Context context;

    private List<String> dataTitles;
    public NumberProductAdapter(Context context, List<String> dataTitles){
        this.context=context;
        this.dataTitles=dataTitles;
    }
    /**
     * ViewHolder的一个初始化
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.item_numberadapter,parent,false);
       // View view=View.inflate(context, R.layout.item_numberadapter,parent,);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 绑定viewholder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(dataTitles.get(position));//


        //这里我们要拿到数据
       // holder.mRecyclerView.setAdapter();

    }

    @Override
    public int getItemCount() {
        return dataTitles.size();
    }

    /**
     * ViewHolder
     */
    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tvTitle;
        RecyclerView mRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle= (AppCompatTextView) itemView.findViewById(R.id.tvProductTitle);
            mRecyclerView= (RecyclerView) itemView.findViewById(R.id.mListProduct);
        }
    }
}
