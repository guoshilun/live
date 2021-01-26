package com.jk.jkproject.ui.entity;

import java.util.List;

public class LiveRoomContributionInfo extends BaseInfo {
  private DataBean data;
  
  private int sumCount;
  
  public DataBean getData() {
    return this.data;
  }
  
  public int getSumCount() {
    return this.sumCount;
  }
  
  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  
  public void setSumCount(int paramInt) {
    this.sumCount = paramInt;
  }
  
  public static class DataBean {
    private List<ListBean> list;
    
    public List<ListBean> getList() {
      return this.list;
    }
    
    public void setList(List<ListBean> param1List) {
      this.list = param1List;
    }
    
    public static class ListBean {
      private int amount;
      
      private String nickName;
      
      private String picture;
      
      private String userId;
      
      public int getAmount() {
        return this.amount;
      }
      
      public String getNickName() {
        return this.nickName;
      }
      
      public String getPicture() {
        return this.picture;
      }
      
      public String getUserId() {
        return this.userId;
      }
      
      public void setAmount(int param2Int) {
        this.amount = param2Int;
      }
      
      public void setNickName(String param2String) {
        this.nickName = param2String;
      }
      
      public void setPicture(String param2String) {
        this.picture = param2String;
      }
      
      public void setUserId(String param2String) {
        this.userId = param2String;
      }
    }
  }
  
  public static class ListBean {
    private int amount;
    
    private String nickName;
    
    private String picture;
    
    private String userId;
    
    public int getAmount() {
      return this.amount;
    }
    
    public String getNickName() {
      return this.nickName;
    }
    
    public String getPicture() {
      return this.picture;
    }
    
    public String getUserId() {
      return this.userId;
    }
    
    public void setAmount(int param1Int) {
      this.amount = param1Int;
    }
    
    public void setNickName(String param1String) {
      this.nickName = param1String;
    }
    
    public void setPicture(String param1String) {
      this.picture = param1String;
    }
    
    public void setUserId(String param1String) {
      this.userId = param1String;
    }
  }
}