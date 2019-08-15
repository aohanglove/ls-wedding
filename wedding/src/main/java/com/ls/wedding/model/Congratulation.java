package com.ls.wedding.model;

import java.util.Date;

public class Congratulation {

    private long id;
    private String openId;
    private String nickName;
    private String head_imgurl;
    private String msg;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHead_imgurl() {
        return head_imgurl;
    }

    public void setHead_imgurl(String head_imgurl) {
        this.head_imgurl = head_imgurl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
