package com.valarcfcc.xyz.api.service.impl;

import com.valarcfcc.xyz.api.entity.User;
import com.valarcfcc.xyz.api.mapper.UserMapper;
import com.valarcfcc.xyz.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author valarcfcc
 * @since 2020-02-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
