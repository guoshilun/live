package com.jk.jkproject.utils;

public class OnClickUtils {
  // 两次点击按钮之间的点击间隔不能少于1000毫秒
  private static final int MIN_CLICK_DELAY_TIME = 1000;
  private static long lastClickTime;

  //返回为true 说明可以点击 返回false 说明是连续点击
  public static boolean isFastClick() {
    boolean flag = false;
    long curClickTime = System.currentTimeMillis();
    if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
      flag = true;
    }
    lastClickTime = curClickTime;
    return flag;
  }

  //返回为true 说明可以点击 返回false 说明是连续点击 不需要提示
  public static boolean isFastClickNotToast() {
    boolean flag = false;
    long curClickTime = System.currentTimeMillis();
    if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
      flag = true;
    }
    lastClickTime = curClickTime;
    return flag;
  }
}
