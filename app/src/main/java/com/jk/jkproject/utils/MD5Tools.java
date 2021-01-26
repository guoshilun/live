package com.jk.jkproject.utils;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class MD5Tools {
  static final char[] hexDigits = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f' };
  
  private static byte[] createChecksum(String paramString) throws Exception {
    FileInputStream fileInputStream = new FileInputStream(paramString);
    byte[] arrayOfByte = new byte[1024];
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    while (true) {
      int i = fileInputStream.read(arrayOfByte);
      if (i > 0)
        messageDigest.update(arrayOfByte, 0, i); 
      if (i == -1) {
        fileInputStream.close();
        return messageDigest.digest();
      } 
    } 
  }
  
  public static String getMD5Checksum(String paramString) throws Exception {
    byte[] arrayOfByte = createChecksum(paramString);
    paramString = "";
    for (byte b = 0; b < arrayOfByte.length; b++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(Integer.toString((arrayOfByte[b] & 0xFF) + 256, 16).substring(1));
      paramString = stringBuilder.toString();
    } 
    return paramString;
  }
  
  public static final String toMD5(String paramString) {
    try {
      byte[] arrayOfByte1 = paramString.getBytes();
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = messageDigest.digest();
      int i = arrayOfByte2.length;
      char[] arrayOfChar = new char[i * 2];
      int j = 0;
      for (byte b = 0; b < i; b++) {
        byte b1 = arrayOfByte2[b];
        int k = j + 1;
        arrayOfChar[j] = (char)hexDigits[b1 >>> 4 & 0xF];
        j = k + 1;
        arrayOfChar[k] = (char)hexDigits[b1 & 0xF];
      } 
      return new String(arrayOfChar);
    } catch (Exception exception) {
      return null;
    } 
  }
}
