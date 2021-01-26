package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.utils.ScreenUtils;
import com.opensource.svgaplayer.SVGAParser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc 金币游戏Dialog
 */
public class DialogLiveRoomGameGoldHint extends BaseDialog {

    private static SVGAParser parser;
    @BindView(R.id.iv_title)
    TextView ivTitle;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;

    public DialogLiveRoomGameGoldHint(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_game_gold_hint);
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
        getWindow().setLayout(ScreenUtils.dp2px(mContext, 333), ScreenUtils.dp2px(getContext(), 300));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick(R.id.iv_title)
    public void onViewClicked() {
        dismiss();
    }


    public static interface DialogReturnListener {
        void onClick(PKTypeBaseInfo info);
    }
}