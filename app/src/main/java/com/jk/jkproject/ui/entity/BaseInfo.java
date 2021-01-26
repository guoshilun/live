package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class BaseInfo implements Serializable {
  private int code;
  
  private String msg;
  
  public int getCode() {
    return this.code;
  }
  
  public String getMsg() {
    return this.msg;
  }
  
  public void setCode(int paramInt) {
    this.code = paramInt;
  }
  
  public void setMsg(String paramString) {
    this.msg = paramString;
  }

  @Override
  public String toString() {
    return "BaseInfo{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            '}';
  }
}