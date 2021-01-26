package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogNotices extends BaseDialog {


    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.tv_top_up)
    TextView tvTopUp;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    private int mType;

    private String titleName;

    private int type;

    private String userName, time, cause;

    public DialogNotices(Context context, int mType) {
        super(context);
        this.mContext = context;
        this.type = mType;
    }

    public DialogNotices(Context context, int mType, String time, String cause) {
        super(context);
        this.mContext = context;
        this.type = mType;
        this.time = time;
        this.cause = cause;
    }

    private void init() {
        switch (type) {
            case 1:
                tv1.setText(cause);
                tvTopUp.setText("我知道了");
                break;
            default:
                break;
        }

    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_notices);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.h = 145;
        this.gravity = Gravity.CENTER;
    }

    protected void initView() {
        init();
    }

    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 301), ScreenUtils.dp2px(getContext(), 145));
    }

    @OnClick({R.id.tv_top_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_up:
                if (listener != null) {
                    listener.onClick(type);
                    dismiss();
                }
                break;
        }
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public static interface DialogReturnListener {
        void onClick(int type);
    }
}