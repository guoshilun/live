package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.utils.SPUtils;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TeamNewTeamActivity extends BActivity {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.sdv_left_header)
    SimpleDraweeView sdvLeftHeader;
    @BindView(R.id.tv_upload_pic)
    TextView tvUploadPic;
    @BindView(R.id.et_team_name)
    EditText tvTeamName;
    @BindView(R.id.tv_team_tt)
    TextView tvTeamTt;
    @BindView(R.id.tv_team_int)
    TextView tvTeamInt;
    @BindView(R.id.et_int)
    EditText tvInt;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.ll_team_form)
    LinearLayout llTeamForm;
    @BindView(R.id.tv_team_commit)
    TextView tvTeamCommit;
    private Unbinder bind;
    private String picturePath = "";
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_new_team);
        bind = ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        tvTitle.setText(R.string.str_new_team);
        tvInt.addTextChangedListener(new TextWatcher() {
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
                        tvCount.setText(s.toString().length() + "/60");
                    } else {
                        Toast.makeText(TeamNewTeamActivity.this, "输入字数已达上限", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    tvCount.setText(s.toString().length() + "/60");
                }
            }
        });
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_TEAM_NEW_TEAM)) {
            try {
                jsonObject = new JSONObject(obj.toString());
                Toast.makeText(TeamNewTeamActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                if (jsonObject.getInt("code") == 200) {
                    SPUtils.setTeamType(3);
                }
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        if (url.equals(Urls.GET_TEAM_NEW_TEAM)) {
            Log.e("===onFailure==", error);
        }
    }

    @Nullable
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                if (!picturePath.isEmpty()) {
                    File file = new File(picturePath);
                    Uri uri = Uri.fromFile(file);
                    sdvLeftHeader.setImageURI(uri);
                }
                Log.e("===picturePath=", picturePath);
            }
        }
    }

    @OnClick({R.id.iv_title, R.id.tv_title, R.id.public_top_layout, R.id.sdv_left_header, R.id.tv_upload_pic, R.id.et_team_name, R.id.tv_team_tt, R.id.tv_team_int, R.id.et_int, R.id.tv_count, R.id.ll_team_form, R.id.tv_team_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.public_top_layout:
                break;
            case R.id.sdv_left_header:
            case R.id.tv_upload_pic:
                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                PictureSelector
                        .create(this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.et_team_name:
                break;
            case R.id.tv_team_tt:
                break;
            case R.id.tv_team_int:
                break;
            case R.id.et_int:
                break;
            case R.id.tv_count:
                break;
            case R.id.ll_team_form:
                break;
            case R.id.tv_team_commit:
                commit();
                break;
        }
    }

    private void commit() {
        String teamName = tvTeamName.getText().toString();
        String etInt = tvInt.getText().toString();
        if (picturePath.isEmpty()) {
            Toast.makeText(this, "战队图像不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (teamName.isEmpty()) {
            Toast.makeText(this, "战队名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etInt.isEmpty()) {
            Toast.makeText(this, "战队简介不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        AppApis.getTeamNewTeam(teamName,picturePath,etInt,this);
    }
}
