package com.jk.jkproject.ui.widget.pagescroll;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

public class HorizontalPageLayoutManager extends RecyclerView.LayoutManager implements PageDecorationLastJudge {
  private SparseArray<Rect> allItemFrames = new SparseArray();
  
  int columns = 0;
  
  int itemHeight = 0;
  
  int itemHeightUsed;
  
  int itemWidth = 0;
  
  int itemWidthUsed;
  
  int offsetX = 0;
  
  int offsetY = 0;
  
  int onePageSize = 0;
  
  int pageSize = 0;
  
  int rows = 0;
  
  int totalHeight = 0;
  
  int totalWidth = 0;
  
  public HorizontalPageLayoutManager(int paramInt1, int paramInt2) {
    this.rows = paramInt1;
    this.columns = paramInt2;
    this.onePageSize = paramInt1 * paramInt2;
  }
  
  private void computePageSize(RecyclerView.State paramState) {
    byte b;
    int i = paramState.getItemCount() / this.onePageSize;
    if (paramState.getItemCount() % this.onePageSize == 0) {
      b = 0;
    } else {
      b = 1;
    } 
    this.pageSize = i + b;
  }
  
  private int getUsableHeight() {
    return getHeight() - getPaddingTop() - getPaddingBottom();
  }
  
  private int getUsableWidth() {
    return getWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private void recycleAndFillItems(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    if (paramState.isPreLayout())
      return; 
    Rect rect1 = new Rect(getPaddingLeft() + this.offsetX, getPaddingTop(), getWidth() - getPaddingLeft() - getPaddingRight() + this.offsetX, getHeight() - getPaddingTop() - getPaddingBottom());
    Rect rect2 = new Rect();
    byte b;
    for (b = 0; b < getChildCount(); b++) {
      View view = getChildAt(b);
      rect2.left = getDecoratedLeft(view);
      rect2.top = getDecoratedTop(view);
      rect2.right = getDecoratedRight(view);
      rect2.bottom = getDecoratedBottom(view);
      if (!Rect.intersects(rect1, rect2))
        removeAndRecycleView(view, paramRecycler); 
    } 
    for (b = 0; b < getItemCount(); b++) {
      if (Rect.intersects(rect1, (Rect)this.allItemFrames.get(b))) {
        View view = paramRecycler.getViewForPosition(b);
        addView(view);
        measureChildWithMargins(view, this.itemWidthUsed, this.itemHeightUsed);
        rect2 = (Rect)this.allItemFrames.get(b);
        layoutDecorated(view, rect2.left - this.offsetX, rect2.top, rect2.right - this.offsetX, rect2.bottom);
      } 
    } 
  }
  
  public boolean canScrollHorizontally() {
    return true;
  }
  
  public int computeHorizontalScrollExtent(RecyclerView.State paramState) {
    return getWidth();
  }
  
  public int computeHorizontalScrollOffset(RecyclerView.State paramState) {
    return this.offsetX;
  }
  
  public int computeHorizontalScrollRange(RecyclerView.State paramState) {
    computePageSize(paramState);
    return this.pageSize * getWidth();
  }
  
  public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return null;
  }
  
  public boolean isLastColumn(int paramInt) {
    return (paramInt >= 0 && paramInt < getItemCount() && (paramInt + 1) % this.columns == 0);
  }
  
  public boolean isLastRow(int paramInt) {
    if (paramInt >= 0 && paramInt < getItemCount()) {
      int i = this.onePageSize;
      paramInt = paramInt % i + 1;
      if (paramInt > (this.rows - 1) * this.columns && paramInt <= i)
        return true; 
    } 
    return false;
  }
  
  public boolean isPageLast(int paramInt) {
    boolean bool = true;
    if ((paramInt + 1) % this.onePageSize != 0)
      bool = false; 
    return bool;
  }
  
  public void onDetachedFromWindow(RecyclerView paramRecyclerView, RecyclerView.Recycler paramRecycler) {
    super.onDetachedFromWindow(paramRecyclerView, paramRecycler);
    this.offsetX = 0;
    this.offsetY = 0;
  }
  
  public void onLayoutChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    if (getItemCount() == 0) {
      removeAndRecycleAllViews(paramRecycler);
      return;
    } 
    if (paramState.isPreLayout())
      return; 
    this.itemWidth = getUsableWidth() / this.columns;
    int i = getUsableHeight();
    int j = this.rows;
    this.itemHeight = i / j;
    this.itemWidthUsed = (this.columns - 1) * this.itemWidth;
    this.itemHeightUsed = (j - 1) * this.itemHeight;
    computePageSize(paramState);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("itemCount=");
    stringBuilder.append(getItemCount());
    stringBuilder.append(" state itemCount=");
    stringBuilder.append(paramState.getItemCount());
    stringBuilder.append(" pageSize=");
    stringBuilder.append(this.pageSize);
    Log.i("zzz", stringBuilder.toString());
    this.totalWidth = (this.pageSize - 1) * getWidth();
    detachAndScrapAttachedViews(paramRecycler);
    int k = getItemCount();
    for (i = 0; i < this.pageSize; i++) {
      j = 0;
      while (j < this.rows) {
        int m;
        int n;
        byte b = 0;
        while (true) {
          int i1 = this.columns;
          m = i;
          n = j;
          if (b < i1) {
            int i2 = this.onePageSize * i + i1 * j + b;
            if (i2 == k) {
              j = this.columns;
              n = this.rows;
              m = this.pageSize;
              break;
            } 
            View view = paramRecycler.getViewForPosition(i2);
            addView(view);
            measureChildWithMargins(view, this.itemWidthUsed, this.itemHeightUsed);
            int i3 = getDecoratedMeasuredWidth(view);
            m = getDecoratedMeasuredHeight(view);
            Rect rect2 = (Rect)this.allItemFrames.get(i2);
            Rect rect1 = rect2;
            if (rect2 == null)
              rect1 = new Rect(); 
            i1 = getUsableWidth() * i + this.itemWidth * b;
            n = this.itemHeight * j;
            rect1.set(i1, n, i3 + i1, m + n);
            this.allItemFrames.put(i2, rect1);
            b++;
            continue;
          } 
          break;
        } 
        j = n + 1;
        i = m;
      } 
      removeAndRecycleAllViews(paramRecycler);
    } 
    recycleAndFillItems(paramRecycler, paramState);
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState) {
    detachAndScrapAttachedViews(paramRecycler);
    int i = this.offsetX;
    int j = i + paramInt;
    int k = this.totalWidth;
    if (j > k) {
      paramInt = k - i;
    } else if (j < 0) {
      paramInt = 0 - i;
    } 
    this.offsetX += paramInt;
    offsetChildrenHorizontal(-paramInt);
    recycleAndFillItems(paramRecycler, paramState);
    return paramInt;
  }
}