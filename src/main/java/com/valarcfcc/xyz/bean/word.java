package com.valarcfcc.xyz.bean;

import lombok.Data;

import java.util.List;

@Data
public class word {
    private String title;
    private String name;
    private String year;
    private String month;
    private String day;
    private String b1;
    private String b2;
    private String b3;
    private String img;
    private List<Abc> list;
}
