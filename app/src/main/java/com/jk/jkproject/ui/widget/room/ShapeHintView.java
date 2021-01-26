package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.jk.jkproject.ui.inter.HintView;

public abstract class ShapeHintView extends LinearLayout implements HintView {
  private Drawable dot_focus;
  
  private Drawable dot_normal;
  
  private int lastPosition = 0;
  
  private int length = 0;
  
  private ImageView[] mDots;
  
  public ShapeHintView(Context paramContext) {
    super(paramContext);
  }
  
  public ShapeHintView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public void initView(int paramInt1, int paramInt2) {
    removeAllViews();
    this.lastPosition = 0;
    setOrientation(0);
    switch (paramInt2) {
      case 2:
        setGravity(21);
        break;
      case 1:
        setGravity(17);
        break;
      case 0:
        setGravity(19);
        break;
    } 
    this.length = paramInt1;
    this.mDots = new ImageView[paramInt1];
    this.dot_focus = makeFocusDrawable();
    this.dot_normal = makeNormalDrawable();
    for (paramInt2 = 0; paramInt2 < paramInt1; paramInt2++) {
      this.mDots[paramInt2] = new ImageView(getContext());
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
      layoutParams.setMargins(10, 0, 10, 0);
      this.mDots[paramInt2].setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      this.mDots[paramInt2].setBackgroundDrawable(this.dot_normal);
      addView((View)this.mDots[paramInt2]);
    } 
    setCurrent(0);
  }
  
  public abstract Drawable makeFocusDrawable();
  
  public abstract Drawable makeNormalDrawable();
  
  public void setCurrent(int paramInt) {
    if (paramInt < 0 || paramInt > this.length - 1)
      return; 
    this.mDots[this.lastPosition].setBackgroundDrawable(this.dot_normal);
    this.mDots[paramInt].setBackgroundDrawable(this.dot_focus);
    this.lastPosition = paramInt;
  }
}


/* Location:              E:\BaiduNetdiskDownload\111\afby\jar\classes2-dex2jar.jar!\com\jk\jkprojec\\ui\widget\room\ShapeHintView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */