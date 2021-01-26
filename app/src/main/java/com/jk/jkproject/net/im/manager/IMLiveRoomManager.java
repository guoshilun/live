package com.jk.jkproject.net.im.manager;

import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.net.PushGameSmashGoldenEggsWinningNotification;
import com.jk.jkproject.net.im.RequestPacket;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.info.BannedByProto;
import com.jk.jkproject.net.im.info.BlockByProto;
import com.jk.jkproject.net.im.info.ClosePlayByProto;
import com.jk.jkproject.net.im.info.JoinRoomByProto;
import com.jk.jkproject.net.im.info.LeaveRoomByProto;
import com.jk.jkproject.net.im.info.PushAnchorHotRankingByProto;
import com.jk.jkproject.net.im.info.PushAnchorRoomMessageByProto;
import com.jk.jkproject.net.im.info.PushAuditMessageByProto;
import com.jk.jkproject.net.im.info.PushClosePlayByProto;
import com.jk.jkproject.net.im.info.PushCountByProto;
import com.jk.jkproject.net.im.info.PushGiftByProto;
import com.jk.jkproject.net.im.info.PushMessageByProto;
import com.jk.jkproject.net.im.info.PushSystemNotificationByProto;
import com.jk.jkproject.net.im.info.PushSystemNotificationImmediatelyByProto;
import com.jk.jkproject.net.im.info.SendMessageByProto;
import com.jk.jkproject.net.im.netty.ChujianSocketThread;
import com.jk.jkproject.ui.activity.LoginActivity;
import com.jk.jkproject.ui.chat.Message;
import com.jk.jkproject.ui.chat.MsgSendStatus;
import com.jk.jkproject.ui.chat.MsgType;
import com.jk.jkproject.ui.chat.TextMsgBody;
import com.jk.jkproject.ui.dialog.DialogNotices;
import com.jk.jkproject.ui.dialog.DialogWarningNotices;
import com.jk.jkproject.ui.entity.DanmuBean;
import com.jk.jkproject.ui.entity.JoinRoomBeanInfo;
import com.jk.jkproject.ui.entity.LiveClosePushInfo;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.entity.LiveRoomGameGoldBaseInfo;
import com.jk.jkproject.ui.entity.LiveRoomGameGoldInfo;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.entity.PKBattleBaseInfo;
import com.jk.jkproject.ui.entity.PushPKStreamInfo;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.ui.entity.TypeProtocol;
import com.jk.jkproject.ui.entity.TypeSerialize;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.net.im.info.AddMergeByProto;
import com.jk.jkproject.net.im.info.AnswerByProto;
import com.jk.jkproject.net.im.info.BetByProto;
import com.jk.jkproject.net.im.info.CallByProto;
import com.jk.jkproject.net.im.info.GameLogProto;
import com.jk.jkproject.net.im.info.HangupPKByProto;
import com.jk.jkproject.net.im.info.PKBattleForceByProto;
import com.jk.jkproject.net.im.info.PKCancelByProto;
import com.jk.jkproject.net.im.info.PKResidueTimeByProto;
import com.jk.jkproject.net.im.info.PKResponseByProto;
import com.jk.jkproject.net.im.info.PushGame;
import com.jk.jkproject.net.im.info.PushGameWait;
import com.jk.jkproject.net.im.info.PushLinkByProto;
import com.jk.jkproject.net.im.info.PushPKAnchorInfo;
import com.jk.jkproject.net.im.info.PushPKCloseByProto;
import com.jk.jkproject.net.im.info.PushPKInfo;
import com.jk.jkproject.net.im.info.PushPKResult;
import com.jk.jkproject.net.im.info.PushRemoveCall;
import com.jk.jkproject.net.im.info.PushResponse;
import com.jk.jkproject.net.im.info.PushStreamByProto;
import com.jk.jkproject.net.im.info.PushStreamPKByProto;
import com.jk.jkproject.net.im.info.PushTimeoutPK;
import com.jk.jkproject.net.im.info.PushWin;
import com.jk.jkproject.net.im.info.ReconnectionByProto;
import com.jk.jkproject.net.im.info.SendPKFriendByProto;
import com.jk.jkproject.net.im.info.SendPKRandomByProto;
import com.jk.jkproject.net.im.info.SendPKRecurByProto;

import java.io.Serializable;

public class IMLiveRoomManager extends ChujianManager {
    private static IMLiveRoomManager                        inst;
    private        SendMessageByProto.SendMessageByProtoRes sendMes;
    private        DialogNotices                            warningNoti;
    private        DialogWarningNotices                     mWarningNotices;

    public static IMLiveRoomManager instance() {
        if (inst == null) {
            synchronized (IMLiveRoomManager.class) {
                if (inst == null) {
                    inst = new IMLiveRoomManager();
                }
            }
        }
        return inst;
    }

