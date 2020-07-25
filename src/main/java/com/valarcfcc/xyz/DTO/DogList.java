package com.valarcfcc.xyz.DTO;

import com.valarcfcc.xyz.api.entity.Dog;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/24 14:08
 * @Description:
 */
@Data
public class DogList {
    private List<Dog> dogListInfo;
    private String xxx;
}
