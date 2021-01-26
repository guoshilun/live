package com.jk.jkproject.ui.entity;

public class PkAcceptInfo extends BaseInfo {
  private String html;
  
  private int is_rand;
  
  private SenderBean sender;
  
  public String getHtml() {
    return this.html;
  }
  
  public int getIs_rand() {
    return this.is_rand;
  }
  
  public SenderBean getSender() {
    return this.sender;
  }
  
  public void setHtml(String paramString) {
    this.html = paramString;
  }
  
  public void setIs_rand(int paramInt) {
    this.is_rand = paramInt;
  }
  
  public void setSender(SenderBean paramSenderBean) {
    this.sender = paramSenderBean;
  }
  
  public static class SenderBean {
    private String avatar;
    
    private String nickname;
    
    private String uid;
    
    public String getAvatar() {
      return this.avatar;
    }
    
    public String getNickname() {
      return this.nickname;
    }
    
    public String getUid() {
      return this.uid;
    }
    
    public void setAvatar(String param1String) {
      this.avatar = param1String;
    }
    
    public void setNickname(String param1String) {
      this.nickname = param1String;
    }
    
    public void setUid(String param1String) {
      this.uid = param1String;
    }
  }
}
