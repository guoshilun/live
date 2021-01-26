package com.jk.jkproject.ui.dialog;


import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.Zego;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.zego.zegoexpress.entity.ZegoBeautifyOption;

public class DialogHostBeautySet extends BaseDialog implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.seekbar_1)
    SeekBar seekbar1;
    @BindView(R.id.seekbar_2)
    SeekBar seekbar2;
    @BindView(R.id.seekbar_3)
    SeekBar seekbar3;
    private Context mContext;
    private TextureView mPusherView;
    private boolean isSkin;
    private int progress_1, progress_2, progress_3;

    private int mType;

    private DialogReturnListener listener;
    private ZegoBeautifyOption zegoBeautifyOption;


    public DialogHostBeautySet(Context context, TextureView mPusherView, boolean isSkin) {
        super(context);
        this.mContext = context;
        this.mPusherView = mPusherView;
        this.isSkin = isSkin;
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_host_beauty);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 1.0F;
        this.mDimAmount = 0.6F;
        this.h = 180;
        this.gravity = Gravity.BOTTOM;
    }

    protected void initView() {
        zegoBeautifyOption = new ZegoBeautifyOption();
        seekbar1.setOnSeekBarChangeListener(this);
        seekbar2.setOnSeekBarChangeListener(this);
        seekbar3.setOnSeekBarChangeListener(this);
        if (isSkin) {
            String beauty = SPUtils.getString("beauty", "");
            if (!beauty.isEmpty()) {
                String seekBar[] = beauty.split(",");
                seekbar1.setProgress(Integer.parseInt(seekBar[0]));
                seekbar2.setProgress(Integer.parseInt(seekBar[1]));
                seekbar3.setProgress(Integer.parseInt(seekBar[2]));
                if (Integer.parseInt(seekBar[0]) > 0) {
                    zegoBeautifyOption.polishStep = Integer.parseInt(seekBar[0]) / 10;
                }
                if (Integer.parseInt(seekBar[0]) > 0) {
                    zegoBeautifyOption.whitenFactor = Integer.parseInt(seekBar[0]) / 10;
                }
                if (Integer.parseInt(seekBar[0]) > 0) {
                    zegoBeautifyOption.sharpenFactor = Integer.parseInt(seekBar[0]) / 10;
                }
            }
        } else {
            zegoBeautifyOption.polishStep = 0.5;
            zegoBeautifyOption.whitenFactor = 0.5;
            zegoBeautifyOption.sharpenFactor = 0.5;
        }
        Zego.instance().setBeautifyOption(zegoBeautifyOption);
        this.setOnDismissListener(dialog -> {
            if (listener != null) {
                listener.onClick(progress_1, progress_2, progress_3);
            }
        });
    }


    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(getContext(), 180));
    }

    public void setDialogClickListener(DialogReturnListener listener) {
        this.listener = listener;
    }

    /**
     * style -美颜风格。三种美颜风格：0：光滑1：自然2：朦胧
     * beautyLevel -美颜等级。美颜等级即beautyLevel取值0-9。取变量0时代表关闭美颜效果。值0：即关闭美颜效果。
     * whiteningLevel -美白等级。美白等级即whiteningLevel取值0-9。取变量0时代表关闭美白效果。值​​0：即关闭美白效果。
     * ruddyLevel -红润等级。美白等级即ruddyLevel取变量0-9。取变量0时代表关闭美白效果。值​​0：即关闭美白效果。
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        LogUtils.e("progress=", progress);
        switch (seekBar.getId()) {
            case R.id.seekbar_1:
                zegoBeautifyOption.polishStep = progress / 10;
                progress_1 = progress;
                break;
            case R.id.seekbar_2:
                zegoBeautifyOption.whitenFactor = progress / 10;
                progress_2 = progress;
                break;
            case R.id.seekbar_3:
                zegoBeautifyOption.sharpenFactor = progress / 10;
                progress_3 = progress;
                break;
        }
        Zego.instance().setBeautifyOption(zegoBeautifyOption);
        SPUtils.putString("beauty", progress_1 + "," + progress_2 + "," + progress_3);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public static interface DialogReturnListener {
        void onClick(int pr_1, int pr_2, int pr_3);
    }
}
