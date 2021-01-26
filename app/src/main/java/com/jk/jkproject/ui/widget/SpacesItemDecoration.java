package com.jk.jkproject.ui.widget;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
  private int space;
  
  public SpacesItemDecoration(int paramInt) {
    this.space = paramInt;
  }
  
  public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
    int i = this.space;
    paramRect.left = i;
    paramRect.right = i;
    paramRect.bottom = i;
  }
}
