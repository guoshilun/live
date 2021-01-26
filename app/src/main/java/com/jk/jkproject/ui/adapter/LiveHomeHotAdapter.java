package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;
import com.facebook.fresco.helper.utils.DensityUtil;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.widget.CircleIndicator;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.room.AutoScrollViewPager;
import com.jk.jkproject.utils.FastClickUtils;
import com.jk.jkproject.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LiveHomeHotAdapter extends BaseWrapperRecyclerAdapter<LiveHomeBeanInfo.DataBean, LiveHomeHotAdapter.ItemViewHolder> implements AutoScrollViewPager.CallBack {

    private Context context;

    private List<LiveHomeBeanInfo.DataBean> list = new ArrayList<LiveHomeBeanInfo.DataBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public LiveHomeHotAdapter(Context paramContext, List<LiveHomeBeanInfo.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }

    public void OnListener(String paramString1, String paramString2) {
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, final int position) {
        final LiveHomeBeanInfo.DataBean dataBean = getItem(position);
        if (dataBean != null) {
            LogUtils.e("roomId=", dataBean.getRoomId());
            int rank = position;
            paramItemViewHolder.tvTop.setText("TOP" + rank);
            switch (rank) {
                case 1:
                    paramItemViewHolder.tvTop.setBackgroundResource(R.mipmap.icon_hot_top1);
                    break;
                case 2:
                    paramItemViewHolder.tvTop.setBackgroundResource(R.mipmap.icon_hot_top2);
                    break;
                case 3:
                    paramItemViewHolder.tvTop.setBackgroundResource(R.mipmap.icon_hot_top3);
                    break;
                default:
                    paramItemViewHolder.tvTop.setBackgroundResource(R.mipmap.icon_hot_top4);
                    break;
            }
            paramItemViewHolder.tvCount.setText(dataBean.getPeopleNumber() + "");
            paramItemViewHolder.tvTitle.setText(dataBean.getR_name());
            paramItemViewHolder.tvDistance.setText(StringUtils.getDistance(dataBean.getDistance()));
            ImageLoader.loadImage(paramItemViewHolder.sdvPic, dataBean.getR_cover(), (DensityUtil.getDisplayWidth(this.context) - DensityUtil.dipToPixels(this.context, 9)) / 2, DensityUtil.getDisplayWidth(this.context) / 2)
            ;
            paramItemViewHolder.llItem.setOnClickListener(param1View -> {
                if (FastClickUtils.isFastClick() && NetworkUtils.isConnected()) {
                    Intent intent = new Intent(context, SlideActivity.class);
                    intent.putExtra("roomId", dataBean.getRoomId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_home_hot, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.live_vp_pager)
        AutoScrollViewPager headerPager;
        @BindView(R.id.live_viewpager_tab)
        CircleIndicator tab;
        @BindView(R.id.sdv_pic)
        SimpleDraweeView sdvPic;
        @BindView(R.id.tv_top)
        TextView tvTop;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.rl_item)
        LinearLayout llItem;

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.tvTop = (TextView) param1View.findViewById(R.id.tv_top);
            this.tvCount = (TextView) param1View.findViewById(R.id.tv_count);
            this.tvTitle = (TextView) param1View.findViewById(R.id.tv_title);
            this.tvDistance = (TextView) param1View.findViewById(R.id.tv_distance);
            this.sdvPic = (SimpleDraweeView) param1View.findViewById(R.id.sdv_pic);
            this.llItem = (LinearLayout) param1View.findViewById(R.id.rl_item);
            this.headerPager = (AutoScrollViewPager) param1View.findViewById(R.id.live_vp_pager);
            this.tab = (CircleIndicator) param1View.findViewById(R.id.live_viewpager_tab);
        }
    }

    public static interface OnItemClickListener {
        void terminationClick(View param1View, String param1String);
    }
}