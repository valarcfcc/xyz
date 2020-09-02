package com.valarcfcc.xyz.UtilsTest;

import com.valarcfcc.xyz.XyzApplication;
import com.valarcfcc.xyz.config.SysConfig;
import com.valarcfcc.xyz.utils.Base64Utils;
import com.valarcfcc.xyz.utils.DocxUtils;
import com.valarcfcc.xyz.utils.ValueUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public void excelTest() {

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", "test");
        dataMap.put("name", "lim59");
        dataMap.put("year", "1994");
        dataMap.put("month", "1");
        dataMap.put("day", "1");
        DocxUtils.creatWordByTemplate("ftl.ftl", dataMap);


    }

    @Test
    public void wordTest() {
        Map<String, Object> dataMap = new HashMap<>();
        try {
            dataMap.put("title", "test");
            dataMap.put("name", "lim59");
            dataMap.put("year", "1994");
            dataMap.put("month", "1");
            dataMap.put("day", "1");
            Configuration configuration = new Configuration(new Version("2.3.0"));
            configuration.setDefaultEncoding("utf-8");
            configuration.setDirectoryForTemplateLoading(new File("D:/"));
            //输出文档路径及名称
            File outFile = new File("D:/" +
                    System.currentTimeMillis() +
                    "ftl.doc");
            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("ftl.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8), 10240);
            template.process(dataMap, out);
            out.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wordListTest() {
        Map<String, Object> dataMap = new HashMap<>();
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            int b1 = 0;
            for (int i = 0; i < 5; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("b", i);
                map.put("c", i);
                map.put("d", i);
                list.add(map);
            }
            dataMap.put("list", list);
            dataMap.put("title", "test");
            dataMap.put("name", "lim59");
            dataMap.put("year", 1994);
            dataMap.put("month", "1");
            dataMap.put("day", "1");
            dataMap.put("b1", b1 + 1);
            dataMap.put("b2", b1 + 2);
            dataMap.put("b3", b1 + 3);
            dataMap.put("img", Base64Utils.ImageFilePathBase64("D:/img.png"));
            List<Map> taskList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                taskList.add(dataMap);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("bigLists", taskList);
            Configuration configuration = new Configuration(new Version("2.3.0"));
            configuration.setDefaultEncoding("utf-8");
            configuration.setDirectoryForTemplateLoading(new File("/"));
            //输出文档路径及名称
            String pathName = "/" +
                    "list" + System.currentTimeMillis() +
                    ".docx";
            File outFile = new File(pathName);
            //以utf-8的编码读取ftl文件
            System.out.println(pathName);
            Template template = configuration.getTemplate("list.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8), 10240);
            template.process(map, out);
            out.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void img() {
        System.out.println("data:image/png;base64," + Base64Utils.ImageFilePathBase64("D:/img.png"));
    }


}
