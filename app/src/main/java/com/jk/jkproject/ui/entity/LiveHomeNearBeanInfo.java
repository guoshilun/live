package com.jk.jkproject.ui.entity;

import java.util.List;

public class LiveHomeNearBeanInfo extends BaseInfo {
  private List<DataBean> data;
  
  public List<DataBean> getData() {
    return this.data;
  }
  
  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }
  
  public static class DataBean {
    private String cover;
    
    private String distance;
    
    private int live_type;
    
    private int peopleNumber;
    
    private String r_name;
    
    private int r_state;
    
    private String roomId;
    
    private String u_id;
    
    private String u_name;
    private String r_cover;
    private String u_picture;

    public String getR_cover() {
      return r_cover;
    }

    public void setR_cover(String r_cover) {
      this.r_cover = r_cover;
    }

    public String getU_picture() {
      return u_picture;
    }

    public void setU_picture(String u_picture) {
      this.u_picture = u_picture;
    }

    public String getCover() {
      return this.cover;
    }
    
    public String getDistance() {
      return this.distance;
    }
    
    public int getLive_type() {
      return this.live_type;
    }
    
    public int getPeopleNumber() {
      return this.peopleNumber;
    }
    
    public String getR_name() {
      return this.r_name;
    }
    
    public int getR_state() {
      return this.r_state;
    }
    
    public String getRoomId() {
      return this.roomId;
    }
    
    public String getU_id() {
      return this.u_id;
    }
    
    public String getU_name() {
      return this.u_name;
    }
    
    public void setCover(String param1String) {
      this.cover = param1String;
    }
    
    public void setDistance(String param1String) {
      this.distance = param1String;
    }
    
    public void setLive_type(int param1Int) {
      this.live_type = param1Int;
    }
    
    public void setPeopleNumber(int param1Int) {
      this.peopleNumber = param1Int;
    }
    
    public void setR_name(String param1String) {
      this.r_name = param1String;
    }
    
    public void setR_state(int param1Int) {
      this.r_state = param1Int;
    }
    
    public void setRoomId(String param1String) {
      this.roomId = param1String;
    }
    
    public void setU_id(String param1String) {
      this.u_id = param1String;
    }
    
    public void setU_name(String param1String) {
      this.u_name = param1String;
    }
  }
}
