package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.PKRecordBaseInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import java.util.List;

public class LiveRoomPKItemAdapter extends BaseWrapperRecyclerAdapter<PKRecordBaseInfo.DataBean, LiveRoomPKItemAdapter.ItemViewHolder> {


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<PKRecordBaseInfo.DataBean> paramList;

    private OnItemClickListener onItemClickListener;

    public LiveRoomPKItemAdapter(Context context, List<PKRecordBaseInfo.DataBean> paramList) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.paramList = paramList;
        appendToList(paramList);
    }

    @Override
    public int getItemCount() {
        return getList().size() + getHeaderSize();
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder viewHolder, final int position) {
        paramList = getList();
        if (paramList.size() > 0) {
            final PKRecordBaseInfo.DataBean dataBean = paramList.get(position - getHeaderSize());
            if (dataBean != null) {
                if (dataBean.getVictory_userId().equals(SPUtils.getUserId())) {
                    viewHolder.tvWin.setText("胜");
                    viewHolder.tvWin.setTextColor(mContext.getResources().getColor(R.color.color_FFA723));
                } else {
                    viewHolder.tvWin.setText("败");
                    viewHolder.tvWin.setTextColor(mContext.getResources().getColor(R.color.color_999));
                }
                switch (dataBean.getR_state()) {
                    case 0:
                        viewHolder.tvAdd.setBackgroundResource(R.drawable.bg_pk_unbtn);
                        viewHolder.tvAdd.setEnabled(false);
                        break;
                    case 1:
                        viewHolder.tvAdd.setBackgroundResource(R.drawable.bg_pk_btn);
                        viewHolder.tvAdd.setEnabled(true);
                        break;
                }

                viewHolder.sdvHeader.setImageURI(dataBean.getPicture());
                viewHolder.tvName.setText(dataBean.getNickname());
                viewHolder.tvUserLevel.setText(dataBean.getAnchorGrade());
                UserLevelSetUtils.setHostLevel(viewHolder.tvUserLevel, dataBean.getAnchorGrade());

                viewHolder.rlItem.setOnClickListener(v -> {
                    if (OnClickUtils.isFastClick() && onItemClickListener != null) {
                        onItemClickListener.onClick(1, dataBean);
                    }
                });

                viewHolder.tvAdd.setOnClickListener(v -> {
                    if (OnClickUtils.isFastClick() && onItemClickListener != null) {
                        onItemClickListener.onClick(2, dataBean);
                    }
                });
            }
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.live_room_pk_record_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        TextView tvWin;
        SimpleDraweeView sdvHeader;
        TextView tvName;
        TextView tvUserLevel;
        TextView tvAdd;
        RelativeLayout rlItem;

        public ItemViewHolder(View param1View) {
            super(param1View);
            tvWin = (TextView) param1View.findViewById(R.id.tv_win);
            tvName = (TextView) param1View.findViewById(R.id.tv_name);
            tvUserLevel = (TextView) param1View.findViewById(R.id.tv_user_level);
            tvAdd = (TextView) param1View.findViewById(R.id.tv_add);
            sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            rlItem = param1View.findViewById(R.id.rl_item);
        }
    }

    public static interface OnItemClickListener {
        void onClick(int type, PKRecordBaseInfo.DataBean info);
    }
}