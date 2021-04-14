package com.valarcfcc.xyz.bean;

import lombok.Data;

@Data
public class Code {
    public Code(String path){
        this.path = path;

    }
    String path;
}
