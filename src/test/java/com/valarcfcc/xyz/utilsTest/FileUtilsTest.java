package com.valarcfcc.xyz.utilsTest;

import com.valarcfcc.xyz.utils.Common;
import com.valarcfcc.xyz.utils.FileUtils;
import com.valarcfcc.xyz.utils.Type;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.valarcfcc.xyz.utils.Common.println;

public class FileUtilsTest {
    @Test
    public void getFiles1() {
        List<String> fileList = new ArrayList<>();
        String path = "C:\\Users\\cheng\\OneDrive\\blog";
        FileUtils.getFiles(fileList, path);
        fileList.forEach(Common::println);
    }
    @Test
    public void filePathTest() {
        Map<String,File> oldFileMap = new HashMap<>();
        String oldPath = "";
        List<String> ignoreFile = new ArrayList(){{

        }};
        List<String> ignoreDir = new ArrayList(){{

        }};
        FileUtils.getFilesNotIgnore(oldFileMap,oldPath,ignoreDir,ignoreFile);
        Map<String,File> newFileMap = new HashMap<>();
        String newPath = "";
        FileUtils.getFilesNotIgnore(newFileMap,newPath,ignoreDir,ignoreFile);
        List<String> codeNewList = new ArrayList<>();
        List<String> codeModList = new ArrayList<>();
        newFileMap.forEach((key,file)->{
            if(!oldFileMap.containsKey(key.replace(newPath,oldPath))){
                codeNewList.add(key.replace(newPath,oldPath));
            } else {
                try {
                    if (!FileUtils.contentEquals(file,oldFileMap.get(key.replace(newPath,oldPath)))){
                        codeModList.add(key.replace(newPath,oldPath));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Collections.sort(codeModList);
        Collections.sort(codeNewList);

    }

    @Test
    public void getFiles2() {
        List<File> fileList = new ArrayList<>();
        Map<String, File> fileMap = new HashMap<>();
        String path = "C:\\Users\\cheng\\OneDrive\\blog";
        FileUtils.getFiles(fileList, fileMap, path);
        fileList.forEach(file -> {
            println(file.getName());
            println(file.getPath());
        });
        fileMap.forEach((key, value) -> println(key + "：" + value.getPath()));
    }

    @Test
    public void getFiles3() {
        List<File> fileList = new ArrayList<>();
        String path = "C:\\Users\\cheng\\OneDrive\\blog";
        FileUtils.getFiles(fileList, path, Type.FILE);
        fileList.forEach(file -> {
            println(file.getName());
            println(file.getPath());
        });
    }

    @Test
    public void getFiles4() {
        Map<String, File> fileMap = new HashMap<>();
        String path = "C:\\Users\\cheng\\OneDrive\\blog";
        FileUtils.getFiles(fileMap, path);
        fileMap.forEach((key, value) -> println(key + "：" + value.getPath()));
    }
    @Test
    public void copeFile1(){
        String src = "C:\\Users\\cheng\\OneDrive\\blog\\设计模式之禅.md";
        String dec = "D:\\设计模式之禅.md";
        try {
            FileUtils.copyFile(src,dec);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void copeFile2(){
        long time1 = System.currentTimeMillis();
        try {
            FileUtils.copyFileBuffered("D:\\【Top002】教父.The.Godfather.1972.mkv","D:\\教父.The.Godfather.1.mkv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println("缓冲字节流花费的时间为：" + (time2 - time1));

        // 使用普通字节流实现复制
        long time3 = System.currentTimeMillis();
        try {
            FileUtils.copyFile("D:\\【Top002】教父.The.Godfather.1972.mkv","D:\\教父.The.Godfather.2.mkv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time4 = System.currentTimeMillis();
        System.out.println("缓冲区字节流花费的时间为：" + (time4 - time3));

        // 使用普通字节流实现复制
        long time5 = System.currentTimeMillis();
        FileUtils.copyFile2("D:\\【Top002】教父.The.Godfather.1972.mkv","D:\\教父.The.Godfather.3.mkv");
        long time6 = System.currentTimeMillis();
        System.out.println("普通字节流花费的时间为：" + (time6 - time5));
    }
}
