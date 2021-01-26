package com.jk.jkproject.ui.widget.MsgListView;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;

import androidx.viewpager.widget.ViewPager;

public class MyHorizontalScrollView extends HorizontalScrollView {
  public MyHorizontalScrollView(Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public MyHorizontalScrollView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    MyHorizontalScrollView myHorizontalScrollView = this;
    while (true) {
      ViewParent viewParent2 = myHorizontalScrollView.getParent();
      ViewParent viewParent1 = viewParent2;
      if (!(viewParent2 instanceof ViewPager))
        continue; 
      viewParent1.requestDisallowInterceptTouchEvent(true);
      return super.dispatchTouchEvent(paramMotionEvent);
    } 
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return super.onTouchEvent(paramMotionEvent);
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\widget\MsgListView\MyHorizontalScrollView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */