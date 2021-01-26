package com.jk.jkproject.ui.chat;


import com.jk.jkproject.ui.entity.BaseInfo;

import java.io.Serializable;

public class Message extends BaseInfo implements Serializable {

    //"conversationList":["{\"msgType\":0,\"count\":0,\"msgId\":\"1897\",\"time\":1594711600205,\"body\":\"[/流泪][/流泪][/流泪]r\",\"userName\":\"赤い\",\"userId\":\"1000002\",\"picture\":\"http://qn.zbjlife.cn/58f43ffe6ccab14ad4d47688ae9e08fb.png\",\"target\":\"1000035\"}
    private String uuid;
    private String msgId;
    private MsgType msgType;
    private MsgBody body;
    private MsgSendStatus sentStatus;
    private String userId;
    private String targetId;
    private long sentTime;
    private int count;
    private String userName;
    private String picture;
    private String content;
    private int mType;
    private boolean isRead; //是否已读
    private boolean isShowTime;


    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public MsgBody getBody() {
        return body;
    }

    public void setBody(MsgBody body) {
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public MsgSendStatus getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(MsgSendStatus sentStatus) {
        this.sentStatus = sentStatus;
    }


    public long getSentTime() {
        return sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "uuid='" + uuid + '\'' +
                ", msgId='" + msgId + '\'' +
                ", msgType=" + msgType +
                ", body=" + body +
                ", sentStatus=" + sentStatus +
                ", userId='" + userId + '\'' +
                ", targetId='" + targetId + '\'' +
                ", sentTime=" + sentTime +
                ", count=" + count +
                ", userName='" + userName + '\'' +
                ", picture='" + picture + '\'' +
                ", content='" + content + '\'' +
                ", mType=" + mType +
                ", isRead=" + isRead +
                '}';
    }

    public boolean isShowTime() {
        return isShowTime;
    }

    public void setShowTime(boolean showTime) {
        isShowTime = showTime;
    }



}
