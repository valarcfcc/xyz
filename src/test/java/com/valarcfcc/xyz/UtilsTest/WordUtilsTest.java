package com.valarcfcc.xyz.UtilsTest;

import com.valarcfcc.xyz.utils.DocxUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;

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

    @Test
    public void merge() throws Exception {
        String template="D:";
        List<String> list= new ArrayList<>();
        list.add(template+"/list1598868226649.docx");
        list.add(template+"/list1598868271638.docx");
        mergeDoc(list,"D:/out.docx");

    }
    public static void mergeDoc(List<String> fileList, String newFilePath) throws Exception {
        try{
            Document document = new Document();
            //创建空白页（可不创建，用Document document = new Document("文件路径")）
//            document.createMinialDocument();
            //合并文档
            for(String path : fileList){
                document.insertTextFromFile(path, FileFormat.Docx_2010);
            }
            //保存文档
            document.saveToFile(newFilePath, FileFormat.Docx_2010);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
