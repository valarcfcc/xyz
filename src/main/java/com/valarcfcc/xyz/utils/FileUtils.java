package com.valarcfcc.xyz.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class FileUtils extends org.apache.commons.io.FileUtils {

    public static void getFilesNotIgnore(Map<String, File> fileMap, String path, List<String> ignoreDir, List<String> ignoreFile) {
        try {
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File fileIndex : files) {
                        if (fileIndex.isDirectory()) {
                            if (!ignoreDir.contains(fileIndex.getName())) {
                                getFiles(fileMap, fileIndex.getPath());
                            }
                        } else {
                            if (!ignoreFile.contains(fileIndex.getName())) {
                                fileMap.put(fileIndex.getPath(), fileIndex);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 描述:  获取文件列表
     * @param fileList 文件列表
     * @param fileMap  文件名-文件
     * @param path     路径
     * @author valarcfcc
     * @since 2020/11/26 19:07
     */
    public static void getFiles(List<File> fileList, Map<String, File> fileMap, String path) {
        try {
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File fileIndex : files) {
                        if (fileIndex.isDirectory()) {
                            getFiles(fileList, fileMap, fileIndex.getPath());
                        } else {
                            fileList.add(fileIndex);
                            fileMap.put(fileIndex.getPath(), fileIndex);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 描述:  获取文件列表
     * @param fileList 文件列表
     * @param path     路径
     * @author valarcfcc
     * @since 2020/11/26 19:07
     */
    public static void getFiles(List<File> fileList, String path, Type type) {
        try {
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File fileIndex : files) {
                        if (fileIndex.isDirectory()) {
                            if (Type.FILE.compareTo(type) > 0)
                                getFiles(fileList, fileIndex.getPath(), Type.FILE);
                        } else {
                            fileList.add(fileIndex);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 描述:  获取文件列表
     * @param fileMap 文件名-文件
     * @param path    路径
     * @author valarcfcc
     * @since 2020/11/26 19:07
     */
    public static void getFiles(Map<String, File> fileMap, String path) {
        try {
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File fileIndex : files) {
                        if (fileIndex.isDirectory()) {
                            getFiles(fileMap, fileIndex.getPath());
                        } else {
                            fileMap.put(fileIndex.getPath(), fileIndex);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 描述:  获取文件列表
     * @param fileList 文件名列表
     * @param path     路径
     * @author valarcfcc
     * @since 2020/11/26 19:07
     */
    public static void getFiles(List<String> fileList, String path) {
        try {
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File fileIndex : files) {
                        if (fileIndex.isDirectory()) {
                            getFiles(fileList, fileIndex.getPath());
                        } else {
                            fileList.add(fileIndex.getPath());
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 将src文件的内容拷贝到dec文件
     * @param src 源文件
     * @param dec 目标文件
     */
    public static void copyUnicodeFile(String src, String dec) throws IOException {
        //为了提高效率，创建缓冲用的字符数组
        char[] buffer = new char[1024];
        int temp;
        if( (new File(src)).exists()){
            try(FileReader inputFile = new FileReader(src);
                FileWriter outputStream = new FileWriter(dec)){
                while ((temp = inputFile.read(buffer))!=-1){
                    /*将缓存数组中的数据写入文件中，注意：写入的是读取的真实长度；
                     *outputStream.write(buffer)方法，那么写入的长度将会是1024，即缓存
                     *数组的长度*/
                    outputStream.write(buffer,0,temp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IOException("源文件不存在！");
        }

    }

    /**
     * 将src文件的内容拷贝到dec文件
     * @param src 源文件
     * @param dec 目标文件
     */
    public static void copyFile(String src, String dec) throws IOException {
        byte[] buffer = new byte[1024];
        int temp;
        if( (new File(src)).exists()){
            try(FileInputStream inputFile = new FileInputStream(src);
                FileOutputStream outputStream = new FileOutputStream(dec)){
                while ((temp = inputFile.read(buffer))!=-1){
                    outputStream.write(buffer,0,temp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IOException("源文件不存在！");
        }

    }
    public static void copyFile2(String src, String dec) {
        int temp;
        try(FileInputStream inputFile = new FileInputStream(src);
            FileOutputStream outputStream = new FileOutputStream(dec)){
            while ((temp = inputFile.read()) != -1) {
                outputStream.write(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 将src文件的内容拷贝到dec文件
     * @param src 源文件
     * @param dec 目标文件
     */
    public static void copyFileBuffered(String src, String dec) throws IOException {
        int temp;
        if( (new File(src)).exists()){
            //使用缓冲字节流包装文件字节流，增加缓冲功能，提高效率
            //缓存区的大小（缓存数组的长度）默认是8192，也可以自己指定大小
            try(FileInputStream inputStream = new FileInputStream(src);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                FileOutputStream outputStream = new FileOutputStream(dec);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)){
                while ((temp = bufferedInputStream.read()) != -1) {
                    bufferedOutputStream.write(temp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IOException("源文件不存在！");
        }
    }
    /**
     * 将src文件的内容拷贝到dec文件
     * @param src 源文件
     * @param dec 目标文件
     */
    public static void copyUnicodeFileBuffered(String src, String dec) throws IOException {
        String tempString;
        if ((new File(src)).exists()) {
            //使用缓冲字节流包装文件字节流，增加缓冲功能，提高效率
            //缓存区的大小（缓存数组的长度）默认是8192，也可以自己指定大小
            try (FileReader inputStream = new FileReader(src);
                 BufferedReader bufferedReader = new BufferedReader(inputStream);
                 FileWriter outputStream = new FileWriter(dec);
                 BufferedWriter bufferedWriter = new BufferedWriter(outputStream)) {
                //BufferedReader提供了更方便的readLine()方法，直接按行读取文本
                //br.readLine()方法的返回值是一个字符串对象，即文本中的一行内容
                while ((tempString = bufferedReader.readLine()) != null) {
                    //将读取的一行字符串写入文件中
                    bufferedWriter.write(tempString);
                    //下次写入之前先换行，否则会在上一行后边继续追加，而不是另起一行
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IOException("源文件不存在！");
        }
    }

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }


        System.out.println("info:"+url+" download success");

    }



    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        try{
            downLoadFromUrl("http://101.95.48.97:8005/res/upload/interface/apptutorials/manualstypeico/6f83ce8f-0da5-49b3-bac8-fd5fc67d2725.png",
                    "百度.jpg","d:/resource/images/diaodiao/country/");
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
}

