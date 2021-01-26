package com.jk.jkproject.net.okhttp;

import android.os.Message;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.net.ResponseFactory;
import com.jk.jkproject.net.okhttp.base.BaseResponseHandler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.jk.jkproject.net.okhttp.base.BaseResponseHandler.FAILURE;
import static com.jk.jkproject.net.okhttp.base.BaseResponseHandler.START;
import static com.jk.jkproject.net.okhttp.base.BaseResponseHandler.SUCCESS;


/**
 * Created by zhaotun on 15/11/3.
 */
public class RequestTask implements Runnable {

    private static final String TAG = "RequestTask";

    private String mBaseUrl;
    private OkHttpClient mClient;
    private Request mRequest;
    private BaseResponseHandler mHandler;
    private Class<?> mCls;
    private long startTime = 0;

    private String mOid;

    public RequestTask(String baseUrl, OkHttpClient client, Request request, BaseResponseHandler handler) {
        this.mBaseUrl = baseUrl;
        this.mClient = client;
        this.mRequest = request;
        this.mHandler = handler;
    }

    public RequestTask(String baseUrl, OkHttpClient client, Request request, BaseResponseHandler handler, Class<?> cls) {
        this.mBaseUrl = baseUrl;
        this.mClient = client;
        this.mRequest = request;
        this.mHandler = handler;
        this.mCls = cls;
    }

    public RequestTask(String baseUrl, OkHttpClient client, Request request, BaseResponseHandler handler, Class<?> cls, String oid) {
        this.mBaseUrl = baseUrl;
        this.mClient = client;
        this.mRequest = request;
        this.mHandler = handler;
        this.mCls = cls;
        this.mOid = oid;
    }


    @Override
    public void run() {

        // start
        LogUtils.d("request start");

        sendMessage(START, -1, null);
        try {
            startTime = System.currentTimeMillis();
            mClient.newCall(mRequest).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    // fail
                    LogUtils.e("request url : " + mBaseUrl);
                    LogUtils.e("request time : " + (System.currentTimeMillis() - startTime));
                    LogUtils.e(TAG, "onFailure : " + e.getMessage());
                    fail(HttpConstants.FAILURE_CODE_DEFAULT, HttpConstants.DEFAULT_ERROR);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
//                    Logger.d("request url : " + mBaseUrl);
                    LogUtils.e("request time : " + (System.currentTimeMillis() - startTime));
                    if (response == null) {
                        // fail
                        LogUtils.e(TAG, "onResponse : response is null");
                        fail(HttpConstants.FAILURE_CODE_DEFAULT, HttpConstants.DEFAULT_ERROR);
                        return;
                    }

//                    int code = response.code();

                    String responseString = "without response body";
                    if (response.body() != null) {
                        responseString = response.body().string();
//                        try {
//                            // 用于格式化日期,作为日志文件名的一部分
//                            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//                            responseString = GsonUtils.get().format(responseString);
//                            StringBuffer sb = new StringBuffer();
//                            sb.append(responseString);
//                            long timestamp = System.currentTimeMillis();
//                            String time = formatter.format(new Date());
//                            String fileName = "api" + time + "-" + timestamp + ".log";
//                            if (Environment.getExternalStorageState().equals(
//                                    Environment.MEDIA_MOUNTED)) {
//                                String path = crashFile;
//                                File dir = new File(path);
//                                if (!dir.exists()) {
//                                    dir.mkdirs();
//                                }
//                                FileOutputStream fos = new FileOutputStream(path + fileName);
//                                fos.write(sb.toString().getBytes());
//                                fos.close();
//                            }
//
//                        } catch (Exception e) {
////                            Log.e("tag", "an error occured while writing file...", e);
//                        }
                    }
//                    Logger.json( responseString);
//
//                    L.syso("response : " + responseString);

                    if (response.isSuccessful()) {
                        Object obj;
                        if (mCls != null) {
                            long time = System.currentTimeMillis();
                            obj = ResponseFactory.handleResponse(responseString, mCls);
                            LogUtils.e("responseString====" + responseString);
                            LogUtils.e("parse json time : " + (System.currentTimeMillis() - time));
                        } else {
                            obj = responseString;
                        }

                        if (obj != null) {
                            sendMessage(SUCCESS, -1, obj);
                        } else {
                            LogUtils.e(TAG, "response parse error : " + response.toString());
                            fail(HttpConstants.FAILURE_CODE_PARSE_ERR, "parse error");
                        }
                    } else {
                        // fail

                        LogUtils.e(TAG, "response fail : " + response.toString());
                        fail(HttpConstants.FAILURE_CODE_DEFAULT, "response fail");
                    }
                }

            });
        } catch (Throwable e) {
            e.printStackTrace();

            // fail
            LogUtils.e("request url : " + mBaseUrl);
            LogUtils.e(TAG, "response error : " + e.getMessage());
            fail(HttpConstants.FAILURE_CODE_DEFAULT, "response error");
        }

    }

    private void fail(int code, Object obj) {
//        if (Urls.FEED_LIKE.equals(mBaseUrl)) {
//            sendMessage(FAILURE, code, mOid);
//        } else {
            sendMessage(FAILURE, code, obj);
//        }
    }

    private void sendMessage(int what, int code, Object obj) {
        if (mHandler != null) {
            Message msg = mHandler.obtainMessage(what);
            msg.arg1 = code;
            msg.obj = obj;

            msg.sendToTarget();
        }
    }
}
