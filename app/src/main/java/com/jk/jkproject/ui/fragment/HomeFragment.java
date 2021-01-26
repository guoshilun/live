package com.jk.jkproject.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.base.BaseFragmentPageAdapter;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.net.okhttp.ResponseListener;
import com.jk.jkproject.ui.adapter.HomeTabRVAdapter;
import com.jk.jkproject.ui.entity.HomeTabClassify;
import com.jk.jkproject.ui.entity.HomeTabInfo;
import com.jk.jkproject.ui.widget.CatonViewPager;
import com.jk.jkproject.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BFragment implements ViewPager.OnPageChangeListener, Observer {

    private static String tab_type = "tab_type";
    @BindView(R.id.tab_rv)
    RecyclerView tabRv;
    @BindView(R.id.live_top_search)
    ImageView liveTopSearch;
    @BindView(R.id.live_main_top_title)
    LinearLayout liveMainTopTitle;
    @BindView(R.id.live_img_red_dot)
    ImageView liveImgRedDot;
    @BindView(R.id.live_vp_pager)
    CatonViewPager vp;
    private HomeTabRVAdapter adapter;

    private List<HomeTabClassify.DataBean> dataBeans = new ArrayList<HomeTabClassify.DataBean>();

    private List<Fragment> fragList = new ArrayList<Fragment>();

    private FragmentManager fragmentManager;

    private HomeTabClassify homeTabClassify;

    private List<HomeTabInfo> homeTabInfos = new ArrayList<HomeTabInfo>();


    private BaseFragmentPageAdapter mAdapter;

    private String tabName = "";

    private void initRv() {
        tabRv.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        for (byte b = 0; b < this.homeTabClassify.getData().getColumnList().size(); b++) {
            if (b == 0) {
                this.homeTabInfos.add(new HomeTabInfo((this.homeTabClassify.getData().getColumnList().get(b)).getName(), true, ((HomeTabClassify.DataBean.ColumnListBean) this.homeTabClassify.getData().getColumnList().get(b)).getId()));
            } else {
                this.homeTabInfos.add(new HomeTabInfo((this.homeTabClassify.getData().getColumnList().get(b)).getName(), false, ((HomeTabClassify.DataBean.ColumnListBean) this.homeTabClassify.getData().getColumnList().get(b)).getId()));
            }
        }
        adapter = new HomeTabRVAdapter(getContext(), homeTabInfos);
        tabRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomeTabRVAdapter.OnItemClickListener() {
            public void click(HomeTabInfo param1HomeTabInfo, int param1Int) {
                vp.setCurrentItem(param1Int);
                tabRv.scrollToPosition(param1Int);
            }
        });
    }

    private void initToPosition(int paramInt) {
        HomeTabRVAdapter homeTabRVAdapter = this.adapter;
        if (homeTabRVAdapter != null) {
            homeTabRVAdapter.set(paramInt, true);
            this.tabRv.scrollToPosition(paramInt);
        }
    }

    private void initVp() {
        this.fragmentManager = getChildFragmentManager();
        this.mAdapter = new BaseFragmentPageAdapter(this.fragmentManager, this.fragList);
        this.vp.setAdapter((PagerAdapter) this.mAdapter);
        this.vp.addOnPageChangeListener(this);
        this.vp.setOffscreenPageLimit(this.fragList.size());
        this.vp.setCurrentItem(0);
    }

    public static HomeFragment newInstance(String paramString) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(tab_type, paramString);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    protected void initData() {
        super.initData();
        if (getArguments() != null)
            this.tabName = getArguments().getString(tab_type);
    }

    protected void initView(View paramView) {
        super.initView(paramView);
        AppApis.getHomeTabClassify((ResponseListener) this);
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        LogUtils.e("数据获取失败");
        ToastUtils.showShortToast(paramString2);
    }

    public void onPageScrollStateChanged(int paramInt) {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    }

    public void onPageSelected(int paramInt) {
        initToPosition(paramInt);
    }

    public void onSuccess(String paramString, Object paramObject) {
        super.onSuccess(paramString, paramObject);
        if (paramString.equals(Urls.HOME_TAB_CLASSIFY) && paramObject != null && paramObject instanceof HomeTabClassify) {
            this.homeTabClassify = (HomeTabClassify) paramObject;
            if (this.homeTabClassify.getCode() == 200) {
                Collections.sort(this.homeTabClassify.getData().getColumnList(), new Comparator<HomeTabClassify.DataBean.ColumnListBean>() {
                    public int compare(HomeTabClassify.DataBean.ColumnListBean param1ColumnListBean1, HomeTabClassify.DataBean.ColumnListBean param1ColumnListBean2) {
                        return (param1ColumnListBean1.getId() > param1ColumnListBean2.getId()) ? 1 : ((param1ColumnListBean1.getId() == param1ColumnListBean2.getId()) ? 0 : -1);
                    }
                });
                initVp();
                initRv();
            }
        }
    }

    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    public void update(Observable paramObservable, Object paramObject) {
    }

    @OnClick(R.id.live_top_search)
    public void onViewClicked() {
        ToastUtils.showShortToast("点击搜索");
    }
}