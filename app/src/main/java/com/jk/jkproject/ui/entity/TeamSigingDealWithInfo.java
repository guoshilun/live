package com.jk.jkproject.ui.entity;

import java.util.List;

public class TeamSigingDealWithInfo extends BaseInfo {

  private List<DataBean> data;
  


  public List<DataBean> getData() {
    return this.data;
  }
  

  public void setData(List<DataBean> paramList) {
    this.data = paramList;
  }
  

  public static class DataBean {
    private String create_time;
    
    private String ex_msg;
    
    private int id;
    
    private String nickName;
    
    private int state;
    
    private String u_picture;
    
    private String uid;
    
    public String getCreate_time() {
      return this.create_time;
    }
    
    public String getEx_msg() {
      return this.ex_msg;
    }
    
    public int getId() {
      return this.id;
    }
    
    public String getNickName() {
      return this.nickName;
    }
    
    public int getState() {
      return this.state;
    }
    
    public String getU_picture() {
      return this.u_picture;
    }
    
    public String getUid() {
      return this.uid;
    }
    
    public void setCreate_time(String param1String) {
      this.create_time = param1String;
    }
    
    public void setEx_msg(String param1String) {
      this.ex_msg = param1String;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setNickName(String param1String) {
      this.nickName = param1String;
    }
    
    public void setState(int param1Int) {
      this.state = param1Int;
    }
    
    public void setU_picture(String param1String) {
      this.u_picture = param1String;
    }
    
    public void setUid(String param1String) {
      this.uid = param1String;
    }
  }
}