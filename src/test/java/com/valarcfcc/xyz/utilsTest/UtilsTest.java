package com.valarcfcc.xyz.utilsTest;

import com.valarcfcc.xyz.config.SysConfig;
import com.valarcfcc.xyz.utils.Base64Utils;
import com.valarcfcc.xyz.utils.DocxUtils;
import com.valarcfcc.xyz.utils.ValueUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class UtilsTest {
    @Resource
    private SysConfig sysConfig;
    @Test
    public void configTest() throws Exception {
        ValueUtils.getValue(sysConfig);
        System.out.println(sysConfig.getTempPath());
    }
    @Test
    public void wordTest() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", "test");
        dataMap.put("name", "lim59");
        dataMap.put("year", "1994");
        dataMap.put("month", "1");
        dataMap.put("day", "1");
        DocxUtils.creatWordByTemplate("ftl", dataMap);
    }
    @Test
    public void imgTest() {
        System.out.println("data:image/png;base64," + Base64Utils.ImageFilePathBase64("D:/img.png"));
    }


}
