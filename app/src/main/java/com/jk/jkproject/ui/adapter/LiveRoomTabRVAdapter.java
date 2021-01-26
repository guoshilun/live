package com.jk.jkproject.ui.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.HomeTabInfo;
import com.jk.jkproject.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveRoomTabRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;

    private List<HomeTabInfo> datas;

    private final LayoutInflater mLayoutInflater;

    private final int mWidth;

    private OnItemClickListener onItemClickListener;

    private int pos = 0;

    public LiveRoomTabRVAdapter(Context paramContext, List<HomeTabInfo> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        this.datas = paramList;
        this.mWidth = (ScreenUtils.getScreenW(paramContext) - ScreenUtils.dp2px(paramContext, 75.0F)) / 4;
    }

    public LiveRoomTabRVAdapter(Context paramContext, List<HomeTabInfo> paramList, int paramInt) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        this.datas = paramList;
        this.pos = paramInt;
        this.mWidth = (ScreenUtils.getScreenW(paramContext) - ScreenUtils.dp2px(paramContext, 41.0F)) / 3;
    }

    public int getItemCount() {
        return datas.size() > 0 ? datas.size() : 0;
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, final int position) {
        ViewHolder viewHolder = (ViewHolder) paramViewHolder;
        final HomeTabInfo data = this.datas.get(position);
        viewHolder.tabTv.setText(data.getTitleName());
        if (pos != 1) {
            if (data.isSelected()) {
                viewHolder.tabTv.setTextColor(this.context.getResources().getColor(R.color.tabSelectTextColor));
                viewHolder.tabTv.setTextSize(20.0F);
                viewHolder.tabIv.setImageResource(R.drawable.bg_red_btn);
            } else {
                viewHolder.tabTv.setTextSize(16.0F);
                viewHolder.tabTv.setTextColor(this.context.getResources().getColor(R.color.blackColor));
                viewHolder.tabIv.setImageResource(R.drawable.bg_white_round_btn);
            }
        } else {
            viewHolder.tabIv.setVisibility(View.GONE);
            switch (position) {
                case 2:
                    viewHolder.tabLine.setVisibility(View.VISIBLE);
                    if (data.isSelected()) {
                        viewHolder.tabTv.setBackgroundResource(R.drawable.bg_select_red_right);
                        viewHolder.tabTv.setTextColor(context.getResources().getColor(R.color.color_F1F5F8));
                        break;
                    }
                    viewHolder.tabTv.setBackgroundResource(R.drawable.bg_noselect_red_right);
                    viewHolder.tabTv.setTextColor(context.getResources().getColor(R.color.tabSelectTextColor));
                    break;
                case 1:
                    viewHolder.tabLine.setVisibility(View.VISIBLE);
                    if (data.isSelected()) {
                        viewHolder.tabTv.setBackgroundColor(this.context.getResources().getColor(R.color.tabSelectTextColor));
                        viewHolder.tabTv.setTextColor(this.context.getResources().getColor(R.color.color_F1F5F8));
                        break;
                    }
                    viewHolder.tabTv.setBackgroundColor(this.context.getResources().getColor(R.color.color_F1F5F8));
                    viewHolder.tabTv.setTextColor(this.context.getResources().getColor(R.color.tabSelectTextColor));
                    break;
                case 0:
                    viewHolder.tabLine.setVisibility(View.GONE);
                    if (data.isSelected()) {
                        viewHolder.tabTv.setBackgroundResource(R.drawable.bg_select_red_left);
                        viewHolder.tabTv.setTextColor(this.context.getResources().getColor(R.color.color_F1F5F8));
                        break;
                    }
                    viewHolder.tabTv.setBackgroundResource(R.drawable.bg_noselect_red_left);
                    viewHolder.tabTv.setTextColor(this.context.getResources().getColor(R.color.tabSelectTextColor));
                    break;
            }
        }
        if (data.getCount() > 0) {
            viewHolder.tabTvCount.setVisibility(View.VISIBLE);
            TextView textView = viewHolder.tabTvCount;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("(");
            stringBuilder.append(data.getCount());
            stringBuilder.append(")");
            textView.setText(stringBuilder.toString());
        } else {
            viewHolder.tabTvCount.setVisibility(View.GONE);
        }
        viewHolder.tabItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                set(position, true);
                if (onItemClickListener != null)
                    onItemClickListener.click(data, position);
            }
        });
    }

    public void onClick(View paramView) {
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        LinearLayout.LayoutParams layoutParams;
        View view = this.mLayoutInflater.inflate(R.layout.live_room_tab_item, paramViewGroup, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.tab_item);
        if (this.pos != 1) {
            layoutParams = new LinearLayout.LayoutParams(this.mWidth, ScreenUtils.dp2px(this.context, 43.0F));
        } else {
            layoutParams = new LinearLayout.LayoutParams(this.mWidth, -1);
        }
        linearLayout.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    public void set(int paramInt, boolean paramBoolean) {
        for (byte b = 0; b < this.datas.size(); b++)
            ((HomeTabInfo) this.datas.get(b)).setSelected(false);
        ((HomeTabInfo) this.datas.get(paramInt)).setSelected(true);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public void setUpdata(int paramInt) {
        for (byte b = 0; b < this.datas.size(); b++) {
            HomeTabInfo homeTabInfo = this.datas.get(b);
            if (homeTabInfo.getTitleName().equals("贵宾"))
                homeTabInfo.setCount(paramInt);
        }
        notifyDataSetChanged();
    }

    public static interface OnItemClickListener {
        void click(HomeTabInfo param1HomeTabInfo, int param1Int);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      @BindView(R.id.tab_line)
      TextView tabLine;
      @BindView(R.id.tab_tv)
      TextView tabTv;
      @BindView(R.id.tab_tv_count)
      TextView tabTvCount;
      @BindView(R.id.tab_iv)
      ImageView tabIv;
      @BindView(R.id.tab_item)
      LinearLayout tabItem;

        ViewHolder(View param1View) {
            super(param1View);
            ButterKnife.bind(this, param1View);
        }
    }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\LiveRoomTabRVAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */