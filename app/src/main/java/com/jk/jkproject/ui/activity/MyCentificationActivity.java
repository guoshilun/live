package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.entity.QiNiuTokenInfo;
import com.jk.jkproject.utils.QiNiuFileUpdownUtils;
import com.jk.jkproject.utils.RegularConstants;
import com.jk.jkproject.utils.SPUtils;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * @author Zick
 * @params
 * @date 2020/7/10 11:14 AM
 * @desc 实名认证
 */
public class MyCentificationActivity extends BActivity {
    public static final int REQUEST_CODE = 22;
    public static final int REQUEST_HANDHELD_CODE = 23;
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
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_clear_name)
    ImageView ivClearName;
    @BindView(R.id.et_id_card_name)
    EditText etIdCardName;
    @BindView(R.id.iv_clear_card)
    ImageView ivClearCard;
    @BindView(R.id.tv_id_card_pic)
    TextView tvIdCardPic;
    @BindView(R.id.iv_id_card_positive)
    ImageView ivIdCardPositive;
    @BindView(R.id.iv_id_card_reverse)
    ImageView ivIdCardReverse;
    @BindView(R.id.iv_id_card_handheld)
    ImageView ivIdCardHandheld;
    @BindView(R.id.ll_id_pic)
    LinearLayout llIdPic;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.iv_ok)
    ImageView ivOk;
    @BindView(R.id.tv_login_ok)
    TextView tvLoginOk;

    private Unbinder bind;
    private String cardPositivePath = "";
    private String cardReversePath = "";
    private String cardHandheldPath = "";
    private List<String> tokens = new ArrayList<>();
    private List<String> picUrl = new ArrayList<>();
    private int position = -1, pos = -1;
    private String[] paths = {"", "", ""};
    private String positive = "";
    private String reverse = "";
    private String handheld = "";
    private String QiNiuUrl;
    private boolean isOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_centification);
        bind = ButterKnife.bind(this);
        initView();
        tokens.add("-----");
        tokens.add("-----");
        tokens.add("-----");
        picUrl.add("-----");
        picUrl.add("-----");
        picUrl.add("-----");
        for (int i = 0; i < 3; i++) {
            questToken();
        }
        SPUtils.putBoolean("HOST_AGGRESS", false);
    }

    private void initView() {
        tvTitle.setText(R.string.str_certification);
        ivClearName.setVisibility(View.GONE);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    ivClearName.setVisibility(View.VISIBLE);
                } else {
                    ivClearName.setVisibility(View.GONE);
                }
            }
        });

        etIdCardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    ivClearCard.setVisibility(View.VISIBLE);
                } else {
                    ivClearCard.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                cardPositivePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                LogUtils.e("position=1=", cardPositivePath);
                if (!cardPositivePath.isEmpty()) {
                    paths[0] = cardPositivePath;
                    File file = new File(cardPositivePath);
                    Uri uri = Uri.fromFile(file);
                    ivIdCardPositive.setImageURI(uri);
                    position = 0;
//                    for (int i = 0; i < 3; i++) {
//                        LogUtils.e("Token=", tokens.get(i));
//                    }
                    QiNiuFileUpdownUtils.get().upload(0, tokens.get(0), cardPositivePath, this);
//                    questToken();
                }
            }
        } else if (requestCode == REQUEST_CODE) {
            if (data != null) {
                cardReversePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                LogUtils.e("position=2=", cardReversePath);
                if (!cardReversePath.isEmpty()) {
                    paths[1] = cardReversePath;
                    File file = new File(cardReversePath);
                    Uri uri = Uri.fromFile(file);
                    ivIdCardReverse.setImageURI(uri);
                    position = 1;
                    QiNiuFileUpdownUtils.get().upload(1, tokens.get(1), cardReversePath, this);
//                    questToken();
                }
            }
        } else if (requestCode == REQUEST_HANDHELD_CODE) {
            if (data != null) {
                cardHandheldPath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                LogUtils.e("position=3=", cardHandheldPath);
                if (!cardHandheldPath.isEmpty()) {
                    paths[2] = cardHandheldPath;
                    File file = new File(cardHandheldPath);
                    Uri uri = Uri.fromFile(file);
                    ivIdCardHandheld.setImageURI(uri);
                    position = 2;
                    QiNiuFileUpdownUtils.get().upload(2, tokens.get(2), cardHandheldPath, this);
//                    questToken();
                }
            }
        }
    }

    private void questToken() {
        AppApis.getGitPicToken(this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_QINIU_TOKEN)) {
            if (obj != null && obj instanceof QiNiuTokenInfo) {
                QiNiuTokenInfo qnToken = (QiNiuTokenInfo) obj;
                if (qnToken.getCode() == 200) {
                    QiNiuUrl = qnToken.getData().getUrl();
                    Log.e("===", "position=" + position + ",ken" + qnToken.getData().getQnToken());

//                    if (tokens.size() >= position) {
//                        tokens.remove(position);
//                    }
                    pos++;
                    tokens.add(pos, qnToken.getData().getQnToken());
                }
            }
        } else if (url.equals(0 + "")) {
            positive = obj.toString();
            LogUtils.e("=====0", QiNiuUrl + positive);
//            if (tokens.size() >= 1) {
//                tokens.remove(0);
//            }
            picUrl.add(0, QiNiuUrl + positive);
//            if (!reverse.isEmpty() && !positive.isEmpty() && !handheld.isEmpty()) {
//                AppApis.getGitIdentityAuth(etName.getText().toString().trim(), etIdCardName.getText().toString().trim(), QiNiuUrl + positive, QiNiuUrl + reverse, QiNiuUrl + handheld, this);
//                positive = "";
//            }
        } else if (url.equals(1 + "")) {
            reverse = obj.toString();
            LogUtils.e("=====1", QiNiuUrl + reverse);
//            if (!reverse.isEmpty() && !positive.isEmpty() && !handheld.isEmpty()) {
//                AppApis.getGitIdentityAuth(etName.getText().toString().trim(), etIdCardName.getText().toString().trim(), QiNiuUrl + positive, QiNiuUrl + reverse, QiNiuUrl + handheld, this);
//                reverse = "";
//            }
//            if (tokens.size() >= 1) {
//                tokens.remove(0);
//            }
            picUrl.add(1, QiNiuUrl + reverse);
        } else if (url.equals(2 + "")) {
            handheld = obj.toString();
            LogUtils.e("=====2", QiNiuUrl + handheld);
//            if (!reverse.isEmpty() && !positive.isEmpty() && !handheld.isEmpty()) {
//                AppApis.getGitIdentityAuth(etName.getText().toString().trim(), etIdCardName.getText().toString().trim(), QiNiuUrl + positive, QiNiuUrl + reverse, QiNiuUrl + handheld, this);
//                handheld = "";
//            }
            picUrl.add(2, QiNiuUrl + handheld);
        } else if (url.equals(Urls.GET_IDENTITY_AUTH)) {
            try {
                JSONObject jsonObject = new JSONObject(obj.toString());
                if (jsonObject.getInt("code") == 200) {
                    ToastUtils.showShortToast("实名认证提交成功");
                    SPUtils.setStatusType(2);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        ToastUtils.showShortToast(error);
    }

    @OnClick({R.id.iv_title, R.id.iv_clear_name, R.id.iv_clear_card,R.id.tv_login_ok, R.id.tv_ok, R.id.iv_id_card_positive, R.id.iv_id_card_reverse, R.id.iv_id_card_handheld, R.id.tv_commit, R.id.iv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.iv_clear_name:
                etName.setText("");
                etName.setHint(R.string.str_name_hint);
                break;
            case R.id.iv_clear_card:
                etIdCardName.setText("");
                etIdCardName.setHint(R.string.str_id_card_hint);
                break;
            case R.id.iv_id_card_positive:
                PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE).selectPicture(false,0,0,0,0);
                break;
            case R.id.iv_id_card_reverse:
                PictureSelector.create(this, REQUEST_CODE).selectPicture(false,0,0,0,0);
                break;
            case R.id.iv_id_card_handheld:
                PictureSelector.create(this, REQUEST_HANDHELD_CODE).selectPicture(false,0,0,0,0);
                break;
            case R.id.tv_commit:
                updataPic();
                break;
            case R.id.iv_ok:
            case R.id.tv_ok:
                isOk = true;
                ivOk.setImageResource(R.mipmap.icon_agree);
                ivOk.setClickable(false);
                break;
            case R.id.tv_login_ok:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("type", 10);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SPUtils.getBoolean("HOST_AGGRESS", false)) {
            isOk = true;
            ivOk.setImageResource(R.mipmap.icon_agree);
            ivOk.setClickable(false);
        }
    }

    private void updataPic() {
        if (!isOk) {
            ToastUtils.showShortToast("请仔细阅读协议");
            return;
        }
        if (picUrl.get(0).equals("-----") && picUrl.get(1).equals("-----") && picUrl.get(2).equals("-----")) {
            ToastUtils.showShortToast("获取图片地址错误");
            return;
        }
        if (etName.getText().toString().trim().isEmpty() && etIdCardName.getText().toString().trim().isEmpty()) {
            ToastUtils.showShortToast("请完善个人信息");
            return;
        }
        if (!etIdCardName.getText().toString().trim().matches(RegularConstants.isIDCard)) {
            ToastUtils.showShortToast("身份证信息错误");
            return;
        }
//        for (int i = 0; i < tokens.size(); i++) {
        AppApis.getGitIdentityAuth(etName.getText().toString().trim(), etIdCardName.getText().toString().trim(), picUrl.get(0), picUrl.get(1), picUrl.get(2), this);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }


}