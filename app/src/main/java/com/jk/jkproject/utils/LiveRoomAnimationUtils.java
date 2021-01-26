package com.jk.jkproject.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class LiveRoomAnimationUtils {
  private void init(Context paramContext, final View paramView, int paramInt1, long paramLong, final int visiable, final AnimationCallBack callBack) {
    Animation animation = AnimationUtils.loadAnimation(paramContext, paramInt1);
    animation.setDuration(paramLong);
    animation.setAnimationListener(new Animation.AnimationListener() {
          public void onAnimationEnd(Animation param1Animation) {
            paramView.setVisibility(visiable);
            LiveRoomAnimationUtils.AnimationCallBack animationCallBack = callBack;
            if (animationCallBack != null)
              animationCallBack.onAimed(); 
          }
          
          public void onAnimationRepeat(Animation param1Animation) {}
          
          public void onAnimationStart(Animation param1Animation) {}
        });
    paramView.startAnimation(animation);
  }
  
  public void startAnimtion(Context paramContext, View paramView, int paramInt1, long paramLong, int paramInt2) {
    init(paramContext, paramView, paramInt1, paramLong, paramInt2, null);
  }
  
  public void startAnimtion(Context paramContext, View paramView, int paramInt1, long paramLong, int paramInt2, AnimationCallBack paramAnimationCallBack) {
    init(paramContext, paramView, paramInt1, paramLong, paramInt2, paramAnimationCallBack);
  }
  
  public static interface AnimationCallBack {
    void onAimed();
  }
}