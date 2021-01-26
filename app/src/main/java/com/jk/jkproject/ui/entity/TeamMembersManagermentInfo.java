package com.jk.jkproject.ui.entity;

import java.util.List;

public class TeamMembersManagermentInfo {
  private int code;
  
  private DataBean data;
  
  private String msg;
  
  public int getCode() {
    return this.code;
  }
  
  public DataBean getData() {
    return this.data;
  }
  
  public String getMsg() {
    return this.msg;
  }
  
  public void setCode(int paramInt) {
    this.code = paramInt;
  }
  
  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  
  public void setMsg(String paramString) {
    this.msg = paramString;
  }
  
  public static class DataBean {
    private List<DataListBean> dataList;
    
    private int dataNumber;
    
    private int dataSumNumber;
    
    private int joinNumber;
    
    public List<DataListBean> getDataList() {
      return this.dataList;
    }
    
    public int getDataNumber() {
      return this.dataNumber;
    }
    
    public int getDataSumNumber() {
      return this.dataSumNumber;
    }
    
    public int getJoinNumber() {
      return this.joinNumber;
    }
    
    public void setDataList(List<DataListBean> param1List) {
      this.dataList = param1List;
    }
    
    public void setDataNumber(int param1Int) {
      this.dataNumber = param1Int;
    }
    
    public void setDataSumNumber(int param1Int) {
      this.dataSumNumber = param1Int;
    }
    
    public void setJoinNumber(int param1Int) {
      this.joinNumber = param1Int;
    }
    
    public static class DataListBean {
      private String contract_time;
      
      private int dataNumber;
      
      private int dataSumNumber;
      
      private int id;
      
      private int joinNumber;
      
      private String nickName;
      
      private String pump;
      
      private String team_uid;
      
      private String u_picture;
      
      public String getContract_time() {
        return this.contract_time;
      }
      
      public int getDataNumber() {
        return this.dataNumber;
      }
      
      public int getDataSumNumber() {
        return this.dataSumNumber;
      }
      
      public int getId() {
        return this.id;
      }
      
      public int getJoinNumber() {
        return this.joinNumber;
      }
      
      public String getNickName() {
        return this.nickName;
      }
      
      public String getPump() {
        return this.pump;
      }
      
      public String getTeam_uid() {
        return this.team_uid;
      }
      
      public String getU_picture() {
        return this.u_picture;
      }
      
      public void setContract_time(String param2String) {
        this.contract_time = param2String;
      }
      
      public void setDataNumber(int param2Int) {
        this.dataNumber = param2Int;
      }
      
      public void setDataSumNumber(int param2Int) {
        this.dataSumNumber = param2Int;
      }
      
      public void setId(int param2Int) {
        this.id = param2Int;
      }
      
      public void setJoinNumber(int param2Int) {
        this.joinNumber = param2Int;
      }
      
      public void setNickName(String param2String) {
        this.nickName = param2String;
      }
      
      public void setPump(String param2String) {
        this.pump = param2String;
      }
      
      public void setTeam_uid(String param2String) {
        this.team_uid = param2String;
      }
      
      public void setU_picture(String param2String) {
        this.u_picture = param2String;
      }
    }
  }
  
  public static class DataListBean {
    private String contract_time;
    
    private int dataNumber;
    
    private int dataSumNumber;
    
    private int id;
    
    private int joinNumber;
    
    private String nickName;
    
    private String pump;
    
    private String team_uid;
    
    private String u_picture;
    
    public String getContract_time() {
      return this.contract_time;
    }
    
    public int getDataNumber() {
      return this.dataNumber;
    }
    
    public int getDataSumNumber() {
      return this.dataSumNumber;
    }
    
    public int getId() {
      return this.id;
    }
    
    public int getJoinNumber() {
      return this.joinNumber;
    }
    
    public String getNickName() {
      return this.nickName;
    }
    
    public String getPump() {
      return this.pump;
    }
    
    public String getTeam_uid() {
      return this.team_uid;
    }
    
    public String getU_picture() {
      return this.u_picture;
    }
    
    public void setContract_time(String param1String) {
      this.contract_time = param1String;
    }
    
    public void setDataNumber(int param1Int) {
      this.dataNumber = param1Int;
    }
    
    public void setDataSumNumber(int param1Int) {
      this.dataSumNumber = param1Int;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setJoinNumber(int param1Int) {
      this.joinNumber = param1Int;
    }
    
    public void setNickName(String param1String) {
      this.nickName = param1String;
    }
    
    public void setPump(String param1String) {
      this.pump = param1String;
    }
    
    public void setTeam_uid(String param1String) {
      this.team_uid = param1String;
    }
    
    public void setU_picture(String param1String) {
      this.u_picture = param1String;
    }
  }
}