package com.jk.jkproject.ui.activity;

import android.webkit.JavascriptInterface;

import com.blankj.utilcode.util.LogUtils;

public class AndroidtoJs extends Object {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public static interface OnClickListener {
        void onBack();

        void onClick(String roomId);

        void onUserClick(String userId);
    }

    private int mType;

    public AndroidtoJs() {
    }

    public AndroidtoJs(int mType) {
        this.mType = mType;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        LogUtils.e("=========" + msg);
//        if (onClickListener != null) {
//            onClickListener.onBack();
//        }
    }


    @JavascriptInterface
    public void back() {
        System.out.println("返回");
        if (onClickListener != null) {
            onClickListener.onBack();
        }
    }


    @JavascriptInterface
    public void joinRoom(String roomId) {
        LogUtils.e("roomId=", roomId);
        if (onClickListener != null) {
            onClickListener.onClick(roomId);
        }
    }

    @JavascriptInterface
    public void enterHomePage(String userId) {
        LogUtils.e("userId=", userId);
        if (onClickListener != null) {
            onClickListener.onUserClick(userId);
        }
    }
}
