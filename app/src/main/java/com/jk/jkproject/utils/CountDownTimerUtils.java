package com.jk.jkproject.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.jk.jkproject.R;

public class CountDownTimerUtils extends CountDownTimer {
    private Context mContext;
    private TextView mTextView;

    public CountDownTimerUtils(TextView paramTextView, long paramLong1, long paramLong2) {
        super(paramLong1, paramLong2);
        this.mTextView = paramTextView;
    }

    public CountDownTimerUtils(Context mContext, TextView paramTextView, long paramLong1, long paramLong2) {
        super(paramLong1, paramLong2);
        this.mContext = mContext;
        this.mTextView = paramTextView;

    }

    public void onFinish() {
        mTextView.setText("发验证码");
        mTextView.setClickable(true);
        mTextView.setBackgroundResource(R.mipmap.icon_login_click_send_code_bg);
    }

    public void onTick(long paramLong) {
        mTextView.setClickable(false);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramLong / 1000L);
        stringBuilder.append("s");
        mTextView.setText(stringBuilder.toString());
        if (null != mContext) {
            mTextView.setTextColor(mContext.getResources().getColor(R.color.color_465B73));
        }
        mTextView.setBackgroundResource(R.mipmap.icon_login_unclick_send_code_bg);
    }
}
