package com.jk.jkproject.ui.entity;

public class PkSuccessInfo extends BaseInfo {
  private String head;
  
  private String html;
  
  private boolean isRandom;
  
  private String nickname;
  
  private int sex;
  
  private String uid;
  
  private int wait_seconds;
  
  public String getHead() {
    return this.head;
  }
  
  public String getHtml() {
    return this.html;
  }
  
  public String getNickname() {
    return this.nickname;
  }
  
  public int getSex() {
    return this.sex;
  }
  
  public String getUid() {
    return this.uid;
  }
  
  public int getWait_seconds() {
    return this.wait_seconds;
  }
  
  public boolean isRandom() {
    return this.isRandom;
  }
  
  public void setHead(String paramString) {
    this.head = paramString;
  }
  
  public void setHtml(String paramString) {
    this.html = paramString;
  }
  
  public void setNickname(String paramString) {
    this.nickname = paramString;
  }
  
  public void setRandom(boolean paramBoolean) {
    this.isRandom = paramBoolean;
  }
  
  public void setSex(int paramInt) {
    this.sex = paramInt;
  }
  
  public void setUid(String paramString) {
    this.uid = paramString;
  }
  
  public void setWait_seconds(int paramInt) {
    this.wait_seconds = paramInt;
  }
  
  public String toString() {
    return super.toString();
  }
}
