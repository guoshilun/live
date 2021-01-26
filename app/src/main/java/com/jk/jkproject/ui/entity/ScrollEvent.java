package com.jk.jkproject.ui.entity;

public class ScrollEvent {
  private boolean isCanScroll;
  
  private String message;
  
  public ScrollEvent(boolean paramBoolean, String paramString) {
    this.isCanScroll = paramBoolean;
    this.message = paramString;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public boolean isCanScroll() {
    return this.isCanScroll;
  }
  
  public void setCanScroll(boolean paramBoolean) {
    this.isCanScroll = paramBoolean;
  }
  
  public void setMessage(String paramString) {
    this.message = paramString;
  }
}
