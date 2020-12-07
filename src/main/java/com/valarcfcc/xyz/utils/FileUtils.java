package com.valarcfcc.xyz.utils;

import java.io.*;
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param src 源文件
     * @param dec 目标文件
     */
    public static void copyUnicodeFile(String src, String dec) throws IOException {
        //为了提高效率，创建缓冲用的字符数组
        char[] buffer = new char[1024];
        int temp;
        if ((new File(src)).exists()) {
            try (FileReader inputFile = new FileReader(src);
                 FileWriter outputStream = new FileWriter(dec)) {
                while ((temp = inputFile.read(buffer)) != -1) {
                    /*将缓存数组中的数据写入文件中，注意：写入的是读取的真实长度；
                     *outputStream.write(buffer)方法，那么写入的长度将会是1024，即缓存
                     *数组的长度*/
                    outputStream.write(buffer, 0, temp);
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
     *
     * @param src 源文件
     * @param dec 目标文件
     */
    public static void copyFile(String src, String dec) throws IOException {
        byte[] buffer = new byte[1024];
        int temp;
        if ((new File(src)).exists()) {
            try (FileInputStream inputFile = new FileInputStream(src);
                 FileOutputStream outputStream = new FileOutputStream(dec)) {
                while ((temp = inputFile.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, temp);
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
        try (FileInputStream inputFile = new FileInputStream(src);
             FileOutputStream outputStream = new FileOutputStream(dec)) {
            while ((temp = inputFile.read()) != -1) {
                outputStream.write(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将src文件的内容拷贝到dec文件
     *
     * @param src 源文件
     * @param dec 目标文件
     */
    public static void copyFileBuffered(String src, String dec) throws IOException {
        int temp;
        if ((new File(src)).exists()) {
            //使用缓冲字节流包装文件字节流，增加缓冲功能，提高效率
            //缓存区的大小（缓存数组的长度）默认是8192，也可以自己指定大小
            try (FileInputStream inputStream = new FileInputStream(src);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                 FileOutputStream outputStream = new FileOutputStream(dec);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
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
     *
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
}

