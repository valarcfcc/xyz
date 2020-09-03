package com.valarcfcc.xyz.UtilsTest;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class BeanConverterUtilsTest {
    @Test
    public void forEachMapTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("key",1);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

    }
}
