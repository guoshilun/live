package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.utils.SPUtils;

public class SplashActivity extends BActivity {

    private Handler handler = new Handler();
    private String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //去掉窗口标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //隐藏顶部状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        if (!SPUtils.getIP().isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler = null;
        }
    }
}
