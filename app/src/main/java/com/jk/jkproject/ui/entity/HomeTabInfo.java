package com.jk.jkproject.ui.entity;

public class HomeTabInfo extends BaseInfo{
  private int count;
  
  private int id;
  
  private boolean isSelected;
  
  private String titleName;
  
  public HomeTabInfo(String paramString, int paramInt, boolean paramBoolean) {
    this.titleName = paramString;
    this.isSelected = paramBoolean;
    this.count = paramInt;
  }
  
  public HomeTabInfo(String paramString, boolean paramBoolean, int paramInt) {
    this.titleName = paramString;
    this.isSelected = paramBoolean;
    this.id = paramInt;
  }
  
  public int getCount() {
    return this.count;
  }
  
  public int getId() {
    return this.id;
  }
  
  public String getTitleName() {
    return this.titleName;
  }
  
  public boolean isSelected() {
    return this.isSelected;
  }
  
  public void setCount(int paramInt) {
    this.count = paramInt;
  }
  
  public void setId(int paramInt) {
    this.id = paramInt;
  }
  
  public void setSelected(boolean paramBoolean) {
    this.isSelected = paramBoolean;
  }
  
  public void setTitleName(String paramString) {
    this.titleName = paramString;
  }
}

