package com.example.administrator.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
public class MyAdapter extends BaseAdapter {


    //字符串数组

   // public String[] listDatas = null;
    public List<String> stringList;
    public Context context;

    public MyAdapter(Context context, List<String> strings) {
        this.stringList = strings;
        this.context = context;

    }


    /**
     * 列表有几项
     *
     * @return
     */
    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;
        if (convertView == null) {
            convertView = View.inflate(context, android.R.layout.simple_list_item_1, null);
            textView = (TextView) convertView.findViewById(android.R.id.text1);
        }
        textView.setText(stringList.get(position));
        return convertView;
    }

    public void setData(List<String> strings) {
        this.stringList=strings;
    }
}
