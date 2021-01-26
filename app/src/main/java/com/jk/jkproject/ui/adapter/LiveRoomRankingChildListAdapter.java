package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.dialog.DialogLiveRoomRankList;
import com.jk.jkproject.ui.entity.LiveRankInfoBean;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.StringUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LiveRoomRankingChildListAdapter extends BaseWrapperRecyclerAdapter<LiveRankInfoBean.DataBean, LiveRoomRankingChildListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {

    private Context context;

    private List<LiveRankInfoBean.DataBean> list = new ArrayList<LiveRankInfoBean.DataBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    private int type;

    public LiveRoomRankingChildListAdapter(Context paramContext, List<LiveRankInfoBean.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, int paramInt) {
        this.list = getList();
        if (this.list.size() > 0) {
            final LiveRankInfoBean.DataBean dataBean = list.get(paramInt);
            if (dataBean.getUserId().equals(SPUtils.getUserId())) {
                paramItemViewHolder.tvHostType.setVisibility(View.GONE);
            }
            switch (dataBean.getId()) {
                default:
                    if (dataBean.getId() == -2) {
                        paramItemViewHolder.tvRank.setText("未上榜");
                        paramItemViewHolder.tvRank.setTextSize(11);
                    } else if (dataBean.getId() == -1) {
                        paramItemViewHolder.tvRank.setText("99+");
                    } else {
                        paramItemViewHolder.tvRank.setText(dataBean.getId() + "");
                    }
                    paramItemViewHolder.tvRank.setCompoundDrawables(null, null, null, null);
                    break;
                case 3:
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.icon_live_room_contribution_3);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    paramItemViewHolder.tvRank.setCompoundDrawables(drawable, null, null, null);
                    break;
                case 2:
                    drawable = this.context.getResources().getDrawable(R.mipmap.icon_live_room_contribution_2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    paramItemViewHolder.tvRank.setCompoundDrawables(drawable, null, null, null);
                    break;
                case 1:
                    drawable = this.context.getResources().getDrawable(R.mipmap.icon_live_room_contribution_1);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    paramItemViewHolder.tvRank.setCompoundDrawables(drawable, null, null, null);
                    break;
            }
            paramItemViewHolder.sdvHeader.setImageURI(dataBean.getPicture());
            paramItemViewHolder.tvUserName.setText(dataBean.getNickname());
            paramItemViewHolder.tvUserHot.setText(StringUtils.ReadSize(dataBean.getSum_hot())+"热度");
            paramItemViewHolder.tvUserNameHost.setText(dataBean.getUserGrade());
            UserLevelSetUtils.setUserLevel(paramItemViewHolder.tvUserNameHost, dataBean.getUserGrade());
            switch (dataBean.getR_state()) {
                case 1:
                    if (DialogLiveRoomRankList.uid.equals(dataBean.getUserId())) {
                        switch (dataBean.getState()) {
                            case 1:
                                paramItemViewHolder.tvHostType.setText("已关注");
                                paramItemViewHolder.tvHostType.setBackgroundColor(this.context.getResources().getColor(R.color.transparent));
                                paramItemViewHolder.tvHostType.setEnabled(false);
                                break;
                            case 0:
                                paramItemViewHolder.tvHostType.setBackgroundResource(R.drawable.bg_live_room_red_follow);
                                paramItemViewHolder.tvHostType.setText("关注");
                                paramItemViewHolder.tvHostType.setEnabled(true);
                                break;
                        }
                    } else {
                        paramItemViewHolder.tvHostType.setText("直播中");
                        paramItemViewHolder.tvHostType.setBackgroundResource(R.drawable.bg_live_room_red);
                    }
                    break;
                case 0:
                    switch (dataBean.getState()) {
                        default:
                            break;
                        case 1:
                            paramItemViewHolder.tvHostType.setText("已关注");
                            paramItemViewHolder.tvHostType.setBackgroundColor(this.context.getResources().getColor(R.color.transparent));
                            paramItemViewHolder.tvHostType.setEnabled(false);
                            break;
                        case 0:
                            paramItemViewHolder.tvHostType.setText("关注");
                            paramItemViewHolder.tvHostType.setBackgroundResource(R.drawable.bg_live_room_red_follow);
                            paramItemViewHolder.tvHostType.setEnabled(true);
                            break;
                    }
                    break;
            }
            paramItemViewHolder.rlItem.setOnClickListener(v -> {
                if (OnClickUtils.isFastClick() && onItemClickListener != null) {
                    onItemClickListener.onItemClick(2, dataBean);
                }
            });
            paramItemViewHolder.tvHostType.setOnClickListener(param1View -> {
                if (OnClickUtils.isFastClick() && onItemClickListener != null) {
                    onItemClickListener.onItemClick(1, dataBean);
                }
            });
        }
    }

    public void onClick(View paramView, int paramInt) {
        if (FuncUtils.isFastDoubleClick())
            return;
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_room_rank_list, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
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

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.tvRank = (TextView) param1View.findViewById(R.id.tv_rank);
            this.sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            this.tvUserName = (TextView) param1View.findViewById(R.id.tv_user_name);
            this.tvUserHot = (TextView) param1View.findViewById(R.id.tv_user_hot);
            this.tvUserNameHost = (TextView) param1View.findViewById(R.id.tv_user_name_host);
            this.tvHostType = (TextView) param1View.findViewById(R.id.tv_host_type);
            this.llItem = (RelativeLayout) param1View.findViewById(R.id.ll_item);
            this.rlItem = (RelativeLayout) param1View.findViewById(R.id.rl_item);
            addOnItemViewClickListener();
            addOnViewClickListener(param1View);
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(int type, LiveRankInfoBean.DataBean param1DataBean);
    }
}
