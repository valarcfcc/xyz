package com.valarcfcc.xyz;

import com.valarcfcc.xyz.bean.Abc;
import com.valarcfcc.xyz.bean.word;
import com.valarcfcc.xyz.utils.BeanConverterUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BeanConverterUtilsTest {
    @Test
    public void map2BeanTest() throws Exception {
        word word = new word();
        word.setB1("2");
        word.setB2("2");
        word.setB3("3");
        word.setDay("1");
        word.setName("lim");
        word.setYear("1997");
        word.setImg("aaa");
        word.setMonth("1");
        Abc abc = new Abc();
        abc.setA(1);
        abc.setB(2);
        abc.setC(3);
        List<Abc> list = new ArrayList<>();
        list.add(abc);
        list.add(abc);
        word.setList(list);
        Map<String ,Object>  map = BeanConverterUtils.bean2Map(word);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
          }

    }
}
