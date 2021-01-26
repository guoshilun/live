package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.activity.SlideActivity;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.utils.FastClickUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LiveHomeFocusAdapter extends BaseWrapperRecyclerAdapter<LiveHomeBeanInfo.DataBean, LiveHomeFocusAdapter.ItemViewHolder> {

    private Context context;

    private List<LiveHomeBeanInfo.DataBean> list = new ArrayList<LiveHomeBeanInfo.DataBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public LiveHomeFocusAdapter(Context paramContext, List<LiveHomeBeanInfo.DataBean> paramList) {
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
            paramItemViewHolder.sdvHeader.setImageURI(dataBean.getU_picture());
            paramItemViewHolder.tvFocusName.setText(dataBean.getU_name());
            paramItemViewHolder.tvFocusTitle.setText(dataBean.getSgin());
            paramItemViewHolder.tvFocusCount.setText(dataBean.getPeopleNumber() + "");
            paramItemViewHolder.tvFocusLiveTitle.setText(dataBean.getR_name());
            paramItemViewHolder.tvFocusLevel.setText(dataBean.getAnchorGrade() + "");
            paramItemViewHolder.sdvPic.setImageURI(dataBean.getR_cover());
            paramItemViewHolder.llItem.setOnClickListener(v -> {
                if (FastClickUtils.isFastClick()) {
                    Intent intent = new Intent(context, SlideActivity.class);
                    intent.putExtra("roomId", dataBean.getRoomId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_home_focus, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.tv_focus_name)
        TextView tvFocusName;
        @BindView(R.id.tv_focus_level)
        TextView tvFocusLevel;
        @BindView(R.id.tv_focus_title)
        TextView tvFocusTitle;
        @BindView(R.id.tv_focus_count)
        TextView tvFocusCount;
        @BindView(R.id.sdv_pic)
        SimpleDraweeView sdvPic;
        @BindView(R.id.tv_focus_live_title)
        TextView tvFocusLiveTitle;
        @BindView(R.id.ll_item)
        LinearLayout llItem;

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            this.tvFocusName = (TextView) param1View.findViewById(R.id.tv_focus_name);
            this.tvFocusLevel = (TextView) param1View.findViewById(R.id.tv_focus_level);
            this.tvFocusTitle = (TextView) param1View.findViewById(R.id.tv_focus_title);
            this.tvFocusCount = (TextView) param1View.findViewById(R.id.tv_focus_count);
            this.sdvPic = (SimpleDraweeView) param1View.findViewById(R.id.sdv_pic);
            this.tvFocusLiveTitle = (TextView) param1View.findViewById(R.id.tv_focus_live_title);
            this.llItem = (LinearLayout) param1View.findViewById(R.id.ll_item);
        }
    }

    public static interface OnItemClickListener {
        void terminationClick(View param1View, String param1String);
    }
}