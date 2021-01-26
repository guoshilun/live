package com.jk.jkproject.ui.entity;

import java.util.List;

public class LiveRankInfoBean extends BaseInfo {
  private List<DataBean> data;
  
  public List<DataBean> getData() {
    return this.data;
  }
  
  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }

  @Override
  public String toString() {
    return "LiveRankInfoBean{" +
            "data=" + data +
            '}';
  }

  public static class DataBean {
    private String anchorGrade;
    
    private int id;
    
    private String nickname;
    
    private String picture;
    
    private int r_state;
    
    private String roomId;
    
    private int state;
    
    private int sum_amount;
    
    private int sum_hot;
    
    private String userId;
    
    public int getId() {
      return this.id;
    }
    
    public String getNickname() {
      return this.nickname;
    }
    
    public String getPicture() {
      return this.picture;
    }
    
    public int getR_state() {
      return this.r_state;
    }
    
    public String getRoomId() {
      return this.roomId;
    }
    
    public int getState() {
      return this.state;
    }
    
    public int getSum_amount() {
      return this.sum_amount;
    }
    
    public int getSum_hot() {
      return this.sum_hot;
    }
    
    public String getUserGrade() {
      return this.anchorGrade;
    }
    
    public String getUserId() {
      return this.userId;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setNickname(String param1String) {
      this.nickname = param1String;
    }
    
    public void setPicture(String param1String) {
      this.picture = param1String;
    }
    
    public void setR_state(int param1Int) {
      this.r_state = param1Int;
    }
    
    public void setRoomId(String param1String) {
      this.roomId = param1String;
    }
    
    public void setState(int param1Int) {
      this.state = param1Int;
    }
    
    public void setSum_amount(int param1Int) {
      this.sum_amount = param1Int;
    }
    
    public void setSum_hot(int param1Int) {
      this.sum_hot = param1Int;
    }
    
    public void setUserGrade(String param1String) {
      this.anchorGrade = param1String;
    }
    
    public void setUserId(String param1String) {
      this.userId = param1String;
    }

    @Override
    public String toString() {
      return "DataBean{" +
              "anchorGrade='" + anchorGrade + '\'' +
              ", id=" + id +
              ", nickname='" + nickname + '\'' +
              ", picture='" + picture + '\'' +
              ", r_state=" + r_state +
              ", roomId='" + roomId + '\'' +
              ", state=" + state +
              ", sum_amount=" + sum_amount +
              ", sum_hot=" + sum_hot +
              ", userId='" + userId + '\'' +
              '}';
    }
  }
}
