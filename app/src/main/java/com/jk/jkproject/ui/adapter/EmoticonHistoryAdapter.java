package com.jk.jkproject.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.jk.jkproject.R;
import com.jk.jkproject.utils.ScreenUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class EmoticonHistoryAdapter extends CursorAdapter {
  private int dp28;
  
  private int dp50;
  
  private ViewHolder mHolder;
  
  private DisplayImageOptions option;
  
  public EmoticonHistoryAdapter(Context paramContext, Cursor paramCursor, boolean paramBoolean) {
    super(paramContext, paramCursor, paramBoolean);
    this.dp50 = ScreenUtils.dp2px(paramContext, 50.0F);
    this.dp28 = ScreenUtils.dp2px(paramContext, 32.0F);
  }
  
  private DisplayImageOptions getOption() {
    return (new DisplayImageOptions.Builder()).cacheInMemory(true).cacheOnDisk(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
  }
  
  private void setImage(ImageView paramImageView, String paramString) {
    if (this.option == null)
      this.option = getOption(); 
    try {
      ImageLoader.getInstance().displayImage(paramString, paramImageView, this.option);
    } catch (Throwable throwable) {
      paramImageView.setImageBitmap(null);
    } 
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor) {
    this.mHolder = (ViewHolder)paramView.getTag();
    if ("emoticon_emoji".equals(paramCursor.getString(paramCursor.getColumnIndexOrThrow("epkg_pkg_name")))) {
      ViewGroup.LayoutParams layoutParams = this.mHolder.iv.getLayoutParams();
      int i = this.dp28;
      layoutParams.width = i;
      layoutParams.height = i;
      this.mHolder.iv.setLayoutParams(layoutParams);
      String str = paramCursor.getString(paramCursor.getColumnIndexOrThrow("emo_id"));
      try {
        this.mHolder.iv.setImageResource(Integer.valueOf(str).intValue());
      } catch (Throwable throwable) {
        this.mHolder.iv.setImageBitmap(null);
      } 
    } else {
      ViewGroup.LayoutParams layoutParams = this.mHolder.iv.getLayoutParams();
      int i = this.dp50;
      layoutParams.width = i;
      layoutParams.height = i;
      this.mHolder.iv.setLayoutParams(layoutParams);
      try {
        Bitmap bitmap = BitmapFactory.decodeFile(paramCursor.getString(paramCursor.getColumnIndexOrThrow("local_url")));
        if (bitmap != null && !bitmap.isRecycled()) {
          this.mHolder.iv.setImageBitmap(bitmap);
        } else {
          String str = paramCursor.getString(paramCursor.getColumnIndex("emo_gif_url"));
          setImage(this.mHolder.iv, str);
        } 
      } catch (Throwable throwable) {
        String str = paramCursor.getString(paramCursor.getColumnIndex("emo_gif_url"));
        setImage(this.mHolder.iv, str);
      } 
    } 
  }
  
  public int getCount() {
    int i;
    Cursor cursor = getCursor();
    if (cursor == null) {
      i = 0;
    } else {
      i = cursor.getCount();
    } 
    return i;
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup) {
    this.mHolder = new ViewHolder();
    View view = View.inflate(paramContext, R.layout.emo_history_grid_item, null);
    this.mHolder.iv = (ImageView)view.findViewById(R.id.iv_emo);
    view.setTag(this.mHolder);
    return view;
  }
  
  private class ViewHolder {
    ImageView iv;
    
    private ViewHolder() {}
  }
}
