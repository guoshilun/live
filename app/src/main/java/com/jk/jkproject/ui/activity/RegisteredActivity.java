package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.manager.IMLoginManager;
import com.jk.jkproject.ui.entity.UserDateInfo;
import com.jk.jkproject.utils.CountDownTimerUtils;
import com.jk.jkproject.utils.RegularConstants;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;

import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/25 2:21 PM
 * @desc 账号注册和找回密码
 */
public class RegisteredActivity extends BActivity {

    public static int request_code = 100;
    public static int request_phone_code = 200;
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
    @BindView(R.id.sdv_log)
    SimpleDraweeView sdvLog;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.ll_et_number)
    RelativeLayout llEtNumber;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.rl_code)
    RelativeLayout rlCode;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.et_reset_code)
    EditText etResetCode;
    @BindView(R.id.iv_clear_password)
    ImageView ivClearPassword;
    @BindView(R.id.iv_hint_password)
    CheckBox ivHintPassword;
    @BindView(R.id.tv_reset_code)
    TextView tvResetCode;
    @BindView(R.id.ll_et_code)
    LinearLayout llEtCode;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.container)
    RelativeLayout container;


    private Unbinder bind;
    private String phone_prefix = "+86";
    private UserDateInfo userInfo;
    private boolean isPhoneAble = false;
    private boolean isPasswordAble = false;
    private Timer timer;
    private int cnt = 29;
    private int mType; //1.忘记密码 2.注册 3.绑定手机号
    private CountDownTimerUtils mCountDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        bind = ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        if (getIntent() != null) {
            mType = getIntent().getIntExtra("type", -1);
        }

        switch (mType) {
            case 1:
                tvTitle.setText("找回密码");
                break;
            case 2:
                tvTitle.setText("注册账号");
                break;
            case 3:
                tvTitle.setText("绑定手机号");
                tvRightTitle.setText("跳过");
                tvRightTitle.setVisibility(View.VISIBLE);
                tvRightTitle.setTextColor(getResources().getColor(R.color.color_333));
                tvRightTitle.setTextSize(16);
                break;
        }
    }

    private void initView() {
        mCountDownTimerUtils = new CountDownTimerUtils(tvSendCode, 60000, 1000);
        etResetCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
        ivHintPassword.setChecked(false);
        ivHintPassword.setOnCheckedChangeListener((param1CompoundButton, param1Boolean) -> {
            if (param1Boolean) {
                etResetCode.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                etResetCode.setSelection(etResetCode.getText().length());
            } else {
                etResetCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
                etResetCode.setSelection(etResetCode.getText().length());
            }
        });
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    if (s.toString().trim().matches(RegularConstants.FORMAT_PHONE)) {
                        setIsPhoneAble(true);
                    } else {
                        setIsPhoneAble(false);
                    }
                } else {
                    setIsPhoneAble(false);
                }
            }
        });
        etResetCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    ivHintPassword.setVisibility(View.VISIBLE);
                    ivClearPassword.setVisibility(View.VISIBLE);
                    if (s.toString().trim().matches(RegularConstants.FORMAT_PWD)) {
                        setIsPasswordAble(true);
                    } else {
                        setIsPasswordAble(false);
                    }
                } else {
                    ivHintPassword.setVisibility(View.GONE);
                    ivClearPassword.setVisibility(View.GONE);
                    setIsPasswordAble(false);
                }
            }
        });

    }


    private void setIsPhoneAble(boolean isPhoneAble) {
        this.isPhoneAble = isPhoneAble;
        if (isPhoneAble && isPasswordAble) {
            tvLogin.setBackground(getResources().getDrawable(R.drawable.bg_red_btn));
        } else {
            tvLogin.setBackground(getResources().getDrawable(R.drawable.bg_round_6_bc));
        }
    }

    private void setIsPasswordAble(boolean isPassswordAble) {
        this.isPasswordAble = isPassswordAble;
        if (isPassswordAble && isPhoneAble) {
            tvLogin.setBackground(getResources().getDrawable(R.drawable.bg_red_btn));
        } else {
            tvLogin.setBackground(getResources().getDrawable(R.drawable.bg_round_6_bc));
        }
    }

    @Override
    protected void onDestroy() {
        mCountDownTimerUtils.cancel();
        super.onDestroy();
        bind.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            if (requestCode == request_code) {
//                phone_prefix = data.getStringExtra("phone_prefix");
//                tvCode.setText(phone_prefix);
//            } else if (requestCode == request_phone_code) {
//                phone_code = data.getStringExtra("phone_code");
//            }
        }
    }

    private void loginPassword() {
        if (!isPhoneAble) {
            ToastUtils.showShortToast("请输入正确的手机格式");
            return;
        }
        if (!isPasswordAble) {
            ToastUtils.showShortToast("请输入8-12位数字加字母的密码");
            return;
        }
//        AppApis.login(etNumber.getText().toString().trim(), etResetCode.getText().toString().trim(), 1, 0, phone_prefix, this);
        AppApis.login(etNumber.getText().toString().trim(), "",0, etCode.getText().toString().trim(), phone_prefix, this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        hideLoadingDialog();
        if (url.equals(Urls.USER_LOGIN)) {
            if (null != obj) {
                userInfo = (UserDateInfo) obj;
                if (userInfo.getCode() == 200) {
                    SPUtils.saveUserTokenInfo(userInfo.getData());
                    IMLoginManager.instance().tryReconnectServer();
                } else {
                    Toast.makeText(this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        hideLoadingDialog();
        if (url.equals(Urls.USER_LOGIN)) {
            if (null != error) {
                LogUtils.e(error);
            }
        }
    }

    @OnClick({R.id.iv_title, R.id.tv_send_code, R.id.iv_clear_password, R.id.tv_login, R.id.tv_right_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_send_code:
                tvSendCode.setClickable(false);
                mCountDownTimerUtils.start();
                break;
            case R.id.iv_clear_password:
                etResetCode.setText("");
                ivClearPassword.setVisibility(View.GONE);
                break;
            case R.id.tv_login:
                break;
            case R.id.tv_right_title:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
