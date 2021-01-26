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
import com.jk.jkproject.ui.entity.GetFocusList;
import com.jk.jkproject.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFocusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;

    private List<GetFocusList.DataBean> datas;

    private final LayoutInflater mLayoutInflater;

    private final int mWidth;

    private OnItemClickListener onItemClickListener;

    private int pos = 0;

    public MyFocusAdapter(Context paramContext, List<GetFocusList.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        this.datas = paramList;
        this.mWidth = (ScreenUtils.getScreenW(paramContext) - ScreenUtils.dp2px(paramContext, 55.0F)) / 4;
    }

    @Override
    public int getItemCount() {
        return datas.size() > 0 ? datas.size() : 0;
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final GetFocusList.DataBean dataBean = this.datas.get(position);
        if (dataBean.getR_state() != 1) {
            ((ViewHolder) viewHolder).ivLiveIn.setVisibility(View.GONE);
        } else {
            ((ViewHolder) viewHolder).ivLiveIn.setVisibility(View.VISIBLE);
        }
        ((ViewHolder) viewHolder).sdvFocusHeader.setImageURI(dataBean.getPicture());
        ((ViewHolder) viewHolder).tvUserName.setText(dataBean.getNickName());
        ((ViewHolder) viewHolder).rlFocusItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (onItemClickListener != null)
                    onItemClickListener.click(dataBean, position);
            }
        });
    }

    public void onClick(View paramView) {
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = this.mLayoutInflater.inflate(R.layout.my_focus_item, paramViewGroup, false);
        ((RelativeLayout) view.findViewById(R.id.rl_focus_item)).setLayoutParams((ViewGroup.LayoutParams) new LinearLayout.LayoutParams(this.mWidth, -1));
        return new ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public static interface OnItemClickListener {
        void click(GetFocusList.DataBean param1DataBean, int param1Int);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      @BindView(R.id.sdv_focus_header)
      SimpleDraweeView sdvFocusHeader;
      @BindView(R.id.iv_live_in)
      ImageView ivLiveIn;
      @BindView(R.id.tv_user_name)
      TextView tvUserName;
      @BindView(R.id.rl_focus_item)
      RelativeLayout rlFocusItem;
        ViewHolder(View param1View) {
            super(param1View);
            ButterKnife.bind(this, param1View);
        }
    }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\MyFocusAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */