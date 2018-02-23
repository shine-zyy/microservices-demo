package com.zyy.springcloud.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String PERIOD_UNIT_MINUTE = "m";
    public static final String PERIOD_UNIT_HOUR = "h";
    public static final String PERIOD_UNIT_DAY = "d";
    public static final String PERIOD_UNIT_MONTH = "M";

    /**
     * 将指定字符串转换成日期
     *
     * @param dateStr     String 日期字符串
     * @param datePattern String 日期格式
     * @return Date
     */
    public static Date getDate(String dateStr, String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将指定日期对象转换成格式化字符串
     *
     * @param date        Date XML日期对象
     * @param datePattern String 日期格式
     * @return String
     */
    public static String getDateStr(Date date, String datePattern) {
        SimpleDateFormat sd = new SimpleDateFormat(datePattern);
        return sd.format(date);
    }

    public static String getCurrentYear() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        return strYear;
    }

    // 获取当天时间
    public static java.sql.Timestamp getCurrentTimeStamp(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
        String dateString = dateFormat.format(now);
        SimpleDateFormat sd = new SimpleDateFormat(dateformat);
        Date dateFormt = sd.parse(dateString, new java.text.ParsePosition(0));
        java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt.getTime());
        return dateTime;
    }

    // 获取指定时间
    public static java.sql.Timestamp getTimeStamp(String date, String dateformat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
        Date dateFormt = dateFormat.parse(date, new java.text.ParsePosition(0));
        java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt.getTime());
        return dateTime;
    }

    /**
     * @param tdate 含有yyyy-MM-dd'T'hh:mm:ss.SSS格式的时间转换.
     * @return
     */
    public static String getTFormatString(String tdate) {
        SimpleDateFormat format1 = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS");
        String str = "";
        try {
            Date date = format1.parse(tdate);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取n小时前的时间
     *
     * @param n
     * @return
     */
    public static String getHourOfDate(int n) {
        SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -n); // 目前時間加3小時
        return df.format(c.getTime());

    }

    /**
     * 将毫秒转换成日期格式
     *
     * @param dateFormat
     * @param millSec
     * @return
     */
    public static String getDateOfMillsec(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 将字符串转换成毫秒
     *
     * @param tdate
     * @param format
     * @return
     */
    public static long getMillsecOfDate(String tdate, String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            date = format1.parse(tdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    /**
     * @param time1 当前时间
     * @param time2 比较时间
     * @param gap   时间间隔，单位分钟
     * @return 如果time1比time2大gap分钟，则返回true;
     */
    public static boolean compareDateTime(Date time1, Date time2, int gap, String unit) {
        boolean result = false;
        switch (unit) {
            case "D":    //天
                result = (time1.getTime() - time2.getTime() > gap * 86400000);
                break;
            case "H":    //时
                result = (time1.getTime() - time2.getTime() > gap * 3600000);
                break;
            case "M":    //分
                result = (time1.getTime() - time2.getTime() > gap * 60000);
                break;
            default:    //天
                result = (time1.getTime() - time2.getTime() > gap * 86400000);
                break;
        }
        return result;
    }

    public static boolean compareDateTime(Date time1, Date time2) {
        return compareDateTime(time1, time2, 0, "M");
    }

    /**
     * 获取时间差
     *
     * @param startTime
     * @param endTime
     * @param unit
     * @return
     */
    public static long getDateGap(Date startTime, Date endTime, String unit) {
        return getDateGap(startTime.getTime(), endTime.getTime(), unit);
    }

    /**
     * 获取时间差
     *
     * @param startTime
     * @param endTime
     * @param unit      时间差单位，默认为D(天)
     * @return
     */
    public static long getDateGap(long startTime, long endTime, String unit) {
        long gap = 0l;
        switch (unit) {
            case "D":    //天
                gap = (endTime - startTime) / 86400000;
                break;
            case "H":    //时
                gap = (endTime - startTime) / 3600000;
                break;
            case "M":    //分
                gap = (endTime - startTime) / 60000;
                break;
            default:    //天
                gap = (endTime - startTime) / 86400000;
                break;
        }
        return gap;
    }

    /**
     * 获取时间戳，格式DATE_FORMAT_YYYYMMDDHHMMSS
     */
    public static String getCurrentTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取几分钟后
     *
     * @param dateStr
     * @param minute
     * @param dateFormat
     * @return
     */
    public static String addMinute(String dateStr, int minute, String dateFormat) {
        Calendar cale = Calendar.getInstance();
        Date date = StringToDate(dateStr, dateFormat);
        cale.setTime(date);
        cale.add(Calendar.MINUTE, minute);
        Date tasktime = cale.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(tasktime);
    }

    /**
     * 时间增加n分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MINUTE, minute);
        return cale.getTime();
    }

    /**
     * 时间增加n秒
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.SECOND, seconds);
        return cale.getTime();
    }

    /**
     * 将字符转换为日期类型
     *
     * @param dateStr
     * @param sourceFormat
     * @return
     */
    public static Date StringToDate(String dateStr, String sourceFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(sourceFormat);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 分钟数动态转换为分，时，天，月
     *
     * @param minute
     * @param maxPeriodLen : 允许的单位值长度，如120分钟表示2小时，若maxPeriodLen=3，则表示为120m，超过maxPeriodLen则向上转换，如1440m转换为1d
     *                     若为0表示，时间单位按标准转换,1mon=30d=1440h=43200m
     * @return
     */
    public static String transferMinute(long minute, int maxPeriodLen) {
        String result = "";

        if (maxPeriodLen == 0) { //按标准时间转换
            long mon = minute / 43200;
            long day = minute % 43200 / 1440;
            long hour = minute % 43200 % 1440 / 60;
            long mi = minute % 43200 % 1440 % 60;
            if (mon > 0) { //单位月
                result = mon + PERIOD_UNIT_MONTH;
            }
            if (day > 0) { //单位天
                result = result + day + PERIOD_UNIT_DAY;
            }
            if (hour > 0) { //单位小时
                result = result + hour + PERIOD_UNIT_HOUR;
            }
            if (mi > 0) {
                result = result + mi + PERIOD_UNIT_MINUTE;
            }
        } else { //根据maxPeriodLen值转换
            long power = (long) Math.pow(10, maxPeriodLen);
            boolean isDay = ((minute / 1440) / (power) == 0) ? true : false;
            boolean isHour = ((minute / 60) / (power) == 0) ? true : false;
            boolean isMi = (minute / (power) == 0) ? true : false;

            long mon = minute / 43200;
            long day = minute / 1440;
            long hour = minute / 60;
            if (isMi) {
                result = minute + PERIOD_UNIT_MINUTE;
            } else if (isHour) {
                result = hour + PERIOD_UNIT_HOUR;
            } else if (isDay) {
                result = day + PERIOD_UNIT_DAY;
            } else {
                result = mon + PERIOD_UNIT_MONTH;
            }
        }
        return result;
    }

    /**
     * 获取时间的月和日
     *
     * @param date
     * @return eg:8月8日
     */
    public static String getMonthAndDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return new StringBuilder().append(month).append("月")
                .append(day).append("日")
                .toString();
    }

    public static Long getTime(String dateStr, String dateformat, Locale locale) {
        try {
            return new SimpleDateFormat(dateformat, locale).parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getDateStr(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
    }
}
