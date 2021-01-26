package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.LiveRoomHostOperationAdapter;
import com.jk.jkproject.ui.entity.BlackList;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;
import com.jk.jkproject.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class DialogHostOperation extends BaseDialog implements RefreshRecyclerViewListener {
    @BindView(R.id.tv_name)
    TextView tvName;
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

    private List<BlackList.DataBean> list;

    private DialogReturnListener listener;

    private LiveRoomHostOperationAdapter mAdapter;

    private Context mContext;

    private int mType;
    private String roomId;

    private int page = 0;

    private String titleName;
    private String uid;

    private String userName;

    public DialogHostOperation(Context paramContext, int paramInt, String roomId) {
        super(paramContext);
        this.mContext = paramContext;
        this.mType = paramInt;
        this.roomId = roomId;
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
        switch (this.mType) {
            default:
                return;
            case 3:
                AppApis.getAdminlist(paramInt, this);
                break;
            case 2:
                AppApis.getAdminlistInfo(paramInt, roomId, this);
                break;
            case 1:
                AppApis.getBannedlist(paramInt, roomId,this);
                break;
        }
    }

    @RequiresApi(api = 23)
    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        this.recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);
        this.recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        this.list = new ArrayList<>();
        this.mAdapter = new LiveRoomHostOperationAdapter(getContext(), this.list);
        this.recyclerView.setAdapter((BaseWrapperRecyclerAdapter) this.mAdapter);
        this.mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView(getContext()));
        this.recyclerView.setRecyclerViewListener(this);
        this.recyclerView.setBackgroundColor(getContext().getColor(R.color.color_d92B2B2B));
        this.layoutLoadingRoot.setBackgroundColor(getContext().getColor(R.color.color_d92B2B2B));
        this.layoutNetworkNoRoot.setBackgroundColor(getContext().getColor(R.color.color_d92B2B2B));
        getData(page);
        this.mAdapter.setOnItemClickListener(uid -> {
            if (listener != null) {
                listener.onClick(mType, uid);
                dismiss();
            }
        });
        switch (this.mType) {
            case 3:
                tvName.setText("黑名单列表");
                break;
            case 2:
                tvName.setText("管理员列表");
                break;
            case 1:
                tvName.setText("禁言列表");
                break;
        }
    }

    private void setData(List<BlackList.DataBean> list) {
        if (list == null || list.size() == 0) {
            if (list != null) {
                isHasData = list.size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            if (isRefresh) {
                mAdapter.clear(true);
            }
            mAdapter.showNoMoreDataView();
            recyclerView.loadMoreComplete();
            recyclerView.refreshComplete();
            return;
        }
        isHasData = list.size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
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

    private void showFailView() {
        if (layoutNetworkNoRoot != null)
            layoutNetworkNoRoot.setVisibility(View.VISIBLE);
    }

    private void showLoading() {
        if (layoutLoadingRoot != null)
            layoutLoadingRoot.setVisibility(View.VISIBLE);
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_host_operation);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 1.0F;
        this.mDimAmount = 0.6F;
        this.h = 240;
        this.gravity = Gravity.BOTTOM;
    }

    protected void initView() {
        if (Build.VERSION.SDK_INT >= 23)
            init();
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        dismissLoading();
        this.recyclerView.refreshComplete();
        this.recyclerView.loadMoreComplete();
        showFailView();
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

    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(getContext(), 240));
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        dismissLoading();
        if (Urls.GET_ADMIN_LIST.equals(url) || Urls.GET_ADMIN_LIST_INFO.equals(url) || Urls.GET_BANNED_LIST.equals(url)) {
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
        }
    }

    @OnClick({R.id.btn_network_no_reload})
    public void onViewClicked(View paramView) {
        if (paramView.getId() == R.id.btn_network_no_reload) {
            dismissFailView();
            showLoading();
            page = 0;
            getData(this.page);
        }
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public static interface DialogReturnListener {
        void onClick(int param1Int, String param1String);
    }
}
