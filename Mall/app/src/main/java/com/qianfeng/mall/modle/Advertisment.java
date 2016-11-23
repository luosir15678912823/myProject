package com.qianfeng.mall.modle;

import java.util.List;

/**
 * Created by qf on 2016/10/31.
 */
public class Advertisment {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"noticeList":[{"id":"114341","time":"2016-10-13 11:05","title":"\u201c美丽中国·全国城市定向马拉松\u201d天津站免费报名中","link":"http://www.7jia2.com/article/114341.html"},{"id":"114342","time":"2016-10-17 11:10","title":"七加二商城二周年领礼物攻略","link":"http://www.7jia2.com/article/114342.html"},{"id":"3080","time":"2015-08-03 17:53","title":"教你如何赚积分","link":"http://www.7jia2.com/article/3080.html"},{"id":"114340","time":"2016-09-30 10:43","title":"七加二商城国庆假期发货公告","link":"http://www.7jia2.com/article/114340.html"},{"id":"114335","time":"2016-08-26 11:38","title":"G20峰会温馨提醒\u2014\u2014浙江买家请注意","link":"http://www.7jia2.com/article/114335.html"},{"id":"114333","time":"2016-08-17 11:21","title":"第四期众筹活动获得者公布","link":"http://www.7jia2.com/article/114333.html"},{"id":"114330","time":"2016-08-04 14:54","title":"实用+功能 轻量自由 汉道-生存大师佩刀","link":"http://www.7jia2.com/article/114330.html"},{"id":"114328","time":"2016-07-25 16:25","title":"加拿大风暴stormtech知名户外服装品牌入驻七加二商城","link":"http://www.7jia2.com/article/114328.html"},{"id":"114327","time":"2016-07-25 10:26","title":"一言不合就送优惠券","link":"http://www.7jia2.com/article/114327.html"},{"id":"114319","time":"2016-07-12 10:18","title":"欢迎萨米时光旗舰店入驻七加二商城","link":"http://www.7jia2.com/article/114319.html"},{"id":"114324","time":"2016-07-15 14:14","title":"关于近期APP无法注册问题的说明","link":"http://www.7jia2.com/article/114324.html"},{"id":"114321","time":"2016-07-13 14:06","title":"2016寻光共聚神农架虔心打造靠谱新品","link":"http://www.7jia2.com/article/114321.html"},{"id":"114322","time":"2016-07-14 15:26","title":"午夜嗨购","link":"http://www.7jia2.com/article/114322.html"},{"id":"114320","time":"2016-07-12 11:53","title":"新品不断看点无限 汉道亮相亚洲户外展","link":"http://www.7jia2.com/article/114320.html"},{"id":"114312","time":"2016-07-06 13:29","title":"夏不为利 欢享暑期","link":"http://www.7jia2.com/article/114312.html"},{"id":"114311","time":"2016-07-04 10:35","title":"七加二源一云商零售管理软件分享会圆满结束","link":"http://www.7jia2.com/article/114311.html"},{"id":"114313","time":"2016-07-07 11:16","title":"夏不为利 欢享暑期","link":"http://www.7jia2.com/article/114313.html"},{"id":"114214","time":"2016-02-03 15:40","title":"店铺广告申请功能上线","link":"http://www.7jia2.com/article/114214.html"},{"id":"114310","time":"2016-07-04 10:33","title":"户外精英沙龙精髓辩论实录","link":"http://www.7jia2.com/article/114310.html"},{"id":"114304","time":"2016-06-22 09:34","title":"关于网站升级改版的公告","link":"http://www.7jia2.com/article/114304.html"},{"id":"114302","time":"2016-06-15 15:55","title":"北京徒步攻略里找不到的虐人线路:三山五虐线","link":"http://www.7jia2.com/article/114302.html"},{"id":"114281","time":"2016-05-17 11:00","title":"抢百万红包攻略","link":"http://www.7jia2.com/article/114281.html"}],"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * noticeList : [{"id":"114341","time":"2016-10-13 11:05","title":"\u201c美丽中国·全国城市定向马拉松\u201d天津站免费报名中","link":"http://www.7jia2.com/article/114341.html"},{"id":"114342","time":"2016-10-17 11:10","title":"七加二商城二周年领礼物攻略","link":"http://www.7jia2.com/article/114342.html"},{"id":"3080","time":"2015-08-03 17:53","title":"教你如何赚积分","link":"http://www.7jia2.com/article/3080.html"},{"id":"114340","time":"2016-09-30 10:43","title":"七加二商城国庆假期发货公告","link":"http://www.7jia2.com/article/114340.html"},{"id":"114335","time":"2016-08-26 11:38","title":"G20峰会温馨提醒\u2014\u2014浙江买家请注意","link":"http://www.7jia2.com/article/114335.html"},{"id":"114333","time":"2016-08-17 11:21","title":"第四期众筹活动获得者公布","link":"http://www.7jia2.com/article/114333.html"},{"id":"114330","time":"2016-08-04 14:54","title":"实用+功能 轻量自由 汉道-生存大师佩刀","link":"http://www.7jia2.com/article/114330.html"},{"id":"114328","time":"2016-07-25 16:25","title":"加拿大风暴stormtech知名户外服装品牌入驻七加二商城","link":"http://www.7jia2.com/article/114328.html"},{"id":"114327","time":"2016-07-25 10:26","title":"一言不合就送优惠券","link":"http://www.7jia2.com/article/114327.html"},{"id":"114319","time":"2016-07-12 10:18","title":"欢迎萨米时光旗舰店入驻七加二商城","link":"http://www.7jia2.com/article/114319.html"},{"id":"114324","time":"2016-07-15 14:14","title":"关于近期APP无法注册问题的说明","link":"http://www.7jia2.com/article/114324.html"},{"id":"114321","time":"2016-07-13 14:06","title":"2016寻光共聚神农架虔心打造靠谱新品","link":"http://www.7jia2.com/article/114321.html"},{"id":"114322","time":"2016-07-14 15:26","title":"午夜嗨购","link":"http://www.7jia2.com/article/114322.html"},{"id":"114320","time":"2016-07-12 11:53","title":"新品不断看点无限 汉道亮相亚洲户外展","link":"http://www.7jia2.com/article/114320.html"},{"id":"114312","time":"2016-07-06 13:29","title":"夏不为利 欢享暑期","link":"http://www.7jia2.com/article/114312.html"},{"id":"114311","time":"2016-07-04 10:35","title":"七加二源一云商零售管理软件分享会圆满结束","link":"http://www.7jia2.com/article/114311.html"},{"id":"114313","time":"2016-07-07 11:16","title":"夏不为利 欢享暑期","link":"http://www.7jia2.com/article/114313.html"},{"id":"114214","time":"2016-02-03 15:40","title":"店铺广告申请功能上线","link":"http://www.7jia2.com/article/114214.html"},{"id":"114310","time":"2016-07-04 10:33","title":"户外精英沙龙精髓辩论实录","link":"http://www.7jia2.com/article/114310.html"},{"id":"114304","time":"2016-06-22 09:34","title":"关于网站升级改版的公告","link":"http://www.7jia2.com/article/114304.html"},{"id":"114302","time":"2016-06-15 15:55","title":"北京徒步攻略里找不到的虐人线路:三山五虐线","link":"http://www.7jia2.com/article/114302.html"},{"id":"114281","time":"2016-05-17 11:00","title":"抢百万红包攻略","link":"http://www.7jia2.com/article/114281.html"}]
     * ret_code : 0
     */

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
         * id : 114341
         * time : 2016-10-13 11:05
         * title : “美丽中国·全国城市定向马拉松”天津站免费报名中
         * link : http://www.7jia2.com/article/114341.html
         */

        private List<NoticeListBean> noticeList;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<NoticeListBean> getNoticeList() {
            return noticeList;
        }

        public void setNoticeList(List<NoticeListBean> noticeList) {
            this.noticeList = noticeList;
        }

        public static class NoticeListBean {
            private String id;
            private String time;
            private String title;
            private String link;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
