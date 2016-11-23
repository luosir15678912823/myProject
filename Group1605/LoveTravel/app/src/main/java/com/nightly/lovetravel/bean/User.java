package com.nightly.lovetravel.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016-11-03.
 */

public class User extends BmobUser{

    String signtrue;//签名
    String pic;//头像
    String nickName;//昵称



    public String getSigntrue() {
        return signtrue;
    }

    public void setSigntrue(String signtrue) {
        this.signtrue = signtrue;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "signtrue='" + signtrue + '\'' +
                ", pic=" + pic +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}























