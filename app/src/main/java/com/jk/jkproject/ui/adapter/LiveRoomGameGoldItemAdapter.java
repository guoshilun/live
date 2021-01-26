package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.net.im.info.GameLogProto;
import com.jk.jkproject.ui.entity.PKRecordBaseInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;

import java.util.List;

import butterknife.BindView;

public class LiveRoomGameGoldItemAdapter extends BaseWrapperRecyclerAdapter<GameLogProto.GameLogResult, LiveRoomGameGoldItemAdapter.ItemViewHolder> {


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<GameLogProto.GameLogResult> paramList;

    private OnItemClickListener onItemClickListener;

    public LiveRoomGameGoldItemAdapter(Context context, List<GameLogProto.GameLogResult> paramList) {
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
            GameLogProto.GameLogResult dataBean = getItem(position);
            if (dataBean.getNum() == 1) {
                viewHolder.tvNewName.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvNewName.setVisibility(View.INVISIBLE);
            }

            switch (dataBean.getType()) { //1正 2 反
                case 1:
                    viewHolder.ivLeft.setImageResource(R.mipmap.icon_game_gold_img);
                    viewHolder.ivRight.setImageResource(R.mipmap.live_icon_room_close);
                    viewHolder.llRight.setVisibility(View.GONE);
                    if (dataBean.getMoney() > 0) {
                        viewHolder.llLeft.setVisibility(View.VISIBLE);
                        viewHolder.tvLeftGold.setText(dataBean.getMoney() + "");
                    } else {
                        viewHolder.llLeft.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    viewHolder.ivRight.setImageResource(R.mipmap.icon_game_gold_img);
                    viewHolder.ivLeft.setImageResource(R.mipmap.live_icon_room_close);
                    viewHolder.llLeft.setVisibility(View.GONE);
                    if (dataBean.getMoney() > 0) {
                        viewHolder.llRight.setVisibility(View.VISIBLE);
                        viewHolder.tvRightGold.setText(dataBean.getMoney() + "");
                    } else {
                        viewHolder.llRight.setVisibility(View.GONE);
                    }
                    break;
            }
            if (dataBean.getNum() % 2 == 0) {
                viewHolder.rl.setBackgroundColor(mContext.getResources().getColor(R.color.color_F0F0F0));
            } else {
                viewHolder.rl.setBackgroundColor(mContext.getResources().getColor(R.color.white));

            }
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.live_room_game_gold_record_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        TextView tvNewName;
        ImageView ivLeft;
        @BindView(R.id.iv_right)
        ImageView ivRight;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.tv_left_gold)
        TextView tvLeftGold;
        @BindView(R.id.ll_left)
        LinearLayout llLeft;
        @BindView(R.id.tv_right_gold)
        TextView tvRightGold;
        @BindView(R.id.ll_right)
        LinearLayout llRight;

        public ItemViewHolder(View param1View) {
            super(param1View);
            tvNewName = param1View.findViewById(R.id.tv_new_name);
            ivLeft = param1View.findViewById(R.id.iv_left);
            ivRight = param1View.findViewById(R.id.iv_right);
            tvLeftGold = param1View.findViewById(R.id.tv_left_gold);
            tvRightGold = param1View.findViewById(R.id.tv_right_gold);
            llLeft = param1View.findViewById(R.id.ll_left);
            llRight = param1View.findViewById(R.id.ll_right);
            rl = param1View.findViewById(R.id.rl);
        }
    }

    public static interface OnItemClickListener {
        void onClick(int type, PKRecordBaseInfo.DataBean info);
    }
}