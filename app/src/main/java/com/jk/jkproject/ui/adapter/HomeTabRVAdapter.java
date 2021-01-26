package com.jk.jkproject.ui.adapter;


import android.content.Context;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.HomeTabInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeTabRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private final LayoutInflater mLayoutInflater;

    private Context context;
    private List<HomeTabInfo> datas;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void click(HomeTabInfo data, int position);
    }

    /**
     * @param context
     */
    public HomeTabRVAdapter(Context context, List<HomeTabInfo> datas) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.live_tab_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final HomeTabInfo data = datas.get(position);

        viewHolder.tabTv.setText(data.getTitleName());

        if (data.isSelected()) {
            viewHolder.tabTv.setTextColor(context.getResources().getColor(R.color.color_323232));
            viewHolder.tabTv.setTextSize(25);
            viewHolder.tabTv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            viewHolder.tabIv.setImageResource(R.mipmap.live_icon_home_tab_line);
        } else {
            viewHolder.tabTv.setTextSize(17);
            viewHolder.tabTv.setTextColor(context.getResources().getColor(R.color.color_646464));
            viewHolder.tabIv.setImageResource(R.drawable.bg_white_round_btn);
        }

        viewHolder.tabItem.setOnClickListener(v -> {
            set(position, true);
            if (onItemClickListener != null) {
                onItemClickListener.click(data, position);
            }
        });
    }


    public void set(int position, boolean isSelect) {
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setSelected(false);
        }
        datas.get(position).setSelected(true);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tab_tv)
        TextView tabTv;
        @BindView(R.id.tab_iv)
        ImageView tabIv;
        @BindView(R.id.tab_item)
        LinearLayout tabItem;

        ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}