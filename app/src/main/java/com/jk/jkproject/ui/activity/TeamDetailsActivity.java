package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.entity.TeamCenterInfo;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TeamDetailsActivity extends BActivity {


    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.sdv_left_header)
    SimpleDraweeView sdvLeftHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_instructions)
    TextView tvInstructions;
    @BindView(R.id.tv_team_name)
    TextView tvTeamName;
    @BindView(R.id.tv_members)
    TextView tvMembers;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_introduction_name)
    TextView tvIntroductionName;
    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    @BindView(R.id.et_introduction)
    EditText etIntroduction;
    @BindView(R.id.tv_word_count)
    TextView tvWordCount;
    @BindView(R.id.tv_apply_join)
    TextView tvApplyJoin;
    private TeamCenterInfo.DataBean data;
    private Unbinder bind;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_details_activity);
        bind = ButterKnife.bind(this);
    }

    @Override
    public boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();

        initView();
    }


    private void initData() {
        data = (TeamCenterInfo.DataBean) getIntent().getSerializableExtra("data");
    }

    private void initView() {
        if (data != null) {
            if (!TextUtils.isEmpty(data.getTm_name())) {
                tvTeamName.setText(data.getTm_name());
            } else {
                tvTeamName.setText("--");
            }
            if (data.getTm_count() != 0) {
                int num = data.getTm_count();
                String onlinenum = String.valueOf(num);
                tvMembers.setText("成员" + onlinenum);

            } else {
                tvMembers.setText("0");
            }
            if (!TextUtils.isEmpty(data.getCaptain_name())) {
//                String distance = data.getDistance() + "(" + data.getArea() + ")";
                String distance = data.getCaptain_name() + "";
                tvUserName.setText(distance);
            } else {
                tvUserName.setText("--");
            }
            if (!TextUtils.isEmpty(data.getId() + "")) {
//                String distance = data.getDistance() + "(" + data.getArea() + ")";
                String distance = data.getId() + "";
                tvId.setText("ID" + distance);
            } else {
                tvId.setText("--");
            }
            if (!TextUtils.isEmpty(data.getTm_msg() + "")) {
//                String distance = data.getDistance() + "(" + data.getArea() + ")";
                String distance = data.getTm_msg() + "";
                tvIntroduction.setText(distance);
            } else {
                tvIntroduction.setText("--");
            }

            if (!TextUtils.isEmpty(data.getTm_max_number() + "")) {
//                String distance = data.getDistance() + "(" + data.getArea() + ")";
                String distance = data.getTm_max_number() + "";
                tvIntroduction.setText(distance);
            } else {
                tvIntroduction.setText("--");
            }

            sdvLeftHeader.setImageURI(data.getTm_url());
        }


        etIntroduction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    if (s.toString().trim().length() <= 60) {
                        tvWordCount.setText(s.toString().length() + "/60");
                    } else {
                        Toast.makeText(TeamDetailsActivity.this, "输入字数已达上限", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    tvWordCount.setText(s.toString().length() + "/60");
                }
            }
        });
    }

    @OnClick(R.id.tv_apply_join)
    public void onViewClicked() {

        if (!etIntroduction.getText().toString().trim().isEmpty()) {
            AppApis.getTeamApplyJoin(data.getId() + "", etIntroduction.getText().toString().trim(), this);
        } else {
            Toast.makeText(TeamDetailsActivity.this, "备注信息不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        if (url.equals(Urls.GET_TEAM_APPLY_JOIN)) {
            Toast.makeText(TeamDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_TEAM_APPLY_JOIN)) {
            try {
                jsonObject = new JSONObject(obj.toString());
                Toast.makeText(TeamDetailsActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                if (jsonObject.getInt("code") == 200) {
                    SPUtils.setTeamType(2);
                }
                EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_TEAM_TYPE_1, Constants.USER_TYPE.USER_TEAM_TYPE_2));
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
