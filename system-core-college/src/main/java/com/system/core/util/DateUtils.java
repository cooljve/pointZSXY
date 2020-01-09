package com.system.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getNowDate() {// 得到当前日期
        return new SimpleDateFormat(Constant.YYYY_MM_DD).format(new Date());
    }

    public static String getNowDateTime() {// 得到当前日期时间
        return new SimpleDateFormat(Constant.YYYY_MM_DD_HH_MM_SS)
                .format(new Date());
    }

    public static String getNowDateTimeAsNumber() {// 得到当前时间
        return new SimpleDateFormat(Constant.IODT)
                .format(new Date());
    }

    public static Date getNowTime(String format) {// 得到当前时间
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

    public static String getNowDateAsNumber() {// 得到当前时间
        return new SimpleDateFormat(Constant.YYYYMMDD).format(new java.util.Date());
    }
}
