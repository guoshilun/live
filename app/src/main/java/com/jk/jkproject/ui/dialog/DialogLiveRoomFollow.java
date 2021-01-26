package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.JoinRoomBeanInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogLiveRoomFollow extends BaseDialog {
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_add_follow)
    TextView tvAddFollow;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.sdv_live_room_header)
    SimpleDraweeView sdvLiveRoomHeader;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    private int mType;

    private JoinRoomBeanInfo roomBeanInfo;
    private String titleName;
    private String userName;

    public DialogLiveRoomFollow(Context paramContext, JoinRoomBeanInfo paramJoinRoomBeanInfo) {
        super(paramContext);
        this.mContext = paramContext;
        this.roomBeanInfo = paramJoinRoomBeanInfo;
    }

    private void init() {
        this.tvUserName.setText(this.roomBeanInfo.getNickname());
        this.sdvLiveRoomHeader.setImageURI(this.roomBeanInfo.getPicture());
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_follow);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.BOTTOM;
    }

    protected void initView() {
        if (this.roomBeanInfo != null)
            init();
    }

    @OnClick(R.id.tv_add_follow)
    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.tv_add_follow:
                if (listener != null) {
                    listener.onClick(roomBeanInfo.getUserId());
                    dismiss();
                }
                break;
        }
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public static interface DialogReturnListener {
        void onClick(String param1String);
    }
}