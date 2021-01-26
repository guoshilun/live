package com.jk.jkproject.ui.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.ui.adapter.LiveGiftRecyclerViewAdapter;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;
import com.jk.jkproject.ui.entity.LiveGiftTab;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.ui.widget.pagescroll.HorizontalPageLayoutManager;
import com.jk.jkproject.ui.widget.pagescroll.PageIndicatorView;
import com.jk.jkproject.ui.widget.pagescroll.PagingScrollHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class LiveGrifDialogFragment extends BFragment implements PagingScrollHelper.onPageChangeListener, LiveGiftRecyclerViewAdapter.OnItemClickListener {
    private static final String mLiveGift = "liveData";

    private static final String mPosition = "position";
    @BindView(R.id.live_gift_prv)
    RecyclerView liveGiftPrv;
    @BindView(R.id.live_gift_piv)
    PageIndicatorView liveGiftPiv;
    Unbinder unbinder;
    @BindView(R.id.empty)
    View emptyView;

    private LiveGiftInfoBean.DataBean giftInfo;

    private LiveGiftRecyclerViewAdapter giftRecyclerViewAdapter;

    private boolean isupdate = false;

    private List<LiveGiftInfoBean.DataBean> mGiftDatas;

    private List<LiveGiftTab> mLiveGiftTab;

    private int pointCount;

    private int select_position;

    private String tab_position;

    public static LiveGrifDialogFragment newInstance(String paramString, List<LiveGiftInfoBean.DataBean> paramList) {
        Bundle bundle = new Bundle();
        LiveGrifDialogFragment liveGrifDialogFragment = new LiveGrifDialogFragment();
        bundle.putString("position", paramString);
        bundle.putParcelableArrayList("mGiftDatas", (ArrayList) paramList);
        liveGrifDialogFragment.setArguments(bundle);
        return liveGrifDialogFragment;
    }

    protected void initData() {
        super.initData();
        this.tab_position = getArguments().getString("position");
        this.mGiftDatas = getArguments().getParcelableArrayList("mGiftDatas");
        if (mGiftDatas.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
        }else{
            for (int i = 0;i < mGiftDatas.size();i++){
                mGiftDatas.get(i).setSelect_item(false);
            }
            mGiftDatas.get(0).setSelect_item(true);
            this.giftInfo = mGiftDatas.get(0);
            //EventBus.getDefault().post(new PostResult("eventbus_gift_select", this.giftInfo));
            emptyView.setVisibility(View.GONE);
        }
    }

    protected void initView(View paramView) {
        super.initView(paramView);
    }

    @Subscribe
    protected boolean isBindEventBusHere() {
        return true;
    }

    public void onClick(int paramInt) {
    }

    public void onClick(View paramView, int paramInt) {
    }

    @Subscribe
    public void onEvent(PostResult paramPostResult) {
        super.onEvent(paramPostResult);
    }

    public void onItemClick(View paramView, LiveGiftInfoBean.DataBean paramDataBean, int paramInt) {
        this.giftInfo = paramDataBean;
        paramDataBean.setSelect_position(paramInt);
        this.select_position = paramInt;
        EventBus.getDefault().post(new PostResult("eventbus_gift_select", paramDataBean));
    }

    public void onPageChange(int paramInt) {
        if (liveGiftPiv != null)
            liveGiftPiv.setSelectedPage(paramInt);
    }

    public void setGiftData(List<LiveGiftInfoBean.DataBean> paramList) {
        if (paramList != null && paramList.size() > 0) {
            if (giftRecyclerViewAdapter != null) {
                giftRecyclerViewAdapter.setLiveGiftInfos(paramList);
            }
        }
    }

    public void setPocketData(){
        if (mGiftDatas.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
        }else{
            for (LiveGiftInfoBean.DataBean data : mGiftDatas) {
                if (data.isSelect_item()){
                    this.giftInfo = data;
                    break;
                }
            }
            EventBus.getDefault().post(new PostResult("eventbus_gift_select", this.giftInfo));
            emptyView.setVisibility(View.GONE);
        }
        giftRecyclerViewAdapter.notifyDataSetChanged();
    }

    protected int setLayoutId() {
        return R.layout.live_gift_recyclerview;
    }

    protected void setListener() {
        super.setListener();
        if (this.mGiftDatas.size() % 8 > 0) {
            this.pointCount = this.mGiftDatas.size() / 8 + 1;
            this.liveGiftPiv.initIndicator(this.pointCount);
        } else {
            this.pointCount = this.mGiftDatas.size() / 8;
            this.liveGiftPiv.initIndicator(this.pointCount);
        }
        PagingScrollHelper pagingScrollHelper = new PagingScrollHelper();
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(2, 4);
        giftRecyclerViewAdapter = new LiveGiftRecyclerViewAdapter(mContext, mGiftDatas, tab_position, this);
        liveGiftPrv.setAdapter(giftRecyclerViewAdapter);
        ((DefaultItemAnimator) liveGiftPrv.getItemAnimator()).setSupportsChangeAnimations(false);
        this.liveGiftPrv.setItemViewCacheSize(-1);
        pagingScrollHelper.setUpRecycleView(this.liveGiftPrv);
        pagingScrollHelper.setOnPageChangeListener(this);
        this.liveGiftPrv.setLayoutManager(horizontalPageLayoutManager);
        pagingScrollHelper.updateLayoutManger();
        this.liveGiftPrv.setHorizontalScrollBarEnabled(true);
        this.liveGiftPrv.setItemAnimator(null);
        pagingScrollHelper.scrollToPosition(0);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && this.giftInfo != null){
            EventBus.getDefault().post(new PostResult("eventbus_gift_select", this.giftInfo));
        }
    }
}