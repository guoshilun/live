package com.jk.jkproject.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.RecordsListAdapter;
import com.jk.jkproject.ui.entity.MyRecordsListBean;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;
import com.yuyan.statusbar.StatusBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * @author Zick
 * @params
 * @date 2020/8/24 5:26 PM
 * @desc 用户充值记录
 */
public class TopUpRecordsActivity extends BActivity implements RefreshRecyclerViewListener {


    @BindView(R.id.sbv)
    StatusBarView sbv;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_left_name)
    TextView tvLeftName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_title)
    ImageView ivRightTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
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
    private Unbinder bind;
    private int isHasData;

    private boolean isRefresh = false;

    private List<MyRecordsListBean.DataBean> list;
    private int page = 0;
    private RecordsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_records);
        bind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        list = new ArrayList<>();
        mAdapter = new RecordsListAdapter(this, list);
        recyclerView.setAdapter((BaseWrapperRecyclerAdapter) this.mAdapter);
        mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView(this));
        recyclerView.setRecyclerViewListener(this);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        mFlagShowNoNetworkToast = false;
        mFlagShowDefaultFailToast = false;
        getData(page);
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
        AppApis.getRecordsList(paramInt, this);
    }

    private void setData(MyRecordsListBean info) {
        if (info == null || info.getData() == null || info.getData().size() == 0) {
            if (info != null) {
                isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            if (isRefresh) {
                mAdapter.clear(true);
            }
            mAdapter.showNoMoreDataView();
            recyclerView.loadMoreComplete();
            recyclerView.refreshComplete();
            return;
        }
        isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        ;
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
                        MyRecordsListBean.DataBean dataBean = info.getData().get(i);
                        boolean addResult = true;
                        for (int j = 0; j < mAdapter.getList().size(); j++) {
                            MyRecordsListBean.DataBean dataBean2 = mAdapter.getList().get(j);
                            if (dataBean.getId() == dataBean2.getId()) {
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

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        dismissLoading();
        if (recyclerView != null) {
            this.recyclerView.refreshComplete();
            this.recyclerView.loadMoreComplete();
            if (Urls.GET_PREPAID_RECORDS_LIST.equals(paramString1) && this.page == 1)
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
        if (Urls.GET_PREPAID_RECORDS_LIST.equals(paramString))
            if (paramObject != null && paramObject instanceof MyRecordsListBean) {
                MyRecordsListBean liveHomeBeanInfo = (MyRecordsListBean) paramObject;
                if (liveHomeBeanInfo.getCode() == 200) {
                    setData(liveHomeBeanInfo);
                } else {
                    this.recyclerView.refreshComplete();
                    this.recyclerView.loadMoreComplete();
                }
            } else {
                this.recyclerView.refreshComplete();
                this.recyclerView.loadMoreComplete();
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

    private void initView() {
        tvTitle.setText("充值记录");
        tvTitle.setTextColor(getResources().getColor(R.color.color_333));
        tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvTitle.setTextSize(18);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @OnClick({R.id.iv_title, R.id.btn_network_no_reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.btn_network_no_reload:
                dismissFailView();
                showLoading();
                page = 0;
                getData(page);
                break;
        }
    }
}
