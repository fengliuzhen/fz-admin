package com.fz.admin.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCore {
    public static Date getNowDate()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return StrToDate(df.format(new Date()));
    }
    public static String getNowTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

    public static int getDateStamp()
    {
        Date date=new Date();
        return dateToStamp(date);
    }
    /*
     * 将时间转换为时间戳
     */
    public static int dateToStamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }
    /*
     * 将时间戳转换为时间
     */
    public static Date stampToDate(int s){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time=new Long(s);
        String d = format.format(time);
        return StrToDate(d);
    }
    /*
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }
    /*
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
