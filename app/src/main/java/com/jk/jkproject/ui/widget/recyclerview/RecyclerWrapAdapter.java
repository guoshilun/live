package com.jk.jkproject.ui.widget.recyclerview;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Author: CJ_Xiongj
 * Data: 2015/12/16 15:27
 * Description:
 */
public abstract class RecyclerWrapAdapter extends RecyclerView.Adapter  implements View.OnClickListener{

    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFootViews = new ArrayList<>();

    public RecyclerWrapAdapter() {

    }


    public void addHeaderView(View view) {
        mHeaderViews.clear();
        mHeaderViews.add(view);
    }

    public void addFootView(View view) {
        mFootViews.clear();
        mFootViews.add(view);
    }


    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerView.INVALID_TYPE) {
            return new HeaderViewHolder(mHeaderViews.get(0));
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
            return new HeaderViewHolder(mFootViews.get(0));
        }
        return onCreateAdvanceViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = getAdvanceCount();
        if (adjPosition < adapterCount) {
            onBindAdvanceViewHolder(holder, adjPosition);
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(this);
        }

    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getAdvanceCount();

    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return RecyclerView.INVALID_TYPE;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = getAdvanceCount();
        if (adjPosition < adapterCount) {
            return getAdvanceViewType(adjPosition);
        }
        return RecyclerView.INVALID_TYPE - 1;
    }


    @Override
    public long getItemId(int position) {
        int numHeaders = getHeadersCount();
        if (position >= numHeaders) {
            int adjPosition = position - numHeaders;
            int adapterCount = getAdvanceCount();
            if (adjPosition < adapterCount) {
                return getItemID(adjPosition);
            }
        }
        return -1;
    }

    public abstract long getItemID(int pos);

    public abstract int getAdvanceViewType(int position);

    /**
     * !! 不能为0！！！
     *
     * @return
     */
    protected abstract int getAdvanceCount();

    protected abstract void onBindAdvanceViewHolder(RecyclerView.ViewHolder holder, int i);

    protected abstract RecyclerView.ViewHolder onCreateAdvanceViewHolder(ViewGroup parent, int viewType);


    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int dataPos);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int)v.getTag());
        }
    }
}
