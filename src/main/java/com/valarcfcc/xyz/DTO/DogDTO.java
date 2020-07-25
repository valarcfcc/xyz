package com.valarcfcc.xyz.DTO;

import com.valarcfcc.xyz.api.entity.Dog;
import com.valarcfcc.xyz.api.entity.User;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/23 12:52
 * @Description:
 */
@Data
@XmlAccessorType(value= XmlAccessType.FIELD)
@XmlRootElement
public class DogDTO {
    @XmlElement
    private String name ;
    @XmlElement
    private List<Dog> dogList;
    private Dog dog;
    @XmlElement
    private List<DogList> listList;
    @XmlElement
    private Map dogMap;



}
