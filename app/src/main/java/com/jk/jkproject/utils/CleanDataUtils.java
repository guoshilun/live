package com.jk.jkproject.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.math.BigDecimal;

public class CleanDataUtils {
  public static void clearAllCache(Context paramContext) {
    deleteDir(paramContext.getCacheDir());
    if (Environment.getExternalStorageState().equals("mounted")) {
      deleteDir(paramContext.getExternalCacheDir());
      paramContext.deleteDatabase("webview.db");
      paramContext.deleteDatabase("webviewCache.db");
    } 
  }
  
  private static boolean deleteDir(File paramFile) {
    if (paramFile != null && paramFile.isDirectory()) {
      String[] arrayOfString = paramFile.list();
      for (byte b = 0; b < arrayOfString.length; b++) {
        if (!deleteDir(new File(paramFile, arrayOfString[b])))
          return false; 
      } 
      return paramFile.delete();
    } 
    return (paramFile != null) ? paramFile.delete() : false;
  }
  
  public static long getFolderSize(File paramFile) {
    long l1 = 0L;
    long l2 = l1;
    if (paramFile != null) {
      File[] arrayOfFile = paramFile.listFiles();
      l2 = l1;
      if (arrayOfFile != null) {
        l2 = l1;
        if (arrayOfFile.length > 0) {
          byte b = 0;
          while (true) {
            l2 = l1;
            if (b < arrayOfFile.length) {
              if (arrayOfFile[b].isDirectory()) {
                l1 += getFolderSize(arrayOfFile[b]);
              } else {
                l1 += arrayOfFile[b].length();
              } 
              b++;
              continue;
            } 
            break;
          } 
        } 
      } 
    } 
    return l2;
  }
  
  public static String getFormatSize(double paramDouble) {
    double d = paramDouble / 1024.0D / 1024.0D;
    paramDouble = d / 1024.0D;
    if (paramDouble < 1.0D) {
      BigDecimal bigDecimal1 = new BigDecimal(Double.toString(d));
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(bigDecimal1.setScale(2, 4).toPlainString());
      stringBuilder1.append("MB");
      return stringBuilder1.toString();
    } 
    d = paramDouble / 1024.0D;
    if (d < 1.0D) {
      BigDecimal bigDecimal1 = new BigDecimal(Double.toString(paramDouble));
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(bigDecimal1.setScale(2, 4).toPlainString());
      stringBuilder1.append("GB");
      return stringBuilder1.toString();
    } 
    BigDecimal bigDecimal = BigDecimal.valueOf(d);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(bigDecimal.setScale(2, 4).toPlainString());
    stringBuilder.append("TB");
    return stringBuilder.toString();
  }
  
  public static String getTotalCacheSize(Context paramContext) {
    long l1 = getFolderSize(paramContext.getCacheDir());
    long l2 = l1;
    if (Environment.getExternalStorageState().equals("mounted"))
      l2 = l1 + getFolderSize(paramContext.getExternalCacheDir()); 
    return getFormatSize(l2);
  }
}
