package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.adapter.LiveRoomPkListAdapter;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc 好友pk邀请失败Dialog
 */
public class DialogLiveRoomPKFriendInFailure extends BaseDialog {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_other_invitation)
    TextView tvOtherInvitation;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;
    private int mType;
    private LiveRoomPkListAdapter mAdapter;
    private DialogLiveRoomPKRecord mPkRecordDialog;

    public DialogLiveRoomPKFriendInFailure(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }


    /**
     * //填充数据
     *
     * @param mType   1.好友拒绝 2.好友超时
     * @param mPKInfo
     */
    public void setData(int mType, LiveRoomWheatBaseInfo.DataBean mPKInfo) {
        sdvHeader.setImageURI(mPKInfo.getPicture());
        tvName.setText(mPKInfo.getNickname());
        tvUserId.setText("JK账号:" + mPKInfo.getUserId());

        switch (mType) {
            case 1:
                tvDes.setText("拒绝了你的PK邀请");
                break;
            case 2:
                tvDes.setText("等待邀请超时,请重试");
                break;
        }
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_pk_friend_invitation_failure);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        gravity = Gravity.BOTTOM;
    }

    protected void initView() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.getScreenW(getContext()), ScreenUtils.dp2px(getContext(), 259));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick({R.id.tv_other_invitation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_other_invitation:
                if (listener != null) {
                    listener.onClick(null);
                    dismiss();
                }
                break;
        }
    }


    public static interface DialogReturnListener {
        void onClick(PKTypeBaseInfo info);
    }

}