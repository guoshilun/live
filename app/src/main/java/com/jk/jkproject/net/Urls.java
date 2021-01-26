package com.jk.jkproject.net;

import com.jk.jkproject.BuildConfig;
import com.jk.jkproject.utils.SPUtils;

/**
 *
 */
public class Urls {


    //长连接 地址和端口
    public static String IM_SERVER_IP = "im_server_ip";
    public static String IM_SERVER_port = "im_server_port";
    public static String IM_SERVER_TOKEN = "im_server_token";
    public static String QINIU_URL = "http://q9y5lc39o.bkt.clouddn.com/";
    public static String PACKAGE_NAME = "com.jk.jkproject";

    public static String Ali_Path = "https://cjfeed.oss-cn-hangzhou.aliyuncs.com/";
    public static String SHARE_PATH = "http://live.lingdangji.com/live/share/client.html?roomId=";
    public static String SHARE_ACC_PATH = "http://live.lingdangji.com/live/download.html?shareId=";
            public static String WEB_PATH = "http://live.lingdangji.com/live";
//    public static String WEB_PATH = "http://192.168.0.99:8081/Live";

    /**
     * api 根路径
     */
    public static String API_BASEURL;
    /**
     * APP配置
     */
    public static String APP_CONFIG;
    public static String REG;

    public static String getUrl() {
        if (BuildConfig.DEBUG){
            return "http://39.100.228.217:7777/"; //内网
        }else{
            return "http://live.lingdangji.com:7777/";
        }
//        return "http://47.92.111.211/"; //外网
//         //外网

//        return "http://192.168.0.88:9020/";
//        http://47.92.111.211/test/test.html
    }


    public static String H5_BASE_URL = SPUtils.getH5PageUrl().isEmpty() ? "http://192.168.0.99:8081" : SPUtils.getH5PageUrl();

    //h5页面
    public static String HOST_PAGE; //主播榜单
    public static String USER_PAGE; //用户榜单
    public static String USER_LEVEL; //用户等级
    public static String HOST_LEVEL; //主播等级
    public static String MY_EARNINGS; //我的收益
    public static String RECHARGE_PAGE; //充值页面
    public static String FAN_CONTRIBUTION; //粉丝贡献榜
    public static String MANAGEMENT_REGULATIONS; //管理条例
    public static String USER_SERVICE_AGREEMENT; //用户服务协议
    public static String HOST_AGREEMENT; //直播协议
    public static String USER_DIAMOND; //用户充值协议
    public static String USER_PRIVACY; //JK直播隐私政策
    public static String PK_RULES; //PK规则
    public static String GAME_GOLD_RULES; //金币游戏规则


    /**
     * 单张表情前缀
     */
    public static String EMOTICON_GIF_BASE_URL;

    public static String CURRENTCOUNT = "12";
    //首页TAB分类
    public static String HOME_TAB_CLASSIFY;
    //首页列表数据
    public static String HOME_TAB_LIST;
    public static String GET_PHONE_PREFIX; //获取手机区号
    public static String USER_LOGIN; //用户登录
    public static String VISITOR_LOGIN; //游客登录
    public static String CODE_LOGIN; //获取验证码
    public static String RESET_PASSWORD_LOGIN; //重置密码


    public static String GET_TEAM_CENTER; //战队搜索列表
    public static String GET_TEAM_DETAILS_INFO; //战队详情
    public static String GET_TEAM_APPLY_JOIN;//申请战队
    public static String GET_TEAM_APPLY_MESSAGE; //获取战队的基本信息
    public static String GET_TEAM_CENCAL_MESSAGE; //撤销申请战队
    public static String GET_TEAM_NEW_TEAM;//创建战队
    public static String GET_TEAM_INFO;//战队基础信息
    public static String GET_TEAM_MEMBERS;//战队成员列表
    public static String GET_TEAM_RETURN;//退出战队
    public static String GET_TEAM_DISSOLUTION;//解散战队
    public static String GET_TEAM_EDITOR_INFO;//更改战队信息
    public static String GET_TEAM_USER_LIST;//战队成员列表
    public static String GET_TEAM_CONTRACT_MANAGEMENT;//战队签约管理列表
    public static String GET_TEAM_TERMINATION; //战队签约/解约
    public static String GET_TEAM_APPOINTMENT; //战队队长任命 副队长/成员
    public static String GET_TEAM_JOINLIST; //签约待审核列表
    public static String GET_TEAM_APPROVAL_TO_JOIN; //审批用户
    public static String GET_TEAM_DATASTATISTICS; //数据统计

