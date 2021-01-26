package com.jk.jkproject.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.widget.SuperEditText;
import com.jk.jkproject.utils.CountDownTimerUtils;
import com.jk.jkproject.utils.RegularConstants;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginResetPassword extends BActivity implements SuperEditText.ITextWatcher {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.et_reset_phone)
    EditText etResetPhone;
    @BindView(R.id.et_reset_code)
    EditText etResetCode;
    @BindView(R.id.tv_reset_code)
    TextView tvResetCode;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    private int code;
    private SuperEditText passwordSuperEditText;
    private SuperEditText passwordAgainSuperEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_code);
        ButterKnife.bind(this);
        tvTitle.setText(R.string.str_reset_password);

        initData();

    }

    public void questCode() {
        String phoneNumber = etResetPhone.getText().toString().trim();
        AppApis.getPhoneCode(phoneNumber, "", this);
    }

    private void initData() {

        passwordSuperEditText = (SuperEditText) findViewById(R.id.ones_password);
        passwordSuperEditText.setTextHint(getString(R.string.str_new_password));
//        passwordSuperEditText.setMaxLength(8);
//        passwordSuperEditText.setMinLength(6);
        passwordSuperEditText.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD);
        passwordSuperEditText.showHeadIcon(R.mipmap.icon_password_show, true);
        passwordSuperEditText.setSuccessIcon(R.mipmap.icon_password_hint);
        passwordSuperEditText.setiTextWatcher(this);


        passwordAgainSuperEditText = (SuperEditText) findViewById(R.id.again_password);
        passwordAgainSuperEditText.setTextHint(getString(R.string.str_new_once_password));
//        passwordSuperEditText.setMaxLength(8);
//        passwordSuperEditText.setMinLength(6);
        passwordAgainSuperEditText.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD);
        passwordAgainSuperEditText.showHeadIcon(R.mipmap.icon_password_show, true);
        passwordAgainSuperEditText.setSuccessIcon(R.mipmap.icon_password_hint);
        passwordAgainSuperEditText.setiTextWatcher(this);


        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tvResetCode, 60000, 1000);
        mCountDownTimerUtils.start();
        tvResetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etResetPhone.getText().toString().trim().isEmpty() && etResetPhone.getText().toString().trim().matches(RegularConstants.FORMAT_PHONE)) {
                    questCode();
                } else {
                    Toast.makeText(LoginResetPassword.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });

        etResetPhone.addTextChangedListener(new TextWatcher() {
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
                        setTvLogin();
                        tvResetCode.setClickable(true);
                        tvResetCode.setTextColor(getResources().getColor(R.color.tabSelectTextColor));
                    } else {
                        setTvLogin();
                        tvResetCode.setClickable(false);
                        tvResetCode.setTextColor(getResources().getColor(R.color.color_999));
                    }
                } else {
                    setTvLogin();
                    tvResetCode.setClickable(false);
                    tvResetCode.setTextColor(getResources().getColor(R.color.color_999));
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
                    if (s.toString().trim().length() >= 6) {
                        setTvLogin();
                    } else {
                        setTvLogin();
                    }
                } else {
                    setTvLogin();
                }
            }
        });
    }

    @OnClick({R.id.iv_title, R.id.tv_title, R.id.public_top_layout, R.id.et_reset_phone, R.id.et_reset_code, R.id.tv_reset_code, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.public_top_layout:
                break;
            case R.id.et_reset_phone:
                break;
            case R.id.et_reset_code:
                break;
            case R.id.tv_reset_code:
                break;
            case R.id.tv_login:
                resetLogin();
                break;
        }
    }

    private void resetLogin() {
        if (etResetCode.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();
            return;
        }
        AppApis.getResetPassword(etResetPhone.getText().toString().trim(), passwordSuperEditText.getText().toString().trim(), passwordAgainSuperEditText.getText().toString().trim(),
                etResetCode.getText().toString().trim(),this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        JSONObject jb = null;
        try {
            jb = new JSONObject((String) obj);
            String msg = jb.getString("msg");
            code = jb.getInt("code");
            if (code == 200) {
                if (url.equals(Urls.CODE_LOGIN)) {
                    Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                } else if (url.equals(Urls.RESET_PASSWORD_LOGIN)) {
                    Toast.makeText(this, "重置密码成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);

    }

    @Override
    public void textLenght(View v, int lenght) {
        setTvLogin();
    }

    private void setTvLogin() {
        if (!passwordSuperEditText.getText().toString().trim().isEmpty() && !passwordAgainSuperEditText.getText().toString().trim().isEmpty() &&
                !etResetPhone.getText().toString().trim().isEmpty() && etResetPhone.getText().toString().trim().matches(RegularConstants.FORMAT_PHONE) &&
                !etResetCode.getText().toString().trim().isEmpty() && etResetCode.getText().toString().trim().length() >= 6) {
            tvLogin.setClickable(true);
            tvLogin.setBackground(getResources().getDrawable(R.drawable.bg_red_btn));
        } else {
            tvLogin.setClickable(false);
            tvLogin.setBackground(getResources().getDrawable(R.drawable.bg_not_btn));
        }
    }
}
