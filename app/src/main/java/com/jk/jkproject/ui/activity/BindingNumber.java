package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.utils.CountDownTimerUtils;
import com.jk.jkproject.utils.RegularConstants;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/24 2:15 PM
 * @desc 更换和绑定手机号
 */
public class BindingNumber extends BActivity {


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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.ll_et_number)
    LinearLayout llEtNumber;
    @BindView(R.id.et_reset_code)
    EditText etResetCode;
    @BindView(R.id.tv_reset_code)
    TextView tvResetCode;
    @BindView(R.id.ll_et_code)
    LinearLayout llEtCode;
    @BindView(R.id.tv_return_login)
    TextView tvReturnLogin;
    private Unbinder bind;
    private int mType;
    private CountDownTimerUtils mCountDownTimerUtils;
    private boolean isPhoneAble;
    private boolean isPassswordAble;
    private String phone_prefix = "+86";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_number);
        bind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        mCountDownTimerUtils = new CountDownTimerUtils(tvResetCode, 60000, 1000);
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
                    if (RegularConstants.CHINA_PATTERN.matcher(s.toString().trim()).matches()) {
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
                    if (s.toString().trim().length() == 6) {
                        setIsPasswordAble(true);
                    } else {
                        setIsPasswordAble(false);
                    }
                } else {
                    setIsPasswordAble(false);
                }
            }
        });

        setEditTextInputSpeChat(etNumber);
    }

    private void setIsPhoneAble(boolean isPhoneAble) {
        this.isPhoneAble = isPhoneAble;
        if (isPhoneAble && isPassswordAble) {
            tvReturnLogin.setBackground(getResources().getDrawable(R.drawable.bg_red_btn));
        } else {
            tvReturnLogin.setBackground(getResources().getDrawable(R.drawable.bg_round_6_bc));
        }
    }


    private void setIsPasswordAble(boolean isPassswordAble) {
        this.isPassswordAble = isPassswordAble;
        if (isPassswordAble && isPhoneAble) {
            tvReturnLogin.setBackground(getResources().getDrawable(R.drawable.bg_red_btn));
        } else {
            tvReturnLogin.setBackground(getResources().getDrawable(R.drawable.bg_round_6_bc));
        }
    }

    private void initView() {
        if (getIntent() != null) {
            mType = getIntent().getIntExtra("type", -1);
        }
        tvTitle.setText("账号绑定");
        tvName.setVisibility(View.VISIBLE);
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        switch (mType) {
            case 1:
                tvName.setText("请输入您的手机号进行绑定");
                tvDesc.setVisibility(View.GONE);
                etNumber.setHint("请输入手机号");
                break;
            case 2:
//                String str = getResources().getString(R.string.str_bind_number, StringUtils.strToNum(SPUtils.getNumber()));
//                tvName.setText((CharSequence) Html.fromHtml(str));
                tvName.setVisibility(View.GONE);
                tvDesc.setVisibility(View.VISIBLE);
                etNumber.setHint("请输入新手机号");
                tvTitle.setText("更换手机号");
                break;
            case 3:
                tvName.setText("请输入您的手机号进行绑定");
                tvDesc.setVisibility(View.GONE);
                etNumber.setHint("请输入手机号");
                tvRightTitle.setVisibility(View.VISIBLE);
                tvRightTitle.setText("跳过");
                tvRightTitle.setTextColor(getResources().getColor(R.color.color_333));
                ivTitle.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.CODE_LOGIN)) {
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
        } else if (url.equals(Urls.GET_BINDING_NUMBER)) {
            if (null != obj) {
                JSONObject jb = null;
                try {
                    jb = new JSONObject((String) obj);
                    String msg = jb.getString("msg");
                    int code = jb.getInt("code");
                    if (code == 200) {
                        ToastUtils.showShortToast(msg);
                        SPUtils.setNumber(etNumber.getText().toString().trim());
                        switch (mType) {
                            case 1:
                            case 2:
                                finish();
                                break;
                            default:
                                startActivity(new Intent(this, MainActivity.class));
                                finish();
                                break;
                        }

                    } else {
                        ToastUtils.showShortToast(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    @OnClick({R.id.iv_title, R.id.tv_return_login, R.id.tv_right_title, R.id.tv_reset_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_return_login:
                if (!isPhoneAble) {
                    ToastUtils.showShortToast("请输入正确的手机格式");
                    return;
                }
                if (!isPassswordAble) {
                    ToastUtils.showShortToast("请输入手机验证码");
                    return;
                }
                AppApis.getBindingNumber(phone_prefix, etNumber.getText().toString().trim(), etResetCode.getText().toString().trim(), this);
                switch (mType) {
                    case 3: // 绑定手机号
                        break;
                }
                break;
            case R.id.tv_right_title:
                if (mType == 3) {
                    AppApis.getSkinBind(this);
                }
                break;
            case R.id.tv_reset_code:
                if (!isPhoneAble) {
                    ToastUtils.showShortToast("请输入正确的手机格式");
                    return;
                }
                tvResetCode.setClickable(false);
                mCountDownTimerUtils.start();
                AppApis.getPhoneCode(etNumber.getText().toString().trim(), phone_prefix, this);
                break;
        }
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText EditText输入框
     */
    public static void setEditTextInputSpeChat(EditText editText) {
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {
            String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern pattern = Pattern.compile(speChat);
            Matcher matcher = pattern.matcher(source.toString());
            if (source.equals(" ") || source.toString().contentEquals("\n")) {
                return "";
            } else if (matcher.find()) {
                return "";
            } else {
                return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(11)});
    }
}
