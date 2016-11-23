package com.qianfeng.mall.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016-10-18.
 */
public class GroupBuyToday {


    private int showapi_res_code;
    private String showapi_res_error;

    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        private int ret_code;
        /**
         * title : Highrock天石 男女款连帽羽绒服　80%精选鸭绒，500蓬，20D超轻防钻绒面料，防泼、防风、透气...
         * show_count : 36
         * stock : 423
         * price : 199.00
         * market_price : 898
         * end_time : 2016-10-20 02:00:00
         * img : http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/0/306/306/32/L2VjbW1lL2x2eW91bWFsbC9ncm91cHMvZ29vZHMvMjAxNjEwMTMvMjAxNjEwMTMxNDA4MjMxNTc1LmpwZw==.auto.jpg
         * start_time : 2016-10-18 02:00:00
         * show_time : 1476900000
         * url : http://tuan.7jia2.com/goods-6279.html
         */

        private List<ListBean> list;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            private String title;
            private String show_count;
            private String stock;
            private String price;
            private int market_price;
            private String end_time;
            private String img;
            private String start_time;
            private String show_time;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShow_count() {
                return show_count;
            }

            public void setShow_count(String show_count) {
                this.show_count = show_count;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getMarket_price() {
                return market_price;
            }

            public void setMarket_price(int market_price) {
                this.market_price = market_price;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getShow_time() {
                return show_time;
            }

            public void setShow_time(String show_time) {
                this.show_time = show_time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "title='" + title + '\'' +
                        ", show_count='" + show_count + '\'' +
                        ", stock='" + stock + '\'' +
                        ", price='" + price + '\'' +
                        ", market_price=" + market_price +
                        ", end_time='" + end_time + '\'' +
                        ", img='" + img + '\'' +
                        ", start_time='" + start_time + '\'' +
                        ", show_time='" + show_time + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }
    }
}
