package com.jk.jkproject.ui.entity;

public class PKRecordValueBaseInfo extends BaseInfo {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PKRecordValueBaseInfo{" +
                "data=" + data +
                '}';
    }

    public static class DataBean {
        private int maxVictory; //最大连胜数
        private int money; //收益
        private int count; //场次

        public int getMaxVictory() {
            return maxVictory;
        }

        public void setMaxVictory(int maxVictory) {
            this.maxVictory = maxVictory;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "maxVictory=" + maxVictory +
                    ", money=" + money +
                    ", count=" + count +
                    '}';
        }
    }
}
