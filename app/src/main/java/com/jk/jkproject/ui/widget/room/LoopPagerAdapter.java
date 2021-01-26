package com.jk.jkproject.ui.widget.room;

import android.database.DataSetObserver;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.jk.jkproject.ui.inter.HintView;
import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class LoopPagerAdapter extends PagerAdapter {
  private ArrayList<View> mViewList = new ArrayList<View>();
  
  private RollPagerView mViewPager;
  
  public LoopPagerAdapter(RollPagerView paramRollPagerView) {
    this.mViewPager = paramRollPagerView;
    paramRollPagerView.setHintViewDelegate(new LoopHintViewDelegate());
  }
  
  private View findViewByPosition(ViewGroup paramViewGroup, int paramInt) {
    for (View view1 : this.mViewList) {
      if (((Integer)view1.getTag()).intValue() == paramInt && view1.getParent() == null)
        return view1; 
    } 
    View view = getView(paramViewGroup, paramInt);
    view.setTag(Integer.valueOf(paramInt));
    this.mViewList.add(view);
    return view;
  }
  
  private void initPosition() {
    if (this.mViewPager.getViewPager().getCurrentItem() == 0 && getRealCount() > 0)
      setCurrent(1073741823 - 1073741823 % getRealCount()); 
  }
  
  private void setCurrent(int paramInt) {
    try {
      Field field = ViewPager.class.getDeclaredField("mCurItem");
      field.setAccessible(true);
      field.set(this.mViewPager.getViewPager(), Integer.valueOf(paramInt));
    } catch (NoSuchFieldException noSuchFieldException) {
      noSuchFieldException.printStackTrace();
    } catch (IllegalAccessException illegalAccessException) {
      illegalAccessException.printStackTrace();
    } 
  }
  
  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
    paramViewGroup.removeView((View)paramObject);
  }
  
  @Deprecated
  public final int getCount() {
    int i;
    if (getRealCount() <= 0) {
      i = getRealCount();
    } else {
      i = Integer.MAX_VALUE;
    } 
    return i;
  }
  
  public int getItemPosition(Object paramObject) {
    return -2;
  }
  
  public abstract int getRealCount();
  
  public abstract View getView(ViewGroup paramViewGroup, int paramInt);
  
  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
    View view = findViewByPosition(paramViewGroup, paramInt % getRealCount());
    paramViewGroup.addView(view);
    return view;
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
  
  public void notifyDataSetChanged() {
    this.mViewList.clear();
    initPosition();
    super.notifyDataSetChanged();
  }
  
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver) {
    super.registerDataSetObserver(paramDataSetObserver);
    initPosition();
  }
  
  private class LoopHintViewDelegate implements RollPagerView.HintViewDelegate {
    private LoopHintViewDelegate() {}
    
    public void initView(int param1Int1, int param1Int2, HintView param1HintView) {
      if (param1HintView != null)
        param1HintView.initView(LoopPagerAdapter.this.getRealCount(), param1Int2); 
    }
    
    public void setCurrentPosition(int param1Int, HintView param1HintView) {
      if (param1HintView != null && LoopPagerAdapter.this.getRealCount() > 0)
        param1HintView.setCurrent(param1Int % LoopPagerAdapter.this.getRealCount()); 
    }
  }
}
