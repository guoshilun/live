package com.jk.jkproject.ui.chat;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.jk.jkproject.ui.entity.BaseInfo;
import com.jk.jkproject.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zick
 * @params
 * @date 2020/7/15 9:38 AM
 * @desc 聊天记录
 */
public class ChatRecordBeanInfo extends BaseInfo {


    /**
     * data : {"conversationList":["{\"msgType\":0,\"count\":0,\"msgId\":\"1897\",\"time\":1594711600205,\"body\":\"[/流泪][/流泪][/流泪]r\",\"userName\":\"赤い\",\"userId\":\"1000002\",\"picture\":\"http://qn.zbjlife.cn/58f43ffe6ccab14ad4d47688ae9e08fb.png\",\"target\":\"1000035\"}"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> msgList;

        private ArrayList<Message> messageList;

        public ArrayList<Message> getUserList() {
            return setUserList();
        }

        public List<String> getConversationList() {
            return msgList;
        }

        public void setConversationList(List<String> msgList) {
            this.msgList = msgList;
        }

        public ArrayList<Message> setUserList() {
            if (msgList != null && msgList.size() > 0) {
                messageList = new ArrayList<>();
                long msgTime = -1;
                for (int b = 0; b < msgList.size(); b++) {
                    try {
                        JSONObject jSONObject = new JSONObject(msgList.get(b));
                        Message message = new Message();
                        if (jSONObject.getInt("msgType") == Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_0 || (jSONObject.getInt("msgType") >= 51 && jSONObject.getInt("msgType") <= 66)) {

                            message.setUuid(jSONObject.getString("msgId"));
                            message.setMsgId(jSONObject.getString("msgId"));
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
                            if (jSONObject.getInt("msgType") == 0) {
                                message.setMsgType(MsgType.TEXT);
                                TextMsgBody body = new TextMsgBody();
                                body.setMessage(jSONObject.getString("body"));
                                message.setBody(body);
                                message.setContent(jSONObject.getString("body"));
                                message.setCount(jSONObject.getInt("count"));
                                message.setSentTime(jSONObject.getLong("time"));
                                message.setUserName(jSONObject.getString("username"));
                                message.setUserId(jSONObject.getString("userId"));
                                message.setTargetId(jSONObject.getString("target"));
                                if (!message.getUserId().equals("1")) {
                                    message.setPicture(jSONObject.getString("picture"));
                                }
                            } else if (jSONObject.getInt("msgType") >= 51 && jSONObject.getInt("msgType") <= 66) {
                                message.setMsgType(MsgType.ORDER);
                                TextMsgBody body = new TextMsgBody();
                                body.setMessage(jSONObject.getString("body"));
                                message.setBody(body);
                                message.setSentTime(jSONObject.getLong("time"));
                                message.setContent(jSONObject.getString("body"));
                                message.setUserId(jSONObject.getString("userId"));
                            }

                            if (msgTime == -1) {
                                message.setShowTime(true);
                                msgTime = message.getSentTime();
                            } else {
                                long timeSpan = TimeUtils.getTimeSpan(msgTime, message.getSentTime(), TimeConstants.MIN);
                                if (timeSpan >= 3) {
                                    message.setShowTime(true);
                                    msgTime = message.getSentTime();
                                } else {
                                    message.setShowTime(false);
                                }
                            }
                            messageList.add(message);
                        }
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                }
            }
            return messageList;
        }

        public ArrayList<Message> getReverseMessage() {
            if (messageList != null && messageList.size() > 0)
                Collections.sort(messageList, (o1, o2) -> {
                    if (o1.getSentTime() > o2.getSentTime()) {
                        return 1;
                    } else if (o1.getSentTime() == o2.getSentTime()) {
                        return 0;
                    } else {
                        return -1;
                    }
                });
            return messageList;
        }
    }
}
