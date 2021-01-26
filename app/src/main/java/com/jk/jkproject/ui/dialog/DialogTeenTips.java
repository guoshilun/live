package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/27 1:41 PM
 * @desc 青少年提示弹窗
 */
public class DialogTeenTips extends BaseDialog {

    private final Context mContext;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_btn_1)
    TextView tvBtn1;
    @BindView(R.id.tv_btn_2)
    TextView tvBtn2;
    private Unbinder unbinder;
    private DialogReturnListener listener;

    public DialogTeenTips(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    private void init() {

    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_teen_tips);
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
                if (listener != null) {
                    listener.onDialogReturnClick(1);
                }
                break;
            case R.id.tv_btn_2:
                SPUtils.setTeenTips(false);
                dismiss();
                break;
        }
    }

    public static interface DialogReturnListener {
        void onDialogReturnClick(int type);
    }
}