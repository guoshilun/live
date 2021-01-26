package com.jk.jkproject.ui.widget.room;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.entity.LiveGiftUtils;
import com.jk.jkproject.ui.inter.LiveComboGiftAnimListener;
import com.jk.jkproject.ui.inter.LiveGift999AnimListener;
import com.jk.jkproject.ui.inter.LiveGiftActionListener;
import com.jk.jkproject.ui.inter.LiveGiftSvgaAnimListener;
import com.jk.jkproject.ui.inter.LiveRoseRainAnimListener;
import com.jk.jkproject.utils.emoji.AlineCenterImageSpanReward;

import java.util.LinkedList;

public class LiveViewForGift extends RelativeLayout implements LiveComboGiftAnimListener {

    private static final int FLAG_ALL_FINISHED = 1;
    private static final int FLAG_START_ANIM = 2;
    private static final int ALL_FINISHED_DELAY = 3000;
    public boolean isRunning = false;
    PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2.0f, 1.0f);
    PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2.0f, 1.0f);
    PropertyValuesHolder longHolderX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2.0f, 1.0f, 1.0f, 1.0f);
    PropertyValuesHolder longHolderY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2.0f, 1.0f, 1.0f, 1.0f);
    private View rlContent;
    private SimpleDraweeView ivIcon;
    private TextView tvName;
    private TextView tvDes;
    private SimpleDraweeView ivGift;
    private ImageView ivRewardX;
    private LiveGiftInfoBean.DataBean mInfo;
    private long mRewardLastTime = 1;
    private long mRewardCurTime = 1;
    private ObjectAnimator animScale;
    private ObjectAnimator rewardanimScale;
    private boolean isStartAnimFromEndAnim = false;
    private LiveGiftActionListener mLiveGiftAction;
    private LiveRoseRainAnimListener mRoseRainListener;
    private LiveGift999AnimListener mGift999Listener;
    private LiveGiftSvgaAnimListener mGiftSvgaAnimListener;
    private LinkedList<LiveGiftInfoBean.DataBean> allCombos = new LinkedList();
    private Context mContext;
    private AlineCenterImageSpanReward imageSpan;
    private SpannableString spannableString;
    private ImageView ivRewardBig;
    private StrokeTextView tvTimes;
    private TextView tvRewardBig;
    private RelativeLayout relateReward;
    private TextView tvReward;
    private int AsTime = 300, countCombo = 1;
    private int AsTimeText = 280;
    private int AwardAsTimeText = 1500;
    private int comboTime = 1;
    private int currentTimes = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FLAG_ALL_FINISHED:
                    comboTime = 1;
