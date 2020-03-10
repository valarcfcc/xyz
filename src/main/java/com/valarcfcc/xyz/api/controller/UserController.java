package com.valarcfcc.xyz.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.valarcfcc.xyz.api.entity.User;
import com.valarcfcc.xyz.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author valarcfcc
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/page")
    public Object selectUserPage(Page page, Integer age) {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.eq("age",age);
        return userService.page(page,wrapper);

    }
    @GetMapping(value = "/selectPage")
    public Object selectPage(Page page, Integer age) {
        return userService.selectUserPage(page,age);
    }


}
