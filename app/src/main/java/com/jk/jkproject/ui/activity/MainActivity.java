package com.jk.jkproject.ui.activity;


import android.Manifest;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.fansan.common.BaseApp;
import com.fansan.common.OrderPopup;
import com.fansan.common.pojo.OrderPushBean;
import com.jk.jkproject.R;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.db.TeenTipsDB;
import com.jk.jkproject.net.im.db.TeenTipsHelper;
import com.jk.jkproject.net.im.manager.IMLoginManager;
import com.jk.jkproject.net.im.service.ChujianIMService;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.service.LocationService;
import com.jk.jkproject.ui.chat.LiveMessageActivity;
import com.jk.jkproject.ui.chat.Message;
import com.jk.jkproject.ui.dialog.DialogTeenTips;
import com.jk.jkproject.ui.dialog.DialogUpdateVersion;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.entity.LiveGiftUtils;
import com.jk.jkproject.ui.entity.UpdateInfo;
import com.jk.jkproject.ui.entity.UserDateInfo;
import com.jk.jkproject.ui.fragment.LiveHomeFragment;
import com.jk.jkproject.ui.fragment.LiveMessageFragment;
import com.jk.jkproject.ui.fragment.MyCenterFragment;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.ui.widget.MainBottomItem;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.order.PlayWithUFragment;
import com.jk.weblib.EsportBean;
import com.mob.pushsdk.MobPush;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.net.shoot.sharetracesdk.AppData;
import cn.net.shoot.sharetracesdk.ShareTrace;
import cn.net.shoot.sharetracesdk.ShareTraceInstallListener;

public class MainActivity extends BActivity {

    public static final  String     tab_home                        = "tab_home";
    public static final  String     tab_agenda                      = "tab_agenda";
    public static final  String     tab_team                        = "tab_team";
    public static final  String     tab_attention                   = "tab_attention";
    public static final  String     tab_my                          = "tab_my";
    private static final int        PERMISSION_REQUEST_CODE_STORAGE = 200;
    private static final int        PERMISSION_REQUEST_CODE         = 201;
    private static       SVGAParser parser;
    @BindView(R.id.fl_container)
    FrameLayout    flContainer;
    @BindView(R.id.tab_home)
    MainBottomItem tabHome;
    @BindView(R.id.tab_play)
    MainBottomItem tabPlay;
    @BindView(R.id.tab_msg)
    MainBottomItem tabMsg;
    @BindView(R.id.tab_match)
    MainBottomItem tabMatch;
    @BindView(R.id.go_live_attention)
    ImageView      goLiveAttention;
    @BindView(R.id.iv_left_select)
    ImageView      ivLeftSelect;
    @BindView(R.id.playSelected)
    ImageView      playSelected;
    @BindView(R.id.msgSelected)
    ImageView      msgSelected;
    @BindView(R.id.iv_right_select)
    ImageView      ivRightSelect;
    @BindView(R.id.left_svga)
    SVGAImageView  leftSvag;
    @BindView(R.id.right_svga)
    SVGAImageView  rightSvag;
    @BindView(R.id.btnPlay)
    SVGAImageView  btnPlay;
    @BindView(R.id.btnMsg)
    SVGAImageView  btnMsg;
    private Unbinder            bind;
    private FragmentManager     fm;
    private LiveHomeFragment    homeFragment;
    private PlayWithUFragment   playFragment;
    private UserDateInfo        userInfo;
    private MyCenterFragment    myCenterFragment;
    private LiveMessageFragment liveMessageFragment;
    private LocationManager     locationManager;
    private DialogTeenTips      tips;
    private LocationService     mLocationService;
    private DialogUpdateVersion updateVersion;

    @Override
    public boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        initView();

        if (!SPUtils.getUserId().isEmpty()) {
            MobPush.setAlias(SPUtils.getUserId());
        }
        //获取礼物数据
        AppApis.getGiftList(this);
        TeenTipsDialog();

        BindingShare();
    }

    private void getEsport() {
        if (!SPUtils.getToken().isEmpty())
            AppApis.esport(new ResponseListener() {
                @Override
                public void onFailure(int paramInt, String paramString1, String paramString2) {
                    LogUtils.e("getEsportUrl error" + paramString1 + paramString2);
                }

                @Override
                public void onSuccess(String paramString, Object paramObject) {
                    EsportBean bean = (EsportBean) paramObject;
                    if (bean.getCode() == 200) {
                        SharedPreferences.Editor esportsUrl = getSharedPreferences("esportsUrl", Context.MODE_PRIVATE).edit();
                        esportsUrl.putString("1", bean.getData().getForecast_url());
                        esportsUrl.putString("2", bean.getData().getList_url());
                        esportsUrl.putBoolean("esportShown", true);
                        esportsUrl.apply();
                        if (homeFragment!=null)
                        leftSvag.postDelayed(() -> homeFragment.showEsport(), 500);

                    } else {
                        LogUtils.e(bean.getMsg());
                    }
                }

                @Override
                public void onStartRequest() {

                }
            });
    }

    private void BindingShare() {
        ShareTrace.getInstallTrace(new ShareTraceInstallListener() {
            @Override
            public void onInstall(AppData data) {
                LogUtils.e("绑定分享=" + data.toString());
                if (null != data) {
                    setData(data);
                }
            }

            @Override
            public void onError(int code, String msg) {
                LogUtils.e("绑定分享失败=code=" + code + "==msg=" + msg);
            }
        });
    }

    private void setData(AppData data) {
        if (SPUtils.getUserStatus() == 1 && !SPUtils.getBoolean("shareId", false)) {
            if (data != null && data.paramsData != null && !data.getParamsData().equals("null") && data.getParamsData().contains("=")) {
                String str = data.getParamsData().substring(data.getParamsData().indexOf("=") + 1);
                AppApis.getShareBinding(str, this);
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (SPUtils.getLatitude().equals("") && SPUtils.getLongitude().equals("")) {
            initLocation();
        }
        if (!ServiceUtils.isServiceRunning(ChujianIMService.class) && !SPUtils.getIP().isEmpty()) {
            IMLoginManager.instance().reset();
            startService(new Intent(this, ChujianIMService.class));
        }
        new Handler().postDelayed(() -> requestPermisson(), 100);
    }

    @SuppressLint("MissingPermission")
    private void initLocation() {
        //获取定位服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //检查定位是否被打开
        boolean gpsIsOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gpsIsOpen) {
            //定位 并请求接口
            //定位
            // 为获取地理位置信息时设置查询条件 是按GPS定位还是network定位
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (null == location) {
                return;
            }
            SPUtils.setLatitude(String.valueOf(location.getLatitude()));
            SPUtils.setLongitude(String.valueOf(location.getLongitude()));
        } else {
            LogUtils.e("请开启GPS定位权限");
        }
    }


    private void initView() {
        tabHome.setTvName("直播");
        tabPlay.setTvName("陪玩");
        tabMsg.setTvName("消息");
        tabMatch.setTvName("我的");
        tabPlay.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_FF3CE1));
        setSelectTab(1);
        //todo
        loadAnimation(btnPlay, "home_play.svga", 3);

        rightSvag.setLoops(1);
        rightSvag.setFillMode(SVGAImageView.FillMode.Forward);

        btnPlay.setLoops(1);
        btnPlay.setFillMode(SVGAImageView.FillMode.Forward);

        btnMsg.setLoops(1);
        btnMsg.setFillMode(SVGAImageView.FillMode.Forward);

        leftSvag.setLoops(1);
        leftSvag.setFillMode(SVGAImageView.FillMode.Forward);

        btnMsg.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                msgSelected.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {

            }
        });

        btnPlay.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                playSelected.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {

            }
        });

        leftSvag.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                //                tabHome.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                //                setCurrentTab(1);
                //                startAnimation(tabHome.getView(R.id.iv_icon));
                if (ivLeftSelect != null) {
                    ivLeftSelect.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int frame, double percentage) {

            }
        });

        rightSvag.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                //                setCurrentTab(2);
                //                tabMatch.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                //                startAnimation(tabMatch.getView(R.id.iv_icon));
                if (ivRightSelect != null) {
                    ivRightSelect.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int frame, double percentage) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences.Editor esportsUrl = getSharedPreferences("esportsUrl", Context.MODE_PRIVATE).edit();
        esportsUrl.putBoolean("esportShown", false);
        esportsUrl.apply();
        getEsport();
    }

    @Override
    protected void onDestroy() {
        if (tips != null) {
            tips.dismiss();
            tips = null;
        }
        //        unbindService(conn);
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.VISITOR_LOGIN)) {
            if (null != obj) {
                userInfo = (UserDateInfo) obj;
                if (userInfo.getCode() == 200) {
                    SPUtils.saveUserTokenInfo(userInfo.getData());
                    /* 绑定后台Service和启动登录消息服务器 */
                    startService(new Intent(this, ChujianIMService.class));
                    Log.e("===", userInfo.getData().toString());
                } else {
                    Toast.makeText(this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (url.equals(Urls.GET_GIFT_LIST)) {
            if (obj != null && obj instanceof LiveGiftInfoBean) {
                LiveGiftInfoBean giftInfo = (LiveGiftInfoBean) obj;
                if (giftInfo.getCode() == 200) {
                    LiveGiftUtils.get().setGiftList(giftInfo.getData());
                }
            }
        } else if (url.equals(Urls.SHARE_BINDING)) {
            if (obj != null) {
                try {
                    JSONObject object = new JSONObject(obj.toString());
                    if (object.getInt("code") == 200) {
                        SPUtils.putBoolean("shareId", true);
                    } else {
                        SPUtils.putBoolean("shareId", false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } else if (url.equals(Urls.VERSION_CHECK)) {
            if (obj != null && obj instanceof UpdateInfo) {
                UpdateInfo info = (UpdateInfo) obj;
                if (info.getCode() == 200) {
                    if (!info.getData().getVersions().equals(AppUtils.getAppVersionName(getPackageName()))) {
                        showUpdateDialog(info.getData());
                    }
                }
            }
        }
    }

    @OnClick({R.id.tab_home, R.id.tab_match, R.id.go_live_attention, R.id.tab_play, R.id.tab_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                setSelectTab(3);
                //                startAnimation(tabHome.getView(R.id.iv_icon), tabMatch.getView(R.id.iv_icon));
                loadAnimation(leftSvag, "home_live.svga", 1);
                break;
            case R.id.tab_match:
                setSelectTab(2);
                //                startAnimation(tabMatch.getView(R.id.iv_icon), tabHome.getView(R.id.iv_icon));
                loadAnimation(rightSvag, "home_mine.svga", 2);
                break;
            case R.id.tab_play:
                setSelectTab(1);
                //                startAnimation(tabMatch.getView(R.id.iv_icon), tabHome.getView(R.id.iv_icon));
                loadAnimation(btnPlay, "home_play.svga", 3);
                break;
            case R.id.tab_msg:
                setSelectTab(4);
                //                startAnimation(tabMatch.getView(R.id.iv_icon), tabHome.getView(R.id.iv_icon));
                loadAnimation(btnMsg, "home_msg.svga", 4);
                break;
            case R.id.go_live_attention:
                if (OnClickUtils.isFastClick()) {
                    startActivity(new Intent(this, MyPremiereActivity.class));
                }
                break;
        }
    }

    public static SVGAParser instance(Context context) {
        synchronized (SVGAParser.class) {
            if (parser == null) {
                parser = new SVGAParser(context);
            }
            return parser;
        }
    }

    private void loadAnimation(SVGAImageView svga, String urlName, int type) {
        switch (type) {
            case 1:
                rightSvag.stopAnimation();
                rightSvag.pauseAnimation();
                rightSvag.setVisibility(View.GONE);
                btnPlay.stopAnimation();
                btnPlay.pauseAnimation();
                btnPlay.setVisibility(View.GONE);
                btnMsg.stopAnimation();
                btnMsg.pauseAnimation();
                btnMsg.setVisibility(View.GONE);
                break;
            case 2:
                leftSvag.stopAnimation();
                leftSvag.pauseAnimation();
                leftSvag.setVisibility(View.GONE);
                btnPlay.stopAnimation();
                btnPlay.pauseAnimation();
                btnPlay.setVisibility(View.GONE);
                btnMsg.stopAnimation();
                btnMsg.pauseAnimation();
                btnMsg.setVisibility(View.GONE);
                break;
            case 3:
                rightSvag.stopAnimation();
                rightSvag.pauseAnimation();
                rightSvag.setVisibility(View.GONE);
                leftSvag.stopAnimation();
                leftSvag.pauseAnimation();
                leftSvag.setVisibility(View.GONE);
                btnMsg.stopAnimation();
                btnMsg.pauseAnimation();
                btnMsg.setVisibility(View.GONE);
                break;
            case 4:
                rightSvag.stopAnimation();
                rightSvag.pauseAnimation();
                rightSvag.setVisibility(View.GONE);
                leftSvag.stopAnimation();
                leftSvag.pauseAnimation();
                leftSvag.setVisibility(View.GONE);
                btnPlay.stopAnimation();
                btnPlay.pauseAnimation();
                btnPlay.setVisibility(View.GONE);
                break;
        }

        instance(this).decodeFromAssets(urlName, new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                ivRightSelect.setVisibility(View.GONE);
                ivLeftSelect.setVisibility(View.GONE);
                msgSelected.setVisibility(View.GONE);
                playSelected.setVisibility(View.GONE);
                switch (type) {
                    case 1:
                        tabHome.getView(R.id.iv_icon).setVisibility(View.GONE);
                        tabMatch.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabPlay.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabMsg.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        tabMatch.getView(R.id.iv_icon).setVisibility(View.GONE);
                        tabHome.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabPlay.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabMsg.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        tabMatch.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabHome.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabPlay.getView(R.id.iv_icon).setVisibility(View.GONE);
                        tabMsg.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        tabMatch.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabHome.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabPlay.getView(R.id.iv_icon).setVisibility(View.VISIBLE);
                        tabMsg.getView(R.id.iv_icon).setVisibility(View.GONE);
                        break;
                }
                if (svga != null && videoItem != null) {
                    svga.setVideoItem(videoItem);
                    svga.setVisibility(View.VISIBLE);
                    svga.startAnimation();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void startAnimation(View view) {
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 0.5f),
                Keyframe.ofFloat(1f, 1.1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 0.5f),
                Keyframe.ofFloat(1f, 1.1f)
        );
        ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY).
                setDuration(1).start();
    }

    private void endAnimation(View view) {
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(1f, 0.5f)
        );
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(1f, 0.5f)
        );
        ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY).
                setDuration(1).start();
    }


    /**
     * 切换tab
     *
     * @param tabIndex 1:首页  2:赛程  3:战队  4:关注 5:个人
     */
    public void setSelectTab(int tabIndex) {
        setCurrentTab(tabIndex);
        //        initTabIcon(tabIndex);
        setCurrentFragment(tabIndex);
    }

    public void initTabIcon(int position) {
        ivLeftSelect.setVisibility(View.GONE);
        ivRightSelect.setVisibility(View.GONE);
        tabHome.setIconResId(R.mipmap.live_icon_home_unselect_1);
        tabMatch.setIconResId(R.mipmap.live_icon_home_unselect_2);
        tabMsg.setIconResId(R.mipmap.msg_normal);
        tabPlay.setIconResId(R.mipmap.play_normal);
        tabHome.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_969696));
        tabMatch.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_969696));
        tabMsg.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_969696));
        tabPlay.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_969696));
    }

    /**
     * 切换tab
     *
     * @param currentTab 1:首页  2:赛程  3:战队  4:关注 5:个人
     */
    private void setCurrentTab(int currentTab) {
        initTabIcon(currentTab);
        switch (currentTab) {
            case 1:
                //                tabHome.setIconResId(R.mipmap.live_icon_home_select_1);
                tabPlay.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_FF3CE1));
                break;
            case 2:
                //                tabMatch.setIconResId(R.mipmap.live_icon_home_select_2);
                tabMatch.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_FF3CE1));
                break;
            case 3:
                tabHome.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_FF3CE1));
                break;
            case 4:
                tabMsg.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_FF3CE1));
                break;
        }
    }

    /**
     * 切换fragment
     *
     * @param currentTab
     */
    private void setCurrentFragment(int currentTab) {
        if (fm == null) {
            fm = getSupportFragmentManager();
        }
        if (this == null || isFinishing()) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        hideFragments(ft);
        switch (currentTab) {
            case 1:
                if (playFragment == null) {
                    playFragment = PlayWithUFragment.newInstance();
                    ft.add(R.id.fl_container, playFragment, "playFragment");
                } else {
                    ft.show(playFragment);
                }
                break;
            case 2:
                if (SPUtils.getType() == 501 || SPUtils.getIP().isEmpty()) {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                } else {//真实用户
                    if (myCenterFragment == null) {
                        myCenterFragment = new MyCenterFragment();
                        ft.add(R.id.fl_container, myCenterFragment, "myCenterFragment");
                    } else {
                        ft.show(myCenterFragment);
                    }
                }
                break;
            case 3:
                if (homeFragment == null) {
                    homeFragment = new LiveHomeFragment();
                    ft.add(R.id.fl_container, homeFragment, "homeFragment");
                } else {
                    ft.show(homeFragment);
                }
                break;
            case 4:
                if (liveMessageFragment == null) {
                    liveMessageFragment = new LiveMessageFragment();
                    ft.add(R.id.fl_container, liveMessageFragment, "liveMessageFragment");
                } else {
                    ft.show(liveMessageFragment);
                }
                break;
        }
        //        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有fragment
     *
     * @param ft
     */
    private void hideFragments(FragmentTransaction ft) {
        if (ft == null) {
            return;
        }

        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (myCenterFragment != null) {
            ft.hide(myCenterFragment);
        }

        if (playFragment != null) {
            ft.hide(playFragment);
        }
        if (liveMessageFragment != null) {
            ft.hide(liveMessageFragment);
        }
    }

    /**
     * 6.0动态申请权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSION_REQUEST_CODE_STORAGE);
        }
    }

    private void requestPermisson() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
            }, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE_STORAGE == requestCode) {
            if (grantResults != null && grantResults.length >= 1 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
                ToastUtils.showShortToast("拒绝权限可能无法正常体验所有功能");
            } else {
                initLocation();
            }
            return;
        } else if (PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults != null && grantResults.length >= 1 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermisson();
                ToastUtils.showShortToast("拒绝权限可能无法正常体验所有功能");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void TeenTipsDialog() {
        List<TeenTipsDB> dbList = TeenTipsHelper.instance(this).findAll();
        for (TeenTipsDB teenTipsDB : dbList) {
            if (teenTipsDB.getU_id().equals(SPUtils.getUserId())) {
                if (TimeUtils.getFitTimeSpanByNow(teenTipsDB.getTime(), 2).contains("天")) {
                    initDialog();
                    TeenTipsHelper.instance(this).update(teenTipsDB.getId(), teenTipsDB.getU_id(), TimeUtils.getNowString());
                }
                return;
            }
        }
        initDialog();
        TeenTipsHelper.instance(this).save(SPUtils.getUserId(), TimeUtils.getNowString());
    }

    //青少年提示弹窗
    private void initDialog() {
        AppApis.checkUpdate(this);
        tips = new DialogTeenTips(this);
        tips.show();
        tips.setDialogClickListener(type -> {
            switch (type) {
                case 1:
                    AppApplication.getInstance().exitAll();
                    break;
            }
        });
        tips.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //显示更新弹窗
    private void showUpdateDialog(UpdateInfo.DataBean data) {
        if (updateVersion == null) {
            updateVersion = new DialogUpdateVersion(this, data);
        }
        updateVersion.show();
        updateVersion.setDialogClickListener(type -> {
            switch (type) {
                case 1:
                    uploadFile(data.getUpdate_url());
                    break;
            }
        });
        updateVersion.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void uploadFile(String url) {
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir("", url.substring(url.lastIndexOf("/") + 1));
        //获取下载管理器
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        downloadManager.enqueue(request);
    }
}
