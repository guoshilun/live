package com.jk.jkproject.ui.entity;

import java.util.List;

public class TeamMembersInfo extends BaseInfo{

  private List<DataBean> data;
  


  public List<DataBean> getData() {
    return this.data;
  }
  

  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }


  public static class DataBean {
    private int id;
    
    private int team_grade;
    
    private String team_uid;
    
    private String u_dataTime;
    
    private String u_nickName;
    
    private String u_picture;
    
    public int getId() {
      return this.id;
    }
    
    public int getTeam_grade() {
      return this.team_grade;
    }
    
    public String getTeam_uid() {
      return this.team_uid;
    }
    
    public String getU_dataTime() {
      return this.u_dataTime;
    }
    
    public String getU_nickName() {
      return this.u_nickName;
    }
    
    public String getU_picture() {
      return this.u_picture;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setTeam_grade(int param1Int) {
      this.team_grade = param1Int;
    }
    
    public void setTeam_uid(String param1String) {
      this.team_uid = param1String;
    }
    
    public void setU_dataTime(String param1String) {
      this.u_dataTime = param1String;
    }
    
    public void setU_nickName(String param1String) {
      this.u_nickName = param1String;
    }
    
    public void setU_picture(String param1String) {
      this.u_picture = param1String;
    }
  }
}
