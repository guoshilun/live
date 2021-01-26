package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.UpdateInfo;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/27 1:41 PM
 * @desc 版本更新弹窗
 */
public class DialogUpdateVersion extends BaseDialog {

    private final Context mContext;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_1)
    TextView tv1;
    private UpdateInfo.DataBean data;
    private Unbinder unbinder;
    private DialogReturnListener listener;

    public DialogUpdateVersion(Context mContext, UpdateInfo.DataBean data) {
        super(mContext);
        this.mContext = mContext;
        this.data = data;
    }

    private void init() {
        if (data != null) {
            tvVersion.setText(data.getVersions());
            tv1.setText(data.getContent());
        }
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_update_version);
        unbinder = ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        mWidthScale = 0.95F;
        mDimAmount = 0.6F;
        gravity = Gravity.CENTER;
    }

    protected void initView() {
        init();
    }


    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 315), ScreenUtils.dp2px(getContext(), 372));
    }

    public void setDialogClickListener(DialogReturnListener listener) {
        this.listener = listener;
    }

    @OnClick({R.id.tv_btn_1, R.id.tv_btn_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_1:
                dismiss();
                break;
            case R.id.tv_btn_2:
                if (listener != null) {
                    listener.onDialogReturnClick(1);
                    dismiss();
                }
                break;
        }
    }

    public static interface DialogReturnListener {
        void onDialogReturnClick(int type);
    }
}