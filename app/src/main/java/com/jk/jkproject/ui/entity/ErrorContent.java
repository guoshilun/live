package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class ErrorContent implements Serializable {
  private int diamond_remain;
  
  public int getDiamond_remain() {
    return this.diamond_remain;
  }
  
  public void setDiamond_remain(int paramInt) {
    this.diamond_remain = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ErrorContent{diamond_remain=");
    stringBuilder.append(this.diamond_remain);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}
