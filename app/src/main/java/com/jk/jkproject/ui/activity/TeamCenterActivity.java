package com.jk.jkproject.ui.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.TeamSearchListAdapter;
import com.jk.jkproject.ui.entity.TeamCenterInfo;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class TeamCenterActivity extends BActivity implements RefreshRecyclerViewListener {
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.public_top_layout)
    RelativeLayout publicTopLayout;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
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
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private int page = 1;
    private boolean isRefresh = false;
    //是否有下页数据，0：有，1：没有
    private int isHasData;
    private List<TeamCenterInfo.DataBean> list = new ArrayList<>();


    private Unbinder bind;
    private TeamSearchListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_center);
        bind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.team_center);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        List<TeamCenterInfo.DataBean> list = new ArrayList<>();
        mAdapter = new TeamSearchListAdapter(this, list);
        mAdapter.setLoadMoreFooterView(new DefaultLoadMoreFooterView(this));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setRecyclerViewListener(this);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.color_F1F5F8));
        mFlagShowNoNetworkToast = false;
        mFlagShowDefaultFailToast = false;
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        recyclerView.enableLoadMore();
        recyclerView.loadMoreComplete();
        getData(page);
    }

    @Override
    public void onLoadMore(int pagination, int pageSize) {
        if (isHasData == 1) {
            mAdapter.showLoadMoreView();
            getData(page);
        }
    }

    @OnClick({R.id.iv_title, R.id.iv_search, R.id.tv_cancel, R.id.btn_network_no_reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.iv_search:
                dismissFailView();
                showLoading();
                getData(page);
                break;
            case R.id.tv_cancel:
                etSearch.setText("");
                etSearch.setHint(R.string.str_search);
                break;
            case R.id.btn_network_no_reload:
                dismissFailView();
                showLoading();
                page = 1;
                getData(page);
                break;
        }
    }

    private void getData(int page) {
        AppApis.getTeamCenter(etSearch.getText().toString().trim(), page, this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        dismissLoading();
        if (Urls.GET_TEAM_CENTER.equals(url)) {
            if (null != obj && obj instanceof TeamCenterInfo) {
                TeamCenterInfo listData = (TeamCenterInfo) obj;
                if (listData.getCode() == 200) {
                    setTeamCenter(listData);
                } else {
                    recyclerView.refreshComplete();
                    recyclerView.loadMoreComplete();
                }
            } else {
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        }
    }

    private void setTeamCenter(TeamCenterInfo info) {
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

        recyclerView.setVisibility(View.VISIBLE);
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
                        TeamCenterInfo.DataBean dataBean = info.getData().get(i);
                        boolean addResult = true;
                        for (int j = 0; j < mAdapter.getList().size(); j++) {
                            TeamCenterInfo.DataBean dataBean2 = mAdapter.getList().get(j);
                            if (dataBean.getId() == dataBean2.getId()){
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

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        dismissLoading();
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (Urls.GET_TEAM_CENTER.equals(url) && page == 1) {
            showFailView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        if (mAdapter != null) {
            mAdapter.clear(false);
        }
    }

    private void showLoading() {
        if (layoutLoadingRoot != null) {
            layoutLoadingRoot.setVisibility(View.VISIBLE);
        }
    }

    private void dismissLoading() {
        if (layoutLoadingRoot != null) {
            layoutLoadingRoot.setVisibility(View.GONE);
        }
    }

    /**
     * 没有网络,加载失败布局
     */
    private void showFailView() {
        if (layoutNetworkNoRoot != null) {
            layoutNetworkNoRoot.setVisibility(View.VISIBLE);
        }
    }

    private void dismissFailView() {
        if (layoutNetworkNoRoot != null) {
            layoutNetworkNoRoot.setVisibility(View.GONE);
        }
    }
}
