package com.jk.jkproject.utils;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.fansan.common.utils.CacheUtil;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.net.Https;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.info.ConnectByProto;
import com.jk.jkproject.ui.entity.UserDateInfo;

public class SPUtils extends SPBase {
    static {
        INIT_METHOD_DEFAULT = INIT_METHOD_2;
    }

    public static String getIP() {
        return getString(Urls.IM_SERVER_IP, "");
    }

    public static String getNickname() {
        return getString("nickname", "");
    }

    public static String getNumber() {
        return getString("number", "");
    }

    public static void setNumber(String number) {
        putString("number", number);
    }

    public static String getPlatformId() {
        return getString("platformid", "");
    }

    public static String getPortrait() {
        return getString("portrait", "");
    }

    public static String getProt() {
        return getString(Urls.IM_SERVER_port, "");
    }

    public static int getSex() {
        return getInt("sex", 0);
    }

    public static int getStatus() {
        return getInt("autonymstatus", 0);
    }

    public static void setStatusType(int paramInt) {
        putInt("autonymstatus", paramInt);
    }

    public static void setStatusMessage(String message) {
        putString("statusMessage", message);
    }

    public static String getStatusMessage() {
        return getString("statusMessage", "");
    }

    public static int getTeamType() {
        return getInt("teamtype", 0);
    }

    public static String getToken() {
        return getString(Urls.IM_SERVER_TOKEN, "");
    }

    public static int getType() {
        return getInt("type", 0);
    }

    public static String getUserId() {
        return getString("user_id", "");
    }

    public static String getBId() {
        return getString("b_id", "");
    }

    public static String getAnchorGrade() {
        return getString("anchorgrade", "");
    }

    public static String getLatitude() {
//        return getString(Impl.N, "31.196141");
        return getString(Impl.N, "");
    }

    public static void setLatitude(String latitude) {
        putString(Impl.N, latitude);
    }

    public static String getLongitude() {
//        return getString(Impl.E, "121.255648");
        return getString(Impl.E, "");
    }

    public static void setLongitude(String longitude) {
        putString(Impl.E, longitude);
    }


    public static String getRegion() {
        return getString("region", "");
    }

    public static String getPicture() {
        return getString("portrait", "");
    }


    public static void init(Context paramContext) {
        initSP(paramContext, "user_preferences");
    }

    public static void saveUserInfo(ConnectByProto.ConnectByProtoRes paramConnectByProtoRes) {
        LogUtils.e("====saveUserInfo=", paramConnectByProtoRes.toString());
        putString("number", paramConnectByProtoRes.getPhone());
        putString("user_id", paramConnectByProtoRes.getUserId());
        putString("b_id", paramConnectByProtoRes.getBeautifulUserId());
        putString("nickname", paramConnectByProtoRes.getNickname());
        putString("account", paramConnectByProtoRes.getAccount());
        putString("portrait", paramConnectByProtoRes.getPicture());
        putString("birthday", paramConnectByProtoRes.getBirthday());
        putString("sgin", paramConnectByProtoRes.getSgin());
        putString("background", paramConnectByProtoRes.getBackground());
        putString("region", paramConnectByProtoRes.getRegion());
        putInt("userexperience", paramConnectByProtoRes.getUserExperience());
        putInt("usergrade", paramConnectByProtoRes.getUserGrade());
        putInt("anchorexperience", paramConnectByProtoRes.getAnchorExperience());
        putString("anchorgrade", paramConnectByProtoRes.getAnchorGrade() + "");
        putInt("onlinetime", paramConnectByProtoRes.getOnlineTime());
        putInt("sex", paramConnectByProtoRes.getSex());
        putInt("type", paramConnectByProtoRes.getType());
        putInt("pwdstatus", paramConnectByProtoRes.getPwdStatus());
        putInt("userstatus", paramConnectByProtoRes.getUserStatus());//1注册  2 启用  0禁用
        putInt("autonymstatus", paramConnectByProtoRes.getAutonymStatus());
        putString("openId", paramConnectByProtoRes.getOpenId());//微信id
        putString("qqId", paramConnectByProtoRes.getQqId());//qqid
        putString("iphoneId", paramConnectByProtoRes.getIphoneId());//苹果id
        putString("isSkip", paramConnectByProtoRes.getIsSkip());//绑定手机是否跳过  -1没有跳过  1跳过
    }


    public static void saveUserTokenInfo(UserDateInfo.DataBean paramDataBean) {
        putString(Urls.IM_SERVER_TOKEN, paramDataBean.getToken());
        CacheUtil.saveToken(paramDataBean.getToken());
        CacheUtil.saveUserId(paramDataBean.getUserId());
        putString(Urls.IM_SERVER_port, paramDataBean.getPort() + "");
        putString(Urls.IM_SERVER_IP, paramDataBean.getHost());
        putString(Impl.H5_PAGE_URL, paramDataBean.getUrl());
        putString(Impl.getUid(), paramDataBean.getUserId());
        if (!getToken().isEmpty()) {
            Https.get().removeHeader("Access-Token");
            Https.get().addHeader("Access-Token", getToken());
        }
    }


    public static String getH5PageUrl() {
        return getString(Impl.H5_PAGE_URL, "");
    }


    public static void setIsHost(boolean isHost) {
        putBoolean(Impl.IS_HOST, isHost);
    }

    public static boolean getIsHost() {
        return getBoolean(Impl.IS_HOST, false);
    }

    public static int getUserStatus() {
        return getInt(Impl.USERSTATUS, -1);
    }

    public static String getIsSkip() {
        return getString("isSkip", "");
    }

