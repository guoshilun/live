package com.jk.jkproject.utils;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.jk.jkproject.base.AppApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import im.zego.zegoexpress.ZegoExpressEngine;
import im.zego.zegoexpress.callback.IZegoEventHandler;
import im.zego.zegoexpress.callback.IZegoMixerStartCallback;
import im.zego.zegoexpress.constants.ZegoBeautifyFeature;
import im.zego.zegoexpress.constants.ZegoMixerInputContentType;
import im.zego.zegoexpress.constants.ZegoPlayerState;
import im.zego.zegoexpress.constants.ZegoPublisherState;
import im.zego.zegoexpress.constants.ZegoRoomState;
import im.zego.zegoexpress.constants.ZegoScenario;
import im.zego.zegoexpress.constants.ZegoUpdateType;
import im.zego.zegoexpress.constants.ZegoVideoMirrorMode;
import im.zego.zegoexpress.constants.ZegoViewMode;
import im.zego.zegoexpress.entity.ZegoAudioConfig;
import im.zego.zegoexpress.entity.ZegoBeautifyOption;
import im.zego.zegoexpress.entity.ZegoCDNConfig;
import im.zego.zegoexpress.entity.ZegoCanvas;
import im.zego.zegoexpress.entity.ZegoMixerAudioConfig;
import im.zego.zegoexpress.entity.ZegoMixerInput;
import im.zego.zegoexpress.entity.ZegoMixerOutput;
import im.zego.zegoexpress.entity.ZegoMixerTask;
import im.zego.zegoexpress.entity.ZegoMixerVideoConfig;
import im.zego.zegoexpress.entity.ZegoPlayerConfig;
import im.zego.zegoexpress.entity.ZegoRoomConfig;
import im.zego.zegoexpress.entity.ZegoStream;
import im.zego.zegoexpress.entity.ZegoUser;

public class Zego {

    private static Zego inst;
    private ZegoListener listener;
    private ZegoExpressEngine engine;
    private String roomId;
    private TextureView mPusherView;
    private TextureView mPusherView1;
    private boolean isCamera;
    private ImageView mImgLoading;
    private String streamID;
    private final ZegoRoomConfig config;
    private ZegoPlayerConfig playerConfig;
    private ZegoCDNConfig cdnConfig;
    private SurfaceHolder holder;
    private ZegoCanvas zegoCanvas, zegoCanvas1;
    private String TAG = "Zego";
    private boolean isPush, isMixter = false;
    private SurfaceTexture surfaceTexture;
    private String pkStreamID;
    private ZegoMixerTask task;
    private String tag = "";
    private String mergeStreamUrl = "";
    private String cdnPushUrl = "rtmp://push-tencent.lingdangji.com/zegojg/";
    private String cdnPullUrl = "rtmp://play-tencent.lingdangji.com/zegojg/";
    private boolean isTestEnv = true;


    public void setReturnListener(ZegoListener listener) {
        this.listener = listener;
    }

    public static Zego instance() {
        if (inst == null) {
            synchronized (Zego.class) {
                if (inst == null) {
                    inst = new Zego();
                }
            }
        }
        return inst;
    }

    public Zego() {
        engine = ZegoExpressEngine.createEngine(AppApplication.appID, AppApplication.appSign, false, ZegoScenario.GENERAL, AppApplication.getInstance(), eventHandler);
        engine.enableBeautify(ZegoBeautifyFeature.POLISH.value() | ZegoBeautifyFeature.SHARPEN.value());
        config = new ZegoRoomConfig();
        config.isUserStatusNotify = true;
        playerConfig = new ZegoPlayerConfig();
        cdnConfig = new ZegoCDNConfig();
    }

    public ZegoExpressEngine getEngine() {
        return engine;
    }

    //摄像头切换
    public void useFrontCamera(boolean isCamera) {
        engine.useFrontCamera(isCamera);
    }

    //静音设置
    public void muteMicrophone(boolean isMute) {
        engine.muteMicrophone(isMute);
    }

    //设置美颜
    public void setBeautifyOption(ZegoBeautifyOption zegoBeautifyOption) {
        engine.setBeautifyOption(zegoBeautifyOption);
    }

    //拉流
    public void initPull(boolean isPush, String roomId, TextureView mPusherView) {
        mergeStreamUrl = "";
        LogUtils.e(TAG, isPush + "===" + roomId + "===");
        this.roomId = roomId;
        this.mPusherView = mPusherView;
        this.isPush = isPush;
        engine.loginRoom(roomId, new ZegoUser(SPUtils.getUserId(), SPUtils.getNickname()), config);
        zegoCanvas = new ZegoCanvas(mPusherView);
//        engine.setVideoMirrorMode(ZegoVideoMirrorMode.NO_MIRROR);
        zegoCanvas.viewMode = ZegoViewMode.ASPECT_FILL;
    }

    //pk拉流
    public void initPkPull(boolean isPush, String streamID, TextureView mPusherView) {
        mergeStreamUrl = "";
//        this.mPusherView1 = mPusherView;
//        this.isPush = isPush;
        this.pkStreamID = streamID;
        zegoCanvas = new ZegoCanvas(mPusherView);
        zegoCanvas.viewMode = ZegoViewMode.ASPECT_FILL;
        engine.startPlayingStream(streamID, zegoCanvas);
    }

    //播放rtmp流地址
    public void initPlayRtmp(String roomId, String mergeStreamUrl, TextureView mPusherView) {
        LogUtils.e("initRtmp=", mergeStreamUrl + "=====" + roomId);
        this.mergeStreamUrl = mergeStreamUrl;
        cdnConfig.url = mergeStreamUrl;
        streamID = "5ee8563c69fa4";
        playerConfig.cdnConfig = cdnConfig;
        zegoCanvas = new ZegoCanvas(mPusherView);
        zegoCanvas.viewMode = ZegoViewMode.ASPECT_FILL;
        if (this.roomId == null) {
            this.roomId = roomId;
            engine.loginRoom(roomId, new ZegoUser(SPUtils.getUserId(), SPUtils.getNickname()));
        }
        engine.startPlayingStream(streamID, zegoCanvas, playerConfig);
    }


    public void initPlayRtmp(String pullUrl, ZegoCanvas zegoCanvas) {
        LogUtils.e(TAG, "initPlayRtmp=" + pullUrl);
        cdnConfig.url = cdnPullUrl + pullUrl;
        streamID = pullUrl;
        playerConfig.cdnConfig = cdnConfig;
        engine.startPlayingStream(streamID, zegoCanvas, playerConfig);
    }

    public void initWheatPull(boolean isPush, String streamID, TextureView mPusherView) {
        mergeStreamUrl = "";
//        this.mPusherView1 = mPusherView;
        this.isPush = isPush;
        this.pkStreamID = streamID;
        zegoCanvas1 = new ZegoCanvas(mPusherView);
        zegoCanvas1.viewMode = ZegoViewMode.ASPECT_FILL;
    }

    public void initPkStartPreview(TextureView mPusherView) {
        this.mPusherView = mPusherView;
//        engine.stopPreview();
        initPkStartPreview();
    }

    public void initPkStartPreview() {
        LogUtils.e(TAG, "initPkStartPreview=");
        mergeStreamUrl = "";
        //重新预览
        zegoCanvas = new ZegoCanvas(mPusherView);
        engine.setVideoMirrorMode(ZegoVideoMirrorMode.BOTH_MIRROR);
        zegoCanvas.viewMode = ZegoViewMode.ASPECT_FILL;
        engine.startPreview(zegoCanvas);
    }


    public void initPkMixerTask(String streamID, String tag) {
        this.tag = tag;
        initPkMixerTask(streamID);
    }

