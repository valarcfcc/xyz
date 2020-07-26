package com.valarcfcc.xyz.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * 
 * </p>
 *
 * @author valarcfcc
 * @since 2020-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder

@XmlAccessorType(value= XmlAccessType.FIELD)
@XmlRootElement
public class User implements Serializable {
    public User() {
    }

    public User(Long id, String name, Integer age, String email, Integer delFlag) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.delFlag = delFlag;
    }

//    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @XmlElement
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @XmlElement
    private String name;

    /**
     * 年龄
     */
    @XmlElement
    private Integer age;

    /**
     * 邮箱
     */
    @XmlElement
    private String email;

    /**
     * 逻辑删除
     */
    @XmlElement
    @TableLogic
    private Integer delFlag;


}
