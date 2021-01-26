package com.jk.jkproject.ui.holder;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.jk.jkproject.ui.inter.OnItemClickLitener;

public class NoLineClickSpan extends ClickableSpan {
  private OnItemClickLitener listener;
  
  public NoLineClickSpan(OnItemClickLitener paramOnItemClickLitener) {
    this.listener = paramOnItemClickLitener;
  }
  
  public void onClick(View paramView) {}
  
  public void release() {
    this.listener = null;
  }
  
  public void updateDrawState(TextPaint paramTextPaint) {
    paramTextPaint.setUnderlineText(false);
  }
}