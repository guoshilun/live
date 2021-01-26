package com.jk.jkproject.ui.widget.room;

import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.fresco.helper.Phoenix;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.DanmuBean;
import com.jk.jkproject.ui.entity.LiveGift;
import com.jk.jkproject.ui.inter.LiveAnimaActionListener;
import com.jk.jkproject.ui.inter.LiveEnterRoomAnimListener;
import com.jk.jkproject.utils.emoji.LHImageSpan;
import com.miliyo.danmaku.model.utils.DimensionUtil;

/**
 * @author Zick
 * @params
 * @date 2020/8/17 1:37 PM
 * @desc 直播间公告
 */
public class LiveViewAnnouncement extends LinearLayout implements LiveEnterRoomAnimListener {
    private static final int FLAG_OUT = 1;
    private static final int FLAG_OUT_DELAY = 800;
    private boolean isRunning = false;
    private LiveAnimaActionListener mLiveGiftAction;
    private Context context;
    private Animation mAnimation;
    private String contentStr;
    private RelativeLayout rl_anim;
    private TextView tvMarrquee;
    private DanmuBean info;
    private float startX, startY;

    public LiveViewAnnouncement(Context context) {
        super(context);
        init(context);
    }

    public LiveViewAnnouncement(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LiveViewAnnouncement(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_announcement_layout, this, true);
        tvMarrquee = findViewById(R.id.tvMarqueeOne);
        rl_anim = findViewById(R.id.ll_announcement_anim);
        rl_anim.setVisibility(GONE);
        startX = tvMarrquee.getTranslationX();
        startY = tvMarrquee.getTranslationY();
    }

    @Override
    public void release() {
        if (context != null) {
            context = null;
        }
        if (rl_anim != null) {
            rl_anim = null;
        }
        if (info != null) {
            info = null;
        }
        if (tvMarrquee != null) {
            tvMarrquee = null;
        }
        isRunning = false;
    }

    public void setVisibility() {
//        if (mAnimation != null) {
//            mAnimation.cancel();
//            mAnimation.reset();
//            mAnimation = null;
//        }
        isRunning = false;
        info = null;
        rl_anim.setVisibility(GONE);
    }

    @Override
    public String getAnimate() {
        return LiveGift.LIVE_EFFECT_ANIMATE_SVGA;
    }

    @Override
    public void setAnimaActionListener(LiveAnimaActionListener listener) {
        mLiveGiftAction = listener;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void startAnim(DanmuBean info) {
    }

    @Override
    public void startAnim(DanmuBean info, boolean isLast) {
        isRunning = true;
        this.info = info;
        String body = info.getData().getContent();
        String imageUrl = "";
        String content;
        if (body.contains("---")) {
            String[] split = info.getData().getContent().split("---");
            content = split[0] + "";
            imageUrl = split[1];
            if (content.length() > 25) {
                String str = content.substring(0, content.indexOf("主播"));
                if (str.length() > 17) {
                    String str1 = str.substring(0, 17);
                    content = content.replace(str, str1);
                }
            }
        } else {
            content = body + "";
        }
        LogUtils.e("content=", content.length() + "==" + content);
        String msgStr = "系统公告:" + content;
        SpannableString spannableString = new SpannableString(msgStr);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.color_FBFF7E)), 0, "系统公告：".length(), 13);
        tvMarrquee.setTextColor(context.getResources().getColor(R.color.white));
        tvMarrquee.setText(spannableString);
        if (!imageUrl.trim().isEmpty()) {
            final int imgSize = DimensionUtil.dpToPx(context, 30);
            Phoenix.with(context)
                    .setUrl(imageUrl)
                    .setWidth(imgSize)
                    .setHeight(imgSize)
                    .setResult(bitmap -> {
                        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                        spannableStringBuilder.append("我是礼物");
                        LHImageSpan imageSpan = new LHImageSpan(context, bitmap, imgSize);
                        spannableStringBuilder.setSpan(imageSpan,
                                0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        tvMarrquee.append(spannableStringBuilder);
                    })
                    .load();
        }

        if (handler != null) {
            handler.removeMessages(FLAG_OUT);
            handler.sendEmptyMessage(FLAG_OUT);
        }
    }


    private void startAnimation() {

        PropertyValuesHolder alpha = PropertyValuesHolder.ofKeyframe("alpha",
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.25f, 1f),
                Keyframe.ofFloat(0.5f, 1f),
                Keyframe.ofFloat(1f, 1f)
        );


    }


    private void loadAnimation(DanmuBean info, boolean isLast) {
        rl_anim.setVisibility(VISIBLE);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_live_room_stytem_msg);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation param1Animation) {
                rl_anim.setVisibility(View.GONE);
                isRunning = false;
                if (mLiveGiftAction != null) {
                    mLiveGiftAction.pollAnnDanmus();
                }
            }

            public void onAnimationRepeat(Animation param1Animation) {
            }

            public void onAnimationStart(Animation param1Animation) {
                isRunning = true;
            }
        });
        mAnimation.setFillEnabled(true);
        tvMarrquee.setAnimation(mAnimation);
        mAnimation.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FLAG_OUT:
                    if (info != null) {
                        loadAnimation(info, false);
                    }
                    break;
            }
        }
    };
}
