package com.example.administrator.myapp.utils;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class AndroidShare extends Dialog implements
        AdapterView.OnItemClickListener {
    private LinearLayout mLayout;
    private GridView mGridView;
    private float mDensity;
    private String msgText;
    private List<ShareItem> mListData;

    public AndroidShare(Context context) {
        super(context, R.style.shareDialogTheme);
    }

    public AndroidShare(Context context, int theme, String msgText) {
        super(context, theme);
        this.msgText = msgText;
    }

    public AndroidShare(Context context, String msgText) {
        super(context, R.style.shareDialogTheme);
        this.msgText = msgText;
    }

    void init(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        this.mDensity = dm.density;
        this.mListData = new ArrayList<ShareItem>();
        this.mListData.add(new ShareItem("微信好友", R.mipmap.share_logo_wechat,
                "com.tencent.mm.ui.tools.ShareImgUI", "com.tencent.mm"));
        // this.mListData.add(new ShareItem("朋友圈",
        // R.drawable.share_logo_wechatmoments,
        // "com.tencent.mm.ui.tools.ShareToTimeLineUI", "com.tencent.mm"));
        this.mListData.add(new ShareItem("qq好友", R.mipmap.share_logo_qq,
                "com.tencent.mobileqq.activity.JumpActivity",
                "com.tencent.mobileqq"));
        this.mListData
                .add(new ShareItem("qq空间", R.mipmap.share_logo_qzone,
                        "com.qzone.ui.operation.QZonePublishMoodActivity",
                        "com.qzone"));
        this.mListData.add(new ShareItem("新浪微博",
                R.mipmap.share_logo_sinaweibo, "com.sina.weibo.EditActivity",
                "com.sina.weibo"));
        this.mListData.add(new ShareItem("其他", R.mipmap.share_logo_other, "",
                ""));
        this.mLayout = new LinearLayout(context);
        this.mLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        this.mLayout.setLayoutParams(params);
        this.mLayout.setBackgroundColor(Color.parseColor("#fcfcfc"));
        int padding = ((int) (10.0F * this.mDensity));
        this.mLayout.setPadding(padding, padding, padding, padding);

        this.mGridView = new GridView(context);
        this.mGridView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.mGridView.setHorizontalSpacing((int) (10.0F * this.mDensity));
        this.mGridView.setVerticalSpacing((int) (10.0F * this.mDensity));
        this.mGridView.setNumColumns(3);
        this.mGridView.setColumnWidth((int) (100.0F * this.mDensity));
        this.mGridView.setHorizontalScrollBarEnabled(false);
        this.mGridView.setVerticalScrollBarEnabled(false);
        this.mLayout.addView(this.mGridView);
    }

    public List<ComponentName> queryPackage() {
        List<ComponentName> cns = new ArrayList<ComponentName>();
        Intent i = new Intent("android.intent.action.SEND");
        i.setType("image/*");
        List<ResolveInfo> resolveInfo = getContext().getPackageManager()
                .queryIntentActivities(i, 0);
        for (ResolveInfo info : resolveInfo) {
            ActivityInfo ac = info.activityInfo;
            ComponentName cn = new ComponentName(ac.packageName, ac.name);
            cns.add(cn);
        }
        return cns;
    }

    public boolean isAvilible(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();

        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (((PackageInfo) pinfo.get(i)).packageName
                    .equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getContext();
        init(context);
        setContentView(this.mLayout);
        getWindow().setGravity(80);
        this.mGridView.setAdapter(new MyAdapter());
        this.mGridView.setOnItemClickListener(this);
    }

    public void show() {
        super.show();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        ShareItem share = (ShareItem) this.mListData.get(position);
        shareMsg(getContext(), "分享到...", this.msgText, share);
        dismiss();
    }

    private void shareMsg(Context context, String msgTitle, String msgText,
                          ShareItem share) {
        if (!share.packageName.isEmpty()
                && !isAvilible(getContext(), share.packageName)) {
           // ToastUtils.showShort(getContext(), "请先安装" + share.title);
            Toast.makeText(getContext(),"请先安装" + share.title, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (TextUtils.isEmpty(share.packageName)) {
            context.startActivity(Intent.createChooser(intent, msgTitle));
        } else {
            intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
            intent.setComponent(new ComponentName(share.packageName,
                    share.activityName));
            context.startActivity(intent);
        }
        intent = null;
    }

    private final class MyAdapter extends BaseAdapter {

        public MyAdapter() {
        }

        public int getCount() {
            return mListData.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0L;
        }

        private View getItemView() {
            LinearLayout item = new LinearLayout(getContext());
            item.setOrientation(LinearLayout.VERTICAL);
            item.setGravity(17);

            ImageView iv = new ImageView(getContext());
            item.addView(iv);
            iv.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            iv.setId(R.id.image_id);

            TextView tv = new TextView(getContext());
            item.addView(tv);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    -2, -2);
            layoutParams.topMargin = ((int) (5.0F * mDensity));
            tv.setLayoutParams(layoutParams);
            tv.setTextColor(Color.parseColor("#212121"));
            tv.setTextSize(16.0F);
            tv.setId(R.id.tv_id);

            return item;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getItemView();
            }
            ImageView iv = (ImageView) convertView.findViewById(R.id.image_id);
            TextView tv = (TextView) convertView.findViewById(R.id.tv_id);
            ShareItem item = (ShareItem) mListData
                    .get(position);
            iv.setImageResource(item.logo);
            tv.setText(item.title);
            return convertView;
        }
    }

    private class ShareItem {
        String title;
        int logo;
        String activityName;
        String packageName;

        public ShareItem(String title, int logo, String activityName,
                         String packageName) {
            this.title = title;
            this.logo = logo;
            this.activityName = activityName;
            this.packageName = packageName;
        }
    }
}
