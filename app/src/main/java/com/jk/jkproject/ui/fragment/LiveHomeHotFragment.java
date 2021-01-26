package com.jk.jkproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.activity.WebViewActivity;
import com.jk.jkproject.ui.adapter.LiveHomeHotAdapter;
import com.jk.jkproject.ui.adapter.LiveHotHeaderAdapter;
import com.jk.jkproject.ui.entity.BannerBeanInfo;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.widget.CircleIndicator;
import com.jk.jkproject.ui.widget.SpaceItemDecoration;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;
import com.jk.jkproject.ui.widget.room.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @author Zick
 * @params
 * @date 2020/7/31 10:58 AM
 * @desc 首页热门
 */
public class LiveHomeHotFragment extends BFragment implements RefreshRecyclerViewListener, AutoScrollViewPager.CallBack {
    private static String tab_type = "tab_type";
    @BindView(R.id.live_new_recycler_view)
    WrapperRecyclerView mRecyclerView;
    @BindView(R.id.network_no_title)
    TextView networkNoTitle;
    @BindView(R.id.network_no_des)
    TextView networkNoDes;
    @BindView(R.id.btn_network_no_reload)
    Button btnNetworkNoReload;
    @BindView(R.id.layout_network_no_root)
    LinearLayout layoutNetworkNoRoot;
    @BindView(R.id.loading_cpb_progress)
    CircularProgressBar loadingCpbProgress;
    @BindView(R.id.layout_loading_root)
    LinearLayout layoutLoadingRoot;

