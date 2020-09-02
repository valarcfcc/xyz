package com.valarcfcc.xyz.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

public class PropertiesUtils {
    private static String PROPERTY_NAME = "application.yml";

    /**
     * 功能描述:
     * @author  valarcfcc
     * @since   2020/9/2 18:48
     * @param key key
     * @return    value
     *
     */
    public static String  getCommonYml(Object key){
        Resource resource = new ClassPathResource(PROPERTY_NAME);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return (String) properties.get(key);
    }
}
