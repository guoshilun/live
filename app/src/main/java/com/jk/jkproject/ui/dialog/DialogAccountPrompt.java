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

public class DialogAccountPrompt extends BaseDialog {
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_top_up)
    TextView tvTopUp;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    private int mType;

    private String titleName;

    private int type;

    private String userName;
    private String time, cause;

    public DialogAccountPrompt(Context context, int mType) {
        super(context);
        this.mContext = context;
        this.type = mType;
    }

    public DialogAccountPrompt(Context context, int mType, String time) {
        super(context);
        this.mContext = context;
        this.type = mType;
        this.time = time;
    }

    private void init() {
        switch (type) {
            case 14:
                tv1.setVisibility(View.VISIBLE);
                tvTopUp.setText("确定");
                break;
        }

    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_account_prompt);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.h = 161;
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