package com.jk.jkproject.ui.adapter;

import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import java.util.List;

public class EmoViewPageAdapter extends PagerAdapter {
  private List<GridView> mListViews;
  
  public EmoViewPageAdapter(List<GridView> paramList) {
    this.mListViews = paramList;
  }
  
  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
    try {
      paramViewGroup.removeView((View)this.mListViews.get(paramInt));
    } catch (Exception exception) {}
  }
  
  public int getCount() {
    return this.mListViews.size();
  }
  
  public int getItemPosition(Object paramObject) {
    return super.getItemPosition(paramObject);
  }
  
  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
    try {
      paramViewGroup.addView((View)this.mListViews.get(paramInt), 0);
      return this.mListViews.get(paramInt);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public boolean isViewFromObject(View paramView, Object paramObject) {
    boolean bool;
    if (paramView == paramObject) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\adapter\EmoViewPageAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */