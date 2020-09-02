package com.valarcfcc.xyz.api.controller;


import com.valarcfcc.xyz.config.SysConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    @Resource
    private SysConfig sysConfig;
    @ApiOperation(value = "hello", notes = "Hello")
    @GetMapping("hello")
    public @ResponseBody
    String hello() {
        return sysConfig.getTempPath();
    }
}
