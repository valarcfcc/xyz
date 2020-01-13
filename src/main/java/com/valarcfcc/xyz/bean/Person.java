package com.valarcfcc.xyz.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {
  @Length(max = 20, message = "姓名长度不能大于20")
  @NotEmpty(message = "姓名不能为空")
  private String name;
  @Range(min = 0, max = 1, message = "性别只能输入只能输入0或1")
  private Integer gender;
  private Integer age;
  // @Pattern(regexp = "{s,b}", message = "用户名必须以字母下划线开头，可由字母数字下划线组成")
  // private String username;
}