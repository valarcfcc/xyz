package com.valarcfcc.xyz.UtilsTest;

import com.valarcfcc.xyz.utils.DocxUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author valarcfcc
 */

@SpringBootTest
@Slf4j
public class WordUtilsTest {
    @Test
    public void MergeTest(){
        String template="D:/docs";
        List<String> list= new ArrayList<>();
        list.add(template+"/1.docx");
        list.add(template+"/2.docx");
        list.add(template+"/3.docx");
        list.add(template+"/4.docx");
        String outPath = DocxUtils.mergeWord(list);
        log.info(outPath);
    }
}
