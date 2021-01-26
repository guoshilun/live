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
 * @desc 好友pk邀请Dialog
 */
public class DialogLiveRoomPKFriendInvitation extends BaseDialog {

    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_user_level_3)
    TextView tvUserLevel3;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_refused)
    TextView tvRefused;
    @BindView(R.id.tv_invitation)
    TextView tvInvitation;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;
    private int mType;

    public DialogLiveRoomPKFriendInvitation(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_pk_friend_invitation);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        gravity = Gravity.CENTER;
    }

    /**
     * @param mType  1.接收方弹窗 2.发送方弹窗 3.随机PK接受方弹窗 4.再来一局接收方弹窗
     * @param info
     */
    public void setData(int mType, LiveRoomWheatBaseInfo.DataBean info) {
        this.mType = mType;
        if (info != null) {
            sdvHeader.setImageURI(info.getPicture());
            tvName.setText(info.getNickname());
            tvUserLevel3.setText(info.getAnchorGrade());
            UserLevelSetUtils.setHostLevel(tvUserLevel3, info.getAnchorGrade());
            tvUserId.setText("JK账号:" + info.getUserId());
        }
        switch (mType) {
            case 1:
            case 3:
                tvInvitation.setText("接  受");
                tvDes.setText("邀请你进行PK");
                tvRefused.setText("拒绝19s");
                break;
            case 2:
                tvInvitation.setText("邀  请");
                tvDes.setText("是否向该主播发起PK？");
                tvRefused.setText("取  消");
                break;
            case 4:
                tvInvitation.setText("接  受");
                tvDes.setText("邀请你再来一局，是否同意？");
                tvRefused.setText("拒绝19s");
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
                if (listener != null) {
                    listener.onClick(1);
                    dismiss();
                    resetTimer();
                }
                break;
            case R.id.tv_invitation:
                if (listener != null) {
                    listener.onClick(2);
                    dismiss();
                    resetTimer();
                }
                break;
        }
    }

    public void setPKWaitTime(long remainTime) {
        long reTime = remainTime / 1000;
        tvRefused.post(() -> tvRefused.setText("拒绝" + reTime + "s"));
        if (reTime == 0) {
            tvRefused.setText("拒绝19s");
            if (listener != null) {
                listener.onClick(3);
                dismiss();
            }
        }
    }

    public static interface DialogReturnListener {
        void onClick(int type);
    }

    private void resetTimer() {

    }
}