package com.jk.jkproject.utils;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.jk.jkproject.base.AppApplication;

public class AppUtils {
  public static String getAppMetaData() {
    String str2;
    String str1 = "gf";
    try {
      PackageManager packageManager = AppApplication.getInstance().getPackageManager();
      str2 = str1;
      if (packageManager != null) {
        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(AppApplication.getInstance().getPackageName(), 128);
        str2 = str1;
        if (applicationInfo != null) {
          str2 = str1;
          if (applicationInfo.metaData != null)
            str2 = applicationInfo.metaData.getString("UMENG_CHANNEL"); 
        } 
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.printStackTrace();
      str2 = str1;
    } 
    return str2;
  }
  
  public static String getAppName() {
    try {
      int i = (AppApplication.getInstance().getPackageManager().getPackageInfo(AppApplication.getInstance().getPackageName(), 0)).applicationInfo.labelRes;
      return AppApplication.getInstance().getResources().getString(i);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.printStackTrace();
      return null;
    } 
  }
  
  @SuppressLint({"NewApi"})
  public static Drawable getIcon() {
    try {
      int i = (AppApplication.getInstance().getPackageManager().getPackageInfo(AppApplication.getInstance().getPackageName(), 0)).applicationInfo.icon;
      return AppApplication.getInstance().getResources().getDrawable(i);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.printStackTrace();
      return null;
    } 
  }
  
  public static String getPackageName() {
    return AppApplication.getInstance().getPackageName();
  }
  
  public static int getVersionCode() {
    try {
      return (AppApplication.getInstance().getPackageManager().getPackageInfo(AppApplication.getInstance().getPackageName(), 0)).versionCode;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.printStackTrace();
      return 1;
    } 
  }
  
  public static String getVersionName() {
    try {
      return (AppApplication.getInstance().getPackageManager().getPackageInfo(AppApplication.getInstance().getPackageName(), 0)).versionName;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.printStackTrace();
      return null;
    } 
  }
}
