package com.jk.jkproject.ui.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.adapter.LiveRoomRankingChildListAdapter;
import com.jk.jkproject.ui.entity.LiveRankInfoBean;
import com.jk.jkproject.ui.entity.LiveRankInfoMyBean;
import com.jk.jkproject.ui.fragment.LiveRoomFragment;
import com.jk.jkproject.ui.widget.recyclerview.BaseLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.ui.widget.recyclerview.RefreshRecyclerViewListener;
import com.jk.jkproject.ui.widget.recyclerview.WrapperRecyclerView;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ScreenUtils;
import com.jk.jkproject.utils.StringUtils;
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
 * @date 2020/7/31 4:31 PM
 * @desc 直播间日榜
 */
public class DialogLiveRoomRankList extends BaseDialog implements RefreshRecyclerViewListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
    @BindView(R.id.tv_rank)
    TextView tvRank;
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_hot)
    TextView tvUserHot;
    @BindView(R.id.tv_user_name_host)
    TextView tvUserNameHost;
    @BindView(R.id.tv_host_type)
    TextView tvHostType;
    @BindView(R.id.ll_item)
    RelativeLayout llItem;
    @BindView(R.id.rl_item)
    RelativeLayout rlItem;
    private LiveRankInfoMyBean.DataBean data;
    private int isHasData;
    private boolean isRefresh = false;


    private List<LiveRankInfoBean.DataBean> list;

    private DialogReturnListener listener;
    private LiveRoomRankingChildListAdapter mAdapter;

    private Context mContext;

    private int mType;
    private int page = 0;
    private String titleName;
    public static String uid, u_id;

    private String userName;
    private Unbinder bind;
    private boolean list_follow = false;
    private LiveRankInfoBean.DataBean bean;

    public DialogLiveRoomRankList(Context paramContext, String paramString) {
        super(paramContext);
        this.mContext = paramContext;
        this.uid = paramString;
    }

    private void dismissFailView() {
        if (layoutNetworkNoRoot != null) {
            recyclerView.setVisibility(View.VISIBLE);
            layoutNetworkNoRoot.setVisibility(View.GONE);
        }
    }

    private void dismissLoading() {
        if (layoutLoadingRoot != null) {
            recyclerView.setVisibility(View.VISIBLE);
            layoutLoadingRoot.setVisibility(View.GONE);
        }
    }

    private void getData(int paramInt) {
        AppApis.getHostRankList(1, paramInt, this);
    }

    @RequiresApi(api = 23)
    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        this.recyclerView.setLayoutManager((RecyclerView.LayoutManager) linearLayoutManager);
        this.recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        this.list = new ArrayList<>();
        this.mAdapter = new LiveRoomRankingChildListAdapter(getContext(), this.list);
        this.recyclerView.setAdapter((BaseWrapperRecyclerAdapter) this.mAdapter);
        this.mAdapter.setLoadMoreFooterView((BaseLoadMoreFooterView) new DefaultLoadMoreFooterView(getContext()));
        this.recyclerView.setRecyclerViewListener(this);
        this.llItem.setBackgroundColor(getContext().getColor(R.color.color_cc2B2B2B));
        this.recyclerView.setBackgroundColor(getContext().getColor(R.color.color_cc070101));
        this.layoutNetworkNoRoot.setBackgroundColor(getContext().getColor(R.color.color_cc070101));
        this.layoutLoadingRoot.setBackgroundColor(getContext().getColor(R.color.color_cc070101));
        getData(page);
        this.mAdapter.setOnItemClickListener((type, dataBean) -> {
            switch (type) {
                case 1:
                    if (uid.equals(dataBean.getUserId())) {
                        setFollow(dataBean);
                    } else if (dataBean.getR_state() != 1) {
                        setFollow(dataBean);
                    } else {
                        listener.onDialogReturnClick(dataBean.getRoomId(), 1);
                    }
                    break;
                case 2:
                    if (listener != null && dataBean != null) {
                        listener.onDialogReturnClick(dataBean.getUserId(), 2);
                        dismiss();
                    }
                    break;
            }
        });
    }

    private void setFollow(LiveRankInfoBean.DataBean bean) {
        if (bean.getState() != 0) {
            return;
        }
        this.bean = bean;
        list_follow = true;
        setUpdateFollow(bean.getState() == 0 ? 1 : 0, bean.getUserId());
    }

    private void setData(List<LiveRankInfoBean.DataBean> paramList) {
        byte b = 0;
        if (paramList == null || paramList.size() == 0) {
            if (paramList != null) {
                if (paramList.size() > Integer.parseInt(Urls.CURRENTCOUNT))
                    b = 1;
                this.isHasData = b;
            }
            this.mAdapter.showNoMoreDataView();
            this.recyclerView.loadMoreComplete();
            this.recyclerView.refreshComplete();
            return;
        }
        if (paramList.size() > Integer.parseInt(Urls.CURRENTCOUNT)) {
            b = 1;
        } else {
            b = 0;
        }
        this.isHasData = b;
        if (this.isRefresh) {
            this.isRefresh = false;
            this.recyclerView.refreshComplete();
            LiveRoomRankingChildListAdapter liveRoomRankingChildListAdapter = this.mAdapter;
            if (liveRoomRankingChildListAdapter != null) {
                liveRoomRankingChildListAdapter.clear(true);
                if (this.list.size() > 0) {
                    this.list.clear();
                    this.list = paramList;
                }
                this.mAdapter.addAll(paramList, true);
            }
            this.recyclerView.getRecyclerView().scrollToPosition(0);
            if (paramList.size() > 0 && this.isHasData == 1)
                this.page++;
        } else {
            LiveRoomRankingChildListAdapter liveRoomRankingChildListAdapter = this.mAdapter;
            if (liveRoomRankingChildListAdapter != null && liveRoomRankingChildListAdapter.getBasicItemCount() == 0) {
                this.mAdapter.addAll(paramList, true);
                if (paramList.size() > 0 && this.isHasData == 1)
                    this.page++;
            } else {
                int i = paramList.size();
                if (i > 0) {
                    for (b = 0; b < i; b++) {
                        boolean bool2;
                        LiveRankInfoBean.DataBean dataBean = paramList.get(b);
                        boolean bool1 = true;
                        byte b1 = 0;
                        while (true) {
                            bool2 = bool1;
                            if (b1 < this.mAdapter.getList().size()) {
                                LiveRankInfoBean.DataBean dataBean1 = this.mAdapter.getList().get(b1);
                                if (dataBean.getUserId() == dataBean1.getUserId()) {
                                    bool2 = false;
                                    break;
                                }
                                b1++;
                                continue;
                            }
                            break;
                        }
                        if (bool2)
                            this.mAdapter.add(dataBean);
                    }
                    this.mAdapter.notifyDataSetChanged();
                }
                if (paramList.size() > 0 && this.isHasData == 1) {
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

    private void setDataView(final LiveRankInfoMyBean.DataBean data) {
        this.data = data;
        if (data != null) {
            StringBuilder stringBuilder1;
            Drawable drawable;
            TextView textView1;
            if (data.getUserId().equals(SPUtils.getUserId())) {
                tvHostType.setVisibility(View.GONE);
            }
            switch (data.getId()) {
                default:
                    if (data.getId() == -2) {
                        tvRank.setText("未上榜");
                        tvRank.setTextSize(11);
                    } else if (data.getId() == -1) {
                        tvRank.setText("99+");
                    } else {
                        tvRank.setText(data.getId() + "");
                    }
                    tvRank.setCompoundDrawables(null, null, null, null);
                    break;
                case 3:
                    drawable = this.context.getResources().getDrawable(R.mipmap.icon_live_room_contribution_3);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    this.tvRank.setCompoundDrawables(drawable, null, null, null);
                    this.tvRank.setText("");
                    break;
                case 2:
                    drawable = this.context.getResources().getDrawable(R.mipmap.icon_live_room_contribution_2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    this.tvRank.setCompoundDrawables(drawable, null, null, null);
                    break;
                case 1:
                    drawable = this.context.getResources().getDrawable(R.mipmap.icon_live_room_contribution_1);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    this.tvRank.setCompoundDrawables(drawable, null, null, null);
                    break;
            }
            this.sdvHeader.setImageURI(data.getPicture());
            this.tvUserName.setText(data.getNickname());
            tvUserHot.setText(StringUtils.ReadSize(data.getSum_hot()) + "热度");
            this.tvUserNameHost.setText(data.getAnchorGrade());
            switch (data.getState()) {
                case 1:
                    this.tvHostType.setText("已关注");
                    this.tvHostType.setBackgroundResource(R.color.transparent);
                    tvHostType.setEnabled(false);
                    break;
                case 0:
                    tvHostType.setBackgroundResource(R.drawable.bg_live_room_red_follow);
                    tvHostType.setText("关注");
                    tvHostType.setEnabled(true);
                    break;
            }
            this.tvHostType.setOnClickListener(param1View -> {
                if (OnClickUtils.isFastClick() && listener != null) {
                    setUpdateFollow(1, data.getUserId());
                }
            });
            rlItem.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDialogReturnClick(data.getUserId(), 2);
                    dismiss();
                }
            });
            llItem.setBackgroundColor(context.getResources().getColor(R.color.color_2B2B2B));
        } else {
            return;
        }

    }

    private void setUpdateFollow(int state, String u_id) {
        this.u_id = u_id;
        AppApis.getUpdateFollow(state, u_id, this);
    }

    private void showFailView() {
        if (layoutNetworkNoRoot != null) {
            recyclerView.setVisibility(View.GONE);
            layoutNetworkNoRoot.setVisibility(View.VISIBLE);
        }
    }

    private void showLoading() {
        if (layoutLoadingRoot != null) {
            recyclerView.setVisibility(View.GONE);
            layoutLoadingRoot.setVisibility(View.VISIBLE);
        }
    }

    protected void create(Bundle paramBundle) {
        setContentView(R.layout.dialog_live_room_rank_list);
        bind = ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        this.mWidthScale = 1.0F;
        this.mDimAmount = 0.6F;
        this.h = 492;
        this.gravity = 80;
    }

    @RequiresApi(api = 23)
    protected void initView() {
        init();
        AppApis.getHostRank(uid, 1, this);
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
        if (paramString1.equals(Urls.GET_HOST_RANK_LIST)) {
            dismissLoading();
            this.recyclerView.refreshComplete();
            this.recyclerView.loadMoreComplete();
            showFailView();
        } else {
            ToastUtils.showShortToast(paramString2);
            llItem.setVisibility(View.GONE);
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

    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(getContext(), 492));
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        dismissLoading();
        if (Urls.GET_HOST_RANK_LIST.equals(url)) {
            if (obj != null && obj instanceof LiveRankInfoBean) {
                LiveRankInfoBean infoBean = (LiveRankInfoBean) obj;
                if (infoBean.getCode() == 200) {
                    setData(infoBean.getData());
                } else {
                    this.recyclerView.refreshComplete();
                    this.recyclerView.loadMoreComplete();
                }
            } else {
                this.recyclerView.refreshComplete();
                this.recyclerView.loadMoreComplete();
            }
        } else if (Urls.GET_HOST_RANK_MY.equals(url)) {
            if (obj != null && obj instanceof LiveRankInfoMyBean) {
                LiveRankInfoMyBean infoMyBean = (LiveRankInfoMyBean) obj;
                if (infoMyBean.getCode() == 200) {
                    llItem.setVisibility(View.VISIBLE);
                    setDataView(infoMyBean.getData());
                } else {
                    llItem.setVisibility(View.GONE);
                }
            }
        } else if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    data.setState(data.getState() == 0 ? 1 : 0);
                    if (uid.equals(u_id)) {
                        LiveRoomFragment.isFollow = data.getState() == 1;
                        tvHostType.setText("已关注");
                        tvHostType.setEnabled(false);
                        tvHostType.setBackgroundColor(this.context.getResources().getColor(R.color.transparent));
                    }
                    if (!list_follow) {
                        switch (this.data.getState()) {
                            case 1:
                                tvHostType.setText("已关注");
                                tvHostType.setEnabled(false);
                                tvHostType.setBackgroundColor(this.context.getResources().getColor(R.color.transparent));
                                ToastUtils.showShortToast("关注成功");
                                break;
                            default:
                                tvHostType.setText("关注");
                                tvHostType.setEnabled(true);
                                tvHostType.setBackgroundResource(R.drawable.bg_live_room_red_follow);
                                ToastUtils.showShortToast("取消关注成功");
                                break;
                        }
                    } else {
                        LiveRankInfoBean.DataBean info = bean;
                        info.setState(1);
                        mAdapter.notifyDataSetChanged();
                        ToastUtils.showShortToast("关注成功");
                    }
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        }
    }

    public void setDialogClickListener(DialogReturnListener paramDialogReturnListener) {
        this.listener = paramDialogReturnListener;
    }

    @OnClick(R.id.btn_network_no_reload)
    public void onViewClicked() {
        dismissFailView();
        showLoading();
        page = 0;
        getData(page);
    }

    public static interface DialogReturnListener {
        void onDialogReturnClick(String param1String, int param1Int);
    }
}