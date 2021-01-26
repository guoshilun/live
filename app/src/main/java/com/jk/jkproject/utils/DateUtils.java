package com.jk.jkproject.utils;

import android.text.TextUtils;

import com.jk.jkproject.ui.entity.TimeInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author zhaotun
 */
public class DateUtils {
    public static final String FORMAT_DATE_yyyyMMdd = "yyyy-MM-dd";
    public static final String FORMAT_DATE_yyyyMMdd2 = "yyyy/MM/dd";
    public static final String FORMAT_DATE_yyyyMMdd3 = "yyyyMMdd";
    public static final String FORMAT_DATE_yyMMdd = "yy/MM/dd";
    public static final String FORMAT_DATE_Md = "MM/dd";
    public static final String FORMAT_DATE_Md_2 = "MM-dd";

    public static final String FORMAT_TIME_Hm = "HH:mm";
    public static final String FORMAT_TIME_Hms = "HH:mm:ss";

    public static final String FORMAT_SHORT = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_SHORT2 = "yyyy/MM/dd HH:mm";
    public static final String FORMAT_SHORT_MdHm = "MM/dd HH:mm";
    public static final String FORMAT_SHORT_Md_Hm = "MM-dd HH:mm";

    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String FORMAT_DATE_CN = "yyyy年MM月dd日";
    public static final String FORMAT_TIME_CN = "HH时mm分ss秒";
    public static final String FORMAT_SHORT_CN = "yyyy年MM月dd日 HH时mm分";
    public static final String FORMAT_LONG_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";

    public static final String CONSTELLATION_AQUARIUS = "水瓶座";
    public static final String CONSTELLATION_PISCES = "双鱼座";
    public static final String CONSTELLATION_ARIES = "白羊座";
    public static final String CONSTELLATION_TAURUS = "金牛座";
    public static final String CONSTELLATION_GEMINI = "双子座";
    public static final String CONSTELLATION_CANCER = "巨蟹座";
    public static final String CONSTELLATION_LEO = "狮子座";
    public static final String CONSTELLATION_VIRGO = "处女座";
    public static final String CONSTELLATION_LIBRA = "天秤座";
    public static final String CONSTELLATION_SCORPIO = "天蝎座";
    public static final String CONSTELLATION_SAGITTARIUS = "射手座";
    public static final String CONSTELLATION_CAPRICORN = "摩羯座";

