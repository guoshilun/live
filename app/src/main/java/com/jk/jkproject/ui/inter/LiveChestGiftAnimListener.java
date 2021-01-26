package com.jk.jkproject.ui.inter;

import com.jk.jkproject.ui.entity.LiveChestInfo;

public interface LiveChestGiftAnimListener extends LiveRelease {
  void addTimes(LiveChestInfo paramLiveChestInfo);
  
  String getAnimate();
  
  boolean isRunning();
  
  void setLiveGiftActionListener(LiveGiftActionListener paramLiveGiftActionListener);
  
  void startAnim(LiveChestInfo paramLiveChestInfo);
}
