package com.jk.jkproject.ui.entity;

public class LiveEntryInfo {
  private String content;
  
  private int glamour;
  
  private String nickname;
  
  private int uid;
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject != null && paramObject instanceof LiveEntryInfo && ((LiveEntryInfo)paramObject).getUid() == getUid()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public int getGlamour() {
    return this.glamour;
  }
  
  public String getNickname() {
    return this.nickname;
  }
  
  public int getUid() {
    return this.uid;
  }
  
  public void setContent(String paramString) {
    this.content = paramString;
  }
  
  public void setGlamour(int paramInt) {
    this.glamour = paramInt;
  }
  
  public void setNickname(String paramString) {
    this.nickname = paramString;
  }
  
  public void setUid(int paramInt) {
    this.uid = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LiveEntryInfo{uid=");
    stringBuilder.append(this.uid);
    stringBuilder.append(", glamour=");
    stringBuilder.append(this.glamour);
    stringBuilder.append(", content='");
    stringBuilder.append(this.content);
    stringBuilder.append('\'');
    stringBuilder.append(", nickname='");
    stringBuilder.append(this.nickname);
    stringBuilder.append('\'');
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}
