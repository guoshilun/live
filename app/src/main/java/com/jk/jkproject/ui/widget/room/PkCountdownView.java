package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPunishmentClose;
import com.jk.jkproject.ui.entity.PkStartBean;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;

import butterknife.BindView;
import cn.iwgang.countdownview.CountdownView;


public class PkCountdownView extends RelativeLayout {
    @BindView(R.id.im_pk_status)
    ImageView imPkStatus;
    @BindView(R.id.im_pk_status_vs)
    ImageView imPkStatusVs;
    @BindView(R.id.tv_pk_status)
    TextView tvPkStatus;
    @BindView(R.id.pk_countdown)
    CountdownView pkCountdown;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_pk_countdown)
    TextView tvPkCountdown;
    TextView tvToGame;
    @BindView(R.id.iv_pk_countdown)
    ImageView ivPkCountdown;
    private DialogLiveRoomPunishmentClose mPKPunishClose;

    private CountDownListener listener;
    private int time = -1;
    private boolean isFast = false;

    public void setDialogClickListener(CountDownListener listener) {
        this.listener = listener;
    }

    public static interface CountDownListener {
        void onClick(int type);
    }

    public PkCountdownView(Context context) {
        super(context);
        initView(context);
    }

    public PkCountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PkCountdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void relase() {
        if (pkCountdown != null) {
            pkCountdown.stop();
            pkCountdown = null;
        }

        if (imPkStatus != null) {
            imPkStatus = null;
        }
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_live_pk_countdown, this, true);
        imPkStatus = findViewById(R.id.im_pk_status);
        imPkStatusVs = findViewById(R.id.im_pk_status_vs);
        pkCountdown = findViewById(R.id.pk_countdown);
        tvPkStatus = findViewById(R.id.tv_pk_status);
        tvPkCountdown = findViewById(R.id.tv_pk_countdown);
        ivPkCountdown = findViewById(R.id.iv_pk_countdown);
        tvToGame = findViewById(R.id.tv_to_a_game);
        ll1 = findViewById(R.id.ll_1);
        ll1.setVisibility(GONE);
        imPkStatus.setVisibility(GONE);
        tvPkCountdown.setVisibility(GONE);
        ivPkCountdown.setVisibility(GONE);
        pkCountdown.customTimeShow(false, false, true, true, false);

        imPkStatus.setOnClickListener(v -> {
            if (OnClickUtils.isFastClick()) {
                showDialog();
            }
        });

        tvToGame.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(2);
            }
        });
    }

    public void resetTime(int type, int time) {
        this.time = time;
        resetTime(type);
    }

    //1.5s倒计时 2.pk倒计时 3.惩罚倒计时
    public void resetTime(int type) {
        if (null == pkCountdown) {
            return;
        }
        pkCountdown.stop();
        switch (type) {
            case 1:
                ll1.setVisibility(GONE);
                imPkStatus.setVisibility(GONE);
                tvPkCountdown.setVisibility(VISIBLE);
                ivPkCountdown.setVisibility(VISIBLE);
                imPkStatusVs.setVisibility(VISIBLE);
                tvPkStatus.setVisibility(GONE);
                tvToGame.setVisibility(GONE);
                if (time != -1) {
                    time = time + 1;
                    pkCountdown.start(time * 1000);
                    time = -1;
                } else {
                    pkCountdown.start(6 * 1000);
                }
                if (listener != null) {
                    listener.onClick(4);
                }
                break;
            case 2:
                ll1.setVisibility(VISIBLE);
                imPkStatus.setVisibility(GONE);
                tvPkCountdown.setVisibility(GONE);
                ivPkCountdown.setVisibility(GONE);
                imPkStatusVs.setVisibility(VISIBLE);
                tvPkStatus.setVisibility(GONE);
                tvToGame.setVisibility(GONE);
                if (time != -1) {
                    pkCountdown.start(time * 1000);
                    time = -1;
                } else {
                    pkCountdown.start(5 * 60 * 1000);
                }
                break;
            case 3:
                ll1.setVisibility(VISIBLE);
                imPkStatusVs.setVisibility(GONE);
                tvPkStatus.setVisibility(VISIBLE);
                setVisibil(SPUtils.getIsHost());
                if (time != -1) {
                    pkCountdown.start(time * 1000);
                    time = -1;
                } else {
                    pkCountdown.start(3 * 60 * 1000);
                }
                if (time >= 45) {
                    isFast = true;
                }
                break;
        }
        pkCountdown.setOnCountdownEndListener(cv -> {
            switch (type) {
                case 1:
                    resetTime(2);
                    if (null != listener) {
                        listener.onClick(3);
                    }
                    break;
                case 2:
                    resetTime(3);
                    break;
                case 3:
                    if (listener != null) {
                        time = -1;
                        listener.onClick(1);
                    }
                    break;
            }
        });
        pkCountdown.setOnCountdownIntervalListener(1000, (cv, remainTime) -> {
            long reTime = remainTime / 1000;
            if (type == 1) {
                if (reTime == 0) {
                    tvPkCountdown.setVisibility(GONE);
                    ivPkCountdown.setVisibility(GONE);
                    tvPkCountdown.setText(5 + "");
                } else {
                    tvPkCountdown.setText(reTime + "");
                }
            } else if (type == 3) {
                if (reTime <= 45 && listener != null && isFast) {
                    isFast = false;
                    listener.onClick(6);
                }
            }
        });
    }

    public void setVisibil(boolean isHost) {
        if (isHost) {
            tvToGame.setVisibility(VISIBLE);
            imPkStatus.setVisibility(VISIBLE);
        } else {
            tvToGame.setVisibility(GONE);
            imPkStatus.setVisibility(GONE);
        }
    }

    public void setToGameVis(boolean isVis) {
        if (isVis) {
            tvToGame.setVisibility(VISIBLE);
        } else {
            tvToGame.setVisibility(GONE);
        }
    }


    public void setData(PkStartBean pkBean) {

    }

    private void showDialog() {
        if (getContext() == null) {
            return;
        }
        if (mPKPunishClose == null) {
            mPKPunishClose = new DialogLiveRoomPunishmentClose(getContext());
        }
        mPKPunishClose.show();
        mPKPunishClose.setData(2);
        mPKPunishClose.setDialogClickListener(param1String -> {
            pkCountdown.stop();
            if (listener != null) {
                listener.onClick(5);
            }
//            ToastUtils.showLongToast("惩罚时间内关闭PK");
        });
        mPKPunishClose.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }
}
