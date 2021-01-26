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
import com.jk.jkproject.ui.entity.BlackList;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.utils.UserLevelSetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LiveRoomHostOperationAdapter extends BaseWrapperRecyclerAdapter<BlackList.DataBean, LiveRoomHostOperationAdapter.ItemViewHolder> {

    private Context context;

    private List<BlackList.DataBean> list = new ArrayList<BlackList.DataBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public LiveRoomHostOperationAdapter(Context paramContext, List<BlackList.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder viewHolder, int position) {
        list = getList();
        if (list.size() > 0) {
            final BlackList.DataBean dataBean = this.list.get(position);
            viewHolder.tvCancel.setVisibility(View.VISIBLE);
            viewHolder.sdvHeader.setImageURI(dataBean.getPicture());
            viewHolder.tvUserLevel.setText(dataBean.getUserGrade() + "");
            viewHolder.tvUserNameHost.setText(dataBean.getAnchorGrade() + "");
            viewHolder.tvUserName.setText(dataBean.getNickname());
            viewHolder.tvUserSign.setText(dataBean.getSgin());
            UserLevelSetUtils.setUserLevel(viewHolder.tvUserLevel, dataBean.getUserGrade());
            UserLevelSetUtils.setHostLevel(viewHolder.tvUserNameHost, dataBean.getAnchorGrade());
            if (Integer.parseInt(dataBean.getSex()) != 1) {
                viewHolder.ivUserSex.setImageResource(R.mipmap.live_icon_my_female);
            } else {
                viewHolder.ivUserSex.setImageResource(R.mipmap.live_icon_my_male);
            }
            viewHolder.tvCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (onItemClickListener != null)
                        onItemClickListener.onClick(dataBean.getUserId());
                }
            });
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_room_lock_user, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.iv_user_sex)
        ImageView ivUserSex;
        @BindView(R.id.tv_user_level)
        TextView tvUserLevel;
        @BindView(R.id.tv_user_name_host)
        TextView tvUserNameHost;
        @BindView(R.id.rl_1)
        RelativeLayout rl1;
        @BindView(R.id.tv_user_sign)
        TextView tvUserSign;
        @BindView(R.id.tv_cancel)
        TextView tvCancel;
        @BindView(R.id.ll_item)
        RelativeLayout llItem;

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            this.llItem = (RelativeLayout) param1View.findViewById(R.id.ll_item);
            this.tvUserName = (TextView) param1View.findViewById(R.id.tv_user_name);
            this.ivUserSex = (ImageView) param1View.findViewById(R.id.iv_user_sex);
            this.tvUserLevel = (TextView) param1View.findViewById(R.id.tv_user_level);
            this.tvUserNameHost = (TextView) param1View.findViewById(R.id.tv_user_name_host);
            this.tvUserSign = (TextView) param1View.findViewById(R.id.tv_user_sign);
            this.tvCancel = (TextView) param1View.findViewById(R.id.tv_cancel);
        }
    }

    public static interface OnItemClickListener {
        void onClick(String param1String);
    }
}