    private View.OnClickListener bannerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Object obj = v.getTag(R.id.tag_position);
            if (obj != null && obj instanceof Integer) {
                int position = (int) obj;
                BannerBeanInfo.DataBean info = headerAdapter.getItem(position);
                if (info != null && !TextUtils.isEmpty(info.getArrive())) {
                    Intent intent = null;
                    switch (info.getType()) {
                        case "1":
                            intent = new Intent(getActivity(), WebViewActivity.class);
                            intent.putExtra("type", 11);
                            intent.putExtra("url", info.getArrive());
                            startActivity(intent);
                            break;
                        case "2":
                            intent = new Intent(getActivity(), SlideActivity.class);
                            intent.putExtra("roomId", info.getArrive());
                            startActivity(intent);
                            break;
                    }
                }
            }
        }
    };

    private LiveHotHeaderAdapter headerAdapter;

    private AutoScrollViewPager headerPager;

    private int hotNum = 0;

    private int isHasData = 0;

    private boolean isRefresh = true;


    private List<LiveHomeBeanInfo.DataBean> list;


    private LiveHomeHotAdapter mAdapter;

    private View mHeaderView;

    private int page = 0;

    private int recommNum = 0;

    private CircleIndicator tab;

    private int tabName;
    private BannerBeanInfo bannerBeanInfo;

    private void addHeaderView() {
        mHeaderView = LayoutInflater.from((Context) getActivity()).inflate(R.layout.layout_live_hot_header_view, (ViewGroup) this.mRecyclerView, false);
        headerPager = (AutoScrollViewPager) getView(this.mHeaderView, R.id.live_vp_pager);
        headerAdapter = new LiveHotHeaderAdapter((Context) getActivity());
        headerPager.setInterval(getResources().getInteger(R.integer.banner_interval));
        headerPager.setAdapter((PagerAdapter) this.headerAdapter);
        headerPager.setOffscreenPageLimit(1);
        tab = (CircleIndicator) getView(this.mHeaderView, R.id.live_viewpager_tab);
        tab.setViewPager((ViewPager) this.headerPager);
        headerPager.setListener(this);
    }

    private void dismissFailView() {
        if (layoutNetworkNoRoot != null) {
            layoutNetworkNoRoot.setVisibility(View.GONE);
        }
    }

    private void dismissLoading() {
        if (layoutLoadingRoot != null) {
            layoutLoadingRoot.setVisibility(View.GONE);
        }
    }

    private void getData(int paramInt) {
        AppApis.getHomeHotData(paramInt, this);
    }

    public static LiveHomeHotFragment newInstance(int paramInt) {
        LiveHomeHotFragment liveHomeHotFragment = new LiveHomeHotFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(tab_type, paramInt);
        liveHomeHotFragment.setArguments(bundle);
        return liveHomeHotFragment;
    }

    private void setData(LiveHomeBeanInfo paramLiveHomeBeanInfo) {
        if (paramLiveHomeBeanInfo != null) {
            try {
                if (paramLiveHomeBeanInfo.getData() == null || paramLiveHomeBeanInfo.getData().size() == 0) {
                    if (paramLiveHomeBeanInfo != null) {
                        if (paramLiveHomeBeanInfo.getData().size() <= Integer.parseInt(Urls.CURRENTCOUNT))
                            this.isHasData = 0;
                    }
//                    this.mAdapter.showNoMoreDataView();
                    if (isRefresh) {
                        mAdapter.clear(true);
                    }
                    this.mRecyclerView.loadMoreComplete();
                    this.mRecyclerView.refreshComplete();
//                    showFailView();
                    return;
                }
                this.mRecyclerView.setVisibility(View.VISIBLE);
                if (paramLiveHomeBeanInfo.getData().size() > Integer.parseInt(Urls.CURRENTCOUNT)) {
                    this.isHasData = 1;
                }
                if (this.isRefresh) {
                    this.isRefresh = false;
                    this.mRecyclerView.refreshComplete();
                    if (this.mAdapter != null) {
                        this.hotNum = 0;
                        this.mAdapter.clear(true);
                        this.hotNum = paramLiveHomeBeanInfo.getData().size();
                        if (paramLiveHomeBeanInfo.getData() != null && paramLiveHomeBeanInfo.getData().size() > 0)
                            this.mAdapter.addAll(paramLiveHomeBeanInfo.getData(), false);
                    }
                    this.mRecyclerView.getRecyclerView().scrollToPosition(0);
                } else if (this.mAdapter != null && this.mAdapter.getBasicItemCount() == 0) {
                    this.hotNum = 0;
                    this.hotNum = paramLiveHomeBeanInfo.getData().size();
                    if (paramLiveHomeBeanInfo.getData() != null && paramLiveHomeBeanInfo.getData().size() > 0)
                        this.mAdapter.addAll(paramLiveHomeBeanInfo.getData(), false);
                }
            } catch (Exception exception) {
                showFailView();
                exception.printStackTrace();
            }
            return;
        }
        if (paramLiveHomeBeanInfo != null) {
            if (paramLiveHomeBeanInfo.getData().size() <= Integer.parseInt(Urls.CURRENTCOUNT))
                isHasData = 0;
        }
        this.mAdapter.showNoMoreDataView();
        this.mRecyclerView.loadMoreComplete();
        this.mRecyclerView.refreshComplete();
        showFailView();
    }

    private void showFailView() {
        if (layoutNetworkNoRoot != null) {
            layoutNetworkNoRoot.setVisibility(View.VISIBLE);
        }
    }

    private void showLoading() {
        if (layoutLoadingRoot != null) {
            layoutLoadingRoot.setVisibility(View.VISIBLE);
        }
    }

    public void OnListener(String paramString1, String paramString2) {
        if (paramString1.equals("ACTION_DOWN")) {
            if (mRecyclerView != null) {
                mRecyclerView.disableRefresh();
            }
        }
        if (paramString1.equals("ACTION_UP")) {
            if (mRecyclerView != null) {
                mRecyclerView.enableRefresh();
            }
        }
    }

    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            tabName = getArguments().getInt(tab_type);
        }
        getData(page);
        AppApis.getHomeBanndData(this);
    }

    protected void initView(View paramView) {
        super.initView(paramView);
        this.list = new ArrayList<>();
        this.mAdapter = new LiveHomeHotAdapter((Context) getActivity(), this.list);
        this.mRecyclerView.setRecyclerViewListener(this);
        addHeaderView();
        this.mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView((Context) getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int param1Int) {
                return (param1Int == 0 && LiveHomeHotFragment.this.recommNum == 1) ? 2 : ((param1Int == LiveHomeHotFragment.this.hotNum) ? 1 : 1);
            }
        });
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(mContext, 9));
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        this.mRecyclerView.setLayoutManager((RecyclerView.LayoutManager) gridLayoutManager);
        this.mRecyclerView.getPtrFrameLayout().setHorizontalMoveArea(new PtrFrameLayout.HorizontalMoveArea() {
            public boolean isHorizontalMoveArea(float param1Float1, float param1Float2) {
                Rect rect = new Rect();
                if (LiveHomeHotFragment.this.mHeaderView != null)
                    LiveHomeHotFragment.this.mHeaderView.getLocalVisibleRect(rect);
                return rect.contains((int) param1Float1, (int) param1Float2);
            }
        });
        this.mRecyclerView.setAdapter((BaseWrapperRecyclerAdapter) this.mAdapter);
        this.mFlagShowNoNetworkToast = false;
        this.mFlagShowDefaultFailToast = false;
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    public void loadData() {
        getData(this.page);
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        dismissLoading();
        if (mRecyclerView != null) {
            mRecyclerView.refreshComplete();
            this.mRecyclerView.loadMoreComplete();
        }
        if (Urls.GET_HOME_HOT_LIST.equals(paramString1))
            showFailView();
    }

    public void onLoadMore(int paramInt1, int paramInt2) {
        if (this.isHasData == 1) {
            this.mAdapter.showLoadMoreView();
            getData(this.page);
        }
    }

    public void onPause() {
        if (headerPager != null)
            headerPager.stopAutoScroll();
        super.onPause();
    }

    public void onRefresh() {
        this.isRefresh = true;
        this.page = 0;
        this.mRecyclerView.enableLoadMore();
        this.mRecyclerView.loadMoreComplete();
        getData(this.page);
        AppApis.getHomeBanndData(this);
    }

    public void onResume() {
        super.onResume();
        AutoScrollViewPager autoScrollViewPager = this.headerPager;
        if (autoScrollViewPager != null && autoScrollViewPager.getChildCount() > 1 && this.headerPager.isCurrentAutoScroll())
            this.headerPager.startAutoScroll();
    }

    public void onSuccess(String paramString, Object paramObject) {
        LiveHomeBeanInfo liveHomeBeanInfo;
        super.onSuccess(paramString, paramObject);
        dismissLoading();
        if (paramString.equals(Urls.GET_HOME_HOT_LIST)) {
            if (paramObject != null && paramObject instanceof LiveHomeBeanInfo) {
                liveHomeBeanInfo = (LiveHomeBeanInfo) paramObject;
                if (liveHomeBeanInfo != null && liveHomeBeanInfo.getCode() == 200) {
                    setData(liveHomeBeanInfo);
                } else {
                    this.mRecyclerView.refreshComplete();
                    this.mRecyclerView.loadMoreComplete();
                }
            } else {
                this.mRecyclerView.refreshComplete();
                this.mRecyclerView.loadMoreComplete();
            }
        } else if (paramString.equals(Urls.GET_HOME_BANNER_LIST) && paramObject != null && paramObject instanceof BannerBeanInfo) {
            bannerBeanInfo = (BannerBeanInfo) paramObject;
            if (bannerBeanInfo.getData() != null) {
                int i = bannerBeanInfo.getData().size();
                if (i > 0) {
                    this.recommNum = 1;
                    if (!this.mAdapter.isHeaderView(0))
                        this.mAdapter.addHeaderView(this.mHeaderView);
                    this.headerAdapter.setdata(bannerBeanInfo.getData());
                    this.headerAdapter.setBannerClickListener(this.bannerClickListener);
                    this.headerPager.setAdapter((PagerAdapter) this.headerAdapter);
                    this.headerPager.setOffscreenPageLimit(i);
                    this.tab.setViewPager((ViewPager) this.headerPager);
                    if (i > 1) {
                        this.headerAdapter.setInfiniteLoop(true);
                        this.headerPager.startAutoScroll(R.integer.banner_interval);
                    }
                } else {
                    this.recommNum = 0;
                    if (this.mAdapter.isHeaderView(0))
                        this.mAdapter.removeHeaderView(this.mHeaderView);
                }
            } else if (this.mAdapter.isHeaderView(0)) {
                this.recommNum = 0;
                this.mAdapter.removeHeaderView(this.mHeaderView);
            }
        }
    }

    protected int setLayoutId() {
        return R.layout.fragment_home_hot;
    }


    @OnClick(R.id.btn_network_no_reload)
    public void onViewClicked() {
        dismissFailView();
        showLoading();
        page = 0;
        getData(page);
    }
}