package com.example.administrator.myapp.adrotatorcomponent.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adrotatorcomponent.adapter.AdvertisementAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 *@author dong 
 *@version 2015-3-8下午4:33:39
 * 广告轮播图片
 */

public class Advertisements implements OnPageChangeListener
{

	private ViewPager vpAdvertise;
	private Context context;
	private LayoutInflater inflater;
	private boolean fitXY;
	private int timeDratioin;//多长时间切换一次pager

	List<View> views;
	// 底部小点图片
	private ImageView[] dots;

	// 记录当前选中位置
	private int currentIndex;
	
	Timer timer;
	TimerTask task;
	int count = 0;
	
	private Handler runHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x01:
				int currentPage = (Integer) msg.obj;
				setCurrentDot(currentPage);
				vpAdvertise.setCurrentItem(currentPage);
				break;
			}
		};
	};

	/**
	 *
	 * @param context 上下文
	 * @param fitXY  是否将图片 xy
	 * @param inflater 视图解析器
	 * @param timeDratioin  图片轮播的时间
     */
	public Advertisements(Context context, boolean fitXY, LayoutInflater inflater , int timeDratioin)
	{
		this.context = context;
		this.fitXY = fitXY;
		this.inflater = inflater;
		this.timeDratioin = timeDratioin;
	}

	/**
	 * 通过传入进来的jsonArray数组图片路径 获取视图view
	 * @param advertiseArray
	 * @return
     */
	public View initView(final JSONArray advertiseArray){
		View vpView= inflater.inflate(R.layout.advertisement_board, null);
		vpAdvertise = (ViewPager)vpView.findViewById(R.id.vpAdvertise);
		vpAdvertise.addOnPageChangeListener(this);
		views = new ArrayList<View>();
		LinearLayout ll = (LinearLayout) vpView.findViewById(R.id.ll);//获取轮播图片的点的parent，用于动态添加要显示的点
		//根据图片数组的长度进行遍历=====用来装imageview
		for(int i = 0 ; i < advertiseArray.length() ; i ++){
			if(fitXY){
				views.add(inflater.inflate(R.layout.advertisement_item_fitxy, null));
			}else {
				views.add(inflater.inflate(R.layout.advertisement_item_fitcenter, null));
			}
			//水平的linearlayout
			ll.addView(inflater.inflate(R.layout.advertisement_board_dot, null));
		}
		//viewpager   linearlayout
		initDots(views,ll);
		AdvertisementAdapter adapter = new AdvertisementAdapter(context , views , advertiseArray);
		vpAdvertise.setOffscreenPageLimit(3);
		vpAdvertise.setAdapter(adapter);
		
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				int currentPage = count%advertiseArray.length();
				count++;
				Message msg = Message.obtain();
				msg.what = 0x01;
				msg.obj = currentPage;
				runHandler.sendMessage(msg);
			}
		};
		timer.schedule(task, 0, timeDratioin);
		return vpView;
	}

	/**
	 * 根据你传入进来的view   ll
	 * @param views
	 * @param ll
     */
	private void initDots(List<View> views, LinearLayout ll) {
		dots = new ImageView[views.size()];
		// 循环取得小点图片
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(false);// 都设为灰色
		}
		currentIndex = 0;
		dots[currentIndex].setEnabled(true);// 设置为黄色，即选中状态
	}

	private void setCurrentDot(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}

		dots[position].setEnabled(true);
		dots[currentIndex].setEnabled(false);

		currentIndex = position;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		count = position;
		setCurrentDot(position);
	}
	
}
