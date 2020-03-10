package com.valarcfcc.xyz.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.valarcfcc.xyz.api.entity.User;
import com.valarcfcc.xyz.api.mapper.UserMapper;
import com.valarcfcc.xyz.api.service.IUserService;
import com.valarcfcc.xyz.utils.MD5Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void saveUser() {
        User user = User.builder().age(18).email("123456@qq.com").name("ming").build();
        boolean isSave = userService.save(user);
        Assert.assertTrue(isSave);
    }

    @Test
    public void SaveOrUpdateUser() {
        User user = User.builder().id(new Long("11")).age(20).email("123456@qq.com").name("ming").build();
        boolean isSave = userService.save(user);
        Assert.assertTrue(isSave);
    }

    @Test
    public void removeUser() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age", "18");
        boolean isRemoveById = userService.removeById(12);
        boolean isRemove = userService.remove(wrapper);
        Assert.assertTrue(isRemove);
        Assert.assertTrue(isRemoveById);
    }

    @Test
    public void UpdateUser() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age", "20");
        User user = User.builder().age(18).email("123456@qq.com").name("ming").build();
        User userById = User.builder().id(new Long("10")).age(77).email("qwerttt@qq.com").name("777").build();
        // 根据 whereEntity 条件，更新记录
        boolean isUpdateById = userService.updateById(userById);
        // 根据 ID 选择修改
        boolean isUpdate = userService.update(user, wrapper);
        Assert.assertTrue(isUpdate);
        Assert.assertTrue(isUpdateById);
    }

    @Test
    public void getUser() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age", "20");
        wrapper.last("LIMIT 1");
        // 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
        User user = userService.getOne(wrapper);
        User userById = userService.getById(5);
        System.out.println(user);
        System.out.println(userById);
    }

    @Test
    public void getPassword() {
        String name = "admin";
        String password = "123456";
        String newPassword = MD5Utils.encrypt(name, password);
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        System.out.println(newPassword);
    }

    @Test
    public void listUser() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age", "28");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Billie");
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(8);
        list.add(12);
        // 根据 Wrapper，查询结果集。
        List<User> userByWrapper = userService.list(wrapper);
        // 查询（根据 columnMap 条件）
        List<User> userByMap = userService.listByMap(map);
        // 查询（根据ID 批量查询）
        List<User> userByList = userService.listByIds(list);
        System.out.println(("----- Wrapper查询结果集 ------"));
        userByWrapper.forEach(System.out::println);
        System.out.println(("----- Map查询结果集 ------"));
        userByMap.forEach(System.out::println);
        System.out.println(("----- IdList查询结果集 ------"));
        userByList.forEach(System.out::println);
    }

    @Test
    public void pageUser() {
        IPage<User> page = new Page<>(1, 2);

        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age", 28);
        List<User> userList = userService.page(page, wrapper).getRecords();
        userList.forEach(System.out::println);

    }

    @Test
    public void insertUserMapper() {
        User user = User.builder().age(18).email("123456@qq.com").name("小明").build();
        int isSave = userMapper.insert(user);
        Assert.assertEquals(1, isSave);
    }

    @Test
    public void deleteUserMapper() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age", 28);
        int isSave = userMapper.delete(wrapper);
        Assert.assertEquals(1, isSave);
    }

    @Test
    public void updateUserMapper() {
        User user = User.builder().age(128).email("123456@qq.com").name("小明").id(new Long(13)).build();
        int isUpdate = userMapper.updateById(user);
        Assert.assertEquals(1, isUpdate);
    }

    @Test
    public void selectUserMapper() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age", "128");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Billie");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(13);
        // 根据 Wrapper，查询结果集。
        List<User> userByWrapper = userMapper.selectList(wrapper);
        // 查询（根据 columnMap 条件）
        List<User> userByMap = userMapper.selectByMap(map);
        // 查询（根据ID 批量查询）
        List<User> userByList = userMapper.selectBatchIds(list);
        System.out.println(("----- Wrapper查询结果集 ------"));
        userByWrapper.forEach(System.out::println);
        System.out.println(("----- Map查询结果集 ------"));
        userByMap.forEach(System.out::println);
        System.out.println(("----- IdList查询结果集 ------"));
        userByList.forEach(System.out::println);
    }

    @Test
    public void page() {
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userService.selectUserPage(page, 128);
        List<User> userList = userIPage.getRecords();
        userList.forEach(System.out::println);

    }
}
