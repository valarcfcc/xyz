package com.valarcfcc.xyz;

import org.junit.Test;

import java.time.LocalDate;

public class DateTest {
    @Test
    public void dateTest(){
        LocalDate date = LocalDate.of(2036,10,22);
        date = date.minusDays(90);

        System.out.printf("首次还贷日：%d年%d月%d日\n", date.getYear(), date.getMonthValue(), date.getDayOfMonth());

    }
}
