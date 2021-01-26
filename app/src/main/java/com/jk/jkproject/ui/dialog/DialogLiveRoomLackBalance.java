package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogLiveRoomLackBalance extends BaseDialog {
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
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

    public DialogLiveRoomLackBalance(Context context, int mType) {
        super(context);
        this.mContext = context;
        this.type = mType;
    }

    public DialogLiveRoomLackBalance(Context context, int mType, String time) {
        super(context);
        this.mContext = context;
        this.type = mType;
        this.time = time;
    }

    private void init() {
        switch (type) {
            default:
                break;
            case 4:
            case 3:
                break;
            case 2:
                tv1.setText("确定进入直播间？");
                tv2.setText("进入直播间将导致您目前的直播关闭！");
                tvTopUp.setText("确认");
                break;
            case 1:
                tv1.setText("余额不足");
                tv2.setText("您的账户余额不足是否去充值");
                tvTopUp.setText("立即充值");
                break;
            case 6:
                tv1.setVisibility(View.GONE);
                tv2.setText("确定要将该用户踢出房间吗？");
                tvTopUp.setText("确认");
                break;
            case 7:
                tv1.setVisibility(View.GONE);
                tv2.setText("确定要将该用户禁言吗？");
                tvTopUp.setText("确认");
                break;
            case 8:
                tv1.setVisibility(View.GONE);
                tv2.setText("确定要将该用户取消禁言吗？");
                tvTopUp.setText("确认");
                break;
            case 9:
                tv1.setVisibility(View.GONE);
                tv2.setText("确定要将该用户设为管理员吗？");
                tvTopUp.setText("确认");
                break;
            case 10:
                tv1.setVisibility(View.GONE);
                tv2.setText("确定要将该用户取消管理员吗？");
                tvTopUp.setText("确认");
                break;
            case 11:
                tv1.setText("实名认证");
                tv2.setText("请先实名认证");
                tvTopUp.setText("确认");
                break;
            case 12:
                tv1.setText("实名认证");
                tv2.setText("实名认证正在审核中");
                tvTopUp.setText("确定");
                tvCancel.setVisibility(View.GONE);
                break;
            case 13:
                tv1.setText("审核未通过");
                tv2.setText(SPUtils.getStatusMessage());
                tvTopUp.setText("重新认证");
                tvCancel.setText("取消");
                break;
            case 14:
                tv1.setVisibility(View.GONE);
                tv2.setText("您的账号在其他设备登录了，请\n" +
                        "重新登录~");
                tvTopUp.setText("确定");
                tvCancel.setVisibility(View.GONE);
                break;
            case 15:
                tv1.setText("提示");
                tv2.setText("确定退出登录吗？");
                tvTopUp.setText("确定");
                tvCancel.setText("取消");
                break;
            case 16:
                tv1.setText("是否进行拉黑？");
                tv2.setText("拉黑后，Ta不能进入你的直播间，也不能关注");
                tv2.setTextSize(14);
                tvTopUp.setText("确定");
                tvCancel.setText("取消");
                tv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv2.setTypeface(Typeface.DEFAULT);
                break;
            case 17:
                tv1.setText("是否取消拉黑？");
                tv2.setText("取消拉黑后功能恢复正常");
                tvTopUp.setText("确定");
                tv2.setTextSize(14);
                tv1.setTextSize(16);
                tv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv2.setTypeface(Typeface.DEFAULT);
                tvCancel.setText("取消");
                break;
            case 18:
                tv1.setText("取消连麦");
                tv1.setTextSize(21);
                tv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv2.setText("是否取消与主播连麦？");
                tv2.setTypeface(Typeface.DEFAULT);
                tv2.setTextSize(13);
                tv2.setTextColor(mContext.getResources().getColor(R.color.color_969696));
                tvCancel.setText("取消");
                tvTopUp.setText("确定");
                tvCancel.setTextSize(13);
                tvTopUp.setTextSize(13);
                break;
            case 19:
                tv1.setText("关闭直播间");
                tv2.setText("确定要关闭直播间吗？");
                tvTopUp.setText("确认");
                tv2.setTextSize(14);
                tv1.setTextSize(14);
                tv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvTopUp.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvCancel.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvCancel.setText("取消");
                tvTopUp.setTextColor(context.getResources().getColor(R.color.color_FC5E8E));
                break;
        }

    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_balance);
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

    @OnClick({R.id.tv_cancel, R.id.tv_top_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                switch (type) {
                    case 11:
                    case 13:
                        listener.onClick(-1);
                        dismiss();
                        break;
                    default:
                        dismiss();
                        break;
                }
                break;
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