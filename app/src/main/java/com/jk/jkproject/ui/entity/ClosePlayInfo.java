package com.jk.jkproject.ui.entity;

public class ClosePlayInfo extends BaseInfo {

    /**
     * data : {"sumCount":"1","anchorGrade":"1","money":1800,"nickname":"一颗小虎牙","picture":"default-picture","account":"未设置"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sumCount : 1
         * anchorGrade : 1
         * money : 1800
         * nickname : 一颗小虎牙
         * picture : default-picture
         * account : 未设置
         */

        private String sumCount;
        private String anchorGrade;
        private int money;
        private String nickname;
        private String picture;
        private String account;
        private String userId;

        public String getSumCount() {
            return sumCount;
        }

        public void setSumCount(String sumCount) {
            this.sumCount = sumCount;
        }

        public String getAnchorGrade() {
            return anchorGrade;
        }

        public void setAnchorGrade(String anchorGrade) {
            this.anchorGrade = anchorGrade;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
