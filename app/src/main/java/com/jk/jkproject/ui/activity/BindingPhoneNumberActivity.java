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

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.entity.UserDateInfo;
import com.jk.jkproject.utils.CountDownTimerUtils;
import com.jk.jkproject.utils.FastClickUtils;
import com.jk.jkproject.utils.FrescoUtil;
import com.jk.jkproject.utils.RegularConstants;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/25 2:21 PM
 * @desc 绑定手机号
 */
public class BindingPhoneNumberActivity extends BActivity {

    public static int request_code = 100;
    public static int request_phone_code = 200;
    @BindView(R.id.tv_right_name)
    TextView tvRightName;
    @BindView(R.id.sdv_bg)
    SimpleDraweeView sdvBg;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.ll_et_number)
    RelativeLayout llEtNumber;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.rl_code)
    RelativeLayout rlCode;
    @BindView(R.id.tv_login)
    ImageView tvLogin;
    @BindView(R.id.container)
    RelativeLayout container;

    private Unbinder bind;
    private String phone_prefix = "+86";
    private String password;
    private String str_phone;
    private boolean isLoginCode = true;
    private UserDateInfo userInfo;
    private String phone_code;
    int clickType = 0;
    private boolean isPhoneAble = false;
    private boolean isPasswordAble = false;
    private CountDownTimerUtils mCountDownTimerUtils;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_phone_number);
        bind = ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
    }

    private void initView() {
        FrescoUtil.loadPicInApp(sdvBg, R.mipmap.login, 0);
        mCountDownTimerUtils = new CountDownTimerUtils(this, tvSendCode, 60000, 1000);
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
                        setIsPhoneAble(1, true);
                    } else {
                        setIsPhoneAble(1, false);
                    }
                } else {
                    setIsPhoneAble(0, false);
                }
            }
        });
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    if (s.toString().trim().length() == 6) {
                        setIsPasswordAble(1, true);
                    } else {
                        setIsPasswordAble(1, false);
                    }
                } else {
                    setIsPasswordAble(0, false);
                }
            }
        });
    }

    private void setIsPhoneAble(int type, boolean isPhoneAble) {
        this.isPhoneAble = isPhoneAble;
        if (type == 1) {
            llEtNumber.setBackgroundResource(R.mipmap.icon_login_click_number_bg);
        } else {
            llEtNumber.setBackgroundResource(R.mipmap.icon_login_unclick_number);
        }
        if (isPhoneAble) {
            tvSendCode.setBackgroundResource(R.mipmap.icon_login_click_send_code_bg);
            tvSendCode.setTextColor(getResources().getColor(R.color.white));
        } else {
            tvSendCode.setBackgroundResource(R.mipmap.icon_login_unclick_send_code_bg);
            tvSendCode.setTextColor(getResources().getColor(R.color.color_465B73));
        }
        if (isPhoneAble && isPasswordAble) {
            tvLogin.setImageResource(R.mipmap.icon_binding_btn_bg);
        } else {
            tvLogin.setImageResource(R.mipmap.icon_unbinding_btn_bg);
        }
    }


    private void setIsPasswordAble(int type, boolean isPassswordAble) {
        this.isPasswordAble = isPassswordAble;
        if (type == 1) {
            etCode.setBackgroundResource(R.mipmap.icon_login_click_code_bg);
            etCode.setTextColor(getResources().getColor(R.color.color_A0D9D8));
        } else {
            etCode.setBackgroundResource(R.mipmap.icon_login_unclick_code_bg);
            etCode.setTextColor(getResources().getColor(R.color.color_475C74));
        }
        if (isPassswordAble && isPhoneAble) {
            tvLogin.setImageResource(R.mipmap.icon_binding_btn_bg);
        } else {
            tvLogin.setImageResource(R.mipmap.icon_unbinding_btn_bg);
        }
    }

    @Override
    protected void onDestroy() {
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
            ToastUtils.showShortToast("请输入手机验证码");
            return;
        }
        AppApis.getBindingNumber(phone_prefix, etNumber.getText().toString().trim(), etCode.getText().toString().trim(), this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        hideLoadingDialog();
        if (url.equals(Urls.GET_BINDING_NUMBER)) {
            if (null != obj) {
                JSONObject jb = null;
                try {
                    jb = new JSONObject((String) obj);
                    String msg = jb.getString("msg");
                    int code = jb.getInt("code");
                    if (code == 200) {
                        ToastUtils.showShortToast("手机号绑定成功");
                        SPUtils.setNumber(etNumber.getText().toString().trim());
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (url.equals(Urls.CODE_LOGIN)) {
            JSONObject jb = null;
            try {
                jb = new JSONObject((String) obj);
                String msg = jb.getString("msg");
                int code = jb.getInt("code");
                if (code == 200) {
                    ToastUtils.showShortToast("验证码发送成功");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (url.equals(Urls.GET_SKIN_BAIND)) {
            if (null != obj) {
                JSONObject jb = null;
                try {
                    jb = new JSONObject(obj.toString());
                    String msg = jb.getString("msg");
                    int code = jb.getInt("code");
                    if (code == 200) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        hideLoadingDialog();
        ToastUtils.showShortToast(error);
    }

    @OnClick({R.id.et_code, R.id.tv_send_code, R.id.tv_login, R.id.tv_right_name})
    public void onViewClicked(View view) {
        if (FastClickUtils.isFastClick()) {
            switch (view.getId()) {
                case R.id.tv_send_code:
                    if (!isPhoneAble) {
                        ToastUtils.showShortToast("请输入正确的手机格式");
                        return;
                    }
                    initEtBg();
                    tvSendCode.setClickable(false);
                    mCountDownTimerUtils.start();
                    AppApis.getPhoneCode(etNumber.getText().toString().trim(), phone_prefix, this);
                    break;
                case R.id.tv_login:
                    loginPassword();
                    break;
                case R.id.tv_right_name:
                    AppApis.getSkinBind(this);
                    break;
            }
        }
    }

    private void initEtBg() {
        etCode.setBackgroundResource(R.mipmap.icon_login_click_code_bg);
        etCode.setTextColor(getResources().getColor(R.color.color_A0D9D8));
    }

}
