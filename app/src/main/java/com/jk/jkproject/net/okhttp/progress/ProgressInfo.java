package com.jk.jkproject.net.okhttp.progress;

import java.io.Serializable;

public class ProgressInfo implements Serializable {
  private long contentLength;
  
  private long currentBytes;
  
  private boolean done;
  
  public ProgressInfo(long paramLong1, long paramLong2, boolean paramBoolean) {
    this.currentBytes = paramLong1;
    this.contentLength = paramLong2;
    this.done = paramBoolean;
  }
  
  public long getContentLength() {
    return this.contentLength;
  }
  
  public long getCurrentBytes() {
    return this.currentBytes;
  }
  
  public boolean isDone() {
    return this.done;
  }
  
  public void setContentLength(long paramLong) {
    this.contentLength = paramLong;
  }
  
  public void setCurrentBytes(long paramLong) {
    this.currentBytes = paramLong;
  }
  
  public void setDone(boolean paramBoolean) {
    this.done = paramBoolean;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ProgressInfo{currentBytes=");
    stringBuilder.append(this.currentBytes);
    stringBuilder.append(", contentLength=");
    stringBuilder.append(this.contentLength);
    stringBuilder.append(", done=");
    stringBuilder.append(this.done);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkproject\net\okhttp\progress\ProgressInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */