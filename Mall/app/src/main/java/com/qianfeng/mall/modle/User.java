package com.qianfeng.mall.modle;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016-11-09.
 */

public class User extends BmobUser{

    String nickName;
    String picUrl;
    String signature;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
