package com.jk.jkproject.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fansan.common.ImageLoader;
import com.jk.jkproject.R;
import com.jk.jkproject.UserGameAdapter;
import com.jk.jkproject.UserGameListBean;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.chat.LiveMessageActivity;
import com.jk.jkproject.ui.chat.Message;
import com.jk.jkproject.ui.dialog.DialogLiveRoomUserReport1;
import com.jk.jkproject.ui.entity.FanContributionList;
import com.jk.jkproject.ui.entity.LiveHomeFansCountBaseInfo;
import com.jk.jkproject.ui.entity.LiveRoomUserInfo;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;
import com.jk.lib_egg.ViewExtKt;
import com.lxj.xpopup.XPopup;
import com.newer.library.adapter.base.BaseQuickAdapter;
import com.newer.library.adapter.base.listener.OnItemChildClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/**
 * @author Zick
 * @params
 * @date 2020/6/9 10:02 AM
 * @desc 用户主页
 */
@Route(path = "/jk/homepage")
public class MyHomePageActivity extends BActivity {

    @BindView(R.id.sdv_big_header)
    SimpleDraweeView sdvBigHeader;
    @BindView(R.id.iv_title)
    ImageView        ivTitle;
    @BindView(R.id.tv_left_name)
    TextView         tvLeftName;
    @BindView(R.id.tv_title)
    TextView         tvTitle;
    @BindView(R.id.iv_right_title)
    ImageView        ivRightTitle;
    @BindView(R.id.tv_right_title)
    TextView         tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout   publicTopLayout;
    @BindView(R.id.sdv_small_header)
    SimpleDraweeView sdvSmallHeader;
    @BindView(R.id.tv_host)
    TextView         tvHost;
    @BindView(R.id.tv_user_name)
    TextView         tvUserName;
    @BindView(R.id.iv_user_sex)
    ImageView        ivUserSex;
    @BindView(R.id.tv_user_level)
    TextView         tvUserLevel;
    @BindView(R.id.tv_user_name_host)
    TextView         tvUserNameHost;
    @BindView(R.id.ll_1)
    LinearLayout     ll1;
    @BindView(R.id.tv_user_sign)
    TextView         tvUserSign;
    @BindView(R.id.tv_fans)
    TextView         tvFans;
    @BindView(R.id.tv_follow)
    TextView         tvFollow;
    @BindView(R.id.ll_2)
    LinearLayout     ll2;
    @BindView(R.id.tv_fans_list)
    TextView         tvFansList;
    @BindView(R.id.iv)
    ImageView        iv;
    @BindView(R.id.tv_fans_list_1)
    TextView         tvFansList1;
    @BindView(R.id.iv_return)
    ImageView        ivReturn;
    @BindView(R.id.iv_header_1)
    SimpleDraweeView ivHeader1;
    @BindView(R.id.iv_header_2)
    SimpleDraweeView ivHeader2;
    @BindView(R.id.iv_header_3)
    SimpleDraweeView ivHeader3;
    @BindView(R.id.rl_3)
    RelativeLayout   rl3;
    @BindView(R.id.tv_1)
    TextView         tv1;
    @BindView(R.id.tv_id)
    TextView         tvId;
    @BindView(R.id.tv_2)
    TextView         tv2;
    @BindView(R.id.iv_1)
    ImageView        iv1;
    @BindView(R.id.tv_3)
    TextView         tv3;
    @BindView(R.id.tv_level_user)
    TextView         tvLevelUser;
    @BindView(R.id.ll_3)
    RelativeLayout   ll3;
    @BindView(R.id.rl_4)
    RelativeLayout   ll4;
    @BindView(R.id.iv_2)
    ImageView        iv2;
    @BindView(R.id.iv_return_1)
    ImageView        ivReturn1;
    @BindView(R.id.iv_return_2)
    ImageView        ivReturn2;
    @BindView(R.id.tv_4)
    TextView         tv4;
    @BindView(R.id.tv_level_host)
    TextView         tvLevelHost;
    @BindView(R.id.tv_follow_btn)
    TextView         tvFollowBtn;
    @BindView(R.id.tv_sixin)
    TextView         tvSixin;
    @BindView(R.id.tv_copy)
    TextView         tvCopy;
    @BindView(R.id.ll_5)
    LinearLayout     ll5;
    @BindView(R.id.recyclerview)
    RecyclerView     recyclerView;
    private Unbinder                  bind;
    private LiveRoomUserInfo.DataBean userInfo;
    public  String                    uid;
    private DialogLiveRoomUserReport1 reportDialog;
    private int                       isBlock     = -1;
    private MediaPlayer               mediaPlayer = null;
    private UserGameAdapter           adapter;
    private String                    recorderUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_live_room_user_home_page);
        bind = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        if (getIntent() != null) {
            uid = getIntent().getStringExtra("u_id");
        }
        //        AppApis.getHomeFansCountAndFollowData(uid, this);
        publicTopLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        ivTitle.setImageResource(R.mipmap.icon_white_return);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        if (SPUtils.getUserId().equals(uid)) {
            AppApis.getGitUserInfo(uid, this);
            if (getIntent().hasExtra("fans")) {
                String fansCount = "粉丝 " + getIntent().getIntExtra("fans", 0);
                String followCount = "关注 " + getIntent().getIntExtra("follow", 0);
                tvFans.setText(fansCount);
                tvFollow.setText(followCount);
            }
        } else {
            AppApis.getGitTagIdInfo(uid, this);
            AppApis.getHomeFansCountAndFollowData(uid, this);
            AppApis.getAttandBlock(uid, this);
        }
        AppApis.getFanContributionList(uid, 4, 0, "3", this);

        adapter = new UserGameAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener((ad, view, position) -> {
            if (view.getId() == R.id.takeOrder) {
                Bundle bundle = new Bundle();
                bundle.putString("userId",adapter.getData().get(position).userId);
                bundle.putString("name",adapter.getData().get(position).nickname);
                bundle.putInt("gameId",adapter.getData().get(position).play_id);
                ARouter.getInstance().build("/order/orderconfirm").with(bundle).navigation();
            } else if (view.getId() == R.id.img){
                new XPopup.Builder(this).asImageViewer((ImageView) view,adapter.getData().get(position).img,new ImageLoader())
                        .show();
            }else{
                recorderUrl = adapter.getData().get(position).video;
                releaseMediaPlayer();
                initMediaPlayer();
                TextView textView = view.findViewById(R.id.record);
                new Thread(() -> {
                    while (mediaPlayer!= null && mediaPlayer.isPlaying()){
                        runOnUiThread(() -> {
                            textView.setText((mediaPlayer.getCurrentPosition() / 1000) + "S");
                        });
                    }
                }).start();
            }
        });
    }

    private void initMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            mediaPlayer.setDataSource(recorderUrl);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(mp -> {
                mediaPlayer.start();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        //mediaPlayer.
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer == null)
            return;
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_USER_INFO) || url.equals(Urls.GET_TAGID_INFO)) {
            if (obj != null && obj instanceof LiveRoomUserInfo) {
                LiveRoomUserInfo bannedTime = (LiveRoomUserInfo) obj;
                if (bannedTime.getCode() == 200) {
                    this.userInfo = bannedTime.getData();
                    initData();
                }
            }
        } else if (url.equals(Urls.GET_LIVE_FANS_FOLLOW_DATA)) {
            if (obj != null && obj instanceof LiveHomeFansCountBaseInfo) {
                LiveHomeFansCountBaseInfo countBaseInfo = (LiveHomeFansCountBaseInfo) obj;
                if (countBaseInfo.getCode() == 200) {
                    LogUtils.e("数量=", countBaseInfo.getData().getFansCount() + "===" + countBaseInfo.getData().getFollowCount());
                    if (countBaseInfo.getData().getFansCount() > 0) {
                        String fansCount = "粉丝 " + countBaseInfo.getData().getFansCount();
                        tvFans.setText(fansCount);
                    }
                    if (countBaseInfo.getData().getFollowCount() > 0) {
                        String followCount = "关注 " + countBaseInfo.getData().getFollowCount();
                        tvFollow.setText(followCount);
                    }
                }
            }
        } else if (Urls.GET_FAN_CONTRIBUTION_LIST.equals(url)) {
            if (obj != null && obj instanceof FanContributionList) {
                FanContributionList liveHomeBeanInfo = (FanContributionList) obj;
                if (liveHomeBeanInfo.getCode() == 200) {
                    if (liveHomeBeanInfo.getData().size() >= 3) {
                        ivHeader1.setImageURI(liveHomeBeanInfo.getData().get(0).getPicture());
                        ivHeader2.setImageURI(liveHomeBeanInfo.getData().get(1).getPicture());
                        ivHeader3.setImageURI(liveHomeBeanInfo.getData().get(2).getPicture());
                    } else if (liveHomeBeanInfo.getData().size() >= 2) {
                        ivHeader1.setImageURI(liveHomeBeanInfo.getData().get(0).getPicture());
                        ivHeader2.setImageURI(liveHomeBeanInfo.getData().get(1).getPicture());
                    } else if (liveHomeBeanInfo.getData().size() >= 1) {
                        ivHeader1.setImageURI(liveHomeBeanInfo.getData().get(0).getPicture());
                    }
                }
            }
        } else if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    String str;
                    int i = Integer.parseInt(userInfo.getIsAttention());
                    if (i == 0) {
                        str = "1";
                    } else {
                        str = "0";
                    }
                    userInfo.setIsAttention(str);
                    if (Integer.parseInt(this.userInfo.getIsAttention()) != 1) {
                        tvFollowBtn.setText("关注");
                        ToastUtils.showShortToast("取消关注成功");
                    } else {
                        tvFollowBtn.setText("已关注");
                        ToastUtils.showShortToast("关注成功");
                    }
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        } else if (url.equals(Urls.GET_ATTAND_BLOCK)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    JSONObject jsonObject1 = new JSONObject(jSONObject.getString("data"));
                    isBlock = jsonObject1.getInt("isBlock");
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        } else if (url.equals(Urls.UPDATE_USER_BLACKLIST)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    isBlock = isBlock == 0 ? 1 : 0;
                    if (reportDialog != null && reportDialog.isShowing()) {
                        reportDialog.setData(uid, isBlock);
                    }
                    switch (isBlock) {
                        case 0:
                            ToastUtils.showShortToast("拉黑成功");
                            break;
                        case 1:
                            ToastUtils.showShortToast("取消拉黑成功");
                            break;
                    }
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        } else if (url.equals(Urls.GetPlayUserGameList)) {
            if (obj != null && obj instanceof UserGameListBean) {
                UserGameListBean bean = (UserGameListBean) obj;
                if (bean.getCode() == 200) {
                    adapter.setNewInstance(bean.data);
                }
            }
        }
    }

    private void initData() {
        AppApis.GetPlayUserGameList(this, userInfo.getUserId());
        tvTitle.setVisibility(View.INVISIBLE);
        ivRightTitle.setVisibility(View.VISIBLE);
        if (!userInfo.getUserId().equals(SPUtils.getUserId())) {
            ll5.setVisibility(View.VISIBLE);
            if (Integer.parseInt(userInfo.getIsAttention()) != 1) {
                this.tvFollowBtn.setText("关注");
            } else {
                this.tvFollowBtn.setText("已关注");
            }
            ivReturn1.setVisibility(View.GONE);
            ivReturn2.setVisibility(View.GONE);
            ivRightTitle.setImageResource(R.mipmap.live_icon_live_room_three_point_white);
        } else {
            ll5.setVisibility(View.GONE);
            ivReturn1.setVisibility(View.VISIBLE);
            ivReturn2.setVisibility(View.VISIBLE);
            ivRightTitle.setImageResource(R.mipmap.live_icon_home_page_edit);
        }
        publicTopLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
        sdvBigHeader.setImageURI(userInfo.getPicture());
        sdvSmallHeader.setImageURI(userInfo.getPicture());
        tvUserName.setText(userInfo.getNickname());
        if (null != userInfo.getSex()) {
            if (Integer.parseInt(userInfo.getSex()) != 1) {
                ivUserSex.setImageResource(R.mipmap.live_icon_my_female);
            } else {
                ivUserSex.setImageResource(R.mipmap.live_icon_my_male);
            }
        } else {
            ivUserSex.setImageResource(R.mipmap.live_icon_my_male);
        }
        tvUserLevel.setText(userInfo.getUserGrade());
        tvLevelUser.setText("LV" + userInfo.getUserGrade());
        UserLevelSetUtils.setUserLevel(tvUserLevel, userInfo.getUserGrade() + "");
        switch (userInfo.getType()) {
            case "1":
                tvLevelHost.setVisibility(View.GONE);
                ll4.setVisibility(View.GONE);
                tvFansList.setVisibility(View.GONE);
                rl3.setVisibility(View.GONE);
                tvUserNameHost.setVisibility(View.GONE);
                break;
            case "2":
                tvFansList.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.VISIBLE);
                ll4.setVisibility(View.VISIBLE);
                tvLevelHost.setText("LV" + userInfo.getAnchorGrade());
                tvUserNameHost.setText(userInfo.getAnchorGrade());
                UserLevelSetUtils.setHostLevel(tvUserNameHost, userInfo.getAnchorGrade() + "", true);
                break;
        }
        tvUserSign.setText(userInfo.getSgin());
        tvId.setText(userInfo.getUserId());
        if (userInfo.getUserId().equals(SPUtils.getUserId())) {
            tvFans.setEnabled(true);
            tvFollow.setEnabled(true);
        } else {
            tvFans.setEnabled(false);
            tvFollow.setEnabled(false);
        }
        this.tvId.setText(userInfo.getBeautifulUserId());
        tvHost.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @OnClick({R.id.sdv_big_header, R.id.tv_follow_btn, R.id.tv_sixin, R.id.tv_fans, R.id.tv_follow, R.id.iv_title, R.id.iv_right_title, R.id.rl_3, R.id.tv_copy, R.id.ll_3, R.id.rl_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sdv_big_header:
                break;
            case R.id.iv_title:
                finish();
                break;
            case R.id.iv_right_title:
                if (null != userInfo) {
                    if (userInfo.getUserId().equals(SPUtils.getUserId())) {
                        Intent intent = new Intent(this, MyEditUserInfoActivity.class);
                        intent.putExtra("userInfo", userInfo);
                        startActivity(intent);
                    } else {
                        //                        弹窗
                        showReportDialog(userInfo.getUserId());
                    }
                }
                break;
            case R.id.tv_fans:
                Intent intent = new Intent(this, FocusOrFollowActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("userId", SPUtils.getUserId());
                startActivity(intent);
                break;
            case R.id.tv_follow:
                Intent intent1 = new Intent(this, FocusOrFollowActivity.class);
                intent1.putExtra("type", 2);
                intent1.putExtra("userId", SPUtils.getUserId());
                startActivity(intent1);
                break;
            case R.id.rl_3:
                Intent intent2 = new Intent(this, ContributionListActivity.class);
                intent2.putExtra("type", 3);
                intent2.putExtra("targetId", userInfo.getUserId());
                startActivity(intent2);
                break;
            case R.id.tv_follow_btn:
                if (null != userInfo) {
                    AppApis.getUpdateFollow(Integer.parseInt(userInfo.getIsAttention()) != 0 ? 0 : 1, this.userInfo.getUserId(), this);
                }
                break;
            case R.id.tv_sixin:
                jumpToMessage();
                break;
            case R.id.tv_copy:
                if (userInfo != null && !userInfo.getUserId().isEmpty()) {
                    copy(userInfo.getUserId());
                }
                break;
            case R.id.ll_3:
                if (userInfo != null && userInfo.getUserId().equals(SPUtils.getUserId())) {
                    intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra("targetId", userInfo.getUserId());
                    intent.putExtra("type", 6);
                    startActivity(intent);
                }
                break;
            case R.id.rl_4:
                if (userInfo != null && userInfo.getUserId().equals(SPUtils.getUserId())) {
                    intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra("targetId", userInfo.getUserId());
                    intent.putExtra("type", 5);
                    startActivity(intent);
                }
                break;
        }
    }

    private void jumpToMessage() {
        if (!SPUtils.getUserId().equals(userInfo.getUserId())) {
            Message bean = new Message();
            bean.setUserId(SPUtils.getUserId());
            bean.setTargetId(userInfo.getUserId());
            bean.setUserName(userInfo.getNickname());
            bean.setPicture(userInfo.getPicture());
            Intent intent3 = new Intent(this, LiveMessageActivity.class);
            intent3.putExtra("type", 3);
            intent3.putExtra("message", bean);
            startActivity(intent3);
        }
    }

    //复制
    private void copy(String data) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）,其他的还有
        ClipData clipData = ClipData.newPlainText(null, data);

        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
        ToastUtils.showShortToast("复制成功");
    }


    private void showReportDialog(String uid) {
        if (reportDialog == null) {
            reportDialog = new DialogLiveRoomUserReport1(this, uid);
        }
        reportDialog.show();
        reportDialog.setData(uid, isBlock);
        reportDialog.setDialogClickListener((param1String, param1Int) -> {
            AppApis.getUpdateBlock(isBlock == 0 ? 1 : 0, uid, this);
        });
        this.reportDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    @BusUtils.Bus(tag = "jumpMsg")
    public void jump2Msg(){
       jumpToMessage();
    }
}