package com.jk.jkproject.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ScreenUtils {
  private static float screenDensity;
  
  private static int screenH;
  
  private static int screenW;
  
  public static int dp2px(Context paramContext, float paramFloat) {
    return (int)(getScreenDensity(paramContext) * paramFloat + 0.5F);
  }
  
  public static float getScreenDensity(Context paramContext) {
    if (screenDensity == 0.0F)
      initScreen(paramContext); 
    return screenDensity;
  }
  
  public static int getScreenH(Context paramContext) {
    if (screenH == 0)
      initScreen(paramContext); 
    return screenH;
  }
  
  public static int getScreenW(Context paramContext) {
    if (screenW == 0)
      initScreen(paramContext); 
    return screenW;
  }
  
  private static void initScreen(Context paramContext) {
    DisplayMetrics displayMetrics = paramContext.getResources().getDisplayMetrics();
    screenW = displayMetrics.widthPixels;
    screenH = displayMetrics.heightPixels;
    screenDensity = displayMetrics.density;
  }
  
  public static int px2dp(Context paramContext, float paramFloat) {
    return (int)(paramFloat / getScreenDensity(paramContext) + 0.5F);
  }

  /**
   * 获取状态栏高度
   * @param context
   * @return
   */
  public static int getStatusBarHeight(Context context) {
    Resources resources = context.getResources();
    int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
    int height = resources.getDimensionPixelSize(resourceId);
    return height;
  }
}
