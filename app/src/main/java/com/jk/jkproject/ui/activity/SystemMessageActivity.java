package com.jk.jkproject.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
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
import com.jk.jkproject.net.im.cores.IMEventType;
import com.jk.jkproject.net.im.cores.MessageNotifyCenter;
import com.jk.jkproject.ui.adapter.SystemNotifitonListAdapter;
import com.jk.jkproject.ui.entity.SystemNotiBeanInfo;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * @author Zick
 * @params
 * @date 2020/6/9 10:02 AM
 * @desc 系统消息
 */
public class SystemMessageActivity extends BActivity implements RefreshRecyclerViewListener, Observer {

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

    private List<SystemNotiBeanInfo.DataBean> list;

    private SystemNotifitonListAdapter mAdapter;
    private int page = 0;


    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(android.os.Message param1Message) {
            Bundle bundle = (Bundle) param1Message.obj;
            switch (bundle.getInt("method")) {
                case IMEventType.ACTION_SYSTEM_NOTI_MSG_PUSH:
                    if (mAdapter != null) {
                        SystemNotiBeanInfo.DataBean message = (SystemNotiBeanInfo.DataBean) bundle.getSerializable("bean");
                        mAdapter.add(message, true);
//                        recyclerView.set(mAdapter.getItemCount() - 1);
                    }
                    break;
                default:
                    break;
            }
        }
    };


    private void dismissFailView() {
        if (layoutNetworkNoRoot != null)
            layoutNetworkNoRoot.setVisibility(View.GONE);
    }

    private void dismissLoading() {
        if (layoutLoadingRoot != null) {
            layoutLoadingRoot.setVisibility(View.GONE);
        }
    }

    private void getData(int page) {
        AppApis.getSystemNotificationList(page, this);
    }

    private void setData(SystemNotiBeanInfo info) {
        if (info == null || info.getData() == null || info.getData().size() == 0) {
            if (info != null) {
                this.isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            if (isRefresh) {
                mAdapter.clear(true);
            }
            this.mAdapter.showNoMoreDataView();
            this.recyclerView.loadMoreComplete();
            this.recyclerView.refreshComplete();
            return;
        }
        this.isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        if (isRefresh) {
            isRefresh = false;
            recyclerView.refreshComplete();
            if (mAdapter != null) {
                mAdapter.clear(true);
                if (list.size() > 0) {
                    list.clear();
                    list = info.getData();
                }
                this.mAdapter.addAll(info.getData(), true);
            }
            this.recyclerView.getRecyclerView().scrollToPosition(0);
            if (info.getData().size() > 0 && this.isHasData == 1) {
                this.page++;
            }
        } else {
            if (mAdapter != null && mAdapter.getBasicItemCount() == 0) {
                this.mAdapter.addAll(info.getData(), true);
                if (info.getData().size() > 0 && this.isHasData == 1)
                    this.page++;
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
                            SystemNotiBeanInfo.DataBean dataBean = info.getData().get(i);
                            boolean addResult = true;
                            for (int j = 0; j < mAdapter.getList().size(); j++) {
                                SystemNotiBeanInfo.DataBean dataBean2 = mAdapter.getList().get(j);
                                if (dataBean.getCreate_time() == dataBean2.getCreate_time()) {
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


    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        dismissLoading();
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (Urls.GET_SYSTEM_NOTI_LIST.equals(paramString1) && this.page == 1) {
            showFailView();
        }
    }

    public void onLoadMore(int paramInt1, int paramInt2) {
        if (isHasData == 1) {
            mAdapter.showLoadMoreView();
            getData(page);
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
        if (Urls.GET_SYSTEM_NOTI_LIST.equals(url)) {
            if (obj != null && obj instanceof SystemNotiBeanInfo) {
                SystemNotiBeanInfo info = (SystemNotiBeanInfo) obj;
                if (info.getCode() == 200) {
                    setData(info);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);
        bind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        MessageNotifyCenter.getInstance().register(this);
        tvTitle.setText("系统消息");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        list = new ArrayList<>();
        mAdapter = new SystemNotifitonListAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreFooterView(new DefaultLoadMoreFooterView(this));
        mAdapter.setOnItemClickListener(new SystemNotifitonListAdapter.OnItemClickListener() {
            public void terminationClick(View param1View, String param1String) {
            }
        });
        recyclerView.setRecyclerViewListener(this);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.color_F1F5F8));
        layoutLoadingRoot.setBackgroundColor(getResources().getColor(R.color.color_F1F5F8));
        layoutNetworkNoRoot.setBackgroundColor(getResources().getColor(R.color.color_F1F5F8));
        mFlagShowNoNetworkToast = false;
        mFlagShowDefaultFailToast = false;
        getData(page);
    }

    @Override
    protected void onDestroy() {
        MessageNotifyCenter.getInstance().unregister(this);
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            android.os.Message message = android.os.Message.obtain();
            message.obj = arg;
            handler.sendMessage(message);
        }
    }
}