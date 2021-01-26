package com.jk.jkproject.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.UserPhonePrefixAdapter;
import com.jk.jkproject.ui.entity.PhonePrefixInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/25 1:20 PM
 * @desc 区号
 */
public class LoginGetPhonePrefixActivity extends BActivity {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.rv)
    RecyclerView rv;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_get_phone_prefix);
        bind = ButterKnife.bind(this);

//        Intent intent = new Intent();
//        intent.putExtra("phone_prefix","" );
//        setResult(LoginActivity.request_code,intent);
        initData();
    }

    private void initData() {
        tvTitle.setText("区号");
        AppApis.getGetPhonePrefix(this);

    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_PHONE_PREFIX)) {
            if (obj != null) {
                rv.setLayoutManager(new LinearLayoutManager(this));
                UserPhonePrefixAdapter userPhonePrefixAdapter = new UserPhonePrefixAdapter(this, ((PhonePrefixInfo) obj).getData(), R.layout.user_phone_prefix_item, LoginGetPhonePrefixActivity.this);
                rv.setAdapter(userPhonePrefixAdapter);
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);

    }

    @OnClick(R.id.iv_title)
    public void onViewClicked() {
        finish();
    }

    @Nullable
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