    //pk混流
    public void initPkMixerTask(String streamID) {
        task = new ZegoMixerTask(SPUtils.getUserId() + "-" + streamID);
        ArrayList<ZegoMixerInput> inputList = new ArrayList<>();
        ZegoMixerVideoConfig videoConfig = new ZegoMixerVideoConfig();
        videoConfig.width = 360 * 2;

        int px = SizeUtils.dp2px(0.5f);
        ZegoMixerInput input_1 = new ZegoMixerInput(SPUtils.getUserId(), ZegoMixerInputContentType.VIDEO, new Rect(0, 0, videoConfig.width / 2 - px, videoConfig.height));
        input_1.soundLevelID = 123;
        inputList.add(input_1);

        ZegoMixerInput input_2 = new ZegoMixerInput(streamID, ZegoMixerInputContentType.VIDEO, new Rect(videoConfig.width / 2 + px, 0, videoConfig.width, videoConfig.height));
        input_2.soundLevelID = 1235;
        inputList.add(input_2);

        ArrayList<ZegoMixerOutput> outputList = new ArrayList<>();
        //混流输出目标，URL 或者流 ID，若为 URL 格式 目前只支持 RTMP URL 格式：rtmp://xxxxxxxx
        String randomSuffix = "mix-" + SPUtils.getUserId() + "-" + streamID + "-" + String.valueOf(new Date().getTime() % (new Date().getTime() / 1000));
//        ZegoMixerOutput output = new ZegoMixerOutput("99999999");
        ZegoMixerOutput output = new ZegoMixerOutput(randomSuffix);
        ZegoMixerAudioConfig audioConfig = new ZegoMixerAudioConfig();
        task.setVideoConfig(videoConfig);
        task.setAudioConfig(audioConfig);
        outputList.add(output);
        task.enableSoundLevel(true);
        task.setInputList(inputList);
        task.setOutputList(outputList);

        engine.startMixerTask(task, mixerStartCallback);
        engine.setAudioConfig(new ZegoAudioConfig());
//        engine.setVideoConfig(new ZegoVideoConfig());
    }

    public void stopMixerTask() {
        if (null != task) {
            engine.stopMixerTask(task, i -> {
                LogUtils.e(TAG, i);
            });
        }
    }

    /**
     * 如果用的是express sdk，需要用直推CDN的方法来推流。
     * 这个是推流地址：rtmp://push-tencent.lingdangji.com/zegojg/流id
     * 对应的拉流地址是：rtmp://play-tencent.lingdangji.com/zegojg/流id
     *
     * @param streamID
     * @param mPusherView
     */

    public void startPush(String streamID, TextureView mPusherView) {
        mergeStreamUrl = "";
        this.isPush = true;
        //开始推流
        zegoCanvas = new ZegoCanvas(mPusherView);
        engine.setVideoMirrorMode(ZegoVideoMirrorMode.BOTH_MIRROR);
        zegoCanvas.viewMode = ZegoViewMode.ASPECT_FILL;
        engine.startPreview(zegoCanvas);

        // URL 需要开发者根据实际情况填写
        if (!isTestEnv) {
            cdnConfig.url = cdnPushUrl + streamID;
            engine.enablePublishDirectToCDN(true, cdnConfig);
        }
        engine.startPublishingStream(streamID);
        if (isCamera) {
            useFrontCamera(false);
        }
    }

    public void startPush(String streamID) {
        startPush(streamID, mPusherView);
    }


    public void reset() {
        tag = "";
        if (roomId != null) {
            if (isPush) {
                engine.stopPreview();
                engine.stopPublishingStream();
                engine.useFrontCamera(true);
                ZegoBeautifyOption zegoBeautifyOption = new ZegoBeautifyOption();
                zegoBeautifyOption.sharpenFactor = 0;
                zegoBeautifyOption.whitenFactor = 0;
                zegoBeautifyOption.polishStep = 0;
                setBeautifyOption(zegoBeautifyOption);
                if (null != task) {
                    stopMixerTask();
                }
            } else {
                LogUtils.e(TAG, roomId + "===" + streamID);
                if (streamID != null) {
                    engine.stopPlayingStream(streamID);
                }
            }
            engine.logoutRoom(roomId);
            pkStreamID = null;
            roomId = null;
        }
    }

    public void finsh() {
        ZegoExpressEngine.destroyEngine(() -> LogUtils.e(TAG, "销毁成功"));
        inst = null;
    }


