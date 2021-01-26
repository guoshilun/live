package com.jk.jkproject.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.ui.inter.WebViewCallBack;
import com.jk.jkproject.ui.widget.MyWebViewClient;
import com.jk.jkproject.utils.AppConfig;
import com.jk.jkproject.utils.FuncUtils;
import com.jk.jkproject.utils.SPUtils;

public class WebActivity extends BActivity implements View.OnClickListener{

  private final static int FLAG_FILE_CHOOSER = 2;
  private final static int FLAG_FILE_CHOOSER_KITKAT = 3;

  private ValueCallback<Uri> mUploadMessage;
  private ValueCallback<Uri[]> mUploadCallbackAboveL;

  private WebView wv;
  private TextView tvTitle;
  private FrameLayout wv_layout;
  private RelativeLayout mPublicTopLayout;
  private MyWebViewClient myWebViewClient;

  private String mUrl;

  private View networkNo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_agreement);
    myWebViewClient = new MyWebViewClient(WebActivity.this, new WebViewCallBack() {
      @Override
      public void onTransverse() {
        if (mPublicTopLayout != null) {
          mPublicTopLayout.setVisibility(View.GONE);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
//        LiveEndUtil.isOrientationLandscape = true;
        // 保存屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
      }

      @Override
      public void onShowFailView() {
        showFailView();
      }

      @Override
      public void onSetTitle(String title) {
        tvTitle.setText(title);
      }
    });
    initView();
    initData();
  }

  private void initView() {
    getView(R.id.back).setOnClickListener(this);
    getView(R.id.right).setVisibility(View.INVISIBLE);
    tvTitle = getView(R.id.title);
    wv = getView(R.id.wv_user_agreement);
    wv_layout = getView(R.id.wv_layout);
    mPublicTopLayout = getView(R.id.public_top_layout);
    setting();
  }

  @SuppressLint("SetJavaScriptEnabled")
  private void setting() {
    wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    WebSettings settings = wv.getSettings();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    }
    settings.setPluginState(WebSettings.PluginState.ON);
    settings.setJavaScriptEnabled(true); // 支持js
    settings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过JS打开新窗口
    settings.setLoadsImagesAutomatically(true); // 支持自动加载图片
    settings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
    settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    settings.setSupportZoom(true); // 不支持缩放
    settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
    settings.setAllowFileAccess(true);// 设置可以访问文件
    settings.setAllowContentAccess(true);
    wv.setWebChromeClient(new MyWebChromeClient((ProgressBar) findViewById(R.id.progress_loading)));
    wv.setWebViewClient(myWebViewClient);
    wv.requestFocusFromTouch();
  }

  private void initData() {
    Intent in = getIntent();
    mUrl = in.getStringExtra("url");

    if (!TextUtils.isEmpty(mUrl) && mUrl.contains("{uid}")) {
      mUrl = mUrl.replace("{uid}", SPUtils.Impl.getUid());
    }
    if (!TextUtils.isEmpty(mUrl) && mUrl.contains("{webtoken}")) {
      mUrl = mUrl.replace("{webtoken}", getWebSign());
    }

    String title = in.getStringExtra("title");
    wv.clearCache(true);
    if (FuncUtils.isEmpty(mUrl)) {
      tvTitle.setText(R.string.app_name);
      mUrl = AppConfig.USER_AGREE;
      wv.loadUrl(mUrl);
    } else {
      tvTitle.setText(title);
      wv.loadUrl(mUrl);
    }
  }

  private String getWebSign() {

    String sigin = "";
    String webtoken = SPUtils.Impl.getToken();
    String mString = webtoken + "hylhweb";
//    sigin = MD5Fun.getMD5Str(mString).toLowerCase();
    return sigin;
  }

  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.back:
        finish();
//        if (LiveEndUtil.isOpen) {
//          LiveEndUtil.goToAct(WebActivity.this);
//        }
        break;
      case R.id.btn_network_no_reload:
        wv_layout.setVisibility(View.VISIBLE);
        networkNo.setVisibility(View.GONE);
        wv.loadUrl(mUrl);
        break;
    }
  }

  private class MyWebChromeClient extends WebChromeClient {

    private ProgressBar loadingBar;

    public MyWebChromeClient(ProgressBar loadingBar) {
      this.loadingBar = loadingBar;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
      if (newProgress == 100) {
        loadingBar.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
      }
      super.onProgressChanged(view, newProgress);
    }

    //The undocumented magic method override
    //Eclipse will swear at you if you try to put @Override here
    // For Android 3.0-
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
      mUploadMessage = uploadMsg;

      Intent i = new Intent(Intent.ACTION_GET_CONTENT);
      i.addCategory(Intent.CATEGORY_OPENABLE);
      i.setType("image/*");
      WebActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), WebActivity.FLAG_FILE_CHOOSER);
    }

    // For Android 3.0+
    public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
      mUploadMessage = uploadMsg;

      Intent i = new Intent(Intent.ACTION_GET_CONTENT);
      i.addCategory(Intent.CATEGORY_OPENABLE);
      i.setType("*/*");
      WebActivity.this.startActivityForResult(Intent.createChooser(i, "File Browser"), WebActivity.FLAG_FILE_CHOOSER);
    }

    //For Android 4.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
      mUploadMessage = uploadMsg;

      Intent i = new Intent(Intent.ACTION_GET_CONTENT);
      i.addCategory(Intent.CATEGORY_OPENABLE);
      i.setType("image/*");
      WebActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), WebActivity.FLAG_FILE_CHOOSER);
    }

    // The new code
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, WebChromeClient.FileChooserParams fileChooserParams) {
      // Here is part of the issue, the uploadMsg is null since it is not triggered from Android
      mUploadCallbackAboveL = uploadMsg;

      Intent i = new Intent(Intent.ACTION_GET_CONTENT);
      i.addCategory(Intent.CATEGORY_OPENABLE);
      i.setType("image/*");
      WebActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), WebActivity.FLAG_FILE_CHOOSER_KITKAT);

      return true;
    }
  }

  @Override
  protected void onDestroy() {
//    LiveEndUtil.isOrientationLandscape = false;
    if (myWebViewClient != null) {
      myWebViewClient.release();
    }
    super.onDestroy();
    wv.clearCache(true);
    wv_layout.removeView(wv);
    wv.removeAllViews();
    wv.destroy();
    wv = null;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    switch (requestCode) {
      case FLAG_FILE_CHOOSER:
        if (null == mUploadMessage) {
          return;
        }

        Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
        mUploadMessage.onReceiveValue(result);
        mUploadMessage = null;
        break;
      case FLAG_FILE_CHOOSER_KITKAT:
        onActivityResultAboveL(requestCode, resultCode, data);
        break;
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
    if (null == mUploadCallbackAboveL) {
      return;
    }

    Uri[] results = null;
    if (resultCode == RESULT_OK) {
      if (data == null) {
      } else {
        String dataString = data.getDataString();
        ClipData clipData = data.getClipData();
        if (clipData != null) {
          int count = clipData.getItemCount();
          results = new Uri[count];
          for (int i = 0; i < count; i++) {
            ClipData.Item item = clipData.getItemAt(i);
            results[i] = item.getUri();
          }
        }

        if (dataString != null) {
          results = new Uri[]{Uri.parse(dataString)};
        }
      }
    }
    mUploadCallbackAboveL.onReceiveValue(results);
    mUploadCallbackAboveL = null;
  }

  /**
   * 没有网络,加载失败布局
   */
  private void showFailView() {
    wv_layout.setVisibility(View.GONE);
    if (networkNo == null) {
      ViewStub stub = getView(R.id.v_network_no);
      if (stub != null) {
        stub.inflate();
        networkNo = getView(R.id.layout_network_no_root);
        getView(R.id.btn_network_no_reload).setOnClickListener(this);
      }
    }

    networkNo.setVisibility(View.VISIBLE);
  }

}