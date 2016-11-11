package com.example.androidexp.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    /**
     * 格式化12小时制<br>
     * 格式：yyyy-MM-dd hh-MM-ss
     *
     * @param time 时间
     * @return
     */
    public static String format12Time(long time) {
        return format(time, "yyyy-MM-dd hh:MM:ss");
    }

    /**
     * 格式化24小时制<br>
     * 格式：yyyy-MM-dd HH-MM-ss
     *
     * @param time 时间
     * @return
     */
    public static String format24Time(long time) {
        return format(time, "yyyy-MM-dd HH:MM:ss");
    }

    /**
     * 格式化时间,自定义标签
     *
     * @param time    时间
     * @param pattern 格式化时间用的标签
     * @return
     */
    public static String format(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(time));
    }

    /**
     * 格式化时间,自定义标签
     *
     * @param date    时间
     * @param pattern 格式化时间用的标签
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转换为日期
     * @param date
     * @return
     * @throws Exception
     */
    public static Date stringToDate(String date, String pattern) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(date);
    }

    /**
     * 获取当前天
     *
     * @return 天
     */
    @SuppressWarnings("static-access")
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.DAY_OF_MONTH;
    }

    /**
     * 获取当前月
     *
     * @return 月
     */
    @SuppressWarnings("static-access")
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.MONTH;
    }

    /**
     * 获取当前年
     *
     * @return 年
     */
    @SuppressWarnings("static-access")
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.YEAR;
    }

    /**
     * 获取更新的时间
     * @param date
     * @return
     */
    public static String getDateString(Date date, String pattern) {
        Calendar calendar = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (calendar.YEAR - cal.YEAR > 0) {
            return format(date, pattern);
        } else if (calendar.MONTH - cal.MONTH > 0) {
            return format(date, pattern);
        } else if (calendar.DAY_OF_MONTH - cal.DAY_OF_MONTH > 6) {
            return format(date, pattern);
        } else if ((calendar.DAY_OF_MONTH - cal.DAY_OF_MONTH > 0) && (calendar.DAY_OF_MONTH - cal.DAY_OF_MONTH < 6)) {
            int i = calendar.DAY_OF_MONTH - cal.DAY_OF_MONTH;
            return i + "天前";
        } else if (calendar.HOUR_OF_DAY - cal.HOUR_OF_DAY > 0) {
            int i = calendar.HOUR_OF_DAY - cal.HOUR_OF_DAY;
            return i + "小时前";
        } else if (calendar.MINUTE - cal.MINUTE > 0) {
            int i = calendar.MINUTE - cal.MINUTE;
            return i + "分钟前";
        } else if (calendar.SECOND - cal.SECOND > 0) {
            int i = calendar.SECOND - cal.SECOND;
            return i + "秒前";
        } else if (calendar.SECOND - cal.SECOND == 0) {
            return "刚刚";
        } else {
            return format(date, pattern);
        }
    }
}
