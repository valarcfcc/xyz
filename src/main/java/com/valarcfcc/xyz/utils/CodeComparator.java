package com.valarcfcc.xyz.utils;

import com.valarcfcc.xyz.bean.Code;

import java.util.Comparator;

public class CodeComparator implements Comparator<Code> {
    @Override
    public int compare(Code o1, Code o2) {
        //按名字升序排序
        String name1 = o1.getPath();
        String name2 = o2.getPath();
        //升序比较
        int n = name1.compareTo(name2);  //name1>name2返回正整数，反之返回负整数，相等返回0
        //倒序比较
//        int n = name2.compareTo(name1);
        return n;
    }
}
