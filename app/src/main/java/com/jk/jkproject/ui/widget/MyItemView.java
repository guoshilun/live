package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;

public class MyItemView extends RelativeLayout {
  private ImageView ivIcon;
  
  private TextView tvDes;
  
  public MyItemView(Context paramContext) {
    super(paramContext);
    init(paramContext);
  }
  
  public MyItemView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public MyItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext) {
    LayoutInflater.from(paramContext).inflate(R.layout.view_my_item, (ViewGroup)this, true);
    this.ivIcon = (ImageView)findViewById(R.id.iv_icon);
    this.tvDes = (TextView)findViewById(R.id.tv_des);
  }
  
  public void setDes(int paramInt) {
    TextView textView = this.tvDes;
    if (textView != null)
      textView.setText(paramInt); 
  }
  
  public void setDes(CharSequence paramCharSequence) {
    TextView textView = this.tvDes;
    if (textView != null)
      textView.setText(paramCharSequence); 
  }
  
  public void setDesTextColor(int paramInt) {
    TextView textView = this.tvDes;
    if (textView != null)
      textView.setTextColor(paramInt); 
  }
  
  public void setIconResId(int paramInt) {
    ImageView imageView = this.ivIcon;
    if (imageView != null)
      imageView.setImageResource(paramInt); 
  }
  
  public void setPress(boolean paramBoolean) {
    ImageView imageView = this.ivIcon;
    if (imageView != null)
      imageView.setPressed(paramBoolean); 
    TextView textView = this.tvDes;
    if (textView != null)
      textView.setPressed(paramBoolean); 
  }
  
  public void setSelect(boolean paramBoolean) {
    ImageView imageView = this.ivIcon;
    if (imageView != null)
      imageView.setSelected(paramBoolean); 
    TextView textView = this.tvDes;
    if (textView != null)
      textView.setSelected(paramBoolean); 
  }
}
