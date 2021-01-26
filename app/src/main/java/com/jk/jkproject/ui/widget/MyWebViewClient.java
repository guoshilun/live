package com.jk.jkproject.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.jk.jkproject.ui.inter.WebViewCallBack;

import java.util.HashMap;

/**
 * Created by Ade on 2017/1/11.
 */

public class MyWebViewClient extends WebViewClient {
    private Context context;
    private WebViewCallBack webViewCallBack;

    public MyWebViewClient(Context context, WebViewCallBack webViewCallBack) {
        this.context = context;
        this.webViewCallBack = webViewCallBack;
    }

    public void release() {
        context = null;
        webViewCallBack = null;
    }

    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, String url) {
        if (!TextUtils.isEmpty(url) && url.contains("liehuo://VIPGuide")) {
            if (context instanceof Activity) {
//                ((WebActivity) context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent vipIn = new Intent(context, VipActivity.class);
//                        vipIn.putExtra("vip", Constants.VIP.VIP_WEB_VIP);
//                        context.startActivity(vipIn);
//                    }
//                });
            }
        } else if (!TextUtils.isEmpty(url) && url.contains("liehuo://PurchaseDiamonds")) {
            if (context instanceof Activity) {
//                ((Activity) context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent vipIn = new Intent(context, TopUpDiamondActivity.class);
//                        vipIn.putExtra("buy_source", Constants.VIP.VIP_WEB_VIP);
//                        context.startActivity(vipIn);
//                    }
//                });
            }
        } else if (!TextUtils.isEmpty(url) && url.contains("liehuo://UserInfo?uid=")) {
            final String uid = url.substring("liehuo://UserInfo?uid=".length());
            if (context instanceof Activity) {
//                ((WebActivity) context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent vipIn = new Intent(context, UserInfoActivity.class);
//                        vipIn.putExtra("vid", uid);
//                        context.startActivity(vipIn);
//                    }
//                });
            }
        } else if (!TextUtils.isEmpty(url) && url.contains("liehuo://callfunc")) {
            String jsonString = url.substring("liehuo://callfunc?".length());
            try {
                Gson gson = new Gson();
//                final Request request = gson.fromJson(jsonString, Request.class);
//                MLog.d("request=" + request.toString());
//                if (request != null) {
//                    try {
//                        if (TextUtils.equals("openLiveGiftTab", request.funcName)) {
//                            Intent intent = new Intent(OPEN_GIFTDIALOG);
//                            String strUTF8 = URLDecoder.decode(request.params.tab_name, "UTF-8");
//                            intent.putExtra("tab_name", strUTF8);
//                            context.sendBroadcast(intent);
//                            if (context instanceof Activity)
//                                ((Activity) context).finish();
//                        } else if (TextUtils.equals("transverse", request.funcName)) {
//                            webViewCallBack.onTransverse();
//                        } else if (TextUtils.equals("openWeb", request.funcName)) {
////                            MLog.d(">>>>>>>>>>>request" + request.params != null + ">>>" + !TextUtils.isEmpty(request.params.url));
//                            if (request.params != null && !TextUtils.isEmpty(request.params.url)) {
//                                Intent achievement = new Intent(context, WebActivity.class);
//                                achievement.putExtra("url", request.params.url);
//                                if (!TextUtils.isEmpty(request.params.title)) {
//                                    achievement.putExtra("title", URLDecoder.decode(request.params.title, "UTF-8"));
//                                }
//                                context.startActivity(achievement);
//                            }
//                        } else if (TextUtils.equals("transverse_back", request.funcName)) {
//                            if (context instanceof Activity) {
//                                ((WebActivity) context).finish();
//                                if (LiveEndUtil.isOpen) {
//                                    LiveEndUtil.goToAct((WebActivity) context);
//                                }
//                            }
//                        } else if (TextUtils.equals("openBrowser", request.funcName)) {
//                            Uri uri = Uri.parse(request.params.url);
//                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                            context.startActivity(intent);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (!TextUtils.isEmpty(url) && url.startsWith("http:") || url.startsWith("https:")) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Referer", view.getOriginalUrl());
            view.loadUrl(url, hashMap);
        } else {
            try {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            } catch (Exception e) {
            }
        }
        return true;
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if (webViewCallBack != null) {
            webViewCallBack.onShowFailView();
        }
    }
}
