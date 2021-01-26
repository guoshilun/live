package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.DanmuBean;
import com.jk.jkproject.ui.inter.LiveAnimaActionListener;
import com.jk.jkproject.ui.inter.LiveEnterRoomAnimListener;
import com.jk.jkproject.utils.UserLevelSetUtils;

/**
 * @author Zick
 * @params
 * @date 2020/8/17 1:37 PM
 * @desc 土豪加入直播间
 */
public class LiveViewEnterRoom extends FrameLayout implements LiveEnterRoomAnimListener {
    private static final int FLAG_OUT = 1;
    private static final int FLAG_OUT_DELAY = 800;
    private TextView tvUserLevel, tvUserContent;
    private LinearLayout enterRoomAnim;
    private boolean isRunning = false;
    private LiveAnimaActionListener mLiveGiftAction;
    private Context context;
    private Animation mAnimation;
    private String contentStr;
    private DanmuBean info;

    public LiveViewEnterRoom(Context context) {
        super(context);
        init(context);
    }

    public LiveViewEnterRoom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LiveViewEnterRoom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_enter_room_layout, this, true);
        enterRoomAnim = findViewById(R.id.enter_room_anim);
        tvUserLevel = findViewById(R.id.tv_user_level);
        tvUserContent = findViewById(R.id.tv_user_content);
    }

    @Override
    public void release() {
        if (context != null) {
            context = null;
        }
        if (info != null) {
            info = null;
        }
        if (handler != null) {
            handler.removeMessages(FLAG_OUT);
            handler = null;
        }
        if (enterRoomAnim != null) {
            enterRoomAnim = null;
        }
        if (tvUserLevel != null) {
            tvUserLevel = null;
        }
        if (tvUserContent != null) {
            tvUserContent = null;
        }
        isRunning = false;
    }

    public void setVisibility() {
        isRunning = false;
//        if (mAnimation != null) {
//            mAnimation.cancel();
//            mAnimation.reset();
//            mAnimation = null;
//        }
        enterRoomAnim.setVisibility(GONE);
    }


    @Override

    public String getAnimate() {
        return "";
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
        LogUtils.e("====1loadAnimation");
        isRunning = true;
        this.info = info;
        contentStr = getResources().getString(R.string.str_live_room_enter);
        UserLevelSetUtils.setUserLevel(tvUserLevel, info.getData().getFrom().getLevel());
        tvUserLevel.setText(info.getData().getFrom().getLevel() + "");
        String content = info.getData().getFrom().getNickName() + ": ";
        tvUserContent.setText(Html.fromHtml(String.format(contentStr, content, info.getData().getContent())));
        if (handler != null) {
            handler.removeMessages(FLAG_OUT);
            handler.sendEmptyMessage(FLAG_OUT);
        }
    }

    @Override
    public void startAnim(DanmuBean info, boolean isLast) {

    }


    private void loadAnimation(DanmuBean info) {
        LogUtils.e("====loadAnimation");
        enterRoomAnim.setVisibility(VISIBLE);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_live_room_user_enter);
        this.mAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation param1Animation) {
                isRunning = false;
                enterRoomAnim.setVisibility(GONE);
                if (mLiveGiftAction != null) {
                    mLiveGiftAction.pollDanmuBeans();
                }
            }

            public void onAnimationRepeat(Animation param1Animation) {
            }

            public void onAnimationStart(Animation param1Animation) {
                isRunning = true;
            }
        });
        mAnimation.setFillEnabled(true);
        enterRoomAnim.setAnimation(mAnimation);
        mAnimation.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FLAG_OUT:
                    if (info != null) {
                        loadAnimation(info);
                    }
                    break;
            }
        }
    };
}
