package com.jk.jkproject.ui.widget.danmaku;

import android.content.Context;

public interface DanmakuClickListener {
  void onDanmakuClick(Context paramContext, DanmakuEntity paramDanmakuEntity);
  
  void onDanmuIconClick(String paramString1, String paramString2, int paramInt);
}
