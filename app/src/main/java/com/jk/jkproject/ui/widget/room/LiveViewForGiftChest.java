package com.jk.jkproject.ui.widget.room;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.fresco.helper.utils.DensityUtil;
import com.jk.jkproject.R;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.entity.LiveChestInfo;
import com.jk.jkproject.ui.entity.LiveGift;
import com.jk.jkproject.ui.inter.LiveChestGiftAnimListener;
import com.jk.jkproject.ui.inter.LiveGiftActionListener;
import com.jk.jkproject.utils.Constants;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import cn.iwgang.countdownview.CountdownView;

/**
 * 宝箱动画控件
 * Created by gaobl on 2016/6/2.
 */
public class LiveViewForGiftChest extends FrameLayout implements LiveChestGiftAnimListener, View.OnClickListener, ResponseListener, Observer {

    // 背景流星雨动画开启
    private static final int ANIM_START = 1;
    // 所有操作都结束了
    private static final int ANIM_ALL_FINISHED = 5;
    // 增加次数
    private static final int ANIM_ADD_TIMES = 6;
    // 检查宝箱状态
    private static final int CHECK_CHEST_STATE = 7;
    // 宝箱结束
    private static final int CHEST_END = 8;
    // 还原点击打开的宝箱
    private static final int ANIM_CHEST_OPENED = 9;
    // 宝箱在收到结束推送,倒计时结束后消失
    private static final int ANIM_CHEST_DISMISS = 10;
    private static final int DELAYED_TIME_CHEST_RIGHT_OPENED = 150 * 4;
    private static final int DELAYED_TIME_CHECK_CHEST = 14000;
    private static final int DELAYED_TIME_CHEST_DISMISS = 5000;
    private static final int ROCK_REPEAT_COUNT = -1;
    //右边流星雨倒计时
    private RelativeLayout relateRight;
    //整个背景
    private ImageView ivBgFireworks;
    // 右边晃动的宝箱
    private ImageView ivChestRight;
    // 宝箱数量控件
    private StrokeTextView tvCount;
    // 宝箱倒计时控件
    private CountdownView cvCountDown;
    private FrameAnimation frameAnimation;
    // 右边的宝箱晃动动画
    private ObjectAnimator animChestRightRock;
    private LiveChestInfo mInfo;
    private LiveChestInfo mNextChest;
    private LiveGiftActionListener mLiveGiftAction;
    private boolean isRunning = false;
    private boolean isGetChest = false;// 是否可以领取宝箱
    private boolean isAnimTransEnd = true;
    private int curIndex = 0;//当前第几个
    private int chestCount = 1; // 宝箱数量

    private long startTime;
    private boolean mIsCurEnd = false;

    private String live_id;
    // 宝箱礼物
    private LinkedList<LiveChestInfo> mChestGifts = new LinkedList<>();
    // 已删除的宝箱
    private LinkedList<LiveChestInfo> mEndChestGifts = new LinkedList<>();

    private boolean isPlaying = false;
    private Context context;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ANIM_START:
                    // 背景流星雨动画开启
                    // MLog.e("LiveViewForGiftChest", "livegift>>>>>>change state bg start   ivChestRight.setVisibility(GONE)");
                    setVisibility(VISIBLE);
                    ivBgFireworks.setVisibility(VISIBLE);
                    ivChestRight.setVisibility(GONE);
                    cvCountDown.setVisibility(GONE);
                    setChestCount(0);
                    if (mInfo != null) {
                        countDown(mInfo.getTtl());
                        cvCountDown.setVisibility(GONE);
                    }
                    // MLog.e("LiveViewForGiftChest", " ANIM_START   ");
                    animRain();
                    break;

                case ANIM_ALL_FINISHED:
                    removeMessages(CHECK_CHEST_STATE);
                    removeMessages(ANIM_CHEST_DISMISS);

