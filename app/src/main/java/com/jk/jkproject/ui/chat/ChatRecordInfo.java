package com.jk.jkproject.ui.chat;

import com.jk.jkproject.ui.entity.BaseInfo;

import java.util.List;

/**
 * @author Zick
 * @params
 * @date 2020/7/15 9:38 AM
 * @desc 聊天记录
 */
public class ChatRecordInfo extends BaseInfo {
    /**
     * data : {"conversationList":["{\"msgType\":0,\"count\":1,\"msgId\":\"24768\",\"time\":1601370527098,\"body\":\"嗯呢[/难过]\",\"userId\":\"1020820\",\"picture\":\"http://qn.zbjlife.cn/1020820.png\",\"target\":\"1020839\",\"username\":\"Kae的直播\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"20935\",\"time\":1600241015128,\"body\":\"啦咯啦咯啦咯啦\",\"userId\":\"1020870\",\"picture\":\"http://qn.zbjlife.cn/1020870.png\",\"target\":\"1020839\",\"username\":\"小姐姐03\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"20871\",\"time\":\"1600237833863\",\"body\":\"我是一条关注自动回复消息\",\"userId\":\"1020819\",\"picture\":\"http://qn.zbjlife.cn/1020819.png\",\"target\":\"1020839\",\"username\":\"Quokka\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"20870\",\"time\":\"1600237820950\",\"body\":\"滚滚滚滚滚滚\",\"userId\":\"1020821\",\"picture\":\"http://qn.zbjlife.cn/1020821.png\",\"target\":\"1020839\",\"username\":\"嘎嘣脆\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"20705\",\"time\":\"1600157788101\",\"body\":\"谢谢关注哦1\",\"userId\":\"1020931\",\"picture\":\"http://qn.zbjlife.cn/default-cover.png\",\"target\":\"1020839\",\"username\":\"啊\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"11439\",\"time\":\"1598260166647\",\"body\":\"穿越号官方方法干活好吧\",\"userId\":\"1020844\",\"picture\":\"http://sl.file.diaodiaosocial.com/1595552613912.png\",\"target\":\"1020839\",\"username\":\"用户1020844\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"11438\",\"time\":\"1598260162050\",\"body\":\"啦啦啦\",\"userId\":\"1020862\",\"picture\":\"http://thirdqq.qlogo.cn/g?b=oidb&k=2l8Cxnv0jO9UdGVSZ5DoIA&s=640&t=1557299259\",\"target\":\"1020839\",\"username\":\"深入骨髓 自然萌\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"5776\",\"time\":1597285156709,\"body\":\"法国红酒更多\",\"userId\":\"1020845\",\"picture\":\"http://qn.zbjlife.cn/1020845.png\",\"target\":\"1020839\",\"username\":\"老朱的苹果\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"5225\",\"time\":\"1597043616863\",\"body\":\"-1\",\"userId\":\"1020843\",\"picture\":\"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI34A8lRUxHPEzAz1N9pFBjoobKvzMMUzyXVA7v89qwib6Mb0OrYaaQCtIbkCkklFPeWswhC2alMvg/132\",\"target\":\"1020839\",\"username\":\"老朱\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"5189\",\"time\":\"1597039179756\",\"body\":\"-1\",\"userId\":\"1020840\",\"picture\":\"http://qn.zbjlife.cn/1020840.png\",\"target\":\"1020839\",\"username\":\"用户1020840\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"3037\",\"time\":1596696948701,\"body\":\"Hjj\",\"userId\":\"1020822\",\"picture\":\"http://qn.zbjlife.cn/1603353693142.png\",\"target\":\"1020839\",\"username\":\"arrow \"}","{\"msgType\":0,\"count\":0,\"msgId\":\"3016\",\"time\":\"1596679783081\",\"body\":\"-1\",\"userId\":\"1020830\",\"picture\":\"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3CWPrANicepyUSMDBCpNHoUdFy6SmPtIzOicqEjoKBE7vOXuwX3icKKwibBSmyUXJnWaDKZjGdt9upA/132\",\"target\":\"1020839\",\"username\":\"小姐姐01\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"3014\",\"time\":\"1596625132184\",\"body\":\"-1\",\"userId\":\"1020829\",\"picture\":\"http://sl.file.diaodiaosocial.com/1595552613912.png\",\"target\":\"1020839\",\"username\":\"小姐姐02\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"2951\",\"time\":\"1596615809249\",\"body\":\"-1\",\"userId\":\"1020832\",\"picture\":\"http://qn.zbjlife.cn/1020832.png\",\"target\":\"1020839\",\"username\":\"用户1020832\"}"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> conversationList;

        public List<String> getConversationList() {
            return conversationList;
        }

        public void setConversationList(List<String> conversationList) {
            this.conversationList = conversationList;
        }
    }


//    /**
//     * data : {"conversationList":["{\"msgType\":0,\"count\":0,\"msgId\":\"1897\",\"time\":1594711600205,\"body\":\"[/流泪][/流泪][/流泪]r\",\"userName\":\"赤い\",\"userId\":\"1000002\",\"picture\":\"http://qn.zbjlife.cn/58f43ffe6ccab14ad4d47688ae9e08fb.png\",\"target\":\"1000035\"}"]}
//     */
//
//    private DataBean data;
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        private List<String> msgList;
//
//        private ArrayList<Message> messageList;
//        private Message message;
//
//        public ArrayList<Message> getUserList() {
//            return setUserList();
//        }
//
//        public List<String> getConversationList() {
//            return msgList;
//        }
//
//        public void setConversationList(List<String> msgList) {
//            this.msgList = msgList;
//        }
//
//        public ArrayList<Message> setUserList() {
//            if (msgList != null && msgList.size() > 0) {
//                messageList = new ArrayList<>();
//                long msgTime = -1;
//                for (int b = 0; b < msgList.size(); b++) {
//                    GsonUtils.get().fromJson(msgList.get(b), Message.class);
//                }
//            }
//            return messageList;
//        }
//
//        public ArrayList<Message> getReverseMessage() {
//            if (messageList != null && messageList.size() > 0)
//                Collections.sort(messageList, (o1, o2) -> {
//                    if (o1.getSentTime() > o2.getSentTime()) {
//                        return 1;
//                    } else if (o1.getSentTime() == o2.getSentTime()) {
//                        return 0;
//                    } else {
//                        return -1;
//                    }
//                });
//            return messageList;
//        }
//    }



//    {"msg":"成功","code":200,"data":{"conversationList":["{\"msgType\":0,\"count\":1,\"msgId\":\"24768\",\"time\":1601370527098,\"body\":\"嗯呢[/难过]\",\"userId\":\"1020820\",\"picture\":\"http://qn.zbjlife.cn/1020820.png\",\"target\":\"1020839\",\"username\":\"Kae的直播\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"20935\",\"time\":1600241015128,\"body\":\"啦咯啦咯啦咯啦\",\"userId\":\"1020870\",\"picture\":\"http://qn.zbjlife.cn/1020870.png\",\"target\":\"1020839\",\"username\":\"小姐姐03\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"20871\",\"time\":\"1600237833863\",\"body\":\"我是一条关注自动回复消息\",\"userId\":\"1020819\",\"picture\":\"http://qn.zbjlife.cn/1020819.png\",\"target\":\"1020839\",\"username\":\"Quokka\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"20870\",\"time\":\"1600237820950\",\"body\":\"滚滚滚滚滚滚\",\"userId\":\"1020821\",\"picture\":\"http://qn.zbjlife.cn/1020821.png\",\"target\":\"1020839\",\"username\":\"嘎嘣脆\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"20705\",\"time\":\"1600157788101\",\"body\":\"谢谢关注哦1\",\"userId\":\"1020931\",\"picture\":\"http://qn.zbjlife.cn/default-cover.png\",\"target\":\"1020839\",\"username\":\"啊\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"11439\",\"time\":\"1598260166647\",\"body\":\"穿越号官方方法干活好吧\",\"userId\":\"1020844\",\"picture\":\"http://sl.file.diaodiaosocial.com/1595552613912.png\",\"target\":\"1020839\",\"username\":\"用户1020844\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"11438\",\"time\":\"1598260162050\",\"body\":\"啦啦啦\",\"userId\":\"1020862\",\"picture\":\"http://thirdqq.qlogo.cn/g?b=oidb&k=2l8Cxnv0jO9UdGVSZ5DoIA&s=640&t=1557299259\",\"target\":\"1020839\",\"username\":\"深入骨髓 自然萌\"}","{\"msgType\":0,\"count\":1,\"msgId\":\"5776\",\"time\":1597285156709,\"body\":\"法国红酒更多\",\"userId\":\"1020845\",\"picture\":\"http://qn.zbjlife.cn/1020845.png\",\"target\":\"1020839\",\"username\":\"老朱的苹果\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"5225\",\"time\":\"1597043616863\",\"body\":\"-1\",\"userId\":\"1020843\",\"picture\":\"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI34A8lRUxHPEzAz1N9pFBjoobKvzMMUzyXVA7v89qwib6Mb0OrYaaQCtIbkCkklFPeWswhC2alMvg/132\",\"target\":\"1020839\",\"username\":\"老朱\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"5189\",\"time\":\"1597039179756\",\"body\":\"-1\",\"userId\":\"1020840\",\"picture\":\"http://qn.zbjlife.cn/1020840.png\",\"target\":\"1020839\",\"username\":\"用户1020840\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"3037\",\"time\":1596696948701,\"body\":\"Hjj\",\"userId\":\"1020822\",\"picture\":\"http://qn.zbjlife.cn/1603353693142.png\",\"target\":\"1020839\",\"username\":\"arrow \"}","{\"msgType\":0,\"count\":0,\"msgId\":\"3016\",\"time\":\"1596679783081\",\"body\":\"-1\",\"userId\":\"1020830\",\"picture\":\"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL3CWPrANicepyUSMDBCpNHoUdFy6SmPtIzOicqEjoKBE7vOXuwX3icKKwibBSmyUXJnWaDKZjGdt9upA/132\",\"target\":\"1020839\",\"username\":\"小姐姐01\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"3014\",\"time\":\"1596625132184\",\"body\":\"-1\",\"userId\":\"1020829\",\"picture\":\"http://sl.file.diaodiaosocial.com/1595552613912.png\",\"target\":\"1020839\",\"username\":\"小姐姐02\"}","{\"msgType\":0,\"count\":0,\"msgId\":\"2951\",\"time\":\"1596615809249\",\"body\":\"-1\",\"userId\":\"1020832\",\"picture\":\"http://qn.zbjlife.cn/1020832.png\",\"target\":\"1020839\",\"username\":\"用户1020832\"}"]}}
}
