package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class CritTimes implements Serializable {
  private int crit_diamond;
  
  private int crit_times;
  
  private int index;
  
  public int getCrit_diamond() {
    return this.crit_diamond;
  }
  
  public int getCrit_times() {
    return this.crit_times;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public void setCrit_diamond(int paramInt) {
    this.crit_diamond = paramInt;
  }
  
  public void setCrit_times(int paramInt) {
    this.crit_times = paramInt;
  }
  
  public void setIndex(int paramInt) {
    this.index = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CritTimes{index=");
    stringBuilder.append(this.index);
    stringBuilder.append("crit_times=");
    stringBuilder.append(this.crit_times);
    stringBuilder.append("crit_diamond=");
    stringBuilder.append(this.crit_diamond);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}
