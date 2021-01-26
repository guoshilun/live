package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
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
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.StringUtils;
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
 * @date 2020/7/24 2:15 PM
 * @desc 更换和绑定手机号
 */
public class ChangeNumberActivity extends BActivity {


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
    @BindView(R.id.tv_line)
    TextView tvLine;

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
    }

    private void setIsPasswordAble(boolean isPassswordAble) {
        this.isPassswordAble = isPassswordAble;
        if (isPassswordAble) {
            tvReturnLogin.setBackground(getResources().getDrawable(R.drawable.bg_red_btn));
        } else {
            tvReturnLogin.setBackground(getResources().getDrawable(R.drawable.bg_round_6_bc));
        }
    }

    private void initView() {
        if (getIntent() != null) {
            mType = getIntent().getIntExtra("type", -1);
        }
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        String str = getResources().getString(R.string.str_bind_number, StringUtils.strToNum(SPUtils.getNumber()));
        tvName.setText((CharSequence) Html.fromHtml(str));
        tvDesc.setVisibility(View.VISIBLE);
        tvDesc.setText("修改绑定手机号前，需先验证已绑定手机号");
        etNumber.setVisibility(View.GONE);
        tvTitle.setText("更换手机号");
        tvReturnLogin.setText("下一步");
        tvLine.setVisibility(View.GONE);
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
        } else if (url.equals(Urls.GET_CHACK_CODE)) {
            JSONObject jb = null;
            try {
                jb = new JSONObject((String) obj);
                String msg = jb.getString("msg");
                int code = jb.getInt("code");
                if (code == 200) {
                    Intent intent = new Intent(this, BindingNumber.class);
                    intent.putExtra("type", 2);
                    startActivity(intent);
                    finish();
                } else {
                    ToastUtils.showShortToast(msg);
                }
            } catch (JSONException e) {
                e.printStackTrace();
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
                if (!isPassswordAble) {
                    ToastUtils.showShortToast("请输入手机验证码");
                    return;
                }
                AppApis.getCheckCode(etResetCode.getText().toString().trim(), this);
                break;
            case R.id.tv_reset_code:
                tvResetCode.setClickable(false);
                mCountDownTimerUtils.start();
                if (SPUtils.getNumber().contains("-")) {
                    String num = SPUtils.getNumber().substring(4);
                    AppApis.getPhoneCode(num, phone_prefix, this);
                } else {
                    AppApis.getPhoneCode(SPUtils.getNumber(), phone_prefix, this);
                }
                break;
        }
    }
}
