package com.jk.jkproject.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.DiamondTopUpBean;

import java.util.List;

public class DiamondListAdapter extends BaseQuickAdapter<DiamondTopUpBean, BaseViewHolder> {
    private OnItemclickListener onItemClickListener;

    public DiamondListAdapter(List<DiamondTopUpBean> paramList) {
        super(R.layout.item_diamond_list_layout, paramList);
    }

    protected void convert(BaseViewHolder viewHolder, final DiamondTopUpBean bean) {
        if (bean.getIsClick()) {
            viewHolder.setBackgroundRes(R.id.rl_diamond, R.mipmap.bg_diamond_pay_onclick);
        } else {
            viewHolder.setBackgroundRes(R.id.rl_diamond, R.drawable.bg_diamond_pay_unclick);
        }
        viewHolder.setText(R.id.tv_diamond, bean.getDiamond());
        viewHolder.setText(R.id.tv_money, bean.getMoney() + "元");
        viewHolder.setOnClickListener(R.id.rl_diamond, v -> {
            setOnClick(viewHolder.getLayoutPosition());
            if (onItemClickListener != null) {
                onItemClickListener.onClick(bean);
            }
        });
    }

    private void setOnClick(int position) {
        for (int i = 0; i < getData().size(); i++) {
            if (i == position) {
                getData().get(i).setIsClick(true);
            } else {
                getData().get(i).setIsClick(false);
            }
        }
        notifyDataSetChanged();
    }

    public OnItemclickListener getOnItemclickListener() {
        return this.onItemClickListener;
    }

    public void setOnItemClickListener(OnItemclickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public static interface OnItemclickListener {
        void onClick(DiamondTopUpBean param1DanmuBean);
    }
}