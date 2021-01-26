package com.jk.jkproject.net.okhttp.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.net.okhttp.progress.ProgressInfo;
import com.jk.jkproject.net.okhttp.progress.listener.ProgressListener;
import com.jk.jkproject.utils.SPUtils;


public abstract class BaseResponseHandler extends Handler {

    public final static int START = 0x1;

    public final static int UPDATE = START << 1;

    public final static int SUCCESS = UPDATE << 1;

    public final static int FAILURE = SUCCESS << 1;

    public boolean isFast;

//    private final static int CANCEL = FAILURE << 1;

//    protected final WeakReference<ResponseListener> mResponseListener;

//    protected WeakReference<ProgressListener> mProgressListener;

    protected ResponseListener mResponse;
    protected ProgressListener mProgress;

    protected String mBaseUrl;

    public BaseResponseHandler(String baseUrl, ResponseListener responseListener) {
        super(Looper.getMainLooper());
        this.mBaseUrl = baseUrl;
        mResponse = responseListener;
    }

    public BaseResponseHandler(String baseUrl, ResponseListener responseListener, ProgressListener progressListener) {
        this(baseUrl, responseListener);
        this.mProgress = progressListener;
    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case START:
                if (SPUtils.getUserId().isEmpty()) {
                    mResponse.onFailure(30000, mBaseUrl, "");
                }
                if (!isFast) {
                    mResponse.onStartRequest();
                    isFast = !isFast;
                }
                break;
            case UPDATE:
                isFast = false;
                if (mProgress != null) {
                    try {
                        ProgressInfo info = (ProgressInfo) msg.obj;
                        if (info != null) {
                            mProgress.onProgress(info.getCurrentBytes(), info.getContentLength(), "", info.isDone());
                        }
                    } catch (Exception e) {
                        LogUtils.e("BaseResponseHandler", "error", e);
                    }
                }
                break;
            case SUCCESS:
                isFast = false;
                if (mResponse != null) {
                    mResponse.onSuccess(mBaseUrl, msg.obj);
                    LogUtils.e("onSuccess,url=", mBaseUrl);
                    LogUtils.e("onSuccess,obj=", msg.obj.toString());
                }
                break;
            case FAILURE:
                isFast = false;
                if (mResponse != null) {
                    String error = null;
                    if (msg.obj != null) {
                        error = msg.obj.toString();
                    }
                    mResponse.onFailure(msg.arg1, mBaseUrl, error);
                    LogUtils.e("onFailure=" + mBaseUrl, error);
                }
                break;
        }
    }

}
