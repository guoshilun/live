package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.TeamMembersInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;
import com.jk.jkproject.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class TeamMembersListAdapter extends BaseWrapperRecyclerAdapter<TeamMembersInfo.DataBean, TeamMembersListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {
  private Context context;
  
  private List<TeamMembersInfo.DataBean> list = new ArrayList<TeamMembersInfo.DataBean>();
  
  private LayoutInflater mLayoutInflater;
  
  private OnItemClickListener onItemClickListener;
  
  private int type;
  
  public TeamMembersListAdapter(Context paramContext, List<TeamMembersInfo.DataBean> paramList, int paramInt) {
    this.context = paramContext;
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    this.type = paramInt;
    appendToList(paramList);
  }
  
  public OnItemClickListener getOnItemClickListener() {
    return this.onItemClickListener;
  }
  
  public void onBindItemViewHolder(final ItemViewHolder holder, int paramInt) {
    this.list = getList();
    if (this.list.size() > 0) {
      final TeamMembersInfo.DataBean data = this.list.get(paramInt);
      if (paramInt == 0) {
        holder.llTeamMembers.setVisibility(View.VISIBLE);
      } else {
        holder.llTeamMembers.setVisibility(View.GONE);
      } 
      if (data != null) {
        if (!TextUtils.isEmpty(data.getU_nickName())) {
          holder.tvTeamNikeName.setText(data.getU_nickName());
        } else {
          holder.tvTeamNikeName.setText("------");
        } 
        switch (data.getTeam_grade()) {
          default:
            holder.tvTeamIdentity.setText("成员");
            break;
          case 2:
            holder.tvTeamIdentity.setText("队长");
            break;
          case 1:
            holder.tvTeamIdentity.setText("管理员");
            break;
        } 
        holder.sdvLeftHeader.setImageURI(data.getU_picture());
        if (SPUtils.getTeamType() != 6) {
          holder.tvTeamOperation.setVisibility(View.VISIBLE);
          holder.tvTeamMembersOperation.setVisibility(View.VISIBLE);
        } else {
          holder.tvTeamOperation.setVisibility(View.GONE);
          holder.tvTeamMembersOperation.setVisibility(View.GONE);
        } 
        holder.tvTeamOperation.setOnClickListener(new View.OnClickListener() {
              public void onClick(View param1View) {
                if (FuncUtils.isFastDoubleClick())
                  return; 
                if (onItemClickListener != null)
                  onItemClickListener.terminationClick((View)holder.ivOperation, data.getTeam_uid());
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
    return new ItemViewHolder(this.mLayoutInflater.inflate(2131427528, paramViewGroup, false));
  }
  
  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
    this.onItemClickListener = paramOnItemClickListener;
  }
  
  public class ItemViewHolder extends ClickableViewHolder {
    ImageView ivOperation;
    
    View llTeamMembers;
    
    SimpleDraweeView sdvLeftHeader;
    
    TextView tvTeamIdentity;
    
    TextView tvTeamMembersHeader;
    
    TextView tvTeamMembersIdentity;
    
    TextView tvTeamMembersNikeName;
    
    TextView tvTeamMembersOperation;
    
    TextView tvTeamNikeName;
    
    ImageView tvTeamOperation;
    
    public ItemViewHolder(View param1View) {
      super(param1View);
      this.sdvLeftHeader = (SimpleDraweeView)param1View.findViewById(R.id.sdv_left_header);
      this.tvTeamMembersHeader = (TextView)param1View.findViewById(R.id.tv_team_members_header);
      this.tvTeamMembersNikeName = (TextView)param1View.findViewById(R.id.tv_team_members_nikeName);
      this.tvTeamMembersIdentity = (TextView)param1View.findViewById(R.id.tv_team_members_identity);
      this.tvTeamMembersOperation = (TextView)param1View.findViewById(R.id.tv_team_members_operation);
      this.tvTeamNikeName = (TextView)param1View.findViewById(R.id.tv_team_nikeName);
      this.tvTeamIdentity = (TextView)param1View.findViewById(R.id.tv_team_identity);
      this.tvTeamOperation = (ImageView)param1View.findViewById(R.id.tv_team_operation);
      this.llTeamMembers = param1View.findViewById(R.id.team);
      this.ivOperation = (ImageView)param1View.findViewById(R.id.iv_operation);
      addOnItemViewClickListener();
      addOnViewClickListener(param1View);
    }
  }
  
  public static interface OnItemClickListener {
    void terminationClick(View param1View, String param1String);
  }
}