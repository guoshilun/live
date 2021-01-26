package com.jk.jkproject.ui.entity.pay;

public class AppPayInfo {


    /**
     * msg : 成功
     * code : 200
     * data : {"pay_type":0,"ali":{"app_id":"2021001165604654","biz_content":"{\"body\":\"JK直播钻石购买\",\"out_trade_no\":\"2020110515561716826978559962337\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"subject\":\"JK直播钻石购买\",\"total_amount\":\"0.01\"}","charset":"utf-8","format":"json","method":"alipay.trade.app.pay","notify_url":"http://www.baidu.com","sign":"Xrj+KqfroXvGhbKf2FR5320mo1CEea6DRl1GtxZN+8WqVnFav3jFNTw27bcz8/nfS1/SIjJZ9qOH+0hd9A34GCvAg0fQJZFaEv7m5b1Dj9IwHRQrGdWmvPqf7C1or8yeQ1wgM9qDJp+1ijn20Yx+Uf26aGv0uDO691Nicpm2Tw2yNVsu4GrkT18go4c8PNDuocWJsFt4jjqOFww0qr/tQeYUBVkAg+n/pvhTs+883mVYngt+50Jx5/6NXFc1UaWMnz3Q1/T8gPimBpWTroTOzwKpiEACxtBNnP5oyTJ82636OaAxLZ/2mzkjeySWSU24665BC6NXqSOzxA4veV3cTQ==","sign_type":"RSA2","timestamp":"2020-11-05 15:56:17","version":"1.0"}}
     */

    private String msg;
    private Integer code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pay_type : 0
         * ali : {"app_id":"2021001165604654","biz_content":"{\"body\":\"JK直播钻石购买\",\"out_trade_no\":\"2020110515561716826978559962337\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"subject\":\"JK直播钻石购买\",\"total_amount\":\"0.01\"}","charset":"utf-8","format":"json","method":"alipay.trade.app.pay","notify_url":"http://www.baidu.com","sign":"Xrj+KqfroXvGhbKf2FR5320mo1CEea6DRl1GtxZN+8WqVnFav3jFNTw27bcz8/nfS1/SIjJZ9qOH+0hd9A34GCvAg0fQJZFaEv7m5b1Dj9IwHRQrGdWmvPqf7C1or8yeQ1wgM9qDJp+1ijn20Yx+Uf26aGv0uDO691Nicpm2Tw2yNVsu4GrkT18go4c8PNDuocWJsFt4jjqOFww0qr/tQeYUBVkAg+n/pvhTs+883mVYngt+50Jx5/6NXFc1UaWMnz3Q1/T8gPimBpWTroTOzwKpiEACxtBNnP5oyTJ82636OaAxLZ/2mzkjeySWSU24665BC6NXqSOzxA4veV3cTQ==","sign_type":"RSA2","timestamp":"2020-11-05 15:56:17","version":"1.0"}
         */

        private Integer pay_type; //0.支付宝 1.微信
        private String ali;
        private WxBean wx;

        public Integer getPay_type() {
            return pay_type;
        }

        public void setPay_type(Integer pay_type) {
            this.pay_type = pay_type;
        }

        public String getAli() {
            return ali;
        }

        public void setAli(String ali) {
            this.ali = ali;
        }

        public WxBean getWx() {
            return wx;
        }

        public void setWx(WxBean wx) {
            this.wx = wx;
        }

        public static class WxBean {
            private String appid;
            private String noncestr;
            private String _package;
            private String partnerid;
            private String prepayid;
            private String sign;
            private long timestamp;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String get_package() {
                return _package;
            }

            public void set_package(String _package) {
                this._package = _package;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            @Override
            public String toString() {
                return "WxBean{" +
                        "appid='" + appid + '\'' +
                        ", noncestr='" + noncestr + '\'' +
                        ", _package='" + _package + '\'' +
                        ", partnerid='" + partnerid + '\'' +
                        ", prepayid='" + prepayid + '\'' +
                        ", sign='" + sign + '\'' +
                        ", timestamp=" + timestamp +
                        '}';
            }
        }
    }
}
