package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class ViewSelectBean implements Serializable {
  private static final long serialVersionUID = -5102109576617005077L;
  
  private long _id;
  
  private long duration;
  
  private int height;
  
  private String name = "";
  
  private int width;
  
  public long getDuration() {
    return this.duration;
  }
  
  public int getHeight() {
    return this.height;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getWidth() {
    return this.width;
  }
  
  public long get_id() {
    return this._id;
  }
  
  public void setDuration(long paramLong) {
    this.duration = paramLong;
  }
  
  public void setHeight(int paramInt) {
    this.height = paramInt;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setWidth(int paramInt) {
    this.width = paramInt;
  }
  
  public void set_id(long paramLong) {
    this._id = paramLong;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("name:");
    stringBuilder.append(this.name);
    stringBuilder.append("\nduration:");
    stringBuilder.append(this.duration);
    stringBuilder.append("\nwidth:");
    stringBuilder.append(this.width);
    stringBuilder.append("\nheight:");
    stringBuilder.append(this.height);
    return stringBuilder.toString();
  }
}
