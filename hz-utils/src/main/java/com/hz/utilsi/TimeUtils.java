package com.hz.utilsi;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;


public class TimeUtils {


    public static int getBeforeHourTime(int ihour, String format) {
        String returnstr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
        return calendar.get(Calendar.HOUR_OF_DAY);
        //SimpleDateFormat df = new SimpleDateFormat(format);
        //returnstr = df.format(calendar.getTime());
        //return returnstr;
    }

    /**
     * 得到系统当前时间
     *
     * @return
     */
    public static long getCurrentTime() {

        return System.currentTimeMillis();

    }

    /**
     * 生成平台统一的timeId
     *
     * @param shotDate
     * @param shotTime
     * @return
     */
    public static long getTimeId(String shotDate, String shotTime) throws Exception{
        String date = TimeUtils.getDateToString(TimeUtils.getStringToDate(shotDate), "yyyyMMdd");
        if (!StringUtils.isEmpty(shotTime)) {
            // 转时间
            String time = shotTime.substring(0, 2);
            return Long.parseLong(date + time);
        }
        return Long.valueOf(date);

    }

    /**
     * long转时间
     *
     * @param time
     * @return
     */
    public static String getDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date(time);
        return sdf.format(dt);
    }

    public static String getDate(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt = new Date(time);
        return sdf.format(dt);
    }

    /**
     * 得到当天日期
     *
     * @return
     */
    public static String getCurrentDay() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(dt);
    }

    public static long getDayTime() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String strDate = sdf.format(dt);
        // Date date = new Date();
        try {
            dt = sdf.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dt.getTime();
    }

    /**
     * 得到当天周几
     *
     * @return
     */
    public static String getWeekOfDate(String dateStr, String formatStr) {
        DateFormat format = new SimpleDateFormat(formatStr);
        try {
            Date date = format.parse(dateStr);
            String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
            return weekDays[w];
        } catch (ParseException e) {

            e.printStackTrace();
            return "周日";
        }

    }

    public static String getWeekOfDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return "";
        }
        if (dateStr.contains(".")) {
            return getWeekOfDate(dateStr, "yyyy.MM.dd");
        } else {
            return getWeekOfDate(dateStr, "yyyy-MM-dd");
        }
    }

    public static String getHour(long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal.setTimeInMillis(time);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            return new String("0" + hour + ":00");
        }
        return new String(hour + ":00");
    }

    public static long getLongValueTime(String time, String timeFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        Date dt = null;
        try {
            dt = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt.getTime();
    }


    // /**
    // * 得到当前日期的下一天，注意，日期格式必须是固定的。
    // *
    // * @param startDate
    // * @return
    // */
    // public static String getNextDay(String startDate) {
    //
    // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    // java.util.Calendar calendar = java.util.Calendar.getInstance();
    // calendar.roll(java.util.Calendar.DAY_OF_YEAR, -1);
    // // System.out.println("昨天："+df.format(calendar.getTime()));
    // // calendar=java.util.Calendar.getInstance();
    // // System.out.println("今天："+df.format(calendar.getTime()));
    // calendar.roll(java.util.Calendar.DAY_OF_YEAR, 1);
    // return df.format(calendar.getTime());
    //
    // }

    // public static String getNextDay() {
    //
    // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    // java.util.Calendar calendar = java.util.Calendar.getInstance();
    // calendar.roll(java.util.Calendar.DAY_OF_YEAR, -1);
    // // System.out.println("昨天："+df.format(calendar.getTime()));
    // // calendar=java.util.Calendar.getInstance();
    // // System.out.println("今天："+df.format(calendar.getTime()));
    // calendar.roll(java.util.Calendar.DAY_OF_YEAR, 1);
    // return df.format(calendar.getTime());
    //
    // }

    /**
     * 得到指定日期字符串
     *
     * @param date
     * @return
     */
    public static String getDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    /**
     * 取前一天
     * <p>
     *
     * @param beginDate
     * @param num       前几天
     * @return
     * @jira TODO
     * @author hezhuang
     * @date 2017年7月7日上午11:14:52
     * @modified TODO
     * @see 1.5.0
     */
    public static String getDateToString(Date beginDate, int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy.MM.dd");
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - num);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 得到指定日期字符串（MM月dd日）
     *
     * @param date
     * @return
     */
    public static String getDateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * String 转 Date
     * <p>
     * <p>
     *
     * @param date
     * @return
     * @jira TODO
     * @author 贲国龙
     * @date 2016年8月8日上午10:49:33
     * @modified TODO
     * @see 1.5.0
     */
    public static Date getStringToDate(String date) throws Exception {
        Date d = null;
        SimpleDateFormat sdf = null;
        if (date.contains(".")) {
            sdf = new SimpleDateFormat("yyyy.MM.dd");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        d = sdf.parse(date);

        return d;
    }

    public static String[] getStringUserDate(String date) throws UnsupportedEncodingException {
        String[] shotdates = new String[2];
        if (date.contains("|")) {
            return date.replaceAll("\\s*-", ".").replaceAll("\\|", "-").split("-");
        } else {
            shotdates[0] = date.toString().replaceAll("\\s*-", ".");
            shotdates[1] = "";
            return shotdates;
        }

    }

    /**
     * 日期格式的计算相差天数
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * <p>
     * 获取星座
     *
     * @return
     * @jira TODO
     * @author hezhuang
     * @date 2017年3月15日下午4:23:27
     * @modified TODO
     * @see 1.5.0
     */
    public static String getConstellation(long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal.setTimeInMillis(time);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
        String[] constellationArr = new String[]{"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
                "天蝎座", "射手座", "摩羯座"};
        return day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];
    }

    /**
     * 根据出生日期获得年龄
     * <p>
     *
     * @param birthDay
     * @return
     * @throws Exception
     * @jira TODO
     * @author hezhuan
     * @date 2017年4月24日下午2:28:23
     * @modified TODO
     * @see 1.5.0
     */
    public static int getAge(Date birthDay) throws Exception {
        if (birthDay == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 获取某周一9点的时间戳
     * <p>
     *
     * @param type this 这周 last上周 next下周
     * @return
     * @jira TODO
     * @author hezhuang
     * @date 2017年6月30日下午5:51:16
     * @modified TODO
     * @see 1.5.0
     */
    public static long getMonday(String type) {
        int mondayPlus = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        System.out.println(dayOfWeek);
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else if (dayOfWeek == 0) { // 妈的 还得兼容他妈周日的情况.. 国外拿周日做礼拜一使
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        if (type.equals("this")) {
            currentDate.add(GregorianCalendar.DATE, mondayPlus);
        }
        if (type.equals("last")) {
            currentDate.add(GregorianCalendar.DATE, mondayPlus - 7);
        }
        if (type.equals("next")) {
            currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        }
        Date monday = currentDate.getTime();
        String date = getDateToString(monday, "yyyy-MM-dd");
        date = date + " 09:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            monday = sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return monday.getTime();
    }

    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * 获取本月第一天的时间戳
     * <p>
     *
     * @param year
     * @param month
     * @return String
     * @author hezhuang
     * @date 2017年9月14日 下午2:44:55
     * @Description: TODO
     */
    public static long getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return TimeUtils.getLongValueTime(new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime()) + "00:00:00", "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 获取本月最后天的时间戳
     * <p>
     *
     * @param year
     * @param month
     * @return String
     * @author hezhuang
     * @date 2017年9月14日 下午2:44:38
     * @Description: TODO
     */
    public static long getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return TimeUtils.getLongValueTime(new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime()) + "23:59:59", "yyyy-MM-dd hh:mm:ss");
    }
}
