package com.jk.jkproject.ui.entity;

import android.annotation.SuppressLint;

import java.util.List;

public class PKRecordBaseInfo extends BaseInfo {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> paramList) {
        this.data = paramList;
    }

    @Override
    public String toString() {
        return "PKRecordBaseInfo{" +
                "data=" + data +
                '}';
    }

    @SuppressLint("SupportAnnotationUsage")
    public static class DataBean {
        private String pk_id;
        private String anchorGrade;
        private String victory_userId = "null";
        private int send_score;
        private String picture;
        private int r_state;
        private String nickname;
        private boolean isWin;
        private String userId;
        private int receive_score;
        private int pk_type;
        private String roomId;
        private boolean isPk;

        public String getPk_id() {
            return pk_id;
        }

        public void setPk_id(String pk_id) {
            this.pk_id = pk_id;
        }

        public String getAnchorGrade() {
            return anchorGrade;
        }

        public void setAnchorGrade(String anchorGrade) {
            this.anchorGrade = anchorGrade;
        }

        public String getVictory_userId() {
            return victory_userId;
        }

        public void setVictory_userId(String victory_userId) {
            this.victory_userId = victory_userId;
        }

        public int getSend_score() {
            return send_score;
        }

        public void setSend_score(int send_score) {
            this.send_score = send_score;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getR_state() {
            return r_state;
        }

        public void setR_state(int r_state) {
            this.r_state = r_state;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public boolean isWin() {
            return isWin;
        }

        public void setWin(boolean win) {
            isWin = win;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getReceive_score() {
            return receive_score;
        }

        public void setReceive_score(int receive_score) {
            this.receive_score = receive_score;
        }

        public int getPk_type() {
            return pk_type;
        }

        public void setPk_type(int pk_type) {
            this.pk_type = pk_type;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public boolean isPk() {
            return isPk;
        }

        public void setPk(boolean pk) {
            isPk = pk;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "pk_id='" + pk_id + '\'' +
                    ", anchorGrade='" + anchorGrade + '\'' +
                    ", victory_userId='" + victory_userId + '\'' +
                    ", send_score=" + send_score +
                    ", picture='" + picture + '\'' +
                    ", r_state=" + r_state +
                    ", nickname='" + nickname + '\'' +
                    ", isWin=" + isWin +
                    ", userId='" + userId + '\'' +
                    ", receive_score=" + receive_score +
                    ", pk_type=" + pk_type +
                    ", roomId='" + roomId + '\'' +
                    ", isPk=" + isPk +
                    '}';
        }
    }
}
