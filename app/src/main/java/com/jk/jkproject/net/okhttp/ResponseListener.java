package com.jk.jkproject.net.okhttp;

public interface ResponseListener {
  void onFailure(int paramInt, String paramString1, String paramString2);
  
  void onSuccess(String paramString, Object paramObject);

  void onStartRequest();
}
