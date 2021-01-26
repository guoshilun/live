package com.jk.jkproject.ui.widget.danmaku;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import com.jk.jkproject.utils.ScreenUtils;

public class AnimationHelper {
  public static ObjectAnimator createObjectAnim(Context paramContext, View paramView, int paramInt1, int paramInt2) {
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(paramView, "translationX", new float[] { paramInt1, paramInt2 });
    long l = (long)(Math.abs(paramInt2 - paramInt1) * 1.0F / ScreenUtils.getScreenW(paramContext) * 4000.0F);
    objectAnimator.setInterpolator((TimeInterpolator)new LinearInterpolator());
    objectAnimator.setDuration(l);
    return objectAnimator;
  }
  
  public static Animation createTranslateAnim(Context paramContext, int paramInt1, int paramInt2) {
    TranslateAnimation translateAnimation = new TranslateAnimation(paramInt1, paramInt2, 0.0F, 0.0F);
    translateAnimation.setDuration(10000L);
    translateAnimation.setFillAfter(true);
    return (Animation)translateAnimation;
  }
}