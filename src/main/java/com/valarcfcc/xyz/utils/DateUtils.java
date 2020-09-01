package com.valarcfcc.xyz.utils;

import java.text.SimpleDateFormat;

/**
 *  描述: 日期工具
 *  @author valarcfcc
 *  @since 2020/9/1 22:35
 */
public class DateUtils {

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
