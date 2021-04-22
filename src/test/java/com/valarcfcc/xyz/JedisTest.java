package com.valarcfcc.xyz;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JedisTest {
@Test
    public void jedistest(){
    Jedis jedis = new Jedis("192.168.150.128");
    // 如果 Redis 服务设置来密码，需要下面这行，没有就不需要
     jedis.auth("123456");
    System.out.println("连接成功");
    //查看服务是否运行
    System.out.println("服务正在运行: "+jedis.ping());
    jedis.set("runoobkey", "www.runoob.com");
    // 获取存储的数据并输出
    System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    jedis.lpush("site-list", "Runoob");
    jedis.lpush("site-list", "Google");
    jedis.lpush("site-list", "Taobao");
    // 获取存储的数据并输出
    List<String> list = jedis.lrange("site-list", 0 ,2);
    for(int i=0; i<list.size(); i++) {
        System.out.println("列表项为: "+list.get(i));
    }
    // 获取数据并输出
    Set<String> keys = jedis.keys("*");
    Iterator<String> it=keys.iterator() ;
    while(it.hasNext()){
        String key = it.next();
        System.out.println(key);
    }

}
}