    private static final String[] WEEKDAY = new String[]{"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static final String[] WEEKDAY_2 = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    private static String FORMAT_DEFAULT = FORMAT_LONG_CN;

    private DateUtils() {
    }

    public static String getDatePattern() {
        return FORMAT_DEFAULT;
    }

    public static void setDatePattern(String pattern) {
        FORMAT_DEFAULT = pattern;
    }

    public static String getNow() {
        return getNow(FORMAT_DEFAULT);
    }

    public static String getNow(String pattern) {
        return getNow(pattern, Locale.CHINA);
    }

    public static String getNow(String pattern, Locale locale) {
        return format(new Date(), pattern, locale);
    }

    public static String format(Date date) {
        return format(date, FORMAT_DEFAULT);
    }

    public static String format(Date date, String pattern) {
        return format(date, pattern, Locale.CHINA);
    }

    public static String format(long milliseconds) {
        return format(new Date(milliseconds));
    }

    public static String format(long milliseconds, String pattern) {
        return format(new Date(milliseconds), pattern);
    }

    public static String format(String milliseconds, String pattern) {
        try {
            long mm = Long.parseLong(milliseconds) * 1000;
            return format(new Date(mm), pattern);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "01-01 00:00";
    }

    public static String format(long milliseconds, String pattern, Locale locale) {
        return format(new Date(milliseconds), pattern, locale);
    }

    public static String format(Date date, String pattern, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        return format.format(date);
    }

    /**
     * 判断用户的设备时区是否为东八区（中国）
     *
     * @return
     */
    public static boolean isInEastern8Zones() {
        return TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08");
    }

    /**
     * 根据不同时区，转换时间
     *
     * @param date
     * @param oldZone
     * @param newZone
     * @return
     */
    public static Date transformTime(Date date, TimeZone oldZone, TimeZone newZone) {
        Date finalDate = null;
        if (date != null) {
            int timeOffset = oldZone.getOffset(date.getTime()) - newZone.getOffset(date.getTime());
            finalDate = new Date(date.getTime() - timeOffset);
        }
        return finalDate;

    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param day  要增加的天数
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 在日期上增加秒数
     *
     * @param date   日期
     * @param second 要增加的秒数
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * date1 和 date2 之间相差的秒数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDistanceSecond(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            long dif = date1.getTime() - date2.getTime();
            return ((int) (dif / 1000));
        } else {
            return 0;
        }
    }

    /**
     * 获取一个星期 数据
     *
     * @param date 日期
     * @return
     */
    public static String[] getWeeks(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_CN, Locale.CHINA);
        String[] weeks = new String[7];
        for (int i = -6, j = 0; i <= 0; i++, j++) {
            weeks[j] = format.format(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return weeks;
    }

    /**
     * 把毫秒值转换成可读时间
     *
     * @param milliseconds : 毫秒值
     * @return
     */
    public static String formatLongToTimeStr(long milliseconds) {

        milliseconds = milliseconds / 1000;
        long day = milliseconds / (24 * 60 * 60);
        long hour = ((milliseconds % (24 * 60 * 60) / 3600)); // 小时
        long minute = (milliseconds % 3600 / 60); // 分钟
        long second = (milliseconds % 60);// 秒

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day + "天");
        }

        if (hour > 0) {
            sb.append(hour + "小时");
        }

        if (minute > 0) {
            sb.append(minute + "分钟");
        }

        if (second > 0) {
            sb.append(second + "秒");
        }

        return sb.toString();
    }

    /**
     * 根据日期，返回其是多久之前
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        long delta = System.currentTimeMillis() - date.getTime();

        long min = delta / (1000 * 60);
        if (min < 1) {
            return "刚刚";
        }

        long hour = min / 60;
        if (hour < 24) {
            return hour + "小时前";
        }

        long day = hour / 24;
        if (day < 7) {
            return day + "天前";
        }

        long week = day / 7;
        if (week < 5) {
            return week + "周前";
        }

        long month = day / 30;
        if (month < 12) {
            return month + "月前";
        }

        long year = month / 12;
        return year + "年前";
    }


    /**
     * 获取显示时间
     *
     * @param m
     * @return 今年非今天 MM-dd 去年 yyyy-MM-dd 今天 HH:mm
     */
    public static String getShowTime(long m) {
        if (m <= 0l) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_LONG, Locale.CHINA);
        String dateStr = sdf.format(m);
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(m);
        Calendar c2 = Calendar.getInstance();
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
            return dateStr.substring(0, 10);
        } else {
            if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.DATE) == c2.get(Calendar.DATE)) {
                return dateStr.substring(11, 16);
            } else {
                return dateStr.substring(5, 10);
            }
        }

    }

    /**
     * 获取显示时间
     *
     * @param m
     * @return 今年非今天 MM-dd 去年 yyyy-MM-dd 今天 HH:mm
     */
    public static String getMsgItemTime(long m) {
        if (m <= 0l) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_LONG, Locale.CHINA);
        String dateStr = sdf.format(m);
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(m);
        Calendar c2 = Calendar.getInstance();
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
            return dateStr.substring(0, 16);
        } else {
            if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.DATE) == c2.get(Calendar.DATE)) {
                return dateStr.substring(11, 16);
            } else {
                return dateStr.substring(5, 16);
            }
        }

    }

    /**
     * 根据日期对应礼拜几
     *
     * @param dateStr
     * @return
     */
    public static String getWeekDay(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_yyyyMMdd, Locale.CHINA);
        try {
            Date d = df.parse(dateStr);
            int day = d.getDay();

            return WEEKDAY[day];
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getWeekDay(long milliseconds) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(milliseconds);
        return WEEKDAY_2[c.get(Calendar.DAY_OF_WEEK) - 1];
    }


    /**
     * 根据年月日获取年龄
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return
     */
    public static int getAge(int year, int month, int day) {
        int age = 0;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) == year) {
            if (calendar.get(Calendar.MONTH) == month) {
                if (calendar.get(Calendar.DAY_OF_MONTH) >= day) {
                    age = calendar.get(Calendar.YEAR) - year + 1;
                } else {
                    age = calendar.get(Calendar.YEAR) - year;
                }
            } else if (calendar.get(Calendar.MONTH) > month) {
                age = calendar.get(Calendar.YEAR) - year + 1;
            } else {
                age = calendar.get(Calendar.YEAR) - year;
            }
        } else {
            age = calendar.get(Calendar.YEAR) - year;
//            if (calendar.get(Calendar.MONTH) < month ){
//                age -= 1 ;
//            }

        }
        if (age < 0) {
            return 0;
        }
        return age;
    }

    /**
     * 根据生日获取年龄
     *
     * @param birthday
     * @return
     */
    public static int getAge(String birthday) {
        if (TextUtils.isEmpty(birthday))
            return 0;
        if (birthday.contains("-")) {
            String[] strs = birthday.split("-");
            try {
                int y = Integer.parseInt(strs[0]);
                int m = Integer.parseInt(strs[1]) - 1;
                int d = Integer.parseInt(strs[2]);
                return getAge(y, m, d);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String getMonth(long milliseconds) {
        Calendar co = Calendar.getInstance();
        co.setTimeInMillis(milliseconds);

        Calendar c = Calendar.getInstance();

        StringBuilder sb = new StringBuilder();
        if (co.get(Calendar.YEAR) != c.get(Calendar.YEAR)) {
            sb.append(co.get(Calendar.YEAR)).append("年").append(co.get(Calendar.MONTH) + 1).append("月");
        } else {
            if (co.get(Calendar.MONTH) == c.get(Calendar.MONTH)) {
                sb.append("本月");
            } else {
                sb.append(co.get(Calendar.MONTH) + 1).append("月");
            }
        }

        c.clear();
        co.clear();

        return sb.toString();
    }

    /**
     * 根据月日获取星座
     *
     * @param month 月
     * @param day   日
     * @return
     */
    public static String getConstellation(int month, int day) {
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
            return CONSTELLATION_AQUARIUS;
        } else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
            return CONSTELLATION_PISCES;
        } else if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
            return CONSTELLATION_ARIES;
        } else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
            return CONSTELLATION_TAURUS;
        } else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
            return CONSTELLATION_GEMINI;
        } else if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) {
            return CONSTELLATION_CANCER;
        } else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
            return CONSTELLATION_LEO;
        } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
            return CONSTELLATION_VIRGO;
        } else if ((month == 9 && day >= 23) || (month == 10 && day <= 23)) {
            return CONSTELLATION_LIBRA;
        } else if ((month == 10 && day >= 24) || (month == 11 && day <= 22)) {
            return CONSTELLATION_SCORPIO;
        } else if ((month == 11 && day >= 23) || (month == 12 && day <= 21)) {
            return CONSTELLATION_SAGITTARIUS;
        } else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) {
            return CONSTELLATION_CAPRICORN;
        }
        return "";
    }

    /**
     * 根据生日获取星座
     *
     * @param birthday
     * @return
     */
    public static String getConstellation(String birthday) {
        if (TextUtils.isEmpty(birthday))
            return "";

        if (birthday.contains("-")) {
            try {
                String[] strs = birthday.split("-");
                int m = Integer.parseInt(strs[1]);
                int d = Integer.parseInt(strs[2]);

                return getConstellation(m, d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    /**
     * 根据星座id获取星座名称
     *
     * @param constellation
     * @return
     */
    public static String getConstellation(int constellation) {
        switch (constellation) {
            case 1:
                return CONSTELLATION_AQUARIUS;
            case 2:
                return CONSTELLATION_PISCES;
            case 3:
                return CONSTELLATION_ARIES;
            case 4:
                return CONSTELLATION_TAURUS;
            case 5:
                return CONSTELLATION_GEMINI;
            case 6:
                return CONSTELLATION_CANCER;
            case 7:
                return CONSTELLATION_LEO;
            case 8:
                return CONSTELLATION_VIRGO;
            case 9:
                return CONSTELLATION_LIBRA;
            case 10:
                return CONSTELLATION_SCORPIO;
            case 11:
                return CONSTELLATION_SAGITTARIUS;
            case 12:
                return CONSTELLATION_CAPRICORN;
            default:
                return CONSTELLATION_AQUARIUS;
        }
    }


    public static TimeInfo getTodayStartAndEndTime() {

        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.set(Calendar.HOUR_OF_DAY, 0);
        localCalendar1.set(Calendar.MINUTE, 0);
        localCalendar1.set(Calendar.SECOND, 0);
        localCalendar1.set(Calendar.MILLISECOND, 0);
        Date localDate1 = localCalendar1.getTime();
        long l1 = localDate1.getTime();


        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.set(Calendar.HOUR_OF_DAY, 23);
        localCalendar2.set(Calendar.MINUTE, 59);
        localCalendar2.set(Calendar.SECOND, 59);
        localCalendar2.set(Calendar.MILLISECOND, 999);
        Date localDate2 = localCalendar2.getTime();
        long l2 = localDate2.getTime();


        TimeInfo localTimeInfo = new TimeInfo();
        localTimeInfo.setStartTime(l1);
        localTimeInfo.setEndTime(l2);
        return localTimeInfo;
    }

    public static boolean isSameDay(long paramLong) {
        TimeInfo localTimeInfo = getTodayStartAndEndTime();
        return (paramLong > localTimeInfo.getStartTime()) && (paramLong < localTimeInfo.getEndTime());
//        return (paramLong > localTimeInfo.getStartTime()) && (paramLong <  localTimeInfo.getStartTime()+20000);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
