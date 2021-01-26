package com.jk.jkproject.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.dialog.DialogHostBeautySet;
import com.jk.jkproject.ui.entity.JoinRoomBeanInfo;
import com.jk.jkproject.ui.fragment.LiveRoomFragment;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.FlashUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.Zego;
import com.yuyan.statusbar.StatusBarCompat;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LiveRoomActivity extends BActivity {
    private static final int HANDLER_CONTROLLER_DURATION = 5000;

    private static final int HANDLER_HIDE_CONTROLLER = 100;

    public static boolean IS_HOST = false;

    public static String LIVE_ROOM_ID;

    private static final String TAG = "LiveRoomActivity";
    @BindView(R.id.frame_video)
    RelativeLayout frameVideo;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.frame_video1)
    RelativeLayout frameVideo1;
    @BindView(R.id.im_pk_bg)
    ImageView imPkBg;

    private Unbinder bind;

    private FragmentManager fragmentManager;

    private FragmentTransaction fragmentTransaction;

    private String liveDirection = "";

    @BindView(R.id.surfaceview)
    TextureView mPusherView;

    @BindView(R.id.surfaceview1)
    TextureView mPusherView1;

    private String pushUrl = "";

    private boolean isFlash = false;
    private boolean isMute = false;
    private boolean isCamera = false, isSkin = false;
    private int pr_1, pr_2, pr_3;

    private FragmentManager supportFragmentManager;

    private DialogHostBeautySet mHostBeautyDialog;
    private String streamID;
    private String pkRoomId;

    private boolean checkPublishPermission() {
        String[] permissionNeeded = {
                "android.permission.CAMERA",
                "android.permission.RECORD_AUDIO"};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissionNeeded, 101);
            }
        }
        return true;
    }

    private void initData() {
        checkPublishPermission();
        if (getIntent().hasExtra("liveDirection")) {
            LIVE_ROOM_ID = getIntent().getStringExtra("roomId");
            pushUrl = getIntent().getStringExtra("pullFlowUrl");
            JoinRoomBeanInfo joinRoomBeanInfo = (JoinRoomBeanInfo) getIntent().getSerializableExtra("roomBeanInfo");
            if (pushUrl == null || pushUrl.isEmpty()) {
                pushUrl = "rtmp://103597.livepush.myqcloud.com/live/3?txSecret=09d822efb1d8457a513ed27e35ce19c3&txTime=5F71D66C";
            }
            liveDirection = getIntent().getStringExtra("liveDirection");
            IS_HOST = true;
            supportFragmentManager = getSupportFragmentManager();
            fragmentTransaction = supportFragmentManager.beginTransaction();
            LiveRoomFragment.mActivity = this;
            isCamera = joinRoomBeanInfo.isCamera();
            isSkin = joinRoomBeanInfo.isSkin();
            fragmentTransaction.add(R.id.fragment_container, LiveRoomFragment.newInstance(-1, null, Integer.parseInt(LIVE_ROOM_ID), 0, null, joinRoomBeanInfo)).commit();
            initTXLive();
        } else {
            LIVE_ROOM_ID = getIntent().getStringExtra("roomId");
            IS_HOST = false;
        }
    }

    private void initTXLive() {
        if (IS_HOST) {
//            Zego.instance().initPush(true, LIVE_ROOM_ID, mPusherView, isCamera);
            setPkView(false, "-1", "");
        }
    }

    public boolean isBindEventBusHere() {
        return true;
    }

    public void onConfigurationChanged(Configuration paramConfiguration) {
        super.onConfigurationChanged(paramConfiguration);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        window.setAttributes(layoutParams);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        BarUtils.setNavBarVisibility(this,false);
        setContentView(R.layout.activity_live_room);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.black_alpha));
        this.bind = ButterKnife.bind(this);
        initData();
    }


    @Override
    public boolean isHideVirtual() {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        initTXLive();
        Zego.instance().initPkStartPreview(mPusherView);
    }

    protected void onDestroy() {
//        Zego.instance().reset();
//        MessageNotifyCenter.getInstance().deleteObserver(this);
        FlashUtils.getInstance().finishFlashUtils();
        bind.unbind();
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onEvent(PostResult result) {
        super.onEvent(result);
        if (result.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_BEAUTY_SET)) {
            // position 1 美颜 2.反转 3.静音 4.闪光灯
            int position = (int) result.getResult();
            switch (position) {
                case 1:
                    beautySet();
                    break;
                case 2:
//                    ZegoExpressEngine.getEngine().useFrontCamera(isCamera);
                    Zego.instance().useFrontCamera(isCamera);
                    isCamera = !isCamera;
                    break;
                case 3:
                    isMute = !isMute;
//                    ZegoExpressEngine.getEngine().muteMicrophone(isMute);
                    Zego.instance().muteMicrophone(isMute);
                    break;
                case 4:
                    isFlash = !isFlash;  //后置摄像头才有用
                    if (isCamera) {
                        FlashUtils.getInstance().switchFlash();
                    }
                    break;
            }
        } else if (result.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_SEND_PK) || result.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_RECEIVE_PK)) { //接收方
            pkRoomId = (String) result.getResult();
            setPkView(true, pkRoomId, result.getTag());
        } else if (result.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_FINISH_PK)) {
            setPkView(false, "", "");
        }
    }

    //PK布局初始化
    @SuppressLint("ClickableViewAccessibility")
    private void setPkView(boolean isPk, String roomId, String tag) {
        LogUtils.e("setPkView=", roomId + "=====" + tag);
        if (isPk) {
            frameVideo1.setVisibility(View.VISIBLE);
            imPkBg.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(this) / 2 - ScreenUtils.dp2px(this, 0.5f), ScreenUtils.dp2px(this, 261));
            RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(this) / 2 - ScreenUtils.dp2px(this, 0.5f), ScreenUtils.dp2px(this, 261));
            layoutParams.topMargin = ScreenUtils.dp2px(this, 79);
            layoutParams1.topMargin = ScreenUtils.dp2px(this, 79);
            frameVideo.setLayoutParams(layoutParams);
            //窗口右边
            layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            frameVideo1.setLayoutParams(layoutParams1);
            Zego.instance().initPkStartPreview();
            Zego.instance().initPkPull(false, roomId, mPusherView1);
            Zego.instance().initPkMixerTask(roomId, tag);
        } else {
            imPkBg.setVisibility(View.GONE);
            frameVideo1.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ScreenUtils.getScreenW(this), ScreenUtils.getScreenH(this) + ScreenUtils.getStatusBarHeight(this));
            frameVideo.setLayoutParams(layoutParams);
            if (pkRoomId != null) {
                LogUtils.e("setPkView=", pkRoomId);
                Zego.instance().getEngine().stopPlayingStream(pkRoomId);
                Zego.instance().reset();
                pkRoomId = null;
            }
            Zego.instance().initPush(true, LIVE_ROOM_ID, mPusherView, isCamera);
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeFlashLight(boolean openOrClose) {
        //判断API是否大于24（安卓7.0系统对应的API）
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            try {
//                //获取CameraManager
//                CameraManager mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//                //获取当前手机所有摄像头设备ID
//                String[] ids = mCameraManager.getCameraIdList();
//                for (String id : ids) {
//                    CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
//                    //查询该摄像头组件是否包含闪光灯
//                    Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
//                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
//                    if (flashAvailable != null && flashAvailable
//                            && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
//                        //打开或关闭手电筒
//                        mCameraManager.setTorchMode(id, openOrClose);
//                        LogUtils.e("=======", openOrClose);
//                        break;
//                    }
//                }
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
//        } else {
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        if (openOrClose) {
            //打开闪光灯
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);//开启
            camera.startPreview();
            camera.setParameters(parameters);
        } else {
            //关闭闪光灯
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);//关闭
            camera.stopPreview();
            camera.setParameters(parameters);
        }
//        }

    }

    //美颜设置
    private void beautySet() {
        if (mHostBeautyDialog == null) {
            mHostBeautyDialog = new DialogHostBeautySet(this, mPusherView, isSkin);
        }
        mHostBeautyDialog.show();
        mHostBeautyDialog.setDialogClickListener((pr_1, pr_2, pr_3) -> setPr(pr_1, pr_2, pr_3));

        mHostBeautyDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void setPr(int pr_1, int pr_2, int pr_3) {
        this.pr_1 = pr_1;
        this.pr_2 = pr_2;
        this.pr_3 = pr_3;
    }


    protected void onPause() {
        super.onPause();

    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") != 200)
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        }
    }

}