package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveRoomWheatBaseInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LiveRoomWheatListAdapter extends BaseWrapperRecyclerAdapter<LiveRoomWheatBaseInfo.DataBean, LiveRoomWheatListAdapter.ItemViewHolder> {


    private Context context;

    private List<LiveRoomWheatBaseInfo.DataBean> list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;
    private int mType;

    public LiveRoomWheatListAdapter(Context paramContext, List<LiveRoomWheatBaseInfo.DataBean> paramList, int type) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        this.list = paramList;
        this.mType = type;
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder holder, final int position) {
        final LiveRoomWheatBaseInfo.DataBean dataBean = getItem(position);
        if (dataBean != null) {
            holder.sdvPic.setImageURI(dataBean.getPicture());
            holder.tvName.setText(dataBean.getNickname());
            switch (mType) {
                case 1:
                    holder.tvUserLevel.setText(dataBean.getUserGrade());
                    UserLevelSetUtils.setUserLevel(holder.tvUserLevel, dataBean.getUserGrade());
                    break;
                case 2:
                    holder.tvUserLevel.setText(dataBean.getAnchorGrade());
                    UserLevelSetUtils.setHostLevel(holder.tvUserLevel, dataBean.getAnchorGrade());
                    holder.tvUserId.setVisibility(View.VISIBLE);
                    holder.tvUserId.setText("JK账号:" + dataBean.getUserId());
                    break;
            }
            holder.tvRefused.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    switch (mType) {
                        case 1:
                            onItemClickListener.onClick(position, 2, dataBean.getUserId());
                            remove(dataBean, true);
                            break;
                        case 2:
                            onItemClickListener.onItemClick(2, dataBean);
                            break;
                    }
                }
//                remove(position, true);
//                removeItem(position);
            });
            if (mType == 2) {
                holder.tvRefused.setBackgroundResource(R.drawable.bg_pk_round_btn);
                holder.tvRefused.setText("邀请");
            }
            holder.ll.setOnClickListener(v -> {
                if (OnClickUtils.isFastClick() && onItemClickListener != null) {
                    onItemClickListener.onClick(position, 1, dataBean.getUserId());
                    switch (mType) {
                        case 1:
                            remove(dataBean, true);
                            break;
                    }
                }
            });
            holder.tvLine.setVisibility(View.VISIBLE);
            if (getList().size() == 3 && position % 4 == 3 || getList().size() == 2 && position % 4 == 2 || getList().size() == 1) {
                holder.tvLine.setVisibility(View.GONE);
            }
        }
    }

    private void removeItem(int postiton) {
        list.remove(postiton);
        notifyDataSetChanged();
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_room_wheat_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_pic)
        SimpleDraweeView sdvPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_user_level)
        TextView tvUserLevel;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.tv_line)
        TextView tvLine;
        @BindView(R.id.tv_refused)
        TextView tvRefused;
        @BindView(R.id.tv_user_id)
        TextView tvUserId;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;


        public ItemViewHolder(View param1View) {
            super(param1View);
            this.tvName = param1View.findViewById(R.id.tv_name);
            this.tvUserLevel = param1View.findViewById(R.id.tv_user_level);
            this.ll = param1View.findViewById(R.id.ll);
            this.tvLine = param1View.findViewById(R.id.tv_line);
            this.sdvPic = param1View.findViewById(R.id.sdv_pic);
            this.tvRefused = param1View.findViewById(R.id.tv_refused);
            this.tvUserId = param1View.findViewById(R.id.tv_user_id);
            this.rlItem = param1View.findViewById(R.id.rl_item);
        }
    }

    public static interface OnItemClickListener {
        void onClick(int position, int Type, String userId);

        void onItemClick(int type, LiveRoomWheatBaseInfo.DataBean info);
    }
}