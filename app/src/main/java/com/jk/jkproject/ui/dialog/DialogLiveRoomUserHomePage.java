package com.jk.jkproject.ui.dialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.activity.MyEditUserInfoActivity;
import com.jk.jkproject.ui.activity.WebViewActivity;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.entity.LiveRoomUserInfo;
import com.jk.jkproject.ui.fragment.LiveRoomFragment;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/7/31 5:50 PM
 * @desc 主页弹窗
 */
public class DialogLiveRoomUserHomePage extends BaseDialog implements ResponseListener {
    @BindView(R.id.sdv_big_header)
    SimpleDraweeView sdvBigHeader;
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
    @BindView(R.id.sdv_small_header)
    SimpleDraweeView sdvSmallHeader;
    @BindView(R.id.tv_host)
    TextView tvHost;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_user_sex)
    ImageView ivUserSex;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.tv_user_name_host)
    TextView tvUserNameHost;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_user_sign)
    TextView tvUserSign;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.ll_5)
    LinearLayout ll5;
    @BindView(R.id.tv_fans_list)
    TextView tvFansList;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_fans_list_1)
    TextView tvFansList1;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.iv_header_1)
    SimpleDraweeView ivHeader1;
    @BindView(R.id.iv_header_2)
    SimpleDraweeView ivHeader2;
    @BindView(R.id.iv_header_3)
    SimpleDraweeView ivHeader3;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_level_user)
    TextView tvLevelUser;
    @BindView(R.id.ll_3)
    RelativeLayout ll3;
    @BindView(R.id.rl_4)
    RelativeLayout rl4;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_level_host)
    TextView tvLevelHost;
    @BindView(R.id.tv_follow_btn)
    TextView tvFollowBtn;
    @BindView(R.id.tv_sixin)
    TextView tvSixin;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.iv_return_1)
    ImageView ivReturn1;
    @BindView(R.id.iv_return_2)
    ImageView ivReturn2;
    private Unbinder bind;


    private DialogReturnListener listener;


    private Context mContext;

    private int mType;

    private String uid;
    private String roomId;

    private LiveRoomUserInfo.DataBean userInfo;
    private String hostUserId; //直播id
    private Intent intent;
    private DialogLiveRoomUserReport1 reportDialog;
    private int isBlock;

    public DialogLiveRoomUserHomePage(Context context, String uid, String roomId, String hostUserId) {
        super(context);
        this.mContext = context;
        this.uid = uid;
        this.roomId = roomId;
        this.hostUserId = hostUserId;
    }

    private void initData() {
        tvTitle.setVisibility(View.INVISIBLE);
        ivRightTitle.setVisibility(View.VISIBLE);
        if (userInfo.getUserId().equals(SPUtils.getUserId())) {
            ll5.setVisibility(View.GONE);
            ivRightTitle.setImageResource(R.mipmap.live_icon_home_page_edit);
            ivReturn1.setVisibility(View.VISIBLE);
            ivReturn2.setVisibility(View.VISIBLE);
        } else {
            ivRightTitle.setImageResource(R.mipmap.live_icon_live_room_three_point_white);
            ivReturn1.setVisibility(View.GONE);
            ivReturn2.setVisibility(View.GONE);
            AppApis.getAttandBlock(userInfo.getUserId(), this);
        }
        ivTitle.setImageResource(R.mipmap.icon_white_return);
        publicTopLayout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        sdvBigHeader.setImageURI(userInfo.getPicture());
        sdvSmallHeader.setImageURI(userInfo.getPicture());
        tvUserName.setText(userInfo.getNickname());
        if (Integer.parseInt(this.userInfo.getSex()) != 1) {
            ivUserSex.setImageResource(R.mipmap.live_icon_my_female);
        } else {
            ivUserSex.setImageResource(R.mipmap.live_icon_my_male);
        }
        tvUserLevel.setText(userInfo.getUserGrade());
        tvLevelUser.setText("LV" + userInfo.getUserGrade());
        UserLevelSetUtils.setUserLevel(tvUserLevel, userInfo.getUserGrade() + "");
        switch (userInfo.getType()) {
            case "1":
                tvLevelHost.setVisibility(View.GONE);
                rl4.setVisibility(View.GONE);
                tvFansList.setVisibility(View.GONE);
                rl3.setVisibility(View.GONE);
                tvUserNameHost.setVisibility(View.GONE);
                break;
            case "2":
                tvFansList.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.VISIBLE);
                rl4.setVisibility(View.VISIBLE);
                tvLevelHost.setText("LV" + userInfo.getAnchorGrade());
                tvUserNameHost.setText(userInfo.getAnchorGrade());
                UserLevelSetUtils.setHostLevel(tvUserNameHost, userInfo.getAnchorGrade() + "", true);
                break;
        }
        tvUserSign.setText(userInfo.getSgin());
        TextView textView1 = tvFans;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("粉丝 ");
        stringBuilder2.append(userInfo.getFans());
        textView1.setText(stringBuilder2.toString());
        TextView textView2 = tvFollow;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("关注 ");
        stringBuilder1.append(userInfo.getAttention());
        textView2.setText(stringBuilder1.toString());
        this.tvId.setText(userInfo.getBeautifulUserId());
        if (Integer.parseInt(userInfo.getIsAttention()) != 1) {
            this.tvFollowBtn.setText("关注");
        } else {
            this.tvFollowBtn.setText("已关注");
        }
        if (Integer.parseInt(userInfo.getLiveStatus()) != 1) {
            this.tvHost.setVisibility(View.GONE);
        } else {
            this.tvHost.setVisibility(View.VISIBLE);
        }
        LiveRoomFragment.UserName = userInfo.getNickname();
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_user_home_page);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 1.0F;
        this.h = -1;
        this.gravity = 80;
        AppApis.getGitLiveRoomUserInfo(roomId, uid, this);
        AppApis.getUserFansList(uid, 1, 0, this);
    }

    protected void initView() {
    }

    public void onClick(View paramView) {
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_LIVE_ROOM_USER_INFO)) {
            if (obj != null && obj instanceof LiveRoomUserInfo) {
                LiveRoomUserInfo bannedTime = (LiveRoomUserInfo) obj;
                if (bannedTime.getCode() == 200) {
                    userInfo = bannedTime.getData();
                    initData();
                }
            }
        } else if (Urls.GET_USER_FANS_LIST.equals(url)) {
            if (obj != null && obj instanceof LiveHomeBeanInfo) {
                LiveHomeBeanInfo liveHomeBeanInfo = (LiveHomeBeanInfo) obj;
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
                    if (uid.equals(hostUserId)) {
                        LiveRoomFragment.isFollow = userInfo.getIsAttention().equals("1");
                    }
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
        }
    }

    @OnClick({R.id.iv_title, R.id.iv_right_title, R.id.tv_host, R.id.rl_3, R.id.ll_3, R.id.rl_4, R.id.tv_follow_btn, R.id.tv_sixin, R.id.tv_copy})
    public void onViewClicked(View paramView) {
        switch (paramView.getId()) {
            default:
                break;
            case R.id.tv_sixin:
                if (listener != null) {
                    listener.onDialogReturnClick(this.userInfo.getUserId(), 2);
                    dismiss();
                }
                break;
            case R.id.tv_follow_btn:
                AppApis.getUpdateFollow(Integer.parseInt(userInfo.getIsAttention()) != 0 ? 0 : 1, this.userInfo.getUserId(), this);
                break;
            case R.id.rl_3:
                if (listener != null) {
                    listener.onDialogReturnClick(this.userInfo.getUserId(), 3);
                    dismiss();
                }
                break;
            case R.id.iv_title:
                dismiss();
                break;
            case R.id.iv_right_title:
                if (null != userInfo) {
                    if (userInfo.getUserId().equals(SPUtils.getUserId())) {
                        Intent intent = new Intent(context, MyEditUserInfoActivity.class);
                        intent.putExtra("userInfo", userInfo);
                        context.startActivity(intent);
                    } else {
                        showReportDialog(userInfo.getUserId());
                    }
                }
                break;
            case R.id.tv_copy:
                if (userInfo != null && !userInfo.getUserId().isEmpty()) {
                    copy(userInfo.getUserId());
                }
                break;
            case R.id.ll_3:
                if (userInfo != null && userInfo.getUserId().equals(SPUtils.getUserId())) {
                    intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("targetId", userInfo.getUserId());
                    intent.putExtra("type", 6);
                    context.startActivity(intent);
                }
                break;
            case R.id.rl_4:
                if (userInfo != null && userInfo.getUserId().equals(SPUtils.getUserId())) {
                    intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("targetId", userInfo.getUserId());
                    intent.putExtra("type", 5);
                    context.startActivity(intent);
                }
                break;
        }
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public void updateInfo(String uid) {
        AppApis.getGitLiveRoomUserInfo(roomId, uid, this);
    }

    public static interface DialogReturnListener {
        void onDialogReturnClick(String param1String, int param1Int);
    }

    //复制
    private void copy(String data) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）,其他的还有
        ClipData clipData = ClipData.newPlainText(null, data);

        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
        ToastUtils.showShortToast("复制成功");
    }

    private void showReportDialog(String uid) {
        if (reportDialog == null) {
            reportDialog = new DialogLiveRoomUserReport1(context, uid);
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
}
