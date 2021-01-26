package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.ui.entity.BlackList;
import com.jk.jkproject.ui.widget.recyclerview.BaseWrapperRecyclerAdapter;
import com.jk.jkproject.ui.widget.recyclerview.ClickableViewHolder;
import com.jk.jkproject.ui.widget.recyclerview.OnRecyclerItemClickListener;
import com.jk.jkproject.utils.FuncUtils;

import java.util.ArrayList;
import java.util.List;

public class MyBlackListAdapter extends BaseWrapperRecyclerAdapter<BlackList.DataBean, MyBlackListAdapter.ItemViewHolder> implements OnRecyclerItemClickListener {
    private Context context;

    private List<BlackList.DataBean> list = new ArrayList<BlackList.DataBean>();

    private LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    private int type;

    public MyBlackListAdapter(Context paramContext, List<BlackList.DataBean> paramList) {
        this.context = paramContext;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        this.type = this.type;
        appendToList(paramList);
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public void onBindItemViewHolder(ItemViewHolder paramItemViewHolder, int paramInt) {
        list = getList();
        if (this.list.size() > 0) {
            final BlackList.DataBean dataBean = list.get(paramInt);
            paramItemViewHolder.sdvUserHeader.setImageURI(dataBean.getPicture());
            paramItemViewHolder.tvUserName.setText(dataBean.getNickname());
            paramItemViewHolder.ivType.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (onItemClickListener != null)
                        onItemClickListener.onClick(dataBean.getUserId());
                }
            });
        }
    }

    public void onClick(View paramView, int paramInt) {
        if (FuncUtils.isFastDoubleClick())
            return;
    }

    public ItemViewHolder onCreateItemViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(2131427492, paramViewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.onItemClickListener = paramOnItemClickListener;
    }

    public class ItemViewHolder extends ClickableViewHolder {
        @BindView(2131296533)
        ImageView ivType;

        @BindView(2131296759)
        SimpleDraweeView sdvUserHeader;

        @BindView(2131297051)
        TextView tvUserName;

        public ItemViewHolder(View param1View) {
            super(param1View);
            this.sdvUserHeader = (SimpleDraweeView) param1View.findViewById(2131296759);
            this.tvUserName = (TextView) param1View.findViewById(2131297051);
            this.ivType = (ImageView) param1View.findViewById(2131296533);
            addOnItemViewClickListener();
            addOnViewClickListener(param1View);
        }
    }

    public static interface OnItemClickListener {
        void onClick(String param1String);
    }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\MyBlackListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */