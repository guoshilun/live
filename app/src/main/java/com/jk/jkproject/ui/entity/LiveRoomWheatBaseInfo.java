package com.jk.jkproject.ui.entity;

import java.io.Serializable;
import java.util.List;

public class LiveRoomWheatBaseInfo extends BaseInfo implements Serializable {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> paramList) {
        this.data = paramList;
    }

    @Override
    public String toString() {
        return "LiveRoomWheatBaseInfo{" +
                "data=" + data +
                '}';
    }


    public static class DataBean implements Serializable {
        private int type; //1.左边 2.右边
        private String picture;
        private String nickname;
        private String anchorGrade;
        private String userId;
        private String userGrade;
        private String roomId;
        private String steeamPKId;
        private String victoryUserId;//胜利的用户id
        private int condition;//输赢情况  1.赢  2输
        private boolean isShowLine;
        private int time;


        //PK时接收方操作传参
        private String myRoomId;
        private String myStreamId;
        private int aisle;
        private String mergeStreamUrl;


        //PK最大胜利数
        private String leftPkMaxVictory;
        private String rightPkMaxVictory;
        private int maxVictory;
        private String maxNickname;
        private String maxPicture;

        public String getLeftPkMaxVictory() {
            return leftPkMaxVictory;
        }

        public void setLeftPkMaxVictory(String leftPkMaxVictory) {
            this.leftPkMaxVictory = leftPkMaxVictory;
        }

        public String getRightPkMaxVictory() {
            return rightPkMaxVictory;
        }

        public void setRightPkMaxVictory(String rightPkMaxVictory) {
            this.rightPkMaxVictory = rightPkMaxVictory;
        }

        public String getVictoryUserId() {
            return victoryUserId;
        }

        public void setVictoryUserId(String victoryUserId) {
            this.victoryUserId = victoryUserId;
        }

        public int getCondition() {
            return condition;
        }

        public void setCondition(int condition) {
            this.condition = condition;
        }

        public String getMyRoomId() {
            return myRoomId;
        }

        public void setMyRoomId(String myRoomId) {
            this.myRoomId = myRoomId;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getSteeamPKId() {
            return steeamPKId;
        }

        public void setSteeamPKId(String steeamPKId) {
            this.steeamPKId = steeamPKId;
        }

        public String getAnchorGrade() {
            return anchorGrade;
        }

        public void setAnchorGrade(String anchorGrade) {
            this.anchorGrade = anchorGrade;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            if (picture.isEmpty()) {
                this.picture = "http://qn.zbjlife.cn/1020839.png";
            } else {
                this.picture = picture;
            }
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            if (nickname.isEmpty()) {
                this.nickname = "小姐姐04";
            } else {
                this.nickname = nickname;
            }
        }

        public String getUserGrade() {
            return userGrade;
        }

        public void setUserGrade(String userGrade) {
            if (userGrade.isEmpty()) {
                this.userGrade = "40";
            } else {
                this.userGrade = userGrade;
            }
        }

        public boolean isShowLine() {
            return isShowLine;
        }

        public void setShowLine(boolean showLine) {
            isShowLine = showLine;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "type=" + type +
                    ", picture='" + picture + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", anchorGrade='" + anchorGrade + '\'' +
                    ", userId='" + userId + '\'' +
                    ", userGrade='" + userGrade + '\'' +
                    ", roomId='" + roomId + '\'' +
                    ", steeamPKId='" + steeamPKId + '\'' +
                    ", isShowLine=" + isShowLine +
                    ", time=" + time +
                    ", myRoomId='" + myRoomId + '\'' +
                    ", myStreamId='" + myStreamId + '\'' +
                    ", aisle=" + aisle +
                    ", mergeStreamUrl='" + mergeStreamUrl + '\'' +
                    '}';
        }
    }
}