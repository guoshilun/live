package com.jk.jkproject.ui.entity;

public class UserDateInfo extends BaseInfo{

  private DataBean data;
  


  public DataBean getData() {
    return this.data;
  }
  


  public void setData(DataBean paramDataBean) {
    this.data = paramDataBean;
  }
  

  @Override
  public String toString() {
    return "UserDateInfo{" +
            ", data=" + data +
            '}';
  }

  public static class DataBean {
    private String Portrait;
    
    private String host;
    
    private String nickname;
    
    private String number;
    
    private String platformId;
    
    private int port;
    
    private int sex;
    
    private int teamType;
    
    private String token;
    
    private int type;
    
    private String userId;
    
    private String userid;
    private String url;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getHost() {
      return this.host;
    }
    
    public String getNickname() {
      return this.nickname;
    }
    
    public String getNumber() {
      return this.number;
    }
    
    public String getPlatformId() {
      return this.platformId;
    }
    
    public int getPort() {
      return this.port;
    }
    
    public String getPortrait() {
      return this.Portrait;
    }
    
    public int getSex() {
      return this.sex;
    }
    
    public int getTeamType() {
      return this.teamType;
    }
    
    public String getToken() {
      return this.token;
    }
    
    public int getType() {
      return this.type;
    }
    
    public String getUserId() {
      return this.userId;
    }
    
    public String getUserid() {
      return this.userid;
    }
    
    public void setHost(String param1String) {
      this.host = param1String;
    }
    
    public void setNickname(String param1String) {
      this.nickname = param1String;
    }
    
    public void setNumber(String param1String) {
      this.number = param1String;
    }
    
    public void setPlatformId(String param1String) {
      this.platformId = param1String;
    }
    
    public void setPort(int param1Int) {
      this.port = param1Int;
    }
    
    public void setPortrait(String param1String) {
      this.Portrait = param1String;
    }
    
    public void setSex(int param1Int) {
      this.sex = param1Int;
    }
    
    public void setTeamType(int param1Int) {
      this.teamType = param1Int;
    }
    
    public void setToken(String param1String) {
      this.token = param1String;
    }
    
    public void setType(int param1Int) {
      this.type = param1Int;
    }
    
    public void setUserId(String param1String) {
      this.userId = param1String;
    }
    
    public void setUserid(String param1String) {
      this.userid = param1String;
    }

    @Override
    public String toString() {
      return "DataBean{" +
              "Portrait='" + Portrait + '\'' +
              ", host='" + host + '\'' +
              ", nickname='" + nickname + '\'' +
              ", number='" + number + '\'' +
              ", platformId='" + platformId + '\'' +
              ", port=" + port +
              ", sex=" + sex +
              ", teamType=" + teamType +
              ", token='" + token + '\'' +
              ", type=" + type +
              ", userId='" + userId + '\'' +
              ", userid='" + userid + '\'' +
              ", url='" + url + '\'' +
              '}';
    }
  }
}
