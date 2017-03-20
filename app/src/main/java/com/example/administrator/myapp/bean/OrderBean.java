package com.example.administrator.myapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 * 全部订单
 */

public class OrderBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 夕夕九木旗舰店
         * orderproduct : {"picUrl":"https://img.alicdn.com/bao/uploaded/i1/1777634143/TB23G5QazHz11Bjy0FpXXcNiVXa_!!1777634143.jpg_80x80.jpg","productname":"白色衬衫","price":"31.00"}
         */

        private String title;
        private OrderproductBean orderproduct;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public OrderproductBean getOrderproduct() {
            return orderproduct;
        }

        public void setOrderproduct(OrderproductBean orderproduct) {
            this.orderproduct = orderproduct;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "title='" + title + '\'' +
                    ", orderproduct=" + orderproduct +
                    '}';
        }

        public static class OrderproductBean {
            /**
             * picUrl : https://img.alicdn.com/bao/uploaded/i1/1777634143/TB23G5QazHz11Bjy0FpXXcNiVXa_!!1777634143.jpg_80x80.jpg
             * productname : 白色衬衫
             * price : 31.00
             */

            private String picUrl;
            private String productname;
            private String price;

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getProductname() {
                return productname;
            }

            public void setProductname(String productname) {
                this.productname = productname;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            @Override
            public String toString() {
                return "OrderproductBean{" +
                        "picUrl='" + picUrl + '\'' +
                        ", productname='" + productname + '\'' +
                        ", price='" + price + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "data=" + data +
                '}';
    }
}
