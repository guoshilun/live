package com.jk.jkproject.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPersonalHome extends BActivity {
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_personal_home);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        publicTopLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
    }
}