    //推流
    public void initPush(boolean isPush, String roomId, TextureView mPusherView, boolean isCamera) {
        this.roomId = roomId;
        this.isPush = isPush;
        this.isCamera = isCamera;
        this.mPusherView = mPusherView;
        /**
         * 创建引擎单例对象
         *
         * 在调用其他 API 前需要先创建并初始化引擎；SDK 只支持创建一个 ZegoExpressEngine 实例，多次调用此接口返回的都是同一个对象。
         * @param appID ZEGO 为开发者签发的应用 ID，请从ZEGO 管理控制台 https://console-express.zego.im/ 申请，取值范围为 0~4294967295。
         * @param appSign 每个 AppID 对应的应用签名，请从 ZEGO 管理控制台申请。为 64 字符的字符串。每个字符范围为'0'~'9', 'a'~'z'。
         * @param isTestEnv 选择使用测试环境还是正式商用环境，正式环境需要在 ZEGO 管理控制台提交工单配置。测试环境为测试开发用，限制房间数为 30，用户数为 230。正式环境 App 正式上线用，ZEGO 会根据开发者在管理控制台提交的配置记录提供相应的服务资源。测试环境与正式环境是两套环境，不可互通。
         * @param scenario 所属的应用场景，开发者可根据所开发的 App 的场景选择其中一个，引擎会通过所设置的场景预设置一个针对具体场景的较为通用的设置。当设置具体场景后，开发者若有定制化参数设置，依然可以调用具体接口来设置具体参数。
         * @param application 安卓应用上下文
         * @param eventHandler 事件通知回调。传 [null] 则意味着不接收任何回调通知。之后也可通过 [setEventHandler] 进行设置
         * @return 引擎单例对象
         */
//        String randomSuffix = String.valueOf(new Date().getTime() % (new Date().getTime() / 1000));
//        streamID = "streamid-" + randomSuffix;
        streamID = SPUtils.getUserId();
        engine.loginRoom(roomId, new ZegoUser(SPUtils.getUserId(), SPUtils.getNickname()), config);
        engine.setVideoMirrorMode(ZegoVideoMirrorMode.NO_MIRROR);
    }


