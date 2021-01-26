package com.jk.jkproject.ui.entity;

import com.google.gson.annotations.SerializedName;

public class UserInfo extends BaseInfo {
  private DataBean data;
  
  public DataBean getData() {
    return this.data;
  }
  
  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  
  public static class DataBean {
    private String account;
    
    private String anchorExperience;
    
    private String anchorGrade;
    
    private String autonymStatus;
    
    private String background;
    
    private String birthday;
    
    private String equipment;
    
    private String ip;
    
    private String nickName;
    
    private String onlineTime;
    
    private String phone;
    
    private String picture;
    
    private String pwdStatus;
    
    private String region;
    
    private String sex;
    
    private String sgin;
    
    @SerializedName("status")
    private String statusX;
    
    private String type;
    
    private String userExperience;
    
    private String userGrade;

    //缺少字段
    private String userId;
    private String isAttention;
    private String liveStatus;
    private int  attention;
    private int  fans;

    public int getFans() {
      return fans;
    }

    public void setFans(int fans) {
      this.fans = fans;
    }

    public String getLiveStatus() {
      return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
      this.liveStatus = liveStatus;
    }

    public int getAttention() {
      return attention;
    }

    public void setAttention(int attention) {
      this.attention = attention;
    }


    public String getAccount() {
      return this.account;
    }
    
    public String getAnchorExperience() {
      return this.anchorExperience;
    }
    
    public String getAnchorGrade() {
      return this.anchorGrade;
    }
    
    public String getAutonymStatus() {
      return this.autonymStatus;
    }
    
    public String getBackground() {
      return this.background;
    }
    
    public String getBirthday() {
      return this.birthday;
    }
    
    public String getEquipment() {
      return this.equipment;
    }
    
    public String getIp() {
      return this.ip;
    }
    
    public String getNickName() {
      return this.nickName;
    }
    
    public String getOnlineTime() {
      return this.onlineTime;
    }
    
    public String getPhone() {
      return this.phone;
    }
    
    public String getPicture() {
      return this.picture;
    }
    
    public String getPwdStatus() {
      return this.pwdStatus;
    }
    
    public String getRegion() {
      return this.region;
    }
    
    public String getSex() {
      return this.sex;
    }
    
    public String getSgin() {
      return this.sgin;
    }
    
    public String getStatusX() {
      return this.statusX;
    }
    
    public String getType() {
      return this.type;
    }
    
    public String getUserExperience() {
      return this.userExperience;
    }
    
    public String getUserGrade() {
      return this.userGrade;
    }
    
    public void setAccount(String param1String) {
      this.account = param1String;
    }
    
    public void setAnchorExperience(String param1String) {
      this.anchorExperience = param1String;
    }
    
    public void setAnchorGrade(String param1String) {
      this.anchorGrade = param1String;
    }
    
    public void setAutonymStatus(String param1String) {
      this.autonymStatus = param1String;
    }
    
    public void setBackground(String param1String) {
      this.background = param1String;
    }
    
    public void setBirthday(String param1String) {
      this.birthday = param1String;
    }
    
    public void setEquipment(String param1String) {
      this.equipment = param1String;
    }
    
    public void setIp(String param1String) {
      this.ip = param1String;
    }
    
    public void setNickName(String param1String) {
      this.nickName = param1String;
    }
    
    public void setOnlineTime(String param1String) {
      this.onlineTime = param1String;
    }
    
    public void setPhone(String param1String) {
      this.phone = param1String;
    }
    
    public void setPicture(String param1String) {
      this.picture = param1String;
    }
    
    public void setPwdStatus(String param1String) {
      this.pwdStatus = param1String;
    }
    
    public void setRegion(String param1String) {
      this.region = param1String;
    }
    
    public void setSex(String param1String) {
      this.sex = param1String;
    }
    
    public void setSgin(String param1String) {
      this.sgin = param1String;
    }
    
    public void setStatusX(String param1String) {
      this.statusX = param1String;
    }
    
    public void setType(String param1String) {
      this.type = param1String;
    }
    
    public void setUserExperience(String param1String) {
      this.userExperience = param1String;
    }
    
    public void setUserGrade(String param1String) {
      this.userGrade = param1String;
    }

    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }

    public String getIsAttention() {
      return isAttention;
    }

    public void setIsAttention(String isAttention) {
      this.isAttention = isAttention;
    }
  }
}
