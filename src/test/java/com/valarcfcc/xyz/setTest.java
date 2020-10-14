package com.valarcfcc.xyz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static com.valarcfcc.xyz.utils.Common.println;
@SpringBootTest
@Slf4j
public class setTest {
    @Test
    public void outTest(){
        Integer as = 1;
        println("s{a}");
        log.info("s{}","b");
    }
}
