package com.jk.jkproject.ui.entity;

import java.io.Serializable;
import java.util.List;

public class LiveRoomBeanList extends BaseInfo implements Serializable{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LiveRoomBeanList{" +
                "data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        /**
         * cover : http://qn.zbjlife.cn/c8c4bbab3bbc71ae0df847748d273504.png
         * id : 3
         * roomId : 5
         */

        private String cover;
        private int id;
        private String roomId;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "cover='" + cover + '\'' +
                    ", id=" + id +
                    ", roomId='" + roomId + '\'' +
                    '}';
        }
    }
}
