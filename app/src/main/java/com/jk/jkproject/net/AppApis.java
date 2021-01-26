package com.jk.jkproject.net;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.UserGameListBean;
import com.jk.jkproject.net.okhttp.RequestParams;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.net.okhttp.progress.listener.UploadListener;
import com.jk.jkproject.ui.chat.ChatListBeanInfo;
import com.jk.jkproject.ui.chat.ChatRecordBeanInfo;
import com.jk.jkproject.ui.entity.BannerBeanInfo;
import com.jk.jkproject.ui.entity.BaseInfo;
import com.jk.jkproject.ui.entity.BlackList;
import com.jk.jkproject.ui.entity.ClosePlayInfo;
import com.jk.jkproject.ui.entity.ExpLevelInfo;
import com.jk.jkproject.ui.entity.FanContributionList;
import com.jk.jkproject.ui.entity.GetFocusList;
import com.jk.jkproject.ui.entity.HomeTabClassify;
import com.jk.jkproject.ui.entity.HomeTabList;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.entity.LiveHomeFansCountBaseInfo;
import com.jk.jkproject.ui.entity.LiveHomeFoundBaseInfo;
import com.jk.jkproject.ui.entity.LiveHostClost;
import com.jk.jkproject.ui.entity.LiveOpenPlayInfo;
import com.jk.jkproject.ui.entity.LiveRankInfoBean;
import com.jk.jkproject.ui.entity.LiveRankInfoMyBean;
import com.jk.jkproject.ui.entity.LiveRoomBannedTime;
import com.jk.jkproject.ui.entity.LiveRoomBaseInfo;
import com.jk.jkproject.ui.entity.LiveRoomBeanList;
import com.jk.jkproject.ui.entity.LiveRoomContributionInfo;
import com.jk.jkproject.ui.entity.LiveRoomUserInfo;
import com.jk.jkproject.ui.entity.LiveRoomUserLockList;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.entity.MyDiamondBean;
import com.jk.jkproject.ui.entity.MyMoneyBean;
import com.jk.jkproject.ui.entity.MyRecordsListBean;
import com.jk.jkproject.ui.entity.PKContributionListBaseInfo;
import com.jk.jkproject.ui.entity.PKRecordBaseInfo;
import com.jk.jkproject.ui.entity.PKRecordValueBaseInfo;
import com.jk.jkproject.ui.entity.PhonePrefixInfo;
import com.jk.jkproject.ui.entity.QiNiuTokenInfo;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.ui.entity.TeamApplyMessageInfo;
import com.jk.jkproject.ui.entity.TeamBaseInfo;
import com.jk.jkproject.ui.entity.TeamCenterInfo;
import com.jk.jkproject.ui.entity.TeamMembersInfo;
import com.jk.jkproject.ui.entity.TeamMembersManagermentInfo;
import com.jk.jkproject.ui.entity.TeamSigingDealWithInfo;
import com.jk.jkproject.ui.entity.TeamStatisticalInfo;
import com.jk.jkproject.ui.entity.UpdateInfo;
import com.jk.jkproject.ui.entity.UserDateInfo;
import com.jk.jkproject.ui.entity.pay.AppPayInfo;
import com.jk.jkproject.utils.AppUtils;
import com.jk.jkproject.utils.DeviceUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.lib_egg.pojo.EggBean;
import com.jk.lib_egg.pojo.GameListBean;
import com.jk.lib_egg.pojo.GameResultBean;
import com.jk.weblib.EsportBean;

public class AppApis {
    public static void cancel(String paramString) {
        Https.get().cancel(paramString);
    }


    public static RequestParams getParams() {
        RequestParams params = RequestParams.get();
        params.clear();
        String time = System.currentTimeMillis() + "";
        params.put("device", "android");
        params.put("app_version", AppUtils.getVersionName());
        params.put("time_stamp", time);
        params.put("pkg_channel", AppUtils.getAppMetaData());
        try {
            params.put("device_id", DeviceUtils.getDeviceId());
        } catch (Exception ex) {

        }
        return params;
    }

