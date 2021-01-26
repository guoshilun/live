package com.jk.jkproject.ui.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.PKBattleBaseInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;

import java.util.List;

import butterknife.BindView;

public class LiveRoomPKHeaderAdapter extends BaseWrapperRecyclerAdapter<PKBattleBaseInfo.aMessage, LiveRoomPKHeaderAdapter.ItemViewHolder> {


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ObjectAnimator objectAnimatorHeader;

    private OnItemClickListener onItemClickListener;
    private int type; //1.左边 2.右边

    public LiveRoomPKHeaderAdapter(Context context, List<PKBattleBaseInfo.aMessage> paramList, int type) {
        this.type = type;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        appendToList(paramList);
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder viewHolder, final int position) {
        final PKBattleBaseInfo.aMessage dataBean = getItem(position);
        if (dataBean != null) {
            viewHolder.sdvHeader.setImageURI(dataBean.getPicture());
            switch (type) {
                case 1:
                    switch (position) {
                        case 2:
                            viewHolder.ivRankCount.setImageResource(R.mipmap.icon_pk_seek_left_top_1);
//                            setHeaderAnim(1, viewHolder.rlHeader, viewHolder.ivRankCount);
                            break;
                        case 1:
                            viewHolder.ivRankCount.setImageResource(R.mipmap.icon_pk_seek_left_top_2);
//                            setHeaderAnim(2, viewHolder.rlHeader, viewHolder.ivRankCount);
                            break;
                        case 0:
                            viewHolder.ivRankCount.setImageResource(R.mipmap.icon_pk_seek_left_top_3);
//                            setHeaderAnim(3, viewHolder.rlHeader, viewHolder.ivRankCount);
                            break;
                    }
                    switch (dataBean.getIsMVP()) {
                        case 1:
                            viewHolder.sdvHeader.setBackgroundResource(R.mipmap.bg_found_hollow_circle);
                            viewHolder.ivRankMvp.setVisibility(View.VISIBLE);
                            viewHolder.ivRankCrown.setVisibility(View.VISIBLE);
                            break;
                        case 0:
                            viewHolder.sdvHeader.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                            viewHolder.ivRankMvp.setVisibility(View.GONE);
                            viewHolder.ivRankCrown.setVisibility(View.GONE);
                            break;
                    }
                    break;
                case 2:
                    switch (position) {
                        case 0:
                            viewHolder.ivRankCount.setImageResource(R.mipmap.icon_pk_seek_right_top_1);
//                            setHeaderAnim(1, viewHolder.rlHeader, viewHolder.ivRankCount);
                            break;
                        case 1:
                            viewHolder.ivRankCount.setImageResource(R.mipmap.icon_pk_seek_right_top_2);
//                            setHeaderAnim(2, viewHolder.rlHeader, viewHolder.ivRankCount);
                            break;
                        case 2:
                            viewHolder.ivRankCount.setImageResource(R.mipmap.icon_pk_seek_right_top_3);
//                            setHeaderAnim(3, viewHolder.rlHeader, viewHolder.ivRankCount);
                            break;
                    }
                    switch (dataBean.getIsMVP()) {
                        case 1:
                            viewHolder.sdvHeader.setBackgroundResource(R.mipmap.bg_found_hollow_circle);
                            viewHolder.ivRankMvp.setVisibility(View.VISIBLE);
                            viewHolder.ivRankCrown.setVisibility(View.VISIBLE);
                            break;
                        case 0:
                            viewHolder.sdvHeader.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                            viewHolder.ivRankMvp.setVisibility(View.GONE);
                            viewHolder.ivRankCrown.setVisibility(View.GONE);
                            break;
                    }
                    break;
            }

            viewHolder.rlWinHeader.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(1, dataBean);
                }
            });
            if (onItemClickListener != null && dataBean.getPicture() == null && position == 2 && type == 1) {
                onItemClickListener.onClick(position, viewHolder.rlHeader, viewHolder.ivRankCount);
            }

            if (onItemClickListener != null && dataBean.getPicture() == null && position == 0 && type == 2) {
                onItemClickListener.onClick(position, viewHolder.rlHeader, viewHolder.ivRankCount);
            }
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.layout_pk_seek_header_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.iv_rank_crown)
        ImageView ivRankCrown;
        @BindView(R.id.iv_rank_mvp)
        ImageView ivRankMvp;
        @BindView(R.id.iv_rank_count)
        ImageView ivRankCount;
        @BindView(R.id.rl_win_header)
        RelativeLayout rlWinHeader;
        @BindView(R.id.rl_header)
        RelativeLayout rlHeader;

        public ItemViewHolder(View param1View) {
            super(param1View);
            sdvHeader = param1View.findViewById(R.id.sdv_header);
            ivRankCrown = param1View.findViewById(R.id.iv_rank_crown);
            ivRankMvp = param1View.findViewById(R.id.iv_rank_mvp);
            ivRankCount = param1View.findViewById(R.id.iv_rank_count);
            rlWinHeader = param1View.findViewById(R.id.rl_win_header);
            rlHeader = param1View.findViewById(R.id.rl_header);
        }
    }

    public static interface OnItemClickListener {
        void onClick(int type, PKBattleBaseInfo.aMessage info);

        void onClick(int position, RelativeLayout rlHeader, ImageView ivRankCount);
    }
}