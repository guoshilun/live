package com.jk.jkproject.ui.entity;

public class ExpLevelInfo extends BaseInfo {

    /**
     * data : {"next":200000,"money":87352630,"userGrade":5,"userExperience":185460}
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
         * next : 200000
         * money : 87352630
         * userGrade : 5
         * userExperience : 185460
         */

        private int next;
        private int money;
        private int userGrade;
        private int userExperience;

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getUserGrade() {
            return userGrade;
        }

        public void setUserGrade(int userGrade) {
            this.userGrade = userGrade;
        }

        public int getUserExperience() {
            return userExperience;
        }

        public void setUserExperience(int userExperience) {
            this.userExperience = userExperience;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "next=" + next +
                    ", money=" + money +
                    ", userGrade=" + userGrade +
                    ", userExperience=" + userExperience +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ExpLevelInfo{" +
                "data=" + data +
                '}';
    }
}