    public static void setTeamType(int paramInt) {
        putInt("teamtype", paramInt);
    }

    public static void setType(int paramInt) {
        putInt("type", paramInt);
    }

    public static void setNickname(String nickname) {
        putString("nickname", nickname);
    }

    public static void setBirthday(String birthday) {
        putString("birthday", birthday);
    }

    public static void setSgin(String sgin) {
        putString("sgin", sgin);
    }

    public static void setSex(int sex) {
        putInt("sex", sex);
    }

    //在线客服未读消息标记
    public static void setKefuIsRead(boolean isRead) {
        putBoolean(Impl.KefuIsRead, isRead);
    }

    //在线客服未读消息标记
    public static void setSystemIsRead(boolean isRead) {
        putBoolean(Impl.SystemIsRead, isRead);
    }

    //在线客服未读消息标记
    public static boolean getKefuIsRead() {
        return getBoolean(Impl.KefuIsRead, false);
    }

    //在线客服未读消息标记
    public static boolean getSystemIsRead() {
        return getBoolean(Impl.SystemIsRead, false);
    }

    public static void setPicture(String portrait) {
        putString("portrait", portrait);
    }

    //青少年提示
    public static boolean getTeenTips() {
        return getBoolean(Impl.TeenTips, true);
    }

    //青少年提示
    public static boolean setTeenTips(boolean isTeen) {
        return putBoolean(Impl.TeenTips, isTeen);
    }


    //主播禁播Type
    public static int getHostType() {
        return getInt(Impl.HOST_TYPE, -1);
    }

    //主播禁播Type
    public static boolean setHostType(int type) {
        return putInt(Impl.HOST_TYPE, type);
    }

    //主播禁播Time
    public static String getHostTime() {
        return getString(Impl.HOST_TIME, "");
    }

    //主播禁播Type
    public static void setHostTime(String time) {
        putString(Impl.HOST_TIME, time);
    }

    //主播禁播Message
    public static String getHostMessage() {
        return getString(Impl.HOST_MESSAGE, "");
    }

    //主播禁播Message
    public static void setHostMessage(String message) {
        putString(Impl.HOST_MESSAGE, message);
    }

    public static String getPKID() {
        return getString("PKID", "");
    }

    public static void setPKID(String PKID) {
        putString("PKID", PKID);
    }

    public static class Impl {
        public static final String KefuIsRead = "kefuisread";
        public static final String SystemIsRead = "systemisread";
        public static final String TeenTips = "teentips";
        public static final String H5_PAGE_URL = "h5_page_url";
        public static final String HOST_TYPE = "host_type";
        public static final String HOST_TIME = "host_time";
        public static final String HOST_MESSAGE = "host_message";

        public static final String ACCOUNT = "account";

        public static final String ANCHOREXPERIENCE = "anchorexperience";

        public static final String ANCHORGRADE = "anchorgrade";

        public static final String APPLE_AUDITING = "apple_auditing";

        public static final String AUTONYMSTATUS = "autonymstatus";

        public static final String BACKGROUND = "background";

        public static final String BIRTHDAY = "birthday";

        public static final String FLAG_LIVE_GIFTS_IDS = "live_gifts_ids";

        public static final String FLAG_TOKEN = "KEY_HTTP_TOKEN";

        public static final String FLAG_UID = "KEY_UID";

        public static final String NICK_NAME = "nickname";

        public static final String NUMBER = "number";

        public static final String ONLINETIME = "onlinetime";

        public static final String PLATFORMID = "platformid";

        public static final String PORTRAIT = "portrait";

        public static final String PWDSTATUS = "pwdstatus";

        public static final String REGION = "region";

        public static final String SEX = "sex";

        public static final String SGIN = "sgin";

        public static final String STATUS = "status";

        public static final String TEAMTYPE = "teamtype";

        public static final String TYPE = "type";

        public static final String USEREXPERIENCE = "userexperience";

        public static final String USERGRADE = "usergrade";

        public static final String USERSTATUS = "userstatus";

        public static final String USER_ID = "user_id";

        public static final String IS_HOST = "is_host";

        public static final String N = "latitude"; //北纬
        public static final String E = "longitude"; //东经


        public static int getAppAuditing() {
            SPBase.getInt("apple_auditing", 1);
            return SPBase.getInt("apple_auditing", 0);
        }

        public static String getToken() {
            String str1 = (AppApplication.getInstance()).TOKEN;
            String str2 = str1;
            if (TextUtils.isEmpty(str1)) {
                str2 = SPBase.getString("KEY_HTTP_TOKEN", "");
                (AppApplication.getInstance()).TOKEN = str2;
            }
            return str2;
        }

        public static String getUid() {
            String str1 = (AppApplication.getInstance()).UID;
            String str2 = str1;
            if (TextUtils.isEmpty(str1)) {
                str1 = SPBase.getString("KEY_UID", "");
                if (TextUtils.isEmpty(str1)) {
                    String str = getToken();
                    str2 = str1;
                    if (!TextUtils.isEmpty(str)) {
                        String[] arrayOfString = str.split("|");
                        str2 = str1;
                        if (arrayOfString != null) {
                            str2 = str1;
                            if (arrayOfString.length == 3) {
                                str2 = arrayOfString[0];
                                setUid(str2);
                                (AppApplication.getInstance()).UID = str2;
                            }
                        }
                    }
                } else {
                    (AppApplication.getInstance()).UID = str1;
                    str2 = str1;
                }
            }
            return str2;
        }

        public static void setUid(String param1String) {
            SPBase.putString("KEY_UID", param1String);
        }
    }
}
