package com.example.administrator.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.administrator.myapp.R;
import com.example.administrator.myapp.adrotatorcomponent.view.Advertisements;
import com.example.administrator.myapp.base.BaseActivity;
import com.example.administrator.myapp.imp.Constant;
import com.example.administrator.myapp.utils.GlideUtils;
import com.example.administrator.myapp.utils.RoundImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 界面
 */
public class MainActivity extends BaseActivity {

    private String content;
    private EditText etContent;
    private Button btnStartAct;
    private Button btnSend;
    private Button btnFragTabHost = null;
    private Button btnOneKeyShare = null;
    private LinearLayout llAdvertiseBoard;
    private LayoutInflater inflater;
    private JSONArray advertiseArray;
    private CircleImageView mRoundedImageView = null;

    private RoundImageView roundImageView;

    private String picUrl = "http://img3.imgtn.bdimg.com/it/u=1282947565,1111767110&fm=26&gp=0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//step1 关联布局
        context=this;
        //step2 获取文本的内容
        etContent = (EditText) findViewById(R.id.etContent);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnStartAct = (Button) findViewById(R.id.btnStartAct);
        btnFragTabHost= (Button) findViewById(R.id.btnFragTabHost);
        btnOneKeyShare = (Button) this.findViewById(R.id.btnOneKeyShare);
        llAdvertiseBoard = (LinearLayout) findViewById(R.id.llAdvertiseBoard);
        mRoundedImageView = (CircleImageView) findViewById(R.id.mRoundImageView);
        roundImageView = (RoundImageView) findViewById(R.id.mImageView);
        roundImageView.setRectAdius(20.0f);
        GlideUtils.GlideLoadImage(this, mRoundedImageView, picUrl);
        // FrescoUtils.frescoLoadImage(picUrl,mRoundedImageView);
        inflater = LayoutInflater.from(this);
        //step2:jsonArray方式添加图片的Url地址
        advertiseArray = new JSONArray();
        try {
            //json对象获取图片地址
            JSONObject head_img0 = new JSONObject();
            head_img0.put("head_img", "http://pic.nipic.com/2008-08-12/200881211331729_2.jpg");
            JSONObject head_img1 = new JSONObject();
            head_img1.put("head_img", "http://pic1.ooopic.com/uploadfilepic/sheji/2010-01-12/OOOPIC_1982zpwang407_20100112ae3851a13c83b1c4.jpg");
            JSONObject head_img2 = new JSONObject();
            head_img2.put("head_img", "http://pic1.ooopic.com/uploadfilepic/sheji/2009-09-12/OOOPIC_wenneng837_200909122b2c8368339dd52a.jpg");
            JSONObject head_img3 = new JSONObject();
            head_img3.put("head_img", "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1429108038,1536204131&fm=58");
            //第二步 json数组获取图片对象
            advertiseArray.put(head_img0);
            advertiseArray.put(head_img1);
            advertiseArray.put(head_img2);
            advertiseArray.put(head_img3);
            //第三步骤 讲json数组转化成字符串
            String jsongArrayStr = advertiseArray.toString();
            //广告图片轮播类
            Advertisements advertisements = new Advertisements(this, true, inflater, 3000);
            View dotView = advertisements.initView(advertiseArray);//
            //将做好的图片视图放到容器中去
            llAdvertiseBoard.addView(dotView);//添加视图
            Log.i("ct", jsongArrayStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ButtonListener listener = new ButtonListener();
        //注册广播
        //第三步发送广播
        btnSend.setOnClickListener(listener);
        btnStartAct.setOnClickListener(listener);
        btnFragTabHost.setOnClickListener(listener);
        btnOneKeyShare.setOnClickListener(listener);
        //发送广播
    }


    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btnSend:
                    content = etContent.getText().toString().trim();
                    //发送广播
                    Intent intent = new Intent();
                    intent.putExtra("name", content);
                    intent.setAction(Constant.TEST_ACTION);
                    sendBroadcast(intent);
                    openActivity(context, TestReceiverActivity.class);
                    break;
                case R.id.btnStartAct://开启act
                    openActivity(context,HomeActivity.class);
                    finish();
                    break;
                case R.id.btnFragTabHost://进入http测试界面
                    openActivity(context,HnActivity.class);
                    break;
                case R.id.btnOneKeyShare:

                    oneKeyShare(context);
                    break;
            }
        }
    }

    /**
     * 取消注册
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();//取消注册广播
    }
}
