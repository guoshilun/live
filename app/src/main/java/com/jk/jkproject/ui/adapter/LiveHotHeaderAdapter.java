package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.BannerBeanInfo;
import com.jk.jkproject.ui.widget.RecyclingPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LiveHotHeaderAdapter extends RecyclingPagerAdapter {
    private View.OnClickListener bannerListener = null;

    private List<BannerBeanInfo.DataBean> infos = new ArrayList<BannerBeanInfo.DataBean>();

    private boolean isInfiniteLoop;

    private Context mContext;

    private int size;

    public LiveHotHeaderAdapter(Context paramContext) {
        this.mContext = paramContext;
        this.isInfiniteLoop = false;
    }

    public int getCount() {
        int i;
        if (this.isInfiniteLoop) {
            i = Integer.MAX_VALUE;
        } else {
            i = this.size;
        }
        return i;
    }

    public BannerBeanInfo.DataBean getItem(int paramInt) {
        if (paramInt >= 0) {
            List<BannerBeanInfo.DataBean> list = this.infos;
            if (list != null && paramInt < list.size())
                return this.infos.get(paramInt);
        }
        return null;
    }

    public int getPosition(int paramInt) {
        if (this.isInfiniteLoop)
            paramInt %= this.size;
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        View view = paramView;
        if (paramView == null)
            view = View.inflate(this.mContext, R.layout.layout_home_hot_header_item, null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.iv_pic);
        paramInt = getPosition(paramInt);
        BannerBeanInfo.DataBean dataBean = getItem(paramInt);
        if (dataBean != null) {
            if (dataBean.getImgUrl() != null)
                simpleDraweeView.setImageURI(Uri.parse(dataBean.getImgUrl()));
            simpleDraweeView.setTag(R.id.tag_position, paramInt);
            simpleDraweeView.setOnClickListener(this.bannerListener);
        }
        return view;
    }

    public void setBannerClickListener(View.OnClickListener paramOnClickListener) {
        this.bannerListener = paramOnClickListener;
    }

    public void setInfiniteLoop(boolean paramBoolean) {
        this.isInfiniteLoop = paramBoolean;
        notifyDataSetChanged();
    }

    public void setdata(List<BannerBeanInfo.DataBean> paramList) {
        int i;
        this.infos = paramList;
        paramList = this.infos;
        if (paramList == null || paramList.size() == 0) {
            i = 1;
        } else {
            i = this.infos.size();
        }
        this.size = i;
        this.isInfiniteLoop = false;
    }
}
