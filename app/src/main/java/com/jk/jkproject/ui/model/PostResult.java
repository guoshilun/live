package com.jk.jkproject.ui.model;

import java.io.Serializable;

public class PostResult implements Serializable {
  public Object result;
  
  public String tag;
  
  public PostResult() {}
  
  public PostResult(String paramString) {
    setTag(paramString);
    setResult("");
  }
  
  public PostResult(String paramString, Object paramObject) {
    setTag(paramString);
    setResult(paramObject);
  }
  
  public Object getResult() {
    return this.result;
  }
  
  public String getTag() {
    return this.tag;
  }
  
  public void setResult(Object paramObject) {
    this.result = paramObject;
  }
  
  public void setTag(String paramString) {
    this.tag = paramString;
  }


  @Override
  public String toString() {
    return "PostResult{" +
            "result=" + result +
            ", tag='" + tag + '\'' +
            '}';
  }
}
