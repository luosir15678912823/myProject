package com.qianfeng.mall.modle;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016-11-10.
 */

public class PriseCount extends BmobObject {

    Integer count;
    String commodityName;
    PriseUser priseUser;

    public PriseCount(Integer count, String commodityName, PriseUser priseUser) {
        this.count = count;
        this.commodityName = commodityName;
        this.priseUser = priseUser;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public PriseUser getPriseUser() {
        return priseUser;
    }

    public void setPriseUser(PriseUser priseUser) {
        this.priseUser = priseUser;
    }

    @Override
    public String toString() {
        return "PriseCount{" +
                "count=" + count +
                ", commodityName='" + commodityName + '\'' +
                ", priseUser=" + priseUser +
                '}';
    }
}
