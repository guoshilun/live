package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.utils.FastClickUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FocusOrFollowAdapter extends BaseWrapperRecyclerAdapter<LiveHomeBeanInfo.DataBean, FocusOrFollowAdapter.ItemViewHolder> {


    private Context context;

    private List<LiveHomeBeanInfo.DataBean> list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    private int mType;

    private OnItemClickListener onItemClickListener;

    public FocusOrFollowAdapter(Context paramContext, List<LiveHomeBeanInfo.DataBean> paramList, int type) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        this.mType = type;
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, int paramInt) {
        list = getList();
        if (list.size() > 0) {
            final LiveHomeBeanInfo.DataBean dataBean = list.get(paramInt);
            /*
            private String nickname;
        private int type;
        private String sgin;
        private String userId;
        private int state;//0.未关注 1.关注
             */
            dataBean.setU_id(dataBean.getUserId());
            paramItemViewHolder.sdvHeader.setImageURI(dataBean.getPicture());
            paramItemViewHolder.tvUserName.setText(dataBean.getNickname());
            paramItemViewHolder.tvUserHot.setText(dataBean.getSgin());
            switch (dataBean.getSex()) {
                case 1:
                    paramItemViewHolder.ivUserSex.setImageResource(R.mipmap.live_icon_my_male);
                    break;
                default:
                    paramItemViewHolder.ivUserSex.setImageResource(R.mipmap.live_icon_my_female);
                    break;
            }
            paramItemViewHolder.tvUserLevel.setText(dataBean.getUserGrade() + "");
            paramItemViewHolder.tvUserNameHost.setText(dataBean.getAnchorGrade() + "");
            UserLevelSetUtils.setUserLevel(paramItemViewHolder.tvUserLevel, dataBean.getUserGrade() + "");
            UserLevelSetUtils.setHostLevel(paramItemViewHolder.tvUserNameHost, dataBean.getAnchorGrade() + "");
            switch (dataBean.getR_state()) {
                case 1:
                    paramItemViewHolder.tvHostType.setText("直播中");
                    paramItemViewHolder.tvHostType.setBackgroundResource(R.drawable.bg_live_room_red);
                    break;
                default:
                    switch (dataBean.getState()) {
                        case 1:
                            paramItemViewHolder.tvHostType.setText("已关注");
                            paramItemViewHolder.tvHostType.setBackgroundResource(R.drawable.bg_cencal_btn);
                            break;
                        default:
                            paramItemViewHolder.tvHostType.setText("关注");
                            paramItemViewHolder.tvHostType.setBackgroundResource(R.drawable.bg_follow_red);
                            break;
                    }
                    break;
            }

            paramItemViewHolder.rl2.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(1, dataBean);
                }
            });


            paramItemViewHolder.tvHostType.setOnClickListener(param1View -> {
                switch (dataBean.getR_state()) {
                    case 1:
                        if (FastClickUtils.isFastClick()&& NetworkUtils.isConnected()) {
                            Intent intent = new Intent(context, SlideActivity.class);
                            intent.putExtra("roomId", dataBean.getRoomId());
                            context.startActivity(intent);
                        }
                        break;
                    default:
                        if (onItemClickListener != null) {
                            onItemClickListener.onClick(paramInt, 0, dataBean);
                        }
                        break;
                }
            });
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.home_focus_list_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_hot)
        TextView tvUserHot;
        @BindView(R.id.iv_user_sex)
        ImageView ivUserSex;
        @BindView(R.id.tv_user_level)
        TextView tvUserLevel;
        @BindView(R.id.tv_user_name_host)
        TextView tvUserNameHost;
        @BindView(R.id.rl_1)
        RelativeLayout rl1;
        @BindView(R.id.tv_host_type)
        TextView tvHostType;
        @BindView(R.id.ll_item)
        RelativeLayout llItem;
        @BindView(R.id.rl_2)
        RelativeLayout rl2;

        public ItemViewHolder(View param1View) {
            super(param1View);
            sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            tvUserName = (TextView) param1View.findViewById(R.id.tv_user_name);
            tvUserHot = (TextView) param1View.findViewById(R.id.tv_user_hot);
            ivUserSex = (ImageView) param1View.findViewById(R.id.iv_user_sex);
            tvUserLevel = (TextView) param1View.findViewById(R.id.tv_user_level);
            tvUserNameHost = (TextView) param1View.findViewById(R.id.tv_user_name_host);
            tvHostType = (TextView) param1View.findViewById(R.id.tv_host_type);
            llItem = (RelativeLayout) param1View.findViewById(R.id.ll_item);
            rl2 = (RelativeLayout) param1View.findViewById(R.id.rl_2);
        }
    }

    public static interface OnItemClickListener {
        void onClick(int state, String uid);

        void onClick(int state, LiveHomeBeanInfo.DataBean dataBean);

        void onClick(int position, int state, LiveHomeBeanInfo.DataBean dataBean);

        void onItemClick(int position);
    }
}