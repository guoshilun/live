package com.jk.jkproject.ui.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveHomeBeanInfo;
import com.jk.jkproject.ui.entity.LiveRoomUserLockBean;

import java.util.ArrayList;
import java.util.List;

public class LiveOnlineUsersAdapter extends RecyclerView.Adapter<LiveOnlineUsersAdapter.ViewHolder> {

    private List<LiveRoomUserLockBean> mInfos;

    private LayoutInflater mLayoutInflater;

    public LiveOnlineUsersAdapter(Context paramContext, List<LiveRoomUserLockBean> paramArrayList) {
        List<LiveRoomUserLockBean> arrayList;
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        if (paramArrayList == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = paramArrayList;
        }
        this.mInfos = arrayList;
    }

    public boolean add(LiveRoomUserLockBean paramLiveRoomUserLockBean) {
        boolean bool = false;
        if (this.mInfos.indexOf(paramLiveRoomUserLockBean) == -1)
            bool = this.mInfos.add(paramLiveRoomUserLockBean);
        notifyDataSetChanged();
        return bool;
    }

    public LiveRoomUserLockBean getItem(int paramInt) {
        if (paramInt >= 0) {
            List<LiveRoomUserLockBean> arrayList = this.mInfos;
            if (arrayList != null && paramInt < arrayList.size())
                return this.mInfos.get(paramInt);
        }
        return null;
    }

    public int getItemCount() {
        int i;
        List<LiveRoomUserLockBean> arrayList = this.mInfos;
        if (arrayList == null) {
            i = 0;
        } else {
            i = arrayList.size();
        }
        int j = i;
        if (i > 20)
            j = 20;
        return j;
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        LiveRoomUserLockBean liveRoomUserLockBean = getItem(paramInt);
        if (liveRoomUserLockBean != null)
            if (TextUtils.isEmpty(liveRoomUserLockBean.getPicture())) {
                paramViewHolder.ivIcon.setVisibility(View.GONE);
            } else {
                paramViewHolder.ivIcon.setVisibility(View.VISIBLE);
                paramViewHolder.ivIcon.setImageURI(liveRoomUserLockBean.getPicture());
            }
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        View view = this.mLayoutInflater.inflate(R.layout.layout_item_for_live_online_user, paramViewGroup, false);
        return new ViewHolder(this.mLayoutInflater.getContext(), view);
    }

    public boolean remove(LiveRoomUserLockBean paramLiveRoomUserLockBean) {
        boolean bool = this.mInfos.remove(paramLiveRoomUserLockBean);
        notifyDataSetChanged();
        return bool;
    }

    public void clear() {
        if (mInfos != null) {
            mInfos.clear();
            notifyDataSetChanged();
        }
    }

    public void update(LiveHomeBeanInfo.DataBean paramDataBean) {
    }

    public boolean update(List<LiveRoomUserLockBean> paramArrayList) {
        if (paramArrayList == null)
            return false;
        this.mInfos.clear();
        boolean bool = this.mInfos.addAll(paramArrayList);
        notifyDataSetChanged();
        return bool;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView ivIcon;

        ImageView ivLevel;

        SimpleDraweeView ivLove;


        public ViewHolder(Context param1Context, View param1View) {
            super(param1View);
            this.ivIcon = (SimpleDraweeView) param1View.findViewById(R.id.iv_icon);
            this.ivLove = (SimpleDraweeView) param1View.findViewById(R.id.iv_love);
            this.ivLevel = (ImageView) param1View.findViewById(R.id.iv_level);
        }
    }
}