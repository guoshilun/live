package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.ui.dialog.DialogLiveRoomLackBalance;
import com.jk.jkproject.ui.widget.DrawerItemView;
import com.jk.jkproject.utils.CleanDataUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.StringUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/6/9 10:02 AM
 * @desc 设置页面
 */
public class MySetActivity extends BActivity {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.div_blacklist)
    DrawerItemView divBlacklist;
    @BindView(R.id.div_clear_cache)
    DrawerItemView divClearCache;
    @BindView(R.id.div_feedback)
    DrawerItemView divFeedback;
    @BindView(R.id.tv_return_login)
    TextView tvReturnLogin;
    @BindView(R.id.tv_left_name)
    TextView tvLeftName;
    @BindView(R.id.iv_right_title)
    ImageView ivRightTitle;
    @BindView(R.id.div_account_binding)
    DrawerItemView divAccountBinding;
    @BindView(R.id.div_alerts)
    DrawerItemView divAlerts;
    @BindView(R.id.div_auto_reply)
    DrawerItemView divAutoReply;
    @BindView(R.id.div_introduce)
    DrawerItemView divIntroduce;
    private Unbinder bind;
    private DialogLiveRoomLackBalance mBalanceDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_set);
        bind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText("设置");
        divBlacklist.setVisib(R.id.iv_icon);
        divClearCache.setVisib(R.id.iv_icon);
        divFeedback.setVisib(R.id.iv_icon);
        divClearCache.setVisib(R.id.iv_icon);
        divAccountBinding.setVisib(R.id.iv_icon);
        divAlerts.setVisib(R.id.iv_icon);
        divAutoReply.setVisib(R.id.iv_icon);
        divIntroduce.setVisib(R.id.iv_icon);
        divBlacklist.setName("黑名单");
        divClearCache.setName("清除缓存");
        divFeedback.setName("意见反馈");
        divIntroduce.setName("关于锦阔电竞");
        divAccountBinding.setName("账号绑定");
        divAlerts.setName("消息通知");
        divAutoReply.setName("被关注自动回复");
        clearData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        divAccountBinding.setDes(StringUtils.strToNum(SPUtils.getNumber()));
    }

    //获取系统缓存
    private void clearData() {
        try {
            String totalCacheSize = CleanDataUtils.getTotalCacheSize(Objects.requireNonNull(this));
            if (totalCacheSize.isEmpty()) {
                divClearCache.setDes("0.00MB");
            } else {
                divClearCache.setDes(totalCacheSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.iv_title, R.id.div_blacklist, R.id.div_clear_cache, R.id.div_feedback, R.id.tv_return_login, R.id.div_account_binding, R.id.div_alerts, R.id.div_auto_reply, R.id.div_introduce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.div_blacklist:
                startActivity(new Intent(this, BlackListActivity.class));
                break;
            case R.id.div_clear_cache:
                //清除缓存
                CleanDataUtils.clearAllCache(Objects.requireNonNull(this));
                String clearSize = CleanDataUtils.getTotalCacheSize(Objects.requireNonNull(this));
                divClearCache.setDes(clearSize);
                ToastUtils.showShortToast("缓存清除成功");
                break;
            case R.id.div_feedback:
                startActivity(new Intent(this, MyFeedBack.class));
                break;
            case R.id.tv_return_login:
                setDialogTips(15);
                break;
            case R.id.div_account_binding:
                startActivity(new Intent(this,AccountBindingActivity.class));
                break;
            case R.id.div_alerts:
                startActivity(new Intent(this,AlertsActivity.class));
                break;
            case R.id.div_auto_reply:
                startActivity(new Intent(this,AutoReplyActivity.class));
                break;
            case R.id.div_introduce:
                startActivity(new Intent(this,IntroduceActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }


    //退出登录提示框
    private void setDialogTips(int mType) {
        mBalanceDialog = new DialogLiveRoomLackBalance(this, mType);
        mBalanceDialog.show();
        mBalanceDialog.setDialogClickListener(type -> {
            switch (type) {
                case 15:
                    AppApplication.getInstance().logOut(this);
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        });
        mBalanceDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

}