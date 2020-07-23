package com.valarcfcc.xyz.DTO;

import com.valarcfcc.xyz.api.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/23 09:07
 * @Description:
 */
@Data
public class TestDTO {
    private String name ;
    private List<User> userList;
    private User user;
}
