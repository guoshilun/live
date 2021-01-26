package com.jk.jkproject.ui.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/9/12 4:39 PM
 * @desc 金币游戏规则
 */
public class DialogLiveRoomGameGoldRules extends BaseDialog {

    @BindView(R.id.iv_title)
    TextView ivTitle;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_pk_record)
    TextView tvPkRecord;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.iv)
    ImageView imageView;
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;


    public DialogLiveRoomGameGoldRules(Context paramContext, int mType) {
        super(paramContext);
        this.mContext = paramContext;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_gold_game_rules);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.CENTER;
    }

    @Override
    public void show() {
        super.show();

    }


    protected void initView() {
        tvPkRecord.setText("游戏说明");
        imageView.setImageResource(R.mipmap.icon_gold_game_rules);
//        initWeb();
    }


    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.dp2px(getContext(), 333), ScreenUtils.dp2px(getContext(), 443));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick(R.id.iv_title)
    public void onViewClicked() {
        dismiss();
    }

    public static interface DialogReturnListener {
        void onClick(PKTypeBaseInfo info);
    }

    @SuppressLint("JavascriptInterface")
    private void initWeb() {
        WebSettings settings = web.getSettings();
        settings.setAllowContentAccess(true);
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
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        web.loadUrl(getH5Page(Urls.GAME_GOLD_RULES));
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public String getH5Page(String url) {
        return url + "?token=" + StringUtils.escapeQueryChars(SPUtils.getToken());
    }
}