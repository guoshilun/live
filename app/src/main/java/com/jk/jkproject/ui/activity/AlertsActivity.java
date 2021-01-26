package com.jk.jkproject.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/27 10:55 AM
 * @desc 消息通知
 */
public class AlertsActivity extends BActivity {


    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_left_name)
    TextView tvLeftName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_title)
    ImageView ivRightTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.cb_system)
    CheckBox cbSystem;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.cb_follow)
    CheckBox cbFollow;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.cb_open_live)
    CheckBox cbOpenLive;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        bind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
//        cbSystem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                cbSystem.setChecked(!isChecked);
//            }
//        });
//        cbFollow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                cbFollow.setChecked(!isChecked);
//            }
//        });
//        cbOpenLive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                cbOpenLive.setChecked(!isChecked);
//            }
//        });
    }


    private void initView() {
        tvTitle.setText("消息通知");
        tvTitle.setTextSize(18);
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        AppApis.getGetMsgNoti(this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_MSG_NOTI)) {
            if (obj != null) {
                try {
                    JSONObject object = new JSONObject(obj.toString());
                    if (object.getInt("code") == 200) {
                        JSONObject object1 = new JSONObject(object.getString("data"));
                        cbSystem.setChecked(object1.getInt("sysmsg") != 0);
                        cbFollow.setChecked(object1.getInt("atte") != 0);
                        cbOpenLive.setChecked(object1.getInt("play") != 0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @OnClick({R.id.iv_title, R.id.cb_system, R.id.cb_follow, R.id.cb_open_live})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.cb_system:
                setCb(1, cbSystem.isChecked() ? 1 : 0);
                break;
            case R.id.cb_follow:
                setCb(2, cbFollow.isChecked() ? 1 : 0);
                break;
            case R.id.cb_open_live:
                setCb(3, cbOpenLive.isChecked() ? 1 : 0);
                break;
        }
    }


    private void setCb(int type, int state) {
        AppApis.getSetMsgNoti(type, state, this);
    }


}
