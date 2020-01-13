package com.system.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDateUtil {

    public static String getNowDate() {// 得到当前日期
        return new SimpleDateFormat(Constant.YYYY_MM_DD).format(new Date());
    }

    public static String getNowDateTime() {
        return new SimpleDateFormat(Constant.YYYY_MM_DD_HH_MM_SS)
                .format(new Date());
    }

    public static String getNowDateTimeAsNumber() {
        return new SimpleDateFormat(Constant.IODT)
                .format(new Date());
    }

    public static Date getNowTime(String format) {
        Date date = new Date();
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat(format);
            String str = formatDate.format(date);
            date = formatDate.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getNowDateAsNumber() {
        return new SimpleDateFormat(Constant.YYYYMMDD).format(new java.util.Date());
    }

     public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        date = calendar.getTime();
        return date;
    }
}
