package com.jk.jkproject.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fansan.common.ext.ActivityMessenger;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.activity.DiamondTopUpActivity;
import com.jk.jkproject.ui.activity.FocusOrFollowActivity;
import com.jk.jkproject.ui.activity.MyEditUserInfoActivity;
import com.jk.jkproject.ui.activity.MyHomePageActivity;
import com.jk.jkproject.ui.activity.MySetActivity;
import com.jk.jkproject.ui.activity.ShareQrCodeActivity;
import com.jk.jkproject.ui.activity.WebViewActivity;
import com.jk.jkproject.ui.adapter.MyFocusAdapter;
import com.jk.jkproject.ui.entity.LiveHomeFansCountBaseInfo;
import com.jk.jkproject.ui.entity.LiveRoomUserInfo;
import com.jk.jkproject.ui.entity.MyMoneyBean;
import com.jk.jkproject.ui.widget.DrawerLiveItemView;
import com.jk.jkproject.ui.widget.MyItemView;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;
import com.jk.order.OrderListActivity;
import com.jk.order.TakeOrderActivity;
import com.jk.weblib.EsportsActivity;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyCenterFragment extends BFragment implements Observer {
    private static String tab_type = "tab_type";
    @BindView(R.id.sdv_user_header)
    SimpleDraweeView   sdvUserHeader;
    @BindView(R.id.tv_user_name)
    TextView           tvUserName;
    @BindView(R.id.iv_sex)
    ImageView          ivSex;
    @BindView(R.id.tv_user_ID)
    TextView           tvUserID;
    @BindView(R.id.tv_user_level)
    TextView           tvUserLevel;
    @BindView(R.id.mv_my_top_up)
    MyItemView         mvMyTopUp;
    @BindView(R.id.mv_my_certification)
    MyItemView         mvMyCertification;
    @BindView(R.id.mv_my_premiere)
    MyItemView         mvMyPremiere;
    @BindView(R.id.rl_my_header)
    RelativeLayout     rlMyHeader;
    @BindView(R.id.rl_home_page)
    RelativeLayout     rlHomePage;
    @BindView(R.id.div_wallet)
    DrawerLiveItemView divWallet;
    @BindView(R.id.div_host)
    DrawerLiveItemView divHost;
    @BindView(R.id.div_fans)
    DrawerLiveItemView divFans;
    @BindView(R.id.div_guess)
    DrawerLiveItemView guess;
    @BindView(R.id.order)
    DrawerLiveItemView order;
    @BindView(R.id.orderCenter)
    DrawerLiveItemView orderCenter;
    @BindView(R.id.setting)
    DrawerLiveItemView setting;
    @BindView(R.id.div_browse)
    DrawerLiveItemView divBrowse;
    @BindView(R.id.div_appointment)
    DrawerLiveItemView divAppointment;
    @BindView(R.id.tv_user_name_host)
    TextView           tvUserNameHost;
    @BindView(R.id.goldenDiamond)
    TextView           goldenDiamond;
    @BindView(R.id.balance)
    TextView           balance;
    Unbinder unbinder;

    private Handler                   handler = new Handler() {
        @SuppressLint({"HandlerLeak"})
        public void handleMessage(Message param1Message) {
            Bundle bundle = (Bundle) param1Message.obj;
            if (bundle.getInt("method") == 20 && bundle.getInt("statusType") == 1)
                MyCenterFragment.this.setView();
        }
    };
    private MyFocusAdapter            myFouceAdapter;
    private String                    tabName = "";
    private LiveHomeFansCountBaseInfo liveHomeFansCountBaseInfo;
    private LiveRoomUserInfo roomUserInfo = null;

    public static MyCenterFragment newInstance(String paramString) {
        MyCenterFragment myCenterFragment = new MyCenterFragment();
        Bundle bundle = new Bundle();
        bundle.putString(tab_type, paramString);
        myCenterFragment.setArguments(bundle);
        return myCenterFragment;
    }

    private void setView() {
        this.mvMyCertification.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        AppApis.getHomeFansCountAndFollowData(SPUtils.getUserId(), this);
        AppApis.getGitUserInfo(SPUtils.getUserId(), this);
        AppApis.getMoney(this);
    }

    protected void initData() {
        super.initData();
        this.sdvUserHeader.setImageURI(SPUtils.getPortrait());
        this.tvUserName.setText(SPUtils.getNickname());
        mvMyTopUp.setIconResId(R.mipmap.live_icon_my_focus);
        mvMyCertification.setIconResId(R.mipmap.live_icon_my_fans);
        mvMyPremiere.setIconResId(R.drawable.me_edit);
        mvMyPremiere.setDes("编辑资料");
        mvMyPremiere.setDesTextColor(getResources().getColor(R.color.color_666));
        divHost.setVisib(R.id.tv_desc);
        divFans.setVisib(R.id.tv_desc);
        divWallet.setVisib(R.id.tv_desc);
        divBrowse.setVisib(R.id.tv_desc);
        divAppointment.setVisib(R.id.tv_desc);
        divWallet.setName("我的等级");
        divWallet.setIconResId(R.drawable.me_icon_level);
        divHost.setName("主播等级");
        divHost.setVisibility(View.GONE);
        divFans.setName("我的收益");
        divFans.setVisibility(View.GONE);
        guess.setName("我的竞猜");
        guess.setIconResId(R.drawable.me_icon_guess);
        divBrowse.setName("钻石充值");
        divAppointment.setName("设置");
        setting.setName("设置");
        setting.setName("设置");
        setting.setIconResId(R.drawable.me_icon_setting);
        order.setName("我要接单");
        order.setIconResId(R.drawable.me_icon_getorider);
        orderCenter.setName("订单中心");
        orderCenter.setIconResId(R.drawable.me_icon_order);
        if (SPUtils.getSex() != 1) {
            this.ivSex.setImageResource(R.mipmap.live_icon_my_female);
        } else {
            this.ivSex.setImageResource(R.mipmap.live_icon_my_male);
        }
        int userGrade = SPUtils.getInt("usergrade", 0);
        String anchorgrade = SPUtils.getString("anchorgrade", "");
        tvUserLevel.setText(userGrade + "");
        tvUserNameHost.setText(anchorgrade);
        UserLevelSetUtils.setUserLevel(tvUserLevel, userGrade + "");
        UserLevelSetUtils.setHostLevel(tvUserNameHost, anchorgrade + "", false);
        SharedPreferences esportsUrl = getActivity().getSharedPreferences("esportsUrl", Context.MODE_PRIVATE);
        if (esportsUrl.getBoolean("esportShown", false)) {
            showEsport();
        }
    }

    protected void initView(View paramView) {
        super.initView(paramView);
        //        AppApis.getHomeFansCountAndFollowData(SPUtils.getUserId(), this);
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    public void onSuccess(String paramString, Object paramObject) {
        super.onSuccess(paramString, paramObject);
        if (paramString.equals(Urls.GET_LIVE_FANS_FOLLOW_DATA) && paramObject != null && paramObject instanceof LiveHomeFansCountBaseInfo) {
            liveHomeFansCountBaseInfo = (LiveHomeFansCountBaseInfo) paramObject;
            if (liveHomeFansCountBaseInfo.getCode() == 200) {
                mvMyCertification.setDes(liveHomeFansCountBaseInfo.getData().getFansCount() + "粉丝");
                mvMyCertification.setDesTextColor(getResources().getColor(R.color.color_666));
                mvMyTopUp.setDesTextColor(getResources().getColor(R.color.color_666));
                mvMyTopUp.setDes(liveHomeFansCountBaseInfo.getData().getFollowCount() + "关注");
            }
        } else if (paramString.equals(Urls.GET_USER_INFO)) {
            if (paramObject != null && paramObject instanceof LiveRoomUserInfo) {
                LiveRoomUserInfo bannedTime = (LiveRoomUserInfo) paramObject;
                roomUserInfo = (LiveRoomUserInfo) paramObject;
                if (bannedTime.getCode() == 200) {
                    SPUtils.putString("iphoneId", bannedTime.getData().getIphoneId());
                    SPUtils.putString("qqId", bannedTime.getData().getQqId());
                    SPUtils.putString("openId", bannedTime.getData().getOpenId());
                    TextView textView = this.tvUserID;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("ID ");
                    stringBuilder.append(roomUserInfo.getData().getBeautifulUserId());
                    textView.setText(stringBuilder.toString());
                    if (SPUtils.getLatitude().equals("")) {
                        SPUtils.setLatitude(bannedTime.getData().getN());
                    }
                    if (SPUtils.getLongitude().equals("")) {
                        SPUtils.setLongitude(bannedTime.getData().getE());
                    }
                }
            }
        } else if (paramString.equals(Urls.GET_MONEY)) {
            if (paramObject != null && paramObject instanceof MyMoneyBean) {
                MyMoneyBean bean = (MyMoneyBean) paramObject;
                if (bean.getCode() == 200) {
                    MyMoneyBean.DataBean data = bean.getData();
                    goldenDiamond.setText(String.valueOf(data.getAmount2()));
                    balance.setText(String.valueOf(data.getAmount1()));
                }
            }
        }
    }

    protected int setLayoutId() {
        return R.layout.my_layout;
    }

    public void update(Observable paramObservable, Object paramObject) {
        if (paramObject != null) {
            Message message = Message.obtain();
            message.obj = paramObject;
            this.handler.sendMessage(message);
        }
    }

    public void showEsport() {
        guess.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.sdv_user_header, R.id.tv_user_name, R.id.rl_home_page, R.id.iv_sex, R.id.tv_user_ID, R.id.tv_user_level, R.id.mv_my_top_up, R.id.mv_my_certification, R.id.mv_my_premiere, R.id.rl_my_header, R.id.div_wallet, R.id.div_host, R.id.div_fans, R.id.div_browse, R.id.div_appointment, R.id.div_guess, R.id.setting, R.id.order, R.id.orderCenter,R.id.invite,R.id.me_balance,R.id.me_gold_balance})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.sdv_user_header:
                break;
            case R.id.tv_user_name:
                break;
            case R.id.iv_sex:
                break;
            case R.id.tv_user_ID:
                break;
            case R.id.tv_user_level:
                break;
            case R.id.mv_my_top_up:
                intent = new Intent(getContext(), FocusOrFollowActivity.class);
                intent.putExtra("type", 2);
                intent.putExtra("userId", SPUtils.getUserId());
                startActivity(intent);
                break;
            case R.id.mv_my_certification:
                intent = new Intent(getContext(), FocusOrFollowActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("userId", SPUtils.getUserId());
                startActivity(intent);
                break;
            case R.id.mv_my_premiere:
                Intent i = new Intent(getContext(), MyEditUserInfoActivity.class);
                i.putExtra("userInfo", roomUserInfo.getData());
                startActivity(i);
                break;
            case R.id.rl_my_header:
                break;
            case R.id.div_wallet:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("type", 6);
                startActivity(intent);
                break;
            case R.id.div_host:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("type", 5);
                startActivity(intent);
                break;
            case R.id.div_fans:
            case R.id.me_gold_balance:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                //                startActivity(new Intent(getContext(), TextActivity.class));
                break;
            case R.id.div_browse:
            case R.id.me_balance:
                intent = new Intent(getContext(), DiamondTopUpActivity.class);
                startActivity(intent);
                break;
            case R.id.div_appointment:
            case R.id.setting:
                startActivity(new Intent(getContext(), MySetActivity.class));
                break;
            case R.id.rl_home_page:
                if (OnClickUtils.isFastClick()) {
                    intent = new Intent(getContext(), MyHomePageActivity.class);
                    intent.putExtra("u_id", SPUtils.getUserId());
                    if (liveHomeFansCountBaseInfo != null) {
                        intent.putExtra("fans", liveHomeFansCountBaseInfo.getData().getFansCount());
                        intent.putExtra("follow", liveHomeFansCountBaseInfo.getData().getFollowCount());
                    }
                    startActivity(intent);
                }
                break;
            case R.id.div_guess:
                EsportsActivity.start(getContext(), 2);
                break;
            case R.id.orderCenter:
                ActivityMessenger.INSTANCE.startActivity(getActivity(), OrderListActivity.class);
                break;
            case R.id.order:
                ActivityMessenger.INSTANCE.startActivity(getActivity(), TakeOrderActivity.class);
                break;
            case R.id.invite:
                startActivity(new Intent(getContext(), ShareQrCodeActivity.class));
                break;

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            AppApis.getMoney(this);
            SharedPreferences esportsUrl = getActivity().getSharedPreferences("esportsUrl", Context.MODE_PRIVATE);
            if (esportsUrl.getBoolean("esportShown", false)) {
                showEsport();
            }
        }
    }
}
