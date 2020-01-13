package com.valarcfcc.xyz.bean;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
  // 用户名
  @NotBlank
  private String name;

}