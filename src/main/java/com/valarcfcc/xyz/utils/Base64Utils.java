package com.valarcfcc.xyz.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.stream.FileImageInputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/***
 * 图片字符串转换
 */
public class Base64Utils {
    /**
     * 字符串转图片
     * @param base64Str Base64字符串
     * @return 图片字节流
     */
    public static byte[] decode(String base64Str){
        byte[] b = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            b = decoder.decodeBuffer(replaceEnter(base64Str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 图片转字符串
     * @param image 图片
     * @return Base64字符串
     */
    public static String encode(byte[] image){
        BASE64Encoder decoder = new BASE64Encoder();
        return replaceEnter(decoder.encode(image));
    }

    public static String encode(String uri){
        BASE64Encoder encoder = new BASE64Encoder();
        return replaceEnter(encoder.encode(uri.getBytes()));
    }

    /**
     *
     * @param path     图片路径
     * @return 图片
     */

    public static byte[] imageTobyte(String path){
        byte[] data = null;
        FileImageInputStream input;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead;
            while((numBytesRead = input.read(buf)) != -1){
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }



    public static String replaceEnter(String str){
        String reg ="[\n-\r]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    public static String ImageFilePathBase64(String imgFile) {
        String base64Str = null;
        File file = new File(imgFile);
        try {
            base64Str = getImageBase64String(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64Str;
    }

    /**
     * 远程读取image转换为Base64字符串
     * @param imgUrl =图片地址全路径
     * @return Base64字符串
     */
    public static String ImageUrlBase64(String imgUrl) {
        URL url;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;
        try{
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();

            outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len;
            //使用一个输入流从buffer里把数据读取出来
            while( (len=is.read(buffer)) != -1 ){
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            // 对字节数组Base64编码
            return new BASE64Encoder().encode(outStream.toByteArray());
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outStream != null)
            {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpUrl != null)
            {
                httpUrl.disconnect();
            }
        }
        return imgUrl;
    }

    /**
     * 获取图片对应的base64码（以文件的形式）
     * @throws IOException 读写错误
     */
    public static String getImageBase64String(File imgFile) throws IOException {
        InputStream inputStream = new FileInputStream(imgFile);
        byte[] data = new byte[inputStream.available()];
        int totalNumberBytes = inputStream.read(data);
        BASE64Encoder encoder = new BASE64Encoder();
        return replaceEnter(encoder.encode(data));
    }


}