package com.jk.jkproject.ui.activity;

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
import android.widget.Toast;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.TeamSigingDealWithListAdapter;
import com.jk.jkproject.ui.entity.TeamSigingDealWithInfo;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class TeamSigingDealWithActivity extends BActivity implements RefreshRecyclerViewListener {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
    // type_promptly  type_lockAll
    private int type;

    private int page = 1;
    private boolean isRefresh = false;
    //是否有下页数据，0：有，1：没有
    private int isHasData;
    private List<TeamSigingDealWithInfo.DataBean> list;
    private TeamSigingDealWithListAdapter mAdapter;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_siging_deal_with);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (null != getIntent()) {
            type = getIntent().getIntExtra("type", -1);
        }

        switch (type) {
            case 1:
                tvTitle.setText("申请签约列表");
                break;
            default:
                tvTitle.setText("全部签约列表");
                break;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        list = new ArrayList<>();
        mAdapter = new TeamSigingDealWithListAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreFooterView(new DefaultLoadMoreFooterView(this));
        recyclerView.setRecyclerViewListener(this);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.color_F1F5F8));
        mFlagShowNoNetworkToast = false;
        mFlagShowDefaultFailToast = false;

        getData(page);


        mAdapter.setOnItemClickListener(new TeamSigingDealWithListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int type, int team_id) {
                getQuestData(type, team_id);
            }
        });

    }

    private void getQuestData(int type, int team_id) {
        AppApis.getTeamUpdateApprovalToJoin(team_id, type, this);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        recyclerView.enableLoadMore();
        recyclerView.loadMoreComplete();
        getData(page);
    }

    private void getData(int page) {
        AppApis.getTeamJoinList(page, type, this);
    }

    @Override
    public void onLoadMore(int pagination, int pageSize) {
        if (isHasData == 1) {
//            mAdapter.showLoadMoreView();
            getData(page);
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        dismissLoading();
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        showFailView();
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        dismissLoading();
        if (Urls.GET_TEAM_JOINLIST.equals(url)) {
            if (null != obj && obj instanceof TeamSigingDealWithInfo) {
                TeamSigingDealWithInfo listData = (TeamSigingDealWithInfo) obj;
                if (listData.getCode() == 200) {
                    setInfo(listData);
                } else {
                    recyclerView.refreshComplete();
                    recyclerView.loadMoreComplete();
                }
            } else {
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        } else if (Urls.GET_TEAM_APPROVAL_TO_JOIN.equals(url)) {
            try {
                json = new JSONObject(obj.toString());
                if (json.getInt("code") == 200) {
                    Toast.makeText(this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                }
                getData(page);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setInfo(TeamSigingDealWithInfo info) {
        if (info == null || info.getData() == null || info.getData().size() == 0) {
            if (info != null) {
                isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
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
                TeamSigingDealWithInfo.DataBean data = info.getData().get(0);
                mAdapter.addAll(info.getData(), true);
                if (info.getData().size() > 0 && isHasData == 1) {
                    page++;
                }
            } else {
                //加载更多时
                int size = info.getData().size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        TeamSigingDealWithInfo.DataBean dataBean = info.getData().get(i);
                        boolean addResult = true;
                        for (int j = 0; j < mAdapter.getList().size(); j++) {
                            TeamSigingDealWithInfo.DataBean dataBean2 = mAdapter.getList().get(j);
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

    @OnClick({R.id.iv_title, R.id.tv_title, R.id.tv_right_title, R.id.public_top_layout, R.id.live_new_recycler_view, R.id.network_no_title, R.id.network_no_des, R.id.btn_network_no_reload, R.id.layout_network_no_root, R.id.loading_cpb_progress, R.id.layout_loading_root})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.tv_right_title:
                break;
            case R.id.public_top_layout:
                break;
            case R.id.live_new_recycler_view:
                break;
            case R.id.network_no_title:
                break;
            case R.id.network_no_des:
                break;
            case R.id.btn_network_no_reload:
                break;
            case R.id.layout_network_no_root:
                break;
            case R.id.loading_cpb_progress:
                break;
            case R.id.layout_loading_root:
                break;
        }
    }
}