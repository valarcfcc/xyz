package com.valarcfcc.xyz.mathTest;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.MessageFormat;

import static com.valarcfcc.xyz.utils.Common.println;

public class bigDecimalTest {
    @Test
    public void test() {
        BigDecimal a = BigDecimal.ZERO;
        BigDecimal b = BigDecimal.ZERO;
        BigDecimal c = BigDecimal.ZERO;
        println(a.toString());
        println(b.toString());
        println(c.toString());
        b = b.add(new BigDecimal("12"));
        println(a.toString());
        println(b.toString());
        println(c.toString());
    }
}
