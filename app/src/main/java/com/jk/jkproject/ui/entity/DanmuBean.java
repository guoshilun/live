package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class DanmuBean implements Serializable {
  private DataBean data;
  
  private int time;
  
  private String type;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "DanmuBean{" +
            "data=" + data +
            ", time=" + time +
            ", type='" + type + '\'' +
            '}';
  }

  public static class DataBean implements Serializable {
    private String content;
    
    private FromBean from;
    
    private ToBean to;

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public FromBean getFrom() {
      return from;
    }

    public void setFrom(FromBean from) {
      this.from = from;
    }

    public ToBean getTo() {
      return to;
    }

    public void setTo(ToBean to) {
      this.to = to;
    }

    @Override
    public String toString() {
      return "DataBean{" +
              "content='" + content + '\'' +
              ", from=" + from +
              ", to=" + to +
              '}';
    }

    public static class FromBean implements Serializable {
      private String __plat;
      
      private String identity;
      
      private String level;
      
      private String msgId;
      
      private int msgType = 0;
      private int userType = 0;

      private String msgcolor;
      
      private String nickName;
      
      private String rid;
      
      private String sp_identity;
      
      private String time;
      
      private String userName;

      public String get__plat() {
        return __plat;
      }

      public void set__plat(String __plat) {
        this.__plat = __plat;
      }

      public String getIdentity() {
        return identity;
      }

      public void setIdentity(String identity) {
        this.identity = identity;
      }

      public String getLevel() {
        return level;
      }

      public void setLevel(String level) {
        this.level = level;
      }

      public String getMsgId() {
        return msgId;
      }

      public void setMsgId(String msgId) {
        this.msgId = msgId;
      }

      public int getMsgType() {
        return msgType;
      }

      public void setMsgType(int msgType) {
        this.msgType = msgType;
      }

      public int getUserType() {
        return userType;
      }

      public void setUserType(int userType) {
        this.userType = userType;
      }

      public String getMsgcolor() {
        return msgcolor;
      }

      public void setMsgcolor(String msgcolor) {
        this.msgcolor = msgcolor;
      }

      public String getNickName() {
        return nickName;
      }

      public void setNickName(String nickName) {
        this.nickName = nickName;
      }

      public String getRid() {
        return rid;
      }

      public void setRid(String rid) {
        this.rid = rid;
      }

      public String getSp_identity() {
        return sp_identity;
      }

      public void setSp_identity(String sp_identity) {
        this.sp_identity = sp_identity;
      }

      public String getTime() {
        return time;
      }

      public void setTime(String time) {
        this.time = time;
      }

      public String getUserName() {
        return userName;
      }

      public void setUserName(String userName) {
        this.userName = userName;
      }

      @Override
      public String toString() {
        return "FromBean{" +
                "__plat='" + __plat + '\'' +
                ", identity='" + identity + '\'' +
                ", level='" + level + '\'' +
                ", msgId='" + msgId + '\'' +
                ", msgType=" + msgType +
                ", userType=" + userType +
                ", msgcolor='" + msgcolor + '\'' +
                ", nickName='" + nickName + '\'' +
                ", rid='" + rid + '\'' +
                ", sp_identity='" + sp_identity + '\'' +
                ", time='" + time + '\'' +
                ", userName='" + userName + '\'' +
                '}';
      }
    }
    
    public static class ToBean implements Serializable {
      private String toroom;

      public String getToroom() {
        return toroom;
      }

      public void setToroom(String toroom) {
        this.toroom = toroom;
      }

      @Override
      public String toString() {
        return "ToBean{" +
                "toroom='" + toroom + '\'' +
                '}';
      }
    }
  }
}
