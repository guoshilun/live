package com.jk.jkproject.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitmapUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.entity.QiNiuTokenInfo;
import com.jk.jkproject.ui.entity.ShareBean;
import com.jk.jkproject.utils.QiNiuFileUpdownUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.ViewToBitmapUtils;
import com.jk.jkproject.utils.sharesdk.ShareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * @author Zick
 * @params
 * @date 2020/7/17 11:10 AM
 * @desc 分享二维码
 */
public class ShareQrCodeActivity extends BActivity {
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_left_name)
    TextView tvLeftName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_title)
    ImageView ivRightTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.ll_py)
    LinearLayout llPy;
    @BindView(R.id.ll_qq)
    LinearLayout llQq;
    @BindView(R.id.ll_wb)
    LinearLayout llWb;
    @BindView(R.id.ll_fz)
    LinearLayout llFz;
    @BindView(R.id.rl_share)
    RelativeLayout rlShare;
    private Unbinder bind;
    Handler handler = new Handler();
    private String sharePath, QiNiuUrl;
    private String platform, imgUrl;
    private boolean isClick = true;
    private QiNiuTokenInfo.DataBean qnTokenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_qr_code);
        bind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        publicTopLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        tvTitle.setText("分享名片");
        tvTitle.setTextColor(getResources().getColor(R.color.white));
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvUserName.setText(SPUtils.getNickname());
        tvUserId.setText("ID:" + SPUtils.getBId());
        ivTitle.setImageResource(R.mipmap.icon_white_return);

        setQrCode();
        ViewToBitmapUtils.deleteFile();
        sharePath = ViewToBitmapUtils.viewSaveToImage(this, rlShare);
    }

    @Override
    protected void onDestroy() {
        ViewToBitmapUtils.reset();
        super.onDestroy();
        bind.unbind();
    }

    @OnClick({R.id.iv_title, R.id.ll_weixin, R.id.ll_py, R.id.ll_qq, R.id.ll_wb, R.id.ll_fz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.ll_weixin:
                showShare(Wechat.NAME);
                break;
            case R.id.ll_py:
                showShare(WechatMoments.NAME);
                break;
            case R.id.ll_qq:
                showShare(QQ.NAME);
                break;
            case R.id.ll_wb:
                showShare(QZone.NAME);
                break;
            case R.id.ll_fz:
                ToastUtils.showShortToast("复制分享链接");
                break;
        }
    }


    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        isClick = true;
        if (url.equals(Urls.GET_QINIU_TOKEN)) {
            if (obj != null && obj instanceof QiNiuTokenInfo) {
                QiNiuTokenInfo qnToken = (QiNiuTokenInfo) obj;
                if (qnToken.getCode() == 200) {
                    qnTokenData = qnToken.getData();
                    QiNiuFileUpdownUtils.get().upload(1, qnTokenData.getQnToken(), sharePath, this);
                }
            }
        } else if (url.equals(1 + "")) {
            imgUrl = qnTokenData.getUrl() + obj.toString();
            ShareBean shareBean = new ShareBean();
            shareBean.setPlatform(platform);
            shareBean.setImageUrl(imgUrl);
            ShareUtils.instance().showShare(shareBean);
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        isClick = true;
    }

    private void showShare(String platform) {
        if (sharePath == null) {
            sharePath = ViewToBitmapUtils.viewSaveToImage(this, rlShare);
        }
        if (isClick) {
            this.platform = platform;
            if (imgUrl != null) {
                ShareBean shareBean = new ShareBean();
                shareBean.setPlatform(platform);
                shareBean.setImageUrl(imgUrl);
                ShareUtils.instance().showShare(shareBean);
                isClick = true;
            } else {
                AppApis.getGitPicToken(this);
            }
            isClick = false;
        }
    }

    private void setQrCode() {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapUtils.create2DCode(Urls.SHARE_ACC_PATH + SPUtils.getUserId());
            LogUtils.e("=====", Urls.SHARE_ACC_PATH + SPUtils.getUserId());
        } catch (WriterException e) {
            e.printStackTrace();
        }
        ivQrCode.setImageBitmap(bitmap);
    }
}
