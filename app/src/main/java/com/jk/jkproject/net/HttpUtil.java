package com.jk.jkproject.net;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.net.okhttp.RequestParams;
import com.jk.jkproject.net.okhttp.progress.listener.UploadListener;

import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

/**
 * Author: CJ_Xiongj
 * Data: 2016/1/18 11:37
 * Description:
 */
public class HttpUtil {

    private volatile static HttpUtil util;

    private HttpUtil() {
    }

    public static HttpUtil get() {
        if (null == util) {
            synchronized (HttpUtil.class) {
                if (null == util) util = new HttpUtil();
            }
        }
        return util;
    }


    public void sendFile(String host, final String urlPath, String filePath, RequestParams params, final String messId, final
    UploadListener listener) {

        LogUtils.e("host:" + host + "    urlPath::" + urlPath + "   filePath:" + filePath + "params::" + params);
        AsyncTask<Object, Long, String> task = new AsyncTask<Object, Long, String>() {

            @Override
            protected String doInBackground(Object... p) {
                try {
                    if (p == null) return null;
                    String file = (String) p[0];
                    String ul = (String) p[1];
                    String ht = (String) p[2];
                    RequestParams rp = (RequestParams) p[3];

                    File f = new File(file);
                    if (!f.exists()) {
                        return null;
                    }
                    String end = "\r\n";
                    String twoHyphens = "--";
                    String boundary = UUID.randomUUID().toString();

                    URL url = new URL(ul);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setUseCaches(false);
                    con.setConnectTimeout(10000);
                    con.setRequestMethod("POST");
                    //以下三行设置http请求头信息
                    con.setRequestProperty("Host", ht);// "cjfeed.oss-cn-hangzhou.aliyuncs.com");
                    con.setRequestProperty("Connection", "Keep-Alive");
                    con.setRequestProperty("Accept-Encoding", "identity");
                    con.setRequestProperty("Charset", "UTF-8");
                    con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary + end);
                    long start = System.currentTimeMillis();
                    DataOutputStream ds = new DataOutputStream(con.getOutputStream());//
                    ds.writeBytes(twoHyphens + boundary + end);
                    for (HashMap.Entry<String, String> entry : rp.getParams().entrySet()) {
                        ds.writeBytes("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + end);
                        ds.writeBytes(end);
                        ds.writeBytes(entry.getValue() + end);
                        ds.writeBytes(twoHyphens + boundary + end);
                    }
//                    L.i(file+"===" + url + "上传中："+rp);
                    rp.clear();
                    //上传文件相关信息，这些信息包括请求参数名，上传文件名，文件类型，但并不限于此
                    ds.writeBytes("Content-Disposition: form-data; " + "name=\"file\"; filename=\"" + f.getName() +
                            "\"" + end);
                    ds.writeBytes("Content-Type: video/mp4" + end);
                    ds.writeBytes(end);

                    //获得文件的输入流，通过流传输文件。在这里我重写了FileInputStream，为了监听上传进度
                    CustomFileInputStream fStream = new CustomFileInputStream(file);
//                    fStream.setOnUploadListener(listener);
                    long total = fStream.getTotal();
                    /* 设置每次写入1024bytes */
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    int length = -1;
                    long count = 0;
                    // 从文件读取数据至缓冲区
                    while ((length = fStream.read(buffer)) != -1) {
                        //将资料写入DataOutputStream中
                        count += length;
                        ds.write(buffer, 0, length);
//                        L.syso(count +"----total---"+total);
                        publishProgress(count, total);
                    }
                    ds.writeBytes(end);
                    ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

                    fStream.close();
                    ds.flush();

                    //上传完成以后获取服务器的反馈
                    if (con.getResponseCode() == 200) {
                        InputStream is = con.getInputStream();
                        if (is == null) {
                            LogUtils.e(200 + con.getResponseMessage());
                            return null;
                        }
                        int ch;
                        StringBuffer b = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                            b.append((char) ch);
                        }
                        ds.close();
                        LogUtils.e("上传图片时间：" + (System.currentTimeMillis() - start) + b.toString());

                        return b.toString();
                    } else {
                        LogUtils.e(con.getResponseCode() + con.getResponseMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.e(e.getMessage());
                } catch (OutOfMemoryError oom) {
                    LogUtils.e(oom.getMessage());
                } catch (Throwable t) {
                    LogUtils.e(t.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String b) {
                super.onPostExecute(b);
                if (!TextUtils.isEmpty(b) && b.length() > 0) {
                    if (listener != null) {
                        listener.onSuccess(urlPath + messId, b);
                    }
                } else {
                    if (listener != null)
                        listener.onFailure(0, urlPath + messId, b);
                }
            }

            @Override
            protected void onProgressUpdate(Long... values) {
                super.onProgressUpdate(values);
                if (listener != null)
                    listener.onProgress(values[0], values[1], "", values[0] == values[1]);
            }
        };
        Object[] paramsObj = new Object[4];
        paramsObj[0] = filePath;
        paramsObj[1] = urlPath;
        paramsObj[2] = host;
        paramsObj[3] = params.clone();
        task.execute(paramsObj);
    }

}


