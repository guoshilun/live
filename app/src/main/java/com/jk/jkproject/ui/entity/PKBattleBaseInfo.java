package com.jk.jkproject.ui.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

public class PKBattleBaseInfo extends BaseInfo implements Serializable {
    private String aRoomId;
    private long aReceiveNumber;
    private String aMessage;
    private String bMessage;
    private String bRoomId;
    private long bReceiveNumber;

    private List<aMessage> aMessages;
    private List<aMessage> bMessages;

    public String getaRoomId() {
        return aRoomId;
    }

    public void setaRoomId(String aRoomId) {
        this.aRoomId = aRoomId;
    }

    public long getaReceiveNumber() {
        return aReceiveNumber;
    }

    public void setaReceiveNumber(long aReceiveNumber) {
        this.aReceiveNumber = aReceiveNumber;
    }

    public String getaMessage() {
        return aMessage;
    }

    public void setaMessage(String aMessage) {
        this.aMessage = aMessage;
    }

    public String getbMessage() {
        return bMessage;
    }

    public void setbMessage(String bMessage) {
        this.bMessage = bMessage;
    }

    public List<aMessage> getaMessages() {
        return JSONObject.parseArray(aMessage, aMessage.class);
    }

    public void setaMessages(List<aMessage> aMessages) {
        this.aMessages = aMessages;
    }

    public List<aMessage> getbMessages() {
        return JSONObject.parseArray(bMessage, aMessage.class);
    }

    public void setbMessages(List<aMessage> bMessages) {
        this.bMessages = bMessages;
    }

    public String getbRoomId() {
        return bRoomId;
    }

    public void setbRoomId(String bRoomId) {
        this.bRoomId = bRoomId;
    }

    public long getbReceiveNumber() {
        return bReceiveNumber;
    }

    public void setbReceiveNumber(long bReceiveNumber) {
        this.bReceiveNumber = bReceiveNumber;
    }

    public static class aMessage implements Serializable{
        private int isMVP;
        private int sum_amount;
        private String userId;
        private String picture;

        public int getIsMVP() {
            return isMVP;
        }

        public void setIsMVP(int isMVP) {
            this.isMVP = isMVP;
        }

        public int getSum_amount() {
            return sum_amount;
        }

        public void setSum_amount(int sum_amount) {
            this.sum_amount = sum_amount;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }


        @Override
        public String toString() {
            return "aMessage{" +
                    "isMVP=" + isMVP +
                    ", sum_amount=" + sum_amount +
                    ", userId='" + userId + '\'' +
                    ", picture='" + picture + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PKBattleBaseInfo{" +
                "aRoomId='" + aRoomId + '\'' +
                ", aReceiveNumber=" + aReceiveNumber +
                ", aMessage='" + aMessage + '\'' +
                ", bMessage='" + bMessage + '\'' +
                ", bRoomId='" + bRoomId + '\'' +
                ", bReceiveNumber=" + bReceiveNumber +
                ", aMessages=" + getaMessages() +
                ", bMessages=" + getbMessages() +
                '}';
    }
}
