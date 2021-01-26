package com.jk.jkproject.net.im.cores;

public class IMEventType {
    public static final int USERCHATRECVRES = 100;// 收到消息
    public static final int USERCHATRES = 103;// 聊天结果（发送给自己）


    public static final int ACTION_NET_WORK_CHANGE = 9; //网络变化
    public static final int ACTION_DOWNLOAD_MSG_MEDIA = 11;
    public static final int ACTION_START_IM_SERVICE_END = 15;//启动IMservice结束
    public static final int ACTION_UPDATE_SNAP_MESSAGE_OPEN = 16;
    public static final int ACTION_UPDATE_HEATTIME = 17; //心跳包
    public static final int ACTION_LOGIN_SUCCESS = 18; //登录成功

    public static final int ACTION_UPDATE_TEAM_TYPE = 19; //更新用户加入战队状态
    public static final int ACTION_UPDATE_STATUS_TYPE = 20; //更新用户实名认证状态
    public static final int ACTION_ENTER_LIVE_ROOM = 21; //进入直播间回调状态
    public static final int ACTION_ENTER_LIVE_ROOM_COUNT = 22; //进入直播间人数
    public static final int ACTION_LIVE_ROOM_MESSAGE = 23; //发送消息回调
    public static final int ACTION_LIVE_ROOM_MESSAGE_PUSH = 24; //推送消息回调
    public static final int ACTION_LIVE_ROOM_GIFT_PUSH = 25; //推送礼物回调
    public static final int ACTION_ENTER_LIVE_ROOM_LEAVE = 26; //离开直播间
    public static final int ACTION_LIVE_ROOM_COLSE_PUSH = 27; //主播离开房间推送
    public static final int ACTION_LIVE_ROOM_COLSE = 28; //主播离开房间回调
    public static final int ACTION_CHAT_MSG = 29; //私信回调
    public static final int ACTION_CHAT_MSG_PUSH = 30; //私信推送
    public static final int ACTION_SYSTEM_NOTI_MSG_PUSH = 31; //系统消息推送
    public static final int ACTION_WARNING_NOTI_PUSH = 32; //主播禁播等通知
    public static final int ACTION_LOGIN_OUT = 33; //账号登出
    public static final int ACTION_ANCHOR_HOT_RANKING = 34; //推送房间日榜排名
    public static final int ACTION_UPDATE_COVER = 35; //推送主播开播封面
    public static final int ACTION_LINK_USER_CALL = 36; //用户端发起连麦响应
    public static final int ACTION_LINK_HOST_RES_CALL = 37; //主播接收连麦操作
    public static final int ACTION_LINK_HOST_USER_CALL_PUSH = 38; //主播连麦用户推送
    public static final int ACTION_LINK_CANCEL_CALL_PUSH = 39; //用户发起取消连麦
    public static final int ACTION_LINK_TIMEOUT_CALL_PUSH = 40; //连麦超时
    public static final int ACTION_LINK_ANSWERL = 41; //主播同意和拒绝连麦回调
    public static final int ACTION_LINK_WHEAT_STREAM_PUSH = 42; //连麦用户端推流
    public static final int ACTION_LINK_WHEAT_HANGUP_PUSH = 43; //连麦用户端推流
    public static final int ACTION_LINK_WHEAT_HANGUP_PUSH_2 = 44; //连麦用户端推流
    public static final int ACTION_LINK_Friend_PK_INVITATION = 45; //好友PK邀请回调
    public static final int ACTION_LINK_FRIEND_PK_RECEIVE_PUSH = 46; //好友PK接收方推送
    public static final int ACTION_LINK_FRIEND_PK_TIME_OUT_PUSH = 47; //好友PK邀请超时推送
    public static final int ACTION_LINK_FRIEND_PK_STREAMPK_PUSH = 48; //将接收方的房间合并好的流推送给当前这个直播间的用户
    public static final int ACTION_LINK_FRIEND_PK_CONSENTPK_PUSH = 49; //推送给发起方，让他去合流
    public static final int ACTION_LINK_FRIEND_PK_ACCEPTRANDOMPK_PUSH = 50; //推送给匹配到的主播
    public static final int ACTION_LINK_FRIEND_PK_SENDRANDOMPK_PUSH = 51; //推给发起方的主播
    public static final int ACTION_LINK_FRIEND_PK_PKREJECT_PUSH = 52; //好友pk响应拒绝
    public static final int ACTION_LINK_FRIEND_PK_ADDMERGESTREAM = 53; //发起方得到对方pk同意后上传合流地址
    public static final int ACTION_LINK_RANDOM_PK_INVITATION = 54; //随机PK邀请回调
    public static final int ACTION_LINK_PK_PKHANGUP = 55; //主播强制挂断回调
    public static final int ACTION_LINK_PK_PKCLOSE_PUSH = 56; //主播强制挂断推送
    public static final int ACTION_LINK_PK_PKRESPONSE = 57; //PK接收方合流地址上传回调
    public static final int ACTION_LINK_PK_PKANCHORINFO_PUSH = 58; //PK方的信息给客户端
    public static final int ACTION_LINK_PK_PKRESULT_PUSH = 59; //PK胜利推送
    public static final int ACTION_LINK_PK_ACCEPTRECURPK_PUSH = 60; //再来一局推送给对方的监听
    public static final int ACTION_LINK_PK_ACCEPTRECURPK = 61; //再来一局回调
    public static final int ACTION_LINK_PK_RECURPKREJECT_PUSH = 62; //再来一局pk响应拒绝
    public static final int ACTION_LINK_PK_PKRANDOMTIMEOUT_PUSH = 63; //随机PK超时推送
    public static final int ACTION_LINK_PK_PKRECURTIMEOUT_PUSH = 64; //再来一局pk超时推送
    public static final int ACTION_LINK_PK_BATTLEFORCE_PUSH = 65; //金币推送
    public static final int ACTION_LINK_GAME_RECONNECTIONHANDLER = 66; //金币游戏回调
    public static final int ACTION_LINK_GAME_GOLD_WAIT = 67; //金币游戏阶段时间推送
    public static final int ACTION_LINK_GAME_GOLD_BET = 68; //金币游戏下注回调
    public static final int ACTION_LINK_GAME_GOLD_TOTAL_COUNT = 69; //金币游戏总下注数量
    public static final int ACTION_LINK_GAME_GOLD_RESULTS_PUSH = 70; //金币游戏结果推送
    public static final int ACTION_LINK_PK_PKRESIDUETIME = 71; //拉取时间回调
    public static final int ACTION_LINK_GAMELOGHANDLER = 72; //金币游戏记录回调
    public static final int ACTION_LINK_WHEAT_CANCEL_HANDLER = 73; //发起方取消连麦回调
    public static final int ACTION_LINK_RANDOM_PK_CANCEL = 74; //取消随机PK回调
    public static final int ACTION_LINK_ANCHORROOMMESSAGE_PUSH = 75; //直播间主播经验值推送
    public static final int EGG = 76; //直播间主播经验值推送
    public static final int ACTION_IS_CLEAR_DATA = 77; //判断下线后台清理数据


}