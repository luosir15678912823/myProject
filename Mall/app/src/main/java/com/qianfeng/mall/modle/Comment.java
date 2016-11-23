package com.qianfeng.mall.modle;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016-11-10.
 */

public class Comment extends BmobObject {

    String userId;
    String nickName;
    String picUrl;
    String commodityName;
    String commentContent;

    public Comment() {
    }

    public Comment( String userId,String nickName, String picUrl, String commodityName, String commentContent) {
        this.nickName = nickName;
        this.picUrl = picUrl;
        this.commodityName = commodityName;
        this.commentContent = commentContent;
    }

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

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "nickName='" + nickName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}
