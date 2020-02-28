package com.valarcfcc.xyz.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.valarcfcc.xyz.api.entity.User;
import com.valarcfcc.xyz.api.mapper.UserMapper;
import com.valarcfcc.xyz.api.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(10, userList.size());
        userList.forEach(System.out::println);
    }
    @Test
    public void saveUser(){
        User user = User.builder().age(18).email("123456@qq.com").name("ming").build();
        boolean isSave = userService.save(user);
        Assert.assertTrue(isSave);
    }
    @Test
    public void SaveOrUpdateUser(){
        User user = User.builder().id(new Long("11")).age(20).email("123456@qq.com").name("ming").build();
        boolean isSave = userService.save(user);
        Assert.assertTrue(isSave);
    }
    @Test
    public void removeUser(){
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age","18");
        boolean isRemoveById = userService.removeById(12);
        boolean isRemove = userService.remove(wrapper);
        Assert.assertTrue(isRemove);
        Assert.assertTrue(isRemoveById);
    }

    @Test
    public void UpdateUser(){
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age","20");
        User user = User.builder().age(18).email("123456@qq.com").name("ming").build();
        User userById = User.builder().id(new Long("10")).age(77).email("qwerttt@qq.com").name("777").build();
        // 根据 whereEntity 条件，更新记录
        boolean isUpdateById = userService.updateById(userById);
        // 根据 ID 选择修改
        boolean isUpdate = userService.update(user,wrapper);
        Assert.assertTrue(isUpdate);
        Assert.assertTrue(isUpdateById);
    }
    @Test
    public void getUser(){
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age","20");
        User user = User.builder().age(18).email("123456@qq.com").name("ming").build();
        User userById = User.builder().id(new Long("10")).age(77).email("qwerttt@qq.com").name("777").build();
        // 根据 whereEntity 条件，更新记录
        boolean isUpdateById = userService.updateById(userById);
        // 根据 ID 选择修改
        boolean isUpdate = userService.update(user,wrapper);
        Assert.assertTrue(isUpdate);
        Assert.assertTrue(isUpdateById);
    }

}
