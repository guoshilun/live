package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.PKTypeBaseInfo;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.ViewHolder;

import java.util.List;

public class LiveRoomPkListAdapter extends CommonAdapter<PKTypeBaseInfo> {
    Context context;
    private List<PKTypeBaseInfo> datas;

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public static interface OnItemClickListener {
        void onItemClick(int type, PKTypeBaseInfo info);
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public LiveRoomPkListAdapter(Context paramContext, List<PKTypeBaseInfo> paramList, int paramInt) {
        super(paramContext, paramList, paramInt);
        this.context = paramContext;
        this.datas = paramList;
    }

    public void convert(ViewHolder paramViewHolder, final PKTypeBaseInfo testBean) {
        if (testBean != null) {
            TextView textView = (TextView) paramViewHolder.itemView.findViewById(R.id.tv_name);
            textView.setText(testBean.getName());
            paramViewHolder.setImageResource(R.id.sdv_header, testBean.getPicture()).setText(R.id.tv_name, testBean.getName()).
                    setText(R.id.tv_desc, testBean.getDescribe()).setText(R.id.tv_sign, testBean.getSgin()).setChecked(R.id.cb_select, testBean.isSelect() == 1);
            paramViewHolder.setOnClickListener(R.id.cb_select, v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(1, testBean);
                }
            });
            paramViewHolder.setOnClickListener(R.id.tv_add, v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(0, testBean);
                }
            });
        }
    }
}