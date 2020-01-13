package com.valarcfcc.xyz.controller;

import com.alibaba.fastjson.JSONObject;


import com.valarcfcc.xyz.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

  @GetMapping("/test")
  public String test() {
    System.out.println(123);
    Date date = new Date();
    date.getTime();
    System.out.println(date.toString());

    Object obj = new Date();
    Object object = new Object();
    System.out.println(obj.toString());
    System.out.println(object.toString());
    Date date1 = (Date)obj;
    date1.getTime();
    User user = new User();
    user.setName("lim59");
    String s1 = "123";
    String s2 = "234";
    str(s1,user);
    System.out.println(s1);
    System.out.println(user.getName());
    return "hello lim59 理想一哥";
  }

  public  void str(String s1, User s2){
    s1 = "234";
    s2.setName("lim");
  }
  @PostMapping("/posttest")
  public String postTest(@RequestBody String name) {
    System.out.println(name.toString());
    Date date = new Date();
    date.getTime();

    Object obj = new Date();
    Date date1 = (Date)obj;
    date1.getTime();
    return "hello " + name;
  }

  @PostMapping("/user")
  public String user(@RequestBody User user) {
    System.out.println(12345);
    // System.out.println(jsonParam.toJSONString());
    // User user =
    // (User)JSONObject.parseObject(jsonParam.toJSONString(),User.class);
    System.out.println(user.toString());
    return "hello " + user.getName();
  }

  @PostMapping(value = "/xml", headers = { "content-type=application/xml" })
  public String xml(@RequestBody User user) {
    System.out.println(user.toString());
    return user.getName();
  }
}