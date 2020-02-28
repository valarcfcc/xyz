package com.valarcfcc.xyz.api;

import com.valarcfcc.xyz.api.entity.User;
import com.valarcfcc.xyz.api.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: valarcfcc
 * @Date: 2020/2/27 22:27
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(10, userList.size());
        userList.forEach(System.out::println);
    }
}
