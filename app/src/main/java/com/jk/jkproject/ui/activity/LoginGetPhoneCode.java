package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.manager.IMLoginManager;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.entity.UserDateInfo;
import com.jk.jkproject.utils.CountDownTimerUtils;
import com.jk.jkproject.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginGetPhoneCode extends BActivity implements ResponseListener {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.tv_code_phone)
    TextView tvCodePhone;
    @BindView(R.id.et_reset_code)
    EditText etResetCode;
    @BindView(R.id.tv_code_time)
    TextView tvCodeTime;
    @BindView(R.id.tv_reset_send)
    TextView tvResetSend;
    @BindView(R.id.tv_login)
    TextView tvCode;
    @BindView(R.id.tv_reset_code)
    TextView tvResetCode;
    private String phoneNumber, phone_prefix;
    private int code;
    private String phone_code;
    private UserDateInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reset_password);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        phone_prefix = getIntent().getStringExtra("phone_prefix");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        String phone = phone_prefix + " " + phoneNumber;
        tvCodePhone.setText(phone);

        if (phoneNumber.length() > 0) {
            questCode();
        }
    }

    public void questCode() {
        AppApis.getPhoneCode(phoneNumber, phone_prefix, this);
    }

    private void initView() {

        tvTitle.setVisibility(View.GONE);

        ivTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tvResetCode, 60000, 1000);
        mCountDownTimerUtils.start();

        tvResetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questCode();
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
                        tvCode.setClickable(true);
                        tvCode.setBackground(getResources().getDrawable(R.drawable.bg_red_btn));
                    } else {
                        tvCode.setClickable(false);
                        tvCode.setBackground(getResources().getDrawable(R.drawable.bg_not_btn));
                    }
                } else {
                    tvCode.setClickable(false);
                    tvCode.setBackground(getResources().getDrawable(R.drawable.bg_not_btn));
                }
            }
        });
        tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questData();
            }
        });

    }

    public void questData() {
        phone_code = etResetCode.getText().toString().trim();
//        AppApis.login(phoneNumber, "", 2, Integer.parseInt(phone_code), phone_prefix, this);
//        AppApis.login(phoneNumber, "",0, etCode.getText().toString().trim(), phone_prefix, this);

    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.CODE_LOGIN)) {
            JSONObject jb = null;
            try {
                jb = new JSONObject((String) obj);
                String msg = jb.getString("msg");
                code = jb.getInt("code");
                if (code == 200) {
                    Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (url.equals(Urls.USER_LOGIN)) {
            if (null != obj) {
                userInfo = (UserDateInfo) obj;
                if (userInfo.getCode() == 200) {
                    SPUtils.saveUserTokenInfo(userInfo.getData());
                    IMLoginManager.instance().tryReconnectServer();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
    }
}
