package com.jk.jkproject.ui.entity;

public class PkStartBean extends BaseInfo {
  private int close_period;
  
  private boolean isSelf;
  
  private UserInfo other;
  
  private String ownLiveCover;
  
  private String ownNickName;
  
  private String ownUid;
  
  private UserInfo owner;
  
  private int pking_period;
  
  private String push_pk;
  
  private int ready_period;
  
  private String room_name;
  
  private String rtmp_pk;
  
  public int getClose_period() {
    return this.close_period;
  }
  
  public UserInfo getOther() {
    if (this.other == null)
      this.other = new UserInfo(); 
    return this.other;
  }
  
  public String getOwnLiveCover() {
    return this.ownLiveCover;
  }
  
  public String getOwnNickName() {
    return this.ownNickName;
  }
  
  public String getOwnUid() {
    return this.ownUid;
  }
  
  public UserInfo getOwner() {
    if (this.owner == null)
      this.owner = new UserInfo(); 
    return this.owner;
  }
  
  public int getPking_period() {
    return this.pking_period;
  }
  
  public String getPush_pk() {
    return this.push_pk;
  }
  
  public int getReady_period() {
    return this.ready_period;
  }
  
  public String getRoom_name() {
    return this.room_name;
  }
  
  public String getRtmp_pk() {
    return this.rtmp_pk;
  }
  
  public boolean isSelf() {
    return this.isSelf;
  }
  
  public void setClose_period(int paramInt) {
    this.close_period = paramInt;
  }
  
  public void setOther(UserInfo paramUserInfo) {}
  
  public void setOwnLiveCover(String paramString) {
    this.ownLiveCover = paramString;
  }
  
  public void setOwnNickName(String paramString) {
    this.ownNickName = paramString;
  }
  
  public void setOwnUid(String paramString) {
    this.ownUid = paramString;
  }
  
  public void setOwner(UserInfo paramUserInfo) {
    this.owner = paramUserInfo;
  }
  
  public void setPking_period(int paramInt) {
    this.pking_period = paramInt;
  }
  
  public void setPush_pk(String paramString) {
    this.push_pk = paramString;
  }
  
  public void setReady_period(int paramInt) {
    this.ready_period = paramInt;
  }
  
  public void setRoom_name(String paramString) {
    this.room_name = paramString;
  }
  
  public void setRtmp_pk(String paramString) {
    this.rtmp_pk = paramString;
  }
  
  public void setSelf(boolean paramBoolean) {
    this.isSelf = paramBoolean;
  }
}
