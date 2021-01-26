package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CatonViewPager extends ViewPager {
  private int preX = 0;
  
  public CatonViewPager(Context paramContext) {
    super(paramContext);
  }
  
  public CatonViewPager(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction();
    return (i != 4 && i != 8) ? super.onInterceptTouchEvent(paramMotionEvent) : true;
  }
}
