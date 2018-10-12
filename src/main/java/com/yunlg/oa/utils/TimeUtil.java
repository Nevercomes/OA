package com.yunlg.oa.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {

    public static java.sql.Date strToDate(String strDate) {
        if(!strDate.equals("")) {
            String str = strDate;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = null;
            try {
                d = format.parse(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            java.sql.Date date = new java.sql.Date(d.getTime());
            return date;
        }
        return null;
    }

    public static String dateToStr(java.sql.Date date) {
        if(date != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        }
        return "";
    }

    public static java.sql.Time getCurrentTime() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = new java.util.Date();
        java.sql.Time dateTime = new java.sql.Time(d.getTime());
        return dateTime;
    }

    public static java.sql.Date getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = new java.util.Date();
        String dateStr = df.format(d);
        d = null;
        try {
            d = df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getBeforMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return month==0 ? 12 : month;
    }

    public static java.sql.Date getReturnTime(java.sql.Date date) {
        return null;
    }
}
