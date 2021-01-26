package com.jk.jkproject.ui.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.dialog.DialogUpdateVersion;
import com.jk.jkproject.ui.entity.UpdateInfo;
import com.jk.jkproject.ui.widget.DrawerItemView;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/24 4:15 PM
 * @desc 关于JK直播
 */
public class IntroduceActivity extends BActivity {


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
    @BindView(R.id.sdv_pic)
    SimpleDraweeView sdvPic;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.div_check)
    DrawerItemView divCheck;
    @BindView(R.id.div_action)
    DrawerItemView divAction;
    @BindView(R.id.div_agreement)
    DrawerItemView divAgreement;
    @BindView(R.id.div_privacy)
    DrawerItemView divPrivacy;
    private Unbinder bind;
    private UpdateInfo.DataBean data;
    private DialogUpdateVersion updateVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        bind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        AppApis.checkUpdate(this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.VERSION_CHECK)) {
            if (obj != null && obj instanceof UpdateInfo) {
                UpdateInfo info = (UpdateInfo) obj;
                if (info.getCode() == 200) {
                    data = info.getData();
                } else {
                    ToastUtils.showShortToast("获取版本失败");
                }
            }
        }
    }

    //显示更新弹窗
    private void showUpdateDialog() {
        if (updateVersion == null) {
            updateVersion = new DialogUpdateVersion(this, data);
        }
        updateVersion.show();
        updateVersion.setDialogClickListener(type -> {
            switch (type) {
                case 1:
                    uploadFile(data.getUpdate_url());
                    break;
            }
        });
        updateVersion.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void initView() {
        divCheck.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_333))
                .setText(R.id.tv_name, "检查更新").setGone(R.id.iv_icon, false).
                setGone(R.id.tv_desc, false).setGone(R.id.tv_des, false).setVisible(R.id.line, true)
                .setTextSize(R.id.tv_name, 16);

        divAction.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_333)).setTextSize(R.id.tv_name, 16)
                .setText(R.id.tv_name, "行为规范").setGone(R.id.iv_icon, false).
                setGone(R.id.tv_desc, false).setGone(R.id.tv_des, false).setVisible(R.id.line, true);

        divAgreement.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_333)).setTextSize(R.id.tv_name, 16)
                .setText(R.id.tv_name, "服务协议").setGone(R.id.iv_icon, false).
                setGone(R.id.tv_desc, false).setGone(R.id.tv_des, false).setVisible(R.id.line, true);
        divPrivacy.setTextColor(R.id.tv_name, getResources().getColor(R.color.color_333)).setTextSize(R.id.tv_name, 16)
                .setText(R.id.tv_name, "隐私政策").setGone(R.id.iv_icon, false).
                setGone(R.id.tv_desc, false).setGone(R.id.tv_des, false).setVisible(R.id.line, true);

        tvTitle.setText("关于锦阔电竞");
        tvVersion.setText("版本号" + AppUtils.getAppVersionName(getPackageName()));
        sdvPic.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    protected void onDestroy() {
        if (updateVersion != null) {
            updateVersion = null;
        }
        super.onDestroy();
        bind.unbind();
    }

    @OnClick({R.id.iv_title, R.id.div_check, R.id.div_action, R.id.div_agreement,R.id.div_privacy})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.div_check:
                if (data != null) {
                    if (!data.getVersions().equals(AppUtils.getAppVersionName(getPackageName()))) {
                        showUpdateDialog();
                    } else {
                        ToastUtils.showShortToast("当前版本已是最新版本");
                    }
                }
                break;
            case R.id.div_action:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("type", 8);
                startActivity(intent);
                break;
            case R.id.div_agreement:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("type", 9);
                startActivity(intent);
                break;
            case R.id.div_privacy:
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("type", 13);
                startActivity(intent);
                break;
        }
    }


    private void uploadFile(String url) {
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir("", url.substring(url.lastIndexOf("/") + 1));
        //获取下载管理器
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        long appId = downloadManager.enqueue(request);
        SPUtils.putLong("appId", appId);
    }

}
