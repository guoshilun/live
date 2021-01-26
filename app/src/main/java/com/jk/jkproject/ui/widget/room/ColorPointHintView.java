package com.jk.jkproject.ui.widget.room;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import com.jk.jkproject.utils.ScreenUtils;

public class ColorPointHintView extends ShapeHintView {
  private int focusColor;
  
  private int normalColor;
  
  public ColorPointHintView(Context paramContext, int paramInt1, int paramInt2) {
    super(paramContext);
    this.focusColor = paramInt1;
    this.normalColor = paramInt2;
  }
  
  public Drawable makeFocusDrawable() {
    GradientDrawable gradientDrawable = new GradientDrawable();
    gradientDrawable.setColor(this.focusColor);
    gradientDrawable.setCornerRadius(ScreenUtils.dp2px(getContext(), 2.0F));
    gradientDrawable.setSize(ScreenUtils.dp2px(getContext(), 4.0F), ScreenUtils.dp2px(getContext(), 4.0F));
    return (Drawable)gradientDrawable;
  }
  
  public Drawable makeNormalDrawable() {
    GradientDrawable gradientDrawable = new GradientDrawable();
    gradientDrawable.setColor(this.normalColor);
    gradientDrawable.setCornerRadius(ScreenUtils.dp2px(getContext(), 2.0F));
    gradientDrawable.setSize(ScreenUtils.dp2px(getContext(), 4.0F), ScreenUtils.dp2px(getContext(), 4.0F));
    return (Drawable)gradientDrawable;
  }
}

