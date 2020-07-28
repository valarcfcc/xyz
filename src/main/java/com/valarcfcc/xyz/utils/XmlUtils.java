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

    /**
     * @Description: 实体类转xml node为节点前缀
     * @param obj
     * @param node
     * @return
     * @throws IllegalAccessException
     */
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
}
