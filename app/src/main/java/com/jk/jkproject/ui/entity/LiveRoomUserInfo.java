package com.jk.jkproject.ui.entity;

import java.io.Serializable;

public class LiveRoomUserInfo extends BaseInfo implements Serializable {
    private DataBean data;

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean paramDataBean) {
        this.data = paramDataBean;
    }

    @Override
    public String toString() {
        return "LiveRoomUserInfo{" +
                "data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        private String E;

        private String N;

        private String account;

        private String anchorExperience;

        private String anchorGrade;

        private String attention;

        public String getBeautifulUserId() {
            return beautifulUserId;
        }

        public void setBeautifulUserId(String beautifulUserId) {
            this.beautifulUserId = beautifulUserId;
        }

        private String beautifulUserId;

        private String autonymStatus;

        private String background;

        private String birthday;

        private String equipment;

        private String fans;

        private String giver;

        private String gold;

        private String ip;

        private String isAttention;

        private String liveStatus;

        private String nickname;

        private String offlineTime;

        private String onlineTime;

        private String phone;

        private String picture;

        private String pwdStatus;

        private String region;

        private String sex;

        private String sgin;

        private String type; //1 普通用户  2.主播  3.管理员 4.客服
        private String myType; //1 普通用户  2.主播  3.管理员

        private String update;

        private String userExperience;

        private String userGrade;

        private String userId;

        private String userStatus;

        private int banned; //禁言  -2正常  其他都不正常

        private String openId;
        private String qqId;
        private String iphoneId;

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getQqId() {
            return qqId;
        }

        public void setQqId(String qqId) {
            this.qqId = qqId;
        }

        public String getIphoneId() {
            return iphoneId;
        }

        public void setIphoneId(String iphoneId) {
            this.iphoneId = iphoneId;
        }

        public String getAccount() {
            return this.account;
        }

        public String getAnchorExperience() {
            return this.anchorExperience;
        }

        public String getAnchorGrade() {
            return this.anchorGrade;
        }

        public String getAttention() {
            return this.attention;
        }

        public String getAutonymStatus() {
            return this.autonymStatus;
        }

        public String getBackground() {
            return this.background;
        }

        public String getBirthday() {
            return this.birthday;
        }

        public String getE() {
            return this.E;
        }

        public String getEquipment() {
            return this.equipment;
        }

        public String getFans() {
            return this.fans;
        }

        public String getGiver() {
            return this.giver;
        }

        public String getGold() {
            return this.gold;
        }

        public String getIp() {
            return this.ip;
        }

        public String getIsAttention() {
            return this.isAttention;
        }

        public String getLiveStatus() {
            return this.liveStatus;
        }

        public String getN() {
            return this.N;
        }

        public String getNickname() {
            return this.nickname;
        }

        public String getOfflineTime() {
            return this.offlineTime;
        }

        public String getOnlineTime() {
            return this.onlineTime;
        }

        public String getPhone() {
            return this.phone;
        }

        public String getPicture() {
            return this.picture;
        }

        public String getPwdStatus() {
            return this.pwdStatus;
        }

        public String getRegion() {
            return this.region;
        }

        public String getSex() {
            return this.sex;
        }

        public String getSgin() {
            return this.sgin;
        }

        public String getType() {
            return this.type;
        }

        public String getUpdate() {
            return this.update;
        }

        public String getUserExperience() {
            return this.userExperience;
        }

        public String getUserGrade() {
            return this.userGrade;
        }

        public String getUserId() {
            return this.userId;
        }

        public String getUserStatus() {
            return this.userStatus;
        }

        public void setAccount(String param1String) {
            this.account = param1String;
        }

        public void setAnchorExperience(String param1String) {
            this.anchorExperience = param1String;
        }

        public void setAnchorGrade(String param1String) {
            this.anchorGrade = param1String;
        }

        public void setAttention(String param1String) {
            this.attention = param1String;
        }

        public void setAutonymStatus(String param1String) {
            this.autonymStatus = param1String;
        }

        public void setBackground(String param1String) {
            this.background = param1String;
        }

        public void setBirthday(String param1String) {
            this.birthday = param1String;
        }

        public void setE(String param1String) {
            this.E = param1String;
        }

        public void setEquipment(String param1String) {
            this.equipment = param1String;
        }

        public void setFans(String param1String) {
            this.fans = param1String;
        }

        public void setGiver(String param1String) {
            this.giver = param1String;
        }

        public void setGold(String param1String) {
            this.gold = param1String;
        }

        public void setIp(String param1String) {
            this.ip = param1String;
        }

        public void setIsAttention(String param1String) {
            this.isAttention = param1String;
        }

        public void setLiveStatus(String param1String) {
            this.liveStatus = param1String;
        }

        public void setN(String param1String) {
            this.N = param1String;
        }

        public void setNickname(String param1String) {
            this.nickname = param1String;
        }

        public void setOfflineTime(String param1String) {
            this.offlineTime = param1String;
        }

        public void setOnlineTime(String param1String) {
            this.onlineTime = param1String;
        }

        public void setPhone(String param1String) {
            this.phone = param1String;
        }

        public void setPicture(String param1String) {
            this.picture = param1String;
        }

        public void setPwdStatus(String param1String) {
            this.pwdStatus = param1String;
        }

        public void setRegion(String param1String) {
            this.region = param1String;
        }

        public void setSex(String param1String) {
            this.sex = param1String;
        }

        public void setSgin(String param1String) {
            this.sgin = param1String;
        }

        public void setType(String param1String) {
            this.type = param1String;
        }

        public void setUpdate(String param1String) {
            this.update = param1String;
        }

        public void setUserExperience(String param1String) {
            this.userExperience = param1String;
        }

        public void setUserGrade(String param1String) {
            this.userGrade = param1String;
        }

        public void setUserId(String param1String) {
            this.userId = param1String;
        }

        public void setUserStatus(String param1String) {
            this.userStatus = param1String;
        }


        public int getBanned() {
            return banned;
        }

        public void setBanned(int banned) {
            this.banned = banned;
        }

        public String getMyType() {
            return myType;
        }

        public void setMyType(String myType) {
            this.myType = myType;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "E='" + E + '\'' +
                    ", N='" + N + '\'' +
                    ", account='" + account + '\'' +
                    ", anchorExperience='" + anchorExperience + '\'' +
                    ", anchorGrade='" + anchorGrade + '\'' +
                    ", attention='" + attention + '\'' +
                    ", autonymStatus='" + autonymStatus + '\'' +
                    ", background='" + background + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", equipment='" + equipment + '\'' +
                    ", fans='" + fans + '\'' +
                    ", giver='" + giver + '\'' +
                    ", gold='" + gold + '\'' +
                    ", ip='" + ip + '\'' +
                    ", isAttention='" + isAttention + '\'' +
                    ", liveStatus='" + liveStatus + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", offlineTime='" + offlineTime + '\'' +
                    ", onlineTime='" + onlineTime + '\'' +
                    ", phone='" + phone + '\'' +
                    ", picture='" + picture + '\'' +
                    ", pwdStatus='" + pwdStatus + '\'' +
                    ", region='" + region + '\'' +
                    ", sex='" + sex + '\'' +
                    ", sgin='" + sgin + '\'' +
                    ", type='" + type + '\'' +
                    ", myType='" + myType + '\'' +
                    ", update='" + update + '\'' +
                    ", userExperience='" + userExperience + '\'' +
                    ", userGrade='" + userGrade + '\'' +
                    ", userId='" + userId + '\'' +
                    ", userStatus='" + userStatus + '\'' +
                    ", banned=" + banned +
                    '}';
        }
    }
}