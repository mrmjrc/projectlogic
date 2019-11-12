package cn.mrmj.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * create by: mrmj
 * description: 对阿帕奇的DateUtils进行再次封装
 * create time: 2019/11/12 14:19
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    /**
     * create by: mrmj
     * description: 获得今天所在周的周一日期
     * create time: 2019/11/12 14:19
     */
    public static Date getThisMondayDate() {
        Calendar calendar = Calendar.getInstance();
        // 国外的习惯是周日是一周的第一天 如果今天是星期天 那么 国内 的定义 这周的星期一是六天前 而国外是第二天
        int days = 0;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            days = -6;
        } else {
            days = Calendar.MONDAY - calendar.get(Calendar.DAY_OF_WEEK);
        }
        calendar.add(Calendar.DATE, days);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * create by: mrmj
     * description: 仅仅格式化日期
     * create time: 2019/11/12 14:20
     */
    public static String formatOnlyDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //TimeZone 表示时区偏移量，也可以计算夏令时。
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * create by: mrmj
     * description: 只格式化时间
     * create time: 2019/11/12 14:23
     */
    public static String formatOnlyTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * create by: mrmj
     * description: 格式化日期和时间，按照默认格式化
     * create time: 2019/11/12 14:24
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * create by: mrmj
     * description: 格式化时间和日期，按照传入的格式进行格式化
     * create time: 2019/11/12 14:24
     */
    public static String formatDateTime(Date date, String format ) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = null;
        if (StringUtils.isEmpty(format)) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }else {
            sdf = new SimpleDateFormat(format);
        }
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * create by: mrmj
     * description: 转化时分
     * create time: 2019/11/12 14:25
     */
    public static String formatHourAndMinute(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * create by: mrmj
     * description: 根据传入的时间得到这是周几
     * create time: 2019/11/12 14:26
     */
    public static Integer getWeek (Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
       int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
       Integer week = null;
       switch (dayOfWeek) {
                case 1:
                    week = 7;
               break;
               case 2:
                   week = 1;
                break;
               case 3:
                   week = 2;
                break;
                case 4:
                    week = 3;
                 break;
               case 5:
                   week = 4;
              break;
             case 6:
                 week = 5;
                 break;
                case 7:
                    week = 6;
              break;
        }
        return week;
    }

    /**
     * create by: mrmj
     * description: 通过生日计算年龄
     * create time: 2019/11/12 14:26
     */
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);
            if (birth.after(now)) {// 如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {// 兼容性更强,异常后返回数据0
            return 0;
        }
    }

    /**
     * create by: mrmj
     * description: 仅仅获取当前时间
     * create time: 2019/11/12 14:27
     */
    public static Date getOnlyDate(String format) {
        String onlyDate = formatOnlyDate(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(onlyDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * create by: mrmj
     * description: 获取当前天的时间
     * create time: 2019/11/12 14:29
     */
    public static Date getCurrentDate() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return date;
    }


    /**
     * create by: mrmj
     * description: 查询两个时间差距月数
     * create time: 2019/11/12 14:29
     */
    public static int calculateMonthIn(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(date1);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(date2);
        int c =
                (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
                        - cal2.get(Calendar.MONTH);
        return c;
    }

    /**
     * create by: mrmj
     * description: 查询两个时间相差的天数
     * create time: 2019/11/12 14:31
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }


    /**
     * create by: mrmj
     * description: 日期加一天方法
     * create time: 2019/11/12 14:32
     */
    public static Date addOneDay(String time, Integer n) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, n);
            date = cal.getTime();
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * create by: mrmj
     * description: 根据固定的格式，将字符串转化为Date
     * create time: 2019/11/12 14:32
     */
    public static Date parseDate(String str, String ftm) {
        if (str == null){
            return null;
        }
        try {
            return new SimpleDateFormat(ftm).parse(str);
        } catch (ParseException e) {
            return null;
        }

    }

    /**
     * create by: mrmj
     * description: 将当前时间转换为“yyyy-MM-dd HH:mm:ss”以便放入数据库
     * create time: 2019/11/12 14:33
     */
    public static Date parseCurrentDate() {
        String dateString = formatDateTime(new Date(), null);
        Date date = parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
        return date;
    }

    /**
     * create by: mrmj
     * description: 获取过去的天数
     * create time: 2019/11/12 14:34
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis()-date.getTime();
        return t/(24*60*60*1000);
    }

    /**
     * create by: mrmj
     * description: 获取过去的小时
     * create time: 2019/11/12 14:37
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*60*1000);
    }

    /**
     * create by: mrmj
     * description: 获取过去几分钟
     * create time: 2019/11/12 14:39
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*1000);
    }

    /**
     * create by: mrmj
     * description: 转换为时间  天,时:分:秒.毫秒
     * create time: 2019/11/12 14:39
     */
    public static String formatDateTime(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

    /**
     * create by: mrmj
     * description: 获取两个日期之间的天数，Date类型
     * create time: 2019/11/12 14:39
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * create by: mrmj
     * description: 获取两个日期之间的天数，字符串格式
     * create time: 2019/11/12 14:40
     */
    public static double getDistanceOfTwoDate(String before, String after, String fmt){
        Date beforeD = parseDate(before, fmt);
        Date afterD = parseDate(after, fmt);
        return getDistanceOfTwoDate(beforeD, afterD);
    }

    /**
     * create by: mrmj
     * description: 获取两个日期之间的小时数
     * create time: 2019/11/12 14:40
     */
    public static double getDistHoursOfTwoDate(Date before, Date after){
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60);
    }

    /**
     * create by: mrmj
     * description: 获取两个时间相差的分钟数
     * create time: 2019/11/12 14:41
     */
    public static Integer diffMinute(Date start, Date end){
        return  (int) Math.ceil((double)(end.getTime() - start.getTime()) / (1000 * 60));
    }

    /**
     * create by: mrmj
     * description: 通过毫秒时间戳获取小时数和分钟数
     * create time: 2019/11/12 14:41
     */
    public static String getHourAndMinute(long time){
        int minute = (int) Math.ceil((double)(time) / (1000 * 60));
        int hours = (int) Math.floor((double)minute / 60);
        minute = minute % 60;
        StringBuilder sb = new StringBuilder();
        if (hours > 0){
            sb.append(hours).append("小时");
        }
        if (minute > 0){
            sb.append(minute).append("分");
        }
        return sb.toString();
    }

}
