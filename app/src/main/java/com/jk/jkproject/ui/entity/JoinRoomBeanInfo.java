package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class JoinRoomBeanInfo extends BaseInfo implements Serializable {
    private String account;

    private int anchorGrade;

    private int banned;

    private int count;

    private int isAttention;

    private int needExperience;

    private String nickname;

    private int oth;//1 普通用户  2.主播  3.管理员

    private String picture;

    private String playUrl;

    private int ranking;
    private int gameState;

    private String roomName;

    private String userId;

    private String roomId;
    private String streamId;//连麦流Id字段
    private String streamPkId;//PK流Id字段
    private String bId;

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    private String cover;

    private boolean isSkin;

    private boolean isCamera;

    private String pkId;

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getStreamPkId() {
        return streamPkId;
    }

    public void setStreamPkId(String streamPkId) {
        this.streamPkId = streamPkId;
    }

    public boolean isSkin() {
        return isSkin;
    }

    public void setSkin(boolean skin) {
        isSkin = skin;
    }

    public boolean isCamera() {
        return isCamera;
    }

    public void setCamera(boolean camera) {
        isCamera = camera;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAccount() {
        return this.account;
    }

    public int getAnchorGrade() {
        return this.anchorGrade;
    }

    public int getBanned() {
        return this.banned;
    }

    public int getCount() {
        return this.count;
    }

    public int getIsAttention() {
        return this.isAttention;
    }

    public int getNeedExperience() {
        return this.needExperience;
    }

    public String getNickname() {
        return this.nickname;
    }

    public int getOth() {
        return this.oth;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getPlayUrl() {
        return this.playUrl;
    }

    public int getRanking() {
        return this.ranking;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setAccount(String paramString) {
        this.account = paramString;
    }

    public void setAnchorGrade(int paramInt) {
        this.anchorGrade = paramInt;
    }

    public void setBanned(int paramInt) {
        this.banned = paramInt;
    }

    public void setCount(int paramInt) {
        this.count = paramInt;
    }

    public void setIsAttention(int paramInt) {
        this.isAttention = paramInt;
    }

    public void setNeedExperience(int paramInt) {
        this.needExperience = paramInt;
    }

    public void setNickname(String paramString) {
        this.nickname = paramString;
    }

    public void setOth(int paramInt) {
        this.oth = paramInt;
    }

    public void setPicture(String paramString) {
        this.picture = paramString;
    }

    public void setPlayUrl(String paramString) {
        this.playUrl = paramString;
    }

    public void setRanking(int paramInt) {
        this.ranking = paramInt;
    }

    public void setRoomName(String paramString) {
        this.roomName = paramString;
    }

    public void setUserId(String paramString) {
        this.userId = paramString;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    @Override
    public String toString() {
        return "JoinRoomBeanInfo{" +
                "account='" + account + '\'' +
                ", anchorGrade=" + anchorGrade +
                ", banned=" + banned +
                ", count=" + count +
                ", isAttention=" + isAttention +
                ", needExperience=" + needExperience +
                ", nickname='" + nickname + '\'' +
                ", oth=" + oth +
                ", picture='" + picture + '\'' +
                ", playUrl='" + playUrl + '\'' +
                ", ranking=" + ranking +
                ", roomName='" + roomName + '\'' +
                ", userId='" + userId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", streamId='" + streamId + '\'' +
                ", streamPkId='" + streamPkId + '\'' +
                ", cover='" + cover + '\'' +
                ", isSkin=" + isSkin +
                ", isCamera=" + isCamera +
                ", pkId='" + pkId + '\'' +
                '}';
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }
}