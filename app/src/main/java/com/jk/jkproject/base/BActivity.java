package com.jk.jkproject.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.http.HttpResponseCache;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.LogUtils;
import com.fansan.common.OrderPopup;
import com.fansan.common.pojo.OrderPushBean;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.okhttp.HttpConstants;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.activity.LoginActivity;
import com.jk.jkproject.ui.chat.LiveMessageActivity;
import com.jk.jkproject.ui.chat.Message;
import com.jk.jkproject.ui.dialog.DialogLoading;
import com.jk.jkproject.ui.dialog.DialogProgress;
import com.jk.jkproject.ui.entity.BaseInfo;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.yuyan.statusbar.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Observable;
import java.util.Observer;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import razerdp.basepopup.BasePopupSDK;

import static com.jk.jkproject.net.Urls.USER_LOGIN;

/**
 * Created by zhaotun on 15/11/3.
 */
public abstract class BActivity extends FragmentActivity implements ResponseListener {

    protected boolean mFlagShowDefaultFailToast = true;
    protected boolean mFlagShowParseFailToast   = true;
    protected boolean mFlagShowNoNetworkToast   = true;
    String[] baseUrlArr    = null;
    long     lastClickTime = 0;
    private DialogProgress loadingDialog;
    private int            postion     = 0;
    private boolean        isCanUpdate = true;
    private String         baseApiUrl  = "";
    private DialogLoading  loading;
    public boolean isStop = false;


    @Override
    protected void onStart() {
        super.onStart();
        BusUtils.register(this);
        //        initLoadingDialog();
    }

    @Override
    @Subscribe
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        initTitle();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 禁用横屏
        AppApplication.getInstance().addActvity(this);
        //        ((AppApplication) getApplication()).initWorkerThread();
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        if (isBindEventBusHere()) {
            LogUtils.e("====");
            EventBus.getDefault().register(this);
        }

        if (isHideVirtual()) {
            setHideVirtual();
        }
    }

    private void setHideVirtual() {
        //显示虚拟按键
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            //低版本sdk
            View v = getWindow().getDecorView();
            v.setSystemUiVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void initLoadingDialog() {
        loading = new DialogLoading(this);
    }


    private void initTitle() {
        Window window = getWindow();
        View decorView = window.getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 是否绑定eventBus
     */
    @Subscribe
    public boolean isBindEventBusHere() {
        return false;
    }

    public boolean isHideVirtual() {
        return false;
    }

    /**
     * 接受EventBus 广播
     */
    @Subscribe
    public void onEvent(PostResult postResult) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        isStop = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppApplication.getInstance().removeActvity(this);
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }

        BusUtils.unregister(this);
    }

    @Override
    public void onStartRequest() {
        //        if (loading != null && !loading.isShowing()) {
        //            loading.show();
        //        }
    }

    @Override
    public void onSuccess(String url, Object obj) {
        //        loading.dismiss();
        if (this == null || this.isFinishing() || obj == null) {
            return;
        }
        if (obj instanceof BaseInfo) {
            BaseInfo baseInfo = (BaseInfo) obj;
            if (baseInfo.getCode() == -1) {
                ToastUtils.showShortToast("登录验证失败");
                AppApplication.getInstance().logOut(this);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        //        loading.dismiss();
        if (url.equals(USER_LOGIN) ||
                url.equals(Urls.CODE_LOGIN) ||
                url.equals(Urls.SET_WX_LOGIN) ||
                url.equals(Urls.SET_QQ_LOGIN)) {
        } else {
            if (code == 30000) {
                LogUtils.e("没有登录");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
        if (this == null || this.isFinishing()) {
            return;
        }
        if (url.equals(baseApiUrl)) {
            isCanUpdate = true;
        }
        if (postion > 10) {
            return;
        }
        postion++;
        switch (code) {
            case HttpConstants.FAILURE_CODE_DEFAULT:// 正常请求失败
                if (mFlagShowDefaultFailToast) {
                    ToastUtils.showShortToast("网络请求出错,请稍后再试");
                }
                break;
            case HttpConstants.FAILURE_CODE_NETWORK_NO:// 无网络
                if (mFlagShowNoNetworkToast) {
                    ToastUtils.showShortToast("检测到未连接网络,请设置!");
                }
                break;
            case HttpConstants.FAILURE_CODE_PARSE_ERR:// 数据格式错误
                if (mFlagShowParseFailToast) {
                    ToastUtils.showShortToast(R.string.data_format_wrong);
                }
                break;
        }
    }

    /**
     * Cancels all scheduled tasks tagged with tag.
     * Requests that are already complete cannot be canceled.
     *
     * @param url the tag of HTTP request which used the request url.
     */
    protected void cancel(String url) {
        AppApis.cancel(url);
    }

    protected <E extends View> E getView(int resViewId) {
        try {
            return (E) findViewById(resViewId);
        } catch (ClassCastException e) {
            LogUtils.e("Could not cast View to concrete class", e);
            throw e;
        }
    }

    protected <E extends View> E getView(View rootView, int resViewId) {
        try {
            if (rootView != null) {
                return (E) rootView.findViewById(resViewId);
            } else {
                throw new NullPointerException("root view is null, can not find view");
            }
        } catch (Throwable e) {
            LogUtils.e("Could not cast View to concrete class", e);

            throw e;
        }
    }

    protected void showLoadingDialog(int msgId) {
        showLoadingDialog(getString(msgId));
    }

    protected void showLoadingDialog(String res) {
        if (this == null || this.isFinishing()) {
            return;
        }
        try {
            if (loadingDialog == null) {
                loadingDialog = new DialogProgress(this);
            }
            if (!loadingDialog.isShowing())
                loadingDialog.show();
            loadingDialog.setMsg(res);
        } catch (Exception e) {
        }
    }

    protected void showLoadingDialog() {
        showLoadingDialog(R.string.get_data);
    }

    protected void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.hide();
        }
    }

    protected void dismissLoadingDialog() {
        try {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }

    public void replaceFragment(@IdRes int resId, Fragment fragment) {
        replaceFragment(resId, fragment, false);
    }

    public void replaceFragment(@IdRes int resId, Fragment fragment, boolean isBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(resId, fragment);
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
                return true;
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && time <= 2000) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        HttpResponseCache cache = HttpResponseCache.getInstalled();
        if (cache != null) {
            cache.flush();
        }

        isStop = true;
    }




    @BusUtils.Bus(tag = "orderPush", threadMode = BusUtils.ThreadMode.MAIN)
    public void orderPushReceive(OrderPushBean bean) {
        if (isStop){
            return;
        }
        OrderPopup orderPopup = new OrderPopup(this, bean);
        orderPopup.setContactCallback((s, s2) -> {
            Message bean1 = new Message();
            bean1.setUserId(SPUtils.getUserId());
            bean1.setTargetId(s);
            bean1.setUserName(s2);
            Intent intent3 = new Intent(BActivity.this, LiveMessageActivity.class);
            intent3.putExtra("type", 3);
            intent3.putExtra("message", bean1);
            startActivity(intent3);
            return null;
        });
        orderPopup.showPopupWindow();
    }
}
