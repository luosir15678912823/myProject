package com.qianfeng.mall.modle;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016-11-10.
 */

public class PriseUser extends BmobObject {

    String userId;
    String commodityName;

    public PriseUser(String userId, String commodityName) {
        this.userId = userId;
        this.commodityName = commodityName;
    }

    public PriseUser() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    @Override
    public String toString() {
        return "PriseUser{" +
                "userId='" + userId + '\'' +
                ", commodityName='" + commodityName + '\'' +
                '}';
    }
}
