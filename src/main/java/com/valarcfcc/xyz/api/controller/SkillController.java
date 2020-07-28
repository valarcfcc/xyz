package com.valarcfcc.xyz.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author valarcfcc
 * @since 2020-02-22
 */
@Api(value = "用户Controller")
@Controller
@RequestMapping("/api/skill")
public class SkillController {

    @ApiOperation(value = "hello", notes = "Hello")
    @GetMapping("hello")
    public @ResponseBody
    String hello() {
        return "hello";
    }
}
