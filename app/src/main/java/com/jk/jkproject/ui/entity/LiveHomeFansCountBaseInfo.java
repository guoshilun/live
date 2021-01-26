package com.jk.jkproject.ui.entity;

public class LiveHomeFansCountBaseInfo extends BaseInfo {
  private DataBean data;
  
  public DataBean getData() {
    return this.data;
  }
  
  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }

  @Override
  public String toString() {
    return "LiveHomeFansCountBaseInfo{" +
            "data=" + data +
            '}';
  }

  public static class DataBean {
    private int fansCount;
    
    private int followCount;
    
    public int getFansCount() {
      return this.fansCount;
    }
    
    public int getFollowCount() {
      return this.followCount;
    }
    
    public void setFansCount(int param1Int) {
      this.fansCount = param1Int;
    }
    
    public void setFollowCount(int param1Int) {
      this.followCount = param1Int;
    }

    @Override
    public String toString() {
      return "DataBean{" +
              "fansCount=" + fansCount +
              ", followCount=" + followCount +
              '}';
    }
  }
}
