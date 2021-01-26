package com.jk.jkproject.ui.adapter;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class LiveRoomViewPageAdapter extends PagerAdapter {
    private static final String TAG = "LiveRoomViewPageAdapter";
    private List<View> viewlist;

    public LiveRoomViewPageAdapter() {
    }

    public LiveRoomViewPageAdapter(List<View> paramList) {
        this.viewlist = paramList;
    }

    public void destroyItem(View paramView, int paramInt, Object paramObject) {

        ((ViewPager) paramView).removeView(this.viewlist.get(paramInt));
    }

    public void finishUpdate(ViewGroup paramViewGroup) {
        super.finishUpdate(paramViewGroup);
    }

    public int getCount() {
        return this.viewlist.size();
    }

    public View instantiateItem(View paramView, int paramInt) {

        ((ViewPager) paramView).addView(this.viewlist.get(paramInt));
        return this.viewlist.get(paramInt);
    }

    public boolean isViewFromObject(View paramView, Object paramObject) {
        return paramView == paramObject;
    }

    public void startUpdate(ViewGroup paramViewGroup) {
        super.startUpdate(paramViewGroup);
    }
}