    IZegoMixerStartCallback mixerStartCallback = (errorCode, extendedData) -> {
        if (errorCode != 0) {
            if (errorCode == 1005026) {
                isMixter = true;
            }
            LogUtils.e(TAG, "混流errorCode=" + errorCode);
        } else {
            isMixter = false;
            LogUtils.e(TAG, "Mixing success");
        }
        //{"mixer_output_list":[{"stream_id":"99999999","rtmp_url":"rtmp:\/\/rtmp.wsdemo.zego.im\/miniapp\/zegotest-2250166144-99999999","flv_url":"http:\/\/hdl-wsdemo.zego.im\/miniapp\/zegotest-2250166144-99999999.flv","hls_url":"http:\/\/hls.wsdemo.zego.im\/miniapp\/zegotest-2250166144-99999999\/playlist.m3u8"}]}
        try {
            JSONObject jsonObject = new JSONObject(extendedData.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("mixer_output_list");
            JSONObject jsonObject2 = jsonArray.getJSONObject(0);
            String rtmp_url = jsonObject2.getString("rtmp_url");
            if (listener != null) {
                listener.mixerStartCallback(rtmp_url, tag);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.e(TAG, extendedData.toString());
    };

    IZegoEventHandler eventHandler = new IZegoEventHandler() {
        @Override
        public void onRoomStreamUpdate(String roomID, ZegoUpdateType updateType, ArrayList<ZegoStream> streamList) {
            super.onRoomStreamUpdate(roomID, updateType, streamList);
            LogUtils.e(TAG, "🚩 🌊 onRoomStreamUpdate: roomID=" + roomID + ", updateType:" + updateType.value() + ", streamList: " + streamList.size());
//            listener.onRoomStreamUpdate(roomID, updateType, streamList);
            // Add the rendered view dynamically after pulling the stream here
            if (!mergeStreamUrl.equals("")) {
                return;
            }
            if (!isPush) {
                if (updateType == ZegoUpdateType.ADD) {
                    for (ZegoStream zegoStream : streamList) {
                        LogUtils.e(TAG, "🚩 🌊 onRoomStreamUpdate: streamID=" + zegoStream.streamID + "=pkStreamID=" + pkStreamID);
                        if (pkStreamID != null && zegoStream.streamID.equals(pkStreamID)) {
//                            initPkPull(false, streamID, mPusherView1);
                            if (!isTestEnv) {
                                initPlayRtmp(zegoStream.streamID, zegoCanvas1);
                            } else {
                                engine.startPlayingStream(zegoStream.streamID, zegoCanvas1);
                            }
                        } else {
                            if (!isTestEnv) {
                                initPlayRtmp(zegoStream.streamID, zegoCanvas);
                            } else {
                                streamID = zegoStream.streamID;
                                engine.startPlayingStream(zegoStream.streamID, zegoCanvas);
                            }
                        }
                    }
                } else if (updateType == ZegoUpdateType.DELETE) {
                    for (ZegoStream zegoStream : streamList) {
                        engine.stopPlayingStream(zegoStream.streamID);
                    }
                }
            }
        }

        @Override
        public void onCapturedSoundLevelUpdate(float soundLevel) {
            super.onCapturedSoundLevelUpdate(soundLevel);
            LogUtils.e(TAG, "onCapturedSoundLevelUpdate:" + soundLevel);
        }

        @Override
        public void onRemoteSoundLevelUpdate(HashMap<String, Float> soundLevels) {
            super.onRemoteSoundLevelUpdate(soundLevels);
            LogUtils.e(TAG, "onRemoteSoundLevelUpdate:" + soundLevels.size());
        }

        @Override
        public void onCapturedAudioSpectrumUpdate(float[] frequencySpectrum) {
            super.onCapturedAudioSpectrumUpdate(frequencySpectrum);
            LogUtils.e(TAG, "call back onCapturedAudioSpectrumUpdate");
        }

        @Override
        public void onRemoteAudioSpectrumUpdate(HashMap<String, float[]> frequencySpectrums) {
            super.onRemoteAudioSpectrumUpdate(frequencySpectrums);
            LogUtils.e(TAG, "call back onRemoteAudioSpectrumUpdate:" + frequencySpectrums);
        }

        @Override
        public void onRoomStateUpdate(String roomID, ZegoRoomState state, int errorCode, JSONObject extendedData) {
            /** 调用拉流接口成功后，当拉流器状态发生变更，如出现网络中断导致推流异常等情况，SDK在重试拉流的同时，会通过该回调通知 */
            if (state == ZegoRoomState.CONNECTED && errorCode == 0) {
                LogUtils.e(TAG, "🚩 🚪 Login room success");
                if (isPush && streamID != null) {
                    startPush(streamID);
                }
            }
            if (errorCode == 1002051) { //重连

            }
            if (errorCode != 0) {
                LogUtils.e(TAG, "🚩 ❌ 🚪 Login room fail, errorCode: " + errorCode);
            }
        }

        @Override
        public void onPublisherStateUpdate(String streamID, ZegoPublisherState state, int errorCode, JSONObject extendedData) {
            super.onPublisherStateUpdate(streamID, state, errorCode, extendedData);
            LogUtils.e(TAG, "🚩 🌊 onPublisherStateUpdate: streamID=" + streamID + ", state:" + state.value() + ", errorCode: " + errorCode);
            if (isPush && errorCode == 1003001) {

            }
        }

        @Override
        public void onPlayerStateUpdate(String streamID, ZegoPlayerState state, int errorCode, JSONObject extendedData) {
            super.onPlayerStateUpdate(streamID, state, errorCode, extendedData);
            LogUtils.e(TAG, "onPlayerStateUpdate:" + streamID + "=state:" + state.name() + "=errorCode:" + errorCode);
            if (errorCode == 0 && state.name().equals(ZegoPlayerState.PLAYING.name())) {
                if (null != pkStreamID && pkStreamID.equals(streamID) && isMixter) {
                    stopMixerTask();
                    initPkMixerTask(streamID);
                }
//                else if (pkStreamID != null) {
//                    if (streamID.equals(pkStreamID)) {
//                        initPkPull(false, streamID, mPusherView1);
//                    }
//                }
            } else if (errorCode == 1004001) {

            }
        }
    };

    public interface ZegoListener {
        //混流后返回地址
        void mixerStartCallback(String rtmp_url, String tag);
    }
}
