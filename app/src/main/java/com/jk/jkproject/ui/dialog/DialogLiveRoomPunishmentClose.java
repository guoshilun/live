package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/17 3:35 PM
 * @desc
 */
public class DialogLiveRoomPunishmentClose extends BaseDialog {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;
    private int type;
    private DialogLiveRoomPunishmentClose mPKPunishClose;

    public DialogLiveRoomPunishmentClose(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    private void init() {

    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_punishment);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.CENTER;
    }

    protected void initView() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 333), ScreenUtils.dp2px(getContext(), 220));
    }

    @OnClick({R.id.tv_cancel, R.id.tv_ok})
    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.tv_cancel:
                switch (type) {
                    case 1:
                        if (listener != null) {
                            listener.onClick(2);
                        }
                        break;
                }
                dismiss();
                break;
            case R.id.tv_ok:
                switch (type) {
                    case 1:
                        if (listener != null) {
                            listener.onClick(3);
                        }
                        break;
                    case 2:
                        if (listener != null) {
                            listener.onClick(1);
                        }
                        break;
                }
                dismiss();
                break;
        }
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public void setData(int type) {
        this.type = type;
        switch (type) {
            case 1:
                tvTitle.setText("取消随机匹配");
                tvContent.setText("确定要取消随机匹配？");
                break;
            case 2:
                tvTitle.setText("断开连接");
                tvContent.setText("你确定要断开连接吗？");
                break;
        }
    }

    public static interface DialogReturnListener {
        void onClick(int type);
    }
}