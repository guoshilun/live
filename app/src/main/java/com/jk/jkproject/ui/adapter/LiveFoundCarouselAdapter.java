package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.ui.entity.LiveHomeFoundBaseInfo;
import com.jk.jkproject.ui.widget.RecyclingPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
*@params
*@date 2020/7/31 10:43 AM
*@author Zick
*@desc 发现页面轮播图
*/
public class LiveFoundCarouselAdapter extends RecyclingPagerAdapter {
    private View.OnClickListener bannerListener = null;

    private List<LiveHomeFoundBaseInfo.DataBean.CarouselBean> infos = new ArrayList<LiveHomeFoundBaseInfo.DataBean.CarouselBean>();

    private boolean isInfiniteLoop;

    private Context mContext;

    private int size;

    public LiveFoundCarouselAdapter(Context paramContext) {
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

    public LiveHomeFoundBaseInfo.DataBean.CarouselBean getItem(int paramInt) {
        if (paramInt >= 0) {
            List<LiveHomeFoundBaseInfo.DataBean.CarouselBean> list = this.infos;
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
            view = View.inflate(this.mContext, R.layout.layout_home_all_header_item, null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.iv_pic);
        paramInt = getPosition(paramInt);
        LiveHomeFoundBaseInfo.DataBean.CarouselBean carouselBean = getItem(paramInt);
        if (carouselBean != null) {
            if (carouselBean.getImgUrl() != null)
                simpleDraweeView.setImageURI(Uri.parse(carouselBean.getImgUrl()));
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

    public void setdata(List<LiveHomeFoundBaseInfo.DataBean.CarouselBean> paramList) {
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
