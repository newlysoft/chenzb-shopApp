package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.bean.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 * 产品分类的适配器
 */

public class CategoryLeftMrvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Category category;
    private List<Category.DataBean> dataBeanList = null;//这个是包含标题跟内容区域的集合
    private LayoutInflater mInflater=null;
    private List<Category.DataBean.ContentListBean> contentListBeans;//这个是包含就下面的内容区域的RecyclerView

    /**
     * 构造器
     * @param context
     */
    public CategoryLeftMrvAdapter(Context context) {
        this.context = context;
        category = new Category();
        dataBeanList = new ArrayList<>();
        contentListBeans=new ArrayList<>();
        mInflater= LayoutInflater.from(context);
    }

    /**
     * 初始化ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.menu_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    /**
     * ViewHolder的绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //显示数据
        final ViewHolder viewHolder = (ViewHolder) holder;
        //在这里获取数据===右边的集合的数据
        contentListBeans= dataBeanList.get(position).getContentList();
        //根据位置获取对象 ---- 获取实体类的属性
        viewHolder.textView.setText(dataBeanList.get(position).getListTitle());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //点击事件
                if (mOnItemClickListener != null) {
                    /**
                     * 接口走起
                     */
                    mOnItemClickListener.onItemSelected(category,dataBeanList,contentListBeans,position);
                }
            }
        });
    }

    /**
     * 返回的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    /**
     * 这个方法也是给外部调用刷新适配器的数据
     */

    public void setData(Category category) {
        this.category = category;

        //这里获取对象里面的所有数据
        //获取对象最外层的集合
        //最外面的集合
        dataBeanList = category.getData();
        Log.i("chent", "dataBeanList右边的数据=" + dataBeanList.toString());
        //刷新数据
        this.notifyDataSetChanged();
    }

    ///ViewHolder的干货
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvMenu);
        }
    }


    /**
     * 提供给外部调用
     */
    public void setOnItemListener(OnItemClickListener mOnItemClickListener) {

        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 接口类型的变量的声明
     */
    OnItemClickListener mOnItemClickListener;

    /**
     * 接口
     */
    public interface OnItemClickListener {
        void onItemSelected(Category category, List<Category.DataBean> dataBeanList,List<Category.DataBean.ContentListBean> contentListBeens,int position);
    }

}
