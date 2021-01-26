package com.jk.jkproject.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.manager.IMLoginManager;
import com.jk.jkproject.ui.dialog.DialogAccountPrompt;
import com.jk.jkproject.ui.dialog.DialogWarningNotices;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.ui.entity.UserDateInfo;
import com.jk.jkproject.utils.CountDownTimerUtils;
import com.jk.jkproject.utils.FastClickUtils;
import com.jk.jkproject.utils.FrescoUtil;
import com.jk.jkproject.utils.RegularConstants;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.sharesdk.LoginApi;
import com.jk.jkproject.utils.sharesdk.OnLoginListener;
import com.jk.jkproject.utils.sharesdk.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * @author Zick
 * @params
 * @date 2020/7/25 2:21 PM
 * @desc 登录页面
 */
public class LoginActivity extends BActivity implements Observer {

    public static int request_code = 100;
    public static int request_phone_code = 200;
    @BindView(R.id.sdv_log)
    SimpleDraweeView sdvLog;
    @BindView(R.id.sdv_bg)
    SimpleDraweeView sdv_bg;
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
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.iv_wx_login)
    ImageView ivWxLogin;
    @BindView(R.id.iv_qq_login)
    ImageView ivQqLogin;
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.tv_agree1)
    TextView tvAgree1;


    private Unbinder bind;
    private String phone_prefix = "+86";
    private String password;
    private String str_phone;
    private boolean isLoginCode = true;
    private UserDateInfo userInfo;
    private String phone_code;
    int clickType = 0;
    String openid = "";
    String access_token = "";
    String refresh_token = "";
    private boolean isPhoneAble = false;
    private boolean isPasswordAble = false;
    private CountDownTimerUtils mCountDownTimerUtils;
    private int type;
    private DialogAccountPrompt mBalanceDialog;
    private DialogWarningNotices warningNoti;
    private int PERMISSION_REQUEST_CODE_STORAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bind = ButterKnife.bind(this);
        initData();
        initView();
        requestPermission();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, PERMISSION_REQUEST_CODE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE_STORAGE == requestCode) {
            if (grantResults != null && grantResults.length >= 1 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                requestPermission();
                ToastUtils.showShortToast("拒绝权限可能无法正常体验所有功能");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initData() {
        MessageNotifyCenter.getInstance().addObserver(this);
        if (getIntent() != null && getIntent().hasExtra("type")) {
            type = getIntent().getIntExtra("type", -1);
        } else if (getIntent() != null && getIntent().hasExtra("message")) {
            SystemNotiBeanInfo.DataBean message = (SystemNotiBeanInfo.DataBean) getIntent().getSerializableExtra("message");
            setShowDialog(2, message.getTime(), message.getMessage());
        }
        switch (type) {
            case 1:
                showTopUpDialog();
                break;
            case 2:
                ToastUtils.showLongToast("服务器连接失败");
                break;
            default:
//                if (!SPUtils.getIP().isEmpty()) {
//                    startActivity(new Intent(this, MainActivity.class));
//                    finish();
//                }
                break;
        }
    }

    private void initView() {
//        FrescoUtil.loadGifPicInApp(sdv_bg, R.drawable.login, 0);
        FrescoUtil.loadPicInApp(sdv_bg, R.mipmap.login, 0);
        sdvLog.setVisibility(View.GONE);
        String str = getResources().getString(R.string.str_acc_agreement);
        tvAgree.setText(Html.fromHtml(str));
        String str1 = getResources().getString(R.string.str_acc_agreement1);
        tvAgree1.setText(Html.fromHtml(str1));
        mCountDownTimerUtils = new CountDownTimerUtils(this, tvSendCode, 60000, 1000);
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 11) {
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    if (RegularConstants.CHINA_PATTERN.matcher(s.toString().trim()).matches()) {
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
        setEditTextInputSpeChat(etNumber);
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
            tvLogin.setImageResource(R.mipmap.icon_login_click_btn_bg);
        } else {
            tvLogin.setImageResource(R.mipmap.icon_login_btn_bg);
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
            tvLogin.setImageResource(R.mipmap.icon_login_click_btn_bg);
        } else {
            tvLogin.setImageResource(R.mipmap.icon_login_btn_bg);
        }
    }

    @Override
    protected void onDestroy() {
        MessageNotifyCenter.getInstance().unregister(this);
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
        AppApis.login(etNumber.getText().toString().trim(), "", 0, etCode.getText().toString().trim(), phone_prefix, this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        hideLoadingDialog();
        if (url.equals(Urls.USER_LOGIN) || url.equals(Urls.SET_WX_LOGIN) || url.equals(Urls.SET_QQ_LOGIN)) {
            if (null != obj) {
                userInfo = (UserDateInfo) obj;
                if (userInfo.getCode() == 200) {
                    SPUtils.saveUserTokenInfo(userInfo.getData());
                    LogUtils.e("tryReconnectServer=3", "重连服务器");
                    IMLoginManager.instance().tryReconnectServer();
                } else {
                    ToastUtils.showShortToast(userInfo.getMsg());
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

    @OnClick({R.id.et_code, R.id.tv_send_code, R.id.tv_login, R.id.iv_wx_login, R.id.iv_qq_login, R.id.tv_agree, R.id.tv_agree1})
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
                case R.id.iv_wx_login:
                    login(Wechat.NAME);
                    break;
                case R.id.iv_qq_login:
                    login(QQ.NAME);
                    break;
                case R.id.tv_agree:
                    Intent intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra("type", 9);
                    startActivity(intent);
                    break;
                case R.id.tv_agree1:
                    Intent intent1 = new Intent(this, WebViewActivity.class);
                    intent1.putExtra("type", 13);
                    startActivity(intent1);
                    break;
            }
        }
    }

    private void initEtBg() {
        etCode.setBackgroundResource(R.mipmap.icon_login_click_code_bg);
        etCode.setTextColor(getResources().getColor(R.color.color_A0D9D8));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            Message message = Message.obtain();
            message.obj = arg;
            this.handler.sendMessage(message);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = (Bundle) msg.obj;
            switch (bundle.getInt("method")) {
                case IMEventType.ACTION_LOGIN_SUCCESS:
                    if (LoginActivity.this != null) {
                        if (SPUtils.getNumber().equals("未设置") && SPUtils.getIsSkip().equals("-1")) {
                            Intent intent = new Intent(LoginActivity.this, BindingPhoneNumberActivity.class);
                            intent.putExtra("type", 3);
                            startActivity(intent);
                            finish();
                        } else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                    break;
                case IMEventType.ACTION_LOGIN_OUT:
                    if (LoginActivity.this != null) {
                        switch (bundle.getInt("type")) {
                            case 2: //账号异常登录
                                String message = bundle.getString("message1");
                                String msg1[] = message.split("\\|");
                                setShowDialog(3, msg1[2], msg1[1]);
                                break;
                        }
                    }
                    break;
            }
        }
    };

    /*
     * 演示执行第三方登录/注册的方法
     * <p>
     * 这不是一个完整的示例代码，需要根据您项目的业务需求，改写登录/注册回调函数
     *
     * @param platformName 执行登录/注册的平台名称，如：SinaWeibo.NAME
     */
    private void login(String platformName) {
        LoginApi api = new LoginApi();
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            @Override
            public boolean onLogin(UserInfo userInfo) {
                if (userInfo.getType().equals(Wechat.NAME)) {
                    setWeiXinLogin(1, userInfo.getAccess_token(), userInfo.getOpenid());
                } else if (userInfo.getType().equals(QQ.NAME)) {
                    //qq登录
                    setWeiXinLogin(2, userInfo.getAccess_token(), userInfo.getOpenid());
                }
                return true;
            }

            @Override
            public boolean onRegister(UserInfo info) {
                return true;
            }
        });
        api.login(this);
    }

    //微信登录
    private void setWeiXinLogin(int type, String access_token, String openid) {
        AppApis.setWXLogin(type, access_token, openid, this);
    }

    private void showTopUpDialog() {
        mBalanceDialog = new DialogAccountPrompt(this, 14);
        mBalanceDialog.show();
        mBalanceDialog.setDialogClickListener(type1 -> {
            switch (type1) {
                case 14:
                    mBalanceDialog.dismiss();
                    break;
            }
        });
        this.mBalanceDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void setShowDialog(int mType, String time, String cause) {
        AppApplication.getInstance().stopService(this);
        warningNoti = new DialogWarningNotices(this, mType, time, cause);
        warningNoti.show();
        warningNoti.setDialogClickListener(type -> {
            switch (type) {
                case 2:
                    break;
            }
        });
        warningNoti.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
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
