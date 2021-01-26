package com.jk.jkproject.ui.dialog;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DialogLiveRoomWheat extends BaseDialog {

    @BindView(R.id.sdv_wheat)
    SimpleDraweeView sdvWheat;
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.tv_countdown)
    TextView tvCountdown;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.iv_4)
    ImageView iv4;
    @BindView(R.id.iv_5)
    ImageView iv5;
    @BindView(R.id.iv_6)
    ImageView iv6;
    private DialogReturnListener listener;
    private Context mContext;
    private int mType; //1.连麦状态 2.取消连麦
    private Unbinder bind;
    private String anchorId;
    private String streamId;
    private ObjectAnimator animator1,animator2,animator3,animator4,animator5,animator6;
    private AnimatorSet animatorSet;

    public DialogLiveRoomWheat(Context paramContext, int mType) {
        super(paramContext);
        this.mContext = paramContext;
        this.mType = mType;
    }

    //用户连麦的状态
    public void setWheatData(int type, String anchorId, String streamId) {
        this.anchorId = anchorId;
        this.streamId = streamId;
        switch (type) {
            case 1://连麦
                mType = 1;
                tvCountdown.setVisibility(View.GONE);
                tvAgree.setBackgroundResource(R.drawable.bg_wheat_btn_round_blue);
                tvAgree.setText("申请连麦");
                tvAgree.setTextColor(mContext.getResources().getColor(R.color.white));
                setVis(false);
                break;
            case 2://取消连麦
                mType = 2;
                tvAgree.setBackgroundResource(R.mipmap.bg_wheat_btn_round_white);
                tvAgree.setText("取消连麦");
                tvAgree.setTextColor(mContext.getResources().getColor(R.color.color_9559FF));
                tvCountdown.setText("180s");
                tvCountdown.setVisibility(View.VISIBLE);
                setVis(true);
                break;
        }
        tvAgree.setOnClickListener(v -> {
            switch (type) {
                case 1://连麦
                    IMLiveRoomManager.instance().userCallWheat(anchorId, streamId, "link.CallHandler");
                    break;
                case 2://取消连麦
                    if (listener != null) {
                        listener.onClick(type);
                        dismiss();
                    }
                    break;
            }
        });
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_wheat);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.BOTTOM;
    }

    protected void initView() {
        //组合动画
        animatorSet = new AnimatorSet();
        sdvWheat.setImageResource(R.mipmap.icon_wheat_dialog_agress);
        setStartAnim();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.getScreenW(getContext()), ScreenUtils.dp2px(getContext(), 259));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public void setWheatTime(long time) {
        long reTime = time / 1000;
        tvCountdown.setText(reTime + "s");
        if (reTime == 0) {
            tvCountdown.setVisibility(View.GONE);
            tvCountdown.setText("180s");
            setVis(false);
            setWheatData(1, anchorId, streamId);
        }
    }

    public static interface DialogReturnListener {
        void onClick(int type);
    }

    private void setStartAnim() {
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY",
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(0.1f, 1.5f),
                Keyframe.ofFloat(0.2f, 2f),
                Keyframe.ofFloat(0.3f, 2.5f),
                Keyframe.ofFloat(0.4f, 3f),
                Keyframe.ofFloat(0.5f, 3.5f),
                Keyframe.ofFloat(0.55f, 4f),
                Keyframe.ofFloat(0.6f, 3.5f),
                Keyframe.ofFloat(0.7f, 3f),
                Keyframe.ofFloat(0.8f, 2.5f),
                Keyframe.ofFloat(0.9f, 2f),
                Keyframe.ofFloat(0.95f, 1.5f),
                Keyframe.ofFloat(1f, 1f)
        );
        animator1 = ObjectAnimator.ofPropertyValuesHolder(iv1, scaleY);
        animator2 = ObjectAnimator.ofPropertyValuesHolder(iv2, scaleY);
        animator2.setStartDelay(100);
        animator3 = ObjectAnimator.ofPropertyValuesHolder(iv3, scaleY);
        animator3.setStartDelay(200);
        animator4 = ObjectAnimator.ofPropertyValuesHolder(iv4, scaleY);
        animator4.setStartDelay(200);
        animator5 = ObjectAnimator.ofPropertyValuesHolder(iv5, scaleY);
        animator5.setStartDelay(100);
        animator6 = ObjectAnimator.ofPropertyValuesHolder(iv6, scaleY);

        animator1.setRepeatCount(360);
        animator2.setRepeatCount(360);
        animator3.setRepeatCount(360);
        animator4.setRepeatCount(360);
        animator5.setRepeatCount(360);
        animator6.setRepeatCount(360);
        animatorSet.playTogether(animator1,animator2,animator3,animator4,animator5,animator6);
        animatorSet.setDuration(800);
    }

    private void setVis(boolean isVis) {
        if (isVis) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.VISIBLE);
            iv4.setVisibility(View.VISIBLE);
            iv5.setVisibility(View.VISIBLE);
            iv6.setVisibility(View.VISIBLE);
            animatorSet.start();
        } else {
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
            iv4.setVisibility(View.GONE);
            iv5.setVisibility(View.GONE);
            iv6.setVisibility(View.GONE);
            animatorSet.pause();
        }
    }

}