    public static RequestParams getParamsWithNoSign() {
        RequestParams requestParams = RequestParams.get();
        requestParams.clear();
        requestParams.put("device", "android");
        requestParams.put("pkg_channel", AppUtils.getAppMetaData());
        requestParams.put("app_version", AppUtils.getVersionName());
        requestParams.put("device_id", DeviceUtils.getDeviceId());
        return requestParams;
    }

    public static RequestParams getParamsWithUidToken() {
        RequestParams requestParams = getParams();
        requestParams.put("uid", SPUtils.Impl.getUid());
        requestParams.put("token", SPUtils.Impl.getToken());
        return requestParams;
    }

    public static void getPhoneCode(String paramString1, String paramString2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("phone", paramString1);
        paramString1 = paramString2;
        if (paramString2.isEmpty())
            paramString1 = "+ 86";
        requestParams.put("prefix", paramString1);
        Https.get().post(Urls.CODE_LOGIN, requestParams, paramResponseListener);
    }

    public static void getReportUser(int paramInt, String paramString, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", paramInt);
        requestParams.put("tagId", paramString);
        Https.get().post(Urls.GET_REPORT_USER, requestParams, paramResponseListener);
    }

    public static void getResetPassword(String paramString1, String paramString2, String paramString3, String paramString4, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("phone", paramString1);
        requestParams.put("password", paramString2);
        requestParams.put("twicePassword", paramString3);
        requestParams.put("code", paramString4);
        Https.get().post(Urls.RESET_PASSWORD_LOGIN, requestParams, paramResponseListener);
    }

    public static void getTeamApplyJoin(String paramString1, String paramString2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("teamId", paramString1);
        requestParams.put("exMsg", paramString2);
        Https.get().post(Urls.GET_TEAM_APPLY_JOIN, requestParams, paramResponseListener);
    }

