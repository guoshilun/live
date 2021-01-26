package com.jk.jkproject.net.im.manager;

import android.content.Context;

public abstract class ChujianManager {
  protected Context ctx;
  
  public abstract void reset();
  
  public void setContext(Context paramContext) {
    if (paramContext != null) {
      this.ctx = paramContext;
      return;
    } 
    throw new RuntimeException("context is null");
  }
}
