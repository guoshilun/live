package com.jk.jkproject.ui.entity;

import java.util.List;

public class LiveRoomUserLockList extends BaseInfo {
    /**
     * data : {"msgList":[{"anchorGrade":"1","userGrade":1,"sex":"0","nickname":"小姐姐03","sgin":"这个人非常懒，什么都没有留下！！！","userId":"1020870","picture":"http://sl.file.diaodiaosocial.com/1595552613912.png"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<LiveRoomUserLockBean> msgList;

        public List<LiveRoomUserLockBean> getMsgList() {
            return msgList;
        }

        public void setMsgList(List<LiveRoomUserLockBean> msgList) {
            this.msgList = msgList;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "msgList=" + msgList +
                    '}';
        }
    }
}
