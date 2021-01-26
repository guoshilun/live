package com.jk.jkproject.ui.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveGiftInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveGiftRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final int EMPTY_VIEW = 5;


    private String TyepName;

    private Context context;

    private String keyword;

    private List<LiveGiftInfoBean.DataBean> mGiftDatas;

    private final LayoutInflater mLayoutInflater;

    private OnItemClickListener mOnItemClickListener;

    private String mPosition = "";

    private int select_position = -1;

    public LiveGiftRecyclerViewAdapter(Context paramContext, List<LiveGiftInfoBean.DataBean> paramList, String paramString, OnItemClickListener paramOnItemClickListener) {
        this.context              = paramContext;
        this.mGiftDatas           = paramList;
        this.mPosition            = paramString;
        this.mLayoutInflater      = LayoutInflater.from(paramContext);
        this.mOnItemClickListener = paramOnItemClickListener;
    }

    public int getItemCount() {
        return mGiftDatas.size() > 0 ? mGiftDatas.size() : 0;
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, final int position) {
        ViewHolder viewHolder = (ViewHolder) paramViewHolder;
        final LiveGiftInfoBean.DataBean dataBean = mGiftDatas.get(position);
        viewHolder.liveGiftItemName.setText(dataBean.getG_name() + "");
        viewHolder.liveGiftItemAmondGold.setText(dataBean.getG_amount() + "");
        viewHolder.liveGiftItemIv.setImageURI(dataBean.getG_icon());
        if (dataBean.getExpire_time() != 0) {
            long countdown = dataBean.getExpire_time();
            viewHolder.expire.setText(formatDateTime(countdown));
            viewHolder.expire.setVisibility(View.VISIBLE);
        } else {
            viewHolder.expire.setVisibility(View.GONE);
        }
        if (dataBean.getP_count() != 0) {
            viewHolder.count.setText("X" + dataBean.getP_count());
            viewHolder.count.setVisibility(View.VISIBLE);
        } else {
            viewHolder.count.setVisibility(View.GONE);
        }
        //        Glide.with(this.context).load(dataBean.getG_icon()).skipMemoryCache(false).dontAnimate().into((ImageView) viewHolder.liveGiftItemIv);
        //        GlideUtils.loadChatImage(context, dataBean.getG_icon(),  viewHolder.liveGiftItemIv);
        switch (dataBean.getG_type()) {
            default:
                viewHolder.ivLiveGiftType.setVisibility(View.GONE);
                break;
            case 2:
                viewHolder.ivLiveGiftType.setVisibility(View.VISIBLE);
                viewHolder.ivLiveGiftType.setImageResource(R.mipmap.live_icon_gift_type_2);
                break;
            case 1:
                viewHolder.ivLiveGiftType.setVisibility(View.VISIBLE);
                viewHolder.ivLiveGiftType.setImageResource(R.mipmap.live_icon_gift_type_1);
                break;
        }
        if (dataBean.select_item) {
            viewHolder.liveGiftItemLl.setBackgroundResource(R.drawable.bg_red_gift_boder);
            viewHolder.liveGiftItemName.setTextColor(context.getResources().getColor(R.color.color_FC5E8E));
        } else {
            viewHolder.liveGiftItemLl.setBackgroundResource(R.drawable.bg_black_gift_boder);
            viewHolder.liveGiftItemName.setTextColor(context.getResources().getColor(R.color.white));
        }
        viewHolder.liveGiftItemLl.setOnClickListener(param1View -> {
            if (mOnItemClickListener != null) {
                set(position, true, dataBean);
                mOnItemClickListener.onItemClick(param1View, dataBean, position);
            }
        });
    }

    public void onClick(View paramView) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null)
            onItemClickListener.onClick(paramView, ((Integer) paramView.getTag()).intValue());
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ViewHolder(this.mLayoutInflater.inflate(R.layout.live_gift_item, paramViewGroup, false));
    }

    public void set(int paramInt, boolean paramBoolean, LiveGiftInfoBean.DataBean paramDataBean) {
        for (byte b = 0; b < this.mGiftDatas.size(); b++)
             ((LiveGiftInfoBean.DataBean) this.mGiftDatas.get(b)).select_item = false;
        if (paramBoolean)
            if (paramInt <= this.mGiftDatas.size())
                ((LiveGiftInfoBean.DataBean) this.mGiftDatas.get(paramInt)).select_item = true;
        notifyDataSetChanged();
    }

    public void setLiveGiftInfos(List<LiveGiftInfoBean.DataBean> paramList) {
        if (paramList != null && paramList.size() > 0) {
            List<LiveGiftInfoBean.DataBean> list = this.mGiftDatas;
            if (list == null) {
                this.mGiftDatas = new ArrayList<LiveGiftInfoBean.DataBean>();
            } else {
                list.clear();
            }
            this.mGiftDatas.addAll(paramList);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.mOnItemClickListener = paramOnItemClickListener;
    }

    public static interface OnItemClickListener {
        void onClick(int param1Int);

        void onClick(View param1View, int param1Int);

        void onItemClick(View param1View, LiveGiftInfoBean.DataBean param1DataBean, int param1Int);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.live_gift_item_iv)
        SimpleDraweeView liveGiftItemIv;
        @BindView(R.id.iv_live_gift_type)
        ImageView        ivLiveGiftType;
        @BindView(R.id.live_gift_item_hit)
        ImageView        liveGiftItemHit;
        @BindView(R.id.live_gift_item_name)
        TextView         liveGiftItemName;
        @BindView(R.id.live_gift_item_amond_gold)
        TextView         liveGiftItemAmondGold;
        @BindView(R.id.linear_price)
        LinearLayout     linearPrice;
        @BindView(R.id.live_gift_item_ll)
        RelativeLayout   liveGiftItemLl;
        @BindView(R.id.count)
        TextView         count;
        @BindView(R.id.expire)
        TextView         expire;

        ViewHolder(View param1View) {
            super(param1View);
            ButterKnife.bind(this, param1View);
        }
    }


    private String formatDateTime(long mss) {
        String time;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        if (days > 0) {
            time = days + "天" + hours + "小时";
            return time;
        } else if (hours > 0) {
            time = hours + "小时" + minutes + "分钟";
            return time;
        } else if (minutes > 0) {
            time = minutes + "分钟";
            return time;
        } else {
            return "即将过期";
        }
    }
}
