package com.valarcfcc.xyz.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  描述:
 *  @author valarcfcc
 *  @since 2020/8/31 22:56
 */
public class DocxUtils {
    private static final String PATH = "/docs/";
    private static final String SUFFIX = ".docx";

    /**
    * 功能描述: 合并word文件，不支持文件里有表格
    * @author  valarcfcc
    * @since   2020/8/31 22:56
    * @param  filePathList 读取文件路径
    * @return   outFilePath
    */
    public static String mergeWord(List<String> filePathList) {

        String outFileName = null;
        if (!filePathList.isEmpty()) {
            List<File> srcFileList = new ArrayList<>();
            for (String filePath : filePathList) {
                srcFileList.add(new File(filePath));
            }
            try {
                outFileName = PATH + System.currentTimeMillis() + SUFFIX;
                File outFile = new File(outFileName);
                OutputStream out = new FileOutputStream(outFile);
                ArrayList<XWPFDocument> documentList = new ArrayList<>();
                XWPFDocument doc = null;
                for (File file : srcFileList) {
                    FileInputStream in = new FileInputStream(file.getPath());
                    OPCPackage open = OPCPackage.open(in);
                    XWPFDocument document = new XWPFDocument(open);
                    documentList.add(document);
                }
                for (int i = 0; i < documentList.size(); i++) {
                    doc = documentList.get(0);
                    documentList.get(i).createParagraph().setPageBreak(true);
                    if (i != 0) {
                        appendBody(doc, documentList.get(i));
                    }
                }
                if (doc != null) {
                    doc.write(out);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }

        return outFileName;

    }

    public static void appendBody(XWPFDocument src, XWPFDocument append) throws Exception {
        CTBody src1Body = src.getDocument().getBody();
        CTBody src2Body = append.getDocument().getBody();

        List<XWPFPictureData> allPictures = append.getAllPictures();
        // 记录图片合并前及合并后的ID
        Map<String, String> map = new HashMap<>();
        for (XWPFPictureData picture : allPictures) {
            String before = append.getRelationId(picture);
            //将原文档中的图片加入到目标文档中
            String after = src.addPictureData(picture.getData(), Document.PICTURE_TYPE_PNG);
            map.put(before, after);
        }

        appendBody(src1Body, src2Body, map);

    }

    private static void appendBody(CTBody src, CTBody append, Map<String, String> map) throws Exception {
        XmlOptions optionsOuter = new XmlOptions();
        optionsOuter.setSaveOuter();
        String appendString = append.xmlText(optionsOuter);

        String srcString = src.xmlText();
        String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
        String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));
        String sufix = srcString.substring(srcString.lastIndexOf("<"));
        String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));

        if (map != null && !map.isEmpty()) {
            //对xml字符串中图片ID进行替换
            for (Map.Entry<String, String> set : map.entrySet()) {
                addPart = addPart.replace(set.getKey(), set.getValue());
            }
        }
        //将两个文档的xml内容进行拼接
        CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + sufix);
        src.set(makeBody);
    }


    public static String creatWordByTemplate(String templateName,Map<String, Object> dataMap){
        try {
            URL url = DocxUtils.class.getClassLoader().getResource("templates/");
            String templatePath = null;
            if (url != null) {
                templatePath = url.getFile();
            }
            Configuration configuration = new Configuration(new Version("2.3.0"));
            configuration.setDefaultEncoding("utf-8");

            configuration.setDirectoryForTemplateLoading(new File(templatePath));
            //输出文档路径及名称
            File outFile = new File("D:/" +
                    System.currentTimeMillis()+
                    "ftl.doc");
            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate(templateName, "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8), 10240);
            template.process(dataMap, out);
            out.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return null;
    }

}
