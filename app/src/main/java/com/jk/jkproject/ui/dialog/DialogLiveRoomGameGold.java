package com.jk.jkproject.ui.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.ui.entity.LiveRoomGameGoldInfo;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.StringUtils;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc 金币游戏Dialog
 */
public class DialogLiveRoomGameGold extends BaseDialog {

    private static SVGAParser parser;
    @BindView(R.id.tv_pk_record)
    TextView tvPkRecord;
    @BindView(R.id.ll_1)
    RelativeLayout ll1;
    @BindView(R.id.iv_rules)
    ImageView ivRules;
    @BindView(R.id.iv_left_item_bg)
    ImageView ivLeftItemBg;
    @BindView(R.id.iv_left_item_gold)
    ImageView ivLeftItemGold;
    @BindView(R.id.tv_left_title_1)
    TextView tvLeftTitle1;
    @BindView(R.id.tv_left_total_gold)
    TextView tvLeftTotalGold;
    @BindView(R.id.tv_left_title_2)
    TextView tvLeftTitle2;
    @BindView(R.id.pb_left)
    ProgressBar pbLeft;
    @BindView(R.id.line_left_1)
    TextView lineLeft1;
    @BindView(R.id.tv_my_left)
    TextView tvMyLeft;
    @BindView(R.id.line_left_2)
    TextView lineLeft2;
    @BindView(R.id.tv_left_gold)
    TextView tvLeftGold;
    @BindView(R.id.rl_game_left)
    RelativeLayout rlGameLeft;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_btn_1)
    TextView tvBtn1;
    @BindView(R.id.tv_btn_2)
    TextView tvBtn2;
    @BindView(R.id.tv_btn_3)
    TextView tvBtn3;
    @BindView(R.id.iv_right_item_bg)
    ImageView ivRightItemBg;
    @BindView(R.id.iv_center)
    ImageView ivCenter;
    @BindView(R.id.svga)
    SVGAImageView svgaImageView;
    @BindView(R.id.left_svga)
    SVGAImageView leftSvga;
    @BindView(R.id.right_svga)
    SVGAImageView rightSvga;
    @BindView(R.id.iv_right_item_gold)
    ImageView ivRightItemGold;
    @BindView(R.id.tv_right_title_1)
    TextView tvRightTitle1;
    @BindView(R.id.tv_right_total_gold)
    TextView tvRightTotalGold;
    @BindView(R.id.tv_right_title_2)
    TextView tvRightTitle2;
    @BindView(R.id.pb_right)
    ProgressBar pbRight;
    @BindView(R.id.line_right_1)
    TextView lineRight1;
    @BindView(R.id.tv_my_right)
    TextView tvMyRight;
    @BindView(R.id.line_right_2)
    TextView lineRight2;
    @BindView(R.id.tv_right_gold)
    TextView tvRightGold;
    @BindView(R.id.rl_game_right)
    RelativeLayout rlGameRight;
    @BindView(R.id.tv_game_time)
    CountdownView tvGameTime;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.rl_game_center)
    RelativeLayout rlGameCenter;
    @BindView(R.id.tv_left_count)
    TextView tvLeftCount;
    @BindView(R.id.tv_right_count)
    TextView tvRightCount;
    @BindView(R.id.iv_left_bg)
    ImageView ivLeftBg;
    @BindView(R.id.iv_right_bg)
    ImageView ivRightBg;
    @BindView(R.id.tv_toals)
    TextView tvToals;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;
    private DialogLiveRoomGameGoldRules mGameGoldRulesDialog;
    private DialogLiveRoomGameGoldRecord mGameGoldRecordDialog;
    private DialogLiveRoomGameGoldSettlement mGameGoldSettlementDialog;
    private boolean isStartGame, isClick = false;
    private int goldSurface = 0; //0.未选中 1.正面 2.反面
    private LiveRoomGameGoldInfo info;
    private int time;
    private long reTimes;
    private int win;
    private int num;
    private int winType = -1, winStates = -1;
    private int reType = -1;
    private Drawable drawable;
    private Drawable drawable1;
    private int money;

    public DialogLiveRoomGameGold(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_game_gold);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.BOTTOM;
    }

    public static SVGAParser instance(Context context) {
        synchronized (SVGAParser.class) {
            if (parser == null) {
                parser = new SVGAParser(context);
            }
            return parser;
        }
    }

    protected void initView() {
        tvGameTime.customTimeShow(false, false, false, true, false);

        svgaImageView.setLoops(1);
        leftSvga.setLoops(1);
        rightSvga.setLoops(1);
        waitPhase();
        svgaImageView.setFillMode(SVGAImageView.FillMode.Forward);

        svgaImageView.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                if (winType == -1) {
                    if (reType == 1) {
                        reType = -1;
                        tvBalance.setText(money + "");
                        showSettlementDialog(num);
                    } else {
                        if (win != 3) {
                            tvBalance.setText(money + "");
                            tvGameTime.setVisibility(View.GONE);
                            tvToals.setText("竞猜失败，下局加油哦！");
                            tvToals.setVisibility(View.VISIBLE);
                        }
                    }
                }
                if (winStates == 1) {
                    winStates = -1;
                    loadAnimation(leftSvga, "game_gold_win.svga");
                    setLeftSelect();
                } else if (winStates == 2) {
                    winStates = -1;
                    loadAnimation(rightSvga, "game_gold_win.svga");
                    setRightSelect();
                }
                switch (winType) {
                    case 1:
                        loadAnimation(svgaImageView, "game_gold_positive.svga");
                        winType = -1;
                        winStates = 1;
                        break;
                    case 2:
                        loadAnimation(svgaImageView, "game_gold_reverse.svga");
                        winType = -1;
                        winStates = 2;
                        break;
                }
            }

            @Override
            public void onRepeat() {
                if (svgaImageView != null) {
                    svgaImageView.stopAnimation();
                    svgaImageView.pauseAnimation();
                }
            }

            @Override
            public void onStep(int frame, double percentage) {

            }
        });

        svgaImageView.setClearsAfterStop(false);

        drawable = this.context.getResources().getDrawable(R.mipmap.live_icon_gift_diamond);
        drawable1 = this.context.getResources().getDrawable(R.mipmap.live_icon_gift_diamond_gray);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.getScreenW(mContext), ScreenUtils.dp2px(getContext(), 260));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public void setPKWaitTime(long remainTime) {
//        long reTime = remainTime / 1000;
//        tvRefused.post(() -> tvRefused.setText("拒绝" + reTime + "s"));
//        if (reTime == 0) {
//            if (listener != null) {
//                listener.onClick(3);
//                dismiss();
//            }
//        }
    }

    @OnClick({R.id.tv_pk_record, R.id.iv_rules, R.id.rl_game_left, R.id.iv, R.id.tv_balance, R.id.tv_btn_1, R.id.tv_btn_2, R.id.tv_btn_3, R.id.rl_game_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pk_record:
                showGameGoldRecordDialog();
                break;
            case R.id.iv_rules:
                showGameGoldRulesDialog();
                break;
            case R.id.rl_game_left:
                if (isStartGame) {
                    isClick = true;
                    setLeftSelect();
                    goldSurface = 1;
                }
                break;
            case R.id.rl_game_right:
                if (isStartGame) {
                    goldSurface = 2;
                    isClick = true;
                    setRightSelect();
                }
                break;
            case R.id.iv:
            case R.id.tv_balance:
                if (listener != null) {
                    listener.onClick();
                }
                break;
            case R.id.tv_btn_1:
                if (isStartGame && isClick) {
                    gameGoldBat(1000);
                }
                break;
            case R.id.tv_btn_2:
                if (isStartGame && isClick) {
                    gameGoldBat(10000);
                }
                break;
            case R.id.tv_btn_3:
                if (isStartGame && isClick) {
                    gameGoldBat(100000);
                }
                break;
        }
    }

    private void gameGoldBat(int bet) {
        if (Integer.parseInt(tvBalance.getText().toString().trim()) > bet) {
            IMLiveRoomManager.instance().sendGameGoldBat(goldSurface, bet);
        } else {
            if (listener != null) {
                dismiss();
                listener.onClick(null);
            }
        }
    }

    //结算Dialog
    private void showSettlementDialog(int num) {
        if (mContext == null) {
            return;
        }
        if (mGameGoldSettlementDialog == null) {
            mGameGoldSettlementDialog = new DialogLiveRoomGameGoldSettlement(getContext(), 1);
        }
        if (num > 0) {
            mGameGoldSettlementDialog.show();
            mGameGoldSettlementDialog.setData(num);
        }
        mGameGoldSettlementDialog.setDialogClickListener(info -> {
            reType = -1;
        });
        mGameGoldSettlementDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    /**
     * 金币结果
     *
     * @param win   1 赢  2输 3
     * @param num   输赢多少钻石
     * @param type  开奖结果 1 正  2反
     * @param money 当前拥有钻石
     */
    public void setResults(int win, int num, int type, int money) {
        this.money = money;
        this.winStates = -1;
        this.win = win;
        this.num = num;
        this.winType = type;
        switch (win) {
            case 1:
//                showSettlementDialog(num);
//                tvBalance.setText(money + "");
                reType = 1;
                break;
            case 2:
//                tvGameTime.setVisibility(View.GONE);
//                tvToals.setText("竞猜失败，下局加油哦！");
//                tvToals.setVisibility(View.VISIBLE);
//                tvBalance.setText(money + "");
                reType = -1;
                break;
        }
        svgaImageView.setVisibility(View.VISIBLE);
        ivCenter.setVisibility(View.GONE);
//        switch (type) {
//            case 1:
//                loadAnimation("game_gold_positive.svga");
//                break;
//            case 2:
//                loadAnimation("game_gold_reverse.svga");
//                break;
//        }
    }

    //数据填充
    public void setData(LiveRoomGameGoldInfo info) {
        this.info = info;
        tvLeftTotalGold.setText(info.getFront() + "");
        tvRightTotalGold.setText(info.getContrary() + "");

        pbLeft.setMax(info.getMaxBet());
        pbLeft.setProgress(info.getFront());
        pbRight.setMax(info.getMaxBet());
        pbRight.setProgress(info.getContrary());

        String left = StringUtils.ReadSize(info.getFront());
        String right = StringUtils.ReadSize(info.getContrary());
        String total = StringUtils.ReadSize(info.getMaxBet());
        tvLeftCount.setText(left + "/" + total);
        tvRightCount.setText(right + "/" + total);


        tvLeftGold.setText(info.getFrontBet() + "");
        tvRightGold.setText(info.getContraryBet() + "");


        tvBalance.setText(info.getMoney() + "");
        resetTime(info.getStage(), info.getTime());
//        setBalance(Double.parseDouble(info.getMoney()));
    }

    @SuppressLint("ResourceType")
    public void setBalance(double money) {
        if (money >= 100000) {
            setBtnBackground(drawable, R.drawable.bg_gold_blue_btn, tvBtn3);
            setBtnBackground(drawable, R.drawable.bg_gold_blue_btn, tvBtn2);
            setBtnBackground(drawable, R.drawable.bg_gold_blue_btn, tvBtn1);
        } else if (money >= 10000) {
            setBtnBackground(drawable1, R.drawable.bg_gray_btn, tvBtn3);
            setBtnBackground(drawable, R.drawable.bg_gold_blue_btn, tvBtn2);
            setBtnBackground(drawable, R.drawable.bg_gold_blue_btn, tvBtn1);
        } else if (money >= 1000) {
            setBtnBackground(drawable1, R.drawable.bg_gray_btn, tvBtn3);
            setBtnBackground(drawable1, R.drawable.bg_gray_btn, tvBtn2);
            setBtnBackground(drawable, R.drawable.bg_gold_blue_btn, tvBtn1);
        } else {
            setBtnBackground(drawable1, R.drawable.bg_gray_btn, tvBtn3);
            setBtnBackground(drawable1, R.drawable.bg_gray_btn, tvBtn2);
            setBtnBackground(drawable1, R.drawable.bg_gray_btn, tvBtn1);
        }
    }

    @SuppressLint("ResourceType")
    public void setBtnBackground(Drawable drawable, @IdRes int res, TextView tvBtn) {
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        tvBtn.setCompoundDrawables(drawable1, null, null, null);
        tvBtn.setBackgroundResource(res);
    }

    //下注回调数据填充
    public void setBetData(LiveRoomGameGoldInfo info) {
        tvBalance.setText(info.getMoney() + "");
//        setBalance(Double.parseDouble(info.getMoney()));
        tvLeftGold.setText(info.getFrontBet() + "");
        tvRightGold.setText(info.getContraryBet() + "");
    }

    //总下注数量
    public void setTotalBat(LiveRoomGameGoldInfo info) {
        tvLeftTotalGold.setText(info.getFront() + "");
        tvRightTotalGold.setText(info.getContrary() + "");

        pbLeft.setMax(info.getMaxBet());
        pbLeft.setProgress(info.getFront());
        pbRight.setMax(info.getMaxBet());
        pbRight.setProgress(info.getContrary());

        String left = StringUtils.ReadSize(info.getFront());
        String right = StringUtils.ReadSize(info.getContrary());
        String total = StringUtils.ReadSize(info.getMaxBet());
        tvLeftCount.setText(left + "/" + total);
        tvRightCount.setText(right + "/" + total);

        resetTime("2", info.getTime());
    }

    public void resetTime(String type, int time) {
        this.time = time;
        resetTime(type);
    }

    //1.10s等待时间 2.30s下注时间 3.10s揭晓时间
    public void resetTime(String type) {
        if (null == tvGameTime) {
            return;
        }
        tvGameTime.stop();
        switch (type) {
            case "1":
                tvLeftGold.setText("0");
                tvRightGold.setText("0");
                isStartGame = false;
                tvGameTime.start(time * 1000);
                waitPhase();
                winType = -1;
                tvGameTime.setVisibility(View.GONE);
                tvToals.setVisibility(View.VISIBLE);
                ivCenter.setVisibility(View.VISIBLE);
                tvToals.setText("即将开始，请稍等！");
                setBalance(0);
                break;
            case "2":
                if (!isStartGame) {
                    reTimes = 0;
                    ivCenter.setVisibility(View.VISIBLE);
                    tvGameTime.setVisibility(View.GONE);
                    tvToals.setVisibility(View.VISIBLE);
                    tvToals.setText("开始竞猜");
                }
                isStartGame = true;
                winType = -1;
                tvGameTime.start(time * 1000);
                setBalance(Double.parseDouble(info.getMoney()));
                break;
            case "3":
                isStartGame = false;
                tvGameTime.start(time * 1000);
                tvGameTime.setVisibility(View.GONE);
                tvToals.setVisibility(View.VISIBLE);
                tvToals.setText("揭晓结果");
                ivCenter.setVisibility(View.GONE);
                reType = -1;
                loadAnimation(svgaImageView, "game_gold.svga");
                setBalance(0);
                break;
        }
        tvGameTime.setOnCountdownEndListener(cv -> {
            switch (type) {
                case "1":
                    resetTime("2");
                    break;
                case "2":
                    resetTime("3");
                    break;
                case "3":
                    resetTime("1");
                    break;
            }
        });
        tvGameTime.setOnCountdownIntervalListener(1000, (cv, remainTime) -> {
            long reTime = remainTime / 1000;
            switch (type) {
                case "2":
//                    if (reTime <= 3 && isStartGame) { //3s停止下注
//                        isStartGame = false;
//                    }
                    reTimes++;
                    if (reTimes == 3) {
                        tvToals.setVisibility(View.GONE);
                        tvGameTime.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        });
    }

    //等待阶段
    public void waitPhase() {
        ivCenter.setVisibility(View.VISIBLE);
        svgaImageView.setVisibility(View.GONE);
        setLeftUnSelect();
        setRightUnSelect();
        isClick = false;
        if (leftSvga != null) {
            leftSvga.stopAnimation();
            leftSvga.pauseAnimation();
            leftSvga.setVisibility(View.GONE);
        }
        if (rightSvga != null) {
            rightSvga.stopAnimation();
            rightSvga.pauseAnimation();
            rightSvga.setVisibility(View.GONE);
        }
        if (mGameGoldSettlementDialog != null && mGameGoldSettlementDialog.isShowing()) {
            mGameGoldSettlementDialog.dismiss();
        }
    }

    //左边未选中状态
    public void setLeftUnSelect() {
        ivLeftBg.setImageResource(R.drawable.bg_gold_game_unselect_gary);
        ivLeftItemBg.setImageResource(R.mipmap.icon_game_gold_unselect);
        tvLeftTitle1.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
        tvLeftTotalGold.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
        tvLeftTitle2.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
        tvMyLeft.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
        tvLeftGold.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
    }

    //右边未选中状态
    public void setRightUnSelect() {
        ivRightBg.setImageResource(R.drawable.bg_gold_game_unselect_gary);
        ivRightItemBg.setImageResource(R.mipmap.icon_game_gold_unselect);
        tvRightTitle1.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
        tvRightTotalGold.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
        tvRightTitle2.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
        tvMyRight.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
        tvRightGold.setTextColor(mContext.getResources().getColor(R.color.color_ccc));
    }


    //右边选中状态
    public void setRightSelect() {
        setLeftUnSelect();
        ivRightBg.setImageResource(R.drawable.bg_gold_game_select_white);
        ivRightItemBg.setImageResource(R.mipmap.icon_game_gold_select);
        tvRightTitle1.setTextColor(mContext.getResources().getColor(R.color.color_999));
        tvRightTotalGold.setTextColor(mContext.getResources().getColor(R.color.color_009CF0));
        tvRightTitle2.setTextColor(mContext.getResources().getColor(R.color.color_999));
        tvMyRight.setTextColor(mContext.getResources().getColor(R.color.color_999));
        tvRightGold.setTextColor(mContext.getResources().getColor(R.color.color_009CF0));
    }

    //左边选中状态
    public void setLeftSelect() {
        setRightUnSelect();
        ivLeftBg.setImageResource(R.drawable.bg_gold_game_select_white);
        ivLeftItemBg.setImageResource(R.mipmap.icon_game_gold_select);
        tvLeftTitle1.setTextColor(mContext.getResources().getColor(R.color.color_999));
        tvLeftTotalGold.setTextColor(mContext.getResources().getColor(R.color.color_009CF0));
        tvLeftTitle2.setTextColor(mContext.getResources().getColor(R.color.color_999));
        tvMyLeft.setTextColor(mContext.getResources().getColor(R.color.color_999));
        tvLeftGold.setTextColor(mContext.getResources().getColor(R.color.color_009CF0));
    }


    public static interface DialogReturnListener {
        void onClick(PKTypeBaseInfo info);

        void onClick();
    }

    private void showGameGoldRulesDialog() {
        if (mContext == null) {
            return;
        }
        if (mGameGoldRulesDialog == null) {
            mGameGoldRulesDialog = new DialogLiveRoomGameGoldRules(getContext(), 1);
        }
        mGameGoldRulesDialog.show();
        mGameGoldRulesDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showGameGoldRecordDialog() {
        if (mContext == null) {
            return;
        }
        if (mGameGoldRecordDialog == null) {
            mGameGoldRecordDialog = new DialogLiveRoomGameGoldRecord(getContext(), 1);
        }
        mGameGoldRecordDialog.show();
        mGameGoldRecordDialog.setDialogClickListener((type, info) -> {
            switch (type) {
                case 1:
                    break;
                case 2:
                    break;
            }
        });
        mGameGoldRecordDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void loadAnimation(SVGAImageView svgaImageView, String urlName) {
//        svgaImageView.setClearsAfterStop(true);
//        svgaImageView.pauseAnimation();
        instance(context).decodeFromAssets(urlName, new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                LogUtils.e("svgaUrl=", urlName);
                if (svgaImageView != null && videoItem != null) {
                    svgaImageView.setVideoItem(videoItem);
                    ivCenter.setVisibility(View.GONE);
                    svgaImageView.setVisibility(View.VISIBLE);
                    svgaImageView.startAnimation();
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}