package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc 金币游戏结算
 */
public class DialogLiveRoomGameGoldSettlement extends BaseDialog {

    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_pk_record)
    TextView tvPkRecord;
    @BindView(R.id.tv_gold_count)
    TextView tvGoldCount;
    @BindView(R.id.iv_title)
    TextView ivTitle;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;


    public DialogLiveRoomGameGoldSettlement(Context paramContext, int mType) {
        super(paramContext);
        this.mContext = paramContext;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_gold_game_settlement);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.CENTER;
    }

    @Override
    public void show() {
        super.show();

    }


    protected void initView() {
//        tvPkRecord.setText("恭喜！赢了哦！");
//        tvGoldCount.setText("X 200000000");
    }


    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 333), ScreenUtils.dp2px(getContext(), 260));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick(R.id.iv_title)
    public void onViewClicked() {
        if (listener != null) {
            listener.onClick(null);
            dismiss();
        }
    }

    public void setData(int num) {
        tvPkRecord.setText("恭喜！赢了哦！");
        tvGoldCount.setText("X " + num);
    }

    public static interface DialogReturnListener {
        void onClick(PKTypeBaseInfo info);
    }
}