    //直播间
    public static String GET_LIVE_ROOM_CONTRIBUTION; //获取直播间贡献榜
    public static String GET_LIVE_ROOM_DISTINGUISHED;//获取贵宾
    public static String GET_LIVE_ROOM_BASE_INFO;//获取直播间信息
    public static String GET_LIVE_ROOM_USER_INFO; //直播间获取用户信息
    public static String GET_LIVE_ROOM_BANNED_TIME;//直播间禁言时间
    public static String GET_LIVE_ROOM_ADMIN; //直播间添加房管


    //用户
    public static String GET_USER_INFO;//获取用户基本信息
    public static String GET_TAGID_INFO;//获取对方的基本信息
    public static String GET_FOCUS_LIST; //我的关注列表
    public static String GET_BLACK_LIST; //我的黑名单列表
    public static String GET_UPDATE_BLACK;//加入黑名单操作
    public static String GET_FEED_BACK;//意见反馈
    public static String GET_QINIU_TOKEN; //获取七牛token
    public static String GET_IDENTITY_AUTH;//实名认证
    public static String GET_OPEN_PLAY;//开播
    public static String GET_ANCHORASK;
    public static String GET_UPDATE_FOLLOW; //关注


    //----------------------------------------------------------------------------------------
    public static String GET_HOME_FOLLOW_LIST; //首页关注列表
    public static String GET_HOME_NEAR_LIST; //首页附近列表
    public static String GET_HOME_HOT_LIST; //首页热门列表
    public static String GET_HOME_BANNER_LIST; //首页轮播图列表
    public static String GET_HOME_FOUND_LIST; //首页发现列表
    public static String GET_HOME_REMANNDE_LIST; //首页推荐列表
    public static String GET_LIVE_FANS_FOLLOW_DATA;
    public static String GET_HOST_RANK_MY;
    public static String GET_USER_RANK_MY;
    public static String GET_HOST_RANK_LIST;
    public static String GET_LIVE_FANS_USER_LOCK_LIST;
    public static String GET_REPORT_USER;
    public static String GET_GIFT_LIST;
    public static String GET_ADMIN_LIST;
    public static String GET_BANNED_LIST;
    public static String GET_LIVE_SEND_GIFT;//赠送礼物
    public static String GET_LIVE_SEND_POCKET_GIFT;//赠送礼物
    public static String GET_LIVE_USER_INFO;
    public static String GET_LIVE_ROOM_CLOSE_PLAY;//主播下播
    public static String GET_LIVE_ROOM_SEARCH;//搜索
    public static String GET_CHAT_LIST;//聊天用户列表
    public static String GET_CHAT_MSG;//聊天历史消息
    public static String GET_EDITOR_USER_INFO;//编辑用户信息
    public static String GET_USER_FANS_LIST;//获取用户粉丝和关注列表
    public static String GET_SYSTEM_NOTI_LIST;//系统通知列表
    public static String GET_KEFU_INFO;//客服信息
    public static String GET_OLD_ROOM_INFO;//获取旧房间数据
    public static String GET_EXPLEVEL_INFO;//获取经验等级数据
    public static String GET_CLEARCOUNT_INFO; //清空未读消息
    public static String GET_REMOVECONVERSATION_INFO; //删除会话列表
    public static String GET_CLEARALLCOUNT_INFO; //清空所有未读消息
    public static String GET_AUTO_REPLY; //设置自动回复
    public static String GET_AUTO_REPLY_INFO; //获取自动回复
    public static String GET_ADMIN_LIST_INFO; //获取管理员列表
    public static String SET_MSG_NOTI; //设置消息通知
    public static String GET_MSG_NOTI; //获取消息通知
    public static String SET_WX_LOGIN;//微信登录
    public static String SET_QQ_LOGIN;//QQ登录
    public static String SET_WX_BINDING;//微信绑定
    public static String GET_WINXIN_BINDING; //微信解绑
    public static String GET_QQ_BINDING;//QQ解绑
    public static String SET_QQ_BINDING;//QQ绑定
    public static String GET_BINDING_NUMBER; //绑定手机号
    public static String GET_HOST_INFO; //获取主播信息
    public static String GET_SKIN_BAIND; //跳过绑定手机号
    public static String GET_ATTAND_BLOCK; //私聊拉黑和关注状态
    public static String UPDATE_USER_BLACKLIST; //私聊拉黑
    public static String GET_LIVE_ROOM_LIST; //直播间列表
    public static String GET_FAN_CONTRIBUTION_LIST; //粉丝贡献榜
    public static String GET_MONEY; //获取用户钱包
    public static String GET_PREPAID_RECORDS_LIST; //获取用户充值记录
    public static String GET_DIAMOND; //获取充值凭证
    public static String SHARE_BINDING; //分享绑定
    public static String GET_GETMYCONTRIBUTION; //获取本人粉丝贡献榜
    public static String GET_UPDATE_COVER_INFO; //修改开播封面
    public static String GET_APP_PAY_INFO; //获取支付信息
    public static String GET_PK_STATUS; //获取PK自己开关状态
    public static String UPDATE_PK_STATUS; //更新PK自己开关状态
    public static String GET_PK_FRIEDN_LIST; //获取PK好友列表
    public static String GET_PK_RECORD; //获取PK记录列表
    public static String GET_PK_CONTRIBUTION; //获取 直播间PK贡献榜
    public static String GET_PK_RECORD_VALUE; //获取PK战绩
    public static String GET_CHACK_CODE;//校验手机号
    public static String getGameMessageList;//校验手机号
    public static String getEggList;//校验手机号
    public static String eggInvoke;//校验手机号
    public static String backpack;//校验手机号
    public static String closeGame;//校验手机号
    public static String eSport;//校验手机号
    public static String eggExplain;//校验手机号
    public static String GetPlayUserGameList;//校验手机号

