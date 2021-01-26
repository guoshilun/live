package com.jk.jkproject.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChatMessageListAdapter extends BaseWrapperRecyclerAdapter<Message, ChatMessageListAdapter.ItemViewHolder> {

    private Context context;

    private List<Message> list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    private OnLongItemClickListener onLongItemClickListener;

    public OnLongItemClickListener getOnLongItemClickListener() {
        return this.onLongItemClickListener;
    }

    public void setOnLongItemClickListener(OnLongItemClickListener paramOnItemClickListener) {
        this.onLongItemClickListener = paramOnItemClickListener;
    }


    public ChatMessageListAdapter(Context paramContext, List<Message> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }


    public void onBindItemViewHolder(ItemViewHolder viewHolder, int paramInt) {
        list = getList();
        if (list.size() > 0) {
            final Message bean = list.get(paramInt);
            viewHolder.tvUserName.setText(bean.getUserName());
            viewHolder.sdvHeader.setImageURI(bean.getPicture());
            viewHolder.tvUserContent.setText(bean.getContent());
            if (bean.getSentTime() > System.currentTimeMillis()) {
                viewHolder.tvSendTime.setText("刚刚");
            } else {
                viewHolder.tvSendTime.setText(TimeUtils.getFriendlyTimeSpanByNow(bean.getSentTime()));
            }
            viewHolder.rlItem.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(bean);
                }
            });
            viewHolder.rlItem.setOnLongClickListener(v -> {
                if (onLongItemClickListener != null) {
                    onLongItemClickListener.onClick(bean);
                }
                return true;
            });
            if (bean.getCount() <= 0) {
                viewHolder.ivPoint.setVisibility(View.GONE);
            } else {
                viewHolder.ivPoint.setVisibility(View.VISIBLE);
            }
        }
    }


    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.chat_message_item, paramViewGroup, false));
    }


    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_content)
        TextView tvUserContent;
        @BindView(R.id.tv_send_time)
        TextView tvSendTime;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        @BindView(R.id.iv_point)
        ImageView ivPoint;

        public ItemViewHolder(View param1View) {
            super(param1View);
            sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            tvUserName = (TextView) param1View.findViewById(R.id.tv_user_name);
            tvUserContent = (TextView) param1View.findViewById(R.id.tv_user_content);
            tvSendTime = (TextView) param1View.findViewById(R.id.tv_send_time);
            rlItem = (RelativeLayout) param1View.findViewById(R.id.rl_item);
            ivPoint = param1View.findViewById(R.id.iv_point);
        }
    }

    public static interface OnItemClickListener {
        void onClick(Message bean);
    }

    public static interface OnLongItemClickListener {
        void onClick(Message bean);
    }
}