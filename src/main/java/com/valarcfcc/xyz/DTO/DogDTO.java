package com.valarcfcc.xyz.DTO;

import com.valarcfcc.xyz.api.entity.Dog;
import com.valarcfcc.xyz.api.entity.User;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/23 12:52
 * @Description:
 */
@Data
public class DogDTO {
    private String name ;
    private List<Dog> dogList;
    private Dog dog;
    private List<List> listList;
    private Map dogMap;
}
