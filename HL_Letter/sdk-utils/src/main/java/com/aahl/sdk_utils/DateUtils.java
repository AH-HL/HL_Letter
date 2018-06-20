package com.aahl.sdk_utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Horrarndoo on 2017/8/31.
 * <p>
 * 日期时间工具类
 */
public class DateUtils {

    public static final long ONE_SECOND_MILLIONS = 1000;
    public static final long ONE_MINUTE_MILLIONS = 60 * ONE_SECOND_MILLIONS;
    public static final long ONE_HOUR_MILLIONS = 60 * ONE_MINUTE_MILLIONS;
    public static final long ONE_DAY_MILLIONS = 24 * ONE_HOUR_MILLIONS;
    public static final int DAY_OF_YEAR = 365;

    // 日期格式为 2016-02-03 17:04:58
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_SPLIT = " ";
    public static final String PATTERN = PATTERN_DATE + PATTERN_SPLIT + PATTERN_TIME;

    /**
     * 获取一天之内的任意整数时段   注意：开始时间要小于结束时间，且在0~23范围内
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return  时间集合数组
     */
    public static List<String> getHourList(int startTime, int endTime) {

        if (startTime > endTime || startTime < 0 || endTime > 23){
           return null;
        }

        List<String> hourTime = new ArrayList<>();
        String hour;
        int totalTime = endTime - startTime;
        if (totalTime > 0) {

            for (int i = startTime; i <= endTime; i++) {
                if (i < 10) {
                    hour = String.format("0%s:00", i);
                } else {
                    hour = String.format("%s:00", i);
                }
                hourTime.add(hour);
            }
        }

        return hourTime;
    }

    /**
     * 获取当前日期 PATTERN_TIME 部分 "HH:mm:ss"
     */
    public static String getDayTime() {
        Date curDateTime = new Date();
        return getDateTime(date2str(curDateTime));
    }

    /**
     * 获取当前日期 PATTERN_DATE 部分 "yyyy-MM-dd HH:mm:ss"
     */
    public static String getCurDate() {
        Date curDate = new Date();
        return getDate(date2str(curDate));
    }

