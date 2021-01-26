package com.jk.jkproject.ui.dialog;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.activity.DiamondTopUpActivity;
import com.jk.jkproject.ui.adapter.LiveGiftGiveCountAdapter;
import com.jk.jkproject.ui.adapter.LiveGiftPagerAdapter;
import com.jk.jkproject.ui.entity.ExpLevelInfo;
import com.jk.jkproject.ui.entity.GiveCountBean;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.fragment.LiveGrifDialogFragment;
import com.jk.jkproject.ui.fragment.LiveRoomFragment;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.ui.widget.DialogViewPager;
import com.jk.jkproject.ui.widget.room.RoundCircleProgressBar;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint({"ValidFragment"})
public class LiveGiftDialog extends DialogFragment implements ResponseListener {
    @BindView(R.id.tv_level)
    TextView        tvLevel;
    @BindView(R.id.pb_progressbar)
    ProgressBar     pbProgressbar;
    @BindView(R.id.tv_experience)
    TextView        tvExperience;
    @BindView(R.id.tv_desc)
    TextView        tvDesc;
    @BindView(R.id.live_gift_vp)
    DialogViewPager liveGiftVp;
    TextView tvBalance;
    @BindView(R.id.tv_gift_num)
    TextView               tvGiftNum;
    @BindView(R.id.tv_ok_btn)
    TextView               tvOkBtn;
    @BindView(R.id.rl_1)
    RelativeLayout         rl1;
    @BindView(R.id.progress_count)
    RoundCircleProgressBar progressCount;
    @BindView(R.id.iv_gift_circle)
    SimpleDraweeView       ivGiftCircle;
    @BindView(R.id.tv_1)
    TextView               tv1;
    @BindView(R.id.tv_num)
    TextView               tvNum;
    @BindView(R.id.relate_quick_send_gift)
    RelativeLayout         relateQuickSendGift;
    @BindView(R.id.live_gift_give_num)
    RecyclerView           liveGiftGiveRv;
    @BindView(R.id.rl)
    RelativeLayout         rl;
    private LiveGiftPagerAdapter adapter;

    @BindView(R.id.hotTab)
    View hotTab;
    @BindView(R.id.freeTab)
    View freeTab;
    @BindView(R.id.backpackTab)
    View backpackTab;
    private int status = 0;

    protected int animId = -1;

    private int currentIndex = 0;

    private ValueAnimator animatorexperience;

    private LiveGrifDialogFragment fragment;
    private LiveGrifDialogFragment backPackfragment;
    private LiveGrifDialogFragment freeFragment;

    private FragmentTransaction fragmentTransaction;

    private LiveGiftInfoBean.DataBean giftBean;

    private LiveGiftGiveCountAdapter giftGiveCountAdapter;

    private String[] giftNumName = new String[]{"一生一世", "我爱你", "要抱抱", "天长地久", "十全十美", "一心一意"};

    private List<GiveCountBean> liveGiftGiveCount = new ArrayList<GiveCountBean>();


    private OnClickDataListener mClickDataListener;

    private Context mContext;

    private List<LiveGiftInfoBean.DataBean> mGiftDatas;
    private List<LiveGiftInfoBean.DataBean> mFreeDatas = new ArrayList<>();
    private List<LiveGiftInfoBean.DataBean> mBackpackDatas;
    private String                          userId;
    private String                          roomId;
    private int                             sendPid    = 0;
    private int                             sendCount  = 0;

    //记录连击数
    int  curIndex = 0;
    long curTime;
    private Map<String, Long> combo_interval;
    int curGiftId = -1;


    private String selectNum = null;

    private FragmentManager supportFragmentManager;
    Unbinder unbinder;
    private ExpLevelInfo info;
    private int          money  = 0;
    private int          mIndex = 0;

    public LiveGiftDialog() {
    }

    public LiveGiftDialog(Context activity, List<LiveGiftInfoBean.DataBean> giftDatas, List<LiveGiftInfoBean.DataBean> backpackDatas, String userId, String roomId, int index) {
        mContext       = activity;
        mGiftDatas     = giftDatas;
        mBackpackDatas = backpackDatas;
        this.userId    = userId;
        this.roomId    = roomId;
        mIndex         = index;
    }

