package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc pk再来一局Dialog
 */
public class DialogLiveRoomPKToGame extends BaseDialog {

    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.sdv_header_1)
    SimpleDraweeView sdvHeader1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_refused)
    TextView tvRefused;
    @BindView(R.id.tv_invitation)
    TextView tvInvitation;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;
    private int mType; //1.随机 2.好友
    private LiveRoomWheatBaseInfo.DataBean info;

    public DialogLiveRoomPKToGame(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_pk_to_game);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.CENTER;
    }

    public void setData(int mType, LiveRoomWheatBaseInfo.DataBean info) {
        this.mType = mType;
        this.info = info;
        switch (mType) {
            case 1:
                sdvHeader1.setImageURI(info.getPicture());
                tvName.setText(info.getNickname());
                tvUserLevel.setText(info.getAnchorGrade());
                UserLevelSetUtils.setHostLevel(tvUserLevel, info.getAnchorGrade());
                tvUserId.setText("邀请对方再来一局");
                tvInvitation.setText("邀   请");
                break;
            case 2:
                sdvHeader1.setImageURI(info.getPicture());
                tvName.setText(info.getNickname());
                tvUserLevel.setText(info.getAnchorGrade());
                UserLevelSetUtils.setHostLevel(tvUserLevel, info.getAnchorGrade());
                tvUserId.setText("邀请你再来一局，是否同意？");
                tvInvitation.setText("接   受");
                break;
        }
    }

    protected void initView() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 333), ScreenUtils.dp2px(getContext(), 260));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }


    @OnClick({R.id.tv_refused, R.id.tv_invitation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_refused:
                dismiss();
                break;
            case R.id.tv_invitation:
                if (listener != null) {
                    listener.onClick(null);
                    dismiss();
                }
                break;
        }
    }

    public void setPKWaitTime(long remainTime) {
        long reTime = remainTime / 1000;
        tvRefused.post(() -> tvRefused.setText("拒绝" + reTime + "s"));
        if (reTime == 0) {
            if (listener != null) {
//                listener.onClick(3);
                dismiss();
            }
        }
    }

    public static interface DialogReturnListener {
        void onClick(PKTypeBaseInfo info);
    }

}