package com.example.administrator.myapp.utils;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 * Created by Administrator on 2016/10/24.
 * fresco加载网络图片
 */

public class FrescoUtils {

    /**
     * 加载网络图片
     * @param url
     * @param imageView
     */
    public static void frescoLoadImage(String url, ImageView imageView){

        try {
            if (imageView!=null&&(imageView instanceof SimpleDraweeView)){
                SimpleDraweeView simpleDraweeView= (SimpleDraweeView) imageView;

                DraweeController controller=Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse(url))
                        .build();
                simpleDraweeView.setController(controller);
            }
        }catch (Exception e){
            Log.i("chent","exception-messag="+e.getMessage());
        }

    }
}
