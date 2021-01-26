package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.widget.DrawerItemView;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.StringUtils;
import com.jk.jkproject.utils.sharesdk.LoginApi;
import com.jk.jkproject.utils.sharesdk.OnLoginListener;
import com.jk.jkproject.utils.sharesdk.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


public class AccountBindingActivity extends BActivity {

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
    @BindView(R.id.div_num_binding)
    DrawerItemView divNumBinding;
    @BindView(R.id.div_wx_binding)
    DrawerItemView divWxBinding;
    @BindView(R.id.div_qq_binding)
    DrawerItemView divQqBinding;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_bind);
        bind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }


    private void initData() {
        divNumBinding.setDesc(StringUtils.strToNum(SPUtils.getNumber()));
        divNumBinding.setBackgroundRes(R.id.tv_right_name, R.drawable.bg_acc_exit_btn).
                setText(R.id.tv_right_name, SPUtils.getNumber().equals("未设置") ? "绑定" : "更换").
                setTextColor(R.id.tv_right_name, getResources().getColor(R.color.white)).
                setVisible(R.id.img_share, false);
    }


    private void initView() {
        tvTitle.setText("账号绑定");
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        divNumBinding.setIconResId(R.mipmap.icon_num_binding);
        divNumBinding.setName("手机号绑定");
        divNumBinding.setDesc(StringUtils.strToNum(SPUtils.getNumber()));
        divNumBinding.setBackgroundRes(R.id.tv_right_name, R.drawable.bg_acc_exit_btn).
                setText(R.id.tv_right_name, SPUtils.getNumber().equals("未设置") ? "绑定" : "更换").
                setTextColor(R.id.tv_right_name, getResources().getColor(R.color.white)).
                setVisible(R.id.img_share, false);


        divWxBinding.setIconResId(R.mipmap.live_icon_share_wx);
        divWxBinding.setName("微信绑定");

        divQqBinding.setIconResId(R.mipmap.live_icon_share_qq);
        divQqBinding.setName("QQ绑定");

        WxBinding();
        QqBinding();

    }


    private void WxBinding() {
        divWxBinding.setBackgroundRes(R.id.tv_right_name, SPUtils.getString("openId", "-1").equals("-1") ? R.drawable.bg_acc_exit_btn : R.drawable.bg_gray_btn).
                setText(R.id.tv_right_name, SPUtils.getString("openId", "-1").equals("-1") ? "绑定" : "解绑").
                setTextColor(R.id.tv_right_name, getResources().getColor(R.color.white)).
                setVisible(R.id.img_share, false);
    }

    private void QqBinding() {
        divQqBinding.setBackgroundRes(R.id.tv_right_name, SPUtils.getString("qqId", "-1").equals("-1") ? R.drawable.bg_acc_exit_btn : R.drawable.bg_gray_btn).
                setText(R.id.tv_right_name, SPUtils.getString("qqId", "-1").equals("-1") ? "绑定" : "解绑").
                setTextColor(R.id.tv_right_name, getResources().getColor(R.color.white)).
                setVisible(R.id.img_share, false);
    }


    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_WINXIN_BINDING)) {
            if (null != obj) {
                JSONObject jb = null;
                try {
                    jb = new JSONObject((String) obj);
                    String msg = jb.getString("msg");
                    int code = jb.getInt("code");
                    if (code == 200) {
                        ToastUtils.showShortToast("微信解绑成功");
                        SPUtils.putString("openId", "-1");
                        WxBinding();
                    } else {
                        ToastUtils.showShortToast(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (url.equals(Urls.SET_WX_BINDING)) {
            if (null != obj) {
                JSONObject jb = null;
                try {
                    jb = new JSONObject((String) obj);
                    String msg = jb.getString("msg");
                    int code = jb.getInt("code");
                    if (code == 200) {
                        ToastUtils.showShortToast("微信绑定成功");
                        SPUtils.putString("openId", "openId");
                        WxBinding();

                    } else {
                        ToastUtils.showShortToast(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (url.equals(Urls.GET_QQ_BINDING)) {
            if (null != obj) {
                JSONObject jb = null;
                try {
                    jb = new JSONObject((String) obj);
                    String msg = jb.getString("msg");
                    int code = jb.getInt("code");
                    if (code == 200) {
                        ToastUtils.showShortToast("QQ解绑成功");
                        SPUtils.putString("qqId", "-1");
                        QqBinding();
                    } else {
                        ToastUtils.showShortToast(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (url.equals(Urls.SET_QQ_BINDING)) {
            if (null != obj) {
                JSONObject jb = null;
                try {
                    jb = new JSONObject(obj.toString());
                    String msg = jb.getString("msg");
                    int code = jb.getInt("code");
                    if (code == 200) {
                        ToastUtils.showShortToast("QQ绑定成功");
                        SPUtils.putString("qqId", "qqId");
                        QqBinding();
                    } else {
                        ToastUtils.showShortToast(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick({R.id.iv_title, R.id.div_num_binding, R.id.div_wx_binding, R.id.div_qq_binding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.div_num_binding:
                Intent intent = null;
                if (SPUtils.getNumber().equals("未设置")) {
                    intent = new Intent(this, BindingNumber.class);
                    intent.putExtra("type", 1);
                } else {
                    intent = new Intent(this, ChangeNumberActivity.class);
                    intent.putExtra("type", 2);
                }
                startActivity(intent);
                break;
            case R.id.div_wx_binding:
                if (SPUtils.getString("openId", "-1").equals("-1")) {
                    accBinding(Wechat.NAME);
                } else {
                    //解除
                    AppApis.getWeiXinUnBind(1, this);
                }
                break;
            case R.id.div_qq_binding:
                if (SPUtils.getString("qqId", "-1").equals("-1")) {
                    accBinding(QQ.NAME);
                } else {
                    //解除
                    AppApis.getWeiXinUnBind(2, this);
                }
                break;
        }
    }

    private void accBinding(String platformName) {
        LoginApi api = new LoginApi();
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(UserInfo userInfo) {
                // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                // 此处全部给回需要注册
                //遍历Map
                if (userInfo.getType().equals(Wechat.NAME)) {
                    setWXBinding(1, userInfo.getAccess_token(), userInfo.getOpenid());
                } else if (userInfo.getType().equals(QQ.NAME)) {
                    //qq登录
                    setWXBinding(2, userInfo.getAccess_token(), userInfo.getOpenid());
                }
                return true;
            }

            @Override
            public boolean onRegister(UserInfo info) {
                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                return true;
            }
        });
        api.login(this);
    }

    //微信登录
    private void setWXBinding(int type, String access_token, String openid) {
        AppApis.setWXBinding(type, access_token, openid, this);
    }
}
