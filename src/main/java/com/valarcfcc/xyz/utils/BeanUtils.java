package com.valarcfcc.xyz.utils;

import java.lang.reflect.Field;

public class BeanUtils {
    /**
     * 得到属性值
     *
     * @param obj
     */
    public static void readAttributeValue(Object obj) {
        StringBuffer nameVlues = new StringBuffer();
        //得到class
        Class cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object value = field.get(obj);
                //一个个赋值
                nameVlues.append(field.getName()).append(":").append(value).append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int lastIndex = nameVlues.lastIndexOf(",");
        String result = nameVlues.substring(0, lastIndex);
        System.out.println(result);
    }

}
