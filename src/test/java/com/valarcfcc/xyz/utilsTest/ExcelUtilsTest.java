package com.valarcfcc.xyz.utilsTest;

import com.valarcfcc.xyz.constant.GlobalConstants;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExcelUtilsTest {

    @Test
    public void toSqlTest(){
        String str1 = "NUMBER(10)";
        int beginIndex = str1.indexOf(GlobalConstants.Symbol.OPEN_PARENTHESIS);
        int endIndex = str1.lastIndexOf(GlobalConstants.Symbol.CLOSE_PARENTHESIS);
        System.out.println(str1.substring(beginIndex, endIndex + 1));
    }

}
