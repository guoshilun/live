package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jk.jkproject.R;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.entity.ShareBean;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.sharesdk.ShareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class DialogLiveRoomShare extends BaseDialog {
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.ll_weixin_zoo)
    LinearLayout llWeixinZoo;
    @BindView(R.id.ll_qq)
    LinearLayout llQq;
    @BindView(R.id.ll_weibo)
    LinearLayout llWeibo;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;
    private String roomId;
    private String roomName;
    private String cover;

    private int mType;

    private String titleName;

    private String userName;
    private ShareBean shareBean;

    public DialogLiveRoomShare(Context paramContext, String roomId, String roomName, String cover) {
        super(paramContext);
        this.mContext = paramContext;
        this.roomId = roomId;
        this.roomName = roomName;
        this.cover = cover;
    }

    private void init() {
        shareBean = new ShareBean();
        shareBean.setImageUrl(cover);
        shareBean.setText("直播中");
        shareBean.setTitle(roomName);
        shareBean.setUrl(Urls.SHARE_PATH + roomId);
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_share);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.h = 161;
        this.gravity = 80;
    }

    protected void initView() {
        init();
    }

    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(getContext(), 161));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick({R.id.ll_weixin, R.id.ll_weixin_zoo, R.id.ll_qq, R.id.ll_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_weixin:
                ShowShare(Wechat.NAME);
                dismiss();
                break;
            case R.id.ll_weixin_zoo:
                ShowShare(WechatMoments.NAME);
                dismiss();
                break;
            case R.id.ll_qq:
                ShowShare(QQ.NAME);
                dismiss();
                break;
            case R.id.ll_weibo:
                ShowShare(QZone.NAME);
                dismiss();
                break;
        }
    }


    private void ShowShare(String platform) {
        shareBean.setPlatform(platform);
        ShareUtils.instance().showShare(shareBean);
    }

    public static interface DialogReturnListener {
        void onClick(String param1String);
    }
}