package com.jk.jkproject.utils.sharesdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.jk.jkproject.utils.ToastUtils;
import com.mob.MobApplication;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginApi implements Handler.Callback {
    private static final int MSG_AUTH_CANCEL = 1;
    private static final int MSG_AUTH_ERROR = 2;
    private static final int MSG_AUTH_COMPLETE = 3;

    private OnLoginListener loginListener;
    private String platform;
    private Context context;
    private Handler handler;

    public LoginApi() {
        handler = new Handler(Looper.getMainLooper(), this);
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setOnLoginListener(OnLoginListener login) {
        this.loginListener = login;
    }

    public void login(Context context) {
        this.context = context.getApplicationContext();
        if (platform == null) {
            return;
        }

        //初始化SDK
        if (!(context instanceof MobApplication)) {
            MobSDK.init(context.getApplicationContext());
        }
        Platform plat = ShareSDK.getPlatform(platform);
        if (plat == null) {
            return;
        }

        if (plat.isAuthValid()) {
            plat.removeAccount(true);
            return;
        }

        //使用SSO授权，通过客户单授权
        plat.SSOSetting(false);
        plat.setPlatformActionListener(new PlatformActionListener() {
            public void onComplete(Platform plat, int action, HashMap<String, Object> res) {
                if (action == Platform.ACTION_USER_INFOR) {
                    Message msg = new Message();
                    msg.what = MSG_AUTH_COMPLETE;
                    msg.arg2 = action;
                    UserInfo userInfo = new UserInfo();
                    userInfo.setAccess_token(plat.getDb().getToken());
                    if (plat.getName().equals(Wechat.NAME)) {
                        userInfo.setOpenid(plat.getDb().get("openid"));
                    } else {
                        userInfo.setOpenid(plat.getDb().get("userID"));
//                        userInfo.setOpenid(plat.getDb().get("pfkey"));
//                        userInfo.setOpenid(plat.getDb().get("pay_token"));
                    }
                    userInfo.setType(plat.getName());
//                    Iterator ite = res.entrySet().iterator();
//                    while (ite.hasNext()) {
//                        Map.Entry entry = (Map.Entry) ite.next();
//                        Object key = entry.getKey();
//                        Object value = entry.getValue();
//                    }
                    msg.obj = userInfo;
                    handler.sendMessage(msg);
                }
            }

            public void onError(Platform plat, int action, Throwable t) {
                if (action == Platform.ACTION_USER_INFOR) {
                    Message msg = new Message();
                    msg.what = MSG_AUTH_ERROR;
                    msg.arg2 = action;
                    msg.obj = t;
                    handler.sendMessage(msg);
                }
                t.printStackTrace();
            }

            public void onCancel(Platform plat, int action) {
                if (action == Platform.ACTION_USER_INFOR) {
                    Message msg = new Message();
                    msg.what = MSG_AUTH_CANCEL;
                    msg.arg2 = action;
                    msg.obj = plat;
                    handler.sendMessage(msg);
                }
            }
        });
//        plat.authorize();
        plat.showUser(null);
    }

    /**
     * 处理操作结果
     */
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_AUTH_CANCEL: {
                // 取消
                ToastUtils.showShortToast("取消授权");
            }
            break;
            case MSG_AUTH_ERROR: {
                // 失败
                Throwable t = (Throwable) msg.obj;
                String text = "caught error: " + t.getMessage();
                ToastUtils.showShortToast(text);
                t.printStackTrace();
            }
            break;
            case MSG_AUTH_COMPLETE: {
                ToastUtils.showShortToast("授权成功");
                // 成功
//                Object[] objs = (Object[]) msg.obj;
//                String plat = (String) objs[0];
//                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];

                /**
                 * 微信授权资料=country：
                 * 微信授权资料=unionid： ogfvh0lTvxLTncLEgeOYBzB-JWOk
                 * 微信授权资料=province：
                 * 微信授权资料=city：
                 * 微信授权资料=openid： omFmBwBRdqsAi_wqZjl2DxSix_mk
                 * 微信授权资料=sex： 0
                 * 微信授权资料=nickname： 若少
                 * 微信授权资料=headimgurl： http://thirdwx.qlogo.cn/mmopen/vi_32/C3rTATBCTkXO3vWvZNWulYt6GTPWue41VDxQ92rkJd3icV0snXTUJQ3wm0P9Rib027IVGVS4Qo8k5DpOBiaVAfLwQ/132
                 * 微信授权资料=userTags：
                 * 微信授权资料=language： zh_CN
                 * 微信授权资料=privilege： []
                 */
                if (loginListener != null) {
                    loginListener.onLogin((UserInfo) msg.obj);
                }
//                    RegisterPage.setOnLoginListener(loginListener);
//                    RegisterPage.setPlatform(plat);
//                    Intent intent=new Intent(context, RegisterPage.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
            }
            break;
        }
        return false;
    }
}
