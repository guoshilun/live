package com.jk.jkproject.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.okhttp.HttpConstants;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.activity.LoginActivity;
import com.jk.jkproject.ui.dialog.DialogLoading;
import com.jk.jkproject.ui.dialog.DialogProgress;
import com.jk.jkproject.ui.entity.BaseInfo;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 类用途：
 * 最后修改：
 */
public abstract class BFragment extends Fragment implements ResponseListener {

    protected Context mContext;
    protected Activity mActivity;

    protected View mRootView;
    protected boolean mFlagShowDefaultFailToast = true;
    protected boolean mFlagShowParseFailToast = true;
    protected boolean mFlagShowNoNetworkToast = true;
    String[] baseUrlArr = null;
    private Unbinder unbinder;
    private DialogProgress loadingDialog;
    /**
     * Fragment当前状态是否可见
     */
//    protected boolean isVisible;
    //只用来判断
    private String baseApiUrl = "";
    private boolean isCanUpdate = true;
    private int postion = 0;
    private boolean isVisible;
    private DialogLoading loading;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mActivity = activity;
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    @Subscribe
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        if (isBindEventBusHere()) {
            LogUtils.e("====");
            EventBus.getDefault().register(this);
        }
        initView(view);
        initData();
        setListener();
//        initLoadingDialog();
    }

    private void initLoadingDialog() {
        loading = new DialogLoading(mContext);
    }

    public DialogLoading getLoading() {
        return loading;
    }

    @Override
    public void onStartRequest() {
//        if (!loading.isShowing()) {
//            loading.show();
//        }
    }

    @Override
    public void onSuccess(String url, Object obj) {
//        loading.dismiss();
        if (this == null || getActivity() == null || getActivity().isFinishing() || obj == null) {
            return;
        }
        if (obj instanceof BaseInfo) {
            BaseInfo baseInfo = (BaseInfo) obj;
            if (baseInfo.getCode() == -1) {
                ToastUtils.showShortToast("登录验证失败");
                AppApplication.getInstance().logOut(getContext());
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
//        loading.dismiss();
        if (this == null || getActivity() == null || getActivity().isFinishing()) {
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

    public View getRootView() {
        return mRootView;
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


    protected void showLoadingDialog(int msgId) {
        showLoadingDialog(getString(msgId));
    }

    protected void showLoadingDialog(String res) {
        if (loadingDialog == null) {
            loadingDialog = new DialogProgress(getActivity());
        }
        loadingDialog.show();
        loadingDialog.setMsg(res);
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


    /**
     * 是否绑定eventBus
     */
    @Subscribe
    protected boolean isBindEventBusHere() {
        return false;
    }

    /**
     * 接受EventBus 广播
     */
    @Subscribe
    public void onEvent(PostResult postResult) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    protected abstract int setLayoutId();


    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * view与数据绑定
     */
    protected void initView(View view) {

    }

    /**
     * 设置监听
     */
    protected void setListener() {

    }


    /**
     * 可见
     */
    protected void onVisible() {

    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    protected <E extends View> E getView(View rootView, int resViewId) {
        try {
            if (rootView != null) {
                return (E) rootView.findViewById(resViewId);
            } else {
                throw new NullPointerException("root view is null, can not find view");
            }
        } catch (Throwable e) {
            throw e;
        }
    }
}