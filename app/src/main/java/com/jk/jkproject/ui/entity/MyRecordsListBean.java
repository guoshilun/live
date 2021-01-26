package com.jk.jkproject.ui.entity;

import java.util.List;

public class MyRecordsListBean extends BaseInfo {

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    private List<DataBean> data;



    public static class DataBean {

        private int amount;
        private long u_dateTime;
        private int id;
        private int status;
        private String conversion_count;


        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public long getU_dateTime() {
            return u_dateTime;
        }

        public void setU_dateTime(int u_dateTime) {
            this.u_dateTime = u_dateTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getConversion_count() {
            return conversion_count;
        }

        public void setConversion_count(String conversion_count) {
            this.conversion_count = conversion_count;
        }
    }

    @Override
    public String toString() {
        return "MyMoneyBean{" +
                "data=" + data +
                '}';
    }
}