    /**
     * 获取当前日期 PATTERN_DATE 部分 "yyyy-MM-dd"
     */
    public static String getAllDate() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat(PATTERN);
        String date = df.format(new Date());
        return date;
    }

    /**
     * 原有日期上累加月
     *
     * @param dateStr 日期格式为 2016-02-03 17:04:58（也可以是别的符合的格式）
     */
    public static String getShortTime(String dateStr) {
        String str;

        Date date = str2date(dateStr);
        Date curDate = new Date();
        long durTime = curDate.getTime() - date.getTime();
        int dayDiff = calculateDayDiff(date, curDate);

        if (durTime <= 10 * ONE_MINUTE_MILLIONS) {
            str = "刚刚";
        } else if (durTime < ONE_HOUR_MILLIONS) {
            str = durTime / ONE_MINUTE_MILLIONS + "分钟前";
        } else if (dayDiff == 0) {
            str = durTime / ONE_HOUR_MILLIONS + "小时前";
        } else if (dayDiff == -1) {
            str = "昨天" + DateFormat.format("HH:mm", date);
        } else if (isSameYear(date, curDate) && dayDiff < -1) {
            str = DateFormat.format("MM-dd", date).toString();
        } else {
            str = DateFormat.format("yyyy-MM", date).toString();
        }

        return str;
    }

    /**
     * 获取传入日期 PATTERN_DATE 部分 "yyyy-MM-dd"
     */
    public static String getDate(String date) {
        if (TextUtils.isEmpty(date) || !date.contains(PATTERN_SPLIT)) {
            return "";
        }
        return date.split(PATTERN_SPLIT)[0];
    }

    /**
     * 获取传入日期 PATTERN_TIME 部分 "HH:mm:ss"
     */
    public static String getDateTime(String date) {
        if (TextUtils.isEmpty(date) || !date.contains(PATTERN_SPLIT)) {
            return "";
        }
        return date.split(PATTERN_SPLIT)[1];
    }


    /**
     * 原有日期上累加天
     *
     * @return 累加后的日期 PATTERN_DATE 部分" yyyy-MM-dd"
     */
    public static String addDayOfMonth(String date, int dayCount) {
        //如果date为空 就用当前时间
        if (TextUtils.isEmpty(date)) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat(PATTERN_DATE + PATTERN_SPLIT + PATTERN_TIME);
            date = df.format(new Date());
        }
        Calendar calendar = str2calendar(date);
        calendar.add(Calendar.DAY_OF_MONTH, dayCount);
        String s = calendar2str(calendar);
        return getDate(calendar2str(calendar));
    }

    /**
     * 原有日期上累加月
     *
     * @return 累加后的日期 PATTERN_DATE 部分" yyyy-MM-dd"
     */
    public static String addMonth(String date, int moonCount) {
        //如果date为空 就用当前时间
        if (TextUtils.isEmpty(date)) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat(PATTERN_DATE + PATTERN_SPLIT + PATTERN_TIME);
            date = df.format(new Date());
        }
        Calendar calendar = str2calendar(date);
        calendar.add(Calendar.MONTH, moonCount);
        return getDate(calendar2str(calendar));
    }

    /**
     * 获取当前时间起,累计XX天的日期（yyyy-MM-dd）数组
     *
     * @param dayNum 累计XX天
     * @return 累加后的日期 PATTERN_DATE 部分" yyyy-MM-dd"
     */
    public static List<String> getListData(int dayNum) {

        List<String> dataList = new ArrayList();
        for (int i = 0; i < dayNum; i++) {
            String ymd = DateUtils.addDayOfMonth(null, i).replace("-", "");
            dataList.add(ymd);
        }
        return dataList;
    }


    /**
     * 计算天数差
     */
    public static int calculateDayDiff(Date targetTime, Date compareTime) {
        boolean sameYear = isSameYear(targetTime, compareTime);
        if (sameYear) {
            return calculateDayDiffOfSameYear(targetTime, compareTime);
        } else {
            int dayDiff = 0;

            // 累计年数差的整年天数
            int yearDiff = calculateYearDiff(targetTime, compareTime);
            dayDiff += yearDiff * DAY_OF_YEAR;

            // 累计同一年内的天数
            dayDiff += calculateDayDiffOfSameYear(targetTime, compareTime);

            return dayDiff;
        }
    }

    /**
     * 计算同一年内的天数差
     */
    public static int calculateDayDiffOfSameYear(Date targetTime, Date compareTime) {
        if (targetTime == null || compareTime == null) {
            return 0;
        }

        Calendar tarCalendar = Calendar.getInstance();
        tarCalendar.setTime(targetTime);
        int tarDayOfYear = tarCalendar.get(Calendar.DAY_OF_YEAR);

        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTime(compareTime);
        int comDayOfYear = compareCalendar.get(Calendar.DAY_OF_YEAR);

        return tarDayOfYear - comDayOfYear;
    }

    /**
     * 计算年数差
     */
    public static int calculateYearDiff(Date targetTime, Date compareTime) {
        if (targetTime == null || compareTime == null) {
            return 0;
        }

        Calendar tarCalendar = Calendar.getInstance();
        tarCalendar.setTime(targetTime);
        int tarYear = tarCalendar.get(Calendar.YEAR);

        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTime(compareTime);
        int comYear = compareCalendar.get(Calendar.YEAR);

        return tarYear - comYear;
    }

    /**
     * 计算月数差
     */
    public static int calculateMonthDiff(String targetTime, String compareTime) {
        return calculateMonthDiff(str2date(targetTime, PATTERN_DATE),
                str2date(compareTime, PATTERN_DATE));
    }

    /**
     * 计算月数差
     */
    public static int calculateMonthDiff(Date targetTime, Date compareTime) {
        Calendar tarCalendar = Calendar.getInstance();
        tarCalendar.setTime(targetTime);
        int tarYear = tarCalendar.get(Calendar.YEAR);
        int tarMonth = tarCalendar.get(Calendar.MONTH);

        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTime(compareTime);
        int comYear = compareCalendar.get(Calendar.YEAR);
        int comMonth = compareCalendar.get(Calendar.MONTH);
        return ((tarYear - comYear) * 12 + tarMonth - comMonth);

    }

    /**
     * 是否为同一年
     */
    public static boolean isSameYear(Date targetTime, Date compareTime) {
        if (targetTime == null || compareTime == null) {
            return false;
        }

        Calendar tarCalendar = Calendar.getInstance();
        tarCalendar.setTime(targetTime);
        int tarYear = tarCalendar.get(Calendar.YEAR);

        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTime(compareTime);
        int comYear = compareCalendar.get(Calendar.YEAR);

        return tarYear == comYear;
    }

    /**
     * String转为Date
     */
    public static Date str2date(String str, String format) {
        Date date = null;
        try {
            if (str != null) {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String转为Date
     */
    public static Date str2date(String str) {
        return str2date(str, PATTERN);
    }

    /**
     * Date转为String
     */
    public static String date2str(Date date) {
        return date2str(date, PATTERN);
    }

    /**
     * Date转为String 可指定日期格式
     */
    public static String date2str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * String转为calendar
     */
    public static Calendar str2calendar(String str) {
        Calendar calendar = null;
        Date date = str2date(str);
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }

    /**
     * String转为calendar 可指定日期格式
     */
    public static Calendar str2calendar(String str, String format) {
        Calendar calendar = null;
        Date date = str2date(str, format);
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }

    /**
     * calendar转为String
     */
    public static String calendar2str(Calendar calendar) {
        return date2str(calendar.getTime());
    }

    /**
     * calendar转为String 可指定日期格式
     */
    public static String calendar2str(Calendar calendar, String format) {
        return date2str(calendar.getTime(), format);
    }
}
