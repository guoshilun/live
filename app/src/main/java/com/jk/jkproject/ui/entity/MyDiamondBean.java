package com.jk.jkproject.ui.entity;

public class MyDiamondBean extends BaseInfo {
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    private DataBean data;


    public static class DataBean {

        private String wx;
        private int pay_type;// 0支付宝 1 微信 2 IOS
        private String ali;//跳转网页链接

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getAli() {
            return ali;
        }

        public void setAli(String ali) {
            this.ali = ali;
        }
    }

    @Override
    public String toString() {
        return "MyMoneyBean{" +
                "data=" + data +
                '}';
    }
}
