package com.valarcfcc.xyz.utils;


import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/22 23:05
 * @Description:
 */
public class PreferredMapper extends NamespacePrefixMapper {
    @Override
    public String getPreferredPrefix(String s, String s1, boolean b) {
        return "s";
    }
}
