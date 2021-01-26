package com.jk.jkproject.net.im.cores;

import android.os.Bundle;
import java.util.Observable;
import java.util.Observer;

public class MessageNotifyCenter extends Observable {
  private static volatile MessageNotifyCenter instance = null;

  public static MessageNotifyCenter getInstance() {
    if (null == instance) {
      synchronized (MessageNotifyCenter.class) {
        if (null == instance) {
          instance = new MessageNotifyCenter();
        }
      }
    }
    return instance;
  }

  private MessageNotifyCenter() {

  }
  
  public void doNotify(Bundle paramBundle) {
    setChanged();
    notifyObservers(paramBundle);
  }
  
  public void register(Observer paramObserver) {
    addObserver(paramObserver);
  }
  
  public void unreginsers() {
    deleteObservers();
  }
  
  public void unregister(Observer paramObserver) {
    deleteObserver(paramObserver);
  }
}