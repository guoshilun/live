package com.jk.jkproject.ui.widget.pagescroll;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class AutoGridViewManager extends GridLayoutManager {
  private int measuredHeight = 0;
  
  private int measuredWidth = 0;
  
  public AutoGridViewManager(Context paramContext, int paramInt) {
    super(paramContext, paramInt);
  }
  
  public AutoGridViewManager(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean) {
    super(paramContext, paramInt1, paramInt2, paramBoolean);
  }
  
  public AutoGridViewManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
  }
  
  public void onMeasure(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2) {
    if (this.measuredHeight <= 0 && paramState.getItemCount() > 0) {
      View view = paramRecycler.getViewForPosition(0);
      if (view != null) {
        measureChild(view, paramInt1, paramInt2);
        this.measuredWidth = View.MeasureSpec.getSize(paramInt1);
        this.measuredHeight = view.getMeasuredHeight() * getSpanCount();
      } 
    } 
    setMeasuredDimension(this.measuredWidth, this.measuredHeight);
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\widget\pagescroll\AutoGridViewManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */