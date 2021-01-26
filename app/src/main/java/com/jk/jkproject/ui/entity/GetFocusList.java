package com.jk.jkproject.ui.entity;

import java.util.List;

public class GetFocusList extends BaseInfo{

  private List<DataBean> data;
  

  public List<DataBean> getData() {
    return this.data;
  }
  
  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }
  

  public static class DataBean {
    private String nickName;
    
    private String picture;
    
    private int r_state;
    
    private String userId;
    
    public String getNickName() {
      return this.nickName;
    }
    
    public String getPicture() {
      return this.picture;
    }
    
    public int getR_state() {
      return this.r_state;
    }
    
    public String getUserId() {
      return this.userId;
    }
    
    public void setNickName(String param1String) {
      this.nickName = param1String;
    }
    
    public void setPicture(String param1String) {
      this.picture = param1String;
    }
    
    public void setR_state(int param1Int) {
      this.r_state = param1Int;
    }
    
    public void setUserId(String param1String) {
      this.userId = param1String;
    }
  }
}
