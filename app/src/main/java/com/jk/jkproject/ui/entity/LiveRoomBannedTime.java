package com.jk.jkproject.ui.entity;

import java.util.List;

public class LiveRoomBannedTime extends BaseInfo {
  private List<DataBean> data;
  
  public List<DataBean> getData() {
    return this.data;
  }
  
  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LiveRoomBannedTime{data=");
    stringBuilder.append(this.data);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public static class DataBean {
    private int ProhibitSpeakTime;
    
    private int id;
    
    private String name;
    
    private boolean selected;
    
    public int getId() {
      return this.id;
    }
    
    public String getName() {
      return this.name;
    }
    
    public int getProhibitSpeakTime() {
      return this.ProhibitSpeakTime;
    }
    
    public boolean isSelected() {
      return this.selected;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setName(String param1String) {
      this.name = param1String;
    }
    
    public void setProhibitSpeakTime(int param1Int) {
      this.ProhibitSpeakTime = param1Int;
    }
    
    public void setSelected(boolean param1Boolean) {
      this.selected = param1Boolean;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("DataBean{ProhibitSpeakTime=");
      stringBuilder.append(this.ProhibitSpeakTime);
      stringBuilder.append(", name='");
      stringBuilder.append(this.name);
      stringBuilder.append('\'');
      stringBuilder.append(", id=");
      stringBuilder.append(this.id);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
}
