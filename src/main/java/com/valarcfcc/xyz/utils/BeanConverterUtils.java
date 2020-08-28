package com.valarcfcc.xyz.utils;
import org.apache.commons.beanutils.BeanUtils;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanConverterUtils  {
    /**
     * Map转换Bean对象方式一：使用apache.commons.beanutils.BeanUtils实现(需在pom文件中添加依赖)
     * 使用泛型免去了类型转换的麻烦。
     * @param <T> 泛型类
     * @param map map
     * @return 泛型类
     */
    public static <T> T map2Bean(Map<String, Object> map, T t) {
        try {
            BeanUtils.populate(t, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Map转换Bean对象方式二：使用apache.commons.beanutils.BeanUtils实现(需在pom文件中添加依赖)
     * @param <T> 泛型类
     * @param map map
     * @param beanClass 泛型类
     * @return 泛型类
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass){
        T bean = null;
        try {
            bean = beanClass.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * Map转换Bean对象方式三（基于JDK实现）
     * @param map map
     * @param t 泛型类
     * @param <T> 泛型类
     * @return 泛型类
     */
    public static <T> T map2JavaBean(Map<String, Object> map, T t) {
        if(map == null || t == null) {
            return null;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for(PropertyDescriptor pd : pds) {
                String key = pd.getName();
                if(map.containsKey(key)) {
                    Object value = map.get(key);
                    Method setter = pd.getWriteMethod();
                    setter.invoke(t, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * Bean对象转换Map方式一（基于JDK实现）
     * @param obj 对象
     * @return map
     */
    public static Map<String, Object> bean2Map(Object obj)throws Exception{
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            Method getMethod = pd.getReadMethod();
            Object o = getMethod.invoke(obj);
            map.put(field.getName(), o);
        }
        return map;
    }

    /**
     * Bean对象转换Map方式二（基于JDK实现）
     * @param bean 实体类
     * @return map
     */
    public static <T> Map<String, Object> beanToMap(T bean)throws Exception{
        if(bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor pd : pds) {
            String key = pd.getName();
            Method getMethod = pd.getWriteMethod();
            Object o = getMethod.invoke(bean);
            map.put(key, o);
        }
        return map;
    }
    
}