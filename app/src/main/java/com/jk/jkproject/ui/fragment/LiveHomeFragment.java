package com.jk.jkproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.base.BaseFragmentPageAdapter;
import com.jk.jkproject.ui.activity.ContributionListActivity;
import com.jk.jkproject.ui.activity.LiveHomeMessageActivity;
import com.jk.jkproject.ui.activity.LiveHomeSearchActivity;
import com.jk.jkproject.ui.adapter.HomeTabRVAdapter;
import com.jk.jkproject.ui.entity.HomeTabInfo;
import com.jk.jkproject.ui.widget.CatonViewPager;
import com.jk.order.PlayWithUFragment;
import com.jk.weblib.EsportsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LiveHomeFragment extends BFragment implements ViewPager.OnPageChangeListener {
    private static String tab_type = "tab_type";
    @BindView(R.id.tab_rv)
    RecyclerView tabRv;
    @BindView(R.id.live_top_search)
    ImageView    liveTopSearch;
    @BindView(R.id.live_main_top_title)
    LinearLayout   liveMainTopTitle;
    @BindView(R.id.live_vp_pager)
    CatonViewPager vp;
    @BindView(R.id.live_top_crown)
    ImageView      liveTopCrown;
    @BindView(R.id.live_top_message)
    ImageView      liveTopMessage;
    @BindView(R.id.live_top_esport)
    ImageView      esport;
    Unbinder unbinder;

    private HomeTabRVAdapter adapter;

    private List<Fragment> fragList = new ArrayList<Fragment>();

    private FragmentManager fragmentManager;
    private BaseFragmentPageAdapter mAdapter;

    private String tabName = "";


    private List<HomeTabInfo> tabs = new ArrayList<HomeTabInfo>();

    private void initRv() {
        this.tabRv.setLayoutManager((RecyclerView.LayoutManager) new LinearLayoutManager(getContext(), 0, false));
        this.tabs.add(new HomeTabInfo("关注", false, 0));
        this.tabs.add(new HomeTabInfo("热门", true, 1));
        this.tabs.add(new HomeTabInfo("附近", false, 2));
        this.adapter = new HomeTabRVAdapter(getContext(), this.tabs);
        this.tabRv.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new HomeTabRVAdapter.OnItemClickListener() {
            public void click(HomeTabInfo param1HomeTabInfo, int param1Int) {
                LiveHomeFragment.this.vp.setCurrentItem(param1Int);
                LiveHomeFragment.this.tabRv.scrollToPosition(param1Int);
            }
        });
    }

    public void showEsport() {
            esport.setVisibility(View.VISIBLE);
    }


    private void initVp() {
        fragList.add(LiveHomeFocusFragment.newInstance(0));
        fragList.add(LiveHomeHotFragment.newInstance(1));
        fragList.add(LiveHomeNearParentFragment.newInstance(2));
        fragmentManager = getChildFragmentManager();
        mAdapter        = new BaseFragmentPageAdapter(fragmentManager, fragList);
        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(this);
        vp.setOffscreenPageLimit(fragList.size());
        vp.setCurrentItem(1);
        SharedPreferences esportsUrl = getActivity().getSharedPreferences("esportsUrl", Context.MODE_PRIVATE);
        if (esportsUrl.getBoolean("esportShown",false)){
            showEsport();
        }
    }

    public static LiveHomeFragment newInstance(String paramString) {
        LiveHomeFragment liveHomeFragment = new LiveHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(tab_type, paramString);
        liveHomeFragment.setArguments(bundle);
        return liveHomeFragment;
    }

    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            tabName = getArguments().getString(tab_type);
        }
    }

    protected void initView(View paramView) {
        super.initView(paramView);
        initVp();
        initRv();
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    public void onPageScrollStateChanged(int paramInt) {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    }

    public void onPageSelected(int paramInt) {
        if (adapter != null) {
            adapter.set(paramInt, true);
            tabRv.scrollToPosition(paramInt);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            SharedPreferences esportsUrl = getActivity().getSharedPreferences("esportsUrl", Context.MODE_PRIVATE);
            if (esportsUrl.getBoolean("esportShown", false)) {
                showEsport();
            }
        }
    }

    protected int setLayoutId() {
        return R.layout.fragment_live_home;
    }

    @OnClick({R.id.live_top_crown, R.id.live_top_search, R.id.live_top_message, R.id.live_top_esport})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_top_crown:
                startActivity(new Intent(getContext(), ContributionListActivity.class));
                break;
            case R.id.live_top_search:
                startActivity(new Intent(getContext(), LiveHomeSearchActivity.class));
                break;
            case R.id.live_top_message:
                startActivity(new Intent(getContext(), LiveHomeMessageActivity.class));
                break;
            case R.id.live_top_esport:
                EsportsActivity.start(getContext(), 1);
                break;
        }
    }
}
