package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.entity.ClosePlayInfo;
import com.yuyan.statusbar.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/12 12:00 PM
 * @desc 主播下播
 */
public class SowingLiveActivity extends BActivity implements ResponseListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_user_header)
    SimpleDraweeView sdvUserHeader;
    @BindView(R.id.tv_user_name_host)
    TextView tvUserNameHost;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_lock_count)
    TextView tvLockCount;
    @BindView(R.id.tv_lock_time)
    TextView tvLockTime;
    @BindView(R.id.tv_lock_diamond)
    TextView tvLockDiamond;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_host)
    TextView tvHost;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.iv_weixin_zone)
    ImageView ivWeixinZone;
    @BindView(R.id.iv_login_qq)
    ImageView ivLoginQq;
    @BindView(R.id.iv_qq_zone)
    ImageView ivQqZone;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_follow_btn)
    TextView tvFollowBtn;
    private Unbinder bind;
    private String roomId = "", time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_stop);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.color_62BDF3));
        bind = ButterKnife.bind(this);
        roomId = getIntent().getStringExtra("roomId");
        time = getIntent().getStringExtra("time");
        tvLockTime.setText(time + "");
        initView();
    }

    private void initView() {
        AppApis.getClosePlay(roomId, this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_LIVE_ROOM_CLOSE_PLAY)) {
            if (obj != null && obj instanceof ClosePlayInfo) {
                ClosePlayInfo closePlayInfo = (ClosePlayInfo) obj;
                if (closePlayInfo.getCode() == 200) {
                    sdvUserHeader.setImageURI(closePlayInfo.getData().getPicture());
                    tvUserNameHost.setText(closePlayInfo.getData().getNickname());
                    tvUserLevel.setText(closePlayInfo.getData().getAnchorGrade() + "");
                    tvUserId.setText("ID:" + closePlayInfo.getData().getUserId());
                    tvLockCount.setText(closePlayInfo.getData().getSumCount());
                    tvLockDiamond.setText(closePlayInfo.getData().getMoney() + "");
                }
            }
        }
    }

    @OnClick({R.id.iv_weixin, R.id.iv_weixin_zone, R.id.iv_login_qq, R.id.iv_qq_zone, R.id.tv_follow_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_weixin:
                ToastUtils.showShortToast("微信分享");
                break;
            case R.id.iv_weixin_zone:
                ToastUtils.showShortToast("朋友圈分享");
                break;
            case R.id.iv_login_qq:
                ToastUtils.showShortToast("QQ分享");
                break;
            case R.id.iv_qq_zone:
                ToastUtils.showShortToast("QQ空间分享");
                break;
            case R.id.tv_follow_btn:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}