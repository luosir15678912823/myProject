package com.nightly.lovetravel.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016-11-07.
 */

public class ContentBean extends BmobObject{

    AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean;
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean getContentlistBean() {
        return contentlistBean;
    }

    public void setContentlistBean(AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean) {
        this.contentlistBean = contentlistBean;
    }

    //    private String proId;
//    private String summary;
//    private String cityId;
//
//    private AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.LocationBean location;
//    private String cityName;
//    private String areaId;
//    private String id;
//    private String content;
//    private String proName;
//    private String price;
//    private String areaName;
//    private String address;
//    private String name;
//    private String attention;
//    private String coupon;
//    private String opentime;
//    private List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.PriceListBean> priceList;
//    private List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.PicListBean> picList;
}
