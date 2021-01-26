package com.jk.jkproject.manager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat.Builder;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.info.MessageEntity;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class MessageHelper {


    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static MessageHelper instance = null;

    /* 私有构造方法，防止被实例化 */
    private MessageHelper() {
    }

    private Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    public void releaseContext() {
        mContext = null;
    }

    public static synchronized MessageHelper getInstance() {
        if (instance == null) {
            instance = new MessageHelper();
        }
        return instance;
    }


    /**
     * 处理系统推送
     *
     * @param msg 消息
     * @return 是否是红点推送 true是红点推送,播放声音 ，false是系统消息
     */
    // ==============================================
    public boolean handleSystemMessage(MessageEntity msg) {

        boolean result = false;
        String text = msg.getText();
        int type = 0;
        String msgString = "";
//        LogUtils.e("MessageHelper：收到消息" + text);
        try {
            JSONObject json = new JSONObject(text);
            type = json.getInt("type");
            msgString = json.getString("text");
        } catch (JSONException e) {
            return result;
        }
//        LogUtils.e(">>>>>>>>>>>info=handleSystemMessage=" + msgString + "::" + +type);
        switch (type) {
            /////////////// 一对一相关 start  /////////////
            case Constants.OBSERVABLE_ACTION_CONFIRM_BIND:
                notificationObject(Constants.OBSERVABLE_ACTION_CONFIRM_BIND_FOR_USER, msgString);
                result = true;
                break;

            case Constants.OBSERVABLE_ACTION_RELATE_CHANGE://私聊关系发生改变
                notificationObject(Constants.OBSERVABLE_ACTION_RELATE_CHANGE_FRO_USER, msgString);
                result = true;
                break;
            case Constants.OBSERVABLE_ACTION_CALL_UPDATA_CALL_STATUS: //更新1v1主播的在线状态
                notificationObject(Constants.OBSERVABLE_ACTION_CALL_UPDATA_CALL_STATUS_FOR_USER, msgString);
                result = true;
                break;
            case Constants.OBSERVABLE_ACTION_START_CALL:// 收到通话请求
                LogUtils.e(msgString);
                notificationObject(Constants.OBSERVABLE_ACTION_START_CALL_FOR_USER, msgString);
                result = true;
                break;
            case Constants.OBSERVABLE_ACTION_CALL_CALL:// 开始视频
                notificationObject(Constants.OBSERVABLE_ACTION_CALL_FOR_USER, msgString);
                result = true;
                break;
            case Constants.OBSERVABLE_ACTION_CALL_HANGEUP:// 结束视频
                notificationObject(Constants.OBSERVABLE_ACTION_CALL_HANGEUP_FOR_USER, msgString);
                result = true;
                break;
            case Constants.OBSERVABLE_ACTION_CALL_BLURRY:// 视频模糊
                notificationObject(Constants.OBSERVABLE_ACTION_CALL_BLURRY_FOR_USER, msgString);
                result = true;
                break;
            case Constants.OBSERVABLE_ACTION_CALL_GLAMOUR:// 魅力值变化
                notificationObject(Constants.OBSERVABLE_ACTION_CALL_GLAMOUR_FOR_USER, msgString);
                result = true;
                break;
            case Constants.OBSERVABLE_ACTION_CALL_WARN://警告消息
                notificationObject(Constants.OBSERVABLE_ACTION_CALL_WARN_FRO_USER, msgString);
                result = true;
                break;

/////////////// 一对一相关    end  /////////////


            case Constants.OBSERVABLE_ACTION_PK_END_MESSAGE://结束  PK
                result = true;
                notificationObject(Constants.OBSERVABLE_ACTION_PK_END_MESSAGE_ROOM_CANCLE, msgString);
                break;
            case Constants.OBSERVABLE_ACTION_PK_END_MESSAGE_STATUS://有人结束pk
                result = true;
                LogUtils.e("MessageHelper：收到消息 end" + text);
                notificationObject(Constants.OBSERVABLE_ACTION_PK_END_MESSAGE_STATUS_ROOM, msgString);
                break;

            case Constants.OBSERVABLE_ACTION_PK_START_MESSAGE_STATUS://有人开始pk
                result = true;
                LogUtils.e("MessageHelper：收到消息 start " + text);
                notificationObject(Constants.OBSERVABLE_ACTION_PK_START_MESSAGE_STATUS_ROOM, msgString);
                break;

            case Constants.RED_DOT_CHAT_GIFT:
                result = false;
                break;

            //221通知栏通知视频
            case Constants.RED_DOT_CHAT_NOTIFICATIONPUSH_VIDOE:
                result = false;
                //如果MainAvtivity在前台 就执行匹配消息   OBSERVABLE_ACTION_RECOMM_MESSAGE_VIDEO_CHAT
                if (msgString != null) {
                    notificationObject(Constants.OBSERVABLE_ACTION_RECOMM_MESSAGE_VIDEO_CHAT, msgString, msg.getMsgID());
                }
                break;
            //221通知栏通知视频
            case Constants.RED_DOT_NOTIFICATIONPUSH_VIDOE:
                result = false;
                //如果MainAvtivity在前台 就执行匹配消息
                if (msgString != null) {
                    notificationObject(Constants.OBSERVABLE_ACTION_RECOMM_MESSAGE_VIDEO, msgString, msg.getMsgID());
                }
                break;  //221通知栏消息
            case Constants.RED_DOT_NOTIFICATIONPUSH:

                result = false;
                //如果MainAvtivity在前台 就执行匹配消息
                if (msgString != null) {
                    notificationObject(Constants.OBSERVABLE_ACTION_RECOMM_MESSAGE, msgString);
                }
                break;
            default:
                result = true;
                break;
        }
        return result;

    }

    /**
     * 处理直播消息类型
     * // ==============================================
     * // 直播房间系统推送
     * // 301 系统消息
     * // 302 警告消息
     * // 303 关注消息
     * // 304 分享消息
     * // 305 土豪进房间
     * // 306 系统公屏消息
     * // 307 宝箱结束通知
     * // 308 进房特效
     * // 312 添加房管
     * // 313 解除房管
     * <p/>
     * // 350 游戏创建
     * // 351 游戏加入
     * //352 游戏预开始
     * //353 游戏开始
     * //354 游戏结束
     * //355 游戏筹集失败
     * <p/>
     * // 402 直播暂停
     * // 403 重回直播
     * // 404 意外退出
     * // 405 封禁主播
     * // 407 主播解散直播
     * // ==============================================
     *
     * @param msg
     * @return 是否在消息列表显示
     */
    public boolean handleLiveChatMessage() {
        boolean result = false;
//        LiveChat.ChatMsgMajorType majorType = msg.getChat_msg_major_type();
//        LiveChat.ChatMsgMinorType minorType = msg.getChat_msg_minor_type();

//        MLog.d(">>>>>>>>>>>info=majorType=" + majorType + ">>>>minorType=" + minorType);

//        switch (majorType) {
//
//            case CHATMSGMAJORTYPE_NORMAL:
//                switch (minorType) {
//                    case CHATMSGMINORTYPE_TEXT:
//                        result = true;
//                        break;
//                    case CHATMSGMINORTYPE_GIFT:
//                        result = true;
//                        break;
//                    case CHATMSGMINORTYPE_BC:
////                        MLog.i("----->房间内弹幕消息");
//
//                        result = true;
//                        Bundle bd1 = new Bundle();
//                        bd1.putInt("method", Constants.OBSERVABLE_ACTION_LIVE_ROOM_DANMU);
//                        bd1.putSerializable("message", msg);
//                        MessageNotifyCenter.getInstance().doNotify(bd1);
//                        break;
//                    case CHATMSGMINORTYPE_HORN:
//                        result = true;
//                        Bundle bd = new Bundle();
//                        bd.putInt("method", Constants.OBSERVABLE_ACTION_LIVE_HORN_USERCHAT);
//                        bd.putSerializable("message", msg);
//                        MessageNotifyCenter.getInstance().doNotify(bd);
//                        break;
//                    default:
//                        result = false;
//                        break;
//                }
//
//                break;
//            case CHATMSGMAJORTYPE_SYSTEM:
//
//                //系统混合消息
//                if (minorType == LiveChat.ChatMsgMinorType.CHATMSGMINORTYPE_COMPOUND) {
//                    String content = msg.getContent();
////                    MLog.i("message = " + content);
//
//                    LiveChatSystemMsg systemMsg = GsonUtils.get().fromJson(content, LiveChatSystemMsg.class);
//                    if (systemMsg != null) {
//
//                        int type = systemMsg.getType();
//                        String live_id = systemMsg.getLive_id();
//
//                        switch (type) {
//                            case Constants.OBSERVABLE_ACTION_PK_CHANGE_MESSAGE://魅力数据变化
//                                try {
//                                    JSONObject jsonPk = new JSONObject(content);
//                                    String text = jsonPk.getString("text");
//                                    notificationObject(Constants.OBSERVABLE_ACTION_PK_CHANGE_MESSAGE_ROOM, text);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//                            case Constants.OBSERVABLE_ACTION_PK_START_MESSAGE://开始  PK
//                                try {
//                                    JSONObject jsonPk = new JSONObject(content);
//                                    String text = jsonPk.getString("text");
//                                    notificationObject(Constants.OBSERVABLE_ACTION_PK_START_MESSAGE_ROOM, text);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//                            case Constants.OBSERVABLE_ACTION_PK_END_MESSAGE://结束  PK
//                                try {
//                                    JSONObject jsonPk = new JSONObject(content);
//                                    String text = jsonPk.getString("text");
//                                    notificationObject(Constants.OBSERVABLE_ACTION_PK_END_MESSAGE_ROOM, text);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_MESSAGE:
//                                result = true;
//                                msg.setStyle(systemMsg.getStyle());
//                                msg.setType(type);
//                                msg.setContent(systemMsg.getText());
//                                msg.setRichText(systemMsg.getRich_text());
//                                break;
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_WARN:
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_ATTENTION:
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_SHARE:
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_TYRANT:
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_SILENCE:
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_KICKED:
//                                result = true;
//                                msg.setType(type);
//                                msg.setContent(systemMsg.getText());
//                                if (type == Constants.TYPE_LIVE_CHAT_SYSTEM_WARN) {
//                                    //系统小秘书不用再聊天里出现了
//                                    result = false;
//                                    LiveRoomActivity.pushBroadcastReceiver(mContext, systemMsg.getText(), systemMsg.getName());
//                                }
//                                break;
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_PUBLIC_SCREEN:
//                                result = true;
//                                msg.setType(type);
//                                LogUtils.e("TYPE_LIVE_CHAT_SYSTEM_PUBLIC_SCREEN ", systemMsg.getText());
//                                msg.setContent(systemMsg.getText());
//                                msg.setRichText(systemMsg.getRich_text());
//                                notificationObject(Constants.OBSERVABLE_ACTION_LIVE_HORN_SYSTEM_PUBLIC_SCREEN, systemMsg);
//                                break;
//
//                            //添加房管 移除房管
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_ADD_HOUSING:
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_REMOVE_HOUSING:
//                                //{"uid"  : 12200022, "content" : "谁谁谁成了守护！", "role" : 2}
//                                String msgText = systemMsg.getText();
//                                try {
//                                    JSONObject jsonObject = new JSONObject(msgText);
//                                    String msgContent = jsonObject.getString("content");
//                                    msg.setType(type);
//                                    if (!TextUtils.isEmpty(msgContent)) {
//                                        result = true;
//                                        msg.setContent(msgContent);
//                                    }
//                                    notificationPush(type, msgText);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//
//                            //宝箱结束
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_CHEST_END:
//                                result = false;
//                                LogUtils.e("LiveViewForGiftChest  sysytem", systemMsg.getText());
//                                notificationPush(Constants.OBSERVABLE_ACTION_LIVE_CHEST_END, systemMsg.getText());
//                                break;
//                            //进房特效
//                            //进房特效
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_ROOM_EFFECTS:
//                                result = false;
//                                if (systemMsg.getRich_text() != null && systemMsg.getRich_text().size() > 0) {
//                                    notificationPush(Constants.OBSERVABLE_ACTION_LIVE_ROOM_EFFECTS, systemMsg.getText(), systemMsg.getRich_text());
//                                } else {
//                                    notificationPush(Constants.OBSERVABLE_ACTION_LIVE_ROOM_EFFECTS, systemMsg.getText());
//                                }
//                                break;
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_LIVE_PAUSE://
//                                result = false;
//                                notificationPush(Constants.TYPE_LIVE_CHAT_SYSTEM_LIVE_PAUSE, live_id);
//                                break;
//
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_LIVE_ONRESUME://
//                                result = false;
//                                notificationPush(Constants.TYPE_LIVE_CHAT_SYSTEM_LIVE_ONRESUME, live_id);
//                                break;
//
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_LIVE_ERROR://
//                                result = false;
//                                notificationObject(Constants.TYPE_LIVE_CHAT_SYSTEM_LIVE_ERROR, systemMsg);
//                                break;
//
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_CLOSURE_ANCHOR://
//                                result = false;
//                                notificationObject(Constants.TYPE_LIVE_CHAT_SYSTEM_CLOSURE_ANCHOR, systemMsg);
//                                break;
//
//                            case Constants.TYPE_LIVE_CHAT_SYSTEM_LIVE_DISSOLVE://
//                                result = false;
//                                notificationObject(Constants.TYPE_LIVE_CHAT_SYSTEM_LIVE_DISSOLVE, systemMsg);
//                                break;
//
//                            //主播游戏
//                            case Constants.TYPE_LIVE_CHAT_GAME_CREATE:
//                                //游戏创建
//                                result = false;
//                                LogUtils.e("游戏创建" + systemMsg.getText());
//                                notificationObject(Constants.TYPE_LIVE_CHAT_GAME_CREATE, systemMsg);
//                                break;
//
//                            case Constants.TYPE_LIVE_CHAT_GAME_CAN_BEGIN:
//                                //游戏预开始
//                                result = false;
//                                LogUtils.e("游戏预开始" + systemMsg.getText());
//                                notificationObject(Constants.TYPE_LIVE_CHAT_GAME_CAN_BEGIN, systemMsg);
//                                break;
//                            case Constants.TYPE_LIVE_CHAT_GAME_BEGIN:
//                                //游戏开始
//                                result = false;
//                                LogUtils.e("游戏开始" + systemMsg.getText());
//                                notificationObject(Constants.TYPE_LIVE_CHAT_GAME_BEGIN, systemMsg);
//                                break;
//
//                            case Constants.TYPE_LIVE_CHAT_GAME_JOIN:
//                                //游戏有人加入
//                                result = false;
//                                LogUtils.e("游戏加入" + systemMsg.getText());
//                                notificationObject(Constants.TYPE_LIVE_CHAT_GAME_JOIN, systemMsg);
//                                break;
//
//                            case Constants.TYPE_LIVE_CHAT_GAME_OVER:
//                                //游戏结束
//                                result = false;
//                                LogUtils.e("游戏结束" + systemMsg.getText());
//                                notificationObject(Constants.TYPE_LIVE_CHAT_GAME_OVER, systemMsg);
//                                break;
//
//                            case Constants.TYPE_LIVE_CHAT_GAME_FAIL:
//                                //游戏结束
//                                result = false;
//                                LogUtils.e("游戏筹集失败" + systemMsg.getText());
//                                notificationObject(Constants.TYPE_LIVE_CHAT_GAME_FAIL, systemMsg);
//                                break;
//                            case Constants.TYPE_LIVE_HOT_LIST:
//                                // 热榜排名
//                                L.i("systemMsg.getText() = " + systemMsg.getText());
//                                notificationPush(type, systemMsg.getText());
//                                break;
//                            case Constants.TYPE_LIVE_ACHIEVEMENT_MSG:
//                                // 直播成就消息
//                                L.i("systemMsg.getText() = " + systemMsg.getText());
//                                if (!TextUtils.isEmpty(systemMsg.getText())) {
//                                    if (!FuncUtils.isAppisBackground(AppApplication.getInstance())) {
//                                        LiveAchievementInfo info = GsonUtils.get().fromJson(systemMsg.getText(), LiveAchievementInfo.class);
//                                        if (info != null) {
//                                            notificationObject(type, info);
//                                        }
//                                    }
//                                }
//                                break;
//
//                            default:
//                                try {
//                                    JSONObject jsonPk = new JSONObject(content);
////                                    String text = jsonPk.getString("text");
//                                    String unSupport = jsonPk.getString("unsupport");
//                                    notificationObject(Constants.OBSERVABLE_ACTION_UNSUPPORT, unSupport);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//                        }
//
//
//                    } else {
//                        result = false;
//                    }
//
//                } else {
//                    //不支持系统发其他类型消息
//                    result = false;
//                }
//
//
//                break;
//            default:
//                result = false;
//                break;
//        }


        return result;

    }


    /**
     * 评论 发送 图文混排消息
     *
     * @param comment
     * @param userId
     * @param ownerId
     * @param userTagId
     * @param sessionId
     * @param sessionThumbImageUrl
     * @param sessionDescription
     * @param sessionType
     * @param displayType
     */
    public void sendCommentMsg(String comment, Long userId, String ownerId,
                               String userTagId, String sessionId, String sessionThumbImageUrl,
                               String sessionDescription, int sessionType, int displayType) {
        MessageEntity msg = obtainCommentTextMessage(comment, userId, ownerId,
                userTagId, sessionId, sessionThumbImageUrl, sessionDescription,
                sessionType, displayType);
        if (msg != null) {
//            IMMessageSendManager.instance().sendText(msg);
        }
    }


    /**
     * 生成浮框消息
     *
     * @param fromId
     * @param text
     * @param msgState
     * @return
     */
    public MessageEntity createFloatMsg(long fromId, String text, int msgState) {
        MessageEntity msg = null;
        String toId = SPUtils.Impl.getUid();
        if (toId != null && !toId.equals("")) {
            msg = new MessageEntity();
            msg.setUserID(Long.valueOf(toId));
            msg.setText(text);
            msg.setFromID(fromId);
            msg.setToID(Long.valueOf(toId));
            msg.setAction(IMEventType.USERCHATRECVRES);
            msg.setTime(System.currentTimeMillis());
            msg.setDisplayType(Constants.MSG_DISPLAY_TYPE_SYSTEM_FLOAT);//浮框消息
            msg.setMessageState(msgState);
        }
        return msg;
    }


    /**
     * 生成系统打分返回消息对象
     *
     * @param comment              内容
     * @param userId               toID
     * @param ownerId              fromID
     * @param userTagId            标签ID
     * @param sessionId            会话ID
     * @param sessionThumbImageUrl 类型图片地址
     * @param sessionDescription   描述
     * @param sessionType          类型 TypeText TypeImage TypeVoice TypeShortMovie
     * @param displayType          消息展示类型  Constants.MSG_DISPLAY_TYPE_NORMAL
     * @return
     */
    public MessageEntity obtainCommentTextMessage(String comment, Long userId,
                                                  String ownerId, String userTagId, String sessionId,
                                                  String sessionThumbImageUrl, String sessionDescription,
                                                  int sessionType, int displayType) {
        if (TextUtils.isEmpty(comment) || ownerId == null || userId == 0) {
            return null;
        }
        MessageEntity msg = new MessageEntity();
//        CommentMsg json = new CommentMsg();
//        json.setComment(comment);
//        json.setUserTagId(userTagId);
//        json.setSessionId(sessionId);
//        json.setSessionThumbImageUrl(sessionThumbImageUrl);
//        json.setSessionDescription(sessionDescription);
//        json.setSessionType(sessionType);
//        String content = GsonUtils.get().toJson(json);
//        msg.setText(content);
//        msg.setIsSend(true);
//        long createTime = System.currentTimeMillis();
//        msg.setTime(createTime);
//        msg.setFromID(Long.valueOf(ownerId));
//        msg.setToID(userId);
//        msg.setUserID(Long.valueOf(ownerId));
//        msg.setChatType(EChatType.ECT_NORMAL);
//        msg.setTextType(ETextType.ETT_COMPOUND);
//        msg.setDisplayType(displayType);
//        msg.setMessageState(Constants.MESSAGE_STATE_LOADDING);
//        msg.setSessionId(userId);
        return msg;

    }


    /**
     * 生成匹配成功消息
     *
     * @param fromId
     * @param text
     * @return
     */
    public MessageEntity createMatchMsg(MessageEntity msg, long fromId, String text) {
        // MessageEntity msg = null;
        String toId = SPUtils.Impl.getUid();
        if (toId != null && !toId.equals("")) {
            // msg = new MessageEntity();
            msg.setUserID(Long.valueOf(toId));
            msg.setText(text);
            msg.setFromID(fromId);
            msg.setToID(Long.valueOf(toId));
            msg.setAction(IMEventType.USERCHATRECVRES);
            msg.setDisplayType(Constants.MSG_DISPLAY_TYPE_SYSTEM_MATCH);
            msg.setMessageState(Constants.MESSAGE_STATE_SUCCESSED_FINISH);
        }
        return msg;
    }


    private void notificationPush(int type, String object) {
        // broadcast
        Bundle bd = new Bundle();
        bd.putInt("method", type);
        if (object != null) {
            bd.putString("object", object);
        }
        MessageNotifyCenter.getInstance().doNotify(bd);
    }


    private void notificationObject(int type, Object object, String msgId) {
        // broadcast
        Bundle bd = new Bundle();
        bd.putInt("method", type);
        bd.putString("msgId", msgId);
        bd.putSerializable("object", (Serializable) object);
        MessageNotifyCenter.getInstance().doNotify(bd);
    }

    private void notificationObject(int type, Object object) {
        // broadcast
        Bundle bd = new Bundle();
        bd.putInt("method", type);
        bd.putSerializable("object", (Serializable) object);
        MessageNotifyCenter.getInstance().doNotify(bd);
    }


    /**
     * 系统返回的消息是一个json格式 {"type":1,"status":1,"text":"kdjgkdaidk"}
     *
     * @param msg
     */

    public String systemMsgParse(String msg) {
        String text;
        try {
            JSONObject json = new JSONObject(msg);
            text = json.getString("text");
        } catch (JSONException e) {
            text = "系统消息";
        }
        return text;

    }

    /**
     * 收到红点推送后通知出来
     *
     * @param content
     * @param rollText
     */
    public void showInNotification(String content, String rollText, Intent mIntent) {
        Context ctx = AppApplication.getInstance();
        NotificationManager notifyMgr = (NotificationManager) AppApplication
                .getInstance().getSystemService(Context.NOTIFICATION_SERVICE);

        if (notifyMgr == null) {
            return;
        }
        Builder builder = new Builder(ctx);
        builder.setContentTitle("甜橙");
        builder.setContentText(content);
        builder.setTicker(rollText);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);

//        Bitmap defaultBitmap = BitmapFactory.decodeResource(ctx.getResources(),
//                R.mipmap.app_logo);
//        if (defaultBitmap != null) {
//            builder.setLargeIcon(defaultBitmap);
//        }

        //Intent mIntent = new Intent(ctx, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, mIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notifyMgr.notify(0, notification);
    }

//    private boolean shouldUseNotificationSound() {
//        return SPUtils.getBoolean(SPUtils.Impl.NOTIFICATION_VOICE, true);
//    }


    /**
     * 根据不同消息类型 获取消息文本内容
     *
     * @param msg
     * @return
     */
    public String getMessageEntityDescription(MessageEntity msg) {
        if (msg == null) {
            return "错误消息";
        }
        int type = msg.getTextType().getNumber();
        return "你收到一条系统消息";
    }
}
