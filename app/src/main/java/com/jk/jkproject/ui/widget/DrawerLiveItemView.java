package com.jk.jkproject.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;

public class DrawerLiveItemView extends RelativeLayout {
  private ImageView ivIcon;
  
  private ImageView ivShare;
  
  private TextView tvDes;
  
  private TextView tvName;
  
  public DrawerLiveItemView(Context paramContext) {
    super(paramContext);
    init(paramContext);
  }
  
  public DrawerLiveItemView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public DrawerLiveItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext) {
    LayoutInflater.from(paramContext).inflate(R.layout.view_drawer_item_live, (ViewGroup)this, true);
    this.ivIcon = (ImageView)findViewById(R.id.iv_icon);
    this.tvName = (TextView)findViewById(R.id.tv_name);
    this.tvDes = (TextView)findViewById(R.id.tv_desc);
    this.ivShare = (ImageView)findViewById(R.id.img_share);
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
  
  public DrawerLiveItemView setInVisib(int paramInt) {
    ((ImageView)findViewById(paramInt)).setVisibility(INVISIBLE);
    return this;
  }
  
  public void setIvShareVisiable() {
    ImageView imageView = this.ivShare;
    if (imageView != null)
      imageView.setVisibility(VISIBLE);
  }
  
  public void setName(int paramInt) {
    TextView textView = this.tvName;
    if (textView != null)
      textView.setText(paramInt); 
  }
  
  public void setName(CharSequence paramCharSequence) {
    TextView textView = this.tvName;
    if (textView != null)
      textView.setText(paramCharSequence); 
  }
  
  public void setNameTextColor(int paramInt) {
    TextView textView = this.tvName;
    if (textView != null)
      textView.setTextColor(paramInt); 
  }
  
  public void setPress(boolean paramBoolean) {
    ImageView imageView = this.ivIcon;
    if (imageView != null)
      imageView.setPressed(paramBoolean); 
    TextView textView = this.tvName;
    if (textView != null)
      textView.setPressed(paramBoolean); 
    textView = this.tvDes;
    if (textView != null)
      textView.setPressed(paramBoolean); 
  }
  
  public void setSelect(boolean paramBoolean) {
    ImageView imageView = this.ivIcon;
    if (imageView != null)
      imageView.setSelected(paramBoolean); 
    TextView textView = this.tvName;
    if (textView != null)
      textView.setSelected(paramBoolean); 
    textView = this.tvDes;
    if (textView != null)
      textView.setSelected(paramBoolean); 
  }
  
  public void setShareResId(int paramInt) {
    ImageView imageView = this.ivShare;
    if (imageView != null) {
      imageView.setVisibility(VISIBLE);
      this.ivShare.setImageResource(paramInt);
    } 
  }
  
  public DrawerLiveItemView setVisib(int paramInt) {
    ((TextView)findViewById(paramInt)).setVisibility(GONE);
    return this;
  }
}
