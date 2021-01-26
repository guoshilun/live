package com.jk.jkproject.ui.entity;

import java.util.List;

public class FanContributionList extends BaseInfo {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sum_amount : 1585490
         * anchorGrade : 18
         * userGrade : 31
         * nickname : arrow
         * r_state : 0
         * state : 0
         * id : 1
         * userId : 1020822
         * roomId : 15
         * picture : http://qn.zbjlife.cn/e2e39e84881207d5faa3eb295f4f31fb.png
         */

        private int sum_amount;
        private String anchorGrade;
        private String userGrade;
        private String nickname;
        private int r_state;
        private int state;
        private int id;
        private String userId;
        private String roomId;
        private String picture;


        //主播
        private int sum_hot; //	热度
        private String a_id;


        public int getSum_amount() {
            return sum_amount;
        }

        public void setSum_amount(int sum_amount) {
            this.sum_amount = sum_amount;
        }

        public String getAnchorGrade() {
            return anchorGrade;
        }

        public void setAnchorGrade(String anchorGrade) {
            this.anchorGrade = anchorGrade;
        }

        public String getUserGrade() {
            return userGrade;
        }

        public void setUserGrade(String userGrade) {
            this.userGrade = userGrade;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getR_state() {
            return r_state;
        }

        public void setR_state(int r_state) {
            this.r_state = r_state;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getSum_hot() {
            return sum_hot;
        }

        public void setSum_hot(int sum_hot) {
            this.sum_hot = sum_hot;
        }
    }
}
