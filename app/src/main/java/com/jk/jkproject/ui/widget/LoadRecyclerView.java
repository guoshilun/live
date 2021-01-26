package com.jk.jkproject.ui.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

public class LoadRecyclerView extends RecyclerView {
  private RecyclerViewScrollListener rvScrollListener;
  
  public LoadRecyclerView(Context paramContext) {
    this(paramContext, null);
  }
  
  public LoadRecyclerView(Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LoadRecyclerView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void init() {
    this.rvScrollListener = new RecyclerViewScrollListener();
    addOnScrollListener(this.rvScrollListener);
  }
  
  public void setLoading(boolean paramBoolean) {
    this.rvScrollListener.setLoadingMore(paramBoolean);
  }
  
  public void setOnLoadListener(RecyclerViewScrollListener.OnLoadListener paramOnLoadListener) {
    RecyclerViewScrollListener recyclerViewScrollListener = this.rvScrollListener;
    if (recyclerViewScrollListener != null)
      recyclerViewScrollListener.setOnLoadListener(paramOnLoadListener); 
  }
}
