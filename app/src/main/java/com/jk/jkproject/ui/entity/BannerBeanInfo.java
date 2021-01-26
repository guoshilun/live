package com.jk.jkproject.ui.entity;

import java.util.List;

public class BannerBeanInfo extends BaseInfo {
  private List<DataBean> data;
  
  public List<DataBean> getData() {
    return this.data;
  }
  
  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }
  
  public static class DataBean {
    private String arrive;
    
    private String columnType;
    
    private String id;
    
    private String imgUrl;

    //1 网址 2 房间id
    private String type;
    
    public String getArrive() {
      return this.arrive;
    }
    
    public String getColumnType() {
      return this.columnType;
    }
    
    public String getId() {
      return this.id;
    }
    
    public String getImgUrl() {
      return this.imgUrl;
    }
    
    public String getType() {
      return this.type;
    }
    
    public void setArrive(String param1String) {
      this.arrive = param1String;
    }
    
    public void setColumnType(String param1String) {
      this.columnType = param1String;
    }
    
    public void setId(String param1String) {
      this.id = param1String;
    }
    
    public void setImgUrl(String param1String) {
      this.imgUrl = param1String;
    }
    
    public void setType(String param1String) {
      this.type = param1String;
    }
  }
}
