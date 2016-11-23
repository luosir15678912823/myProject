package com.qianfeng.mall.modle;

/**
 * Created by qf on 2016/10/22.
 */
public class RightClassBean {

    String name;
    String url;

    public RightClassBean(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RightClassBean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
