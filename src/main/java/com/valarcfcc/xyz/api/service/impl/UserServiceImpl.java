package com.valarcfcc.xyz.api.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.valarcfcc.xyz.api.entity.User;
import com.valarcfcc.xyz.api.service.UserService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: valarcfcc
 * @Date: 2020/11/18 00:02
 * @Description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public void excelExport(HttpServletResponse response) throws IOException {
//        List<User> list = userDao.queryAllUsers();
//        String fileName = "用户名单";
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + ".xls");
//        ServletOutputStream out = response.getOutputStream();
//        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS,true);
//        Sheet sheet = new Sheet(1,0,User.class);
//        //设置自适应宽度
//        sheet.setAutoWidth(Boolean.TRUE);
//        sheet.setSheetName("用户名单");
//        writer.write(list,sheet);
//        writer.finish();
//        out.flush();
//        response.getOutputStream().close();
//        out.close();
    }
}
