package com.jk.jkproject.ui.entity;

import java.util.List;

public class HomeTabClassify extends BaseInfo {
  private DataBean data;
  
  public DataBean getData() {
    return this.data;
  }
  
  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("HomeTabClassify{data=");
    stringBuilder.append(this.data);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public static class DataBean {
    private List<ColumnListBean> columnList;
    
    public List<ColumnListBean> getColumnList() {
      return this.columnList;
    }
    
    public void setColumnList(List<ColumnListBean> param1List) {
      this.columnList = param1List;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("DataBean{columnList=");
      stringBuilder.append(this.columnList);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public static class ColumnListBean {
      private int id;
      
      private boolean isSelected = false;
      
      private String name;
      
      public int getId() {
        return this.id;
      }
      
      public String getName() {
        return this.name;
      }
      
      public boolean isSelected() {
        return this.isSelected;
      }
      
      public void setId(int param2Int) {
        this.id = param2Int;
      }
      
      public void setName(String param2String) {
        this.name = param2String;
      }
      
      public void setSelected(boolean param2Boolean) {
        this.isSelected = param2Boolean;
      }
      
      public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ColumnListBean{name='");
        stringBuilder.append(this.name);
        stringBuilder.append('\'');
        stringBuilder.append(", id=");
        stringBuilder.append(this.id);
        stringBuilder.append('}');
        return stringBuilder.toString();
      }
    }
  }
  
  public static class ColumnListBean {
    private int id;
    
    private boolean isSelected = false;
    
    private String name;
    
    public int getId() {
      return this.id;
    }
    
    public String getName() {
      return this.name;
    }
    
    public boolean isSelected() {
      return this.isSelected;
    }
    
    public void setId(int param1Int) {
      this.id = param1Int;
    }
    
    public void setName(String param1String) {
      this.name = param1String;
    }
    
    public void setSelected(boolean param1Boolean) {
      this.isSelected = param1Boolean;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ColumnListBean{name='");
      stringBuilder.append(this.name);
      stringBuilder.append('\'');
      stringBuilder.append(", id=");
      stringBuilder.append(this.id);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
}

