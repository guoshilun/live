package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.MyHomePageActivity;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FastClickUtils;
import com.jk.jkproject.utils.FuncUtils;
import com.jk.jkproject.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Zick
 * @params
 * @date 2020/7/31 2:00 PM
 * @desc 首页附近
 */
public class LiveHomeNearAdapter extends BaseWrapperRecyclerAdapter<LiveHomeBeanInfo.DataBean, LiveHomeNearAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {

    private Context context;

    private List<LiveHomeBeanInfo.DataBean> list = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public LiveHomeNearAdapter(Context paramContext, List<LiveHomeBeanInfo.DataBean> paramList) {
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
            LiveHomeBeanInfo.DataBean dataBean = this.list.get(paramInt);
            paramItemViewHolder.tvTitle.setText(dataBean.getR_name());
            paramItemViewHolder.sdvHeader.setImageURI(dataBean.getR_cover());
            paramItemViewHolder.tvLiveCount.setText(dataBean.getPeopleNumber() + "人观看");
            paramItemViewHolder.tvLiveDistance.setText(StringUtils.getDistance(dataBean.getDistance()));
            if (dataBean.getR_state() != 1) {
                paramItemViewHolder.tvLiveType.setText("未开播");
                paramItemViewHolder.tvLiveType.setBackgroundResource(R.drawable.bg_gray_btn);
            } else {
                paramItemViewHolder.tvLiveType.setText("直播中");
                paramItemViewHolder.tvLiveType.setBackgroundResource(R.drawable.bg_red_btn);
            }

            paramItemViewHolder.rlItem.setOnClickListener(v -> {
                Intent intent = null;
                if (dataBean.getR_state() != 1) {
                    intent = new Intent(context, MyHomePageActivity.class);
                    intent.putExtra("u_id", dataBean.getU_id());
                    context.startActivity(intent);
                } else {
                    if (FastClickUtils.isFastClick()) {
                        intent = new Intent(context, SlideActivity.class);
                        intent.putExtra("roomId", dataBean.getRoomId());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    public void onClick(View paramView, int paramInt) {
        if (FuncUtils.isFastDoubleClick())
            return;
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_room_near_item, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.tv_live_type)
        TextView tvLiveType;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_live_distance)
        TextView tvLiveDistance;
        @BindView(R.id.tv_live_count)
        TextView tvLiveCount;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;

        public ItemViewHolder(View param1View) {

            super(param1View);
            this.sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            this.tvLiveType = (TextView) param1View.findViewById(R.id.tv_live_type);
            this.tvTitle = (TextView) param1View.findViewById(R.id.tv_title);
            this.tvLiveDistance = (TextView) param1View.findViewById(R.id.tv_live_distance);
            this.tvLiveCount = (TextView) param1View.findViewById(R.id.tv_live_count);
            this.rlItem = (RelativeLayout) param1View.findViewById(R.id.rl_item);
            addOnItemViewClickListener();
            addOnViewClickListener(param1View);
        }
    }

    public static interface OnItemClickListener {
        void terminationClick(View param1View, String param1String);
    }
}