    /**
     * 升级检测
     */
    public static String VERSION_CHECK;

    static {
        API_BASEURL = getUrl();
        /**
         * APP配置
         */
        HOME_TAB_CLASSIFY = API_BASEURL + "Quokka/index/GetColumnList";

        REG = API_BASEURL + "auth/reg";
        HOME_TAB_LIST = API_BASEURL + "Quokka/index/GetBaseData";

        VERSION_CHECK = API_BASEURL + "QuokkaN/My/GetVersionsList";

        //账号
        GET_PHONE_PREFIX = API_BASEURL + "TAO/Gateway/GetPhonePrefix";
        USER_LOGIN = API_BASEURL + "TAO/Gateway/UserLogin";
        VISITOR_LOGIN = API_BASEURL + "zbj/Gateway/VisitorsLogin";
        CODE_LOGIN = API_BASEURL + "TAO/Gatewy/GetVerificationCode";
        RESET_PASSWORD_LOGIN = API_BASEURL + "TAO/Gateway/ResetPassword";

        //战队
        GET_TEAM_CENTER = API_BASEURL + "Quokka/Team/GetTeamCenter";
        GET_TEAM_DETAILS_INFO = API_BASEURL + "Quokka/Team/GetTeamBaseMessage";
        GET_TEAM_APPLY_JOIN = API_BASEURL + "Quokka/Team/AddApplyToJoinTeam";
        GET_TEAM_APPLY_MESSAGE = API_BASEURL + "Quokka/Team/GetTeamApplyForMessage";
        GET_TEAM_CENCAL_MESSAGE = API_BASEURL + "Quokka/Team/UpdateTeamApply";
        GET_TEAM_NEW_TEAM = API_BASEURL + "Quokka/Team/AddApplyForCreationTeam";
        GET_TEAM_INFO = API_BASEURL + "Quokka/Team/GetTeamBaseMessage";
        GET_TEAM_MEMBERS = API_BASEURL + "Quokka/Team/GetTeamUserList";
        GET_TEAM_RETURN = API_BASEURL + "Quokka/Team/QuitTeam";
        GET_TEAM_DISSOLUTION = API_BASEURL + "Quokka/Team/UpdateQuitTeam";
        GET_TEAM_EDITOR_INFO = API_BASEURL + "Quokka/Team/UpdateTeamMessage";
        GET_TEAM_USER_LIST = API_BASEURL + "Quokka/Team/GetTeamUserList";
        GET_TEAM_CONTRACT_MANAGEMENT = API_BASEURL + "Quokka/Team/GetContractManagement";
        GET_TEAM_TERMINATION = API_BASEURL + "Quokka/Team/UpdateSignedAndContractTermination";
        GET_TEAM_APPOINTMENT = API_BASEURL + "Quokka/Team/UpdateTeamAppointed";
        GET_TEAM_JOINLIST = API_BASEURL + "Quokka/Team/GetTeamJoinList";
        GET_TEAM_APPROVAL_TO_JOIN = API_BASEURL + "Quokka/Team/UpdateApprovalToJoinTeam";
        GET_TEAM_DATASTATISTICS = API_BASEURL + "Quokka/Team/GetTeamDataStatistics";
        GET_LIVE_ROOM_CONTRIBUTION = API_BASEURL + "TAO/Gateway/GetContribution";
        GET_LIVE_ROOM_DISTINGUISHED = API_BASEURL + "TAO/Gateway/GitDistinguished";
        GET_USER_INFO = API_BASEURL + "TAO/Gateway/GetUserInfo";

        GET_FOCUS_LIST = API_BASEURL + "Quokka/user/GetMyFollowList";
        GET_BLACK_LIST = API_BASEURL + "Quokka/my/GetUserBlacklist";


        GET_UPDATE_BLACK = API_BASEURL + "QuokkaN/My/UpdateUserBlacklist";


        GET_FEED_BACK = API_BASEURL + "TAO/Gateway/OpinionFeedback";
        GET_QINIU_TOKEN = API_BASEURL + "TAO/Gateway/GetQiNiuToken";
        GET_IDENTITY_AUTH = API_BASEURL + "QuokkaN/My/AddAnchorAskFor";
        GET_OPEN_PLAY = API_BASEURL + "TAO/Gateway/OpenPlay";
        GET_ANCHORASK = API_BASEURL + "zbj/Gateway/AnchorAsk";
        GET_LIVE_ROOM_BASE_INFO = API_BASEURL + "zbj/room/GetRoomNo";
        GET_UPDATE_FOLLOW = API_BASEURL + "QuokkaN/User/UpdateFollow";
        GET_LIVE_ROOM_USER_INFO = API_BASEURL + "TAO/Gateway/GetRoomUserInfo";
        GET_LIVE_ROOM_BANNED_TIME = API_BASEURL + "Quokka/index/GetProhibitSpeakList";
        GET_LIVE_ROOM_ADMIN = API_BASEURL + "TAO/Gateway/AddRoomAdmin";
        GET_GETMYCONTRIBUTION = API_BASEURL + "QuokkaN/My/GetMyContribution";
        GET_APP_PAY_INFO = API_BASEURL + "zbj/live/AppPay";


        GET_HOME_FOLLOW_LIST = API_BASEURL + "QuokkaN/Home/GetHomeFocusData";
        GET_HOME_NEAR_LIST = API_BASEURL + "QuokkaN/Home/GetHomeNearbyData";
        GET_HOME_HOT_LIST = API_BASEURL + "QuokkaN/Home/GetHomeHotData";
        GET_HOME_BANNER_LIST = API_BASEURL + "QuokkaN/Home/GetHomeCarouselList";
        GET_HOME_FOUND_LIST = API_BASEURL + "QuokkaN/Discover/GetDiscoverData";
        GET_HOME_REMANNDE_LIST = API_BASEURL + "QuokkaN/Discover/GetDiscoverList";
        GET_LIVE_FANS_FOLLOW_DATA = API_BASEURL + "QuokkaN/User/GetFansCountAndFollowCount";
        GET_HOST_RANK_MY = API_BASEURL + "QuokkaN/live/GetLiveAnchorHot";
        GET_HOST_RANK_LIST = API_BASEURL + "QuokkaN/Common/GetAnchorHot";
        GET_LIVE_FANS_USER_LOCK_LIST = API_BASEURL + "TAO/Gateway/GetRoomUserList";
        GET_REPORT_USER = API_BASEURL + "TAO/Gateway/UserReport";
        GET_GIFT_LIST = API_BASEURL + "QuokkaN/Gift/GetGiftList";
        GET_ADMIN_LIST = API_BASEURL + "QuokkaN/My/GetUserBlacklist";
        GET_BANNED_LIST = API_BASEURL + "TAO/Gateway/GetRoomBannedList";
        GET_LIVE_SEND_GIFT = API_BASEURL + "QuokkaN/Gift/SendGift";
        GET_LIVE_SEND_POCKET_GIFT = API_BASEURL + "QuokkaN/Gift/SendKnapsack";
        GET_LIVE_ROOM_CLOSE_PLAY = API_BASEURL + "TAO/Gateway/GetPlayInfo";
        GET_LIVE_ROOM_SEARCH = API_BASEURL + "QuokkaN/Home/GetHomeSearch";
        GET_CHAT_LIST = API_BASEURL + "TAO/Gateway/GetConversation";
        GET_CHAT_MSG = API_BASEURL + "TAO/Gateway/GetMsgHistory";
        GET_EDITOR_USER_INFO = API_BASEURL + "TAO/Gateway/UpdateUserInfo";
        GET_USER_FANS_LIST = API_BASEURL + "QuokkaN/User/GetFansListOrFollowList";
        GET_SYSTEM_NOTI_LIST = API_BASEURL + "Quokka/My/GetSystemNotificationList";
        GET_KEFU_INFO = API_BASEURL + "QuokkaN/Common/GetAirlines";
        GET_OLD_ROOM_INFO = API_BASEURL + "TAO/Gateway/GetOldRoomInfo";
        GET_UPDATE_COVER_INFO = API_BASEURL + "TAO/Gateway/UpdateCover";
        GET_EXPLEVEL_INFO = API_BASEURL + "TAO/Gateway/GetRoomUserData";
        GET_CLEARCOUNT_INFO = API_BASEURL + "TAO/Gatewy/ClearCount";
        GET_REMOVECONVERSATION_INFO = API_BASEURL + "TAO/Gateway/RemoveConversation";
        GET_CLEARALLCOUNT_INFO = API_BASEURL + "TAO/Gatewy/ClearAllCount";
        GET_AUTO_REPLY = API_BASEURL + "TAO/Gateway/SettingsReply";
        GET_AUTO_REPLY_INFO = API_BASEURL + "TAO/Gateway/GetReply";
        GET_ADMIN_LIST_INFO = API_BASEURL + "TAO/Gateway/RoomAdminList";
        SET_MSG_NOTI = API_BASEURL + "TAO/Gateway/SettingMessageInform";
        GET_MSG_NOTI = API_BASEURL + "TAO/Gateway/MessageInform";
        SET_WX_LOGIN = API_BASEURL + "TAO/Gateway/WeChatLogin";
        SET_QQ_LOGIN = API_BASEURL + "TAO/Gateway/QQLogin";
        GET_BINDING_NUMBER = API_BASEURL + "TAO/Gatewy/BindingPhone";
        GET_WINXIN_BINDING = API_BASEURL + "TAO/Gateway/UNBinWX";
        SET_WX_BINDING = API_BASEURL + "TAO/Gateway/BindingWX";
        GET_QQ_BINDING = API_BASEURL + "TAO/Gateway/UNBinQQ";
        SET_QQ_BINDING = API_BASEURL + "TAO/Gateway/BindingQQ";
        GET_TAGID_INFO = API_BASEURL + "TAO/Gateway/GetUserR";
        GET_HOST_INFO = API_BASEURL + "TAO/Gateway/GetAnchorInfo";
        GET_SKIN_BAIND = API_BASEURL + "TAO/Gateway/SkipBinding";
        GET_ATTAND_BLOCK = API_BASEURL + "TAO/Gateway/GetAttAndBlock";
        UPDATE_USER_BLACKLIST = API_BASEURL + "QuokkaN/My/UpdateUserBlacklist";
        GET_LIVE_ROOM_LIST = API_BASEURL + "QuokkaN/Room/GetRoomList";
        GET_FAN_CONTRIBUTION_LIST = API_BASEURL + "QuokkaN/Common/GetContribution";
        GET_MONEY = API_BASEURL + "zbj/live/getMoney";
        GET_PREPAID_RECORDS_LIST = API_BASEURL + "zbj/live/GetOrderList";
        GET_DIAMOND = API_BASEURL + "zbj/Gateway/PayApi";
        SHARE_BINDING = API_BASEURL + "TAO/Gateway/UserBinding";
        GET_USER_RANK_MY = API_BASEURL + "QuokkaN/live/GetLiveUserContribution";
        GET_PK_STATUS = API_BASEURL + "TAO/Gateway/GetPKStatus";
        UPDATE_PK_STATUS = API_BASEURL + "TAO/Gateway/UpdatePKStatus";
        GET_PK_FRIEDN_LIST = API_BASEURL + "TAO/Gateway/GetPKFriend";
        GET_PK_RECORD = API_BASEURL + "TAO/Gateway/GetPKRecord";
        GET_PK_CONTRIBUTION = API_BASEURL + "Quokka/PK/GetPKUserMessage";
        GET_PK_RECORD_VALUE = API_BASEURL + "TAO/Gateway/GetPKValue";
        GET_CHACK_CODE = API_BASEURL + "TAO/Gatewy/CheckPhone";
        HOST_PAGE = WEB_PATH + "/#/pages/anchor/anchor";
        USER_PAGE = WEB_PATH + "/#/pages/user/user";
        HOST_LEVEL = WEB_PATH + "/#/pages/anchorg/anchorg";
        USER_LEVEL = WEB_PATH + "/#/pages/userg/userg";
        MY_EARNINGS = WEB_PATH + "/#/pages/benefit/benefit";
        RECHARGE_PAGE = WEB_PATH + "/#/pages/top/top";
        FAN_CONTRIBUTION = WEB_PATH + "/#/pages/contribution/contribution";
        MANAGEMENT_REGULATIONS = WEB_PATH + "/#/pages/adminp/adminp";
        USER_SERVICE_AGREEMENT = WEB_PATH + "/#/pages/userp/userp";
        HOST_AGREEMENT = WEB_PATH + "/#/pages/LiveTerms/LiveTerms";
        USER_DIAMOND = WEB_PATH + "/#/pages/topp/topp";
        USER_PRIVACY = WEB_PATH + "/#/pages/livePrivacy/livePrivacy";
        PK_RULES = WEB_PATH + "/#/pages/pkRule/pkRule";
        GAME_GOLD_RULES = WEB_PATH + "/#/pages/gameInstructions/gameInstructions";
        /* ----- */
//        eggExplain = WEB_PATH + "/#/pages/egExplain/egExplain";
        eggExplain = WEB_PATH + "/#/pages/gameInstructions2/gameInstructions2";
        getGameMessageList = API_BASEURL + "Quokka/Game/GetGameMessageList";
        getEggList = API_BASEURL + "Quokka/Game/GetSmashGoldenEggsAwardList";
        eggInvoke = API_BASEURL + "Quokka/Game/ExpenditureSlyderAdventuresCount";
        backpack = API_BASEURL + "Quokka/User/GetKnapsackMessage";
        closeGame = API_BASEURL + "Quokka/Game/CloseSmashGoldenEggsPush";
        eSport = API_BASEURL + "eSport/getSportUrl";
        GetPlayUserGameList = API_BASEURL + "Quokka/play/GetPlayUserGameList";


    }

    public static void setBaseUrl(String url) {
        API_BASEURL = url;
        /**
         * APP配置
         */
        HOME_TAB_CLASSIFY = API_BASEURL + "Quokka/index/GetColumnList";

        REG = API_BASEURL + "auth/reg";
        HOME_TAB_LIST = API_BASEURL + "Quokka/index/GetBaseData";
        VERSION_CHECK = API_BASEURL + "QuokkaN/My/GetVersionsList";
        GET_PHONE_PREFIX = API_BASEURL + "TAO/Gateway/GetPhonePrefix";
        USER_LOGIN = API_BASEURL + "TAO/Gateway/UserLogin";
        VISITOR_LOGIN = API_BASEURL + "zbj/Gateway/VisitorsLogin";
        CODE_LOGIN = API_BASEURL + "TAO/Gatewy/GetVerificationCode";
        RESET_PASSWORD_LOGIN = API_BASEURL + "TAO/Gateway/ResetPassword";

        GET_TEAM_CENTER = API_BASEURL + "Quokka/Team/GetTeamCenter";
        GET_TEAM_DETAILS_INFO = API_BASEURL + "Quokka/Team/GetTeamBaseMessage";
        GET_TEAM_APPLY_JOIN = API_BASEURL + "Quokka/Team/AddApplyToJoinTeam";
        GET_TEAM_APPLY_MESSAGE = API_BASEURL + "Quokka/Team/GetTeamApplyForMessage";
        GET_TEAM_CENCAL_MESSAGE = API_BASEURL + "Quokka/Team/UpdateTeamApply";
        GET_TEAM_NEW_TEAM = API_BASEURL + "Quokka/Team/AddApplyForCreationTeam";
        GET_TEAM_INFO = API_BASEURL + "Quokka/Team/GetTeamBaseMessage";
        GET_TEAM_MEMBERS = API_BASEURL + "Quokka/Team/GetTeamUserList";
        GET_TEAM_RETURN = API_BASEURL + "Quokka/Team/QuitTeam";
        GET_TEAM_DISSOLUTION = API_BASEURL + "Quokka/Team/UpdateQuitTeam";
        GET_TEAM_EDITOR_INFO = API_BASEURL + "Quokka/Team/UpdateTeamMessage";
        GET_TEAM_USER_LIST = API_BASEURL + "Quokka/Team/GetTeamUserList";
        GET_TEAM_CONTRACT_MANAGEMENT = API_BASEURL + "Quokka/Team/GetContractManagement";
        GET_TEAM_TERMINATION = API_BASEURL + "Quokka/Team/UpdateSignedAndContractTermination";
        GET_TEAM_APPOINTMENT = API_BASEURL + "Quokka/Team/UpdateTeamAppointed";
        GET_TEAM_JOINLIST = API_BASEURL + "Quokka/Team/GetTeamJoinList";
        GET_TEAM_APPROVAL_TO_JOIN = API_BASEURL + "Quokka/Team/UpdateApprovalToJoinTeam";
        GET_TEAM_DATASTATISTICS = API_BASEURL + "Quokka/Team/GetTeamDataStatistics";
        GET_LIVE_ROOM_CONTRIBUTION = API_BASEURL + "TAO/Gateway/GetContribution";
        GET_LIVE_ROOM_DISTINGUISHED = API_BASEURL + "TAO/Gateway/GitDistinguished";

        GET_USER_INFO = API_BASEURL + "TAO/Gateway/GetUserInfo";
        GET_FOCUS_LIST = API_BASEURL + "Quokka/user/GetMyFollowList";
        GET_BLACK_LIST = API_BASEURL + "Quokka/my/GetUserBlacklist";
        GET_UPDATE_BLACK = API_BASEURL + "QuokkaN/My/UpdateUserBlacklist";
        GET_FEED_BACK = API_BASEURL + "TAO/Gateway/OpinionFeedback";
        GET_QINIU_TOKEN = API_BASEURL + "TAO/Gateway/GetQiNiuToken";
        GET_IDENTITY_AUTH = API_BASEURL + "QuokkaN/My/AddAnchorAskFor";
        GET_OPEN_PLAY = API_BASEURL + "TAO/Gateway/OpenPlay";
        GET_ANCHORASK = API_BASEURL + "zbj/Gateway/AnchorAsk";
        GET_LIVE_ROOM_BASE_INFO = API_BASEURL + "zbj/room/GetRoomNo";
        GET_UPDATE_FOLLOW = API_BASEURL + "QuokkaN/User/UpdateFollow";
        GET_LIVE_ROOM_USER_INFO = API_BASEURL + "TAO/Gateway/GetRoomUserInfo";
        GET_LIVE_ROOM_ADMIN = API_BASEURL + "TAO/Gateway/AddRoomAdmin";
        GET_GETMYCONTRIBUTION = API_BASEURL + "QuokkaN/My/GetMyContribution";


        GET_HOME_FOLLOW_LIST = API_BASEURL + "QuokkaN/Home/GetHomeFocusData";
        GET_HOME_NEAR_LIST = API_BASEURL + "QuokkaN/Home/GetHomeNearbyData";
        GET_HOME_HOT_LIST = API_BASEURL + "QuokkaN/Home/GetHomeHotData";
        GET_HOME_BANNER_LIST = API_BASEURL + "QuokkaN/Home/GetHomeCarouselList";
        GET_HOME_FOUND_LIST = API_BASEURL + "QuokkaN/Discover/GetDiscoverData";
        GET_HOME_REMANNDE_LIST = API_BASEURL + "QuokkaN/Discover/GetDiscoverList";
        GET_LIVE_FANS_FOLLOW_DATA = API_BASEURL + "QuokkaN/User/GetFansCountAndFollowCount";
        GET_HOST_RANK_MY = API_BASEURL + "QuokkaN/live/GetLiveAnchorHot";
        GET_USER_RANK_MY = API_BASEURL + "QuokkaN/live/GetLiveUserContribution";
        GET_HOST_RANK_LIST = API_BASEURL + "QuokkaN/Common/GetAnchorHot";
        GET_LIVE_FANS_USER_LOCK_LIST = API_BASEURL + "TAO/Gateway/GetRoomUserList";
        GET_REPORT_USER = API_BASEURL + "TAO/Gateway/UserReport";
        GET_GIFT_LIST = API_BASEURL + "QuokkaN/Gift/GetGiftList";
        GET_ADMIN_LIST = API_BASEURL + "QuokkaN/My/GetUserBlacklist";
        GET_BANNED_LIST = API_BASEURL + "TAO/Gateway/GetRoomBannedList";
        GET_LIVE_SEND_GIFT = API_BASEURL + "QuokkaN/Gift/SendGift";
        GET_LIVE_ROOM_CLOSE_PLAY = API_BASEURL + "TAO/Gateway/GetPlayInfo";
        GET_LIVE_ROOM_SEARCH = API_BASEURL + "QuokkaN/Home/GetHomeSearch";
        GET_CHAT_LIST = API_BASEURL + "TAO/Gateway/GetConversation";
        GET_CHAT_MSG = API_BASEURL + "TAO/Gateway/GetMsgHistory";
        GET_EDITOR_USER_INFO = API_BASEURL + "TAO/Gateway/UpdateUserInfo";
        GET_USER_FANS_LIST = API_BASEURL + "QuokkaN/User/GetFansListOrFollowList";
        GET_SYSTEM_NOTI_LIST = API_BASEURL + "Quokka/My/GetSystemNotificationList";
        GET_KEFU_INFO = API_BASEURL + "QuokkaN/Common/GetAirlines";
        GET_OLD_ROOM_INFO = API_BASEURL + "TAO/Gateway/GetOldRoomInfo";
        GET_UPDATE_COVER_INFO = API_BASEURL + "TAO/Gateway/UpdateCover";
        GET_EXPLEVEL_INFO = API_BASEURL + "TAO/Gateway/GetRoomUserData";
        GET_CLEARCOUNT_INFO = API_BASEURL + "TAO/Gatewy/ClearCount";
        GET_REMOVECONVERSATION_INFO = API_BASEURL + "TAO/Gateway/RemoveConversation";
        GET_CLEARALLCOUNT_INFO = API_BASEURL + "TAO/Gatewy/ClearAllCount";
        GET_AUTO_REPLY = API_BASEURL + "TAO/Gateway/SettingsReply";
        GET_AUTO_REPLY_INFO = API_BASEURL + "TAO/Gateway/GetReply";
        GET_ADMIN_LIST_INFO = API_BASEURL + "TAO/Gateway/RoomAdminList";
        SET_MSG_NOTI = API_BASEURL + "TAO/Gateway/SettingMessageInform";
        GET_MSG_NOTI = API_BASEURL + "TAO/Gateway/MessageInform";
        SET_WX_LOGIN = API_BASEURL + "TAO/Gateway/WeChatLogin";
        SET_QQ_LOGIN = API_BASEURL + "TAO/Gateway/QQLogin";
        GET_BINDING_NUMBER = API_BASEURL + "TAO/Gatewy/BindingPhone";
        GET_WINXIN_BINDING = API_BASEURL + "TAO/Gateway/UNBinWX";
        SET_WX_BINDING = API_BASEURL + "TAO/Gateway/BindingWX";
        GET_QQ_BINDING = API_BASEURL + "TAO/Gateway/UNBinQQ";
        SET_QQ_BINDING = API_BASEURL + "TAO/Gateway/BindingQQ";
        GET_TAGID_INFO = API_BASEURL + "TAO/Gateway/GetUserR";
        GET_HOST_INFO = API_BASEURL + "TAO/Gateway/GetAnchorInfo";
        GET_SKIN_BAIND = API_BASEURL + "TAO/Gateway/SkipBinding";
        GET_ATTAND_BLOCK = API_BASEURL + "TAO/Gateway/GetAttAndBlock";
        UPDATE_USER_BLACKLIST = API_BASEURL + "QuokkaN/My/UpdateUserBlacklist";
        GET_LIVE_ROOM_LIST = API_BASEURL + "QuokkaN/Room/GetRoomList";
        GET_FAN_CONTRIBUTION_LIST = API_BASEURL + "QuokkaN/Common/GetContribution";
        GET_MONEY = API_BASEURL + "zbj/live/getMoney";
        GET_PREPAID_RECORDS_LIST = API_BASEURL + "zbj/live/GetOrderList";
        GET_DIAMOND = API_BASEURL + "zbj/Gateway/PayApi";
        SHARE_BINDING = API_BASEURL + "TAO/Gateway/UserBinding";
        GET_PK_STATUS = API_BASEURL + "TAO/Gateway/GetPKStatus";
        UPDATE_PK_STATUS = API_BASEURL + "TAO/Gateway/UpdatePKStatus";
        GET_PK_FRIEDN_LIST = API_BASEURL + "TAO/Gateway/GetPKFriend";
        GET_PK_RECORD = API_BASEURL + "TAO/Gateway/GetPKRecord";
        GET_PK_CONTRIBUTION = API_BASEURL + "Quokka/PK/GetPKUserMessage";
        GET_PK_RECORD_VALUE = API_BASEURL + "TAO/Gateway/GetPKValue";
        GET_CHACK_CODE = API_BASEURL + "TAO/Gatewy/CheckPhone";
        GET_APP_PAY_INFO = API_BASEURL + "zbj/live/AppPay";

        HOST_PAGE = WEB_PATH + "/#/pages/anchor/anchor";
        USER_PAGE = WEB_PATH + "/#/pages/user/user";
        USER_LEVEL = WEB_PATH + "/#/pages/anchorg/anchorg";
        HOST_LEVEL = WEB_PATH + "/#/pages/userg/userg";
        MY_EARNINGS = WEB_PATH + "/#/pages/benefit/benefit";
        RECHARGE_PAGE = WEB_PATH + "/#/pages/top/top";
        FAN_CONTRIBUTION = WEB_PATH + "/#/pages/contribution/contribution";
        HOST_AGREEMENT = WEB_PATH + "/#/pages/LiveTerms/LiveTerms";
        USER_DIAMOND = WEB_PATH + "/#/pages/topp/topp";
        USER_PRIVACY = WEB_PATH + "/#/pages/livePrivacy/livePrivacy";
        PK_RULES = WEB_PATH + "/#/pages/pkRule/pkRule";
        GAME_GOLD_RULES = WEB_PATH + "/#/pages/gameInstructions/gameInstructions";

    }
}
