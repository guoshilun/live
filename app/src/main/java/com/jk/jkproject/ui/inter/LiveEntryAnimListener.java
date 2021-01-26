package com.jk.jkproject.ui.inter;

import com.jk.jkproject.ui.entity.LiveEntryInfo;

public interface LiveEntryAnimListener extends LiveRelease {
  boolean isRunning();
  
  void setLiveEntryActionListener(LiveEntryActionListener paramLiveEntryActionListener);
  
  void startAnim(LiveEntryInfo paramLiveEntryInfo);
}

