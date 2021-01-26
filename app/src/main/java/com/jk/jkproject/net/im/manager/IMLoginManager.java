package com.jk.jkproject.net.im.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.RequestPacket;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.info.ConnectByProto;
import com.jk.jkproject.net.im.info.PushUserRealNameStateProto;
import com.jk.jkproject.net.im.netty.ChujianSocketThread;
import com.jk.jkproject.net.im.netty.MsgServerHandler;
import com.jk.jkproject.ui.activity.LoginActivity;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.SPUtils;

public class IMLoginManager extends ChujianManager {
    private static String HOST_IP = Urls.IM_SERVER_IP;

    private static String HOST_PORT = Urls.IM_SERVER_port;

    private static final String TAG = "ChujianIMSendManager";

    private static IMLoginManager inst;

    private int CONNECTIONSCOKET = 0;

    private ChujianSocketThread connectServerThread;
    private RequestPacket requestPacket;

    Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (!SPUtils.getIP().isEmpty()) {
                        tryReconnectServer();
                    }
                    break;
            }
        }
    };

    private String ipUrl = Urls.IM_SERVER_IP;
    private PendingIntent pi = null;
    private int retry = 0;
    private long sendMsgTime = 0L;
    private boolean isNetWork = true;

    public static IMLoginManager instance() {
        synchronized (IMLoginManager.class) {
            if (inst == null) {
                inst = new IMLoginManager();
            }
            return inst;
        }
    }


    private void removelHeartbeatTimer() {
        if (pi == null) {
            return;
        }
        AlarmManager am = (AlarmManager) AppApplication.getInstance()
                .getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }

    private void startConnect() {
        if (connectServerThread == null && SPUtils.getIP() != null && SPUtils.getProt() != null) {
            connectServerThread = new ChujianSocketThread(SPUtils.getIP(), Integer.parseInt(SPUtils.getProt()), new MsgServerHandler());
            connectServerThread.setName("connectServerThread");
            connectServerThread.start();
        }
    }


    public void broadcast(int paramInt) {
        Bundle bundle = new Bundle();
        bundle.putInt("method", paramInt);
        MessageNotifyCenter.getInstance().doNotify(bundle);
    }


    public void connectImServer() {
        startConnect();
    }

    public ChujianSocketThread getChujianSocket() {
        return connectServerThread;
    }

    public void handleSendingHeartbeart() {
        if (connectServerThread != null) {
            if (System.currentTimeMillis() - sendMsgTime >= 10000 || sendMsgTime == 0) {
                if (requestPacket == null) {
                    requestPacket = new RequestPacket((short) 5, "", (short) 2, "".getBytes());
                }
                sendMsgTime = System.currentTimeMillis();
                LogUtils.e("心跳包=发送");
                retry++;
                connectServerThread.sendPacket(requestPacket);
                onFailed(true);
//                if (!connectServerThread.sendPacket(requestPacket)) {
//                    onFailed(true);
//                }
            }
        } else {
            onFailed(true);
        }
        if (!NetworkUtils.isConnected()) {
            if (isNetWork) {
                isNetWork = false;
                broadcast(IMEventType.ACTION_IS_CLEAR_DATA);
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        } else {
            isNetWork = true;
        }
    }

    public void resetHeartbeart() {
        retry = 0;
    }

    public void onFailed(boolean paramBoolean) {
        if (paramBoolean) {
            if (retry <= 9) {
                if (retry % 3 == 0 && handler != null) {
                    broadcast(IMEventType.ACTION_IS_CLEAR_DATA);
                    handler.sendEmptyMessageDelayed(1, 2000);
                }
            } else {
                retry = 0;
                if (ctx != null) {
                    AppApplication.getInstance().logOut(ctx);
                    Intent intent = new Intent(ctx, LoginActivity.class);
                    intent.putExtra("type", 2);
                    ctx.startActivity(intent);
                    LogUtils.e("长连接失败");
                }
            }
        }
    }

    public void onMsgServerConnected() {
        String str = SPUtils.getToken();
        if (this.connectServerThread != null && !str.equals("")) {
            RequestPacket requestPacket = new RequestPacket((short) 3, "proxy.ConnectByProto", (short) 2, ConnectByProto.ConnectByProtoReq.newBuilder().setToken(str).build().toByteArray());
            this.connectServerThread.sendPacket(requestPacket);
        }
    }

    /**
     * 服务器断开回调函数
     */
    public void onMsgServerDisconnected() {
        LogUtils.e("IMLogin", "onMsgServerDisconnected");
//        onFailed(true);
    }

    /**
     * 断开服务器后进行sorcket操作后抛出异常函数的回调 在这里可以在有网络的情况下进行服务器重连
     */
    public void onMsgServerUnconnected() {
        LogUtils.e("IMLogin", "onMsgServerUnconnected");
//        onFailed(true);
    }

    public void onSuccess() {
        if (handler != null) {
            handler.removeMessages(this.CONNECTIONSCOKET);
        }
        this.retry = 0;
        broadcast(615);
    }

    public void reset() {
        removelHeartbeatTimer();
        disconectServer();
        sendMsgTime = 0L;
        inst = null;
        this.retry = 0;
        if (handler != null) {
            handler.removeMessages(this.CONNECTIONSCOKET);
            this.handler = null;
        }
    }

    //实名认证审核回调
    public void pullCertification(byte[] contentBytes) {
        try {
            PushUserRealNameStateProto.PushUserRealNameStateProtoReq req = PushUserRealNameStateProto.PushUserRealNameStateProtoReq.parseFrom(contentBytes);
            SPUtils.setStatusType(req.getState());
            SPUtils.setStatusMessage(req.getMessage());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    //账号下线
    public void exit(byte[] contentBytes) {
        AppApplication.getInstance().logOut(ctx);
        Intent intent = new Intent(ctx, LoginActivity.class);
        intent.putExtra("type", 1);
        ctx.startActivity(intent);
    }

    /* 设置心跳包定时器 */
    public void scheduleHeartbeat(int seconds) {
        if (pi == null) {
            Intent intent = new Intent(Constants.ACTION_SENDING_HEARTBEAT);
            if (ctx != null) {
                pi = PendingIntent.getBroadcast(ctx, 0, intent, 0);
            }
            if (pi == null) {
                return;
            }
        }
        AlarmManager am = (AlarmManager) AppApplication.getInstance()
                .getSystemService(Context.ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + seconds, seconds, pi);
    }

    /**
     * 登陆服务器后初始化结果
     *
     * @param contentBytes
     */
    public void serverInitResult(byte[] contentBytes) {
        ConnectByProto.ConnectByProtoRes initRes = null;
        try {
            initRes = ConnectByProto.ConnectByProtoRes.parseFrom(contentBytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        if (initRes.getMsg().getCode() == 200) {
            SPUtils.saveUserInfo(initRes);
            onSuccess();
            broadcast(IMEventType.ACTION_LOGIN_SUCCESS);
        } else if (initRes.getMsg().getCode() == 10055) {
            AppApplication.getInstance().logOut(ctx);
            Bundle bd = new Bundle();
            bd.putInt("method", IMEventType.ACTION_LOGIN_OUT);
            bd.putInt("type", 2);
            bd.putString("message1", initRes.getMsg().getMsg());
            MessageNotifyCenter.getInstance().doNotify(bd);
        } else {
            Log.e("===", initRes.getMsg().getMsg());
        }
    }

    /**
     * 断掉链接服务器
     */
    public void disconectServer() {
        if (connectServerThread != null) {
            connectServerThread.close();
            connectServerThread = null;
        }
    }

    /**
     * 重连服务器
     */
    public synchronized void tryReconnectServer() {
        if (NetworkUtils.isConnected()) {
            isNetWork = true;
            disconectServer();
            connectImServer();
        }
    }
}