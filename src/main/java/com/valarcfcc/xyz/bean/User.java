package com.valarcfcc.xyz.bean;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class User {
    // 用户名

    private Long id;
    @NotBlank
    private String name;
    private Integer age;
    private String email;

}