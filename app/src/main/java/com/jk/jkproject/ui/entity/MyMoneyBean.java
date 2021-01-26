package com.jk.jkproject.ui.entity;

public class MyMoneyBean extends BaseInfo {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int amount3;//钻石 (充值金额)
        private int amount2;//金钻 (主播可提现的金额 )
        private int amount1;//邀请收益的金钻( 可以提现 与 amount2 意义相同)

        @Override
        public String toString() {
            return "DataBean{" +
                    "amount3='" + amount3 + '\'' +
                    ", amount2='" + amount2 + '\'' +
                    ", amount1='" + amount1 + '\'' +
                    '}';
        }

        public int getAmount3() {
            return amount3;
        }

        public void setAmount3(int amount3) {
            this.amount3 = amount3;
        }

        public int getAmount2() {
            return amount2;
        }

        public void setAmount2(int amount2) {
            this.amount2 = amount2;
        }

        public int getAmount1() {
            return amount1;
        }

        public void setAmount1(int amount1) {
            this.amount1 = amount1;
        }
    }

    @Override
    public String toString() {
        return "MyMoneyBean{" +
                "data=" + data +
                '}';
    }
}
