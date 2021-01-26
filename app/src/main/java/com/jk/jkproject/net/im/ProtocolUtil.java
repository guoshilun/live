package com.jk.jkproject.net.im;

public class ProtocolUtil {
  public static RequestPacket decode(String paramString, byte[] paramArrayOfbyte2) {
    return new RequestPacket(paramString, paramArrayOfbyte2);
  }
}
