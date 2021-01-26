package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DialogViewPager extends ViewPager {
  public DialogViewPager(Context paramContext) {
    super(paramContext);
  }
  
  public DialogViewPager(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i = 0;
    paramInt2 = 0;
    while (paramInt2 < getChildCount()) {
      View view = getChildAt(paramInt2);
      view.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(0, 0));
      int j = view.getMeasuredHeight();
      int k = i;
      if (j > i)
        k = j; 
      paramInt2++;
      i = k;
    } 
    super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(i, 1073741824));
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return false;
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return false;
  }
}