//                    countCombo = 1;
                    reset();
                    mInfo = null;
                    animScale = null;
                    isRunning = false;
                    setVisibility(INVISIBLE);
                    tvTimes.setText("");
                    if (mLiveGiftAction != null) {
                        mLiveGiftAction.pollComboGift();
                    }
                    break;
                case FLAG_START_ANIM:
                    comboTime = 1;
                    startAnim();
                    if (isStartAnimFromEndAnim && mLiveGiftAction != null) {
                        mLiveGiftAction.pollComboGift();
                    }
                    break;
            }
        }
    };

    public LiveViewForGift(Context context) {
        super(context);
        init(context);
    }

    public LiveViewForGift(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LiveViewForGift(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_view_live_view_for_gift, this, true);
        rlContent = findViewById(R.id.rl_content);
        ivIcon = (SimpleDraweeView) findViewById(R.id.iv_icon);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvDes = (TextView) findViewById(R.id.tv_des);
        ivGift = (SimpleDraweeView) findViewById(R.id.iv_gift);
        ivRewardX = (ImageView) findViewById(R.id.iv_reward_x);
        ivRewardBig = (ImageView) findViewById(R.id.iv_reward_big);
        tvTimes = (StrokeTextView) findViewById(R.id.tv_times);
        tvReward = findViewById(R.id.tv_reward_diamond);
        relateReward = findViewById(R.id.reward_relate);
        tvRewardBig = findViewById(R.id.tv_reward_big);
        mContext = context;
    }

    public void setTimesColor(int color) {
        tvTimes.setTextColor(color);
        tvTimes.setBorderColor(getResources().getColor(R.color.live_view_gift_border_color));
    }

    private void initAnimate() {
        PropertyValuesHolder transXGift = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, -(rlContent.getWidth() + ivGift.getWidth() / 2), 0f);
        final ObjectAnimator animTransXGift = ObjectAnimator.ofPropertyValuesHolder(ivGift, transXGift);
        animTransXGift.setDuration(AsTime);
        animTransXGift.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animScale != null) {
                    tvTimes.setVisibility(VISIBLE);
                    animScale.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        PropertyValuesHolder transXContent = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, -rlContent.getWidth(), 0f);
        ObjectAnimator animTransXContent = ObjectAnimator.ofPropertyValuesHolder(rlContent, transXContent);
        animTransXContent.setDuration(AsTime);
        animTransXContent.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animTransXGift != null) {
                    ivGift.setVisibility(VISIBLE);
                    animTransXGift.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        initAnimScale(false, AsTimeText);
        animTransXContent.start();
    }

    /**
     * 初始化动画
     *
     * @param isAward 是否是大奖
     */
    private void initAnimScale(boolean isAward, int duration) {
        if (isAward) {
            animScale = ObjectAnimator.ofPropertyValuesHolder(tvTimes, longHolderX, longHolderY);
        } else {
            animScale = ObjectAnimator.ofPropertyValuesHolder(tvTimes, holderX, holderY);
        }
        animScale.setDuration(duration);
        animScale.setInterpolator(new DecelerateInterpolator());
        animScale.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (handler != null) {
                    handler.removeMessages(FLAG_ALL_FINISHED);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                AnimEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    private void AnimEnd() {
        if (allCombos != null) {
            if (allCombos.size() > 0) {
                mInfo = allCombos.poll();
                if (handler != null) {
                    isStartAnimFromEndAnim = true;
                    handler.removeMessages(FLAG_START_ANIM);
                    handler.sendEmptyMessage(FLAG_START_ANIM);
                }
            } else {
                if (handler != null) {
                    handler.removeMessages(FLAG_ALL_FINISHED);
                    handler.sendEmptyMessageDelayed(FLAG_ALL_FINISHED, ALL_FINISHED_DELAY);
                }
            }
        }
    }

    @Override
    public String getAnimate() {
        return "";
    }

    @Override
    public void setLiveGiftActionListener(LiveGiftActionListener listener) {
        this.mLiveGiftAction = listener;
    }

    @Override
    public boolean isCombo(LiveGiftInfoBean.DataBean info) {
        boolean isCombo = false;
        if (mInfo != null && info != null && mInfo.getIs_combo() == 1 && mInfo.getId().equals(info.getId()) && TextUtils.equals(mInfo.getUserId(), info.getUserId())
                && mInfo.getG_amount() == info.getG_amount()) {
            isCombo = true;
        }
        LogUtils.e("======",isCombo);
        return isCombo;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    int count = 0, count1 = 0;

    @Override
    public void addTimes(LiveGiftInfoBean.DataBean info) {
        setVisibility(VISIBLE);
        addGiftNum(info, false);
        if (handler != null) {
            isStartAnimFromEndAnim = false;
            handler.removeMessages(FLAG_START_ANIM);
            handler.sendEmptyMessage(FLAG_START_ANIM);
        }
    }

    /**
     * 增加个数
     *
     * @param info
     */
    private void addGiftNum(LiveGiftInfoBean.DataBean info, boolean isFirst) {
        if (info.getIs_combo() == 1) {
            for (int i = (isFirst ? 1 : 0), sun = info.getCombo(); i < sun; i++) {
//                LiveGiftInfoBean.DataBean live = getLiveGiftResponse(info, Integer.parseInt(tvTimes.getText().toString().trim().substring(1)) + 1);
                LiveGiftInfoBean.DataBean live = getLiveGiftResponse(info, countCombo++);
                allCombos.add(live);
            }
            if (isFirst) {
                countCombo = 1;
                LogUtils.e("======addGiftNum");
                mInfo.setCombo(1);
            }
        } else {
            if (!isFirst) {
                allCombos.add(info);
            }
        }
    }

    @NonNull
    private LiveGiftInfoBean.DataBean getLiveGiftResponse(LiveGiftInfoBean.DataBean pushGift, int i) {
        LiveGiftInfoBean.DataBean giftBean = new LiveGiftInfoBean.DataBean();
        giftBean.setType(pushGift.getType());
        giftBean.setIs_combo(pushGift.getIs_combo());
        giftBean.setPicture(pushGift.getPicture());
        giftBean.setUsername(pushGift.getUsername());
        giftBean.setUserId(pushGift.getUserId());
        giftBean.setG_name(pushGift.getG_name());
        giftBean.setG_icon(pushGift.getG_icon());
        giftBean.setG_amount(pushGift.getG_amount());
        giftBean.setSvgaAnimate(pushGift.getSvgaAnimate());
        giftBean.setId(pushGift.getId());
        giftBean.setCombo(i);
        return giftBean;
    }

    @Override
    public void startAnim(LiveGiftInfoBean.DataBean info) {
        mInfo = info;
        addGiftNum(info, true);
        isRunning = true;
        if (info == null) {
            isRunning = false;
            return;
        }
        switch (info.getG_amount()) {
            case 1:
                rlContent.setBackgroundResource(R.drawable.bg_gift_combo_special);
                break;
            case 10:
                rlContent.setBackgroundResource(R.drawable.bg_gift_combo_special_10);
                break;
            case 99:
                rlContent.setBackgroundResource(R.drawable.bg_gift_combo_special_99);
                break;
            case 188:
                rlContent.setBackgroundResource(R.drawable.bg_gift_combo_special_188);
                break;
            case 520:
                rlContent.setBackgroundResource(R.drawable.bg_gift_combo_special_520);
                break;
            case 1314:
                rlContent.setBackgroundResource(R.drawable.bg_gift_combo_special_1314);
                break;
        }
        setVisibility(VISIBLE);

        String icon = info.getPicture();
        ivIcon.setVisibility(VISIBLE);
        ivIcon.setImageURI(Uri.parse(icon));
        LiveGiftInfoBean.DataBean gift = LiveGiftUtils.get().getGift(info.getId());
        if (gift != null) {
            if (TextUtils.isEmpty(info.getUsername())) {
                // 匿名
                tvName.setText("匿名");
            } else {
                // 非匿名
                tvName.setText(info.getUsername());
            }
            tvDes.setText("送" + info.getG_amount() + "个" + info.getG_name());
            Phoenix.with(ivGift).load(info.getG_icon());
            reset();
            tvTimes.setText("x" + mInfo.getCombo());
            currentTimes = mInfo.getCombo();
            initAnimate();
            setReward(info);
        } else {
            isRunning = false;
        }
    }

    private void setReward(LiveGiftInfoBean.DataBean info) {
        initAnimScale(false, AsTimeText);
    }

    @Override
    public void release() {
        isRunning = false;
        countCombo = 1;
        if (animScale != null) {
            animScale = null;
        }

        if (mInfo != null) {
            mInfo = null;
        }

        if (handler != null) {
            handler.removeMessages(FLAG_START_ANIM);
            handler.removeMessages(FLAG_ALL_FINISHED);
            handler = null;
        }
    }

    public void setRoseRainListener(LiveRoseRainAnimListener listener) {
        mRoseRainListener = listener;
    }

    public void setGift999Listener(LiveGift999AnimListener listener) {
        mGift999Listener = listener;
    }

    public void setGiftSvgaAnimListener(LiveGiftSvgaAnimListener listener) {
        mGiftSvgaAnimListener = listener;
    }

    private void startAnim() {
        if (isStartAnimFromEndAnim || animScale != null) {
            isRunning = true;
            if (mInfo != null && mInfo.getCombo() != currentTimes) {
                currentTimes = mInfo.getCombo();
                tvTimes.setText("x" + mInfo.getCombo());
                setReward(mInfo);
                if (handler != null) {
                    handler.removeMessages(FLAG_ALL_FINISHED);
                }
                animScale.start();
            } else {
                AnimEnd();
            }

        }
    }

    private void reset() {
        ivGift.setVisibility(INVISIBLE);
        tvTimes.setVisibility(INVISIBLE);
    }

    public void clear() {
        if (allCombos != null && allCombos.size() > 0) {
            allCombos.clear();
        }
    }
}