package com.jk.jkproject.utils;

import android.util.Log;

import com.jk.jkproject.net.okhttp.ResponseListener;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Date;

public class QiNiuFileUpdownUtils {

    private UploadManager uploadManager;

    private volatile static QiNiuFileUpdownUtils util;

    private QiNiuFileUpdownUtils() {
    }

    public static QiNiuFileUpdownUtils get() {
        if (null == util) {
            synchronized (QiNiuFileUpdownUtils.class) {
                if (null == util) util = new QiNiuFileUpdownUtils();
            }
        }
        return util;
    }


    public void upload(final int position, String uploadToken, String uploadFilePath, final ResponseListener listener) {
        //可以自定义zone
        //Zone zone = new FixedZone(new String[]{"domain1","domain2"});

        //手动指定上传区域
        //Zone zone = FixedZone.zone0;//华东

        //config配置上传参数
        Configuration configuration = new Configuration.Builder()
                .connectTimeout(10)
                //.zone(zone)
                //.dns(buildDefaultDns())//指定dns服务器
                .responseTimeout(60).build();

        if (this.uploadManager == null) {
            //this.uploadManager = new UploadManager(fileRecorder);
            this.uploadManager = new UploadManager(configuration, 3);
        }

        UploadOptions opt = new UploadOptions(null, null, true, new UpProgressHandler() {
            @Override
            public void progress(String key, double percent) {

            }
        }, null);

        File uploadFile = new File(uploadFilePath);
        long time = new Date().getTime();
        String picName = time + ".png";

        this.uploadManager.put(uploadFile, picName, uploadToken,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo respInfo,
                                         JSONObject jsonData) {
                        long endTime = System.currentTimeMillis();
                        if (respInfo.isOK()) {
                            try {
                                Log.e("zw", jsonData.toString() + respInfo.toString());
                                String fileKey = jsonData.getString("key");
                                String fileHash = jsonData.getString("hash");
                                listener.onSuccess(position + "", "/" + fileKey);
                            } catch (JSONException e) {
                                if (jsonData != null) {
                                    Log.e("====", jsonData.toString());
                                }
                            }
                        } else {
                            listener.onFailure(respInfo.statusCode, respInfo.host, respInfo.error);
                            Log.e("====", respInfo.toString());
                            if (jsonData != null) {
                                Log.e("====", jsonData.toString());
                            }
                            Log.e("====", "--------------------------------" + "\n上传失败");
                        }
                    }

                }, opt);
    }

}
