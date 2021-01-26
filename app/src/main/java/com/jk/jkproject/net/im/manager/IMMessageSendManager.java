package com.jk.jkproject.net.im.manager;

import android.os.Bundle;

import com.jk.jkproject.net.im.RequestPacket;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.info.MessageEntity;
import com.jk.jkproject.net.im.netty.ChujianSocketThread;


public class IMMessageSendManager extends ChujianManager {

  private static IMMessageSendManager inst;
  private String tag = "IMMessageSendManager";

  public static IMMessageSendManager instance() {
    if (inst == null) {
      synchronized (IMMessageSendManager.class) {
        if (inst == null) {
          inst = new IMMessageSendManager();
        }
      }
    }

    return inst;
  }
  /**
   * @param msgInfo
   * @param
   * @author zjy
   */
  public void sendText(MessageEntity msgInfo) {
    if (msgInfo != null) {
//            UserChat chat = UserChat
//                    .newBuilder()
//                    .setToID(msgInfo.getToID())
//                    .setTextType(msgInfo.getTextType())
//                    .setIndex(msgInfo.getMsgID())
//                    .setChatType(EChatType.ECT_NORMAL)
//                    .setTextCss(msgInfo.getBubble())
//                    .setText(msgInfo.getText()).build();
//            RequestPacket packet = new RequestPacket(
//                    (short) GeneraLogUtils.eC2S.C2S_USERCHAT_VALUE,
//                    msgInfo.getFromID(), chat.toByteArray());
//            sendMessage(msgInfo, packet);
    }
  }


  /**
   * 发送 snap
   *
   * @param msgInfo
   */

  public void sendSnapText(MessageEntity msgInfo, boolean batched) {
    if (msgInfo != null) {
//            MessageInfo.ChatTarget chatTarget = MessageInfo.ChatTarget
//                    .newBuilder()
//                    .setType(MessageInfo.ChatTarget.Type.TYPE_ALL_MATCHED_VALUE)
//                    .build();
//            UserChat chat = UserChat
//                    .newBuilder()
//                    .setToID(msgInfo.getToID())
//                    .setTextType(msgInfo.getTextType())
//                    .setIndex(msgInfo.getMsgID())
//                    .setChatType(EChatType.ECT_SNAP)
//                    //.setTextCss(msgInfo.getBubble())
//                    .setBatched(batched)
//                    .setTarget(chatTarget)
//                    .setText(msgInfo.getText()).build();
//            RequestPacket packet = new RequestPacket(
//                    (short) GeneraLogUtils.eC2S.C2S_USERCHAT_VALUE,
//                    msgInfo.getFromID(), chat.toByteArray());
//            sendSnapMessage(msgInfo, packet,!batched);
    }
  }


  /**
   * 发送消息
   *
   * @param msgInfo
   * @param packet
   */
  private void sendMessage(MessageEntity msgInfo, RequestPacket packet) {
    // 保存数据库并且更新消息列表最后一条信息的
//        if (msgInfo.getTextType() == ETextType.ETT_TEXT
//                || msgInfo.getTextType() == ETextType.ETT_TIETU
//                || msgInfo.getTextType() == ETextType.ETT_DITU
//                || msgInfo.getTextType() == ETextType.ETT_GIFT
//                || msgInfo.getTextType() == ETextType.ETT_COMPOUND
//                ) {
//            handleSendMsg(msgInfo);
//        } else {
//            updateContextByMsgId(msgInfo);
//        }
//
//        sendMessage2Server(msgInfo, packet);
  }

  /**
   * 发送瞬间消息
   * @param update 是否要更新snap消息（如果snap消息没有插入数据库就不需要更新）
   * @param msgInfo
   * @param packet
   */
  private void sendSnapMessage(MessageEntity msgInfo, RequestPacket packet,boolean update) {
    // 保存数据库并且更新消息列表最后一条信息的
    if(update) {
      updateImage2Snap(msgInfo);
    }
    sendMessage2Server(msgInfo, packet);
  }


  /**
   * 发送到服务器
   *
   * @param msgInfo
   * @param packet
   */
  private void sendMessage2Server(MessageEntity msgInfo, RequestPacket packet) {
    // 加入超时监视
//        if (msgInfo.getMsgID() != null) {
//            Action.Builder builer = new Action.Builder();
//            Action action = builer.setPacket(packet).setMessage(msgInfo)
//                    .setCallback(null).build();
//            IMSendMonitorManager.instance().add2MonitorList(msgInfo.getMsgID(),
//                    action);
//        }
//        // 向服务端发送消息
//        sendMessagePacket(packet);
  }


  /**
   * 向服务器发送消息包
   *
   * @param packet
   */
  public void sendMessagePacket(RequestPacket packet) {
    ChujianSocketThread messageSocket = IMLoginManager.instance()
            .getChujianSocket();
    if (messageSocket != null) {
      messageSocket.sendPacket(packet);
    } else {
      IMLoginManager.instance().tryReconnectServer();
    }
  }

  /**
   * 上传图片后更新消息的 text
   *
   * @param msgInfo
   * @author xuxs
   */
  private void updateContextByMsgId(MessageEntity msgInfo) {
//        IMRecentSessionManager.instance().updateSession(msgInfo);
//        broadcast(IMEventType.ACTION_SEND_MESSAGE, msgInfo);
//        MsgDBHelper.instance(ctx).updateElementByMsgId(msgInfo.getMsgID(),
//                "content", msgInfo.getText());
  }

  /**
   * 上传图片后更新消息成瞬间格式
   *
   * @param msgInfo
   */
  private void updateImage2Snap(MessageEntity msgInfo) {
//        IMRecentSessionManager.instance().updateSession(msgInfo);
//        broadcast(IMEventType.ACTION_SEND_MESSAGE, msgInfo);
//        MsgDBHelper.instance(ctx).updateImage2Snap(msgInfo.getMsgID(), msgInfo.getText(),
//                EChatType.ECT_SNAP.getNumber(), ETextType.ETT_COMPOUND.getNumber(), msgInfo.getDisplayType());
  }


  /**
   * @param msgInfo
   * @author xuxs
   */
  public void updateStatusByMsgId(MessageEntity msgInfo) {
//        IMRecentSessionManager.instance().updateSession(msgInfo);
//        broadcast(IMEventType.ACTION_SEND_MESSAGE, msgInfo);
//        MsgDBHelper.instance(ctx).updateElementByMsgId(msgInfo.getMsgID(),
//                "status", msgInfo.getMessageState());
  }


  /**
   * 处理发送的消息
   *
   * @param msgInfo
   */
  public void handleSendMsg(MessageEntity msgInfo) {
    // msgInfo.setMessageState(Constants.MESSAGE_STATE_LOADDING); //by xiaosong.xu
//        IMRecentSessionManager.instance().updateSession(msgInfo);
//        broadcast(IMEventType.ACTION_SEND_MESSAGE, msgInfo);
    // save to db
//        MsgDBHelper.instance(ctx).saveMsg(msgInfo);
  }

  public void broadcast(int action, MessageEntity msg) {
    Bundle bd = new Bundle();
    bd.putInt("method", action);
    bd.putSerializable("message", msg);
    MessageNotifyCenter.getInstance().doNotify(bd);
  }

  @Override
  public void reset() {
    inst = null;
  }


}
