package com.jk.jkproject.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jk.jkproject.R;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, AppApplication.APP_ID_WX);
        api.handleIntent(getIntent(), this);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                ToastUtils.showShortToast("支付成功");
                break;
            case -1:
                ToastUtils.showShortToast("签名错误");
                break;
            case -2:
                ToastUtils.showShortToast("支付取消");
                break;
        }
    }
}