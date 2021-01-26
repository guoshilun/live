package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogWarningNotices extends BaseDialog {

    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_top_up)
    TextView tvTopUp;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    private int mType;

    private String titleName;

    private int type;

    private String userName, time, cause;

    public DialogWarningNotices(Context context, int mType) {
        super(context);
        this.mContext = context;
        this.type = mType;
    }

    public DialogWarningNotices(Context context, int mType, String time, String cause) {
        super(context);
        this.mContext = context;
        this.type = mType;
        this.time = time;
        this.cause = cause;
    }

    private void init() {
        switch (type) {
            case 1:
            case 4:
                tv2.setText("该账号已经被禁播！");
                tv3.setText("禁播原因：" + cause);
                tv4.setText("解禁时间：" + (time.equals("永久封号") ? "永久封号" : TimeUtils.millis2String(Long.parseLong(time))));
                tvTopUp.setText("确定");
                break;
            case 2:
                tv2.setText("该账号已经被封停！");
                tv3.setText("封停原因：" + cause);
                tv4.setText("解封时间：" + (time.equals("永久封号") ? "永久封号" : TimeUtils.millis2String(Long.parseLong(time))));
                tvTopUp.setText("确定");
            case 3:
                tv2.setText("该账号已经被封停！");
                tv3.setText(cause);
                tv4.setText(time);
                tvTopUp.setText("确定");
                break;
            default:
                break;
        }
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_warning_notices);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.h = 205;
        this.gravity = Gravity.CENTER;
    }

    protected void initView() {
        init();
    }

    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 301), ScreenUtils.dp2px(getContext(), 205));
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