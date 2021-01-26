package com.jk.jkproject.ui.entity;

public class QiNiuTokenInfo extends BaseInfo{

  private DataBean data;
  

  public DataBean getData() {
    return this.data;
  }
  


  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  

  public static class DataBean {
    private String qnToken;
    
    private String url;
    
    public String getQnToken() {
      return this.qnToken;
    }
    
    public String getUrl() {
      return this.url;
    }
    
    public void setQnToken(String param1String) {
      this.qnToken = param1String;
    }
    
    public void setUrl(String param1String) {
      this.url = param1String;
    }
  }
}
