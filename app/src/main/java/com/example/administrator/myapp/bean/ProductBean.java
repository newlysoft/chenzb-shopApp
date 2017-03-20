package com.example.administrator.myapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/15.
 * 产品对象
 */

public class ProductBean implements Serializable{

    /**
     * code : 200
     * message : success
     * data : [{"title":"手机\\数码\\通信","list":[{"text":"text","picUrl":"httpUrl","price":"2300"},{"text":"text","picUrl":"httpUrl","price":"2300"}]},{"title":"家用电器","list":[{"text":"text","picUrl":"httpUrl","price":"2300"},{"text":"text","picUrl":"httpUrl","price":"2300"}]}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProductsBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }



    public static class DataBean implements Serializable{
        /**
         * title : 手机\数码\通信
         * list : [{"text":"text","picUrl":"httpUrl","price":"2300"},{"text":"text","picUrl":"httpUrl","price":"2300"}]
         */

        private String title;
        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "title='" + title + '\'' +
                    ", list=" + list +
                    '}';
        }

        public static class ListBean implements Serializable{
            /**
             * text : text
             * picUrl : httpUrl
             * price : 2300
             */

            private String text;
            private String picUrl;
            private String price;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "text='" + text + '\'' +
                        ", picUrl='" + picUrl + '\'' +
                        ", price='" + price + '\'' +
                        '}';
            }
        }
    }
}