    public void enterRevMessage(byte[] contentBytes, int paramInt) {
        try {
            DanmuBean.DataBean.ToBean toBean;
            PushCountByProto.pushCountMessage pushCountMessage;
            JoinRoomByProto.JoinRoomByProtoRes joinRoomByProtoRes;
            PushMessageByProto.pushMessage pushMessage;
            PushGiftByProto.pushGift pushGift;
            LeaveRoomByProto.LeaveRoomByProtoRes leaveRoom;
            ClosePlayByProto.ClosePlayByProtoRes closePlay;
            PushClosePlayByProto.pushClosePlayMessage pushClosePlay;
            SendMessageByProto.SendMessageByProtoRes sendMessage;
            PushSystemNotificationByProto.PushSystemNotification systemNotification;
            PushSystemNotificationImmediatelyByProto.PushSystemNotificationImmediately systemNotificationImmediately;
            PushAnchorHotRankingByProto.pushAnchorHotRanking pushAnchorHotRanking;
            PushAuditMessageByProto.pushAuditMessage pushAuditMessage;
            DanmuBean danmuBean;
            DanmuBean.DataBean dataBean;
            DanmuBean.DataBean.FromBean fromBean;
            Bundle bd = new Bundle();
            switch (paramInt) {
                case IMEventType.ACTION_LIVE_ROOM_MESSAGE_PUSH:
                    pushMessage = PushMessageByProto.pushMessage.parseFrom(contentBytes);
                    LogUtils.e("弹幕推送=", pushMessage.toString());
                    danmuBean = new DanmuBean();
                    dataBean = new DanmuBean.DataBean();
                    fromBean = new DanmuBean.DataBean.FromBean();
                    toBean = new DanmuBean.DataBean.ToBean();
                    fromBean.setRid(pushMessage.getUserId());
                    fromBean.setMsgId(pushMessage.getMsgId());
                    fromBean.setMsgType(pushMessage.getMsgType());
                    fromBean.setUserType(pushMessage.getUserType());
                    fromBean.setTime(pushMessage.getTime());
                    fromBean.set__plat(pushMessage.getPicture());
                    fromBean.setLevel(pushMessage.getGrade() + "");
                    fromBean.setNickName(pushMessage.getUsername());
                    dataBean.setFrom(fromBean);
                    dataBean.setContent(pushMessage.getBody());
                    toBean.setToroom(pushMessage.getTarget());
                    dataBean.setTo(toBean);
                    danmuBean.setData(dataBean);
                    bd.putInt("method", IMEventType.ACTION_LIVE_ROOM_MESSAGE_PUSH);
                    bd.putSerializable("danmuBean", danmuBean);
                    break;
                case IMEventType.ACTION_LIVE_ROOM_MESSAGE:
                    sendMes = SendMessageByProto.SendMessageByProtoRes.parseFrom(contentBytes);
                    LogUtils.e("弹幕回调=", sendMes.getMsg());
                    break;
                case IMEventType.ACTION_ENTER_LIVE_ROOM_COUNT:
                    pushCountMessage = PushCountByProto.pushCountMessage.parseFrom(contentBytes);
                    LogUtils.e("房间人数推送=", pushCountMessage.toString());
                    bd.putInt("method", IMEventType.ACTION_ENTER_LIVE_ROOM_COUNT);
                    bd.putString("roomId", pushCountMessage.getRoomId());
                    bd.putInt("count", pushCountMessage.getCount());
                    break;
                case IMEventType.ACTION_ENTER_LIVE_ROOM:
                    joinRoomByProtoRes = JoinRoomByProto.JoinRoomByProtoRes.parseFrom(contentBytes);
                    LogUtils.e("加入房间=", joinRoomByProtoRes.toString());
                    bd.putInt("method", IMEventType.ACTION_ENTER_LIVE_ROOM);
                    if (joinRoomByProtoRes.getMsg().getCode() == 200) {
                        JoinRoomBeanInfo joinRoomBeanInfo = new JoinRoomBeanInfo();
                        joinRoomBeanInfo.setBanned(joinRoomByProtoRes.getBanned());
                        joinRoomBeanInfo.setPlayUrl(joinRoomByProtoRes.getPlayUrl());
                        joinRoomBeanInfo.setRoomName(joinRoomByProtoRes.getRoomName());
                        joinRoomBeanInfo.setCount(joinRoomByProtoRes.getCount());
                        joinRoomBeanInfo.setbId(joinRoomByProtoRes.getBeautifulUserId());
                        joinRoomBeanInfo.setPicture(joinRoomByProtoRes.getPicture());
                        joinRoomBeanInfo.setNickname(joinRoomByProtoRes.getNickname());
                        joinRoomBeanInfo.setAccount(joinRoomByProtoRes.getAccount());
                        joinRoomBeanInfo.setUserId(joinRoomByProtoRes.getUserId());
                        joinRoomBeanInfo.setAnchorGrade(joinRoomByProtoRes.getAnchorGrade());
                        joinRoomBeanInfo.setNeedExperience(joinRoomByProtoRes.getNeedExperience());
                        joinRoomBeanInfo.setIsAttention(joinRoomByProtoRes.getIsAttention());
                        joinRoomBeanInfo.setRanking(joinRoomByProtoRes.getRanking());
                        joinRoomBeanInfo.setStreamId(joinRoomByProtoRes.getStreamId());
                        joinRoomBeanInfo.setStreamPkId(joinRoomByProtoRes.getStreamPkId());
                        joinRoomBeanInfo.setPkId(joinRoomByProtoRes.getPkId());
                        joinRoomBeanInfo.setGameState(joinRoomByProtoRes.getGameState());
                        joinRoomBeanInfo.setOth(joinRoomByProtoRes.getOth());
                        LogUtils.e("进入房间数据" + joinRoomBeanInfo.toString());
                        bd.putSerializable("roomBeanInfo", (Serializable) joinRoomBeanInfo);
                        bd.putInt("type", 1);
                    } else if (joinRoomByProtoRes.getMsg().getCode() == 10057) {
                        bd.putInt("type", 0);
                    } else if (joinRoomByProtoRes.getMsg().getCode() == 10043) { //你已被主播拉黑，加入直播间失败
                        bd.putInt("type", 2);
                    } else if (joinRoomByProtoRes.getMsg().getCode() == 10013) { //直播间不存在
                        bd.putInt("type", 3);
                    }
                    break;
                case IMEventType.ACTION_LIVE_ROOM_GIFT_PUSH:
                    bd.putInt("method", IMEventType.ACTION_LIVE_ROOM_GIFT_PUSH);
                    pushGift = PushGiftByProto.pushGift.parseFrom(contentBytes);
                    LogUtils.e("礼物推送=", pushGift.toString());
                    if (pushGift != null) {
                        LiveGiftInfoBean.DataBean giftBean = new LiveGiftInfoBean.DataBean();
                        giftBean.setType(pushGift.getType());
                        giftBean.setIs_combo(pushGift.getStatus());
                        giftBean.setPicture(pushGift.getPicture());
                        giftBean.setUsername(pushGift.getUsername());
                        giftBean.setUserId(pushGift.getUserId());
                        giftBean.setG_name(pushGift.getGiftName());
                        giftBean.setG_icon(pushGift.getGiftUrl());
                        giftBean.setG_amount(pushGift.getAmount());
                        giftBean.setSvgaAnimate(pushGift.getGCartoonUrl());
                        giftBean.setId(pushGift.getGiftId());
                        giftBean.setCombo(1);
                        bd.putParcelable("giftBean", giftBean);
                    }
                    break;
                case IMEventType.ACTION_ENTER_LIVE_ROOM_LEAVE:
                    leaveRoom = LeaveRoomByProto.LeaveRoomByProtoRes.parseFrom(contentBytes);
                    bd.putInt("method", IMEventType.ACTION_ENTER_LIVE_ROOM_LEAVE);
                    bd.putInt("code", leaveRoom.getMsg().getCode());
                    LogUtils.e("主播下播推送=1", leaveRoom.toString());
                    break;
                case IMEventType.ACTION_LIVE_ROOM_COLSE:
                    closePlay = ClosePlayByProto.ClosePlayByProtoRes.parseFrom(contentBytes);
                    bd.putInt("method", IMEventType.ACTION_LIVE_ROOM_COLSE);
                    bd.putInt("code", closePlay.getMsg().getCode());
                    LogUtils.e("主播下播推送=2", closePlay.toString());
                    break;
                case IMEventType.ACTION_LIVE_ROOM_COLSE_PUSH:
                    pushClosePlay = PushClosePlayByProto.pushClosePlayMessage.parseFrom(contentBytes);
                    bd.putInt("method", IMEventType.ACTION_LIVE_ROOM_COLSE_PUSH);
                    LiveClosePushInfo closeInfo = new LiveClosePushInfo();
                    closeInfo.setRoomId(pushClosePlay.getRoomId());
                    closeInfo.setAccount(pushClosePlay.getAccount());
                    closeInfo.setAnchorGrade(pushClosePlay.getAnchorGrade());
                    closeInfo.setIsAttention(pushClosePlay.getIsAttention());
                    closeInfo.setNickName(pushClosePlay.getNickname());
                    closeInfo.setOth(pushClosePlay.getOth());
                    closeInfo.setPicture(pushClosePlay.getPicture());
                    bd.putSerializable("closeInfo", closeInfo);
                    LogUtils.e("主播下播推送=", closeInfo.toString());
                    break;
                case IMEventType.ACTION_CHAT_MSG://聊天回调
                    sendMessage = SendMessageByProto.SendMessageByProtoRes.parseFrom(contentBytes);
                    LogUtils.e("聊天回调=", sendMessage.toString());
                    bd.putInt("method", IMEventType.ACTION_CHAT_MSG);
                    Message message = new Message();
                    message.setUuid(sendMessage.getSgin());
                    if (sendMessage.getMsg().getCode() == 200) {
                        message.setSentStatus(MsgSendStatus.SENT);
                    } else {
                        message.setSentStatus(MsgSendStatus.FAILED);
                        message.setCode(sendMessage.getMsg().getCode());
                        message.setMsg(sendMessage.getMsg().getMsg());
                    }
                    bd.putSerializable("message", message);
                    break;
                case IMEventType.ACTION_CHAT_MSG_PUSH://聊天推送
                    //todo
                    pushMessage = PushMessageByProto.pushMessage.parseFrom(contentBytes);
                    LogUtils.e("聊天推送=", pushMessage.toString());
                    if (pushMessage.getMsgType() == Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_0 || pushMessage.getMsgType() >= 51 && pushMessage.getMsgType() <= 66) {
                        Message mMessage = new Message();
                        mMessage.setSentStatus(MsgSendStatus.SENT);
                        mMessage.setUuid(pushMessage.getMsgId());
                        mMessage.setUserId(pushMessage.getUserId());
                        mMessage.setTargetId(pushMessage.getTarget());
                        mMessage.setSentTime(Long.parseLong(pushMessage.getTime()));
                        mMessage.setSentStatus(MsgSendStatus.SENT);

                        if (pushMessage.getMsgType() == 0) {
                            mMessage.setMsgType(MsgType.TEXT);
                            TextMsgBody mTextMsgBody = new TextMsgBody();
                            mTextMsgBody.setMessage(pushMessage.getBody());
                            mMessage.setBody(mTextMsgBody);
                        }

                        if (pushMessage.getMsgType() >= 51 && pushMessage.getMsgType() <= 66) {
                            mMessage.setMsgType(MsgType.ORDER);
                            TextMsgBody mTextMsgBody = new TextMsgBody();
                            mTextMsgBody.setMessage(pushMessage.getBody());
                            mMessage.setBody(mTextMsgBody);
                        }
                        mMessage.setContent(pushMessage.getBody());
                        mMessage.setCount(1);
                        mMessage.setMsgId(pushMessage.getMsgId());
                        mMessage.setUserName(pushMessage.getUsername());
                        mMessage.setPicture(pushMessage.getPicture());
                        bd.putSerializable("mMessage", mMessage);
                        bd.putInt("method", IMEventType.ACTION_CHAT_MSG_PUSH);
                    }

                    /**
                     * * 订单相关
                     * *  51 用户已发起订单，同意并立刻接单
                     * *  52 用户取消订单
                     * *  53 超时未接单，订单已取消，金额将原路退回
                     * *  54 你已拒绝该订单，订单金额将原路退还
                     * *  55 订单已接受，去联系用户开始游戏
                     * *  56 用户昵称xxx已发起退款，请立即处理退款
                     * *  57 用户已经确认完成，订单已完成
                     * *  58 等待大神接单
                     * *  59 大神超时未接单
                     * *  60 你已取消订单，金额原路退还
                     * *  61 大神已拒绝订单，订单金额将原路退还
                     * *  62 大神已准备就绪，随时可以开始游戏
                     * *  63 你已申请退款，等待大神处理
                     * *  64 大神已同意退款，订单金额将原路退还
                     * *  65 大神已拒绝退款，原因：
                     * *  66 大神已发起服务完成，同意并确认完成
                     */
                    break;
                case IMEventType.ACTION_SYSTEM_NOTI_MSG_PUSH:
                    bd.putInt("method", IMEventType.ACTION_SYSTEM_NOTI_MSG_PUSH);
                    systemNotification = PushSystemNotificationByProto.PushSystemNotification.parseFrom(contentBytes);
                    LogUtils.e("系统消息推送=", systemNotification.toString());
                    SystemNotiBeanInfo.DataBean bean = new SystemNotiBeanInfo.DataBean();
                    bean.setCreate_time(TimeUtils.getNowMills());
                    bean.setMessage(systemNotification.getMessage());
                    bean.setType(systemNotification.getType());
                    bd.putSerializable("bean", bean);
                    break;
                case IMEventType.ACTION_WARNING_NOTI_PUSH:
                    break;
                case IMEventType.ACTION_ANCHOR_HOT_RANKING:
                    bd.putInt("method", IMEventType.ACTION_ANCHOR_HOT_RANKING);
                    pushAnchorHotRanking = PushAnchorHotRankingByProto.pushAnchorHotRanking.parseFrom(contentBytes);
                    LogUtils.e("排名推送=", pushAnchorHotRanking.toString());
                    bd.putString("roomId", pushAnchorHotRanking.getRoomId());
                    bd.putInt("ranking", pushAnchorHotRanking.getRanking());
                    break;
                case IMEventType.ACTION_UPDATE_COVER:
                    bd.putInt("method", IMEventType.ACTION_UPDATE_COVER);
                    pushAuditMessage = PushAuditMessageByProto.pushAuditMessage.parseFrom(contentBytes);
                    LogUtils.e("封面审核信息推送=", pushAuditMessage.toString());
                    bd.putInt("type", pushAuditMessage.getType());
                    bd.putInt("state", pushAuditMessage.getState());
                    break;
            }
            MessageNotifyCenter.getInstance().doNotify(bd);
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
            invalidProtocolBufferException.printStackTrace();
        }
    }


