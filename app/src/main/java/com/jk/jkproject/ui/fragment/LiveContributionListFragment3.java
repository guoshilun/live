package com.jk.jkproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.activity.MyHomePageActivity;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.adapter.LiveContributionAdapter;
import com.jk.jkproject.ui.entity.FanContributionList;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.ui.widget.AutoSwipeRefreshLayout;
import com.jk.jkproject.ui.widget.recyclerview.DefaultLoadMoreFooterView;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.FastClickUtils;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.ToastUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Zick
 * @params
 * @date 2020/8/28 9:42 AM
 * @desc 榜单Fragment
 */
public class LiveContributionListFragment3 extends BFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static String tab_type = "tab_type";
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.iv_crown_1)
    ImageView ivCrown1;
    @BindView(R.id.sdv_crown_1)
    SimpleDraweeView sdvCrown1;
    @BindView(R.id.tv_user_name_1)
    TextView tvUserName1;
    @BindView(R.id.tv_user_level_1)
    TextView tvUserLevel1;
    @BindView(R.id.tv_hot_count_1)
    TextView tvHotCount1;
    @BindView(R.id.tv_host_type_1)
    TextView tvHostType1;
    @BindView(R.id.iv_crown_2)
    ImageView ivCrown2;
    @BindView(R.id.sdv_crown_2)
    SimpleDraweeView sdvCrown2;
    @BindView(R.id.tv_user_name_2)
    TextView tvUserName2;
    @BindView(R.id.tv_user_level_2)
    TextView tvUserLevel2;
    @BindView(R.id.tv_hot_count_2)
    TextView tvHotCount2;
    @BindView(R.id.tv_host_type_2)
    TextView tvHostType2;
    @BindView(R.id.iv_crown_3)
    ImageView ivCrown3;
    @BindView(R.id.sdv_crown_3)
    SimpleDraweeView sdvCrown3;
    @BindView(R.id.tv_user_name_3)
    TextView tvUserName3;
    @BindView(R.id.tv_user_level_3)
    TextView tvUserLevel3;
    @BindView(R.id.tv_hot_count_3)
    TextView tvHotCount3;
    @BindView(R.id.tv_host_type_3)
    TextView tvHostType3;
    @BindView(R.id.ll_item)
    LinearLayout llItem;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    AutoSwipeRefreshLayout mSwipeRefresh;
    private int isHasData;
    private int position = -1;

    private boolean isRefresh = false;

    private List<FanContributionList.DataBean> list;


    private LiveContributionAdapter mAdapter;


    private int page = 0;


    private int tabName; //1.日榜 2.周榜 3.月榜
    private int mType;//1.用户榜 2.主播榜


    private int type = 1;
    private FanContributionList.DataBean dataBean;
    private View mHeaderView;
    private int lastVisibleItem;
    private String targetId;

    public void getData(int page) {
        this.isRefresh = true;
        this.page = page;
        AppApis.getMyContribution(tabName, targetId, page, this);
    }

    public static LiveContributionListFragment3 newInstance(String targetId, int position) {
        LiveContributionListFragment3 liveHomeNearFragment = new LiveContributionListFragment3();
        Bundle bundle = new Bundle();
        bundle.putInt(tab_type, position);
        bundle.putString("targetId", targetId);
        liveHomeNearFragment.setArguments(bundle);
        return liveHomeNearFragment;
    }

    private void setData(FanContributionList info) {
        if (info == null || info.getData() == null || info.getData().size() == 0) {
            if (info != null) {
                isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
            }
            if (isRefresh) {
                mAdapter.clear(true);
            }
            mAdapter.showNoMoreDataView();
            mSwipeRefresh.setRefreshing(false);
            EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_CONTRIBUTION_FANS_EMPLY, 1));
            return;
        }
        isHasData = info.getData().size() >= Integer.parseInt(Urls.CURRENTCOUNT) ? 1 : 0;
        EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_CONTRIBUTION_FANS_EMPLY, 2));
        //当触发下拉刷新时
        if (isRefresh) {
            mSwipeRefresh.setRefreshing(false);
            isRefresh = false;
            if (mAdapter != null) {
                mAdapter.clear(true);
                if (list.size() > 0) {
                    list.clear();
                    if (info.getData().size() > 3) {
                        list = info.getData().subList(3, info.getData().size());
                    } else {
                        list = info.getData();
                    }
                }
                setTopContribution(info.getData());
                if (info.getData().size() > 3) {
                    mAdapter.addAll(info.getData().subList(3, info.getData().size()), true);
                } else {
                    mAdapter.showNoMoreDataView();
                    return;
                }
            }
            if (info.getData().size() > 0 && isHasData == 1) {
                page++;
            }
        } else {
            setTopContribution(info.getData());
            // 第一次加载数据
            if (mAdapter != null && mAdapter.getBasicItemCount() == 0) {
                if (info.getData().size() > 3) {
                    mAdapter.addAll(info.getData().subList(3, info.getData().size()), true);
                }
                if (info.getData().size() > 0 && isHasData == 1) {
                    page++;
                }
            } else {
                //加载更多时
//                mSwipeRefresh.setLoading(false);
                int size = info.getData().size();
                if (size > 0) {
                    for (int i = 0; i < size; i++) {
                        FanContributionList.DataBean dataBean = info.getData().get(i);
                        boolean addResult = true;
                        for (int j = 0; j < mAdapter.getList().size(); j++) {
                            FanContributionList.DataBean dataBean2 = mAdapter.getList().get(j);
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
                    mAdapter.showNoMoreDataView();
                    page++;
                } else {
                    mAdapter.showNoMoreDataView();
                }
            }
        }
    }

    //设置top榜
    private void setTopContribution(List<FanContributionList.DataBean> data) {
        if (data.size() >= 3) {
            setTop1(data.get(1));
            setTop2(data.get(0));
            setTop3(data.get(2));
        } else if (data.size() == 2) {
            setTop1(data.get(1));
            setTop2(data.get(0));
        } else if (data.size() == 1) {
            setTop2(data.get(0));
        }
    }

    private void setOnHead(String u_id) {
        if (OnClickUtils.isFastClick()) {
            Intent intent = new Intent(getContext(), MyHomePageActivity.class);
            intent.putExtra("u_id", u_id);
            getActivity().startActivity(intent);
        }
    }

    private void setHotType(FanContributionList.DataBean dataBean) {
        if (FastClickUtils.isFastClick()) {
            switch (dataBean.getR_state()) {
                case 1:
                    Intent intent = new Intent(getContext(), SlideActivity.class);
                    intent.putExtra("roomId", dataBean.getRoomId());
                    getActivity().startActivity(intent);
                    break;
                default:
                    setOnClick(0, dataBean);
                    break;
            }
        }
    }

    private void setTop1(FanContributionList.DataBean dataBean) {
        tvUserLevel1.setVisibility(View.VISIBLE);
        if (dataBean.getUserId().equals(SPUtils.getUserId())) {
            tvHostType1.setVisibility(View.GONE);
        } else {
            tvHostType1.setVisibility(View.VISIBLE);
        }
        tvHotCount1.setVisibility(View.VISIBLE);
        tvUserName1.setVisibility(View.VISIBLE);
        sdvCrown1.setImageURI(dataBean.getPicture());
        tvUserName1.setText(dataBean.getNickname());
        tvHotCount1.setText(dataBean.getSum_amount() + "热度");
        tvUserLevel1.setText(dataBean.getUserGrade() + "");
        UserLevelSetUtils.setUserLevel(tvUserLevel1, dataBean.getUserGrade());
        sdvCrown1.setOnClickListener(v -> setOnHead(dataBean.getUserId()));
        tvHostType1.setOnClickListener(v -> {
            position = 1;
            setHotType(dataBean);
        });
        switch (dataBean.getR_state()) {
            case 1:
//                tvHostType1.setText("直播中");
                tvHostType1.setBackgroundResource(R.mipmap.icon_contribution_live);
                tvHostType1.setEnabled(true);
                break;
            default:
                switch (dataBean.getState()) {//   0. 未关注   1.关注
                    case 1:
                        tvHostType1.setText("已关注");
                        tvHostType1.setTextColor(getResources().getColor(R.color.white));
                        tvHostType1.setBackgroundResource(R.drawable.bg_transparent_btn);
                        tvHostType1.setTextSize(8);
                        tvHostType1.setEnabled(false);
                        break;
                    default:
//                        tvHostType2.setText("关注");
//                        tvHostType1.setBackgroundResource(R.drawable.bg_follow_red);
                        tvHostType1.setBackgroundResource(R.mipmap.icon_contribution_follow);
                        tvHostType1.setEnabled(true);
                        break;
                }
                break;
        }
    }

    private void setTop2(FanContributionList.DataBean dataBean) {
        tvUserLevel2.setVisibility(View.VISIBLE);
        if (dataBean.getUserId().equals(SPUtils.getUserId())) {
            tvHostType2.setVisibility(View.GONE);
        } else {
            tvHostType2.setVisibility(View.VISIBLE);
        }
        tvHotCount2.setVisibility(View.VISIBLE);
        tvUserName2.setVisibility(View.VISIBLE);
        sdvCrown2.setImageURI(dataBean.getPicture());
        tvUserName2.setText(dataBean.getNickname());
        tvHotCount2.setText(dataBean.getSum_amount() + "热度");
        tvUserLevel2.setText(dataBean.getUserGrade() + "");
        UserLevelSetUtils.setUserLevel(tvUserLevel2, dataBean.getUserGrade());
        sdvCrown2.setOnClickListener(v -> setOnHead(dataBean.getUserId()));
        tvHostType2.setOnClickListener(v -> {
            position = 2;
            setHotType(dataBean);
        });
        switch (dataBean.getR_state()) {
            case 1:
//                tvHostType1.setText("直播中");
                tvHostType2.setBackgroundResource(R.mipmap.icon_contribution_live);
                tvHostType2.setEnabled(true);
                break;
            default:
                switch (dataBean.getState()) {//   0. 未关注   1.关注
                    case 1:
                        tvHostType2.setText("已关注");
                        tvHostType2.setTextColor(getResources().getColor(R.color.white));
                        tvHostType2.setBackgroundResource(R.drawable.bg_transparent_btn);
                        tvHostType2.setTextSize(8);
                        tvHostType2.setEnabled(false);
                        break;
                    default:
//                        tvHostType2.setText("关注");
//                        tvHostType1.setBackgroundResource(R.drawable.bg_follow_red);
                        tvHostType2.setBackgroundResource(R.mipmap.icon_contribution_follow);
                        tvHostType2.setEnabled(true);
                        break;
                }
                break;
        }
    }

    private void setTop3(FanContributionList.DataBean dataBean) {
        tvUserLevel3.setVisibility(View.VISIBLE);
        if (dataBean.getUserId().equals(SPUtils.getUserId())) {
            tvHostType3.setVisibility(View.GONE);
        } else {
            tvHostType3.setVisibility(View.VISIBLE);
        }
        tvHotCount3.setVisibility(View.VISIBLE);
        tvUserName3.setVisibility(View.VISIBLE);
        sdvCrown3.setImageURI(dataBean.getPicture());
        tvUserName3.setText(dataBean.getNickname());
        tvHotCount3.setText(dataBean.getSum_amount() + "热度");
        tvUserLevel3.setText(dataBean.getUserGrade() + "");
        UserLevelSetUtils.setUserLevel(tvUserLevel3, dataBean.getUserGrade());
        sdvCrown3.setOnClickListener(v -> setOnHead(dataBean.getUserId()));
        tvHostType3.setOnClickListener(v -> {
            position = 3;
            setHotType(dataBean);
        });
        switch (dataBean.getR_state()) {
            case 1:
//                tvHostType1.setText("直播中");
                tvHostType3.setBackgroundResource(R.mipmap.icon_contribution_live);
                tvHostType3.setEnabled(true);
                break;
            default:
                switch (dataBean.getState()) {//   0. 未关注   1.关注
                    case 1:
                        tvHostType3.setText("已关注");
                        tvHostType3.setTextColor(getResources().getColor(R.color.white));
                        tvHostType3.setBackgroundResource(R.drawable.bg_transparent_btn);
                        tvHostType3.setTextSize(8);
                        tvHostType3.setEnabled(false);
                        break;
                    default:
//                        tvHostType2.setText("关注");
//                        tvHostType1.setBackgroundResource(R.drawable.bg_follow_red);
                        tvHostType3.setBackgroundResource(R.mipmap.icon_contribution_follow);
                        tvHostType3.setEnabled(true);
                        break;
                }
                break;
        }
    }


    protected void initData() {
        super.initData();
        backdrop.setImageResource(R.mipmap.icon_fans_contribution_bg);
    }

    protected void initView(View paramView) {
        super.initView(paramView);
        list = new ArrayList<>();
        if (getArguments() != null) {
            tabName = getArguments().getInt(tab_type);
            targetId = getArguments().getString("targetId");
            LogUtils.e("targetId=", targetId);
        }
        mAdapter = new LiveContributionAdapter(getActivity(), list, 1);
        mAdapter.setLoadMoreFooterView(new DefaultLoadMoreFooterView(getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setBackgroundResource(R.mipmap.icon_contribution_list_bg);
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setOnLoadListener(this::onLoadMore);
        //设置可上滑的条数
        mSwipeRefresh.setItemCount(12);
        mAdapter.setOnItemClickListener((type, dataBean) -> {
            position = -1;
            setOnClick(type, dataBean);
        });
        mFlagShowNoNetworkToast = false;
        mFlagShowDefaultFailToast = false;
        switch (tabName) {
            case 1:
                getData(page);
                break;
        }
    }

    private void setOnClick(int state, FanContributionList.DataBean bean) {
        this.dataBean = bean;
        AppApis.getUpdateFollow(bean.getState() == 0 ? 1 : 0, bean.getUserId(), this);
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
    }

    public void onLoadMore() {
        if (isHasData == 1) {
            mAdapter.showLoadMoreView();
            getData(page);
        }
    }

    public void onRefresh() {
        isRefresh = true;
        page = 0;
        getData(page);
    }

    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (Urls.GET_FAN_CONTRIBUTION_LIST.equals(url)) {
            if (obj != null && obj instanceof FanContributionList) {
                FanContributionList liveHomeBeanInfo = (FanContributionList) obj;
                if (liveHomeBeanInfo.getCode() == 200) {
                    setData(liveHomeBeanInfo);
                } else {
                    mSwipeRefresh.setRefreshing(false);
                    EventBus.getDefault().post(new PostResult(Constants.EVENTBUS.EVENTBUS_CONTRIBUTION_FANS_EMPLY, 1));
                }
            }
        } else if (url.equals(Urls.GET_UPDATE_FOLLOW)) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                if (jSONObject.getInt("code") == 200) {
                    switch (position) {
                        case 1:
                            setTvHost(tvHostType1);
                            break;
                        case 2:
                            setTvHost(tvHostType2);
                            break;
                        case 3:
                            setTvHost(tvHostType3);
                            break;
                        default:
                            if (dataBean != null && mAdapter != null) {
                                FanContributionList.DataBean info = dataBean;
                                info.setState(dataBean.getState() == 0 ? 1 : 0);
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
                            break;
                    }
                } else {
                    ToastUtils.showShortToast(jSONObject.getString("msg"));
                }
            } catch (JSONException jSONException) {
                jSONException.printStackTrace();
            }
        }
    }

    private void setTvHost(TextView view) {
        ToastUtils.showShortToast("关注成功");
        view.setText("已关注");
        view.setTextColor(getResources().getColor(R.color.white));
        view.setTextSize(8);
        view.setBackgroundResource(R.drawable.bg_transparent_btn);
        view.setEnabled(false);
    }

    protected int setLayoutId() {
        return R.layout.fragment_contribution_list;
    }
}