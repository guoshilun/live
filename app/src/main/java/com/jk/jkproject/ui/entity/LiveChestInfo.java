package com.jk.jkproject.ui.entity;

import android.text.TextUtils;

public class LiveChestInfo extends BaseInfo {
  private boolean isAnime = false;
  
  private String key;
  
  private int ttl;
  
  public LiveChestInfo() {}
  
  public LiveChestInfo(String paramString, int paramInt) {
    this.key = paramString;
    this.ttl = paramInt;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject != null && paramObject instanceof LiveChestInfo && !TextUtils.isEmpty(getKey()) && TextUtils.equals(getKey(), ((LiveChestInfo)paramObject).getKey())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public int getTtl() {
    return this.ttl;
  }
  
  public boolean isAnime() {
    return this.isAnime;
  }
  
  public void setAnime(boolean paramBoolean) {
    this.isAnime = paramBoolean;
  }
  
  public void setKey(String paramString) {
    this.key = paramString;
  }
  
  public void setTtl(int paramInt) {
    this.ttl = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LiveChestInfo{key='");
    stringBuilder.append(this.key);
    stringBuilder.append('\'');
    stringBuilder.append(", ttl=");
    stringBuilder.append(this.ttl);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}
