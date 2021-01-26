package com.jk.jkproject.ui.entity.pay;


public interface PayListener {
    void onPaySuccess(int type, String result);

    void onPayFailure(int type, String error);

    void onPayCancel(int type, String cancel);

}
