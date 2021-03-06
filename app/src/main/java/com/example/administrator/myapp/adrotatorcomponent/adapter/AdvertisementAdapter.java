package com.example.administrator.myapp.adrotatorcomponent.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.utils.GlideUtils;

import org.json.JSONArray;

import java.util.List;


/**
 * 广告轮播adapter
 *@author dong
 *@data 2015年3月8日下午3:46:35
 *@contance dong854163@163.com
 */
public class AdvertisementAdapter extends PagerAdapter {

	private Context context;
	private List<View> views;
	JSONArray advertiseArray;
	
	public AdvertisementAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdvertisementAdapter(Context context, List<View> views, JSONArray advertiseArray) {
		this.context = context;
		this.views = views;
		this.advertiseArray = advertiseArray;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((ViewPager) container).addView(views.get(position), 0);
		final int POSITION = position;
		View view = views.get(position);
		try {
			String head_img = advertiseArray.optJSONObject(position).optString("head_img");
			ImageView ivAdvertise = (ImageView) view.findViewById(R.id.ivAdvertise);
			// 加载网络图片
			//ImageLoaderUtil.getImage(context,ivAdvertise,head_img, R.mipmap.ic_launcher, R.mipmap.ic_launcher,0,0);
			//FrescoUtils.frescoLoadImage(head_img,ivAdvertise);
			GlideUtils.GlideLoadImage(context,ivAdvertise,head_img);
			// item的点击监听
//			ivAdvertise.setOnClickListener(new OnClickListener() {
//				@SuppressLint("ShowToast")
//				@Override
//				public void onClick(View v) {
//					//Toast.makeText(context, "第"+POSITION+"个广告图片被点击",Toast.LENGTH_SHORT).show();
//				}
//			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
}
