package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.jk.jkproject.R;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/17 3:35 PM
 * @desc pk中主播断开
 */
public class DialogLiveRoomPKDisconnect extends BaseDialog {

    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    public DialogLiveRoomPKDisconnect(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    private void init() {

    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_pk_disconnect);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.CENTER;
    }

    protected void initView() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 333), ScreenUtils.dp2px(getContext(), 260));
    }

    @OnClick({R.id.tv_ok, R.id.tv_cancel})
    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_ok:
                if (listener != null) {
                    listener.onClick("1");
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