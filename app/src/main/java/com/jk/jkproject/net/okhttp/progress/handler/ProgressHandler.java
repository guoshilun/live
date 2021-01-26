package com.jk.jkproject.net.okhttp.progress.handler;

import android.os.Message;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.net.okhttp.base.BaseResponseHandler;
import com.jk.jkproject.net.okhttp.progress.ProgressInfo;
import com.jk.jkproject.net.okhttp.progress.listener.ProgressListener;

public class ProgressHandler extends BaseResponseHandler implements ProgressListener {
  public ProgressHandler(String paramString, ResponseListener paramResponseListener, ProgressListener paramProgressListener) {
    super(paramString, paramResponseListener, paramProgressListener);
  }
  
  public void onProgress(long paramLong1, long paramLong2, String paramString, boolean paramBoolean) {
    ProgressInfo progressInfo;
    Message message = obtainMessage(2);
    if (message.obj == null) {
      progressInfo = new ProgressInfo(paramLong1, paramLong2, paramBoolean);
    } else {
      progressInfo = (ProgressInfo)message.obj;
      progressInfo.setCurrentBytes(paramLong1);
      progressInfo.setContentLength(paramLong2);
      progressInfo.setDone(paramBoolean);
    } 
    message.obj = progressInfo;
    sendMessage(message);
  }
}