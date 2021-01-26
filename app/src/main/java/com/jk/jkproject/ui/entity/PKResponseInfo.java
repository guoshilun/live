package com.jk.jkproject.ui.entity;

public class PKResponseInfo {
    String roomId;//发起方的roomId
    String streamId;//发起方的流id
    int type;//响应类型  1同意  2挂断  3.时间到期
    String myRoomId;//自己的房间id
    String userId;//对方的userId
    String myStreamId;//自己的流id
    int aisle;//通道类型  1随机  2好友
    String mergeStreamUrl;//合流地址

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMyRoomId() {
        return myRoomId;
    }

    public void setMyRoomId(String myRoomId) {
        this.myRoomId = myRoomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMyStreamId() {
        return myStreamId;
    }

    public void setMyStreamId(String myStreamId) {
        this.myStreamId = myStreamId;
    }

    public int getAisle() {
        return aisle;
    }

    public void setAisle(int aisle) {
        this.aisle = aisle;
    }

    public String getMergeStreamUrl() {
        return mergeStreamUrl;
    }

    public void setMergeStreamUrl(String mergeStreamUrl) {
        this.mergeStreamUrl = mergeStreamUrl;
    }
}
