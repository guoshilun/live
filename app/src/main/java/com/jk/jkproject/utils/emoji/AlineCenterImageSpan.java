package com.jk.jkproject.utils.emoji;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

public class AlineCenterImageSpan extends ImageSpan {
  private Context mContext;
  
  private Drawable mDrawable;
  
  private int mResourceId;
  
  private int mSize;
  
  public AlineCenterImageSpan(Context paramContext, int paramInt) {
    super(paramContext, paramInt);
    this.mContext = paramContext;
    this.mResourceId = paramInt;
  }
  
  public AlineCenterImageSpan(Context paramContext, int paramInt1, int paramInt2) {
    super(paramContext, paramInt1);
    this.mContext = paramContext;
    this.mResourceId = paramInt1;
    this.mSize = paramInt2;
  }
  
  public void draw(Canvas paramCanvas, CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, Paint paramPaint) {
    Drawable drawable = getDrawable();
    paramCanvas.save();
    paramCanvas.translate(paramFloat, ((paramInt5 - paramInt3 - (drawable.getBounds()).bottom) / 2 + paramInt3));
    drawable.draw(paramCanvas);
    paramCanvas.restore();
  }
  
  public Drawable getDrawable() {
    if (this.mDrawable == null)
      try {
        this.mDrawable = this.mContext.getResources().getDrawable(this.mResourceId);
        if (this.mSize == 0) {
          this.mDrawable.setBounds(5, 0, this.mDrawable.getIntrinsicWidth() + 5, this.mDrawable.getIntrinsicHeight());
        } else {
          int i = this.mSize;
          this.mDrawable.setBounds(0, 0, i, i);
        } 
      } catch (Exception exception) {} 
    return this.mDrawable;
  }
  
  public int getSize(Paint paramPaint, CharSequence paramCharSequence, int paramInt1, int paramInt2, Paint.FontMetricsInt paramFontMetricsInt) {
    Rect rect = getDrawable().getBounds();
    if (paramFontMetricsInt != null) {
      Paint.FontMetricsInt fontMetricsInt = paramPaint.getFontMetricsInt();
      int i = fontMetricsInt.bottom - fontMetricsInt.top;
      paramInt2 = rect.bottom - rect.top;
      paramInt1 = paramInt2 / 2 - i / 4;
      paramInt2 = paramInt2 / 2 + i / 4;
      paramFontMetricsInt.ascent = -paramInt2;
      paramFontMetricsInt.top = -paramInt2;
      paramFontMetricsInt.bottom = paramInt1;
      paramFontMetricsInt.descent = paramInt1;
    } 
    return rect.right;
  }
  
  public void release() {
    this.mContext = null;
    this.mDrawable = null;
  }
}

