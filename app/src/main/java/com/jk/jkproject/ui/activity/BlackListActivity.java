package com.jk.jkproject.ui.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
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
import com.jk.jkproject.ui.adapter.BlackListAdapter;
import com.jk.jkproject.ui.entity.BlackList;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;
import com.jk.jkproject.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

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
 * @date 2020/7/24 4:05 PM
 * @desc 黑名单列表
 */
public class BlackListActivity extends BActivity implements RefreshRecyclerViewListener {

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
    private Unbinder bind;

    private int page = 0;
    private boolean isRefresh = false;
    //是否有下页数据，0：有，1：没有
    private int isHasData;
    private List<BlackList.DataBean> list;
    private BlackListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_list);
        bind = ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        tvTitle.setText("黑名单");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        list = new ArrayList<>();
        mAdapter = new BlackListAdapter(this, this.list);
        recyclerView.setAdapter((BaseWrapperRecyclerAdapter) this.mAdapter);
        mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView(this));
        recyclerView.setRecyclerViewListener(this);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        layoutLoadingRoot.setBackgroundColor(getResources().getColor(R.color.white));
        layoutNetworkNoRoot.setBackgroundColor(getResources().getColor(R.color.white));
        mAdapter.setOnItemClickListener(new BlackListAdapter.OnItemClickListener() {
            public void onClick(String uid) {
                updataBlick(uid);
            }
        });
        getData(page);
    }

    private void getData(int page) {
        AppApis.getAdminlist(page, this);
    }

    private void updataBlick(String uid) {
        AppApis.getUpdateBlick(0, uid, this);
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

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 0;
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
        if (Urls.GET_ADMIN_LIST.equals(url)) {
            if (obj != null && obj instanceof BlackList) {
                BlackList blackList = (BlackList) obj;
                if (blackList.getCode() == 200) {
                    setData(blackList.getData());
                } else {
                    this.recyclerView.refreshComplete();
                    this.recyclerView.loadMoreComplete();
                }
            } else {
                this.recyclerView.refreshComplete();
                this.recyclerView.loadMoreComplete();
            }
        } else if (url.equals(Urls.GET_UPDATE_BLACK)) {
            try {
                JSONObject json = new JSONObject(obj.toString());
                if (json.getInt("code") == 200) {
                    isRefresh = true;
                    page = 0;
                    getData(page);
                    ToastUtils.showShortToast("解除成功");
                } else {
                    ToastUtils.showShortToast(json.getString("msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setData(List<BlackList.DataBean> list) {
        if (list == null || list.size() == 0) {
            if (list != null) {
                isHasData = list.size() > Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            if (isRefresh) {
                mAdapter.clear(true);
            }
            mAdapter.showNoMoreDataView();
            recyclerView.loadMoreComplete();
            recyclerView.refreshComplete();
            return;
        }
        isHasData = list.size() > Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        if (isRefresh) {
            isRefresh = false;
            recyclerView.refreshComplete();
            if (mAdapter != null) {
                mAdapter.clear(true);
                if (this.list.size() > 0) {
                    this.list.clear();
                    this.list = list;
                }
                mAdapter.addAll(list, true);
            }
            this.recyclerView.getRecyclerView().scrollToPosition(0);
            if (list.size() > 0 && this.isHasData == 1)
                this.page++;
        } else {
            if (mAdapter != null && mAdapter.getBasicItemCount() == 0) {
                this.mAdapter.addAll(list, true);
                if (list.size() > 0 && this.isHasData == 1)
                    this.page++;
            } else {
                int size = list.size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        BlackList.DataBean dataBean = list.get(i);
                        boolean addResult = true;
                        for (int j = 0; j < mAdapter.getList().size(); j++) {
                            BlackList.DataBean dataBean1 = mAdapter.getList().get(j);
                            if (TextUtils.equals(dataBean.getUserId(), dataBean1.getUserId())) {
                                addResult = false;
                                break;
                            }
                        }
                        if (addResult) {
                            mAdapter.add(dataBean);
                        }
                    }
                    this.mAdapter.notifyDataSetChanged();
                }
                if (list.size() > 0 && this.isHasData == 1) {
                    this.recyclerView.loadMoreComplete();
                    this.mAdapter.showNoMoreDataView();
                    this.page++;
                } else {
                    this.mAdapter.showNoMoreDataView();
                    this.recyclerView.loadMoreComplete();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}