    private void initFullScreen() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.width     = -1;
                layoutParams.height    = -2;
                layoutParams.dimAmount = 0.0F;
                window.setAttributes(layoutParams);
            }
        }
    }

    private void initPW() {
        if (null == combo_interval) {
            combo_interval = new HashMap<>();
        } else {
            combo_interval.clear();
        }
        liveGiftGiveRv.setLayoutManager((RecyclerView.LayoutManager) new LinearLayoutManager(getContext()));
        liveGiftGiveRv.addItemDecoration((RecyclerView.ItemDecoration) new DividerItemDecoration(getContext(), 1));
        giftGiveCountAdapter = new LiveGiftGiveCountAdapter(getContext());
        liveGiftGiveRv.setAdapter((RecyclerView.Adapter) this.giftGiveCountAdapter);
        giftGiveCountAdapter.setOnItemClickListener(param1GiveCountBean -> {
            tvGiftNum.setText(param1GiveCountBean.getNum());
            liveGiftGiveRv.setVisibility(View.GONE);
        });
        animatorexperience = ValueAnimator.ofInt(0, 100);
        animatorexperience.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int count = 30;
            int count_3 = 0;

            public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
                int i = ((Integer) param1ValueAnimator.getAnimatedValue()).intValue();
                if (i == 0) {
                    tvNum.setText("30");
                }
                if (i % 5 == 0) {
                    count = Integer.parseInt(tvNum.getText().toString().trim()) - 1;
                    if (count >= 0) {
                        tvNum.setText(count + "");
                    }
                }
                if (progressCount != null) {
                    progressCount.setProgress(100 - i);
                }
                if (100 == i) {
                    relateQuickSendGift.setVisibility(View.GONE);
                    tvGiftNum.setVisibility(View.VISIBLE);
                    tvOkBtn.setVisibility(View.VISIBLE);
                    combo_interval.clear();
                }
            }
        });
        animatorexperience.setDuration(3000);
        //        animatorexperience.start();
    }

    @OnClick({R.id.hotTab, R.id.freeTab, R.id.backpackTab})
    public void tabClick(View view) {
        switch (view.getId()) {
            case R.id.hotTab:
                if (currentIndex != 0) {
                    hotTab.setBackgroundColor(Color.parseColor("#D83FDD"));
                    freeTab.setBackgroundColor(Color.TRANSPARENT);
                    backpackTab.setBackgroundColor(Color.TRANSPARENT);
                    currentIndex = 0;
                    liveGiftVp.setCurrentItem(currentIndex, true);
                }
                break;
            case R.id.freeTab:
                if (currentIndex != 1) {
                    hotTab.setBackgroundColor(Color.TRANSPARENT);
                    freeTab.setBackgroundColor(Color.parseColor("#D83FDD"));
                    backpackTab.setBackgroundColor(Color.TRANSPARENT);
                    currentIndex = 1;
                    liveGiftVp.setCurrentItem(currentIndex, true);
                }
                break;
            case R.id.backpackTab:
                if (currentIndex != 2) {
                    hotTab.setBackgroundColor(Color.TRANSPARENT);
                    freeTab.setBackgroundColor(Color.TRANSPARENT);
                    backpackTab.setBackgroundColor(Color.parseColor("#D83FDD"));
                    currentIndex = 2;
                    liveGiftVp.setCurrentItem(currentIndex, true);
                }
                break;
        }
    }

    private void initView() {
        this.supportFragmentManager = getActivity().getSupportFragmentManager();
        this.fragmentTransaction    = this.supportFragmentManager.beginTransaction();
        this.fragment               = LiveGrifDialogFragment.newInstance("1", this.mGiftDatas);
    }

    private void setGiveGiftNum() {
        String[] arrayOfString;
        String str2 = giftBean.getG_continuous_send().substring(1, this.giftBean.getG_continuous_send().length() - 1).replace("\"", "");
        if (str2.contains(",")) {
            arrayOfString = str2.split(",");
            for (byte b = 0; b < arrayOfString.length; b++)
                 this.liveGiftGiveCount.add(new GiveCountBean(this.giftNumName[b], arrayOfString[b], false));
        } else {
            this.liveGiftGiveCount.add(new GiveCountBean(this.giftNumName[0], str2, false));
        }
        String str1 = getResources().getString(R.string.str_live_room_gift_desc);
        tvDesc.setText((CharSequence) Html.fromHtml(String.format(str1, giftBean.getG_name(), giftBean.getG_gain_amount() + "", giftBean.getG_gain_hot() + "")));
    }

    private void setNumView(boolean paramBoolean) {
        if (paramBoolean) {
            liveGiftGiveRv.setVisibility(View.VISIBLE);
            Drawable drawable = this.mContext.getResources().getDrawable(R.mipmap.live_icon_live_room_gift_down);
            drawable.setBounds(0, 0, 20, 20);
            this.tvGiftNum.setCompoundDrawables(null, null, drawable, null);
            if (giftGiveCountAdapter != null) {
                giftGiveCountAdapter.setGiveCountData(this.liveGiftGiveCount);
            }
        } else {
            this.liveGiftGiveRv.setVisibility(View.GONE);
            Drawable drawable = this.mContext.getResources().getDrawable(R.mipmap.live_icon_live_room_gift_num_up);
            drawable.setBounds(0, 0, 20, 20);
            tvGiftNum.setCompoundDrawables(null, null, drawable, null);
        }
    }

    private void setOkBtn() {
        if (mClickDataListener != null && giftBean != null) {
            giftBean.setSelect_count(tvGiftNum.getText().toString().trim());
            switch (giftBean.getG_type()) {
                case 1:
                    if (animatorexperience != null) {
                        tvGiftNum.setVisibility(View.GONE);
                        tvOkBtn.setVisibility(View.GONE);
                        relateQuickSendGift.setVisibility(View.VISIBLE);
                        animatorexperience.start();
                    }
                    //                    sendGift(1, 1);
                    sendGiftCombo();
                    break;
                default:
                    sendGift(0, 1);
                    break;
            }

            tvOkBtn.setEnabled(false);

        }
    }

    //发送礼物
    private void sendGift(int g_status, int curIndex) {
        if (info != null) {
            if (giftBean == null) {
                return;
            }
            if (giftBean.getP_id() > 0) {
                if (Integer.valueOf(tvGiftNum.getText().toString()) > giftBean.getP_count()) {
                    ToastUtils.showShortToast("超过已有礼物数量");
                    return;
                }

                AppApis.getSendPocketGift(SPUtils.getPKID(), roomId, userId, String.valueOf(giftBean.getP_id()), tvGiftNum.getText().toString().trim(), g_status, curIndex, this);
                sendPid   = giftBean.getP_id();
                sendCount = Integer.valueOf(tvGiftNum.getText().toString().trim());
                if (g_status == 1) {
                    status = g_status;
                    for (int i = 0; i < mBackpackDatas.size(); i++) {
                        LiveGiftInfoBean.DataBean dataBean = mBackpackDatas.get(i);
                        if (dataBean.getP_id() == sendPid) {
                            dataBean.setP_count(dataBean.getP_count() - sendCount);
                        }
                    }


                    Iterator<LiveGiftInfoBean.DataBean> iterator = mBackpackDatas.iterator();
                    while (iterator.hasNext()) {
                        LiveGiftInfoBean.DataBean next = iterator.next();
                        if (next.getP_count() <= 0) {
                            iterator.remove();
                            if (!mBackpackDatas.isEmpty()) {
                                mBackpackDatas.get(0).setSelect_item(true);
                            } else {
                                giftBean = null;
                            }
                            relateQuickSendGift.setVisibility(View.GONE);
                        }
                    }
                    backPackfragment.setPocketData();
                    sendPid   = 0;
                    sendCount = 0;
                }
            } else {
                money = giftBean.getG_amount() * Integer.parseInt(tvGiftNum.getText().toString().trim());
                if (money <= info.getData().getMoney()) {
                    AppApis.getSendGift(SPUtils.getPKID(), roomId, userId, giftBean.getId(), tvGiftNum.getText().toString().trim(), g_status, curIndex, this);
                } else {
                    if (mClickDataListener != null) {
                        mClickDataListener.onGoPay();
                    }
                }
            }
        } else {
            AppApis.getGetExpLevelInfo(this);
        }
    }

    //赠送连击礼物
    private void sendGiftCombo() {
        animatorexperience.setIntValues(0, 100);
        animatorexperience.start();
        sendGift(isCombo(1, giftBean), curIndex);
    }

    private void setupViewPager(ViewPager paramViewPager, int index) {
        this.adapter          = new LiveGiftPagerAdapter((Context) getActivity(), getChildFragmentManager());
        this.fragment         = LiveGrifDialogFragment.newInstance("1", mGiftDatas);
        this.freeFragment     = LiveGrifDialogFragment.newInstance("2", mFreeDatas);
        this.backPackfragment = LiveGrifDialogFragment.newInstance("3", mBackpackDatas);
        this.adapter.addFragment((Fragment) this.fragment, "背包");
        this.adapter.addFragment((Fragment) this.freeFragment, "免费");
        this.adapter.addFragment((Fragment) this.backPackfragment, "背包");
        paramViewPager.setAdapter((PagerAdapter) this.adapter);
        paramViewPager.setOffscreenPageLimit(3);
        if (index == 2) {
            hotTab.setBackgroundColor(Color.TRANSPARENT);
            freeTab.setBackgroundColor(Color.TRANSPARENT);
            backpackTab.setBackgroundColor(Color.parseColor("#D83FDD"));
            currentIndex = 2;
            if (!mBackpackDatas.isEmpty())
            giftBean     = mBackpackDatas.get(0);
        }
        paramViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                paramViewPager.setCurrentItem(index, true);
            }
        }, 500);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(PostResult paramPostResult) {
        if (paramPostResult.getTag().equals("eventbus_gift_select")) {
            if (status != 1) {
                relateQuickSendGift.setVisibility(View.GONE);
            }
            tvGiftNum.setVisibility(View.VISIBLE);
            tvOkBtn.setVisibility(View.VISIBLE);
            setNumView(false);
            liveGiftGiveCount.clear();
            giftBean                  = (LiveGiftInfoBean.DataBean) paramPostResult.getResult();
            LiveRoomFragment.giftBean = (LiveGiftInfoBean.DataBean) paramPostResult.getResult();
            if (giftBean != null) {
                setGiveGiftNum();
                switch (giftBean.getG_type()) {
                    case 1:
                        tvGiftNum.setText("1");
                        tvGiftNum.setClickable(true);
                        break;
                    default:
                        tvGiftNum.setText("1");
                        tvGiftNum.setClickable(false);
                        break;
                }
            }
        }
    }

    public void onCreate(@Nullable Bundle paramBundle) {
        super.onCreate(paramBundle);
        setStyle(1, R.style.MyDialog);
        setCancelable(true);
        EventBus.getDefault().register(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.systemUiVisibility = 2050;
        window.setAttributes(layoutParams);
        View view = paramLayoutInflater.inflate(R.layout.live_gift_dialog, paramViewGroup, false);
        getDialog().setCanceledOnTouchOutside(true);
        setWindow();
        this.unbinder = ButterKnife.bind(this, view);
        tvBalance     = view.findViewById(R.id.tv_balance);
        return view;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        if (liveGiftGiveCount != null) {
            liveGiftGiveCount.clear();
            liveGiftGiveCount = null;
        }
        if (animatorexperience != null) {
            animatorexperience.cancel();
            animatorexperience = null;
        }
        super.onDestroyView();
        this.unbinder.unbind();
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
    }

    public void onStart() {
        super.onStart();
        //        if (LiveRoomFragment.giftBean == null) {
        //            if (mGiftDatas != null) {
        //                giftBean = mGiftDatas.get(0);
        //            }
        //        } else {
        if (null != mGiftDatas) {
            giftBean = mGiftDatas.get(0);
        }
        //        }
        setGiveGiftNum();
        initFullScreen();
        initPW();
        setupViewPager(liveGiftVp, mIndex);
        AppApis.getGetExpLevelInfo(this);
    }

    public void onSuccess(String url, Object obj) {
        if (url.equals(Urls.GET_GIFT_LIST) && obj != null && obj instanceof LiveGiftInfoBean) {
            LiveGiftInfoBean liveGiftInfo = (LiveGiftInfoBean) obj;
            if (liveGiftInfo.getCode() == 200) {
                if (fragment != null) {
                    fragment.setGiftData(liveGiftInfo.getData());
                }
            }
        } else if (url.equals(Urls.GET_LIVE_SEND_GIFT) || url.equals(Urls.GET_LIVE_SEND_POCKET_GIFT)) {
            //            {"msg":"成功","code":200,"data":{"experience":9900}}
            try {
                JSONObject object = new JSONObject(obj.toString());
                if (object.getInt("code") == 200) {
                    JSONObject object1 = new JSONObject(object.getString("data"));
                    int experience = object1.getInt("experience");
                    if (pbProgressbar != null && info != null) {
                        int exp = pbProgressbar.getProgress() + experience;
                        pbProgressbar.setProgress(exp);
                        int next = info.getData().getNext() - exp;
                        if (next > 0) {
                            tvExperience.setText("需" + next + "升级");
                        }
                        if (exp >= info.getData().getNext()) {
                            AppApis.getGetExpLevelInfo(this);
                        }
                    }
                    int balance = info.getData().getMoney() - money;
                    info.getData().setMoney(balance);
                    tvBalance.setText(balance + "");
                    if (sendPid > 1) {
                        if (status == 1) {
                            status = 0;
                            return;
                        }
                        for (int i = 0; i < mBackpackDatas.size(); i++) {
                            LiveGiftInfoBean.DataBean dataBean = mBackpackDatas.get(i);
                            if (dataBean.getP_id() == sendPid) {
                                dataBean.setP_count(dataBean.getP_count() - sendCount);
                            }
                        }


                        Iterator<LiveGiftInfoBean.DataBean> iterator = mBackpackDatas.iterator();
                        while (iterator.hasNext()) {
                            LiveGiftInfoBean.DataBean next = iterator.next();
                            if (next.getP_count() <= 0) {
                                iterator.remove();
                                if (!mBackpackDatas.isEmpty()) {
                                    mBackpackDatas.get(0).setSelect_item(true);
                                } else {
                                    giftBean = null;
                                }
                            }
                        }
                        backPackfragment.setPocketData();
                        sendPid   = 0;
                        sendCount = 0;
                    }
                } else {
                    ToastUtils.showShortToast(object.getString("msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (tvOkBtn != null)
                    tvOkBtn.setEnabled(true);
            }
        } else if (url.equals(Urls.GET_EXPLEVEL_INFO)) {
            if (obj != null && obj instanceof ExpLevelInfo) {
                info = (ExpLevelInfo) obj;
                if (info.getCode() == 200) {
                    tvLevel.setText("LV" + info.getData().getUserGrade());
                    pbProgressbar.setMax(info.getData().getNext());
                    pbProgressbar.setProgress(info.getData().getUserExperience());
                    tvBalance.setText(info.getData().getMoney() + "");
                    int exp = info.getData().getNext() - info.getData().getUserExperience();
                    tvExperience.setText("需" + exp + "升级");
                }
            }
        }
    }

    @Override
    public void onStartRequest() {

    }

    public void setCancelListener(OnClickDataListener paramOnClickDataListener) {
        this.mClickDataListener = paramOnClickDataListener;
    }

    protected void setWindow() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width   = -1;
            layoutParams.height  = -2;
            layoutParams.gravity = 80;
            int i = this.animId;
            if (i != -1)
                window.setWindowAnimations(i);
            window.setAttributes(layoutParams);
        }
    }

    public void show(FragmentManager paramFragmentManager, String paramString) {
        try {
            Class<?> clazz = Class.forName("android.support.v4.app.DialogFragment");
            Object object = clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
            Field field2 = clazz.getDeclaredField(" mDismissed");
            field2.setAccessible(true);
            field2.set(object, Boolean.valueOf(false));
            Field field1 = clazz.getDeclaredField("mShownByMe");
            field1.setAccessible(true);
            field1.set(object, Boolean.valueOf(false));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        FragmentTransaction fragmentTransaction = paramFragmentManager.beginTransaction();
        fragmentTransaction.add((Fragment) this, paramString);
        fragmentTransaction.commitAllowingStateLoss();
    }

    // Intent intent = new Intent(getContext(), DiamondTopUpActivity.class);
    //                    startActivity(intent);
    @OnClick({R.id.tv_gift_num, R.id.tv_ok_btn, R.id.relate_quick_send_gift, R.id.tv_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_gift_num:
                setNumView(liveGiftGiveRv.getVisibility() == View.GONE ? true : false);
                break;
            case R.id.tv_ok_btn:
                setNumView(false);
                setOkBtn();
                break;
            case R.id.relate_quick_send_gift:
                sendGiftCombo();
                break;
            case R.id.tv_balance:
                Intent intent = new Intent(getContext(), DiamondTopUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    public int isCombo(int is_combo, LiveGiftInfoBean.DataBean liveGiftInfo) {
        // 播放小礼物动画 add by ade
        curTime = System.currentTimeMillis();
        if (combo_interval.containsKey(liveGiftInfo.getId())) {
            if (curTime > (combo_interval.get(liveGiftInfo.getId()) + 3000)) {
                curIndex = 1;
                is_combo = 0;
            } else {
                curIndex++;
                is_combo = 1;
            }
            combo_interval.put(liveGiftInfo.getId(), curTime);
        } else {
            combo_interval.put(liveGiftInfo.getId(), curTime);
        }
        return is_combo;
    }


    public static interface OnClickDataListener {
        void onClickData(LiveGiftInfoBean.DataBean param1DataBean);

        void onDismiss(DialogInterface param1DialogInterface, LiveGiftInfoBean.DataBean param1DataBean);

        void onGoPay();
    }
}