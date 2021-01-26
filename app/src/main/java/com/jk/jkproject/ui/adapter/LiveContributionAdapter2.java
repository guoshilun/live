package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.MyHomePageActivity;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.entity.FanContributionList;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FastClickUtils;
import com.jk.jkproject.utils.FuncUtils;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Zick
 * @params
 * @date 2020/7/31 2:00 PM
 * @desc 榜单适配器
 */
public class LiveContributionAdapter2 extends BaseWrapperRecyclerAdapter<FanContributionList.DataBean, LiveContributionAdapter2.ItemViewHolder> implements OnRecyclerItemClickListener {


    private Context context;

    private List<FanContributionList.DataBean> list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;
    private int position; //1.用户榜 2.主播榜

    private OnItemClickListener onItemClickListener;

    public LiveContributionAdapter2(Context paramContext, List<FanContributionList.DataBean> paramList, int position) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        this.position = position;
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, int position) {
        list = getList();
        if (list.size() > 0) {
            FanContributionList.DataBean dataBean = list.get(position);
            paramItemViewHolder.tvUserName.setText(dataBean.getNickname());
            paramItemViewHolder.sdvHeader.setImageURI(dataBean.getPicture());
            if (dataBean.getUserId().equals(SPUtils.getUserId())) {
                paramItemViewHolder.tvHostType.setVisibility(View.GONE);
            }
            paramItemViewHolder.tvHotCount.setText(dataBean.getSum_hot() + "热度");
            paramItemViewHolder.tvUserLevel.setText(dataBean.getAnchorGrade());
            UserLevelSetUtils.setHostLevel(paramItemViewHolder.tvUserLevel, dataBean.getAnchorGrade());
            Drawable drawable = context.getResources().getDrawable(R.mipmap.live_icon_host_level);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            paramItemViewHolder.tvUserLevel.setCompoundDrawables(drawable, null, null, null);
            switch (dataBean.getR_state()) {
                case 1:
//                tvHostType1.setText("直播中");
                    paramItemViewHolder.tvHostType.setBackgroundResource(R.mipmap.icon_contribution_live);
                    paramItemViewHolder.tvHostType.setEnabled(true);
                    break;
                default:
                    switch (dataBean.getState()) {//   0. 未关注   1.关注
                        case 1:
                            paramItemViewHolder.tvHostType.setText("已关注");
                            paramItemViewHolder.tvHostType.setTextColor(context.getResources().getColor(R.color.color_969696));
                            paramItemViewHolder.tvHostType.setBackgroundResource(R.drawable.bg_white_round_btn);
                            paramItemViewHolder.tvHostType.setTextSize(10);
                            paramItemViewHolder.tvHostType.setEnabled(false);
                            break;
                        case 0:
                            paramItemViewHolder.tvHostType.setText("");
                            paramItemViewHolder.tvHostType.setBackgroundResource(R.mipmap.icon_contribution_follow_list);
                            paramItemViewHolder.tvHostType.setEnabled(true);
                            break;
                    }
                    break;
            }
            paramItemViewHolder.tvHostType.setOnClickListener(v -> {
                Intent intent = null;
                if (FastClickUtils.isFastClick()) {
                    switch (dataBean.getR_state()) {
                        case 1:
                            intent = new Intent(context, SlideActivity.class);
                            intent.putExtra("roomId", dataBean.getRoomId());
                            context.startActivity(intent);
                            break;
                        default:
                            if (onItemClickListener != null) {
                                onItemClickListener.onClick(0, dataBean);
//                                switch (dataBean.getState()) {//   0. 未关注   1.关注
//                                    case 0:
//                                        paramItemViewHolder.tvHostType.setText("已关注");
//                                        paramItemViewHolder.tvHostType.setTextColor(context.getResources().getColor(R.color.color_969696));
//                                        paramItemViewHolder.tvHostType.setTextSize(10);
//                                        paramItemViewHolder.tvHostType.setEnabled(false);
//                                        break;
//                                    case 1:
//                                        paramItemViewHolder.tvHostType.setBackgroundResource(R.mipmap.icon_contribution_follow_list);
//                                        paramItemViewHolder.tvHostType.setEnabled(true);
//                                        break;
//                                }
                            }
                            break;
                    }
                }
            });
            if (dataBean.getId() >= 99) {
                paramItemViewHolder.tvRankNumber.setText("99+");
            } else if (dataBean.getId() >= 9) {
                paramItemViewHolder.tvRankNumber.setText(dataBean.getId() + "");
            } else if (dataBean.getId() > 0) {
                paramItemViewHolder.tvRankNumber.setText("0" + dataBean.getId());
            }
            paramItemViewHolder.rl2.setOnClickListener(v -> {
                if (OnClickUtils.isFastClick()) {
                    Intent intent = new Intent(context, MyHomePageActivity.class);
                    intent.putExtra("u_id", dataBean.getUserId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void onClick(View paramView, int paramInt) {
        if (FuncUtils.isFastDoubleClick())
            return;
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_contribution_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.tv_rank_number)
        TextView tvRankNumber;
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_hot_count)
        TextView tvHotCount;
        @BindView(R.id.tv_user_level)
        TextView tvUserLevel;
        @BindView(R.id.tv_host_type)
        TextView tvHostType;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        @BindView(R.id.rl_2)
        RelativeLayout rl2;

        public ItemViewHolder(View param1View) {

            super(param1View);
            this.sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            this.tvRankNumber = (TextView) param1View.findViewById(R.id.tv_rank_number);
            this.tvUserName = (TextView) param1View.findViewById(R.id.tv_user_name);
            this.tvHotCount = (TextView) param1View.findViewById(R.id.tv_hot_count);
            this.tvUserLevel = (TextView) param1View.findViewById(R.id.tv_user_level);
            this.tvHostType = (TextView) param1View.findViewById(R.id.tv_host_type);
            this.rlItem = (RelativeLayout) param1View.findViewById(R.id.rl_item);
            this.rl2 = (RelativeLayout) param1View.findViewById(R.id.rl_2);
        }
    }

    public static interface OnItemClickListener {
        void onClick(int type, FanContributionList.DataBean dataBean);
    }
}
