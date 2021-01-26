package com.jk.jkproject.ui.activity;

import android.os.Bundle;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TextActivity1 extends BActivity {


    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        bind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

    }
    private void initView() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
