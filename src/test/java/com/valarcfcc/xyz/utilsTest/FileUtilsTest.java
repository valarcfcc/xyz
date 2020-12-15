package com.valarcfcc.xyz.utilsTest;

import com.valarcfcc.xyz.utils.Common;
import com.valarcfcc.xyz.utils.FileUtils;
import com.valarcfcc.xyz.utils.Type;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static com.valarcfcc.xyz.utils.Common.println;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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

    @Test
    public void downloadFile() throws IOException {
        FileUtils.downLoadFromUrl(
                "https://marketplace.visualstudio.com/_apis/public/gallery/publishers/CoenraadS/vsextensions/bracket-pair-colorizer/1.0.61/vspackage",
                "bracket-pair-colorizer-1.0.61.zip","D:\\");
    }

    public static final String PATH = "D:\\Back\\vscode\\";
    @Test
    public void vscode(){
        JSONArray jsonArray = new JSONArray();
        jsonArray = getJsonArray("D:\\UserData\\Downloads\\1ef4e6e2dee8b230ed9b87365a664361-d388e65a21e4642f2d2bd84d1e76ed3f97dff946\\1ef4e6e2dee8b230ed9b87365a664361-d388e65a21e4642f2d2bd84d1e76ed3f97dff946\\extensions.json");
        HashMap<String,String> URLMap = new HashMap<>();

        for (int j=0;j<jsonArray.size();j++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(j);
            String name = jsonObject1.get("name").toString();
            String version = jsonObject1.get("version").toString();
            String publisher = jsonObject1.get("publisher").toString();
            URLMap.put(name+"-"+ version,"https://marketplace.visualstudio.com/_apis/public/gallery/publishers/" +publisher+
                    "/vsextensions/"+ name + "/"+ version+"/vspackage");
        }
        int i = 0;
        URLMap.forEach((name, url)->{
            try {
                Thread.sleep(300000);
                FileUtils.downLoadFromUrl(url,name+".vsix.zip",PATH);
            } catch (IOException | InterruptedException e) {
                println(name);
                println(url);
            }

        });


    }
    public static JSONArray getJsonArray(String url) {
        //jsonArray[]格式
        JSONArray jsonArray = new JSONArray();
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(url), "UTF-8"));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
            br.close();
            jsonArray = JSONArray.parseArray(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONObject getJsonObjectBy(String url){

        JSONObject jsonObject = new JSONObject();
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(url),"UTF-8"));
            String s = null;
            while((s = bufferedReader.readLine()) != null){
                result.append(System.lineSeparator() + s);
            }
            bufferedReader.close();
            jsonObject = JSONObject.parseObject(result.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

}
