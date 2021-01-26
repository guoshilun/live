package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.entity.LiveRoomBannedTime;
import com.jk.jkproject.ui.entity.LiveRoomUserInfo;
import com.jk.jkproject.ui.fragment.LiveRoomFragment;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.StringUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogLiveRoomUserInfo extends BaseDialog implements ResponseListener {
    @BindView(R.id.iv_live_room_report)
    ImageView ivLiveRoomReport;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_user_sex)
    ImageView ivUserSex;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.tv_user_name_host)
    TextView tvUserNameHost;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.tv_user_address)
    TextView tvUserAddress;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.tv_follow_count)
    TextView tvFollowCount;
    @BindView(R.id.tv_fans_count)
    TextView tvFansCount;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_diamond_count)
    TextView tvDiamondCount;
    @BindView(R.id.tv_diamond)
    TextView tvDiamond;
    @BindView(R.id.tv_gift_count)
    TextView tvGiftCount;
    @BindView(R.id.tv_gift)
    TextView tvGift;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_Ta)
    TextView tvTa;
    @BindView(R.id.tv_pri_chat)
    TextView tvPriChat;
    @BindView(R.id.tv_home_page)
    TextView tvHomePage;
    @BindView(R.id.sdv_live_room_header)
    SimpleDraweeView sdvLiveRoomHeader;
    @BindView(R.id.ll_4)
    LinearLayout ll4;
    @BindView(R.id.ll_6)
    LinearLayout ll6;
    @BindView(R.id.tv_m1)
    TextView tvM1;
    @BindView(R.id.tv_m2)
    TextView tvM2;
    @BindView(R.id.tv_m3)
    TextView tvM3;
    @BindView(R.id.tv_m4)
    TextView tvM4;
    private LiveRoomUserInfo bannedTime;
    private Unbinder bind;
    private List<LiveRoomBannedTime.DataBean> list = new ArrayList<LiveRoomBannedTime.DataBean>();

    private DialogReturnListener listener;

    private Context mContext;

    private int mType;
    private String uid;
    private int position;
    private int userType;
    private String roomId;

    private LiveRoomUserInfo.DataBean userInfo;

    public DialogLiveRoomUserInfo(Context context, String uid, int position, int oth, String roomId) {
        super(context);
        this.mContext = context;
        this.uid = uid;
        this.position = position;
        this.userType = oth;
        this.roomId = roomId;
    }

    private void initData() {
        try {
            LiveRoomFragment.UserName = userInfo.getNickname();
            this.sdvLiveRoomHeader.setImageURI(this.userInfo.getPicture());
            this.tvUserName.setText(this.userInfo.getNickname());
            if (Integer.parseInt(this.userInfo.getSex()) != 1) {
                ivUserSex.setImageResource(R.mipmap.live_icon_my_female);
            } else {
                ivUserSex.setImageResource(R.mipmap.live_icon_my_male);
            }
            this.tvUserLevel.setText(this.userInfo.getUserGrade());
            this.tvUserNameHost.setText(this.userInfo.getAnchorGrade());

            UserLevelSetUtils.setHostLevel(tvUserNameHost, userInfo.getAnchorGrade());
            UserLevelSetUtils.setUserLevel(tvUserLevel, userInfo.getUserGrade());
            this.tvUserAddress.setText(this.userInfo.getRegion());
            this.tvSign.setText(this.userInfo.getSgin());


            tvUserId.setText("直播ID：" + userInfo.getBeautifulUserId());
            tvFansCount.setText(userInfo.getFans() + "");
            tvFollowCount.setText(userInfo.getAttention() + "");
            tvDiamondCount.setText(StringUtils.ReadSize(Integer.parseInt(userInfo.getGold())));
            tvGiftCount.setText(StringUtils.ReadSize(Integer.parseInt(userInfo.getGiver())));

            if (Integer.parseInt(this.userInfo.getIsAttention()) != 1) {
                this.tvFollow.setText("关注");
            } else {
                this.tvFollow.setText("已关注");
                tvFollow.setEnabled(false);
            }
            switch (userInfo.getBanned()) {
                case -2:
                    tvM2.setText("禁言");
                    break;
                default:
                    tvM2.setText("解除禁言");
                    tvM2.setTextColor(context.getResources().getColor(R.color.color_CCCCCC));
                    break;
            }
            //1 普通用户  2.主播  3.管理员
            switch (userInfo.getType()) {
                case "1":
                    tvM4.setText("设为管理员");
                    break;
                case "3":
                    tvM4.setText("解除管理员");
                    tvM4.setTextColor(context.getResources().getColor(R.color.color_CCCCCC));
                    break;
                default:
                    break;
            }

            setView();
        } catch (NumberFormatException | Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_user_info);
        bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        mWidthScale = 0.95F;
        mDimAmount = 0.6F;
        gravity = Gravity.BOTTOM;
        AppApis.getGitLiveRoomUserInfo(roomId, uid, this);
    }

    @Override
    protected void initView() {

    }

    protected void setView() {
        if (uid.equals(SPUtils.getUserId())) {
            tvFollow.setVisibility(View.GONE);
            tvTa.setVisibility(View.GONE);
            tvPriChat.setVisibility(View.GONE);
            ivLiveRoomReport.setVisibility(View.GONE);
        } else {
            tvFollow.setVisibility(View.VISIBLE);
            tvTa.setVisibility(View.VISIBLE);
            tvPriChat.setVisibility(View.VISIBLE);
            ivLiveRoomReport.setVisibility(View.VISIBLE);
            switch (userInfo.getMyType()) {
                case "1":
                    tvHomePage.setText("主页");
                    break;
                case "3":
                    if ("3".equals(userInfo.getType())) {
                        tvHomePage.setText("主页");
                    } else {
                        tvHomePage.setText("管理");
                    }
                    break;
            }
            if (SPUtils.getIsHost()) {
                tvHomePage.setText("管理");
                tvM4.setVisibility(View.VISIBLE);
            } else {
                tvM4.setVisibility(View.GONE);
            }
        }
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
                    LogUtils.e("======", userInfo.toString());
                    initData();
                }
            }
        } else if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    userInfo.setIsAttention(userInfo.getIsAttention().equals("1") ? "0" : "1");
                    LiveRoomFragment.isFollow = userInfo.getIsAttention().equals("1");
                    if (Integer.parseInt(userInfo.getIsAttention()) != 1) {
                        this.tvFollow.setText("关注");
                    } else {
                        this.tvFollow.setText("已关注");
                        tvFollow.setEnabled(false);
                    }
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        }
    }

    @OnClick({R.id.ll_4, R.id.tv_m1, R.id.tv_m2, R.id.tv_m3, R.id.tv_m4, R.id.tv_pri_chat, R.id.tv_home_page, R.id.tv_follow, R.id.tv_Ta, R.id.iv_live_room_report})
    public void onViewClicked(View paramView) {
        if (listener != null && userInfo != null) {
            switch (paramView.getId()) {
                default:
                    return;
                case R.id.tv_pri_chat:
                    this.listener.onDialogReturnClick(userInfo.getUserId(), 4);
                    dismiss();
                    break;
                case R.id.tv_home_page:
                    if (tvHomePage.getText().toString().trim().equals("管理")) {
                        ll6.setVisibility(View.VISIBLE);
                    } else {
                        listener.onDialogReturnClick(userInfo.getUserId(), 5);
                        dismiss();
                    }
                    break;
                case R.id.tv_follow:
                    AppApis.getUpdateFollow(Integer.parseInt(userInfo.getIsAttention()) == 0 ? 1 : 0, userInfo.getUserId(), this);
                    break;
                case R.id.tv_Ta:
                    listener.onDialogReturnClick(userInfo.getNickname(), 3);
                    dismiss();
                    break;
                case R.id.iv_live_room_report:
                    listener.onDialogReturnClick(userInfo.getUserId(), 1);
                    dismiss();
                    break;
                case R.id.ll_4:
                    break;
                case R.id.tv_m1:
                    ll6.setVisibility(View.GONE);
//                    IMLiveRoomManager.instance().updateBanned(roomId, uid, "live.KickLiveHandler");
                    listener.onDialogReturnClick(userInfo.getUserId(), 6);
                    dismiss();
                    break;
                case R.id.tv_m2:
                    ll6.setVisibility(View.GONE);
                    switch (userInfo.getBanned()) {
                        case -2:
//                            IMLiveRoomManager.instance().updateBanned(roomId, uid, "live.BannedUser");
                            listener.onDialogReturnClick(userInfo.getUserId(), 7);
                            dismiss();
                            break;
                        default:
//                            IMLiveRoomManager.instance().updateBanned(roomId, uid, "live.CancelBanned");
                            listener.onDialogReturnClick(userInfo.getUserId(), 8);
                            dismiss();
                            break;
                    }
                    break;
                case R.id.tv_m3:
                    ll6.setVisibility(View.GONE);
                    break;
                case R.id.tv_m4: //设为管理员
                    switch (userInfo.getType()) {
                        case "1":
//                            IMLiveRoomManager.instance().updateBanned(roomId, uid, "live.SettingAdminHandler");
                            listener.onDialogReturnClick(userInfo.getUserId(), 9);
                            dismiss();
                            break;
                        case "3":
                            listener.onDialogReturnClick(userInfo.getUserId(), 10);
                            dismiss();
                            break;
                        default:
                            break;
                    }
                    ll6.setVisibility(View.GONE);
                    break;
            }

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
}