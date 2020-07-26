package com.valarcfcc.xyz.utils;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Map;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/22 22:53
 * @Description:
 */
@Slf4j
public class XmlUtils {
    public static String beanToXml(Object object, Class<?> clazz) throws JAXBException {
        String result = StringUtils.EMPTY;
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        NamespacePrefixMapper mapper = new PreferredMapper();
        marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", mapper);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
        StringWriter out = new StringWriter();
        marshaller.marshal(object, out);
        return out.toString();
    }

    /**
     * 将map转化为XML格式的字符串
     *
     * @param resmap
     * @return
     */
    public static String requesttoxml(Map<String, Object> resmap) {
        StringBuffer soapResultData = new StringBuffer();
        for (String key : resmap.keySet()) {
            Object object = resmap.get(key);
            if (object instanceof String || object instanceof Integer || object instanceof Boolean || object instanceof Byte
                    || object instanceof Short || object instanceof Character || object instanceof Long || object instanceof Float || object instanceof Double) {
                soapResultData.append(requesttoxml((Map<String, Object>) object));
            }

            soapResultData.append("<");
            soapResultData.append(key);
            soapResultData.append(">");
            soapResultData.append(resmap.get(key));
            soapResultData.append("</");
            soapResultData.append(key);
            soapResultData.append(">");
        }
        return soapResultData.toString();
    }

    public static String DTOToXML(Object object) {

        return null;
    }


    private static final String JAVA_TYPE = "java.";
    private static final String JAVADATESTR = "java.util.Date";
    private static final String OBJECT = "java.lang.Object";

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (Objects.isNull(value)) {
                continue;
            }
            if (value instanceof String) {
                System.out.println(("----- String ------"));
            }
            if (value instanceof List) {
                System.out.println(("----- List ------"));
            }
            if (value instanceof Map) {
                System.out.println(("----- Map ------"));
            }
            map.put(fieldName, value);
        }
        return map;
    }

    public static String objectToXML(@NotNull Object obj, @NotNull String node) throws IllegalAccessException {
        StringBuffer soapResultData = new StringBuffer();
        Class<?> clazz = obj.getClass();
        if (isBaseOrStringOrMath(obj)) {
            return obj.toString();
        }
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (Objects.isNull(value)) {
                soapResultData.append("<" + node + ":" + fieldName + ">");
                soapResultData.append("</" + node + ":" + fieldName + ">");
                continue;
            } else if (value instanceof List) {
                for (Object o : (List) value) {
                    soapResultData.append("<" + node + ":" + fieldName + ">");
                    soapResultData.append(objectToXML(o, node));
                    soapResultData.append("</" + node + ":" + fieldName + ">");
                }
                continue;
            } else if (value instanceof Map) {
                soapResultData.append("<" + node + ":" + fieldName + ">");
                Map<String, Object> map = (Map<String, Object>) value;
                for (String key : map.keySet()) {
                    soapResultData.append("<" + node + ":" + key + ">");
                    soapResultData.append(objectToXML(map.get(key), node));
                    soapResultData.append("</" + node + ":" + key + ">");
                }
                soapResultData.append("</" + node + ":" + fieldName + ">");
                continue;
            } else if (isBaseOrStringOrMath(value)) {
                soapResultData.append("<" + node + ":" + fieldName + ">");
                soapResultData.append(value.toString());
                soapResultData.append("</" + node + ":" + fieldName + ">");
                continue;
            } else if (value instanceof Object) {
                soapResultData.append("<" + node + ":" + fieldName + ">");
                soapResultData.append(objectToXML(value, node));
                soapResultData.append("</" + node + ":" + fieldName + ">");
            } else {
                throw new IllegalAccessException("Bean类型错误！");
            }
        }
        return soapResultData.toString();
    }

    private static Boolean isBaseOrStringOrMath(Object obj) {
        return obj instanceof String || obj instanceof StringBuffer || obj instanceof Boolean || obj instanceof Byte || obj instanceof Integer || obj instanceof Short || obj instanceof Character || obj instanceof Long || obj instanceof Float || obj instanceof Double || obj instanceof BigDecimal || obj instanceof BigInteger;
    }

    /**
     * 利用递归调用将Object中的值全部进行获取
     *
     * @param timeFormatStr 格式化时间字符串默认<strong>2017-03-10 10:21</strong>
     * @param obj           对象
     * @param excludeFields 排除的属性
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMapString(String timeFormatStr, Object obj, String... excludeFields) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();

        if (excludeFields.length != 0) {
            List<String> list = Arrays.asList(excludeFields);
            objectTransfer(timeFormatStr, obj, map, list);
        } else {
            objectTransfer(timeFormatStr, obj, map, null);
        }
        return map;
    }

    /**
     * 递归调用函数
     *
     * @param obj           对象
     * @param map           map
     * @param excludeFields 对应参数
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String, String> objectTransfer(String timeFormatStr, Object obj, Map<String, String> map, List<String> excludeFields) throws IllegalAccessException {
        boolean isExclude = false;
        //默认字符串
        String formatStr = "YYYY-MM-dd HH:mm:ss";
        //设置格式化字符串
        if (timeFormatStr != null && !timeFormatStr.isEmpty()) {
            formatStr = timeFormatStr;
        }
        if (excludeFields != null) {
            isExclude = true;
        }
        Class<?> clazz = obj.getClass();
        //获取值
        for (Field field : clazz.getDeclaredFields()) {
            String fieldName = clazz.getSimpleName() + "." + field.getName();
            //判断是不是需要跳过某个属性
            if (isExclude && excludeFields.contains(fieldName)) {
                continue;
            }
            //设置属性可以被访问
            field.setAccessible(true);
            Object value = field.get(obj);
            Class<?> valueClass = value.getClass();
            if (valueClass.isPrimitive()) {
                map.put(fieldName, value.toString());

            } else if (valueClass.getName().contains(JAVA_TYPE)) {//判断是不是基本类型
                if (valueClass.getName().equals(JAVADATESTR)) {
                    //格式化Date类型
                    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
                    Date date = (Date) value;
                    String dataStr = sdf.format(date);
                    map.put(fieldName, dataStr);
                } else {
                    map.put(fieldName, value.toString());
                }
            } else {
                objectTransfer(timeFormatStr, value, map, excludeFields);
            }
        }
        return map;
    }


}
