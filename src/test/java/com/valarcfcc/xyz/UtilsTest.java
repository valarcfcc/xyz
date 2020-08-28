package com.valarcfcc.xyz;

import com.valarcfcc.xyz.utils.Base64Utils;
import com.valarcfcc.xyz.utils.ExcelUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UtilsTest {
    @Test
    public void excelTest() {
        String filePath = "D://test.xlsx";
        List<HashMap<Integer, String>> list = ExcelUtils.excel2MapList(filePath, 5, 5);
        list.forEach(System.out::println);
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
            File outFile = new File("D:/ftl.doc");
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
            int b2 = 0;
            int b3 = 0;
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
            Configuration configuration = new Configuration(new Version("2.3.0"));
            configuration.setDefaultEncoding("utf-8");
            configuration.setDirectoryForTemplateLoading(new File("/"));
            //输出文档路径及名称
            File outFile = new File("/list.docx");
            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("list.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8), 10240);
            template.process(dataMap, out);
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
