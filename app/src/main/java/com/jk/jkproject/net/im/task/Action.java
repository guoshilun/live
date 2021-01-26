package com.jk.jkproject.net.im.task;

import com.jk.jkproject.net.im.RequestPacket;
import com.jk.jkproject.net.im.info.MessageEntity;

public class Action {
  public static final int DEFAULT_REPEAT_COUNT = 3;
  
  public static final int DEFAULT_TIME_OUT = 35000;
  
  protected ActionCallback mCallback;
  
  protected MessageEntity mMesssage;
  
  protected RequestPacket mPacket;
  
  private int mRepeatCountIfFaild;
  
  private int mSequenceNo;
  
  private long mTimeStamp;
  
  private int mTimeout;
  
  private Action(ActionCallback paramActionCallback, RequestPacket paramRequestPacket, MessageEntity paramMessageEntity, int paramInt1, long paramLong, int paramInt2) {
    this.mCallback = paramActionCallback;
    this.mPacket = paramRequestPacket;
    this.mTimeout = paramInt1;
    this.mTimeStamp = paramLong;
    this.mMesssage = paramMessageEntity;
    this.mRepeatCountIfFaild = paramInt2;
  }
  
  public ActionCallback getCallback() {
    return this.mCallback;
  }
  
  public RequestPacket getPacket() {
    return this.mPacket;
  }
  
  public int getSequenceNo() {
    return this.mSequenceNo;
  }
  
  public long getTimeStamp() {
    return this.mTimeStamp;
  }
  
  public int getTimeout() {
    return this.mTimeout;
  }
  
  public MessageEntity getmMesssage() {
    return this.mMesssage;
  }
  
  public int getmRepeatCountIfFaild() {
    return this.mRepeatCountIfFaild;
  }
  
  public int minusRepeatCountIfFaild() {
    this.mRepeatCountIfFaild--;
    return this.mRepeatCountIfFaild;
  }
  
  public void setSquenceNo(int paramInt) {
    this.mSequenceNo = paramInt;
  }
  
  public void setmRepeatCountIfFaild(int paramInt) {
    this.mRepeatCountIfFaild = paramInt;
  }
  
  public static class Builder {
    private ActionCallback callback;
    
    private MessageEntity message;
    
    private RequestPacket packet;
    
    private int repeatcountIfFaild = -1;
    
    private int timeout = -1;
    
    public Action build() {
      if (this.timeout == -1)
        this.timeout = 35000; 
      if (this.repeatcountIfFaild < 0)
        this.repeatcountIfFaild = 3; 
      return new Action(this.callback, this.packet, this.message, this.timeout, System.currentTimeMillis(), this.repeatcountIfFaild);
    }
    
    public Builder setCallback(ActionCallback param1ActionCallback) {
      this.callback = param1ActionCallback;
      return this;
    }
    
    public Builder setMessage(MessageEntity param1MessageEntity) {
      if (param1MessageEntity != null) {
        this.message = param1MessageEntity;
        return this;
      } 
      throw new IllegalArgumentException("An action must have a Packet!");
    }
    
    public Builder setPacket(RequestPacket param1RequestPacket) {
      if (param1RequestPacket != null) {
        this.packet = param1RequestPacket;
        return this;
      } 
      throw new IllegalArgumentException("An action must have a Packet!");
    }
    
    public Builder setRepeatCountIfFaild(int param1Int) {
      int i = param1Int;
      if (param1Int < 0)
        i = 0; 
      this.repeatcountIfFaild = i;
      return this;
    }
    
    public Builder setTimeout(int param1Int) {
      if (param1Int > 0) {
        this.timeout = param1Int;
        return this;
      } 
      throw new IllegalArgumentException("Timeout must not less than 0!");
    }
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkproject\net\im\task\Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */