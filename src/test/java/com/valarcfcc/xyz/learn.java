package com.valarcfcc.xyz;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static com.valarcfcc.xyz.utils.Common.println;
public class learn {
    @Test
    public void lambdaTest() {
        MathOperation add = (int a, int b) -> a + b;
        println("10 + 5 = " + operation(10, 5, add));
        println("10 * 5 = " + operation(10, 5, (a, b) -> a * b));
        GreetingService greetingService = message -> println("hello:" + message);
        greetingService.sayMessage("sss");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operation(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
    @Test
    public void functionTest(){
        Consumer<String> consumer = System.out::println;
        consumer.accept("hi");
        ToIntFunction<String> intFunction = String::length;
        println( intFunction.applyAsInt("adswsdad"));
    }
    @Test
    public void streamTest(){
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        random.ints().limit(10).sorted().forEach(System.out::println);
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
    @Test
    public void optionalTest(){
        Integer value1 = null;
        Integer value2 = new Integer(10);
        Optional<Integer> a = Optional.ofNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        println(sum(a,b));
    }
    public Integer sum(Optional<Integer> a,Optional<Integer> b){
        println("第一个参数值存在：" + a.isPresent());
        println("第二个参数值存在：" + b.isPresent());
        Integer value1 = a.orElse(new Integer(0));
        Integer value2 = b.orElse(new Integer(0));
        return value1 + value2;
    }
    @Test
    public void localDateTimeTest(){
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month.getValue() +", 日: " + day +", 秒: " + seconds);
        // withYear修改年 withDayOfMonth修改当月日
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
    @Test
    public void baseTest(){
        try {

            // 使用基本编码
            String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);

            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

            System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));
            base64encodedString = Base64.getUrlEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);

        }catch(UnsupportedEncodingException e){
            System.out.println("Error :" + e.getMessage());
        }
    }

    @Test
    public void forTest(){
        List<String> stringList = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++){
            stringList.add(String.valueOf(i));
        }
        Long date1 = System.currentTimeMillis();
        int size = stringList.size();
        for (int i = 0; i <size;i++){
            String q = stringList.get(i)+i+i;
        }
        Long date2 = System.currentTimeMillis();
        println(date2-date1);
    }


}
