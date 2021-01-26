package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.TeamSigingDealWithActivity;
import com.jk.jkproject.ui.entity.TeamMembersManagermentInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeamMembersSingerListAdapter extends BaseWrapperRecyclerAdapter<TeamMembersManagermentInfo.DataBean.DataListBean, TeamMembersSingerListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {

    private Context context;

    private List<TeamMembersManagermentInfo.DataBean.DataListBean> list = new ArrayList<TeamMembersManagermentInfo.DataBean.DataListBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public TeamMembersSingerListAdapter(Context paramContext, List<TeamMembersManagermentInfo.DataBean.DataListBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, int paramInt) {
        list = getList();
        if (list.size() > 0) {
            final TeamMembersManagermentInfo.DataBean.DataListBean dataListBean = this.list.get(paramInt);
            if (dataListBean != null) {
                if (paramInt == 0) {
                    paramItemViewHolder.rlTeamHost.setVisibility(View.VISIBLE);
                    paramItemViewHolder.rlTeamSinging.setVisibility(View.VISIBLE);
                    paramItemViewHolder.tvTeamCount.setText(dataListBean.getDataNumber() + "/" + dataListBean.getDataSumNumber() + "人");
                    if (dataListBean.getJoinNumber() > 99) {
                        paramItemViewHolder.tvTeamSingerCount.setText("99条");
                    } else {
                        paramItemViewHolder.tvTeamSingerCount.setText(dataListBean.getJoinNumber() + "条");
                    }
                } else {
                    paramItemViewHolder.rlTeamHost.setVisibility(View.GONE);
                    paramItemViewHolder.rlTeamSinging.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(dataListBean.getNickName())) {
                    paramItemViewHolder.tvTeamName.setText(dataListBean.getNickName());
                } else {
                    paramItemViewHolder.tvTeamName.setText("--");
                }
                TextView textView2 = paramItemViewHolder.tvTeamAsa;
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("队长抽成 ");
                stringBuilder1.append(dataListBean.getPump());
                textView2.setText(stringBuilder1.toString());
                TextView textView1 = paramItemViewHolder.tvTeamId;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("ID ");
                stringBuilder2.append(dataListBean.getTeam_uid());
                textView1.setText(stringBuilder2.toString());
                textView1 = paramItemViewHolder.tvTeamTime;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("签约剩余时长 ");
                stringBuilder2.append(dataListBean.getContract_time());
                textView1.setText(stringBuilder2.toString());
                paramItemViewHolder.sdvTeamHeader.setImageURI(dataListBean.getU_picture());
                paramItemViewHolder.tvTeamTermination.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        if (FuncUtils.isFastDoubleClick())
                            return;
                        if (TeamMembersSingerListAdapter.this.onItemClickListener != null)
                            TeamMembersSingerListAdapter.this.onItemClickListener.terminationClick(dataListBean.getTeam_uid());
                    }
                });
                paramItemViewHolder.tvTeamPromptly.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        Intent intent = new Intent(TeamMembersSingerListAdapter.this.context, TeamSigingDealWithActivity.class);
                        intent.putExtra("type", 1);
                        TeamMembersSingerListAdapter.this.context.startActivity(intent);
                    }
                });
                paramItemViewHolder.tvTeamLockAll.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        Intent intent = new Intent(TeamMembersSingerListAdapter.this.context, TeamSigingDealWithActivity.class);
                        intent.putExtra("type", 0);
                        TeamMembersSingerListAdapter.this.context.startActivity(intent);
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
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.team_members_singer_list, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
      @BindView(R.id.iv_team_header)
      ImageView ivTeamHeader;
      @BindView(R.id.tv_team_count)
      TextView tvTeamCount;
      @BindView(R.id.tv_team_host)
      TextView tvTeamHost;
      @BindView(R.id.rl_team_host)
      RelativeLayout rlTeamHost;
      @BindView(R.id.iv_team_singer_header)
      ImageView ivTeamSingerHeader;
      @BindView(R.id.tv_team_singer_count)
      TextView tvTeamSingerCount;
      @BindView(R.id.tv_team_signing)
      TextView tvTeamSigning;
      @BindView(R.id.tv_team_promptly)
      TextView tvTeamPromptly;
      @BindView(R.id.tv_team_lockAll)
      TextView tvTeamLockAll;
      @BindView(R.id.rl_team_singing)
      RelativeLayout rlTeamSinging;
      @BindView(R.id.sdv_team_header)
      SimpleDraweeView sdvTeamHeader;
      @BindView(R.id.tv_team_name)
      TextView tvTeamName;
      @BindView(R.id.tv_team_termination)
      TextView tvTeamTermination;
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
            addOnItemViewClickListener();
            addOnViewClickListener(param1View);
        }
    }

    public static interface OnItemClickListener {
        void itemOnClick(TeamMembersManagermentInfo.DataBean.DataListBean param1DataListBean);

        void terminationClick(String param1String);
    }
}
