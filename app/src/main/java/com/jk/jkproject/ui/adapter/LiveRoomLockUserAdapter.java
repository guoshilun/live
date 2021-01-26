package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveRoomUserLockBean;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LiveRoomLockUserAdapter extends BaseWrapperRecyclerAdapter<LiveRoomUserLockBean, LiveRoomLockUserAdapter.ItemViewHolder> {

    private Context context;

    private List<LiveRoomUserLockBean> list = new ArrayList<LiveRoomUserLockBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public LiveRoomLockUserAdapter(Context paramContext, List<LiveRoomUserLockBean> paramList) {
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
            final LiveRoomUserLockBean liveRoomUserLockBean = this.list.get(paramInt);
            paramItemViewHolder.sdvHeader.setImageURI(liveRoomUserLockBean.getPicture());
            TextView textView1 = paramItemViewHolder.tvUserLevel;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(liveRoomUserLockBean.getUserGrade());
            stringBuilder2.append("");
            textView1.setText(stringBuilder2.toString());
            TextView textView2 = paramItemViewHolder.tvUserNameHost;
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append(liveRoomUserLockBean.getAnchorGrade());
            stringBuilder1.append("");
            textView2.setText(stringBuilder1.toString());
            paramItemViewHolder.tvUserName.setText(liveRoomUserLockBean.getNickname());
            paramItemViewHolder.tvUserSign.setText(liveRoomUserLockBean.getSgin());
            if (!liveRoomUserLockBean.getSex().equals("1")) {
                paramItemViewHolder.ivUserSex.setImageResource(R.mipmap.live_icon_my_female);
            } else {
                paramItemViewHolder.ivUserSex.setImageResource(R.mipmap.live_icon_my_male);
            }
            paramItemViewHolder.llItem.setOnClickListener(param1View -> {
                if (onItemClickListener != null)
                    onItemClickListener.onClick(liveRoomUserLockBean.getUserId());
            });

            paramItemViewHolder.tvUserSign.setTextColor(context.getResources().getColor(R.color.color_999));
            paramItemViewHolder.tvUserName.setTextColor(context.getResources().getColor(R.color.color_333));
        }
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.live_room_lock_user, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(R.id.sdv_header)
        SimpleDraweeView sdvHeader;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.iv_user_sex)
        ImageView ivUserSex;
        @BindView(R.id.tv_user_level)
        TextView tvUserLevel;
        @BindView(R.id.tv_user_name_host)
        TextView tvUserNameHost;
        @BindView(R.id.rl_1)
        RelativeLayout rl1;
        @BindView(R.id.tv_user_sign)
        TextView tvUserSign;
        @BindView(R.id.tv_cancel)
        TextView tvCancel;
        @BindView(R.id.ll_item)
        RelativeLayout llItem;

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.sdvHeader = (SimpleDraweeView) param1View.findViewById(R.id.sdv_header);
            this.llItem = (RelativeLayout) param1View.findViewById(R.id.ll_item);
            this.tvUserName = (TextView) param1View.findViewById(R.id.tv_user_name);
            this.ivUserSex = (ImageView) param1View.findViewById(R.id.iv_user_sex);
            this.tvUserLevel = (TextView) param1View.findViewById(R.id.tv_user_level);
            this.tvUserNameHost = (TextView) param1View.findViewById(R.id.tv_user_name_host);
            this.tvUserSign = (TextView) param1View.findViewById(R.id.tv_user_sign);
        }
    }

    public static interface OnItemClickListener {
        void onClick(String param1String);
    }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\LiveRoomLockUserAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */