package com.nightly.lovetravel.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nightly on 2016/10/12.
 */

public class DateTimeUtil {
    public static final String TAG = "Test";

    public static String durationMillisToString(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long secs = seconds % 60;

        String secsStr = secs < 10 ? "0" + secs : "" + secs;
        String minutesStr = minutes < 10 ? "0" + minutes : "" + minutes;
        return minutesStr + ":" + secsStr;
    }

    public static long parseTimeStrToLong(String timeStr) {
        Log.e(TAG, "parseTimeStrToLong: " + timeStr);
        long minMillis = Long.valueOf(timeStr.substring(0, 2)) * 60 * 1000;
        long secMills = Long.valueOf(timeStr.substring(timeStr.indexOf(":") + 1, timeStr.indexOf("."))) * 1000;
        long oddMillis = Long.valueOf(timeStr.substring(timeStr.indexOf(".") + 1)) * 10;
        return minMillis + secMills + oddMillis;
    }

    public static String getTimestampLong() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    public static String getTimestampShort() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c
                .getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyyMMdd")
                .format(c.getTime());
        return dayAfter;
    }
}
