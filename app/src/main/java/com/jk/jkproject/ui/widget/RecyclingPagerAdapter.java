package com.jk.jkproject.ui.widget;

import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class RecyclingPagerAdapter extends PagerAdapter {
  static final int IGNORE_ITEM_VIEW_TYPE = -1;
  
  private final RecycleBin recycleBin;
  
  public RecyclingPagerAdapter() {
    this(new RecycleBin());
  }
  
  RecyclingPagerAdapter(RecycleBin paramRecycleBin) {
    this.recycleBin = paramRecycleBin;
    paramRecycleBin.setViewTypeCount(getViewTypeCount());
  }
  
  public final void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
    paramObject = paramObject;
    paramViewGroup.removeView((View)paramObject);
    int i = getItemViewType(paramInt);
    if (i != -1)
      this.recycleBin.addScrapView((View)paramObject, paramInt, i); 
  }
  
  public int getItemViewType(int paramInt) {
    return 0;
  }
  
  public abstract View getView(int paramInt, View paramView, ViewGroup paramViewGroup);
  
  public int getViewTypeCount() {
    return 1;
  }
  
  public final Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
    int i = getItemViewType(paramInt);
    View view = null;
    if (i != -1)
      view = this.recycleBin.getScrapView(paramInt, i); 
    view = getView(paramInt, view, paramViewGroup);
    paramViewGroup.addView(view);
    return view;
  }
  
  public final boolean isViewFromObject(View paramView, Object paramObject) {
    boolean bool;
    if (paramView == paramObject) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void notifyDataSetChanged() {
    this.recycleBin.scrapActiveViews();
    super.notifyDataSetChanged();
  }
}