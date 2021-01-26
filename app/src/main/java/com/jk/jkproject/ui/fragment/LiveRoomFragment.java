package com.jk.jkproject.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.AppApplication;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.manager.MessageHelper;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.net.im.manager.IMLiveRoomManager;
import com.jk.jkproject.net.im.manager.IMLoginManager;
import com.jk.jkproject.net.im.service.ChujianIMService;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.activity.DiamondTopUpActivity;
import com.jk.jkproject.ui.activity.LoginActivity;
import com.jk.jkproject.ui.activity.MyPremiereActivity;
import com.jk.jkproject.ui.activity.SowingLiveActivity;
import com.jk.jkproject.ui.activity.UserLiveActivity;
import com.jk.jkproject.ui.activity.WebViewActivity;
import com.jk.jkproject.ui.adapter.LiveChatRoomAdapter;
import com.jk.jkproject.ui.adapter.LiveFoundRecommendAdapter;
import com.jk.jkproject.ui.adapter.LiveOnlineUsersAdapter;
import com.jk.jkproject.ui.adapter.LiveRoomViewPageAdapter;
import com.jk.jkproject.ui.dialog.DialogChat;
import com.jk.jkproject.ui.dialog.DialogHostOperation;
import com.jk.jkproject.ui.dialog.DialogLiveMessage;
import com.jk.jkproject.ui.dialog.DialogLiveRoomFollow;
import com.jk.jkproject.ui.dialog.DialogLiveRoomGameGold;
import com.jk.jkproject.ui.dialog.DialogLiveRoomGameGoldHint;
import com.jk.jkproject.ui.dialog.DialogLiveRoomLackBalance;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPKDisconnect;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPKFriendInFailure;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPKFriendInvitation;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPKInvitation;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPKList;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPKToGame;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPKType;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPkContributionList;
import com.jk.jkproject.ui.dialog.DialogLiveRoomPunishmentClose;
import com.jk.jkproject.ui.dialog.DialogLiveRoomRankList;
import com.jk.jkproject.ui.dialog.DialogLiveRoomShare;
import com.jk.jkproject.ui.dialog.DialogLiveRoomSkinCare;
import com.jk.jkproject.ui.dialog.DialogLiveRoomUserHomePage;
import com.jk.jkproject.ui.dialog.DialogLiveRoomUserInfo;
import com.jk.jkproject.ui.dialog.DialogLiveRoomUserReport;
import com.jk.jkproject.ui.dialog.DialogLiveRoomWheat;
import com.jk.jkproject.ui.dialog.DialogLiveRoomWheatList;
import com.jk.jkproject.ui.dialog.DialogLockCount;
import com.jk.jkproject.ui.dialog.DialogNotices;
import com.jk.jkproject.ui.dialog.DialogSystemMessage;
import com.jk.jkproject.ui.dialog.LiveGiftDialog;
import com.jk.jkproject.ui.entity.DanmuBean;
import com.jk.jkproject.ui.entity.JoinRoomBeanInfo;
import com.jk.jkproject.ui.entity.LiveClosePushInfo;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.entity.LiveGiftUtils;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.entity.LiveRoomBeanList;
import com.jk.jkproject.ui.entity.LiveRoomGameGoldInfo;
import com.jk.jkproject.ui.entity.LiveRoomUserLockBean;
import com.jk.jkproject.ui.entity.LiveRoomUserLockList;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.entity.MyMoneyBean;
import com.jk.jkproject.ui.entity.PKBattleBaseInfo;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.jk.jkproject.ui.entity.PushPKStreamInfo;
import com.jk.jkproject.ui.entity.ScrollEvent;
import com.jk.jkproject.ui.entity.ShareBean;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.ui.inter.OnItemClickLitener;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.ui.widget.danmaku.DanMuHelper;
import com.jk.jkproject.ui.widget.danmaku.DanmakuClickListener;
import com.jk.jkproject.ui.widget.danmaku.DanmakuEntity;
import com.jk.jkproject.ui.widget.danmaku.RichMessage;
import com.jk.jkproject.ui.widget.recyclerview.RecyclerItemClickListener;
import com.jk.jkproject.ui.widget.room.KeyboardListenRelativeLayout;
import com.jk.jkproject.ui.widget.room.LiveAnimationQueueActionManager;
import com.jk.jkproject.ui.widget.room.LiveGiftActionManager;
import com.jk.jkproject.ui.widget.room.LiveViewAnnouncement;
import com.jk.jkproject.ui.widget.room.LiveViewEnterRoom;
import com.jk.jkproject.ui.widget.room.LiveViewForGift;
import com.jk.jkproject.ui.widget.room.LiveViewForSvgaGift;
import com.jk.jkproject.ui.widget.room.LiveViewWithIcon;
import com.jk.jkproject.ui.widget.room.OnScrollRecyclerView;
import com.jk.jkproject.ui.widget.room.PkCountdownView;
import com.jk.jkproject.ui.widget.room.PkSmallAnimation;
import com.jk.jkproject.ui.widget.room.PkStartAnimation;
import com.jk.jkproject.ui.widget.room.RollPagerView;
import com.jk.jkproject.ui.widget.room.SeekBarClickRelatelayout;
import com.jk.jkproject.ui.widget.room.SeekBarRelatelayout;
import com.jk.jkproject.utils.AppConfig;
import com.jk.jkproject.utils.AppConfigUrl;
import com.jk.jkproject.utils.CleanDataUtils;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.KeyBoardUtils;
import com.jk.jkproject.utils.LiveRoomAnimationUtils;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.Zego;
import com.jk.lib_egg.EggPopup;
import com.jk.lib_egg.GamePopup;
import com.jk.lib_egg.LoadingPopup;
import com.jk.lib_egg.ResultPopup;
import com.jk.lib_egg.pojo.EggBean;
import com.jk.lib_egg.pojo.GameListBean;
import com.jk.lib_egg.pojo.GameResultBean;
import com.jk.weblib.EggExplainPopup;
import com.miliyo.danmaku.DanMuParentView;
import com.miliyo.danmaku.DanMuView;
import com.miliyo.danmaku.view.IDanMuParent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;
import razerdp.basepopup.BasePopupWindow;

import static com.jk.jkproject.net.im.cores.IMEventType.ACTION_LIVE_ROOM_COLSE_PUSH;
import static com.jk.jkproject.utils.Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_15;

/**
 * @author Zick
 * @params
 * @date 2020/7/9 10:07 AM
 * @desc 直播间
 */
public class LiveRoomFragment extends BFragment implements View.OnClickListener, DanmakuClickListener, Observer, View.OnTouchListener {

    public static String PKId = "";  //pk时房间中送礼物id
    public static Activity mActivity;
    public static boolean isFollow = false;
    public static String UserName;
    public static LiveGiftInfoBean.DataBean giftBean;
    public static boolean IS_HOST = false;
    public static String LIVE_ROOM_ID = "null";
    private static String LIVE_ROOM_CLOSE = "null";

    private static final int LIVE_SHOW_CHAT_FIRST = 1012;

    private static final int LIVE_SHOW_USER_FIRST = 1013;

    private static final int LIVE_SHOW_USER_TIME = 30000;

