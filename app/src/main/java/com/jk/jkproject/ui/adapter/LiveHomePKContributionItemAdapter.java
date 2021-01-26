package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.PKContributionListBaseInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Zick
 * @params
 * @date 2020/7/31 2:00 PM
 * @desc 首页附近
 */
public class LiveHomePKContributionItemAdapter extends BaseWrapperRecyclerAdapter<PKContributionListBaseInfo.DataBean, LiveHomePKContributionItemAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {


    private Context context;

    private List<PKContributionListBaseInfo.DataBean> list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public LiveHomePKContributionItemAdapter(Context paramContext, List<PKContributionListBaseInfo.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder viewHolder, int paramInt) {
        this.list = getList();
        if (this.list.size() > 0) {
            PKContributionListBaseInfo.DataBean dataBean = this.list.get(paramInt);
            viewHolder.tvRank.setText("0" + (paramInt + 1));
            switch (dataBean.getIsMVP()) {
                case 1:
                    viewHolder.sdvHeader.setBackgroundResource(R.mipmap.bg_found_hollow_circle);
                    viewHolder.ivRankMvp.setVisibility(View.VISIBLE);
                    viewHolder.ivRankCrown.setVisibility(View.VISIBLE);
                    viewHolder.sdvHeader.setImageURI(dataBean.getPicture());
                    break;
                case 0:
                    viewHolder.ivRankMvp.setVisibility(View.GONE);
                    viewHolder.ivRankCrown.setVisibility(View.GONE);
                    viewHolder.sdvHeader.setImageURI(dataBean.getPicture());
                    break;
            }
            viewHolder.tvBalance.setText(dataBean.getSum_amount() + "");
            viewHolder.tvName.setText(dataBean.getNickname());
            viewHolder.tvUserLevel.setText(dataBean.getUserGrade());
            UserLevelSetUtils.setUserLevel(viewHolder.tvUserLevel, dataBean.getUserGrade());
        }
    }

    public void onClick(View paramView, int paramInt) {
        if (FuncUtils.isFastDoubleClick())
            return;
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_room_pk_contribution_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.tv_rank)
        TextView tvRank;
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.rl_win_header)
        RelativeLayout rlWinHeader;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_user_level)
        TextView tvUserLevel;
        @BindView(R.id.tv_balance)
        TextView tvBalance;
        @BindView(R.id.iv_rank_crown)
        ImageView ivRankCrown;
        @BindView(R.id.iv_rank_mvp)
        ImageView ivRankMvp;

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            this.tvRank = (TextView) param1View.findViewById(R.id.tv_rank);
            this.rlWinHeader = param1View.findViewById(R.id.rl_win_header);
            this.tvName = (TextView) param1View.findViewById(R.id.tv_name);
            this.tvUserLevel = (TextView) param1View.findViewById(R.id.tv_user_level);
            this.tvBalance = param1View.findViewById(R.id.tv_balance);
            this.ivRankCrown = param1View.findViewById(R.id.iv_rank_crown);
            this.ivRankMvp = param1View.findViewById(R.id.iv_rank_mvp);
        }
    }

    public static interface OnItemClickListener {
        void terminationClick(View param1View, String param1String);
    }
}
