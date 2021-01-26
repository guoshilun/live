package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class LiveClosePushInfo  implements Serializable {

    /**
     * string roomId=1;
     * string nickname=2; //昵称
     * string account=3;//账号
     * int32 isAttention=4;//是否关注   0.是未关注 1.是关注
     * string picture=5;//头像
     * string anchorGrade=6;//主播等级
     * int32 oth=7;//扩展字段
     */
    private String roomId;
    private String nickName;
    private String account;
    private int isAttention;
    private String picture;
    private String anchorGrade;
    private int oth;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAnchorGrade() {
        return anchorGrade;
    }

    public void setAnchorGrade(String anchorGrade) {
        this.anchorGrade = anchorGrade;
    }

    public int getOth() {
        return oth;
    }

    public void setOth(int oth) {
        this.oth = oth;
    }

    @Override
    public String toString() {
        return "LiveClosePushInfo{" +
                "roomId='" + roomId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", account='" + account + '\'' +
                ", isAttention=" + isAttention +
                ", picture='" + picture + '\'' +
                ", anchorGrade='" + anchorGrade + '\'' +
                ", oth=" + oth +
                '}';
    }
}
