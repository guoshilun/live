package com.jk.jkproject.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.base.BaseFragmentPageAdapter;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.ui.widget.NoScrollViewPager;
import com.jk.jkproject.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/8/28 9:42 AM
 * @desc 粉丝贡献榜Fragment
 */
public class LiveContributionParentFragment3 extends BFragment implements ViewPager.OnPageChangeListener {
    private static String tab_type = "tab_type";
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.rl_item)
    RelativeLayout rlItem;
    @BindView(R.id.live_vp_pager)
    NoScrollViewPager vp;
    @BindView(R.id.iv_small)
    ImageView ivSmall;
    @BindView(R.id.iv_emply)
    ImageView ivEmply;

    Unbinder unbinder;
    private List<Fragment> fragList = new ArrayList<Fragment>();

    private String targetId;
    private FragmentManager fragmentManager;
    private BaseFragmentPageAdapter mAdapter;
    private LiveContributionListFragment3 fragment,fragment1,fragment2;


    public static LiveContributionParentFragment3 newInstance(String position) {
        LiveContributionParentFragment3 liveHomeNearFragment = new LiveContributionParentFragment3();
        Bundle bundle = new Bundle();
        bundle.putString(tab_type, position);
        liveHomeNearFragment.setArguments(bundle);
        return liveHomeNearFragment;
    }


    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            targetId = getArguments().getString(tab_type);
        }
        ivSmall.setImageResource(R.mipmap.icon_fans_contribution_head_bg);
        initVp();
    }

    private void initVp() {
        vp.setNoScroll(true);
        fragment = LiveContributionListFragment3.newInstance(targetId, 1);
        fragList.add(fragment);
        fragment1 = LiveContributionListFragment3.newInstance(targetId, 2);
        fragList.add(fragment1);
        fragment2 = LiveContributionListFragment3.newInstance(targetId, 3);
        fragList.add(fragment2);
        fragmentManager = getChildFragmentManager();
        mAdapter = new BaseFragmentPageAdapter(fragmentManager, fragList);
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(fragList.size());
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(this);
        rlItem.setVisibility(View.GONE);
    }

    protected void initView(View paramView) {
        super.initView(paramView);
    }

    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals(Constants.EVENTBUS.EVENTBUS_CONTRIBUTION_FANS_EMPLY)) {
            switch ((int) postResult.getResult()) {
                case 1:
                    ivEmply.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ivEmply.setVisibility(View.GONE);
                    break;
            }
        }
    }

    protected int setLayoutId() {
        return R.layout.fragment_contribution_parent;
    }

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                setInitBg(0);
                vp.setCurrentItem(0);
                if (fragment != null) {
                    fragment.getData(0);
                }
                break;
            case R.id.tv_2:
                setInitBg(1);
                vp.setCurrentItem(1);
                if (fragment1 != null) {
                    fragment1.getData(0);
                }
                break;
            case R.id.tv_3:
                setInitBg(2);
                vp.setCurrentItem(2);
                if (fragment2 != null) {
                    fragment2.getData(0);
                }
                break;
        }
    }

    private void setInitBg(int position) {
        tv1.setBackgroundColor(getResources().getColor(R.color.transparent));
        tv2.setBackgroundColor(getResources().getColor(R.color.transparent));
        tv3.setBackgroundColor(getResources().getColor(R.color.transparent));
        switch (position) {
            case 0:
                tv1.setBackgroundResource(R.drawable.bg_day_btn);
                break;
            case 1:
                tv2.setBackgroundResource(R.drawable.bg_day_btn);
                break;
            case 2:
                tv3.setBackgroundResource(R.drawable.bg_day_btn);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setInitBg(position);
        vp.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}