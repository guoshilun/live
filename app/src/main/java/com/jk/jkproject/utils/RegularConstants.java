package com.jk.jkproject.utils;

import java.util.regex.Pattern;

public class RegularConstants {
  public static final String FORMAT_EMAIL = "^\\w[\\w.-]*@[\\w.]+\\.\\w+$";
  
  public static final String FORMAT_PHONE = "^[1]+\\d{10}$";

  public static final Pattern CHINA_PATTERN = Pattern.compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");
  
  public static final String FORMAT_PWD = "^[\\da-zA-Z]{6,18}$";
  
  public static final String FORMAT_REGEX = "[\"'\\\\]";
  
  public static final String isIDCard = "^(\\d{18}|\\d{15}|\\d{17}x|X)$";
  
  public static final String isIDCard1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
  
  public static final String isIDCard2 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
}
