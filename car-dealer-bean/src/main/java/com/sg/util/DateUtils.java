package com.sg.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtils
 * @Description: 日期工具类
 * @Author Zheng.Zeng
 * @Date 2019/12/12 0012
 * @Version V1.0
 **/
public class DateUtils {
    /**
     * 完整日期时间无间隔格式
     */
    public static final String LONG_FORMAT = "yyyyMMddHHmmss";
    /**
     * 日期无间隔格式
     */
    public static final String SHORT_FORMAT = "yyyyMMdd";
    /**
     * 无间隔时间格式
     */
    public static final String SHORT_FORMAT_TIME = "HHmmss";

    public static final String LONG_FORMAT_MIN = "yyyyMMddHHmm";

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    /**
     * 默认格式：天
     *
     * @return
     */
    public static String getDatePattern() {

        return "yyyy-MM-dd";
    }

    /**
     * 默认格式 微毫秒
     *
     * @return
     */
    public static String getDateTimePattern() {
        return DateUtils.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     *
     * @return
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @param pattern
     * @return
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }


    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断字符串是否是日期
     *
     * @param timeString
     * @return
     */
    public static boolean isDate(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(timeString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 字符串转为日期
     *
     * @param timeString
     * @return
     */
    public static Date strToDate(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            return format.parse(timeString);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date strToDateHms(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setLenient(false);
        try {
            return format.parse(timeString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long strToTime(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setLenient(false);
        try {
            return format.parse(timeString).getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 格式化时间
     *
     * @param timestamp
     * @return
     */
    public static String dateFormat(Date timestamp) {
        if (timestamp == null){
            return null;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Instant instant = timestamp.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return format.format(localDateTime);
    }


    /**
     * 获取系统时间Date
     *
     * @return
     */
    public static Date getSysDate() {
        return new Date();
    }

    /**
     * 生成时间随机数
     *
     * @return
     */
    public static String getDateRandom() {
        String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return s;
    }

    public static Date convertToType(Long value) {
        Date date = null;
        if (value == null) {
            logger.warn("value is null!");
            return date;
        } else {
            date = new Date(((Long) value).longValue());
            return date;
        }

    }

    public static Date getNextDay(Date today) {
        Date nextday = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(today);// 把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, 1); // 设置为第二天
        nextday = calendar.getTime(); // 得到第二天的时间
        return nextday;
    }

    public static Date getLastDay(Date today) {
        Date nextday = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(today);// 把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为第二天
        nextday = calendar.getTime(); // 得到第二天的时间
        return nextday;
    }

    /**
     * 获取前几天的日期
     *
     * @param past 天数
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public static Date getNextMonth(Date today) {
        Date nextday = new Date();
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(today);// 把当前时间赋给日历
        calendar.add(Calendar.MONTH, 3); // 加三个月
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        nextday = calendar.getTime(); // 得到第二天的时间
        return nextday;
    }


    public static String parsefomatCalendar(Calendar cal, String parsePattern) {
        if (cal == null) {
            return "";
        }
        String str = "";
        if (StringUtils.isEmpty(parsePattern)) {
            str = DateFormatUtils.format(cal, LONG_FORMAT);
        } else {
            str = DateFormatUtils.format(cal, parsePattern);
        }
        return str;
    }

    /**
     * 计算两个时间相差的月份
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer getDifMonth(Date startDate, Date endDate) {
//        Calendar start = Calendar.getInstance();
//        Calendar end = Calendar.getInstance();
//        start.setTime(startDate);
//        end.setTime(endDate);
//        int result = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
//        int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
//        return Math.abs(month + result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(startDate);
        aft.setTime(endDate);
        int surplus = aft.get(Calendar.DATE) - bef.get(Calendar.DATE);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);
//        System.out.println(result);
//        else if(result == 0){
//            result = surplus <= 0 ? 1 : 0;
//        }
        if (result < 0) {
            result = 1;
        } else {
            result = 0;
        }
//        System.out.println(result);
//        System.out.println("相差年份：" + ((Math.abs(year)) + result));
        return (Math.abs(year)) + result;
    }

    /*
     * @MethodName: datePoor
     * @Description: 获取时差多少天
     * @Param: [endDate, startDate]
     * @Return: long
     * @Author: Zheng.Zeng
     * @Date: 下午 5:35
     **/
    public static long datePoor(Date endDate, Date startDate) {
        if (null == endDate || null == startDate) {
            return 0L;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            endDate = sdf.parse(sdf.format(endDate));
            startDate = sdf.parse(sdf.format(startDate));
        } catch (ParseException e) {
            return 0L;
        }
        //得到相差的天数 betweenDate
        long betweenDate = (endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000);
        return betweenDate;
    }

    /**
     * 字符串转日期
     *
     * @param dateString
     * @param formatString
     * @return
     */
    public static Date stringToDate(String dateString, String formatString) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

}
