package com.jk.jkproject.ui.entity;

import java.util.List;

public class BlackList extends BaseInfo {
  private List<DataBean> data;
  
  public List<DataBean> getData() {
    return this.data;
  }
  
  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }
  
  public static class DataBean {
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
    
    public void setAnchorGrade(String param1String) {
      this.anchorGrade = param1String;
    }
    
    public void setNickname(String param1String) {
      this.nickname = param1String;
    }
    
    public void setPicture(String param1String) {
      this.picture = param1String;
    }
    
    public void setSex(String param1String) {
      this.sex = param1String;
    }
    
    public void setSgin(String param1String) {
      this.sgin = param1String;
    }
    
    public void setUserGrade(String param1String) {
      this.userGrade = param1String;
    }
    
    public void setUserId(String param1String) {
      this.userId = param1String;
    }
  }
}
