package com.jk.jkproject.utils;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.ui.entity.pay.AliPayResult;
import com.jk.jkproject.ui.entity.pay.AppPayInfo;
import com.jk.jkproject.ui.entity.pay.PayListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PayUtils {
    private static final int ZFB_PAY_FLAG = 1;
    private static final int WX_PAY_FLAG = 2;
    public static final String state = "wechat_sdk_userinfo_login";
    private static int SDK_PAY_FLAG = 1;

    /**
     * @param payInfo     支付参数
     * @param activity    需要调用的Activity
     * @param payListener 支付监听
     */
    public static void payZFB(final String payInfo, final Activity activity, final PayListener payListener) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask PayInfo = new PayTask(activity);
                Map<String, String> result = PayInfo.payV2(payInfo, true);
                LogUtils.e("支付支付返回信息：" + result.toString());
                AliPayResult payResult = new AliPayResult(result);
                if (payResult.getResultStatus().equals("9000")) {
                    payListener.onPaySuccess(ZFB_PAY_FLAG, payResult.getResult());
                } else if (payResult.getResultStatus().equals("6001")) {
                    //支付取消
                    payListener.onPayCancel(ZFB_PAY_FLAG, payResult.getResult());
                } else {
                    payListener.onPayFailure(ZFB_PAY_FLAG, payResult.getResult());
                }
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * @param wxPayBean 微信支付参数
     * @param activity  需要调用的Activity
     */
    public static void payWX(final AppPayInfo.DataBean.WxBean wxPayBean, final Activity activity) {
        LogUtils.e("result=" + wxPayBean.toString());
        final IWXAPI payApi = WXAPIFactory.createWXAPI(activity, null);
        PayReq request = new PayReq();
        payApi.registerApp(AppApplication.APP_ID_WX);
        request.appId = wxPayBean.getAppid();
        request.partnerId = wxPayBean.getPartnerid();
        request.prepayId = wxPayBean.getPrepayid();
        request.packageValue = wxPayBean.get_package();
        request.nonceStr = wxPayBean.getNoncestr();
        request.timeStamp = wxPayBean.getTimestamp() + "";
        request.sign = wxPayBean.getSign();
//        payApi.sendReq(setWx(request));
        payApi.sendReq(request);
    }

    private static PayReq setWx(PayReq req) {
        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
        req.sign = genAppSign(signParams);
        return req;
    }

    private static String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(AppApplication.API_KEY_WX);
        sb.append("sign str\n" + sb.toString() + "\n\n");
        return MD5Tools.toMD5(sb.toString());
    }
}
