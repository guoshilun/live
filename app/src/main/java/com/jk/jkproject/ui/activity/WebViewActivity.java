package com.jk.jkproject.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.inter.WebViewCallBack;
import com.jk.jkproject.utils.FastClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.StringUtils;
import com.yuyan.statusbar.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WebViewActivity extends BActivity implements WebViewCallBack {

    /**
     * 接收安卓5.0以上的
     */
    public ValueCallback<Uri[]> uploadMessage;
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
    @BindView(R.id.web)
    WebView web;
    private Unbinder bind;
    private int mType;// 1 充值 2 收益 3 主播榜 4 用户榜 5 主播等级 6 用户等级 7 粉丝贡献榜 8 行为规范 9 服务协议 10.JK直播协议  11.网页 12.用户充值协议 13.JK直播隐私政策
    private String url;
    private AndroidtoJs androidtoJs;
    private int REQUEST_SELECT_FILE = 2000;
    private String targetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        bind = ButterKnife.bind(this);
        initView();
        initWeb();
    }

    /**
     * 主播榜：http://192.168.0.99:8080/#/pages/anchor/anchor
     * 共享榜：http://192.168.0.99:8080/#/pages/contribution/contribution
     * 用户榜：http://192.168.0.99:8080/#/pages/user/user
     * 主播等级：http://192.168.0.99:8080/#/pages/anchorg/anchorg
     * 用户等级：http://192.168.0.99:8080/#/pages/userg/userg
     * 关注列表：http://192.168.0.99:8080/#/pages/attention/attention
     * 我的收益：http://192.168.0.99:8080/#/pages/benefit/benefit
     * 收益明细：http://192.168.0.99:8080/#/pages/benefitC/benefitC
     * 绑定银行卡：http://192.168.0.99:8080/#/pages/bindingC/bindingC
     * 兑换：http://192.168.0.99:8080/#/pages/exchange/exchange
     * 兑换明细：http://192.168.0.99:8080/#/pages/exchangeD/exchangeD
     * 粉丝列表：http://192.168.0.99:8080/#/pages/fans/fans
     * 提现记录：http://192.168.0.99:8080/#/pages/withdrawalR/withdrawalR
     * 提现认证：http://192.168.0.99:8080/#/pages/withdrawalC/withdrawalC
     * 充值：http://192.168.0.99:8080/#/pages/top/top
     * 充值详情：http://192.168.0.99:8080/#/pages/topR/topR
     * 直播管理条例：http://192.168.0.99:8081/#/pages/adminp/adminp
     * 直播用户服务协议：http://192.168.0.99:8081/#/pages/userp/userp
     * 提现规则：http://192.168.0.99:8081/#/pages/wthp/wthp
     * 用户充值协议：http://192.168.0.99:8081/#/pages/topp/topp
     * 直播公约：http://192.168.0.99:8081/#/pages/LiveTerms/LiveTerms
     */
    private void initView() {
        if (getIntent() != null) {
            mType = getIntent().getIntExtra("type", -1);
        }
        switch (mType) {
            case 1://充值
                url = getH5Page(Urls.RECHARGE_PAGE);
                tvTitle.setText("钻石充值");
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                publicTopLayout.setVisibility(View.GONE);
                break;
            case 2://收益
                url = getH5Page(Urls.MY_EARNINGS);
                tvTitle.setText("我的收益");
                publicTopLayout.setVisibility(View.GONE);
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                break;
            case 3://主播榜
                url = getH5Page(Urls.HOST_PAGE);
                tvTitle.setText("主播榜");
                publicTopLayout.setBackgroundColor(getResources().getColor(R.color.color_FC5E8E));
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.color_FC5E8E));
                tvTitle.setTextColor(getResources().getColor(R.color.white));
                ivTitle.setImageResource(R.mipmap.icon_white_return);
                break;
            case 4://用户榜
                url = getH5Page(Urls.USER_PAGE);
                tvTitle.setText("用户榜");
                publicTopLayout.setBackgroundColor(getResources().getColor(R.color.color_FBDD67));
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.color_FBDD67));
                tvTitle.setTextColor(getResources().getColor(R.color.white));
                ivTitle.setImageResource(R.mipmap.icon_white_return);
                break;
            case 5://主播等级
                url = getH5Page(Urls.HOST_LEVEL);
                tvTitle.setText("主播等级");
                publicTopLayout.setBackgroundColor(getResources().getColor(R.color.color_4EB2FF));
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.color_4EB2FF));
                tvTitle.setTextColor(getResources().getColor(R.color.white));
                ivTitle.setImageResource(R.mipmap.icon_white_return);
                break;
            case 6://用户等级
                url = getH5Page(Urls.USER_LEVEL);
                tvTitle.setText("用户等级");
                publicTopLayout.setBackgroundColor(getResources().getColor(R.color.color_FBDC65));
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.color_FBDC65));
                tvTitle.setTextColor(getResources().getColor(R.color.white));
                ivTitle.setImageResource(R.mipmap.icon_white_return);
                break;
            case 7://粉丝贡献榜
                targetId = getIntent().getStringExtra("targetId");
                url = getH5Page(Urls.FAN_CONTRIBUTION);
                tvTitle.setText("粉丝贡献榜");
                publicTopLayout.setBackgroundColor(getResources().getColor(R.color.color_FBDD67));
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.color_FBDD67));
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                break;
            case 8:
                url = getH5Page(Urls.MANAGEMENT_REGULATIONS);
                tvTitle.setText("锦阔电竞管理条例");
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                break;
            case 9:
                url = getH5Page(Urls.USER_SERVICE_AGREEMENT);
                tvTitle.setText("锦阔电竞用户服务协议");
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                break;
            case 10:
                url = getH5Page(Urls.HOST_AGREEMENT);
                tvTitle.setText("锦阔电竞协议");
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                break;
            case 11:
                url = getIntent().getStringExtra("url");
                tvTitle.setText("锦阔电竞");
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                break;
            case 12:
                url = getH5Page(Urls.USER_DIAMOND);
                tvTitle.setText("用户充值协议");
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                break;
            case 13:
                url = getH5Page(Urls.USER_PRIVACY);
                tvTitle.setText("锦阔电竞隐私政策");
                tvTitle.setTextColor(getResources().getColor(R.color.color_333));
                break;
        }
