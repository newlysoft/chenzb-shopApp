package com.example.administrator.myapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.myapp.R;

/**
 * Created by Administrator on 2016/10/23.
 */

public class GlideUtils {

    /**
     * 工具类加载图片
     */
    public static void GlideLoadImage(Context context, ImageView imageView,String imgUrl){

        Glide.with(context).load(imgUrl).fitCenter().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.mipmap.ic_launcher).into(imageView);
    }


    /**
     * 工具类加载图片
     */
    public static void GlideLoadLocalImage(Context context, ImageView imageView,String imgUrl,int resId){

        Glide.with(context).load(imgUrl).fitCenter().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).error(resId).into(imageView);
    }
}
