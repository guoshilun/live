package com.jk.jkproject.ui.entity;

public class LiveRoomUserLockBean {
  private String anchorGrade;
  
  private String nickname;
  
  private String picture;
  
  private String sex;
  
  private String sgin;
  
  private String userGrade;
  
  private String userId;
  
  public String getAnchorGrade() {
    return this.anchorGrade;
  }
  
  public String getNickname() {
    return this.nickname;
  }
  
  public String getPicture() {
    return this.picture;
  }
  
  public String getSex() {
    return this.sex;
  }
  
  public String getSgin() {
    return this.sgin;
  }
  
  public String getUserGrade() {
    return this.userGrade;
  }
  
  public String getUserId() {
    return this.userId;
  }
  
  public void setAnchorGrade(String paramString) {
    this.anchorGrade = paramString;
  }
  
  public void setNickname(String paramString) {
    this.nickname = paramString;
  }
  
  public void setPicture(String paramString) {
    this.picture = paramString;
  }
  
  public void setSex(String paramInt) {
    this.sex = paramInt;
  }
  
  public void setSgin(String paramString) {
    this.sgin = paramString;
  }
  
  public void setUserGrade(String paramString) {
    this.userGrade = paramString;
  }
  
  public void setUserId(String paramString) {
    this.userId = paramString;
  }

  @Override
  public String toString() {
    return "LiveRoomUserLockBean{" +
            "anchorGrade='" + anchorGrade + '\'' +
            ", nickname='" + nickname + '\'' +
            ", picture='" + picture + '\'' +
            ", sex=" + sex +
            ", sgin='" + sgin + '\'' +
            ", userGrade='" + userGrade + '\'' +
            ", userId='" + userId + '\'' +
            '}';
  }
}
