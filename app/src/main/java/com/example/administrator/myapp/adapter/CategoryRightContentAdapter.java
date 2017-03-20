package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.Category;
import com.example.administrator.myapp.ui.CategoryItemActivity;
import com.example.administrator.myapp.ui.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 * 右边整个包含title的RecyClerView的适配器
 */

public class CategoryRightContentAdapter extends RecyclerView.Adapter<CategoryRightContentAdapter.ViewHolder> {

    private Context context = null;

    LayoutInflater mInflater = null;

    private Category.DataBean dataBean = null;
    private List<Category.DataBean.ContentListBean> contentListBeans=null;

    /**
     * 构造器
     *
     * @param context
     */
    public CategoryRightContentAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        dataBean = new Category.DataBean();
        contentListBeans = new ArrayList<>();
    }

    /**
     * 绑定视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.category_content_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 绑定视图
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //在这个里面获取每一项的对象
        Category.DataBean.ContentListBean contentListBean = contentListBeans.get(position);
        List<Category.DataBean.ContentListBean.ContentBean> contentBeans = contentListBean.getContent();
        //我要拿到这个集合传给我itemRecycleview的适配器啊
        CategoryRightItemContentAdapter categoryRightItemContentAdapter = new CategoryRightItemContentAdapter(context);
        GridLayoutManager mGridLayoutManager=new GridLayoutManager(context,3);
        holder.itemContentView.setLayoutManager(mGridLayoutManager);
        holder.itemContentView.setLayoutFrozen(true);
        holder.itemTitle.setText(contentBeans.get(position).getText());
        holder.itemContentView.setAdapter(categoryRightItemContentAdapter);
        categoryRightItemContentAdapter.setData(contentBeans);
       categoryRightItemContentAdapter.setOnItemClickListener(new CategoryRightItemContentAdapter.OnItemClickListener() {
           @Override
           public void onItemSelected(List<Category.DataBean.ContentListBean.ContentBean> contentBeanList, int position) {

               if (context instanceof HomeActivity){
                   HomeActivity homeActivity= (HomeActivity) context;
                   Intent it=new Intent(context, CategoryItemActivity.class);
                   homeActivity.startActivityForResult(it,0);
               }
           }
       });
    }

    /**
     * 刷新数据
     *
     * @param dataBean
     */
    public void setData(Category.DataBean dataBean) {
        this.dataBean = dataBean;
        contentListBeans = dataBean.getContentList();
        this.notifyDataSetChanged();//刷新数据
    }

    /**
     * 获取项目的个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return contentListBeans.size();
    }

    /**
     * ViewHolder做缓存
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle = null;
        RecyclerView itemContentView = null;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemContentView = (RecyclerView) itemView.findViewById(R.id.itemRecycleView);
        }
    }
}
