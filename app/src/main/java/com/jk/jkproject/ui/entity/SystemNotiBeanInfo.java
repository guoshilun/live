package com.jk.jkproject.ui.entity;

import java.io.Serializable;
import java.util.List;

public class SystemNotiBeanInfo extends BaseInfo implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * create_time : 1594925833000
         * type : 1
         * message : {"title":"名侦探柯南"}
         */

        private long create_time;
        private int type;
        private String message;
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "create_time=" + create_time +
                    ", type=" + type +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SystemNotiBeanInfo{" +
                "data=" + data +
                '}';
    }
}
