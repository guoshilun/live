package com.jk.jkproject.ui.entity;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

public class LiveHomeBeanInfo extends BaseInfo implements Serializable {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> paramList) {
        this.data = paramList;
    }

    @Override
    public String toString() {
        return "LiveHomeBeanInfo{" +
                "data=" + data +
                '}';
    }

    public static class DataBean implements Serializable, Comparable<DataBean> {
        private String distance;

        private int live_type;

        private int peopleNumber;

        private String r_cover;

        private String r_msg;

        private String r_name;

        private int r_state; // 0.未直播 1.直播中

        private int rank;

        private String roomId;

        private String u_id;

        private String u_name;

        private String u_picture;

        private int userGrade;
        private int anchorGrade; //	主播等级
        private int sex;// 0.女 1.男
        private String cover;

        //搜索
        private String nickname;
        private int type;
        private String sgin;
        private String userId;
        private String picture;
        private int state;//0.未关注 1.关注


        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getSgin() {
            return sgin;
        }

        public void setSgin(String sgin) {
            this.sgin = sgin;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }



        public int compareTo(DataBean param1DataBean) {
            int i = param1DataBean.rank;
            int j = this.rank;
            return (i > j) ? (i - j) : ((i < j) ? (i - j) : 0);
        }

        public boolean equals(Object param1Object) {
            boolean bool;
            if (param1Object != null && param1Object instanceof DataBean && TextUtils.equals(getU_id(), ((DataBean) param1Object).getU_id())) {
                bool = true;
            } else {
                bool = false;
            }
            return bool;
        }


        public String getDistance() {
            return this.distance;
        }

        public int getLive_type() {
            return this.live_type;
        }

        public int getPeopleNumber() {
            return this.peopleNumber;
        }

        public String getR_msg() {
            return this.r_msg;
        }

        public String getR_name() {
            return this.r_name;
        }

        public int getRank() {
            return this.rank;
        }

        public String getRoomId() {
            return this.roomId;
        }

        public String getU_id() {
            return this.u_id;
        }

        public String getU_name() {
            return this.u_name;
        }

        public String getU_picture() {
            return this.u_picture;
        }


        public void setDistance(String param1String) {
            this.distance = param1String;
        }

        public void setLive_type(int param1Int) {
            this.live_type = param1Int;
        }

        public void setPeopleNumber(int param1Int) {
            this.peopleNumber = param1Int;
        }

        public void setR_msg(String param1String) {
            this.r_msg = param1String;
        }

        public void setR_name(String param1String) {
            this.r_name = param1String;
        }

        public void setRank(int param1Int) {
            this.rank = param1Int;
        }

        public void setRoomId(String param1String) {
            this.roomId = param1String;
        }

        public void setU_id(String param1String) {
            this.u_id = param1String;
        }

        public void setU_name(String param1String) {
            this.u_name = param1String;
        }

        public void setU_picture(String param1String) {
            this.u_picture = param1String;
        }

        public String getR_cover() {
            return r_cover;
        }

        public void setR_cover(String r_cover) {
            this.r_cover = r_cover;
        }

        public int getR_state() {
            return r_state;
        }

        public void setR_state(int r_state) {
            this.r_state = r_state;
        }

        public int getUserGrade() {
            return userGrade;
        }

        public void setUserGrade(int userGrade) {
            this.userGrade = userGrade;
        }

        public int getAnchorGrade() {
            return anchorGrade;
        }

        public void setAnchorGrade(int anchorGrade) {
            this.anchorGrade = anchorGrade;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "distance='" + distance + '\'' +
                    ", live_type=" + live_type +
                    ", peopleNumber=" + peopleNumber +
                    ", r_cover='" + r_cover + '\'' +
                    ", r_msg='" + r_msg + '\'' +
                    ", r_name='" + r_name + '\'' +
                    ", r_state=" + r_state +
                    ", rank=" + rank +
                    ", roomId='" + roomId + '\'' +
                    ", u_id='" + u_id + '\'' +
                    ", u_name='" + u_name + '\'' +
                    ", u_picture='" + u_picture + '\'' +
                    ", userGrade=" + userGrade +
                    ", anchorGrade=" + anchorGrade +
                    ", sex=" + sex +
                    ", cover='" + cover + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", type=" + type +
                    ", sgin='" + sgin + '\'' +
                    ", userId='" + userId + '\'' +
                    ", picture='" + picture + '\'' +
                    ", state=" + state +
                    '}';
        }
    }
}