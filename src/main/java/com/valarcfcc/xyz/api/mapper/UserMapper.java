package com.valarcfcc.xyz.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.valarcfcc.xyz.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author valarcfcc
 * @since 2020-02-16
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectPageVo(Page<?> page, Integer age);

}
