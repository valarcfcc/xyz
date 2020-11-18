package com.valarcfcc.xyz.api.entity;
import static com.valarcfcc.xyz.utils.Common.println;
public class BMM extends Car{
    @Override
    public void run(){
        println("BMM run!");
    }

    @Override
    public void print(String... names) {
        super.print(names);
    }

    public void song(){
        println("BMM song!");
    }
}
