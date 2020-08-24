package com.valarcfcc.xyz;

import com.valarcfcc.xyz.utils.ExcelUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class UtilsTest {
    @Test
    public void excelTest(){
        String filePath = "D://test.xlsx";
        List<HashMap<Integer,String>> list  = ExcelUtils.excel2MapList(filePath,5,5);
        list.forEach(System.out::println);
    }
}
