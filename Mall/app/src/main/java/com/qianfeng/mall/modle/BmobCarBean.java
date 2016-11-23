package com.qianfeng.mall.modle;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016-11-09.
 */

public class BmobCarBean extends BmobObject {


    String userId;
    String name;
    String imgUrl;

    public BmobCarBean(String userId, String name, String imgUrl) {
        this.userId = userId;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public BmobCarBean() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "BmobCarBean{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
