package com.jk.jkproject.ui.inter;

import com.jk.jkproject.ui.entity.LiveEntryInfo;

public interface LiveEntryActionListener {
  void addEntry(LiveEntryInfo paramLiveEntryInfo);
  
  void pollEntry();
}
