package com.jk.jkproject.ui.entity;

import java.util.List;

public class PhonePrefixInfo extends BaseInfo{

  private List<DataBean> data;
  

  public List<DataBean> getData() {
    return this.data;
  }
  

  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }
  

  public static class DataBean {
    private String name;
    
    private String prefix;
    
    public String getName() {
      return this.name;
    }
    
    public String getPrefix() {
      return this.prefix;
    }
    
    public void setName(String param1String) {
      this.name = param1String;
    }
    
    public void setPrefix(String param1String) {
      this.prefix = param1String;
    }
  }
}
