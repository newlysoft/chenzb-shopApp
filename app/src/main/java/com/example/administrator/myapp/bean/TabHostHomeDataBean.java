package com.example.administrator.myapp.bean;

/**
 * Created by Administrator on 2017/6/28.
 */

public class TabHostHomeDataBean {

    public String imgId;
    public String text;


    public TabHostHomeDataBean() {
    }

    public TabHostHomeDataBean(String text, String imgId) {
        this.text = text;
        this.imgId = imgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    @Override
    public String toString() {
        return "TabHostHomeDataBean{" +
                "imgId='" + imgId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
