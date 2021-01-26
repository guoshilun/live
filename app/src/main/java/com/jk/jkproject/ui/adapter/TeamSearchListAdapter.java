package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.TeamDetailsActivity;
import com.jk.jkproject.ui.entity.TeamCenterInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeamSearchListAdapter extends BaseWrapperRecyclerAdapter<TeamCenterInfo.DataBean, TeamSearchListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {

    private Context context;

    private List<TeamCenterInfo.DataBean> list = new ArrayList<TeamCenterInfo.DataBean>();

    private LayoutInflater mLayoutInflater;

    public TeamSearchListAdapter(Context paramContext, List<TeamCenterInfo.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }

    public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, int paramInt) {
        this.list = getList();
        if (this.list.size() > 0) {
            final TeamCenterInfo.DataBean dataBean = this.list.get(paramInt);
            if (dataBean != null) {
                if (!TextUtils.isEmpty(dataBean.getTm_name())) {
                    paramItemViewHolder.tvTeamName.setText(dataBean.getTm_name());
                } else {
                    paramItemViewHolder.tvTeamName.setText("--");
                }
                if (dataBean.getTm_count() != 0) {
                    paramInt = dataBean.getTm_count();
                    TextView textView = paramItemViewHolder.tvMembers;
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("成员");
                    stringBuilder1.append(String.valueOf(paramInt));
                    textView.setText(stringBuilder1.toString());
                } else {
                    paramItemViewHolder.tvMembers.setText("0");
                }
                if (!TextUtils.isEmpty(dataBean.getCaptain_name())) {
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(dataBean.getCaptain_name());
                    stringBuilder1.append("");
                    String str = stringBuilder1.toString();
                    paramItemViewHolder.tvUserName.setText(str);
                } else {
                    paramItemViewHolder.tvUserName.setText("--");
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(dataBean.getId());
                stringBuilder.append("");
                if (!TextUtils.isEmpty(stringBuilder.toString())) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(dataBean.getId());
                    stringBuilder.append("");
                    String str = stringBuilder.toString();
                    TextView textView = paramItemViewHolder.tvId;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ID");
                    stringBuilder.append(str);
                    textView.setText(stringBuilder.toString());
                } else {
                    paramItemViewHolder.tvId.setText("--");
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(dataBean.getTm_msg());
                stringBuilder.append("");
                if (!TextUtils.isEmpty(stringBuilder.toString())) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(dataBean.getTm_msg());
                    stringBuilder.append("");
                    String str = stringBuilder.toString();
                    paramItemViewHolder.tvInstructions.setText(str);
                } else {
                    paramItemViewHolder.tvInstructions.setText("--");
                }
                paramItemViewHolder.sdvLeftHeader.setImageURI(dataBean.getTm_url());
                paramItemViewHolder.llTeamList.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        if (FuncUtils.isFastDoubleClick())
                            return;
                        Intent intent = new Intent(TeamSearchListAdapter.this.context, TeamDetailsActivity.class);
                        intent.putExtra("data", (Serializable) dataBean);
                        TeamSearchListAdapter.this.context.startActivity(intent);
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
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.team_search_list, paramViewGroup, false));
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_left_header)
        SimpleDraweeView sdvLeftHeader;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_join)
        TextView tvJoin;
        @BindView(R.id.tv_instructions)
        TextView tvInstructions;
        @BindView(R.id.tv_team_name)
        TextView tvTeamName;
        @BindView(R.id.tv_members)
        TextView tvMembers;
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.ll_team_list)
        LinearLayout llTeamList;

        public ItemViewHolder(View param1View) {
            super(param1View);
            addOnItemViewClickListener();
            addOnViewClickListener(param1View);
        }
    }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\TeamSearchListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */