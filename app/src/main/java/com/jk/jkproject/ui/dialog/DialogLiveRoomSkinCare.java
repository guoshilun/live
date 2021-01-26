package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogLiveRoomSkinCare extends BaseDialog {
    @BindView(R.id.tv_skin_care_1)
    TextView tvSkinCare1;
    @BindView(R.id.tv_skin_care_2)
    TextView tvSkinCare2;
    @BindView(R.id.tv_skin_care_3)
    TextView tvSkinCare3;
    @BindView(R.id.tv_skin_care_4)
    TextView tvSkinCare4;
    @BindView(R.id.tv_skin_care_5)
    TextView tvSkinCare5;
    @BindView(R.id.tv_skin_care_6)
    TextView tvSkinCare6;
    @BindView(R.id.tv_skin_care_7)
    TextView tvSkinCare7;
    @BindView(R.id.tv_skin_care_8)
    TextView tvSkinCare8;
    private boolean isFlash = false;
    private boolean isMute = false;
    private Unbinder bind;

    private DialogReturnListener listener;

    private Context mContext;

    private int mType;

    private String titleName;

    private int type;

    private String userName;

    public DialogLiveRoomSkinCare(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
        this.type = this.type;
    }

    private void init() {
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_skin_care);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.h = 161;
        this.gravity = 80;
    }

    protected void initView() {
        init();
    }

    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(getContext(), 162));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick({R.id.tv_skin_care_1, R.id.tv_skin_care_2, R.id.tv_skin_care_3, R.id.tv_skin_care_4, R.id.tv_skin_care_5, R.id.tv_skin_care_6, R.id.tv_skin_care_7, R.id.tv_skin_care_8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_skin_care_1:
                listener.onClick(1);
                dismiss();
                break;
            case R.id.tv_skin_care_2:
                listener.onClick(2);
                break;
            case R.id.tv_skin_care_3:
                isMute = !isMute;
                if (isMute) {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.live_icon_skin_care_3);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvSkinCare3.setCompoundDrawables(null, drawable, null, null);
                } else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.live_icon_skin_care_9);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvSkinCare3.setCompoundDrawables(null, drawable, null, null);
                }
                listener.onClick(3);
                break;
            case R.id.tv_skin_care_4:
                isFlash = !isFlash;
                if (isFlash) {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.live_icon_skin_care_4);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvSkinCare4.setCompoundDrawables(null, drawable, null, null);
                } else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.live_icon_skin_care_10);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvSkinCare4.setCompoundDrawables(null, drawable, null, null);
                }
                listener.onClick(4);
                break;
            case R.id.tv_skin_care_5:
                listener.onClick(5);
                dismiss();
                break;
            case R.id.tv_skin_care_6:
                listener.onClick(6);
                dismiss();
                break;
            case R.id.tv_skin_care_7:
                listener.onClick(7);
                dismiss();
                break;
            case R.id.tv_skin_care_8:
                listener.onClick(8);
                dismiss();
                break;
        }
    }

    public static interface DialogReturnListener {
        void onClick(int param1Int);
    }
}