//        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvTitle.setTextSize(18);
        androidtoJs = new AndroidtoJs();
    }

    @SuppressLint("JavascriptInterface")
    private void initWeb() {
//        tvTitle.setText("钻石充值");
//        tvTitle.setTextColor(getResources().getColor(R.color.color_333));
////        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//        tvTitle.setTextSize(18);
//        tvTitle.setInputType();
        //获取webSettings
        WebSettings settings = web.getSettings();
        settings.setAllowContentAccess(true);
        settings.setSupportZoom(true);
        //让webView支持JS
        settings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //加载百度网页
//        web.loadUrl("http://192.168.0.188:8089/#/pages/top/top");
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启 DOM storage API 功能
        settings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        LogUtils.e("url=", url);
        web.loadUrl(url);
        // 只需要将第一种方法的loadUrl()换成下面该方法即可
//        web.addJavascriptInterface(androidtoJs, "navBack");//AndroidtoJS类对象映射到js的test对象
        web.addJavascriptInterface(androidtoJs, "getSystemInfoFromApp");//AndroidtoJS类对象映射到js的test对象
        androidtoJs.setOnClickListener(new AndroidtoJs.OnClickListener() {
            @Override
            public void onBack() {
                finish();
            }

            @Override
            public void onClick(String roomId) {
//                goToLive();
                if (FastClickUtils.isFastClick()) {
                    Intent intent = new Intent(WebViewActivity.this, SlideActivity.class);
                    intent.putExtra("roomId", roomId);
                    startActivity(intent);
                }
            }

            @Override
            public void onUserClick(String userId) {
                Intent intent = new Intent(WebViewActivity.this, MyHomePageActivity.class);
                intent.putExtra("u_id", userId);
                startActivity(intent);
            }
        });
//        web.evaluateJavascript("javascript:getSystemInfoFromApp()", value -> {
//            //此处为 js 返回的结果
//            ToastUtils.showShortToast("web弹窗=======" + value);
//        });

        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置支持调用相册
        web.setWebChromeClient(new WebChromeClient() {
            //Android 5.0会调用这个
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]>
                    filePathCallback, FileChooserParams fileChooserParams) {
                //确保没有现有的消息
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                uploadMessage = filePathCallback;
                Intent intent = fileChooserParams.createIntent();
                startActivityForResult(intent, REQUEST_SELECT_FILE);
                return true;
            }
        });
    }

    private void goToLive() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_SELECT_FILE) {
            if (uploadMessage == null)
                return;
            uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
            uploadMessage = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (uploadMessage != null) {
            //取消之后要告诉WebView不要再等待返回结果，设置为空就等于重置了状态,也是避免只能选择一次图片的原因
            uploadMessage.onReceiveValue(null);
            uploadMessage = null;
        }
    }

    @OnClick({R.id.iv_title, R.id.tv_right_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                if (mType == 10) {
                    SPUtils.putBoolean("HOST_AGGRESS", true);
                }
                finish();
                break;
            case R.id.tv_right_title:

                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public String getH5Page(String url) {
        if (mType == 7) {
            return url + "?token=" + StringUtils.escapeQueryChars(SPUtils.getToken()) + "&&targetId=" + targetId;
        } else {
            return url + "?token=" + StringUtils.escapeQueryChars(SPUtils.getToken());
        }
    }

    @Override
    public void onSetTitle(String paramString) {

    }

    @Override
    public void onShowFailView() {

    }

    @Override
    public void onTransverse() {

    }


}