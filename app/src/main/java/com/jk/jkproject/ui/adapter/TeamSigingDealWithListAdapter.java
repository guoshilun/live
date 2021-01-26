package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.TeamSigingDealWithInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeamSigingDealWithListAdapter extends BaseWrapperRecyclerAdapter<TeamSigingDealWithInfo.DataBean, TeamSigingDealWithListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {

    private Context context;

    private List<TeamSigingDealWithInfo.DataBean> list = new ArrayList<TeamSigingDealWithInfo.DataBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public TeamSigingDealWithListAdapter(Context paramContext, List<TeamSigingDealWithInfo.DataBean> paramList) {
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
            final TeamSigingDealWithInfo.DataBean dataBean = this.list.get(paramInt);
            if (dataBean != null) {
                if (!TextUtils.isEmpty(dataBean.getNickName())) {
                    paramItemViewHolder.tvTeamName.setText(dataBean.getNickName());
                } else {
                    paramItemViewHolder.tvTeamName.setText("--");
                }
                switch (dataBean.getState()) {
                    case 2:
                        paramItemViewHolder.tvTeamAgree.setVisibility(View.GONE);
                        paramItemViewHolder.tvTeamRefused.setVisibility(View.GONE);
                        paramItemViewHolder.tvTeamTime.setVisibility(View.VISIBLE);
                        paramItemViewHolder.tvTeamState.setVisibility(View.GONE);
                        paramItemViewHolder.tvTeamTime.setText("已拒绝");
                        paramItemViewHolder.tvTeamTime.setTextColor(this.context.getResources().getColor(R.color.color_999));
                        if (!TextUtils.isEmpty(dataBean.getUid())) {
                            paramItemViewHolder.tvTeamAsa.setText(dataBean.getUid());
                            break;
                        }
                        paramItemViewHolder.tvTeamAsa.setText("--");
                        break;
                    case 1:
                        paramItemViewHolder.tvTeamAgree.setVisibility(View.GONE);
                        paramItemViewHolder.tvTeamRefused.setVisibility(View.GONE);
                        paramItemViewHolder.tvTeamTime.setVisibility(View.VISIBLE);
                        paramItemViewHolder.tvTeamState.setVisibility(View.GONE);
                        paramItemViewHolder.tvTeamTime.setText("已审批");
                        paramItemViewHolder.tvTeamTime.setTextColor(this.context.getResources().getColor(R.color.color_FFA723));
                        if (!TextUtils.isEmpty(dataBean.getUid())) {
                            paramItemViewHolder.tvTeamAsa.setText(dataBean.getUid());
                            break;
                        }
                        paramItemViewHolder.tvTeamAsa.setText("--");
                        break;
                    case 0:
                        paramItemViewHolder.tvTeamAgree.setVisibility(View.VISIBLE);
                        paramItemViewHolder.tvTeamRefused.setVisibility(View.VISIBLE);
                        paramItemViewHolder.tvTeamState.setVisibility(View.VISIBLE);
                        paramItemViewHolder.tvTeamState.setText("待审批");
                        paramItemViewHolder.tvTeamTime.setVisibility(View.GONE);
                        paramItemViewHolder.tvTeamState.setTextColor(this.context.getResources().getColor(R.color.color_149CFF));
                        if (!TextUtils.isEmpty(dataBean.getEx_msg())) {
                            paramItemViewHolder.tvTeamAsa.setText(dataBean.getEx_msg());
                            break;
                        }
                        paramItemViewHolder.tvTeamAsa.setText("--");
                        break;
                }
                paramItemViewHolder.sdvTeamHeader.setImageURI(dataBean.getU_picture());
                paramItemViewHolder.tvTeamAgree.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        if (FuncUtils.isFastDoubleClick())
                            return;
                        if (TeamSigingDealWithListAdapter.this.onItemClickListener != null)
                            TeamSigingDealWithListAdapter.this.onItemClickListener.onClick(1, dataBean.getId());
                    }
                });
                paramItemViewHolder.tvTeamRefused.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        if (FuncUtils.isFastDoubleClick())
                            return;
                        if (TeamSigingDealWithListAdapter.this.onItemClickListener != null)
                            TeamSigingDealWithListAdapter.this.onItemClickListener.onClick(2, dataBean.getId());
                    }
                });
            }
        }
    }

    public void onClick(View paramView, int paramInt) {
        if (FuncUtils.isFastDoubleClick())
            return;
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.team_siging_deal_with_list, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_team_header)
        SimpleDraweeView sdvTeamHeader;
        @BindView(R.id.tv_team_name)
        TextView tvTeamName;
        @BindView(R.id.tv_team_state)
        TextView tvTeamState;
        @BindView(R.id.tv_team_refused)
        TextView tvTeamRefused;
        @BindView(R.id.tv_team_agree)
        TextView tvTeamAgree;
        @BindView(R.id.tv_team_Asa)
        TextView tvTeamAsa;
        @BindView(R.id.tv_team_id)
        TextView tvTeamId;
        @BindView(R.id.tv_team_time)
        TextView tvTeamTime;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;

        public ItemViewHolder(View param1View) {
            super(param1View);
//            this.tvTeamTime = (TextView) param1View.findViewById(2131297026);
            addOnItemViewClickListener();
            addOnViewClickListener(param1View);
        }
    }

    public static interface OnItemClickListener {
        void onClick(int param1Int1, int param1Int2);
    }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\TeamSigingDealWithListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */