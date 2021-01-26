package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class LiveOpenPlayInfo extends BaseInfo implements Serializable {
    private DataBean data;


    public DataBean getData() {
        return this.data;
    }


    public void setData(DataBean paramDataBean) {
        this.data = paramDataBean;
    }


    @Override
    public String toString() {
        return "LiveOpenPlayInfo{" +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        private String pull_flow_url;

        private String roomId;
        private String picture;
        private String roomName;
        private String userId;
        private String nickname;
        private int ranking;
        private int banned;
        private int status;
        private int needExperience;
        private int oth;
        private int count;
        private int isAttention;
        private String anchorGrade;
        private String account;
        private String beautifulUserId;
        private String time;
        private String cause;


        public int getNeedExperience() {
            return needExperience;
        }

        public void setNeedExperience(int needExperience) {
            this.needExperience = needExperience;
        }

        public int getOth() {
            return oth;
        }

        public void setOth(int oth) {
            this.oth = oth;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getIsAttention() {
            return isAttention;
        }

        public void setIsAttention(int isAttention) {
            this.isAttention = isAttention;
        }

        public String getAnchorGrade() {
            return anchorGrade;
        }

        public void setAnchorGrade(String anchorGrade) {
            this.anchorGrade = anchorGrade;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public int getBanned() {
            return banned;
        }

        public void setBanned(int banned) {
            this.banned = banned;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPull_flow_url() {
            return this.pull_flow_url;
        }

        public String getRoomId() {
            return this.roomId;
        }

        public void setPull_flow_url(String param1String) {
            this.pull_flow_url = param1String;
        }

        public void setRoomId(String param1String) {
            this.roomId = param1String;
        }


        @Override
        public String toString() {
            return "DataBean{" +
                    "pull_flow_url='" + pull_flow_url + '\'' +
                    ", roomId='" + roomId + '\'' +
                    ", picture='" + picture + '\'' +
                    ", roomName='" + roomName + '\'' +
                    ", userId='" + userId + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", ranking=" + ranking +
                    ", banned=" + banned +
                    ", status=" + status +
                    ", needExperience=" + needExperience +
                    ", oth=" + oth +
                    ", count=" + count +
                    ", isAttention=" + isAttention +
                    ", anchorGrade='" + anchorGrade + '\'' +
                    ", account='" + account + '\'' +
                    ", time='" + time + '\'' +
                    ", cause='" + cause + '\'' +
                    '}';
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public String getBeautifulUserId() {
            return beautifulUserId;
        }

        public void setBeautifulUserId(String beautifulUserId) {
            this.beautifulUserId = beautifulUserId;
        }
    }
}
