package com.valarcfcc.xyz.mathTest;

import org.junit.Test;

import java.math.BigDecimal;

import static com.valarcfcc.xyz.utils.Common.println;

public class bigDecimalTest {
    @Test
    public void test() {
        BigDecimal a = new BigDecimal("1200");
        BigDecimal b = new BigDecimal("1323.0");
        BigDecimal c = new BigDecimal("123");
        BigDecimal d = new BigDecimal("123.000");
        println(c.compareTo(d));
        println(1200 / 1323.0);
        if (c.compareTo(d) == 0) {
            println("xiang");
        }
    }
}
