package com.valarcfcc.xyz.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author valarcfcc
 * @since 2020-02-16
 */
public interface IUserService extends IService<User> {

    public IPage<User> selectUserPage(Page<User> page, Integer state) ;

}
