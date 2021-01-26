package com.jk.jkproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.activity.WebViewActivity;
import com.jk.jkproject.ui.adapter.LiveFoundCarouselAdapter;
import com.jk.jkproject.ui.adapter.LiveFoundRecommendAdapter;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.entity.LiveHomeFoundBaseInfo;
import com.jk.jkproject.ui.widget.CircleIndicator;
import com.jk.jkproject.ui.widget.SpaceFoundItemDecoration;
import com.jk.jkproject.ui.widget.room.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Zick
 * @params
 * @date 2020/8/1 3:10 PM
 * @desc 发现
 */
public class LiveFoundFragment extends BFragment implements AutoScrollViewPager.CallBack, SwipeRefreshLayout.OnRefreshListener {
    private static int MESSAGE_TYPE = 291;

    private static String tab_type = "tab_type";
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
    @BindView(R.id.live_vp_pager)
    AutoScrollViewPager liveVpPager;
    @BindView(R.id.live_viewpager_tab)
    CircleIndicator liveViewpagerTab;
    @BindView(R.id.iv_hot)
    ImageView ivHot;
    @BindView(R.id.iv_crown)
    ImageView ivCrown;
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.iv_crown_host)
    ImageView ivCrownHost;
    @BindView(R.id.sdv_header_host)
    SimpleDraweeView sdvHeaderHost;
    @BindView(R.id.tv_user_name_host)
    TextView tvUserNameHost;
    @BindView(R.id.rl_host)
    RelativeLayout rlHost;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private View.OnClickListener bannerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Object obj = v.getTag(R.id.tag_position);
            if (obj != null && obj instanceof Integer) {
                int position = (int) obj;
                LiveHomeFoundBaseInfo.DataBean.CarouselBean info = headerAdapter.getItem(position);
                if (info != null && !TextUtils.isEmpty(info.getArrive())) {
                    Intent intent = null;
                    switch (info.getType()) {
                        case "1":
                            intent = new Intent(getActivity(), WebViewActivity.class);
                            intent.putExtra("type", 11);
                            intent.putExtra("url", info.getArrive());
                            startActivity(intent);
                            break;
                        case "2":
                            intent = new Intent(getActivity(), SlideActivity.class);
                            intent.putExtra("roomId", info.getArrive());
                            startActivity(intent);
                            break;
                    }
                }
            }
        }
    };

    private LiveFoundCarouselAdapter headerAdapter;

    private List<LiveHomeBeanInfo.DataBean> list;

    private LiveFoundRecommendAdapter mAdapter;

    private ProgressBar pb;

    private int progress;

    private int size;

    private String tabName = "";

    public Handler updateBarHandler = new Handler() {
        public void handleMessage(Message param1Message) {
            if (LiveFoundFragment.MESSAGE_TYPE == param1Message.what) {
                if (pb != null) {
                    pb.setProgress(progress);
                }
                if (progressBar != null) {
                    progressBar.setProgress(progress);
                }
            }
        }
    };
    private ProgressBar progressBar;

    private void addBannerView(List<LiveHomeFoundBaseInfo.DataBean.CarouselBean> paramList) {
        this.size = paramList.size();
        this.headerAdapter.setdata(paramList);
        this.headerAdapter.setBannerClickListener(this.bannerClickListener);
        this.liveVpPager.setAdapter((PagerAdapter) this.headerAdapter);
        this.liveVpPager.setOffscreenPageLimit(this.size);
        this.liveViewpagerTab.setViewPager((ViewPager) this.liveVpPager);
        if (this.size > 1) {
            this.headerAdapter.setInfiniteLoop(true);
            this.liveVpPager.startAutoScroll(3000);
        }
    }

    public static LiveFoundFragment newInstance(String paramString) {
        LiveFoundFragment liveFoundFragment = new LiveFoundFragment();
        Bundle bundle = new Bundle();
        bundle.putString(tab_type, paramString);
        liveFoundFragment.setArguments(bundle);
        return liveFoundFragment;
    }

    private void setQuestRecommendData() {
        AppApis.getHomeRecommendedData(this);
    }

    public void OnListener(String paramString1, String paramString2) {
    }

    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            tabName = getArguments().getString(tab_type);
        }
        AppApis.getHomeFoundData(this);
        AppApis.getHomeRecommendedData(this);

    }

    protected void initView(View paramView) {
        super.initView(paramView);
        headerAdapter = new LiveFoundCarouselAdapter((Context) getActivity());
        liveVpPager.setInterval(getResources().getInteger(R.integer.banner_interval));
        liveVpPager.setAdapter((PagerAdapter) this.headerAdapter);
        liveVpPager.setOffscreenPageLimit(1);
        liveViewpagerTab.setViewPager((ViewPager) this.liveVpPager);
        liveVpPager.setListener(this);
        swipe.setOnRefreshListener(this);
        list = new ArrayList<>();
        mAdapter = new LiveFoundRecommendAdapter((Context) getActivity(), list, R.layout.live_found_recommend);
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(), 2);
        recyclerView.addItemDecoration(new SpaceFoundItemDecoration(mContext,9));
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager((RecyclerView.LayoutManager) gridLayoutManager);
        recyclerView.setScrollBarStyle(RecyclerView.SCROLLBARS_OUTSIDE_OVERLAY);
        recyclerView.setAdapter((RecyclerView.Adapter) this.mAdapter);
        ivTitle.setVisibility(View.GONE);
        tvTitle.setText("发现");
        mAdapter.setOnItemClickListener(new LiveFoundRecommendAdapter.OnItemClickListener() {
            public void click(ProgressBar pb) {
                LogUtils.e("触发子线程");
                setStart(pb);
            }

            @Override
            public void positionClick(ProgressBar view) {
                setposition(view);
            }


        });
    }

    private void setStart(ProgressBar pb) {
        this.pb = pb;
        (new MyThread()).start();
    }

    private void setposition(ProgressBar view) {
        progressBar = view;
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.updateBarHandler;
        if (handler != null) {
            handler.removeMessages(MESSAGE_TYPE);
            this.updateBarHandler = null;
        }
    }

    public void onFailure(int paramInt, String paramString1, String paramString2) {
        super.onFailure(paramInt, paramString1, paramString2);
    }

    public void onSuccess(String paramString, Object paramObject) {
        LiveHomeFoundBaseInfo.DataBean.RankingListBean rankingListBean;
        super.onSuccess(paramString, paramObject);
        swipe.setRefreshing(false);
        if (paramString.equals(Urls.GET_HOME_FOUND_LIST)) {
            if (paramObject != null && paramObject instanceof LiveHomeFoundBaseInfo) {
                LiveHomeFoundBaseInfo liveHomeFoundBaseInfo = (LiveHomeFoundBaseInfo) paramObject;
                if (liveHomeFoundBaseInfo.getCode() == 200) {
                    addBannerView(liveHomeFoundBaseInfo.getData().getCarousel());
                    for (int i = 0; i < liveHomeFoundBaseInfo.getData().getRankingList().size(); i++) {
                        rankingListBean = liveHomeFoundBaseInfo.getData().getRankingList().get(i);
                        if (rankingListBean.getRanking() == 1) {
                            this.sdvHeaderHost.setImageURI(rankingListBean.getPicture());
                            this.tvUserNameHost.setText(rankingListBean.getNickName());
                            continue;
                        }
                        this.sdvHeader.setImageURI(rankingListBean.getPicture());
                        this.tvUserName.setText(rankingListBean.getNickName());
                    }
                }
            }
        } else if (paramString.equals(Urls.GET_HOME_REMANNDE_LIST) && paramObject != null && paramObject instanceof LiveHomeBeanInfo) {
            LiveHomeBeanInfo info = (LiveHomeBeanInfo) paramObject;
            if (info.getCode() == 200) {
                if (mAdapter != null) {
                    progress = 0;
                    mAdapter.setDatas(info.getData());
                }
            }
        }
    }

    protected int setLayoutId() {
        return R.layout.fragment_found;
    }

    @OnClick({R.id.rl_user, R.id.rl_host})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_user:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("type", 4);
                startActivity(intent);
                break;
            case R.id.rl_host:
                intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        AppApis.getHomeFoundData(this);
        if (mAdapter.getDatas().size() <= 0) {
            AppApis.getHomeRecommendedData(this);
        }
    }

    private class MyThread extends Thread {
        private MyThread() {
        }

        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                if (progress == 100) {
                    setQuestRecommendData();
                    progress = 0;
                    return;
                }
                progress++;
                Message message = new Message();
                message.what = MESSAGE_TYPE;
                updateBarHandler.sendMessage(message);
            }
        }
    }
}