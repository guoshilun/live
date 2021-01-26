package com.jk.jkproject.net.okhttp;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.thread.ThreadExecutors;
import com.jk.jkproject.utils.SPUtils;
import java.util.concurrent.ExecutorService;
import okhttp3.Headers;

public class HttpConfig {
  private int mConnectTimeOut;
  
  private Headers.Builder mHeaderBuilder;
  
  private int mReadTimeOut;
  
  private ExecutorService mThreadPool;
  
  private int mWriteTimeOut;
  
  public HttpConfig(Builder paramBuilder) {
    this.mThreadPool = paramBuilder.threadPool;
    this.mHeaderBuilder = paramBuilder.headerBuilder;
    this.mConnectTimeOut = paramBuilder.connectTimeOut;
    this.mReadTimeOut = paramBuilder.readTimeOut;
    this.mWriteTimeOut = paramBuilder.writeTimeOut;
  }
  
  public static HttpConfig createDefault() {
    return (new Builder()).build();
  }
  
  public int getConnectTimeOut() {
    return this.mConnectTimeOut;
  }
  
  public Headers.Builder getHeaderBuilder() {
    return this.mHeaderBuilder;
  }
  
  public int getReadTimeOut() {
    return this.mReadTimeOut;
  }
  
  public ExecutorService getThreadPool() {
    return this.mThreadPool;
  }
  
  public int getWriteTimeOut() {
    return this.mWriteTimeOut;
  }
  
  public void release() {
    try {
      if (this.mHeaderBuilder != null)
        this.mHeaderBuilder = null; 
      if (this.mThreadPool != null) {
        this.mThreadPool.shutdownNow();
        this.mThreadPool = null;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public void setConnectTimeOut(int paramInt) {
    this.mConnectTimeOut = paramInt;
  }
  
  public void setHeaderBuilder(Headers.Builder paramBuilder) {
    this.mHeaderBuilder = paramBuilder;
  }
  
  public void setReadTimeOut(int paramInt) {
    this.mReadTimeOut = paramInt;
  }
  
  public void setThreadPool(ExecutorService paramExecutorService) {
    this.mThreadPool = paramExecutorService;
  }
  
  public void setWriteTimeOut(int paramInt) {
    this.mWriteTimeOut = paramInt;
  }
  
  public static class Builder {
    private int connectTimeOut = 30;
    
    private Headers.Builder headerBuilder = new Headers.Builder();
    
    private int readTimeOut = 30;
    
    private ExecutorService threadPool = ThreadExecutors.get().getThreadPool();
    
    private int writeTimeOut = 30;
    
    public Builder() {
      this.headerBuilder.add("Content-Type", "application/x-www-form-urlencoded");
      LogUtils.e("====Token", SPUtils.getToken());
      if (!SPUtils.getToken().isEmpty())
        this.headerBuilder.add("Access-Token", SPUtils.getToken()); 
    }
    
    public Builder ConnectTimeOut(int param1Int) {
      this.connectTimeOut = param1Int;
      return this;
    }
    
    public Builder HeaderBuilder(Headers.Builder param1Builder) {
      this.headerBuilder = param1Builder;
      return this;
    }
    
    public Builder ReadTimeOut(int param1Int) {
      this.readTimeOut = param1Int;
      return this;
    }
    
    public Builder ThreadPool(ExecutorService param1ExecutorService) {
      this.threadPool = param1ExecutorService;
      return this;
    }
    
    public Builder WriteTimeOut(int param1Int) {
      this.writeTimeOut = param1Int;
      return this;
    }
    
    public HttpConfig build() {
      return new HttpConfig(this);
    }
  }
}