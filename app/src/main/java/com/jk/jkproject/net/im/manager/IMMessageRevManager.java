package com.jk.jkproject.net.im.manager;

import android.os.Bundle;
import android.util.Log;

import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.db.MsgDBHelper;
import com.jk.jkproject.net.im.info.MessageEntity;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IMMessageRevManager extends ChujianManager {

  private static IMMessageRevManager inst;
  // Local thread data
  private boolean mRunning = false;
  // Msg list
  private Object mLockCoreMsg;
  private List<MessageEntity> mCoreMsgsList;
  private ThreadMsgsProc mProcessMsg;

  private static final String TAG = "ChujianMessageRevManager";
  private static final int ERT_CUR_AFTER_LOG_VALUE = 3;// 拉离线消息的时候
  // 每20条为一组发过来，并发送end
  // ，如果还有在继续发送20条为一组，一直循环

  public static IMMessageRevManager instance() {

    synchronized (IMMessageRevManager.class) {
      if (inst == null) {
        inst = new IMMessageRevManager();
      }
      return inst;
    }
  }

  private IMMessageRevManager() {
    init();
  }

  public void init() {
    mLockCoreMsg = new Object();
    mCoreMsgsList = new ArrayList<MessageEntity>();
    mRunning = true;
    mProcessMsg = new ThreadMsgsProc();
    mProcessMsg.start();
  }



//    public void onSendRecvMessageRequest(int type, long index, int remaincount) {
//        if (IMLoginManager.instance().getChujianSocket() != null) {
//            JoinRoomByProto.JoinRoomByProtoReq roomReq =
//            MessageInfo.UserChatRecv messageRev = null;
//            com.jk.cn.entity.MessageInfo.UserChatRecv.Builder builder = MessageInfo.UserChatRecv
//                    .newBuilder();
//            switch (type) {
//                case ERecvType.ERT_LOG_VALUE:
//                    messageRev = MessageInfo.UserChatRecv.newBuilder()
//                            .setType(ERecvType.ERT_LOG).build();
//                    break;
//
//                case ERecvType.ERT_CUR_VALUE:
//
//                    messageRev = MessageInfo.UserChatRecv.newBuilder()
//                            .setType(ERecvType.ERT_CUR).addSmsID(index).build();
//                    break;
//
//                case ERT_CUR_AFTER_LOG_VALUE:
//
//                    if (remaincount >= 20) {
//
//                        for (int i = 1; i <= 20; i++) {
//
//                            builder.addSmsID(index + i);
//                        }
//
//                    } else {
//
//                        for (int j = 1; j <= remaincount; j++) {
//                            builder.addSmsID(index + j);
//
//                        }
//
//                    }
//                    builder.setType(ERecvType.ERT_LOG);
//                    messageRev = builder.build();
//                    break;
//            }
//            long uid = Long.valueOf(SPUtils.Impl.getUid());
//            RequestPacket packet = new RequestPacket(
//                    (short) GeneraLogUtils.eC2S.C2S_USERCHATRECV_VALUE, uid,
//                    messageRev.toByteArray());
//
//            IMLoginManager.instance().getChujianSocket().sendPacket(packet);
//
//        }
//    }

//    public void onRecvMessage(byte[] contentBytes, int type) {
//        UserChatRecvRes messageRev = null;
//        UserChatCastRes messageCatRev = null;
//        UserChatRecvEnd messageRevEnd = null;
//        UserChatRes messageRes = null;
//        UserChatUpdateRes messageUpdateRes = null;
//        int offlineMessageId = SPUtils.getInt(SPUtils.Impl.OFFLINE_MESSAGE_ID, 0);
////        LogUtils.e("msg----->", type + ":" + new String(contentBytes));
//        try {
//            // 接受的消息类型
//            switch (type) {
//                // 收到拉取消息的广播
//                case IMEventType.USERCHATCASTRES:
//
//                    messageCatRev = UserChatCastRes.parseFrom(contentBytes);
//                    onSendRecvMessageRequest(ERecvType.ERT_CUR_VALUE,
//                            messageCatRev.getSmsID(0), 0);
//                    //LogUtils.e(TAG, "广播");
//                    break;
//
//                // 收到的消息
//                case IMEventType.USERCHATRECVRES:
//                    messageRev = UserChatRecvRes.parseFrom(contentBytes);
//                    if (messageRev == null) {
//                        return;
//                    }
//                    MessageEntity msg = UserChatRecvRes2Msg(messageRev);
//                    //LogUtils.e(TAG, "收到聊天消息:"+msg.getText());
//                    int msgType = msg.getTextType().getNumber();
//                    if (msgType == ETextType.ETT_TEXT_VALUE
//                            || msgType == ETextType.ETT_TIETU_VALUE
//                            || msgType == ETextType.ETT_DITU_VALUE
//                            || msgType == ETextType.ETT_GIFT_VALUE
//                            || msgType == ETextType.ETT_COMPOUND_VALUE) {
//                        msg.setAction(IMEventType.USERCHATRECVRES);
//                        pushMessage(msg);
//                    } else if (msgType == ETextType.ETT_IMAGE_VALUE
//                            || msgType == ETextType.ETT_YUYIN_VALUE) {
//                        // 检查1.是否为接收状态；2.是否存在smsid
//                        // 接收状态，smsID存在，且已经下载完毕，直接发送askserver（防止已经下载完毕但还未来得及通知服务器）
//                        int downloadStatus = MsgDBHelper.instance(ctx)
//                                .getMediaDownloadStatusBySmsId(msg.getSmsID()); // -1代表smsid不存在
//                        if (isRevType(msg) && downloadStatus == -1) {
//                            msg.setAction(IMEventType.USERCHATRECVRES);
//                            pushMessage(msg);
//                        } else {
//                            if (isRevType(msg)
//                                    && downloadStatus > Constants.MESSAGE_MEDIA_STATUS_DOWNLOADING) {
//                                IMMessageRevManager.instance().ackMsg(msg);
//                            }
//                        }
//                    }
//
//                    break;
//
//                // 消息同步接受完毕，拉离线消息的时候 每20条为一组发过来，并发送end ，如果还有在继续发送20条为一组，一直循环
//
//                case IMEventType.USERCHATRECVEND:
//                    // LogUtils.e(TAG, "聊天消息同步接收完毕");
//                    messageRevEnd = UserChatRecvEnd.parseFrom(contentBytes);
//                    int count = messageRevEnd.getSmsIDList().size();
//                    if (count > 0) {
//                        UserChatRecvItem lastMessageId = messageRevEnd
//                                .getSmsIDList().get(count - 1);
////                        LogUtils.e(TAG, "Last消息ID" + (int)
////                                lastMessageId.getSmsID());
//                        if ((int) lastMessageId.getSmsID() < offlineMessageId) {
//
//                            onSendRecvMessageRequest(ERT_CUR_AFTER_LOG_VALUE,
//                                    (int) lastMessageId.getSmsID(),
//                                    (offlineMessageId - ((int) lastMessageId.getSmsID() + 1)) + 1);
//                        }
//                    }
//                    break;
//                // 聊天结果 （发送消息成功或者失败）
//                case IMEventType.USERCHATRES:
//                    //LogUtils.e(TAG, "消息发送成功");
//                    messageRes = UserChatRes.parseFrom(contentBytes);
//                    if (messageRes == null) {
//                        return;
//                    }
//                    MessageEntity msgResult = UserChatRes2Msg(messageRes);
//                    msgResult.setAction(IMEventType.USERCHATRES);
//                    pushMessage(msgResult);
//                    break;
//
//                // 聊天结果 （你在对方黑名单里）
//                case IMEventType.USERCHAT_UPDATE_RES:
//                    // LogUtils.e(TAG, "聊天结果返回");
//                    messageUpdateRes = UserChatUpdateRes.parseFrom(contentBytes);
//                    if (messageUpdateRes == null) {
//                        return;
//                    }
//                    long fromId = messageUpdateRes.getToID();
//                    MessageHelper helper = MessageHelper.getInstance();
//                    MessageEntity floatMsg = helper.createFloatMsg(fromId, ctx.getResources().getString(R.string.refuse_receive), Constants.MESSAGE_STATE_SUCCESSED_FINISH);
//                    pushMessage(floatMsg);
//                    break;
//
//
//                //////// livecha直播聊天相关
//
//                // 创建直播聊天室返回
//                case GeneraLogUtils.eS2C.S2C_CREATE_LIVE_CHAT_ROOM_RSP_VALUE:
//                    LiveChat.CreateLiveChatRoomRsp createLiveChatRoomRsp = LiveChat.CreateLiveChatRoomRsp.parseFrom(contentBytes);
//                    if (createLiveChatRoomRsp != null) {
//                        GeneraLogUtils.eResType state = createLiveChatRoomRsp.getError();
//                        if (state == GeneraLogUtils.eResType.ERT_SUCCESS) {
//                            long live_chat_room_id = createLiveChatRoomRsp.getLiveChatRoomId();
//                            Bundle bd = new Bundle();
//                            bd.putInt("method", Constants.OBSERVABLE_ACTION_CREATE_LIVE_CHAT_ROOM_RSP);
//                            bd.putLong("live_chat_room_id", live_chat_room_id);
//                            MessageNotifyCenter.getInstance().doNotify(bd);
//                            MLog.i("创建直播聊天成功:" + live_chat_room_id);
//                        } else {
//                            MLog.i("创建直播聊天失败");
//                        }
//                    }
//
//                    break;
//
//                // 解散直播聊天室返回
//                case GeneraLogUtils.eS2C.S2C_DISSOLVE_LIVE_CHAT_ROOM_RSP_VALUE:
//
//                    break;
//                // 进入直播聊天室返回
//                case GeneraLogUtils.eS2C.S2C_ENTER_LIVE_CHAT_ROOM_RSP_VALUE:
//
//                    LiveChat.EnterLiveChatRoomRsp enterLiveChatRoomRsp = LiveChat.EnterLiveChatRoomRsp.parseFrom(contentBytes);
//                    if (enterLiveChatRoomRsp != null) {
//                        GeneraLogUtils.eResType state = enterLiveChatRoomRsp.getError();
//                        if (state == GeneraLogUtils.eResType.ERT_SUCCESS) {
//                            Bundle bd = new Bundle();
//                            bd.putInt("method", Constants.OBSERVABLE_ACTION_ENTER_LIVE_CHAT_ROOM_RSP);
//                            MessageNotifyCenter.getInstance().doNotify(bd);
//                            LogUtils.e(TAG, "进入房间成功");
//                        } else {
//                            LogUtils.e(TAG, "进入房间失败");
//                        }
//                    }
//
//                    break;
//
//                // 离开直播聊天室返回
//                case GeneraLogUtils.eS2C.S2C_LEAVE_LIVE_CHAT_ROOM_RSP_VALUE:
//                    LogUtils.e(TAG, "离开直播聊天室");
//                    break;
//
//                // 直播聊天请求返回(直播发送消息结果（聊天结果）)
//                case GeneraLogUtils.eS2C.S2C_LIVE_CHAT_RSP_VALUE:
//                    LiveChat.LiveChatRsp liveChatRsp = LiveChat.LiveChatRsp.parseFrom(contentBytes);
//                    if (liveChatRsp != null) {
//                        //LogUtils.e(TAG, "发送聊天消息结果");
//                        if (liveChatRsp.getError() == GeneraLogUtils.eResType.ERT_SUCCESS) {
//                            LogUtils.e(TAG, "发送聊天消息成功");
//                        }
//                    }
//                    break;
//
//                // 直播聊天消息
//                case GeneraLogUtils.eS2C.S2C_LIVE_CHAT_NOTIFY_VALUE:
//                    LogUtils.e(TAG, "收到消息");
//                    LiveChat.LiveChatNotify liveChatNotify = LiveChat.LiveChatNotify.parseFrom(contentBytes);
//                    if (liveChatNotify != null) {
//                        List<LiveChat.LiveChatMsg> mList = liveChatNotify.getMsgsList();
//                        for (int i = 0; i < mList.size(); i++) {
//                            LiveChat.LiveChatMsg item = mList.get(i);
//                            LiveChatMessage liveChatMsg = LiveChatMsg2LiveChatMessage(item);
//                            MessageHelper help = MessageHelper.getInstance();
//                            if (help.handleLiveChatMessage(liveChatMsg)) {
//                                liveChatMsg.setOnLineNum(liveChatNotify.getNumMemberOnline());
//                                Bundle bd = new Bundle();
////                                LogUtils.e(TAG, "收到消息" + item.getOption());
//                                bd.putInt("method", Constants.OBSERVABLE_ACTION_LIVE_CHAT_NOTIFY);
//                                bd.putSerializable("message", liveChatMsg);
//                                MessageNotifyCenter.getInstance().doNotify(bd);
//                            }
//                        }
//                    }
//                    break;
//
//                //  直播聊天室通知消息（成员进入，退出，聊天室解散）
//                case GeneraLogUtils.eS2C.S2C_LIVE_NOTIFICATION_NOTIFY_VALUE:
//                    // LogUtils.e(TAG, "收到通知消息");
//                    LiveChat.LiveNotificationNotify liveNotificationNotify = LiveChat.LiveNotificationNotify.parseFrom(contentBytes);
//                    if (liveNotificationNotify != null) {
//                        Bundle bd = new Bundle();
//                        bd.putInt("method", Constants.OBSERVABLE_ACTION_LIVE_NOTIFICATION_NOTIFY);
//                        bd.putSerializable("message", liveNotificationNotify);
//                        MessageNotifyCenter.getInstance().doNotify(bd);
//                    }
//                    break;
//                case GeneraLogUtils.eC2S.C2S_HEARTBEAT_VALUE:
//                    Bundle bd = new Bundle();
//                    bd.putInt("method", Constants.OBSERVABLE_ACTION_RECEIVED);
//                    MessageNotifyCenter.getInstance().doNotify(bd);
////                    LogUtils.e(TAG, "收到通知消息心跳消息");
//                    break;
//            }
//
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }
//    }

  @Override
  public void reset() {
    mRunning = false;
    inst = null;
  }

  /**
   * 聊天消息接收确认
   *
   * @param msg
   */
  public void ackMsg(MessageEntity msg) {

//        if (IMLoginManager.instance().getChujianSocket() != null) {
//
//            UserChatConfirm confirm = MessageInfo.UserChatConfirm.newBuilder()
//                    .addSmsID(msg.getSmsID()).setChatType(msg.getChatType())
//                    .build();
//            long uid = Long.valueOf(SPUtils.Impl.getUid());
//            RequestPacket packet = new RequestPacket(
//                    (short) GeneraLogUtils.eC2S.C2S_USERCHATCONFIRM_VALUE, uid,
//                    confirm.toByteArray());
//
//            IMLoginManager.instance().getChujianSocket().sendPacket(packet);
//        }

  }

  private void processDownload(MessageEntity msgInfo, String downloadElement) {
    MsgDBHelper.instance(ctx).saveMsg(msgInfo);
    MsgDBHelper.instance(ctx).updateElementByMsgId(msgInfo.getMsgID(),
            downloadElement, Constants.MESSAGE_MEDIA_STATUS_DOWNLOADING); // voice_read
    downloadBroadcast(msgInfo);
  }

  private void downloadBroadcast(MessageEntity msg) {
    Bundle bd = new Bundle();
    bd.putInt("method", IMEventType.ACTION_DOWNLOAD_MSG_MEDIA);
    bd.putSerializable("message", msg);
    MessageNotifyCenter.getInstance().doNotify(bd);
  }

  private void pharseVoiceUrl(String s, MessageEntity msgInfo) {
    try {
      try {
        if (s != null) {
          JSONObject json = new JSONObject(s);
          String voiceUrl = json
                  .getString(Constants.JsonChatVoice.VOICE_URL);
          int voiceTime = Float.valueOf(
                  json.getString(Constants.JsonChatVoice.VOICE_TIME))
                  .intValue();
          msgInfo.setVoiceUrl(voiceUrl);
          msgInfo.setVoiceTime(voiceTime);
          msgInfo.setMediaDownloadStatus(Constants.MESSAGE_MEDIA_STATUS_UNDOWNLOAD);
        }

      } catch (JSONException e) {
        e.printStackTrace();
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }


  /**
   * 观察者模式通知所有观察者更新
   *
   * @param action
   */
  public void broadcast(int action) {
    Bundle bd = new Bundle();
    bd.putInt("method", action);
    MessageNotifyCenter.getInstance().doNotify(bd);
  }

  public void broadcast(int action, MessageEntity msg) {
    Bundle bd = new Bundle();
    bd.putInt("method", action);
    bd.putSerializable("message", msg);
    MessageNotifyCenter.getInstance().doNotify(bd);
  }

  private boolean isRevType(MessageEntity msg) {
    return (msg.getFromID() == Long.valueOf(Long.valueOf(SPUtils.Impl.getUid()))) ? false : true;
  }


  /**
   * 接受消息队列
   *
   * @param msg
   */
  public void pushMessage(MessageEntity msg) {
    if (msg != null) {
      synchronized (mLockCoreMsg) {
        mCoreMsgsList.add(msg);
      }
    }
  }

  /**
   * 接受消息队列
   */
  private class ThreadMsgsProc extends Thread {
    @Override
    public void run() {
      MessageEntity coreMsg;
      while (mRunning) {
        coreMsg = null;
        synchronized (mLockCoreMsg) {
          if (mCoreMsgsList.size() > 0) {
            coreMsg = mCoreMsgsList.remove(0);
          }
        }
        if (null != coreMsg) {

          Log.i("LiveChatMessage", "coreMsg.getAction() = " + coreMsg.getAction());

          continue;
        }

        try {
          Thread.sleep(30);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      synchronized (mLockCoreMsg) {
        if (mCoreMsgsList != null) {
          mCoreMsgsList.clear();
          mCoreMsgsList = null;
        }
      }
      mLockCoreMsg = null;

    }
  }


  /**
   * 停止接受消息
   */
  public void stopImpl() {
    mRunning = false;
    try {
      mProcessMsg.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


}