package com.jk.jkproject.ui.entity;

import java.util.List;

public class TeamStatisticalInfo extends BaseInfo{

  private DataBean data;
  

  public DataBean getData() {
    return this.data;
  }
  


  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  

  public static class DataBean {
    private List<DataListBean> dataList;
    
    private int sum_personal_income;
    
    private int sum_push_money;
    
    public List<DataListBean> getDataList() {
      return this.dataList;
    }
    
    public int getSum_personal_income() {
      return this.sum_personal_income;
    }
    
    public int getSum_push_money() {
      return this.sum_push_money;
    }
    
    public void setDataList(List<DataListBean> param1List) {
      this.dataList = param1List;
    }
    
    public void setSum_personal_income(int param1Int) {
      this.sum_personal_income = param1Int;
    }
    
    public void setSum_push_money(int param1Int) {
      this.sum_push_money = param1Int;
    }
    
    public static class DataListBean {
      private int personal_income;
      
      private int sum_personal_income;
      
      private int sum_push_money;
      
      private String team_uid;
      
      private String u_nickName;
      
      private String u_picture;
      
      public int getPersonal_income() {
        return this.personal_income;
      }
      
      public int getSum_personal_income() {
        return this.sum_personal_income;
      }
      
      public int getSum_push_money() {
        return this.sum_push_money;
      }
      
      public String getTeam_uid() {
        return this.team_uid;
      }
      
      public String getU_nickName() {
        return this.u_nickName;
      }
      
      public String getU_picture() {
        return this.u_picture;
      }
      
      public void setPersonal_income(int param2Int) {
        this.personal_income = param2Int;
      }
      
      public void setSum_personal_income(int param2Int) {
        this.sum_personal_income = param2Int;
      }
      
      public void setSum_push_money(int param2Int) {
        this.sum_push_money = param2Int;
      }
      
      public void setTeam_uid(String param2String) {
        this.team_uid = param2String;
      }
      
      public void setU_nickName(String param2String) {
        this.u_nickName = param2String;
      }
      
      public void setU_picture(String param2String) {
        this.u_picture = param2String;
      }
    }
  }
  
  public static class DataListBean {
    private int personal_income;
    
    private int sum_personal_income;
    
    private int sum_push_money;
    
    private String team_uid;
    
    private String u_nickName;
    
    private String u_picture;
    
    public int getPersonal_income() {
      return this.personal_income;
    }
    
    public int getSum_personal_income() {
      return this.sum_personal_income;
    }
    
    public int getSum_push_money() {
      return this.sum_push_money;
    }
    
    public String getTeam_uid() {
      return this.team_uid;
    }
    
    public String getU_nickName() {
      return this.u_nickName;
    }
    
    public String getU_picture() {
      return this.u_picture;
    }
    
    public void setPersonal_income(int param1Int) {
      this.personal_income = param1Int;
    }
    
    public void setSum_personal_income(int param1Int) {
      this.sum_personal_income = param1Int;
    }
    
    public void setSum_push_money(int param1Int) {
      this.sum_push_money = param1Int;
    }
    
    public void setTeam_uid(String param1String) {
      this.team_uid = param1String;
    }
    
    public void setU_nickName(String param1String) {
      this.u_nickName = param1String;
    }
    
    public void setU_picture(String param1String) {
      this.u_picture = param1String;
    }
  }
}
