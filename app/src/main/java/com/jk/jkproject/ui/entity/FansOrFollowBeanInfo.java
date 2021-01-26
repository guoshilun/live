package com.jk.jkproject.ui.entity;

import java.io.Serializable;
import java.util.List;

public class FansOrFollowBeanInfo extends BaseInfo {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * userId : 1000073
         * anchorGrade : 1
         * userGrade : 1
         * sex : 1
         * nickname : 一颗小虎牙
         * sgin : 这个人非常懒，什么都没有留下！！！
         * picture : http://thirdwx.qlogo.cn/mmopen/vi_32/vEsRzCD0cbqoBDlafuE7eUOWV7bluJxGUDYCQT0S84EpOoKQ2xAEvI1x5tE8G7AhqqYypMK0vzDAEKAfoycGnQ/132
         */

        private String userId;
        private String anchorGrade;
        private String userGrade;
        private String sex;
        private String nickname;
        private String sgin;
        private String picture;
        private int r_state;
        private int roomId;
        private int type;
        private int state;

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSgin() {
            return sgin;
        }

        public void setSgin(String sgin) {
            this.sgin = sgin;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }


        @Override
        public String toString() {
            return "DataBean{" +
                    "userId='" + userId + '\'' +
                    ", anchorGrade='" + anchorGrade + '\'' +
                    ", userGrade='" + userGrade + '\'' +
                    ", sex='" + sex + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", sgin='" + sgin + '\'' +
                    ", picture='" + picture + '\'' +
                    '}';
        }

        public int getR_state() {
            return r_state;
        }

        public void setR_state(int r_state) {
            this.r_state = r_state;
        }
    }

    @Override
    public String toString() {
        return "FansOrFollowBeanInfo{" +
                "data=" + data +
                '}';
    }
}