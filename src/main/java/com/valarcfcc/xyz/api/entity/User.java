package com.valarcfcc.xyz.api.entity;

import lombok.Data;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/28 22:45
 * @Description:
 */
@Data
public class User {
    private Integer age;
    private Long id;
    private String name;
    public void useCar(Car car){
        car.run();
    }

}
