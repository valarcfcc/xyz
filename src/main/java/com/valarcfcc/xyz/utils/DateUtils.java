package com.valarcfcc.xyz.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  描述: 日期工具
 *  @author valarcfcc
 *  @since 2020/9/1 22:35
 */
public class DateUtils {

    private static final List<String> formarts = new ArrayList<String>(4);
    static {
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    public static Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, formarts.get(0));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, formarts.get(1));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(2));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(3));
        } else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 功能描述：格式化日期
     *
     * @param dateStr
     *            String 字符型日期
     * @param format
     *            String 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = (Date) dateFormat.parse(dateStr);
        } catch (Exception e) {
        }
        return date;
    }

    /**
    * 功能描述: 是否是日期格式 yyyy-MM-dd格式
    * @author  valarcfcc
    * @since   2020/9/1 22:36
    * @param  str 日期字符串
    * @return boolean
    */
    public static boolean isDate(String str) {
        boolean result = true;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            format.setLenient(false);
            format.parse(str);
        }catch (Exception e){
            result = false;
        }
        return result;

    }

}
