package com.jk.jkproject.utils.emoji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

public class LHImageSpan extends ImageSpan {
  private Context mContext;
  
  private Drawable mDrawable;
  
  private int mSize;
  
  public LHImageSpan(Context paramContext, Bitmap paramBitmap, int paramInt) {
    super(paramContext, paramBitmap);
    BitmapDrawable bitmapDrawable;
    this.mContext = paramContext;
    this.mSize = paramInt;
    if (paramContext != null) {
      bitmapDrawable = new BitmapDrawable(paramContext.getResources(), paramBitmap);
    } else {
      bitmapDrawable = new BitmapDrawable(paramBitmap);
    } 
    this.mDrawable = (Drawable)bitmapDrawable;
    paramInt = this.mSize;
    if (paramInt > 0) {
      this.mDrawable.setBounds(0, 0, paramInt, paramInt);
    } else {
      Drawable drawable = this.mDrawable;
      drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
    } 
  }
  
  public void draw(Canvas paramCanvas, CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, Paint paramPaint) {
    Drawable drawable = getDrawable();
    paramCanvas.save();
    paramCanvas.translate(paramFloat, ((paramInt5 - paramInt3 - (drawable.getBounds()).bottom) / 2 + paramInt3));
    drawable.draw(paramCanvas);
    paramCanvas.restore();
  }
  
  public Drawable getDrawable() {
    return this.mDrawable;
  }
  
  public int getSize(Paint paramPaint, CharSequence paramCharSequence, int paramInt1, int paramInt2, Paint.FontMetricsInt paramFontMetricsInt) {
    Rect rect = getDrawable().getBounds();
    if (paramFontMetricsInt != null) {
      Paint.FontMetricsInt fontMetricsInt = paramPaint.getFontMetricsInt();
      paramInt2 = fontMetricsInt.bottom - fontMetricsInt.top;
      int i = rect.bottom - rect.top;
      paramInt1 = i / 2 - paramInt2 / 4;
      paramInt2 = i / 2 + paramInt2 / 4;
      paramFontMetricsInt.ascent = -paramInt2;
      paramFontMetricsInt.top = -paramInt2;
      paramFontMetricsInt.bottom = paramInt1;
      paramFontMetricsInt.descent = paramInt1;
    } 
    return rect.right;
  }
}
