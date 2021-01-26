package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.LiveRoomPKItemAdapter;
import com.jk.jkproject.ui.entity.PKRecordBaseInfo;
import com.jk.jkproject.ui.entity.PKRecordValueBaseInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
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
 * @date 2020/9/12 4:39 PM
 * @desc pk记录
 */
public class DialogLiveRoomPKRecord extends BaseDialog implements RefreshRecyclerViewListener {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_pk_record)
    TextView tvPkRecord;
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
    private DialogReturnListener listener;
    private Context mContext;
    private Unbinder bind;
    private int mType;
    private LiveRoomPKItemAdapter mAdapter;
    private List<PKRecordBaseInfo.DataBean> list;
    private View mHeaderView;
    TextView tvSession, tvWinCount, tvEarnings;
    private int hotNum = 0;
    private int isHasData = 0;
    private boolean isRefresh = true;
    private int page = 0;

    public DialogLiveRoomPKRecord(Context paramContext, int mType) {
        super(paramContext);
        this.mContext = paramContext;
        this.mType = mType;
    }


    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_pk_record);
        this.bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 0.95F;
        this.mDimAmount = 0.6F;
        this.gravity = Gravity.BOTTOM;
    }

    public void getData(int paramInt) {
        page = paramInt;
        AppApis.getPKRecordList(paramInt, this);
    }


    protected void initView() {
        dismissLoading();
        list = new ArrayList<>();
        mAdapter = new LiveRoomPKItemAdapter(mContext, list);
        recyclerView.setRecyclerViewListener(this);
        addHeaderView();
        mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView(context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.getPtrFrameLayout().setHorizontalMoveArea((param1Float1, param1Float2) -> {
            Rect rect = new Rect();
            if (mHeaderView != null)
                mHeaderView.getLocalVisibleRect(rect);
            return rect.contains((int) param1Float1, (int) param1Float2);
        });
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((type, info) -> {
            switch (type) {
                case 1:
                    break;
                case 2:
//                    ToastUtils.showLongToast("点击邀请");
                    if (listener != null) {
                        listener.onClick(1, info);
                    }
                    dismiss();
                    break;
            }
        });
//        getData(page);
        AppApis.getPKValue(this);
    }

    private void addHeaderView() {
        mHeaderView = LayoutInflater.from(context).inflate(R.layout.live_room_pk_record_header_view, (ViewGroup) recyclerView, false);
        tvSession = getView(mHeaderView, R.id.tv_session);
        tvWinCount = getView(mHeaderView, R.id.tv_win_count);
        tvEarnings = getView(mHeaderView, R.id.tv_earnings);
        mAdapter.addHeaderView(mHeaderView);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        dismissLoading();
        if (Urls.GET_PK_RECORD.equals(url)) {
            if (obj != null && obj instanceof PKRecordBaseInfo) {
                PKRecordBaseInfo liveHomeBeanInfo = (PKRecordBaseInfo) obj;
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
        } else if (url.equals(Urls.GET_PK_RECORD_VALUE)) {
            if (obj != null && obj instanceof PKRecordValueBaseInfo) {
                PKRecordValueBaseInfo baseInfo = (PKRecordValueBaseInfo) obj;
                if (baseInfo != null && baseInfo.getCode() == 200) {
                    tvSession.setText(baseInfo.getData().getCount() + "");
                    tvWinCount.setText(baseInfo.getData().getMaxVictory() + "");
                    tvEarnings.setText(baseInfo.getData().getMoney() + "");
                }
            }
        }
    }

    @Override
    public void onFailure(int code, String url, String error) {
        super.onFailure(code, url, error);
        dismissLoading();
        this.recyclerView.refreshComplete();
        this.recyclerView.loadMoreComplete();
        if (Urls.GET_PK_RECORD.equals(url) && this.page == 1) {
            showFailView();
        }
        LogUtils.e(error);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ScreenUtils.getScreenW(getContext()), ScreenUtils.dp2px(getContext(), 443));
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick({R.id.iv_title, R.id.btn_network_no_reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                dismiss();
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
        this.isRefresh = true;
        this.page = 0;
        this.recyclerView.enableLoadMore();
        this.recyclerView.loadMoreComplete();
        getData(this.page);
    }

    @Override
    public void onLoadMore(int pagination, int pageSize) {
        if (this.isHasData == 1) {
            this.mAdapter.showLoadMoreView();
            getData(this.page);
        }
    }

    public static interface DialogReturnListener {
        void onClick(int type, PKRecordBaseInfo.DataBean info);
    }

    private void setData(PKRecordBaseInfo info) {
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
                        mAdapter.add(info.getData().get(i));
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
}