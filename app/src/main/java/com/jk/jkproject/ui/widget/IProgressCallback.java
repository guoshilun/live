package com.jk.jkproject.ui.widget;

import com.jk.jkproject.ui.entity.EmoticonPkgInfo;

public interface IProgressCallback {
  void showProgress(int paramInt);
  
  void showStatus(EmoticonPkgInfo paramEmoticonPkgInfo);
}

