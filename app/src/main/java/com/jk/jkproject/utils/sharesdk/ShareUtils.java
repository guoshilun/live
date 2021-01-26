package com.jk.jkproject.utils.sharesdk;

import com.jk.jkproject.R;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.ui.entity.ShareBean;
import com.jk.jkproject.utils.ToastUtils;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;

/**
 * @author Zick
 * @params
 * @date 2020/8/20 9:58 AM
 * @desc 分享单例模式
 */
public class ShareUtils {

    private static ShareUtils inst;
    private final OnekeyShare oks;
    private OnShareListener onShareListener;


    public static ShareUtils instance() {
        synchronized (ShareUtils.class) {
            if (inst == null) {
                inst = new ShareUtils();
            }
            return inst;
        }
    }

    public ShareUtils() {
        oks = new OnekeyShare();
    }


    public void showShare(ShareBean info) {
        if (info.getPlatform() != null) {
            oks.setPlatform(info.getPlatform());
        }
        oks.setDialogMode(true);
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(info.getTitle());
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(info.getTitleUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(info.getText());
        // setImageUrl是网络图片的url
        oks.setImageUrl(info.getImageUrl());
        // url在微信、Facebook等平台中使用
        if (info.getPlatform().equals(QQ.NAME) || info.getPlatform().equals(QZone.NAME)) {
            oks.setTitleUrl(info.getUrl());
            oks.setSite(AppApplication.getInstance().getResources().getString(R.string.app_name));
            oks.setSiteUrl(info.getUrl());
        } else {
            oks.setUrl(info.getUrl());
        }
        // 启动分享GUI
        oks.show(MobSDK.getContext());

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ToastUtils.showShortToast("分享成功");
                //分享成功
                if (onShareListener != null) {
                    onShareListener.onComplete();
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastUtils.showShortToast("分享失败");
                //分享失败
                if (onShareListener != null) {
                    onShareListener.onComplete();
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ToastUtils.showShortToast("取消分享");
                //取消分享
                if (onShareListener != null) {
                    onShareListener.onComplete();
                }
            }
        });
    }

    public void setOnShareListener(OnShareListener onShareListener) {
        this.onShareListener = onShareListener;
    }
}
