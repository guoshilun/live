package com.jk.jkproject.net.im.task;

import com.jk.jkproject.net.im.RequestPacket;

public interface ActionCallback {
  void onFaild(RequestPacket paramRequestPacket);
  
  void onSuccess(RequestPacket paramRequestPacket);
  
  void onTimeout(RequestPacket paramRequestPacket);
}