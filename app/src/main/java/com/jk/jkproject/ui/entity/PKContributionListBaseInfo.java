package com.jk.jkproject.ui.entity;

import java.util.List;

public class PKContributionListBaseInfo extends BaseInfo {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> paramList) {
        this.data = paramList;
    }

    @Override
    public String toString() {
        return "PKContributionListBaseInfo{" +
                "data=" + data +
                '}';
    }

    public static class DataBean {
        private int sum_amount;
        private String userGrade;
        private String nickname;
        private String userId;
        private String picture;

        public int getSum_amount() {
            return sum_amount;
        }

        public void setSum_amount(int sum_amount) {
            this.sum_amount = sum_amount;
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

        public int getIsMVP() {
            return isMVP;
        }

        public void setIsMVP(int isMVP) {
            this.isMVP = isMVP;
        }

        private int isMVP;

        @Override
        public String toString() {
            return "DataBean{" +
                    "sum_amount=" + sum_amount +
                    ", userGrade='" + userGrade + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", userId='" + userId + '\'' +
                    ", picture='" + picture + '\'' +
                    ", isMVP=" + isMVP +
                    '}';
        }
    }

}
