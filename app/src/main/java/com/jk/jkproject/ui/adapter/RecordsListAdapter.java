package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.MyRecordsListBean;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecordsListAdapter extends BaseWrapperRecyclerAdapter<MyRecordsListBean.DataBean, RecordsListAdapter.ItemViewHolder> {

    private Context context;

    private List<MyRecordsListBean.DataBean> list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public RecordsListAdapter(Context paramContext, List<MyRecordsListBean.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, int paramInt) {
        this.list = getList();
        if (this.list.size() > 0) {
            MyRecordsListBean.DataBean dataBean = this.list.get(paramInt);
            paramItemViewHolder.tvName.setText("充值" + dataBean.getConversion_count() + "个钻石");
            paramItemViewHolder.tvTime.setText(TimeUtils.millis2String(dataBean.getU_dateTime()));
            if (dataBean.getStatus() == 2) {
                paramItemViewHolder.tvType.setText("成功");
            } else {
                paramItemViewHolder.tvType.setText("失败");
            }
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.records_list, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.ll_item)
        RelativeLayout llItem;

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.tvTime = (TextView) param1View.findViewById(R.id.tv_time);
            this.tvName = (TextView) param1View.findViewById(R.id.tv_name);
            this.tvType = (TextView) param1View.findViewById(R.id.tv_type);
            this.llItem = (RelativeLayout) param1View.findViewById(R.id.ll_item);
        }
    }

    public static interface OnItemClickListener {
        void terminationClick(View param1View, String param1String);
    }
}