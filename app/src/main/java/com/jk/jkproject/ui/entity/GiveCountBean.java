package com.jk.jkproject.ui.entity;

public class GiveCountBean extends BaseInfo{
  private Boolean isSelect;
  
  private String name;
  
  private String num;
  
  public GiveCountBean(String paramString, Boolean paramBoolean) {
    this.name = paramString;
    this.isSelect = paramBoolean;
  }
  
  public GiveCountBean(String paramString1, String paramString2, Boolean paramBoolean) {
    this.name = paramString1;
    this.num = paramString2;
    this.isSelect = paramBoolean;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getNum() {
    return this.num;
  }
  
  public Boolean getSelect() {
    return this.isSelect;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setNum(String paramString) {
    this.num = paramString;
  }
  
  public void setSelect(Boolean paramBoolean) {
    this.isSelect = paramBoolean;
  }
}
