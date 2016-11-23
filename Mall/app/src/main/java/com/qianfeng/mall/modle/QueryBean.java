package com.qianfeng.mall.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qf on 2016/10/22.
 */
public class QueryBean {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"pageBean":{"contentlist":[{"id":"67305","price":"￥239.00","name":"【尊驴尊享】K2summit/凯图巅峰  男款三合一冲锋衣 防水保暖透气两件套冲锋衣 K213FMAA13025","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzMwNS8yMDE2MDkzMC8yMDE2MDkzMDE1MDMwNTY1MDEuanBlZw==.auto.jpg"},{"id":"67306","price":"￥319.00","name":"【尊驴尊享】K2summit/凯图巅峰 男款三合一冲锋衣 防水透气保暖两件套冲锋衣","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/9/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzMwNi8yMDE2MDkzMC8yMDE2MDkzMDE1MDI1NTg2MjkuanBlZw==.auto.jpg"},{"id":"67243","price":"￥459.00","name":"TECTOP/探拓 户外服装男女三合一冲锋衣秋冬情侣款全压胶两件套羽绒内胆JW6589","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zNjEwOTE2My8yMDE2MDkyNi8yMDE2MDkyNjIzMTEwMTU1MDguanBn.auto.jpg"},{"id":"52609","price":"￥699.00","name":"布来亚克BLACKYAK 男款gore-tex棉三合一冲锋衣FPM553","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/e/5/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81MjYwOS8yMDE2MDkyNy8yMDE2MDkyNzA5MjI1NzUyNjkuanBn.auto.jpg"},{"id":"47296","price":"￥528.00","name":"KAILAS 凯乐石防风透气男女款三合一冲锋衣 KG110044/KG120044","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/b/c/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80NzI5Ni8yMDE0MTAwOS8yMDE0MTAwOTE1MTcwMjk4NjUuanBn.auto.jpg"},{"id":"46949","price":"￥1389.00","name":"布来亚克BLACKYAK三合一冲锋衣FPM557","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80Njk0OS8yMDE2MDkyNy8yMDE2MDkyNzA5MjEzNDM0MTYuanBn.auto.jpg"},{"id":"59576","price":"￥850.00","name":"Kailas凯乐石EVENT透气保暖可拆三合一冲锋衣KG110015 120015","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/3/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8xNjgwNzczMS8yMDE1MDkxMC8yMDE1MDkxMDE1NDkzNDg4MzAuanBn.auto.jpg"},{"id":"54290","price":"￥499.00","name":"TREKSTA 特瑞达 男士 GORE-TEX 冲锋衣 三合一，圆通包邮，偏远地区除外","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/0/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81NDI5MC8yMDE1MTEwMy8yMDE1MTEwMzExMDMxMjM4MzIuanBn.auto.jpg"},{"id":"59123","price":"￥1379.00","name":"【尊驴尊享】布来亚克BLACKYAK 男款羽绒三合一冲锋衣FPM551","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81OTEyMy8yMDE1MDgxOS8yMDE1MDgxOTE1MjAxOTY1OTQuanBn.auto.jpg"},{"id":"49039","price":"￥599.00","name":"kailas凯乐石 男款远征两件套三合一冲锋衣KG110014","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80OTAzOS8yMDE1MTAyNy8yMDE1MTAyNzE5NTczMTgyMDIuanBn.auto.jpg"},{"id":"61027","price":"￥599.00","name":"商场同款 KAILAS凯乐石男女款三合一冲锋衣远征发现KG110014","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/7/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MTAyNy8yMDE1MTExOS8yMDE1MTExOTEwMjY1Mzg0MjUuanBlZw==.auto.jpg"},{"id":"29069","price":"￥599.00","name":"Pinewood磐雾 两件套  男 三合一套棉 冲锋衣防风防雨CM122009","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/7/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8yOTA2OS8yMDE1MDEwOS8yMDE1MDEwOTA5NTEwODU4ODcuanBn.auto.jpg"},{"id":"61788","price":"￥1880.00","name":"爬山虎登峰系列男款三合一 HY10MA05","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/1/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MTc4OC8yMDE1MTIyOC8yMDE1MTIyODEzNDIyMDYzMDQuanBn.auto.jpg"},{"id":"49159","price":"￥1999.00","name":"Marmot土拨鼠男款三合一冲锋衣40320","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/8/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80OTE1OS8yMDE1MTEwOS8yMDE1MTEwOTEwNDg0MDIyNDMuanBn.auto.jpg"},{"id":"31060","price":"￥368.00","name":"XLIMIT艾利米特 两件套冲锋衣 男女款 三合一 防水透气防风 棉内胆可拆卸","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9zdG9yZV8zNjk1NzU2MC9nb29kc182NS8yMDEzMDMzMDE1NTQyNTIwMDkuanBlZw==.auto.jpg"},{"id":"60008","price":"￥899.00","name":"VAUDE沃德凯途 男款棉内胆三合一冲锋衣 1215033","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MDAwOC8yMDE2MDMxMC8yMDE2MDMxMDE3NDE0Njc4NjYuanBn.auto.jpg"},{"id":"59643","price":"￥1799.00","name":"Marmot土拨鼠轻量三合一冲锋衣2015秋冬男抓绒内胆防水保暖40720","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/6/4/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81OTY0My8yMDE1MTIwNy8yMDE1MTIwNzE0MTMyODU1NTAuanBn.auto.jpg"},{"id":"67347","price":"￥288.00","name":"K2summit/凯图巅峰 男款三合一防水透气保暖 棉内胆两件套冲锋衣","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzM0Ny8yMDE2MTAwOS8yMDE2MTAwOTEzMTIzNzM4MzAuanBlZw==.auto.jpg"},{"id":"36343","price":"￥399.00","name":"奥特山OUTSHINE 户外冲锋衣 男 两件套三合一抓绒防风防水M123","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/b/0/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zNzMzMzM5MS8yMDE0MDIxOC8yMDE0MDIxODEwMjQyNTIwNjUuanBn.auto.jpg"},{"id":"49993","price":"￥979.00","name":"Jack wolfskin冬季男士户外三合一冲锋衣防寒保暖透气舒适5001472","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/f/8/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zMzg5MTc2NC8yMDE0MTIwNS8yMDE0MTIwNTEwNDcyODY4NzQuanBn.auto.jpg"}],"currentPage":"1","maxResult":"20"}}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * ret_code : 0
     * pageBean : {"contentlist":[{"id":"67305","price":"￥239.00","name":"【尊驴尊享】K2summit/凯图巅峰  男款三合一冲锋衣 防水保暖透气两件套冲锋衣 K213FMAA13025","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzMwNS8yMDE2MDkzMC8yMDE2MDkzMDE1MDMwNTY1MDEuanBlZw==.auto.jpg"},{"id":"67306","price":"￥319.00","name":"【尊驴尊享】K2summit/凯图巅峰 男款三合一冲锋衣 防水透气保暖两件套冲锋衣","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/9/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzMwNi8yMDE2MDkzMC8yMDE2MDkzMDE1MDI1NTg2MjkuanBlZw==.auto.jpg"},{"id":"67243","price":"￥459.00","name":"TECTOP/探拓 户外服装男女三合一冲锋衣秋冬情侣款全压胶两件套羽绒内胆JW6589","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zNjEwOTE2My8yMDE2MDkyNi8yMDE2MDkyNjIzMTEwMTU1MDguanBn.auto.jpg"},{"id":"52609","price":"￥699.00","name":"布来亚克BLACKYAK 男款gore-tex棉三合一冲锋衣FPM553","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/e/5/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81MjYwOS8yMDE2MDkyNy8yMDE2MDkyNzA5MjI1NzUyNjkuanBn.auto.jpg"},{"id":"47296","price":"￥528.00","name":"KAILAS 凯乐石防风透气男女款三合一冲锋衣 KG110044/KG120044","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/b/c/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80NzI5Ni8yMDE0MTAwOS8yMDE0MTAwOTE1MTcwMjk4NjUuanBn.auto.jpg"},{"id":"46949","price":"￥1389.00","name":"布来亚克BLACKYAK三合一冲锋衣FPM557","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80Njk0OS8yMDE2MDkyNy8yMDE2MDkyNzA5MjEzNDM0MTYuanBn.auto.jpg"},{"id":"59576","price":"￥850.00","name":"Kailas凯乐石EVENT透气保暖可拆三合一冲锋衣KG110015 120015","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/3/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8xNjgwNzczMS8yMDE1MDkxMC8yMDE1MDkxMDE1NDkzNDg4MzAuanBn.auto.jpg"},{"id":"54290","price":"￥499.00","name":"TREKSTA 特瑞达 男士 GORE-TEX 冲锋衣 三合一，圆通包邮，偏远地区除外","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/0/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81NDI5MC8yMDE1MTEwMy8yMDE1MTEwMzExMDMxMjM4MzIuanBn.auto.jpg"},{"id":"59123","price":"￥1379.00","name":"【尊驴尊享】布来亚克BLACKYAK 男款羽绒三合一冲锋衣FPM551","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81OTEyMy8yMDE1MDgxOS8yMDE1MDgxOTE1MjAxOTY1OTQuanBn.auto.jpg"},{"id":"49039","price":"￥599.00","name":"kailas凯乐石 男款远征两件套三合一冲锋衣KG110014","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80OTAzOS8yMDE1MTAyNy8yMDE1MTAyNzE5NTczMTgyMDIuanBn.auto.jpg"},{"id":"61027","price":"￥599.00","name":"商场同款 KAILAS凯乐石男女款三合一冲锋衣远征发现KG110014","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/7/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MTAyNy8yMDE1MTExOS8yMDE1MTExOTEwMjY1Mzg0MjUuanBlZw==.auto.jpg"},{"id":"29069","price":"￥599.00","name":"Pinewood磐雾 两件套  男 三合一套棉 冲锋衣防风防雨CM122009","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/7/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8yOTA2OS8yMDE1MDEwOS8yMDE1MDEwOTA5NTEwODU4ODcuanBn.auto.jpg"},{"id":"61788","price":"￥1880.00","name":"爬山虎登峰系列男款三合一 HY10MA05","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/1/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MTc4OC8yMDE1MTIyOC8yMDE1MTIyODEzNDIyMDYzMDQuanBn.auto.jpg"},{"id":"49159","price":"￥1999.00","name":"Marmot土拨鼠男款三合一冲锋衣40320","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/8/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80OTE1OS8yMDE1MTEwOS8yMDE1MTEwOTEwNDg0MDIyNDMuanBn.auto.jpg"},{"id":"31060","price":"￥368.00","name":"XLIMIT艾利米特 两件套冲锋衣 男女款 三合一 防水透气防风 棉内胆可拆卸","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9zdG9yZV8zNjk1NzU2MC9nb29kc182NS8yMDEzMDMzMDE1NTQyNTIwMDkuanBlZw==.auto.jpg"},{"id":"60008","price":"￥899.00","name":"VAUDE沃德凯途 男款棉内胆三合一冲锋衣 1215033","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MDAwOC8yMDE2MDMxMC8yMDE2MDMxMDE3NDE0Njc4NjYuanBn.auto.jpg"},{"id":"59643","price":"￥1799.00","name":"Marmot土拨鼠轻量三合一冲锋衣2015秋冬男抓绒内胆防水保暖40720","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/6/4/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81OTY0My8yMDE1MTIwNy8yMDE1MTIwNzE0MTMyODU1NTAuanBn.auto.jpg"},{"id":"67347","price":"￥288.00","name":"K2summit/凯图巅峰 男款三合一防水透气保暖 棉内胆两件套冲锋衣","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzM0Ny8yMDE2MTAwOS8yMDE2MTAwOTEzMTIzNzM4MzAuanBlZw==.auto.jpg"},{"id":"36343","price":"￥399.00","name":"奥特山OUTSHINE 户外冲锋衣 男 两件套三合一抓绒防风防水M123","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/b/0/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zNzMzMzM5MS8yMDE0MDIxOC8yMDE0MDIxODEwMjQyNTIwNjUuanBn.auto.jpg"},{"id":"49993","price":"￥979.00","name":"Jack wolfskin冬季男士户外三合一冲锋衣防寒保暖透气舒适5001472","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/f/8/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zMzg5MTc2NC8yMDE0MTIwNS8yMDE0MTIwNTEwNDcyODY4NzQuanBn.auto.jpg"}],"currentPage":"1","maxResult":"20"}
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
         * contentlist : [{"id":"67305","price":"￥239.00","name":"【尊驴尊享】K2summit/凯图巅峰  男款三合一冲锋衣 防水保暖透气两件套冲锋衣 K213FMAA13025","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzMwNS8yMDE2MDkzMC8yMDE2MDkzMDE1MDMwNTY1MDEuanBlZw==.auto.jpg"},{"id":"67306","price":"￥319.00","name":"【尊驴尊享】K2summit/凯图巅峰 男款三合一冲锋衣 防水透气保暖两件套冲锋衣","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/9/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzMwNi8yMDE2MDkzMC8yMDE2MDkzMDE1MDI1NTg2MjkuanBlZw==.auto.jpg"},{"id":"67243","price":"￥459.00","name":"TECTOP/探拓 户外服装男女三合一冲锋衣秋冬情侣款全压胶两件套羽绒内胆JW6589","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zNjEwOTE2My8yMDE2MDkyNi8yMDE2MDkyNjIzMTEwMTU1MDguanBn.auto.jpg"},{"id":"52609","price":"￥699.00","name":"布来亚克BLACKYAK 男款gore-tex棉三合一冲锋衣FPM553","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/e/5/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81MjYwOS8yMDE2MDkyNy8yMDE2MDkyNzA5MjI1NzUyNjkuanBn.auto.jpg"},{"id":"47296","price":"￥528.00","name":"KAILAS 凯乐石防风透气男女款三合一冲锋衣 KG110044/KG120044","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/b/c/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80NzI5Ni8yMDE0MTAwOS8yMDE0MTAwOTE1MTcwMjk4NjUuanBn.auto.jpg"},{"id":"46949","price":"￥1389.00","name":"布来亚克BLACKYAK三合一冲锋衣FPM557","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80Njk0OS8yMDE2MDkyNy8yMDE2MDkyNzA5MjEzNDM0MTYuanBn.auto.jpg"},{"id":"59576","price":"￥850.00","name":"Kailas凯乐石EVENT透气保暖可拆三合一冲锋衣KG110015 120015","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/3/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8xNjgwNzczMS8yMDE1MDkxMC8yMDE1MDkxMDE1NDkzNDg4MzAuanBn.auto.jpg"},{"id":"54290","price":"￥499.00","name":"TREKSTA 特瑞达 男士 GORE-TEX 冲锋衣 三合一，圆通包邮，偏远地区除外","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/0/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81NDI5MC8yMDE1MTEwMy8yMDE1MTEwMzExMDMxMjM4MzIuanBn.auto.jpg"},{"id":"59123","price":"￥1379.00","name":"【尊驴尊享】布来亚克BLACKYAK 男款羽绒三合一冲锋衣FPM551","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81OTEyMy8yMDE1MDgxOS8yMDE1MDgxOTE1MjAxOTY1OTQuanBn.auto.jpg"},{"id":"49039","price":"￥599.00","name":"kailas凯乐石 男款远征两件套三合一冲锋衣KG110014","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80OTAzOS8yMDE1MTAyNy8yMDE1MTAyNzE5NTczMTgyMDIuanBn.auto.jpg"},{"id":"61027","price":"￥599.00","name":"商场同款 KAILAS凯乐石男女款三合一冲锋衣远征发现KG110014","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/7/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MTAyNy8yMDE1MTExOS8yMDE1MTExOTEwMjY1Mzg0MjUuanBlZw==.auto.jpg"},{"id":"29069","price":"￥599.00","name":"Pinewood磐雾 两件套  男 三合一套棉 冲锋衣防风防雨CM122009","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/7/d/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8yOTA2OS8yMDE1MDEwOS8yMDE1MDEwOTA5NTEwODU4ODcuanBn.auto.jpg"},{"id":"61788","price":"￥1880.00","name":"爬山虎登峰系列男款三合一 HY10MA05","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/1/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MTc4OC8yMDE1MTIyOC8yMDE1MTIyODEzNDIyMDYzMDQuanBn.auto.jpg"},{"id":"49159","price":"￥1999.00","name":"Marmot土拨鼠男款三合一冲锋衣40320","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/8/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy80OTE1OS8yMDE1MTEwOS8yMDE1MTEwOTEwNDg0MDIyNDMuanBn.auto.jpg"},{"id":"31060","price":"￥368.00","name":"XLIMIT艾利米特 两件套冲锋衣 男女款 三合一 防水透气防风 棉内胆可拆卸","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9zdG9yZV8zNjk1NzU2MC9nb29kc182NS8yMDEzMDMzMDE1NTQyNTIwMDkuanBlZw==.auto.jpg"},{"id":"60008","price":"￥899.00","name":"VAUDE沃德凯途 男款棉内胆三合一冲锋衣 1215033","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/a/b/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82MDAwOC8yMDE2MDMxMC8yMDE2MDMxMDE3NDE0Njc4NjYuanBn.auto.jpg"},{"id":"59643","price":"￥1799.00","name":"Marmot土拨鼠轻量三合一冲锋衣2015秋冬男抓绒内胆防水保暖40720","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/6/4/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy81OTY0My8yMDE1MTIwNy8yMDE1MTIwNzE0MTMyODU1NTAuanBn.auto.jpg"},{"id":"67347","price":"￥288.00","name":"K2summit/凯图巅峰 男款三合一防水透气保暖 棉内胆两件套冲锋衣","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/5/6/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzM0Ny8yMDE2MTAwOS8yMDE2MTAwOTEzMTIzNzM4MzAuanBlZw==.auto.jpg"},{"id":"36343","price":"￥399.00","name":"奥特山OUTSHINE 户外冲锋衣 男 两件套三合一抓绒防风防水M123","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/b/0/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zNzMzMzM5MS8yMDE0MDIxOC8yMDE0MDIxODEwMjQyNTIwNjUuanBn.auto.jpg"},{"id":"49993","price":"￥979.00","name":"Jack wolfskin冬季男士户外三合一冲锋衣防寒保暖透气舒适5001472","img":"http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/f/8/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy8zMzg5MTc2NC8yMDE0MTIwNS8yMDE0MTIwNTEwNDcyODY4NzQuanBn.auto.jpg"}]
         * currentPage : 1
         * maxResult : 20
         */

        private PageBeanBean pageBean;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PageBeanBean getPageBean() {
            return pageBean;
        }

        public void setPageBean(PageBeanBean pageBean) {
            this.pageBean = pageBean;
        }

        public static class PageBeanBean {
            private String currentPage;
            private String maxResult;
            /**
             * id : 67305
             * price : ￥239.00
             * name : 【尊驴尊享】K2summit/凯图巅峰  男款三合一冲锋衣 防水保暖透气两件套冲锋衣 K213FMAA13025
             * img : http://autoimg.7jia2.com/ecmme/lvyoumall/thumbimg/2/7/230/230/32/L2VjbW1lL2x2eW91bWFsbC9nb29kcy9nb29kcy82NzMwNS8yMDE2MDkzMC8yMDE2MDkzMDE1MDMwNTY1MDEuanBlZw==.auto.jpg
             */

            private List<ContentlistBean> contentlist;

            public String getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(String currentPage) {
                this.currentPage = currentPage;
            }

            public String getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(String maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean implements Serializable {
                private String id;
                private String price;
                private String name;
                private String img;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                @Override
                public String toString() {
                    return "ContentlistBean{" +
                            "id='" + id + '\'' +
                            ", price='" + price + '\'' +
                            ", name='" + name + '\'' +
                            ", img='" + img + '\'' +
                            '}';
                }
            }
        }
    }
}
