package com.valarcfcc.xyz.api.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/28 22:45
 * @Description:
 */
@Data
public class User extends BaseRowModel {
    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;
    @ExcelProperty(value = "ID",index = 0)
    private Long id;
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
    public void useCar(Car car){
        car.run();
    }

}
