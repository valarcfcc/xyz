package com.valarcfcc.xyz;

import com.valarcfcc.xyz.utils.ExcelUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UtilsTest {
    @Test
    public void excelTest(){
        String filePath = "D://test.xlsx";
        List<HashMap<Integer,String>> list  = ExcelUtils.excel2MapList(filePath,5,5);
        list.forEach(System.out::println);
    }
    @Test
    public void wordTest(){
        Map<String,Object> dataMap = new HashMap<String, Object>();
        try {
            dataMap.put("title","test");
            dataMap.put("name","lim59");
            dataMap.put("year",1994);
            dataMap.put("month","1");
            dataMap.put("day","1");
            Configuration configuration = new Configuration(new Version("2.3.0"));
            configuration.setDefaultEncoding("utf-8");
            configuration.setDirectoryForTemplateLoading(new File("D:/"));
            //输出文档路径及名称
            File outFile = new File("D:/ftl.doc");
            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("ftl.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            template.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
