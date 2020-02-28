package com.valarcfcc.xyz.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author valarcfcc
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "skill_id", type = IdType.AUTO)
    private Integer skill_id;

    /**
     * 技能类型：0，语言；1，数据库；2，框架；3，系统；4，解决方案；5，中间件；6，业务类
     */
    private Integer skill_type;

    private String skill_name;

    private LocalDateTime skill_create_date;

    private LocalDateTime skill_update_date;


}
