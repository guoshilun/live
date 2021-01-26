package com.jk.jkproject.ui.widget;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;

public class RecycleBin {
  private int[] activeViewTypes = new int[0];
  
  private View[] activeViews = new View[0];
  
  private SparseArray<View> currentScrapViews;
  
  private SparseArray<View>[] scrapViews;
  
  private int viewTypeCount;
  
  private void pruneScrapViews() {
    int i = this.activeViews.length;
    int j = this.viewTypeCount;
    SparseArray<View>[] arrayOfSparseArray = this.scrapViews;
    for (byte b = 0; b < j; b++) {
      SparseArray<View> sparseArray = arrayOfSparseArray[b];
      int k = sparseArray.size();
      int m = k - 1;
      byte b1 = 0;
      while (b1 < k - i) {
        sparseArray.remove(sparseArray.keyAt(m));
        b1++;
        m--;
      } 
    } 
  }
  
  static View retrieveFromScrap(SparseArray<View> paramSparseArray, int paramInt) {
    int i = paramSparseArray.size();
    if (i > 0) {
      for (byte b = 0; b < i; b++) {
        int j = paramSparseArray.keyAt(b);
        View view1 = (View)paramSparseArray.get(j);
        if (j == paramInt) {
          paramSparseArray.remove(j);
          return view1;
        } 
      } 
      paramInt = i - 1;
      View view = (View)paramSparseArray.valueAt(paramInt);
      paramSparseArray.remove(paramSparseArray.keyAt(paramInt));
      return view;
    } 
    return null;
  }
  
  void addScrapView(View paramView, int paramInt1, int paramInt2) {
    if (this.viewTypeCount == 1) {
      this.currentScrapViews.put(paramInt1, paramView);
    } else {
      this.scrapViews[paramInt2].put(paramInt1, paramView);
    } 
    if (Build.VERSION.SDK_INT >= 14)
      paramView.setAccessibilityDelegate(null); 
  }
  
  View getScrapView(int paramInt1, int paramInt2) {
    if (this.viewTypeCount == 1)
      return retrieveFromScrap(this.currentScrapViews, paramInt1); 
    if (paramInt2 >= 0) {
      SparseArray<View>[] arrayOfSparseArray = this.scrapViews;
      if (paramInt2 < arrayOfSparseArray.length)
        return retrieveFromScrap(arrayOfSparseArray[paramInt2], paramInt1); 
    } 
    return null;
  }
  
  void scrapActiveViews() {
    View[] arrayOfView = this.activeViews;
    int[] arrayOfInt = this.activeViewTypes;
    int i = this.viewTypeCount;
    boolean bool = true;
    if (i <= 1)
      bool = false; 
    SparseArray<View> sparseArray = this.currentScrapViews;
    i = arrayOfView.length - 1;
    while (i >= 0) {
      View view = arrayOfView[i];
      SparseArray<View> sparseArray1 = sparseArray;
      if (view != null) {
        int j = arrayOfInt[i];
        arrayOfView[i] = null;
        arrayOfInt[i] = -1;
        if (!shouldRecycleViewType(j)) {
          sparseArray1 = sparseArray;
        } else {
          if (bool)
            sparseArray = this.scrapViews[j]; 
          sparseArray.put(i, view);
          sparseArray1 = sparseArray;
          if (Build.VERSION.SDK_INT >= 14) {
            view.setAccessibilityDelegate(null);
            sparseArray1 = sparseArray;
          } 
        } 
      } 
      i--;
      sparseArray = sparseArray1;
    } 
    pruneScrapViews();
  }
  
  public void setViewTypeCount(int paramInt) {
    if (paramInt >= 1) {
      SparseArray[] arrayOfSparseArray = new SparseArray[paramInt];
      for (byte b = 0; b < paramInt; b++)
        arrayOfSparseArray[b] = new SparseArray(); 
      this.viewTypeCount = paramInt;
      this.currentScrapViews = arrayOfSparseArray[0];
      this.scrapViews = (SparseArray<View>[])arrayOfSparseArray;
      return;
    } 
    throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
  }
  
  protected boolean shouldRecycleViewType(int paramInt) {
    boolean bool;
    if (paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}

