package com.jk.jkproject.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class StringUtils {
    public static String replaceUrlWithPlus(String paramString) {
        return (paramString != null) ? paramString.replaceAll("http://(.)*?/", "").replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+") : null;
    }

    public static String stringToJson(String paramString1, String paramString2) {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put(paramString1, paramString2);
        return GsonUtils.get().toJson(hashMap);
    }

    public static String stringToJson(String paramString1, String paramString2, String paramString3, String paramString4) {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put(paramString1, paramString2);
        hashMap.put(paramString3, paramString4);
        return GsonUtils.get().toJson(hashMap);
    }

    public static String stringToJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt) {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put(paramString1, paramString2);
        hashMap.put(paramString3, paramString4);
        hashMap.put(paramString5, Integer.valueOf(paramInt));
        return GsonUtils.get().toJson(hashMap);
    }

    public static String stringToJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14) {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put(paramString1, paramString2);
        hashMap.put(paramString3, paramString4);
        hashMap.put(paramString5, paramString6);
        hashMap.put(paramString7, paramString8);
        hashMap.put(paramString9, paramString10);
        hashMap.put(paramString11, paramString12);
        hashMap.put(paramString13, paramString14);
        return GsonUtils.get().toJson(hashMap);
    }

    /**
     * 17537289384
     * 转化
     * 175****3748
     */
    public static String strToNum(String number) {
        if (number.isEmpty() || number.length() < 11) {
            return "";
        }
        String num;
        if (number.contains("+86")) {
            num = number.substring(4);
        } else {
            num = number;
        }
        String repNum = num.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        LogUtils.e("=====", number + "====" + repNum);
        return repNum;
    }

    /**
     * 身份证
     * 转化
     * 175****3748
     */
    public static String strToIdCard(String idCard) {
        if (idCard.isEmpty() || idCard.length() <= 18) {
            return "";
        }
        return idCard.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2");
    }

    public static boolean validate(String paramString) {
        boolean bool;
        if (paramString != null && !TextUtils.isEmpty(paramString)) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    /**
     * 字符串转换为Ascii
     *
     * @param value
     * @return
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]);
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    /**
     * solr检索时，转换特殊字符
     *
     * @param s 需要转义的字符串
     * @return 返回转义后的字符串
     */
    public static String escapeQueryChars(String s) {
        if (s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        //查询字符串一般不会太长，挨个遍历也花费不了多少时间
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            switch (c) {
                case '+':
                    sb.append("%2B");
                    break;
                case ' ':
                    sb.append("%20");
                    break;
                case '/':
                    sb.append("%2F");
                    break;
                case '?':
                    sb.append("%3F");
                    break;
                case '%':
                    sb.append("%25");
                    break;
                case '#':
                    sb.append("%23");
                    break;
                case '&':
                    sb.append("%26");
                    break;
                case '=':
                    sb.append("%3D");
                    break;
                case '!':
                    sb.append("%21");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }


    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (!keyword.isEmpty()) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    public static String getDistance(String distance) {
        if (distance == null || distance.isEmpty()) {
            return "";
        }
        if (distance.contains("火星")) {
            return "火星";
        }
        int dis = Integer.parseInt(distance);
        if (dis < 1000) {
            return dis + "米";
        } else if (dis <= 9999) {
            return (dis / 1000) + "公里";
        } else {
            return "火星";
        }
    }

    /**
     * 数字转换
     * 1000 = 1K
     * 10000 = 10K
     *
     * @param path 输入数字
     * @return 输出数字
     */
    public static String ReadSize(int data) {
        String size = "";
        BigDecimal fileSize = new BigDecimal(data);
        if (data < 10000) {
            return data + "";
        } else if (data < 1000000) {
            size = fileSize.divide(new BigDecimal(10000), 2, RoundingMode.HALF_UP) + "万";
        } else if (data < 10000000) {
            size = fileSize.divide(new BigDecimal(1000000), 0, RoundingMode.HALF_UP) + "百万";
        } else if (data < 100000000) {
            size = fileSize.divide(new BigDecimal(10000000), 2, RoundingMode.HALF_UP) + "千万";
        } else {
            size = fileSize.divide(new BigDecimal(10000000), 0, RoundingMode.HALF_UP) + "千万";
        }
        return size;
    }
}