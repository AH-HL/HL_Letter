package com.aahl.sdk_utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : Mr.Hao
 * @date :  2018/6/20
 * @description : 基础类型转换类
 */

public class TypeConvertUtils {
   /**
    * yyyy-MM-dd HH:mm:ss 格式
    */
   public static final String DEFAULT_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
   /**
    * yyyy-MM-dd HH:mm 格式
    */
   public static final String DEFAULT_DATE_TIME_HHmm_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";
   /**
    * yyyy-MM-dd HH 格式
    */
   public static final String DEFAULT_DATE_TIME_HH_FORMAT_PATTERN = "yyyy-MM-dd HH";
   /**
    * yyyy-MM-dd 格式
    */
   public static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
   /**
    * HH:mm:ss 格式
    */
   public static final String DEFAULT_TIME_FORMAT_PATTERN = "HH:mm:ss";
   /**
    * HH:mm 格式
    */
   public static final String DEFAULT_TIME_HHmm_FORMAT_PATTERN = "HH:mm";

   private java.util.Calendar c;   //日历类

   /**
    * string to boolean
    */
   public static boolean stingToBoolean(String value, boolean defaultValue) {
      return TextUtils.isEmpty(value) ? defaultValue : Boolean.valueOf(value);
   }


   /**
    * string to int
    */
   public static int stringToInt(String value, int defaultValue) {
      return TextUtils.isEmpty(value) ? defaultValue : Integer.parseInt(value);
   }

   /**
    * string to string
    */
   public static String getString(String value, String defaultValue) {
      return TextUtils.isEmpty(value) ? defaultValue : value;
   }


   /**
    * string to Ingeger
    */
   public static Integer stringToInteger(String value, Integer defaultValue) {
      return TextUtils.isEmpty(value) ? defaultValue : Integer.valueOf(value);
   }


   /**
    * string to long
    */
   public static long stringToLong(String value, long defaultValue) {
      return TextUtils.isEmpty(value) ? defaultValue : Long.parseLong(value);
   }


   /**
    * string to double
    */
   public static double stringToDouble(String value, double defaultValue) {
      return TextUtils.isEmpty(value) ? defaultValue : Double.parseDouble(value);
   }


   /**
    * string to float
    */
   public static float stringToFloat(String value, float defaultValue) {
      return TextUtils.isEmpty(value) ? defaultValue : Float.parseFloat(value);
   }


   /**
    * 使用日期转换pattern <p>pattern的用法参见java.text.SimpleDateFormat</p>
    *
    * @param pattern 日期解析规则
    * @return 按规则转换后的日期时间字符串
    */
   public String toDateTimeString(String pattern) {
      SimpleDateFormat fmt = (SimpleDateFormat) DateFormat.getDateInstance();
      fmt.applyPattern(pattern);
      return fmt.format(c.getTime());
   }

   // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
   // data Date类型的时间
   @SuppressLint("SimpleDateFormat")
   public static String dateToString(Date data, String formatType) {
      return new SimpleDateFormat(formatType).format(data);
   }

   // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
   // HH时mm分ss秒，
   // strTime的时间格式必须要与formatType的时间格式相同
   public static Date stringToDate(String strTime, String formatType)
         throws ParseException {
      @SuppressLint("SimpleDateFormat")
      SimpleDateFormat formatter = new SimpleDateFormat(formatType);
      Date date = null;
      date = formatter.parse(strTime);
      return date;
   }

   /**
    * 转换为 DEFAULT_DATE_FORMAT_PATTERN (yyyy-MM-dd) 格式字符串
    *
    * @return yyyy-MM-dd格式字符串
    */
   public String toDateString() {
      return toDateTimeString(TypeConvertUtils.DEFAULT_DATE_FORMAT_PATTERN);
   }

   /**
    * 转换为 DEFAULT_TIME_FORMAT_PATTERN (HH:mm:ss) 格式字符串
    *
    * @return HH:mm:ss 格式字符串
    */
   public  String toTimeString() {
      return toDateTimeString(TypeConvertUtils.DEFAULT_TIME_FORMAT_PATTERN);
   }

   /**
    * 转换为 DEFAULT_DATE_TIME_FORMAT_PATTERN (yyyy-MM-dd HH:mm:ss) 格式字符串
    *
    * @return yyyy-MM-dd HH:mm:ss 格式字符串
    */
   public String toDateTimeString() {
      return toDateTimeString(TypeConvertUtils.DEFAULT_DATE_TIME_FORMAT_PATTERN);
   }


   /**
    * 将字符按照指定分隔符生成字符数组
    *
    * @param propValue  需要分隔的字符串
    * @param delim    分隔符
    * @return    数组
    */
   public static String[] toStringArray(String propValue, String delim) {
      if (propValue != null) {
         return propValue.split(delim);
      } else {
         return null;
      }
   }

   /**
    * 将list转化为数组
    *
    * @param list 集合
    * @return   数组
    */
   public static String[] toArray(List<String> list) {
      if (list == null) return null;
      String[] result = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
         result[i] = list.get(i);
      }
      return result;
   }


}