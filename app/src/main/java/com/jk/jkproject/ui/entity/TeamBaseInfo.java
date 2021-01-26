package com.jk.jkproject.ui.entity;

public class TeamBaseInfo extends BaseInfo{

  private DataBean data;
  

  public DataBean getData() {
    return this.data;
  }
  

  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  

  public static class DataBean {
    private String create_time;
    
    private int sumCount;
    
    private int team_id;
    
    private int tm_count;
    
    private String tm_msg;
    
    private String tm_name;
    
    private String tm_url;
    
    public String getCreate_time() {
      return this.create_time;
    }
    
    public int getSumCount() {
      return this.sumCount;
    }
    
    public int getTeam_id() {
      return this.team_id;
    }
    
    public int getTm_count() {
      return this.tm_count;
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
    
    public void setCreate_time(String param1String) {
      this.create_time = param1String;
    }
    
    public void setSumCount(int param1Int) {
      this.sumCount = param1Int;
    }
    
    public void setTeam_id(int param1Int) {
      this.team_id = param1Int;
    }
    
    public void setTm_count(int param1Int) {
      this.tm_count = param1Int;
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
