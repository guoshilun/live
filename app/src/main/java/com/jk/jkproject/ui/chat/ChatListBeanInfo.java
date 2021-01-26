package com.jk.jkproject.ui.chat;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.ui.entity.BaseInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatListBeanInfo extends BaseInfo {


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
        private List<String> conversationList = new ArrayList<>();

        private ArrayList<Message> messageList;

        public ArrayList<Message> getUserList() {
            if (messageList == null) {
                return setUserList();
            }
            return messageList;
        }


        public List<String> getConversationList() {
            return conversationList;
        }

        public void setConversationList(List<String> conversationList) {
            LogUtils.e("setUserList=4=",conversationList.size());
            this.conversationList = conversationList;
        }

        public ArrayList<Message> setUserList() {
            if (conversationList != null && conversationList.size() > 0) {
                LogUtils.e("setUserList=1",conversationList.size());
                messageList = new ArrayList<>();
                for (int b = 0; b < conversationList.size(); b++) {
                    try {
                        JSONObject jSONObject = new JSONObject(conversationList.get(b));
                        Message message = new Message();
                        LogUtils.e("setUserList=2",jSONObject.getString("msgId"));
                        message.setUuid(jSONObject.getString("msgId"));
                        message.setMsgId(jSONObject.getString("msgId"));
                        switch (jSONObject.getInt("msgType")) {
                            case 0://普通消息
                                message.setMsgType(MsgType.TEXT);
                                TextMsgBody body = new TextMsgBody();
                                body.setMessage(jSONObject.getString("body"));
                                message.setBody(body);
                                break;
                        }
                        message.setContent(jSONObject.getString("body"));
                        message.setCount(jSONObject.getInt("count"));
                        message.setSentTime(jSONObject.getLong("time"));
                        message.setUserName(jSONObject.getString("username"));
                        message.setUserId(jSONObject.getString("target"));
                        message.setTargetId(jSONObject.getString("userId"));
                        message.setPicture(jSONObject.getString("picture"));
                        messageList.add(message);
                    } catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                }
            }
            return messageList;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "conversationList=" + conversationList +
                    ", messageList=" + messageList +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "ChatListBeanInfo{" +
                "data=" + data +
                '}';
    }
}
