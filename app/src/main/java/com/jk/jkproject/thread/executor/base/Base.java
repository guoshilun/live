package com.jk.jkproject.thread.executor.base;

public abstract class Base {
  protected static final int CORE_POOL_SIZE = 5;
  
  protected static final int MAX_POOL_SIZE = 8;
  
  public void check(Object paramObject) {
    if (paramObject != null)
      return; 
    throw new NullPointerException("param must not be null!");
  }
}
