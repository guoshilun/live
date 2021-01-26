package com.jk.jkproject.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.GiveCountBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveGiftGiveCountAdapter extends RecyclerView.Adapter {

    private List<GiveCountBean> liveGiftGiveCount;

    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public LiveGiftGiveCountAdapter(Context paramContext) {
        this.mContext = paramContext;
    }

    public int getItemCount() {
        return this.liveGiftGiveCount.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final GiveCountBean giveCountBean = liveGiftGiveCount.get(position);
        ((ViewHolder) viewHolder).tvGiftNum.setText(giveCountBean.getNum() + "");
        ((ViewHolder) viewHolder).tvGiftName.setText(giveCountBean.getName());
        ((ViewHolder) viewHolder).itemSelectGift.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor"})
            public void onClick(View param1View) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(giveCountBean);
            }
        });
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ViewHolder(View.inflate(paramViewGroup.getContext(), R.layout.item_layout_select_gift, null));
    }

    public void setGiveCountData(List<GiveCountBean> paramList) {
        if (paramList != null && paramList.size() > 0) {
            List<GiveCountBean> list = this.liveGiftGiveCount;
            if (list == null) {
                this.liveGiftGiveCount = new ArrayList<GiveCountBean>();
            } else {
                list.clear();
            }
            this.liveGiftGiveCount.addAll(paramList);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public static interface OnItemClickListener {
        void onClick(GiveCountBean param1GiveCountBean);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      @BindView(R.id.tv_gift_num)
      TextView tvGiftNum;
      @BindView(R.id.tv_gift_name)
      TextView tvGiftName;
      @BindView(R.id.item_select_gift)
      LinearLayout itemSelectGift;
        ViewHolder(View param1View) {
            super(param1View);
            ButterKnife.bind(this, param1View);
        }
    }
}