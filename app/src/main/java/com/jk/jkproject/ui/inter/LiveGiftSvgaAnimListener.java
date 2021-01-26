package com.jk.jkproject.ui.inter;

import com.jk.jkproject.ui.entity.LiveGiftInfoBean;

public interface LiveGiftSvgaAnimListener extends LiveRelease {
  void addTimes(LiveGiftInfoBean.DataBean paramLiveGiftResponse);
  
  boolean isRunning();
  
  void startAnim(LiveGiftInfoBean.DataBean paramLiveGiftResponse);
}
