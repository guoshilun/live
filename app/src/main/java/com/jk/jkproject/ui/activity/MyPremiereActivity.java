package com.jk.jkproject.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.ui.chat.GlideUtils;
import com.jk.jkproject.ui.dialog.DialogHostBeautySet;
import com.jk.jkproject.ui.dialog.DialogLiveRoomLackBalance;
import com.jk.jkproject.ui.dialog.DialogWarningNotices;
import com.jk.jkproject.ui.entity.JoinRoomBeanInfo;
import com.jk.jkproject.ui.entity.LiveOpenPlayInfo;
import com.jk.jkproject.ui.entity.QiNiuTokenInfo;
import com.jk.jkproject.ui.entity.ShareBean;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.utils.QiNiuFileUpdownUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.Zego;
import com.jk.jkproject.utils.sharesdk.ShareUtils;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.constants.ZegoViewMode;
import im.zego.zegoexpress.entity.ZegoBeautifyOption;
import im.zego.zegoexpress.entity.ZegoCanvas;

/**
 * @author Zick
 * @params
 * @date 2020/8/1 10:21 AM
 * @desc 开播页面
 */
public class MyPremiereActivity extends BActivity implements Observer {
    public static final int REQUEST_CODE = 200;
    private static final int PERMISSION_REQUEST_CODE_STORAGE = 200;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.tv_classif)
    TextView tvClassif;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_skin_care)
    ImageView ivSkinCare;
    @BindView(R.id.tv_skin_care)
    TextView tvSkinCare;
    @BindView(R.id.iv_lens_switch)
    ImageView ivLensSwitch;
    @BindView(R.id.tv_lens_switch)
    TextView tvLensSwitch;
    @BindView(R.id.iv_live_pic)
    ImageView ivLivePic;
    @BindView(R.id.tv_pic)
    TextView tvPic;
    @BindView(R.id.et_live_title)
    EditText etLiveTitle;
    @BindView(R.id.rl_open_live_set)
    RelativeLayout rlOpenLiveSet;
    @BindView(R.id.iv_weixin)
    CheckBox ivWeixin;
    @BindView(R.id.iv_weixin_zone)
    CheckBox ivWeixinZone;
    @BindView(R.id.iv_login_qq)
    CheckBox ivLoginQq;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_qq_zone)
    CheckBox ivQqZone;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.surfaceview)
    TextureView mPusherView;

    private String QiNiuUrl;

    private Unbinder bind;


    private int liveDirection = 0;

    private String liveType;

    private String picturePath;

    private String positive;
    private String token = "";
    private String r_cover = "";
    private String r_name = "";
    private int mState;
    private DialogLiveRoomLackBalance mBalanceDialog;
    private DialogWarningNotices warningNoti;
    private ZegoExpressEngine zego;
    private ZegoCanvas zegoCanvas;
    Handler handler = new Handler();
    private boolean isCamera = false, isSkin = false, WXisChecked = true, WXZisChecked = false, QQisChecked = false, QQZisChecked = false;
    private ZegoBeautifyOption zegoBeautifyOption;
    private DialogHostBeautySet mHostBeautyDialog;
    private Geocoder geocoder;
    private List<Address> addressList;

    private void initView() {
        String str = getResources().getString(R.string.str_open_msg);
        tvMsg.setText((CharSequence) Html.fromHtml(str));
        switch (SPUtils.getStatus()) {//  0.未实名  1.已实名 2.审核中 3.实名失败
            case 3:
                setDialogTips(13, "");
                break;
            case 1:
                AppApis.getGetOldRoomInfo(this);
                break;
        }
        if (getIntent() != null && getIntent().hasExtra("message")) {
            SystemNotiBeanInfo.DataBean message = (SystemNotiBeanInfo.DataBean) getIntent().getSerializableExtra("message");
            setShowDialog(1, message.getTime(), message.getMessage());
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {
            handler.postDelayed(() -> {
                if (ivBg != null) {
                    ivBg.setVisibility(View.GONE);
                }
            }, 1000);
            zego = Zego.instance().getEngine();
            zegoCanvas = new ZegoCanvas(mPusherView);
            zegoCanvas.viewMode = ZegoViewMode.ASPECT_FILL;
            zego.startPreview(zegoCanvas);
            zegoBeautifyOption = new ZegoBeautifyOption();
        }
        WXisChecked = true;
        ivLoginQq.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setIsClick();
            QQisChecked = isChecked;
            ivLoginQq.setChecked(isChecked);
        });

        ivWeixinZone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setIsClick();
            WXZisChecked = isChecked;
            ivWeixinZone.setChecked(isChecked);
        });

        ivWeixin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setIsClick();
            WXisChecked = isChecked;
            ivWeixin.setChecked(isChecked);
        });

        ivQqZone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setIsClick();
            QQZisChecked = isChecked;
            ivQqZone.setChecked(isChecked);
        });

        mPusherView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                LogUtils.e("****onSurfaceTextureAvailable");
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                LogUtils.e("****onSurfaceTextureSizeChanged");
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                LogUtils.e("****onSurfaceTextureDestroyed");
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                LogUtils.e("****onSurfaceTextureUpdated");
            }
        });
        if (SPUtils.getString("location", "").equals("")) {
            setLocation();
        } else {
            tvClassif.setText(SPUtils.getString("location", ""));
        }
    }


    //将全部CheckBox初始化
    public void setIsClick() {
        ivLoginQq.setChecked(false);
        ivQqZone.setChecked(false);
        ivWeixin.setChecked(false);
        ivWeixinZone.setChecked(false);
        QQisChecked = false;
        QQZisChecked = false;
        WXZisChecked = false;
        WXisChecked = false;
    }

    //1 2 in
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                if (!picturePath.isEmpty()) {
                    Uri uri = Uri.fromFile(new File(picturePath));
                    ivLivePic.setImageURI(uri);
                    tvPic.setText("更换封面");
                    switch (SPUtils.getStatus()) {//  0.未实名  1.已实名 2.审核中 3.实名失败
                        case 1:
                            AppApis.getGitPicToken(this);
                            break;
                    }
                }
            }
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_my_premiere);
        this.bind = ButterKnife.bind((Activity) this);
        MessageNotifyCenter.getInstance().addObserver(this);
        geocoder = new Geocoder(this);
        initView();
    }

    protected void onDestroy() {
        MessageNotifyCenter.getInstance().unregister(this);
        super.onDestroy();
        this.bind.unbind();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        setLocation();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE_STORAGE == requestCode) {
            if (grantResults != null && grantResults.length >= 1 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
                ToastUtils.showShortToast("拒绝权限可能无法正常体验所有功能");
            } else {
                handler.postDelayed(() -> {
                    if (ivBg != null) {
                        ivBg.setVisibility(View.GONE);
                    }
                }, 1000);
                zego = Zego.instance().getEngine();
                zegoCanvas = new ZegoCanvas(mPusherView);
                zegoCanvas.viewMode = ZegoViewMode.ASPECT_FILL;
                zego.startPreview(zegoCanvas);
                zegoBeautifyOption = new ZegoBeautifyOption();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void setLocation() {
        new Thread(() -> {
            try {
                addressList = geocoder.getFromLocation(Double.parseDouble(SPUtils.getLatitude()), Double.parseDouble(SPUtils.getLongitude()), 1);
                uiCallback.sendEmptyMessage(0); //由于通过addressList对象共享结果，所以使用空消息即可
            } catch (Exception e) {
                LogUtils.e("WEI", "ERROR: " + e.toString());
                e.printStackTrace();
            }
        }).start();
    }

    private Handler uiCallback = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int index = msg.what;
            if (tvClassif != null) {
                if (addressList != null && addressList.size() > index) {
                    Address address = addressList.get(0);
                    tvClassif.setText(address.getAdminArea() + "");
                    SPUtils.putString("location", address.getAdminArea());
                } else {
                    tvClassif.setText("火星");
                }
            }
        }
    };

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        ToastUtils.showShortToast(paramString2);
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_QINIU_TOKEN)) {
            if (obj != null && obj instanceof QiNiuTokenInfo) {
                QiNiuTokenInfo qiNiuTokenInfo = (QiNiuTokenInfo) obj;
                if (qiNiuTokenInfo.getCode() == 200) {
                    token = qiNiuTokenInfo.getData().getQnToken();
                    QiNiuUrl = qiNiuTokenInfo.getData().getUrl();
                    QiNiuFileUpdownUtils.get().upload(0, token, picturePath, this);
                }
            }
        } else if (url.equals(Urls.GET_OLD_ROOM_INFO)) {
            if (obj != null) {
                try {
                    JSONObject object = new JSONObject(obj.toString());
                    LogUtils.e("======", obj.toString());
                    if (object.getInt("code") == 200) {
                        JSONObject object1 = new JSONObject(object.getString("data"));
                        r_cover = object1.getString("r_cover");
                        r_name = object1.getString("r_name");
                        if (!object1.getString("state").isEmpty()) {
                            mState = Integer.parseInt(object1.getString("state"));//0.审核失败 1.审核中 2.审核成功
                        }
                        etLiveTitle.setText(r_name);
//                        etLiveTitle.setBackgroundColor(getResources().getColor(R.color.transparent));
                        etLiveTitle.setSelection(r_name.length());
                        if (!r_cover.isEmpty()) {
                            GlideUtils.loadChatImage(this, r_cover, ivLivePic, ScreenUtils.dp2px(this, 86));
                            switch (mState) {
                                case 0:
                                    tvPic.setText("封面审核失败");
                                    break;
                                case 1:
                                    tvPic.setText("封面审核中");
                                    break;
                                case 2:
                                    tvPic.setText("封面审核成功");
                                    break;
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (Urls.GET_UPDATE_COVER_INFO.equals(url)) {

        } else {
            if (url.equals("0")) {
                if (!obj.toString().isEmpty()) {
                    AppApis.getUpdateCover(QiNiuUrl + obj.toString(), 1, this);
//                    AppApis.getGitOpenLive(etLiveTitle.getText().toString().trim(), QiNiuUrl + positive, this);
                }
            } else if (url.equals(Urls.GET_OPEN_PLAY) && obj != null && obj instanceof LiveOpenPlayInfo) {
                LiveOpenPlayInfo liveOpenPlayInfo = (LiveOpenPlayInfo) obj;
                LogUtils.e(liveOpenPlayInfo.toString());
                if (liveOpenPlayInfo.getCode() == 200) {
                    if (liveOpenPlayInfo.getData().getStatus() == 1) {
                        Intent intent = new Intent(this, LiveRoomActivity.class);
                        LiveOpenPlayInfo.DataBean joinRoomByProtoRes = liveOpenPlayInfo.getData();
                        JoinRoomBeanInfo joinRoomBeanInfo = new JoinRoomBeanInfo();
                        joinRoomBeanInfo.setBanned(joinRoomByProtoRes.getBanned());
                        joinRoomBeanInfo.setPlayUrl(joinRoomByProtoRes.getPull_flow_url());
                        joinRoomBeanInfo.setRoomName(joinRoomByProtoRes.getRoomName());
                        joinRoomBeanInfo.setCount(joinRoomByProtoRes.getCount());
                        joinRoomBeanInfo.setPicture(joinRoomByProtoRes.getPicture());
                        joinRoomBeanInfo.setNickname(joinRoomByProtoRes.getNickname());
                        joinRoomBeanInfo.setAccount(joinRoomByProtoRes.getAccount());
                        joinRoomBeanInfo.setUserId(joinRoomByProtoRes.getBeautifulUserId());
                        joinRoomBeanInfo.setAnchorGrade(Integer.parseInt(joinRoomByProtoRes.getAnchorGrade()));
                        joinRoomBeanInfo.setNeedExperience(joinRoomByProtoRes.getNeedExperience());
                        joinRoomBeanInfo.setIsAttention(joinRoomByProtoRes.getIsAttention());
                        joinRoomBeanInfo.setRanking(joinRoomByProtoRes.getRanking());
                        joinRoomBeanInfo.setOth(joinRoomByProtoRes.getOth());
                        if (QiNiuUrl != null) {
                            joinRoomBeanInfo.setCover(QiNiuUrl + positive);
                        } else {
                            joinRoomBeanInfo.setCover(r_cover);
                        }
                        joinRoomBeanInfo.setCamera(isCamera);
                        joinRoomBeanInfo.setSkin(isSkin);
                        intent.putExtra("liveDirection", liveDirection);
                        intent.putExtra("roomId", liveOpenPlayInfo.getData().getRoomId());
                        intent.putExtra("pullFlowUrl", liveOpenPlayInfo.getData().getPull_flow_url());
                        intent.putExtra("roomBeanInfo", joinRoomBeanInfo);
                        if (!WXisChecked && !WXZisChecked && !QQZisChecked && !QQisChecked) {
                            startActivity(intent);
                            finish();
                        } else {
                            ShareBean shareBean = new ShareBean();
                            shareBean.setUrl(Urls.SHARE_PATH + joinRoomByProtoRes.getRoomId());
                            shareBean.setTitle(joinRoomBeanInfo.getRoomName());
                            shareBean.setText("直播中");
                            shareBean.setImageUrl(joinRoomBeanInfo.getCover());
                            if (WXisChecked) {
                                shareBean.setPlatform(Wechat.NAME);
                            } else if (WXZisChecked) {
                                shareBean.setPlatform(WechatMoments.NAME);
                            } else if (QQisChecked) {
                                shareBean.setPlatform(QQ.NAME);
                            } else {
                                shareBean.setPlatform(QZone.NAME);
                            }
                            ShareUtils.instance().showShare(shareBean);
                            startActivity(intent);
                            finish();
                        }
                    } else {
//                        setDialogTips(15, liveOpenPlayInfo.getData().getTime());
                        setShowDialog(4, liveOpenPlayInfo.getData().getTime(), liveOpenPlayInfo.getData().getCause());
                    }
                } else {
                    ToastUtils.showShortToast(liveOpenPlayInfo.getMsg());
                }
            }
        }
    }

    @OnClick({R.id.iv_close, R.id.iv_skin_care, R.id.tv_msg, R.id.tv_skin_care, R.id.iv_lens_switch, R.id.tv_lens_switch, R.id.iv_live_pic, R.id.et_live_title, R.id.iv_weixin, R.id.iv_weixin_zone, R.id.iv_login_qq, R.id.iv_qq_zone, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_msg:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("type", 8);
                startActivity(intent);
                break;
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_skin_care:
            case R.id.tv_skin_care:
                isSkin = true;
                beautySet();
//                    Zego.instance().setBeautifyOption(zegoBeautifyOption);
                break;
            case R.id.iv_lens_switch:
            case R.id.tv_lens_switch:
                Zego.instance().useFrontCamera(isCamera);
                isCamera = !isCamera;
                break;
            case R.id.iv_live_pic:
                PictureSelector.create((Activity) this, 21).selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.et_live_title:
                break;
            case R.id.tv_commit:
                switch (SPUtils.getType()) {
                    case 1:
                        switch (SPUtils.getStatus()) {//  0.未实名  1.已实名 2.审核中 3.实名失败
                            default:
//                                ToastUtils.showShortToast("审核中");
                                setDialogTips(12, "");
                                break;
                            case 1:
                                commit();
//                                startActivity(new Intent(this, MyPremiereActivity.class));
                                break;
                            case 0:
                                setDialogTips(11, "");
                                break;
                        }
                        break;
                    case 2:
                        commit();
//                        startActivity(new Intent(this, MyPremiereActivity.class));
                        break;
                }
                break;
        }
    }

    private void setShowDialog(int mType, String time, String cause) {
        warningNoti = new DialogWarningNotices(this, mType, time, cause);
        warningNoti.show();
        warningNoti.setDialogClickListener(type -> {
            switch (type) {
                case 1:

                    break;
                case 4:
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        });
        warningNoti.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //实名认证提示框
    private void setDialogTips(int mType, String time) {
        mBalanceDialog = new DialogLiveRoomLackBalance(this, mType, time);
        mBalanceDialog.show();
        mBalanceDialog.setDialogClickListener(type -> {
            switch (type) {
                case 11:
                case 13:
                    startActivity(new Intent(this, MyCentificationActivity.class));
                    mBalanceDialog.dismiss();
                    break;
                case 12:
                    mBalanceDialog.dismiss();
                    break;
                case -1:
                    finish();
                    break;
            }
        });
        mBalanceDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void commit() {
        if (etLiveTitle.getText().toString().trim().isEmpty()) {
            ToastUtils.showShortToast("房间名称不能为空");
            return;
        }
        if (r_cover.isEmpty() && token.isEmpty()) {
            ToastUtils.showShortToast("直播封面不能为空");
            return;
        }
        AppApis.getGitOpenLive(etLiveTitle.getText().toString().trim(), r_cover, this);
    }


    //美颜设置
    private void beautySet() {
        if (mHostBeautyDialog == null) {
            mHostBeautyDialog = new DialogHostBeautySet(this, mPusherView, false);
        }
        mHostBeautyDialog.show();
        mHostBeautyDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            Message message = Message.obtain();
            message.obj = arg;
            handler1.sendMessage(message);
        }
    }

    private Handler handler1 = new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message param1Message) {
            Bundle bundle = (Bundle) param1Message.obj;
            switch (bundle.getInt("method")) {
                case IMEventType.ACTION_UPDATE_COVER:
                    int mType = bundle.getInt("type");//2 封面审核 //  1 头像审核
                    mState = bundle.getInt("state");//审核状态 0.审核失败 1.审核中 2.审核成功
                    if (mType == 2) {
                        switch (mState) {
                            case 0:
                                tvPic.setText("封面审核失败");
                                break;
                            case 1:
                                tvPic.setText("封面审核中");
                                break;
                            case 2:
                                tvPic.setText("封面审核成功");
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