                    // 所有操作都结束了
                    // MLog.e("LiveViewForGiftChest", "livegift>>>>>>change state all finished   ivChestRight.setVisibility(GONE)");
                    isRunning = false;
                    isGetChest = false;
                    isAnimTransEnd = true;
                    // MLog.e("LiveViewForGiftChest", "isAnimTransEnd  582" + isAnimTransEnd);
                    mIsCurEnd = false;
                    startTime = 0;
                    curIndex = 0;
                    chestCount = 1;
                    mInfo = null;
                    setVisibility(GONE);
                    ivBgFireworks.setVisibility(GONE);
                    ivChestRight.setVisibility(GONE);
                    cvCountDown.setVisibility(GONE);
                    tvCount.setText("");
                    if (mLiveGiftAction != null) {
                        mLiveGiftAction.pollChestGift();
                    }
                    break;
                case ANIM_ADD_TIMES:
                    // 再次开启流星雨动画
                    // MLog.e("LiveViewForGiftChest", "livegift>>>>>>change state add times isAnimTransEnd:   602  " + isAnimTransEnd);
                    if (isAnimTransEnd) {
                        isAnimTransEnd = false;
                        setVisibility(VISIBLE);
                        ivBgFireworks.setVisibility(VISIBLE);
                        if (!isPlaying && null != frameAnimation) {
                            isPlaying = true;
                            frameAnimation.restartAnimation(0);
                        }
                    }
                    break;
                case CHECK_CHEST_STATE:
                    // MLog.e("LiveViewForGiftChest", "handler 检查本宝箱状态");
                    isGetChest = false;
                    // 检查本宝箱状态
                    if (mInfo != null) {
                        // MLog.e("LiveViewForGiftChest", "handler 检查本宝箱状态" + mInfo.getKey());
//                        AppApis.liveCheckChest(mInfo.getKey(), live_id, LiveViewForGiftChest.this);
                    }
                    break;
                case CHEST_END:
                    isGetChest = false;
                    mIsCurEnd = false;
                    startTime = 0;
                    removeMessages(CHECK_CHEST_STATE);
                    if (msg.obj != null && msg.obj instanceof String) {
                        String endChestStr = (String) msg.obj;
//                        LiveChestEndInfo endInfo = GsonUtils.get().fromJson(endChestStr, LiveChestEndInfo.class);
//                        if (endInfo != null) {
//                            // 本宝箱结束
//                            // MLog.e("LiveViewForGiftChest", "handler 宝箱结束 CHEST_END endInfo: " + endInfo.toString());
////                            LiveChestInfo chest = new LiveChestInfo(endInfo.getOver_chest_key(), -1);
////                            chestEnd(chest);
//                            // MLog.e("LiveViewForGiftChest", "已经结束的宝箱 mEndChestGifts.size() : " + mEndChestGifts.size());
//                            // MLog.e("LiveViewForGiftChest", "已经结束的宝箱 mEndChestGifts : " + mEndChestGifts);
//                            // MLog.e("LiveViewForGiftChest", "已经结束的宝箱 mEndChestGifts.contains : " + mEndChestGifts.contains(chest));
//                            if (mEndChestGifts != null && mEndChestGifts.contains("chest")) {
//                                removeMessages(ANIM_ALL_FINISHED);
//                                // MLog.e("LiveViewForGiftChest", "mEndChestGifts contains : " + chest.toString());
//                                return;
////                            } else if (chest.equals(mInfo) && !TextUtils.isEmpty(endInfo.getNext_chest_key())) {
////                                removeMessages(ANIM_ALL_FINISHED);
////
////                                if (mEndChestGifts != null && !mEndChestGifts.contains(chest)) {
////                                    // MLog.e("LiveViewForGiftChest", "mEndChestGifts add : " + chest.toString());
////                                    mEndChestGifts.add(chest);
////                                }
////                                curIndex--;
//////                                mNextChest = new LiveChestInfo(endInfo.getNext_chest_key(), endInfo.getNext_chest_ttl());
////                                if (cvCountDown.getVisibility() == VISIBLE && cvCountDown.getRemainTime() > 0) {
////                                    // 当前倒计时还没结束,已经收到结束通知
////                                    mIsCurEnd = true;
////                                    startTime = System.currentTimeMillis();
////                                } else {
////                                    // MLog.e("LiveViewForGiftChest", "livegift>>>>>>ANIM_ALL_FINISHED1");
////                                    animChestRightAlphaOut();
////                                }
////                            } else {
////                                mNextChest = null;
////                                // MLog.e("LiveViewForGiftChest", "livegift>>>>>>ANIM_ALL_FINISHED2");
////                                animChestRightAlphaOut();
////                            }
//                        } else {
//                            mNextChest = null;
//                            // MLog.e("LiveViewForGiftChest", "livegift>>>>>>ANIM_ALL_FINISHED3");
//                            animChestRightAlphaOut();
//                        }
                    } else {
                        mNextChest = null;
                        // MLog.e("LiveViewForGiftChest", "livegift>>>>>>ANIM_ALL_FINISHED4");
                        animChestRightAlphaOut();
                    }
                    break;
                case ANIM_CHEST_OPENED:
                    // 还原点击打开的宝箱
                    ((AnimationDrawable) ivChestRight.getBackground()).stop();
                    break;
                case ANIM_CHEST_DISMISS:
                    // 宝箱在收到结束推送,倒计时结束后消失
                    removeMessages(CHECK_CHEST_STATE);
                    // MLog.e("LiveViewForGiftChest", "ANIM_CHEST_DISMISS", "livegift>>>>>>ANIM_ALL_FINISHED5");
                    animChestRightAlphaOut();
                    break;
                default:
                    break;
            }
        }
    };

    public LiveViewForGiftChest(Context context) {
        super(context);
        init(context);
    }

    public LiveViewForGiftChest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LiveViewForGiftChest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_view_live_view_for_gift_chest, this, true);
        this.context = context;
        relateRight = findViewById(R.id.relate_gift_chest_right);
        ivBgFireworks = (ImageView) findViewById(R.id.iv_live_gift_chest_bg_fireworks);
        cvCountDown = (CountdownView) findViewById(R.id.cv_gift_chest_countdown);
        ivChestRight = (ImageView) findViewById(R.id.iv_gift_chest_right);
        tvCount = (StrokeTextView) findViewById(R.id.tv_gift_chest_count);
        ivChestRight.setOnClickListener(this);
        cvCountDown.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                                                  @Override
                                                  public void onEnd(CountdownView cv) {
                                                      cvCountDown.setVisibility(GONE);
                                                      canGetChest(false, true);
                                                      if (animChestRightRock != null && animChestRightRock.isRunning()) {
                                                          animChestRightRock.cancel();
                                                      }
                                                      if (mIsCurEnd) {
                                                          sendEmptyMessageDelayed(ANIM_CHEST_DISMISS, DELAYED_TIME_CHEST_DISMISS);
                                                      }
                                                  }
                                              }

        );
        MessageNotifyCenter.getInstance().addObserver(this);
    }

    /**
     * 设置live_id
     *
     * @param live_id
     */
    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    @Override
    public void onClick(View v) {
        // MLog.e("LiveViewForGiftChest", "宝箱被鸡蛋肌了，");
        if (v.getId() == R.id.iv_gift_chest_right) {

            if (mInfo != null && isGetChest) {
                isGetChest = false;
                // MLog.e("LiveViewForGiftChest", "liveGetChest，" + mInfo.getKey());
//                AppApis.liveGetChest(mInfo.getKey(), live_id, this);
                if (animChestRightRock != null && animChestRightRock.isRunning()) {
                    animChestRightRock.cancel();
                }
                // 开宝箱动画
                ((AnimationDrawable) ivChestRight.getBackground()).start();
                // 还原点击打开的宝箱
                sendEmptyMessageDelayed(ANIM_CHEST_OPENED, DELAYED_TIME_CHEST_RIGHT_OPENED);
                return;
            }
            if (cvCountDown != null && cvCountDown.getRemainTime() < 1) {
                isGetChest = false;
                // MLog.e("LiveViewForGiftChest", "liveGetChest，" + mInfo.getKey());
//                AppApis.liveGetChest(mInfo.getKey(), live_id, this);
                if (animChestRightRock != null && animChestRightRock.isRunning()) {
                    animChestRightRock.cancel();
                }
                // 开宝箱动画
                ((AnimationDrawable) ivChestRight.getBackground()).start();
                // 还原点击打开的宝箱
                sendEmptyMessageDelayed(ANIM_CHEST_OPENED, DELAYED_TIME_CHEST_RIGHT_OPENED);
            }
        }
    }

    @Override
    public void onSuccess(String url, Object obj) {
        if (this == null || this.getVisibility() != VISIBLE || obj == null) {
            return;
        }
        // MLog.e("LiveViewForGiftChest", "onSuccess" + obj.toString());
//        if (TextUtils.equals(Urls.LIVE_GET_CHEST, url)) {
//            // 领取宝箱
//            LiveChestResponse info = (LiveChestResponse) obj;
//            if (info != null && info.getStatus() == 1) {
//                // MLog.e("LiveViewForGiftChest", "tag 已领取 key" + info.getPub_key() + "现有的宝箱key：" + mChestGifts.size() + "已经结束的宝箱：" + mEndChestGifts.size());
//                chestEnd(new LiveChestInfo(info.getPub_key(), -1));
//                String text;
//                if (info.getIs_taken() == 1) {
//                    // 已领取
//                    text = "已领取" + info.getUser_nickname() + "的宝箱" + info.getDiamond_take() + "橙币";
//                } else {
//                    text = info.getTooltip();
//                }
//                Bundle b = new Bundle();
//                b.putInt("method", Constants.OBSERVABLE_ACTION_LIVE_CHEST_GET_TIPS);
//                b.putString("text", text);
//                MessageNotifyCenter.getInstance().doNotify(b);
//
//            } else if (info != null && info.getStatus() == 0) {
//                Bundle b = new Bundle();
//                b.putInt("method", Constants.OBSERVABLE_ACTION_LIVE_CHEST_GET_TIPS);
//                b.putString("text", info.getError());
//                MessageNotifyCenter.getInstance().doNotify(b);
//                isGetChest = true;
//            }
//        } else if (TextUtils.equals(Urls.LIVE_CHECK_CHEST, url)) {
//            // 检查宝箱状态
//            BaseInfo info = (BaseInfo) obj;
//            if (info != null && info.getCode() == 200) {
//                if (handler != null) {
//                    // MLog.e("LiveViewForGiftChest", "检测宝箱状态返回结果：" + info.getStatus());
//                    handler.sendEmptyMessageDelayed(CHECK_CHEST_STATE, 2000);
//                }
//            }
//        }
    }

    @Override
    public void onStartRequest() {

    }

    @Override
    public void onFailure(int code, String url, String error) {

        // MLog.e("LiveViewForGiftChest", "onFailure" + error);
//        if (TextUtils.equals(Urls.LIVE_GET_CHEST, url)) {
//            // 领取宝箱请求出错
//            isGetChest = true;
//        } else if (TextUtils.equals(Urls.LIVE_CHECK_CHEST, url)) {
//            if (handler != null) {
//                handler.sendEmptyMessageDelayed(CHECK_CHEST_STATE, 2000);
//            }
//        }
    }

    @Override
    public void update(Observable observable, Object data) {
        if (this == null || this.getVisibility() != VISIBLE || data == null || !(data instanceof Bundle)) {
            return;
        }

        Bundle bd = (Bundle) data;
        int method = bd.getInt("method");
        if (method == Constants.OBSERVABLE_ACTION_LIVE_CHEST_END) {
            // 宝箱结束
            String endChestInfo = bd.getString("object");
            if (!TextUtils.isEmpty(endChestInfo)) {
                // MLog.e("LiveViewForGiftChest", "update:" + endChestInfo);

                if (handler != null) {
                    Message msg = handler.obtainMessage(CHEST_END);
                    // MLog.e("LiveViewForGiftChest", "update:" + endChestInfo);
                    msg.obj = endChestInfo;
                    msg.sendToTarget();
                }
            }
        }
    }

    private void anim() {
        sendEmptyMessageDelayed(ANIM_START, 0);
    }

    /**
     * 右边的宝箱淡出
     */
    private void animChestRightAlphaOut() {
        AlphaAnimation alphaOut = new AlphaAnimation(1f, 0f);
        alphaOut.setDuration(1000);
        alphaOut.setFillAfter(true);
        alphaOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 开启下一个宝箱
                if (mNextChest != null) {
                    // MLog.e("LiveViewForGiftChest", "livegift>>>>>>ANIM_ALL_FINISHED  mNextChest" + mNextChest.getKey());
                    startNextChest(mNextChest);
                } else {
                    // MLog.e("LiveViewForGiftChest", "livegift>>>>>>ANIM_ALL_FINISHED");
                    if (getVisibility() == VISIBLE) {
                        sendEmptyMessageDelayed(ANIM_ALL_FINISHED, 0);
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ivChestRight.startAnimation(alphaOut);
    }

    /**
     * 右边的宝箱淡入
     */
    private void animChestRightAlphaIn(long duration) {
        AlphaAnimation alphaIn = new AlphaAnimation(0f, 1f);
        alphaIn.setDuration(duration);
        alphaIn.setFillAfter(true);
        ivChestRight.startAnimation(alphaIn);
    }

    /**
     * 把中间的宝箱移动到右边
     */
    private void transChest() {
        ivChestRight.setVisibility(View.VISIBLE);
        animChestRightAlphaIn(10);
        ((AnimationDrawable) ivChestRight.getBackground()).stop();
        isAnimTransEnd = true;
        // MLog.e("LiveViewForGiftChest", "isAnimTransEnd   301 curIndex：" + curIndex);
        if (curIndex == 0) {
            //cvCountDown.getVisibility() != VISIBLE && cvCountDown.getRemainTime() > 0) {
            // 还在隐藏着倒计时,则显示
            // MLog.e("LiveViewForGiftChest", "transEnd countdown RemainTime:" + cvCountDown.getRemainTime());
            if (cvCountDown.getRemainTime() > 0) {
                // 第一个动画结束后,若宝箱还在倒计时,则显示倒计时
                cvCountDown.setVisibility(VISIBLE);
                startRock();
            } else {
                // 第一个动画结束后,若宝箱倒计时结束,则
                canGetChest(false, true);
            }
        } else if (cvCountDown.getVisibility() == VISIBLE && cvCountDown.getRemainTime() > 0) {//animChestRightRock != null && animChestRightRock.isRunning()) {
            // 此刻前一个宝箱还正在倒计时
            // MLog.e("LiveViewForGiftChest", "transEnd countdown ing 316  curIndex：" + curIndex);
        } else if (handler != null && handler.hasMessages(CHECK_CHEST_STATE)) {
            // 此刻前一个宝箱倒计时已结束,在等待检查宝箱状态
            // MLog.e("LiveViewForGiftChest", "transEnd waiting for check chest state  319 curIndex：" + curIndex);
        } else {
            // MLog.e("LiveViewForGiftChest", "transEnd else 321  curIndex：" + curIndex);
        }
        setChestCount(++curIndex);
//        if (null != mChestGifts) {
//            if (curIndex < mChestGifts.size()) {
//                setChestCount(curIndex);
//            } else {
//                setChestCount(mChestGifts.size() + 1);
//            }
//        } else {
//            setChestCount(curIndex);
//        }
//       // MLog.e("LiveViewForGiftChest   325 ", curIndex + "::" + chestCount + "mChestGifts：" + mChestGifts.size() + "mEndChestGifts：" + mEndChestGifts.size());
        if (curIndex < chestCount) {
            // MLog.e("LiveViewForGiftChest 326   ANIM_ADD_TIMES  ");
            sendEmptyMessageDelayed(ANIM_ADD_TIMES, 0);
        }
    }

    /**
     * 右侧宝箱摇摆动画
     */
    private void startRock() {
        ivChestRight.setVisibility(View.VISIBLE);
        ivChestRight.setBackgroundResource(0);
        ivChestRight.setBackgroundResource(R.drawable.live_treasure_gift_animation_open);

        if (animChestRightRock == null) {
            animChestRightRock = ObjectAnimator.ofFloat(ivChestRight, "rotation", 0f, 15f, 0f, -30f, 0, 15f, 0, -30f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animChestRightRock.setDuration(1000);
            animChestRightRock.setRepeatCount(ROCK_REPEAT_COUNT);
        }

        if (animChestRightRock != null) {
            animChestRightRock.start();
        }
    }

    @Override
    public String getAnimate() {
        return LiveGift.LIVE_GIFT_ANIMATE_FIREWORKS;
    }

    @Override
    public void setLiveGiftActionListener(LiveGiftActionListener listener) {
        this.mLiveGiftAction = listener;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void addTimes(LiveChestInfo info) {
        setVisibility(VISIBLE);
//       // MLog.e("LiveViewForGiftChest", " 370  add info " + mInfo.getTtl() + "::" + mInfo.getKey() + "  info ：" + info.getTtl() + ":" + info.getKey());
        if (info != null && !info.equals(mInfo) && mChestGifts != null && !mChestGifts.contains(info)) {
            chestCount++;
            // MLog.e("LiveViewForGiftChest", " 373  add info " + info.getKey() + "  chestCount:" + chestCount);
            mChestGifts.add(info);
            if (this.mInfo == null && mChestGifts.size() == 1) {
                mInfo = new LiveChestInfo();
                this.mInfo = info;
            }
            sendEmptyMessageDelayed(ANIM_ADD_TIMES, 0);
            if (null != cvCountDown) {
                if (info.getTtl() > 0) {
                    countDown(info.getTtl());
                    cvCountDown.setVisibility(INVISIBLE);
                }
            }
        }
    }

    @Override
    public void startAnim(LiveChestInfo info) {
        if (info == null || TextUtils.isEmpty(info.getKey())) {
            return;
        }
        isRunning = true;
        isGetChest = false;
        isAnimTransEnd = false;
        // MLog.e("LiveViewForGiftChest", "isAnimTransEnd   384" + isAnimTransEnd);
        curIndex = 0;
        chestCount = 1;
        // MLog.e("LiveViewForGiftChest   startAnim   ", "startAnim  info" + info.getKey());
        this.mInfo = info;
        // MLog.e("LiveViewForGiftChest", "当前 startAnim  info" + mInfo.getKey());
        anim();
    }

    @Override
    public void release() {
        MessageNotifyCenter.getInstance().deleteObserver(this);
        if (mChestGifts != null) {
            mChestGifts.clear();
            mChestGifts = null;
        }
        if (mEndChestGifts != null) {
            mEndChestGifts.clear();
            mEndChestGifts = null;
        }
        if (animChestRightRock != null) {
            animChestRightRock = null;
        }
    }

    /**
     * 宝箱倒计时
     *
     * @param time 宝箱倒计时的时间：second
     */
    private void countDown(int time) {
        if (time > 0) {
            if (time >= 60) {
                // 大于60秒就显示分钟
                cvCountDown.customTimeShow(false, false, true, true, true);
            } else {
                cvCountDown.customTimeShow(false, false, false, true, true);
            }
            cvCountDown.setVisibility(VISIBLE);
            cvCountDown.start(time * 1000);
        } else {
            cvCountDown.setVisibility(GONE);
        }
    }

    /**
     * 设置宝箱数量
     *
     * @param count
     */
    private void setChestCount(int count) {
        if (tvCount != null) {
            if (count > 0) {
                if (tvCount.getVisibility() == View.INVISIBLE) {
                    tvCount.setVisibility(View.VISIBLE);
                }
                tvCount.setText("x" + count);
            } else {
                tvCount.setText("");
            }
        }
    }

    /**
     * 宝箱结束
     *
     * @param chest 宝箱
     */
    private void chestEnd(LiveChestInfo chest) {
        if (chest != null && !TextUtils.isEmpty(chest.getKey())) {
            if (mChestGifts != null && mChestGifts.contains(chest)) {
                mChestGifts.remove(chest);
            }
        }
    }

    /**
     * 可以领取宝箱了
     *
     * @param isRock       是否晃动
     * @param isCheckChest 是否检查宝箱状态
     */
    private void canGetChest(boolean isRock, boolean isCheckChest) {
        isGetChest = true;
        if (isRock) {
            startRock();
        }
        if (isCheckChest) {
            sendEmptyMessageDelayed(CHECK_CHEST_STATE, DELAYED_TIME_CHECK_CHEST);
        }
    }

    /**
     * 开启下一个宝箱
     *
     * @param info 待开启的宝箱
     */
    private void startNextChest(LiveChestInfo info) {
        if ((cvCountDown.getVisibility() == VISIBLE && cvCountDown.getRemainTime() > 0)) {
            // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest return cvCountDown");
            return;
        }

        if (handler != null && handler.hasMessages(CHECK_CHEST_STATE)) {
            // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest return handler has msg");
            return;
        }

        if (mChestGifts != null && mChestGifts.size() > 0) {
            // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest  1");

            if (info != null && mChestGifts.contains(info)) {
                // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest  1");
                mInfo = info;
                chestCount--;
                mChestGifts.remove(info);
            } else {
                // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest  1");
                mInfo = mChestGifts.poll();
            }

            if (isAnimTransEnd) {
                // MLog.e("LiveViewForGiftChest", "isAnimTransEnd   508" + isAnimTransEnd + "" + (mChestGifts.size() + 1));
                setChestCount(mChestGifts.size() + 1);
                // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest  1");
            }
            animChestRightAlphaIn(1000);
            if (mInfo.getTtl() > 0) {
                // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest  1");
                int dif = 0;
                if (mIsCurEnd && startTime > 0) {
                    // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest  1");
                    // 提前收到上一个的结束通知(下一个的开始通知),则下一个开始时减去已经过去的倒计时
                    dif = (int) ((System.currentTimeMillis() - startTime) / 1000);
                    mIsCurEnd = false;
                    startTime = 0;
                }
                countDown(mInfo.getTtl() - dif);
                startRock();
            } else {
                // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest  1");
                canGetChest(true, true);
            }
        } else {
            if (getVisibility() == VISIBLE && null == info) {
                // MLog.e("LiveViewForGiftChest", "livegift>>>>>>startNextChest-ANIM_ALL_FINISHED");
                sendEmptyMessageDelayed(ANIM_ALL_FINISHED, 0);
            }
        }
    }

    /**
     * handler 发送空消息
     *
     * @param what
     */
    private void sendEmptyMessageDelayed(int what, long delayMillis) {
        if (handler != null) {
            handler.removeMessages(ANIM_ALL_FINISHED);
            handler.removeMessages(what);
            handler.sendEmptyMessageDelayed(what, delayMillis);
        }
    }

    //开启宝箱整体动画
    private void animRain() {
        setVisibility(View.VISIBLE);
        // 每50ms一帧 循环播放动画
        ivBgFireworks.setVisibility(View.VISIBLE);
//        frameAnimation = new FrameAnimation(ivBgFireworks, getRes(), 80, false);
//        frameAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
//            @Override
//            public void onAnimationStart() {
//                isPlaying = true;
//            }
//
//            @Override
//            public void onAnimationEnd() {
//                ivBgFireworks.setVisibility(INVISIBLE);
//                isPlaying = false;
//                transChest();
//            }
//
//            @Override
//            public void onAnimationRepeat() {
//                isPlaying = true;
//            }
//        });
    }

    /**
     * 获取需要播放的动画资源
     */
//    private int[] getRes() {
//        TypedArray typedArray = getResources().obtainTypedArray(R.array.rain);
//        int len = typedArray.length();
//        int[] resId = new int[len];
//        for (int i = 0; i < len; i++) {
//            resId[i] = typedArray.getResourceId(i, -1);
//        }
//        typedArray.recycle();
//        return resId;
//    }

    public void reSet() {
        chestCount = 0;
        curIndex = 0;

        setVisibility(GONE);
        if (handler != null) {
            if (handler.hasMessages(ANIM_START)) {
                handler.removeMessages(ANIM_START);
            }

            if (handler.hasMessages(ANIM_ALL_FINISHED)) {
                handler.removeMessages(ANIM_ALL_FINISHED);
            }
            if (handler.hasMessages(ANIM_ADD_TIMES)) {
                handler.removeMessages(ANIM_ADD_TIMES);
            }
            if (handler.hasMessages(CHECK_CHEST_STATE)) {
                handler.removeMessages(CHECK_CHEST_STATE);
            }
            if (handler.hasMessages(CHEST_END)) {
                handler.removeMessages(CHEST_END);
            }
            if (handler.hasMessages(ANIM_CHEST_OPENED)) {
                handler.removeMessages(ANIM_CHEST_OPENED);
            }
            if (handler.hasMessages(ANIM_CHEST_DISMISS)) {
                handler.removeMessages(ANIM_CHEST_DISMISS);
            }
        }
        tvCount.setVisibility(INVISIBLE);
        ivChestRight.setVisibility(INVISIBLE);
        cvCountDown.setVisibility(INVISIBLE);
        if (mChestGifts != null) {
            mChestGifts.clear();
        }
        if (mEndChestGifts != null) {
            mEndChestGifts.clear();
        }
        if (animChestRightRock != null && animChestRightRock.isRunning()) {
            animChestRightRock.cancel();
        }
        if (ivChestRight != null) {
            if (ivChestRight.getAnimation() != null) {
                ivChestRight.getAnimation().cancel();
            }
            ivChestRight.setVisibility(INVISIBLE);
        }
        if (ivBgFireworks != null) {
            if (ivBgFireworks.getAnimation() != null) {
                ivBgFireworks.getAnimation().cancel();
            }
            ivBgFireworks.setVisibility(INVISIBLE);
        }
        if (mInfo != null) {
            mInfo = null;
        }
        isAnimTransEnd = true;
    }

    public void setChestLocation(boolean isPk, int height) {
        if (isPk) {
            LayoutParams taskView = new LayoutParams(DensityUtil.dip2px(context, 80), DensityUtil.dip2px(context, 100));
            taskView.setMargins(0, height, 0, 0);
            taskView.gravity = Gravity.RIGHT;
            relateRight.setLayoutParams(taskView);
        } else {
            LayoutParams taskView = new LayoutParams(DensityUtil.dip2px(context, 80), DensityUtil.dip2px(context, 100));
            taskView.setMargins(0, DensityUtil.getDisplayHeight(context) / 2 - DensityUtil.dip2px(context, 50), 0, 0);
            taskView.gravity = Gravity.RIGHT;
            relateRight.setLayoutParams(taskView);
        }
    }

    public void setChestBoxVisible() {
        if (relateRight != null) {
            relateRight.setVisibility(GONE);
        }
    }
}