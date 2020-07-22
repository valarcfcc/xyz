package com.valarcfcc.xyz.utils;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

import com.sun.xml.internal.txw2.output.XMLWriter;
import org.apache.commons.lang.StringUtils;

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
public class XmlUtils {
    public static String beanToXml(Object object, Class<?> clazz) throws JAXBException {
        String result = StringUtils.EMPTY;
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        NamespacePrefixMapper mapper = new PreferredMapper();
        marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper",mapper);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
        StringWriter out = new StringWriter();
        marshaller.marshal(object,out);
        return out.toString();
    }

    /**
     * 将map转化为XML格式的字符串
     * @param resmap
     * @return
     */
    public static String requesttoxml(Map<String, Object> resmap){
        StringBuffer soapResultData = new StringBuffer();
        for(String key : resmap.keySet()){
            Object object = resmap.get(key);
            if(object instanceof String || object instanceof Integer || object instanceof Boolean || object instanceof Byte
                    || object instanceof Short || object instanceof Character || object instanceof Long || object instanceof Float || object instanceof Double){
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


}
