package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.TeamStatisticalInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeamStatisicalListAdapter extends BaseWrapperRecyclerAdapter<TeamStatisticalInfo.DataBean.DataListBean, TeamStatisicalListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {

    private Context context;

    private List<TeamStatisticalInfo.DataBean.DataListBean> list = new ArrayList<TeamStatisticalInfo.DataBean.DataListBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public TeamStatisicalListAdapter(Context paramContext, List<TeamStatisticalInfo.DataBean.DataListBean> paramList) {
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
            paramItemViewHolder.tvTeamMembersOperation.setVisibility(View.GONE);
            paramItemViewHolder.tvTeamOperation.setVisibility(View.GONE);
            paramItemViewHolder.tvTeamMembersIdentity.setText("个人收入");
            TeamStatisticalInfo.DataBean.DataListBean dataListBean = this.list.get(paramInt);
            if (paramInt == 0) {
                paramItemViewHolder.ll.setVisibility(View.VISIBLE);
            } else {
                paramItemViewHolder.ll.setVisibility(View.GONE);
            }
            if (dataListBean != null) {
                if (!TextUtils.isEmpty(dataListBean.getU_nickName())) {
                    paramItemViewHolder.tvTeamNikeName.setText(dataListBean.getU_nickName());
                } else {
                    paramItemViewHolder.tvTeamNikeName.setText("--");
                }
                TextView textView = paramItemViewHolder.tvTeamIdentity;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(dataListBean.getPersonal_income());
                stringBuilder.append("");
                textView.setText(stringBuilder.toString());
                paramItemViewHolder.tvTeamIdentity.setTextSize(16.0F);
                paramItemViewHolder.tvTeamIdentity.setMaxLines(10);
                paramItemViewHolder.sdvLeftHeader.setImageURI(dataListBean.getU_picture());
            }
        }
    }

    public void onClick(View paramView, int paramInt) {
        if (FuncUtils.isFastDoubleClick())
            return;
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.team_members_list, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.tv_team_members_header)
        TextView tvTeamMembersHeader;
        @BindView(R.id.tv_team_members_nikeName)
        TextView tvTeamMembersNikeName;
        @BindView(R.id.tv_team_members_identity)
        TextView tvTeamMembersIdentity;
        @BindView(R.id.tv_team_members_operation)
        TextView tvTeamMembersOperation;
        @BindView(R.id.sdv_left_header)
        SimpleDraweeView sdvLeftHeader;
        @BindView(R.id.tv_team_nikeName)
        TextView tvTeamNikeName;
        @BindView(R.id.tv_team_identity)
        TextView tvTeamIdentity;
        @BindView(R.id.tv_team_operation)
        ImageView tvTeamOperation;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.iv_operation)
        ImageView ivOperation;

        public ItemViewHolder(View param1View) {
            super(param1View);
//            this.ivOperation = (ImageView) param1View.findViewById(2131296508);
            addOnItemViewClickListener();
            addOnViewClickListener(param1View);
        }
    }

    public static interface OnItemClickListener {
        void terminationClick(View param1View, String param1String);
    }
}