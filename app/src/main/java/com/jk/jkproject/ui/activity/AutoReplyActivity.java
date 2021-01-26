package com.jk.jkproject.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/27 10:04 AM
 * @desc 自动回复
 */
public class AutoReplyActivity extends BActivity {


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
    @BindView(R.id.et_int)
    EditText etInt;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.ll_editor_init)
    RelativeLayout llEditorInit;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_reply);
        bind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        AppApis.getGetAutoReplyInfo(this);
        tvRightTitle.setText("保存");
        tvRightTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("被关注自动回复");
        tvTitle.setTextSize(18);
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvRightTitle.setTextColor(getResources().getColor(R.color.color_D83FDD));

        etInt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    if (s.toString().trim().length() <= 200) {
                        tvCount.setText(s.toString().length() + "/200字");
                        etInt.setFocusable(true);
                    } else {
                        ToastUtils.showShortToast("输入字数已达上限");
                        etInt.setFocusable(false);
                    }
                } else {
                    tvCount.setText(s.toString().length() + "/200字");
                    etInt.setFocusable(true);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_AUTO_REPLY)) {
            if (obj != null) {
                try {
                    JSONObject object = new JSONObject(obj.toString());
                    if (object.getInt("code") == 200) {
                        ToastUtils.showShortToast("保存成功");
                        finish();
                    } else {
                        ToastUtils.showShortToast(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (url.equals(Urls.GET_AUTO_REPLY_INFO)) {
            if (obj != null) {
                try {
                    JSONObject object = new JSONObject(obj.toString());
                    if (object.getInt("code") == 200) {
                        JSONObject object1 = new JSONObject(object.getString("data"));
                        String reply = object1.getString("reply");
                        if (!reply.isEmpty()) {
                            etInt.setText(reply);
                            etInt.setSelection(reply.length());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick({R.id.iv_title, R.id.tv_right_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_right_title:
                if (!etInt.getText().toString().trim().isEmpty()) {
                    AppApis.getGetAutoReply(etInt.getText().toString().trim(), this);
                } else {
                    ToastUtils.showShortToast("回复内容不能为空");
                }
                break;
        }
    }
}