    private static final int LIVE_UPDATE_SYSTEM_MESSAGE = 1010;
    RelativeLayout rlContent;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.surfaceview)
    TextureView mPusherView;
    @BindView(R.id.iv_wheat_close)
    ImageView ivWheatClose;
    @BindView(R.id.rl_wheat)
    RelativeLayout rlWheat;
    @BindView(R.id.rl_wheat_or_pk)
    RelativeLayout rlWheatOrPk;
    TextView tvWheat;
    CountdownView cvWheatTime;
    LinearLayout llWheatTime;
    TextView tvWheatTime;
    Unbinder unbinder;
    private List<LiveHomeBeanInfo.DataBean> list;
    private static int MESSAGE_TYPE = 291;
    private static LiveRoomFragment fragmetntA;
    LiveViewWithIcon viewAnchorInfo;
    RecyclerView rvOnlineUsers;
    TextView tvLoadCount;
    LinearLayout liveRoomAnchorinfo;
    TextView tvCharisma;
    LinearLayout llLiveCharisma;
    LinearLayout liveGiftTicketLl;
    TextView tvTime;
    RelativeLayout topView;
    CheckBox cbBroadcast;
    EditText etBottomMsg;
    ImageView ivLiveBottomEmo;
    TextView btnMsgSend;
    LinearLayout liveRoomChat;
    FrameLayout flEmoContainer;
    LinearLayout llLiveRoomEmo;
    LinearLayout llLiveRoomBottomFuncEmo;
    OnScrollRecyclerView chatListView;
    TextView etBottom;
    ImageView ivSkinCare;
    ImageView icMail;
    ImageView ivGift;
    ImageView ivShare;
    ImageView ivClose;
    LinearLayout linearOther;
    RelativeLayout liveRoomBottom;
    View viewGotoRoom;
    SeekBarRelatelayout pkSeekBar;
    LiveViewForGift viewGiftCombo2;
    LiveViewForGift viewGiftCombo1;
    View empty;
    PkCountdownView relatePkCountdown;
    PkStartAnimation relatePkAnimation;
    PkSmallAnimation relatePkSmallAnimation;
    DanMuView danmakuMiddle;
    DanMuParentView dpvMiddle;
    TextView tvUserLevel;
    TextView tvUserEnter;
    LinearLayout llUserLevel;
    RelativeLayout relativeRoot;
    RollPagerView taskViewpager;
    RollPagerView taskViewpager1;
    SeekBarClickRelatelayout seekclick;

    KeyboardListenRelativeLayout mRootView;
    @BindView(R.id.iv_bg)
    SimpleDraweeView iv_bg;
    int cnt = 0;
    private boolean isFiveSecond = false, isFiveMinutes = false, isFastWheat = false;
    private ProgressBar pb;
    private int progress;
    private ProgressBar progressBar;

    //svga 动画
    private LiveViewForSvgaGift effectSvga;

    private LiveViewEnterRoom liveViewEnterRoom;
    private LiveViewAnnouncement liveViewAnnouncement;

    private LiveChatRoomAdapter adapter;

    private ArrayList<DanmuBean> chatMsgList;
    private List<View> viewList = null;

    private String cover;

    private int currentPostion;

    private DialogLiveRoomFollow d;
    private DialogLiveRoomWheat mWheatDialog;
    private FragmentManager fm;
    private LiveRoomWheatBaseInfo.DataBean mPKInfo, mPushPkInfo; //pk对方的个人信息


    private List<LiveGiftInfoBean.DataBean> giftDatas = new ArrayList<>();
    private List<LiveGiftInfoBean.DataBean> backpackDatas = new ArrayList<>();

    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message param1Message) {
            @SuppressLint("HandlerLeak") Bundle bundle = (Bundle) param1Message.obj;
            switch (bundle.getInt("method")) {
                case IMEventType.ACTION_LIVE_ROOM_MESSAGE_PUSH:
                    DanmuBean danmuBean = (DanmuBean) bundle.getSerializable("danmuBean");
                    int msgType = danmuBean.getData().getFrom().getMsgType();
                    switch (msgType) {
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_4:
                            showStyAnnmentAnimation();
                            mLiveActionManager.addGift(danmuBean);
                            break;
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_14:
                            DanmakuEntity danmakuEntity = new DanmakuEntity();
                            danmakuEntity.setType(1);
                            danmakuEntity.setName(danmuBean.getData().getFrom().getNickName());
                            if (danmuBean.getData().getFrom().get__plat().isEmpty()) {
                                danmakuEntity.setAvatar("https://q.qlogo.cn/qqapp/100229475/E573B01150734A02F25D8E9C76AFD138/100");
                            } else {
                                danmakuEntity.setAvatar(danmuBean.getData().getFrom().get__plat());
                            }
                            danmakuEntity.setLevel(-1);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(danmuBean.getData().getContent());
                            stringBuilder.append("        ");
                            danmakuEntity.setText(stringBuilder.toString());
                            addDanmaku(danmakuEntity, 1);
                            addMsg(danmuBean);
                            break;
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_3:
                            if (danmuBean.getData().getTo().getToroom().equals(LiveRoomFragment.LIVE_ROOM_ID)) {
                                //                                tyrEnterRoom(danmuBean.getData().getContent(), danmuBean.getData().getFrom().getLevel(), danmuBean.getData().getFrom().getNickName());
                                showEnterRoomAnimation();
                                mLiveActionManager.addGift(danmuBean);
                                addMsg(danmuBean);
                            }
                            break;
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_9:
                            if (danmuBean.getData().getFrom().getRid().equals(SPUtils.getUserId())) {
                                finish(1);
                            } else {
                                addMsg(danmuBean);
                            }
                            break;
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_5:
                            if (danmuBean.getData().getFrom().getRid().equals(SPUtils.getUserId())) {
                                if (roomBeanInfo != null) {
                                    roomBeanInfo.setBanned(0);
                                }
                            }
                            addMsg(danmuBean);
                            break;
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_6:
                            if (danmuBean.getData().getFrom().getRid().equals(SPUtils.getUserId())) {
                                if (roomBeanInfo != null) {
                                    roomBeanInfo.setBanned(-2);
                                }
                            }
                            addMsg(danmuBean);
                            break;
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_37:
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_38:
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_39:
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_40:
                        case Constants.MsgType.TYPE_LIVE_CHAT_SYSTEM_42:
                            if (!isHost) {
                                addMsg(danmuBean);
                            }
                            break;
                        default:
                            addMsg(danmuBean);
                            break;
                    }
                    break;
                case IMEventType.ACTION_ENTER_LIVE_ROOM_COUNT:
                    if (!LIVE_ROOM_ID.equals("null") && LIVE_ROOM_ID.equals(bundle.getString("roomId"))) {
                        tvLoadCount.setText(bundle.getInt("count") + "");
                        if (bundle.getInt("count") <= 3) {
                            updateLockList();
                        }
                    }
                    break;
                case IMEventType.ACTION_ENTER_LIVE_ROOM:
                    EventBus.getDefault().post(new ScrollEvent(false, ""));
                    switch (bundle.getInt("type")) {
                        case 0:
                            Intent intent = new Intent(getContext(), UserLiveActivity.class);
                            intent.putExtra("roomId", LIVE_ROOM_ID);
                            intent.putExtra("type", 1);
                            startActivity(intent);
                            getActivity().finish();
                            break;
                        case 1:
                            JoinRoomBeanInfo liveRoomFragment = (JoinRoomBeanInfo) bundle.getSerializable("roomBeanInfo");
                            liveRoomData(liveRoomFragment);
                            break;
                        case 2://你已被主播拉黑，加入直播间失败
                            ToastUtils.showShortToast("你已被主播拉黑，加入直播间失败");
                            getActivity().finish();
                            break;
                        case 3:
                            ToastUtils.showShortToast("当前直播间不存在");
                            getActivity().finish();
                            break;
                    }
                    break;
                case IMEventType.ACTION_LIVE_ROOM_GIFT_PUSH:
                    if (mLiveGiftActionManager != null) {
                        LiveGiftInfoBean.DataBean giftBean = (LiveGiftInfoBean.DataBean) bundle.getParcelable("giftBean");
                        if (giftBean.getType() == 1) {
                            showSvgaAnimation();
                        }
                        mLiveGiftActionManager.addGift(giftBean);
                    }
                    break;
                case IMEventType.ACTION_LIVE_ROOM_COLSE:
                    if (bundle.getInt("code") == 200) {
                        if (isHost && getActivity() != null) {
                            Intent intent = null;
                            if (null != message3) {
                                switch (message3.getType()) {
                                    case 2:
                                        intent = new Intent(getContext(), MyPremiereActivity.class);
                                        intent.putExtra("message", message3);
                                        startActivity(intent);
                                        getActivity().finish();
                                        break;
                                    case 3:
                                        AppApplication.getInstance().logOut(getContext());
                                        intent = new Intent(getContext(), LoginActivity.class);
                                        intent.putExtra("message", message3);
                                        startActivity(intent);
                                        getActivity().finish();
                                        break;
                                }
                            } else {
                                intent = new Intent(getContext(), SowingLiveActivity.class);
                                intent.putExtra("roomId", LIVE_ROOM_ID);
                                intent.putExtra("time", tvTime.getText().toString().trim());
                                startActivity(intent);
                                getActivity().finish();
                            }
                        }
                    }
                    break;
                case ACTION_LIVE_ROOM_COLSE_PUSH:
                    LiveClosePushInfo info = (LiveClosePushInfo) bundle.getSerializable("closeInfo");
                    if (LIVE_ROOM_ID.equals(info.getRoomId())) {
                        Intent intent = new Intent(getContext(), UserLiveActivity.class);
                        intent.putExtra("roomId", LIVE_ROOM_ID);
                        intent.putExtra("isAttention", isFollow);
                        intent.putExtra("type", 0);
                        intent.putExtra("info", info);
                        startActivity(intent);
                        getActivity().finish();
                        //                        stopPlay(info);
                    }
                    break;
                case IMEventType.ACTION_CHAT_MSG_PUSH:
                    com.jk.jkproject.ui.chat.Message message = (com.jk.jkproject.ui.chat.Message) bundle.getSerializable("mMessage");
                    if (mChatMsgDialog != null && mChatMsgDialog.isShowing()) {
                        mChatMsgDialog.setMsgPush(message);
                    }
                    if (mChatDialog != null && mChatDialog.isShowing()) {
                        mChatDialog.setMessageType(message);
                    }
                    break;
                case IMEventType.ACTION_CHAT_MSG:
                    com.jk.jkproject.ui.chat.Message message1 = (com.jk.jkproject.ui.chat.Message) bundle.getSerializable("message");
                    if (mChatMsgDialog != null && mChatMsgDialog.isShowing()) {
                        mChatMsgDialog.setMsgSend(message1);
                    }
                    break;
                case IMEventType.ACTION_SYSTEM_NOTI_MSG_PUSH:
                    SystemNotiBeanInfo.DataBean message2 = (SystemNotiBeanInfo.DataBean) bundle.getSerializable("bean");
                    if (mStystemMsg != null && mStystemMsg.isShowing()) {
                        mStystemMsg.setData(message2);
                    }
                    if (mChatDialog != null && mChatDialog.isShowing()) {
                        mChatDialog.setSystem(message2);
                    }
                case IMEventType.ACTION_WARNING_NOTI_PUSH:
                    if (SPUtils.getIsHost()) {
                        switch (bundle.getInt("type")) {
                            case 1:
                                setShowWarningDialog(message3);
                                break;
                            case 2:
                            case 3:
                                finish(2);
                                break;
                        }
                    }
                    break;
                case IMEventType.ACTION_ANCHOR_HOT_RANKING:
                    String roomId = bundle.getString("roomId");
                    int ranking = bundle.getInt("ranking");
                    if (roomId.equals(LIVE_ROOM_ID)) {
                        if (ranking == -1) {
                            tvCharisma.setText("日榜 99+ >");
                        } else if (ranking > 0) {
                            tvCharisma.setText("日榜 " + ranking + ">");
                        } else if (ranking == -2) {
                            tvCharisma.setText("未上榜 >");
                        }
                    }
                    break;
                case IMEventType.ACTION_LINK_USER_CALL:
                    if (mWheatDialog != null && mWheatDialog.isShowing()) {
                        mWheatDialog.setWheatData(2, roomBeanInfo.getUserId(), LIVE_ROOM_ID);
                        showWheatTime(1);
                    }
                    break;
                case IMEventType.ACTION_LINK_HOST_RES_CALL:
                    tvWheat.setVisibility(View.VISIBLE);
                    if (ivWheatClose.getVisibility() == View.GONE) {
                        ivWheatClose.setVisibility(View.VISIBLE);
                    }
                    switch (bundle.getInt("type")) {
                        case 1: //同意
                            rlWheat.setVisibility(View.VISIBLE);
                            if (mWheatDialog != null && mWheatDialog.isShowing()) {
                                mWheatDialog.dismiss();
                            }
                            Zego.instance().startPush(SPUtils.getUserId(), mPusherView);
                            if (llWheatTime != null) {
                                cvWheatTime.stop();
                                cvWheatTime.destroyDrawingCache();
                                llWheatTime.setVisibility(View.GONE);
                            }
                            wheatType = 1;
                            tvWheat.setText("连麦中...");
                            tvWheat.setVisibility(View.VISIBLE);
                            tvWheat.setBackgroundResource(R.drawable.bg_wheat_btn);
                            tvWheat.setEnabled(false);
                            break;
                        case 2: //拒绝
                            ToastUtils.showLongToast("主播暂时不方便接受连麦哦！");
                            wheatType = 1;
                            if (llWheatTime != null) {
                                cvWheatTime.stop();
                                cvWheatTime.destroyDrawingCache();
                                llWheatTime.setVisibility(View.GONE);
                            }
                            tvWheat.setText("申请连麦");
                            tvWheat.setBackgroundResource(R.drawable.bg_wheat_btn);
                            ivWheatClose.setVisibility(View.GONE);
                            if (mWheatDialog != null && mWheatDialog.isShowing()) {
                                mWheatDialog.dismiss();
                            }
                            break;
                    }
                    break;
                case IMEventType.ACTION_LINK_HOST_USER_CALL_PUSH:
                    LiveRoomWheatBaseInfo.DataBean dataBean = (LiveRoomWheatBaseInfo.DataBean) bundle.getSerializable("info");
                    wheatBeans.add(dataBean);
                    updateWheatView(1);
                    //                    ivWheatClose.setVisibility(View.GONE);
                    if (mWheatListDialog != null && mWheatListDialog.isShowing()) {
                        mWheatListDialog.setBeanInfo(dataBean);
                    }
                    break;
                case IMEventType.ACTION_LINK_CANCEL_CALL_PUSH:
                case IMEventType.ACTION_LINK_TIMEOUT_CALL_PUSH:
                    String userId = bundle.getString("userId");
                    for (int i = 0; i < wheatBeans.size(); i++) {
                        if (wheatBeans.get(i).getUserId().equals(userId)) {
                            wheatBeans.remove(i);
                        }
                    }
                    updateWheatView(2);
                    if (mWheatListDialog != null && mWheatListDialog.isShowing()) {
                        mWheatListDialog.setUpdataUserId(userId);
                    }
                    break;
                case IMEventType.ACTION_LINK_ANSWERL:
                    if (bundle.getString("userId").equals("-1")) {
                        wheatBeans.clear();
                        tvWheat.setVisibility(View.GONE);
                    } else {
                        updateWheatView(2);
                        for (int i = 0; i < wheatBeans.size(); i++) {
                            if (bundle.getString("userId").equals(wheatBeans.get(i).getUserId())) {
                                wheatBeans.remove(i);
                            }
                        }
                    }
                    break;
                case IMEventType.ACTION_LINK_WHEAT_STREAM_PUSH:
                    String streamId = bundle.getString("streamId");
                    String anchorId = bundle.getString("anchorId");
                    String userId1 = bundle.getString("userId");
                    if (!isHost && !SPUtils.getUserId().equals(userId1)) {
                        rlWheat.setVisibility(View.VISIBLE);
                        ivWheatClose.setVisibility(View.GONE);
                        tvWheat.setEnabled(true);
                        tvWheat.setText("申请连麦");
                        Zego.instance().initWheatPull(false, streamId, mPusherView);
                    } else {
                        rlWheat.setVisibility(View.VISIBLE);
                    }
                    if (SPUtils.getUserId().equals(bundle.getString("userId"))) {
                        EventBus.getDefault().post(new ScrollEvent(true, ""));
                    } else {
                        EventBus.getDefault().post(new ScrollEvent(false, ""));
                    }
                    ivHostPK.setEnabled(false);
                    ivHostPK.setImageResource(R.mipmap.iv_host_un_pk_);
                    break;
                case IMEventType.ACTION_LINK_WHEAT_HANGUP_PUSH:
                    String hangUp_StreamId = bundle.getString("streamId");
                    if (hangUp_StreamId.equals(SPUtils.getUserId())) {
                        Zego.instance().getEngine().stopPreview();
                        Zego.instance().getEngine().stopPublishingStream();
                        EventBus.getDefault().post(new ScrollEvent(false, ""));
                        tvWheat.setEnabled(true);
                        tvWheat.setText("申请连麦");
                        if (isFastWheat) {
                            isFastWheat = false;
                            EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_RECEIVE_PK, mPKInfo.getUserId()));
                            setVisibleSeekBar(mPKInfo);
                            //将所有连麦请求都拒绝掉
                            setRefusedWheat();
                        }
                    } else {
                        Zego.instance().getEngine().stopPlayingStream(hangUp_StreamId);
                    }
                    rlWheat.setVisibility(View.GONE);
                    ivHostPK.setEnabled(true);
                    ivHostPK.setImageResource(R.mipmap.icon_live_host_pk);
                    break;
                case IMEventType.ACTION_LINK_WHEAT_HANGUP_PUSH_2:
                    String hangUp_StreamId2 = bundle.getString("streamId");
                    if (hangUp_StreamId2.equals(SPUtils.getUserId())) {
                        Zego.instance().getEngine().stopPreview();
                        Zego.instance().getEngine().stopPublishingStream();
                        EventBus.getDefault().post(new ScrollEvent(false, ""));
                        tvWheat.setEnabled(true);
                        tvWheat.setText("申请连麦");
                    } else {
                        Zego.instance().getEngine().stopPlayingStream(hangUp_StreamId2);
                    }
                    ivHostPK.setEnabled(true);
                    ivHostPK.setImageResource(R.mipmap.icon_live_host_pk);
                    break;
                case IMEventType.ACTION_LINK_Friend_PK_INVITATION: //邀请好友PK回调
                    //                    showPKInvitation(2, mPKInfo);
                    showPKInvitation(3, mPKInfo);
                    showWheatTime(2);
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_RECEIVE_PUSH://推送给对方好友PK申请
                    mPKInfo = (LiveRoomWheatBaseInfo.DataBean) bundle.getSerializable("info");
                    showPKFriendInvitation(1, mPKInfo);
                    showWheatTime(3);
                    break;
                case IMEventType.ACTION_LINK_PK_ACCEPTRECURPK_PUSH://再来一局推送给对方的监听
                    mPKInfo = (LiveRoomWheatBaseInfo.DataBean) bundle.getSerializable("info");
                    showPKFriendInvitation(4, mPKInfo);
                    showWheatTime(5);
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_ACCEPTRANDOMPK_PUSH: //推送给匹配到的主播
                    mPKInfo = (LiveRoomWheatBaseInfo.DataBean) bundle.getSerializable("info");
                    showPKFriendInvitation(3, mPKInfo);
                    showWheatTime(3);
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_SENDRANDOMPK_PUSH://推给发起方的主播
                    mPKInfo = (LiveRoomWheatBaseInfo.DataBean) bundle.getSerializable("info");
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_STREAMPK_PUSH: //用户端
                    PushPKStreamInfo pkStreamInfo = (PushPKStreamInfo) bundle.getSerializable("pkStreamInfo");
                    SPUtils.setPKID(pkStreamInfo.getPKId());
                    if (!isHost) {
                        if (pkSeekBar.getVisibility() != View.VISIBLE) {
                            setVisibleSeekBar(mPKInfo);
                            EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_STREAMPK_PK, pkStreamInfo.getMergeStreamUrl()));
                            tvWheat.setVisibility(View.GONE);
                        } else {
                            pkSeekBar.relase();
                        }
                    }
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_CONSENTPK_PUSH:  //主播
                    PushPKStreamInfo streamInfo = (PushPKStreamInfo) bundle.getSerializable("pkStreamInfo");
                    //                    PKId = pkStreamInfo.getPKId();
                    SPUtils.setPKID(streamInfo.getPKId());
                    //通知主播合流
                    if (pkSeekBar.getVisibility() != View.VISIBLE) {
                        EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_SEND_PK, streamInfo.getTagStreamId()));
                        setVisibleSeekBar(mPKInfo);
                    } else { //再来一局
                        pkSeekBar.relase();
                        relatePkCountdown.setToGameVis(false);
                        IMLiveRoomManager.instance().sendAddMergeStream("", LIVE_ROOM_ID);
                    }
                    isPK = true;
                    if (mPkInvitationDialog != null && mPkInvitationDialog.isShowing()) {
                        mPkInvitationDialog.dismiss();
                    }
                    cvWheatTime.stop();
                    llWheatTime.setVisibility(View.GONE);
                    tvWheat.setVisibility(View.GONE);
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_ADDMERGESTREAM: //合流地址上传成功
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_TIME_OUT_PUSH: //等待好友超时
                    tvWheatTime.setVisibility(View.GONE);
                    tvWheat.setVisibility(View.GONE);
                    llWheatTime.setVisibility(View.GONE);
                    showPKFIFailure(2, mPKInfo);
                    hideDialog();
                    break;
                case IMEventType.ACTION_LINK_FRIEND_PK_PKREJECT_PUSH: //好友pk响应拒绝
                    tvWheatTime.setVisibility(View.GONE);
                    tvWheat.setVisibility(View.GONE);
                    llWheatTime.setVisibility(View.GONE);
                    showPKFIFailure(1, mPKInfo);
                    hideDialog();
                    break;
                case IMEventType.ACTION_LINK_PK_RECURPKREJECT_PUSH: //再来一局拒绝
                case IMEventType.ACTION_LINK_PK_PKRECURTIMEOUT_PUSH: //再来一局超时
                    ToastUtils.showLongToast("对方拒绝再来一局的请求");
                    hideDialog();
                    break;
                case IMEventType.ACTION_LINK_PK_PKRANDOMTIMEOUT_PUSH:
                    ToastUtils.showLongToast("随机PK请求超时");
                    hideDialog();
                    break;
                case IMEventType.ACTION_LINK_RANDOM_PK_INVITATION: // 计时开始
                    showWheatTime(4);
                    break;
                case IMEventType.ACTION_LINK_PK_PKHANGUP: //主播强制挂断回调
                    break;
                case IMEventType.ACTION_LINK_PK_PKCLOSE_PUSH:
                    SPUtils.setPKID("");
                    int type = bundle.getInt("type");
                    ivHostPK.setEnabled(true);
                    ivHostPK.setImageResource(R.mipmap.icon_live_host_pk);
                    if (LIVE_ROOM_ID.equals(bundle.getString("roomId"))) {
                        switch (type) {
                            case 1:
                            case 4:
                                finish(2);
                                break;
                            case 2:
                            case 3:
                            case 5:
                                setPKEnd(1);
                                break;
                        }
                    } else {
                        setPKEnd(2);
                    }
                    if (!isHost) {
                        tvWheat.setVisibility(View.VISIBLE);
                        tvWheat.setBackgroundResource(R.drawable.bg_wheat_btn);
                        Drawable drawable = getResources().getDrawable(R.mipmap.icon_wheat);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tvWheat.setCompoundDrawables(drawable, null, null, null);
                    }
                    break;
                case IMEventType.ACTION_LINK_PK_PKRESPONSE:
                    //                    setVisibleSeekBar(mPKInfo);
                    break;
                case IMEventType.ACTION_LINK_PK_PKANCHORINFO_PUSH:
                    mPKInfo = (LiveRoomWheatBaseInfo.DataBean) bundle.getSerializable("info");
                    IMLiveRoomManager.instance().sendPKResidueTime(SPUtils.getPKID());
                    //                    pkSeekBar.setInitData();
                    pkSeekBar.setData(mPKInfo);
                    pkSeekBar.startAnim();
                    break;
                case IMEventType.ACTION_LINK_PK_PKRESULT_PUSH: //PK胜利数据推送
                    pkSeekBar.setOwnWinData((LiveRoomWheatBaseInfo.DataBean) bundle.getSerializable("info"));
                    break;
                case IMEventType.ACTION_LINK_PK_ACCEPTRECURPK: //隐藏再来一局
                    //                    pkSeekBar.relase();
                    relatePkCountdown.setToGameVis(false);
                    break;
                case IMEventType.ACTION_LINK_GAME_RECONNECTIONHANDLER:
                    LiveRoomGameGoldInfo goldInfo = (LiveRoomGameGoldInfo) bundle.getSerializable("info");
                    if (goldInfo != null) {
                        if (mGameGoldDialog != null) {
                            if (mGameGoldDialog.isShowing()) {
                                mGameGoldDialog.setData(goldInfo);
                            } else {
                                IMLiveRoomManager.instance().sendGameGoldClose(SPUtils.getUserId());
                            }
                            if (goldInfo.getIsFirstOpen() == 1) {
                                showGameGoldHintDialog();
                            }
                        }
                    }
                    break;
                case IMEventType.ACTION_LINK_GAME_GOLD_WAIT: //金币游戏阶段时间推送
                    if (mGameGoldDialog != null && mGameGoldDialog.isShowing()) {
                        mGameGoldDialog.resetTime(bundle.getString("stage"), bundle.getInt("time"));
                    }
                    break;
                case IMEventType.ACTION_LINK_GAME_GOLD_BET: //金币游戏下注回调
                    if (mGameGoldDialog != null && mGameGoldDialog.isShowing()) {
                        mGameGoldDialog.setBetData((LiveRoomGameGoldInfo) bundle.getSerializable("info"));
                    }
                    break;
                case IMEventType.ACTION_LINK_GAME_GOLD_TOTAL_COUNT: //金币游戏总下注数量
                    if (mGameGoldDialog != null && mGameGoldDialog.isShowing()) {
                        mGameGoldDialog.setTotalBat((LiveRoomGameGoldInfo) bundle.getSerializable("info"));
                    }
                    break;
                case IMEventType.ACTION_LINK_GAME_GOLD_RESULTS_PUSH: //金币游戏结果推送
                    if (mGameGoldDialog != null && mGameGoldDialog.isShowing()) {
                        mGameGoldDialog.setResults(bundle.getInt("win"), bundle.getInt("num"), bundle.getInt("type"), bundle.getInt("money"));
                    }
                    break;
                case IMEventType.ACTION_LINK_PK_BATTLEFORCE_PUSH:
                    PKBattleBaseInfo baseInfo = (PKBattleBaseInfo) bundle.getSerializable("info");
                    LogUtils.e("======", baseInfo.toString());
                    pkSeekBar.setSendGiftHeader(baseInfo, LIVE_ROOM_ID);
                    break;
                case IMEventType.ACTION_LINK_PK_PKRESIDUETIME:
                    int pkTime = bundle.getInt("time");
                    relatePkCountdown.setVisibility(View.VISIBLE);
                    ivHostPK.setEnabled(false);
                    ivHostPK.setImageResource(R.mipmap.iv_host_un_pk_);
                    //                    relatePkCountdown.resetTime(1);
                    if (pkTime > 480) {
                        relatePkCountdown.resetTime(1, pkTime - 480);
                        pkSeekBar.relase();
                    } else if (pkTime > 180) {
                        //                        pkSeekBar.setInitData();
                        relatePkCountdown.resetTime(2, pkTime - 180);
                        pkSeekBar.setPkView();
                    } else {
                        relatePkCountdown.resetTime(3, pkTime);
                        pkSeekBar.setPkView();
                    }
                    break;
                case IMEventType.ACTION_LINK_RANDOM_PK_CANCEL:
                    if (llWheatTime != null) {
                        cvWheatTime.stop();
                        cvWheatTime.destroyDrawingCache();
                        llWheatTime.setVisibility(View.GONE);
                    }
                    break;
                case IMEventType.ACTION_IS_CLEAR_DATA: //socket掉线
                    if (getActivity() != null) {
                        ToastUtils.showShortToast("网络连接失败");
                        getActivity().finish();
                    }
                    break;
                case IMEventType.ACTION_LINK_ANCHORROOMMESSAGE_PUSH:
                    if (LIVE_ROOM_ID.equals(bundle.getString("roomId"))) {
                        viewAnchorInfo.setLevel(bundle.getInt("grade"), bundle.getLong("experience"));
                    }
                    break;
                case IMEventType.EGG:
                    LogUtils.dTag("fansangg", "setBroadcast" + bundle.getString("gift"));
                    if (eggPopup != null && eggPopup.isShowing()) {
                        eggPopup.setBroadcast(bundle.getString("username"), bundle.getString("gift"), String.valueOf(bundle.getInt("amont")));
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void updateLockList() {
        AppApis.getLiveRoomLockList(0, LIVE_ROOM_ID, this);
    }


    private boolean isFromChat = false;

    private boolean isGlobalDanmu;

    private boolean isHost;

    private boolean isJoinRoom = false;

    private boolean isKeyboardCloseFromClickEmo = false;

    private boolean isKeyboardVisible = false;
    private ArrayList<LiveHomeBeanInfo.DataBean> liveCardInfos;

    private DialogLiveRoomRankList liveRoomRankDialog;
    private GamePopup gamePopup;
    private EggPopup eggPopup;
    private LoadingPopup loadingPopup;

    private String live_chat_room_id;

    private String live_id;
    private DialogLockCount lockCountDialog;

    private LiveRoomAnimationUtils mAnimUtils;

    private Animation mAnimation;

    private DialogLiveRoomLackBalance mBalanceDialog;

    private DanMuHelper mDanMuHelper;

    private LiveGiftDialog mGiftDialog;

    private DialogHostOperation mHostOperationDialog;

    private LiveRoomBeanList.DataBean mLiveCardInfo;

    private String mOid;

    private LiveOnlineUsersAdapter mOnlineUsersAdapter;

    private DialogLiveRoomShare mShareDialog;

    private DialogLiveRoomSkinCare mSkinCareDialog;

    private boolean needAtNickName;

    private String nickName;

    private int position;

    private List<AppConfigUrl.AppReplacesBean> replaceList;

    private DialogLiveRoomUserReport reportDialog;

    private JoinRoomBeanInfo roomBeanInfo;

    private int shared;

    private DialogLiveRoomUserInfo userDialog;

    private DialogLiveRoomUserHomePage userHomePageDialog;
    private LiveMsgHandler liveHandler;
    private LiveGiftActionManager mLiveGiftActionManager;
    private LiveAnimationQueueActionManager mLiveActionManager;
    private Timer timer;
    private LiveFoundRecommendAdapter mAdapter;
    private DialogChat mChatDialog;
    private DialogLiveMessage mChatMsgDialog;
    private DialogSystemMessage mStystemMsg;
    private View vEmptyView;
    private LiveRoomViewPageAdapter liveRoomAdapter;
    private ViewPager vRoomContentPager;
    private View vRoomContent;
    private DialogNotices warningNoti;
    private SystemNotiBeanInfo.DataBean message3;
    private ShareBean shareBean;
    private int amount;
    private ImageView ivHostPK, ivGameGold;
    private DialogLiveRoomWheatList mWheatListDialog;
    private List<LiveRoomWheatBaseInfo.DataBean> wheatBeans = new ArrayList<>();
    private String mWheat_UserId;
    private int wheatType = 1;
    private DialogLiveRoomPKType mPkTypeDialog;
    private DialogLiveRoomPKInvitation mPkInvitationDialog;
    private DialogLiveRoomPKFriendInvitation mPkFriendInvitationDialog;
    private DialogLiveRoomPKFriendInFailure mPkFriendInFailureDialog;
    private DialogLiveRoomPKDisconnect mPkDisconnectDialog;
    private boolean isPK = false;
    private DialogLiveRoomPKToGame mPKToGameDialog;
    private DialogLiveRoomPKList mPKListDialog;
    private DialogLiveRoomPunishmentClose mPKPunishClose;
    private int type = 1; //1.连麦 2.好友 4.随机
    private DialogLiveRoomPkContributionList mPKContributionDialog;
    private DialogLiveRoomGameGold mGameGoldDialog;
    private DialogLiveRoomGameGoldHint mGameGoldHintDialog;

    private void addDanmaku(DanmakuEntity paramDanmakuEntity, int paramInt) {
        if (this.mDanMuHelper != null) {
            if (paramDanmakuEntity.getType() != 1) {
                if (paramDanmakuEntity.getText() != null)
                    paramDanmakuEntity.setText(rePlace(paramDanmakuEntity.getText()));
                if (paramDanmakuEntity.getRichText() != null) {
                    ArrayList<RichMessage> arrayList = paramDanmakuEntity.getRichText();
                    for (paramInt = 0; paramInt < arrayList.size(); paramInt++)
                        ((RichMessage) arrayList.get(paramInt)).setContent(rePlace(((RichMessage) arrayList.get(paramInt)).getContent()));
                    paramDanmakuEntity.setRichText(arrayList);
                }
            }
            this.mDanMuHelper.addDanMu(paramDanmakuEntity, false);
        }
    }

    private void addMsg(DanmuBean paramDanmuBean) {
        if (paramDanmuBean != null && chatMsgList != null && adapter != null) {
            checkSizeForList();
            if (chatMsgList != null) {
                chatMsgList.add(paramDanmuBean);
            }
            if (liveHandler != null) {
                liveHandler.sendEmptyMessage(LIVE_SHOW_CHAT_FIRST);
            }
        } else {
            if (chatMsgList != null) {
                chatMsgList.add(paramDanmuBean);
            }
        }
        adapter.notifyDataSetChanged();
        scrollToBottomListItem();
    }

    private void addRoomPrompt() {
        DanmuBean danmuBean = new DanmuBean();
        DanmuBean.DataBean dataBean = new DanmuBean.DataBean();
        DanmuBean.DataBean.FromBean fromBean = new DanmuBean.DataBean.FromBean();
        DanmuBean.DataBean.ToBean toBean = new DanmuBean.DataBean.ToBean();
        fromBean.setRid("-100");
        fromBean.setMsgId("-100");
        fromBean.setMsgType(TYPE_LIVE_CHAT_SYSTEM_15);
        fromBean.setTime("10000");
        fromBean.set__plat("");
        fromBean.setLevel("");
        fromBean.setNickName("");
        dataBean.setFrom(fromBean);
        dataBean.setContent("直播提示：锦阔电竞倡导绿色文明直播，封面和直播内容涉及政治、色情、暴力、吸烟、赌博等违法不良信息将被封停账号。文明直播，从我做起。【网警24小时在线巡查】");
        toBean.setToroom("");
        dataBean.setTo(toBean);
        danmuBean.setData(dataBean);
        chatMsgList.add(danmuBean);
    }

    private void exitRoom(int type) {
        if (!LIVE_ROOM_ID.equals("null")) {
            IMLiveRoomManager.instance().exitRoom(LIVE_ROOM_ID, type);
        }
    }

    int count = 0;
    boolean isClose = false;

    private void finish(int paramInt) {
        if (paramInt == 1 && getActivity() != null) {
            if (ivWheatClose.getVisibility() == View.VISIBLE && roomBeanInfo != null) {
                IMLiveRoomManager.instance().hostDropCallWheat(SPUtils.getUserId(), LIVE_ROOM_ID, roomBeanInfo.getUserId(), "link.HangupHandler");
            }
            if (null != roomBeanInfo) {
                exitRoom(paramInt);
            }
            getActivity().finish();
        } else if (paramInt == 2 && getActivity() != null) {
            if (mWheat_UserId != null && ivWheatClose.getVisibility() == View.VISIBLE && roomBeanInfo != null) {
                IMLiveRoomManager.instance().hostDropCallWheat(mWheat_UserId, LIVE_ROOM_ID, roomBeanInfo.getUserId(), "link.HangupHandler");
            }
            exitRoom(paramInt);
        }
    }

    private void hideDialog() {
        if (mPkInvitationDialog != null && mPkInvitationDialog.isShowing()) {
            mPkInvitationDialog.dismiss();
        }
    }

    private void hideChatInput() {
        if (mActivity != null && etBottomMsg != null) {
            KeyBoardUtils.closeKeybord((Context) mActivity, etBottomMsg);
        }
        mAnimUtils.startAnimtion(getActivity(), topView, R.anim.anim_translate_alpha_top_show, 150, View.VISIBLE);
        if (llLiveRoomEmo != null) {
            llLiveRoomEmo.setVisibility(View.GONE);
        }
    }

    private void initChatList() {
        chatMsgList = new ArrayList<>();
        adapter = new LiveChatRoomAdapter((Context) getActivity(), chatMsgList, new OnItemClickLitener() {
            public void onItemClick() {
                keyboardVisibleChanged(false);
            }

            public void onItemLongCLick(String param1String, boolean param1Boolean) {
                //                keyboardVisibleChangedAtTa(param1String, param1Boolean);
            }

            public void onItemNameClick(String param1String) {
                keyboardVisibleChanged(false);
                setUserInfoDialog(param1String, 2);
            }
        });
        //        chatListView.getParent().getParent().requestDisallowInterceptTouchEvent(true);
        chatListView.setNestedScrollingEnabled(true);
        ((SimpleItemAnimator) this.chatListView.getItemAnimator()).setSupportsChangeAnimations(false);
        chatListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatListView.setAdapter(adapter);
    }

    private void initDanMu() {
        this.mDanMuHelper = new DanMuHelper((Context) mActivity);
        this.mDanMuHelper.setDanmakuClickListener(this);
        this.danmakuMiddle.prepare();
        this.mDanMuHelper.add((IDanMuParent) this.danmakuMiddle);
    }

    //连击礼物展示
    private void initGiftView() {
        mLiveGiftActionManager = new LiveGiftActionManager();
        mLiveGiftActionManager.addComboGiftAnimListener(viewGiftCombo1);
        mLiveGiftActionManager.addComboGiftAnimListener(viewGiftCombo2);
        viewGiftCombo1.setLiveGiftActionListener(mLiveGiftActionManager);
        viewGiftCombo2.setLiveGiftActionListener(mLiveGiftActionManager);

        mLiveActionManager = new LiveAnimationQueueActionManager();
    }


    /**
     * 显示svga动画
     */
    private void showSvgaAnimation() {
        if (effectSvga == null) {
            ViewStub stub = (ViewStub) getRootView().findViewById(R.id.layout_svga_layout);
            if (stub != null && mLiveGiftActionManager != null) {
                stub.inflate();
                effectSvga = (LiveViewForSvgaGift) getRootView().findViewById(R.id.livegift_svga);
                effectSvga.setVisibility(View.VISIBLE);
                mLiveGiftActionManager.addOtherGiftAnimListener(effectSvga);
                effectSvga.setLiveGiftActionListener(mLiveGiftActionManager);
            }
        }
    }

    /**
     * 显示土豪进入直播间动画
     */
    private void showEnterRoomAnimation() {
        if (liveViewEnterRoom == null) {
            ViewStub stub = (ViewStub) findViewInViewPageById(R.id.layout_enter_room);
            if (stub != null && mLiveActionManager != null) {
                stub.inflate();
                LogUtils.e("进入直播间");
                liveViewEnterRoom = (LiveViewEnterRoom) findViewInViewPageById(R.id.enter_room);
                liveViewEnterRoom.setVisibility(View.VISIBLE);
                mLiveActionManager.addComboGiftAnimListener(liveViewEnterRoom);
                liveViewEnterRoom.setAnimaActionListener(mLiveActionManager);
            }
        }
    }


    /**
     * 系统公告动画
     */
    private void showStyAnnmentAnimation() {
        if (liveViewAnnouncement == null) {
            ViewStub stub = (ViewStub) findViewInViewPageById(R.id.layout_stytem_ann);
            if (stub != null && mLiveActionManager != null) {
                stub.inflate();
                liveViewAnnouncement = (LiveViewAnnouncement) findViewInViewPageById(R.id.announce);
                liveViewAnnouncement.setVisibility(View.VISIBLE);
                mLiveActionManager.addComboNativeGiftAnimListener(liveViewAnnouncement);
                liveViewAnnouncement.setAnimaActionListener(mLiveActionManager);
            }
        }
    }

    private void initOnLineUserList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvOnlineUsers.setLayoutManager(linearLayoutManager);
        rvOnlineUsers.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), rvOnlineUsers, new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemClick(View param1View, int param1Int) {
                if (mOnlineUsersAdapter != null) {
                    LiveRoomUserLockBean liveRoomUserLockBean = mOnlineUsersAdapter.getItem(param1Int);
                    if (liveRoomUserLockBean != null) {
                        setUserInfoDialog(liveRoomUserLockBean.getUserId(), 2);
                    }
                }
            }

            public void onItemLongClick(View param1View, int param1Int) {
            }
        }));
    }

    private void liveRoomData(final JoinRoomBeanInfo roomBeanInfo) {
        this.roomBeanInfo = roomBeanInfo;
        if (roomBeanInfo.getPlayUrl().isEmpty()) {
            finish(1);
        }
        isJoinRoom = true;
        etBottom.setEnabled(true);
        roomBeanInfo.setRoomId(LIVE_ROOM_ID);
        if (roomBeanInfo.getGameState() == 1 && !isHost) {
            ivGameGold.setVisibility(View.VISIBLE);
        } else {
            ivGameGold.setVisibility(View.GONE);
        }

        if (!isHost) {
            if (!roomBeanInfo.getStreamId().equals("-1")) {  //连麦
                rlWheat.setVisibility(View.VISIBLE);
                ivWheatClose.setVisibility(View.GONE);
                EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_PLAY_URL, roomBeanInfo));
                Zego.instance().initWheatPull(false, roomBeanInfo.getStreamId(), mPusherView);
            } else if (!roomBeanInfo.getStreamPkId().equals("-1") && !roomBeanInfo.getPkId().equals("-1")) {
                SPUtils.setPKID(roomBeanInfo.getPkId());
                tvWheat.setVisibility(View.GONE);
                rlWheat.setVisibility(View.VISIBLE);
                ivWheatClose.setVisibility(View.GONE);
                EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_STREAMPK_PK, roomBeanInfo.getStreamPkId()));
                setVisibleSeekBar(null);
                IMLiveRoomManager.instance().sendPKResidueTime(SPUtils.getPKID());
            } else {
                EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_PLAY_URL, roomBeanInfo));
                pkSeekBar.setVisibility(View.GONE);
                relatePkCountdown.setVisibility(View.GONE);
            }
        }
        AppApis.getLiveRoomLockList(0, LIVE_ROOM_ID, (ResponseListener) this);
        tvLoadCount.setText(roomBeanInfo.getCount() + "");
        viewAnchorInfo.setLiveRoomData(roomBeanInfo);

        if (roomBeanInfo.getRanking() == -1) {
            tvCharisma.setText("日榜 99+ >");
        } else if (roomBeanInfo.getRanking() > 0) {
            tvCharisma.setText("日榜 " + roomBeanInfo.getRanking() + ">");
        } else if (roomBeanInfo.getRanking() == -2) {
            tvCharisma.setText("未上榜 >");
        }
        tvId.setText("ID" + roomBeanInfo.getUserId());
        if (roomBeanInfo.getIsAttention() == 1) {
            isFollow = true;
        }
        viewAnchorInfo.setOnClickListener((param1String, param1Boolean) -> {
            if (!param1Boolean) {
                setFollow(1, param1String);
            } else {
                setUserInfoDialog(roomBeanInfo.getUserId(), 1);
            }
        });
        if (!isHost) {
            if (roomBeanInfo.getIsAttention() != 1) {
                if (liveHandler != null) {
                    liveHandler.removeMessages(LIVE_SHOW_USER_FIRST);
                    liveHandler.sendEmptyMessageDelayed(LIVE_SHOW_USER_FIRST, 30000);
                }
            }
        }
        tvLoadCount.setOnClickListener(param1View -> showLockCountDialog(LiveRoomFragment.LIVE_ROOM_ID));
        tvCharisma.setOnClickListener(param1View -> showDayRankDialog(roomBeanInfo));
        addRoomPrompt();
    }

    public static LiveRoomFragment newInstance(int position, LiveRoomBeanList.DataBean paramDataBean, int paramInt2, int paramInt3, List<LiveHomeBeanInfo.DataBean> paramList, JoinRoomBeanInfo info) {
        Bundle bundle = new Bundle();
        fragmetntA = new LiveRoomFragment();
        if (position == -1) {
            bundle.putInt("postion", position);
            bundle.putInt("entrance", paramInt2);
            bundle.putBoolean("is_host", true);
            bundle.putSerializable("roomBeanInfo", info);
        } else if (paramDataBean != null) {
            bundle.putBoolean("is_host", false);
            bundle.putString("oid", paramDataBean.getId() + "");
            bundle.putInt("entrance", paramInt2);
            bundle.putSerializable("cardInfo", (Serializable) paramDataBean);
            bundle.putInt("shared", paramInt3);
            bundle.putInt("postion", position);
            bundle.putSerializable("list", (Serializable) paramList);
        }
        fragmetntA.setArguments(bundle);
        return fragmetntA;
    }

    private String rePlace(String paramString) {
        String str = paramString;
        if (this.replaceList != null) {
            str = paramString;
            if (paramString != null) {
                byte b = 0;
                while (true) {
                    str = paramString;
                    if (b < this.replaceList.size()) {
                        paramString = paramString.replace(((AppConfigUrl.AppReplacesBean) this.replaceList.get(b)).getFrom(), ((AppConfigUrl.AppReplacesBean) this.replaceList.get(b)).getTo());
                        b++;
                        continue;
                    }
                    break;
                }
            }
        }
        return str;
    }

    private void scrollToBottomListItem() {
        if (chatListView != null) {
            if (adapter != null) {
                chatListView.scrollToPosition(adapter.getItemCount() - 1);
            }
        }
    }

    private void sendMsg() {
        if (isGlobalDanmu) {
            if (amount < 10) {
                showTopUpDialog(1, SPUtils.getUserId());
                keyboardVisibleChanged(false);
                return;
            } else if (amount < 100) {
                AppApis.getMoney(this);
            }
        }
        if (!this.etBottomMsg.getText().toString().trim().isEmpty()) {
            if (isGlobalDanmu) {
                if (etBottomMsg.getText().toString().trim().length() > 20) {
                    ToastUtils.showLongToast("弹幕不能超过20个字");
                    return;
                }
            } else {
                if (etBottomMsg.getText().toString().trim().length() > 140) {
                    ToastUtils.showLongToast("消息不能超过140个字");
                    return;
                }
            }
            DanmuBean danmuBean = new DanmuBean();
            DanmuBean.DataBean dataBean = new DanmuBean.DataBean();
            DanmuBean.DataBean.FromBean fromBean = new DanmuBean.DataBean.FromBean();
            DanmuBean.DataBean.ToBean toBean = new DanmuBean.DataBean.ToBean();
            if (isGlobalDanmu) {
                fromBean.setMsgType(14);
            } else {
                fromBean.setMsgType(0);
            }
            dataBean.setFrom(fromBean);
            dataBean.setContent(etBottomMsg.getText().toString().trim());
            toBean.setToroom(LIVE_ROOM_ID);
            dataBean.setTo(toBean);
            danmuBean.setData(dataBean);
            IMLiveRoomManager.instance().sendDanmu(danmuBean);
            etBottomMsg.setText("");
            keyboardVisibleChanged(false);
        } else {
            ToastUtils.showShortToast("消息内容为空");
        }
    }

    private void setFollow(int paramInt, String paramString) {
        AppApis.getUpdateFollow(paramInt, paramString, this);
    }

    private void showChatInput() {
        LogUtils.e("keyboardVisibleChanged=showChatInput");
        mAnimUtils.startAnimtion((Context) this.mActivity, (View) topView, R.anim.anim_translate_alpha_top_hide, 150, View.INVISIBLE);
        if (etBottomMsg != null) {
            etBottomMsg.setFocusableInTouchMode(true);
            etBottomMsg.setFocusable(true);
            etBottomMsg.requestFocus();
            KeyBoardUtils.openKeybord((Context) getActivity(), etBottomMsg);
        }
    }

    private void updateBlackUser(int type, String uid) {
        switch (type) {
            default:
                return;
            case 3:
                AppApis.getUpdateBlick(0, uid, this);
                break;
            case 2:
                IMLiveRoomManager.instance().updateBanned(LIVE_ROOM_ID, uid, "live.CancelAdminHandler");
                break;
            case 1:
                IMLiveRoomManager.instance().updateBanned(LIVE_ROOM_ID, uid, "live.CancelBanned");
                break;
        }
    }

    private void updateOnlineUsers(LiveRoomUserLockBean paramLiveRoomUserLockBean, boolean paramBoolean) {
        if (this.mOnlineUsersAdapter == null) {
            this.mOnlineUsersAdapter = new LiveOnlineUsersAdapter((Context) this.mActivity, null);
            if (rvOnlineUsers != null)
                rvOnlineUsers.setAdapter((RecyclerView.Adapter) this.mOnlineUsersAdapter);
        }
        if (paramBoolean) {
            this.mOnlineUsersAdapter.add(paramLiveRoomUserLockBean);
        } else {
            this.mOnlineUsersAdapter.remove(paramLiveRoomUserLockBean);
        }
    }

    public void checkSizeForList() {
        try {
            if (chatMsgList == null) {
                return;
            }
            for (int i = chatMsgList.size(); i > 100; i = chatMsgList.size()) {
                chatMsgList.remove(0);
            }
        } catch (Exception exception) {
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initLiveRoomContent(View vRoomContent) {
        rlContent = (RelativeLayout) findViewInViewPageById(R.id.rl_content);
        mRootView = (KeyboardListenRelativeLayout) findViewInViewPageById(R.id.live_room_view);
        liveRoomAnchorinfo = (LinearLayout) findViewInViewPageById(R.id.live_room_anchorinfo);
        topView = (RelativeLayout) findViewInViewPageById(R.id.live_room_top);
        viewAnchorInfo = (LiveViewWithIcon) findViewInViewPageById(R.id.view_anchor_info);
        rvOnlineUsers = (RecyclerView) findViewInViewPageById(R.id.rv_online_users);
        tvLoadCount = (TextView) findViewInViewPageById(R.id.tv_load_count);
        llLiveCharisma = (LinearLayout) findViewInViewPageById(R.id.ll_live_charisma);
        tvCharisma = (TextView) findViewInViewPageById(R.id.tv_charisma);
        liveGiftTicketLl = (LinearLayout) findViewInViewPageById(R.id.live_gift_ticket_ll);
        tvTime = (TextView) findViewInViewPageById(R.id.tv_time);
        llLiveRoomBottomFuncEmo = (LinearLayout) findViewInViewPageById(R.id.ll_live_room_bottom_func_emo);
        llLiveRoomEmo = (LinearLayout) findViewInViewPageById(R.id.ll_live_room_emo);
        flEmoContainer = (FrameLayout) findViewInViewPageById(R.id.fl_emo_container);
        chatListView = (OnScrollRecyclerView) findViewInViewPageById(R.id.rl_live_room_chatlist);
        viewGotoRoom = findViewInViewPageById(R.id.view_goto_room);
        pkSeekBar = (SeekBarRelatelayout) findViewInViewPageById(R.id.relate_pk_seekbar);
        viewGiftCombo1 = (LiveViewForGift) findViewInViewPageById(R.id.view_gift_combo1);
        viewGiftCombo2 = (LiveViewForGift) findViewInViewPageById(R.id.view_gift_combo2);

        relatePkCountdown = (PkCountdownView) findViewInViewPageById(R.id.relate_pk_countdown);
        relatePkAnimation = (PkStartAnimation) findViewInViewPageById(R.id.relate_pk_animation);
        relatePkSmallAnimation = (PkSmallAnimation) findViewInViewPageById(R.id.relate_pk_small_animation);
        relativeRoot = (RelativeLayout) findViewInViewPageById(R.id.relative_root);
        dpvMiddle = (DanMuParentView) findViewInViewPageById(R.id.dpv_middle);
        danmakuMiddle = (DanMuView) findViewInViewPageById(R.id.danmaku_middle);
        //        llUserLevel = (LinearLayout) findViewInViewPageById(R.id.ll_user_level);
        //        tvUserLevel = (TextView) findViewInViewPageById(R.id.tv_user_level);
        //        tvUserEnter = (TextView) findViewInViewPageById(R.id.tv_user_enter);
        taskViewpager = (RollPagerView) findViewInViewPageById(R.id.task_viewpager);
        taskViewpager1 = (RollPagerView) findViewInViewPageById(R.id.task_viewpager1);
        seekclick = (SeekBarClickRelatelayout) findViewInViewPageById(R.id.seekclick);

        cbBroadcast = (CheckBox) findViewInViewPageById(R.id.cb_danmu);
        etBottomMsg = (EditText) findViewInViewPageById(R.id.et_bottom_msg);
        ivLiveBottomEmo = (ImageView) findViewInViewPageById(R.id.iv_live_bottom_emo);
        btnMsgSend = (TextView) findViewInViewPageById(R.id.btn_msg_send);
        liveRoomChat = (LinearLayout) findViewInViewPageById(R.id.live_room_chat);

        etBottom = (TextView) findViewInViewPageById(R.id.et_bottom);
        ivSkinCare = (ImageView) findViewInViewPageById(R.id.iv_skin_care);
        ivSkinCare.setOnClickListener(this);
        icMail = (ImageView) findViewInViewPageById(R.id.ic_mail);
        ivHostPK = (ImageView) findViewInViewPageById(R.id.iv_host_pk);
        ivGameGold = (ImageView) findViewInViewPageById(R.id.iv_game_gold);
        ivGift = (ImageView) findViewInViewPageById(R.id.iv_gift);
        ivShare = (ImageView) findViewInViewPageById(R.id.iv_share);
        ivShare.setOnClickListener(this);
        ivClose = (ImageView) findViewInViewPageById(R.id.iv_close);

        linearOther = (LinearLayout) findViewInViewPageById(R.id.linear_other);
        liveRoomBottom = (RelativeLayout) findViewInViewPageById(R.id.live_room_bottom);

        tvWheat = (TextView) findViewInViewPageById(R.id.tv_wheat);
        llWheatTime = (LinearLayout) findViewInViewPageById(R.id.ll_wheat_time);
        cvWheatTime = (CountdownView) findViewInViewPageById(R.id.cv_wheat_time);
        tvWheatTime = (TextView) findViewInViewPageById(R.id.tv_wheat_time);

        ivGift.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        etBottom.setOnClickListener(this);
        btnMsgSend.setOnClickListener(this);
        ivHostPK.setOnClickListener(this);
        ivGameGold.setOnClickListener(this);
        icMail.setOnClickListener(this);
        rlWheat.setOnTouchListener(this);
        tvWheat.setOnClickListener(this);
        llWheatTime.setOnClickListener(this);
        tvWheatTime.setOnClickListener(this);
        pkSeekBar.setDialogClickListener((mType, baseInfo) -> {
            switch (mType) {
                case 0:
                case 1:
                    showPKContributionDialog(mType, baseInfo);
                    break;
                case 2:
                    //                    if (relatePkCountdown != null) {
                    //                        relatePkCountdown.resetTime(1);
                    //                    }
                    break;
            }
        });

        relatePkCountdown.setDialogClickListener(type -> {
            switch (type) {
                case 1:
                    //                    IMLiveRoomManager.instance().sendPKHangup(SPUtils.getPKID(), LIVE_ROOM_ID, 5);
                    relatePkCountdown.setVisibility(View.GONE);
                    break;
                case 2:
                    //                    ToastUtils.showLongToast("再来一局");
                    showPKToGameDialog(1, mPKInfo);
                    break;
                case 3: //5s倒计时结束
                    if (pkSeekBar != null) {
                        isFiveSecond = false;
                        pkSeekBar.setInitData();
                        pkSeekBar.setPkView();
                        pkSeekBar.setPKHeader();
                    }
                    break;
                case 4:
                    isFiveSecond = true;
                    break;
                case 5:
                    IMLiveRoomManager.instance().sendPKHangup(SPUtils.getPKID(), LIVE_ROOM_ID, 3);
                    break;
                case 6:
                    IMLiveRoomManager.instance().sendPKResidueTime(SPUtils.getPKID());
                    break;
            }
        });

        Zego.instance().setReturnListener((rtmp_url, tag) -> {
            if (tag == null) {
                return;
            }
            if (tag.equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_SEND_PK)) {
                IMLiveRoomManager.instance().sendAddMergeStream(rtmp_url, LIVE_ROOM_ID);
            } else if (tag.equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_RECEIVE_PK)) {
                if (mPKInfo != null) {
                    mPKInfo.setType(1);
                    mPKInfo.setMergeStreamUrl(rtmp_url);
                    LogUtils.e("合流=", mPKInfo.toString());
                    IMLiveRoomManager.instance().sendPKResponse(mPKInfo);
                    //                        setVisibleSeekBar(mPKInfo);
                    isPK = true;
                } else {
                    LogUtils.e("合流=null");
                }
            }
        });


    }

    public View findViewInViewPageById(int paramInt) {
        return vRoomContent.findViewById(paramInt);
    }

    @SuppressLint("ClickableViewAccessibility")
    protected void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            int i = bundle.getInt("postion");
            if (i == -1) {
                this.position = i;
                LIVE_ROOM_ID = bundle.getInt("entrance") + "";
                isHost = bundle.getBoolean("is_host");
                SPUtils.setIsHost(isHost);
                ivGift.setVisibility(View.GONE);
                ivSkinCare.setVisibility(View.VISIBLE);
                liveGiftTicketLl.setVisibility(View.VISIBLE);
                ivHostPK.setVisibility(View.VISIBLE);
                ivGameGold.setVisibility(View.GONE);
                showTickerTime();
                roomBeanInfo = (JoinRoomBeanInfo) bundle.getSerializable("roomBeanInfo");
                cover = roomBeanInfo.getCover();
                liveRoomData(roomBeanInfo);
                rlWheat.setVisibility(View.GONE);
                //                EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_ENTER_ID, LIVE_ROOM_ID));
            } else {
                EventBus.getDefault().post(new ScrollEvent(false, ""));
                this.position = i;
                this.isHost = bundle.getBoolean("is_host");
                SPUtils.setIsHost(isHost);
                this.mOid = bundle.getString("oid");
                currentPostion = bundle.getInt("postion", 1);
                if (TextUtils.isEmpty(this.mOid)) {
                    finish(1);
                    return;
                }
                this.shared = bundle.getInt("shared", 0);
                mLiveCardInfo = (LiveRoomBeanList.DataBean) bundle.getSerializable("cardInfo");
                if (mLiveCardInfo != null) {
                    live_id = mLiveCardInfo.getId() + "";
                    live_chat_room_id = mLiveCardInfo.getRoomId();
                    cover = mLiveCardInfo.getCover();
                }
                ivSkinCare.setVisibility(View.GONE);
                ivGift.setVisibility(View.VISIBLE);
                liveGiftTicketLl.setVisibility(View.GONE);
                ivHostPK.setVisibility(View.GONE);
                tvWheat.setVisibility(View.VISIBLE);
                rlWheat.setVisibility(View.GONE);
                ivGameGold.setVisibility(View.VISIBLE);
                EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_ENTER, mLiveCardInfo));
            }
        }
        this.replaceList = AppConfig.APP_REPLACES;
        this.mAnimUtils = new LiveRoomAnimationUtils();
        if (liveHandler == null) {
            liveHandler = new LiveMsgHandler();
        }
        viewList = new ArrayList<>();
        viewList.add(vEmptyView);
        viewList.add(mRootView);
        liveRoomAdapter = new LiveRoomViewPageAdapter(viewList);
        vRoomContentPager = getView(getRootView(), R.id.live_room_viewpager);
        vRoomContentPager.setAdapter(liveRoomAdapter);
        vRoomContentPager.setCurrentItem(this.viewList.size() - 1);
    }


    protected void initView(View paramView) {
        super.initView(paramView);
        MessageNotifyCenter.getInstance().addObserver(this);
        vEmptyView = View.inflate(getActivity(), R.layout.live_room_empty, null);
        vRoomContent = View.inflate(getActivity(), R.layout.live_room_content, null);
        initQuest();
        initLiveRoomContent(vRoomContent);
        keyboardState();
        initDanMu();
        initChatList();
        initOnLineUserList();
        initGiftView();
    }

    private void initQuest() {
        AppApis.getGiftList(this);
        AppApis.getBackpack(this);
        AppApis.getMoney(this);
    }

    protected boolean isBindEventBusHere() {
        return true;
    }

    public void keyboardState() {
        cbBroadcast.setChecked(false);
        cbBroadcast.setOnCheckedChangeListener((param1CompoundButton, param1Boolean) -> {
            if (param1Boolean) {
                isGlobalDanmu = true;
                etBottomMsg.setHint(getString(R.string.live_room_msg_hint_for_danmu_opened, 10));
            } else {
                isGlobalDanmu = false;
                etBottomMsg.setHint(R.string.live_room_msg_hint_for_broadcast_unopen);
            }
        });
        etBottomMsg.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
                if (TextUtils.isEmpty((CharSequence) param1Editable)) {
                    btnMsgSend.setTextColor(getContext().getResources().getColor(R.color.color_999));
                    btnMsgSend.setEnabled(false);
                } else {
                    btnMsgSend.setEnabled(true);
                    btnMsgSend.setTextColor(getContext().getResources().getColor(R.color.color_D83FDD));
                }
            }

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            }

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            }
        });
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mRootView.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        mRootView.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        mRootView.setDrawingCacheEnabled(true);
        mRootView.setKeepScreenOn(true);
        mRootView.setOnKeyboardStateChangedListener((param1Int1, param1Int2) -> {
            switch (param1Int1) {
                case KeyboardListenRelativeLayout.KEYBOARD_STATE_HIDE:
                    LogUtils.e("软键盘隐藏");
                    liveRoomBottom.setVisibility(View.VISIBLE);
                    liveRoomChat.setVisibility(View.GONE);
                    keyboardVisibleChanged(false);
                    break;
                case KeyboardListenRelativeLayout.KEYBOARD_STATE_SHOW:
                    LogUtils.e("软键盘显示");
                    btnMsgSend.setEnabled(true);
                    liveRoomBottom.setVisibility(View.GONE);
                    liveRoomChat.setVisibility(View.VISIBLE);
                    //                keyboardVisibleChanged(true);
                    break;
            }
        });
    }

    protected void keyboardVisibleChanged(boolean visible) {
        if (isKeyboardVisible == visible) {
            return;
        }
        isKeyboardCloseFromClickEmo = false;
        isKeyboardVisible = visible;
        LogUtils.e("keyboardVisibleChanged=" + visible);
        if (this.isKeyboardVisible) {
            showChatInput();
        } else {
            hideChatInput();
        }
    }

    public void onDanmakuClick(Context paramContext, DanmakuEntity paramDanmakuEntity) {
        ToastUtils.showShortToast("onDanmakuClick");
    }

    public void onDanmuIconClick(String paramString1, String paramString2, int paramInt) {
        ToastUtils.showShortToast("onDanmuIconClick");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PostResult paramPostResult) {
        super.onEvent(paramPostResult);
        if (paramPostResult.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_ENTER)) {
            initEnterRoom(paramPostResult);
        } else if (paramPostResult.getTag().equals(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_ENTER_ID)) {
            if (!isHost) {
                if (!paramPostResult.getResult().equals(LIVE_ROOM_ID)) {
                    if (!LIVE_ROOM_ID.equals("null")) {
                        initLiveRoom();
                        exitRoom(1);
                    }
                    LIVE_ROOM_ID = (String) paramPostResult.getResult();
                    IMLiveRoomManager.instance().enterRoom(LIVE_ROOM_ID);
                }
                EventBus.getDefault().post(new ScrollEvent(true, ""));
            }
        }
    }

    private void initEnterRoom(PostResult paramPostResult) {
        if (!LIVE_ROOM_ID.equals("null")) {
            if (LIVE_ROOM_ID.equals(((LiveRoomBeanList.DataBean) paramPostResult.getResult()).getRoomId())) {
                EventBus.getDefault().post(new ScrollEvent(false, ""));
                return;
            }
            if (!OnClickUtils.isFastClick()) {
                return;
            }
            initLiveRoom();
            exitRoom(1);
        }
        LIVE_ROOM_ID = ((LiveRoomBeanList.DataBean) paramPostResult.getResult()).getRoomId();
        cover = ((LiveRoomBeanList.DataBean) paramPostResult.getResult()).getCover();
        IMLiveRoomManager.instance().enterRoom(LIVE_ROOM_ID);
        EventBus.getDefault().post(new ScrollEvent(true, ""));
    }


    public void onStart() {
        super.onStart();
        keyboardVisibleChanged(false);
        if (!ServiceUtils.isServiceRunning(ChujianIMService.class) && !SPUtils.getIP().isEmpty()) {
            IMLoginManager.instance().reset();
            mContext.startService(new Intent(mContext, ChujianIMService.class));
        }
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_LIVE_FANS_USER_LOCK_LIST)) {
            if (obj != null && obj instanceof LiveRoomUserLockList) {
                LiveRoomUserLockList lockList = (LiveRoomUserLockList) obj;
                if (lockList.getCode() == 200) {
                    if (mOnlineUsersAdapter == null) {
                        mOnlineUsersAdapter = new LiveOnlineUsersAdapter(mActivity, lockList.getData().getMsgList());
                        if (rvOnlineUsers != null) {
                            rvOnlineUsers.setAdapter(mOnlineUsersAdapter);
                        }
                    } else {
                        mOnlineUsersAdapter.update(lockList.getData().getMsgList());
                    }
                }
            }
        } else if (url.equals(Urls.GET_MONEY)) {
            if (obj != null && obj instanceof MyMoneyBean) {
                MyMoneyBean bean = (MyMoneyBean) obj;
                if (bean.getCode() == 200 && bean.getData() != null) {
                    amount = bean.getData().getAmount1();
                    if (eggPopup != null && eggPopup.isShowing()) {
                        eggPopup.setDiamond(amount);
                    }
                }
            }
        } else if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    if (viewAnchorInfo != null) {
                        isFollow = true;
                        viewAnchorInfo.setIvIcon(true);
                    }
                    ToastUtils.showShortToast("关注成功");
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        } else {
            if (url.equals(Urls.GET_GIFT_LIST)) {
                if (obj != null && obj instanceof LiveGiftInfoBean) {
                    LiveGiftInfoBean liveGiftInfoBean = (LiveGiftInfoBean) obj;
                    if (liveGiftInfoBean.getCode() == 200) {
                        (liveGiftInfoBean.getData().get(0)).select_item = true;
                        giftDatas.clear();
                        giftDatas.addAll(liveGiftInfoBean.getData());
                        LiveGiftUtils.get().setGiftList(liveGiftInfoBean.getData());
                    }
                }
            } else if (url.equals(Urls.backpack)) {
                if (obj != null && obj instanceof LiveGiftInfoBean) {
                    LiveGiftInfoBean liveGiftInfoBean = (LiveGiftInfoBean) obj;
                    if (liveGiftInfoBean.getCode() == 200) {
                        backpackDatas.clear();
                        if (liveGiftInfoBean.getData() != null && !liveGiftInfoBean.getData().isEmpty()) {
                            liveGiftInfoBean.getData().get(0).setSelect_item(true);
                            backpackDatas.addAll(liveGiftInfoBean.getData());
                        }
                        //                        LiveGiftUtils.get().setGiftList(liveGiftInfoBean.getData());
                    }
                }
            } else if (url.equals(Urls.GET_UPDATE_BLACK)) {
                try {
                    JSONObject jSONObject = new JSONObject(obj.toString());
                    if (jSONObject.getInt("code") == 200) {
                        ToastUtils.showShortToast("解除成功");
                    }
                } catch (JSONException jSONException1) {
                    jSONException1.printStackTrace();
                }
            } else if (url.equals(Urls.GET_HOME_REMANNDE_LIST) && obj != null && obj instanceof LiveHomeBeanInfo) {
                LiveHomeBeanInfo info = (LiveHomeBeanInfo) obj;
                if (info.getCode() == 200) {
                    if (mAdapter != null) {
                        mAdapter.setDatas(info.getData());
                    }
                }
            } else if (url.equals(Urls.GET_KEFU_INFO)) {
                try {
                    JSONObject jSONObject = new JSONObject(obj.toString());
                    if (jSONObject.getInt("code") == 200) {
                        JSONObject jSONObject1 = new JSONObject(jSONObject.getString("data"));
                        int state = jSONObject1.getInt("state");
                        showChatMsgDialog(2, state + "", "");
                    } else {
                        ToastUtils.showShortToast(jSONObject.getString("msg"));
                    }
                } catch (JSONException jSONException1) {
                    jSONException1.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        LogUtils.e("===onFailure=", url);
        ToastUtils.showShortToast(error);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_skin_care:
                showSkinCareDialog();
                break;
            case R.id.iv_share:
                showShareDialog();
                //                initEvenWheat();
                break;
            case R.id.iv_love:
                ToastUtils.showShortToast("关注");
                break;
            case R.id.iv_gift:
                showGiftDialog(0);
                break;
            case R.id.iv_close:
                if (OnClickUtils.isFastClick()) {
                    if (isHost) {
                        if (isPK) {
                            if (isFiveSecond) {
//                                finish(2);
                                showTopUpDialog(19, "-1");
                            } else {
                                showPkDisconnect();
                            }
                        } else {
                            showTopUpDialog(19, "");
//                            finish(2);
                        }
                    } else {
                        finish(1);
                    }
                }
                break;
            case R.id.et_bottom:
                isFromChat = true;
                keyboardVisibleChanged(true);
                break;
            case R.id.btn_msg_send:
                if (roomBeanInfo != null) {
                    if (roomBeanInfo.getBanned() == -2) {
                        sendMsg();
                    } else {
                        ToastUtils.showShortToast("你已被禁言");
                    }
                }
                break;
            case R.id.ic_mail:
                showChatDialog();
                break;
            case R.id.iv_host_pk:
                showPkTypeDialog(1);
                //                if (isHost) {
                //                    EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_SEND_PK, ""));
                //                    setVisibleSeekBar();
                //                    isPK = true;
                //                } else {
                //                    showPkTypeDialog(1);
                //                }
                break;
            case R.id.tv_wheat:
            case R.id.ll_wheat_time:
            case R.id.tv_wheat_time:
                switch (type) {
                    case 1:
                        if (isHost) {
                            showWheatListDialog();
                        } else {
                            showWheatDialog(wheatType);
                        }
                        break;
                    case 2:
                        showPKInvitation(3, mPKInfo);
                        break;
                    case 4:
                        showPKInvitation(4, null);
                        break;
                }
                if (tvWheat.getText().toString().trim().equals("申请超时")) {
                    tvWheat.setText("申请连麦");
                    tvWheat.setBackgroundResource(R.drawable.bg_wheat_btn);
                }
                break;
            case R.id.iv_game_gold: //金币游戏
                if (gamePopup == null) {
                    gamePopup = new GamePopup(getActivity());
                    gamePopup.setCoinClick(() -> {
                        showGameGoldDialog();
                        gamePopup.dismiss();
                        return null;
                    });
                    gamePopup.setEggClick(() -> {
                        showEgg();
                        gamePopup.dismiss();
                        return null;
                    });
                }
                gamePopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        BarUtils.setNavBarVisibility(getActivity(), false);
                        liveRoomBottom.setVisibility(View.VISIBLE);
                        liveRoomChat.setVisibility(View.GONE);
                        keyboardVisibleChanged(false);
                    }
                });
                gamePopup.showPopupWindow();
                AppApis.getGameMessageList(new ResponseListener() {
                    @Override
                    public void onFailure(int paramInt, String paramString1, String paramString2) {
                        gamePopup.dismiss();
                        ToastUtils.showLongToast(paramString1);
                    }

                    @Override
                    public void onSuccess(String paramString, Object paramObject) {
                        if (paramObject instanceof GameListBean) {
                            GameListBean bean = ((GameListBean) paramObject);
                            if (bean.getCode() == 200) {
                                List<GameListBean.DataBean> data = bean.getData();
                                gamePopup.updateGame(data);

                            }
                        }
                    }

                    @Override
                    public void onStartRequest() {

                    }
                });
                break;
        }
    }


    private void showEgg() {
        eggPopup = new EggPopup(getActivity());
        eggPopup.setDiamondClick(() -> {
            startActivity(new Intent(getActivity(), DiamondTopUpActivity.class));
            return null;
        });
        eggPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                BarUtils.setNavBarVisibility(getActivity(), false);
                liveRoomBottom.setVisibility(View.VISIBLE);
                liveRoomChat.setVisibility(View.GONE);
                keyboardVisibleChanged(false);
                AppApis.closeGame(new ResponseListener() {
                    @Override
                    public void onFailure(int paramInt, String paramString1, String paramString2) {
                        LogUtils.d("egg push close failure");
                    }

                    @Override
                    public void onSuccess(String paramString, Object paramObject) {
                        LogUtils.d("egg push closed");
                    }

                    @Override
                    public void onStartRequest() {

                    }
                });
            }
        });
        eggPopup.setRuleClick(() -> {
            EggExplainPopup eggExplainPopup = new EggExplainPopup(getActivity(), Urls.eggExplain);
            eggExplainPopup.showPopupWindow();
            return null;
        });
        //动画结束，显示结果
        eggPopup.setShowResult(() -> {
            AppApis.eggInvoke(new ResponseListener() {
                @Override
                public void onFailure(int paramInt, String paramString1, String paramString2) {
                    ToastUtils.showLongToast(paramString1);
                    if (loadingPopup != null)
                        loadingPopup.dismiss();
                }

                @Override
                public void onSuccess(String paramString, Object paramObject) {
                    if (paramObject instanceof GameResultBean) {
                        if (loadingPopup != null)
                            loadingPopup.dismiss();
                        GameResultBean bean = ((GameResultBean) paramObject);
                        if (bean.getCode() == 200) {
                            AppApis.getMoney(LiveRoomFragment.this);
                            ResultPopup resultPopup = new ResultPopup(getActivity());
                            resultPopup.setDatas(bean.getData());
                            resultPopup.setResult(() -> {
                                AppApis.getBackpack(LiveRoomFragment.this);
                                return null;
                            });
                            resultPopup.showPopupWindow();
                        } else {
                            ToastUtils.showLongToast(bean.getMsg());
                        }
                    }
                }

                @Override
                public void onStartRequest() {

                }
            }, eggPopup.getHammerType(), eggPopup.isTenCombo() ? 10 : 1);
            return null;
        });
        eggPopup.setPocketClick(() -> {
            //eggPopup.dismiss();
            //ToastUtils.showLongToast("111");
            showGiftDialog(2);
            return null;
        });

        eggPopup.showPopupWindow();
        AppApis.getEggMessage(new ResponseListener() {
            @Override
            public void onFailure(int paramInt, String paramString1, String paramString2) {
                eggPopup.dismiss();
                ToastUtils.showLongToast(paramString1);
            }

            @Override
            public void onSuccess(String paramString, Object paramObject) {
                if (paramObject instanceof EggBean) {
                    EggBean bean = ((EggBean) paramObject);
                    if (bean.getCode() == 200) {
                        eggPopup.setData(bean.getData());
                        eggPopup.setDiamond(amount);
                    } else {
                        ToastUtils.showLongToast(bean.getMsg());
                    }
                }
            }

            @Override
            public void onStartRequest() {

            }
        });


    }

    private void setVisibleSeekBar(LiveRoomWheatBaseInfo.DataBean mPKInfo) {
        //        pkSeekBar.setInitData();
        //        IMLiveRoomManager.instance().sendPKResidueTime(SPUtils.getPKID());
        LogUtils.e("=====setVisibleSeekBar");
        RelativeLayout.LayoutParams seekBarParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        seekBarParam.setMargins(0, ScreenUtils.dp2px(mContext, 79), 0, 0);
        pkSeekBar.setLayoutParams(seekBarParam);
        relatePkCountdown.setLayoutParams(seekBarParam);
        pkSeekBar.setVisibility(View.VISIBLE);
        //        relatePkCountdown.setVisibility(View.VISIBLE);
        //        relatePkCountdown.resetTime(1);
        //        pkSeekBar.setData(mPKInfo);
        //        pkSeekBar.startAnim();
    }

    /**
     * pk结束
     *
     * @param type //1.我结束PK 2.对方结束PK
     */
    private void setPKEnd(int type) {
        isPK = false;
        if (!isHost) {
            Zego.instance().reset();
        }
        EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_FINISH_PK, null));
        pkSeekBar.relase();
        pkSeekBar.setVisibility(View.GONE);
        relatePkCountdown.setVisibility(View.GONE);
        switch (type) {
            case 1:
                break;
            case 2:
                ToastUtils.showLongToast("对方结束了本场PK");
                break;
        }
    }

    protected int setLayoutId() {
        return R.layout.live_room_fragment;
    }

    public void update(Observable paramObservable, Object paramObject) {
        if (paramObject != null) {
            Message message = Message.obtain();
            message.obj = paramObject;
            this.handler.sendMessage(message);
        }
    }

    @OnClick({R.id.iv_wheat_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_wheat_close:
                if (isHost && null != roomBeanInfo) {
                    IMLiveRoomManager.instance().hostDropCallWheat(mWheat_UserId, LIVE_ROOM_ID, roomBeanInfo.getUserId(), "link.HangupHandler");
                } else {
                    IMLiveRoomManager.instance().hostDropCallWheat(SPUtils.getUserId(), LIVE_ROOM_ID, roomBeanInfo.getUserId(), "link.HangupHandler");
                }
                break;
        }
    }

    private class LiveMsgHandler extends Handler {
        private LiveMsgHandler() {
        }

        public void handleMessage(Message param1Message) {
            super.handleMessage(param1Message);
            switch (param1Message.what) {
                default:
                    return;
                case LIVE_SHOW_USER_FIRST:
                    if (!isFollow && roomBeanInfo.getIsAttention() != 1) {
                        showFollowDialog();
                    }
                    break;
                case LIVE_SHOW_CHAT_FIRST:
                    adapter.notifyDataSetChanged();
                    scrollToBottomListItem();
                    break;
            }
        }
    }


    /**
     * 连麦倒计时处理
     *
     * @param type 1.连麦 2.PK 3.好友和随机PK接收方等待时间 4.随机PK发起方等待时间 5.再来一局接收方
     */
    private void showWheatTime(int type) {
        this.type = type;
        if (null == getActivity() || null == cvWheatTime || null == llWheatTime) {
            return;
        }
        /**
         * 倒计时处理
         */
        getActivity().runOnUiThread(() -> {
            tvWheatTime.setVisibility(View.VISIBLE);
            llWheatTime.setVisibility(View.VISIBLE);
            tvWheat.setVisibility(View.GONE);
            tvWheat.setBackgroundResource(R.drawable.bg_wheat_btn);
            cvWheatTime.setVisibility(View.GONE);
            cvWheatTime.stop();
            cvWheatTime.customTimeShow(false, false, false, true, false);
            switch (type) {
                case 1:
                    wheatType = 2;
                    cvWheatTime.start(181 * 1000);
                    break;
                case 2:
                    cvWheatTime.start(20 * 1000);
                    break;
                case 3:
                    if (wheatBeans.size() > 0) {
                        tvWheat.setVisibility(View.VISIBLE);
                        tvWheat.setBackgroundResource(R.drawable.bg_wheat_btn_round_blue);
                        llWheatTime.setVisibility(View.GONE);
                    } else {
                        llWheatTime.setVisibility(View.GONE);
                        tvWheat.setVisibility(View.GONE);
                    }
                    cvWheatTime.start(20 * 1000);
                    break;
                case 5:
                    llWheatTime.setVisibility(View.GONE);
                    cvWheatTime.start(20 * 1000);
                    break;
                case 4:
                    cvWheatTime.start(120 * 1000);
                    break;
            }
            cvWheatTime.setOnCountdownEndListener(cv -> {
                switch (type) {
                    case 1:
                        wheatType = 1;
                        llWheatTime.setVisibility(View.GONE);
                        tvWheat.setVisibility(View.VISIBLE);
                        tvWheat.setText("申请超时");
                        tvWheat.setBackgroundResource(R.drawable.bg_wheat_red_btn);
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        llWheatTime.setVisibility(View.GONE);
                        break;
                }
            });
            cvWheatTime.setOnCountdownIntervalListener(1000, (cv, remainTime) -> {
                long reTime = remainTime / 1000;
                tvWheatTime.setText("申请中 " + reTime + "s");
                switch (type) {
                    case 1:
                        if (mWheatDialog != null && mWheatDialog.isShowing()) {
                            mWheatDialog.setWheatTime(remainTime);
                        }
                        break;
                    case 2:
                    case 4:
                        if (mPkInvitationDialog != null && mPkInvitationDialog.isShowing()) {
                            mPkInvitationDialog.setPKWaitTime(remainTime);
                        }
                        break;
                    case 3:
                    case 5:
                        if (mPkFriendInvitationDialog != null && mPkFriendInvitationDialog.isShowing()) {
                            mPkFriendInvitationDialog.setPKWaitTime(remainTime);
                        }
                        break;
                }
            });
        });
    }

    /**
     * 直播时间显示
     */
    private void showTickerTime() {
        if (null == getActivity() || null == liveGiftTicketLl) {
            return;
        }
        liveGiftTicketLl.setVisibility(View.VISIBLE);
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(timerTask, 0, 1000);
    }

    private String getStringTime(int cnt) {
        int hour = cnt / 3600;
        int min = cnt % 3600 / 60;
        int second = cnt % 60;
        return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            getActivity().runOnUiThread(() -> {
                if (null != tvTime) {
                    tvTime.setText(getStringTime(cnt++));
                }
            });
        }
    };

    private int lastX, lastY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.rl_wheat:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //当手指按下的时候
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        int left = v.getLeft() + dx;
                        int top = v.getTop() + dy;
                        int right = v.getRight() + dx;
                        int bottom = v.getBottom() + dy;
                        // 设置不能出界
                        if (left < 0) {
                            left = 0;
                            right = left + v.getWidth();
                        }
                        if (right > ScreenUtils.getScreenW(mContext)) {
                            right = ScreenUtils.getScreenW(mContext);
                            left = right - v.getWidth();
                        }
                        if (top < 0) {
                            top = 0;
                            bottom = top + v.getHeight();
                        }
                        if (bottom > ScreenUtils.getScreenH(mContext)) {
                            bottom = ScreenUtils.getScreenH(mContext);
                            top = bottom - v.getHeight();
                        }
                        v.layout(left, top, right, bottom);
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        EventBus.getDefault().post(new ScrollEvent(true, ""));
                        break;
                }
                break;
        }
        return true;
    }

    //pk中下播警告Dialog
    private void showPkDisconnect() {
        if (getContext() == null) {
            return;
        }
        if (mPkDisconnectDialog == null) {
            mPkDisconnectDialog = new DialogLiveRoomPKDisconnect(getContext());
        }
        mPkDisconnectDialog.show();
        mPkDisconnectDialog.setDialogClickListener(param1String -> {
            IMLiveRoomManager.instance().sendPKHangup(SPUtils.getPKID(), LIVE_ROOM_ID, 1);
        });
        mPkDisconnectDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //游戏提示
    private void showGameGoldHintDialog() {
        if (getContext() == null) {
            return;
        }
        if (mGameGoldHintDialog == null) {
            mGameGoldHintDialog = new DialogLiveRoomGameGoldHint(getContext());
        }
        mGameGoldHintDialog.show();
        mGameGoldHintDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }


    /**
     * 金币游戏
     */
    private void showGameGoldDialog() {
        if (getContext() == null) {
            return;
        }
        IMLiveRoomManager.instance().sendGameGold(LIVE_ROOM_ID);
        if (mGameGoldDialog == null) {
            mGameGoldDialog = new DialogLiveRoomGameGold(getContext());
        }
        mGameGoldDialog.show();
        mGameGoldDialog.setDialogClickListener(new DialogLiveRoomGameGold.DialogReturnListener() {
            @Override
            public void onClick(PKTypeBaseInfo info) {
                showTopUpDialog(1, SPUtils.getUserId());
            }

            @Override
            public void onClick() {
                Intent intent = new Intent(getContext(), DiamondTopUpActivity.class);
                startActivity(intent);
            }
        });
        mGameGoldDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    /**
     * 再来一局pk Dialog
     *
     * @param mType 1.发起方 2.接收方
     */
    private void showPKToGameDialog(int mType, LiveRoomWheatBaseInfo.DataBean baseInfo) {
        if (getContext() == null) {
            return;
        }
        if (mPKToGameDialog == null) {
            mPKToGameDialog = new DialogLiveRoomPKToGame(getContext());
        }
        mPKToGameDialog.show();
        if (null != baseInfo) {
            mPKToGameDialog.setData(mType, baseInfo);
        }
        mPKToGameDialog.setDialogClickListener(info -> {
            IMLiveRoomManager.instance().sendPKSendRecur(LIVE_ROOM_ID, roomBeanInfo.getUserId(), mPKInfo.getUserId());
            //            ToastUtils.showLongToast("同意再来一局");
        });
        mPKToGameDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //主播PK类型选择 Dialog
    private void showPkTypeDialog(int mType) {
        if (getContext() == null) {
            return;
        }
        if (mPkTypeDialog == null) {
            mPkTypeDialog = new DialogLiveRoomPKType(getContext(), mType);
        }
        mPkTypeDialog.show();
        mPkTypeDialog.setDialogClickListener(new DialogLiveRoomPKType.DialogReturnListener() {
            @Override
            public void onClick(PKTypeBaseInfo info) {
                switch (info.getId()) { //随机PK
                    case 1:
                        showPKInvitation(1, null);
                        break;
                }
            }

            @Override
            public void onItemClick(int type, LiveRoomWheatBaseInfo.DataBean info) {
                switch (type) {
                    case 1:
                        //                        ToastUtils.showLongToast("点击了头像");
                        //                        showPKFIFailure(2);
                        showPKListDialog(1);
                        break;
                    case 2: //点击PK记录邀请PK
                        mPKInfo = info;
                        showPKFriendInvitation(2, info);
                        break;
                }
            }
        });
        mPkTypeDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void pkFriend(LiveRoomWheatBaseInfo.DataBean info) {
        this.mPKInfo = info;
        showPKFriendInvitation(2, info);
    }

    //好友PK列表
    private void showPKListDialog(int mType) {
        if (getContext() == null) {
            return;
        }
        if (mPKListDialog == null) {
            mPKListDialog = new DialogLiveRoomPKList(getContext());
        }
        mPKListDialog.show();
        mPKListDialog.getData();
        mPKListDialog.setDialogClickListener(new DialogLiveRoomPKList.DialogReturnListener() {
            @Override
            public void onClick(int type, String userId) {

            }

            @Override
            public void onClick(int type, LiveRoomWheatBaseInfo.DataBean info) {
                switch (type) {
                    case 2:
                        //                        ToastUtils.showLongToast("点击了邀请");
                        pkFriend(info);
                        break;
                }
            }
        });
        mPKListDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    /**
     * 好友PK邀请确认页面
     *
     * @param mType 1.接收方弹窗 2.发送方弹窗 3.随机PK接受方弹窗 4.再来一局接收方弹窗
     * @param info
     */
    private void showPKFriendInvitation(int mType, LiveRoomWheatBaseInfo.DataBean info) {
        if (getContext() == null) {
            return;
        }
        if (mPkFriendInvitationDialog == null) {
            mPkFriendInvitationDialog = new DialogLiveRoomPKFriendInvitation(getContext());
        }
        mPkFriendInvitationDialog.show();
        mPkFriendInvitationDialog.setData(mType, info);
        if (mType == 1) {
            info.setAisle(2);
        } else if (mType == 3) {
            info.setAisle(1);
        } else if (mType == 4) {
            info.setAisle(3);
        }
        info.setMyStreamId(roomBeanInfo.getUserId());
        info.setMyRoomId(LIVE_ROOM_ID);
        mPkFriendInvitationDialog.setDialogClickListener(type -> {
            switch (type) {
                case 1:
                    if (mType != 2) {
                        info.setType(2);
                        mPKInfo = info;
                        IMLiveRoomManager.instance().sendPKResponse(info);
                    }
                    break;
                case 2:
                    if (mType == 2) {
                        IMLiveRoomManager.instance().sendFriendPK(LIVE_ROOM_ID, roomBeanInfo.getUserId(), info.getUserId());
                        //                        showPKInvitation(3, info);
                        //将所有连麦请求都拒绝掉
                        setRefusedWheat();
                    } else {
                        info.setType(1);
                        if (mType != 4) {
                            if (mWheat_UserId != null && ivWheatClose.getVisibility() == View.VISIBLE) {
                                isFastWheat = true;
                                IMLiveRoomManager.instance().hostDropCallWheat(mWheat_UserId, LIVE_ROOM_ID, roomBeanInfo.getUserId(), "link.HangupHandler");
                            } else {
                                if (pkSeekBar.getVisibility() != View.VISIBLE) {
                                    EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_RECEIVE_PK, mPKInfo.getUserId()));
                                    setVisibleSeekBar(mPKInfo);
                                } else {
                                    mPKInfo.setMergeStreamUrl("");
                                    IMLiveRoomManager.instance().sendPKResponse(mPKInfo);
                                }
                                //将所有连麦请求都拒绝掉
                                setRefusedWheat();
                            }
                        } else { //再来一局操作
                            info.setMergeStreamUrl("");
                            IMLiveRoomManager.instance().sendPKResponse(info);
                        }
                        //                        setVisibleSeekBar(mPKInfo);
                        mPKInfo = info;
                        isPK = true;
                    }
                    break;
                case 3: //超时
                    info.setType(2);
                    mPKInfo = info;
                    IMLiveRoomManager.instance().sendPKResponse(info);
                    break;
            }
        });
        mPkFriendInvitationDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private String str_join = "";

    //拒绝连麦
    public void setRefusedWheat() {
        if (wheatBeans != null && wheatBeans.size() > 0) {
            str_join = "";
            for (int i = 0; i < wheatBeans.size(); i++) {
                if (str_join.equals("")) {
                    str_join = wheatBeans.get(i).getUserId();
                } else {
                    str_join = str_join + "," + wheatBeans.get(i).getUserId();
                }
            }
            IMLiveRoomManager.instance().hostRefusedCallWheat(LIVE_ROOM_ID, str_join, 2, "link.AnswerlHandler");
        }
    }

    /**
     * 邀请失败页面
     *
     * @param mType   1.好友拒绝 2.好友超时
     * @param mPKInfo
     */
    private void showPKFIFailure(int mType, LiveRoomWheatBaseInfo.DataBean mPKInfo) {
        if (getContext() == null) {
            return;
        }
        if (mPkFriendInFailureDialog == null) {
            mPkFriendInFailureDialog = new DialogLiveRoomPKFriendInFailure(getContext());
        }
        mPkFriendInFailureDialog.show();
        if (mPKInfo != null) {
            mPkFriendInFailureDialog.setData(mType, mPKInfo);
        }
        mPkFriendInFailureDialog.setDialogClickListener(info -> {
            showPKListDialog(1);
        });
        mPkFriendInFailureDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }


    //随机PK邀请页面
    private void showPKInvitation(int type, LiveRoomWheatBaseInfo.DataBean info) {
        if (getContext() == null) {
            return;
        }
        if (mPkInvitationDialog == null) {
            mPkInvitationDialog = new DialogLiveRoomPKInvitation(getContext());
        }
        mPkInvitationDialog.show();
        if (type == 1) {
            mPkInvitationDialog.setRandom(LIVE_ROOM_ID, roomBeanInfo.getUserId());
        }
        mPkInvitationDialog.setData(type, info);
        mPkInvitationDialog.setDialogClickListener(info1 -> {
            showCancelDialog(1);
        });
        mPkInvitationDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //取消随机匹配
    private void showCancelDialog(int type) {
        if (getContext() == null) {
            return;
        }
        if (mPKPunishClose == null) {
            mPKPunishClose = new DialogLiveRoomPunishmentClose(getContext());
        }
        mPKPunishClose.show();
        mPKPunishClose.setData(type);
        mPKPunishClose.setDialogClickListener(mType -> {
            switch (mType) {
                case 2: //取消
                    break;
                case 3: //确定
                    IMLiveRoomManager.instance().sendRandomPkCancel(mPKInfo == null ? "" : mPKInfo.getUserId());
                    break;
            }
        });
        mPKPunishClose.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //申请连麦Dialog
    private void showWheatDialog(int mType) {
        if (getContext() == null) {
            return;
        }
        if (mWheatDialog != null) {
            mWheatDialog.show();
        } else {
            mWheatDialog = new DialogLiveRoomWheat(getContext(), mType);
            mWheatDialog.show();
        }
        mWheatDialog.setWheatData(mType, roomBeanInfo.getUserId(), LIVE_ROOM_ID);
        if (mType == 2) {
            mWheatDialog.setWheatTime(cvWheatTime.getRemainTime());
        }
        mWheatDialog.setDialogClickListener(type -> {
            switch (type) {
                case 1:
                    break;
                case 2:
                    showTopUpDialog(18, roomBeanInfo.getUserId());
                    break;
            }
        });
        mWheatDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    int wheatCount = 0;

    //更新WheatView
    private void updateWheatView(int type) {
        this.type = 1;
        switch (type) {
            case 1:
                wheatCount++;
                break;
            case 2:
                if (wheatCount > 0) {
                    wheatCount--;
                }
                break;
        }
        tvWheat.setCompoundDrawables(null, null, null, null);
        tvWheat.setBackgroundResource(R.drawable.bg_wheat_btn_round_blue);
        tvWheat.setText("连麦申请 " + wheatCount);
        if (wheatCount > 0) {
            tvWheat.setVisibility(View.VISIBLE);
        } else {
            tvWheat.setVisibility(View.GONE);
        }
    }

    //申请连麦列表Dialog
    private void showWheatListDialog() {
        if (getContext() == null) {
            return;
        }
        if (mWheatListDialog != null) {
            mWheatListDialog.show();
        } else {
            mWheatListDialog = new DialogLiveRoomWheatList(getContext());
            mWheatListDialog.show();
        }
        mWheatListDialog.setList(wheatBeans);
        mWheatListDialog.setDialogClickListener((type, userId) -> {
            switch (type) {
                case 1://同意
                    rlWheat.setVisibility(View.VISIBLE);
                    ivWheatClose.setVisibility(View.VISIBLE);
                    if (null != mWheat_UserId) {
                        IMLiveRoomManager.instance().hostDropCallWheat(mWheat_UserId, LIVE_ROOM_ID, roomBeanInfo.getUserId(), "link.Hangup2Handler");
                    }
                    this.mWheat_UserId = userId;
                    Zego.instance().initWheatPull(false, userId, mPusherView);
                    IMLiveRoomManager.instance().hostRefusedCallWheat(LIVE_ROOM_ID, userId, type, "link.AnswerlHandler");
                    break;
                case 2://拒绝
                    IMLiveRoomManager.instance().hostRefusedCallWheat(LIVE_ROOM_ID, userId, type, "link.AnswerlHandler");
                    break;
            }
        });
        mWheatListDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }


    private void showChatDialog() {
        if (mChatDialog != null) {
            mChatDialog = null;
        }
        mChatDialog = new DialogChat(getContext());
        mChatDialog.show();
        mChatDialog.setDialogClickListener((type, bean) -> {
            switch (type) {
                case 1:
                    showSystemMsgDialog();
                    break;
                case 2:
                    kefu();
                    //                        showChatMsgDialog(2, "", "", "");
                    break;
                case 3:
                    showChatMsgDialog(3, bean.getTargetId(), bean.getUserName());
                    break;
            }
        });
        mChatDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //客服消息
    public void kefu() {
        AppApis.getKefuInfo(this);
    }

    private void showSystemMsgDialog() {
        if (mStystemMsg != null) {
            mStystemMsg = null;
        }
        mStystemMsg = new DialogSystemMessage(getContext());
        mStystemMsg.show();
        mStystemMsg.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }


    //target=25 userId=35
    private void showChatMsgDialog(final int type, final String target, String userName) {
        if (mChatMsgDialog != null) {
            mChatMsgDialog = null;
        }
        mChatMsgDialog = new DialogLiveMessage(getContext(), type, target, userName, mActivity);
        mChatMsgDialog.setDialogClickListener((uid, param1Int) -> setClearCount(target));
        mChatMsgDialog.show();
        mChatMsgDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void setClearCount(String mTargetId) {
        AppApis.getGetClearCountInfo(mTargetId, this);
    }

    private void showTopUpDialog(final int type, final String uid) {
        if (mBalanceDialog != null) {
            mBalanceDialog = null;
        }
        if (type == 19) {
            if (isClose) {
                count++;
                if (count > 2 && getActivity() != null) {
                    Intent intent = new Intent(getContext(), SowingLiveActivity.class);
                    intent.putExtra("roomId", LIVE_ROOM_ID);
                    intent.putExtra("time", tvTime.getText().toString().trim());
                    startActivity(intent);
                    getActivity().finish();
                }
                return;
            }
        }
        mBalanceDialog = new DialogLiveRoomLackBalance(getContext(), type);
        mBalanceDialog.show();
        mBalanceDialog.setDialogClickListener(type1 -> {
            switch (type1) {
                case 2:
                    if (!isHost) {
                        EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_ENTER_ID, uid));
                    }
                    break;
                case 1:
                    Intent intent = new Intent(getContext(), DiamondTopUpActivity.class);
                    startActivity(intent);
                    break;
                case 6:
                    IMLiveRoomManager.instance().updateBanned(LIVE_ROOM_ID, uid, "live.KickLiveHandler");
                    break;
                case 7:
                    IMLiveRoomManager.instance().updateBanned(LIVE_ROOM_ID, uid, "live.BannedUser");
                    break;
                case 8:
                    IMLiveRoomManager.instance().updateBanned(LIVE_ROOM_ID, uid, "live.CancelBanned");
                    break;
                case 9:
                    IMLiveRoomManager.instance().updateBanned(LIVE_ROOM_ID, uid, "live.SettingAdminHandler");
                    break;
                case 10:
                    IMLiveRoomManager.instance().updateBanned(LIVE_ROOM_ID, uid, "live.CancelAdminHandler");
                    break;
                case 18:
                    IMLiveRoomManager.instance().hostRefusedCallWheat(LIVE_ROOM_ID, uid, 2, "link.CancelHandler");
                    if (llWheatTime != null) {
                        cvWheatTime.stop();
                        cvWheatTime.destroyDrawingCache();
                        llWheatTime.setVisibility(View.GONE);
                    }
                    tvWheat.setVisibility(View.VISIBLE);
                    wheatType = 1;
                    tvWheat.setText("申请连麦");
                    tvWheat.setBackgroundResource(R.drawable.bg_wheat_btn);
                    break;
                case 19:
                    isClose = true;
                    if (uid.equals("-1")) {
                        IMLiveRoomManager.instance().sendPKHangup(SPUtils.getPKID(), LIVE_ROOM_ID, 2);
                    }
                    finish(2);
                    break;
            }
        });
        this.mBalanceDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showDayRankDialog(JoinRoomBeanInfo paramJoinRoomBeanInfo) {
        if (liveRoomRankDialog != null) {
            liveRoomRankDialog = null;
        }
        liveRoomRankDialog = new DialogLiveRoomRankList(getContext(), paramJoinRoomBeanInfo.getUserId());
        liveRoomRankDialog.show();
        liveRoomRankDialog.setDialogClickListener((param1String, param1Int) -> {
            if (param1Int != 1) {
                showHomePageDialog(param1String);
            } else {
                //                showTopUpDialog(2, param1String);
                if (!isHost) {
                    EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_ENTER_ID, param1String));
                    liveRoomRankDialog.dismiss();
                }
            }
        });
        liveRoomRankDialog.setOnDismissListener(dialog -> {
            if (viewAnchorInfo != null) {
                viewAnchorInfo.setIvIcon(isFollow);
            }
        });
        this.liveRoomRankDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showFollowDialog() {
        if (getContext() == null) {
            return;
        }
        if (d != null) {
            d = null;
        }
        d = new DialogLiveRoomFollow(getContext(), roomBeanInfo);
        d.show();
        d.setDialogClickListener(param1String -> setFollow(1, param1String));
        d.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showGiftDialog(int index) {
        if (OnClickUtils.isFastClick()) {
            if (giftDatas == null || roomBeanInfo == null) {
                return;
            }
            if (giftBean != null) {
                for (int i = 0; i < giftDatas.size(); i++) {
                    if (giftBean.getId().equals(giftDatas.get(i).getId())) {
                        giftDatas.get(i).select_item = false;
                        break;
                    }
                }
                giftDatas.get(0).select_item = true;
            }
            AppApis.getBackpack(this);
            fm = getActivity().getSupportFragmentManager();
            if (mGiftDialog != null) {
                fm.beginTransaction().remove((Fragment) mGiftDialog).commitAllowingStateLoss();
                mGiftDialog = null;
            }
            mGiftDialog = new LiveGiftDialog(getActivity(), giftDatas, backpackDatas, roomBeanInfo.getUserId(), LIVE_ROOM_ID, index);
            mGiftDialog.setCancelable(true);
            mGiftDialog.show(fm, "");
            mGiftDialog.setCancelListener(new LiveGiftDialog.OnClickDataListener() {
                public void onClickData(LiveGiftInfoBean.DataBean param1DataBean) {
                }

                public void onDismiss(DialogInterface param1DialogInterface, LiveGiftInfoBean.DataBean param1DataBean) {
                }

                public void onGoPay() {
                    showTopUpDialog(1, SPUtils.getUserId());
                }
            });
        }
    }


    //PK贡献榜列表
    private void showPKContributionDialog(int mType, PKBattleBaseInfo baseInfo) {
        if (OnClickUtils.isFastClick()) {
            if (roomBeanInfo == null) {
                return;
            }
            if (fm == null) {
                fm = getActivity().getSupportFragmentManager();
            }
            if (mPKContributionDialog != null) {
                mPKContributionDialog = null;
            }
            mPKContributionDialog = new DialogLiveRoomPkContributionList(getActivity(), mType, SPUtils.getPKID(), roomBeanInfo.getUserId(), LIVE_ROOM_ID, mPKInfo.getUserId(), mPKInfo.getRoomId());
            mPKContributionDialog.show(fm, "");
            mPKContributionDialog.setCancelable(true);
        }
    }

    private void showHomePageDialog(String uid) {
        if (userHomePageDialog != null) {
            userHomePageDialog = null;
        }
        userHomePageDialog = new DialogLiveRoomUserHomePage(getContext(), uid, LIVE_ROOM_ID, roomBeanInfo.getUserId());
        userHomePageDialog.show();
        userHomePageDialog.setDialogClickListener((uid1, type) -> {
            switch (type) {
                case 1:
                    showReportDialog(uid1);
                    break;
                case 2:
                    showChatMsgDialog(3, uid1, null);
                    break;
                case 3:
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("type", 7);
                    intent.putExtra("targetId", uid1);
                    startActivity(intent);
                    break;
            }
        });
        userHomePageDialog.setOnDismissListener(dialog -> {
            if (viewAnchorInfo != null) {
                viewAnchorInfo.setIvIcon(isFollow);
            }
        });
        this.userHomePageDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showLockCountDialog(String paramString) {
        if (lockCountDialog != null) {
            lockCountDialog = null;
        }
        lockCountDialog = new DialogLockCount(getContext(), paramString);
        lockCountDialog.show();
        lockCountDialog.setDialogClickListener(param1String -> setUserInfoDialog(param1String, 2));
        lockCountDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showReportDialog(String paramString) {
        if (reportDialog != null) {
            reportDialog = null;
        }
        reportDialog = new DialogLiveRoomUserReport(getContext(), paramString);
        reportDialog.show();
        reportDialog.setDialogClickListener((param1String, param1Int) -> {
            IMLiveRoomManager.instance().setBlock(paramString, LIVE_ROOM_ID);
        });
        this.reportDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showShareDialog() {
        if (mShareDialog != null) {
            mShareDialog = null;
        }
        mShareDialog = new DialogLiveRoomShare(getContext(), LIVE_ROOM_ID, roomBeanInfo.getRoomName(), cover);
        mShareDialog.show();
        mShareDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showSkinCareDialog() {
        if (mSkinCareDialog != null) {
            mSkinCareDialog = null;
        }
        mSkinCareDialog = new DialogLiveRoomSkinCare(getContext());
        mSkinCareDialog.show();
        mSkinCareDialog.setDialogClickListener(param1Int -> {
            switch (param1Int) {
                default:
                    return;
                case 8:
                    showSkinDialog(3);
                    break;
                case 7:
                    showSkinDialog(2);
                    break;
                case 6:
                    showSkinDialog(1);
                    break;
                case 5:
                    showGiftDialog(0);
                    break;
                case 4:
                case 3:
                case 2:
                case 1:
                    EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_LIVE_ROOM_BEAUTY_SET, param1Int));
                    break;
            }
        });
        this.mSkinCareDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void showSkinDialog(int paramInt) {
        if (mHostOperationDialog != null) {
            mHostOperationDialog = null;
        }
        mHostOperationDialog = new DialogHostOperation(getContext(), paramInt, LIVE_ROOM_ID);
        mHostOperationDialog.show();
        mHostOperationDialog.setDialogClickListener((type, uid) -> updateBlackUser(type, uid));
        mHostOperationDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //主播禁播弹窗
    private void setShowWarningDialog(SystemNotiBeanInfo.DataBean message3) {
        warningNoti = new DialogNotices(getContext(), 1, message3.getTime(), message3.getMessage());
        warningNoti.show();
        warningNoti.setDialogClickListener(type -> {
            switch (type) {// 类型: 1.警告 2.禁播 3.封号
                case 1:
                    break;

            }
        });
        warningNoti.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    private void setUserInfoDialog(String uid, int position) {
        if (roomBeanInfo == null) {
            return;
        }
        if (userDialog != null) {
            userDialog = null;
        }
        this.userDialog = new DialogLiveRoomUserInfo(getContext(), uid, position, roomBeanInfo.getOth(), LIVE_ROOM_ID);
        this.userDialog.show();
        this.userDialog.setDialogClickListener((uid1, param1Int) -> {
            switch (param1Int) {
                default:
                    showTopUpDialog(param1Int, uid1);
                    return;
                case 5:
                    showHomePageDialog(uid1);
                    break;
                case 4:
                    showChatMsgDialog(3, uid1, null);
                    break;
                case 3:
                    etBottomMsg.setText("@" + uid1);
                    etBottomMsg.setSelection(uid1.length() + 1);
                    keyboardVisibleChanged(true);
                    break;
                case 1:
                    showReportDialog(uid1);
                    break;
            }
        });
        userDialog.setOnDismissListener(dialog -> {
            if (viewAnchorInfo != null) {
                viewAnchorInfo.setIvIcon(isFollow);
            }
        });
        this.userDialog.setOnKeyListener((param1DialogInterface, param1Int, param1KeyEvent) -> {
            if (param1Int == 4 || param1Int == 82 || param1Int == 3) {
                param1DialogInterface.dismiss();
                return true;
            }
            return false;
        });
    }

    //初始化直播间
    private void initLiveRoom() {
        LogUtils.e("initLiveRoom");
        Zego.instance().reset();
        if (rlWheat.getVisibility() == View.VISIBLE) {
            rlWheat.setVisibility(View.GONE);
        }
        mWheat_UserId = null;
        wheatType = 1;
        SPUtils.setPKID("");
        if (null != chatListView && chatListView.getVisibility() == View.GONE) {
            chatListView.setVisibility(View.VISIBLE);
        }
        if (mLiveGiftActionManager != null) {
            mLiveGiftActionManager.clearAllGift();
        }
        if (mLiveActionManager != null) {
            mLiveActionManager.clearAllGift();
        }

        if (liveViewEnterRoom != null) {
            liveViewEnterRoom.setVisibility();
        }
        if (liveViewAnnouncement != null) {
            liveViewAnnouncement.setVisibility();
        }
        if (seekclick != null) {
            seekclick.setVisibility(View.GONE);
        }
        if (liveGiftTicketLl != null) {
            liveGiftTicketLl.setVisibility(View.GONE);
        }

        if (null != mGiftDialog && mGiftDialog.isVisible()) {
            mGiftDialog.dismissAllowingStateLoss();
            mGiftDialog = null;
        }

        if (null != mWheatListDialog && mWheatListDialog.isShowing()) {
            mWheatListDialog.dismiss();
            mWheatListDialog = null;
        }

        if (null != mWheatDialog && mWheatDialog.isShowing()) {
            mWheatDialog.dismiss();
            mWheatDialog = null;
        }


        if (null != viewAnchorInfo) {
            //            viewAnchorInfo.setVisibility(View.INVISIBLE);
            //            viewAnchorInfo.setLoveIcon(event.getCardInfo().getLive_id());
        }

        if (llWheatTime != null) {
            cvWheatTime.stop();
            cvWheatTime.destroyDrawingCache();
            llWheatTime.setVisibility(View.GONE);
        }


        if (viewGiftCombo1 != null) {
            viewGiftCombo1.clear();
        }
        if (viewGiftCombo2 != null) {
            viewGiftCombo1.clear();
        }

        if (chatMsgList != null) {
            chatMsgList.clear();
            adapter.notifyDataSetChanged();
        }

        tvLoadCount.setText("0");
        if (roomBeanInfo != null) {
            roomBeanInfo = null;
        }
        if (mOnlineUsersAdapter != null) {
            mOnlineUsersAdapter.clear();
        }
    }

    public void onDestroy() {
        keyboardVisibleChanged(false);
        Zego.instance().reset();
//        if (!NetworkUtils.isConnected()) {
//            Zego.instance().finsh();
//        }
        if (mDanMuHelper != null) {
            mDanMuHelper.release();
            mDanMuHelper = null;
        }
        if (danmakuMiddle != null) {
            danmakuMiddle.release();
        }
        if (relatePkCountdown != null) {
            relatePkCountdown.relase();
        }
        LIVE_ROOM_ID = "null";
        SPUtils.setPKID("");
        MessageNotifyCenter.getInstance().unregister(this);
        MessageHelper.getInstance().releaseContext();
        CleanDataUtils.clearAllCache(mContext);
        if (mLiveGiftActionManager != null) {
            mLiveGiftActionManager.clearAllGift();
        }
        if (mLiveActionManager != null) {
            mLiveActionManager.clearAllGift();
        }
        if (liveViewEnterRoom != null) {
            liveViewEnterRoom.release();
            liveViewEnterRoom = null;
        }
        if (liveViewAnnouncement != null) {
            liveViewAnnouncement.release();
            liveViewAnnouncement = null;
        }
        if (viewGiftCombo1 != null) {
            viewGiftCombo1.clear();
        }
        if (viewGiftCombo2 != null) {
            viewGiftCombo1.clear();
        }
        if (timer != null) {
            timer.cancel();
            timerTask.cancel();
            timerTask = null;
            timer = null;
        }
        if (adapter != null) {
            adapter.release();
            adapter = null;
        }

        if (vRoomContentPager != null) {
            vRoomContentPager.clearOnPageChangeListeners();
            vRoomContentPager.setAdapter(null);
            vRoomContentPager = null;
        }
        if (null != liveRoomAdapter) {
            liveRoomAdapter = null;
        }
        if (null != viewList) {
            viewList.clear();
            viewList = null;
        }
        if (liveHandler != null) {
            liveHandler.removeMessages(LIVE_SHOW_USER_FIRST);
            liveHandler.removeMessages(LIVE_SHOW_CHAT_FIRST);
            liveHandler = null;
        }
        if (llWheatTime != null) {
            cvWheatTime.stop();
            cvWheatTime.destroyDrawingCache();
            llWheatTime.setVisibility(View.GONE);
        }

        if (null != mWheatListDialog) {
            mWheatListDialog.dismiss();
            mWheatListDialog = null;
        }

        if (null != mWheatDialog) {
            mWheatDialog.dismiss();
            mWheatDialog = null;
        }

        if (null != mPkTypeDialog) {
            mPkTypeDialog.dismiss();
            mPkTypeDialog = null;
        }

        if (null != mPkInvitationDialog) {
            mPkInvitationDialog.dismiss();
            mPkInvitationDialog = null;
        }

        if (null != mPkFriendInvitationDialog) {
            mPkFriendInvitationDialog.dismiss();
            mPkFriendInvitationDialog = null;
        }

        if (null != mPkFriendInFailureDialog) {
            mPkFriendInFailureDialog.dismiss();
            mPkFriendInFailureDialog = null;
        }

        if (null != mPkDisconnectDialog) {
            mPkDisconnectDialog.dismiss();
            mPkDisconnectDialog = null;
        }

        if (null != mPKToGameDialog) {
            mPKToGameDialog.dismiss();
            mPKToGameDialog = null;
        }


        if (null != mPKPunishClose) {
            mPKPunishClose.dismiss();
            mPKPunishClose = null;
        }


        if (null != mPKListDialog) {
            mPKListDialog.dismiss();
            mPKListDialog = null;
        }


        if (null != mPKContributionDialog) {
            mPKContributionDialog.dismiss();
            mPKContributionDialog = null;
        }


        if (null != mGameGoldDialog) {
            mGameGoldDialog.dismiss();
            mGameGoldDialog = null;
        }
        if (null != mGameGoldHintDialog) {
            mGameGoldHintDialog.dismiss();
            mGameGoldHintDialog = null;
        }
        super.onDestroy();
    }
}