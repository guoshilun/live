package com.jk.jkproject.utils;

public class LiveRoleUtils {
  public static final int ROLE_CHARGE_LG = 1024;
  
  public static final int ROLE_CHARGE_MD = 512;
  
  public static final int ROLE_CHARGE_SM = 256;
  
  public static final int ROLE_POTENTIAL = 128;
  
  public static final int anchor = 1;
  
  public static final int anonymous = 4;
  
  public static final int girlfriend = 8;
  
  public static final int guard = 2;
  
  public static final int marriage = 16;
  
  public static final int ordinary = 0;
  
  public static String getLevelDay(int paramInt) {
    String str;
    if (isCharge_lg(paramInt)) {
      str = "1007";
    } else if (isCharge_md(paramInt)) {
      str = "1005";
    } else if (isCharge_sm(paramInt)) {
      str = "1006";
    } else if (isDayPayRole(paramInt)) {
      str = "1004";
    } else {
      str = "";
    } 
    return str;
  }
  
  public static boolean isAnchorRole(int paramInt) {
    boolean bool;
    if ((paramInt & 0x1) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isAnonymousRole(int paramInt) {
    boolean bool;
    if ((paramInt & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isCharge_lg(int paramInt) {
    boolean bool;
    if ((paramInt & 0x400) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isCharge_md(int paramInt) {
    boolean bool;
    if ((paramInt & 0x200) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isCharge_sm(int paramInt) {
    boolean bool;
    if ((paramInt & 0x100) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isDayPayRole(int paramInt) {
    boolean bool;
    if ((paramInt & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isGirlFriendRole(int paramInt) {
    boolean bool;
    if ((paramInt & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isGuardRole(int paramInt) {
    boolean bool;
    if ((paramInt & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isMarriageRole(int paramInt) {
    boolean bool;
    if ((paramInt & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}
