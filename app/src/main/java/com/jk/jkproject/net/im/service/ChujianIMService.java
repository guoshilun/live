package com.jk.jkproject.net.im.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.net.im.manager.IMLoginManager;
import com.jk.jkproject.utils.Constants;

import java.util.Timer;
import java.util.TimerTask;


public class ChujianIMService extends Service {

    private static final String TAG = "ChujianIMService";
    private BaseServiceReceiver imReceiver = null;
    private boolean isFirstRegister = false;
    private final int HEARTBEAT_INTERVAL = 1000;
    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        // startForeground((int) System.currentTimeMillis(), new
        // Notification());

        Context ctx = getApplicationContext();
        // 连接、登录
        IMLoginManager.instance().setContext(ctx);
        IMLoginManager.instance().scheduleHeartbeat(HEARTBEAT_INTERVAL);


        MessageNotifyCenter.getInstance();
        IMLoginManager.instance().connectImServer();

//        收消息
//        IMMessageRevManager.instance().setContext(ctx);
        //发消息
//        IMMessageSendManager.instance().setContext(ctx);

        //直播间收发消息
        IMLiveRoomManager.instance().setContext(ctx);

        // 注册一个监听闹钟和网络变化的广播
        imReceiver = new BaseServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_SENDING_HEARTBEAT);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        isFirstRegister = true;
        registerReceiver(imReceiver, filter);
        timer = new Timer();
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startServiceEnd();
//        flags = START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterActions(this);
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void unregisterActions(Context ctx) {

        try {
            ctx.unregisterReceiver(imReceiver);
        } catch (IllegalArgumentException exception) {
        }

    }

    class BaseServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.ACTION_SENDING_HEARTBEAT)) {
                if (timer != null) {
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            IMLoginManager.instance().handleSendingHeartbeart();
                        }
                    }, 0, 5000);
                }
//                IMLoginManager.instance().handleSendingHeartbeart();
            } else if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                //第一次动态注册广播就会收到 CONNECTIVITY_ACTION
                if (!isFirstRegister) {
                    LogUtils.e("tryReconnectServer=2", "重连服务器");
                    IMLoginManager.instance().tryReconnectServer();
                    Bundle bd = new Bundle();
                    bd.putInt("method", IMEventType.ACTION_NET_WORK_CHANGE);
                    MessageNotifyCenter.getInstance().doNotify(bd);
                } else {
                    isFirstRegister = false;
                }
            }
        }

    }

    // 启动IMService结束后广播
    public void startServiceEnd() {
        Bundle bd = new Bundle();
        bd.putInt("method", IMEventType.ACTION_START_IM_SERVICE_END);
        MessageNotifyCenter.getInstance().doNotify(bd);
    }
}