    public static void getTeamApplyMessage(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_TEAM_APPLY_MESSAGE, requestParams, paramResponseListener, TeamApplyMessageInfo.class);
    }

    public static void getTeamAppointment(String paramString, int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("memberUserId", paramString);
        requestParams.put("teamGrade", Integer.valueOf(paramInt));
        Https.get().post(Urls.GET_TEAM_APPOINTMENT, requestParams, paramResponseListener, TeamMembersManagermentInfo.class);
    }

    public static void getTeamBaseInfo(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_TEAM_INFO, requestParams, paramResponseListener, TeamBaseInfo.class);
    }

    public static void getTeamCencal(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_TEAM_CENCAL_MESSAGE, requestParams, paramResponseListener);
    }

    public static void getTeamCenter(String paramString, int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("search", paramString);
        requestParams.put("currentPage", Integer.valueOf(paramInt));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_TEAM_CENTER, requestParams, paramResponseListener, TeamCenterInfo.class);
    }

    public static void getTeamContractManagement(int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", Integer.valueOf(paramInt));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_TEAM_CONTRACT_MANAGEMENT, requestParams, paramResponseListener, TeamMembersManagermentInfo.class);
    }

    public static void getTeamDissolution(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_TEAM_DISSOLUTION, requestParams, paramResponseListener);
    }

    public static void getTeamEditorInfo(String paramString1, String paramString2, String paramString3, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("tm_name", paramString1);
        requestParams.put("tm_url", paramString2);
        requestParams.put("tm_msg", paramString3);
        Https.get().post(Urls.GET_TEAM_EDITOR_INFO, requestParams, paramResponseListener);
    }

    public static void getTeamGetContribution(String paramString, int paramInt1, int paramInt2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", Integer.valueOf(paramInt1));
        requestParams.put("roomId", paramString);
        requestParams.put("currentPage", Integer.valueOf(paramInt2));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_LIVE_ROOM_CONTRIBUTION, requestParams, paramResponseListener, LiveRoomContributionInfo.class);
    }

    public static void getTeamGetTeamDataStatistics(int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", Integer.valueOf(paramInt));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_TEAM_DATASTATISTICS, requestParams, paramResponseListener, TeamStatisticalInfo.class);
    }

    public static void getTeamJoinList(int paramInt1, int paramInt2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", Integer.valueOf(paramInt1));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        requestParams.put("type", Integer.valueOf(paramInt2));
        Https.get().post(Urls.GET_TEAM_JOINLIST, requestParams, paramResponseListener, TeamSigingDealWithInfo.class);
    }

    public static void getTeamMembersList(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_TEAM_MEMBERS, requestParams, paramResponseListener);
    }

    public static void getTeamNewTeam(String paramString1, String paramString2, String paramString3, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("tmName", paramString1);
        requestParams.put("tmUrl", paramString2);
        requestParams.put("tmMsg", paramString3);
        Https.get().post(Urls.GET_TEAM_NEW_TEAM, requestParams, paramResponseListener);
    }

    public static void getTeamReturn(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_TEAM_RETURN, requestParams, paramResponseListener);
    }

    public static void getTeamTermination(String paramString, int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("team_uid", paramString);
        requestParams.put("type", Integer.valueOf(paramInt));
        Https.get().post(Urls.GET_TEAM_TERMINATION, requestParams, paramResponseListener, TeamMembersManagermentInfo.class);
    }

    public static void getTeamUpdateApprovalToJoin(int paramInt1, int paramInt2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("id", Integer.valueOf(paramInt1));
        requestParams.put("state", Integer.valueOf(paramInt2));
        Https.get().post(Urls.GET_TEAM_APPROVAL_TO_JOIN, requestParams, paramResponseListener);
    }

    public static void getTeamUserList(int page, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", page);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_TEAM_USER_LIST, requestParams, paramResponseListener, TeamMembersInfo.class);
    }

    public static void getUpdateBlick(int type, String c_b_uid, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", type);
        requestParams.put("c_b_uid", c_b_uid);
        Https.get().post(Urls.GET_UPDATE_BLACK, requestParams, paramResponseListener);
    }

    //关注
    public static void getUpdateFollow(int type, String u_id, ResponseListener paramResponseListener) {
        LogUtils.e("=====getUpdateFollow=", type + ",=" + u_id);
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", type);
        requestParams.put("coverFollowUid", u_id);
        Https.get().post(Urls.GET_UPDATE_FOLLOW, requestParams, paramResponseListener);
    }

    public static void login(String phone, String password, int type, String code, String prefix, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("phone", phone);
        if (!password.isEmpty())
            requestParams.put("password", password);
        requestParams.put("type", type);
        requestParams.put("code", code);
        requestParams.put("prefix", prefix);
        Https.get().post(Urls.USER_LOGIN, requestParams, paramResponseListener, UserDateInfo.class);
    }

    public static void uploadToAlliOss(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, UploadListener paramUploadListener) {
        RequestParams requestParams = getParams();
        requestParams.put("OSSAccessKeyId", paramString4);
        requestParams.put("policy", paramString5);
        requestParams.put("Signature", paramString6);
        requestParams.put("callback", paramString7);
        requestParams.put("key", paramString8);
        requestParams.put("x:uid", SPUtils.Impl.getUid());
        requestParams.put("x:type", Integer.valueOf(paramInt));
        HttpUtil.get().sendFile(paramString1, paramString2, paramString3, requestParams, "", paramUploadListener);
    }

    public static void visitorLogin(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.VISITOR_LOGIN, requestParams, paramResponseListener, UserDateInfo.class);
    }


    //    ==============================================================================================================
    public static void checkUpdate(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", 2);
        Https.get().post(Urls.VERSION_CHECK, requestParams, paramResponseListener, UpdateInfo.class);
    }

    //获取黑名单列表
    public static void getAdminlist(int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", Integer.valueOf(paramInt));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_ADMIN_LIST, requestParams, paramResponseListener, BlackList.class);
    }

    //获取管理员列表
    public static void getAdminlistInfo(int page, String roomId, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", roomId);
        requestParams.put("currentPage", page);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_ADMIN_LIST_INFO, requestParams, paramResponseListener, BlackList.class);
    }

    public static void getBannedlist(int page, String roomId, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", roomId);
        requestParams.put("currentPage", page);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_BANNED_LIST, requestParams, paramResponseListener, BlackList.class);
    }

    public static void getBlacklist(int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", Integer.valueOf(paramInt));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_BLACK_LIST, requestParams, paramResponseListener, BlackList.class);
    }

    public static void getGetPhonePrefix(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_PHONE_PREFIX, requestParams, paramResponseListener, PhonePrefixInfo.class);
    }

    public static void getGiftList(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_GIFT_LIST, requestParams, paramResponseListener, LiveGiftInfoBean.class);
    }

    public static void getBackpack(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type",1);
        Https.get().post(Urls.backpack, requestParams, paramResponseListener, LiveGiftInfoBean.class);
    }

    public static void getGitAddRoomAdmin(String paramString1, String paramString2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", paramString1);
        requestParams.put("userId", paramString2);
        Https.get().post(Urls.GET_LIVE_ROOM_ADMIN, requestParams, paramResponseListener);
    }

    public static void getGitAnchorAsk(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_ANCHORASK, requestParams, paramResponseListener);
    }

    public static void getGitBlackList(int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", Integer.valueOf(paramInt));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_BLACK_LIST, requestParams, paramResponseListener, BlackList.class);
    }

    public static void getGitDistinguished(int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", Integer.valueOf(paramInt));
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_LIVE_ROOM_DISTINGUISHED, requestParams, paramResponseListener, LiveRoomContributionInfo.class);
    }


    //意见反馈
    public static void getGitFeedBack(String fb_msg, String img, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("fb_msg", fb_msg);
        requestParams.put("img", img);
        Https.get().post(Urls.GET_FEED_BACK, requestParams, paramResponseListener);
    }

    public static void getGitFocusList(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_FOCUS_LIST, requestParams, paramResponseListener, GetFocusList.class);
    }

    public static void getGitIdentityAuth(String u_name, String u_id_number, String u_just_url, String u_back_url, String u_hold_url, ResponseListener paramResponseListener) {
        LogUtils.e("u_just_url=", u_just_url);
        LogUtils.e("u_back_url=", u_back_url);
        LogUtils.e("u_hold_url=", u_hold_url);
        RequestParams requestParams = RequestParams.get();
        requestParams.put("u_name", u_name);
        requestParams.put("u_id_number", u_id_number);
        requestParams.put("u_just_url", u_just_url);
        requestParams.put("u_back_url", u_back_url);
        requestParams.put("u_hold_url", u_hold_url);
        Https.get().post(Urls.GET_IDENTITY_AUTH, requestParams, paramResponseListener);
    }

    public static void getGitLiveRoomBannedTime(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_LIVE_ROOM_BANNED_TIME, requestParams, paramResponseListener, LiveRoomBannedTime.class);
    }

    public static void getGitLiveRoomUserInfo(String roomId, String tagId, ResponseListener paramResponseListener) {
        LogUtils.e("=======", roomId + "==" + tagId);
        RequestParams requestParams = RequestParams.get();
        requestParams.put("tagId", tagId);
        requestParams.put("roomId", roomId);
        Https.get().post(Urls.GET_LIVE_ROOM_USER_INFO, requestParams, paramResponseListener, LiveRoomUserInfo.class);
    }

    public static void getGitOpenLive(String roomTitle, String cover, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomTitle", roomTitle);
        requestParams.put("cover", cover);
        requestParams.put("N", SPUtils.getLatitude());
        requestParams.put("E", SPUtils.getLongitude());
        Https.get().post(Urls.GET_OPEN_PLAY, requestParams, paramResponseListener, LiveOpenPlayInfo.class);
    }

    public static void getGitPicToken(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_QINIU_TOKEN, requestParams, paramResponseListener, QiNiuTokenInfo.class);
    }

    public static void getGitUserInfo(String uid, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("userId", uid);
        Https.get().post(Urls.GET_USER_INFO, requestParams, paramResponseListener, LiveRoomUserInfo.class);
    }

    public static void getGitTagIdInfo(String uid, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("tagId", uid);
        Https.get().post(Urls.GET_TAGID_INFO, requestParams, paramResponseListener, LiveRoomUserInfo.class);
    }

    public static void getHomeBanndData(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_HOME_BANNER_LIST, requestParams, paramResponseListener, BannerBeanInfo.class);
    }

    public static void getHomeData(int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", paramInt);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_HOME_FOLLOW_LIST, requestParams, paramResponseListener, LiveHomeBeanInfo.class);
    }

    public static void getHomeDataList(int paramInt1, int paramInt2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("page", Integer.valueOf(paramInt1));
        requestParams.put("columnType", Integer.valueOf(paramInt2));
        Https.get().post(Urls.HOME_TAB_LIST, requestParams, paramResponseListener, HomeTabList.class);
    }

    public static void getHomeFansCountAndFollowData(String paramString, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("userId", paramString);
        Https.get().post(Urls.GET_LIVE_FANS_FOLLOW_DATA, requestParams, paramResponseListener, LiveHomeFansCountBaseInfo.class);
    }

    public static void getHomeFoundData(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_HOME_FOUND_LIST, requestParams, paramResponseListener, LiveHomeFoundBaseInfo.class);
    }

    public static void getHomeHotData(int paramInt, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", paramInt);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        requestParams.put("N", SPUtils.getLatitude());
        requestParams.put("E", SPUtils.getLongitude());
        Https.get().post(Urls.GET_HOME_HOT_LIST, requestParams, paramResponseListener, LiveHomeBeanInfo.class);
    }

    public static void getHomeNearData(int currentPage, int type, ResponseListener paramResponseListener) {
        LogUtils.e("type=", type);
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", type);
        requestParams.put("currentPage", currentPage);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        requestParams.put("N", SPUtils.getLatitude());
        requestParams.put("E", SPUtils.getLongitude());
        Https.get().post(Urls.GET_HOME_NEAR_LIST, requestParams, paramResponseListener, LiveHomeBeanInfo.class);
    }

    public static void getHomeRecommendedData(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_HOME_REMANNDE_LIST, requestParams, paramResponseListener, LiveHomeBeanInfo.class);
    }

    public static void getHomeTabClassify(ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().get(Urls.HOME_TAB_CLASSIFY, requestParams, paramResponseListener, HomeTabClassify.class);
    }

    public static void getHostRank(String paramString, int type, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("anchor_userId", paramString);
        requestParams.put("type", type);
        Https.get().post(Urls.GET_HOST_RANK_MY, requestParams, paramResponseListener, LiveRankInfoMyBean.class);
    }

    public static void getMyUseRank(int type, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", type);
        Https.get().post(Urls.GET_USER_RANK_MY, requestParams, paramResponseListener, LiveRankInfoMyBean.class);
    }

    public static void getHostRankList(int paramInt1, int paramInt2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", paramInt1);
        requestParams.put("currentPage", paramInt2);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_HOST_RANK_LIST, requestParams, paramResponseListener, LiveRankInfoBean.class);
    }

    public static void getHostContributionRankList(int paramInt1, int paramInt2, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", paramInt1);
        requestParams.put("currentPage", paramInt2);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_HOST_RANK_LIST, requestParams, paramResponseListener, FanContributionList.class);
    }

    public static void getIMServerIP(ResponseListener paramResponseListener) {
        RequestParams requestParams = getParamsWithUidToken();
        Https.get().post(Urls.IM_SERVER_IP, requestParams, paramResponseListener, String.class);
    }

    public static void getLiveRoomBaseInfo(String paramString, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", paramString);
        Https.get().post(Urls.GET_LIVE_ROOM_BASE_INFO, requestParams, paramResponseListener, LiveRoomBaseInfo.class);
    }

    public static void getLiveRoomLockList(int paramInt, String paramString, ResponseListener paramResponseListener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", paramString);
        requestParams.put("page", Integer.valueOf(paramInt));
        requestParams.put("pageSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_LIVE_FANS_USER_LOCK_LIST, requestParams, paramResponseListener, LiveRoomUserLockList.class);
    }

    //赠送礼物
    public static void getSendGift(String PKId, String roomId, String anchor_userId, String g_id, String count, int g_status, int combo, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", roomId);
        LogUtils.e("PKId=", PKId);
        if (!PKId.equals("")) {
            requestParams.put("PKId", PKId);
        }
        requestParams.put("anchor_userId", anchor_userId);
        requestParams.put("g_id", g_id);
        requestParams.put("count", count);
        requestParams.put("g_status", g_status);
        requestParams.put("combo", combo);
        Https.get().post(Urls.GET_LIVE_SEND_GIFT, requestParams, listener);
    }

    //赠送礼物
    public static void getSendPocketGift(String PKId, String roomId, String anchor_userId, String g_id, String count, int g_status, int combo, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", roomId);
        LogUtils.e("PKId=", PKId);
        if (!PKId.equals("")) {
            requestParams.put("PKId", PKId);
        }
        requestParams.put("anchor_userId", anchor_userId);
        requestParams.put("p_id", g_id);
        requestParams.put("count", count);
        requestParams.put("g_status", g_status);
        requestParams.put("combo", combo);
        Https.get().post(Urls.GET_LIVE_SEND_POCKET_GIFT, requestParams, listener);
    }

    //主播下播
    public static void getClosePlay(String roomId, ResponseListener listener) {
        LogUtils.e("roomId=", roomId);
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", roomId);
        Https.get().post(Urls.GET_LIVE_ROOM_CLOSE_PLAY, requestParams, listener, ClosePlayInfo.class);
    }

    //搜索
    public static void getHomeSearch(String condition, int page, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("condition", condition);
        requestParams.put("page", page);
        requestParams.put("pageSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_LIVE_ROOM_SEARCH, requestParams, listener, LiveHomeBeanInfo.class);
    }

    //搜索
    public static void closeGame(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.closeGame, requestParams, listener, BaseInfo.class);
    }

    //聊天用户列表
    public static void getChatList(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
//        requestParams.put("pageSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_CHAT_LIST, requestParams, listener, ChatListBeanInfo.class);
    }

    //获取消息历史记录
    public static void getChatMsg(String target, int page, ResponseListener listener) {
        LogUtils.e("====", target + "==" + page);
        RequestParams requestParams = RequestParams.get();
        requestParams.put("target", target);
        requestParams.put("page", page);
        requestParams.put("pageSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_CHAT_MSG, requestParams, listener, ChatRecordBeanInfo.class);
    }

    //修改用户信息
    public static void getEditUserInfo(String picture, String nickname, String sex, String birthday, String sgin, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("picture", picture);
        requestParams.put("nickname", nickname);
        requestParams.put("sex", sex);
        requestParams.put("birthday", birthday);
        requestParams.put("sgin", sgin);
        Https.get().post(Urls.GET_EDITOR_USER_INFO, requestParams, listener);
    }

    //用户粉丝和关注列表
    public static void getUserFansList(String userId, int type, int currentPage, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("userId", userId);
        requestParams.put("type", type);
        requestParams.put("currentPage", currentPage);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_USER_FANS_LIST, requestParams, listener, LiveHomeBeanInfo.class);
    }

    //系统通知列表
    public static void getSystemNotificationList(int currentPage, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", currentPage);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_SYSTEM_NOTI_LIST, requestParams, listener, SystemNotiBeanInfo.class);
    }

    //系统通知列表
    public static void getKefuInfo(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_KEFU_INFO, requestParams, listener);
    }

    //获取旧的直播间信息
    public static void getGetOldRoomInfo(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_OLD_ROOM_INFO, requestParams, listener);
    }

    //获取礼物面板经验值
    public static void getGetExpLevelInfo(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_EXPLEVEL_INFO, requestParams, listener, ExpLevelInfo.class);
    }

    //获取礼物面板经验值
    public static void getGetClearCountInfo(String target, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("target", target);
        Https.get().post(Urls.GET_CLEARCOUNT_INFO, requestParams, listener);
    }

    //删除会话列表
    public static void getGetRemoveConversationInfo(String target, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("target", target);
        Https.get().post(Urls.GET_REMOVECONVERSATION_INFO, requestParams, listener);
    }

    //清空所有未读消息
    public static void getGetClearAllCountInfo(String tagArr, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("tagArr", tagArr);
        Https.get().post(Urls.GET_CLEARALLCOUNT_INFO, requestParams, listener);
    }

    //设置自动回复
    public static void getGetAutoReply(String reply, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("reply", reply);
        Https.get().post(Urls.GET_AUTO_REPLY, requestParams, listener);
    }

    //获取自动回复
    public static void getGetAutoReplyInfo(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_AUTO_REPLY_INFO, requestParams, listener);
    }

    /**
     * @param type     1 系统消息 2 关注消息 3 开播消息
     * @param listener
     */
    public static void getSetMsgNoti(int type, int state, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", type);
        requestParams.put("data", state);
        Https.get().post(Urls.SET_MSG_NOTI, requestParams, listener);
    }

    /**
     * @param listener
     */
    public static void getGetMsgNoti(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_MSG_NOTI, requestParams, listener);
    }


    //微信登录

    /**
     * @param type         1.微信 2.QQ
     * @param access_token
     * @param openid
     * @param listener
     */
    public static void setWXLogin(int type, String access_token, String openid, ResponseListener listener) {
        LogUtils.e("setWXLogin=access_token=", access_token);
        LogUtils.e("setWXLogin=openid=", openid);
        RequestParams requestParams = RequestParams.get();
        requestParams.put("access_token", access_token);
        requestParams.put("openid", openid);
        switch (type) {
            case 1:
                Https.get().post(Urls.SET_WX_LOGIN, requestParams, listener, UserDateInfo.class);
                break;
            case 2:
                Https.get().post(Urls.SET_QQ_LOGIN, requestParams, listener, UserDateInfo.class);
                break;
        }
    }

    //微信绑定
    public static void setWXBinding(int type, String access_token, String openid, ResponseListener listener) {
        LogUtils.e("setWXLogin=access_token=", access_token);
        LogUtils.e("setWXLogin=openid=", openid);
        RequestParams requestParams = RequestParams.get();
        requestParams.put("access_token", access_token);
        requestParams.put("openid", openid);
        switch (type) {
            case 1:
                Https.get().post(Urls.SET_WX_BINDING, requestParams, listener);
                break;
            case 2:
                Https.get().post(Urls.SET_QQ_BINDING, requestParams, listener);
                break;
        }
    }

    //手机号绑定
    public static void getBindingNumber(String prefix, String phone, String code, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("prefix", prefix);
        requestParams.put("phone", phone);
        requestParams.put("code", code);
        Https.get().post(Urls.GET_BINDING_NUMBER, requestParams, listener);
    }

    //校验手机号
    public static void getCheckCode(String code, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("code", code);
        Https.get().post(Urls.GET_CHACK_CODE, requestParams, listener);
    }

    //微信解绑
    public static void getWeiXinUnBind(int type, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        switch (type) {
            case 1:
                Https.get().post(Urls.GET_WINXIN_BINDING, requestParams, listener);
                break;
            case 2:
                Https.get().post(Urls.GET_QQ_BINDING, requestParams, listener);
                break;
        }
    }


    //获取主播信息
    public static void getHostInfo(String roomId, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("roomId", roomId);
        Https.get().post(Urls.GET_HOST_INFO, requestParams, listener, LiveHostClost.class);
    }

    //用户是否跳过
    public static void getSkinBind(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_SKIN_BAIND, requestParams, listener);
    }

    //私聊里关注和拉黑的状态
    public static void getAttandBlock(String tagId, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("tagId", tagId);
        Https.get().post(Urls.GET_ATTAND_BLOCK, requestParams, listener);
    }

    //私聊拉黑
    public static void getUpdateBlock(int type, String c_b_uid, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", type);
        requestParams.put("c_b_uid", c_b_uid);
        Https.get().post(Urls.UPDATE_USER_BLACKLIST, requestParams, listener);
    }

    //直播间列表
    public static void getLiveRoomList(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_LIVE_ROOM_LIST, requestParams, listener, LiveRoomBeanList.class);
    }

    //粉丝贡献榜
    public static void getFanContributionList(String anchor_userId, int type, int currentPage, String currentSize, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("anchor_userId", anchor_userId);
        requestParams.put("type", type);
        requestParams.put("currentPage", currentPage);
        requestParams.put("currentSize", currentSize);
        Https.get().post(Urls.GET_FAN_CONTRIBUTION_LIST, requestParams, listener, FanContributionList.class);
    }

    public static void getMoney(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_MONEY, requestParams, listener, MyMoneyBean.class);
    }

    public static void getRecordsList(int page, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", page);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_PREPAID_RECORDS_LIST, requestParams, listener, MyRecordsListBean.class);
    }

    public static void getDiamond(int pay_type, String pay_amt, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("pay_type", pay_type);
        requestParams.put("pay_environment", 0);
        requestParams.put("pay_amt", pay_amt);
        Https.get().post(Urls.GET_DIAMOND, requestParams, listener, MyDiamondBean.class);
    }

    public static void getShareBinding(String shareId, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("shareId", shareId);
        Https.get().post(Urls.SHARE_BINDING, requestParams, listener);
    }

    public static void getMyContribution(int type, String targetId, int currentPage, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", type);
        requestParams.put("targetId", targetId);
        requestParams.put("currentPage", currentPage);
        Https.get().post(Urls.GET_FAN_CONTRIBUTION_LIST, requestParams, listener, FanContributionList.class);
    }

    //修改开播封面
    public static void getUpdateCover(String data, int type, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type", type);
        requestParams.put("data", data);
        Https.get().post(Urls.GET_UPDATE_COVER_INFO, requestParams, listener);
    }

    //获取支付信息
    public static void getAppPayInfo(int pay_type, String pay_amt, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("pay_type", pay_type);
        requestParams.put("pay_amt", pay_amt);
        Https.get().post(Urls.GET_APP_PAY_INFO, requestParams, listener, AppPayInfo.class);
    }

    //获取PK自己开关状态
    public static void getPKStatus(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_PK_STATUS, requestParams, listener);
    }

    //更新PK自己开关状态
    public static void updatePKStatus(int random, int friend, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("random", random);
        requestParams.put("friend", friend);
        Https.get().post(Urls.UPDATE_PK_STATUS, requestParams, listener);
    }

    //获取PK好友列表
    public static void getPKFriendList(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_PK_FRIEDN_LIST, requestParams, listener, LiveRoomWheatBaseInfo.class);
    }

    //获取PK记录
    public static void getPKRecordList(int currentPage, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("currentPage", currentPage);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_PK_RECORD, requestParams, listener, PKRecordBaseInfo.class);
    }


    public static void getPKValue(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.GET_PK_RECORD_VALUE, requestParams, listener, PKRecordValueBaseInfo.class);
    }

    public static void getGameMessageList(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.getGameMessageList, requestParams, listener, GameListBean.class);
    }

    public static void getEggMessage(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.getEggList, requestParams, listener, EggBean.class);
    }

    public static void eggInvoke(ResponseListener listener,int type,int count) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("type",type);
        requestParams.put("count",count);
        Https.get().post(Urls.eggInvoke, requestParams, listener, GameResultBean.class);
    }

    public static void esport(ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        Https.get().post(Urls.eSport, requestParams, listener, EsportBean.class);
    }


    public static void GetPlayUserGameList(ResponseListener listener,String userId) {
        RequestParams requestParams = RequestParams.get();
        requestParams.put("userId",userId);
        Https.get().post(Urls.GetPlayUserGameList, requestParams, listener, UserGameListBean.class);
    }

    public static void getPKContribution(int currentPage, String aUserId, String pkId, String roomId, ResponseListener listener) {
        RequestParams requestParams = RequestParams.get();
        LogUtils.e("getPKContribution=", aUserId + "==" + pkId + "====" + roomId);
        requestParams.put("aUserId", aUserId);
        requestParams.put("pkId", pkId);
        requestParams.put("roomId", roomId);
        requestParams.put("currentPage", currentPage);
        requestParams.put("currentSize", Urls.CURRENTCOUNT);
        Https.get().post(Urls.GET_PK_CONTRIBUTION, requestParams, listener, PKContributionListBaseInfo.class);
    }
}
