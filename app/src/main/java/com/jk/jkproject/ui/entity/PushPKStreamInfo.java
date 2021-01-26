package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class PushPKStreamInfo implements Serializable {
    String tagAnchorId;//对方id
    String anchorId;//自己主播id
    String mergeStreamUrl;//PK合并的流id
    String PKId; //PKid
    String tagRoomId;//对方的房间id
    String tagStreamId;//对方的流id

    public String getTagAnchorId() {
        return tagAnchorId;
    }

    public void setTagAnchorId(String tagAnchorId) {
        this.tagAnchorId = tagAnchorId;
    }

    public String getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(String anchorId) {
        this.anchorId = anchorId;
    }

    public String getMergeStreamUrl() {
        return mergeStreamUrl;
    }

    public void setMergeStreamUrl(String mergeStreamUrl) {
        this.mergeStreamUrl = mergeStreamUrl;
    }

    public String getPKId() {
        return PKId;
    }

    public void setPKId(String PKId) {
        this.PKId = PKId;
    }

    public String getTagRoomId() {
        return tagRoomId;
    }

    public void setTagRoomId(String tagRoomId) {
        this.tagRoomId = tagRoomId;
    }

    public String getTagStreamId() {
        return tagStreamId;
    }

    public void setTagStreamId(String tagStreamId) {
        this.tagStreamId = tagStreamId;
    }
}
