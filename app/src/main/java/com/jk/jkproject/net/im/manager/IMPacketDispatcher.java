package com.jk.jkproject.net.im.manager;

import android.app.Application;
import android.os.Bundle;

import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.LogUtils;
import com.fansan.common.BaseApp;
import com.fansan.common.OrderPopup;
import com.fansan.common.pojo.OrderPushBean;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.net.im.info.PushTopWinOrder;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.net.im.RequestPacket;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

public class IMPacketDispatcher {
    private static final String TAG = "IMPacketDispatcher";

    public static void dispatch(RequestPacket packet) {
        if (packet == null) {
            return;
        }
        Bundle bdTime = new Bundle();
        bdTime.putInt("method", IMEventType.ACTION_UPDATE_HEATTIME);
        MessageNotifyCenter.getInstance().doNotify(bdTime);
        switch (packet.getMethod()) {
            default:
                break;
            case "header_method"://
                LogUtils.e("心跳包=收到");
                IMLoginManager.instance().resetHeartbeart();
                break;
            case "push.userRealNameState":
                IMLoginManager.instance().pullCertification(packet.getContentBytes());
                break;
            case "CancelAdminHandler.C":
                ToastUtils.showShortToast("取消管理员成功");
                break;
            case "SettingAdminHandler.C":
                ToastUtils.showShortToast("设置管理员成功");
                break;
            case "CancelBanned.C":
                ToastUtils.showShortToast("取消禁言成功");
                break;
            case "BannedUser.C":
                ToastUtils.showShortToast("禁言成功");
                break;
            case "push.SendMessage":
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_LIVE_ROOM_MESSAGE_PUSH);
                break;
            case "SendMessage.C":
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_LIVE_ROOM_MESSAGE);
                break;
            case "LeaveRoom.C":
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_ENTER_LIVE_ROOM_LEAVE);
                break;
            case "push.Count":
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_ENTER_LIVE_ROOM_COUNT);
                break;
            case "JoinRoom.C":
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_ENTER_LIVE_ROOM);
                break;
            case "ConnectByProto.C":
                IMLoginManager.instance().serverInitResult(packet.getContentBytes());
                break;
            case "push.Gift":
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_LIVE_ROOM_GIFT_PUSH);
                break;
            case "ClosePlayHandler.C"://主播下播回调
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_LIVE_ROOM_COLSE);
                break;
            case "push.ClosePlay"://主播下播推送
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_LIVE_ROOM_COLSE_PUSH);
                break;
            case "push.Msg"://私信推送
            case "push.MessageAirlines"://客服推送
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_CHAT_MSG_PUSH);
                break;
            case "SendMessageTalk.C"://私信回调
            case "SendMessageAirlinesTalk.C"://客服回调
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_CHAT_MSG);
                break;
            case "push.systemNotification"://系统消息推送:
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_SYSTEM_NOTI_MSG_PUSH);
                break;
            case "push.Kick"://账号下线推送
                LogUtils.e("账号下线推送");
                if (!SPUtils.getIP().isEmpty()) {
                    IMLoginManager.instance().exit(packet.getContentBytes());
                }
                break;
            case "push.SystemNotificationImmediately": //主播禁播推送
                IMLiveRoomManager.instance().hostBanned(packet.getContentBytes());
                break;
            case "push.AnchorHotRanking":
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_ANCHOR_HOT_RANKING);
                break;
            case "push.auditMessage":
                IMLiveRoomManager.instance().enterRevMessage(packet.getContentBytes(), IMEventType.ACTION_UPDATE_COVER);
                break;
            case "CallHandler.C": //用户端发起连麦回调
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_USER_CALL);
                break;
            case "push.Answer"://主播发起连麦推送
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_HOST_RES_CALL);
                break;
            case "push.Call"://主播连麦列表监听
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_HOST_USER_CALL_PUSH);
                break;
            case "push.Cancel": //用户发起取消连麦
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_CANCEL_CALL_PUSH);
                break;
            case "push.removeCall"://连麦超时
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_TIMEOUT_CALL_PUSH);
                break;
            case "AnswerlHandler.C":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_ANSWERL);
                break;
            case "push.Stream":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_WHEAT_STREAM_PUSH);
                break;
            case "push.Hangup":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_WHEAT_HANGUP_PUSH);
                break;
            case "push.Hangup2":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_WHEAT_HANGUP_PUSH_2);
                break;
            case "PKSendFriend.C": //好友PK邀请回调
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_Friend_PK_INVITATION);
                break;
            case "push.AcceptFriendPK"://好友推送接听
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_FRIEND_PK_RECEIVE_PUSH);
                break;

            case "push.PKFriendTimeout"://连接超时（全局监听）
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_FRIEND_PK_TIME_OUT_PUSH);
                break;
            case "push.FriendPKReject": //好友pk响应拒绝
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_FRIEND_PK_PKREJECT_PUSH);
                break;
            case "push.RecurPKReject": //再来一局pk响应拒绝
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_RECURPKREJECT_PUSH);
                break;
            case "push.PKRandomTimeout": //随机PK超时
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_PKRANDOMTIMEOUT_PUSH);
                break;
            case "push.PKRecurTimeout": //再来一局pk响应超时
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_PKRECURTIMEOUT_PUSH);
                break;
            case "push.StreamPK": //将接收方的房间合并好的流推送给当前这个直播间的用户
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_FRIEND_PK_STREAMPK_PUSH);
                break;
            case "push.ConsentPK":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_FRIEND_PK_CONSENTPK_PUSH);
                break;
            case "push.AcceptRandomPK":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_FRIEND_PK_ACCEPTRANDOMPK_PUSH);
                break;
            case "push.SendRandomPK":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_FRIEND_PK_SENDRANDOMPK_PUSH);
                break;
            case "AddMergeStream.C": //发起方得到对方pk同意后上传合流地址
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_FRIEND_PK_ADDMERGESTREAM);
                break;
            case "PKSendRandom.C": //随机PK发送回调
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_RANDOM_PK_INVITATION);
                break;
            case "PKHangup.C": //主播强制挂断回调
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_PKHANGUP);
                break;
            case "push.PKClose": //PK结束推送
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_PKCLOSE_PUSH);
                break;
            case "PKResponse.C": //PK接收方合流地址上传回调
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_PKRESPONSE);
                break;
            case "push.PKAnchorInfo": //PK方的信息给客户端
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_PKANCHORINFO_PUSH);
                break;
            case "push.PKResult":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_PKRESULT_PUSH);
                break;
            case "push.AcceptRecurPK": //再来一局推送给对方监听
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_ACCEPTRECURPK_PUSH);
                break;
            case "PKSendRecur.C":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_ACCEPTRECURPK);
                break;
            case "push.battleForce": //金币推送
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_BATTLEFORCE_PUSH);
                break;
            case "ReconnectionHandler.C":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_GAME_RECONNECTIONHANDLER);
                break;
            case "push.wait": //金币游戏阶段时间
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_GAME_GOLD_WAIT);
                break;
            case "BetHandler.C":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_GAME_GOLD_BET);
                break;
            case "push.Game":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_GAME_GOLD_TOTAL_COUNT);
                break;
            case "push.win":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_GAME_GOLD_RESULTS_PUSH);
                break;
            case "PKResidueTime.C":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_PK_PKRESIDUETIME);
                break;
            case "GameLogHandler.C":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_GAMELOGHANDLER);
                break;
            case "CancelHandler.C":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_WHEAT_CANCEL_HANDLER);
                break;
            case "PKCancel.C":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_RANDOM_PK_CANCEL);
                break;
            case "push.anchorRoomMessage":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.ACTION_LINK_ANCHORROOMMESSAGE_PUSH);
                break;
            case "BlockHandler.C":
                LogUtils.e("BlockHandler.C");
                break;
            case "push.GameSmashGoldenEggsWinningNotificationMessage":
                IMLiveRoomManager.instance().linkRevMessage(packet.getContentBytes(), IMEventType.EGG);
                break;
            case "push.TopWin":
                try {
                    PushTopWinOrder.PushTopWinMessage pushTopWinMessage = PushTopWinOrder.PushTopWinMessage.parseFrom(packet.getContentBytes());
                    LogUtils.e(pushTopWinMessage.toString());
                    OrderPushBean bean = new OrderPushBean(pushTopWinMessage.getUserId(), pushTopWinMessage.getGameName(), pushTopWinMessage.getOrderId(), pushTopWinMessage.getCount(), pushTopWinMessage.getUnits(), pushTopWinMessage.getAmount(), pushTopWinMessage.getOrderMsg(), pushTopWinMessage.getStatus(), pushTopWinMessage.getPushType(), pushTopWinMessage.getCancelType(), pushTopWinMessage.getNickname());
                    BusUtils.post("orderPush", bean);
                    //
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}