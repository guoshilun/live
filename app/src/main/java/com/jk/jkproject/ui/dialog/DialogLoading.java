package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.utils.FrescoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DialogLoading extends BaseDialog {

    @BindView(R.id.data_loading)
    SimpleDraweeView dataLoading;
    private Unbinder unbinder;

    public DialogLoading(Context context) {
        super(context);
    }

    @Override
    protected void create(Bundle b) {
        setContentView(R.layout.dialog_loading);
        unbinder = ButterKnife.bind(this);
        this.mWidthScale = 1F;
        setCanceledOnTouchOutside(false);
        this.gravity = Gravity.CENTER;
    }

    @Override
    protected void initView() {
        FrescoUtil.loadGifPicInApp(dataLoading, R.drawable.load_data);
    }
}
