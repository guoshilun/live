package com.jk.jkproject.ui.entity;

import java.io.Serializable;
import java.util.List;

public class TeamCenterInfo extends BaseInfo implements Serializable {

  private List<DataBean> data;
  

  public List<DataBean> getData() {
    return this.data;
  }
  

  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }
  

  public static class DataBean implements Serializable {
    private String captain_name;
    
    private int id;
    
    private int tm_count;
    
    private int tm_max_number;
    
    private String tm_msg;
    
    private String tm_name;
    
    private String tm_url;
    
    public String getCaptain_name() {
      return this.captain_name;
    }
    
    public int getId() {
      return this.id;
    }
    
    public int getTm_count() {
      return this.tm_count;
    }
    
    public int getTm_max_number() {
      return this.tm_max_number;
    }
    
    public String getTm_msg() {
      return this.tm_msg;
    }
    
    public String getTm_name() {
      return this.tm_name;
    }
    
    public String getTm_url() {
      return this.tm_url;
    }
    
    public void setCaptain_name(String param1String) {
      this.captain_name = param1String;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setTm_count(int param1Int) {
      this.tm_count = param1Int;
    }
    
    public void setTm_max_number(int param1Int) {
      this.tm_max_number = param1Int;
    }
    
    public void setTm_msg(String param1String) {
      this.tm_msg = param1String;
    }
    
    public void setTm_name(String param1String) {
      this.tm_name = param1String;
    }
    
    public void setTm_url(String param1String) {
      this.tm_url = param1String;
    }
  }
}
