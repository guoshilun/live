package com.jk.jkproject.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.dialog.DialogProgress1;
import com.jk.jkproject.ui.entity.QiNiuTokenInfo;
import com.jk.jkproject.ui.widget.pictureselector.FullyGridLayoutManager;
import com.jk.jkproject.ui.widget.pictureselector.GridImageAdapter;
import com.jk.jkproject.utils.QiNiuFileUpdownUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.ScreenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/6/9 1:35 PM
 * @desc 意见反馈
 */
public class MyFeedBack extends BActivity {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.et_int)
    EditText etInt;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.ll_editor_init)
    RelativeLayout llEditorInit;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private Unbinder bind;
    private GridImageAdapter mAdapter;
    private String QiNiuUrl, picUrl = "";
    private int pos = -1;
    private List<String> tokens = new ArrayList<>();
    private String etString;
    private DialogProgress1 loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feed_back);
        bind = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        tvTitle.setText("意见反馈");
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
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

        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4,
                ScreenUtils.dip2px(this, 8), false));
        mAdapter = new GridImageAdapter(this, onAddPicClickListener);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mAdapter.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mAdapter.setSelectMax(4);
        mRecyclerView.setAdapter(mAdapter);
        for (int i = 0; i < 4; i++) {
            AppApis.getGitPicToken(this);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                // 图片选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                mAdapter.setList(selectList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick({R.id.iv_title, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_commit:
                etString = etInt.getText().toString().trim();
                if (etString.isEmpty()) {
                    ToastUtils.showShortToast("反馈内容不能为空");
                    return;
                }
                if (mAdapter.getData().size() > 0) {
                    showLoadingDialog("");
                    for (int i = 0; i < mAdapter.getData().size(); i++) {
                        QiNiuFileUpdownUtils.get().upload(i, tokens.get(i), mAdapter.getData().get(i).getPath(), this);
                    }
                } else {
                    AppApis.getGitFeedBack(etString, "", this);
                }
                break;
        }
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_FEED_BACK)) {
            try {
                JSONObject json = new JSONObject(obj.toString());
                if (json.getInt("code") == 200) {
                    ToastUtils.showShortToast("提交成功");
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (url.equals(Urls.GET_QINIU_TOKEN)) {
            if (obj != null && obj instanceof QiNiuTokenInfo) {
                QiNiuTokenInfo qnToken = (QiNiuTokenInfo) obj;
                if (qnToken.getCode() == 200) {
                    QiNiuUrl = qnToken.getData().getUrl();
                    pos++;
                    Log.e("===", "position=" + pos + ",ken" + qnToken.getData().getQnToken());
                    tokens.add(pos, qnToken.getData().getQnToken());
                }
            }
        } else if (url.equals(0 + "")) {
            if (picUrl.equals("")) {
                picUrl = QiNiuUrl + obj.toString();
            } else {
                picUrl = picUrl + "," + QiNiuUrl + obj.toString();
            }
            LogUtils.e("=====picUrl0=", picUrl);
            if (mAdapter.getData().size() == 1) {
                AppApis.getGitFeedBack(etString, picUrl, this);
            }
        } else if (url.equals(1 + "")) {
            if (picUrl.equals("")) {
                picUrl = QiNiuUrl + obj.toString();
            } else {
                picUrl = picUrl + "," + QiNiuUrl + obj.toString();
            }
            if (mAdapter.getData().size() == 2) {
                AppApis.getGitFeedBack(etString, picUrl, this);
            }
            LogUtils.e("=====picUrl1=", picUrl);
        } else if (url.equals(2 + "")) {
            if (picUrl.equals("")) {
                picUrl = QiNiuUrl + obj.toString();
            } else {
                picUrl = picUrl + "," + QiNiuUrl + obj.toString();
            }
            if (mAdapter.getData().size() == 3) {
                AppApis.getGitFeedBack(etString, picUrl, this);
            }
            LogUtils.e("=====picUrl2=", picUrl);
        } else if (url.equals(3 + "")) {
            if (picUrl.equals("")) {
                picUrl = QiNiuUrl + obj.toString();
            } else {
                picUrl = picUrl + "," + QiNiuUrl + obj.toString();
            }
            if (mAdapter.getData().size() == 4) {
                AppApis.getGitFeedBack(etString, picUrl, this);
            }
            LogUtils.e("=====picUrl3=", picUrl);
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.hide();
        }
        tvCommit.setEnabled(true);
        LogUtils.e(error);
    }

    @Override
    protected void onDestroy() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.hide();
        }
        super.onDestroy();
        bind.unbind();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(MyFeedBack.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .maxSelectNum(4)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .synOrAsy(false)//同步true或异步false 压缩 默认同步
                    .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .selectionMedia(mAdapter.getData())// 是否传入已选图片
                    .minimumCompressSize(100)// 小于多少kb的图片不压缩
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };

    protected void showLoadingDialog(String res) {
        if (this == null || this.isFinishing()) {
            return;
        }
        try {
            if (loadingDialog == null) {
                loadingDialog = new DialogProgress1(this);
            }
            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        } catch (Exception e) {
        }
    }
}