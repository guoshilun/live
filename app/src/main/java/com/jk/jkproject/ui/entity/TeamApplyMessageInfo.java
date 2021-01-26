package com.jk.jkproject.ui.entity;

public class TeamApplyMessageInfo extends BaseInfo{

  private DataBean data;
  

  public DataBean getData() {
    return this.data;
  }
  


  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }


  public static class DataBean {
    private String remark;
    
    private int team_id;
    
    private String tm_name;
    
    public String getRemark() {
      return this.remark;
    }
    
    public int getTeam_id() {
      return this.team_id;
    }
    
    public String getTm_name() {
      return this.tm_name;
    }
    
    public void setRemark(String param1String) {
      this.remark = param1String;
    }
    
    public void setTeam_id(int param1Int) {
      this.team_id = param1Int;
    }
    
    public void setTm_name(String param1String) {
      this.tm_name = param1String;
    }
  }
}
