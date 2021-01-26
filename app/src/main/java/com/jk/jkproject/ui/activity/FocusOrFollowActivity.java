package com.jk.jkproject.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.FocusOrFollowAdapter;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
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
import butterknife.Unbinder;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class FocusOrFollowActivity extends BActivity implements RefreshRecyclerViewListener {
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

    private List<LiveHomeBeanInfo.DataBean> list;
    private int position;
    private LiveHomeBeanInfo.DataBean bean;


    private int page = 0;
    private FocusOrFollowAdapter mAdapter;
    private int mType = 0;
    private String userId = "";


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
        AppApis.getUserFansList(userId, mType, paramInt, this);
    }

    private void initView() {
        if (getIntent() != null) {
            mType = getIntent().getIntExtra("type", 0);
            userId = getIntent().getStringExtra("userId");
        }
        switch (mType) {
            case 2:
                tvTitle.setText("关注");
                break;
            case 1:
                tvTitle.setText("粉丝");
                break;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        list = new ArrayList<>();
        mAdapter = new FocusOrFollowAdapter((Context) this, this.list, mType);
        recyclerView.setAdapter((BaseWrapperRecyclerAdapter) mAdapter);
        mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView((Context) this));
        recyclerView.setRecyclerViewListener(this);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        mFlagShowNoNetworkToast = false;
        mFlagShowDefaultFailToast = false;
        getData(page);
        mAdapter.setOnItemClickListener(new FocusOrFollowAdapter.OnItemClickListener() {

            @Override
            public void onClick(int state, String uid) {
            }

            @Override
            public void onClick(int state, LiveHomeBeanInfo.DataBean bean) {
                switch (state) {
                    case 1:
                        Intent intent = new Intent(FocusOrFollowActivity.this, MyHomePageActivity.class);
                        intent.putExtra("u_id", bean.getU_id());
                        startActivity(intent);
                        break;
                }

            }

            @Override
            public void onClick(int position, int state, LiveHomeBeanInfo.DataBean dataBean) {
                switch (state) {
                    case 0:
                        setFollow(position, dataBean);
                        break;
                }
            }

            @Override
            public void onItemClick(int position) {

            }
        });
    }

    private void setFollow(int position, LiveHomeBeanInfo.DataBean bean) {
        this.position = position;
        this.bean = bean;
        AppApis.getUpdateFollow(bean.getState() == 0 ? 1 : 0, bean.getU_id(), this);
    }


    private void setData(LiveHomeBeanInfo info) {
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
                            if (dataBean.getUserId().equals(dataBean2.getUserId())) {
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
        if (layoutNetworkNoRoot != null) {
            layoutNetworkNoRoot.setVisibility(View.VISIBLE);
        }
    }

    private void showLoading() {
        if (layoutLoadingRoot != null) {
            layoutLoadingRoot.setVisibility(View.VISIBLE);
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_live_home_focus);
        this.bind = ButterKnife.bind((Activity) this);
        initView();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.bind.unbind();
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        dismissLoading();
        this.recyclerView.refreshComplete();
        this.recyclerView.loadMoreComplete();
        if (Urls.GET_USER_FANS_LIST.equals(paramString1) && this.page == 1)
            showFailView();
    }

    public void onLoadMore(int paramInt1, int paramInt2) {
        if (this.isHasData == 1) {
            this.mAdapter.showLoadMoreView();
            getData(this.page);
        }
    }

    public void onRefresh() {
        isRefresh = true;
        page = 0;
        recyclerView.enableLoadMore();
        recyclerView.loadMoreComplete();
        getData(page);
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        dismissLoading();
        if (Urls.GET_USER_FANS_LIST.equals(url)) {
            if (obj != null && obj instanceof LiveHomeBeanInfo) {
                LiveHomeBeanInfo liveHomeBeanInfo = (LiveHomeBeanInfo) obj;
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
        } else if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    if (bean != null && mAdapter != null) {
                        LiveHomeBeanInfo.DataBean info = bean;
                        info.setState(bean.getState() == 0 ? 1 : 0);
//                        mAdapter.notifyItemChanged(position, info);
                        switch (info.getState()) {
                            case 1:
                                ToastUtils.showShortToast("关注成功");
                                break;
                            default:
                                ToastUtils.showShortToast("取消关注成功");
                                break;
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        }
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
