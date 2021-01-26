package com.jk.jkproject.ui.dialog;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.LiveRoomLockUserAdapter;
import com.jk.jkproject.ui.entity.LiveRoomUserLockBean;
import com.jk.jkproject.ui.entity.LiveRoomUserLockList;
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

/**
 * @author Zick
 * @params
 * @date 2020/7/31 4:00 PM
 * @desc 直播间用户观看列表
 */
public class DialogLockCount extends BaseDialog implements RefreshRecyclerViewListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
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


    private List<LiveRoomUserLockBean> list;

    private DialogReturnListener listener;


    private LiveRoomLockUserAdapter mAdapter;

    private Context mContext;

    private int mType;


    private int page = 0;

    private String titleName;


    private String uid;

    private String userName;

    public DialogLockCount(Context paramContext, String paramString) {
        super(paramContext);
        this.mContext = paramContext;
        this.uid = paramString;
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
        AppApis.getLiveRoomLockList(paramInt, this.uid, this);
    }

    @RequiresApi(api = 23)
    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        this.recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);
        this.recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        this.list = new ArrayList<LiveRoomUserLockBean>();
        this.mAdapter = new LiveRoomLockUserAdapter(getContext(), this.list);
        this.recyclerView.setAdapter((BaseWrapperRecyclerAdapter) this.mAdapter);
        this.mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView(getContext()));
        this.recyclerView.setRecyclerViewListener(this);
        this.recyclerView.setBackgroundColor(getContext().getColor(R.color.white));
        getData(this.page);
        this.mAdapter.setOnItemClickListener(param1String -> {
            if (listener != null) {
                listener.onDialogReturnClick(param1String);
                dismiss();
            }
        });
    }

    private void setData(List<LiveRoomUserLockBean> infoBeans) {
        if (infoBeans == null || infoBeans.size() == 0) {
            if (infoBeans != null) {
                this.isHasData = infoBeans.size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            if (isRefresh) {
                mAdapter.clear(true);
            }
            this.mAdapter.showNoMoreDataView();
            this.recyclerView.loadMoreComplete();
            this.recyclerView.refreshComplete();
            return;
        }
        this.isHasData = infoBeans.size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        if (isRefresh) {
            isRefresh = false;
            recyclerView.refreshComplete();
            if (mAdapter != null) {
                mAdapter.clear(true);
                if (this.list.size() > 0) {
                    this.list.clear();
                    this.list = infoBeans;
                }
                this.mAdapter.addAll(infoBeans, true);
            }
            this.recyclerView.getRecyclerView().scrollToPosition(0);
            if (infoBeans.size() > 0 && this.isHasData == 1)
                this.page++;
        } else {
            if (mAdapter != null && mAdapter.getBasicItemCount() == 0) {
                this.mAdapter.addAll(infoBeans, true);
                if (infoBeans.size() > 0 && this.isHasData == 1)
                    this.page++;
            } else {
                int size = infoBeans.size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        LiveRoomUserLockBean liveCardInfo = infoBeans.get(i);
                        boolean addResult = true;
                        for (int j = 0; j < mAdapter.getList().size(); j++) {
                            LiveRoomUserLockBean liveCardInfo2 = mAdapter.getList().get(j);
                            if (TextUtils.equals(liveCardInfo.getUserId(), liveCardInfo2.getUserId())) {
                                addResult = false;
                                break;
                            }
                        }

                        if (addResult) {
                            mAdapter.add(liveCardInfo);
                        }
                    }
                    this.mAdapter.notifyDataSetChanged();
                }
                if (infoBeans.size() > 0 && this.isHasData == 1) {
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
        LinearLayout linearLayout = this.layoutNetworkNoRoot;
        if (linearLayout != null)
            linearLayout.setVisibility(View.VISIBLE);
    }

    private void showLoading() {
        LinearLayout linearLayout = this.layoutLoadingRoot;
        if (linearLayout != null)
            linearLayout.setVisibility(View.VISIBLE);
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_lock_count);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 1.0F;
        this.mDimAmount = 0.6F;
        this.h = 365;
        this.gravity = Gravity.BOTTOM;
    }

    protected void initView() {
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
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(getContext(), 365));
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        dismissLoading();
        if (Urls.GET_LIVE_FANS_USER_LOCK_LIST.equals(url))
            if (obj != null && obj instanceof LiveRoomUserLockList) {
                LiveRoomUserLockList liveRoomUserLockList = (LiveRoomUserLockList) obj;
                if (liveRoomUserLockList.getCode() == 200) {
                    setData(liveRoomUserLockList.getData().getMsgList());
                } else {
                    this.recyclerView.refreshComplete();
                    this.recyclerView.loadMoreComplete();
                }
            } else {
                this.recyclerView.refreshComplete();
                this.recyclerView.loadMoreComplete();
            }
    }

    @OnClick({R.id.iv_close, R.id.btn_network_no_reload})
    public void onViewClicked(View paramView) {
        int i = paramView.getId();
        if (i != R.id.btn_network_no_reload) {
            if (i == R.id.iv_close)
                dismiss();
        } else {
            dismissFailView();
            showLoading();
            this.page = 0;
            getData(this.page);
        }
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    public static interface DialogReturnListener {
        void onDialogReturnClick(String param1String);
    }
}