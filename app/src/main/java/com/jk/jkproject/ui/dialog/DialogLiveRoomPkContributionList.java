package com.jk.jkproject.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BaseFragmentPageAdapter;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.fragment.LiveHomePKContribution2Fragment;
import com.jk.jkproject.ui.fragment.LiveHomePKContributionFragment;
import com.jk.jkproject.ui.widget.NoScrollViewPager;
import com.jk.jkproject.utils.ScreenUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint({"ValidFragment"})
public class DialogLiveRoomPkContributionList extends DialogFragment implements ViewPager.OnPageChangeListener {


    @BindView(R.id.tv_left_name)
    TextView tvLeftName;
    @BindView(R.id.tv_right_name)
    TextView tvRightName;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.live_vp_pager)
    NoScrollViewPager vp;
    protected int animId = -1;
    private OnClickDataListener mClickDataListener;

    private Context mContext;

    private FragmentManager supportFragmentManager;
    Unbinder unbinder;
    private List<Fragment> fragList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private BaseFragmentPageAdapter mAdapter;
    private int mType = 0;
    private String userId, tagUserId;
    private String pkId;
    private String roomId, tagRoomId;
    private LiveHomePKContributionFragment contributionFragment;
    private LiveHomePKContribution2Fragment contribution2Fragment;

    public DialogLiveRoomPkContributionList(Context context, int mType, String pkid, String userId, String roomId, String tagUserId, String tagRoomId) {
        this.mContext = context;
        this.mType = mType;
        this.pkId = pkid;
        this.userId = userId;
        this.roomId = roomId;
        this.tagUserId = tagUserId;
        this.tagRoomId = tagRoomId;
    }

    public DialogLiveRoomPkContributionList(Context context) {
        this.mContext = context;

    }

    private void initFullScreen() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.width = -1;
                layoutParams.height = ScreenUtils.dp2px(mContext, 443);
                layoutParams.dimAmount = 0.0F;
                window.setAttributes(layoutParams);
            }
        }
    }

    private void setupViewPager() {
        contributionFragment = LiveHomePKContributionFragment.newInstance(1);
        contribution2Fragment = LiveHomePKContribution2Fragment.newInstance(2);
        fragList.add(contributionFragment);
        fragList.add(contribution2Fragment);
        fragmentManager = getChildFragmentManager();
        mAdapter = new BaseFragmentPageAdapter(this.fragmentManager, fragList);
        vp.setAdapter((PagerAdapter) mAdapter);
        vp.setOffscreenPageLimit(fragList.size());
//        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(this);
        setData(mType, pkId, userId, roomId, tagUserId, tagRoomId);
    }

    @Subscribe
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
        View view = paramLayoutInflater.inflate(R.layout.dialog_live_room_pk_contribution, paramViewGroup, false);
        getDialog().setCanceledOnTouchOutside(true);
        setWindow();
        this.unbinder = ButterKnife.bind(this, view);
        setupViewPager();
        return view;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        this.unbinder.unbind();
    }


    public void onStart() {
        super.onStart();
        initFullScreen();
    }

    public void setCancelListener(OnClickDataListener paramOnClickDataListener) {
        this.mClickDataListener = paramOnClickDataListener;
    }

    protected void setWindow() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = -1;
            layoutParams.height = ScreenUtils.dp2px(mContext, 443);
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


    @OnClick({R.id.tv_left_name, R.id.tv_right_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left_name:
                tvLeftName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvRightName.setTypeface(Typeface.DEFAULT);
                tvLeftName.setBackgroundResource(R.drawable.bg_pk_contribution);
                tvRightName.setBackgroundResource(R.drawable.bg_pk_contribution_default);
                vp.setCurrentItem(0);
                break;
            case R.id.tv_right_name:
                tvRightName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvLeftName.setTypeface(Typeface.DEFAULT);
                tvRightName.setBackgroundResource(R.drawable.bg_pk_contribution);
                tvLeftName.setBackgroundResource(R.drawable.bg_pk_contribution_default);
                vp.setCurrentItem(1);
                break;
        }
    }

    public void setData(int mType, String pkId, String userId, String roomId, String tagUserId, String tagRoomId) {
        if (vp != null) {
            vp.setCurrentItem(mType);
        }
        if (contributionFragment != null) {
            contributionFragment.getData(pkId, userId, roomId);
        }
        if (contribution2Fragment != null) {
            contribution2Fragment.getData(pkId, tagUserId, tagRoomId);
        }
    }

    public static interface OnClickDataListener {
        void onClickData(LiveGiftInfoBean.DataBean param1DataBean);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                tvLeftName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvRightName.setTypeface(Typeface.DEFAULT);
                tvLeftName.setBackgroundResource(R.drawable.bg_pk_contribution);
                tvRightName.setBackgroundResource(R.drawable.bg_pk_contribution_default);
                break;
            case 1:
                tvRightName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tvLeftName.setTypeface(Typeface.DEFAULT);
                tvRightName.setBackgroundResource(R.drawable.bg_pk_contribution);
                tvLeftName.setBackgroundResource(R.drawable.bg_pk_contribution_default);
                break;
        }
        this.vp.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}