    public void hostBanned(byte[] contentBytes) {
        PushSystemNotificationImmediatelyByProto.PushSystemNotificationImmediately systemNotificationImmediately = null;
        try {
            systemNotificationImmediately = PushSystemNotificationImmediatelyByProto.PushSystemNotificationImmediately.parseFrom(contentBytes);
            LogUtils.e("主播禁播推送=", systemNotificationImmediately.toString());
            switch (systemNotificationImmediately.getType()) {// 类型: 1.警告 2.禁播 3.封号
                case 1:
                    setShowWarningDialog(systemNotificationImmediately.getType(), systemNotificationImmediately.getTime(), systemNotificationImmediately.getMessage());
                    break;
                case 2:
                    Bundle bd = new Bundle();
                    bd.putInt("method", IMEventType.ACTION_WARNING_NOTI_PUSH);
                    bd.putInt("type", systemNotificationImmediately.getType());
                    MessageNotifyCenter.getInstance().doNotify(bd);
                    break;
                case 3:
                    AppApplication.getInstance().stopService(ctx);
                    setShowDialog(2, systemNotificationImmediately.getTime(), systemNotificationImmediately.getMessage());
                    break;
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    //主播禁播弹窗
    private void setShowDialog(int mType, String time, String cause) {
        mWarningNotices = new DialogWarningNotices(ctx, mType, time, cause);
        mWarningNotices.show();
        mWarningNotices.setDialogClickListener(type -> {
            switch (type) {
                case 2:
                    AppApplication.getInstance().logOut(ctx);
                    Intent intent = new Intent(ctx, LoginActivity.class);
                    ctx.startActivity(intent);
                    break;
            }
        });
        mWarningNotices.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //主播禁播弹窗
    private void setShowWarningDialog(int mType, String time, String message) {
        warningNoti = new DialogNotices(ctx, mType, time, message);
        warningNoti.show();
        warningNoti.setDialogClickListener(type -> {
            switch (type) {// 类型: 1.警告 2.禁播 3.封号
                case 1:
                    break;

            }
        });
        warningNoti.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    public void enterRoom(String roomId) {
        LogUtils.e("roomId=", roomId);
        JoinRoomByProto.JoinRoomByProtoReq roomid = JoinRoomByProto.JoinRoomByProtoReq.newBuilder()
                .setRoomId(roomId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "live.JoinRoom", TypeSerialize.TYPE_PROTOBUF,
                roomid.toByteArray());
        sendMessagePacket(packet);
    }

    /**
     * @param roomId
     * @param type   1.用户退出 2.主播退出
     */
    public void exitRoom(String roomId, int type) {
        LeaveRoomByProto.LeaveRoomByProtoReq roomid;
        ClosePlayByProto.ClosePlayByProtoReq closePlay;
        RequestPacket packet = null;
        LogUtils.e("退出直播间=", roomId + "=====" + type);
        switch (type) {
            case 1:
                roomid = LeaveRoomByProto.LeaveRoomByProtoReq.newBuilder()
                        .setRoomId(roomId).build();
                packet = new RequestPacket(
                        TypeProtocol.TYPE_REQ, "live.LeaveRoom", TypeSerialize.TYPE_PROTOBUF,
                        roomid.toByteArray());
                break;
            case 2:
                closePlay = ClosePlayByProto.ClosePlayByProtoReq.newBuilder()
                        .setRoomId(roomId).build();
                packet = new RequestPacket(
                        TypeProtocol.TYPE_REQ, "live.ClosePlayHandler", TypeSerialize.TYPE_PROTOBUF,
                        closePlay.toByteArray());
                break;
        }
        sendMessagePacket(packet);
    }

    public void reset() {
        inst = null;
    }

    public void sendDanmu(DanmuBean bean) {
        SendMessageByProto.SendMessageByProtoReq send = SendMessageByProto.SendMessageByProtoReq.newBuilder().
                setTarget(bean.getData().getTo().getToroom()).
                setMsgType(bean.getData().getFrom().getMsgType()).
                setBody(bean.getData().getContent()).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "live.SendMessage", TypeSerialize.TYPE_PROTOBUF,
                send.toByteArray());
        sendMessagePacket(packet);
    }


    public void sendMsg(Message bean, String method) {
        SendMessageByProto.SendMessageByProtoReq send = SendMessageByProto.SendMessageByProtoReq.newBuilder().
                setTarget(bean.getTargetId()).
                setMsgType(bean.getmType()).
                setSgin(bean.getUuid()).
                setBody(bean.getContent()).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, method, TypeSerialize.TYPE_PROTOBUF,
                send.toByteArray());
        sendMessagePacket(packet);
    }

    public void sendMessagePacket(RequestPacket paramRequestPacket) {
        ChujianSocketThread chujianSocketThread = IMLoginManager.instance().getChujianSocket();
        if (chujianSocketThread != null) {
            chujianSocketThread.sendPacket(paramRequestPacket);
        } else {
            IMLoginManager.instance().tryReconnectServer();
        }
    }

    /**
     * @param roomId
     * @param tagId
     * @param method live.BannedUser  live.CancelBanned  live.SettingAdminHandler  live.CancelAdminHandler  live.KickLiveHandler
     */
    public void updateBanned(String roomId, String tagId, String method) {
        BannedByProto.BannedByProtoReq send = BannedByProto.BannedByProtoReq.newBuilder().
                setTagId(tagId).
                setRoomId(roomId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, method, TypeSerialize.TYPE_PROTOBUF,
                send.toByteArray());
        sendMessagePacket(packet);
    }

    /**
     * @param anchorId //主播id
     * @param streamId //流id
     * @param method   //方法名
     */
    public void userCallWheat(String anchorId, String streamId, String method) {
        CallByProto.CallByProtoReq call = CallByProto.CallByProtoReq.newBuilder().
                setAnchorId(anchorId).setStreamId(streamId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, method, TypeSerialize.TYPE_PROTOBUF,
                call.toByteArray());
        sendMessagePacket(packet);
    }

    /**
     * @param userId //用户id
     * @param type   //类型 1同意 2取消
     * @param method //方法名 AnswerlHandler
     */
    public void hostRefusedCallWheat(String roomId, String userId, int type, String method) {
        LogUtils.e("hostRefusedCallWheat=", userId);
        AnswerByProto.AnswerByProtoReq call = AnswerByProto.AnswerByProtoReq.newBuilder().
                setRoomId(roomId).setStreamId(userId).setUserId(userId).setType(type).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, method, TypeSerialize.TYPE_PROTOBUF,
                call.toByteArray());
        sendMessagePacket(packet);
    }


    /**
     * @param streamId //申请方连麦的流id
     * @param roomId   //房间id
     * @param method   //方法名 HangupHandler
     */
    public void hostDropCallWheat(String streamId, String roomId, String userId, String method) {
        AnswerByProto.AnswerByProtoReq call = AnswerByProto.AnswerByProtoReq.newBuilder().
                setStreamId(streamId).setRoomId(roomId).setUserId(userId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, method, TypeSerialize.TYPE_PROTOBUF,
                call.toByteArray());
        sendMessagePacket(packet);
    }

    public void linkRevMessage(byte[] contentBytes, int paramInt) {
        try {
            int code = 0;
            CallByProto.CallByProtoRes callByProtoRes;
            PushResponse.PushResponseMessage pushResponse;
            PushLinkByProto.pushLonk pushLonk;
            PushRemoveCall.PushremoveCallMessage pushremoveCallMessage;
            AnswerByProto.AnswerByProtoRes answer;
            PushStreamByProto.pushStream pushStream;
            SendPKFriendByProto.SendPKFriendRes sendPKFriendRes;
            PushPKInfo.PushPKInfoMessage pushPKInfoMessage;
            PushTimeoutPK.PushTimeoutPKMessage pushTimeoutPKMessage;
            PushStreamPKByProto.pushPKStream pushPKStream;
            AddMergeByProto.AddMergeByProtoRes addMergeByProtoRes;
            SendPKRandomByProto.SendPKRandomRes sendPKRandomRes;
            HangupPKByProto.HangupPKByProtoRes hangupPKByProtoRes;
            PushPKCloseByProto.pushPKClose pushPKClose;
            PKResponseByProto.PKResponseRes pkResponseRes;
            PushPKAnchorInfo.PushPKAnchorInfoMessage pushPKAnchorInfoMessage;
            PushPKResult.PushPKResultMessage pushPKResultMessage;
            SendPKRecurByProto.SendPKRecurRes sendPKRecurRes;
            PKBattleForceByProto.PKBattleForce pkBattleForce;
            PKResidueTimeByProto.PKResidueTimeRes pkResidueTimeRes;
            PKCancelByProto.PKCancelProtoRes pkCancelProtoRes;
            PushAnchorRoomMessageByProto.pushAnchorRoomMessage pushAnchorRoomMessage;
            PushGameSmashGoldenEggsWinningNotification.PushGameSmashGoldenEggsWinningNotificationMessage message;

            //金币游戏
            ReconnectionByProto.ReconnectionRes reconnectionRes;
            PushGameWait.PushGameWaitMessage pushGameWaitMessage;
            BetByProto.BetMessageRes betMessageRes;
            PushGame.PushGameMessage pushGameMessage;
            PushWin.PushWinMessage pushWinMessage;
            GameLogProto.GameLogRes gameLogRes;
            Bundle bd = new Bundle();
            bd.putInt("method", paramInt);
            switch (paramInt) {
                case IMEventType.ACTION_LINK_USER_CALL:
                    callByProtoRes = CallByProto.CallByProtoRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=用户发起连麦回调=", callByProtoRes.toString());
                    if (callByProtoRes.getMsg().getCode() == 200) {
                        bd.putInt("code", callByProtoRes.getMsg().getCode());
                    } else {
                        code = -200;
                        ToastUtils.showLongToast(callByProtoRes.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_HOST_RES_CALL:
                    pushResponse = PushResponse.PushResponseMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=主播发起连麦推送=", pushResponse.toString());
                    bd.putInt("type", pushResponse.getType());
                    bd.putString("userId", pushResponse.getUserId());
                    break;
                case IMEventType.ACTION_LINK_HOST_USER_CALL_PUSH:
                    pushLonk = PushLinkByProto.pushLonk.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=主播接收连麦人数=", pushLonk.toString());
                    LiveRoomWheatBaseInfo.DataBean info = new LiveRoomWheatBaseInfo.DataBean();
                    info.setUserGrade(pushLonk.getUserGrade() + "");
                    info.setPicture(pushLonk.getPicture());
                    info.setNickname(pushLonk.getNickname());
                    info.setTime(pushLonk.getTime());
                    info.setUserId(pushLonk.getUserId());
                    bd.putSerializable("info", info);
                    break;
                case IMEventType.ACTION_LINK_CANCEL_CALL_PUSH:
                    pushResponse = PushResponse.PushResponseMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=用户发起取消连麦=", pushResponse.toString());
                    bd.putInt("type", pushResponse.getType());
                    bd.putString("userId", pushResponse.getUserId());
                    break;
                case IMEventType.ACTION_LINK_TIMEOUT_CALL_PUSH:
                    pushremoveCallMessage = PushRemoveCall.PushremoveCallMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=用户连麦超时=", pushremoveCallMessage.toString());
                    bd.putString("userId", pushremoveCallMessage.getUserId());
                    break;
                case IMEventType.ACTION_LINK_ANSWERL:
                case IMEventType.ACTION_LINK_WHEAT_CANCEL_HANDLER:
                    answer = AnswerByProto.AnswerByProtoRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=主播同意和拒绝连麦回调=", answer.toString());
                    if (answer.getMsg().getCode() == 200) {
                        bd.putString("userId", answer.getUserId());
                    } else {
                        code = -200;
                        ToastUtils.showLongToast(answer.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_WHEAT_STREAM_PUSH:
                    pushStream = PushStreamByProto.pushStream.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=主播连麦推的流=", pushStream.toString());
                    bd.putString("streamId", pushStream.getStreamId());
                    bd.putString("anchorId", pushStream.getAnchorId());
                    bd.putString("userId", pushStream.getUserId());
                    break;
                case IMEventType.ACTION_LINK_WHEAT_HANGUP_PUSH:
                case IMEventType.ACTION_LINK_WHEAT_HANGUP_PUSH_2:
                    pushStream = PushStreamByProto.pushStream.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=主播连麦关流推送=", pushStream.toString());
                    bd.putString("streamId", pushStream.getStreamId());
                    break;
                case IMEventType.ACTION_LINK_Friend_PK_INVITATION:
                    sendPKFriendRes = SendPKFriendByProto.SendPKFriendRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=好友PK邀请回调=", sendPKFriendRes.getMsg().toString());
                    if (sendPKFriendRes.getMsg().getCode() == 200) {
                        bd.putInt("code", sendPKFriendRes.getMsg().getCode());
                    } else {
                        code = -200;
                        LogUtils.e("linkRevMessage=好友PK邀请回调=", sendPKFriendRes.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_RECEIVE_PUSH:
                case IMEventType.ACTION_LINK_FRIEND_PK_ACCEPTRANDOMPK_PUSH:
                case IMEventType.ACTION_LINK_FRIEND_PK_SENDRANDOMPK_PUSH:
                case IMEventType.ACTION_LINK_PK_ACCEPTRECURPK_PUSH:
                    pushPKInfoMessage = PushPKInfo.PushPKInfoMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=好友PK接收方推送=", pushPKInfoMessage.toString());
                    LiveRoomWheatBaseInfo.DataBean mPKInfo = new LiveRoomWheatBaseInfo.DataBean();
                    mPKInfo.setNickname(pushPKInfoMessage.getNickname());
                    mPKInfo.setPicture(pushPKInfoMessage.getPicture());
                    mPKInfo.setAnchorGrade(pushPKInfoMessage.getAnchorGrade() + "");
                    mPKInfo.setUserId(pushPKInfoMessage.getUserId());
                    mPKInfo.setRoomId(pushPKInfoMessage.getRoomId());
                    mPKInfo.setSteeamPKId(pushPKInfoMessage.getSteeamPKId());
                    bd.putSerializable("info", mPKInfo);
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_TIME_OUT_PUSH:
                case IMEventType.ACTION_LINK_FRIEND_PK_PKREJECT_PUSH://好友pk响应拒绝
                case IMEventType.ACTION_LINK_PK_RECURPKREJECT_PUSH:
                case IMEventType.ACTION_LINK_PK_PKRANDOMTIMEOUT_PUSH:
                case IMEventType.ACTION_LINK_PK_PKRECURTIMEOUT_PUSH:
                    pushTimeoutPKMessage = PushTimeoutPK.PushTimeoutPKMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=超时推送=", pushTimeoutPKMessage.toString());
                    bd.putString("userId", pushTimeoutPKMessage.getUserId());
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_STREAMPK_PUSH:
                case IMEventType.ACTION_LINK_FRIEND_PK_CONSENTPK_PUSH:
                    pushPKStream = PushStreamPKByProto.pushPKStream.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.StreamPK=", pushPKStream.toString());
                    PushPKStreamInfo pkStreamInfo = new PushPKStreamInfo();
                    pkStreamInfo.setAnchorId(pushPKStream.getAnchorId());
                    pkStreamInfo.setMergeStreamUrl(pushPKStream.getMergeStreamUrl());
                    pkStreamInfo.setPKId(pushPKStream.getPKId());
                    pkStreamInfo.setTagAnchorId(pushPKStream.getTagRoomId());
                    pkStreamInfo.setTagRoomId(pushPKStream.getTagRoomId());
                    pkStreamInfo.setTagStreamId(pushPKStream.getTagStreamId());
                    bd.putSerializable("pkStreamInfo", pkStreamInfo);
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_ADDMERGESTREAM:
                    addMergeByProtoRes = AddMergeByProto.AddMergeByProtoRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=AddMergeStream.C=", addMergeByProtoRes.getMsg().toString());
                    if (addMergeByProtoRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=AddMergeStream.C=", addMergeByProtoRes.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_RANDOM_PK_INVITATION:
                    sendPKRandomRes = SendPKRandomByProto.SendPKRandomRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=PKSendRandom.C=", sendPKRandomRes.getMsg().toString());
                    if (sendPKRandomRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=PKSendRandom.C=", sendPKRandomRes.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_PK_PKHANGUP:
                    hangupPKByProtoRes = HangupPKByProto.HangupPKByProtoRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=PKHangup.C=", hangupPKByProtoRes.getMsg().toString());
                    if (hangupPKByProtoRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=PKHangup.C=", hangupPKByProtoRes.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_PK_PKCLOSE_PUSH:
                    pushPKClose = PushPKCloseByProto.pushPKClose.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.PKClose=", pushPKClose.toString());
                    bd.putString("roomId", pushPKClose.getRoomId());
                    bd.putInt("type", pushPKClose.getType());
                    break;
                case IMEventType.ACTION_LINK_PK_PKRESPONSE:
                    pkResponseRes = PKResponseByProto.PKResponseRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=PKResponse.C=", pkResponseRes.toString());
                    if (pkResponseRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=PKResponse.C=", pkResponseRes.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_PK_PKANCHORINFO_PUSH:
                    pushPKAnchorInfoMessage = PushPKAnchorInfo.PushPKAnchorInfoMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.PKAnchorInfo=", pushPKAnchorInfoMessage.toString());
                    LiveRoomWheatBaseInfo.DataBean dataBean = new LiveRoomWheatBaseInfo.DataBean();
                    dataBean.setUserId(pushPKAnchorInfoMessage.getUserId());
                    dataBean.setNickname(pushPKAnchorInfoMessage.getNickname());
                    dataBean.setPicture(pushPKAnchorInfoMessage.getPicture());
                    dataBean.setAnchorGrade(pushPKAnchorInfoMessage.getAnchorGrade() + "");
                    dataBean.setRoomId(pushPKAnchorInfoMessage.getRoomId());
                    dataBean.setMergeStreamUrl(pushPKAnchorInfoMessage.getMergeStreamUrl());
                    dataBean.setLeftPkMaxVictory(pushPKAnchorInfoMessage.getLeftPkMaxVictory());
                    dataBean.setRightPkMaxVictory(pushPKAnchorInfoMessage.getRightPkMaxVictory());
                    bd.putSerializable("info", dataBean);
                    break;
                case IMEventType.ACTION_LINK_PK_PKRESULT_PUSH:
                    pushPKResultMessage = PushPKResult.PushPKResultMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.PKResult=", pushPKResultMessage.toString());
                    LiveRoomWheatBaseInfo.DataBean pkResult = new LiveRoomWheatBaseInfo.DataBean();
                    pkResult.setVictoryUserId(pushPKResultMessage.getVictoryUserId());
                    pkResult.setPicture(pushPKResultMessage.getPicture());
                    pkResult.setCondition(pushPKResultMessage.getCondition());
                    bd.putSerializable("info", pkResult);
                    break;
                case IMEventType.ACTION_LINK_PK_ACCEPTRECURPK:
                    sendPKRecurRes = SendPKRecurByProto.SendPKRecurRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=PKSendRecur.C=", sendPKRecurRes.toString());
                    if (sendPKRecurRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=PKSendRecur.C=", sendPKRecurRes.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_PK_BATTLEFORCE_PUSH:
                    pkBattleForce = PKBattleForceByProto.PKBattleForce.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.battleForce=", pkBattleForce.toString());
                    PKBattleBaseInfo battleBaseInfo = new PKBattleBaseInfo();
                    battleBaseInfo.setaMessage(pkBattleForce.getAMessage());
                    battleBaseInfo.setbMessage(pkBattleForce.getBMessage());
                    battleBaseInfo.setaReceiveNumber(pkBattleForce.getAReceiveNumber());
                    battleBaseInfo.setbReceiveNumber(pkBattleForce.getBReceiveNumber());
                    battleBaseInfo.setaRoomId(pkBattleForce.getARoomId());
                    battleBaseInfo.setbRoomId(pkBattleForce.getBRoomId());
                    bd.putSerializable("info", battleBaseInfo);
                    break;
                case IMEventType.ACTION_LINK_GAME_RECONNECTIONHANDLER:
                    reconnectionRes = ReconnectionByProto.ReconnectionRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=ReconnectionHandler.C=", reconnectionRes.toString());
                    if (reconnectionRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=ReconnectionHandler.C=", reconnectionRes.getMsg().getMsg());
                    }
                    LiveRoomGameGoldInfo goldInfo = new LiveRoomGameGoldInfo();
                    goldInfo.setFront(reconnectionRes.getFront());
                    goldInfo.setContrary(reconnectionRes.getContrary());
                    goldInfo.setMaxBet(reconnectionRes.getMaxBet());
                    goldInfo.setFrontBet(reconnectionRes.getFrontBet());
                    goldInfo.setContraryBet(reconnectionRes.getContraryBet());
                    goldInfo.setStage(reconnectionRes.getStage());
                    goldInfo.setTime(reconnectionRes.getTime());
                    goldInfo.setMoney(reconnectionRes.getMoney() + "");
                    goldInfo.setIsFirstOpen(reconnectionRes.getIsFirstOpen());
                    bd.putSerializable("info", goldInfo);
                    break;
                case IMEventType.ACTION_LINK_GAME_GOLD_WAIT: //金币游戏阶段时间推送
                    pushGameWaitMessage = PushGameWait.PushGameWaitMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.wait=", pushGameWaitMessage.toString());
                    bd.putInt("time", pushGameWaitMessage.getTime());
                    bd.putString("stage", pushGameWaitMessage.getStage());
                    break;
                case IMEventType.ACTION_LINK_GAME_GOLD_BET://金币游戏下注回调
                    betMessageRes = BetByProto.BetMessageRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=BetHandler.C=", betMessageRes.toString());
                    if (betMessageRes.getMsg().getCode() != 200) {
                        code = -200;
                        ToastUtils.showShortToast(betMessageRes.getMsg().getMsg());
                    } else {
                        LiveRoomGameGoldInfo goldBaseInfo = new LiveRoomGameGoldInfo();
                        goldBaseInfo.setMoney(betMessageRes.getMoney() + "");
                        goldBaseInfo.setFrontBet(betMessageRes.getFrontBet());
                        goldBaseInfo.setContraryBet(betMessageRes.getContraryBet());
                        bd.putSerializable("info", goldBaseInfo);
                    }
                    break;
                case IMEventType.ACTION_LINK_GAME_GOLD_TOTAL_COUNT://金币游戏总下注数量
                    pushGameMessage = PushGame.PushGameMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.Game", pushGameMessage.toString());
                    LiveRoomGameGoldInfo goldBaseInfo = new LiveRoomGameGoldInfo();
                    goldBaseInfo.setFront(pushGameMessage.getFront());
                    goldBaseInfo.setContrary(pushGameMessage.getContrary());
                    goldBaseInfo.setMaxBet(pushGameMessage.getMaxBet());
                    goldBaseInfo.setTime(pushGameMessage.getTime());
                    bd.putSerializable("info", goldBaseInfo);
                    break;
                case IMEventType.ACTION_LINK_GAME_GOLD_RESULTS_PUSH: //金币游戏结果推送
                    pushWinMessage = PushWin.PushWinMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.win=", pushWinMessage.toString());
                    bd.putInt("win", pushWinMessage.getWin());
                    bd.putInt("num", pushWinMessage.getNum());
                    bd.putInt("type", pushWinMessage.getType());
                    bd.putInt("money", pushWinMessage.getMoney());
                    break;
                case IMEventType.ACTION_LINK_PK_PKRESIDUETIME:
                    pkResidueTimeRes = PKResidueTimeByProto.PKResidueTimeRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=PKResidueTime.C=", pkResidueTimeRes.toString());
                    if (pkResidueTimeRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=PKResidueTime.C=", pkResidueTimeRes.getMsg().getMsg());
                    } else {
                        bd.putInt("time", pkResidueTimeRes.getTime());
                    }
                    break;
                case IMEventType.ACTION_LINK_GAMELOGHANDLER:
                    gameLogRes = GameLogProto.GameLogRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=game.GameLogHandler=", gameLogRes.toString());
                    if (gameLogRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=game.GameLogHandler=", gameLogRes.getMsg().getMsg());
                    } else {
                        LiveRoomGameGoldBaseInfo gameGoldBaseInfo = new LiveRoomGameGoldBaseInfo();
                        gameGoldBaseInfo.setData(gameLogRes.getGameLogList());
                        bd.putSerializable("info", gameGoldBaseInfo);
                    }
                    break;
                case IMEventType.ACTION_LINK_RANDOM_PK_CANCEL:
                    pkCancelProtoRes = PKCancelByProto.PKCancelProtoRes.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=PKCancel.C=", pkCancelProtoRes.toString());
                    if (pkCancelProtoRes.getMsg().getCode() != 200) {
                        code = -200;
                        LogUtils.e("linkRevMessage=PKCancel.C=", pkCancelProtoRes.getMsg().getMsg());
                    }
                    break;
                case IMEventType.ACTION_LINK_ANCHORROOMMESSAGE_PUSH:
                    pushAnchorRoomMessage = PushAnchorRoomMessageByProto.pushAnchorRoomMessage.parseFrom(contentBytes);
                    LogUtils.e("linkRevMessage=push.anchorRoomMessage=", pushAnchorRoomMessage.toString());
                    bd.putInt("grade", pushAnchorRoomMessage.getGrade());
                    bd.putString("roomId", pushAnchorRoomMessage.getRoomId());
                    bd.putLong("experience", pushAnchorRoomMessage.getExperience());
                    break;
                case IMEventType.EGG:
                    message = PushGameSmashGoldenEggsWinningNotification.PushGameSmashGoldenEggsWinningNotificationMessage.parseFrom(contentBytes);
                    bd.putString("username", message.getUserName());
                    bd.putString("gift", message.getPropsName());
                    bd.putInt("amont", message.getPropsAmount());
                    break;
            }
            if (code != -200) {
                MessageNotifyCenter.getInstance().doNotify(bd);
            }
        } catch (InvalidProtocolBufferException invalidProtocolBufferException) {
            invalidProtocolBufferException.printStackTrace();
        }
    }


    /**
     * //向好友发起PK
     *
     * @param roomId   //房间Id
     * @param streamId //流id
     * @param userId   //对方的用户id
     */
    public void sendFriendPK(String roomId, String streamId, String userId) {
        SendPKFriendByProto.SendPKFriendReq sendPKFriendReq = SendPKFriendByProto.SendPKFriendReq.newBuilder().
                setRoomId(roomId).setStreamId(streamId).setTagUserId(userId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "link.PKSendFriend", TypeSerialize.TYPE_PROTOBUF,
                sendPKFriendReq.toByteArray());
        sendMessagePacket(packet);
    }


    /**
     * 再来一局
     *
     * @param roomId    //房间Id
     * @param streamId  //流id
     * @param tagUserId //对方的用户id
     */
    public void sendPKSendRecur(String roomId, String streamId, String tagUserId) {
        SendPKRecurByProto.SendPKRecurReq sendPKRecurReq = SendPKRecurByProto.SendPKRecurReq.newBuilder().
                setRoomId(roomId).setStreamId(streamId).setTagUserId(tagUserId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "link.PKSendRecur", TypeSerialize.TYPE_PROTOBUF,
                sendPKRecurReq.toByteArray());
        sendMessagePacket(packet);
    }


    /**
     * PK响应--好友、随机公用
     *
     * @param info
     */
    public void sendPKResponse(LiveRoomWheatBaseInfo.DataBean info) {
        LogUtils.e("sendPKResponse=", info.getType());
        PKResponseByProto.PKResponseReq sendPKFriendReq = PKResponseByProto.PKResponseReq.newBuilder().
                setRoomId(info.getRoomId()).setStreamId(info.getSteeamPKId()).setAisle(info.getAisle()).
                setType(info.getType()).setMyRoomId(info.getMyRoomId()).setUserId(info.getUserId()).setMyStreamId(info.getMyStreamId()).
                setMergeStreamUrl(info.getMergeStreamUrl() == null ? "" : info.getMergeStreamUrl()).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "link.PKResponse", TypeSerialize.TYPE_PROTOBUF,
                sendPKFriendReq.toByteArray());
        sendMessagePacket(packet);
    }


    //发起方得到对方pk同意后上传合流地址
    public void sendAddMergeStream(String rtmp_url, String roomId) {
        AddMergeByProto.AddMergeByProtoReq addMergeByProtoReq = AddMergeByProto.AddMergeByProtoReq.newBuilder().setMergeStreamUrl(rtmp_url).
                setRoomId(roomId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "link.AddMergeStream", TypeSerialize.TYPE_PROTOBUF,
                addMergeByProtoReq.toByteArray());
        sendMessagePacket(packet);
    }

    /**
     * 发起随机PK
     *
     * @param roomId
     * @param streamId
     */
    public void sendRandomPk(String roomId, String streamId) {
        SendPKRandomByProto.SendPKRandomReq sendPKRandom = SendPKRandomByProto.SendPKRandomReq.newBuilder().
                setRoomId(roomId).setStreamId(streamId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "link.PKSendRandom", TypeSerialize.TYPE_PROTOBUF,
                sendPKRandom.toByteArray());
        sendMessagePacket(packet);
    }

    /**
     * PK期间强制挂断
     *
     * @param pkId     pkid
     * @param myRoomId 自己的房间Id
     * @param type     //挂断类型  1 5分钟内正常强制挂断  2 5秒倒计时挂断  3 惩罚时间挂断  4 异常挂断     5 pk正常结束
     */
    public void sendPKHangup(String pkId, String myRoomId, int type) {
        HangupPKByProto.HangupPKByProtoReq hangupPk = HangupPKByProto.HangupPKByProtoReq.newBuilder().
                setMyRoomId(myRoomId).setPkId(pkId).setType(type).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "link.PKHangup", TypeSerialize.TYPE_PROTOBUF,
                hangupPk.toByteArray());
        sendMessagePacket(packet);
    }

    /**
     * PK倒计时拉取
     *
     * @param pkId
     */
    public void sendPKResidueTime(String pkId) {
        PKResidueTimeByProto.PKResidueTimeReq pkResidueTimeReq = PKResidueTimeByProto.PKResidueTimeReq.newBuilder().setPkId(pkId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "link.PKResidueTime", TypeSerialize.TYPE_PROTOBUF,
                pkResidueTimeReq.toByteArray());
        sendMessagePacket(packet);
    }

    /**
     * 随机PK 发送者取消
     *
     * @param tagUserId //用户id
     */
    public void sendRandomPkCancel(String tagUserId) {
        PKCancelByProto.PKCancelByProtoReq pkCancelByProtoReq = PKCancelByProto.PKCancelByProtoReq.newBuilder().
                setTagUserId(tagUserId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "link.PKCancel", TypeSerialize.TYPE_PROTOBUF,
                pkCancelByProtoReq.toByteArray());
        sendMessagePacket(packet);
    }


    /**
     * //金币游戏
     *
     * @param roomId 房间ID
     */
    public void sendGameGold(String roomId) {
        ReconnectionByProto.ReconnectionReq reconnectionReq = ReconnectionByProto.ReconnectionReq.newBuilder().
                setRoomId(roomId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "game.ReconnectionHandler", TypeSerialize.TYPE_PROTOBUF,
                reconnectionReq.toByteArray());
        sendMessagePacket(packet);
    }


    /**
     * 下注
     *
     * @param type //下注   1 正面  2反面
     * @param bet  //下注钻石
     */
    public void sendGameGoldBat(int type, int bet) {
        BetByProto.BetMessageReq betMessageReq = BetByProto.BetMessageReq.newBuilder().setType(type).setBet(bet).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "game.BetHandler", TypeSerialize.TYPE_PROTOBUF,
                betMessageReq.toByteArray());
        sendMessagePacket(packet);
    }


    /**
     * 获取开奖记录
     * 获取战绩详情
     * req
     * game.GameLogReq
     * res
     * game.GameLogHandler    // 里面包含 list 集合
     */
    public void sendGameGoldLogReq() {
        LogUtils.e("========sendGameGoldLogReq");
        GameLogProto.GameLogReq gameLogReq = GameLogProto.GameLogReq.newBuilder().build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "game.GameLogHandler", TypeSerialize.TYPE_PROTOBUF,
                gameLogReq.toByteArray());
        sendMessagePacket(packet);
    }

    /**
     * 关闭金币游戏推送
     *
     * @param userId
     */
    public void sendGameGoldClose(String userId) {
        AnswerByProto.AnswerByProtoReq answerByProtoReq = AnswerByProto.AnswerByProtoReq.newBuilder().setUserId(userId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "game.GameCancelHandler", TypeSerialize.TYPE_PROTOBUF,
                answerByProtoReq.toByteArray());
        sendMessagePacket(packet);
    }

    //拉黑
    public void setBlock(String uid, String roomId) {
        BlockByProto.BlockByProtoReq blockByProto = BlockByProto.BlockByProtoReq.newBuilder().setTagId(uid).setRoomId(roomId).build();
        RequestPacket packet = new RequestPacket(
                TypeProtocol.TYPE_REQ, "live.BlockHandler", TypeSerialize.TYPE_PROTOBUF,
                blockByProto.toByteArray());
        sendMessagePacket(packet);
    }
}