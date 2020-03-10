package com.valarcfcc.xyz.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.valarcfcc.xyz.api.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author valarcfcc
 * @since 2020-02-16
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectPageVo(Page<?> page, @Param("age") Integer age);

}
