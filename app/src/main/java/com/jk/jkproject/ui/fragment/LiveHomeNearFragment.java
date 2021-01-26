package com.jk.jkproject.ui.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.LiveHomeNearAdapter;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.widget.SpaceNearItemDecoration;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class LiveHomeNearFragment extends BFragment implements RefreshRecyclerViewListener {
    private static String tab_type = "tab_type";

    @BindView(R.id.tv_title_1)
    TextView tvTitle1;
    @BindView(R.id.iv_tab_line)
    ImageView ivTabLine;
    @BindView(R.id.tv_title_2)
    TextView tvTitle2;
    @BindView(R.id.iv_tab_line1)
    ImageView ivTabLine1;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.live_new_recycler_view)
    WrapperRecyclerView recyclerView;
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
    private int isHasData;

    private boolean isRefresh = false;

    private List<LiveHomeBeanInfo.DataBean> list;


    private LiveHomeNearAdapter mAdapter;


    private int page = 0;


    private int tabName;


    private int type = 1;

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

    public void getData(int paramInt) {
        AppApis.getHomeNearData(paramInt, 1, this);
    }

    public static LiveHomeNearFragment newInstance(int paramInt) {
        LiveHomeNearFragment liveHomeNearFragment = new LiveHomeNearFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(tab_type, paramInt);
        liveHomeNearFragment.setArguments(bundle);
        return liveHomeNearFragment;
    }

    private void setData(LiveHomeBeanInfo info) {
        if (info == null || info.getData() == null || info.getData().size() == 0) {
            if (info != null) {
                isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            if (isRefresh) {
                mAdapter.clear(true);
            }
            try {
                mAdapter.showNoMoreDataView();
                recyclerView.loadMoreComplete();
                recyclerView.refreshComplete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        //当触发下拉刷新时
        if (isRefresh) {
            isRefresh = false;
            recyclerView.refreshComplete();

            if (mAdapter != null) {
                mAdapter.clear(true);
                if (list.size() > 0) {
                    list.clear();
                    list = info.getData();
                }
                mAdapter.addAll(info.getData(), true);
            }

            recyclerView.getRecyclerView().scrollToPosition(0);

            if (info.getData().size() > 0 && isHasData == 1) {
                page++;
            }
        } else {
            // 第一次加载数据
            if (mAdapter != null && mAdapter.getBasicItemCount() == 0) {
                mAdapter.addAll(info.getData(), true);
                if (info.getData().size() > 0 && isHasData == 1) {
                    page++;
                }
            } else {
                //加载更多时
                int size = info.getData().size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        LiveHomeBeanInfo.DataBean dataBean = info.getData().get(i);
                        boolean addResult = true;
                        for (int j = 0; j < mAdapter.getList().size(); j++) {
                            LiveHomeBeanInfo.DataBean dataBean2 = mAdapter.getList().get(j);
                            if (dataBean.getU_id() == dataBean2.getU_id()) {
                                addResult = false;
                                break;
                            }
                        }

                        if (addResult) {
                            mAdapter.add(dataBean);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }
                if (info.getData().size() > 0 && isHasData == 1) {
                    recyclerView.loadMoreComplete();
                    mAdapter.showNoMoreDataView();
                    page++;
                } else {
                    mAdapter.showNoMoreDataView();
                    recyclerView.loadMoreComplete();
                }
            }
        }
    }

    private void showFailView() {
        LinearLayout linearLayout = this.layoutNetworkNoRoot;
        if (linearLayout != null)
            linearLayout.setVisibility(View.VISIBLE);
    }

    private void showLoading() {
        LinearLayout linearLayout = this.layoutLoadingRoot;
        if (linearLayout != null)
            linearLayout.setVisibility(View.VISIBLE);
    }

    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            tabName = getArguments().getInt(tab_type);
            LogUtils.e("tabName=", tabName);
        }
    }

    protected void initView(View paramView) {
        super.initView(paramView);
        list = new ArrayList<>();
        mAdapter = new LiveHomeNearAdapter(getActivity(), this.list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(), 3);
        recyclerView.addItemDecoration(new SpaceNearItemDecoration(mContext,9));
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) gridLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        recyclerView.getPtrFrameLayout().setHorizontalMoveArea((param1Float1, param1Float2) -> (new Rect()).contains((int) param1Float1, (int) param1Float2));
        recyclerView.setAdapter((BaseWrapperRecyclerAdapter) this.mAdapter);
        mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView(getContext()));
        mAdapter.setOnItemClickListener((param1View, param1String) -> {
        });
        recyclerView.setRecyclerViewListener(this);
        mFlagShowNoNetworkToast = false;
        mFlagShowDefaultFailToast = false;
        getData(page);
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        dismissLoading();
        this.recyclerView.refreshComplete();
        this.recyclerView.loadMoreComplete();
        if (Urls.GET_HOME_NEAR_LIST.equals(paramString1) && this.page == 1) {
            showFailView();
        }
    }

    public void onLoadMore(int paramInt1, int paramInt2) {
        if (this.isHasData == 1) {
            this.mAdapter.showLoadMoreView();
            getData(this.page);
        }
    }

    public void onRefresh() {
        this.isRefresh = true;
        this.page = 0;
        this.recyclerView.enableLoadMore();
        this.recyclerView.loadMoreComplete();
        getData(this.page);
    }

    public void onSuccess(String paramString, Object paramObject) {
        super.onSuccess(paramString, paramObject);
        dismissLoading();
        if (Urls.GET_HOME_NEAR_LIST.equals(paramString))
            if (paramObject != null && paramObject instanceof LiveHomeBeanInfo) {
                LiveHomeBeanInfo liveHomeNearBeanInfo = (LiveHomeBeanInfo) paramObject;
                if (liveHomeNearBeanInfo.getCode() == 200) {
                    setData(liveHomeNearBeanInfo);
                } else {
                    this.recyclerView.refreshComplete();
                    this.recyclerView.loadMoreComplete();
                }
            } else {
                this.recyclerView.refreshComplete();
                this.recyclerView.loadMoreComplete();
            }
    }

    protected int setLayoutId() {
        return R.layout.fragment_home_near;
    }

    @OnClick(R.id.btn_network_no_reload)
    public void onViewClicked() {
        dismissFailView();
        showLoading();
        this.page = 0;
        getData(this.page);
    }
}